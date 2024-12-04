package controller;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import character.Player;
import command.AddComputerPlayer;
import command.AddHumanPlayer;
import command.DisplayPlayerInformation;
import command.DisplaySpaceInformation;
import command.GenerateMap;
import command.LookAround;
import command.MakeAnAttempt;
import command.MovePet;
import command.MovePlayer;
import command.PickUpItem;
import command.WorldCommand;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import javax.swing.SwingUtilities;
import space.Space;
import view.View;
import world.MyWorld;
import world.World;

/**
 * The GameController represents the controller of the game.
 */
public class GameController implements Controller {
  private File currentFile;
  private World model;
  private View view;
  private Readable in;
  private Appendable out;
  private int turnLimit;
  private Map<String, Function<Scanner, WorldCommand>> initialCommands;
  private Map<String, Function<Scanner, WorldCommand>> commands;
  private Map<String, Function<Scanner, WorldCommand>> humanCommands;
  private Map<String, Function<World, WorldCommand>> computerCommands;
  private Map<Character, Runnable> keyTypes;
  private Map<String, Runnable> gameActions;
  
  /**
   * Constructor for the view-based game.
   * 
   * @param model      the model to use
   * @param view       the view to use
   * @param file       the world specification file
   * @param turnLimit  the maximum number of turns allowed
   */
  public GameController(World model, View view, File file, int turnLimit) {
    this.model = model;
    this.view = view;
    this.currentFile = file;
    this.turnLimit = turnLimit;
    computerCommands = new HashMap<>();
    computerCommands.put("automaticMovePlayer", m -> new MovePlayer(m));
    computerCommands.put("automaticPickUpItem", m -> new PickUpItem(m));
    computerCommands.put("automaticMakeAnAttempt", m -> new MakeAnAttempt(m));
    computerCommands.put("automaticMovePet", m -> new MovePet(m));
    computerCommands.put("lookAround", m -> new LookAround());
    configureKeyBoardListener();
    configureActionListener();
  }
  
  /**
   * Constructor for the text-based game.
   * 
   * @param in          the input stream of the controller
   * @param out         the output stream of the controller
   * @param turnLimit   the maximum number of turns allowed
   */
  public GameController(Readable in, Appendable out, int turnLimit) {
    this.in = in;
    this.out = out;
    this.turnLimit = turnLimit;
    // commands before adding players
    initialCommands = new HashMap<>();
    initialCommands.put("displaySpaceInformation", s -> new DisplaySpaceInformation(s, out));
    initialCommands.put("addComputerPlayer", s -> new AddComputerPlayer(s, out));  
    initialCommands.put("addHumanPlayer", s -> new AddHumanPlayer(s, out));
    initialCommands.put("generateMap", s -> new GenerateMap());
    // commands after adding at least one player
    commands = new HashMap<>(initialCommands);
    commands.put("displayPlayerInformation", s -> new DisplayPlayerInformation(s, out));
    // commands for human controlled players
    humanCommands = new HashMap<>(); 
    humanCommands.put("movePlayer", s -> new MovePlayer(s, out));
    humanCommands.put("pickUpItem", s -> new PickUpItem(s, out));
    humanCommands.put("makeAnAttempt", s -> new MakeAnAttempt(s, out));
    humanCommands.put("movePet", s -> new MovePet(s, out));
    humanCommands.put("lookAround", s -> new LookAround());
    // commands for computer controlled players
    computerCommands = new HashMap<>();
    computerCommands.put("automaticMovePlayer", m -> new MovePlayer(m));
    computerCommands.put("automaticPickUpItem", m -> new PickUpItem(m));
    computerCommands.put("automaticMakeAnAttempt", m -> new MakeAnAttempt(m));
    computerCommands.put("automaticMovePet", m -> new MovePet(m));
    computerCommands.put("lookAround", m -> new LookAround());
  }
  
  @Override
  public void start(World model) throws IOException {
    Objects.requireNonNull(model);
    Scanner scan = new Scanner(this.in);
    out.append(model.toString());
    out.append("Now, the game starts.\n");
    try {
      // Only four commands can be executed before the first player is added.
      while (model.getPlayers().size() < 1) {
        try {
          out.append("\nPlease enter one of the following commands:\n");
          out.append("displaySpaceInformation\naddComputerPlayer\naddHumanPlayer\ngenerateMap\n");
          WorldCommand c;
          String in = scan.nextLine();
          Function<Scanner, WorldCommand> cmd = initialCommands.getOrDefault(in, null);
          if (cmd == null) {
            throw new UnsupportedOperationException(String.format("Command %s not supported", in));
          } else {
            c = cmd.apply(scan);
            c.execute(model, out);
          }
        } catch (UnsupportedOperationException uoe) {
          // catch the incorrect command
          out.append(uoe.getMessage());
          continue;
        } catch (IllegalArgumentException iae) {
          // When an incorrect argument is inputed, an IllegalArgumentException will be thrown out.
          out.append(iae.getMessage());
          continue;
        }
      }
      
      // After the first player is added, set the turn to the first player.
      model.resetTurn();
      
      // Limit the maximum number of turns allowed.
      while (model.getTurnCount() < turnLimit) {
        try {
          out.append("\nPlease enter one of the following commands:\n");
          out.append("displaySpaceInformation\naddComputerPlayer\naddHumanPlayer\ngenerateMap\n");
          out.append("displayPlayerInformation\nnextTurn\n");
          String in = scan.nextLine();
          WorldCommand c;
          // If the input string is "nextTurn", get into the next turn,
          // else get and execute the corresponding commands.
          if ("nextTurn".equals(in)) {
            Player currentTurn = model.getTurn();
            out.append(String.format("Now, it is %s's turn\n", currentTurn.getName()));
            out.append(model.displaySpaceInformation(currentTurn.getSpace().getName()));
            int currentTurnCount = model.getTurnCount();
            // Do not skip the current turn when an incorrect argument is inputed.
            while (model.getTurnCount() == currentTurnCount) {
              try {
                // Use different command sets according to the type of player.
                if (currentTurn instanceof HumanControlledPlayer) {
                  out.append("\nPlease enter one of the following commands:\n");
                  out.append("movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n");
                  in = scan.nextLine();
                  Function<Scanner, WorldCommand> cmd = humanCommands.getOrDefault(in, null);
                  if (cmd == null) {
                    throw new UnsupportedOperationException(
                        String.format("Command %s not supported", in));
                  } else {
                    c = cmd.apply(scan);
                    c.execute(model, out);
                  }
                } else if (currentTurn instanceof ComputerControlledPlayer) {
                  in = ((ComputerControlledPlayer) currentTurn).getRandomOperation(model);
                  Function<World, WorldCommand> cmd = computerCommands.getOrDefault(in, null);
                  if (cmd == null) {
                    throw new UnsupportedOperationException(
                        String.format("Command %s not supported", in));
                  } else {
                    c = cmd.apply(model);
                    c.execute(model, out);
                  }
                }
                if (("makeAnAttempt".equals(in) || "automaticMakeAnAttempt".equals(in))
                    && model.getTargetCharacter().getHealth() <= 0) {
                  out.append(String.format(
                      "The target character %s has been killed by %s. Congratulations, %s!",
                      model.getTargetCharacter().getName(),
                      currentTurn.getName(),
                      currentTurn.getName()));
                  scan.close();
                  return;
                }
              } catch (UnsupportedOperationException uoe) {
                // catch the incorrect command
                out.append(uoe.getMessage());
                continue;
              } catch (IllegalArgumentException iae) {
                // When an incorrect argument is inputed,
                // an IllegalArgumentException will be thrown out.
                out.append(iae.getMessage());
                continue;
              }
            }
          } else {
            Function<Scanner, WorldCommand> cmd = commands.getOrDefault(in, null);
            if (cmd == null) {
              throw new UnsupportedOperationException(
                  String.format("Command %s not supported", in));
            } else {
              c = cmd.apply(scan);
              c.execute(model, out);
            }
          }
        } catch (UnsupportedOperationException uoe) {
          // catch the incorrect command
          out.append(uoe.getMessage());
          continue;
        } catch (IllegalArgumentException iae) {
          // When an incorrect argument is inputed,
          // an IllegalArgumentException will be thrown out.
          out.append(iae.getMessage());
          continue;
        }
      }
    } catch (NoSuchElementException nee) {
      // When a user terminates the input stream manually,
      // the "No line found" error will be thrown out.
      if (nee.getMessage().contains("No line found")) {
        out.append("Game over! You have end the input manually!");
      }
      scan.close();
      return;
    }
    scan.close();
    out.append("Reaching the maximum number of turns, and the target character escapes.\n");
    out.append("Nobody wins, game over.");
  }
  
  @Override
  public Map<Character, Runnable> getKeyTypes() {
    return Map.copyOf(this.keyTypes);
  }
  
  @Override
  public Map<String, Runnable> getGameActions() {
    return Map.copyOf(this.gameActions);
  }
  
  private void configureKeyBoardListener() {
    keyTypes = new HashMap<>();
    keyTypes.put('i', () -> pickUpItem());
    keyTypes.put('l', () -> lookAround());    
    keyTypes.put('a', () -> makeAnAttempt());
    keyTypes.put('m', () -> movePet());   
    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);
    view.addKeyListener(kbd);
  }

  private void configureActionListener() {
    gameActions = new HashMap<>();
    gameActions.put("Enter Game", () -> view.enterGame());
    gameActions.put("Start a new game with a new world specification", () -> selectNewWorld());
    gameActions.put("Start a new game with the current world specification",
        () -> selectCurrentWorld());
    gameActions.put("Quit", () -> exitProgram());
    gameActions.put("Add a new computer-controlled player", () -> addComputerPlayer());
    gameActions.put("Add a new human-controlled player", () -> addHumanPlayer());
    gameActions.put("Start the game", () -> startGame());
    GameActionListener gameActionListener = new GameActionListener();
    gameActionListener.setGameActionMap(gameActions);
    view.addActionListener(gameActionListener);
  }
  
  private void configureSpaceClickListener() {
    view.configureSpaceClickListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && model.getTurn() != null) {
          movePlayer(e.getX(), e.getY());
        }
      }
    });
  }
  
  private void configurePlayerClickListener() {
    view.configurePlayerClickListener();
  }
  
  private void selectNewWorld() {
    try {
      File selectedFile = view.getNewFile();
      Readable fileReader = new FileReader(selectedFile);
      World newWorld = new MyWorld(fileReader);
      this.model = newWorld;
      view.showGameInterface(newWorld);
      configureActionListener();
      configureSpaceClickListener();
      view.resetFocus();
      this.currentFile = selectedFile;
    } catch (IOException ioe) {
      view.showError("Failed to load world: " + ioe.getMessage());
    } catch (NumberFormatException nfe) {
      view.showError("Failed to load world: " + nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError("Failed to load world: " + uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError("Failed to load world: " + iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError("Failed to load world: " + nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError("Failed to load world: " + ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError("Failed to load world: " + npe.getMessage());
    }
  }

  private void selectCurrentWorld() {
    try {
      World newModel = new MyWorld(new FileReader(currentFile));
      this.model = newModel;
      view.showGameInterface(newModel);
      configureActionListener();
      configureSpaceClickListener();
      view.resetFocus();
    } catch (IOException ioe) {
      view.showError("Failed to load world: " + ioe.getMessage());
    } catch (NumberFormatException nfe) {
      view.showError("Failed to load world: " + nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError("Failed to load world: " + uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError("Failed to load world: " + iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError("Failed to load world: " + nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError("Failed to load world: " + ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError("Failed to load world: " + npe.getMessage());
    }
  }
  
  private void exitProgram() {
    System.exit(0);
  }
  
  private void addComputerPlayer() {
    String playerName = view.getPlayerName();
    String spaceName = view.getSpaceName();
    try {
      model.addComputerPlayer(playerName, spaceName);
      view.addPlayer();
      configurePlayerClickListener();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }
  
  private void addHumanPlayer() {
    String playerName = view.getPlayerName();
    String spaceName = view.getSpaceName();
    try {
      model.addHumanPlayer(playerName, spaceName);
      view.addPlayer();
      configurePlayerClickListener();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }

  private void startGame() {
    model.resetTurn();
    view.startGame();
    checkAutomaticExecution();
    view.resetFocus();
  }
  
  private void checkAutomaticExecution() {
    boolean shouldGameContinue = shouldGameContinue();
    if (!shouldGameContinue) {
      selectCurrentWorld();
      return;
    }
    Player currentTurn = model.getTurn();
    if (currentTurn instanceof ComputerControlledPlayer) {
      try {
        String in = ((ComputerControlledPlayer) currentTurn).getRandomOperation(model);
        Function<World, WorldCommand> cmd = computerCommands.getOrDefault(in, null);
        if (cmd == null) {
          throw new UnsupportedOperationException(String.format("Command %s not supported", in));
        } else {
          WorldCommand c = cmd.apply(model);
          String result = c.execute(model, System.out);
          view.setResult(result);
        }
      } catch (IOException ioe) {
        view.showError(ioe.getMessage());
        return;
      } catch (NumberFormatException nfe) {
        view.showError(nfe.getMessage());
        return;
      } catch (UnsupportedOperationException uoe) {
        view.showError(uoe.getMessage());
        return;
      } catch (IllegalArgumentException iae) {
        view.showError(iae.getMessage());
        return;
      } catch (NoSuchElementException nee) {
        view.showError(nee.getMessage());
        return;
      } catch (IllegalStateException ise) {
        view.showError(ise.getMessage());
        return;
      } catch (NullPointerException npe) {
        view.showError(npe.getMessage());
        return;
      }
      checkAutomaticExecution();
    }
  }
  
  private boolean shouldGameContinue() {
    Player currentTurn = model.getTurn();
    if (model.getTargetCharacter().getHealth() <= 0) {
      String result = String.format(
          "The target character %s has been killed by %s. Congratulations, %s!",
          model.getTargetCharacter().getName(), currentTurn.getName(), currentTurn.getName());
      view.endGame(result);
      return false;
    }
    if (model.getTurnCount() >= this.turnLimit) {
      String result = String.format("Reaching the maximum number of turns, "
          + "and the target character escapes.\nNobody wins, game over.",
          model.getTargetCharacter().getName(), currentTurn.getName(), currentTurn.getName());
      view.endGame(result);
      return false;
    }
    return true;
  }
  
  @Override
  public void movePlayer(int x, int y) {
    Space space = model.getSpace(x, y);
    if (space == null) {
      view.showError("Please choose a valid space!");
      return;
    }
    try {
      model.movePlayer(space.getName());
      String playerName = model.getTurn().getName();
      model.nextTurn();
      view.setResult(String.format("The player %s has moved to %s\n", playerName, space.getName()));
      checkAutomaticExecution();
      view.resetFocus();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }
  
  private void pickUpItem() {
    String itemName = view.getItemName();
    try {
      model.pickUpItem(itemName);
      Player turn = model.getTurn();
      String result = String.format("The player %s has picked up %s from %s\n",
          turn.getName(), itemName, turn.getSpace().getName());
      model.nextTurn();
      view.setResult(result);
      checkAutomaticExecution();
      view.resetFocus();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }
  
  private void lookAround() {
    try {
      String result = model.lookAround();
      model.nextTurn();
      view.setResult(result);
      checkAutomaticExecution();
      view.resetFocus();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }
  
  private void makeAnAttempt() {
    String itemName = view.getAttemptChoice();
    try {
      boolean isSuccessful = model.makeAnAttempt(itemName);
      String name = model.getTurn().getName();
      String result;
      if (isSuccessful) {
        result = String.format(
            "The player %s has made an attempt on the target character using %s\n", name, itemName);
      } else {
        result = String.format(
            "The player %s stopped attacking because it was seen by others.\n", name);
      }
      model.nextTurn();
      view.setResult(result);
      checkAutomaticExecution();
      view.resetFocus();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }
  
  private void movePet() {
    String spaceName = view.getIntendedSpace();
    try {
      model.movePet(spaceName);
      String result = String.format("The pet %s has been moved to %s\n",
          model.getPet().getName(), spaceName);
      model.nextTurn();
      view.setResult(result);
      checkAutomaticExecution();
      view.resetFocus();
    } catch (NumberFormatException nfe) {
      view.showError(nfe.getMessage());
    } catch (UnsupportedOperationException uoe) {
      view.showError(uoe.getMessage());
    } catch (IllegalArgumentException iae) {
      view.showError(iae.getMessage());
    } catch (NoSuchElementException nee) {
      view.showError(nee.getMessage());
    } catch (IllegalStateException ise) {
      view.showError(ise.getMessage());
    } catch (NullPointerException npe) {
      view.showError(npe.getMessage());
    }
  }
}

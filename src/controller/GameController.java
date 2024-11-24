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
import space.Space;
import view.View;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.JFileChooser;

import world.MyWorld;
import world.World;

/**
 * The GameController represents the controller of the game.
 */
public class GameController {
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
  
  /**
   * Constructor.
   * 
   * @param m the model to use
   */
  public GameController(World model, File file, int turnLimit) {
    this.model = model;
    this.currentFile = file;
    this.turnLimit = turnLimit;
  }
  
  /**
   * Constructs the controller of the game.
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
  
  /**
   * Method that gives control to the controller.
   * 
   * @param model the model to use.
   * @throws IOException if something goes wrong appending to out
   */
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

  /**
   * Sets the view to use.
   * 
   * @param v the view
   */
  public void setView(View v) {
    view = v;
    configureKeyBoardListener();
    configureActionListener();
  }
  
  /**
   * Mutator for the model.
   * 
   * @param v the view to use
   */
  public void setModel(World m) {
    model = m;
  }
  
  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();

    keyTypes.put('i', () -> {
      
    });

    keyTypes.put('l', () -> {
      
    });
    
    keyTypes.put('a', () -> {
      
    });

    keyTypes.put('m', () -> {
      
    });
    
    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    view.addKeyListener(kbd);
  }
  
  private void configureActionListener() {
    Map<String, Runnable> gameActions = new HashMap<>();
    GameActionListener gameActionListener = new GameActionListener();
    gameActions.put("Enter Game", () -> view.enterGame());
    gameActions.put("Start a new game with a new world specification", () -> selectNewWorld());
    gameActions.put("Start a new game with the current world specification", () -> selectCurrentWorld());
    gameActions.put("Quit", () -> System.exit(0));

    gameActionListener.setGameActionMap(gameActions);
    view.addActionListener(gameActionListener);
  }
  
  private void selectNewWorld() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      try {
        File selectedFile = fileChooser.getSelectedFile();
        Readable fileReader = new FileReader(selectedFile);
        World newWorld = new MyWorld(fileReader);
        setModel(newWorld);
        view.showGameInterface(newWorld);
        configureActionListener();
      } catch (Exception e) {
        view.showError("Failed to load world: " + e.getMessage());
      }
    }
  }

  private void selectCurrentWorld() {
    if (model == null) {
      view.showError("No model is loaded. Something is wrong.");
      return;
    }
    try {
      World newModel = new MyWorld(new FileReader(currentFile));
      setModel(newModel);
      view.showGameInterface(newModel);
      configureActionListener();
    } catch (Exception e) {
      view.showError("Failed to load world: " + e.getMessage());
    }
  }

//  private void drawTarget() {
//    int positionX = model.getSpaces().get(model.getTargetCharacterPosition()).getPosition()[3] * 20 - 50;
//    int positionY = model.getSpaces().get(model.getTargetCharacterPosition()).getPosition()[2] * 20 + 16;
//    view.drawCharacter(model.getTargetCharacter().getName(), positionX, positionY);
//  }
//  
//  
//  public void addComputerPlayer(String playerName, String spaceName) {
//    model.addComputerPlayer(playerName, spaceName);
//  }
//
//  
//  public void addHumanPlayer(String playerName, String spaceName) {
//    model.addHumanPlayer(playerName, spaceName);
//    
//  }
//
//  
//  public void startGame() {
//    // TODO Auto-generated method stub
//    
//  }
//
//  
//  public void movePlayer(String spaceName) {
//    model.movePlayer(spaceName);
//    String playerName = model.getTurn().getName();
//    Space space = model.getSpace(spaceName);
//    int playerNum = model.getPlayers().stream().mapToInt(p -> p.getSpace().equals(space) ? 1 : 0).sum();
//    int positionX = space.getPosition()[3] * 20 - 50;
//    int positionY = space.getPosition()[0] * 20 + 20 * playerNum;
//    view.drawCharacter(playerName, positionX, positionY);
//  }

}

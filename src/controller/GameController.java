package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import character.Player;
import command.AddComputerPlayer;
import command.AddHumanPlayer;
import command.DisplayPlayerInformation;
import command.DisplaySpaceInformation;
import command.GenerateMap;
import command.LookAround;
import command.MovePlayer;
import command.PickUpItem;
import command.WorldCommand;
import world.World;

public class GameController {
  private final Readable in;
  private final Appendable out;
  private final int turnLimit;
  private final Map<String, Function<Scanner, WorldCommand>> initialCommands;
  private final Map<String, Function<Scanner, WorldCommand>> commands;
  private final Map<String, Function<Scanner, WorldCommand>> humanCommands;
  private final Map<String, Function<World, WorldCommand>> computerCommands;
  
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
    humanCommands.put("lookAround", s -> new LookAround());
    // commands for computer controlled players
    computerCommands = new HashMap<>();
    computerCommands.put("automaticMovePlayer", m -> new MovePlayer(m));
    computerCommands.put("automaticPickUpItem", m -> new PickUpItem(m));
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
      while(model.getPlayers().size() < 1) {
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
          out.append(uoe.getMessage());
          continue;
        } catch (IllegalArgumentException iae) {
          out.append(iae.getMessage());
          continue;
        }
      }
      
      model.resetTurn();
      
      while(model.getTurnCount() < turnLimit) {
        try {
          out.append("\nPlease enter one of the following commands:\n");
          out.append("displaySpaceInformation\naddComputerPlayer\naddHumanPlayer\ngenerateMap\ndisplayPlayerInformation\nnextTurn\n");
          String in = scan.nextLine();
          WorldCommand c;
          if (in.equals("nextTurn")) {
            Player currentTurn = model.getTurn();
            out.append(String.format("Now, it is %s's turn\n", currentTurn.getName()));
            int currentTurnCount = model.getTurnCount();
            while(model.getTurnCount() == currentTurnCount) {
              try {
                if (currentTurn instanceof HumanControlledPlayer) {
                  out.append("\nPlease enter one of the following commands:\n");
                  out.append("movePlayer\npickUpItem\nlookAround\n");
                  in = scan.nextLine();
                  Function<Scanner, WorldCommand> cmd = humanCommands.getOrDefault(in, null);
                  if (cmd == null) {
                    throw new UnsupportedOperationException(String.format("Command %s not supported", in));
                  } else {
                    c = cmd.apply(scan);
                    c.execute(model, out);
                  }
                } else if (currentTurn instanceof ComputerControlledPlayer) {
                  in = ((ComputerControlledPlayer) currentTurn).getRandomOperation();
                  Function<World, WorldCommand> cmd = computerCommands.getOrDefault(in, null);
                  if (cmd == null) {
                    throw new UnsupportedOperationException(String.format("Command %s not supported", in));
                  } else {
                    c = cmd.apply(model);
                    c.execute(model, out);
                  }
                }
              } catch (IllegalArgumentException iae) {
                out.append(iae.getMessage());
                continue;
              }
            }
          } else {
            Function<Scanner, WorldCommand> cmd = commands.getOrDefault(in, null);
            if (cmd == null) {
              throw new UnsupportedOperationException(String.format("Command %s not supported", in));
            } else {
              c = cmd.apply(scan);
              c.execute(model, out);
            }
          }
        } catch (UnsupportedOperationException uoe) {
          out.append(uoe.getMessage());
          continue;
        } catch (IllegalArgumentException iae) {
          out.append(iae.getMessage());
          continue;
        }
      }
    } catch (NoSuchElementException nee) {
      if (nee.getMessage().contains("No line found")) {
        out.append("Game over! You have end the input manually!");
      }
      scan.close();
      return;
    }
    scan.close();
    out.append("Reaching the maximum number of turns, game over.");
  }
}

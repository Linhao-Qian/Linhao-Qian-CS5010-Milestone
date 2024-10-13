package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
  private final Map<String, Function<World, WorldCommand>> automaticCommands;
  
  public GameController(Readable in, Appendable out, int turnLimit) {
    this.in = in;
    this.out = out;
    this.turnLimit = turnLimit;
    initialCommands = new HashMap<>();
    initialCommands.put("displaySpaceInformation", s -> new DisplaySpaceInformation(s, out));
    initialCommands.put("addComputerPlayer", s -> new AddComputerPlayer(s, out));  
    initialCommands.put("addHumanPlayer", s -> new AddHumanPlayer(s, out));
    commands = new HashMap<>(initialCommands);
    commands.put("displayPlayerInformation", s -> new DisplayPlayerInformation(s, out));
    commands.put("movePlayer", s -> new MovePlayer(s, out));
    commands.put("pickUpItem", s -> new PickUpItem(s, out));
    commands.put("lookAround", s -> new LookAround());
    commands.put("generateMap", s -> new GenerateMap());
    automaticCommands = new HashMap<>();
    automaticCommands.put("automaticMovePlayer", m -> new MovePlayer(m));
    automaticCommands.put("automaticPickUpItem", m -> new PickUpItem(m));
    automaticCommands.put("lookAround", m -> new LookAround());
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
    while(model.getPlayers().size() < 1) {
      try {
        out.append("Please enter one of the following commands:\n");
        out.append("displaySpaceInformation\naddComputerPlayer\naddHumanPlayer\n");
        WorldCommand c;
        String in = scan.next();
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
        Player currentTurn = model.getTurn();
        out.append(String.format("Now, it is %s's turn", currentTurn.getName()));
        WorldCommand c;
        if (currentTurn instanceof HumanControlledPlayer) {
          out.append("Please enter one of the following commands:\n");
          out.append("displaySpaceInformation\naddComputerPlayer\naddHumanPlayer\n");
          out.append("displayPlayerInformation\nmovePlayer\npickUpItem\nlookAround\ngenerateMap\n");
          String in = scan.next();
          Function<Scanner, WorldCommand> cmd = commands.getOrDefault(in, null);
          if (cmd == null) {
            throw new UnsupportedOperationException(String.format("Command %s not supported", in));
          } else {
            c = cmd.apply(scan);
            c.execute(model, out);
          }
        } else if (currentTurn instanceof ComputerControlledPlayer) {
          String in = ((ComputerControlledPlayer) currentTurn).getRandomOperation();
          Function<World, WorldCommand> cmd = automaticCommands.getOrDefault(in, null);
          if (cmd == null) {
            throw new UnsupportedOperationException(String.format("Command %s not supported", in));
          } else {
            c = cmd.apply(model);
            c.execute(model, out);
          }
        }
      } catch (UnsupportedOperationException uoe) {
        out.append(uoe.getMessage());
        continue;
      }
    }
    scan.close();
    out.append("Reaching the maximum number of turns, game over.");
  }
}

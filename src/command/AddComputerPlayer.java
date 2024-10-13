package command;

import java.io.IOException;
import java.util.Scanner;

import character.ComputerControlledPlayer;
import character.Player;
import space.Space;
import world.World;

public class AddComputerPlayer implements WorldCommand {

  private final String name;
  private final String spaceName;
  
  public AddComputerPlayer(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the computer controlled player name:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.name = scan.next();
    try {
      out.append("Please enter the name of the space where the player is initially located:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.next();;
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    Space space = model.getSpace(spaceName);
    Player player = new ComputerControlledPlayer(name, space);
    model.addPlayer(player);
    out.append(String.format("Successfully added the computer controlled player %s", spaceName));
  }
}
package command;

import java.io.IOException;
import java.util.Scanner;

import character.Player;
import character.HumanControlledPlayer;
import space.Space;
import world.World;

public class AddHumanPlayer implements WorldCommand {

  private final String name;
  private final String spaceName;
  
  public AddHumanPlayer(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the human controlled player name:\n");
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
    Player player = new HumanControlledPlayer(name, space);
    model.addPlayer(player);
    out.append(String.format("Successfully added the human controlled player %s", spaceName));
  }
}

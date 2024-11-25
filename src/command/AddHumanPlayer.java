package command;

import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * An AddHumanPlayer represents a command used to add a human-controlled player.
 */
public class AddHumanPlayer implements WorldCommand {

  private final String name;
  private final String spaceName;
  
  /**
   * Constructs an AddHumanPlayer command.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public AddHumanPlayer(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the human-controlled player name:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.name = scan.nextLine();
    try {
      out.append("Please enter the name of the space where the player is initially located:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.nextLine();;
  }
  
  @Override
  public String execute(World model, Appendable out) throws IOException {
    model.addHumanPlayer(name, spaceName);
    String result = String.format("Successfully added the human-controlled player %s\n", name);
    out.append(result);
    return result;
  }
}

package command;

import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * An AddComputerPlayer represents a command used to add a computer-controlled player.
 */
public class AddComputerPlayer implements WorldCommand {

  private final String name;
  private final String spaceName;
  
  /**
   * Constructs an AddComputerPlayer command.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public AddComputerPlayer(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the computer-controlled player name:\n");
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
  public void execute(World model, Appendable out) throws IOException {
    model.addComputerPlayer(name, spaceName);
    out.append(String.format("Successfully added the computer-controlled player %s\n", name));
  }
}
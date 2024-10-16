package command;

import java.io.IOException;
import java.util.Scanner;

import world.World;

/**
 * A DisplaySpaceInformation represents a command used to display a space's information.
 */
public class DisplaySpaceInformation implements WorldCommand {
  
  private final String spaceName;
  
  /**
   * Constructs an DisplaySpaceInformation command.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public DisplaySpaceInformation(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the space name:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.nextLine();
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    out.append(model.displaySpaceInformation(spaceName));
  }
}

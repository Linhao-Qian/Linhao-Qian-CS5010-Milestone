package command;

import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * A DisplayPlayerInformation represents a command used to display a player's information.
 */
public class DisplayPlayerInformation implements WorldCommand {

  private final String playerName;

  /**
   * Constructs an DisplayPlayerInformation command.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public DisplayPlayerInformation(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the player name:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.playerName = scan.nextLine();
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    out.append(model.displayPlayerInformation(playerName));
  }
}

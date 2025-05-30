package command;

import character.ComputerControlledPlayer;
import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * A MakeAnAttempt represents a command used to make an attempt.
 */
public class MakeAnAttempt implements WorldCommand {

  private final String itemName;
  
  /**
   * Constructs a MakeAnAttempt command used for human-controlled player.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public MakeAnAttempt(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the name of the item which you want to use");
      out.append(" (If you don't have any item, please enter \"pokeEyes\"):\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.itemName = scan.nextLine();
  }
  
  /**
   * Constructs an MakeAnAttempt command used for computer-controlled player.
   * 
   * @param model  the model of the world
   */
  public MakeAnAttempt(World model) {
    ComputerControlledPlayer turn = (ComputerControlledPlayer) model.getTurn();
    this.itemName = turn.getMostPowerfulItemName();
  }
  
  @Override
  public String execute(World model, Appendable out) throws IOException {
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
    out.append(result);
    model.nextTurn();
    return result;
  }
}

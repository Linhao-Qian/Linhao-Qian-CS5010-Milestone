package command;

import character.ComputerControlledPlayer;
import character.Player;
import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * A PickUpItem represents a command used to pick up an item.
 */
public class PickUpItem implements WorldCommand {

  private final String itemName;
  
  /**
   * Constructs a PickUpItem command used for human-controlled player.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public PickUpItem(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the name of the item which you want to carry:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.itemName = scan.nextLine();
  }
  
  /**
   * Constructs an PickUpItem command used for computer-controlled player.
   * 
   * @param model  the model of the world
   */
  public PickUpItem(World model) {
    ComputerControlledPlayer turn = (ComputerControlledPlayer) model.getTurn();
    this.itemName = turn.getRandomItemName(turn.getSpace().getItems());
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    model.pickUpItem(itemName);
    Player turn = model.getTurn();
    out.append(String.format("The player %s has picked up %s from %s\n",
        turn.getName(), itemName, turn.getSpace().getName()));
    model.nextTurn();
  }
}

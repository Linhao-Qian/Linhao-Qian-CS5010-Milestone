package command;

import java.io.IOException;
import java.util.Scanner;

import character.ComputerControlledPlayer;
import character.Player;
import world.World;

public class PickUpItem implements WorldCommand {

  private final String itemName;
  
  public PickUpItem(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the name of the item which you want to carry :\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.itemName = scan.next();
  }
  
  public PickUpItem(World model) {
    ComputerControlledPlayer turn = (ComputerControlledPlayer) model.getTurn();
    this.itemName = turn.getRandomItemName(turn.getSpace().getItems());
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    model.pickUpItem(itemName);
    Player turn = model.getTurn();
    out.append(String.format("The player %s has picked up %s from %s",
        turn.getName(), itemName, turn.getSpace()));
    model.nextTurn();
  }
}

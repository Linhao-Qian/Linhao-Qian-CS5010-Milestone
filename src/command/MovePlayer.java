package command;

import java.io.IOException;
import java.util.Scanner;

import character.ComputerControlledPlayer;
import world.World;

public class MovePlayer implements WorldCommand {

  private final String spaceName;
  
  public MovePlayer(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the name of the space where you want to move :\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.next();
  }
  
  public MovePlayer(World model) {
    ComputerControlledPlayer turn = (ComputerControlledPlayer) model.getTurn();
    this.spaceName = turn.getRandomNeighborName(turn.getSpace().getNeighbors());
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    model.movePlayer(spaceName);
    out.append(String.format("The player %s has moved to %s", model.getTurn().getName(), spaceName));
    model.nextTurn();
  }
}

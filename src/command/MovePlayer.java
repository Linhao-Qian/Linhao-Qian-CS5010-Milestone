package command;

import character.ComputerControlledPlayer;
import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * A MovePlayer represents a command used to move a player.
 */
public class MovePlayer implements WorldCommand {

  private final String spaceName;
  
  /**
   * Constructs an MovePlayer command used for human-controlled player.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public MovePlayer(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the name of the space where you want to move:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.nextLine();
  }
  
  /**
   * Constructs an MovePlayer command used for computer-controlled player.
   * 
   * @param model  the model of the world
   */
  public MovePlayer(World model) {
    ComputerControlledPlayer turn = (ComputerControlledPlayer) model.getTurn();
    this.spaceName = turn.getRandomNeighborName(turn.getSpace().getNeighbors());
  }
  
  @Override
  public String execute(World model, Appendable out) throws IOException {
    model.movePlayer(spaceName);
    String result = String.format("The player %s has moved to %s\n",
        model.getTurn().getName(), spaceName);
    out.append(result);
    model.nextTurn();
    return result;
  }
}

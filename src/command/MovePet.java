package command;

import character.ComputerControlledPlayer;
import java.io.IOException;
import java.util.Scanner;
import world.World;

/**
 * A MovePet represents a command used to move the pet.
 */
public class MovePet implements WorldCommand {
  
  private final String spaceName;
  
  /**
   * Constructs an MovePet command used for human-controlled player.
   * 
   * @param scan   the scanner
   * @param out    the output stream
   */
  public MovePet(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the name of the space where the pet should be moved to:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.nextLine();
  }
  
  /**
   * Constructs an MovePet command used for computer-controlled player.
   * 
   * @param model  the model of the world
   */
  public MovePet(World model) {
    ComputerControlledPlayer turn = (ComputerControlledPlayer) model.getTurn();
    this.spaceName = turn.getRandomSpaceName(model.getSpaces());
  }
  
  @Override
  public String execute(World model, Appendable out) throws IOException {
    model.movePet(spaceName);
    String result = String.format("The pet %s has been moved to %s\n",
        model.getPet().getName(), spaceName);
    out.append(result);
    model.nextTurn();
    return result;
  }

}

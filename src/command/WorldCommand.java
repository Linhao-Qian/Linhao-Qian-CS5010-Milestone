package command;

import java.io.IOException;
import world.World;

/**
 * A WorldCommand represents a command used to interact with the game.
 */
public interface WorldCommand {
  
  /**
   * Execute the command.
   *
   * @param model  the model of the world
   * @param out    the output stream
   * @return       the result of execution
   */
  String execute(World model, Appendable out) throws IOException;
}

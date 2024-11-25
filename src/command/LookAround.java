package command;

import java.io.IOException;
import world.World;

/**
 * A LookAround represents a command used to display information about
 * where a specific player is in the world.
 */
public class LookAround implements WorldCommand {

  @Override
  public String execute(World model, Appendable out) throws IOException {
    String result = model.lookAround();
    out.append(result);
    model.nextTurn();
    return result;
  }
}

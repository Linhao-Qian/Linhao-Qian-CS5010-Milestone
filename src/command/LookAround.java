package command;

import java.io.IOException;
import world.World;

/**
 * A LookAround represents a command used to display information about
 * where a specific player is in the world.
 */
public class LookAround implements WorldCommand {

  @Override
  public void execute(World model, Appendable out) throws IOException {
    out.append(model.lookAround());
    model.nextTurn();
  }
}

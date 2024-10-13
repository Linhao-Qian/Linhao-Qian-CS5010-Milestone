package command;

import java.io.IOException;

import world.World;

public class LookAround implements WorldCommand {

  @Override
  public void execute(World model, Appendable out) throws IOException {
    out.append(model.lookAround());
    model.nextTurn();
  }
}

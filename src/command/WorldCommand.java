package command;

import java.io.IOException;

import world.World;

public interface WorldCommand {
  void execute(World model, Appendable out) throws IOException;
}

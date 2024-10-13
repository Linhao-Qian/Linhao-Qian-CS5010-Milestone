package command;

import java.io.IOException;
import java.util.Scanner;

import world.World;

public class DisplaySpaceInformation implements WorldCommand {
  
  private final String spaceName;
  
  public DisplaySpaceInformation(Scanner scan, Appendable out) {
    try {
      out.append("Please enter the space name:\n");
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    }
    this.spaceName = scan.next();
  }
  
  @Override
  public void execute(World model, Appendable out) throws IOException {
    out.append(model.displaySpaceInformation(spaceName));
  }
}

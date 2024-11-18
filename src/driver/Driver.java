package driver;

import controller.GameController;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import view.View;
import view.GameWindow;
import world.MyWorld;
import world.World;

/**
 * The Driver class demonstrates how to use the model.
 */
public class Driver {
  /**
   * Main is the entry method of the program.
   * 
   * @param args the arguments list of the entry method
   */  
  public static void main(String[] args) {
    try {
      if (args.length != 3) {
        System.out.println("Error: Invalid command arguments");
        return;
      }
      if (!args[0].endsWith(".txt")) {
        System.out.println("Error: Invalid input file format");
        return;
      }
      int turnLimit = Integer.parseInt(args[1]);
      if (turnLimit < 1) {
        System.out.println("Error: Invalid maximum number of turns allowed");
        return;
      }
      Readable fileReader = new FileReader(args[0]);
      World world = new MyWorld(fileReader);
      Readable reader = new InputStreamReader(System.in);
      GameController controller = new GameController(reader, System.out, turnLimit);
      String mode = args[2];
      if ("text".equals(mode)) {
        controller.start(world);
      } else if("view".equals(mode)) {
        View view = new GameWindow("Game");
        controller.setView(view);
        controller.setModel(world);
      } else {
        System.out.println("Invalid mode. Please choose 'text' or 'view'.");
      }
    } catch (IOException ioe) {
      System.out.println(String.format("IOException: %s", ioe.getMessage()));
    } catch (NumberFormatException nfe) {
      System.out.println(String.format("Number Format Exception: %s", nfe.getMessage()));
    } catch (IllegalArgumentException iae) {
      System.out.println(String.format("Illegal Argument Exception: %s", iae.getMessage()));
    } catch (NoSuchElementException nee) {
      System.out.println(String.format("No Such Element Exception: %s", nee.getMessage()));
    } catch (IllegalStateException ise) {
      System.out.println(String.format("Illegal State Exception: %s", ise.getMessage()));
    }
  }
}

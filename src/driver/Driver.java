package driver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.imageio.ImageIO;
import world.MyWorld;
import world.World;

/**
 * The Driver class demonstrates how to use the model.
 */
public class Driver {
  private static final String SEPARATOR = "\n----------------------------------------------------------------------------------\n";
  
  /**
   * Main is the entry method of the program.
   * 
   * @param args   the arguments list of the entry method
   */  
  public static void main(String[] args) {
    try {
      if (args.length == 0) {
        System.out.println("Error: No input file provided");
        return;
      }
      if (!args[0].endsWith(".txt")) {
        System.out.println("Error: Invalid input file format");
        return;
      }
      Readable fileReader = new FileReader(args[0]);
      World world = new MyWorld(fileReader);
      
      // Print information of the initial world.
      System.out.print(world.toString());
      
      // Move the target character.
      System.out.println(String.format("%sNow the target character moves to the next space.", SEPARATOR));
      world.moveTargetCharacter();
      
      // Print information after the target character moved to the next space.
      System.out.print(
          String.format("After moving, the target character is at:\n\n%s",
              world.displaySpaceInformation(world.getSpaces().get(world.getTargetCharacterPosition()))));
      
      // Use the input file name to generate the output file name, and output the world map.
      String outputFileName = args[0].substring(0, args[0].length() - 4).concat(".png");
      BufferedImage image = world.generateMap();
      ImageIO.write(image, "png", new File(outputFileName));
      System.out.println(String.format("%sThe world map is in the %s file.", SEPARATOR, outputFileName));
    } catch (IOException ioe) {
      System.out.println(String.format("Error: %s", ioe.getMessage()));
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

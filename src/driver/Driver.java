package driver;

import character.TargetCharacter;
import item.Item;
import item.MyItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import space.MySpace;
import space.Space;
import world.MyWorld;
import world.World;

/**
 * The Driver class demonstrates how to use the model.
 */
public class Driver {
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
      Readable in = new FileReader(args[0]);
      Scanner scan = new Scanner(in);
      
      // Read the world information.
      String[] worldInfo = scan.nextLine().split(" ", 3);
      int rows = Integer.parseInt(worldInfo[0]);
      int cols = Integer.parseInt(worldInfo[1]);
      String worldName = worldInfo[2];
      System.out.println(String.format("The world's name is: %s", worldName));
      System.out.println(String.format("The world's size is: %d x %d", rows, cols));
      System.out.println(
          "----------------------------------------------------------------------------------");
      
      // Read the target character information.
      String[] targetCharacterInfo = scan.nextLine().split(" ", 2);
      int health = Integer.parseInt(targetCharacterInfo[0]);
      String targetCharacterName = targetCharacterInfo[1];
      TargetCharacter targetCharacter = new TargetCharacter(targetCharacterName, health);
      
      // Print information of the target character.
      System.out.println(targetCharacter.toString());
      System.out.println(
          "----------------------------------------------------------------------------------");
      
      // Read the spaces information.
      int spaceNum = Integer.parseInt(scan.nextLine());
      List<Space> spaces = new ArrayList<>();
      for (int i = 0; i < spaceNum; i++) {
        String[] spaceInfo = scan.nextLine().trim().split("\\s+", 5);
        int row1 = Integer.parseInt(spaceInfo[0]);
        int col1 = Integer.parseInt(spaceInfo[1]);
        int row2 = Integer.parseInt(spaceInfo[2]);
        int col2 = Integer.parseInt(spaceInfo[3]);
        String spaceName = spaceInfo[4];
        spaces.add(new MySpace(row1, col1, row2, col2, spaceName));
      }

      // Read the items information.
      int itemNum = Integer.parseInt(scan.nextLine());
      List<Item> items = new ArrayList<>();
      for (int i = 0; i < itemNum; i++) {
        String[] itemInfo = scan.nextLine().split("\\s+", 3);
        int position = Integer.parseInt(itemInfo[0]);
        int damage = Integer.parseInt(itemInfo[1]);
        String itemName = itemInfo[2];
        Item item = new MyItem(itemName, position, damage);
        items.add(item);
        spaces.get(position).addItem(item);
      }
      
      // Close the scanner.
      scan.close();
      
      // Print the spaces and items information.
      System.out.println("The spaces and items information is as follows:");
      for (int i = 0; i < spaceNum; i++) {
        Space space = spaces.get(i);
        List<Item> spaceItems = space.getItems();
        System.out.println(
            "\t--------------------------------------------------------------------------");
        System.out.println(String.format("\t%s", space.toString()));
        System.out.println("\tThe space has " + spaceItems.size() + " item(s):");
        for (int j = 0; j < spaceItems.size(); j++) {
          Item item = spaceItems.get(j);
          System.out.println(
              "\t\t------------------------------------------------------------------");
          System.out.println(
              String.format("\t\tItem name: %s, Damage: %d", item.getName(), item.getDamage()));
        }
      }
      
      // Create the MyWorld object.
      World world = new MyWorld(worldName, rows, cols, targetCharacter, spaces, items);
      
      // Print information of the target character position.
      System.out.println(
          "----------------------------------------------------------------------------------");
      Space currentSpace = spaces.get(world.getTargetCharacterPosition());
      System.out.println(
          String.format("The target character is currently at: %s.", currentSpace.getName()));
      
      // Move the target character.
      System.out.println("Now the target character moves to the next space.");
      world.moveTargetCharacter();
      currentSpace = spaces.get(world.getTargetCharacterPosition());
      System.out.println(
          String.format("After moving, the target character is at: %s.", currentSpace.getName()));
      
      // Print the neighbors of the space where the target character is now staying.
      List<Space> neighbors = world.getNeighbors(currentSpace);
      System.out.println("The current neighbors is as follows: ");
      for (int i = 0; i < neighbors.size(); i++) {
        System.out.println(String.format("\t%s", neighbors.get(i).getName()));
      }
      
      // Use the input file name to generate the output file name, and output the world map.
      String outputFileName = args[0].substring(0, args[0].length() - 4).concat(".png");
      BufferedImage image = world.generateMap();
      ImageIO.write(image, "png", new File(outputFileName));
      System.out.println(
          "----------------------------------------------------------------------------------");
      System.out.println(String.format("The world map is in the %s file.", outputFileName));
    } catch (IOException e) {
      System.out.println(String.format("Error: %s", e.getMessage()));
    } catch (IllegalArgumentException e) {
      System.out.println(String.format("Error: %s", e.getMessage()));
    }
  }
}

package space;

import item.Item;
import java.util.List;
import world.World;

/**
 * A Space represents a specified space or room that make up the {@link World}.
 * Spaces in the same world do not overlap.
 */
public interface Space {
  
  /**
   * Return the position of the space. A position is a list that contains the space's
   * row of the upper left corner, column of the upper left corner,
   * row of the lower right corner, column of the lower right corner.
   *
   * @return the position of the space
   */
  int[] getPosition();
  
  /**
   * Return the name of the space.
   *
   * @return the name of the space
   */
  String getName();
  
  /**
   * Return the items in the space. Each space has 0 or more items.
   *
   * @return the items in the space
   */
  List<Item> getItems();
  
  /**
   * Return the neighbors of the space.
   *
   * @return the neighbors of the space
   */
  List<Space> getNeighbors();
  
  /**
   * Get an item of the space's item list according to its name.
   * 
   * @param itemName  the name of the specific item
   * @return the corresponding item of the item name
   */
  Item getItem(String itemName);
  
  /**
   * Add an item to the space's item list.
   * 
   * @param item   the item which needs to be added to the space
   */
  void addItem(Item item);
  
  /**
   * Remove an item from the space's item list.
   * 
   * @param item   the item which needs to be removed from the space
   */
  void removeItem(Item item);
  
  /**
   * Add a neighbor to the space's neighbor list.
   * 
   * @param space   the space which is adjacent to current space
   */
  void addNeighbor(Space space);
}

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
   * Add an item to the space's item list.
   * 
   * @param item   the item which needs to be added to the space
   */
  void addItem(Item item);
}

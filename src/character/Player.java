package character;

import item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import space.Space;

/**
 * A Player represents a player in the milestone board game, controlled by either computer or human.
 */
public abstract class Player implements Character {

  protected final String name;
  protected Space space;
  protected List<Item> items;
  protected final int itemLimit = 2;
  
  /**
   * Constructs a Player object, which has a name and an initial space.
   * 
   * @param name     the name of the player
   * @param space    the initial space in which the player enters the world
   * @throws IllegalArgumentException if the name is null or empty, or the space is null
   */
  protected Player(String name, Space space) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the player can't be empty!");
    }
    if (space == null) {
      throw new IllegalArgumentException("The initial space of the player can't be null!");
    }
    this.name = name;
    this.space = space;
    this.items = new ArrayList<>();
  }
  
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Set the space of the player.
   *
   * @param space the space of the player
   */
  public void setSpace(Space space) {
    this.space = space;
  }
  
  /**
   * Return the space of the player.
   *
   * @return the space of the player
   */
  public Space getSpace() {
    return this.space;
  }
  
  /**
   * Add an item to the player's item list.
   * 
   * @param item the item which needs to be added to the player's item list
   */
  public void addItem(Item item) {
    if (items.size() >= itemLimit) {
      throw new UnsupportedOperationException("Cannot carry more items");
    }
    items.add(item);
  }
  
  /**
   * Return the items of the player.
   *
   * @return the items of the player
   */
  public List<Item> getItems() {
    return new ArrayList<>(this.items);
  }
  
  /**
   * Get an item of the player's item list according to its name.
   * 
   * @param itemName  the name of the specific item
   * @return the corresponding item of the item name
   */
  public Item getItem(String itemName) {
    return items.stream().filter(item -> item.getName().equals(itemName)).findAny().orElseThrow(
        () -> new IllegalArgumentException(String.format("The item %s does not exist", itemName)));
  }
  
  /**
   * Determine whether a player is the neighbor of current player.
   *
   * @param player the player who needs to be compared with
   * @return true if the player can be seen by the provided player, otherwise false
   */
  public boolean canBeSeenBy(Player player) {
    return space.getNeighbors().contains(player.getSpace()) || space.equals(player.getSpace());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    Character that = (Player) o;
    return this.name.equals(that.getName());
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
  @Override
  public String toString() {
    return String.format("Player name: %s, carrying %d item(s):\n%s", name, items.size(),
        items.stream().map(item -> item.toString()).collect(Collectors.joining("\n")));
  }
}

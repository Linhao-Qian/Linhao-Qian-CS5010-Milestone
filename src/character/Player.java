package character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import item.Item;
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
    return String.format("Player name: %s, carrying %d items: \n%s", name, items.size(),
        items.stream().map(item -> item.toString()).collect(Collectors.joining("\n")));
  }
}

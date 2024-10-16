package character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import item.Item;
import space.Space;

public abstract class Player implements Character {

  protected final String name;
  protected Space space;
  protected List<Item> items;
  protected final int itemLimit = 2;
  
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

  public void setSpace(Space space) {
    this.space = space;
  }
  
  public Space getSpace() {
    return this.space;
  }
  
  public void addItem(Item item) {
    if (items.size() >= itemLimit) {
      throw new UnsupportedOperationException("Cannot carry more items");
    }
    items.add(item);
  }
  
  public List<Item> getItems() {
    return new ArrayList<>(this.items);
  }
  
  @Override
  public String toString() {
    return String.format("Player name: %s, carrying %d items: \n%s", name, items.size(),
        items.stream().map(item -> item.toString()).collect(Collectors.joining("\n")));
  }
}

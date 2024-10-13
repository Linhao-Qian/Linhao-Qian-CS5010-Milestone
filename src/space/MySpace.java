package space;

import item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import world.MyWorld;

/**
 * A MySpace object represents a space or a room in a specified {@link MyWorld}.
 */
public class MySpace implements Space {
  private final int row1;
  private final int col1;
  private final int row2;
  private final int col2;
  private final String name;
  private final List<Item> items;
  private final List<Space> neighbors;

  /**
   * Constructs a MySpace object, which has a name, four coordinate values and a list of items.
   * 
   * @param row1   row of the upper left corner
   * @param col1   column of the upper left corner
   * @param row2   row of the lower right corner
   * @param col2   column of the lower right corner
   * @param name   the name of the space
   * @throws IllegalArgumentException  if the name is null or empty or the coordinates are invalid
   */
  public MySpace(int row1, int col1, int row2, int col2, String name)
      throws IllegalArgumentException {
    if (row1 < 0 || col1 < 0 || row2 < row1 || col2 < col1) {
      throw new IllegalArgumentException("Invalid space position");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the space can't be empty!");
    }
    this.row1 = row1;
    this.col1 = col1;
    this.row2 = row2;
    this.col2 = col2;
    this.name = name;
    this.items = new ArrayList<>();
    this.neighbors = new ArrayList<>();
  }

  @Override
  public int[] getPosition() {
    return new int[]{row1, col1, row2, col2};
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<Item> getItems() {
    return new ArrayList<>(items);
  }
  
  @Override
  public List<Space> getNeighbors() {
    return new ArrayList<>(neighbors);
  }
  
  @Override
  public Item getItem(String itemName) {
    return items.stream().filter(item -> item.getName().equals(itemName)).findAny().orElseThrow(
        () -> new IllegalArgumentException(String.format("The item %s does not exist", itemName)));
  }
  
  @Override
  public void addItem(Item item) {
    items.add(item);
  }
  
  @Override
  public void removeItem(Item item) {
    items.remove(item);
  }
  
  @Override
  public void addNeighbor(Space space) {
    neighbors.add(space);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MySpace)) {
      return false;
    }
    Space that = (MySpace) o;
    return Arrays.equals(this.getPosition(), that.getPosition()) && this.name.equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(row1, col1, row2, col2, name);
  }
  
  @Override
  public String toString() {
    return String.format(
        "Space name: %s;\nThe space has %d item(s):\n%s\nThe space has %d neighbor(s):\n%s", name,
        items.size(), items.stream().map(item -> item.toString()).collect(Collectors.joining("\n")),
        neighbors.size(), neighbors.stream().map(neighbor -> neighbor.getName()).collect(Collectors.joining(", ")));
  }
}
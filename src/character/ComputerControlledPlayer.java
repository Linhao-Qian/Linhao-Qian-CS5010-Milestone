package character;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import item.Item;
import space.Space;

/**
 * A ComputerControlledPlayer represents a player controlled by computer.
 */
public class ComputerControlledPlayer extends Player {

  private Random random;
  private int operationIndex;
  private int neighborIndex;
  private int itemIndex;
  
  /**
   * Constructs a HumanControlledPlayer object, which should be used for normal function.
   * 
   * @param name     the name of the player
   * @param space    the initial space in which the player enters the world
   */
  public ComputerControlledPlayer(String name, Space space) {
    super(name, space);
    this.random = new Random();
  }
  
  /**
   * Constructs a HumanControlledPlayer object, which should be used for unit test.
   * 
   * @param name             the name of the player
   * @param space            the initial space in which the player enters the world
   * @param operationIndex   a predictable number used to choose a specific operation
   * @param neighborIndex    a predictable number used to choose a specific neighbor
   * @param itemIndex        a predictable number used to choose a specific item
   */
  public ComputerControlledPlayer(String name, Space space, int operationIndex, int neighborIndex, int itemIndex) {
    super(name, space);
    this.operationIndex = operationIndex;
    this.neighborIndex = neighborIndex;
    this.itemIndex = itemIndex;
  }
  
  /**
   * Return a random operation.
   *
   * @return a random operation
   */
  public String getRandomOperation() {
    Space space = this.getSpace();
    List<String> operations = new ArrayList<>();
    operations.add("lookAround");
    if (space.getNeighbors().size() > 0) {
      operations.add("automaticMovePlayer");
    }
    if (space.getItems().size() > 0) {
      operations.add("automaticPickUpItem");
    }
    if (Objects.isNull(random)) {
      return operations.get(operationIndex);
    }
    return operations.get(random.nextInt(operations.size()));
  }
  
  /**
   * Return the name of a random neighbor.
   *
   * @return the name of a random neighbor
   */
  public String getRandomNeighborName(List<Space> spaces) {
    if (Objects.isNull(random)) {
      return spaces.get(neighborIndex).getName();
    }
    return spaces.get(random.nextInt(spaces.size())).getName();
  }
  
  /**
   * Return the name of a random item.
   *
   * @return the name of a random item
   */
  public String getRandomItemName(List<Item> items) {
    if (Objects.isNull(random)) {
      return items.get(itemIndex).getName();
    }
    return items.get(random.nextInt(items.size())).getName();
  }
}

package character;

import item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import space.Space;
import world.World;

/**
 * A ComputerControlledPlayer represents a player controlled by computer.
 */
public class ComputerControlledPlayer extends Player {

  private Random random;
  private int operationIndex;
  private int neighborIndex;
  private int itemIndex;
  private int petIndex;
  
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
  public ComputerControlledPlayer(String name, Space space, int operationIndex,
      int neighborIndex, int itemIndex, int petIndex) {
    super(name, space);
    this.operationIndex = operationIndex;
    this.neighborIndex = neighborIndex;
    this.itemIndex = itemIndex;
    this.petIndex = petIndex;
  }
  
  /**
   * Return a random operation.
   *
   * @return a random operation
   */
  public String getRandomOperation(World model) {
    Space space = this.getSpace();
    List<String> operations = new ArrayList<>();
    operations.add("lookAround");
    operations.add("automaticMovePet");
    if (space.equals(model.getSpaces().get(model.getTargetCharacterPosition()))) {
      if (model.canBeSeenByOthers()) {
        operations.add("automaticMakeAnAttempt");
      } else {
        return "automaticMakeAnAttempt";
      }
    }
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
   * @param spaces the neighbor list of current space
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
   * @param items the items in the current space
   * @return the name of a random item
   */
  public String getRandomItemName(List<Item> items) {
    if (Objects.isNull(random)) {
      return items.get(itemIndex).getName();
    }
    return items.get(random.nextInt(items.size())).getName();
  }
  
  /**
   * Return the name of a random space.
   *
   * @param spaces the spaces list
   * @return the name of a random space
   */
  public String getRandomSpaceName(List<Space> spaces) {
    if (Objects.isNull(random)) {
      return spaces.get(petIndex).getName();
    }
    return spaces.get(random.nextInt(spaces.size())).getName();
  }
  
  /**
   * Return the name of the most powerful item of the player.
   *
   * @return the name of the most powerful item of the player
   */
  public String getMostPowerfulItemName() {
    if (this.items.size() == 0) {
      return "pokeEyes";
    }
    Item item = this.items.get(0);
    for (int i = 0; i < this.items.size(); i++) {
      Item newItem = this.items.get(i);
      if (newItem.getDamage() > item.getDamage()) {
        item = newItem;
      }
    }
    return item.getName();
  }
}

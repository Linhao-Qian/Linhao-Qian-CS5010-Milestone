package character;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import item.Item;
import space.Space;

public class ComputerControlledPlayer extends Player {

  private Random random;
  private int operationIndex;
  private int neighborIndex;
  private int itemIndex;
  
  public ComputerControlledPlayer(String name, Space space) {
    super(name, space);
    this.random = new Random();
  }
  
  public ComputerControlledPlayer(String name, Space space, int operationIndex, int neighborIndex, int itemIndex) {
    super(name, space);
    this.operationIndex = operationIndex;
    this.neighborIndex = neighborIndex;
    this.itemIndex = itemIndex;
  }
  
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
  
  public String getRandomNeighborName(List<Space> spaces) {
    if (Objects.isNull(random)) {
      return spaces.get(neighborIndex).getName();
    }
    return spaces.get(random.nextInt(spaces.size())).getName();
  }
  
  public String getRandomItemName(List<Item> items) {
    if (Objects.isNull(random)) {
      return items.get(itemIndex).getName();
    }
    return items.get(random.nextInt(items.size())).getName();
  }
}

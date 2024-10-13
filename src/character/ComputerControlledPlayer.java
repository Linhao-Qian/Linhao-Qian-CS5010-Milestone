package character;

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
    String[] operations = new String[]{"automaticMovePlayer", "automaticPickUpItem", "lookAround"};
    if (Objects.isNull(random)) {
      return operations[operationIndex];
    }
    return operations[random.nextInt(3)];
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

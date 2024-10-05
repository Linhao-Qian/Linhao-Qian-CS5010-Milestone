package item;

import character.TargetCharacter;
import java.util.Objects;
import world.MyWorld;

/**
 * A MyItem object represents an item in a specified {@link MyWorld},
 * and it can be used to attack the {@link TargetCharacter}.
 */
public class MyItem implements Item {
  private final String name;
  private final int position;
  private final int damage;

  /**
   * Constructs a MyItem object, which has a name, a position and a specified damage value.
   * 
   * @param name       the name of the item
   * @param position   the index of the space in which the item can be found
   * @param damage     the damage value that the item can cause
   * @throws IllegalArgumentException   if the name is null or empty, the position is negative,
   *                                    or the damage is not positive
   */
  public MyItem(String name, int position, int damage) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the item can't be empty!");
    }
    if (position < 0) {
      throw new IllegalArgumentException("Invalid item position");
    }
    if (damage <= 0) {
      throw new IllegalArgumentException("Invalid damage");
    }
    this.name = name;
    this.position = position;
    this.damage = damage;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPosition() {
    return position;
  }

  @Override
  public int getDamage() {
    return damage;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MyItem)) {
      return false;
    }
    MyItem that = (MyItem) o;
    return this.name.equals(that.name)
        && this.position == that.position
        && this.damage == that.damage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, position, damage);
  }
  
  @Override
  public String toString() {
    return String.format("Item name: %s, Position: %d, Damage: %d", name, position, damage);
  }
}

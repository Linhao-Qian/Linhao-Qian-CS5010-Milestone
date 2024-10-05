package item;

import character.TargetCharacter;
import space.Space;

/**
 * An Item represents something that can be used to attack the {@link TargetCharacter}.
 * Each Item belongs to a specific {@link Space},
 * and it can cause a certain amount of damage to the {@link TargetCharacter}.
 */
public interface Item {

  /**
   * Return the name of the item.
   *
   * @return the name of the item
   */
  String getName();
  
  /**
   * Return the position of the item.
   * Position is the index of the space in which the item can be found.
   *
   * @return the position of the item
   */
  int getPosition();
  
  /**
   * Return the damage of the item. Damage is the amount of damage
   * the item could do if it was used to attack the target character.
   *
   * @return the damage of the item
   */
  int getDamage();
}

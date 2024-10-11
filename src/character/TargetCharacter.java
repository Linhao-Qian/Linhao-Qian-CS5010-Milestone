package character;

import world.MyWorld;

/**
 * A TargetCharacter represents the target character in a specified {@link MyWorld},
 * and it contains the health and name of the target character.
 */
public class TargetCharacter implements Character {
  private final String name;
  private int health;

  /**
   * Constructs a TargetCharacter object, which has a name and a specified health value.
   * 
   * @param name     the name of the target character
   * @param health   the health value of the target character
   * @throws IllegalArgumentException if the name is null or empty, or the health is not positive
   */
  public TargetCharacter(String name, int health) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the target character can't be empty!");
    }
    if (health <= 0) {
      throw new IllegalArgumentException("The health value must be positive!");
    }
    this.name = name;
    this.health = health;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Return the health value of the target character.
   *
   * @return the health value of the target character
   */
  public int getHealth() {
    return health;
  }

  /**
   * Cause a certain amount of damage to the target character and reduce the health value.
   * 
   * @param damage   the damage value that deducted from the target character's health
   * @throws IllegalArgumentException   if the damage is not positive
   */
  public void reduceHealth(int damage) throws IllegalArgumentException {
    if (damage <= 0) {
      throw new IllegalArgumentException("Damage cannot be negative");
    }
    this.health -= damage;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TargetCharacter)) {
      return false;
    }
    Character that = (TargetCharacter) o;
    return this.name.equals(that.getName());
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
  @Override
  public String toString() {
    return String.format("Target character: %s, Health: %d", name, health);
  }
}

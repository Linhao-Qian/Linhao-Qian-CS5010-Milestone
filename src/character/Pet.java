package character;

import space.Space;

/**
 * A Pet represents a pet of the target character.
 */
public class Pet implements Character {
  private final String name;
  private Space space;
  
  /**
   * Constructs a Pet object, which has a unique name.
   * 
   * @param name     the name of the player
   * @throws IllegalArgumentException if the name is null or empty
   */
  public Pet(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the pet can't be empty!");
    }
    this.name = name;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  /**
   * Set the space of the pet.
   *
   * @param space the space of the pet
   * @throws IllegalArgumentException if the space is null
   */
  public void setSpace(Space space) throws IllegalArgumentException {
    if (space == null) {
      throw new IllegalArgumentException("The space of the pet can't be null!");
    }
    this.space = space;
  }
  
  /**
   * Return the space of the pet.
   *
   * @return the space of the pet
   */
  public Space getSpace() {
    return this.space;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pet)) {
      return false;
    }
    Character that = (Pet) o;
    return this.name.equals(that.getName());
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
  @Override
  public String toString() {
    return String.format("Pet name: %s", name);
  }
}

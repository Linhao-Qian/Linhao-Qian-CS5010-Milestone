package character;

import space.Space;

public class Pet implements Character {
  private final String name;
  private Space space;
  
  public Pet(String name) {
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
   */
  public void setSpace(Space space) {
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

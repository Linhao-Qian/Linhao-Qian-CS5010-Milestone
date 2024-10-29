package character.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import character.Pet;
import org.junit.Before;
import org.junit.Test;
import space.MySpace;
import space.Space;

/**
 * A JUnit test class for the pet of the target character.
 */
public class PetTest {

  private Space space;
  private Pet pet;

  /**
   * Set up the pre-defined space, item, computerPlayer and humanPlayer field.
   */
  @Before
  public void setUp() {
    space = new MySpace(0, 0, 3, 3, "Kitchen");
    pet = new Pet("Cat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new Pet("");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithEmptyName() {
    new Pet(null);
  }
  
  @Test
  public void testGetName() {
    assertEquals("Cat", pet.getName());
  }

  @Test
  public void testGetSpace() {
    assertEquals(null, pet.getSpace());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpace() {
    pet.setSpace(null);
  }
  
  @Test
  public void testSetSpace() {
    pet.setSpace(space);
    assertSame(space, pet.getSpace());
  }
}

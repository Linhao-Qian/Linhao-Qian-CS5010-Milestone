package character.test;

import static org.junit.Assert.assertEquals;

import character.TargetCharacter;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the TargetCharacter class.
 */
public class TargetCharacterTest {

  private TargetCharacter character;

  @Before
  public void setUp() {
    character = new TargetCharacter("Leo", 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new TargetCharacter(null, 50);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithEmptyName() {
    new TargetCharacter("", 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidHealth() {
    new TargetCharacter("Doctor", 0);
  }

  @Test
  public void testGetName() {
    assertEquals("Leo", character.getName());
  }

  @Test
  public void testGetHealth() {
    assertEquals(50, character.getHealth());
  }

  @Test
  public void testReduceHealth() {
    character.reduceHealth(3);
    assertEquals(47, character.getHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDamage() {
    character.reduceHealth(-3);
  }
}
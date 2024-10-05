package item.test;

import static org.junit.Assert.assertEquals;

import item.MyItem;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the MyItem class.
 */
public class MyItemTest {

  private MyItem item;

  @Before
  public void setUp() {
    item = new MyItem("Revolver", 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new MyItem(null, 0, 3);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithEmptyName() {
    new MyItem("", 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidPosition() {
    new MyItem("Revolver", -1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidDamage() {
    new MyItem("Revolver", 0, 0);
  }

  @Test
  public void testGetName() {
    assertEquals("Revolver", item.getName());
  }

  @Test
  public void testGetPosition() {
    assertEquals(0, item.getPosition());
  }

  @Test
  public void testGetDamage() {
    assertEquals(3, item.getDamage());
  }
}
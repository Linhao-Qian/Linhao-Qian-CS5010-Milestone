package space.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import item.Item;
import item.MyItem;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import space.MySpace;

/**
 * A JUnit test class for the MySpace class.
 */
public class MySpaceTest {

  private MySpace space;

  @Before
  public void setUp() {
    space = new MySpace(0, 0, 3, 3, "Kitchen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNegativeRow1() {
    new MySpace(-1, 0, 3, 3, "Kitchen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNegativeCol1() {
    new MySpace(0, -1, 3, 3, "Kitchen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidRow2() {
    new MySpace(0, 0, -3, 3, "Kitchen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidCol2() {
    new MySpace(0, 0, 3, -3, "Kitchen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new MySpace(0, 0, 3, 3, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithEmptyName() {
    new MySpace(0, 0, 3, 3, "");
  }

  @Test
  public void testGetPosition() {
    assertArrayEquals(new int[]{0, 0, 3, 3}, space.getPosition());
  }

  @Test
  public void testGetName() {
    assertEquals("Kitchen", space.getName());
  }
  
  @Test
  public void testGetItems() {
    assertArrayEquals(new Item[]{}, space.getItems().toArray());
  }

  @Test
  public void testAddItem() {
    Item item = new MyItem("Revolver", 0, 3);
    space.addItem(item);
    List<Item> items = space.getItems();
    assertEquals(1, items.size());
    assertSame(item, items.get(0));
  }
}
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
import space.Space;

/**
 * A JUnit test class for the MySpace class.
 */
public class MySpaceTest {

  private Space space;

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
    Item item = new MyItem("Revolver", 3);
    space.addItem(item);
    List<Item> items = space.getItems();
    assertEquals(1, items.size());
    assertSame(item, items.get(0));
  }
  
  @Test
  public void testRemoveItem() {
    Item item = new MyItem("Revolver", 3);
    space.addItem(item);
    List<Item> items = space.getItems();
    assertEquals(1, items.size());
    assertSame(item, items.get(0));
    space.removeItem(item);
    assertArrayEquals(new Item[]{}, space.getItems().toArray());
  }
  
  @Test
  public void testGetNeighbors() {
    assertArrayEquals(new MySpace[]{}, space.getNeighbors().toArray());
  }

  @Test
  public void testAddNeighbor() {
    Space neighbor = new MySpace(0, 4, 3, 6, "Foyer");
    space.addNeighbor(neighbor);
    List<Space> neighbors = space.getNeighbors();
    assertEquals(1, neighbors.size());
    assertSame(neighbor, neighbors.get(0));
  }
}
package character.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import item.Item;
import item.MyItem;
import org.junit.Before;
import org.junit.Test;
import space.MySpace;
import space.Space;

/**
 * A JUnit test class for Players.
 */
public class PlayerTest {
  
  private Space space;
  private Item item;
  private ComputerControlledPlayer computerPlayer;
  private HumanControlledPlayer humanPlayer;

  /**
   * Set up the pre-defined space, item, computerPlayer and humanPlayer field.
   */
  @Before
  public void setUp() {
    space = new MySpace(0, 0, 3, 3, "Kitchen");
    item = new MyItem("Revolver", 3);
    computerPlayer = new ComputerControlledPlayer("Leon", space);
    humanPlayer = new HumanControlledPlayer("Leo", space);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testComputerConstructorWithNullName() {
    new ComputerControlledPlayer(null, space);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testHumanConstructorWithNullName() {
    new HumanControlledPlayer(null, space);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testComputerConstructorWithEmptyName() {
    new ComputerControlledPlayer("", space);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testHumanConstructorWithEmptyName() {
    new HumanControlledPlayer("", space);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testComputerConstructorWithNullSpace() {
    new ComputerControlledPlayer("Leon", null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testHumanConstructorWithNullSpace() {
    new HumanControlledPlayer("Leo", null);
  }

  @Test
  public void testGetName() {
    assertEquals("Leo", humanPlayer.getName());
    assertEquals("Leon", computerPlayer.getName());
  }

  @Test
  public void testGetSpace() {
    assertSame(space, humanPlayer.getSpace());
    assertSame(space, computerPlayer.getSpace());
  }

  @Test
  public void testSetSpace() {
    Space newSpace = new MySpace(0, 4, 3, 6, "Drawing Room");
    humanPlayer.setSpace(newSpace);
    computerPlayer.setSpace(newSpace);
    assertSame(newSpace, humanPlayer.getSpace());
    assertSame(newSpace, computerPlayer.getSpace());
  }

  @Test
  public void testGetItems() {
    assertArrayEquals(new Item[]{}, humanPlayer.getItems().toArray());
    assertArrayEquals(new Item[]{}, computerPlayer.getItems().toArray());
  }
  
  @Test
  public void testAddItem() {
    humanPlayer.addItem(item);
    computerPlayer.addItem(item);
    assertEquals(1, humanPlayer.getItems().size());
    assertEquals(1, computerPlayer.getItems().size());
    assertSame(item, humanPlayer.getItems().get(0));
    assertSame(item, computerPlayer.getItems().get(0));
  }
  
  @Test
  public void testGetItem() {
    humanPlayer.addItem(item);
    computerPlayer.addItem(item);
    assertSame(item, humanPlayer.getItem("Revolver"));
    assertSame(item, computerPlayer.getItem("Revolver"));
  }
  
  @Test
  public void testRemoveItem() {
    humanPlayer.addItem(item);
    computerPlayer.addItem(item);
    assertSame(item, humanPlayer.getItem("Revolver"));
    assertSame(item, computerPlayer.getItem("Revolver"));
    humanPlayer.removeItem(item);
    computerPlayer.removeItem(item);
    assertEquals(0, humanPlayer.getItems().size());
    assertEquals(0, computerPlayer.getItems().size());
  }
  
  @Test
  public void testIsNeighbor() {
    Space newSpace = new MySpace(0, 4, 3, 6, "Drawing Room");
    space.addNeighbor(newSpace);
    newSpace.addNeighbor(space);
    humanPlayer.setSpace(newSpace);
    assertTrue(humanPlayer.isNeighbor(computerPlayer));
    assertTrue(computerPlayer.isNeighbor(humanPlayer));
  }
  
  @Test
  public void testIsSameSpace() {
    assertTrue(humanPlayer.isSameSpace(computerPlayer));
    assertTrue(computerPlayer.isSameSpace(humanPlayer));
  }
  
  @Test
  public void testgetMostPowerfulItemName() {
    assertEquals("pokeEyes", computerPlayer.getMostPowerfulItemName());
    computerPlayer.addItem(item);
    assertEquals("Revolver", computerPlayer.getMostPowerfulItemName());
  }
}
package character.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import character.Player;
import item.Item;
import item.MyItem;
import space.MySpace;
import space.Space;
/**
 * A JUnit test class for Players.
 */
public class PlayerTest {
  
  private Space space;
  private Item item;
  private Player computerPlayer;
  private Player humanPlayer;

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
}
package world.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import character.TargetCharacter;
import item.Item;
import item.MyItem;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import space.MySpace;
import space.Space;
import world.MyWorld;

/**
 * A JUnit test class for the MyWorld class.
 */
public class MyWorldTest {

  private MyWorld world;
  private TargetCharacter character;
  private List<Space> spaces;
  private List<Item> items;

  /**
   * Create a virtual world for the unit test.
   */
  @Before
  public void setUp() {
    character = new TargetCharacter("Leo", 50);
    spaces = new ArrayList<>();
    items = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      spaces.add(new MySpace(0, i, 0, i, "Space" + i));
      items.add(new MyItem("Item" + i, i, 3));
    } 
    world = new MyWorld("Mansion", 1, 20, character, spaces, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new MyWorld(null, 1, 20, character, spaces, items);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithEmptyName() {
    new MyWorld("", 1, 20, character, spaces, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidSpaces() {
    new MyWorld("Mansion", 1, 20, character, new ArrayList<>(), items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidItems() {
    new MyWorld("Mansion", 1, 20, character, spaces, new ArrayList<>());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidRows() {
    new MyWorld("Mansion", 0, 20, character, spaces, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidCols() {
    new MyWorld("Mansion", 1, 0, character, spaces, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidItemPosition() {
    List<Item> newItems = new ArrayList<>(items);
    newItems.add(new MyItem("ItemX", 30, 3));
    new MyWorld("Mansion", 1, 20, character, spaces, newItems);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidSpaceRow2() {
    List<Space> newSpaces = new ArrayList<>(spaces);
    newSpaces.add(new MySpace(0, 1, 1, 1, "SpaceX"));
    new MyWorld("Mansion", 1, 20, character, newSpaces, items);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidSpaceCol2() {
    List<Space> newSpaces = new ArrayList<>(spaces);
    newSpaces.add(new MySpace(0, 1, 0, 20, "SpaceX"));
    new MyWorld("Mansion", 1, 20, character, newSpaces, items);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithOverlappingSpaces() {
    List<Space> newSpaces = new ArrayList<>(spaces);
    newSpaces.add(new MySpace(0, 1, 0, 1, "SpaceX"));
    new MyWorld("Mansion", 1, 20, character, newSpaces, items);
  }

  @Test
  public void testGetName() {
    assertEquals("Mansion", world.getName());
  }

  @Test
  public void testGetSize() {
    assertArrayEquals(new int[]{1, 20}, world.getSize());
  }

  @Test
  public void testGetTargetCharacter() {
    assertSame(character, world.getTargetCharacter());
  }

  @Test
  public void testGetItems() {
    assertArrayEquals(items.toArray(), world.getItems().toArray());
  }

  @Test
  public void testGetSpaces() {
    assertArrayEquals(spaces.toArray(), world.getSpaces().toArray());
  }
  
  @Test
  public void testGetTargetCharacterPosition() {
    assertEquals(0, world.getTargetCharacterPosition());
  }

  @Test
  public void testGetNeighbors() {
    List<Space> neighbors = world.getNeighbors(spaces.get(0));
    assertEquals(1, neighbors.size());
    assertSame(spaces.get(1), neighbors.get(0));
  }
  
  @Test
  public void testMoveTargetCharacter() {
    world.moveTargetCharacter();
    assertEquals(1, world.getTargetCharacterPosition());
    for (int i = 0; i < 19; i++) {
      world.moveTargetCharacter();
    }
    assertEquals(0, world.getTargetCharacterPosition());
  }
}
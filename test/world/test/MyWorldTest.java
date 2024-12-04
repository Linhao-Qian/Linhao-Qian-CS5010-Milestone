package world.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import character.ComputerControlledPlayer;
import character.Player;
import item.Item;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import space.Space;
import world.MyWorld;
import world.World;

/**
 * A JUnit test class for the MyWorld class.
 */
public class MyWorldTest {

  private World world;

  /**
   * Create a virtual world for the unit test.
   */
  @Before
  public void setUp() {
    Readable stringReader = new StringReader(
        "36 30 Leo's World\n"
        + "50 Doctor Unlucky\n"
        + "Fortune the Cat\n"
        + "21\n"
        + "12 11 19 20 Dining Hall\n"
        + "20 13 25 18 Drawing Room\n"
        + "26 13 27 17 Foyer\n"
        + "21 26 35 29 Green House\n"
        + "25 19 35 25 Hedge Maze\n"
        + "17  3 21 10 Kitchen\n"
        + " 0  3  5  8 Lancaster Room\n"
        + " 4 21  9 27 Library\n"
        + "10 21 14 26 Trophy Room\n"
        + "22  5 23 12 Wine Cellar\n"
        + "30  7 35 11 Winter Garden\n"
        + "21 19 24 25 Armory\n"
        + " 2  9  9 14 Lilac Room\n"
        + " 2 15  9 20 Master Suite\n"
        + " 0 21  3 28 Nursery\n"
        + "10  5 16 10 Parlor\n"
        + "28 12 35 18 Piazza\n"
        + " 6  3  9  8 Servants' Quarters\n"
        + "10 11 11 20 Tennessee Room\n"
        + "15 21 20 28 Billiard Room\n"
        + "28  0 34  6 Carriage House\n"
        + "20\n"
        + "7 2 Letter Opener\n"
        + "12 2 Shoe Horn\n"
        + "4 2 Crepe Pan\n"
        + "0 3 Revolver\n"
        + "8 3 Sharp Knife\n"
        + "16 2 Broom Stick\n"
        + "6 2 Trowel\n"
        + "15 3 Civil War Cannon\n"
        + "2 4 Big Red Hammer\n"
        + "2 4 Chain Saw\n"
        + "6 2 Pinking Shears\n"
        + "1 2 Billiard Cue\n"
        + "13 2 Bad Cream\n"
        + "19 2 Rat Poison\n"
        + "11 2 Tight Hat\n"
        + "9 3 Silken Cord\n"
        + "18 3 Duck Decoy\n"
        + "19 2 Piece of Rope\n"
        + "18 2 Monkey Hand\n"
        + "8 3 Loud Noise\n");
    world = new MyWorld(stringReader);
  }

  @Test
  public void testReadingWorld() {
    assertEquals(world.toString(),
        "The world's name is: Leo's World\n"
        + "The world's size is: 36 x 30\n"
        + "----------------------------------------------------------------------------------\n"
        + "The spaces and items information is as follows:\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Dining Hall;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The space has 6 neighbor(s):\n"
        + "Drawing Room, Kitchen, Trophy Room, Parlor, Tennessee Room, Billiard Room\n"
        + "The pet is in this space now:\n"
        + "Pet name: Fortune the Cat\n"
        + "The target character is in this space now:\n"
        + "Target character: Doctor Unlucky, Health: 50\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Drawing Room;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Billiard Cue, Damage: 2\n"
        + "The space has 5 neighbor(s):\n"
        + "Dining Hall, Foyer, Hedge Maze, Wine Cellar, Armory\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Foyer;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Big Red Hammer, Damage: 4\n"
        + "Item name: Chain Saw, Damage: 4\n"
        + "The space has 2 neighbor(s):\n"
        + "Drawing Room, Piazza\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Green House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Hedge Maze, Armory, Billiard Room\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Hedge Maze;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Crepe Pan, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Drawing Room, Green House, Armory, Piazza\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Kitchen;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Dining Hall, Wine Cellar, Parlor\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Lancaster Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Trowel, Damage: 2\n"
        + "Item name: Pinking Shears, Damage: 2\n"
        + "The space has 2 neighbor(s):\n"
        + "Lilac Room, Servants' Quarters\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Library;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Letter Opener, Damage: 2\n"
        + "The space has 3 neighbor(s):\n"
        + "Trophy Room, Master Suite, Nursery\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Trophy Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Sharp Knife, Damage: 3\n"
        + "Item name: Loud Noise, Damage: 3\n"
        + "The space has 4 neighbor(s):\n"
        + "Dining Hall, Library, Tennessee Room, Billiard Room\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Wine Cellar;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Silken Cord, Damage: 3\n"
        + "The space has 2 neighbor(s):\n"
        + "Drawing Room, Kitchen\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Winter Garden;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 2 neighbor(s):\n"
        + "Piazza, Carriage House\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Armory;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Tight Hat, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Drawing Room, Green House, Hedge Maze, Billiard Room\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Lilac Room;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Shoe Horn, Damage: 2\n"
        + "The space has 5 neighbor(s):\n"
        + "Lancaster Room, Master Suite, Parlor, Servants' Quarters, Tennessee Room\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Master Suite;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Bad Cream, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Library, Lilac Room, Nursery, Tennessee Room\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Nursery;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 2 neighbor(s):\n"
        + "Library, Master Suite\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Parlor;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Civil War Cannon, Damage: 3\n"
        + "The space has 5 neighbor(s):\n"
        + "Dining Hall, Kitchen, Lilac Room, Servants' Quarters, Tennessee Room\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Piazza;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Broom Stick, Damage: 2\n"
        + "The space has 3 neighbor(s):\n"
        + "Foyer, Hedge Maze, Winter Garden\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Servants' Quarters;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Lancaster Room, Lilac Room, Parlor\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Tennessee Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Duck Decoy, Damage: 3\n"
        + "Item name: Monkey Hand, Damage: 2\n"
        + "The space has 5 neighbor(s):\n"
        + "Dining Hall, Trophy Room, Lilac Room, Master Suite, Parlor\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Billiard Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Rat Poison, Damage: 2\n"
        + "Item name: Piece of Rope, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Dining Hall, Green House, Trophy Room, Armory\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Carriage House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 1 neighbor(s):\n"
        + "Winter Garden\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n");
  }
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWorldDescription() {
    Readable stringReader = new StringReader(
        "36 0 Leo's World\n"
        + "50 Doctor Unlucky\n"
        + "Fortune the Cat\n"
        + "21\n"
        + "12 11 19 20 Dining Hall\n"
        + "20 13 25 18 Drawing Room\n"
        + "26 13 27 17 Foyer\n"
        + "21 26 35 29 Green House\n"
        + "25 19 35 25 Hedge Maze\n"
        + "17  3 21 10 Kitchen\n"
        + " 0  3  5  8 Lancaster Room\n"
        + " 4 21  9 27 Library\n"
        + "10 21 14 26 Trophy Room\n"
        + "22  5 23 12 Wine Cellar\n"
        + "30  7 35 11 Winter Garden\n"
        + "21 19 24 25 Armory\n"
        + " 2  9  9 14 Lilac Room\n"
        + " 2 15  9 20 Master Suite\n"
        + " 0 21  3 28 Nursery\n"
        + "10  5 16 10 Parlor\n"
        + "28 12 35 18 Piazza\n"
        + " 6  3  9  8 Servants' Quarters\n"
        + "10 11 11 20 Tennessee Room\n"
        + "15 21 20 28 Billiard Room\n"
        + "28  0 34  6 Carriage House\n"
        + "20\n"
        + "7 2 Letter Opener\n"
        + "12 2 Shoe Horn\n"
        + "4 2 Crepe Pan\n"
        + "0 3 Revolver\n"
        + "8 3 Sharp Knife\n"
        + "16 2 Broom Stick\n"
        + "6 2 Trowel\n"
        + "15 3 Civil War Cannon\n"
        + "2 4 Big Red Hammer\n"
        + "2 4 Chain Saw\n"
        + "6 2 Pinking Shears\n"
        + "1 2 Billiard Cue\n"
        + "13 2 Bad Cream\n"
        + "19 2 Rat Poison\n"
        + "11 2 Tight Hat\n"
        + "9 3 Silken Cord\n"
        + "18 3 Duck Decoy\n"
        + "19 2 Piece of Rope\n"
        + "18 2 Monkey Hand\n"
        + "8 3 Loud Noise\n");
    new MyWorld(stringReader);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpace() {
    Readable stringReader = new StringReader(
        "36 30 Leo's World\n"
        + "50 Doctor Unlucky\n"
        + "Fortune the Cat\n"
        + "21\n"
        + "12 11 19 20 Dining Hall\n"
        + "20 13 25 18 Drawing Room\n"
        + "26 13 27 17 Foyer\n"
        + "21 26 35 70 Green House\n"
        + "25 19 35 25 Hedge Maze\n"
        + "17  3 21 10 Kitchen\n"
        + " 0  3  5  8 Lancaster Room\n"
        + " 4 21  9 27 Library\n"
        + "10 21 14 26 Trophy Room\n"
        + "22  5 23 12 Wine Cellar\n"
        + "30  7 35 11 Winter Garden\n"
        + "21 19 24 25 Armory\n"
        + " 2  9  9 14 Lilac Room\n"
        + " 2 15  9 20 Master Suite\n"
        + " 0 21  3 28 Nursery\n"
        + "10  5 16 10 Parlor\n"
        + "28 12 35 18 Piazza\n"
        + " 6  3  9  8 Servants' Quarters\n"
        + "10 11 11 20 Tennessee Room\n"
        + "15 21 20 28 Billiard Room\n"
        + "28  0 34  6 Carriage House\n"
        + "20\n"
        + "7 2 Letter Opener\n"
        + "12 2 Shoe Horn\n"
        + "4 2 Crepe Pan\n"
        + "0 3 Revolver\n"
        + "8 3 Sharp Knife\n"
        + "16 2 Broom Stick\n"
        + "6 2 Trowel\n"
        + "15 3 Civil War Cannon\n"
        + "2 4 Big Red Hammer\n"
        + "2 4 Chain Saw\n"
        + "6 2 Pinking Shears\n"
        + "1 2 Billiard Cue\n"
        + "13 2 Bad Cream\n"
        + "19 2 Rat Poison\n"
        + "11 2 Tight Hat\n"
        + "9 3 Silken Cord\n"
        + "18 3 Duck Decoy\n"
        + "19 2 Piece of Rope\n"
        + "18 2 Monkey Hand\n"
        + "8 3 Loud Noise\n");
    new MyWorld(stringReader);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidItem() {
    Readable stringReader = new StringReader(
        "36 30 Leo's World\n"
        + "50 Doctor Unlucky\n"
        + "Fortune the Cat\n"
        + "21\n"
        + "12 11 19 20 Dining Hall\n"
        + "20 13 25 18 Drawing Room\n"
        + "26 13 27 17 Foyer\n"
        + "21 26 35 29 Green House\n"
        + "25 19 35 25 Hedge Maze\n"
        + "17  3 21 10 Kitchen\n"
        + " 0  3  5  8 Lancaster Room\n"
        + " 4 21  9 27 Library\n"
        + "10 21 14 26 Trophy Room\n"
        + "22  5 23 12 Wine Cellar\n"
        + "30  7 35 11 Winter Garden\n"
        + "21 19 24 25 Armory\n"
        + " 2  9  9 14 Lilac Room\n"
        + " 2 15  9 20 Master Suite\n"
        + " 0 21  3 28 Nursery\n"
        + "10  5 16 10 Parlor\n"
        + "28 12 35 18 Piazza\n"
        + " 6  3  9  8 Servants' Quarters\n"
        + "10 11 11 20 Tennessee Room\n"
        + "15 21 20 28 Billiard Room\n"
        + "28  0 34  6 Carriage House\n"
        + "20\n"
        + "27 2 Letter Opener\n"
        + "12 2 Shoe Horn\n"
        + "4 2 Crepe Pan\n"
        + "0 3 Revolver\n"
        + "8 3 Sharp Knife\n"
        + "16 2 Broom Stick\n"
        + "6 2 Trowel\n"
        + "15 3 Civil War Cannon\n"
        + "2 4 Big Red Hammer\n"
        + "2 4 Chain Saw\n"
        + "6 2 Pinking Shears\n"
        + "1 2 Billiard Cue\n"
        + "13 2 Bad Cream\n"
        + "19 2 Rat Poison\n"
        + "11 2 Tight Hat\n"
        + "9 3 Silken Cord\n"
        + "18 3 Duck Decoy\n"
        + "19 2 Piece of Rope\n"
        + "18 2 Monkey Hand\n"
        + "8 3 Loud Noise\n");
    world = new MyWorld(stringReader);
  }
  
  @Test
  public void testDisplayNoNeighbors() {
    Readable stringReader = new StringReader(
        "36 30 Leo's World\n"
        + "50 Doctor Unlucky\n"
        + "Fortune the Cat\n"
        + "21\n"
        + "12 11 19 20 Dining Hall\n"
        + "20 13 25 18 Drawing Room\n"
        + "26 13 27 17 Foyer\n"
        + "21 26 35 29 Green House\n"
        + "25 19 35 25 Hedge Maze\n"
        + "17  3 21 10 Kitchen\n"
        + " 0  3  5  8 Lancaster Room\n"
        + " 4 21  9 27 Library\n"
        + "10 21 14 26 Trophy Room\n"
        + "22  5 23 12 Wine Cellar\n"
        + "30  7 35 11 Winter Garden\n"
        + "21 19 24 25 Armory\n"
        + " 2  9  9 14 Lilac Room\n"
        + " 2 15  9 20 Master Suite\n"
        + " 0 21  3 28 Nursery\n"
        + "10  5 16 10 Parlor\n"
        + "28 12 35 18 Piazza\n"
        + " 6  3  9  8 Servants' Quarters\n"
        + "10 11 11 20 Tennessee Room\n"
        + "15 21 20 28 Billiard Room\n"
        + "28  0 34  5 Carriage House\n"
        + "20\n"
        + "7 2 Letter Opener\n"
        + "12 2 Shoe Horn\n"
        + "4 2 Crepe Pan\n"
        + "0 3 Revolver\n"
        + "8 3 Sharp Knife\n"
        + "16 2 Broom Stick\n"
        + "6 2 Trowel\n"
        + "15 3 Civil War Cannon\n"
        + "2 4 Big Red Hammer\n"
        + "2 4 Chain Saw\n"
        + "6 2 Pinking Shears\n"
        + "1 2 Billiard Cue\n"
        + "13 2 Bad Cream\n"
        + "19 2 Rat Poison\n"
        + "11 2 Tight Hat\n"
        + "9 3 Silken Cord\n"
        + "18 3 Duck Decoy\n"
        + "19 2 Piece of Rope\n"
        + "18 2 Monkey Hand\n"
        + "8 3 Loud Noise\n");
    world = new MyWorld(stringReader);
    assertArrayEquals(new Space[]{}, world.getSpaces().get(20).getNeighbors().toArray());
    assertEquals(
        "Space name: Carriage House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 0 neighbor(s):\n"
        + "\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n",
        world.displaySpaceInformation(world.getSpaces().get(20).getName()));
  }

  @Test
  public void testDisplayOneNeighbor() {
    assertArrayEquals(new Space[]{world.getSpaces().get(10)},
        world.getSpaces().get(20).getNeighbors().toArray());
    assertEquals(
        "Space name: Carriage House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 1 neighbor(s):\n"
        + "Winter Garden\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n",
        world.displaySpaceInformation(world.getSpaces().get(20).getName()));
  }
  
  @Test
  public void testDisplayMutipleNeighbors() {
    assertArrayEquals(new Space[]{world.getSpaces().get(1), world.getSpaces().get(16)},
        world.getSpaces().get(2).getNeighbors().toArray());
    assertEquals(
        "Space name: Foyer;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Big Red Hammer, Damage: 4\n"
        + "Item name: Chain Saw, Damage: 4\n"
        + "The space has 2 neighbor(s):\n"
        + "Drawing Room, Piazza\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n",
        world.displaySpaceInformation(world.getSpaces().get(2).getName()));
  }

  @Test
  public void testDisplayOneItem() {
    assertEquals(
        "Space name: Dining Hall;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The space has 6 neighbor(s):\n"
        + "Drawing Room, Kitchen, Trophy Room, Parlor, Tennessee Room, Billiard Room\n"
        + "The pet is in this space now:\n"
        + "Pet name: Fortune the Cat\n"
        + "The target character is in this space now:\n"
        + "Target character: Doctor Unlucky, Health: 50\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n",
        world.displaySpaceInformation(world.getSpaces().get(0).getName()));
  }
  
  @Test
  public void testDisplayNoItems() {
    assertEquals("Space name: Carriage House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 1 neighbor(s):\n"
        + "Winter Garden\n"
        + "There are 0 player(s) in this space:\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n",
        world.displaySpaceInformation(world.getSpaces().get(20).getName()));
  }
  
  @Test
  public void testDisplaySpaceWithPlayers() {
    world.addHumanPlayer("Leo", "Carriage House");
    assertEquals("Space name: Carriage House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 1 neighbor(s):\n"
        + "Winter Garden\n"
        + "There are 1 player(s) in this space:\n"
        + "Leo\n"
        + "----------------------------------------------------------------------------------\n",
        world.displaySpaceInformation(world.getSpaces().get(20).getName()));
  }
  
  @Test
  public void testGetTargetCharacterPosition() {
    assertEquals(0, world.getTargetCharacterPosition());
  }

  @Test
  public void testMoveTargetCharacter() {
    assertEquals(0, world.getTargetCharacterPosition());
    world.moveTargetCharacter();
    assertEquals(1, world.getTargetCharacterPosition());
    world.moveTargetCharacter();
    world.moveTargetCharacter();
    assertEquals(3, world.getTargetCharacterPosition());
    for (int i = 0; i < 18; i++) {
      world.moveTargetCharacter();
    }
    assertEquals(0, world.getTargetCharacterPosition());
  }
  
  @Test
  public void testGetPlayers() {
    assertEquals(0, world.getPlayers().size());
  }
  
  @Test
  public void testAddHumanPlayer() {
    assertEquals(0, world.getPlayers().size());
    world.addHumanPlayer("Leo", "Dining Hall");
    assertEquals(1, world.getPlayers().size());
    Player player1 = world.getPlayers().get(0);
    assertEquals("Leo", player1.getName());
    assertSame(player1.getSpace(), world.getSpaces().get(0));
    world.addHumanPlayer("Leon", "Drawing Room");
    assertEquals(2, world.getPlayers().size());
    Player player2 = world.getPlayers().get(1);
    assertEquals("Leon", player2.getName());
    assertSame(player2.getSpace(), world.getSpaces().get(1));
  }
  
  @Test
  public void testAddComputerPlayer() {
    assertEquals(0, world.getPlayers().size());
    world.addComputerPlayer("Leo", "Dining Hall");
    assertEquals(1, world.getPlayers().size());
    Player player1 = world.getPlayers().get(0);
    assertEquals("Leo", player1.getName());
    assertSame(player1.getSpace(), world.getSpaces().get(0));
    world.addComputerPlayer("Leon", "Drawing Room");
    assertEquals(2, world.getPlayers().size());
    Player player2 = world.getPlayers().get(1);
    assertEquals("Leon", player2.getName());
    assertSame(player2.getSpace(), world.getSpaces().get(1));
  }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testAddHumanPlayersExceedingLimit() {
    world.addHumanPlayer("a", "Dining Hall");
    world.addHumanPlayer("b", "Dining Hall");
    world.addHumanPlayer("c", "Dining Hall");
    world.addHumanPlayer("d", "Dining Hall");
    world.addHumanPlayer("e", "Dining Hall");
    world.addHumanPlayer("f", "Dining Hall");
    world.addHumanPlayer("g", "Dining Hall");
    world.addHumanPlayer("h", "Dining Hall");
    world.addHumanPlayer("i", "Dining Hall");
    world.addHumanPlayer("j", "Dining Hall");
    world.addHumanPlayer("k", "Dining Hall");
  }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testAddComputerPlayersExceedingLimit() {
    world.addComputerPlayer("a", "Dining Hall");
    world.addComputerPlayer("b", "Dining Hall");
    world.addComputerPlayer("c", "Dining Hall");
    world.addComputerPlayer("d", "Dining Hall");
    world.addComputerPlayer("e", "Dining Hall");
    world.addComputerPlayer("f", "Dining Hall");
    world.addComputerPlayer("g", "Dining Hall");
    world.addComputerPlayer("h", "Dining Hall");
    world.addComputerPlayer("i", "Dining Hall");
    world.addComputerPlayer("j", "Dining Hall");
    world.addComputerPlayer("k", "Dining Hall");
  }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testAddMixedPlayersExceedingLimit() {
    world.addHumanPlayer("a", "Dining Hall");
    world.addHumanPlayer("b", "Dining Hall");
    world.addHumanPlayer("c", "Dining Hall");
    world.addHumanPlayer("d", "Dining Hall");
    world.addHumanPlayer("e", "Dining Hall");
    world.addHumanPlayer("f", "Dining Hall");
    world.addComputerPlayer("g", "Dining Hall");
    world.addComputerPlayer("h", "Dining Hall");
    world.addComputerPlayer("i", "Dining Hall");
    world.addComputerPlayer("j", "Dining Hall");
    world.addComputerPlayer("k", "Dining Hall");
  }
  
  @Test
  public void testDisplayHumanPlayer() {
    assertEquals(0, world.getPlayers().size());
    world.addHumanPlayer("Leo", "Dining Hall");
    assertEquals(1, world.getPlayers().size());
    world.resetTurn();
    world.pickUpItem("Revolver");
    assertEquals(
        "Player name: Leo, carrying 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The player is currently in: Dining Hall\n"
        + "----------------------------------------------------------------------------------\n",
        world.displayPlayerInformation(world.getPlayers().get(0).getName()));
    world.addHumanPlayer("Leon", "Drawing Room");
    assertEquals(2, world.getPlayers().size());
    assertEquals(
        "Player name: Leon, carrying 0 item(s):\n"
        + "\n"
        + "The player is currently in: Drawing Room\n"
        + "----------------------------------------------------------------------------------\n",
        world.displayPlayerInformation(world.getPlayers().get(1).getName()));
  }
  
  @Test
  public void testDisplayComputerPlayer() {
    assertEquals(0, world.getPlayers().size());
    world.addComputerPlayer("Leo", "Dining Hall");
    assertEquals(1, world.getPlayers().size());
    world.resetTurn();
    world.pickUpItem("Revolver");
    assertEquals(
        "Player name: Leo, carrying 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The player is currently in: Dining Hall\n"
        + "----------------------------------------------------------------------------------\n",
        world.displayPlayerInformation(world.getPlayers().get(0).getName()));
    world.addComputerPlayer("Leon", "Drawing Room");
    assertEquals(2, world.getPlayers().size());
    assertEquals(
        "Player name: Leon, carrying 0 item(s):\n"
        + "\n"
        + "The player is currently in: Drawing Room\n"
        + "----------------------------------------------------------------------------------\n",
        world.displayPlayerInformation(world.getPlayers().get(1).getName()));
  }
  
  @Test
  public void testTakingTurns() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertSame(world.getTurn(), world.getPlayers().get(0));
    world.addComputerPlayer("Leon", "Drawing Room");
    world.nextTurn();
    assertSame(world.getTurn(), world.getPlayers().get(1));
  }
  
  @Test
  public void testPlayerMovingAround() {
    world.addHumanPlayer("Leo", "Dining Hall");
    Player player1 = world.getPlayers().get(0);
    assertSame(player1.getSpace(), world.getSpaces().get(0));
    world.resetTurn();
    world.movePlayer("Drawing Room");
    assertSame(player1.getSpace(), world.getSpace("Drawing Room"));
    world.addComputerPlayer("Leon", "Drawing Room");
    Player player2 = world.getPlayers().get(1);
    assertSame(player2.getSpace(), world.getSpaces().get(1));
    world.nextTurn();
    world.movePlayer("Dining Hall");
    assertSame(player2.getSpace(), world.getSpace("Dining Hall"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerMovingNonNeighberSpace() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.movePlayer("Carriage House");
  }
  
  @Test
  public void testPlayerPickUpItem() {
    world.addHumanPlayer("Leo", "Dining Hall");
    Player player1 = world.getPlayers().get(0);
    world.resetTurn();
    Item item1 = world.getSpaces().get(0).getItems().get(0);
    world.pickUpItem("Revolver");
    assertEquals(1, player1.getItems().size());
    assertSame(player1.getItems().get(0), item1);
    world.movePlayer("Drawing Room");
    Item item2 = world.getSpaces().get(1).getItems().get(0);
    world.pickUpItem("Billiard Cue");
    assertEquals(2, player1.getItems().size());
    assertSame(player1.getItems().get(1), item2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickUpItemNonExist() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.pickUpItem("Letter Opener");
  }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testPlayerPickUpItemBeyondMaximum() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.pickUpItem("Revolver");
    world.movePlayer("Trophy Room");
    world.pickUpItem("Sharp Knife");
    world.pickUpItem("Loud Noise");
  }
  
  @Test
  public void testLookAround() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertEquals(
        "Leo is looking around:\n"
        + "Leo is currently in:\n"
        + "Space name: Dining Hall;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The space has 6 neighbor(s):\n"
        + "Drawing Room, Kitchen, Trophy Room, Parlor, Tennessee Room, Billiard Room\n"
        + "The pet is in this space now:\n"
        + "Pet name: Fortune the Cat\n"
        + "The target character is in this space now:\n"
        + "Target character: Doctor Unlucky, Health: 50\n"
        + "There are 1 player(s) in this space:\n"
        + "Leo\n"
        + "----------------------------------------------------------------------------------\n"
        + "\n"
        + "The neighbor(s) information is as follows:\n"
        + "Space name: Drawing Room;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Billiard Cue, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Kitchen;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Trophy Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Sharp Knife, Damage: 3\n"
        + "Item name: Loud Noise, Damage: 3\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Parlor;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Civil War Cannon, Damage: 3\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Tennessee Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Duck Decoy, Damage: 3\n"
        + "Item name: Monkey Hand, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Billiard Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Rat Poison, Damage: 2\n"
        + "Item name: Piece of Rope, Damage: 2\n"
        + "The space has 0 player(s):\n\n",
        world.lookAround());
    world.addComputerPlayer("Leon", "Dining Hall");
    world.nextTurn();
    assertEquals(
        "Leon is looking around:\n"
        + "Leon is currently in:\n"
        + "Space name: Dining Hall;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The space has 6 neighbor(s):\n"
        + "Drawing Room, Kitchen, Trophy Room, Parlor, Tennessee Room, Billiard Room\n"
        + "There are 2 player(s) in this space:\n"
        + "Leo, Leon\n"
        + "----------------------------------------------------------------------------------\n"
        + "\n"
        + "The neighbor(s) information is as follows:\n"
        + "The pet Fortune the Cat is in Drawing Room now, you can't get the space's information!\n"
        + "\n----------------------------------------------------------------------------------\n"
        + "Space name: Kitchen;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Trophy Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Sharp Knife, Damage: 3\n"
        + "Item name: Loud Noise, Damage: 3\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Parlor;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Civil War Cannon, Damage: 3\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Tennessee Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Duck Decoy, Damage: 3\n"
        + "Item name: Monkey Hand, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Billiard Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Rat Poison, Damage: 2\n"
        + "Item name: Piece of Rope, Damage: 2\n"
        + "The space has 0 player(s):\n\n",
        world.lookAround());
  }
  
  @Test
  public void testLookAroundNoItems() {
    world.addHumanPlayer("Leo", "Green House");
    world.resetTurn();
    assertEquals(
        "Leo is looking around:\n"
        + "Leo is currently in:\n"
        + "Space name: Green House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Hedge Maze, Armory, Billiard Room\n"
        + "There are 1 player(s) in this space:\n"
        + "Leo\n"
        + "----------------------------------------------------------------------------------\n"
        + "\n"
        + "The neighbor(s) information is as follows:\n"
        + "Space name: Hedge Maze;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Crepe Pan, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Armory;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Tight Hat, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Billiard Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Rat Poison, Damage: 2\n"
        + "Item name: Piece of Rope, Damage: 2\n"
        + "The space has 0 player(s):\n\n",
        world.lookAround());
  }
  
  @Test
  public void testLookAroundNeighboringPlayers() {
    world.addHumanPlayer("Leo", "Green House");
    world.addHumanPlayer("Leon", "Hedge Maze");
    world.resetTurn();
    assertEquals(
        "Leo is looking around:\n"
        + "Leo is currently in:\n"
        + "Space name: Green House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Hedge Maze, Armory, Billiard Room\n"
        + "There are 1 player(s) in this space:\n"
        + "Leo\n"
        + "----------------------------------------------------------------------------------\n"
        + "\n"
        + "The neighbor(s) information is as follows:\n"
        + "Space name: Hedge Maze;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Crepe Pan, Damage: 2\n"
        + "The space has 1 player(s):\n"
        + "Leon\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Armory;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Tight Hat, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Billiard Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Rat Poison, Damage: 2\n"
        + "Item name: Piece of Rope, Damage: 2\n"
        + "The space has 0 player(s):\n\n",
        world.lookAround());
  }
  
  @Test
  public void testLookAroundNeighboringTarget() {
    world.addHumanPlayer("Leo", "Hedge Maze");
    world.resetTurn();
    world.nextTurn();
    world.nextTurn();
    world.nextTurn();
    assertEquals(
        "Leo is looking around:\n"
        + "Leo is currently in:\n"
        + "Space name: Hedge Maze;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Crepe Pan, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Drawing Room, Green House, Armory, Piazza\n"
        + "There are 1 player(s) in this space:\n"
        + "Leo\n"
        + "----------------------------------------------------------------------------------\n"
        + "\n"
        + "The neighbor(s) information is as follows:\n"
        + "Space name: Drawing Room;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Billiard Cue, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Green House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "The target character is in this space now:\n"
        + "Target character: Doctor Unlucky, Health: 50\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Armory;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Tight Hat, Damage: 2\n"
        + "The space has 0 player(s):\n"
        + "\n"
        + "----------------------------------------------------------------------------------\n"
        + "The pet Fortune the Cat is in Piazza now, you can't get the space's information!\n\n",
        world.lookAround());
  }
  
  @Test
  public void testPetInitialSpace() {
    assertSame(world.getSpaces().get(world.getTargetCharacterPosition()),
        world.getPet().getSpace());
  }
  
  @Test
  public void testMovePet() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertSame(world.getSpaces().get(world.getTargetCharacterPosition()),
        world.getPet().getSpace());
    assertEquals("Dining Hall", world.getPet().getSpace().getName());
    world.movePet("Kitchen");
    world.nextTurn();
    assertEquals("Dining Hall", world.getPet().getSpace().getName());
    world.nextTurn();
    assertEquals("Drawing Room", world.getPet().getSpace().getName());
  }
  
  @Test
  public void testPetWandering() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertSame(world.getSpaces().get(world.getTargetCharacterPosition()),
        world.getPet().getSpace());
    world.nextTurn();
    assertEquals("Drawing Room", world.getPet().getSpace().getName());
    world.nextTurn();
    assertEquals("Foyer", world.getPet().getSpace().getName());
    world.nextTurn();
    assertEquals("Piazza", world.getPet().getSpace().getName());
    world.nextTurn();
    assertEquals("Hedge Maze", world.getPet().getSpace().getName());
  }
  
  @Test
  public void testCanBeSeenByOthersNeighborSpace() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertFalse(world.canBeSeenByOthers());
    world.addComputerPlayer("Leon", "Kitchen");
    assertEquals("Dining Hall", world.getPet().getSpace().getName());
    assertFalse(world.canBeSeenByOthers());
    world.nextTurn();
    assertEquals("Drawing Room", world.getPet().getSpace().getName());
    assertTrue(world.canBeSeenByOthers());
  }
  
  @Test
  public void testCanBeSeenByOthersSameSpace() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertFalse(world.canBeSeenByOthers());
    world.addComputerPlayer("Leon", "Dining Hall");
    assertEquals("Dining Hall", world.getPet().getSpace().getName());
    assertTrue(world.canBeSeenByOthers());
    world.nextTurn();
    assertEquals("Drawing Room", world.getPet().getSpace().getName());
    assertTrue(world.canBeSeenByOthers());
  }
  
  @Test
  public void testCanBeSeenByOthersNonNeighborSpace() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    assertFalse(world.canBeSeenByOthers());
    world.addComputerPlayer("Leon", "Lancaster Room");
    assertFalse(world.canBeSeenByOthers());
    world.nextTurn();
    assertFalse(world.canBeSeenByOthers());
  }
  
  @Test
  public void testComputerPlayerAutomaticMakeAnAttempt() {
    world.addComputerPlayer("Leo", "Dining Hall");
    world.resetTurn();
    ComputerControlledPlayer player = (ComputerControlledPlayer) world.getTurn();
    assertEquals("automaticMakeAnAttempt", player.getRandomOperation(world));
  }
  
  @Test
  public void testComputerPlayerMakeAnAttemptSuccessfully() {
    world.addComputerPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.pickUpItem("Revolver");
    assertEquals("Revolver", world.getTurn().getItems().get(0).getName());
    assertTrue(world.makeAnAttempt("Revolver"));
    assertEquals(47, world.getTargetCharacter().getHealth());
    assertEquals(0, world.getTurn().getItems().size());
  }
  
  @Test
  public void testHumanPlayerMakeAnAttemptSuccessfully() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.pickUpItem("Revolver");
    assertEquals("Revolver", world.getTurn().getItems().get(0).getName());
    assertTrue(world.makeAnAttempt("Revolver"));
    assertEquals(47, world.getTargetCharacter().getHealth());
    assertEquals(0, world.getTurn().getItems().size());
  }
  
  @Test
  public void testComputerPlayerMakeAnAttemptFailSameSpace() {
    world.addComputerPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.addComputerPlayer("Leon", "Dining Hall");
    world.pickUpItem("Revolver");
    assertFalse(world.makeAnAttempt("Revolver"));
    assertEquals(50, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testHumanPlayerMakeAnAttemptFailSameSpace() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.addHumanPlayer("Leon", "Dining Hall");
    world.pickUpItem("Revolver");
    assertFalse(world.makeAnAttempt("Revolver"));
    assertEquals(50, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testComputerPlayerMakeAnAttemptSuccessfullyWithPetHelp() {
    world.addComputerPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.addComputerPlayer("Leon", "Drawing Room");
    world.pickUpItem("Revolver");
    assertTrue(world.makeAnAttempt("Revolver"));
    assertEquals(47, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testHumanPlayerMakeAnAttemptSuccessfullyWithPetHelp() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.addHumanPlayer("Leon", "Drawing Room");
    world.pickUpItem("Revolver");
    assertTrue(world.makeAnAttempt("Revolver"));
    assertEquals(47, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testComputerPlayerMakeAnAttemptFailWithoutPetHelp() {
    world.addComputerPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.movePet("Kitchen");
    world.addComputerPlayer("Leon", "Drawing Room");
    world.pickUpItem("Revolver");
    assertFalse(world.makeAnAttempt("Revolver"));
    assertEquals(50, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testHumanPlayerMakeAnAttemptFailWithoutPetHelp() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.movePet("Kitchen");
    world.addHumanPlayer("Leon", "Drawing Room");
    world.pickUpItem("Revolver");
    assertFalse(world.makeAnAttempt("Revolver"));
    assertEquals(50, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testMakeAnAttemptFailWithNeighboringPlayerAndPet() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.movePet("Drawing Room");
    world.addHumanPlayer("Leon", "Drawing Room");
    world.pickUpItem("Revolver");
    assertFalse(world.makeAnAttempt("Revolver"));
    assertEquals(50, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testComputerPlayerPokeEyes() {
    world.addComputerPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.makeAnAttempt("pokeEyes");
    assertEquals(49, world.getTargetCharacter().getHealth());
  }
  
  @Test
  public void testHumanPlayerPokeEyes() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.makeAnAttempt("pokeEyes");
    assertEquals(49, world.getTargetCharacter().getHealth());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPokeEyesInvalid() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.pickUpItem("Revolver");
    world.makeAnAttempt("pokeEyes");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMakeAnAttemptWithNonExistItem() {
    world.addHumanPlayer("Leo", "Dining Hall");
    world.resetTurn();
    world.makeAnAttempt("Revolver");
  }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testMakeAnAttemptWithNonExistTarget() {
    world.addHumanPlayer("Leo", "Drawing Room");
    world.resetTurn();
    world.makeAnAttempt("pokeEyes");
  }
  
  @Test
  public void testGetSpace() {
    Space space1 = world.getSpace(0, 0);
    assertNull(space1);
    Space space2 = world.getSpace(100, 30);
    assertEquals(space2, world.getSpace("Lancaster Room"));
  }
}
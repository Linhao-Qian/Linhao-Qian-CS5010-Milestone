package world.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import space.Space;
import world.MyWorld;

/**
 * A JUnit test class for the MyWorld class.
 */
public class MyWorldTest {

  private MyWorld world;

  /**
   * Create a virtual world for the unit test.
   */
  @Before
  public void setUp() {
    Readable stringReader = new StringReader(
        "36 30 Leo's World\n"
        + "50 Doctor Unlucky\n"
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
        + "The target character is in this space now:\n"
        + "Target character: Doctor Unlucky, Health: 50\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Drawing Room;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Billiard Cue, Damage: 2\n"
        + "The space has 5 neighbor(s):\n"
        + "Dining Hall, Foyer, Hedge Maze, Wine Cellar, Armory\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Foyer;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Big Red Hammer, Damage: 4\n"
        + "Item name: Chain Saw, Damage: 4\n"
        + "The space has 2 neighbor(s):\n"
        + "Drawing Room, Piazza\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Green House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Hedge Maze, Armory, Billiard Room\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Hedge Maze;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Crepe Pan, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Drawing Room, Green House, Armory, Piazza\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Kitchen;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Dining Hall, Wine Cellar, Parlor\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Lancaster Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Trowel, Damage: 2\n"
        + "Item name: Pinking Shears, Damage: 2\n"
        + "The space has 2 neighbor(s):\n"
        + "Lilac Room, Servants' Quarters\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Library;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Letter Opener, Damage: 2\n"
        + "The space has 3 neighbor(s):\n"
        + "Trophy Room, Master Suite, Nursery\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Trophy Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Sharp Knife, Damage: 3\n"
        + "Item name: Loud Noise, Damage: 3\n"
        + "The space has 4 neighbor(s):\n"
        + "Dining Hall, Library, Tennessee Room, Billiard Room\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Wine Cellar;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Silken Cord, Damage: 3\n"
        + "The space has 2 neighbor(s):\n"
        + "Drawing Room, Kitchen\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Winter Garden;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 2 neighbor(s):\n"
        + "Piazza, Carriage House\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Armory;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Tight Hat, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Drawing Room, Green House, Hedge Maze, Billiard Room\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Lilac Room;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Shoe Horn, Damage: 2\n"
        + "The space has 5 neighbor(s):\n"
        + "Lancaster Room, Master Suite, Parlor, Servants' Quarters, Tennessee Room\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Master Suite;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Bad Cream, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Library, Lilac Room, Nursery, Tennessee Room\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Nursery;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 2 neighbor(s):\n"
        + "Library, Master Suite\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Parlor;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Civil War Cannon, Damage: 3\n"
        + "The space has 5 neighbor(s):\n"
        + "Dining Hall, Kitchen, Lilac Room, Servants' Quarters, Tennessee Room\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Piazza;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Broom Stick, Damage: 2\n"
        + "The space has 3 neighbor(s):\n"
        + "Foyer, Hedge Maze, Winter Garden\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Servants' Quarters;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 3 neighbor(s):\n"
        + "Lancaster Room, Lilac Room, Parlor\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Tennessee Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Duck Decoy, Damage: 3\n"
        + "Item name: Monkey Hand, Damage: 2\n"
        + "The space has 5 neighbor(s):\n"
        + "Dining Hall, Trophy Room, Lilac Room, Master Suite, Parlor\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Billiard Room;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Rat Poison, Damage: 2\n"
        + "Item name: Piece of Rope, Damage: 2\n"
        + "The space has 4 neighbor(s):\n"
        + "Dining Hall, Green House, Trophy Room, Armory\n"
        + "----------------------------------------------------------------------------------\n"
        + "Space name: Carriage House;\n"
        + "The space has 0 item(s):\n"
        + "\n"
        + "The space has 1 neighbor(s):\n"
        + "Winter Garden");
  }
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWorldDescription() {
    Readable stringReader = new StringReader(
        "36 0 Leo's World\n"
        + "50 Doctor Unlucky\n"
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
  public void testNoNeighbors() {
    Readable stringReader = new StringReader(
        "36 30 Leo's World\n"
        + "50 Doctor Unlucky\n"
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
    assertArrayEquals(new Space[]{} ,world.getSpaces().get(20).getNeighbors().toArray());
    assertEquals("Space name: Carriage House;\nThe space has 0 item(s):\n\n"
        + "The space has 0 neighbor(s):\n", world.displaySpaceInformation(world.getSpaces().get(20).getName()));
  }

  @Test
  public void testOneNeighbor() {
    assertArrayEquals(new Space[]{world.getSpaces().get(10)} ,world.getSpaces().get(20).getNeighbors().toArray());
    assertEquals("Space name: Carriage House;\nThe space has 0 item(s):\n\n"
        + "The space has 1 neighbor(s):\nWinter Garden", world.displaySpaceInformation(world.getSpaces().get(20).getName()));
  }
  
  @Test
  public void testMutipleNeighbors() {
    assertArrayEquals(new Space[]{world.getSpaces().get(1), world.getSpaces().get(16)},
        world.getSpaces().get(2).getNeighbors().toArray());
    assertEquals(
        "Space name: Foyer;\n"
        + "The space has 2 item(s):\n"
        + "Item name: Big Red Hammer, Damage: 4\n"
        + "Item name: Chain Saw, Damage: 4\n"
        + "The space has 2 neighbor(s):\n"
        + "Drawing Room, Piazza",
        world.displaySpaceInformation(world.getSpaces().get(2).getName()));
  }

  @Test
  public void testOneItem() {
    assertEquals(
        "Space name: Dining Hall;\n"
        + "The space has 1 item(s):\n"
        + "Item name: Revolver, Damage: 3\n"
        + "The space has 6 neighbor(s):\n"
        + "Drawing Room, Kitchen, Trophy Room, Parlor, Tennessee Room, Billiard Room\n"
        + "The target character is in this space now:\n"
        + "Target character: Doctor Unlucky, Health: 50",
        world.displaySpaceInformation(world.getSpaces().get(0).getName()));
  }
  
  @Test
  public void testNoItems() {
    assertEquals("Space name: Carriage House;\nThe space has 0 item(s):\n\n"
        + "The space has 1 neighbor(s):\nWinter Garden", world.displaySpaceInformation(world.getSpaces().get(20).getName()));
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
}
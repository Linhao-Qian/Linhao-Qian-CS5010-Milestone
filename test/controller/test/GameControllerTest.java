package controller.test;

import static org.junit.Assert.assertEquals;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import character.Pet;
import character.Player;
import character.TargetCharacter;
import controller.GameController;
import item.Item;
import item.MyItem;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import space.MySpace;
import space.Space;
import world.World;

/**
 * A JUnit test class for the Game Controller.
 */
public class GameControllerTest {
  
  private StringBuilder log;
  private World model;
  private TargetCharacter targetCharacter;
  private Pet pet;
  private List<Space> spaces;
  private List<Player> players;
  private Space space;
  private Player player;
  
  /**
   * Set up the pre-defined fields of the unit test.
   */
  @Before
  public void setUp() {
    this.targetCharacter = new TargetCharacter("Doctor", 50);
    this.pet = new Pet("cat");
    this.space = new MySpace(0, 0, 3, 3, "Kitchen");
    Item item = new MyItem("Revolver", 3);
    space.addItem(item);
    this.player = new HumanControlledPlayer("Leo", space);
    this.spaces = new ArrayList<>();
    this.players = new ArrayList<>();
    spaces.add(space);
    players.add(player);
    this.log = new StringBuilder();
    this.model = new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, player, false);
  }
  
  @Test
  public void testStart() throws IOException {
    players.remove(player);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testDisplaySpaceInformation() throws IOException {
    players.remove(player);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("displaySpaceInformation\nFoyer\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("display space information: Foyer\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Please enter the space name:\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAddComputerPlayer() throws IOException {
    players.remove(player);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("addComputerPlayer\nLeon\nFoyer\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("add player: Leon\ninitial space name: Foyer\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Please enter the computer-controlled player name:\n"
        + "Please enter the name of the space where the player is initially located:\n"
        + "Successfully added the computer-controlled player Leon\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAddHumanPlayer() throws IOException {
    players.remove(player);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("addHumanPlayer\nLeon\nFoyer\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("add player: Leon\ninitial space name: Foyer\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Please enter the human-controlled player name:\n"
        + "Please enter the name of the space where the player is initially located:\n"
        + "Successfully added the human-controlled player Leon\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testGenerateMap() throws IOException {
    players.remove(player);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("generateMap\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("generate map\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Now, start generating the world map.\n"
        + "The world map has been generated successfully.\nIt is in the mansion.png file.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testDisplayPlayerInformation() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("displayPlayerInformation\nLeo\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("The turn has been reset.\ndisplay player information: Leo\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Please enter the player name:\n"
        + "player information\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testMovePlayer() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\nmovePlayer\nDrawing Room\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\nmove player to: Drawing Room\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "Please enter the name of the space where you want to move:\n"
        + "The player Leo has moved to Drawing Room\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testPickUpItem() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\npickUpItem\nRevolver\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\npick up item: Revolver\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "Please enter the name of the item which you want to carry:\n"
        + "The player Leo has picked up Revolver from Kitchen\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testLookAround() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\nlookAround\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "look around\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testMovePet() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\nmovePet\nFoyer\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\nmove pet to: Foyer\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "Please enter the name of the space where the pet should be moved to:\n"
        + "The pet cat has been moved to Foyer\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testMakeAnAttemptSuccessfully() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\nmakeAnAttempt\nRevolver\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\nmake an attempt with: Revolver\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "Please enter the name of the item which you want to use"
        + " (If you don't have any item, please enter \"pokeEyes\"):\n"
        + "The player Leo has made an attempt on the target character using Revolver\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testMakeAnAttemptFail() throws IOException {
    World newModel =
        new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, player, true);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\nmakeAnAttempt\nRevolver\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\nmake an attempt with: Revolver\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "Please enter the name of the item which you want to use"
        + " (If you don't have any item, please enter \"pokeEyes\"):\n"
        + "The player Leo stopped attacking because it was seen by others.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAutomaticMovePlayer() throws IOException {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    newSpace.addNeighbor(space);
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 2, 0, 0, 0);
    players.add(newPlayer);
    Space neighbor = new MySpace(0, 4, 3, 6, "Foyer");
    space.addNeighbor(neighbor);
    World newModel =
        new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, newPlayer, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Foyer\nmove player to: Kitchen\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leon's turn\n"
        + "space information"
        + "The player Leon has moved to Kitchen\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAutomaticPickUpItem() throws IOException {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    newSpace.addItem(new MyItem("Revolver", 3));
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 2, 0, 0, 0);
    players.add(newPlayer);
    World newModel =
        new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, newPlayer, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Foyer\npick up item: Revolver\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leon's turn\n"
        + "space information"
        + "The player Leon has picked up Revolver from Foyer\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAutomaticLookAround() throws IOException {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 0, 0, 0, 3);
    players.add(newPlayer);
    World newModel =
        new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, newPlayer, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Foyer\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leon's turn\n"
        + "space information"
        + "look around\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAutomaticMovePet() throws IOException {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    newSpace.addItem(new MyItem("Revolver", 3));
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 1, 0, 0, 0);
    players.add(newPlayer);
    World newModel =
        new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, newPlayer, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Foyer\nmove pet to: Kitchen\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leon's turn\n"
        + "space information"
        + "The pet cat has been moved to Kitchen\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testAutomaticMakeAnAttemptWithMostPowerfulItem() throws IOException {
    players.remove(player);
    Player newPlayer = new ComputerControlledPlayer("Leon", space, 2, 0, 0, 0);
    newPlayer.addItem(new MyItem("Knife", 4));
    newPlayer.addItem(new MyItem("Gun", 5));
    players.add(newPlayer);
    World newModel =
        new MockModel(log, "mansion", 40, 40, targetCharacter, pet, spaces, players, newPlayer, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\ncannot be seen by others\n"
        + "make an attempt with: Gun\nnext turn\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leon's turn\n"
        + "space information"
        + "The player Leon has made an attempt on the target character using Gun\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Game over! You have end the input manually!", out.toString());
  }
  
  @Test
  public void testHumanWin() throws IOException {
    TargetCharacter newTargetCharacter = new TargetCharacter("Doctor", 1);
    World newModel =
        new MockModel(log, "mansion", 40, 40, newTargetCharacter, pet, spaces, players, player, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\nmakeAnAttempt\npokeEyes\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\nmake an attempt with: pokeEyes\nnext turn\n",
        log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leo's turn\n"
        + "space information\n"
        + "Please enter one of the following commands:\n"
        + "movePlayer\npickUpItem\nlookAround\nmovePet\nmakeAnAttempt\n"
        + "Please enter the name of the item which you want to use"
        + " (If you don't have any item, please enter \"pokeEyes\"):\n"
        + "The player Leo has made an attempt on the target character using pokeEyes\n"
        + "The target character Doctor has been killed by Leo. Congratulations, Leo!", out.toString());
  }
  
  @Test
  public void testComputertWin() throws IOException {
    players.remove(player);
    Player newPlayer = new ComputerControlledPlayer("Leon", space, 2, 0, 0, 0);
    players.add(newPlayer);
    TargetCharacter newTargetCharacter = new TargetCharacter("Doctor", 1);
    World newModel =
        new MockModel(log, "mansion", 40, 40, newTargetCharacter, pet, spaces, players, newPlayer, false);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("nextTurn\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(newModel);
    assertEquals("The turn has been reset.\ndisplay space information: Kitchen\ncannot be seen by others\n"
        + "make an attempt with: pokeEyes\nnext turn\n", log.toString());
    assertEquals(
        "Now, the game starts.\n\n"
        + "Please enter one of the following commands:\n"
        + "displaySpaceInformation\n"
        + "addComputerPlayer\n"
        + "addHumanPlayer\n"
        + "generateMap\n"
        + "displayPlayerInformation\n"
        + "nextTurn\n"
        + "Now, it is Leon's turn\n"
        + "space information"
        + "The player Leon has made an attempt on the target character using pokeEyes\n"
        + "The target character Doctor has been killed by Leon. Congratulations, Leon!", out.toString());
  }
  
  @Test
  public void testEndAfterMaximumTurn() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    GameController controller = new GameController(in, out, 0);
    controller.start(model);
    assertEquals("The turn has been reset.\n", log.toString());
    assertEquals(
        "Now, the game starts.\n"
        + "Reaching the maximum number of turns, and the target character escapes.\n"
        + "Nobody wins, game over.", out.toString());
  }
}

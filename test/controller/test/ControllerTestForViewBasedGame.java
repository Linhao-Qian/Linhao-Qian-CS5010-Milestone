package controller.test;

import static org.junit.Assert.assertTrue;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import character.Pet;
import character.Player;
import character.TargetCharacter;
import controller.Controller;
import controller.GameController;
import item.Item;
import item.MyItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import space.MySpace;
import space.Space;
import view.View;
import world.World;

/**
 * A JUnit test class for the Game Controller of the view-based game.
 */
public class ControllerTestForViewBasedGame {
  private Controller controller;
  private StringBuilder modelLog;
  private World model;
  private TargetCharacter targetCharacter;
  private Pet pet;
  private List<Space> spaces;
  private List<Player> players;
  private Space space;
  private Player player;
  private StringBuilder viewLog;
  private View view;
  private File file;

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
    this.modelLog = new StringBuilder();
    this.model = new MockModel(modelLog, "mansion", 40, 40, targetCharacter,
        pet, spaces, players, player, false);
    this.viewLog = new StringBuilder();
    this.view = new MockView(viewLog);
    this.file = new File("res/Leo's_world.txt");
    this.controller = new GameController(model, view, file, 4);
  }

  @Test
  public void testEnterGame() {
    controller.getGameActions().get("Enter Game").run();
    assertTrue(viewLog.toString().contains("enterGame called"));
  }
  
  @Test
  public void testNewWorld() {
    controller.getGameActions().get("Start a new game with a new world specification").run();
    assertTrue(viewLog.toString().contains("getNewFile called"));
    assertTrue(viewLog.toString().contains("showGameInterface called with model: Leo's World"));
    assertTrue(viewLog.toString().contains(
        "The target character graphical representation is drawn in the Dining Hall"));
    assertTrue(viewLog.toString().contains("addActionListener called"));
    assertTrue(viewLog.toString().contains("configureSpaceClickListener called"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }

  @Test
  public void testCurrentWorld() {
    controller.getGameActions().get("Start a new game with the current world specification").run();
    assertTrue(viewLog.toString().contains("showGameInterface called with model: Leo's World"));
    assertTrue(viewLog.toString().contains(
        "The target character graphical representation is drawn in the Dining Hall"));
    assertTrue(viewLog.toString().contains("addActionListener called"));
    assertTrue(viewLog.toString().contains("configureSpaceClickListener called"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testAddComputerPlayer() {
    controller.getGameActions().get("Add a new computer-controlled player").run();
    assertTrue(viewLog.toString().contains("getPlayerName called"));
    assertTrue(viewLog.toString().contains("getSpaceName called"));
    assertTrue(viewLog.toString().contains("addPlayer called"));
    assertTrue(viewLog.toString().contains("configurePlayerClickListener called"));
  }
  
  @Test
  public void testAddHumanPlayer() {
    controller.getGameActions().get("Add a new human-controlled player").run();
    assertTrue(viewLog.toString().contains("getPlayerName called"));
    assertTrue(viewLog.toString().contains("getSpaceName called"));
    assertTrue(viewLog.toString().contains("addPlayer called"));
    assertTrue(viewLog.toString().contains("configurePlayerClickListener called"));
  }
  
  @Test
  public void testStartGame() {
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("The turn has been reset."));
    assertTrue(viewLog.toString().contains("startGame called"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testMovePlayerValidSpace() {
    controller.movePlayer(1, 1);
    assertTrue(modelLog.toString().contains("get space according to coordinates: 1 1"));
    assertTrue(modelLog.toString().contains("move player to: Kitchen"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains(
        "setResult called with result: The player Leo has moved to Kitchen"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testMovePlayerInvalidSpace() {
    controller.movePlayer(0, 0);
    assertTrue(modelLog.toString().contains("get space according to coordinates: 0 0"));
    assertTrue(viewLog.toString().contains("Please choose a valid space!"));
  }
  
  @Test
  public void testPickUpItem() {
    controller.getKeyTypes().get('i').run();
    assertTrue(viewLog.toString().contains("getItemName called"));
    assertTrue(modelLog.toString().contains("pick up item: TestItem"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains(
        "setResult called with result: The player Leo has picked up TestItem from Kitchen"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testLookAround() {
    controller.getKeyTypes().get('l').run();
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: look around"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testMakeAnAttemptSuccessfully() {
    controller.getKeyTypes().get('a').run();
    assertTrue(viewLog.toString().contains("getAttemptChoice called"));
    assertTrue(modelLog.toString().contains("make an attempt with: TestWeapon"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: "
        + "The player Leo has made an attempt on the target character using TestWeapon"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testMakeAnFailedAttempt() {
    World newModel = new MockModel(modelLog, "mansion", 40, 40, targetCharacter,
        pet, spaces, players, player, true);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getKeyTypes().get('a').run();
    assertTrue(viewLog.toString().contains("getAttemptChoice called"));
    assertTrue(modelLog.toString().contains("make an attempt with: TestWeapon"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: "
        + "The player Leo stopped attacking because it was seen by others."));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testMovePet() {
    controller.getKeyTypes().get('m').run();
    assertTrue(viewLog.toString().contains("getIntendedSpace called"));
    assertTrue(modelLog.toString().contains("move pet to: TargetSpace"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains(
        "setResult called with result: The pet cat has been moved to TargetSpace"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testAutomaticMovePlayer() {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    newSpace.addNeighbor(space);
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 2, 0, 0, 0);
    players.add(newPlayer);
    Space neighbor = new MySpace(0, 4, 3, 6, "Foyer");
    space.addNeighbor(neighbor);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, targetCharacter,
        pet, spaces, players, newPlayer, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("move player to: Kitchen"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains(
        "setResult called with result: The player Leon has moved to Kitchen"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testAutomaticPickUpItem() {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    newSpace.addItem(new MyItem("Revolver", 3));
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 2, 0, 0, 0);
    players.add(newPlayer);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, targetCharacter, pet,
        spaces, players, newPlayer, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("pick up item: Revolver"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains(
        "setResult called with result: The player Leon has picked up Revolver from Foyer"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testAutomaticLookAround() {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 0, 0, 0, 3);
    players.add(newPlayer);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, targetCharacter, pet,
        spaces, players, newPlayer, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: look around"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testAutomaticMovePet() {
    players.remove(player);
    Space newSpace = new MySpace(0, 4, 3, 6, "Foyer");
    newSpace.addItem(new MyItem("Revolver", 3));
    Player newPlayer = new ComputerControlledPlayer("Leon", newSpace, 1, 0, 0, 0);
    players.add(newPlayer);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, targetCharacter, pet,
        spaces, players, newPlayer, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("move pet to: Kitchen"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains(
        "setResult called with result: The pet cat has been moved to Kitchen"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testAutomaticMakeAnAttemptWithMostPowerfulItem() {
    players.remove(player);
    Player newPlayer = new ComputerControlledPlayer("Leon", space, 2, 0, 0, 0);
    newPlayer.addItem(new MyItem("Knife", 4));
    newPlayer.addItem(new MyItem("Gun", 5));
    players.add(newPlayer);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, targetCharacter, pet,
        spaces, players, newPlayer, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("make an attempt with: Gun"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: "
        + "The player Leon has made an attempt on the target character using Gun"));
    assertTrue(viewLog.toString().contains("resetFocus called"));
  }
  
  @Test
  public void testHumanWin() {
    TargetCharacter newTargetCharacter = new TargetCharacter("Doctor", 1);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, newTargetCharacter, pet,
        spaces, players, player, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getKeyTypes().get('a').run();
    assertTrue(viewLog.toString().contains("getAttemptChoice called"));
    assertTrue(modelLog.toString().contains("make an attempt with: TestWeapon"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: "
        + "The player Leo has made an attempt on the target character using TestWeapon"));
    assertTrue(viewLog.toString().contains("endGame called with result: "
        + "The target character Doctor has been killed by Leo. Congratulations, Leo!"));
  }
  
  @Test
  public void testComputertWin() {
    players.remove(player);
    Player newPlayer = new ComputerControlledPlayer("Leon", space, 2, 0, 0, 0);
    players.add(newPlayer);
    TargetCharacter newTargetCharacter = new TargetCharacter("Doctor", 1);
    World newModel = new MockModel(modelLog, "mansion", 40, 40, newTargetCharacter, pet,
        spaces, players, newPlayer, false);
    this.controller = new GameController(newModel, view, file, 4);
    controller.getGameActions().get("Start the game").run();
    assertTrue(modelLog.toString().contains("make an attempt with: pokeEyes"));
    assertTrue(modelLog.toString().contains("next turn"));
    assertTrue(viewLog.toString().contains("setResult called with result: "
        + "The player Leon has made an attempt on the target character using pokeEyes"));
    assertTrue(viewLog.toString().contains("endGame called with result: "
        + "The target character Doctor has been killed by Leon. Congratulations, Leon!"));
  }
  
  @Test
  public void testEndAfterMaximumTurn() {
    this.controller = new GameController(model, view, file, 0);
    controller.getGameActions().get("Start the game").run();
    System.out.println(modelLog);
    System.out.println(viewLog);
    assertTrue(viewLog.toString().contains("endGame called with result: Reaching the maximum"
        + " number of turns, and the target character escapes.\nNobody wins, game over."));
  }
}

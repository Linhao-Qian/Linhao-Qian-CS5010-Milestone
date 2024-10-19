package controller.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import character.Player;
import character.HumanControlledPlayer;
import character.TargetCharacter;
import controller.GameController;
import space.MySpace;
import space.Space;
import world.World;

public class GameControllerTest {
  
  private StringBuilder log;
  private World model;
  private TargetCharacter targetCharacter;
  private List<Space> spaces;
  private List<Player> players;
  private Space space;
  private Player player;
  
  @Before
  public void setUp() {
    this.targetCharacter = new TargetCharacter("Doctor", 50);
    this.space = new MySpace(0, 0, 3, 3, "Kitchen");
    this.player = new HumanControlledPlayer("Leo", space);
    this.spaces = new ArrayList<>();
    this.players = new ArrayList<>();
    spaces.add(space);
    players.add(player);
    this.log = new StringBuilder();
    this.model = new MockModel(log, "mansion", 40, 40, targetCharacter, spaces, players, player);
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
    assertEquals("Input space name: Foyer\n", log.toString());
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
    assertEquals("Input player name: Leon\nInput player space name: Foyer\n", log.toString());
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
    assertEquals("Input player name: Leon\nInput player space name: Foyer\n", log.toString());
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
    Reader in = new StringReader("displaySpaceInformation\nFoyer\n");
    GameController controller = new GameController(in, out, 3);
    controller.start(model);
    assertEquals("Input space name: Foyer\n", log.toString());
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
}

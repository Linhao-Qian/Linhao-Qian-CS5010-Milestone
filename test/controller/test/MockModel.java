package controller.test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import character.Player;
import character.TargetCharacter;
import space.Space;
import world.World;

public class MockModel implements World {
  private StringBuilder log;
  private final String name;
  private final int rows;
  private final int cols;
  private final TargetCharacter targetCharacter;
  private final List<Space> spaces;
  private final List<Player> players;
  private Player turn;
  private int turnCount = 0;
  
  public MockModel(StringBuilder log, String name, int rows, int cols, TargetCharacter targetCharacter,
      List<Space> spaces, List<Player> players, Player turn) {
    this.log = log;
    this.name = name;
    this.rows = rows;
    this.cols = cols;
    this.targetCharacter = targetCharacter;
    this.spaces = spaces;
    this.players = players;
    this.turn = turn;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int[] getSize() {
    return new int[]{rows, cols};
  }

  @Override
  public TargetCharacter getTargetCharacter() {
    return targetCharacter;
  }

  @Override
  public List<Space> getSpaces() {
    return new ArrayList<>(spaces);
  }

  @Override
  public Space getSpace(String spaceName) {
    log.append("Input space name: ").append(spaceName).append("\n");
    return spaces.get(0);
  }

  @Override
  public List<Player> getPlayers() {
    return new ArrayList<>(players);
  }

  @Override
  public Player getPlayer(String playerName) {
    log.append("Input player name: ").append(playerName).append("\n");
    return players.get(0);
  }

  @Override
  public String displaySpaceInformation(String spaceName) {
    log.append("Input space name: ").append(spaceName).append("\n");
    return "space information";
  }

  @Override
  public String displayPlayerInformation(String playerName) {
    log.append("Input player name: ").append(playerName).append("\n");
    return "player information";
  }

  @Override
  public int getTargetCharacterPosition() {
    return 0;
  }

  @Override
  public List<Space> getNeighbors(Space space) {
    log.append("Input space information: ").append(space.toString()).append("\n");
    return new ArrayList<>(spaces);
  }

  @Override
  public void addComputerPlayer(String name, String spaceName) {
    log.append("Input player name: ").append(name).append("\n");
    log.append("Input player space name: ").append(spaceName).append("\n");
  }
  
  @Override
  public void addHumanPlayer(String name, String spaceName) {
    log.append("Input player name: ").append(name).append("\n");
    log.append("Input player space name: ").append(spaceName).append("\n");
  }
  
  @Override
  public void moveTargetCharacter() {
    log.append("Target character has moved to next space.\n");
  }

  @Override
  public void resetTurn() {
    log.append("The turn has been reset.\n");
  }

  @Override
  public Player getTurn() {
    return turn;
  }

  @Override
  public void nextTurn() {
    log.append("next turn\n");
    this.turnCount++;
  }

  @Override
  public int getTurnCount() {
    return this.turnCount;
  }

  @Override
  public void movePlayer(String spaceName) {
    log.append("Input space name: ").append(spaceName).append("\n");
  }

  @Override
  public void pickUpItem(String itemName) {
    log.append("Input item name: ").append(itemName).append("\n");
  }

  @Override
  public String lookAround() {
    return "look around\n";
  }

  @Override
  public BufferedImage generateMap() {
    log.append("generate map\n");
    return new BufferedImage(40, 40, BufferedImage.TYPE_BYTE_BINARY);
  }

  @Override
  public String toString() {
    return "";
  }
}

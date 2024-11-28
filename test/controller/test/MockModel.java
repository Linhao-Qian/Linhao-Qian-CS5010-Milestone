package controller.test;

import character.Pet;
import character.Player;
import character.TargetCharacter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import space.Space;
import world.World;

/**
 * Mock implementation of the model for testing.
 */
public class MockModel implements World {
  private StringBuilder log;
  private final String name;
  private final int rows;
  private final int cols;
  private final TargetCharacter targetCharacter;
  private final Pet pet;
  private final List<Space> spaces;
  private final List<Player> players;
  private Player turn;
  private int turnCount = 0;
  private boolean canBeSeenByOthers;
  
  /**
   * Constructs a MockModel object.
   * 
   * @param log               the logger for the input values
   * @param name              the name of the world
   * @param rows              the rows of the world
   * @param cols              the cols of the world
   * @param targetCharacter   the target character of the world
   * @param pet               the pet of the world
   * @param spaces            the spaces of the world
   * @param players           the players of the world
   * @param turn              the current player
   * @param canBeSeenByOthers can current player be seen by others
   */
  public MockModel(StringBuilder log, String name, int rows, int cols,
      TargetCharacter targetCharacter, Pet pet, List<Space> spaces,
      List<Player> players, Player turn, boolean canBeSeenByOthers) {
    this.log = log;
    this.name = name;
    this.rows = rows;
    this.cols = cols;
    this.targetCharacter = targetCharacter;
    this.pet = pet;
    this.spaces = spaces;
    this.players = players;
    this.turn = turn;
    this.canBeSeenByOthers = canBeSeenByOthers;
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
  public Pet getPet() {
    return pet;
  }
  
  @Override
  public List<Space> getSpaces() {
    return new ArrayList<>(spaces);
  }

  @Override
  public Space getSpace(String spaceName) {
    log.append("get space: ").append(spaceName).append("\n");
    return spaces.get(0);
  }

  @Override
  public Space getSpace(int x, int y) {
    log.append("get space according to coordinates: " + x + " " + y).append("\n");
    if (x == 0 && y == 0) {
      return null;
    }
    return spaces.get(0);
  }
  
  @Override
  public List<Player> getPlayers() {
    return new ArrayList<>(players);
  }

  @Override
  public Player getPlayer(String playerName) {
    log.append("get player: ").append(playerName).append("\n");
    return players.get(0);
  }

  @Override
  public String displaySpaceInformation(String spaceName) {
    log.append("display space information: ").append(spaceName).append("\n");
    return "space information";
  }

  @Override
  public String displayPlayerInformation(String playerName) {
    log.append("display player information: ").append(playerName).append("\n");
    return "player information";
  }

  @Override
  public int getTargetCharacterPosition() {
    return 0;
  }

  @Override
  public List<Space> getNeighbors(Space space) {
    log.append("get neighbors of the space: ").append(space.toString()).append("\n");
    return new ArrayList<>(spaces);
  }

  @Override
  public void addComputerPlayer(String name, String spaceName) {
    log.append("add player: ").append(name).append("\n");
    log.append("initial space name: ").append(spaceName).append("\n");
  }
  
  @Override
  public void addHumanPlayer(String name, String spaceName) {
    log.append("add player: ").append(name).append("\n");
    log.append("initial space name: ").append(spaceName).append("\n");
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
    log.append("move player to: ").append(spaceName).append("\n");
  }

  @Override
  public void pickUpItem(String itemName) {
    log.append("pick up item: ").append(itemName).append("\n");
  }

  @Override
  public String lookAround() {
    return "look around\n";
  }
  
  @Override
  public void movePet(String spaceName) {
    log.append("move pet to: ").append(spaceName).append("\n");
  }

  @Override
  public boolean canBeSeenByOthers() {
    log.append("cannot be seen by others").append("\n");
    return canBeSeenByOthers;
  }

  @Override
  public boolean makeAnAttempt(String itemName) {
    log.append("make an attempt with: ").append(itemName).append("\n");
    if (!canBeSeenByOthers) {
      targetCharacter.reduceHealth(1);
    }
    return !canBeSeenByOthers;
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

package world;

import character.ComputerControlledPlayer;
import character.HumanControlledPlayer;
import character.Pet;
import character.Player;
import character.TargetCharacter;
import item.Item;
import item.MyItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import space.MySpace;
import space.Space;

/**
 * A MyWorld object represents the model of the milestone board game.
 */
public class MyWorld implements World {
  private final String name;
  private final int rows;
  private final int cols;
  private final TargetCharacter targetCharacter;
  private final Pet pet;
  private final List<Space> spaces;
  private final List<Player> players;
  private int targetCharacterPosition;
  private Player turn;
  private int turnCount;
  private List<Space> orderedSpaces;
  
  private final String separator =
      "\n----------------------------------------------------------------------------------\n";

  /**
   * Constructs a MyWorld model using the input file reader.
   * 
   * @param reader   the input reader
   * @throws IllegalArgumentException   if the name is null or empty, the spaces or items size
   *                                    is less than 20, the world size is not positive, the item
   *                                    position is invalid, the space boundary is out of the
   *                                    world, the spaces overlap, the spaces or items have the
   *                                    same name 
   */
  public MyWorld(Readable reader) throws IllegalArgumentException {
    Scanner scan = new Scanner(reader);
    
    // Read the world information.
    String[] worldInfo = scan.nextLine().split(" ", 3);
    this.rows = Integer.parseInt(worldInfo[0]);
    this.cols = Integer.parseInt(worldInfo[1]);
    if (rows <= 0 || cols <= 0) {
      scan.close();
      throw new IllegalArgumentException("Invalid world size");
    }
    this.name = worldInfo[2];
    if (name == null || name.isEmpty()) {
      scan.close();
      throw new IllegalArgumentException("The name of the world can't be empty!");
    }
    
    // Read the target character information.
    String[] targetCharacterInfo = scan.nextLine().split(" ", 2);
    int health = Integer.parseInt(targetCharacterInfo[0]);
    String targetCharacterName = targetCharacterInfo[1];
    this.targetCharacter = new TargetCharacter(targetCharacterName, health);
    
    // Read the pet's name.
    String petName = scan.nextLine();
    this.pet = new Pet(petName);
    
    // Read the spaces information.
    int spaceNum = Integer.parseInt(scan.nextLine());
    if (spaceNum < 20) {
      scan.close();
      throw new IllegalArgumentException("A world should consist of at least 20 spaces.");
    }
    this.spaces = new ArrayList<>();
    for (int i = 0; i < spaceNum; i++) {
      String[] spaceInfo = scan.nextLine().trim().split("\\s+", 5);
      int row1 = Integer.parseInt(spaceInfo[0]);
      int col1 = Integer.parseInt(spaceInfo[1]);
      int row2 = Integer.parseInt(spaceInfo[2]);
      int col2 = Integer.parseInt(spaceInfo[3]);
      String spaceName = spaceInfo[4];
      spaces.add(new MySpace(row1, col1, row2, col2, spaceName));
    }
    for (int i = 0; i < spaceNum; i++) {
      Space space1 = spaces.get(i);
      // get and store the neighbor information of each space
      this.getNeighbors(space1).forEach(space1::addNeighbor);
      // check if the space's row2 or col2 is beyond the world boundary
      if (space1.getPosition()[2] >= rows || space1.getPosition()[3] >= cols) {
        scan.close();
        throw new IllegalArgumentException("Space boundary is out of the world");
      }
      // check if any two spaces overlap or have the same name
      for (int j = i + 1; j < spaceNum; j++) {
        Space space2 = spaces.get(j);
        if (space1.getName().equals(space2.getName())) {
          scan.close();
          throw new IllegalArgumentException(
              String.format("Two spaces have the same name: %s", space1.getName()));
        }
        if (areSpacesOverlapping(space1, space2)) {
          scan.close();
          throw new IllegalArgumentException(
              String.format("Spaces overlap: %s and %s", space1.getName(), space2.getName()));
        }
      }
    }
    
    // Read the items information.
    int itemNum = Integer.parseInt(scan.nextLine());
    List<String> itemNames = new ArrayList<>();
    if (itemNum < 20) {
      scan.close();
      throw new IllegalArgumentException("A world should consist of at least 20 items.");
    }
    for (int i = 0; i < itemNum; i++) {
      String[] itemInfo = scan.nextLine().split("\\s+", 3);
      int position = Integer.parseInt(itemInfo[0]);
      if (position >= spaceNum || position < 0) {
        scan.close();
        throw new IllegalArgumentException("Invalid item position");
      }
      int damage = Integer.parseInt(itemInfo[1]);
      String itemName = itemInfo[2];
      Item item = new MyItem(itemName, damage);
      spaces.get(position).addItem(item);
      itemNames.add(itemName);
    }
    // check if any two items have the same name
    List<String> newItemNames = itemNames.stream().distinct().collect(Collectors.toList());
    if (itemNames.size() != newItemNames.size()) {
      scan.close();
      throw new IllegalArgumentException("There are two items with the same name.");
    }
    
    // Close the scanner.
    scan.close();

    // Generate the pet wandering order list.
    this.orderedSpaces = new ArrayList<>();
    this.spacesDepthFirstTraversal(this.spaces.get(0), orderedSpaces);
    
    this.players = new ArrayList<>();
    this.targetCharacterPosition = 0;
    this.turnCount = 0;
    this.pet.setSpace(this.spaces.get(0));
  }

  private boolean areSpacesOverlapping(Space space1, Space space2) {
    int[] pos1 = space1.getPosition();
    int[] pos2 = space2.getPosition();
    boolean isNoOverlapping = 
        pos1[2] < pos2[0]     // check if space1's row2 is less than space2's row1
        || pos1[0] > pos2[2]  // check if space1's row1 is greater than space2's row1
        || pos1[3] < pos2[1]  // check if space1's col2 is less than space2's col1
        || pos1[1] > pos2[3]; // check if space1's col1 is greater than space2's col1
    return !isNoOverlapping;
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
    return spaces.stream().filter(space -> space.getName().equals(spaceName)).findAny()
        .orElseThrow(() -> new IllegalArgumentException(
            String.format("The space %s does not exist\n", spaceName)));
  }
  
  @Override
  public List<Player> getPlayers() {
    return new ArrayList<>(players);
  }
  
  @Override
  public Player getPlayer(String playerName) {
    return players.stream().filter(player -> player.getName().equals(playerName)).findAny()
        .orElseThrow(() -> new IllegalArgumentException(
            String.format("The player %s does not exist\n", playerName)));
  }
  
  @Override
  public int getTargetCharacterPosition() {
    return targetCharacterPosition;
  }

  @Override
  public List<Space> getNeighbors(Space space) {
    List<Space> neighbors = new ArrayList<>();
    int[] curPos = space.getPosition();
    for (Space s : spaces) {
      if (s != space) {
        int[] pos = s.getPosition();
        // If two spaces's rows are overlapped and columns are adjacent, they are neighbors.
        boolean isRowOverlap = pos[0] <= curPos[2] && pos[2] >= curPos[0];
        boolean isColAdjacent = Math.abs(pos[1] - curPos[3]) == 1
            || Math.abs(pos[3] - curPos[1]) == 1;
        // If two spaces's columns are overlapped and rows are adjacent, they are neighbors.
        boolean isColOverlap = pos[1] <= curPos[3] && pos[3] >= curPos[1];
        boolean isRowAdjacent = Math.abs(pos[0] - curPos[2]) == 1
            || Math.abs(pos[2] - curPos[0]) == 1;
        // When one of the above two conditions is met, two spaces are neighbors.
        if ((isRowOverlap && isColAdjacent) || (isColOverlap && isRowAdjacent)) {
          neighbors.add(s);
        }
      }
    }
    return neighbors;
  }

  @Override
  public String displaySpaceInformation(String spaceName) {
    Space space = getSpace(spaceName);
    StringBuilder sb = new StringBuilder(space.toString());
    if (space.equals(pet.getSpace())) {
      sb.append("\nThe pet is in this space now:\n");
      sb.append(pet.toString());
    }
    if (targetCharacterPosition == spaces.indexOf(space)) {
      sb.append("\nThe target character is in this space now:\n");
      sb.append(targetCharacter.toString());
    }
    List<Player> spacePlayers = players.stream()
        .filter(player -> player.getSpace().equals(space)).collect(Collectors.toList());
    sb.append(String.format("\nThere are %d player(s) in this space:\n", spacePlayers.size()));
    spacePlayers.forEach(player -> sb.append(player.getName()));
    sb.append(separator);
    return sb.toString();
  }
  
  @Override
  public String displayPlayerInformation(String playerName) {
    Player player = getPlayer(playerName);
    StringBuilder sb = new StringBuilder(player.toString());
    sb.append(String.format("\nThe player is currently in: %s%s",
        player.getSpace().getName(), separator));
    return sb.toString();
  }
  
  @Override
  public void addComputerPlayer(String name, String spaceName) {
    players.forEach(p -> {
      if (p.getName().equals(name)) {
        throw new IllegalArgumentException("Cannot have two players with the same name!\n");
      }
    });
    Space space = getSpace(spaceName);
    Player player = new ComputerControlledPlayer(name, space);
    players.add(player);
  }
  
  @Override
  public void addHumanPlayer(String name, String spaceName) {
    players.forEach(p -> {
      if (p.getName().equals(name)) {
        throw new IllegalArgumentException("Cannot have two players with the same name!\n");
      }
    });
    Space space = getSpace(spaceName);
    Player player = new HumanControlledPlayer(name, space);
    players.add(player);
  }
  
  @Override
  public void moveTargetCharacter() {
    if (targetCharacterPosition < spaces.size() - 1) {
      targetCharacterPosition++;
    } else {
      targetCharacterPosition = 0;
    }
  }

  @Override
  public void resetTurn() {
    this.turn = players.get(0);
  }
  
  @Override
  public Player getTurn() {
    return this.turn;
  }
  
  @Override
  public void nextTurn() {
    int index = players.indexOf(turn);
    if (index < players.size() - 1) {
      turn = players.get(index + 1);
    } else {
      turn = players.get(0);
    }
    moveTargetCharacter();
    this.turnCount++;
    // Make the pet wander.
    int petPosition = orderedSpaces.indexOf(pet.getSpace());
    if (petPosition < orderedSpaces.size() - 1) {
      pet.setSpace(orderedSpaces.get(petPosition + 1));
    } else {
      pet.setSpace(orderedSpaces.get(0));
    }
  }
  
  @Override
  public int getTurnCount() {
    return this.turnCount;
  }
  
  @Override
  public void movePlayer(String spaceName) {
    Space space = getSpace(spaceName);
    List<Space> neighbors = turn.getSpace().getNeighbors();
    if (!neighbors.contains(space)) {
      throw new IllegalArgumentException("Cannot move to this space!\n");
    }
    turn.setSpace(space);
  }
  
  @Override
  public void pickUpItem(String itemName) {
    Space space = turn.getSpace(); 
    Item item = space.getItem(itemName);
    turn.addItem(item);
    space.removeItem(item);
  }
  
  @Override
  public String lookAround() {
    Space space = turn.getSpace();
    String name = turn.getName();
    StringBuilder sb = new StringBuilder(String.format("%s is looking around:\n", name));
    sb.append(String.format("%s is currently in:\n%s\n", name, displaySpaceInformation(space.getName())));
    sb.append(String.format("The neighbor(s) information is as follows:\n%s\n", space.getNeighbors().size(),
        space.getNeighbors().stream().map(neighbor -> getLookAroundNeighborInformation(neighbor))
        .collect(Collectors.joining(separator))));
    return sb.toString();
  }
  
  private String getLookAroundNeighborInformation(Space space) {
    if (space.equals(pet.getSpace())) {
      return String.format("The pet %s is in this space now, you can't get the space's information!\n",
          pet.getName());
    }
    List<Player> spacePlayers = players.stream()
        .filter(player -> player.getSpace().equals(space)).collect(Collectors.toList());
    StringBuilder sb = new StringBuilder(String.format(
        "Space name: %s;\nThe space has %d item(s):\n%s\nThe space has %d player(s):\n%s",
        space.getName(),
        space.getItems().size(),
        space.getItems().stream().map(item -> item.toString()).collect(Collectors.joining("\n")),
        spacePlayers.size(),
        spacePlayers.stream().map(player -> player.getName()).collect(Collectors.joining(", "))));
    if (targetCharacterPosition == spaces.indexOf(space)) {
      sb.append("\nThe target character is in this space now:\n");
      sb.append(targetCharacter.toString());
    }
    return sb.toString();
  }
  
  private void spacesDepthFirstTraversal(Space space, List<Space> spaces) {
    if (spaces.size() == getSpaces().size()) {
      return;
    }
    if (!spaces.contains(space)) {
      spaces.add(space);
    } else {
      return;
    }
    for (Space neighbor: space.getNeighbors()) {
      spacesDepthFirstTraversal(neighbor, spaces);
    }
  }
  
  @Override
  public void movePet(String spaceName) {
    Space space = getSpace(spaceName);
    pet.setSpace(space);
    // Re-generate the pet wandering order list. 
    this.orderedSpaces = new ArrayList<>();
    spacesDepthFirstTraversal(space, orderedSpaces);
  }
  
  @Override
  public boolean canBeSeenByOthers() {
    for (Player player : players) {
      if (!turn.equals(player) && turn.canBeSeenBy(player)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean makeAnAttempt(String itemName) {
    if (targetCharacterPosition != spaces.indexOf(turn.getSpace())) {
      throw new UnsupportedOperationException("You are not in the same space as the target character!");
    }
    if (canBeSeenByOthers()) {
      return false;
    }
    if (turn.getItems().size() == 0 && "pokeEyes".equals(itemName)) {
      targetCharacter.reduceHealth(1);
    } else {
      Item item = turn.getItem(itemName);
      targetCharacter.reduceHealth(item.getDamage());
      turn.getItems().remove(item);
    }
    return true;
  }
  
  @Override
  public BufferedImage generateMap() {
    // Set the width and height of the image to 10 times the world's width and height.
    // If the image is too small, strings cannot be added. In addition, add an extra 20 units
    // to the width and height, leaving 10 units of blank space around each side of the image. 
    int width = cols * 20 + 40;
    int height = rows * 20 + 40;
    
    // Use TYPE_BYTE_BINARY instead of TYPE_INT_RGB,
    // because the world map is black and white, not colored.
    BufferedImage map = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
    Graphics graphics = map.getGraphics();
    
    // Fill the background with white color.
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);
    
    // Draw the space boundaries with black color.
    graphics.setColor(Color.BLACK);
    
    // Iterate and draw each space boundary.
    for (Space space : spaces) {
      int[] position = space.getPosition();
      
      // Get the coordinates of the upper left corner. 
      int x1 = position[1] * 20 + 20;
      int y1 = position[0] * 20 + 20;
      
      // Get the coordinates of the lower right corner.
      int x2 = (position[3] + 1) * 20 + 20;
      int y2 = (position[2] + 1) * 20 + 20;
      
      // Draw the four lines of the space.
      graphics.drawLine(x1, y1, x1, y2); // left line
      graphics.drawLine(x2, y1, x2, y2); // right line
      graphics.drawLine(x1, y1, x2, y1); // top line
      graphics.drawLine(x1, y2, x2, y2); // bottom line
      
      // Draw the space name string.
      graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
      String[] nameList = space.getName().split(" ");
      for (int i = 0; i < nameList.length; i++) {
        graphics.drawString(nameList[i], x1 + 8, y1 + 16 * i + 20);
      }
    }
    
    // After drawing, dispose the graphic's context to release the system's resources.
    graphics.dispose();
    
    return map;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MyWorld)) {
      return false;
    }
    World that = (MyWorld) o;
    return Arrays.equals(this.getSize(), that.getSize()) && this.name.equals(that.getName())
        && this.targetCharacter.equals(that.getTargetCharacter())
        && this.spaces.equals(that.getSpaces());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, rows, cols, targetCharacter, spaces);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(String.format("The world's name is: %s\n", name));
    sb.append(String.format("The world's size is: %d x %d%s", rows, cols, separator));
    sb.append("The spaces and items information is as follows:");
    sb.append(separator);
    for (int i = 0; i < spaces.size(); i++) {
      sb.append(displaySpaceInformation(spaces.get(i).getName()));
    }
    return sb.toString();
  }
}
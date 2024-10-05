package world;

import character.TargetCharacter;
import item.Item;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import space.MySpace;
import space.Space;

/**
 * A MyWorld object represents a world of the milestone board game.
 * It consists of a number of non-overlapping {@link MySpace} that are laid out on a 2D grid.
 */
public class MyWorld implements World {
  private final String name;
  private final int rows;
  private final int cols;
  private final TargetCharacter targetCharacter;
  private final List<Space> spaces;
  private final List<Item> items;
  private int targetCharacterPosition;

  /**
   * Constructs a MyWorld object, which has a name, a number of rows, a number of columns,
   * a target character, a list of spaces and a list of items.
   * 
   * @param name              the name of the world
   * @param rows              number of the rows
   * @param cols              number of the columns
   * @param targetCharacter   the target character
   * @param spaces            the list of the spaces in the world
   * @param items             the list of the items in the world
   * @throws IllegalArgumentException   if the name is null or empty, the spaces or items size
   *                                    is less than 20, the world size is not positive, the item
   *                                    position is invalid, the space boundary is out of the
   *                                    world, or the spaces overlap
   */
  public MyWorld(String name, int rows, int cols, TargetCharacter targetCharacter,
      List<Space> spaces, List<Item> items) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the world can't be empty!");
    }
    if (spaces.size() < 20) {
      throw new IllegalArgumentException("A world should consist of at least 20 spaces.");
    }
    if (items.size() < 20) {
      throw new IllegalArgumentException("A world should consist of at least 20 items.");
    }
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Invalid world size");
    }
    for (Item item : items) {
      if (item.getPosition() >= spaces.size()) {
        throw new IllegalArgumentException("Invalid item position");
      }
    }
    for (int i = 0; i < spaces.size(); i++) {
      Space space1 = spaces.get(i);
      // check if the space's row2 or col2 is beyond the world boundary
      if (space1.getPosition()[2] >= rows || space1.getPosition()[3] >= cols) {
        throw new IllegalArgumentException("Space boundary is out of the world");
      }
      // check if any two spaces overlap
      for (int j = i + 1; j < spaces.size(); j++) {
        Space space2 = spaces.get(j);
        if (areSpacesOverlapping(space1, space2)) {
          throw new IllegalArgumentException(
              "Spaces overlap: " + space1.getName() + " and " + space2.getName());
        }
      }
    }
    this.name = name;
    this.rows = rows;
    this.cols = cols;
    this.targetCharacter = targetCharacter;
    this.spaces = spaces;
    this.items = items;
    this.targetCharacterPosition = 0;
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
  public List<Space> getSpaces() {
    return new ArrayList<>(spaces);
  }

  @Override
  public List<Item> getItems() {
    return new ArrayList<>(items);
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
  public void moveTargetCharacter() {
    if (targetCharacterPosition < spaces.size() - 1) {
      targetCharacterPosition++;
    } else {
      targetCharacterPosition = 0;
    }
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
    MyWorld that = (MyWorld) o;
    return this.rows == that.rows && this.cols == that.cols
        && this.targetCharacterPosition == that.targetCharacterPosition
        && this.name.equals(that.name) && this.targetCharacter.equals(that.targetCharacter)
        && this.spaces.equals(that.spaces) && this.items.equals(that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, rows, cols, targetCharacter, spaces, items, targetCharacterPosition);
  }
  
  @Override
  public String toString() {
    return String.format("World name: %s, Size: %d x %d", name, rows, cols);
  }
}
package world;

import character.Character;
import java.awt.image.BufferedImage;
import java.util.List;
import space.Space;

/**
 * A World consists of a number of non-overlapping {@link Space} that are laid out on a 2D grid.
 */
public interface World {

  /**
   * Return the name of the world.
   *
   * @return the name of the world
   */
  String getName();
  
  /**
   * Return the size of the world. The size contains the number of rows and the number of columns.
   *
   * @return the size of the world
   */
  int[] getSize();
  
  /**
   * Return the target character of the world. Each world has a target character.
   *
   * @return the target character of the world
   */
  Character getTargetCharacter();
  
  /**
   * Return the spaces in the world. Each world has a number of non-overlapping spaces.
   *
   * @return the spaces in the world
   */
  List<Space> getSpaces();
  
  /**
   * Display the information of a specified space.
   *
   * @param space   the space of which information needs to be displayed
   * @return the space's information
   */
  String displaySpaceInformation(Space space);
  
  /**
   * Return the position of the target character.
   * The target character starts in space 0 and moves from space to space in order
   * using the ordered, 0-indexed list of spaces found in the world specification
   *
   * @return the position of the target character
   */
  int getTargetCharacterPosition();
  
  /**
   * Return the neighbors of a space. Spaces that share a "wall" are neighbors.
   *
   * @param space   the space that needs to find the neighbors
   * @return the neighbors of a space
   */
  List<Space> getNeighbors(Space space);
  
  /**
   * Move the target character around the world.
   * The target character starts in space 0 and moves from space to space in order
   * using the ordered, 0-indexed list of spaces found in the world specification.
   */
  void moveTargetCharacter();
  
  /**
   * Create a graphical representation of the world map in the form of a BufferedImage.
   *
   * @return the graphical representation of the world
   */
  BufferedImage generateMap();
}

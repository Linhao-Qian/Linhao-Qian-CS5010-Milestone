package world;

import character.Pet;
import character.Player;
import character.TargetCharacter;
import java.awt.image.BufferedImage;
import java.util.List;
import space.Space;

/**
 * The read-only world model.
 */
public interface ReadonlyWorld {
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
  TargetCharacter getTargetCharacter();
  
  /**
   * Return the pet of the target character. Each world has a pet.
   *
   * @return the pet of the target character
   */
  Pet getPet();
  
  /**
   * Return the spaces in the world. Each world has a number of non-overlapping spaces.
   *
   * @return the spaces in the world
   */
  List<Space> getSpaces();
  
  /**
   * Return the space in the world according to the given space name.
   *
   * @param spaceName the name of the space which needs to be found
   * @return the space which needs to be found
   */
  Space getSpace(String spaceName);
  
  /**
   * Return the space in the world according to the given coordinates.
   *
   * @param x the x-coordinate of the space
   * @param y the y-coordinate of the space
   * @return the space which needs to be found
   */
  Space getSpace(int x, int y);
  
  /**
   * Return the players in the world. Each world has a number of players.
   *
   * @return the players in the world
   */
  List<Player> getPlayers();
  
  /**
   * Return the player in the world according to the given player name.
   *
   * @param playerName the name of the player which needs to be found
   * @return the player which needs to be found
   */
  Player getPlayer(String playerName);
  
  /**
   * Display the information of a specified space.
   *
   * @param spaceName   the name of the space of which information needs to be displayed
   * @return the space's information
   */
  String displaySpaceInformation(String spaceName);
  
  /**
   * Display the information of a specified player.
   *
   * @param playerName   the name of the player of which information needs to be displayed
   * @return the player's information
   */
  String displayPlayerInformation(String playerName);
  
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
   * Enter the next turn of the game.
   * 
   * @return the player who has the current turn
   */
  Player getTurn();
  
  /**
   * Get the total number of turns.
   * 
   * @return the total number of turns
   */
  int getTurnCount();
  
  /**
   * Determine whether a player can be seen by others.
   *
   * @return true if the player can be seen by others, otherwise false
   */
  boolean canBeSeenByOthers();

  /**
   * Create a graphical representation of the world map in the form of a BufferedImage.
   *
   * @return the graphical representation of the world
   */
  BufferedImage generateMap();
}

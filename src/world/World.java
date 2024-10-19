package world;

import character.Character;
import character.Player;

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
   * Return the space in the world according to the given space name.
   *
   * @param spaceName the name of the space which needs to be found
   * @return the space which needs to be found
   */
  Space getSpace(String spaceName);
  
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
   * Add a computer-controlled player to the game.
   *
   * @param name       the name of the computer-controlled player who needs to be added
   * @param spaceName  the name of the space which the computer-controlled player occupies
   */
  void addComputerPlayer(String name, String spaceName);
  
  /**
   * Add a human-controlled player to the game.
   *
   * @param name       the name of the human-controlled player who needs to be added
   * @param spaceName  the name of the space which the human-controlled player occupies
   */
  void addHumanPlayer(String name, String spaceName);
  
  /**
   * Move the target character around the world.
   * The target character starts in space 0 and moves from space to space in order
   * using the ordered, 0-indexed list of spaces found in the world specification.
   */
  void moveTargetCharacter();
  
  /**
   * Reset the current turn of the game.
   */
  void resetTurn();
  
  /**
   * Enter the next turn of the game.
   * 
   * @return the player who has the current turn
   */
  Player getTurn();
  
  /**
   * Enter the next turn of the game.
   */
  void nextTurn();
  
  /**
   * Get the total number of turns.
   * 
   * @return the total number of turns
   */
  int getTurnCount();
  
  /**
   * Move a player from the space they are currently in to a neighboring space.
   * 
   * @param spaceName the name of the space where the player wants to move
   */
  void movePlayer(String spaceName);
  
  /**
   * Allow a player to pick up an item in the world that is found in the same space that they are currently in thus removing it from the space.
   * 
   * @param itemName the name of the item which the player wants to pick up
   */
  void pickUpItem(String itemName);
  
  /**
   * Allow a player to look around by displaying information about where a specific player
   * is in the world including what spaces that can been seen from where they are.
   * 
   * @return information of the space where the player is in the world
   */
  String lookAround();
  
  /**
   * Create a graphical representation of the world map in the form of a BufferedImage.
   *
   * @return the graphical representation of the world
   */
  BufferedImage generateMap();
}

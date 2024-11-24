package world;

import space.Space;

/**
 * A World consists of a number of non-overlapping {@link Space} that are laid out on a 2D grid.
 */
public interface World extends ReadonlyWorld {
  
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
   */
  void nextTurn();
  
  /**
   * Move a player from current space to a neighboring space.
   * 
   * @param spaceName the name of the space where the player wants to move
   */
  void movePlayer(String spaceName);
  
  /**
   * Allow a player to pick up an item from current space.
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
   * Move the pet to a specified space.
   * 
   * @param spaceName the name of the space where the pet should be moved to
   */
  void movePet(String spaceName);
  
  /**
   * Allow a player to make an attempt on the target character.
   * 
   * @param itemName the name of the item which the player wants to use
   * @return true if the attack is successful, false if the attack fails
   */
  boolean makeAnAttempt(String itemName);
}

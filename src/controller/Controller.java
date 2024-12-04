package controller;

import java.io.IOException;
import java.util.Map;
import world.World;

/**
 * The Controller represents the interface of the game controller.
 */
public interface Controller {
  /**
   * Method that gives control to the controller in the text-based game.
   * 
   * @param  model the model to use.
   * @throws IOException if something goes wrong appending to out
   */
  void start(World model) throws IOException;

  /**
   * Get the keyTypes of the view-based game.
   * 
   * @return the copy of the keyTypes of the view-based game
   */
  Map<Character, Runnable> getKeyTypes();

  /**
   * Get the gameActions of the view-based game.
   * 
   * @return the copy of the gameActions of the view-based game
   */
  Map<String, Runnable> getGameActions();

  /**
   * Move the player according to the given coordinates.
   *
   * @param x the x-coordinate of the space
   * @param y the y-coordinate of the space
   */
  void movePlayer(int x, int y);
}

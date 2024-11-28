package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import world.World;

/**
 * Interface of the view.
 */
public interface View {
  /**
   * Enter the game.
   */
  void enterGame();
  
  /**
   * Get the new world specification file.
   * 
   * @return the new world specification file
   */
  File getNewFile();
  
  /**
   * Show the game interface.
   * 
   * @param model the model used to show the game interface
   */
  void showGameInterface(World model);
  
  /**
   * Show the game error.
   * 
   * @param message the error message
   */
  void showError(String message);
  
  /**
   * This is to force the view to have a method to set up the keyboard. The name
   * has been chosen deliberately. This is the same method signature to add a key
   * listener in Java Swing.
   *
   * Thus our Swing-based implementation of this interface will already have such
   * a method.
   * 
   * @param listener the listener to add
   */
  void addKeyListener(KeyListener listener);

  /**
   * This is to force the view to have a method to set up actions for the game. All
   * the buttons must be given this action listener
   * 
   * @param listener the listener to add
   */
  void addActionListener(ActionListener listener);

  /**
   * Get the player name from input dialog.
   * 
   * @return the player name
   */
  String getPlayerName();

  /**
   * Get the space name from input dialog.
   * 
   * @return the space name
   */
  String getSpaceName();

  /**
   * Get the item name from input dialog.
   * 
   * @return the item name
   */
  String getItemName();
  
  /**
   * Get the name of the item used to attack the target character from input dialog.
   * 
   * @return the name of the item used to attack the target character
   */
  String getAttemptChoice();
  
  /**
   * Get the name of the space which the pet should be moved to from input dialog.
   * 
   * @return the name of the space which the pet should be moved to
   */
  String getIntendedSpace();
  
  /**
   * Add a player to the game.
   */
  void addPlayer();

  /**
   * Start the game.
   */
  void startGame();

  /**
   * Set result of last turn.
   * 
   * @param result the result of last turn
   */
  void setResult(String result);

  /**
   * End the game with a result message.
   * 
   * @param result the result of the game
   */
  void endGame(String result);

  /**
   * Configure the space click listener for the view.
   * 
   * @param mouseAdapter the mouse listener
   */
  void configureSpaceClickListener(MouseAdapter mouseAdapter);

  /**
   * Configure the player click listener for the view.
   */
  void configurePlayerClickListener();
  
  /*
   * In order to make this frame respond to keyboard events, it must be within
   * strong focus. Since there could be multiple components on the screen that
   * listen to keyboard events, we must set one as the "currently focused" one so
   * that all keyboard events are passed to that component. This component is said
   * to have "strong focus".
   * 
   * We do this by first making the component focusable and then requesting focus
   * to it. Requesting focus makes the component have focus AND removes focus from
   * whoever had it before.
   */
  void resetFocus();
}

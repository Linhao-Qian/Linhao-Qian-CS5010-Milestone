package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import world.World;

public interface View {
  
  void enterGame();
  
  void showGameInterface(World model);
  
  void showError(String message);
  
  /**
   * this is to force the view to have a method to set up the keyboard. The name
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
   * this is to force the view to have a method to set up actions for buttons. All
   * the buttons must be given this action listener
   *
   * Thus our Swing-based implementation of this interface will already have such
   * a method.
   * 
   * @param listener the listener to add
   */
  void addActionListener(ActionListener listener);

  String getPlayerName();

  String getSpaceName();

  void addPlayer();

  void startGame();

  void setResult(String result);

  void endGame(String result);
}

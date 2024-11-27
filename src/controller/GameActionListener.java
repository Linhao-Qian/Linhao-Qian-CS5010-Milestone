package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Game action listener.
 */
public class GameActionListener implements ActionListener {
  private Map<String, Runnable> gameActions;

  /**
   * Set the map for game action events.
   * 
   * @param map the actions for the game
   */
  public void setGameActionMap(Map<String, Runnable> map) {
    gameActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (gameActions.containsKey(e.getActionCommand())) {
      gameActions.get(e.getActionCommand()).run();
    }
  }
}

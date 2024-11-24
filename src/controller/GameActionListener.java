package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * A simple button listener.
 */
public class GameActionListener implements ActionListener {
  private Map<String, Runnable> gameActions;

  /**
   * Empty default constructor.
   */
  public GameActionListener() {
    // fields get set with their mutators
    gameActions = null;
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are
   * characters.
   * 
   * @param map the actions for button clicks
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

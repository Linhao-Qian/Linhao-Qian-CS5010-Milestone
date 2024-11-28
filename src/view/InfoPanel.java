package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import world.ReadonlyWorld;

/**
 * The panel used to show game information.
 */
public class InfoPanel extends JPanel {
  private static final long serialVersionUID = 4691417946172572337L;
  private PreGamePanel preGamePanel;
  private GamePanel gamePanel;

  /**
   * Constructor of the info panel.
   * 
   * @param model the read-only model
   */
  public InfoPanel(ReadonlyWorld model) {
    setPreferredSize(new Dimension(500, 3000));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(Box.createVerticalStrut(20));
    this.preGamePanel = new PreGamePanel(model);
    add(preGamePanel);
    this.gamePanel = new GamePanel(model);
  }
  
  /**
   * Add action listener to the pre-game panel.
   * 
   * @param actionListener action listener for the pre-game panel
   */
  public void addActionListener(ActionListener actionListener) {
    preGamePanel.addActionListener(actionListener);
  }
  
  /**
   * Add a player to the game.
   */
  public void addPlayer() {
    preGamePanel.refresh();
  }
  
  /**
   * Start the game.
   */
  public void startGame() {
    remove(preGamePanel);
    add(gamePanel);
    gamePanel.setHint();
    revalidate();
    repaint();
  }
  
  /**
   * Set result of last turn.
   * 
   * @param result the result of last turn
   */
  public void setResult(String result) {
    gamePanel.setResult(result);
  }
} 

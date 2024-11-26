package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import world.ReadonlyWorld;

public class InfoPanel extends JPanel {
  private static final long serialVersionUID = 4691417946172572337L;
  private PreGamePanel preGamePanel;
  private GamePanel gamePanel;

  public InfoPanel(ReadonlyWorld model) {
    setPreferredSize(new Dimension(500, 3000));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(Box.createVerticalStrut(20));
    this.preGamePanel = new PreGamePanel(model);
    add(preGamePanel);
    this.gamePanel = new GamePanel(model);
  }
  
  public void addActionListener(ActionListener actionListener) {
    preGamePanel.addActionListener(actionListener);
  }
  
  public void addPlayer() {
    preGamePanel.refresh();
  }
  
  public void startGame() {
    remove(preGamePanel);
    add(gamePanel);
    gamePanel.setHint();
    revalidate();
    repaint();
  }
  
  public void setResult(String result) {
    gamePanel.setResult(result);
  }
} 

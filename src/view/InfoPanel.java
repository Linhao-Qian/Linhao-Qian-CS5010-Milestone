package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import world.ReadonlyWorld;

public class InfoPanel extends JPanel {
  private static final long serialVersionUID = 4691417946172572337L;
  
  private PreGamePanel preGamePanel;
  private GamePanel gamePanel;

  public InfoPanel(ReadonlyWorld model) {
    BufferedImage map = model.generateMap();
    setPreferredSize(new Dimension(500, map.getHeight()));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(Box.createVerticalStrut(20));
    
  }
}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import world.ReadonlyWorld;

public class MapPanel extends JPanel {
  private static final long serialVersionUID = 6922738675563657970L;
  private JLayeredPane layeredPane;
  
  public MapPanel(ReadonlyWorld model) {
    BufferedImage map = model.generateMap();
    layeredPane = new JLayeredPane();
    layeredPane.setPreferredSize(new Dimension(map.getWidth(), map.getHeight()));
    ImageIcon mapImage = new ImageIcon(map);
    JLabel mapLabel = new JLabel(mapImage);
    mapLabel.setBounds(0, 0, map.getWidth(), map.getHeight());
    layeredPane.add(mapLabel);
    add(layeredPane, BorderLayout.CENTER);
  }
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import character.Player;
import space.Space;
import world.ReadonlyWorld;

public class MapPanel extends JPanel {
  private static final long serialVersionUID = 6922738675563657970L;
  private ReadonlyWorld model;
  private JLayeredPane layeredPane;
  private Map<String, JLabel> characters;
  
  public MapPanel(ReadonlyWorld model) {
    this.model = model;
    this.characters = new HashMap<>();
    BufferedImage map = model.generateMap();
    layeredPane = new JLayeredPane();
    layeredPane.setPreferredSize(new Dimension(map.getWidth(), map.getHeight()));
    ImageIcon mapImage = new ImageIcon(map);
    JLabel mapLabel = new JLabel(mapImage);
    mapLabel.setBounds(0, 0, map.getWidth(), map.getHeight());
    layeredPane.add(mapLabel);
    add(layeredPane, BorderLayout.CENTER);
    drawTarget();
  }
  
  private void drawCharacter(String name, int positionX, int positionY) {
    JLabel characterLabel = characters.getOrDefault(name, new JLabel(name));
    layeredPane.remove(characterLabel);
    characterLabel.setBounds(positionX, positionY, 100, 30);
    characterLabel.setForeground(Color.RED);
    characterLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
          JOptionPane.showMessageDialog(characterLabel, model.displayPlayerInformation(name), "Player information", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    layeredPane.add(characterLabel, Integer.valueOf(1));
    characters.put(name, characterLabel);
  }
  
  private void drawTarget() {
    int positionX = model.getSpaces().get(model.getTargetCharacterPosition()).getPosition()[3] * 20 - 50;
    int positionY = model.getSpaces().get(model.getTargetCharacterPosition()).getPosition()[2] * 20 + 16;
    drawCharacter(model.getTargetCharacter().getName(), positionX, positionY);
  }
  
  private void drawPlayers() {
    Map<Space, List<String>> spacePlayers = new HashMap<>();
    for (Player player : model.getPlayers()) {
      String playerName = player.getName();
      Space space = player.getSpace();
      List<String> players = spacePlayers.getOrDefault(space, new ArrayList<>());
      players.add(playerName);
      spacePlayers.put(space, players);
    }
    for (Space space : spacePlayers.keySet()) {
      List<String> players = spacePlayers.get(space);
      for (int i = 0; i < players.size(); i++) {
        int positionX = space.getPosition()[3] * 20;
        int positionY = space.getPosition()[0] * 20 + 20 * (i + 1);
        drawCharacter(players.get(i), positionX, positionY);
      }
    }
  }
  
  public void refresh() {
    drawTarget();
    drawPlayers();
  }
}

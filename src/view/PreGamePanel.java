package view;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import world.ReadonlyWorld;

public class PreGamePanel extends JPanel {
  private static final long serialVersionUID = 1836177703755529352L;
  private ReadonlyWorld model;
  private JLabel hintLabel;
  private JButton addComputerPlayerButton;
  private JButton addHumanPlayerButton;
  private JButton startGameButton;
  
  public PreGamePanel(ReadonlyWorld model) {
    this.model = model;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    hintLabel = new JLabel("You can only start the game after adding at least one player.");
    hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(hintLabel);
    add(Box.createVerticalStrut(20));
    addComputerPlayerButton = new JButton("Add a new computer-controlled player");
    addComputerPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(addComputerPlayerButton);
    add(Box.createVerticalStrut(20));
    addHumanPlayerButton = new JButton("Add a new human-controlled player");
    addHumanPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(addHumanPlayerButton);
    add(Box.createVerticalStrut(20));
    startGameButton = new JButton("Start the game");
    startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    startGameButton.setEnabled(false);
    add(startGameButton);
  }
  
  public void addActionListener(ActionListener actionListener) {
    addComputerPlayerButton.addActionListener(actionListener);
    addHumanPlayerButton.addActionListener(actionListener);
    startGameButton.addActionListener(actionListener);
  }
  
  public void refresh() {
    StringBuilder hintText = new StringBuilder("<html>Current Players:<br>");
    model.getPlayers().forEach(player -> hintText.append(player.getName()).append("<br>"));
    hintText.append("</html>");
    hintLabel.setText(hintText.toString());
    hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
    startGameButton.setEnabled(model.getPlayers().size() > 0);
  }
}

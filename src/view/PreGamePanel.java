package view;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import world.ReadonlyWorld;

public class PreGamePanel extends JPanel {
  private static final long serialVersionUID = 1836177703755529352L;
  private JLabel hintLabel;
  
  public PreGamePanel(ReadonlyWorld model) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    hintLabel = new JLabel("This is the description text.");
    hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(hintLabel);
    add(Box.createVerticalStrut(20));
  }
}

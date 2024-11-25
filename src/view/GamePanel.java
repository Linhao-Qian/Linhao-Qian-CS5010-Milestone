package view;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import world.ReadonlyWorld;

public class GamePanel extends JPanel {
  private static final long serialVersionUID = 1860979716621182121L;
  private ReadonlyWorld model;
  private JLabel hintLabel;
  private JLabel operationLabel;
  private JLabel resultLabel;
  
  public GamePanel(ReadonlyWorld model) {
    this.model = model;
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    hintLabel = new JLabel();
    hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(hintLabel);
    add(Box.createVerticalStrut(20));
    operationLabel = new JLabel(
        "<html>Right click on a space with the mouse to move current player to it.<br>"
        + "Press 'i' to pick up an item from current space.<br>"
        + "Press 'l' to look around current space.<br>"
        + "Press 'a' to make an attempt.<br>"
        + "Press 'm' to move the pet.<br></html>");
    operationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    operationLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(operationLabel);
    add(Box.createVerticalStrut(20));
    resultLabel = new JLabel();
    resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(resultLabel);
  }
  
  public void startGame() {
    StringBuilder hintText = new StringBuilder(
        String.format("<html>Now, it is %s's turn<br>", model.getTurn().getName()));
    hintText.append(model.displaySpaceInformation(model.getTurn().getSpace().getName()).replace("\n", "<br>"));
    hintText.append("</html>");
    hintLabel.setText(hintText.toString());
  }
}

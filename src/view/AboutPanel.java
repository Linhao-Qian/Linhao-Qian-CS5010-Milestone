package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The About screen.
 */
public class AboutPanel extends JPanel {
  private static final long serialVersionUID = 1032078991652443954L;
  private JLabel welcomeLabel;
  private JLabel creditInfo;
  private JButton confirmButton;
  
  /**
   * Constructor for the About screen.
   */
  public AboutPanel() {
    setLayout(new BorderLayout());
    welcomeLabel = new JLabel("<html><h1>Welcome to the Game!</h1></html>", JLabel.CENTER);
    creditInfo = new JLabel(
        "<html><p>Created by: Linhao Qian</p><p>External resource: https://en.wikipedia.org/wiki/Kill_Doctor_Lucky</p></html>",
        JLabel.CENTER);
    confirmButton = new JButton("Enter Game");
    add(welcomeLabel, BorderLayout.NORTH);
    add(creditInfo, BorderLayout.CENTER);
    add(confirmButton, BorderLayout.SOUTH);
  }

  /**
   * Add action listener to the confirm button.
   * 
   * @param actionListener action listener for the confirm button
   */
  public void addActionListener(ActionListener actionListener) {
    confirmButton.addActionListener(actionListener);
  }
}

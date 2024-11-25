package view;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import world.ReadonlyWorld;
import world.World;

/**
 * Implementation of the view.
 */
public class FrameView extends JFrame implements View {
  private static final long serialVersionUID = 7425202540971385542L;
  private ReadonlyWorld model;
  private AboutPanel aboutPanel;
  private MainPanel mainPanel;
  
  /**
   * Constructor.
   * 
   * @param model the read-only model
   */
  public FrameView(ReadonlyWorld model) {
    super(model.getName()); 
    setSize(1200, 800);
    setLocation(180, 16);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new CardLayout());
    aboutPanel = new AboutPanel();
    mainPanel = new MainPanel(model);
    add(aboutPanel);
    setVisible(true);
  }
  
  @Override
  public void addActionListener(ActionListener actionListener) {
    aboutPanel.addActionListener(actionListener);
    mainPanel.addActionListener(actionListener);
  }
 
  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void enterGame() {
    remove(aboutPanel);
    add(mainPanel);
    revalidate();
    repaint();
  }
 
  @Override
  public void showGameInterface(World model) {
    setName(model.getName());
    this.model = model;
    remove(mainPanel);
    this.mainPanel = new MainPanel(model);
    add(mainPanel);
    mainPanel.showGameInterface();
  }

  @Override
  public String getPlayerName() {
    return JOptionPane.showInputDialog(this, "Enter the name of the player:");
  }
  
  @Override
  public String getSpaceName() {
    return JOptionPane.showInputDialog(this, "Enter the name of the initial space:");
  }
  
  @Override
  public void addPlayer() {
    mainPanel.addPlayer();
  }
  
  @Override
  public void startGame() {
    mainPanel.startGame();
  }
}

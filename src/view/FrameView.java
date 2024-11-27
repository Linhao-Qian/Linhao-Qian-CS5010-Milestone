package view;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import world.ReadonlyWorld;
import world.World;

/**
 * Implementation of the view.
 */
public class FrameView extends JFrame implements View {
  private static final long serialVersionUID = 7425202540971385542L;
  private JScrollPane scrollAboutPane;
  private JScrollPane scrollMainPane;
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
    scrollAboutPane = new JScrollPane(aboutPanel);
    add(scrollAboutPane);
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
    remove(scrollAboutPane);
    scrollMainPane = new JScrollPane(mainPanel);
    add(scrollMainPane);
    revalidate();
    repaint();
  }
 
  @Override
  public void showGameInterface(World model) {
    setName(model.getName());
    remove(scrollMainPane);
    this.mainPanel = new MainPanel(model);
    this.scrollMainPane = new JScrollPane(mainPanel);
    add(scrollMainPane);
    mainPanel.showGameInterface();
    revalidate();
    repaint();
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
  public String getItemName() {
    return JOptionPane.showInputDialog(this, "Enter the name of the item which you want to pick up:");
  }
  
  @Override
  public String getAttemptChoice() {
    return JOptionPane.showInputDialog(this, "Enter the name of the item which you want to use\n(If you have no any item, please enter 'pokeEyes'):");
  }
  
  @Override
  public String getIntendedSpace() {
    return JOptionPane.showInputDialog(this, "Enter the name of the space which you want to move the pet to:");
  }
  
  @Override
  public void addPlayer() {
    mainPanel.addPlayer();
  }
  
  @Override
  public void startGame() {
    mainPanel.startGame();
  }

  @Override
  public void setResult(String result) {
    mainPanel.setResult(result);
  }

  @Override
  public void endGame(String result) {
    JOptionPane.showMessageDialog(this, result, "Game over", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void configureMouseListener(MouseAdapter mouseAdapter) {
    mainPanel.configureMouseListener(mouseAdapter);
  }
  
  /*
   * In order to make this frame respond to keyboard events, it must be within
   * strong focus. Since there could be multiple components on the screen that
   * listen to keyboard events, we must set one as the "currently focused" one so
   * that all keyboard events are passed to that component. This component is said
   * to have "strong focus".
   * 
   * We do this by first making the component focusable and then requesting focus
   * to it. Requesting focus makes the component have focus AND removes focus from
   * whoever had it before.
   */
  @Override
  public void resetFocus() {
    setFocusable(true);
    requestFocus();
  }
}

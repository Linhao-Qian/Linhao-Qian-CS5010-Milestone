package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import world.ReadonlyWorld;

/**
 * The main panel of the game.
 */
public class MainPanel extends JPanel {
  private static final long serialVersionUID = -8668618688820184387L;
  private JMenuBar menuBar;
  private JMenu gameMenu;
  private JMenuItem newWorldItem;
  private JMenuItem currentWorldItem;
  private JMenuItem quitItem;
  private JLabel worldPlaceholder;
  private MapPanel mapPanel;
  private InfoPanel infoPanel;
  
  /**
   * Constructor for the main panel.
   * 
   * @param model the read-only model
   */
  public MainPanel(ReadonlyWorld model) {
    setLayout(new BorderLayout());
    menuBar = new JMenuBar();
    gameMenu = new JMenu("Game Menu");
    newWorldItem = new JMenuItem("Start a new game with a new world specification");
    currentWorldItem = new JMenuItem("Start a new game with the current world specification");
    quitItem = new JMenuItem("Quit");
    menuBar.add(gameMenu);
    gameMenu.add(newWorldItem);
    gameMenu.add(currentWorldItem);
    gameMenu.add(quitItem);
    worldPlaceholder = new JLabel("Please choose an option from the game menu.", JLabel.CENTER);
    worldPlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    mapPanel = new MapPanel(model);
    infoPanel = new InfoPanel(model);
    add(menuBar, BorderLayout.NORTH);
    add(worldPlaceholder, BorderLayout.CENTER);
  }

  /**
   * Add action listener to the game.
   * 
   * @param actionListener action listener for the game
   */
  public void addActionListener(ActionListener actionListener) {
    newWorldItem.addActionListener(actionListener);
    currentWorldItem.addActionListener(actionListener);
    quitItem.addActionListener(actionListener);
    infoPanel.addActionListener(actionListener);
  }
  
  /**
   * Show the game interface.
   */
  public void showGameInterface() {
    remove(worldPlaceholder);
    JScrollPane scrollPaneLeft = new JScrollPane(mapPanel);
    add(scrollPaneLeft, BorderLayout.WEST);
    JScrollPane scrollPaneRight = new JScrollPane(infoPanel);
    add(scrollPaneRight, BorderLayout.EAST);
    revalidate();
    repaint();
  }
  
  /**
   * Add a player to the game.
   */
  public void addPlayer() {
    mapPanel.refresh();
    infoPanel.addPlayer();
  }
  
  /**
   * Start the game.
   */
  public void startGame() {
    infoPanel.startGame();
  }
  
  /**
   * Set result of last turn.
   * 
   * @param result the result of last turn
   */
  public void setResult(String result) {
    mapPanel.refresh();
    infoPanel.setResult(result);
  }
  
  /**
   * Configure the mouse listener for the map panel.
   * 
   * @param mouseAdapter the mouse listener
   */
  public void configureMouseListener(MouseAdapter mouseAdapter) {
    mapPanel.configureMouseListener(mouseAdapter);
  }
}

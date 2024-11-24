package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
  
  private Map<String, JLabel> characters;
  private JButton addComputerPlayerButton;
  private JButton addHumanPlayerButton;
  private JButton startGameButton;
  private JButton movePlayerButton;
  
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
  
  public void addActionListener(ActionListener actionListener) {
    aboutPanel.addActionListener(actionListener);
    mainPanel.addActionListener(actionListener);
  }
 
  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * 显示主界面
   */
  public void enterGame() {
    remove(aboutPanel);
    add(mainPanel);
    revalidate();
    repaint();
  }
  
//  /**
//   * Accept the set of callbacks from the controller, and hook up as needed to
//   * various things in this view.
//   * 
//   * @param f the set of feature callbacks as a Features object
//   */
//
//  public void setFeatures(Features f) {
//    // 添加菜单操作
//    
//    
//    addComputerPlayerButton = new JButton("Add a new computer-controlled player");
//    addComputerPlayerButton.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        String spaceName = JOptionPane.showInputDialog(this, "Enter the name of the space you want the player to move to:");
//        f.movePlayer(spaceName);
//      }
//    });
//    
//    movePlayerButton = new JButton("Move current player");
//    movePlayerButton.addActionListener(new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        String playerName = JOptionPane.showInputDialog(this, "Enter the name of the player:");
//        String spaceName = JOptionPane.showInputDialog(this, "Enter the name of the initial space:");
//        f.addComputerPlayer(playerName, spaceName);
//      }
//    });
//  }


  public void showGameInterface(World model) {
    setName(model.getName());
    this.model = model;
    remove(mainPanel);
    this.mainPanel = new MainPanel(model);
    add(mainPanel);
    mainPanel.showGameInterface();
  }

//
//  public void drawCharacter(String name, int positionX, int positionY) {
//    JLabel characterLabel = characters.getOrDefault(name, new JLabel(name));
//    layeredPane.remove(characterLabel);
//    characterLabel.setBounds(positionX, positionY, 100, 30); // 设置标记位置和大小
//    characterLabel.setForeground(Color.RED); // 设置字体颜色
//    layeredPane.add(characterLabel, Integer.valueOf(1)); // 添加到第 1 层
//    characters.put(name, characterLabel);
//  }
//  

//
//  public void startGame() {
//    hintLabel.setText("game has started!");
// 
//    
//    movePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//    
//    rightPanel.add(movePlayerButton);
//    
//  }
}

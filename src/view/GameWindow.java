package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Features;
import world.World;

public class GameWindow extends JFrame implements View {
  private static final long serialVersionUID = 1L;
  private JPanel aboutPanel;
  private JPanel mainPanel;
  private JLabel welcomeLabel;
  private JLabel creditInfo;
  private JButton confirmButton;
  private JMenuBar menuBar;
  private JMenu gameMenu;
  private JMenuItem newWorldItem;
  private JMenuItem currentWorldItem;
  private JMenuItem quitItem;
  private JLabel worldPlaceholder;
  private JScrollPane scrollPane; // 滚动支持
  
  /**
   * Constructor.
   * 
   * @param caption    the caption to use
   */
  public GameWindow(String caption) {
    super(caption);  
    setSize(1200, 800);
    setLocation(180, 16);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new CardLayout());
    initializeAboutPanel();
    initializeMainPanel();
    add(aboutPanel, "WELCOME");
    add(mainPanel, "MAIN");

    showAboutPanel(); // 默认显示欢迎界面
  }

  /**
   * 初始化欢迎界面
   */
  private void initializeAboutPanel() {
    aboutPanel = new JPanel(new BorderLayout());

    // 欢迎信息
    welcomeLabel = new JLabel("<html><h1>Welcome to the Game!</h1></html>", JLabel.CENTER);
    creditInfo = new JLabel(
        "<html><p>Created by: Linhao Qian</p><p>External resource: https://en.wikipedia.org/wiki/Kill_Doctor_Lucky</p></html>",
        JLabel.CENTER);
    // 进入游戏按钮
    confirmButton = new JButton("Enter Game");
    confirmButton.addActionListener(l -> showMainPanel());
    // 布局组件
    aboutPanel.add(welcomeLabel, BorderLayout.NORTH);
    aboutPanel.add(creditInfo, BorderLayout.CENTER);
    aboutPanel.add(confirmButton, BorderLayout.SOUTH);

    setVisible(true);
  }

  /**
   * 初始化主游戏界面
   */
  private void initializeMainPanel() {
    mainPanel = new JPanel(new BorderLayout());

    // 创建菜单
    menuBar = new JMenuBar();
    gameMenu = new JMenu("Game Menu");
    newWorldItem = new JMenuItem("Start a new game with a new world specification");
    currentWorldItem = new JMenuItem("Start a new game with the current world specification");
    quitItem = new JMenuItem("Quit");
    menuBar.add(gameMenu);
    gameMenu.add(newWorldItem);
    gameMenu.add(currentWorldItem);
    gameMenu.add(quitItem);

    
    // 世界图形界面（占位符）
    worldPlaceholder = new JLabel("Please choose an option from the game menu.", JLabel.CENTER);
    worldPlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    // 布局主游戏界面
    mainPanel.add(menuBar, BorderLayout.NORTH);
    mainPanel.add(worldPlaceholder, BorderLayout.CENTER);
  }
  
  /**
   * 显示欢迎界面
   */
  private void showAboutPanel() {
      CardLayout layout = (CardLayout) getContentPane().getLayout();
      layout.show(getContentPane(), "WELCOME");
  }

  /**
   * 显示主界面
   */
  private void showMainPanel() {
      CardLayout layout = (CardLayout) getContentPane().getLayout();
      layout.show(getContentPane(), "MAIN");
  }
  
  /**
   * Accept the set of callbacks from the controller, and hook up as needed to
   * various things in this view.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  @Override
  public void setFeatures(Features f) {
    // 添加菜单操作
    newWorldItem.addActionListener(l -> f.selectNewWorld());
    currentWorldItem.addActionListener(l -> f.selectCurrentWorld());
    quitItem.addActionListener(l -> f.exitProgram());

  }

  @Override
  public void showGameInterface(World model) {
    ImageIcon mapImage = new ImageIcon(model.generateMap());
    JLabel mapLabel = new JLabel(mapImage);
    scrollPane = new JScrollPane(mapLabel);
    mainPanel.remove(worldPlaceholder);  // 移除占位符
    mainPanel.add(scrollPane, BorderLayout.CENTER);  // 添加地图

    revalidate();  // 重新布局界面
    repaint();
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

}

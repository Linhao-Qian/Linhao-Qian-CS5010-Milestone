package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.Features;

public class GameWindow extends JFrame implements View {
  private static final long serialVersionUID = 1L;
  private JPanel aboutPanel;
  private JPanel mainPanel;
  private JLabel welcomeLabel;
  private JLabel authorInfo;
  private JButton confirmButton;
  private JMenuBar menuBar;
  private JMenu gameMenu;
  private JMenuItem newWorldItem;
  private JMenuItem quitItem;
  private JLabel worldPlaceholder;
  
  /**
   * Constructor.
   * 
   * @param caption    the caption to use
   */
  public GameWindow(String caption) {
    super(caption);  
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new CardLayout());
    initializeWelcomePanel();
    initializeMainPanel();
    add(aboutPanel, "WELCOME");
    add(mainPanel, "MAIN");

    showAboutPanel(); // 默认显示欢迎界面
  }

  /**
   * 初始化欢迎界面
   */
  private void initializeWelcomePanel() {
    aboutPanel = new JPanel(new BorderLayout());

    // 欢迎信息
    welcomeLabel = new JLabel("<html><h1>Welcome to the Game!</h1></html>", JLabel.CENTER);
    authorInfo = new JLabel("<html><p>Created by: Your Name</p></html>", JLabel.CENTER);

    // 进入游戏按钮
    confirmButton = new JButton("Enter Game");

    // 布局组件
    aboutPanel.add(welcomeLabel, BorderLayout.NORTH);
    aboutPanel.add(authorInfo, BorderLayout.CENTER);
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
    gameMenu = new JMenu("Game");
    newWorldItem = new JMenuItem("Start New World");
    quitItem = new JMenuItem("Quit");
    menuBar.add(gameMenu);
    gameMenu.add(newWorldItem);
    gameMenu.add(quitItem);

    
    // 世界图形界面（占位符）
    worldPlaceholder = new JLabel("World Display Placeholder", JLabel.CENTER);
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
    confirmButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          showMainPanel();
      }
    });
    // 添加菜单操作
    newWorldItem.addActionListener(e -> {
        System.out.println("Starting a new world...");
        // 可以调用控制器的方法启动新世界
    });
    quitItem.addActionListener(l -> f.exitProgram());

  }

}

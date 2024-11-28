package controller.test;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.File;

import view.View;
import world.World;

/**
 * Mock implementation of the view for testing.
 */
public class MockView implements View {
  private final StringBuilder log;
  
  /**
   * Constructs a MockView.
   *
   * @param log   the log to record actions
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public File getNewFile() {
    log.append("getNewFile called\n");
    return new File("res/Leo's_world.txt");
  }
  
  @Override
  public void enterGame() {
    log.append("enterGame called\n");
  }

  @Override
  public void showGameInterface(World model) {
    log.append("showGameInterface called with model: ").append(model.getName()).append("\n");
  }

  @Override
  public void showError(String message) {
    log.append("showError called with message: ").append(message).append("\n");
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    log.append("addKeyListener called\n");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    log.append("addActionListener called\n");
  }

  @Override
  public String getPlayerName() {
    log.append("getPlayerName called\n");
    return "TestPlayer";
  }

  @Override
  public String getSpaceName() {
    log.append("getSpaceName called\n");
    return "TestSpace";
  }

  @Override
  public String getItemName() {
    log.append("getItemName called\n");
    return "TestItem";
  }

  @Override
  public String getAttemptChoice() {
    log.append("getAttemptChoice called\n");
    return "TestWeapon";
  }

  @Override
  public String getIntendedSpace() {
    log.append("getIntendedSpace called\n");
    return "TargetSpace";
  }

  @Override
  public void addPlayer() {
    log.append("addPlayer called\n");
  }

  @Override
  public void startGame() {
    log.append("startGame called\n");
  }

  @Override
  public void setResult(String result) {
    log.append("setResult called with result: ").append(result).append("\n");
  }

  @Override
  public void endGame(String result) {
    log.append("endGame called with result: ").append(result).append("\n");
  }


  @Override
  public void configureSpaceClickListener(MouseAdapter mouseAdapter) {
    log.append("configureSpaceClickListener called\n");
  }

  @Override
  public void configurePlayerClickListener() {
    log.append("configurePlayerClickListener called\n");
  }
  
  @Override
  public void resetFocus() {
    log.append("resetFocus called\n");
  }
}

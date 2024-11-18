package view;

import controller.Features;
import world.World;

public interface View {
  /**
   * Get the set of feature callbacks that the view can use.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);
  
  void showGameInterface(World model);
  
  void showError(String message);
}

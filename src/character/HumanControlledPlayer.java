package character;

import space.Space;

/**
 * A HumanControlledPlayer represents a player controlled by human.
 */
public class HumanControlledPlayer extends Player {

  /**
   * Constructs a HumanControlledPlayer object, which has a name and an initial space.
   * 
   * @param name     the name of the player
   * @param space    the initial space in which the player enters the world
   */
  public HumanControlledPlayer(String name, Space space) {
    super(name, space);
  }
}

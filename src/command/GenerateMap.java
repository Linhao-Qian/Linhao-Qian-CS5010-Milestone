package command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import world.World;

public class GenerateMap implements WorldCommand {

  @Override
  public void execute(World model, Appendable out) throws IOException {
    out.append("Now, start generating the world map.");
    BufferedImage image = model.generateMap();
    String outputFileName = model.getName().concat(".png");
    ImageIO.write(image, "png", new File(outputFileName));
    out.append(String.format("The world map has been generated successfully. It is in the %s file.", outputFileName));
  }
}

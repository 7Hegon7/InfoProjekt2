package pvz.td;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoad {
    
    private BufferedImage img;
    
    public BufferedImage loadImage(String path) throws IOException{
        img = ImageIO.read(getClass().getResource(path));
        return img;
    }
    
}

package pvz.td;

import java.awt.image.BufferedImage;

public class Sprite {

    private final BufferedImage img;

    public Sprite(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage image = img.getSubimage((col * 88) - 88, (row * 88) - 88, width, height);
        return image;
    }

    public BufferedImage grabMyNumber(int col, int row, int width, int height) {
        BufferedImage image = img.getSubimage((col * 36) - 36, (row * 36) - 36, width, height);
        return image;
    }
}

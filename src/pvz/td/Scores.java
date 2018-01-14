package pvz.td;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Scores {

    protected int y;
    protected int value;
    protected BufferedImage[] bImage;

    public int getNum(int stelle) {
        int last = 0;
        int now = 0;
        for (int i = 4; i >= stelle; i--) {
            now = (value - last) / (int) Math.pow(10, (i - 1));
            last += now * (int) Math.pow(10, (i - 1));
        }
        return now;
    }

    public void render(Graphics g) {
        int xstart = 920;
        g.drawImage(bImage[getNum(1)], (xstart + 3 * 30), y, null);
        g.drawImage(bImage[getNum(2)], (xstart + 2 * 30), y, null);
        g.drawImage(bImage[getNum(3)], (xstart + 30), y, null);
        g.drawImage(bImage[getNum(4)], xstart, y, null);
    }

    public void adjust(int delta) {
        value += delta;
        if (value > 9999) {
            value = 9999;
        }
        if (value < 0) {
            value = 0;
        }

    }
}

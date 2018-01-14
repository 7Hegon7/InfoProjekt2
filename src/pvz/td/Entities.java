package pvz.td;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entities {

    protected int y;
    protected int x;
    protected int size;
    protected int width;
    protected BufferedImage bImage = null;

    public void render(Graphics g) {
        g.drawImage(bImage, x, y, null);
    }
    public void tick(){
                
    }

    public void setXandY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, size, width);
    }
}

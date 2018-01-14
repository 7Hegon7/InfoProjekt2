package pvz.td;

import java.awt.Graphics;

public class Wave extends Scores {

    public Wave(Textures t) {
        this.bImage = t.gnums;
        value = Spielfeld.field.waveNr;
    }
    
    public void render(Graphics g) {
        g.drawImage(bImage[getNum(1)], 1050, 670, null);
    }
}
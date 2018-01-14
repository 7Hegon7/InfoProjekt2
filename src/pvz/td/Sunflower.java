package pvz.td;

public class Sunflower extends Plants {

    public Sunflower(Textures t) {
        super(400);
        this.bImage = t.sunflower;
    }

    public void tick() {
        if (timer == 120) {
            Spielfeld.l.adjust(15);
            timer = 0;
        }
        timer++;
    }
}

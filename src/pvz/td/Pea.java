package pvz.td;

public class Pea extends Missile {

    public Pea(Zombies z, Textures t) {
        super(z);
        this.bImage = t.pea;
        value = 3;
        speed = 7;
        size = 15;
        width = 15;
    }

}

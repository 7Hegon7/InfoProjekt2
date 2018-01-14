package pvz.td;

public class Light extends Scores {

    public Light(Textures t) {
        this.bImage = t.gnums;
        y = 52;
        value = Spielfeld.MONEY;
    }
}

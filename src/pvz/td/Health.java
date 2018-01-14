package pvz.td;

public class Health extends Scores {

    public Health(Textures t) {
        this.bImage = t.hnums;
        y = 12;
        value = Spielfeld.HEALTH;
    }

}

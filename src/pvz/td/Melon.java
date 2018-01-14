package pvz.td;

public class Melon extends ShootingPlants {

    public Melon(Textures t) {
        super(600, 90, 300);
        this.t = t;
        this.bImage = t.melon;
    }

    public Missile newMissile(Zombies z) {
        MelonM Peter = new MelonM(z, t);
        Peter.setXandY(x, y);
        return Peter;
    }
}

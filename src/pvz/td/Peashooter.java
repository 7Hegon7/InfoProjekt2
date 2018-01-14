package pvz.td;

public class Peashooter extends ShootingPlants {
    
    public Peashooter(Textures t) {
        super(400, 24, 250);
        this.t = t;
        this.bImage = t.peashooter;
    }

    public Missile newMissile(Zombies z) {
        Pea Horst = new Pea(z, t);
        Horst.setXandY(this.x, this.y);
        return Horst;
    }

}

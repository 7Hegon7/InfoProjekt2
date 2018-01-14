package pvz.td;

public class RangedPlants extends Plants {

    public int range;

    public RangedPlants(int rng, int prz) {
        super(prz);
        this.range = rng;
    }

    public boolean zombieInRange(Zombies suspect) {
        return getDist(suspect) <= range;
    }

    public int getDist(Zombies suspect) {
        int dX = suspect.x - this.x;
        int dY = suspect.y - this.y;
        int dist = (int) Math.sqrt(dX * dX + dY * dY);
        return dist;
    }
}

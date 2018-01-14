package pvz.td;

public class Iceshroom extends RangedPlants {
    
    public int slow = 4;
    int prevspd;

    public Iceshroom(Textures t) {
        super(300, 200);
        this.bImage = t.iceshroom;
    }

    public void tick() {
        for (int i = 0; i < Spielfeld.k.getZombies().size(); i++) {
            prevspd = prevspd = Spielfeld.k.getZombies().get(i).speed;
            if (zombieInRange(Spielfeld.k.getZombies().get(i)) && Spielfeld.k.getZombies().get(i).speed > slow) {
                Spielfeld.k.getZombies().get(i).speed = slow;
            } else if(!zombieInRange(Spielfeld.k.getZombies().get(i)) && Spielfeld.k.getZombies().get(i).speed > slow) {
                Spielfeld.k.getZombies().get(i).speed = prevspd;
            }
        }
    }
}

package pvz.td;

public class ShootingPlants extends RangedPlants {

    private Zombies underAttack = null;
    public int attackspeed;
    protected Textures t;    
    
    public ShootingPlants(int rng, int as, int prz) {
        super(rng, prz);
        this.attackspeed = as;
    }

    public void tick() {

        if (timer == attackspeed) {
            if (underAttack != null && (underAttack.health <= 0 || !zombieInRange(underAttack))) {
                underAttack = null;
            }
            if (underAttack == null) {
                int lastDist = Integer.MAX_VALUE;
                for (int i = 0; i < Spielfeld.k.getZombies().size(); i++) {
                    if (zombieInRange(Spielfeld.k.getZombies().get(i)) && lastDist > getDist(Spielfeld.k.getZombies().get(i))) {
                        lastDist = getDist(Spielfeld.k.getZombies().get(i));
                        underAttack = Spielfeld.k.getZombies().get(i);
                    }
                }
            } 
            if(underAttack != null){
                Spielfeld.k.addEntity(newMissile(underAttack));
            }
            timer = 0;
        }
        timer++;
    }

    public Missile newMissile(Zombies z) {
        System.out.println("jgs");
        return null;
    }
}

package pvz.td;

public class MelonM extends Missile {

    public MelonM(Zombies z, Textures t) {
        super(z);
        this.bImage = t.melonM;
        value = 15;
        speed = 4;
        size = 35;
        width = 35;
    }

}

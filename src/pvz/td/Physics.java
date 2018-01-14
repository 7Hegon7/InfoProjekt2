package pvz.td;

import java.util.ArrayList;

public class Physics {

    public static Missile hitBy;

    public static boolean Collision(Zombies zombs, ArrayList<Missile> mssl) {

        for (int i = 0; i < mssl.size(); i++) {
            if (zombs.getBounds().intersects(mssl.get(i).getBounds())) {
                hitBy = mssl.get(i);
                return true;
            }
        }
        return false;
    }

    public static boolean damageSurrounds(Zombies zombs, ArrayList<Zombies> zombies, Missile m) {

        for (int i = 0; i < zombies.size(); i++) {
            if (zombs.getBounds().intersects(zombies.get(i).getBounds())) {
                zombies.get(i).health -= m.value;
                return true;
            }
        }
        return false;
    }

}

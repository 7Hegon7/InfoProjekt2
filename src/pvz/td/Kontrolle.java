package pvz.td;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Kontrolle {

    private static LinkedList<Peashooter> peas = new LinkedList<Peashooter>();
    private static LinkedList<Sunflower> suns = new LinkedList<Sunflower>();
    private static LinkedList<Iceshroom> ice = new LinkedList<Iceshroom>();
    private static LinkedList<Melon> mel = new LinkedList<Melon>();
    private static LinkedList<Football> foot = new LinkedList<Football>();
    private static LinkedList<Zombie> zomb = new LinkedList<Zombie>();
    private static LinkedList<Ballon> ball = new LinkedList<Ballon>();
    private static LinkedList<Disco> disco = new LinkedList<Disco>();
    private static ArrayList<Entities> ents = new ArrayList<Entities>();
    private static ArrayList<Missile> mssl = new ArrayList<Missile>();
    private static ArrayList<Zombies> zombs = new ArrayList<Zombies>();
    Disco discoPlaced;
    Ballon ballPlaced;
    Zombie zombPlaced;
    Football footPlaced;
    Melon melPlaced;
    Iceshroom icePlaced;
    Sunflower sunsPlaced;
    Peashooter peasPlaced;
    Spielfeld game;

    public Kontrolle(Spielfeld game) {
        this.game = game;
    }

    public void tick() {
        for (int i = 0; i < ents.size(); i++) {
            ents.get(i).tick();
        }
    }

    public void render(Graphics g) {

        Collections.sort(ents, (Entities e1, Entities e2) -> (e1.y + e1.size) < (e2.y + e2.size) ? -1 : 1);
        for (int i = 0;
                i < ents.size();
                i++) {
            ents.get(i).render(g);
        }
    }

    public void addEntity(Entities e) {
        if (e instanceof Iceshroom) {
            ice.add((Iceshroom) e);
        } else if (e instanceof Peashooter) {
            peas.add((Peashooter) e);
        } else if (e instanceof Sunflower) {
            suns.add((Sunflower) e);
        } else if (e instanceof Melon) {
            mel.add((Melon) e);
        } else if (e instanceof Football) {
            foot.add((Football) e);
        } else if (e instanceof Disco) {
            disco.add((Disco) e);
        } else if (e instanceof Zombie) {
            zomb.add((Zombie) e);
        } else if (e instanceof Ballon) {
            ball.add((Ballon) e);
        } else if (e instanceof Missile) {
            mssl.add((Missile) e);
        }
        if (e instanceof Zombies){
            zombs.add((Zombies) e);
        }
        ents.add(e);
    }

    public void killZombie(Zombies z) {
        Spielfeld.l.adjust(z.value);
        removeZombie(z);
    }

    public void removeZombie(Zombies z) {
        if (z instanceof Football) {
            foot.remove((Football) z);
        } else if (z instanceof Disco) {
            disco.remove((Disco) z);
        } else if (z instanceof Zombie) {
            zomb.remove((Zombie) z);
        } else if (z instanceof Ballon) {
            ball.remove((Ballon) z);
        }
        zombs.remove(z);
        ents.remove(z);
    }
    
    public void removeMissile(Missile m){
        mssl.remove(m);
        ents.remove(m);
    }

    public void deletePlant(Plants p) {
        if (p instanceof Iceshroom) {
            ice.remove((Iceshroom) p);
        } else if (p instanceof Peashooter) {
            peas.remove((Peashooter) p);
        } else if (p instanceof Sunflower) {
            suns.remove((Sunflower) p);
        } else if (p instanceof Melon) {
            mel.remove((Melon) p);
        }
        Spielfeld.l.adjust(p.prize);
        ents.remove(p);
    }

    public boolean allDead() {
        return foot.isEmpty() && zomb.isEmpty() && ball.isEmpty() && disco.isEmpty();
    }

    public ArrayList<Zombies> getZombies() {
        return zombs;
    }

    public ArrayList<Missile> getMissiles() {
        return mssl;
    }

}

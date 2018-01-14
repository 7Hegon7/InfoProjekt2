package pvz.td;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Zombies extends Entities {

    public int speed;
    public int health;
    public int value;
    Textures t;
    public Animations[] a;// = new Animations[4];
    public int direction = 1;
//    1 rechts
//    3 links
//    2 oben
//    4 unten

    public Zombies(int health, int speed, int value, BufferedImage[] anim) {
        this.speed = speed;
        this.health = health;
        this.value = value;
        y = 15;
        x = -88;
        size = 130;
        width = 88;
        a = new Animations[4];
        a[0] = (new Animations(15, anim[4], anim[5], anim[6], anim[7]));
        a[1] = (new Animations(15, anim[8], anim[9], anim[10], anim[11]));
        a[2] = (new Animations(15, anim[0], anim[1], anim[2], anim[3]));
        a[3] = (new Animations(15, anim[12], anim[13], anim[14], anim[15]));
    }

    public void tick() {
        a[direction - 1].runAnimations();
        moveStep();
        if (Physics.Collision(this, Spielfeld.field.mssl)) {

            if (Physics.hitBy instanceof MelonM) {
                Physics.damageSurrounds(this, Spielfeld.field.zombs, Physics.hitBy);
            }
            this.health -= Physics.hitBy.value;
            Spielfeld.k.removeMissile(Physics.hitBy);
            if (this.health <= 0) {
                Spielfeld.k.killZombie(this);
            }
        }
    }

    public void render(Graphics g) {
        a[direction - 1].drawAnimations(g, x, y, 0);
    }

    protected void moveStep() {
        int ax = this.x;
        int ay = this.y;

        do {
            this.x = Spielfeld.w.getNextX(x, y, speed, direction, size);
            this.y = Spielfeld.w.getNextY(x, y, speed, direction, size);

            if (ax == this.x && ay == this.y) {
                direction = Spielfeld.w.getNextDirection(x, y, size);
                if (direction == 0) {
                    break;
                }
            }

        } while (ax == this.x && ay == this.y);

        if (reached()) {
            Spielfeld.h.adjust(-health);
            Spielfeld.k.removeZombie(this);
        }
    }

    boolean reached() {
        return (y > Spielfeld.HEIGHT);
    }
}

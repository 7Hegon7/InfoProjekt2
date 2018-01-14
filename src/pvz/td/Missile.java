package pvz.td;

public class Missile extends Entities {

    protected int value;
    protected int speed;
    protected Zombies aim;
    private double memM, memN;
    private int dir;

    public Missile(Zombies z) {
        this.aim = z;
    }

    public void tick() {
        if (aim.health > 0) {

            if (x == aim.x) {
                if (aim.y < y) {
                    y -= speed;
                } else if (aim.y > y) {
                    y += speed;
                }
            } else if (y == aim.y) {
                if (aim.x < x) {
                    x -= speed;
                } else {
                    x += speed;
                }
            } else {
                double m = (aim.y - this.y) / (aim.x - (double) this.x);
                int n = (int) (aim.y - m * aim.x);
                if (aim.x < x) {
                    x -= speed * Math.cos(Math.atan(m));
                    dir = -1;
                } else {
                    x += speed * Math.cos(Math.atan(m));
                    dir = 1;
                }
                y = (int) (m * x) + n;
                memN = n;
                memM = m;
            }

        } else {
            x += dir * speed;
            y = (int) (memM * x + memN);
            if (x > Spielfeld.WIDTH || x < 0 - size || y > Spielfeld.HEIGHT || y < 0 - size) {
                Spielfeld.k.removeMissile(this);
            }
        }
    }
}

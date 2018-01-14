package pvz.td;

import java.util.ArrayList;

public class Weg {

    private ArrayList<WayArea> area = new ArrayList<>();

    public Weg() {
        area.add(new WayArea(-88, 973, 108, 153, 4));
        area.add(new WayArea(931, 973, 153, 268, 3));
        area.add(new WayArea(283, 930, 231, 268, 4));
        area.add(new WayArea(283, 326, 268, 682, 1));
        area.add(new WayArea(327, 715, 640, 682, 2));
        area.add(new WayArea(670, 715, 431, 640, 1));
        area.add(new WayArea(715, 1007, 431, 478, 4));
        area.add(new WayArea(962, 1007, 478, 880, 0));
    }

    public int getNextX(int x, int y, int speed, int dir, int size) {
        y += size;
        x += size / 2;
        final int epsilon = 20;
        switch (dir) {
            case 1:
                if (onWay(x + speed + epsilon, y)) {
                    x += speed;
                }
                break;
            case 3:
                if (onWay(x - speed - epsilon, y)) {
                    x -= speed;
                }
                break;
            case 2:
            case 4:
        }
        return x - size / 2;

    }

    public int getNextY(int x, int y, int speed, int dir, int size) {
        y += size;
        x += size / 2;
        final int epsilon = 20;
        switch (dir) {
            case 2:
                if (onWay(x, y - speed - epsilon)) {
                    y -= speed;
                }
                break;
            case 4:
                if (onWay(x, y + speed + epsilon)) {
                    y += speed;
                }
                break;
            case 1:
            case 3:
        }
        return y - size;
    }

    public int getNextDirection(int x, int y,int size) {
        y += size;
        x += size / 2;
        for (int i = 0; i < area.size(); i++) {
            if (area.get(i).inArea(x, y)) {
                return area.get(i).getNextDirection();
            }
        }

        return 1;
    }

    public boolean onWay(int x, int y) {
        for (int i = 0; i < area.size(); i++) {
            if (area.get(i).inArea(x, y)) {
                return true;
            }
        }
        return false;
    }
}

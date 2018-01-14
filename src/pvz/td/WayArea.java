package pvz.td;

public class WayArea {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int nextDirection;

    public WayArea(int x1, int x2, int y1, int y2,int dir) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        nextDirection=dir;
    }

    public boolean inArea(int x, int y) {

        return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
    }
    
    public int getNextDirection()
    {
        return nextDirection;
    }
}

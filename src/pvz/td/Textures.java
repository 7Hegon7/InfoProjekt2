package pvz.td;

import java.awt.image.BufferedImage;

public class Textures {

    private Sprite s = null;
    private Sprite z = null;
    private Sprite n = null;
    public BufferedImage iceshroom, peashooter, sunflower, melon, pea, melonM;
    public BufferedImage[] gnums = new BufferedImage[10];
    public BufferedImage[] hnums = new BufferedImage[10];
    public BufferedImage[] zombie = new BufferedImage[16];
    public BufferedImage[] football = new BufferedImage[16];
    public BufferedImage[] disco = new BufferedImage[16];
    public BufferedImage[] ballon = new BufferedImage[16];

    public Textures(Spielfeld game) {
        s = new Sprite(game.getSpriteSheet());
        n = new Sprite(game.getNums());
        z = new Sprite(game.getZombieSheet());
        getTextures();
    }

    private void getTextures() {
        peashooter = s.grabImage(1, 1, 88, 88);
        sunflower = s.grabImage(2, 1, 88, 88);
        iceshroom = s.grabImage(3, 1, 88, 88);
        melon = s.grabImage(4, 1, 88, 88);
        for (int i = 0; i < 4; i++) {
            zombie[i] = z.grabImage(1, (2 * i) + 1, 88, 176);
            zombie[i + 4] = z.grabImage(2, (2 * i) + 1, 88, 176);
            zombie[i + 12] = z.grabImage(3, (2 * i) + 1, 88, 176);
            zombie[i + 8] = z.grabImage(4, (2 * i) + 1, 88, 176);
            disco[i] = z.grabImage(5, (2 * i) + 1, 88, 176);
            disco[i + 4] = z.grabImage(6, (2 * i) + 1, 88, 176);
            disco[i + 12] = z.grabImage(7, (2 * i) + 1, 88, 176);
            disco[i + 8] = z.grabImage(8, (2 * i) + 1, 88, 176);
            football[i] = z.grabImage(9, (2 * i) + 1, 88, 176);
            football[i + 4] = z.grabImage(10, (2 * i) + 1, 88, 176);
            football[i + 12] = z.grabImage(11, (2 * i) + 1, 88, 176);
            football[i + 8] = z.grabImage(12, (2 * i) + 1, 88, 176);
            ballon[i] = z.grabImage(13, (2 * i) + 1, 88, 176);
            ballon[i + 4] = z.grabImage(15, (2 * i) + 1, 88, 176);
            ballon[i + 12] = z.grabImage(17, (2 * i) + 1, 88, 176);
            ballon[i + 8] = z.grabImage(19, (2 * i) + 1, 88, 176);
        }
        pea = s.grabImage(5, 3, 15, 15);
        melonM = s.grabImage(5, 4, 35, 35);
        for (int i = 0; i <= 9; i++) {
            gnums[i] = n.grabMyNumber(i + 1, 1, 36, 36);
            hnums[i] = n.grabMyNumber(i + 1, 2, 36, 36);
        }
    }

}

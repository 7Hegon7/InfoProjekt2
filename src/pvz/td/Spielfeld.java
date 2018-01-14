package pvz.td;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Spielfeld extends Canvas implements Runnable {

    public static final int WIDTH = 1270;
    public static final int HEIGHT = WIDTH / 16 * 9 - 1;
    public static final int SCALE = 1;
    public static final int HEALTH = 1000;
    public static final int MONEY = 500;
    public static final String TITLE = "Plants vs. Zombies - TowerDefense";

    static Spielfeld field;
    private Thread thread;
    Textures t;

    public static Kontrolle k;
    public static Health h;
    public static Light l;
    public static Wave wv;
    public static Weg w;

    private BufferedImage spriteS = null;
    private BufferedImage zombieS = null;
    private BufferedImage numbers = null;
    private BufferedImage verlauf = null;
    private BufferedImage background = null;

    private boolean running = false;
    private boolean wave = false;
    private boolean taken = false;

    public ArrayList<Zombies> zombs;
    public ArrayList<Missile> mssl;

    Plants lastCreatedPlant;
    Zombies lastCreatedZombie;

    private int wavetick = 0;
    public int waveNr = 0;

    public static void main(String[] args) {
        field = new Spielfeld();

        field.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        field.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        field.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        JFrame frame = new JFrame(TITLE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Spielfeld.class.getResource("/PvZTD.png")));
        frame.add(field);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        field.start();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread((Runnable) this);
        thread.start();
    }

    public void run() {
        init();
        long prev = System.nanoTime();
        final double ticks = 60.0;
        double ns = 1000000000 / ticks;
        double delta = 0;
        int ref = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - prev) / ns;
            prev = now;
            if (delta >= 1) {
                tick();
                ref++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(ref + " Ticks, FPS " + frames);
                ref = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void init() {
        ImageLoad loader = new ImageLoad();
        this.addMouseListener(new Maus(this));
        this.addMouseMotionListener(new Maus(this));
        try {
            spriteS = loader.loadImage("/Sprites.png");
            zombieS = loader.loadImage("/Zombies.png");
            numbers = loader.loadImage("/Numbers.png");
            verlauf = loader.loadImage("/Weg.png");
            background = loader.loadImage("/background.png");
        } catch (IOException e) {
            System.out.println("NO IMAGE");
            //wenn ein Bild fehlt
        }

        t = new Textures(this);
        w = new Weg();
        k = new Kontrolle(this);
        h = new Health(t);
        l = new Light(t);
        wv = new Wave(t);
        zombs = k.getZombies();
        mssl = k.getMissiles();
    }

    private void tick() {
        k.tick();
        if (wave) {

            if (wavetick == 50) {
                k.addEntity(new Zombie(t));
            }
            if (wavetick == 200) {
                k.addEntity(new Disco(t));
                wavetick = 0;
                wave = false;
            }
            wavetick += 1;
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        /////////////////////////////////////////////

        g.drawImage(background, 0, 0, null);
        l.render(g);
        h.render(g);
        k.render(g);
        wv.render(g);

        /////////////////////////////////////////////
        g.dispose();
        bs.show();

    }

    public BufferedImage getSpriteSheet() {
        return spriteS;
    }

    public BufferedImage getZombieSheet() {
        return zombieS;
    }

    public BufferedImage getNums() {
        return numbers;
    }

    public BufferedImage getWeg() {
        return verlauf;
    }
    
    public Textures getTextures(){
        return t;
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + " " + y);
        //check Go-Knopf
        if (x > 16 && x < 170 && y > 618 && y < 701) {
            wave = true;
            wv.adjust(1);
        } else // check Pflanzendrag
        if (x <= 1266 && x >= 1107) {
            if (y <= 169 && y >= 12) {
                lastCreatedPlant = new Peashooter(t);
            }
            if (y <= 528 && y >= 370) {
                lastCreatedPlant = new Iceshroom(t);
            }
            if (y <= 350 && y >= 192) {
                lastCreatedPlant = new Sunflower(t);
            }
            if (y <= 708 && y >= 549) {
                lastCreatedPlant = new Melon(t);
            }
            if (l.value >= lastCreatedPlant.prize) {
                l.adjust(-lastCreatedPlant.prize);
                lastCreatedPlant.setXandY(x, y);
                taken = true;
                k.addEntity(lastCreatedPlant);
            } else {
                k.deletePlant(lastCreatedPlant);
                l.adjust(-lastCreatedPlant.prize);
                taken = false;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (taken) {
            lastCreatedPlant.setXandY(x, y);
            if (x > 1010 || x < 0 || y < 0 || y > 640 || w.onWay(lastCreatedPlant.getX() + lastCreatedPlant.size / 2, lastCreatedPlant.getY() + lastCreatedPlant.size)) {
                k.deletePlant(lastCreatedPlant);
            }
        }
        taken = false;
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (taken) {
            lastCreatedPlant.setXandY(x, y);
        }
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Spielfeld.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(1);
    }
}

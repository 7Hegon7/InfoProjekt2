package pvz.td;

import java.awt.Color;
import static java.awt.Color.BLACK;

public class WegSheet {

    public void calculate() {
        boolean lastPxl = false;
        for (int x = 1; x <= 1280; x++) {
            for (int y = 1; y <= 720; y++) {
                boolean colorised = new Color(Spielfeld.field.getWeg().getRGB(x, y)) == BLACK;
                if (colorised && !lastPxl) {
                    wayEntered(x, y);
                }
                if (!colorised && !lastPxl) {
                    wayLeft(x, y);
                }
                lastPxl = colorised;
            }
            lastPxl = false;
        }

    }

    private void wayEntered(int x, int y) {

    }

    private void wayLeft(int x, int y) {

    }
}

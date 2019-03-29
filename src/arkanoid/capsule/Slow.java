package arkanoid.capsule;

import java.awt.Graphics;
import java.awt.*;

public class Slow extends Capsule {

    Slow(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    CAPSULES effect() {

        return CAPSULES.SLOW;
    }

    @Override
    public void tick() {
        y += 4;
    }

    @Override
    public void render(Graphics g) {
        //
    }
}

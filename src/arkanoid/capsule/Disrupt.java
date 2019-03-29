package arkanoid.capsule;

import java.awt.Graphics;
import java.awt.*;


public class Disrupt extends Capsule {

    Disrupt(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    CAPSULES effect() {

        return CAPSULES.DISRUPT;
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

package arkanoid.capsule;

import java.awt.Graphics;
import java.awt.*;

public class Vaus extends Capsule {

    Vaus(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    CAPSULES effect() {

        return CAPSULES.VAUS;
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

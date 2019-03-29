package arkanoid.capsule;

import java.awt.Graphics;
import java.awt.*;

public class Catch extends Capsule {

    Catch(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    CAPSULES effect() {

        return CAPSULES.CATCH;
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

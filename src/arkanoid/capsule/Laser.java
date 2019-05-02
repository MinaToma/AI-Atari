package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Laser capsule enables the paddle weaponry.
 */
public class Laser extends Capsule {

    public Laser(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.runLaser();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {
        p.stopLaser();
    }
}

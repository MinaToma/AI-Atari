package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Slow capsule slows down all balls in the frame.
 */
public class Slow extends Capsule {

    public Slow(int x, int y, int life , Image image) {
        super(x, y, life , image);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.speedDown();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {

    }
}

package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Fast capsule speeds up all balls in the frame.
 */
public class Fast extends Capsule {

    public Fast(int x, int y, int life, Image image) {
        super(x, y, life, image);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.speedUp();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {

    }
}

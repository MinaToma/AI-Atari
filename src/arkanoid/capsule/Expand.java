package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Expand capsule expands the acquiring paddle.
 */
public class Expand extends Capsule {

    public Expand(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.expand();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {
        p.paddleNormal();
    }
}

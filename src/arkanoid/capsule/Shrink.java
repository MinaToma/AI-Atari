package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Shrink capsule shrinks the acquiring paddle.
 */
public class Shrink extends Capsule {
    public Shrink(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.shrink();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {
        p.paddleNormal();
    }
}
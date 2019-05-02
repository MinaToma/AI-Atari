package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Acid capsule enables the ball to go through any brick without colliding.
 */
public class Acid extends Capsule {

    public Acid(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.makeAcidBall();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {
        p.makeNormalBall();
    }

}
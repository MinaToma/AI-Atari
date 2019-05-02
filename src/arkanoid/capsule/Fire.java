package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Fire capsule enables the ball to break multiple hit bricks in one hit.
 */
public class Fire extends Capsule {

    public Fire(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.makeFireBall();
    }

    /**
     * {@inheritDoc}
     */
    public void removeEffect(Paddle p) {
        p.makeNormalBall();
    }
}

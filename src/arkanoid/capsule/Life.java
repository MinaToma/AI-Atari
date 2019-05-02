package arkanoid.capsule;

import arkanoid.board.Paddle;
import java.awt.*;

/**
 * Life capsule increases player's lives by 1.
 */
public class Life extends Capsule {

    public Life(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.increaseLife();
    }
}

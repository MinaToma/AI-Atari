package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Break capsule breaks to the next level.
 */
public class Break extends Capsule {

    public Break(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.breakToNextLevel();

    }

}

package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Disrupt capsule splits the current ball objects into 3 balls each.
 */
public class Disrupt extends Capsule {

    public Disrupt(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.splitBall();
    }



}

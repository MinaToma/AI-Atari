package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

/**
 * Catch capsule sticks the ball onto the paddle upon collision and enables the player to relaunch it.
 */
public class Catch extends Capsule {

    public Catch(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.sticky();
    }

}

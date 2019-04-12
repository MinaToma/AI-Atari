package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;


public class Laser extends Capsule {

    public Laser(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    @Override
    public void effect(Paddle p) {

        p.runLaser();
    }

    public void unEffect(Paddle p) {
        p.stopLaser();
    }
}

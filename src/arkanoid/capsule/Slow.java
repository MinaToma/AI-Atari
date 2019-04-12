package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

public class Slow extends Capsule {


    public Slow(int x, int y, int life , Image image) {
        super(x, y, life , image);

    }

    @Override
    public void effect(Paddle p) {

        p.speedDown();
    }


    public void unEffect(Paddle p) {

    }
}

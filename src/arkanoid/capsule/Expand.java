package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

public class Expand extends Capsule {

    public Expand(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    @Override
    public void effect(Paddle p) {

        p.expand();
    }

    public void unEffect(Paddle p) {
        p.paddleNormal();
    }
}

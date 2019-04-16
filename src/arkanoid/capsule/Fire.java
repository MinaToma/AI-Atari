package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

public class Fire extends Capsule {

    public Fire(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    @Override
    public void effect(Paddle p) {

        p.makeFireBall();
    }

    public void removeEffect(Paddle p) {
        p.makeNormalBall();
    }
}

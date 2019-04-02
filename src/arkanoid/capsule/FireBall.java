package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;

public class FireBall extends Capsule {

    public FireBall(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    @Override
    public void effect(Paddle p) {

        p.makeFireBall();
    }
}

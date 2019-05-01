package arkanoid.capsule;

import arkanoid.arkHelper;
import arkanoid.board.Paddle;
import atariCore.Handler;

import java.awt.*;
import static arkanoid.ObjectList.paddleList;
import static arkanoid.arkHelper.INIT_PADDLE_X;
import static arkanoid.arkHelper.INIT_PADDLE_Y;

public class Vaus extends Capsule {

    public Vaus(int x, int y, int life, Image image) {
        super(x, y, life, image);
    }

    @Override
    public void effect(Paddle p) {
        if(paddleList.size() > 2) return;
        p = new Paddle(INIT_PADDLE_X, INIT_PADDLE_Y, arkHelper.paddle[0], 0, 0, p.getPlayer());
        Handler.getInstance().addObject(paddleList, p);
    }

    public void removeEffect(Paddle p) {

    }
}

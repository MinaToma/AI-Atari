package arkanoid.capsule;

import arkanoid.board.Paddle;
import atariCore.BaseObject;
import javafx.util.Pair;
import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.INACTIVE;

import java.awt.*;

import static arkanoid.arkHelper.paddleSpeed;

public abstract class Capsule extends BaseObject {

    Paddle p;
    int life;

    Capsule(int x, int y, int life , Image image) {
        super(x, y, image);
        this.life = life;
    }

    public abstract void effect(Paddle p);

    public boolean hit() {
        life--;
        return life == 0;
    }

    @Override
    public void tick() {

        y += paddleSpeed;
    }

    @Override
    public void render(Graphics g) {


        g.drawImage(super.img, super.x, super.y, null);
    }
}

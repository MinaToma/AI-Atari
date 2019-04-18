package arkanoid.capsule;

import arkanoid.arkHelper;
import arkanoid.board.Paddle;
import atariCore.BaseObject;
import java.awt.*;
import java.io.IOException;

import static arkanoid.arkHelper.*;

public abstract class Capsule extends BaseObject {

    Paddle p;
    int life;

    public Capsule(int x, int y, int life , Image image) {
        super(x, y, image);
        this.life = life;
    }

    public abstract void effect(Paddle p) throws IOException;

    public boolean hit() {
        life--;
        if(y > arkHelper.screenHeight) life = 0;
        return life == 0;
    }

    @Override
    public void tick() {

        y += capsuleSpeed;
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(super.img, (int)super.x, (int)super.y, null);
    }
}

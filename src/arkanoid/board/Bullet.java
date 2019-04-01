package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static arkanoid.arkHelper.*;

public class Bullet extends BaseObject {

    Handler handler;
    public Bullet(int x, int y, Image img , Handler handler) {
        super(x, y, img);
        this.handler = handler;
    }

    @Override
    public void tick() {

        y -= capsuleSpeed;

        collision();
    }

    private void collision() {

        int calcScore = 0;

        boolean dead = false;

        for (BaseObject o : handler.getObject()) {

            if ((o instanceof Brick || o instanceof Enemy)) {
                if (o.getRectangle().intersects(getRectangle())) {

                    dead = true;
                    calcScore++;

                    if(((Brick) o).hit()) {
                        handler.removeObject(o);
                        if(((Brick) o).capsule != null) {
                            handler.addObject(((Brick) o).capsule);
                        }
                    }
                }
            }
        }

        if(dead)
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(super.img, super.x, super.y, null);
    }
}

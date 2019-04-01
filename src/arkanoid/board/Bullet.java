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
    public Bullet(float x, float y, Image img , Handler handler) {
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
                    System.out.println(((Brick)o).getPower());
                    if(((Brick) o).hit() && o.getY()>=0 ) {

                        if(((Brick) o).capsule != null) {

                            Capsule capsule = ((Brick) o).capsule;
                            capsule.setX(o.getX());
                            capsule.setY(o.getY());
                            handler.addObject(capsule);
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
        g.drawImage(super.img, (int)super.x, (int)super.y, null);
    }
}

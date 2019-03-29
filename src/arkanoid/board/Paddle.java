

package arkanoid.board;

import arkanoid.Common;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Paddle extends BaseObject {

    public Handler handler;


    public Paddle(int xPostion, int yPostion, Image image, int velX, int velY , Handler handler) {
        super(xPostion, yPostion, image, velX, 0);
        this.handler = handler;
    }

    public void tick() {
        super.x += super.velX;

        if (super.x <= 0)
            x = 0;
        if (super.x >= Common.WIDTH - imageWidth)
            x = Common.WIDTH - imageWidth;

        collision();

    }

    private void collision() {

        ArrayList<BaseObject> object = new ArrayList<>();

        for (int i = 0; i < handler.object.size(); i++) {
            BaseObject baseObject = handler.object.get(i);
            if (baseObject instanceof Capsule) {
                if (baseObject.getRectangle().intersects(super.getRectangle())) {
                    // han3ml 7aga hena
                    object.add(baseObject);
                }
            }
            if (baseObject instanceof Ball) {
                if (baseObject.getRectangle().intersects(super.getRectangle())) {
                    baseObject.setVelY(baseObject.getVelY() * -1);
                }
            }


        }
        for (BaseObject bas : object) {
            handler.object.remove(bas);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 50, 50);
        //g.drawImage(super.img, super.x ,super.y, null);
    }

    public void moveRight() {
        x += 5;
    }

    public void moveLeft() {
        x -= 5;
    }
}




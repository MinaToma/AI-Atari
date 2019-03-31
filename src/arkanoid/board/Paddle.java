

package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static arkanoid.arkHelper.*;

public class Paddle extends BaseObject {

    public Handler handler;

    public Paddle(int xPosition, int yPosition, Image image, int velX, int velY, Handler handler) {

        super(xPosition, yPosition, image, velX, 0);
        this.handler = handler;
    }

    public void tick() {
        x += velX;

        collision();
        clamp();
    }

    private void collision() {

        ArrayList<BaseObject> object = new ArrayList<>();

        for (BaseObject o : handler.getObject()) {
            if (o instanceof Capsule) {
                if (o.getRectangle().intersects(getRectangle())) {
                    // han3ml 7aga hena

                }
            }

            if (o instanceof Ball) {
                if (o.getRectangle().intersects(getRectangle())) {

                    int paddleLPos = (int) getRectangle().getMinX();
                    int ballLPos = (int) o.getRectangle().getMinX();

                    int first = paddleLPos + 8;
                    int second = paddleLPos + 16;
                    int third = paddleLPos + 24;
                    int fourth = paddleLPos + 32;

                    if (ballLPos < first) {

                        o.setVelX(-xSpeed);
                        o.setVelY(-ySpeed);
                    }

                    if (ballLPos >= first && ballLPos < second) {

                        o.setVelX(-xSpeed);
                        o.setVelY(-1 * o.getVelY());
                    }

                    if (ballLPos >= second && ballLPos < third) {

                        o.setVelX(0);
                        o.setVelY(-ySpeed);
                    }

                    if (ballLPos >= third && ballLPos < fourth) {

                        o.setVelX(xSpeed);
                        o.setVelY(-1 * o.getVelY());
                    }

                    if (ballLPos > fourth) {

                        o.setVelX(xSpeed);
                        o.setVelY(-ySpeed);
                    }
                }
            }

            object.add(o);
        }

        handler.object = object;
    }

    public void render(Graphics g) {

        g.drawImage(super.img, super.x, super.y, null);
    }
}




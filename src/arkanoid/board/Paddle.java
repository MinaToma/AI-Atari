

package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;
import javafx.util.Pair;

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
                if (o.getRectangle().intersects(getRectangle()) && o.getY() + o.getImageHeight() / 2 < y) {

                    o.setVelY(o.getVelY() * -1);

                    int dir = (o.getVelX() >= 0) ? 1 : -1;

                    if ( (o.getVelX() > 0 && getVelX() >= 0) || (o.getVelX() < 0 && getVelX() <= 0)) {
                        o.setVelX(dir * getNewVx(o.getX() + o.getImageWidth() / 2));
                    }
                    else {
                        o.setVelX(-dir * getNewVx(o.getX() + o.getImageWidth() / 2));
                    }

                    System.out.println(o.getVelX());
                }
            }

            object.add(o);
        }

        handler.object = object;
    }

    public int getNewVx(int currX) {
        int newVX = xSpeed;

        int q1 =  x + getImageWidth() / 5;
        int q2 = q1 + getImageWidth() / 5;
        int q3 = q2 + getImageWidth() / 5;
        int q4 = q3 + getImageWidth() / 5;
        int q5 = q4 + getImageWidth() / 5;

        System.out.println(currX);
        System.out.println(q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5);

        if(currX < q1 || currX >= q5) newVX = xSpeed + 2;
        else if(currX >= q1 && currX < q2) newVX = xSpeed + 2;
        else if(currX >= q2 && currX < q3) newVX = xSpeed;
        else if(currX >= q3 && currX < q4) newVX = xSpeed;
        else if(currX >= q4 && currX < q5) newVX = xSpeed + 2;

        return newVX;
    }

    public void render(Graphics g) {

        g.drawImage(super.img, super.x, super.y, null);
    }
}




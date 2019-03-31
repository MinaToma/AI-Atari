package arkanoid.board;

import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Ball extends BaseObject {


    Handler handler;
    int offset = 2;

    public Ball(int xPostion, int yPostion, Image image, int xVelocity, int yVelocity, Handler handler) {
        super(xPostion, yPostion, image, xVelocity, yVelocity);
        this.handler = handler;
    }

    public void move() {

    }

    @Override
    public void tick() {
        y += velY;
        x += velX;

        collision();
        clamp();
    }

    @Override
    public void clamp() {

        if (arkHelper.screenWidth <= x + getImageWidth() || x <= 0) velX *= -1;
        if (y <= 0) velY *= -1;
    }

    private void collision() {

        int calcScore = 0;
        ArrayList<BaseObject> object = new ArrayList<>();
        boolean reflected = false;

        for (BaseObject o : handler.getObject()) {
            if ((o instanceof Brick || o instanceof Enemy) && !reflected) {
                if (o .getRectangle().intersects(getRectangle())) {
                    calcScore++;
                    ((Brick) o).hit();

                    int pow = ((Brick) o).getPower();
                    if (pow != 0) {

                        object.add(o);
                    }

                    int hitRight = Math.abs(x - (o.getX() + o.getImageWidth()) );
                    int hitLeft = Math.abs(x + getImageWidth() - o.getX() );
                    int hitDown = Math.abs(y - (o.getY() + o.getImageHeight()) );
                    int hitUp = Math.abs(y + getImageHeight() - o.getY());

                    if((hitLeft < offset && velX > 0) || (hitRight < offset && velX < 0) ) setVelX(velX * -1);
                    if((hitUp   < offset && velY > 0) || (hitDown  < offset && velY < 0) ) setVelY(velY * -1);

                    reflected = true;
                    continue;
                }
            }

            object.add(o);
        }

        handler.object = object;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ball extends BaseObject {


    Handler handler;
    int xOffset = 3;
    int yOffset = 3;

    public Ball(int xPosition, int yPosition, Image image, int xVelocity, int yVelocity, Handler handler) {
        super(xPosition, yPosition, image, xVelocity, yVelocity);
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
        if (y > arkHelper.screenHeight) handler.removeObject(this);
    }

    private void collision() {

        int calcScore = 0;

        boolean reflected = false;

        for (BaseObject o : handler.getObject()) {
            if ((o instanceof Brick || o instanceof Enemy) && !reflected) {
                if (o.getRectangle().intersects(getRectangle())) {
                    calcScore++;

                    if( o instanceof Brick)
                    {
                        ((Brick)o).hit();
                        if(((Brick)o).getPower()<=0)
                        {
                            if( ((Brick)o).capsule != null ){

                                Capsule capsule = ((Brick)o).capsule;
                                capsule.setX(o.getX());
                                capsule.setY(o.getY());


                                handler.addObject(capsule);
                            }
                            handler.removeObject(o);
                        }

                    }

                    int hitRight = Math.abs(x - (o.getX() + o.getImageWidth()));
                    int hitLeft = Math.abs(x + getImageWidth() - o.getX());
                    int hitDown = Math.abs(y - (o.getY() + o.getImageHeight()));
                    int hitUp = Math.abs(y + getImageHeight() - o.getY());

                    if ((hitLeft < xOffset && velX > 0) || (hitRight < xOffset && velX < 0)) setVelX(velX * -1);
                    if ((hitUp < yOffset && velY > 0) || (hitDown < yOffset && velY < 0)) setVelY(velY * -1);


                    //System.out.println(hitLeft + " " + hitRight + " " + hitUp + " " + hitDown);
                    //System.out.println(o.getX() + " " + (o.getX() + o.getImageWidth()) + " " + o.getY() + " " + (o.getY() + o.getImageHeight()));


                    reflected = true;

                    continue;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;

import static arkanoid.arkHelper.xSpeed;
import static arkanoid.arkHelper.ySpeed;

public class Ball extends BaseObject {


    Handler handler;
    Player player;
    float xOffset = xSpeed * 3 / 2;
    float yOffset = ySpeed * 3 / 2;

    public Ball(float xPosition, float yPosition, Image image, float xVelocity, float yVelocity, Handler handler , Player player) {
        super(xPosition, yPosition, image, xVelocity, yVelocity);
        this.handler = handler;
        this.player = player;
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

        for (BaseObject o : handler.getObject()) {
            if ((o instanceof Brick || o instanceof Enemy)) {
                if (o.getRectangle().intersects(getRectangle())) {
                    calcScore++;

                    if( o instanceof Brick)  {
                        if(((Brick)o).hit() && o.getY()>=0) {

                            if( ((Brick)o).capsule != null ){

                                Capsule capsule = ((Brick)o).capsule;
                                capsule.setX(o.getX());
                                capsule.setY(o.getY());

                                handler.addObject(capsule);
                            }
                            handler.removeObject(o);
                        }
                    }

                    float hitRight = Math.abs(x - (o.getX() + o.getImageWidth()));
                    float hitLeft = Math.abs(x + getImageWidth() - o.getX());
                    float hitDown = Math.abs(y - (o.getY() + o.getImageHeight()));
                    float hitUp = Math.abs(y + getImageHeight() - o.getY());

                    if ((hitLeft < xOffset && velX > 0) || (hitRight < xOffset && velX < 0)) setVelX(velX * -1);
                    else if ((hitUp < yOffset && velY > 0) || (hitDown < yOffset && velY < 0)) setVelY(velY * -1);

                    //System.out.println(hitLeft + " " + hitRight + " " + hitUp + " " + hitDown);
                    //System.out.println(o.getX() + " " + (o.getX() + o.getImageWidth()) + " " + o.getY() + " " + (o.getY() + o.getImageHeight()));

                    break;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
    }
}
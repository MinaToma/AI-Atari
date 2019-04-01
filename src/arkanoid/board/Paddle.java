

package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static arkanoid.arkHelper.*;

public class Paddle extends BaseObject {

    public CopyOnWriteArrayList<Capsule> capsules;
    public Handler handler;
    public boolean sticky, laser;
    int normalImageIdx = 0;

    public Paddle(int xPosition, int yPosition, Image image, int velX, int velY, Handler handler) {

        super(xPosition, yPosition, image, velX, 0);
        this.handler = handler;
        capsules = new CopyOnWriteArrayList<>();
    }

    public void tick() {

        if(img != paddleExpanded) {
            updateNormalImage();
        }

        if(laser) {
            updateLaserImage();
        }

        x += velX;

        collision();
        clamp();
    }

    private void updateLaserImage() {
        img = paddleWeapon[normalImageIdx++];
        normalImageIdx %= 3;
    }

    public void splitBall() {

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Ball) {

                Ball ball = (Ball)o;
                ball.setVelX(0);

                Ball newBallL = new Ball(ball.getX() , ball.getY() , ball.getImg() , xSpeed , ball.getVelY() , handler);
                Ball newBallR = new Ball(ball.getX() , ball.getY() , ball.getImg() , -xSpeed , ball.getVelY(), handler);

                handler.addObject(newBallL);
                handler.addObject(newBallR);

                break;
            }
        }
    }

    public void updateLaser() {

        laser = true;
    }

    public void expand() {

        this.img = paddleExpanded;
        this.setImageHeight(paddleExpanded.getHeight(null));
        this.setImageWidth(paddleExpanded.getWidth(null));
    }

    private void updateCapsules() {

        for (Capsule c : capsules) {

            c.effect(this);
            if (!c.hit()) {
                capsules.remove(c);
            }
        }
    }

    private void updateNormalImage() {

        img = paddle[normalImageIdx++];
        normalImageIdx %= 3;
    }

    public void hitLaser() {

        updateCapsules();
        Bullet bulletL = new Bullet(x , y , bullet , handler);
        Bullet bulletR = new Bullet(x + getImageWidth() - getImageWidth() * 3 / 100 , y , bullet , handler);

        handler.addObject(bulletL);
        handler.addObject(bulletR);
    }

    public void speedUp() {
        velX = paddleSpeed;
    }

    public void speedDown() {
        velX = paddleSpeed - 1;
    }

    private void collision() {

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Capsule) {

                if (o.getRectangle().intersects(getRectangle())) {

                    System.out.println("HIH");
                    capsules.add(((Capsule) o));
                    updateCapsules();
                    handler.removeObject(o);
                }
            }

            if (o instanceof Ball) {
                if (o.getRectangle().intersects(getRectangle()) && o.getY() + o.getImageHeight() / 2 < y) {

                    if (!sticky) {
                        o.setVelY(ySpeed * -1);
                    }
                    else {
                        o.setVelY(0);
                    }

                    int dir = (o.getVelX() >= 0) ? 1 : -1;

                    if ((o.getVelX() > 0 && getVelX() >= 0) || (o.getVelX() < 0 && getVelX() <= 0)) {
                        o.setVelX(dir * getNewVx(o.getX() + o.getImageWidth() / 2));
                    } else {
                        o.setVelX(-dir * getNewVx(o.getX() + o.getImageWidth() / 2));
                    }

                    //System.out.println(o.getVelX());
                }
            }
        }
    }

    public int getNewVx(int currX) {

        int newVX = xSpeed;

        int q1 = x + getImageWidth() / 5;
        int q2 = q1 + getImageWidth() / 5;
        int q3 = q2 + getImageWidth() / 5;
        int q4 = q3 + getImageWidth() / 5;
        int q5 = q4 + getImageWidth() / 5;

        //System.out.println(currX);
        //System.out.println(q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5);

        if (currX < q1 || currX >= q5) newVX = xSpeed + 3;
        else if (currX < q2) newVX = xSpeed + 2;
        else if ( currX < q3) newVX = xSpeed;
        else if (currX < q4) newVX = xSpeed;
        else newVX = xSpeed + 2;

        return newVX;
    }

    public void render(Graphics g) {

        g.drawImage(super.img, super.x, super.y, null);
    }


}




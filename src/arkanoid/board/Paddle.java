

package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;


import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.concurrent.CopyOnWriteArrayList;

import static arkanoid.arkHelper.*;

public class Paddle extends BaseObject {

    public CopyOnWriteArrayList<Capsule> capsules;
    public Handler handler;
    public boolean sticky = false , laser = true , shrink, expand;
    private int normalImageIdx = 0;
    private Player player;

    public Paddle(int xPosition, int yPosition, Image image, float velX, float velY, Handler handler, Player player) {

        super(xPosition, yPosition, image, velX, 0);
        this.handler = handler;
        this.player = player;
        capsules = new CopyOnWriteArrayList<>();

    }

    public void tick() {

        x += velX;

        updateImage();
        collision();
        clamp();
    }

    public void splitBall() {

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Ball) {

                Ball ball = (Ball) o;
                ball.setVelX(0);

                Ball newBallL = new Ball(ball.getX(), ball.getY(), ball.getImg(), xBallSpeed, ball.getVelY(), handler, player);
                Ball newBallR = new Ball(ball.getX(), ball.getY(), ball.getImg(), -xBallSpeed, ball.getVelY(), handler, player);

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

        expand = true;
        shrink = false;
    }

    private void updateCapsules() {

        for (Capsule c : capsules) {

            c.effect(this);
            if (!c.hit()) {
                capsules.remove(c);
            }
        }
    }

    public void hitLaser() {

        updateCapsules();
        Bullet bulletL = new Bullet(x, y, bullet, handler);
        Bullet bulletR = new Bullet(x + getImageWidth() - getImageWidth() * 3 / 100, y, bullet, handler);

        handler.addObject(bulletL);
        handler.addObject(bulletR);
        lazerSound();
    }

    public void speedUp() {

        xBallSpeed = Math.min(4, xBallSpeed + 0.5f);
        yBallSpeed = Math.min(-4, yBallSpeed - 0.5f);
    }

    public void speedDown() {

        xBallSpeed = Math.max(1, xBallSpeed - 0.5f);
        yBallSpeed = Math.max(-1, yBallSpeed + 0.5f);
    }

    private void collision() {

        boolean checkIfBricksHeight1 = false, checkIfBricksHeight2 = false;
        int numOfBricks = 0;
        for (BaseObject o : handler.getObject()) {

            if (o instanceof Capsule) {

                if (o.getRectangle().intersects(getRectangle())) {

                    capsules.add(((Capsule) o));
                    updateCapsules();
                    handler.removeObject(o);
                }
            }

            if (o instanceof Ball) {
                if (o.getRectangle().intersects(getRectangle()) && o.getY() + o.getImageHeight() / 2 < y) {

                   // System.out.println(sticky);
                    if (sticky) {

                        o.setVelX(0);
                        o.setVelY(0);
                        o.setY(INIT_BALL_Y);
                        continue;
                    }

                    o.setVelY(yBallSpeed);

                    int dir = (o.getVelX() > 0) ? 1 : -1;

                    if (getVelX() == 0) {

                        o.setVelX(dir * Math.abs(getNewVx(o.getX() + o.getImageWidth() / 2)));
                    }
                    else {

                        dir = (getVelX() < 0) ? -1 : 1;
                        o.setVelX(dir * Math.abs(getNewVx(o.getX() + o.getImageWidth() / 2)));
                    }
                }
            }

            if (o instanceof Brick) {
                numOfBricks++;
                if (o.getY() >= 0) {
                    if (o.getY() >= INIT_BRICKS_HEIGHT) {
                        checkIfBricksHeight1 = true;
                    }
                } else {
                    checkIfBricksHeight2 = true;
                }
                if(o.getY() + o.getImageHeight() >= this.getY())
                {
                    player.die();
                }
            }
        }

        if (checkIfBricksHeight1 == false && checkIfBricksHeight2 == true) {

            for (BaseObject o : handler.getObject()) {
                if (o instanceof Brick) {
                    ((Brick) o).moveDown();
                }
            }
        }
        if (numOfBricks == 0)
        {
            player.setLevel(player.getLevel()+1);
        }

    }

    public float getNewVx(float currX) {

        float distFromCenter = (x + getImageWidth() / 2 - currX);
        distFromCenter /= getImageWidth() / 2;
        float newVX = xBallSpeed * distFromCenter;

        return newVX;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {  this.player = player; }

    public void render(Graphics g) {

        g.drawImage(super.img, (int) super.x, (int) super.y, null);
    }

    public void makeAcidBall() {

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Ball) {

                ((Ball) o).makeAcid();
            }
        }
    }

    public void shrink() {

        expand = false;
        shrink = true;
    }

    public void increaseLife() {

        player.setLives(player.getLives() + 1);
    }

    private void updateImage() {

        if (laser) {

            if (expand) {

                img = paddleExpandedWeapon;
            } else if (shrink) {

                img = paddleShrunk;
            } else {

                img = paddleWeapon[normalImageIdx++];
                normalImageIdx %= 3;
            }
        } else if (expand) {

            img = paddleExpanded;
        } else if (shrink) {

            img = paddleShrunk;
        } else {

            img = paddle[normalImageIdx++];
            normalImageIdx %= 3;
        }

        setSize();
    }

    private void setSize() {

        setImageWidth(img.getWidth(null));
        setImageHeight(img.getHeight(null));
    }

    public void sticky() {

        sticky = true;
    }

    public void makeFireBall() {

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Ball) {

                ((Ball) o).makeFire();
            }
        }
    }
    public void breakToNextLevel()
    {
        player.setLevel(player.getLevel()+1);
    }
}



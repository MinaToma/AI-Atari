

package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Sounds;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static arkanoid.arkHelper.*;

public class Paddle extends BaseObject {

    public CopyOnWriteArrayList<Capsule> capsules;
    public Handler handler;
    public boolean sticky, laser, shrink, expand;
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

                Ball newBallL = new Ball(ball.getX(), ball.getY(), ball.getImg(), xSpeed, ball.getVelY(), handler, player);
                Ball newBallR = new Ball(ball.getX(), ball.getY(), ball.getImg(), -xSpeed, ball.getVelY(), handler, player);

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

    private void updateNormalImage() {

        img = paddle[normalImageIdx++];
        normalImageIdx %= 3;
    }

    public void hitLaser() {

        updateCapsules();
        Bullet bulletL = new Bullet(x, y, bullet, handler);
        Bullet bulletR = new Bullet(x + getImageWidth() - getImageWidth() * 3 / 100, y, bullet, handler);

        handler.addObject(bulletL);
        handler.addObject(bulletR);


    }

    public void speedUp() {

        xSpeed = Math.min(4, xSpeed + 0.5f);
        ySpeed = Math.min(4, ySpeed + 0.5f);
    }

    public void speedDown() {

        xSpeed = Math.max(1, xSpeed - 0.5f);
        ySpeed = Math.max(1, xSpeed - 0.5f);
    }

    private void collision() {
        boolean checkIfBricksHeight1 = false, checkIfBricksHeight2 = false;
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

                    if (sticky) {

                        o.setVelX(0);
                        o.setVelY(0);
                        continue;
                    }

                    o.setVelY(ySpeed * -1);

                    int dir = (o.getVelX() >= 0) ? 1 : -1;

                    if (getVelX() == 0) {

                        o.setVelX(dir * getNewVx(o.getX() + o.getImageWidth() / 2));
                    }
                    else {

                        dir = (getVelX() < 0) ? -1 : 1;
                        o.setVelX(dir * Math.abs(getNewVx(o.getX() + o.getImageWidth() / 2)));
                    }


                    //System.out.println(o.getVelX());
                }
                if (o.getY() >= screenHeight) {
                    player.setLives(player.getLives() - 1);
                    o.setX((this.x + this.getImageWidth() - this.getImageWidth() / 2));
                    o.setY(INIT_BALL_Y);
                    o.setVelX(xSpeed);
                    o.setVelY(ySpeed);
                }
            }
            if (o instanceof Brick) {

                if (o.getY() >= 0) {
                    if (o.getY() >= INIT_BRICKS_HEIGHT) {
                        checkIfBricksHeight1 = true;
                    }
                } else {
                    checkIfBricksHeight2 = true;
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

    }

    public float getNewVx(float currX) {

        float distFromCenter = (x + getImageWidth() / 2 - currX);

        distFromCenter /= getImageWidth() / 2;

        float newVX = xSpeed * distFromCenter;


        /*float q1 = x + getImageWidth() / 5;
        float q2 = q1 + getImageWidth() / 5;
        float q3 = q2 + getImageWidth() / 5;
        float q4 = q3 + getImageWidth() / 5;
        float q5 = q4 + getImageWidth() / 5;

        //System.out.println(currX);
        //System.out.println(q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5);

        if (currX < q1 || currX >= q5) newVX = xSpeed + xSpeed * 25 / 100;
        else if (currX < q2) newVX = xSpeed + xSpeed * 10 / 100;
        else if (currX < q3) newVX = xSpeed * 80 / 100;
        else if (currX < q4) newVX = xSpeed * 80 / 100;
        else newVX = xSpeed + xSpeed * 10 / 100;
*/
        return newVX;
    }

    public Player getPlayer() {
        return player;
    }

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
}



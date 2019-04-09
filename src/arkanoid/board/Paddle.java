

package arkanoid.board;

import arkanoid.Sounds;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Sound;

import java.awt.*;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

public class Paddle extends BaseObject {

    public boolean sticky, laser , shrink, expand;
    private int normalImageIdx = 0;
    private Player player;

    public Paddle(int xPosition, int yPosition, Image image, float velX, float velY , Player player) {

        super(xPosition, yPosition, image, velX, 0);
        this.player = player;
    }

    public void tick() {

        x += velX;

        updateImage();
        collision();
        clamp();
    }

    public void splitBall() {

        for (BaseObject o : ballList) {

            if (o instanceof Ball) {

                Ball ball = (Ball) o;
                ball.setVelX(0);

                Ball newBallL = new Ball(ball.getX(), ball.getY(), ball.getImg(), xBallSpeed, ball.getVelY() , player);
                Ball newBallR = new Ball(ball.getX(), ball.getY(), ball.getImg(), -xBallSpeed, ball.getVelY() , player);

                handler.addObject(ballList, newBallL);
                handler.addObject(ballList, newBallR);

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

        for (BaseObject c : capsuleList) {

            ((Capsule) c).effect(this);
            if (!((Capsule) c).hit()) {
                capsuleList.remove(c);
            }
        }
    }

    public void hitLaser() {

        updateCapsules();
        Bullet bulletL = new Bullet(x, y, bullet);
        Bullet bulletR = new Bullet(x + getImageWidth() - getImageWidth() * 3 / 100, y, bullet);

        handler.addObject(bulletList, bulletL);
        handler.addObject(bulletList, bulletR);
        Sound.play(Sounds.lazerSound);
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

        for (BaseObject o : capsuleList) {

            if (o.getRectangle().intersects(getRectangle())) {

                capsuleList.add(o);
                updateCapsules();
                handler.removeObject(capsuleList, o);
            }
        }


        for (BaseObject o : ballList) {

            if (o.getRectangle().intersects(getRectangle())) {
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
                } else {

                    dir = (getVelX() < 0) ? -1 : 1;
                    o.setVelX(dir * Math.abs(getNewVx(o.getX() + o.getImageWidth() / 2)));
                }
            }
        }

        for (BaseObject o : brickList) {

            if (o.getY() >= 0) {
                if (o.getY() >= INIT_BRICKS_HEIGHT) {
                    checkIfBricksHeight1 = true;
                }
            } else {
                checkIfBricksHeight2 = true;
            }
            if (o.getY() + o.getImageHeight() >= this.getY()) {
                player.die();
            }
        }

        if (checkIfBricksHeight1 == false && checkIfBricksHeight2 == true) {

            for (BaseObject o : brickList) {

                ((Brick) o).moveDown();
            }
        }

        if (numberOfBrics == 0) {
            breakToNextLevel();
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void render(Graphics g) {

        g.drawImage(super.img, (int) super.x, (int) super.y, null);
    }

    public void makeAcidBall() {

        for (BaseObject o : ballList) {

            ((Ball) o).makeAcid();
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

                setImg(paddleExpandedWeapon);
            } else if (shrink) {

                setImg(paddleShrunk);
            } else {

                setImg(paddleWeapon[normalImageIdx++]);
                normalImageIdx %= 3;
            }
        } else if (expand) {

            setImg(paddleExpanded);
        } else if (shrink) {

            setImg(paddleShrunk);
        } else {

            setImg(paddle[normalImageIdx++]);
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

        for (BaseObject o : ballList) {

            ((Ball) o).makeFire();
        }
    }

    public void breakToNextLevel() {

        ballList.clear();
        paddleList.clear();
        bulletList.clear();

        player.setLevel(player.getLevel() + 1);
    }
}



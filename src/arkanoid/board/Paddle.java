package arkanoid.board;

import arkanoid.capsule.*;
import atariCore.BaseObject;
import atariCore.Sound;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

public class Paddle extends BaseObject {

    public boolean sticky, laser, shrink, expand;
    private int normalImageIdx = 0;
    private Player player;
    private CopyOnWriteArrayList<BaseObject> currentCapsuleList;

    public Paddle(int xPosition, int yPosition, Image image, float velX, float velY, Player player) {

        super(xPosition, yPosition, image, velX, 0);
        currentCapsuleList = new CopyOnWriteArrayList<>();
        this.player = player;
        handler.addHandler(currentCapsuleList);
    }

    public void tick() {

        x += velX;

        updateImage();
        collision();
        clamp();
        updateCapsule();
    }

    public void splitBall() {

        int numOfBall = ballList.size();

        for (BaseObject o : ballList) {

            if (o instanceof Ball) {

                Ball ball = (Ball) o;
                ball.setVelX(0);

                Ball newBallL = new Ball(ball.getX(), ball.getY(), ball.getImage(), xBallSpeed, ball.getVelY(), player);
                Ball newBallR = new Ball(ball.getX(), ball.getY(), ball.getImage(), -xBallSpeed, ball.getVelY(), player);

                handler.addObject(ballList, newBallL);
                handler.addObject(ballList, newBallR);

                numOfBall--;
            }
            if (numOfBall == 0) {
                break;
            }
        }
    }

    public void runLaser() {

        laser = true;
    }

    public void stopLaser() {
        laser = false;
    }


    public void expand() {

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Shrink) {
                handler.removeObject(currentCapsuleList, o);
                break;
            }
        }
        expand = true;
        shrink = false;
    }


    public void hitLaser() {

        Bullet bulletL = new Bullet(x, y, bullet);
        Bullet bulletR = new Bullet(x + getImageWidth() - getImageWidth() * 3 / 100, y, bullet);

        handler.addObject(bulletList, bulletL);
        handler.addObject(bulletList, bulletR);
        if(sounds)
        laserSound();
    }

    public void speedUp() {
        xBallSpeed = Math.min(4, xBallSpeed + 0.5f);
        yBallSpeed = Math.max(-4, yBallSpeed - 0.5f);

    }

    public void speedDown() {

        xBallSpeed = Math.max(1, xBallSpeed - 0.5f);
        yBallSpeed = Math.min(-1, yBallSpeed + 0.5f);
    }

    public void speedNormal() {
        xBallSpeed = initBallXSpeed;
        yBallSpeed = initBallYSpeed;
    }

    private void setEnemy() {

        Enemy e = new Enemy(50, 50, baseEnemyXSpeed, baseEnemyYSpeed, enemy[0], player.getLevel() * 2);
        handler.addObject(enemyList, e);
    }

    private void collision() {

        boolean checkIfBricksHeight1 = false, checkIfBricksHeight2 = false;

        for (BaseObject o : capsuleList) {

            if (o.getRectangle().intersects(getRectangle())) {

                ((Capsule) o).effect(this);
                ((Capsule) o).active = true;
                handler.addObject(currentCapsuleList, o);
                handler.removeObject(capsuleList, o);
            }
        }

        for (BaseObject o : ballList) {

            if (o.getRectangle().intersects(getRectangle())) {
                if (sticky) {

                    o.setVelX(0);
                    o.setVelY(0);
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

        if (enemyList.isEmpty())
            if (brickList.isEmpty()) {
                if (player.getLevel() % 5 == 0) {
                    setEnemy();
                } else {

                    breakToNextLevel();
                }
            }

        for (BaseObject o : enemyList) {

            if (o.getRectangle().intersects(getRectangle())) {
                player.die();
            }
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

        g.drawImage(super.image, (int) super.x, (int) super.y, null);
    }

    public void makeAcidBall() {


        for (BaseObject o : ballList) {

            ((Ball) o).makeAcid();
        }

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Fire) {
                handler.removeObject(currentCapsuleList, o);
                break;
            }
        }
    }

    public void shrink() {

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Expand) {
                handler.removeObject(currentCapsuleList, o);
                break;
            }
        }
        expand = false;
        shrink = true;
    }

    public void increaseLife() {

        player.setLives(player.getLives() + 1);
    }

    private void updateImage() {

        if (laser) {

            if (expand) {

                setImage(paddleExpandedWeapon);
            } else if (shrink) {

                setImage(paddleShrunk);
            } else {

                setImage(paddleWeapon[normalImageIdx++]);
                normalImageIdx %= 3;
            }
        } else if (expand) {

            setImage(paddleExpanded);
        } else if (shrink) {

            setImage(paddleShrunk);
        } else {

            setImage(paddle[normalImageIdx++]);
            normalImageIdx %= 3;
        }
        setSize();
    }

    private void setSize() {

        setImageWidth(image.getWidth(null));
        setImageHeight(image.getHeight(null));
    }

    public void sticky() {

        sticky = true;
    }

    public void makeFireBall() {

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Acid) {
                handler.removeObject(currentCapsuleList, o);
                break;
            }
        }

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

    private void updateCapsule() {
        for (BaseObject o : currentCapsuleList) {
            if (((Capsule) o).life <= 0) {
                ((Capsule) o).removeEffect(this);
                handler.removeObject(currentCapsuleList, o);
            }
            else {
                ((Capsule) o).effect(this);
            }
        }
    }

    public void paddleNormal() {
        expand = false;
        shrink = false;
    }

    public void makeNormalBall() {
        for (BaseObject o : ballList) {

            ((Ball) o).makeNormal();
        }
    }

    public void reset() {
        speedNormal();
        sticky = false;
        laser = false;
        shrink = false;
        expand = false;

        for (BaseObject o : currentCapsuleList) {
            ((Capsule) o).removeEffect(this);
            handler.removeObject(currentCapsuleList, o);
        }
    }
}



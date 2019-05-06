package arkanoid.board;

import arkanoid.capsule.*;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;
import static atariCore.Helper.sounds;

/**
 * Paddle Class.
 */
public class Paddle extends BaseObject {
    /**
     * Flag to check if the ball sticks to the paddle.
     */
    public boolean sticky;
    /**
     * Flag to check if the paddle has laser capsule.
     */
    public boolean laser;
    /**
     * Flag to check if the paddle has shrink capsule.
     */
    public boolean shrink;
    /**
     * Flag to check if the paddle has expand capsule.
     */
    public boolean expand;
    /**
     * Counter of current paddle image.
     */
    private int normalImageIdx = 0;
    /**
     * Player object.
     */
    private Player player;
    /**
     * Holds active capsules.
     */
    private CopyOnWriteArrayList<BaseObject> currentCapsuleList;
    /**
     * ID of The current collision Capsule
     */
    public static int capsuleID = 0;

    /**
     * Parameterised constructor takes  X, Y coordinates , paddle image ,x,y velocities and player object.
     *
     * @param xPosition X coordinates of the paddle.
     * @param yPosition Y coordinates of the paddle.
     * @param image     Paddle image.
     * @param velX      X Velocity of the paddle.
     * @param velY      Y Velocity of the paddle.
     * @param player    Player object
     */
    public Paddle(int xPosition, int yPosition, Image image, float velX, float velY, Player player) {

        super(xPosition, yPosition, image, velX, 0);
        currentCapsuleList = new CopyOnWriteArrayList<>();
        this.player = player;
        Handler.getInstance().addHandler(currentCapsuleList);
    }

    /**
     * Updates paddle's position.
     */
    public void tick() {

        x += velX;

        updateImage();
        collision();
        clamp();
        updateCapsule();
    }

    /**
     * Generates enemy on the screen.
     */
    private void setEnemy() {

        Enemy e = new Enemy(50, 50, baseEnemyXSpeed, baseEnemyYSpeed, enemy[0], player.getLevel() / 5);
        Handler.getInstance().addObject(enemyList, e);
    }

    /**
     * Responsible for paddle collision with ball, capsules, enemy and bricks.
     */
    private void collision() {

        boolean checkIfBricksHeight1 = false, checkIfBricksHeight2 = false;

        for (BaseObject o : capsuleList) {

            if (o.getRectangle().intersects(getRectangle())) {
                capsuleID = setCapsuleID(((Capsule) o));
                ((Capsule) o).effect(this);
                ((Capsule) o).active = true;
                Handler.getInstance().addObject(currentCapsuleList, o);
                Handler.removeObject(capsuleList, o);
            }
        }

        for (BaseObject o : ballList) {

            if (o.getRectangle().intersects(getRectangle())) {

                if (y >= o.getY()) {

                    if (sticky) {

                        o.setVelX(0);
                        o.setVelY(0);
                        continue;
                    }

                    setBallSpeed(o);
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

                paddleList.remove(this);

                if (paddleList.size() == 0)
                    player.die();
                break;
            }
        }

        if (!checkIfBricksHeight1 && checkIfBricksHeight2) {

            for (BaseObject o : brickList) {

                ((Brick) o).moveDown();
            }
        }

        if (brickList.isEmpty()) {
            if (player.getLevel() % 5 == 0) {
                if (enemyList.isEmpty())
                    setEnemy();
            } else {
                breakToNextLevel();
            }
        }

        for (BaseObject o : enemyList)
            if (o.getRectangle().intersects(getRectangle()))
                player.die();
    }

    /**
     * Sets ball's speed and direction on collision with paddle.
     *
     * @param o Ball object
     */
    public void setBallSpeed(BaseObject o) {

        float _x = o.getX();
        int dir = (o.getX() >= getX() + getImageWidth() / 2f) ? 1 : -1;

        float distFromCenter = (x + getImageWidth() / 2f - _x);
        distFromCenter /= getImageWidth() / 2;

        float newVX = ballSpeed * (float) Math.sin(Math.abs(distFromCenter));
        float newVY = ballSpeed * (float) Math.cos(Math.abs(distFromCenter));

        ((Ball) o).setSpeed(dir * newVX, -newVY);
    }

    /**
     * Returns player object.
     *
     * @return PLayer object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player of the paddle.
     *
     * @param player Player object.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Renders paddle on screen.
     */
    public void render(Graphics g) {

        g.drawImage(super.image, (int) super.x, (int) super.y, null);
    }


    /**
     * Updates paddle image.
     */
    private void updateImage() {

        if (laser) {

            if (expand) {

                setImage(paddleExpandedWeapon[normalImageIdx++]);
                normalImageIdx %= 3;
            } else if (shrink) {

                setImage(paddleShrunk);
            } else {

                setImage(paddleWeapon[normalImageIdx++]);
                normalImageIdx %= 3;
            }
        } else if (expand) {

            setImage(paddleExpanded[normalImageIdx++]);
            normalImageIdx %= 3;
        } else if (shrink) {

            setImage(paddleShrunk);
        } else {

            setImage(paddle[normalImageIdx++]);
            normalImageIdx %= 3;
        }
        setSize();
    }

    /**
     * Resize the paddle's image size.
     */
    private void setSize() {

        setImageWidth(image.getWidth(null));
        setImageHeight(image.getHeight(null));
    }

    /**
     * Returns the paddle to its normal state when time of capsule ends.
     */
    public void paddleNormal() {
        expand = false;
        shrink = false;
    }

    /**
     * Returns the ball to its normal state when time of capsule ends.
     */
    public void makeNormalBall() {
        for (BaseObject o : ballList) {

            ((Ball) o).makeNormal();
        }
    }

    /**
     * Resets the paddle into default one.
     */
    public void reset() {
        speedNormal();
        sticky = false;
        laser = false;
        shrink = false;
        expand = false;

        for (BaseObject o : currentCapsuleList) {
            ((Capsule) o).removeEffect(this);
            Handler.removeObject(currentCapsuleList, o);
        }
    }


    /****************************************************CAPSULES******************************************************/


    /**
     * Activates sticky capsule.
     */
    public void sticky() {

        sticky = true;
    }

    /**
     * Activates fire ball.
     */
    public void makeFireBall() {

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Acid) {
                Handler.removeObject(currentCapsuleList, o);
                break;
            }
        }

        for (BaseObject o : ballList) {

            ((Ball) o).makeFire();
        }
    }

    /**
     * Moves player to next level and clears current handlers.
     */
    public void breakToNextLevel() {

        if (paddleList.size() == 0) return;

        ballList.clear();
        paddleList.clear();
        capsuleList.clear();
        brickList.clear();
        bulletList.clear();
        enemyList.clear();
        ((Player) (playerList.get(0))).arkanoid.refresh();
        player.setLevel(player.getLevel() + 1);
    }

    /**
     * Removes capsule effect if it expired.
     */
    private void updateCapsule() {
        for (BaseObject o : currentCapsuleList) {
            if (((Capsule) o).life <= 0) {
                ((Capsule) o).removeEffect(this);
                Handler.removeObject(currentCapsuleList, o);
            } else {
                ((Capsule) o).effect(this);
            }
        }
    }

    /**
     * Increase numbers of balls by two.
     */
    public void splitBall() {

        CopyOnWriteArrayList<BaseObject> balls = new CopyOnWriteArrayList<>(ballList);
        for (BaseObject o : balls) {

            Ball ball = (Ball) o;
            int dir = ball.getVelY() < 0 ? -1 : 1;
            ball.setVelX(0);
            ball.setVelY(dir * ballSpeed);

            Ball newBallL = new Ball(ball.getX(), ball.getY(), ball.getImage(), ballSpeed * (float) Math.cos(Math.PI * 45 / 180), dir * ballSpeed * (float) Math.cos(Math.PI * 45 / 180), player);
            Ball newBallR = new Ball(ball.getX(), ball.getY(), ball.getImage(), -ballSpeed * (float) Math.cos(Math.PI * 45 / 180), dir * ballSpeed * (float) Math.cos(Math.PI * 45 / 180), player);

            Handler.getInstance().addObject(ballList, newBallL);
            Handler.getInstance().addObject(ballList, newBallR);
        }
    }

    /**
     * Activates laser capsule and its effect on paddle.
     */
    public void runLaser() {

        laser = true;
    }

    /**
     * Deactivates laser capsule.
     */
    public void stopLaser() {
        laser = false;
    }

    /**
     * Activates expand capsule.
     */
    public void expand() {

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Shrink) {
                Handler.removeObject(currentCapsuleList, o);
                break;
            }
        }
        expand = true;
        shrink = false;
    }

    /**
     * Fires a laser bullet.
     */
    public void hitLaser() {

        Bullet bulletL = new Bullet(x, y, bullet);
        Bullet bulletR = new Bullet(x + getImageWidth() - getImageWidth() * 3 / 100f, y, bullet);

        Handler.getInstance().addObject(bulletList, bulletL);
        Handler.getInstance().addObject(bulletList, bulletR);
        if (sounds)
            laserSound();
    }

    /**
     * Increases paddle's speed.
     */
    public void speedUp() {
        ballSpeed = Math.min(ballSpeed + 0.1f, 4);

    }

    /**
     * Decreases paddle's speed.
     */
    public void speedDown() {

        ballSpeed = Math.max(ballSpeed - 0.1f, 1);
    }

    /**
     * Returns the paddle to its normal speed.
     */
    public void speedNormal() {
        ballSpeed = 2;
    }

    /**
     * Activates acid ball.
     */
    public void makeAcidBall() {


        for (BaseObject o : ballList) {

            ((Ball) o).makeAcid();
        }

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Fire) {
                Handler.removeObject(currentCapsuleList, o);
                break;
            }
        }
    }

    /**
     * Updates paddle's image to shrink paddle.
     */
    public void shrink() {

        for (BaseObject o : currentCapsuleList) {
            if (o instanceof Expand) {
                Handler.removeObject(currentCapsuleList, o);
                break;
            }
        }
        expand = false;
        shrink = true;
    }

    /**
     * Increments player's life.
     */
    public void increaseLife() {

        player.setLives(player.getLives() + 1);
    }

    /**
     * Return the ID of Capsule
     */
    public int setCapsuleID(Capsule c) {

        if (c instanceof Acid)
            return 1;
        if (c instanceof Break)
            return 2;
        if (c instanceof Catch)
            return 3;
        if (c instanceof Disrupt)
            return 4;
        if (c instanceof Expand)
            return 5;
        if (c instanceof Fast)
            return 6;
        if (c instanceof Fire)
            return 7;
        if (c instanceof Laser)
            return 8;
        if (c instanceof Life)
            return 9;
        if (c instanceof Score)
            return 10;
        if (c instanceof Shrink)
            return 11;
        if (c instanceof Slow)
            return 12;
        if (c instanceof Vaus)
            return 13;

        return 0;


    }


    /******************************************************************************************************************/
}



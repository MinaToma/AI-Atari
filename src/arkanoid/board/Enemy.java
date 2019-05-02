package arkanoid.board;

import arkanoid.ObjectList;
import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Helper;

import java.awt.*;

/**
 * Enemy Class.
 */
public class Enemy extends BaseObject {

    /**
     * Power of enemy to be destroyed.
     */
    private int power;
    /**
     * Timer to update enemy image.
     */
    private int timer;
    /**
     * Counter of current enemy image.
     */
    private int cnt;

    /**
     * Parameterised constructor takes X, Y coordinates x,y velocities, enemy image and number of hits to destroy the enemy.
     *
     * @param xPosition X coordinates of the enemy.
     * @param yPosition Y coordinates of the enemy.
     * @param velX      X Velocity of the enemy.
     * @param velY      Y Velocity of the enemy.
     * @param image     Enemy image.
     * @param power     Number of hits to destroy the enemy.
     */
    public Enemy(int xPosition, int yPosition, float velX, float velY, Image image, int power) {
        super(xPosition, yPosition, image);
        this.power = power;
        this.velX = velX;
        this.velY = velY;
        timer = 0;
        cnt = 1;
    }

    /**
     * Updates enemy's position.
     */
    @Override
    public void tick() {

        timer++;
        if (timer % 10 == 0) {
            timer = 0;
            setImage(arkHelper.enemy[cnt++]);
            cnt %= 6;
        }

        x += velX;
        y += velY;

        clamp();
    }

    /**
     * Clamps the enemy within the screen bounds.
     */
    @Override
    protected void clamp() {
        if (y <= 0 || y >= Helper.screenHeight - imageHeight) {
            velY *= -1;
        }
        if (x <= 0 || x >= Helper.screenWidth - imageHeight) {
            velX *= -1;
        }
    }

    /**
     * Set enemy's power.
     *
     * @param power Number of hits to destroy the enemy.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Returns enemy's power.
     *
     * @return Enemy's power.
     */
    public int getPower() {
        return power;
    }

    /**
     * Decrement enemy's power when hit and removes it from handler when it is destroyed.
     */
    public void reducePower() {

        --power;
        if (power == 0) {
            ((Paddle) (ObjectList.paddleList.get(0))).breakToNextLevel();
        }
    }

    /**
     * Renders the enemy on screen.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(super.image, (int) super.x, (int) super.y, null);
    }
}

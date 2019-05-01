package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;

import static arkanoid.ObjectList.*;
import static arkanoid.arkHelper.*;
import static atariCore.Helper.*;

/**
 * Brick Class
 */
public class Brick extends BaseObject {
    /**
     * Flag to know brick's shape.
     */
    private boolean isSmallSquares;
    /**
     * Number of hits to destroy the brick.
     */
    private int power;
    /**
     * Capsule which drops down when the brick is destroyed.
     */
    public Capsule capsule;
    /**
     * Color of the brick
     */
    private int color;
    /**
     * Timer to move the brick down.
     */
    public int timer;
    /**
     * Player object.
     */
    private Player player;

    /**
     * Parameterized constructor takes X, Y coordinates,  brick image ,power of brick ,color of brick and object of player.
     *
     * @param xPosition X coordinates of the brick.
     * @param yPosition Y coordinates of the brick.
     * @param image     Brick image.
     * @param power     Number of hits to destroy the brick.
     * @param color     Color of the brick.
     * @param player    Player object.
     */
    public Brick(int xPosition, int yPosition, Image image, int power, int color, Player player) {

        super(xPosition, yPosition, image);
        this.power = power;
        this.player = player;
        this.color = color;
        if (power > 1) {
            isSmallSquares = false;
        } else
            isSmallSquares = true;
        timer = 0;
    }

    /**
     * Parameterized constructor takes X, Y coordinates,  brick image ,power of brick ,color of brick and object of player.
     *
     * @param xPostion X coordinates of the brick.
     * @param yPostion Y coordinates of the brick.
     * @param image    Brick image.
     * @param power    Number of hits to destroy the brick.
     * @param color    Color of the brick.
     * @param capsule  Capsule carried in the brick.
     * @param player   Player object.
     */
    public Brick(int xPostion, int yPostion, Image image, int power, int color, Capsule capsule, Player player) {

        this(xPostion, yPostion, image, power, color, player);
        this.capsule = capsule;
    }

    /**
     * Updates brick's power.
     *
     * @return Whether the brick is destroyed or not.
     */
    private boolean hit() {

        player.increaseScore(BRICKHITREWARD);
        power -= 1;
        if (power == 1 && !isSmallSquares) {

            this.setImage(arkHelper.brokenBricks[color]);
        }

        return (power <= 0);
    }

    /**
     * Updates the brick position.
     */
    public void tick() {

        super.y += 0;
        timer++;

        if (timer >= Math.max(1000, 10000 - 10 * player.getLevel())) {
            moveDown();
        }
    }

    /**
     * Moves the brick down.
     */
    public void moveDown() {
        timer = 0;
        super.y += getImageHeight() + 3;
    }

    /**
     * Renders the brick on screen.
     */
    public void render(Graphics g) {
        if (y > 0)
            g.drawImage(this.image, (int) this.x, (int) this.y, null);

    }

    /**
     * Generates hit sound and remove the brick if it is destroyed.
     */
    public void hitBrick() {

        if (getY() >= 0) {
            if (!AIMode && sounds) {
                hitSound();
            }

            if (hit()) {
                if (capsule != null) {

                    capsule.setX(getX());
                    capsule.setY(getY());
                    Handler.getInstance().addObject(capsuleList, capsule);
                }
                arkHelper.numberOfBricks--;
                Handler.getInstance().removeObject(brickList, this);

            }
        }
    }

    /**
     * Returns brick's power.
     *
     * @return Brick's power.
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets brick's power.
     *
     * @param power Number of hits to destroy the brick.
     */
    public void setPower(int power) {
        this.power = power;
    }
}
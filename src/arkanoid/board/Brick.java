
package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.AIEngine;
import atariCore.BaseObject;
import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import java.awt.*;

import static arkanoid.ObjectList.*;
import static arkanoid.arkHelper.*;

public class Brick extends BaseObject {

    private boolean isSmallSquares;
    private int power;
    public Capsule capsule;
    private int color;
    public int timer;
    private Player player;

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

    public Brick(int xPostion, int yPostion, Image image, int power, int color, Capsule capsule, Player player) {

        this(xPostion, yPostion, image, power, color, player);
        this.capsule = capsule;
    }

    private boolean hit() {

        player.increaseScore(BRICKHITREWARD);
        power -= 1;
        if (power == 1 && !isSmallSquares) {

            this.setImage(arkHelper.brokenBricks[color]);
        }

        return (power <= 0);
    }

    public void tick() {

        super.y += 0;
        timer++;

        // every level the timer per move brick down decrease
        if (timer >= Math.max(1000, 10000 - 10 * player.getLevel())) {
            moveDown();
        }
    }

    public void moveDown() {
        timer = 0;
        super.y += getImageHeight() + 3;
    }

    public void render(Graphics g) {
        if (y > 0)
            g.drawImage(this.image, (int) this.x, (int) this.y, null);

    }


    public void hitBrick() {

        if (getY() >= 0) {
         if (!AIMode) {

                Sound.Play(hitSound,true);
            }

            if (hit()) {
                if (capsule != null) {

                    capsule.setX(getX());
                    capsule.setY(getY());
                    handler.addObject(capsuleList, capsule);
                }
                arkHelper.numberOfBricks--;
                handler.removeObject(brickList, this);

            }
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}


package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import javax.swing.*;
import java.awt.*;

public class Brick extends BaseObject {

    private boolean destroyed;
    private String color;
    private int power;
    public Capsule capsule;

    public Brick(int xPostion, int yPostion, Image image, String color, int power) {

        super(xPostion, yPostion, image);
        this.destroyed = false;
        this.color = color;
        this.power = power;
    }

    public Brick(int xPostion, int yPostion, Image image, String color, int power, Capsule capsule) {

        this(xPostion, yPostion, image, color, power);
        this.capsule = capsule;
    }

    public boolean hit() {
        power -= 1;

        return  (power == 0);
    }

    public void tick() {
        super.y += super.velY;
    }

    public void render(Graphics g) {

        g.drawImage(this.img, this.x, this.y, null);
    }

    public int getPower() {
        return power;
    }
}

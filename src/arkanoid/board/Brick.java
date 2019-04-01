
package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import javax.swing.*;
import java.awt.*;

public class Brick extends BaseObject {

    public Handler handler;
    private boolean destroyed;
    private String color;
    private int power;
    Capsule capsule;

    public Brick(int xPostion, int yPostion, Image image, String color, int power) {

        super(xPostion, yPostion, image);
        this.destroyed = false;
        this.color = color;
        this.power = power;
    }

    public Brick(int xPostion, int yPostion, Image image, String color, int power, Capsule capsule, Handler handler) {

        this(xPostion, yPostion, image, color, power);
        this.capsule = capsule;
        this.handler = handler;
    }

    public void hit() {
        power -= 1;

        if (power == 0 && capsule != null) {
            handler.object.add(capsule);
        }
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

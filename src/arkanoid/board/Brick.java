
package arkanoid.board;

import atariCore.BaseObject;

import java.awt.*;

public class Brick extends BaseObject {
    private boolean destroyed;
    private String color;
    private int power;


    public Brick(int xPostion, int yPostion, Image image, String color, int power) {
        super(xPostion, yPostion, image);
        this.destroyed = false;
        this.color = color;
        this.power = power;
        power = 10;// we can change it


    }

    public void hit() {
        power -= 1;
    }

    public void tick() {
        super.y += super.velY;
    }

    public void render(Graphics g) {

        if (power > 0)
            g.drawImage(super.img, super.x, super.y, null);

    }

    public int getPower() {
        return power;
    }
}

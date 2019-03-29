package arkanoid.board;

import arkanoid.Common;
import atariCore.BaseObject;

import java.awt.*;

public class Enemy extends BaseObject  implements Common {

    private int power ;
    public Enemy(int xPostion , int yPostion , Image image){
        super(xPostion,yPostion, image);
        power = 10 ;  // we can change it
    }

    public void render(Graphics g){
        g.drawImage(super.img, super.x ,super.y, null);

    }
    public void tick() {
        super.x += super.velX;
        super.y += super.velY;

        if (super.y <= 0 || super.y >= HEIGTH - super.imageHeight)
            super.velY *= -1;
        if (super.x <= 0 || super.x >= WIDTH - super.imageHeight)
            super.velX *= -1;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}

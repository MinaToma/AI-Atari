package arkanoid.board;

import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends BaseObject {

    private int power;
    public Handler handler;

    public Enemy(int xPostion, int yPostion, Image image, int power, Handler handler) {
        super(xPostion, yPostion, image);
        this.power = power;  // we can change it
        this.handler = handler;
    }

    public void render(Graphics g) {
        g.drawImage(super.img, (int)super.x, (int)super.y, null);
    }

    public void tick() {
        super.x += super.velX;
        super.y += super.velY;

        if (super.y <= 0 || super.y >= Helper.screenHeight - super.imageHeight)
            super.velY *= -1;
        if (super.x <= 0 || super.x >= Helper.screenWidth - super.imageHeight)
            super.velX *= -1;

        collision();
    }

    private void collision() {

        ArrayList<BaseObject> object = new ArrayList<>();
        for (int i = 0; i < handler.object.size(); i++) {
            BaseObject baseObject = handler.object.get(i);
            if (baseObject instanceof Paddle) {
                if (baseObject.getRectangle().intersects(super.getRectangle())) {
                    // han3ml 7aga hena
                    object.add(baseObject);
                }
            }

        }
        for (BaseObject bas : object) {
            handler.object.remove(bas);
        }
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}

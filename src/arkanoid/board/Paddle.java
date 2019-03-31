

package arkanoid.board;

import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Paddle extends BaseObject {

    public Handler handler;


    public Paddle(int xPosition, int yPosition, Image image, int velX, int velY, Handler handler) {
        super(xPosition, yPosition, image, velX, 0);
        this.handler = handler;
       // loadImage();
    }

    public void tick() {
        x += velX;

        if (x <= 0)
            x = 0;
        if (x >= Helper.WIDTH - imageWidth)
            x = Helper.WIDTH - imageWidth;

        collision();
    }

    private void collision() {

        ArrayList<BaseObject> object = new ArrayList<>();

        for (int i = 0; i < handler.object.size(); i++) {
            BaseObject baseObject = handler.object.get(i);
            if (baseObject instanceof Capsule) {
                if (baseObject.getRectangle().intersects(super.getRectangle())) {
                    // han3ml 7aga hena
                    object.add(baseObject);
                }
            }
            if (baseObject instanceof Ball) {
                if (baseObject.getRectangle().intersects(super.getRectangle())) {
                    baseObject.setVelY(baseObject.getVelY() * -1);
                }
            }
        }

        for (BaseObject bas : object) {
            handler.object.remove(bas);
        }
    }
    private void loadImage() {

        ImageIcon ii = new ImageIcon("src/Resources/image/49-Breakout-Tiles.png");
        this.img = ii.getImage();
        this.img = this.img.getScaledInstance(img.getWidth(null)/5, img.getHeight(null)/5,  Image.SCALE_DEFAULT);
        setImageHeight(img.getHeight(null));
        setImageWidth(img.getWidth(null));
    }

    public void render(Graphics g) {

        g.drawImage(super.img, super.x ,super.y,null);
    }
}




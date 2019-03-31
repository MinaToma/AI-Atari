package arkanoid.board;

import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Handler;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ball extends BaseObject {


    Handler handler;

    public Ball(int xPostion, int yPostion, Image image, int xVelocity, int yVelocity, Handler handler) {
        super(xPostion, yPostion, image, xVelocity, yVelocity);
        this.handler = handler;
        loadImage();
    }

    public void move() {

    }

    @Override
    public void tick() {
        y += velY;
        x += velX;

        if (arkHelper.WIDTH - imageHeight <= y || y <= 0) {
            velY *= -1;
        }
        if (arkHelper.HEIGTH - imageWidth <= x || x <= 0) {
            velX *= -1;
        }

        collision();
    }

    private void collision() {
        int calcScore = 0;
        ArrayList<BaseObject> object = new ArrayList<>();
        for (int i = 0; i < handler.object.size(); i++) {
            BaseObject baseObject = handler.object.get(i);
            if (baseObject instanceof Brick || baseObject instanceof Enemy) {
                if (baseObject.getRectangle().intersects(super.getRectangle())) {
                    calcScore++;
                    ((Brick) baseObject).hit();

                    int pow = ((Brick) baseObject).getPower();
                    if (pow == 0) {
                        object.add(baseObject);
                    }
                }
            }
        }

        for (BaseObject bas : object) {
            handler.object.remove(bas);
        }
    }
    private void loadImage() {

        ImageIcon ii = new ImageIcon("src/Resources/image/58-Breakout-Tiles.png");
        this.img = ii.getImage();
        this.img = this.img.getScaledInstance(img.getWidth(null)/5, img.getHeight(null)/5,  Image.SCALE_DEFAULT);
        setImageHeight(img.getHeight(null));
        setImageWidth(img.getWidth(null));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
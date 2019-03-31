
package arkanoid.board;

import atariCore.BaseObject;

import javax.swing.*;
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
        loadImage();
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
            g.drawImage( this.img, this.x, this.y, null );

    }
    private void loadImage() {

        ImageIcon ii = new ImageIcon("src/Resources/image/11-Breakout-Tiles.png");
        this.setImg( ii.getImage());
        setImageWidth(img.getWidth(null)/5);
        setImageHeight(img.getHeight(null)/5);

        this.setImg( this.img.getScaledInstance(getImageWidth(), getImageHeight(),  Image.SCALE_DEFAULT));

    }

    public int getPower() {
        return power;
    }
}

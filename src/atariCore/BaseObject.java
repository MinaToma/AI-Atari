package atariCore;


import java.awt.*;
import java.io.IOException;

abstract public class BaseObject {

    protected float x, y;
    protected Image img;
    protected float velY, velX;
    protected int imageWidth, imageHeight;

    public BaseObject(float x, float y, Image img) {

        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
        this.img = img;

        if(img != null) {

            this.imageHeight = img.getHeight(null);
            this.imageWidth = img.getWidth(null);
        }
    }

    public BaseObject(float x, float y, Image img, float velX, float velY) {

        this(x, y, img);
        this.velX = velX;
        this.velY = velY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVelY() {
        return velY;
    }

    public float getVelX() {
        return velX;
    }

    public Image getImg() {
        return img;
    }

    public void clamp() throws IOException {

        x = Math.min(x , Helper.screenWidth - imageWidth);
        x = Math.max(x , 0);

        y = Math.min(y , Helper.screenHeight - imageHeight);
        y = Math.max(y , 0);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)x, (int)y, imageWidth, imageHeight);
    }

    public abstract void tick() throws IOException;

    public abstract void render(Graphics g);
}

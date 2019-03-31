package atariCore;


import java.awt.*;

abstract public class BaseObject {

    protected int x, y;
    protected Image img;
    protected int velY, velX;
    protected int imageWidth, imageHeight;

    public BaseObject(int x, int y, Image img) {

        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
        this.img = img;

        this.imageHeight = img.getHeight(null);
        this.imageWidth = img.getWidth(null);
    }

    public BaseObject(int x, int y, Image img, int velX, int velY) {

        this(x, y, img);
        this.velX = velX;
        this.velY = velY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelY() {
        return velY;
    }

    public int getVelX() {
        return velX;
    }

    public Image getImg() {
        return img;
    }

    public void clamp() {

        x = Math.min(x , Helper.screenWidth - imageWidth);
        x = Math.max(x , 0);

        y = Math.min(y , Helper.screenHeight - imageHeight);
        y = Math.max(y , 0);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, imageWidth, imageHeight);
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}

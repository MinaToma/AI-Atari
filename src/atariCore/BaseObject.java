package atariCore;


import javafx.scene.shape.Circle;

import java.awt.*;

abstract public class BaseObject {

    protected float x, y;
    protected Image image;
    protected float velY, velX;
    protected int imageWidth, imageHeight;

    public BaseObject(float x, float y, Image image) {

        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
        setImage(image);

        if (image != null) {
            setImageWidth(image.getWidth(null));
            setImageHeight(image.getHeight(null));
        }
    }

    public BaseObject(float x, float y, Image image, float velX, float velY) {

        this(x, y, image);
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

    public Image getImage() {
        return image;
    }

    public void clamp() {

        x = Math.min(x, Helper.screenWidth - imageWidth);
        x = Math.max(x, 0);

        y = Math.min(y, Helper.screenHeight - imageHeight);
        y = Math.max(y, 0);
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

    public void setImage(Image image) {
        this.image = image;
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
        return new Rectangle((int) x, (int) y, getImageWidth(), getImageHeight());
    }

    public Circle getCircle(){return new Circle( (x+imageWidth)/2,(y+imageHeight)/2,imageHeight/2 );}

    public abstract void tick();

    public abstract void render(Graphics g);
}

package atariCore;


import java.awt.*;

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
        setImg(img);

        if(img != null)
        {
            setImageWidth(img.getWidth(null));
            setImageHeight(img.getHeight(null));
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

    public void clamp() {

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
      /*  if(img != null) {
            setImageWidth(img.getHeight(null));
            setImageHeight(img.getHeight(null));
        }
        else {
            setImageWidth(0);
            setImageHeight(0);
        }*/
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageWidth(int imageWidth)
    {
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

    public abstract void tick();

    public abstract void render(Graphics g);
}

package atariCore;

import java.awt.*;

/**
 * The main base class to create objects and its movement for any game.
 */
abstract public class BaseObject {

    /**
     * X coordinates of the object.
     */
    protected float x;
    /**
     * Y coordinates of the object.
     */
    protected float y;
    /**
     * X Velocity of the object.
     */
    protected float velX;
    /**
     * Y Velocity of the object.
     */
    protected float velY;
    /**
     * Holds object image.
     */
    protected Image image;

    /**
     * Holds image width and height respectively.
     */
    protected int imageWidth, imageHeight;


    /**
     * Parameterised constructor takes X, Y coordinates and object image.
     *
     * @param x     X coordinates of the object.
     * @param y     Y coordinates of the object.
     * @param image object image
     */
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


    /**
     * Parameterised constructor takes X, Y coordinates, x,y velocities and object image.
     *
     * @param x     X coordinates of the object.
     * @param y     Y coordinates of the object.
     * @param image object image
     * @param velX  X Velocity of the object.
     * @param velY  Y Velocity of the object.
     */
    public BaseObject(float x, float y, Image image, float velX, float velY) {
        this(x, y, image);
        this.velX = velX;
        this.velY = velY;
    }

    /**
     * Clamps the object within frame bounds.
     */
    protected void clamp() {
        x = Math.min(x, Helper.screenWidth - imageWidth);
        x = Math.max(x, 0);

        y = Math.min(y, Helper.screenHeight - imageHeight);
        y = Math.max(y, 0);
    }

    /**
     * Returns the x coordinate of the object.
     *
     * @return x coordinate of the object.
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x coordinates of the object.
     *
     * @param x the x coordinates of the object.
     */
    public void setX(float x) {
        this.x = x;
    }


    /**
     * Returns the y coordinate of the object.
     *
     * @return y coordinate of the object.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y coordinates of the object.
     *
     * @param y the y coordinates of the object.
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     * Returns the x velocity of the object.
     *
     * @return x velocity of the object.
     */
    public float getVelX() {
        return velX;
    }

    /**
     * Sets the x velocity of the object.
     *
     * @param velX the x velocity of the object.
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }


    /**
     * Returns the y velocity of the object.
     *
     * @return y velocity of the object.
     */
    public float getVelY() {
        return velY;
    }

    /**
     * Sets the y velocity of the object.
     *
     * @param velY the y velocity of the object.
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }


    /**
     * Returns the object's image.
     *
     * @return image of the object.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the object's image.
     *
     * @param image object's image.
     */
    public void setImage(Image image) {
        this.image = image;
    }


    /**
     * Returns the width of the object's image.
     *
     * @return width of the object'image.
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Sets the width of object's image.
     *
     * @param imageWidth width of object's image.
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }


    /**
     * Returns the height of the object's image.
     *
     * @return height of the object'image.
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Sets the height of object's image.
     *
     * @param imageHeight height of object's image.
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Returns the a rectangle which represents the bounds of the object.
     *
     * @return rectangle which represents the bounds of the object.
     */
    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, getImageWidth(), getImageHeight());
    }

    /**
     * Updates object over time
     */
    protected abstract void tick();

    /**
     * Renders the objects on the frame.
     *
     * @param g Graphics object inside a runnable thread
     */
    protected abstract void render(Graphics g);
}

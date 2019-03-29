package atariCore;


import java.awt.*;

public class BaseObject {

    protected int xPostion, yPostion;
    protected int imageWidth, imageHight;
    protected int yVelocity, xVelocity;
    protected Image image;

    public BaseObject(int xPostion , int yPostion , Image image )
    {
        this.xPostion = xPostion;
        this.yPostion = yPostion;
        this.image = image;
        this.imageHight = image.getHeight(null);
        this.imageWidth = image.getWidth(null);
        this.xVelocity = 0;
        this.yVelocity = 0;

    }
    public  BaseObject(int xPostion , int yPostion , Image image , int xVelocity , int yVelocity)
    {
        this(xPostion,yPostion,image);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }
    protected void setxPostion(int xPostion)
    {
        this.xPostion = xPostion;
    }
    protected void setyPostion(int yPostion)
    {
        this.yPostion = yPostion;
    }
    protected void setxVelocity(int xVelocity)
    {
        this.xVelocity = xVelocity;
    }
    protected void setyVelocity(int yVelocity)
    {
        this.yVelocity = yVelocity;
    }
    protected int getxPostion()
    {
        return xPostion;
    }
    protected int getyPostion()
    {
        return yPostion;
    }
    protected int getxVelocity()
    {
        return xVelocity;
    }
    protected int getyVelocity()
    {
        return yVelocity;
    }
    protected int getImageWidth()
    {
        return imageWidth;
    }
    protected int getImageHight()
    {
        return imageHight;
    }

    protected Rectangle getRectangle()
    {
        return new Rectangle(xPostion,yPostion,imageWidth,imageHight);
    }

    protected void move()
    {
        xPostion+=xVelocity;
        yPostion+=yVelocity;
    }

}

package arkanoid.board;

import arkanoid.Common;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends BaseObject {

    private int power ;
    public Handler handler;
    public Common common= new Common();
    public Enemy(int xPostion , int yPostion , Image image ,int power, Handler handler){
        super(xPostion,yPostion, image);
        this.power = power;  // we can change it
        this.handler = handler;
    }

    public void render(Graphics g){
        g.drawImage(super.img, super.x ,super.y, null);

    }
    public void tick() {
        super.x += super.velX;
        super.y += super.velY;

        if (super.y <= 0 || super.y >=common.HEIGTH - super.imageHeight)
            super.velY *= -1;
        if (super.x <= 0 || super.x >=common.WIDTH - super.imageHeight)
            super.velX *= -1;

        collision();

    }
    private void collision()
    {

        ArrayList<BaseObject> object = new ArrayList<>();
        for(int i = 0 ; i<handler.object.size() ; i++)
        {
            BaseObject baseObject = handler.object.get(i);
            if(baseObject instanceof Paddle) {
                if (baseObject.getRectangle().intersects(super.getRectangle()))
                {
                    // han3ml 7aga hena
                    object.add(baseObject);
                }
            }

        }
        for(BaseObject bas:object)
        {
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

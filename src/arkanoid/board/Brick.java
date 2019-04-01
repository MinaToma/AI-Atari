
package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import javax.swing.*;
import java.awt.*;

public class Brick extends BaseObject {

    private boolean isSmallSquares;
    private int power;
    public Capsule capsule;
    private int color;
    public int timer;

    public Brick(int xPostion, int yPostion, Image image,  int power, int color) {

        super(xPostion, yPostion, image);
        this.power = power;
        this.color = color;
        if(power > 1)
        {
            isSmallSquares = false;
        }
        else
            isSmallSquares = true;
        timer =0 ;
    }

    public Brick(int xPostion, int yPostion, Image image,  int power,int color ,Capsule capsule) {

        this(xPostion, yPostion, image,power , color);
        this.capsule = capsule;
    }

    public boolean hit() {
        power -= 1;
        if(power == 1 && !isSmallSquares)
        {
            //System.out.println(11111);
            this.setImg(arkHelper.brokenBricks[color]);
        }
        return  (power == 0);
    }

    public void tick() {
        super.y +=0;
        timer++;
        if(timer>=10000) {
           timer=0;
           super.y += getImageHeight()+3;

        }


    }

    public void render(Graphics g) {

        g.drawImage(this.img, this.x, this.y, null);
    }

    public int getPower() {
        return power;
    }
}

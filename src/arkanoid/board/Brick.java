
package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import javax.swing.*;
import java.awt.*;

import static arkanoid.arkHelper.BRICKHITREWARD;

public class Brick extends BaseObject {

    private boolean isSmallSquares;
    private int power;
    public Capsule capsule;
    private int color;
    public int timer;
    private Player player;

    public Brick(int xPostion, int yPostion, Image image,  int power, int color , Player player) {

        super(xPostion, yPostion, image);
        this.power = power;
        this.player = player;
        this.color = color;
        if(power > 1)
        {
            isSmallSquares = false;
        }
        else
            isSmallSquares = true;
        timer =0 ;
    }

    public Brick(int xPostion, int yPostion, Image image,  int power,int color ,Capsule capsule , Player player) {

        this(xPostion, yPostion, image,power , color , player);
        this.capsule = capsule;
    }

    public boolean hit() {

        player.increaseScore(BRICKHITREWARD);
        power -= 1;
        if(power == 1 && !isSmallSquares) {

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
        if(y>0)
        g.drawImage(this.img, (int)this.x, (int)this.y, null);
    }

    public int getPower() {
        return power;
    }
}

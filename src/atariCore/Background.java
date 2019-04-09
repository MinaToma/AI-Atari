package atariCore;

import arkanoid.arkHelper;

import java.awt.*;

public class Background extends BaseObject{

    private static int cnt=0;
    private int timer=0;
    private int length;
    private Image[] imgs;

    public Background(float x, float y, Image img) {
        super(x, y, img);
        cnt =0;
        length=0;
    }

    public Background(float x,float y ,Image[] imgs , int length )
    {
        super(x,y,imgs[0]);
        this.length = length;
        this.imgs = imgs;
    }

    @Override
    public void tick() {
        if(length>0) {
            timer++;
            if (timer % 10 == 0) {
                this.setImg(imgs[cnt++]);
                cnt %= 8;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,0,0,null);
    }
}

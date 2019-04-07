package arkanoid.board;

import arkanoid.arkHelper;
import atariCore.BaseObject;

import java.awt.*;

public class Background extends BaseObject {
    private static int cnt=0;
    private int timer=0;
    public Background(float x, float y, Image img) {
        super(x, y, img);
        cnt =0;
    }

    @Override
    public void tick() {
        timer++;
        if(timer%10==0) {
            this.setImg(arkHelper.backgroundImage[cnt++]);
            cnt %= 8;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,0,0,null);
    }
}

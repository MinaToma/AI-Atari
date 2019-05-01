package flappyBird;

import atariCore.Background;
import atariCore.BaseObject;

import java.awt.*;
import java.util.Random;

import static atariCore.BaseObjectList.handler;
import static atariCore.Helper.screenHeight;
import static flappyBird.ObjectList.pipList;
import static flappyBird.ObjectList.playerList;
import static flappyBird.flappyHelper.*;

public class Pip extends BaseObject {

    private boolean inTheBack;

    public Pip(float x, float y, Image image, float velX, float velY) {
        super(x, y, image, velX, velY);
        inTheBack = false;
    }

    public Pip(float x, float y, Image image) {
        super(x, y, image);

    }

    @Override
    public void tick() {

        if(flappyHelper.startGame)
        x+=velX;

        modfiyPip();
    }

    public boolean isInTheBack() {
        return inTheBack;
    }

    public void setInTheBack(boolean inTheBack) {
        this.inTheBack = inTheBack;
    }

    private void modfiyPip()
    {
        boolean needPip = false;
        float pos=0;
        for(BaseObject o:pipList)
        {
            if(o.getX()+ o.getImageWidth()<0) {
                pos=o.getX();
                handler.removeObject(pipList, o);
                needPip = true;
                break;
            }
        }
        float dist = 100;
        if(needPip)
        {

            for(BaseObject o:pipList)
            {
                if(Math.abs( o.getX() - pos)<dist) {
                    dist = Math.abs( o.getX() - pos);
                }

            }
            for(BaseObject o:pipList)
            {
                if(Math.abs( o.getX() - pos)==dist) {
                    handler.removeObject(pipList, o);
                    break;
                }

            }


        }



        int lastPosPip=0;
        for(BaseObject o:pipList)
        {
            lastPosPip =  Math.max((int)o.getX(),lastPosPip);
        }

        if(needPip)
        {
            ((Player)playerList.get(0)).lastScore++;
            Random rand  = new Random();
            int initialY = rand.nextInt(maxHeight)+minHeight;
            Pip pipD = new Pip(lastPosPip + widthGap ,initialY - pipDown.getHeight(null),pipDown,-1.5f,0);
            Pip pipU = new Pip(lastPosPip + widthGap  ,initialY + heightGap,pipUp,-1.5f,0);

            handler.addObject(pipList,pipD);
            handler.addObject(pipList,pipU);
        }
    }

    @Override
    public void render(Graphics g) {
        if(flappyHelper.startGame)
        g.drawImage(image,(int)x,(int)y,null);
    }
}

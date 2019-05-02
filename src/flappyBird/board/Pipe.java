package flappyBird.board;

import atariCore.BaseObject;
import atariCore.Handler;
import flappyBird.FlappyHelper;

import java.awt.*;
import java.util.Random;

import static flappyBird.ObjectList.pipeList;
import static flappyBird.ObjectList.playerList;
import static flappyBird.FlappyHelper.*;

public class Pipe extends BaseObject {

    public Pipe(float x, float y, Image image, float velX, float velY) {
        super(x, y, image, velX, velY);
    }

    public Pipe(float x, float y, Image image) {
        super(x, y, image);

    }

    @Override
    public void tick() {

        if (FlappyHelper.startGame)
            x += velX;

        modfiyPipe();
    }

    private void modfiyPipe() {
        boolean needPipe = false;
        float pos = 0;
        for (BaseObject o : pipeList) {
            if (o.getX() + o.getImageWidth() < 0) {
                pos = o.getX();
                Handler.getInstance().removeObject(pipeList, o);
                needPipe = true;
                break;
            }
        }
        float dist = 100;
        if (needPipe) {

            for (BaseObject o : pipeList) {
                if (Math.abs(o.getX() - pos) < dist) {
                    dist = Math.abs(o.getX() - pos);
                }

            }
            for (BaseObject o : pipeList) {
                if (Math.abs(o.getX() - pos) == dist) {
                    Handler.getInstance().removeObject(pipeList, o);
                    break;
                }

            }
        }

        int lastPosPipe = 0;
        for (BaseObject o : pipeList) {
            lastPosPipe = Math.max((int) o.getX(), lastPosPipe);
        }

        if (needPipe) {
            ((Player) playerList.get(0)).lastScore++;
            Random rand = new Random();
            int initialY = rand.nextInt(maxHeight) + minHeight;
            Pipe pipeD = new Pipe(lastPosPipe + widthGap, initialY - pipeDown.getHeight(null), pipeDown, -1.5f, 0);
            Pipe pipeU = new Pipe(lastPosPipe + widthGap, initialY + heightGap, pipeUp, -1.5f, 0);

            Handler.getInstance().addObject(pipeList, pipeD);
            Handler.getInstance().addObject(pipeList, pipeU);
        }
    }

    @Override
    public void render(Graphics g) {
        if (FlappyHelper.startGame)
            g.drawImage(image, (int) x, (int) y, null);
    }
}

package flappyBird.board;

import atariCore.BaseObject;
import atariCore.Handler;
import flappyBird.FlappyHelper;

import java.awt.*;
import java.util.Random;

import static flappyBird.ObjectList.pipeList;
import static flappyBird.ObjectList.playerList;
import static flappyBird.FlappyHelper.*;

/**
 * Pipe class.
 */
public class Pipe extends BaseObject {

    /**
     * Parameterised constructor take x,y coordinates of the pipe, pipe image and x,y velocities of the pipe.
     *
     * @param x     X coordinate of the pipe.
     * @param y     Y coordinate of the pipe.
     * @param image image of the pipe.
     * @param velX  X velocity of the pipe.
     * @param velY  Y velocity of the pipe.
     */
    public Pipe(float x, float y, Image image, float velX, float velY) {
        super(x, y, image, velX, velY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

        if (FlappyHelper.startGame)
            x += velX;

        modfiyPipe();
    }

    /**
     * Update the pipe if it went out of screen and adds new pipe.
     */
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
            Random rand = new Random();
            int initialY = rand.nextInt(maxHeight) + minHeight;
            Pipe pipeD = new Pipe(lastPosPipe + widthGap, initialY - pipeDown.getHeight(null), pipeDown, -1.5f, 0);
            Pipe pipeU = new Pipe(lastPosPipe + widthGap, initialY + heightGap, pipeUp, -1.5f, 0);

            Handler.getInstance().addObject(pipeList, pipeD);
            Handler.getInstance().addObject(pipeList, pipeU);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        if (FlappyHelper.startGame)
            g.drawImage(image, (int) x, (int) y, null);
    }
}

package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;
import java.rmi.MarshalException;

import static arkanoid.arkHelper.*;

public class Ball extends BaseObject {

    Handler handler;
    Player player;

    float xOffset = Math.abs(xBallSpeed) * 3 / 2;
    float yOffset = Math.abs(yBallSpeed) * 3 / 2;

    public Ball(float xPosition, float yPosition, Image image, float xVelocity, float yVelocity, Handler handler , Player player) {
        super(xPosition, yPosition, image, xVelocity, yVelocity);
        this.handler = handler;
        this.player = player;
    }

    @Override
    public void tick() {

        y += velY;
        x += velX;

        if(velX == 0 && velY == 0)
            setX(player.paddle.get(0).getX() + Math.min(Math.abs( x -  player.paddle.get(0).getX()) , player.paddle.get(0).getImageWidth() - getImageWidth()) );

        collision();
        clamp();
    }

    @Override
    public void clamp() {

        if (arkHelper.screenWidth <= x + getImageWidth() ) velX = -1;
        if(x <= 0) velX = 1;
        if (y <= 0) velY *= -1;

        if (y > arkHelper.screenHeight) {
            handler.removeObject(this);
            Paddle currPaddle = player.paddle.get(0);
            boolean add = true;

            for (BaseObject o : handler.object)
                if (o instanceof Ball)
                    add = false;

            if (add)
            {
                System.out.println( y + " " + arkHelper.screenHeight);

                player.lostBall();
                for (BaseObject o : handler.object)
                    if (o instanceof Capsule)
                        handler.removeObject(o);

                Ball b = new Ball(currPaddle.getX() + currPaddle.getImageWidth()/2 - 5, INIT_BALL_Y, arkHelper.ball, 0, 0, handler, player);

                for(int i = 1; i < player.paddle.size(); i++)
                {
                    handler.removeObject(player.paddle.get(i));
                    player.paddle.remove(player.paddle.get(i));
                }

                currPaddle.sticky = false;
                currPaddle.laser = false;
                currPaddle.shrink = false;
                currPaddle.expand = false;
                handler.addObject(b);
            }
        }
    }

    private void collision() {

        int calcScore = 0;

        for (BaseObject o : handler.getObject()) {
            if ((o instanceof Brick || o instanceof Enemy)) {
                if (o.getRectangle().intersects(getRectangle())) {
                    calcScore++;

                    if( o instanceof Brick)  {

                           if (((Brick) o).hit() || (img == acidBall)) {

                               if (((Brick) o).capsule != null) {

                                   Capsule capsule = ((Brick) o).capsule;
                                   capsule.setX(o.getX());
                                   capsule.setY(o.getY());

                                   handler.addObject(capsule);
                               }

                               handler.removeObject(o);
                           }
                    }

                    if(img != acidBall) {

                        float hitRight = Math.abs(x - (o.getX() + o.getImageWidth()));
                        float hitLeft = Math.abs(x + getImageWidth() - o.getX());
                        float hitDown = Math.abs(y - (o.getY() + o.getImageHeight()));
                        float hitUp = Math.abs(y + getImageHeight() - o.getY());

                        if ((hitLeft < xOffset && velX > 0) || (hitRight < xOffset && velX < 0)) setVelX(velX * -1);
                        else if ((hitUp < yOffset && velY > 0) || (hitDown < yOffset && velY < 0)) setVelY(velY * -1);

                        break;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
    }

    public  void makeAcid() {

        img = acidBall;
    }

    public void makeFire() {

        img = fireBall;
    }
}
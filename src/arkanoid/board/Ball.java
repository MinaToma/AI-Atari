package arkanoid.board;

import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;

import java.awt.*;

import static arkanoid.ObjectList.*;
import static arkanoid.arkHelper.*;

public class Ball extends BaseObject {

    Player player;

    float xOffset = Math.abs(xBallSpeed) * 3 / 2;
    float yOffset = Math.abs(yBallSpeed) * 3 / 2;

    public Ball(float xPosition, float yPosition, Image image, float xVelocity, float yVelocity, Player player) {
        super(xPosition, yPosition, image, xVelocity, yVelocity);
        this.player = player;
    }

    @Override
    public void tick() {

        y += velY;
        x += velX;

        if (velX == 0 && velY == 0)
            setX(player.paddle.get(0).getX() + Math.min(Math.abs(x - player.paddle.get(0).getX()), player.paddle.get(0).getImageWidth() - getImageWidth()));

        collision();
        clamp();
    }

    @Override
    public void clamp() {

        if (arkHelper.screenWidth <= x + getImageWidth()) velX = -1;
        if (x <= 0) velX = 1;
        if (y <= 0) velY *= -1;

        if (y > arkHelper.screenHeight) {
            handler.removeObject(ballList, this);
            Paddle currPaddle = player.paddle.get(0);

            if (ballList.isEmpty()) {
                player.lostBall();
                for (BaseObject o : capsuleList)
                    handler.removeObject(capsuleList, o);

                Ball b = new Ball(currPaddle.getX() + currPaddle.getImageWidth() / 2 - 5, INIT_BALL_Y, arkHelper.ball, 0, 0, player);

                for (int i = 1; i < player.paddle.size(); i++) {
                    handler.removeObject(playerList, player.paddle.get(i));
                    player.paddle.remove(player.paddle.get(i));
                }

                currPaddle.sticky = false;
                currPaddle.laser = false;
                currPaddle.shrink = false;
                currPaddle.expand = false;
                for (BaseObject p : paddleList) {
                    for (BaseObject o : currentCapsuleList) {
                        ((Capsule) o).unEffect(((Paddle) p));
                        handler.removeObject(currentCapsuleList, o);
                    }
                }
                handler.addObject(ballList, b);

            }
        }
    }

    private void collision() {

        for (BaseObject o : brickList)
            if (o.getRectangle().intersects(getRectangle())) {
                if(image == fireBall)
                    ((Brick) o).setPower(0);

                ((Brick) o).hitBrick();

                if (image == ball || image == fireBall) {

                    setPosition(o);

                    break;
                }
            }


        for (BaseObject o : enemyList) {

            if (o.getRectangle().intersects(getRectangle())) {
                ((Enemy) o).reducePower();

                setPosition(o);

                player.setScore(player.getScore() + 10);

                break;
            }

        }


    }

    private void setPosition(BaseObject o) {

        float hitRight = Math.abs(x - (o.getX() + o.getImageWidth()));
        float hitLeft = Math.abs(x + getImageWidth() - o.getX());
        float hitDown = Math.abs(y - (o.getY() + o.getImageHeight()));
        float hitUp = Math.abs(y + getImageHeight() - o.getY());

        if ((hitLeft < xOffset && velX > 0) || (hitRight < xOffset && velX < 0)) {
            setVelX(velX * -1);
        }
        else if ((hitUp < yOffset && velY > 0) || (hitDown < yOffset && velY < 0)) {
            setVelY(velY * -1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public void makeAcid() {

        image = acidBall;
    }

    public void makeFire() {

        image = fireBall;
    }

    public void makeNormal() {
        image = ball;
    }
}
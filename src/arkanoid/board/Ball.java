package arkanoid.board;

import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import java.awt.*;

import static arkanoid.ObjectList.*;
import static arkanoid.arkHelper.*;

/**
 * Ball Class
 */
public class Ball extends BaseObject {

    /**
     * Player object
     */
    Player player;
    /**
     * Offset X distance to hit the brick.
     */
    private float xOffset = 5;
    /**
     * Offset Y distance to hit the brick.
     */
    private float yOffset = 5;

    /**
     * Parameterized constructor takes X, Y coordinates, x,y velocities and ball image.
     *
     * @param xPosition X coordinates of the ball.
     * @param yPosition Y coordinates of the ball.
     * @param image     Ball image.
     * @param xVelocity X Velocity of the ball.
     * @param yVelocity Y Velocity of the ball.
     * @param player    Player object.
     */
    public Ball(float xPosition, float yPosition, Image image, float xVelocity, float yVelocity, Player player) {
        super(xPosition, yPosition, image, xVelocity, yVelocity);
        this.player = player;
    }

    /**
     * Updates ball direction and position every frame.
     */
    @Override
    public void tick() {

        y += velY;
        x += velX;

        if (velX == 0 && velY == 0)
            setX(player.paddle.get(0).getX() + Math.min(Math.abs(x - player.paddle.get(0).getX()), player.paddle.get(0).getImageWidth() - getImageWidth()));

        collision();
        clamp();
    }

    /**
     * Responsible for ball collision with bricks and enemies.
     */
    private void collision() {

        for (BaseObject o : brickList)
            if (o.getRectangle().intersects(getRectangle())) {
                if (image == fireBall)
                    ((Brick) o).setPower(0);

                ((Brick) o).hitBrick();

                if (image == ball || image == fireBall) {
                    setBallDirectionAfterCollision(o);
                }
                break;
            }

        for (BaseObject o : enemyList)
            if (o.getRectangle().intersects(getRectangle())) {
                ((Enemy) o).reducePower();

                setBallDirectionAfterCollision(o);
                player.setScore(player.getScore() + 10);

                break;
            }
    }

    /**
     * Clamps the ball within frame bounds and checks if the paddle lost the ball.
     */
    @Override
    public void clamp() {

        if (Helper.screenWidth <= x + getImageWidth()) velX = -Math.abs(getVelX());
        if (x <= 0) velX = Math.abs(getVelX());
        if (y <= 0) velY = Math.abs(getVelY());

        if (y > Helper.screenHeight) {
            Handler.getInstance().removeObject(ballList, this);
            Paddle currPaddle = player.paddle.get(0);

            if (ballList.isEmpty()) {
                if (player.lostBall()) {
                    for (BaseObject o : capsuleList)
                        Handler.getInstance().removeObject(capsuleList, o);

                    Ball b = new Ball(currPaddle.getX() + currPaddle.getImageWidth() / 2 - 5, INIT_BALL_Y, arkHelper.ball, 0, 0, player);

                    paddleList.clear();
                    player.paddle.clear();

                    paddleList.add(currPaddle);
                    player.paddle.add(currPaddle);

                    for (BaseObject p : paddleList) {
                        ((Paddle) p).reset();
                    }

                    Handler.getInstance().addObject(ballList, b);
                }
            }
        }
    }

    /**
     * Responsible to reflect the ball and set its direction after brick or enemy collision.
     *
     * @param o : Brick or enemy object which hit the ball.
     */
    private void setBallDirectionAfterCollision(BaseObject o) {

        float hitRight = Math.abs(x - (o.getX() + o.getImageWidth()));
        float hitLeft = Math.abs(x + getImageWidth() - o.getX());
        float hitDown = Math.abs(y - (o.getY() + o.getImageHeight()));
        float hitUp = Math.abs(y + getImageHeight() - o.getY());

        if ((hitLeft <= xOffset && velX > 0) || (hitRight <= xOffset && velX < 0)){
            setVelX(velX * -1);
        } else if ((hitUp <= yOffset && velY > 0) || (hitDown <= yOffset && velY < 0)){
            setVelY(velY * -1);
        }
    }

    /**
     * Renders the ball on screen.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    /**
     * Changes the ball to acid ball.
     */
    public void makeAcid() {

        image = acidBall;
    }

    /**
     * Changes ball to fire ball.
     */
    public void makeFire() {

        image = fireBall;
    }

    /**
     * Returns the ball back to its normal state.
     */
    public void makeNormal() {
        image = ball;
    }

    /**
     * Changes ball's speed into the given speed.
     *
     * @param _x X velocity of the ball.
     * @param _y Y velocity of the ball.
     */
    public void setSpeed(float _x, float _y) {
        setVelX(_x);
        setVelY(_y);
    }
}
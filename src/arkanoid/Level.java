package arkanoid;

import arkanoid.board.*;
import atariCore.Handler;
import atariCore.Helper;

public class Level {

    public boolean[] dim;
    public Player player;
    public Brick[] bricks;
    public Paddle paddle;
    public Enemy enemy;
    public Ball ball;
    public Handler handler;

    public Level(boolean[] dim, Player player, int numOfBricks, Paddle paddle, Ball ball, Handler handler) {

        this.dim = dim;
        this.player = player;
        this.bricks = new Brick[numOfBricks];
        this.paddle = paddle;
        this.ball = ball;
        this.handler = handler;
    }

    public Level(boolean[] dim, Player player, int numOfBricks, Paddle paddle, Ball ball, Handler handler, Enemy enemy) {

        this(dim, player, numOfBricks, paddle, ball, handler);
        this.enemy = enemy;
    }

    public void loadBricks() {
        int numOfBricksInWidth = 5, iniheight = arkHelper.screenHeight / 2;

        bricks[0] = new Brick(0, iniheight, null, "red", 1);
        int number = bricks.length, bricksWidth = bricks[0].getImageWidth();
        int iniWidth = (arkHelper.screenWidth - (numOfBricksInWidth * bricksWidth)) / 2;

        bricks[0].setX(iniWidth);
        System.out.println(bricksWidth);
        for (int j = 0; number > 0; j++) {

            for (int i = 0; i < numOfBricksInWidth && number > 0; i++) {
                bricks[j * numOfBricksInWidth + i] = new Brick(iniWidth + (i * bricksWidth), iniheight, null, "red", 1);
                handler.addObject(bricks[j * numOfBricksInWidth + i]);
                number--;
            }
            iniheight -= (40);

        }
    }
}

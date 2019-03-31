package arkanoid;

import arkanoid.board.*;
import atariCore.Handler;
import atariCore.Helper;

import javax.swing.*;
import java.awt.*;

public class Level {

   public boolean[] dim;
   public Player player;
   public Brick[] bricks;
   public Paddle paddle;
   public Enemy enemy;
   public Ball ball;
   public Handler handler;

    public Level(boolean[] dim, Player player, int numOfBricks, Paddle paddle, Ball ball ,Handler handler) {
        this.dim = dim;
        this.player = player;
        this.bricks = new Brick[numOfBricks];
        this.paddle = paddle;
        this.ball = ball;
        this.handler = handler;
        //handler.addObject(paddle);
    }

    public Level(boolean[] dim, Player player, int numOfBricks, Paddle paddle,  Ball ball,Handler handler,Enemy enemy) {
        this(dim,player,numOfBricks,paddle,ball,handler);
        this.enemy = enemy;
    }

    public void loadBricks()
    {
        int numOfBricksInWidth = 5, iniheight = Helper.screenHeight/2;


        ImageIcon ii = new ImageIcon("src/Resources/image/11-Breakout-Tiles.png");
        Image l = ii.getImage();

        Image n = (new ImageIcon(l.getScaledInstance(l.getWidth(null) / 5 , l.getHeight(null) / 5 , Image.SCALE_SMOOTH))).getImage();


        bricks[0] = new Brick(0,iniheight,n,"red",1);
        int number = bricks.length, bricksWidth = bricks[0].getImageWidth();
        int iniWidth = (Helper.screenWidth-(numOfBricksInWidth*bricksWidth))/2;



        bricks[0].setX(iniWidth);
        System.out.println(bricksWidth);
        for(int j=0 ; number>0 ; j++)
        {
            for(int i = 0; i < numOfBricksInWidth  && number > 0; i++)
            {
                bricks[j*numOfBricksInWidth + i] = new Brick(iniWidth+(i*(bricksWidth) ) , iniheight , n , "red", 1);
                handler.addObject(bricks[j*numOfBricksInWidth + i]);
                number--;
            }
            iniheight -= (60);

        }
    }
}

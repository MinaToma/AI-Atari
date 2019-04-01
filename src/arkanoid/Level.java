package arkanoid;

import arkanoid.board.*;
import arkanoid.capsule.Capsule;
import arkanoid.capsule.Expand;
import arkanoid.capsule.Laser;
import atariCore.Handler;
import atariCore.Helper;

import java.util.ArrayList;

public class Level {

   public ArrayList < ArrayList < Integer > > dimensions;
   public Player player;
   public Brick[] bricks;
   public Paddle paddle;
   public Ball ball;
   public Handler handler;
   public Enemy enemy;


    public Level(ArrayList<ArrayList<Integer>> dimensions,Player player, Paddle paddle, Ball ball, Handler handler) {
        this.dimensions = dimensions;
        this.player = player;
        this.paddle = paddle;
        this.ball = ball;
        this.handler = handler;
        this.bricks = new Brick[dimensions.get(0).get(0)];
    }

    public void setBricks()
    {
        int initialHeight = Helper.screenHeight * 40 / 100;
        int index=0;
        int ok=0;

        Capsule capsule = new Expand(0 , 0 , 4 , arkHelper.capsuleExpand);

        for(ArrayList<Integer> arrayList : dimensions)
        {
            int sumOfWidth = 0;
            if(ok==0)
            {
                ok=1;
                continue;
            }
            for(int x : arrayList)
            {
                //System.out.print(x+" ");
                if(x<20 && x>0)
                {
                    sumOfWidth += arkHelper.normalBricks[0].getWidth(null);
                }
                else
                {
                    sumOfWidth += arkHelper.smallSquares[0].getWidth(null);
                }
            }

            int initialWidth = (Helper.screenWidth - sumOfWidth)/2;
            //System.out.println(initialWidth);
            for(int x : arrayList)
            {

                if(x>0 && x<20)
                {
                    //System.out.println(x);
                    bricks[index] = new Brick(initialWidth,initialHeight,arkHelper.normalBricks[(x-1)/2],2 , capsule);
                    initialWidth += arkHelper.normalBricks[0].getWidth(null);
                    handler.addObject(bricks[index]);
                    index++;
                }
                else if(x>=20)
                {
                    bricks[index] = new Brick(initialWidth,initialHeight,arkHelper.smallSquares[x-20],1);
                    initialWidth += arkHelper.smallSquares[0].getWidth(null);
                    handler.addObject(bricks[index]);
                    index++;
                }
                else
                {
                    initialWidth += arkHelper.smallSquares[0].getWidth(null);
                }

            }

            initialHeight -= (3+arkHelper.normalBricks[0].getHeight(null));
        }


    }


}

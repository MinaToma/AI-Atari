package arkanoid;

import arkanoid.board.*;
import arkanoid.capsule.*;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import static arkanoid.ObjectList.*;

public class Level {

    public ArrayList<ArrayList<Integer>> dimensions;
    public Player player;
    public Brick[] bricks;
    public Paddle paddle;
    public Ball ball;
    public Enemy enemy;


    public Level(ArrayList<ArrayList<Integer>> dimensions, Player player, Paddle paddle, Ball ball) {

        this.dimensions = dimensions;
        this.player = player;
        this.paddle = paddle;
        this.ball = ball;
        this.bricks = new Brick[dimensions.get(0).get(0)];
        arkHelper.numberOfBrics = bricks.length;
        setBricks();
    }

    public void setBricks() {

        int initialHeight = arkHelper.INIT_BRICKS_HEIGHT;
        int index = 0;
        int ok = 0;
        int numberOfCapsule = (bricks.length * 30) / 100;
        ArrayList<Integer> capsul = new ArrayList<>();
        Set<Integer> st = new HashSet<>();
        Random random = new Random();


        for (int i = 0; i < numberOfCapsule; i++) {
            st.add(Math.abs(random.nextInt()) % bricks.length);

        }

        for (Integer x : st) {
            // System.out.println(x);
            capsul.add(x);
        }
        int indexOfCapsul = 0;
        Collections.sort(capsul);
        numberOfCapsule = capsul.size();

        for (ArrayList<Integer> arrayList : dimensions) {
            int sumOfWidth = 0;
            if (ok == 0) {
                ok = 1;
                continue;
            }
            for (int x : arrayList) {
                if (x < 20 && x > 0) {
                    sumOfWidth += arkHelper.normalBricks[0].getWidth(null);
                } else {
                    sumOfWidth += arkHelper.smallSquares[0].getWidth(null);
                }
            }

            int initialWidth = (Helper.screenWidth - sumOfWidth) / 2;

            for (int x : arrayList) {
                if (x > 0 && x < 20) {
                    if (index == capsul.get(indexOfCapsul)) {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.normalBricks[(x - 1) / 2], 2, (x - 1) / 2, getCaps(Math.abs(random.nextInt() % 15 + 1)), player);
                        indexOfCapsul++;
                        indexOfCapsul %= numberOfCapsule;
                    } else {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.normalBricks[(x - 1) / 2], 2, (x - 1) / 2, player);
                    }
                    initialWidth += arkHelper.normalBricks[0].getWidth(null);


                    handler.addObject(brickList , bricks[index]);
                    index++;
                } else if (x >= 20) {
                    if (index == capsul.get(indexOfCapsul)) {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.smallSquares[x - 20], 1, x - 20, getCaps(Math.abs(random.nextInt() % 15 + 1)), player);
                        indexOfCapsul++;
                        indexOfCapsul %= numberOfCapsule;
                    } else {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.smallSquares[x - 20], 1, x - 20, player);
                    }
                    initialWidth += arkHelper.smallSquares[0].getWidth(null);
                    handler.addObject(brickList , bricks[index]);
                    index++;
                } else {
                    initialWidth += arkHelper.smallSquares[0].getWidth(null);
                }

            }

            initialHeight -= (3 + arkHelper.normalBricks[0].getHeight(null));
        }
    }

    public Capsule getCaps(int ID) {



        if (ID == 1) {
            return new Expand(0, 0, 5000, arkHelper.capsuleExpand);
        } else if (ID == 2) {
            return new Disrupt(0, 0, 0, arkHelper.capsuleTripleBall);
        } else if (ID == 3) {
            return new Laser(0, 0, 5000, arkHelper.capsuleWeapon);
        } else if (ID == 4) {
            return new Break(0, 0, 0, arkHelper.star);
        } else if (ID == 5) {
            return new Score(0, 0, 0, arkHelper.capsule50, 50);
        } else if (ID == 6) {
            return new Score(0, 0, 0, arkHelper.capsule250, 250);
        } else if (ID == 7) {
            return new Score(0, 0, 0, arkHelper.capsule500, 500);
        } else if (ID == 8) {
            return new Score(0, 0, 0, arkHelper.capsule100[0], 100);
        } else if (ID == 9) {
            return new Acid(0, 0, 5000, arkHelper.capsuleAcidBall);
        } else if (ID == 10) {
            return new FireBall(0, 0, 5000, arkHelper.capsuleFireBall);
        } else if (ID == 11) {
            return new Life(0, 0, 0, arkHelper.life);
        } else if (ID == 12) {
            return new Shrink(0, 0, 5000, arkHelper.capsuleShrink);
        } else if (ID == 13) {
            return new Catch(0, 0, 0, arkHelper.capsuleCatch);
        } else if (ID == 14) {
            return new Vaus(0, 0, 0, arkHelper.capsuleVaus);
        } else
            return new Slow(0, 0, 5000, arkHelper.capsuleSlow);
    }
}

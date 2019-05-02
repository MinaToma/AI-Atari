package arkanoid;

import arkanoid.board.*;
import arkanoid.capsule.*;
import atariCore.Handler;
import atariCore.Helper;

import java.util.*;

import static arkanoid.ObjectList.*;

/**
 * Level class to generate level's bricks and capsules.
 */
public class Level {
    /**
     * ArrayList has the number of bricks.
     * 0 for empty space.
     * from 1 to 100 id of normal bricks.
     * from 101 to 200 id of square bricks.
     */
    public ArrayList<ArrayList<Integer>> dimensions;
    /**
     * Player object
     */
    public Player player;
    /**
     * Array of Bricks.
     */
    public Brick[] bricks;
    /**
     * Paddle object.
     */
    public Paddle paddle;
    /**
     * Ball object.
     */
    public Ball ball;


    /**
     * Parameterised constructor takes dimensions, and player, paddle and and ball.
     *
     * @param dimensions Number of bricks.
     * @param player     Current player.
     * @param paddle     Current paddle.
     * @param ball       Current ball.
     */
    public Level(ArrayList<ArrayList<Integer>> dimensions, Player player, Paddle paddle, Ball ball) {

        this.dimensions = dimensions;
        this.player = player;
        this.paddle = paddle;
        this.ball = ball;
        this.bricks = new Brick[dimensions.get(0).get(0)];
        arkHelper.numberOfBricks = bricks.length;
        setBricks();
    }

    /**
     * Sets all bricks at their position and carried capsules.
     */
    public void setBricks() {

        int initialHeight = arkHelper.INIT_BRICKS_HEIGHT;
        int index = 0;
        int ok = 0;
        int numberOfCapsules = (bricks.length * 30) / 100;
        ArrayList<Integer> capsule = new ArrayList<>();
        Set<Integer> st = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < numberOfCapsules; i++) {
            st.add(Math.abs(random.nextInt()) % bricks.length);

        }

        for (Integer x : st) {
            capsule.add(x);
        }

        int indexOfCapsule = 0;
        Collections.sort(capsule);
        numberOfCapsules = capsule.size();

        for (ArrayList<Integer> arrayList : dimensions) {
            int sumOfWidth = 0;
            if (ok == 0) {
                ok = 1;
                continue;
            }
            for (int x : arrayList) {
                if (x > 0 && x < 100) {
                    sumOfWidth += arkHelper.normalBricks[0].getWidth(null);
                } else {
                    sumOfWidth += arkHelper.smallSquares[0].getWidth(null);
                }
            }

            int initialWidth = (Helper.screenWidth - sumOfWidth) / 2;

            for (int x : arrayList) {
                if (x > 0 && x < 100) {
                    if (index == capsule.get(indexOfCapsule)) {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.normalBricks[x - 1], 2, (x - 1), getCaps(Math.abs(random.nextInt() % 16 + 1)), player);
                        indexOfCapsule++;
                        indexOfCapsule %= numberOfCapsules;
                    } else {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.normalBricks[(x - 1)], 2, (x - 1), player);
                    }
                    initialWidth += arkHelper.normalBricks[0].getWidth(null);

                    Handler.getInstance().addObject(brickList, bricks[index]);
                    index++;
                } else if (x >= 101) {
                    if (index == capsule.get(indexOfCapsule)) {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.smallSquares[x - 101], 1, x - 101, getCaps(Math.abs(random.nextInt() % 16 + 1)), player);
                        indexOfCapsule++;
                        indexOfCapsule %= numberOfCapsules;
                    } else {
                        bricks[index] = new Brick(initialWidth, initialHeight, arkHelper.smallSquares[x - 101], 1, x - 101, player);
                    }

                    initialWidth += arkHelper.smallSquares[0].getWidth(null);
                    Handler.getInstance().addObject(brickList, bricks[index]);
                    index++;
                } else {
                    initialWidth += arkHelper.smallSquares[0].getWidth(null);
                }
            }

            initialHeight -= (3 + arkHelper.normalBricks[0].getHeight(null));
        }
    }

    /**
     * Returns the Capsule by it's ID.
     *
     * @param ID ID of the Capsule.
     * @return Capsule which carries the specified capsule.
     */
    public Capsule getCaps(int ID) {

        if (ID == 1) {
            return new Expand(0, 0, 5000, arkHelper.capsuleExpand);
        } else if (ID == 2) {
            return new Disrupt(0, 0, 0, arkHelper.capsuleTripleBall);
        } else if (ID == 3) {
            return new Laser(0, 0, 5000, arkHelper.capsuleWeapon);
        } else if (ID == 4 && player.getLevel() % 5 != 0) {
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
            return new Fire(0, 0, 5000, arkHelper.capsuleFireBall);
        } else if (ID == 11) {
            return new Life(0, 0, 0, arkHelper.life);
        } else if (ID == 12) {
            return new Shrink(0, 0, 5000, arkHelper.capsuleShrink);
        } else if (ID == 13) {
            return new Catch(0, 0, 0, arkHelper.capsuleCatch);
        } else if (ID == 14 && paddleList.size()<2) {
            return new Vaus(0, 0, 0, arkHelper.capsuleVaus);
        } else if (ID == 15) {
            return new Fast(0, 0, 0, arkHelper.capsuleFast);
        } else
            return new Slow(0, 0, 0, arkHelper.capsuleSlow);
    }
}

package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.AIEngine;

public class arkAIEngine extends AIEngine {

    static public int calculateReward(Player player , Ball ball , Paddle paddle) {

        double reward = 0;
        reward  = ((double) Math.sqrt((double) ((ball.getX() - (paddle.getX() + (paddle.getImageWidth() / 2))) *
                (ball.getX() - (paddle.getX() + (paddle.getImageWidth() / 2))))));
        reward  /= 1280;
        reward  *= 100;
        reward  += AIEngine.slack;
        reward  -= ((player.getScore() - player.getPreviousScore()) * 2000);

        if (paddle.getY() <= ball.getY())
            reward *= 100;

        rewards.add(-reward);
        System.out.println("the rewerd is " + (-reward));

        player.setPreviousScore(player.getScore());

        return 0;
    }
}

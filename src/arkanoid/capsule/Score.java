package arkanoid.capsule;

import arkanoid.arkHelper;
import arkanoid.board.Paddle;
import java.awt.*;
import static arkanoid.ObjectList.*;
import static arkanoid.arkHelper.*;

/**
 * Score capsule increases player score with one of the following values: 50, 100, 250.
 */
public class Score extends Capsule {
    public int score;
    int changePhoto;
    int timer;

    public Score(int x, int y, int life, Image image, int score) {
        super(x, y, life, image);
        this.score = score;
        timer=0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(Paddle p) {

        p.getPlayer().increaseScore(score);
        capsuleList.remove(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

        timer++;
        y += capsuleSpeed;
        if(score ==100 && timer>=25)
        {
            change();
            timer=0;
        }
    }

    /**
     * Updates the capsule image to add animated effect.
     */
    public void change() {

        this.image = arkHelper.capsule100[changePhoto++];
        changePhoto%=7;
    }
}

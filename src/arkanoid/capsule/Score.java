package arkanoid.capsule;

import arkanoid.arkHelper;
import arkanoid.board.Paddle;

import java.awt.*;

import static arkanoid.arkHelper.capsuleSpeed;

public class Score extends Capsule {
    public int score;
    int changePhoto;
    int timer;
    public Score(int x, int y, int life, Image image, int score) {
        super(x, y, life, image);
        this.score = score;
        timer=0;
    }

    @Override
    public void effect(Paddle p) {

    }
    @Override
    public void tick()
    {
        timer++;
        y += capsuleSpeed;
        if(score ==100 && timer>=25)
        {
            chang();
            timer=0;
        }

    }
    public void chang()
    {
        this.img = arkHelper.capsule100[changePhoto++];
        changePhoto%=7;
    }
}

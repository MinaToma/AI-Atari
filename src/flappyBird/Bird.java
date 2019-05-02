package flappyBird;

import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Sound;

import java.awt.*;
import java.util.ArrayList;

import static atariCore.Helper.*;
import static flappyBird.ObjectList.*;
import static flappyBird.FlappyHelper.*;

public class Bird extends BaseObject {

    private int numOfPic;
    public int previousScore;
    public int currentScore;
    public ArrayList<BaseObject> passedPips;

    public Bird(float x, float y, Image image) {
        super(x, y, image);
        numOfPic = 0;
        passedPips = new ArrayList<>();
        previousScore = 0;
        currentScore = 0;
    }

    @Override
    public void tick() {

        if (startGame) {
            setImage(birds[numOfPic++ / 20]);
            numOfPic %= 80;

            move();
            collision();

            getReward();
        }
    }

    private void move() {
        if (startGame)
            y += velY;

        velY += gravity;
    }

    public void speedUp() {

        velY = pressSpeed;
        if (!AIMode && sounds)
            Sound.Play(wingSound, true);
    }

    private void collision() {
        boolean delete = false;
        for (BaseObject p : ObjectList.pipeList)
            if (!delete && p.getRectangle().intersects(getRectangle())) {
                Handler.removeObject(birdList, this);
                delete = true;
            }

        Rectangle rec = getRectangle();
        if (rec.y <= 0 || rec.y + rec.height >= screenHeight && !delete)
            Handler.removeObject(birdList, this);

        if (birdList.size() == 0) {
            if (!AIMode && sounds)
                Sound.Play(hitSound, true);

            ((Player) playerList.get(0)).die();
        }
    }

    public Float getDistFromPipe() {
        float dist = 1e9f;
        for (BaseObject o : pipeList)
            if (o.getX() >= getX())
                dist = Math.min(dist, o.getX());

        return dist;
    }

    public float getCenterOfNextHole() {
        float uY = getDistFromUpperPipe();
        float dY = getDistFromLowerPipe();

        return (uY + dY) / 2;
    }

    public Float getDistFromUpperPipe() {

        float dist = getDistFromPipe();

        float re = 1e9f;
        for (BaseObject o : pipeList)
            if (dist == o.getX())
                re = Math.min(re, o.getY() + o.getImageHeight());

        return re;
    }

    public Float getDistFromLowerPipe() {
        float dist = getDistFromPipe();

        float re = -1e9f;
        for (BaseObject o : pipeList)
            if (dist == o.getX())
                re = Math.max(re, o.getY());

        return re;
    }

    public boolean getReward() {
        boolean passedPip = false;

        for (BaseObject o : pipeList)
            if (o.getY() > 0 && o.getX() + o.getImageWidth() /2f <= x && !passedPips.contains(o)) {
                passedPip = true;
                passedPips.add(o);
            }

        currentScore += passedPip ? 1 : 0;

        ((Player) playerList.get(0)).setScore(currentScore);
        if (passedPip && !AIMode && sounds)
            Sound.Play(pointSound, true);

        return passedPip;
    }

    public int getScoreDifference() {
        int dif = currentScore - previousScore;
        previousScore = currentScore;
        return dif;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) x + 10, (int) y + 5, imageWidth - 20, imageHeight - 10);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(this.image, (int) x, (int) y, null);
    }

}


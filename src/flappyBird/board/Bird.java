package flappyBird.board;

import atariCore.BaseObject;
import atariCore.Handler;

import atariCore.Sound;

import flappyBird.ObjectList;

import java.awt.*;
import java.util.ArrayList;

import static atariCore.Helper.*;
import static flappyBird.ObjectList.*;
import static flappyBird.FlappyHelper.*;

/**
 * Bird class.
 */
public class Bird extends BaseObject {

    private int numOfPic;
    /**
     * Stores previous score of the birds (needed for AI-Mode).
     */
    public int previousScore;
    /**
     * Stores current score of the birds (needed for AI-Mode).
     */
    public int currentScore;
    /**
     * Holds reference of game's pipes.
     */
    public ArrayList<BaseObject> passedPips;

    /**
     * Parameterised constructor takes x,y coordinates of bird and bird's image.
     *
     * @param x     X coordinate of the bird.
     * @param y     Y coordinate of the bird.
     * @param image image of the bird.
     */
    public Bird(float x, float y, Image image) {
        super(x, y, image);
        numOfPic = 0;
        passedPips = new ArrayList<>();
        previousScore = 0;
        currentScore = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

        if (startGame) {
            setImage(birds[numOfPic++ / 20]);
            numOfPic %= 80;

            move();
            collision();

            if (playerList.size() != 0)
                getReward();
        }
    }

    /**
     * Updates birds speed every frame.
     */
    private void move() {
        if (startGame)
            y += velY;

        velY += gravity;
    }

    /**
     * Performs bird fly action.
     */
    public void speedUp() {

        if (keyboard || AIMode) {
            velY = pressSpeed;
            if (!AIMode && sounds)
                Sound.Play(wingSound, true);
        }
    }

    /**
     * Checks if the birds collides with pipe or screen bounds.
     */
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

    /**
     * Returns distance to coming pipe.
     *
     * @return Distance to next pipe.
     */
    public Float getDistFromPipe() {
        float dist = 1e9f;
        for (BaseObject o : pipeList)
            if (o.getX() >= getX())
                dist = Math.min(dist, o.getX());

        return dist;
    }

    /**
     * Retruns the center of next pipe's gap.
     *
     * @return Center of next pipe gap.
     */
    public float getCenterOfNextHole() {
        float uY = getDistFromUpperPipe();
        float dY = getDistFromLowerPipe();

        return (uY + dY) / 2;
    }

    /**
     * Returns distance to next pipe's upper half.
     *
     * @return Distance to next pipe's upper half.
     */
    public Float getDistFromUpperPipe() {

        float dist = getDistFromPipe();

        float re = 1e9f;
        for (BaseObject o : pipeList)
            if (dist == o.getX())
                re = Math.min(re, o.getY() + o.getImageHeight());

        return re;
    }

    /**
     * Returns distance to next pipe's lower half.
     *
     * @return Distance to next pipe's lower half.
     */
    public Float getDistFromLowerPipe() {
        float dist = getDistFromPipe();

        float re = -1e9f;
        for (BaseObject o : pipeList)
            if (dist == o.getX())
                re = Math.max(re, o.getY());

        return re;
    }

    /**
     * Increase player's score when the birds passes a pipe.
     *
     * @return Whether the bird passed a pipe or not.
     */
    public boolean getReward() {
        boolean passedPip = false;

        for (BaseObject o : pipeList)
            if (o.getY() > 0 && o.getX() + o.getImageWidth() / 2f <= x && !passedPips.contains(o)) {
                passedPip = true;
                passedPips.add(o);
            }

        currentScore += passedPip ? 1 : 0;

        ((Player) playerList.get(0)).setScore(currentScore);
        if (passedPip && !AIMode && sounds)
            Sound.Play(pointSound, true);

        return passedPip;
    }

    /**
     * Returns difference between current score and previous score (needed for AI-Mode).
     *
     * @return Difference between current score and previous score.
     */
    public int getScoreDifference() {
        int dif = currentScore - previousScore;
        previousScore = currentScore;
        return dif;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) x + 10, (int) y + 5, imageWidth - 20, imageHeight - 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(this.image, (int) x, (int) y, null);
    }

}


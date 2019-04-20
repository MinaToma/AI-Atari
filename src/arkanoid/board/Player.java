package arkanoid.board;

import arkanoid.Arkanoid;
import arkanoid.arkAIEngine;
import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.AIMode;
import static atariCore.Helper.frame;

public class Player extends BaseObject {

    private int score;
    private String name;
    private int lives;
    public ArrayList<Paddle> paddle;
    public boolean start;
    private JPanel panel;
    private Arkanoid arkanoid;
    private int level;
    private int previousScore;


    public Player(String Name, int lives, Paddle paddle, JPanel panel, Arkanoid arkanoid) {
        super(10, 10, null);
        this.name = Name;
        this.lives = lives;
        this.paddle = new ArrayList<>();
        this.paddle.add(paddle);
        this.panel = panel;
        this.arkanoid = arkanoid;
        this.level = 1;

        previousScore = 0;
        score = 0;
        start = true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {

        if(score>0 && !AIMode) {
            Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
            panel.add(arkHelper.nextLevelImage);
            frame.setVisible(true);
            Sound.Play(arkHelper.winSound, true);

            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (Exception e) {

            }

            panel.remove(arkHelper.nextLevelImage);
            frame.setVisible(true);

            panel.requestFocusInWindow();
        }
        this.level = level;
        if (level % 10 == 1) {
            Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
        }
        arkanoid.initializeLevels(level);
    }

    public boolean lostBall() {
        lives--;

        if (lives < 0) {

            die();
        }

        return lives >= 0;
    }

    public void die() {
        arkHelper.arkfile.addNewScoreToLeadboard(arkHelper.pathLeaderboards, name, score, level);

        if (!AIMode) {
            arkHelper.running = false;


            Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
            frame.getContentPane().remove(panel);
            panel.add(arkHelper.lossImage);
            frame.getContentPane().add(panel);
            frame.setVisible(true);
            Sound.Play(arkHelper.lossSound, true);

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {

            }
            new arkanoid.menu.Splash();
        } else {

            //arkAIEngine.train();
            setScore(0);
            setPreviousScore(0);
            arkanoid.initializeLevels(1);
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int add) {
        score += add;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void tick() {
        if (arkHelper.backgroundGameSound[(level - 1) / 10].isStopped()) {
            Sound.Play(arkHelper.backgroundGameSound[(level - 1) / 10], false);
        }
    }

    public void render(Graphics g) {

        g.setFont(arkHelper.HUDFont);
        g.setColor(getLevelHUDColor());
        g.drawString(name, 10, 30);
        g.drawString(Integer.toString(score), 10, 100);
        g.drawString("Level " + level,10,60);
        drawLives(g);
    }

    private Color getLevelHUDColor() {
        Color color;
        if (level< 11) {
            color = new Color(55, 30, 20);
        } else if (level< 21) {
            color = new Color(255, 215, 34, 181);
        } else if (level< 31) {
            color = new Color(4, 21, 42, 225);
        } else if (level< 41) {
            color = new Color(43, 21, 11, 235);
        } else if (level< 51) {
            color = new Color(32, 75, 74);
        } else if (level< 61) {
            color = new Color(203, 82, 85, 219);
        } else if (level< 71) {
            color = new Color(32, 75, 74);
        } else if (level< 81) {
            color = new Color(173, 173, 173, 212);
        } else if (level< 91) {
            color = new Color(7, 51, 79, 156);
        } else {
            color = new Color(190, 110, 23, 214);
        }
        return color;
    }
    public void drawLives(Graphics g) {
        int numOfLives = lives;
        int initialHeight = 120;

        for (int i = 0; numOfLives > 0; i++) {
            int initialWidth = 10;
            for (int j = 0; j < 3 && numOfLives > 0; j++) {
                g.drawImage(arkHelper.life, initialWidth, initialHeight, null);
                initialWidth += arkHelper.life.getWidth(null) + 2;
                numOfLives--;
            }

            initialHeight += arkHelper.life.getHeight(null) + 2;
        }
    }

    public int getPreviousScore() {
        return previousScore;
    }

    public void setPreviousScore(int previousScore) {
        this.previousScore = previousScore;
    }
}

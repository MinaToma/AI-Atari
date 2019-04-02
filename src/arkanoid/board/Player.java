package arkanoid.board;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Splash;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player extends BaseObject {

    private int score;
    private String name;
    private int lives;
    public ArrayList<Paddle> paddle;
    public boolean start;
    private JPanel panel;
    private Arkanoid arkanoid;
    private int level;

    public Player(String Name, int lives, Paddle paddle , JPanel panel , Arkanoid arkanoid) {
        super(10 , 10 , null);
        this.name = Name;
        this.lives = lives;
        this.paddle = new ArrayList<>();
        this.paddle.add(paddle);
        this.panel = panel;
        this.arkanoid = arkanoid;
        this.level = 1;

        score = 0;
        start = true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        arkanoid.intialLevels(level);

    }

    public void lostBall() {
        lives--;

        if(lives < 0) {

            arkHelper.running = false;
            new arkanoid.menu.Splash();
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
    }

    public void render(Graphics g) {

        g.setFont(arkHelper.font);
        g.drawString(name, 10, 30);
        g.drawString(Integer.toString(score), 10, 60);
        g.drawString("Level"+level,arkHelper.screenWidth-200,30);
        drawLives(g);
    }

    public void drawLives(Graphics g) {
        int numOfLives = lives;
        int initialHeight = 60;

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
}

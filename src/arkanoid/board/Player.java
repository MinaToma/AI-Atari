package arkanoid.board;

import atariCore.BaseObject;

import java.awt.*;

public class Player extends BaseObject {

    private int score;
    private String name;
    private int lives;

    public Player(String Name, int lives) {
        super(10 , 10 , null);
        this.name = Name;
        this.lives = lives;
        score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int add) {score += add;}

    public int getScore() {
        return score;
    }

    @Override
    public void tick() { }

    public void render(Graphics g) {

        g.drawString(name, 10, 10);
        g.drawString(Integer.toString(score) , 10 , 30);
    }
}

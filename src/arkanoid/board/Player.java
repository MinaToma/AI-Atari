package arkanoid.board;

import arkanoid.arkHelper;
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
        g.setColor(Color.ORANGE);
        g.drawString(name, 10, 10);
        g.drawString(Integer.toString(score) , 10 , 30);
        drawLives(g);
    }
    public void drawLives(Graphics g)
    {
        int numOfLives=lives;
        int initialHeight= 60;

        for(int i=0 ; numOfLives>0 ; i++)
        {
            int initialWidth = 10;
            for(int j=0 ; j<3 && numOfLives>0 ; j++)
            {
                g.drawImage(arkHelper.life,initialWidth,initialHeight,null);
                initialWidth += arkHelper.life.getWidth(null)+2;
                numOfLives--;
            }
            initialHeight += arkHelper.life.getHeight(null) +2;

        }



    }
}

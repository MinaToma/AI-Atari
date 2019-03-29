package arkanoid.board;

import java.awt.*;

public class Player {
    private int score ;
    private String name ;
    private int lives;
    public Player(String Name , int lives ){
        this.name = Name ;
        this.lives = lives;
        score = 0 ;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void render(Graphics g){
        g.drawString(name,10 , 10);
    }


}

package atariCore;

import java.awt.*;

public class Score{

   private int score;
   private int x, y ;

   public Score(int score , int x , int y)
   {
       this.score = score;
       this.x = x;
       this.y = y;

   }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawScore(Graphics g)
   {
       g.drawString("Score : "+score , x ,y);
   }


}

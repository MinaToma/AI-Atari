

package arkanoid.board;
import arkanoid.Common;
import atariCore.BaseObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends BaseObject implements Common {


    public Paddle(int xPostion , int yPostion , Image image, int velX, int velY){
        super(xPostion,yPostion,image, velX ,0);


    }

    public void tick(){
        super.x += super.velX ;

        if(super.x<=0)
            x= 0 ;
        if(super.x >= WIDTH - imageWidth )
            x = WIDTH - imageWidth;
    }

    public void render(Graphics g){
        g.drawImage(super.img, super.x ,super.y, null);

    }



    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            super.velX = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.velX  = 1;
        }
    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            super.velX = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.velX = 0;
        }
    }



}




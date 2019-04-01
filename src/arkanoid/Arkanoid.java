package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.FileInOut;
import atariCore.Helper;
import java.awt.event.KeyEvent;

import static arkanoid.arkHelper.paddleSpeed;

public class Arkanoid extends atariCore.Game {

    Ball b;
    Paddle p;

    public Arkanoid() {
        super("Arkanoid");

        new arkHelper();
        setBall();
        setEnemy();
        setPlayer();
        setPaddle();
        setBricks();
    }

    private void setBall() {

        b = new Ball(Helper.screenWidth * 40 / 100, Helper.screenHeight * 80 / 100 , arkHelper.ball, 1, -1 , handler);
        handler.addObject(b);
    }

    private void setPlayer() {

    }

    private void setEnemy() {

    }

    private void setBricks() {

        FileInOut fileInOut = new FileInOut();
        Level level = new Level(fileInOut.getLevel("Level12"),new Player("m7m7",2), p , b ,handler);
        level.setBricks();
    }

    private void setPaddle() {

         p = new Paddle(Helper.screenWidth * 40 / 100 , Helper.screenHeight * 90 / 100 , arkHelper.paddle[0], 0, 0, handler);
         handler.addObject(p);
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            p.setVelX(-paddleSpeed) ;
        } else if (key == KeyEvent.VK_RIGHT) {

            p.setVelX(paddleSpeed);
        }
        else if (key == KeyEvent.VK_SPACE) {

            if(p.laser)
                p.hitLaser();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        }
    }
}
package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.FileInOut;
import atariCore.Helper;
import java.awt.event.KeyEvent;

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

        b = new Ball(Helper.screenWidth * 40 / 100, Helper.screenHeight * 80 / 100 , arkHelper.ball, 2, -2 , handler);
    /*    Ball b2 = new Ball(Helper.screenWidth * 20 / 100, Helper.screenHeight * 80 / 100 , arkHelper.ball, 2, -1 , handler);
        Ball b3 = new Ball(Helper.screenWidth * 20 / 100, Helper.screenHeight * 80 / 100 , arkHelper.ball, 3, -1 , handler);

        handler.addObject(b3);
        handler.addObject(b2);
        */handler.addObject(b);
    }

    private void setPlayer() {

    }

    private void setEnemy() {

    }

    private void setBricks() {
        FileInOut fileInOut = new FileInOut();
        Level level = new Level(fileInOut.getLevel("Level1"),new Player("m7m7",3), p , b ,handler);
        level.setBricks();
    }

    private void setPaddle() {

         p = new Paddle(Helper.screenWidth * 40 / 100 , Helper.screenHeight * 90 / 100 , arkHelper.paddleExpanded, 0, 0, handler);
         handler.addObject(p);
        //Brick brick = new Brick(0,0,null,"red",1);
        //handler.addObject(brick);

    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Paddle) {

                if (key == KeyEvent.VK_LEFT) {
                    o.setVelX(-5);
                } else if (key == KeyEvent.VK_RIGHT) {
                    o.setVelX(5);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (BaseObject o : handler.getObject()) {

            if (o instanceof Paddle) {

                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    o.setVelX(0);
                } else if (key == KeyEvent.VK_RIGHT) {
                    o.setVelX(0);
                }
            }
        }
    }
}
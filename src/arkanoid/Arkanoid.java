package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.Helper;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Arkanoid extends atariCore.Game {

    public Arkanoid() {

        super("Arkanoid");

        //  setBall();
        setEnemy();
        setPlayer();
        setPaddle();
        setBricks();
    }

    private void setBall() {

        //    Ball ball = new Ball();
        //    handler.addObject(ball);
    }

    private void setPlayer() {

    }

    private void setEnemy() {

    }

    private void setBricks() {

    }

    private void setPaddle() {

        Paddle p = new Paddle(Helper.screenWidth / 3, Helper.screenHeight / 3, null, 0, 0, handler);
        handler.addObject(p);
        p = new Paddle(Helper.screenWidth / 3, Helper.screenHeight / 2, null, 0, 0, handler);
        handler.addObject(p);
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
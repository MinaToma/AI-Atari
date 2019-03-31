package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static arkanoid.arkHelper.xSpeed;
import static arkanoid.arkHelper.ySpeed;

public class Arkanoid extends atariCore.Game {

    Paddle p;
    Ball b;

    public Arkanoid() {

        super("Arkanoid");
        new arkHelper();

        setBall();
        //setEnemy();
        //setPlayer();
        setPaddle();
        setBricks();
    }

    private void setBall() {

        b = new Ball(Helper.screenWidth * 45 / 100 , Helper.screenHeight * 82 / 100 , arkHelper.ball , xSpeed , -ySpeed , handler);

        handler.addObject(b);
    }

    private void setPlayer() {

    }

    private void setEnemy() {

    }

    private void setBricks() {
        boolean[] dim = new boolean[5];
        Level l = new Level(dim, new Player("ahmed", 2), 30, p , b , handler);
        l.loadBricks();
    }

    private void setPaddle() {
         ImageIcon ii = new ImageIcon("src/Resources/image/49-Breakout-Tiles.png");
         Image i = ii.getImage();

         Image n = (new ImageIcon(i.getScaledInstance(i.getWidth(null) / 5 , i.getHeight(null) / 5 , Image.SCALE_SMOOTH))).getImage();

        p = new Paddle(Helper.screenWidth * 45 / 100 , Helper.screenHeight * 85 / 100 , n , 0, 0, handler);
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
                    o.setVelX(-2);
                } else if (key == KeyEvent.VK_RIGHT) {
                    o.setVelX(2);
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
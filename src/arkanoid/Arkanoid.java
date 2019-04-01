package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.FileInOut;
import atariCore.Helper;
import atariCore.Sounds;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static arkanoid.arkHelper.*;

public class Arkanoid extends atariCore.Game {

    Ball b;
    Paddle p;
    Player player;

    public Arkanoid() {
        super("Arkanoid");

        new arkHelper();
        setPlayer();
        setPaddle();
        setBall();
        setEnemy();
        setBricks();
        setMusic();
    }
    private void setMusic()
    {
        Sounds s = new Sounds();
        //s.backgroundGame.start();
        //s.backgroundGame.loop(5);
    }
    private void setBall() {

        b = new Ball(INIT_BALL_X, INIT_BALL_Y , arkHelper.ball, xSpeed , ySpeed , handler , player);
        handler.addObject(b);
    }

    private void setPlayer() {

        player = new Player("Gazra" , 1);
        handler.addObject(player);
    }

    private void setEnemy() {

    }

    private void setBricks() {

        FileInOut fileInOut = new FileInOut();
        Level level = new Level(fileInOut.getLevel("Level12"),player, p , b ,handler);
        level.setBricks();
    }

    private void setPaddle() {

         p = new Paddle(INIT_PADDLE_X, INIT_PADDLE_Y, arkHelper.paddle[0], 0, 0, handler , player);
         handler.addObject(p);
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            p.setVelX(-paddleSpeed) ;
            //System.out.print(1);
        } else if (key == KeyEvent.VK_RIGHT) {

            p.setVelX(paddleSpeed);
            //System.out.print(2);
        }
        else if (key == KeyEvent.VK_SPACE) {

            paddleClick();
        }
    }

    public void paddleClick() {
        if(p.laser) {

            p.hitLaser();
        }

        if(p.sticky) {

            for(BaseObject o : handler.getObject()) {
                if(o instanceof Ball) {
                    o.setVelX(xSpeed);
                    o.setVelY(ySpeed);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        paddleClick();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        if(b.getX() != INIT_BALL_X)
             p.setX(mouseEvent.getX());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
            //System.out.print(3);
        } else if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
           // System.out.print(4);
        }
    }
}
package arkanoid;

import arkanoid.board.Background;
import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.FileInOut;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

public class Arkanoid extends atariCore.Game {

    Ball b;
    Paddle p;
    Player player;

    public Arkanoid(String namePlayer) {

        super("Arkanoid");
        arkHelper.setCursorImage(this, "src/Resources/image/yellowc2.png");

        setPaddle();
        setPlayer(namePlayer);

        intialLevels(player.getLevel());
    }

    public void intialLevels(int level)
    {
        setBackGround();
        handler.addObject(playerList , player);
        handler.addObject(paddleList , p);

        setBall();
        setEnemy();
        setBricks(level);
    }

    private void setBackGround()
    {
        Background background = new Background(0,0, backgroundImage[0]);
        handler.addObject(new CopyOnWriteArrayList<>() , background);
    }

    public void setBall() {

        b = new Ball(player.paddle.get(0).getX() + player.paddle.get(0).getImageWidth()/2 - 5, INIT_BALL_Y , arkHelper.ball, 0, 0 , player);
        handler.addObject(ballList , b);
    }

    private void setPlayer(String namePlayer) {

        player = new Player(namePlayer , 3, p , this , this);
        p.setPlayer(player);
        handler.addObject(playerList , player);
    }

    private void setEnemy() {

    }

    private void setBricks(int lvl) {

        FileInOut fileInOut = new FileInOut();
        Level level = new Level(fileInOut.getLevel("Level"+lvl, pathLevel),player, p , b );
    }

    private void setPaddle() {

         p = new Paddle(INIT_PADDLE_X, INIT_PADDLE_Y, arkHelper.paddle[0], 0, 0,  player);
         handler.addObject(paddleList , p);
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

            paddleClick();
        }
    }

    public void paddleClick() {
        if(p.laser) {

            p.hitLaser();

        }

        for(BaseObject o : ballList) {
            if(o.getVelX() == 0 && o.getVelY() == 0) {
                p.sticky = false;
                o.setVelX(-p.getNewVx(o.getX() + o.getImageWidth() / 2));
                o.setVelY(yBallSpeed);
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

        if(b.getX() != INIT_BALL_X && mouseEvent.getX()<arkHelper.screenWidth-p.getImageWidth()+3)
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
package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.FileInOut;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static arkanoid.arkHelper.*;

public class Arkanoid extends atariCore.Game {

    Ball b;
    Paddle p;
    Player player;


    public Arkanoid() {

        super("Arkanoid");
        arkHelper.setCursorImage(this, "src/Resources/image/yellowc2.png");

        new arkHelper();
        setPaddle();
        setPlayer();

        intialLevels(player.getLevel());

    }
    public void intialLevels(int level)
    {
        handler.object.clear();

        setBall();
        setEnemy();
        setBricks(level);
        setMusic();
        handler.addObject(player);
        handler.addObject(p);
    }
    private void setMusic()
    {
       // Sounds s = new Sounds();
        //s.backgroundGame.start();
        //s.backgroundGame.loop(5);
    }

    public void setBall() {

        b = new Ball(player.paddle.get(0).getX() + player.paddle.get(0).getImageWidth()/2 - 5, INIT_BALL_Y , arkHelper.ball, 0, 0, handler , player);
        handler.addObject(b);
    }

    private void setPlayer() {

        player = new Player("M7M7" , 3, p , this , this);
        p.setPlayer(player);
        handler.addObject(player);
    }

    private void setEnemy() {

    }

    private void setBricks(int lvl) {

        FileInOut fileInOut = new FileInOut();
        Level level = new Level(fileInOut.getLevel("Level"+lvl),player, p , b ,handler);

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

        for(BaseObject o : handler.object) {
            if(o instanceof Ball && o.getVelX() == 0 && o.getVelY() == 0) {
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
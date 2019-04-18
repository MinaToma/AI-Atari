package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.BaseObject;
import atariCore.FileInOut;
import atariCore.Helper;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import static arkanoid.arkHelper.*;
public class Arkanoid extends atariCore.Game {

    public Ball b;
    public Paddle p;
    public Player player;
    int frameCounter = 0;

    public Arkanoid(String namePlayer) throws IOException {

        super("Arkanoid");
        arkHelper.setCursorImage(this, "src/Resources/image/yellowc2.png");

        new arkHelper();
        setPaddle();
        setPlayer(namePlayer);

        intialLevels(player.getLevel());
    }

    public void intialLevels(int level) throws IOException {

        Random rand = new Random();
        double start [] = {Helper.screenWidth * 0.1,Helper.screenWidth * 0.2,Helper.screenWidth * 0.3,Helper.screenWidth * 0.4 , Helper.screenWidth*0.5,Helper.screenWidth * 0.6 ,Helper.screenWidth*0.7,Helper.screenWidth * 0.8, Helper.screenWidth * 0.9 };


        int n = Math.abs(rand.nextInt()%(9));
        System.out.println(n);
        handler.object.clear();
        n = Math.abs(rand.nextInt()%1200);
        p.setX((float)start[0]);
//        p.setX((float)(n));
//        p.setX((Helper.screenWidth/2)-(p.getImageWidth()/2));
        p.setVelX(0);
        handler.addObject(player);
        handler.addObject(p);

        setBall();
        setEnemy();
        setBricks(level);
        setMusic();



    }
    private void setMusic()
    {

    }

    public void setBall() {

        b = new Ball(player.paddle.get(0).getX() + player.paddle.get(0).getImageWidth()/2 - 5, INIT_BALL_Y , arkHelper.ball, 0, 0, handler , player);
        handler.addObject(b);
    }

    private void setPlayer(String namePlayer) {

        player = new Player(namePlayer , 3, p , this , this);
        p.setPlayer(player);
        handler.addObject(player);
    }

    private void setEnemy() {

    }

    private void setBricks(int lvl) {

        FileInOut fileInOut = new FileInOut();
        Level level = new Level(fileInOut.getLevel("Level"+lvl, pathLevel),player, p , b ,handler);

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

            try {
                paddleClick();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void paddleClick() throws IOException {
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

        try {
            paddleClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
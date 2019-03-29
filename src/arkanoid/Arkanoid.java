package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.Helper;

import javax.swing.*;

public class Arkanoid extends atariCore.Game {

   public Arkanoid() {

        super("Arkanoid" , new ArkanoidKeyAdapter());

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

        Paddle p = new Paddle(Helper.screenWidth / 3 , Helper.screenHeight / 3 , null , 0 , 0 , handler);
        handler.addObject(p);
    }
}
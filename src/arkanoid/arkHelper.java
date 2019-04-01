package arkanoid;

import atariCore.Helper;

import javax.swing.*;
import java.awt.*;

public class arkHelper extends Helper {


    public static final int BOTTOM_EDGE = 390;
    public static final int N_OF_BRICKS = 30;
    public static final int INIT_PADDLE_X = 200;
    public static final int INIT_PADDLE_Y = 360;
    public static final int INIT_BALL_X = 230;
    public static final int INIT_BALL_Y = 355;
    public static float paddleSpeed = 5;
    public static float xSpeed = 1.5f;
    public static float ySpeed = 1.5f;
    public static float capsuleSpeed = 1;

    public static Image[] normalBricks;
    public static Image[] brokenBricks;
    public static Image[] smallSquares;
    public static Image[] paddle;
    public static Image[] paddleWeapon;
    public static Image paddleShrunk;
    public static Image paddleExpanded;
    public static Image capsuleEmpty;
    public static Image capsule50;
    public static Image[] capsule100;
    public static Image capsule250;
    public static Image capsule500;
    public static Image capsuleSlow;
    public static Image capsuleFast;
    public static Image capsuleShrink;
    public static Image capsuleExpand;
    public static Image capsuleWeapon;
    public static Image tripleBall;
    public static Image fireBall;
    public static Image acidBall;
    public static Image ball;
    public static Image star;
    public static Image life;
    public static Image bullet;
    String path = "src/Resources/image/";

    public arkHelper() {

        paddle = new Image[3];
        capsule100 = new Image[7];
        paddleWeapon = new Image[3];
        normalBricks = new Image[10];
        brokenBricks = new Image[10];
        smallSquares = new Image[10];

        loadImages();

    }

    private void loadImages() {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 1)
                normalBricks[i / 2] = getImage(path + (i < 10 ? "0" + i : i) + "-Breakout-Tiles.png");
            else
                brokenBricks[i / 2 - 1] = getImage(path + (i < 10 ? "0" + i : i) + "-Breakout-Tiles.png");
        }

        for (int i = 21; i <= 30; i++)
            smallSquares[i - 21] = getImage(path + i + "-Breakout-Tiles.png");

        capsule50 = getImage(path + "31-Breakout-Tiles.png");

        for (int i = 32; i <= 38; i++)
            capsule100[i - 32] = getImage(path + i + "-Breakout-Tiles.png");

        capsule250 = getImage(path + "39-Breakout-Tiles.png");
        capsule500 = getImage(path + "40-Breakout-Tiles.png");
        capsuleSlow = getImage(path + "41-Breakout-Tiles.png");
        capsuleFast = getImage(path + "42-Breakout-Tiles.png");
        tripleBall = getImage(path + "43-Breakout-Tiles.png");
        fireBall = getImage(path + "44-Breakout-Tiles.png");
        acidBall = getImage(path + "45-Breakout-Tiles.png");

        capsuleShrink = getImage(path + "46-Breakout-Tiles.png");
        capsuleExpand = getImage(path + "47-Breakout-Tiles.png");
        capsuleWeapon = getImage(path + "48-Breakout-Tiles.png");
        capsuleEmpty = getImage(path + "49-Breakout-Tiles.png");

        for (int i = 50; i <= 52; i++)
            paddle[i - 50] = getImage(path + i + "-Breakout-Tiles.png");

        for (int i = 53; i <= 55; i++)
            paddleWeapon[i - 53] = getImage(path + i + "-Breakout-Tiles.png");

        paddleExpanded = getImage(path + "56-Breakout-Tiles.png");
        paddleShrunk = getImage(path + "57-Breakout-Tiles.png");
        ball = getImage(path + "58-Breakout-Tiles.png");
        star = getImage(path + "59-Breakout-Tiles.png");
        life = getImage(path + "60-Breakout-Tiles.png");
        bullet = getImage(path + "61-Breakout-Tiles.png");
    }
}

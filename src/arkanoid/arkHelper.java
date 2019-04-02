package arkanoid;

import atariCore.Helper;
import atariCore.Splash;

import javax.swing.*;
import java.awt.*;

public class arkHelper extends Helper {

    public static final int INIT_BRICKS_HEIGHT = screenHeight * 40 / 100;
    public static final int INIT_PADDLE_X = screenWidth * 40 / 100;
    public static final int INIT_PADDLE_Y = screenHeight * 85 / 100;
    public static final int INIT_BALL_X = screenWidth * 43 / 100;
    public static final int INIT_BALL_Y = screenHeight * 82 / 100;
    public static int BRICKHITREWARD = 10;
    public static float paddleSpeed = 5;
    public static float xSpeed = 2f;
    public static float ySpeed = 2f;
    public static float capsuleSpeed = 1;

    public static Image[] normalBricks;
    public static Image[] brokenBricks;
    public static Image[] smallSquares;
    public static Image[] paddle;
    public static Image[] paddleWeapon;
    public static Image paddleShrunk;
    public static Image paddleExpanded;
    public static Image paddleExpandedWeapon;
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
    public static Image capsuleFireBall;
    public static Image capsuleAcidBall;
    public static Image capsuleTripleBall;
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
        capsuleTripleBall = getImage(path + "43-Breakout-Tiles.png");
        capsuleFireBall = getImage(path + "44-Breakout-Tiles.png");
        capsuleAcidBall = getImage(path + "45-Breakout-Tiles.png");

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
        paddleExpandedWeapon = getImage(path + "62-Breakout-Tiles.png");
        fireBall = getImage(path + "63-Breakout-Tiles.png");
        acidBall = getImage(path + "64-Breakout-Tiles.png");

    }
}

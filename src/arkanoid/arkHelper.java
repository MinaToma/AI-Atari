package arkanoid;

import atariCore.Helper;
import atariCore.Splash;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;
import java.net.URL;

public class arkHelper extends Helper {

    public static int ballScale = 6;
    public static int paddleScale = 4;
    public static int brickScale = 3;
    public static int capsuleScale = 5;
    public static int cursorScale = 3;

    public static final int INIT_BRICKS_HEIGHT = screenHeight * 40 / 100;
    public static final int INIT_PADDLE_X = screenWidth * 40 / 100;
    public static final int INIT_PADDLE_Y = screenHeight * 85 / 100;
    public static final int INIT_BALL_X = screenWidth * 43 / 100;
    public static final int INIT_BALL_Y = screenHeight * 82 / 100;
    public static int BRICKHITREWARD = 10;
    public static float paddleSpeed = 5;
    public static float xBallSpeed = 1f;
    public static float yBallSpeed = -2f;
    public static float capsuleSpeed = 1;

    public static Image[] normalBricks;
    public static Image[] brokenBricks;
    public static Image[] smallSquares;
    public static Image[] paddle;
    public static Image[] paddleWeapon;
    public static Image paddleShrunkWeapon;
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
    public static Image capsuleVaus;
    public static Image capsuleCatch;
    //public static Image paddleShrunkWeapon;

    public static Image fireBall;
    public static Image acidBall;
    public static Image ball;
    public static Image star;
    public static Image life;
    public static Image bullet;
    public static String  path = "src/Resources/image/";
    public static MediaPlayer hitSound;
    public static MediaPlayer backgroundSplashSound;
    public static MediaPlayer backgroundGameSound;
    public static MediaPlayer lazerSound;


    public arkHelper() {

        paddle = new Image[3];
        capsule100 = new Image[7];
        paddleWeapon = new Image[3];
        normalBricks = new Image[10];
        brokenBricks = new Image[10];
        smallSquares = new Image[10];

        loadImages();

    }

    public static void lazerSound()
    {
        playSound("lazer.wav",0);
    }
    public static void hitSound()
    {
        playSound("hit.wav",0);
    }
    public static void BackgroundGameSound()
    {
        playSound("background.wav",0);
    }
    public static void backgroundSplashSound()
    {
        playSound("intro_sound.wav",100);
    }








    public static void playSound(String path , int loop) {


        try
        {

            URL url = arkHelper.class.getClassLoader().getResource("Resources/Sounds/"+path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            if(loop>0)
            {
                clip.loop(loop);
            }
            else if(loop==0)
                clip.start();


        }
        catch (Exception e)
        {
            ////
        }




    }

    private void loadImages() {
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 1)
                normalBricks[i / 2] = getImage(path + (i < 10 ? "0" + i : i) + "-Breakout-Tiles.png", brickScale);
            else
                brokenBricks[i / 2 - 1] = getImage(path + (i < 10 ? "0" + i : i) + "-Breakout-Tiles.png", brickScale);
        }

        for (int i = 21; i <= 30; i++)
            smallSquares[i - 21] = getImage(path + i + "-Breakout-Tiles.png", brickScale);

        capsule50 = getImage(path + "31-Breakout-Tiles.png", capsuleScale);

        for (int i = 32; i <= 38; i++)
            capsule100[i - 32] = getImage(path + i + "-Breakout-Tiles.png", capsuleScale);

        capsule250 = getImage(path + "39-Breakout-Tiles.png", capsuleScale);
        capsule500 = getImage(path + "40-Breakout-Tiles.png", capsuleScale);
        capsuleSlow = getImage(path + "41-Breakout-Tiles.png", capsuleScale);
        capsuleFast = getImage(path + "42-Breakout-Tiles.png", capsuleScale);
        capsuleTripleBall = getImage(path + "43-Breakout-Tiles.png", capsuleScale);
        capsuleFireBall = getImage(path + "44-Breakout-Tiles.png", capsuleScale);
        capsuleAcidBall = getImage(path + "45-Breakout-Tiles.png", capsuleScale);
        capsuleShrink = getImage(path + "46-Breakout-Tiles.png",capsuleScale);
        capsuleExpand = getImage(path + "47-Breakout-Tiles.png", capsuleScale);
        capsuleWeapon = getImage(path + "48-Breakout-Tiles.png", capsuleScale);
        capsuleEmpty = getImage(path + "49-Breakout-Tiles.png", capsuleScale);

        for (int i = 50; i <= 52; i++)
            paddle[i - 50] = getImage(path + i + "-Breakout-Tiles.png", paddleScale);

        for (int i = 53; i <= 55; i++)
            paddleWeapon[i - 53] = getImage(path + i + "-Breakout-Tiles.png", paddleScale);

        paddleExpanded = getImage(path + "56-Breakout-Tiles.png", paddleScale);
        paddleShrunk = getImage(path + "57-Breakout-Tiles.png", paddleScale);
        paddleShrunkWeapon = getImage(path + "65-Breakout-Tiles.png", paddleScale);
        ball = getImage(path + "58-Breakout-Tiles.png", ballScale);
        star = getImage(path + "59-Breakout-Tiles.png", ballScale);
        life = getImage(path + "60-Breakout-Tiles.png", ballScale);
        bullet = getImage(path + "61-Breakout-Tiles.png", brickScale);
        paddleExpandedWeapon = getImage(path + "62-Breakout-Tiles.png", paddleScale);
        fireBall = getImage(path + "63-Breakout-Tiles.png", ballScale);
        acidBall = getImage(path + "64-Breakout-Tiles.png", ballScale);
        capsuleCatch = getImage(path + "66-Breakout-Tiles.png", capsuleScale);
        capsuleVaus = getImage(path + "67-Breakout-Tiles.png", capsuleScale);



    }
}

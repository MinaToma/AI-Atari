package arkanoid;

import atariCore.Helper;
import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class arkHelper extends Helper {

    public static int ballScale = 6;
    public static int paddleScale = 4;
    public static int brickScale = 3;
    public static int capsuleScale = 5;

    public static float ballSpeed = 2f;
    public static final int INIT_BRICKS_HEIGHT = screenHeight * 40 / 100;
    public static final int INIT_PADDLE_X = screenWidth * 40 / 100;
    public static final int INIT_PADDLE_Y = screenHeight * 85 / 100;
    public static final int INIT_BALL_X = screenWidth * 43 / 100;
    public static final int INIT_BALL_Y = screenHeight * 82 / 100;
    public static int BRICKHITREWARD = 10;
    public static float paddleSpeed = 5;
    public static float capsuleSpeed = 1;
    public static float baseEnemyXSpeed = .1f;
    public static float baseEnemyYSpeed = .1f;

    public static Font HUDFont;
    public static Image splashBackground;
    public static Image lockImage;
    public static Image lockedEraImage;
    public static Image[] normalBricks;
    public static Image[] brokenBricks;
    public static Image[] smallSquares;
    public static Image[] paddle;
    public static Image[] paddleWeapon;
    public static Image paddleShrunkWeapon;
    public static Image paddleShrunk;
    public static Image[] paddleExpanded;
    public static Image[] paddleExpandedWeapon;
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
    public static Image[] enemy;

    public static Image fireBall;
    public static Image acidBall;
    public static Image ball;
    public static Image star;
    public static Image life;
    public static Image bullet;
    public static String pathImage = "src/Resources/Images/";
    public static String pathLevel = "src/Resources/Files/levels.txt";
    public static String pathLeaderboards = "src/Resources/Files/Leaderboards.txt";

    public static Image[] backgroundImage;
    public static int numberOfBricks = 2;

    public static MP3Player backgroundSplashSound;
    public static MP3Player[] backgroundGameSound;
    public static MP3Player lossSound;
    public static MP3Player winSound;

    public static JLabel nextLevelImage;
    public static JLabel lossImage;


    public arkHelper() {

        paddle = new Image[3];
        capsule100 = new Image[7];
        paddleWeapon = new Image[3];
        normalBricks = new Image[14];
        brokenBricks = new Image[14];
        smallSquares = new Image[14];
        backgroundImage = new Image[10];
        paddleExpanded = new Image[3];
        paddleExpandedWeapon = new Image[3];
        enemy = new Image[6];

        setSound();
        loadImages();
        setHUDFont();
        setButtonBackgroundColor(new Color(0x543131));
        setForegroundColor(new Color(0xe3d3c3));
        setLoseAndWinImage();
    }

    public static void setLoseAndWinImage() {
        Random r = new Random();
        ImageIcon icon = new ImageIcon("src/Resources/Images/dance/dance"+(Math.abs( r.nextInt())%8+1)+".gif");
        nextLevelImage = new JLabel(icon);
        nextLevelImage.setBounds(screenWidth / 2 - icon.getImage().getWidth(null) / 2, screenHeight / 2 - icon.getImage().getHeight(null) / 2, icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        icon = new ImageIcon(pathImage + "sad.gif");
        lossImage = new JLabel(icon);
        lossImage.setBounds(screenWidth / 2 - icon.getImage().getWidth(null) / 2, screenHeight / 2 - icon.getImage().getHeight(null) / 2, icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }

    private void setSound() {

        backgroundSplashSound = Sound.setSound("src/Resources/Sounds/background.mp3");
        lossSound = Sound.setSound("src/Resources/Sounds/lay.mp3");
        winSound = Sound.setSound("src/Resources/Sounds/nextLevel.mp3");
        backgroundGameSound = new MP3Player[10];

        for (int i = 1; i <= 10; i++) {
            backgroundGameSound[i - 1] = Sound.setSound("src/Resources/Sounds/BackgroundGame/" + i + ".mp3");
        }
    }

    public static void laserSound() {
        MP3Player player = Sound.setSound("src/Resources/Sounds/laser.mp3");
        Sound.Play(player, true);
    }

    public static void hitSound() {
        MP3Player player = Sound.setSound("src/Resources/Sounds/hit.mp3");
        Sound.Play(player, true);
    }

    private void loadImages() {
        pathCursor = "src/Resources/Images/yellowc2.png";
        splashBackground = getImage(backgroundImage + "splash.png", 1);


        lockImage = getImage(pathImage + "background/lock.png", 2);
        lockedEraImage = getImage(pathImage + "background/locked.jpg", 7);

        for (int i = 1; i <= 14; i++) {
            normalBricks[i - 1] = getImage(pathImage + "bricks/normal brick" + i + ".png", brickScale);
        }

        for (int i = 1; i <= 14; i++) {
            brokenBricks[i - 1] = getImage(pathImage + "bricks/broken brick" + i + ".png", brickScale);
        }

        for (int i = 1; i <= 14; i++) {
            smallSquares[i - 1] = getImage(pathImage + "bricks/small brick" + i + ".png", brickScale);
        }

        capsule50 = getImage(pathImage + "31-Breakout-Tiles.png", capsuleScale);

        for (int i = 32; i <= 38; i++) {
            capsule100[i - 32] = getImage(pathImage + i + "-Breakout-Tiles.png", capsuleScale);
        }

        capsule250 = getImage(pathImage + "39-Breakout-Tiles.png", capsuleScale);
        capsule500 = getImage(pathImage + "40-Breakout-Tiles.png", capsuleScale);
        capsuleSlow = getImage(pathImage + "41-Breakout-Tiles.png", capsuleScale);
        capsuleFast = getImage(pathImage + "42-Breakout-Tiles.png", capsuleScale);
        capsuleTripleBall = getImage(pathImage + "43-Breakout-Tiles.png", capsuleScale);
        capsuleFireBall = getImage(pathImage + "44-Breakout-Tiles.png", capsuleScale);
        capsuleAcidBall = getImage(pathImage + "45-Breakout-Tiles.png", capsuleScale);
        capsuleShrink = getImage(pathImage + "46-Breakout-Tiles.png", capsuleScale);
        capsuleExpand = getImage(pathImage + "47-Breakout-Tiles.png", capsuleScale);
        capsuleWeapon = getImage(pathImage + "48-Breakout-Tiles.png", capsuleScale);
        capsuleEmpty = getImage(pathImage + "49-Breakout-Tiles.png", capsuleScale);

        for (int i = 50; i <= 52; i++) {
            paddle[i - 50] = getImage(pathImage + i + "-Breakout-Tiles.png", paddleScale);
        }

        for (int i = 53; i <= 55; i++) {
            paddleWeapon[i - 53] = getImage(pathImage + i + "-Breakout-Tiles.png", paddleScale);
        }
        for(int i=1 ; i<=3 ;i++){
            paddleExpanded[i-1] = getImage(pathImage + "56-"+i+"-Breakout-Tiles.png", paddleScale);
        }
        paddleShrunk = getImage(pathImage + "57-Breakout-Tiles.png", paddleScale);
        paddleShrunkWeapon = getImage(pathImage + "65-Breakout-Tiles.png", paddleScale);
        ball = getImage(pathImage + "58-Breakout-Tiles.png", ballScale);
        star = getImage(pathImage + "59-Breakout-Tiles.png", ballScale);
        life = getImage(pathImage + "60-Breakout-Tiles.png", ballScale);
        bullet = getImage(pathImage + "61-Breakout-Tiles.png", brickScale);

        for(int i=1 ; i<=3 ; i++) {
            paddleExpandedWeapon[i-1] = getImage(pathImage + "62-"+i+"-Breakout-Tiles.png", paddleScale);
        }
        fireBall = getImage(pathImage + "63-Breakout-Tiles.png", ballScale);
        acidBall = getImage(pathImage + "64-Breakout-Tiles.png", ballScale);
        capsuleCatch = getImage(pathImage + "66-Breakout-Tiles.png", capsuleScale);
        capsuleVaus = getImage(pathImage + "67-Breakout-Tiles.png", capsuleScale);

        for (int i = 1; i <= 10; i++) {
            backgroundImage[i - 1] = getImage(pathImage + "background/" + i + ".jpg", 1);

        }

        for (int i = 1; i <= 6; i++) {
            enemy[i - 1] = getImage(pathImage + "11-Breakout-Tiles.png", 1);
        }
    }

    public static void setHUDFont() {
        HUDFont = setFont("src/Resources/Fonts/joystix monospace.ttf", 25);
    }
}

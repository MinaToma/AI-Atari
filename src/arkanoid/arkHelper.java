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

    public static int timeTheCredit = 37;

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
    public static String pathImage = "src/Resources/Arkanoid/Images/";
    public static String soundPath = "src/Resources/Arkanoid/Sounds/";
    public static String pathLevel = "src/Resources/Arkanoid/Files/levels.txt";
    public static String pathLeaderboards = "src/Resources/Arkanoid/Files/Leaderboards.txt";

    public static Image[] backgroundImage;
    public static Image[] eraSelectionImage;
    public static int numberOfBricks = 2;

    public static MP3Player backgroundSplashSound;
    public static MP3Player[] backgroundGameSound;
    public static MP3Player lossSound;
    public static MP3Player winSound;
    public static MP3Player creditSound;

    public static JLabel nextLevelImage;
    public static JLabel lossImage;
    public static JLabel creditsImage;


    public arkHelper() {

        paddle = new Image[3];
        capsule100 = new Image[7];
        paddleWeapon = new Image[3];
        normalBricks = new Image[14];
        brokenBricks = new Image[14];
        smallSquares = new Image[14];
        backgroundImage = new Image[10];
        eraSelectionImage = new Image[10];
        paddleExpanded = new Image[3];
        paddleExpandedWeapon = new Image[3];
        enemy = new Image[6];

        setSound();
        loadImages();
        setHUDFont();
        setButtonBackgroundColor(new Color(0x543131));
        setForegroundColor(new Color(0xe3d3c3));
        setLoseAndWinImage();
        setCredit();
    }

    public static void setLoseAndWinImage() {
        Random r = new Random();
        ImageIcon icon = new ImageIcon(pathImage + "/dance/"+(Math.abs( r.nextInt())%8+1)+".gif");
        nextLevelImage = new JLabel(icon);
        nextLevelImage.setBounds(screenWidth / 2 - icon.getImage().getWidth(null) / 2, screenHeight / 2 - icon.getImage().getHeight(null) / 2, icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        icon = new ImageIcon(pathImage + "sad.gif");
        lossImage = new JLabel(icon);
        lossImage.setBounds(screenWidth / 2 - icon.getImage().getWidth(null) / 2, screenHeight / 2 - icon.getImage().getHeight(null) / 2, icon.getImage().getWidth(null), icon.getImage().getHeight(null));


    }

    public void setCredit()
    {
        ImageIcon icon = new ImageIcon(pathImage + "credits.gif");
        creditsImage = new JLabel(icon);
        creditsImage.setBounds(0,0,screenWidth,screenHeight);
        creditSound = Sound.setSound("src/Resources/Atari Core/Sounds/credits.mp3");

    }

    private void setSound() {
        backgroundSplashSound = Sound.setSound(soundPath + "background.mp3");
        lossSound = Sound.setSound(soundPath + "lay.mp3");
        winSound = Sound.setSound(soundPath + "nextLevel.mp3");
        backgroundGameSound = new MP3Player[10];

        for (int i = 1; i <= 10; i++) {
            backgroundGameSound[i - 1] = Sound.setSound(soundPath + "BackgroundGame/" + i + ".mp3");
        }
    }

    public static void laserSound() {
        MP3Player player = Sound.setSound(soundPath + "laser.mp3");
        Sound.Play(player, true);
    }

    public static void hitSound() {
        MP3Player player = Sound.setSound(soundPath + "hit.mp3");
        Sound.Play(player, true);
    }

    private void loadImages() {
        pathCursor = pathImage + "cursor/yellowCursor2.png";
        splashBackgroundImagePath = pathImage + "background/splash.png";
        splashBackground = getImage(splashBackgroundImagePath, 1);

        pausedImage = getImage( pathImage + "background/pausedBG.png",1);

        lockImage = getImage(pathImage + "background/lock.png", 4);

        for (int i = 1; i <= 14; i++) {
            normalBricks[i - 1] = getImage(pathImage + "brick/normal brick" + i + ".png", brickScale);
        }

        for (int i = 1; i <= 14; i++) {
            brokenBricks[i - 1] = getImage(pathImage + "brick/broken brick" + i + ".png", brickScale);
        }

        for (int i = 1; i <= 14; i++) {
            smallSquares[i - 1] = getImage(pathImage + "brick/small brick" + i + ".png", brickScale);
        }

        capsule50 = getImage(pathImage + "capsule/50.png", capsuleScale);

        for (int i = 0; i < 7; i++) {
            capsule100[i] = getImage(pathImage + "capsule/100-" + i + ".png", capsuleScale);
        }

        capsule250 = getImage(pathImage + "capsule/250.png", capsuleScale);
        capsule500 = getImage(pathImage + "capsule/500.png", capsuleScale);
        capsuleSlow = getImage(pathImage + "capsule/slow.png", capsuleScale);
        capsuleFast = getImage(pathImage + "capsule/fast.png", capsuleScale);
        capsuleTripleBall = getImage(pathImage + "capsule/triple.png", capsuleScale);
        capsuleFireBall = getImage(pathImage + "capsule/fire.png", capsuleScale);
        capsuleAcidBall = getImage(pathImage + "capsule/acid.png", capsuleScale);
        capsuleShrink = getImage(pathImage + "capsule/shrink.png", capsuleScale);
        capsuleExpand = getImage(pathImage + "capsule/expand.png", capsuleScale);
        capsuleWeapon = getImage(pathImage + "capsule/laser.png", capsuleScale);
        capsuleEmpty = getImage(pathImage + "capsule/empty.png", capsuleScale);
        capsuleCatch = getImage(pathImage + "capsule/catch.png", capsuleScale);
        capsuleVaus = getImage(pathImage + "capsule/vaus.png", capsuleScale);

        star = getImage(pathImage + "capsule/star.png", ballScale);
        life = getImage(pathImage + "capsule/life.png", ballScale);

        for (int i = 0; i < 3; i++) {
            paddle[i] = getImage(pathImage + "paddle/n-paddle"+ i + ".png", paddleScale);
            paddleWeapon[i] = getImage(pathImage + "paddle/n-laser"+ i + ".png", paddleScale);
            paddleExpanded[i] = getImage(pathImage + "paddle/e-paddle"+ i + ".png", paddleScale);
            paddleExpandedWeapon[i] = getImage(pathImage + "paddle/e-laser"+ i + ".png", paddleScale);
        }

        paddleShrunk = getImage(pathImage + "paddle/s-paddle.png", paddleScale);
        paddleShrunkWeapon = getImage(pathImage + "paddle/s-laser.png", paddleScale);

        bullet = getImage(pathImage + "bullet.png", brickScale);

        ball = getImage(pathImage + "ball/ball.png", ballScale);
        fireBall = getImage(pathImage + "ball/fire ball.png", ballScale);
        acidBall = getImage(pathImage + "ball/acid ball.png", ballScale);

        for (int i = 1; i <= 10; i++) {
            backgroundImage[i - 1] = getImage(pathImage + "background/" + i + ".jpg", 1);
            eraSelectionImage[i - 1] = getImage(pathImage +  "background/era" + i + ".jpg", 7);

        }

        for (int i = 1; i <= 6; i++) {
            enemy[i - 1] = getImage(pathImage +"enemy/"+ i+".png", 1);
        }
    }

    public static void setHUDFont() {
        HUDFont = setFont("src/Resources/Atari Core/Fonts/joystix monospace.ttf", 20);
    }
}

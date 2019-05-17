package arkanoid;

import atariCore.FileManager;
import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static atariCore.Helper.*;

/**
 * Helper class which holds main variables need to set game components.
 */
public class arkHelper {

    /************************************************** Scale Factors *************************************************/


    /**
     * Ball image scale factor.
     */
    public static int ballScale = 6;
    /**
     * Paddle image scale factor.
     */
    public static int paddleScale = 4;
    /**
     * Brick image scale factor.
     */
    public static int brickScale = 3;
    /**
     * Capsule image scale factor.
     */
    public static int capsuleScale = 5;


    /******************************************* Initial Board Items' Values ******************************************/


    /**
     * Initial bricks pattern height to start from.
     */
    public static final int INIT_BRICKS_HEIGHT = screenHeight * 40 / 100;
    /**
     * Initial paddle x-coordinate position.
     */
    public static final int INIT_PADDLE_X = screenWidth * 40 / 100;
    /**
     * Initial paddle y-coordinate position.
     */
    public static final int INIT_PADDLE_Y = screenHeight * 85 / 100;
    /**
     * Initial ball x-coordinate position.
     */
    public static final int INIT_BALL_X = screenWidth * 43 / 100;
    /**
     * Initial ball y-coordinate position.
     */
    public static final int INIT_BALL_Y = screenHeight * 82 / 100;
    /**
     * Reward value when ball hits a brick.
     */
    public static int BRICKHITREWARD = 10;
    /**
     * Initial credits timer value.
     */
    public static int timeTheCredit = 37;
    /**
     * Initial ball speed value.
     */
    public static float ballSpeed = 2f;
    /**
     * Initial paddle speed value.
     */
    public static float paddleSpeed = 5;
    /**
     * Initial capsule speed value.
     */
    public static float capsuleSpeed = 1;
    /**
     * Initial base enemy x velocity value.
     */
    public static float baseEnemyXSpeed = .1f;
    /**
     * Initial base enemy y velocity value.
     */
    public static float baseEnemyYSpeed = .1f;
    /**
     * Number of bricks in current level, used to identify when to break to next level.
     */
    public static int numberOfBricks;
    /**
     * Game Eras
     */
    public static String[] eras = {"Stone Age "," Pharaohs ","   Aztec  ","   Greek  ", "   Roman ", "  Indian ",
                                    "  Viking  ", "  Chinese ", " Medieval ", "  Arabian "};

    /****************************************************** Fonts *****************************************************/

    /**
     * Heads Up Display Font
     */
    public static Font HUDFont;

    /****************************************************** Images *****************************************************/

    /**
     * Next level dance GIF.
     */
    public static JLabel nextLevelImage;
    /**
     * Loss cry GIF.
     */
    public static JLabel lossImage;
    /**
     * Credits GIF.
     */
    public static JLabel creditsImage;
    /**
     * Splash screen background image.
     */
    public static Image splashBackground;
    /**
     * Game background images array.
     */
    public static Image[] backgroundImage;
    /**
     * Level selection image icons array.
     */
    public static Image[] eraSelectionImage;
    /**
     * Locked levels/eras image.
     */
    public static Image lockImage;
    /**
     * Normal bricks images array.
     */
    public static Image[] normalBricks;
    /**
     * Broken bricks images array.
     */
    public static Image[] brokenBricks;
    /**
     * Small bricks images array.
     */
    public static Image[] smallSquares;
    /**
     * Normal paddle images array.
     */
    public static Image[] paddle;
    /**
     * Normal paddle with weapon images array.
     */
    public static Image[] paddleWeapon;
    /**
     * Shrunk paddle image.
     */
    public static Image paddleShrunk;
    /**
     * Shrunk paddle with weapon image.
     */
    public static Image paddleShrunkWeapon;
    /**
     * Expanded paddle images array.
     */
    public static Image[] paddleExpanded;
    /**
     * Expanded paddle with weapon images array.
     */
    public static Image[] paddleExpandedWeapon;
    /**
     * +50 points score capsule image.
     */
    public static Image capsule50;
    /**
     * +100 points score capsule images array.
     */
    public static Image[] capsule100;
    /**
     * +250 points score capsule image.
     */
    public static Image capsule250;
    /**
     * +500 points score capsule image.
     */
    public static Image capsule500;
    /**
     * Slow capsule image.
     */
    public static Image capsuleSlow;
    /**
     * Fast capsule image.
     */
    public static Image capsuleFast;
    /**
     * Shrink capsule image.
     */
    public static Image capsuleShrink;
    /**
     * Expand capsule image.
     */
    public static Image capsuleExpand;
    /**
     * Laser capsule image.
     */
    public static Image capsuleWeapon;
    /**
     * Fire ball capsule image.
     */
    public static Image capsuleFireBall;
    /**
     * Acid ball capsule image.
     */
    public static Image capsuleAcidBall;
    /**
     * Disrupt capsule image.
     */
    public static Image capsuleTripleBall;
    /**
     * Extra Vaus capsule image.
     */
    public static Image capsuleVaus;
    /**
     * Catch capsule image.
     */
    public static Image capsuleCatch;
    /**
     * Break capsule image.
     */
    public static Image star;
    /**
     * Enemy images array.
     */
    public static Image[] enemy;
    /**
     * Fire ball image.
     */
    public static Image fireBall;
    /**
     * Acid ball image.
     */
    public static Image acidBall;
    /**
     * Normal ball image.
     */
    public static Image ball;
    /**
     * Life image.
     */
    public static Image life;
    /**
     * Bullet image.
     */
    public static Image bullet;

    /************************************************* Default Paths *************************************************/

    /**
     * Files path.
     */
    public static String filePath = "res/Arkanoid/Files/";
    /**
     * Image path.
     */
    public static String imagePath = "res/Arkanoid/Images/";
    /**
     * Sounds path.
     */
    public static String soundPath = "res/Arkanoid/Sounds/";
    /**
     * Levels builder path.
     */
    public static String pathLevel = "res/Arkanoid/Files/levels.txt";
    /**
     * Leaderboards path.
     */
    public static String pathLeaderboards = "res/Arkanoid/Files/Leaderboards.txt";
    /**
     * Font path.
     */
    public static String fontPath = "res/Atari Core/Fonts/";
    /**
     * Splash screen background image path.
     */
    public static String splashBackgroundImagePath;
    /**
     * Path for default cursor image.
     */
    public static String pathCursor = imagePath + "cursor/yellowCursor2.png";


    /************************************************* MP3 Players **************************************************/


    /**
     * Splash background sound
     */
    public static MP3Player backgroundSplashSound;
    /**
     * Game background sound
     */
    public static MP3Player[] backgroundGameSound;
    /**
     * Loss sound
     */
    public static MP3Player lossSound;
    /**
     * Win sound
     */
    public static MP3Player winSound;
    /**
     * Credits sound
     */
    public static MP3Player creditSound;


    /************************************************** Functions **************************************************/


    /**
     * Class instance
     */
    private static arkHelper mArkHelper;

    /**
     * Default constructor to initialise image arrays, set sounds, fonts, colors and load images.
     */
    private arkHelper() {

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
        setLoseAndWinImage();
        setCredit();
    }

    /**
     * Loads arkHelper and fills all variables.
     */
    public static void Load() {
        if(mArkHelper == null) {
            mArkHelper = new arkHelper();
        }
    }

    /**
     * Sets game colors.
     */
    public static void setColors(){
        setButtonBackgroundColor(new Color(0x543131));
        setForegroundColor(new Color(0xe3d3c3));
    }

    /**
     * Loads winning and losing GIFs.
     */
    public static void setLoseAndWinImage() {
        Random r = new Random();
        ImageIcon icon = new ImageIcon(imagePath + "/dance/" + (Math.abs(r.nextInt()) % 8 + 1) + ".gif");
        nextLevelImage = new JLabel(icon);
        nextLevelImage.setBounds(screenWidth / 2 - icon.getImage().getWidth(null) / 2, screenHeight / 2 - icon.getImage().getHeight(null) / 2, icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        icon = new ImageIcon(imagePath + "sad.gif");
        lossImage = new JLabel(icon);
        lossImage.setBounds(screenWidth / 2 - icon.getImage().getWidth(null) / 2, screenHeight / 2 - icon.getImage().getHeight(null) / 2, icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }

    /**
     * Loads credits GIF.
     */
    private void setCredit() {
        ImageIcon icon = new ImageIcon(imagePath + "credits.gif");
        creditsImage = new JLabel(icon);
        creditsImage.setBounds(0, 0, screenWidth, screenHeight);
        creditSound = Sound.setSound("res/Atari Core/Sounds/credits.mp3");
    }

    /**
     * Loads background sounds and other sound effects.
     */
    private void setSound() {
        backgroundSplashSound = Sound.setSound(soundPath + "background.mp3");
        lossSound = Sound.setSound(soundPath + "lay.mp3");
        winSound = Sound.setSound(soundPath + "nextLevel.mp3");
        backgroundGameSound = new MP3Player[10];

        for (int i = 1; i <= 10; i++) {
            backgroundGameSound[i - 1] = Sound.setSound(soundPath + "BackgroundGame/" + i + ".mp3");
        }
    }

    /**
     * Loads laser sound.
     */
    public static void laserSound() {
        MP3Player player = Sound.setSound(soundPath + "laser.mp3");
        Sound.Play(player, true);
    }

    /**
     * Loads hit sound.
     */
    public static void hitSound() {
        MP3Player player = Sound.setSound(soundPath + "hit.mp3");
        Sound.Play(player, true);
    }

    /**
     * Loads Arkanoid game images such as bricks, enemies, balls, backgrounds, capsules... etc.
     */
    public static void loadImages() {

        splashBackgroundImagePath = imagePath + "background/splash.png";
        splashBackground = FileManager.loadImage(splashBackgroundImagePath, 1);

        pausedImage = FileManager.loadImage(imagePath + "background/pausedBG.png", 1);

        lockImage = FileManager.loadImage(imagePath + "background/lock.png", 4);

        for (int i = 1; i <= 14; i++) {
            normalBricks[i - 1] = FileManager.loadImage(imagePath + "brick/normal brick" + i + ".png", brickScale);
        }

        for (int i = 1; i <= 14; i++) {
            brokenBricks[i - 1] = FileManager.loadImage(imagePath + "brick/broken brick" + i + ".png", brickScale);
        }

        for (int i = 1; i <= 14; i++) {
            smallSquares[i - 1] = FileManager.loadImage(imagePath + "brick/small brick" + i + ".png", brickScale);
        }

        capsule50 = FileManager.loadImage(imagePath + "capsule/50.png", capsuleScale);

        for (int i = 0; i < 7; i++) {
            capsule100[i] = FileManager.loadImage(imagePath + "capsule/100-" + i + ".png", capsuleScale);
        }

        capsule250 = FileManager.loadImage(imagePath + "capsule/250.png", capsuleScale);
        capsule500 = FileManager.loadImage(imagePath + "capsule/500.png", capsuleScale);
        capsuleSlow = FileManager.loadImage(imagePath + "capsule/slow.png", capsuleScale);
        capsuleFast = FileManager.loadImage(imagePath + "capsule/fast.png", capsuleScale);
        capsuleTripleBall = FileManager.loadImage(imagePath + "capsule/triple.png", capsuleScale);
        capsuleFireBall = FileManager.loadImage(imagePath + "capsule/fire.png", capsuleScale);
        capsuleAcidBall = FileManager.loadImage(imagePath + "capsule/acid.png", capsuleScale);
        capsuleShrink = FileManager.loadImage(imagePath + "capsule/shrink.png", capsuleScale);
        capsuleExpand = FileManager.loadImage(imagePath + "capsule/expand.png", capsuleScale);
        capsuleWeapon = FileManager.loadImage(imagePath + "capsule/laser.png", capsuleScale);
        capsuleCatch = FileManager.loadImage(imagePath + "capsule/catch.png", capsuleScale);
        capsuleVaus = FileManager.loadImage(imagePath + "capsule/vaus.png", capsuleScale);

        star = FileManager.loadImage(imagePath + "capsule/star.png", ballScale);
        life = FileManager.loadImage(imagePath + "capsule/life.png", ballScale);

        for (int i = 0; i < 3; i++) {
            paddle[i] = FileManager.loadImage(imagePath + "paddle/n-paddle" + i + ".png", paddleScale);
            paddleWeapon[i] = FileManager.loadImage(imagePath + "paddle/n-laser" + i + ".png", paddleScale);
            paddleExpanded[i] = FileManager.loadImage(imagePath + "paddle/e-paddle" + i + ".png", paddleScale);
            paddleExpandedWeapon[i] = FileManager.loadImage(imagePath + "paddle/e-laser" + i + ".png", paddleScale);
        }

        paddleShrunk = FileManager.loadImage(imagePath + "paddle/s-paddle.png", paddleScale);
        paddleShrunkWeapon = FileManager.loadImage(imagePath + "paddle/s-laser.png", paddleScale);

        bullet = FileManager.loadImage(imagePath + "bullet.png", brickScale);

        ball = FileManager.loadImage(imagePath + "ball/ball.png", ballScale);
        fireBall = FileManager.loadImage(imagePath + "ball/fire ball.png", ballScale);
        acidBall = FileManager.loadImage(imagePath + "ball/acid ball.png", ballScale);

        for (int i = 1; i <= 10; i++) {
            backgroundImage[i - 1] = FileManager.loadImage(imagePath + "background/" + i + ".jpg", 1);
            eraSelectionImage[i - 1] = FileManager.loadImage(imagePath + "background/era" + i + ".jpg", 7);

        }

        for (int i = 1; i <= 6; i++) {
            enemy[i - 1] = FileManager.loadImage(imagePath + "enemy/" + i + ".png", 1);
        }
    }

    /**
     * Loads Heads Up Display font.
     */
    public static void setHUDFont() {
        HUDFont = setFont("res/Atari Core/Fonts/joystix monospace.ttf", 20);
    }

    /**
     * Returns instance of the class.
     *
     * @return arkHelper instance.
     */
    public static arkHelper getInstance() {
        return mArkHelper;
    }
}

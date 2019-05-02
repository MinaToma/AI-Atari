package flappyBird;

import atariCore.Helper;
import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import java.awt.*;

import static atariCore.Helper.*;

public class FlappyHelper {

    public static Image[] birds;
    private static Image background;
    static Image backgroundGame;
    public static Image pipeDown;
    public static Image pipeUp;
    public static String imagePath = "src/Resources/Flappy Bird/Images/";
    private static String soundPath = "src/Resources/Flappy Bird/Sounds/";
    public static String pathFile = "src/Resources/Flappy Bird/Files/";
    /**
     * Splash screen background image path.
     */
    public static String splashBackgroundImagePath;
    /**
     * Path for default cursor image.
     */
    public static String pathCursor = imagePath + "cursor/yellowCursor2.png";

    public static float gravity = 0.01f;
    public static float pressSpeed = -1;
    public static boolean startGame = false;


    public static int widthGap = 450;
    public static int heightGap = 200;
    public static int maxHeight = 320;
    public static int minHeight = 30;


    public static MP3Player hitSound;
    public static MP3Player wingSound;
    public static MP3Player pointSound;
    public static MP3Player clickSound;
    public static MP3Player backgroundSound;

    private static FlappyHelper flappyHelper;

    private FlappyHelper() {

        setImages();
        setSounds();
    }

    public static void Load() {
        if (flappyHelper == null) {
            flappyHelper = new FlappyHelper();
        }
    }

    private static void setSounds() {
        hitSound = Sound.setSound(soundPath + "hit.mp3");
        wingSound = Sound.setSound(soundPath + "wing.mp3");
        pointSound = Sound.setSound(soundPath + "point.mp3");
        clickSound = Sound.setSound("src/Resources/Atari Core/Sounds/click.mp3");
        backgroundSound = Sound.setSound(soundPath + "background.mp3");
    }

    public static void setImages() {

        birds = new Image[4];
        for (int i = 1; i <= 4; i++)
            birds[i - 1] = getImage(imagePath + "bird/" + i + ".png", 9);

        heightGap = birds[0].getHeight(null) * 4;

        splashBackgroundImagePath = imagePath + "background.png";
        background = getImage(splashBackgroundImagePath, 1);

        pipeDown = getImage(imagePath + "pipeDOWN.png", 2);

        pipeUp = getImage(imagePath + "pipeUP.png", 2);

        backgroundGame = getImage(imagePath + "flappy.png", 1);
    }

    public static FlappyHelper getInstance() {
        return flappyHelper;
    }
}


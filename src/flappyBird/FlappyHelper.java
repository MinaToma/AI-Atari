package flappyBird;

import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import java.awt.*;

import static atariCore.Helper.*;

/**
 * Helper class which holds main variables need to set game components.
 */
public class FlappyHelper {


    /****************************************************** Images *****************************************************/


    /**
     * Holds bird images.
     */
    public static Image[] birds;
    /**
     * Holds background images.
     */
    static Image backgroundGame;
    /**
     * Holds flipped pipe images.
     */
    public static Image pipeDown;
    /**
     * Hold pipe images.
     */
    public static Image pipeUp;


    /************************************************* Default Paths *************************************************/


    /**
     * Image path.
     */
    public static String imagePath = "src/Resources/Flappy Bird/Images/";
    /**
     * Sound path.
     */
    private static String soundPath = "src/Resources/Flappy Bird/Sounds/";
    /**
     * Files path.
     */
    public static String pathFile = "src/Resources/Flappy Bird/Files/";
    /**
     * Splash screen background image path.
     */
    public static String splashBackgroundImagePath;
    /**
     * Path for default cursor image.
     */
    public static String pathCursor = imagePath + "cursor/yellowCursor2.png";


    /******************************************* Initial Board Items' Values ******************************************/


    /**
     * Gravity value.
     */
    public static float gravity = 0.01f;
    /**
     * Acceleration value.
     */
    public static float pressSpeed = -1;
    /**
     * Flag to check if game started or not.
     */
    public static boolean startGame = false;
    /**
     * Horizontal Gap between pipes.
     */
    public static int widthGap = 450;
    /**
     * Vertical gap between pipes/
     */
    public static int heightGap = 200;
    /**
     * Maximum height of the pipe.
     */
    public static int maxHeight = 320;
    /**
     * Minimum height of the pipe.
     */
    public static int minHeight = 30;


    /************************************************* MP3 Players **************************************************/


    /**
     * Bird hit sound.
     */
    public static MP3Player hitSound;
    /**
     * Wing sound.
     */
    public static MP3Player wingSound;
    /**
     * Score point sound.
     */
    public static MP3Player pointSound;
    /**
     * Button Click sound.
     */
    public static MP3Player clickSound;
    /**
     * Splash background sound
     */
    public static MP3Player backgroundSound;

    private static FlappyHelper flappyHelper;


    /************************************************** Functions **************************************************/


    private FlappyHelper() {

        setImages();
        setSounds();
    }


    /**
     * Loads image, sounds and other components.
     */
    public static void Load() {
        if (flappyHelper == null) {
            flappyHelper = new FlappyHelper();
        }
    }

    /**
     * Sets and loads sound.
     */
    private static void setSounds() {
        hitSound = Sound.setSound(soundPath + "hit.mp3");
        wingSound = Sound.setSound(soundPath + "wing.mp3");
        pointSound = Sound.setSound(soundPath + "point.mp3");
        clickSound = Sound.setSound("src/Resources/Atari Core/Sounds/click.mp3");
        backgroundSound = Sound.setSound(soundPath + "background.mp3");
    }

    /**
     * Sets and loads images.
     */
    public static void setImages() {

        birds = new Image[4];
        for (int i = 1; i <= 4; i++)
            birds[i - 1] = getImage(imagePath + "bird/" + i + ".png", 9);

        heightGap = birds[0].getHeight(null) * 4;

        splashBackgroundImagePath = imagePath + "background.png";

        pipeDown = getImage(imagePath + "pipeDOWN.png", 2);

        pipeUp = getImage(imagePath + "pipeUP.png", 2);

        backgroundGame = getImage(imagePath + "flappy.png", 1);
    }

    /**
     * Returns instance of the class.
     *
     * @return Flappy Helper instance.
     */
    public static FlappyHelper getInstance() {
        return flappyHelper;
    }
}


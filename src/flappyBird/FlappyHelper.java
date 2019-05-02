package flappyBird;

import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import java.awt.*;

import static atariCore.Helper.*;

public class FlappyHelper {

    static Image[] birds;
    private static Image background;
    static Image backgroundGame;
    static Image pipeDown;
    static Image pipeUp;
    static String pathImages = "src/Resources/Flappy Bird/Images/";
    private static String pathSounds = "src/Resources/Flappy Bird/Sounds/";
    static String pathFile = "src/Resources/Flappy Bird/Files/";
    static float gravity = 0.01f;
    public static float pressSpeed = -1;
    static boolean startGame = false;


    static int widthGap = 450;
    static int heightGap = 200;
    static int maxHeight = 320;
    static int minHeight = 30;


    static MP3Player hitSound;
    static MP3Player wingSound;
    static MP3Player pointSound;
    public static MP3Player clickSound;

    static FlappyHelper flappyHelper = new FlappyHelper();

    private FlappyHelper() {

        setImages();
        setSounds();
    }

    private static void setSounds() {
        hitSound = Sound.setSound(pathSounds + "hit.mp3");
        wingSound = Sound.setSound(pathSounds + "wing.mp3");
        pointSound = Sound.setSound(pathSounds + "point.mp3");
        clickSound = Sound.setSound("src/Resources/Atari Core/Sounds/click.mp3");
    }

    static void setImages() {

        birds = new Image[4];
        for (int i = 1; i <= 4; i++)
            birds[i - 1] = getImage(pathImages + "bird/" + i + ".png", 9);

        heightGap = birds[0].getHeight(null) * 4;

        splashBackgroundImagePath = pathImages + "background.png";
        background = getImage(splashBackgroundImagePath, 1);

        pipeDown = getImage(pathImages + "pipeDOWN.png", 2);

        pipeUp = getImage(pathImages + "pipeUP.png", 2);

        backgroundGame = getImage(pathImages + "flappy.png", 1);
    }

    public static FlappyHelper getInstance() {
        return flappyHelper;
    }
}


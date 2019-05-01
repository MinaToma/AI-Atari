package flappyBird;

import atariCore.Helper;
import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import java.awt.*;

import static atariCore.Helper.*;

public class flappyHelper {

    public static Image[] birds;
    public static Image background;
    public static Image backgroundGame;
    public static Image pipeDown;
    public static Image pipeUp;
    public static String pathImages = "src/Resources/Flappy Bird/Images/";
    public static String pathSounds = "src/Resources/Flappy Bird/Sounds/";
    public static String pathFile = "src/Resources/Flappy Bird/Files/";
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
    public static MP3Player selectSound;
    public static MP3Player clickSound;


    public flappyHelper() {

        setImages();
        setSounds();
    }

    private void setSounds() {
        hitSound = Sound.setSound(pathSounds + "hit.mp3");
        wingSound = Sound.setSound(pathSounds + "wing.mp3");
        pointSound = Sound.setSound(pathSounds + "point.mp3");
        selectSound = Sound.setSound(pathSounds + "select.mp3");
        clickSound = Sound.setSound("src/Resources/Atari Core/Sounds/click.mp3");
    }

    private void setImages() {

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
}


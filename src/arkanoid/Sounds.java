package arkanoid;

import atariCore.Sound;
import jaco.mp3.player.MP3Player;

import javax.sound.sampled.Clip;
import java.io.File;


public class Sounds extends Sound {

    public static MP3Player laserSound;
    public static MP3Player hitSound;
    public static MP3Player backgroundSplashSound;
    public static MP3Player[] backgroundGameSound;

    public Sounds() {



        laserSound = setSound("src/Resources/Sounds/laser.mp3");
        hitSound = setSound("src/Resources/Sounds/hit.mp3");
        backgroundSplashSound = setSound("src/Resources/Sounds/background.mp3");

        backgroundGameSound = new MP3Player[10];

        for(int i=1 ; i<=10; i++)
        {
            backgroundGameSound[i-1] = setSound("src/Resources/Sounds/BackgroundGame/"+i+".mp3");

        }



    }
}

package arkanoid;

import atariCore.Sound;

import javax.sound.sampled.Clip;


public class Sounds extends Sound {

    public static Clip laserSound;
    public static Clip hitSound;
    public static Clip backgroundSplashSound;
    public static Clip[] backgroundGameSound;

    public Sounds() {
        laserSound = setClip("Resources/Sounds/laser.wav");
        hitSound = setClip("Resources/Sounds/hit.wav");
        backgroundSplashSound = setClip("Resources/Sounds/background.wav");

        backgroundGameSound = new Clip[10];

        for(int i=1 ; i<=10; i++)
        {
            backgroundGameSound[i-1] = setClip("Resources/Sounds/BackgroundGame/"+i+".wav");


        }

    }
}

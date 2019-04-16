package atariCore;

import jaco.mp3.player.MP3Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

abstract public class Sound {


    public MP3Player setSound(String path) {

        MP3Player newClip = null;
        try {
           newClip = new MP3Player(new File(path));

        } catch (Exception e) {
            System.out.println("Failed to load sound file.");
        }

        return newClip;
    }

}

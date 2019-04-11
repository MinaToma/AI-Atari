package atariCore;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

abstract public class Sound {


    public Clip setClip(String path) {

        Clip newClip = null;
        try {

            URL url = Sound.class.getClassLoader().getResource(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            newClip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            newClip.open(audioIn);
        } catch (Exception e) {
            System.out.println("Failed to load sound file.");
        }

        return newClip;
    }

    public static void play(Clip clip) {
        clip.loop(1);

    }

    public static void stop(Clip clip) {
        clip.stop();
    }

    public static void loop(int loop, Clip clip) {
        clip.loop(loop);
    }
}

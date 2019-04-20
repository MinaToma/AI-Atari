package atariCore;

import jaco.mp3.player.MP3Player;

import java.io.File;


 public class Sound {

    public static MP3Player setSound(String path) {

        MP3Player newClip = null;
        try {
            newClip = new MP3Player(new File(path));

        } catch (Exception e) {
            System.out.println("Failed to load sound file.");
        }

        return newClip;
    }

    public static void Play(MP3Player p, boolean thrd)
    {
        if(thrd  == true) {
            new Thread(() -> p.play()).run();
        }
        else
            p.play();
    }
    public static void Stop(MP3Player p)
    {
        p.stop();
    }
    public static void Repeat(MP3Player p)
    {
        p.setRepeat(true);
    }
}

package atariCore;

import jaco.mp3.player.MP3Player;

import java.io.File;

/**
 * Main sound manager Interface.
 */
public interface Sound {

    /**
     * Sets sound of the given path to a clip and returns it.
     *
     * @param path Path of required sound.
     * @return Clip which contains the sound loaded from given path.
     */
    static MP3Player setSound(String path) {

        MP3Player newClip = null;
        try {
            newClip = new MP3Player(new File(path));

        } catch (Exception e) {
            System.out.println("Failed to load sound file.");
        }

        return newClip;
    }

    /**
     * Plays the given Sound
     *
     * @param p    The sound to be played.
     * @param thrd Flag indicates whether to use a new thread for the sound or not.
     */
    static void Play(MP3Player p, boolean thrd) {
        if (thrd == true) {
            new Thread(() -> p.play()).run();
        } else
            p.play();
    }

    /**
     * Stops the given sound.
     *
     * @param p Sound to be stopped.
     */
    static void Stop(MP3Player p) {
        p.stop();
    }

    /**
     * Repeats the given sound.
     *
     * @param p Sound to be repeated.
     */
    static void Repeat(MP3Player p) {
        p.setRepeat(true);
    }
}

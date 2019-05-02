package arkanoid;

import arkanoid.menu.Splash;
import atariCore.Helper;
import atariCore.Sound;

import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.frame;

/**
 * Generates Credit Screen at the end of the game.
 */
public class CreditScreen extends JPanel {

    private int timer;
    private JLabel screen;
    private MP3Player sound;

    /**
     * Parameterised constructor takes timer,label to write on it and MP3 music.
     *
     * @param timer  Timer of credit screen.
     * @param screen Screen to show within credits.
     * @param sound  Sound for this frame.
     */
    public CreditScreen(int timer, JLabel screen, MP3Player sound) {
        this.timer = timer;
        this.screen = screen;
        this.sound = sound;
        showScreen();
    }

    private void showScreen() {
        if (Helper.panel != null) {
            Helper.frame.getContentPane().remove(Helper.panel);
        }

        Helper.panel = this;
        Helper.frame.setTitle("Credits");
        Helper.panel.setLayout(null);

        add(screen);
        frame.getContentPane().add(this);
        Sound.Repeat(sound);
        Sound.Play(sound, false);
        frame.setVisible(true);
        try {
            TimeUnit.SECONDS.sleep(timer);
        } catch (Exception e) {

        }
        sound.setRepeat(false);
        Sound.Stop(sound);
        new Splash();
    }
}
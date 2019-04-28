package arkanoid;

import arkanoid.menu.Splash;
import atariCore.Helper;
import atariCore.Sound;

import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.frame;


public class creditScreen extends JPanel {

    private int timer;
    private JLabel screan;
    private MP3Player sound;
    public creditScreen(int timer, JLabel screan , MP3Player sound )  {
        this.timer = timer;
        this.screan = screan;
        this.sound = sound;
        showScreen();
    }

    private void showScreen()
    {
        if(Helper.panel != null)
        {
            Helper.frame.getContentPane().remove(Helper.panel);
        }

        Helper.panel = this;
        Helper.frame.setTitle("Credits");
        Helper.panel.setLayout(null);

        add(screan);
        frame.getContentPane().add(this);
        Sound.Repeat(sound);
        Sound.Play(sound,false);
        frame.setVisible(true);
        try {
            TimeUnit.SECONDS.sleep(timer);
        }catch (Exception e)
        {

        }
        sound.setRepeat(false);
        Sound.Stop(sound);
        new Splash();
    }
}

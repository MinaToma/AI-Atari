package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

public class Settings extends atariCore.Settings {


    /**
     *
     * @param music
     * @param sounds
     * @param mouse
     * @param keyboard
     */
    public Settings(boolean music, boolean sounds, boolean mouse, boolean keyboard)
    {
        super(music,sounds,mouse,keyboard);
        backButton.addActionListener(e -> {
            if(Helper.sounds)
            Sound.Play(Helper.clickSound,true);
            new Splash();
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(imagePath + "background/bg.jpg", 1), 0, 0, null);
    }

    @Override
    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));
        if(sounds)
            Sound.Play(Helper.clickSound,true);
    }

    @Override
    protected void switchMusicState() {
        Helper.music = !Helper.music;
        musicButton.setIcon(new ImageIcon((Helper.music ? musicOnImage : musicOffImage)));
        if(sounds)
            Sound.Play(Helper.clickSound,true);
        if(!music)
            Sound.Stop(arkHelper.backgroundSplashSound);
        else
        {
            Sound.Play(arkHelper.backgroundSplashSound, false);
            Sound.Repeat(arkHelper.backgroundSplashSound);
        }
    }
}

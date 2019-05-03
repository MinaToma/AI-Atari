package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

/**
 * Makes settings window for the game.
 */
public class Settings extends atariCore.Settings {


    /**
     * Parametrised constructor used to set the music, sounds, mouse and keyboard settings as specified in the previous run.
     *
     * @param music    Flag states music option Enabled/Disabled.
     * @param sounds   Flag states sounds option Enabled/Disabled.
     * @param mouse    Flag states sounds option Enabled/Disabled.
     * @param keyboard Flag states keyboard control Enabled/Disabled.
     */
    public Settings(boolean music, boolean sounds, boolean mouse, boolean keyboard) {
        super(music, sounds, mouse, keyboard);
        setCursorImage(arkHelper.pathCursor);
        backButton.addActionListener(e -> {
            if (Helper.sounds)
                Sound.Play(Helper.clickSound, true);
            new Splash();
        });
    }

    /**
     * Sets settings panel background image.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(arkHelper.imagePath + "background/bg.jpg", 1), 0, 0, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));
        if (sounds)
            Sound.Play(Helper.clickSound, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void switchMusicState() {
        Helper.music = !Helper.music;
        musicButton.setIcon(new ImageIcon((Helper.music ? musicOnImage : musicOffImage)));
        if (sounds)
            Sound.Play(Helper.clickSound, true);
        if (!music)
            Sound.Stop(arkHelper.backgroundSplashSound);
        else {
            Sound.Play(arkHelper.backgroundSplashSound, false);
            Sound.Repeat(arkHelper.backgroundSplashSound);
        }
    }
}
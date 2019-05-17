package flappyBird.menu;


import atariCore.FileManager;
import atariCore.Helper;
import atariCore.Sound;
import flappyBird.FlappyHelper;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

/**
 * Flappy birds settings menu.
 */
public class Settings extends atariCore.Settings {

    /**
     * Default constructor.
     */
    public Settings() {
        super(music, sounds, mouse, keyboard);
        setCursorImage(FlappyHelper.pathCursor);
        backButton.addActionListener(e -> {
            if (Helper.sounds)
                Sound.Play(FlappyHelper.clickSound, true);
            new Splash();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));
        if (sounds)
            Sound.Play(FlappyHelper.clickSound, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void switchMusicState() {
        Helper.music = !Helper.music;
        musicButton.setIcon(new ImageIcon((Helper.music ? musicOnImage : musicOffImage)));
        if (!music)
            Sound.Stop(FlappyHelper.backgroundSound);
        else {
            Sound.Play(FlappyHelper.backgroundSound, false);
            Sound.Repeat(FlappyHelper.backgroundSound);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void switchKeyboardState() {
        Helper.keyboard = !Helper.keyboard;
        keyboardButton.setIcon(new ImageIcon((keyboard ? keyboardOnImage : keyboardOffImage)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(FileManager.loadImage(FlappyHelper.imagePath + "background.png", 1), 0, 0, null);
    }
}

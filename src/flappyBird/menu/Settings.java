package flappyBird.menu;



import atariCore.Helper;
import atariCore.Sound;
import flappyBird.FlappyHelper;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

public class Settings extends atariCore.Settings {

    public Settings() {
        super(music, sounds, false, keyboard);
        setCursorImage(FlappyHelper.pathCursor);
        backButton.addActionListener(e -> {
            if (Helper.sounds)
                Sound.Play(FlappyHelper.clickSound, true);
            new Splash();
        });
    }

    @Override
    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));
        if (sounds)
            Sound.Play(FlappyHelper.clickSound, true);
    }
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

    @Override
    protected void switchKeyboardState() {
        Helper.keyboard = !Helper.keyboard;
        keyboardButton.setIcon(new ImageIcon((keyboard ? keyboardOnImage : keyboardOffImage)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(FlappyHelper.imagePath + "background.png", 1), 0, 0, null);
    }
}

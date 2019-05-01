package flappyBird;


import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

public class Settings extends atariCore.Settings {


    public Settings( boolean sounds)
    {
        super(false,sounds,false,false);
        backButton.addActionListener(e -> {
            if(Helper.sounds)
            Sound.Play(flappyHelper.clickSound,true);
            new Splash();
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(flappyHelper.pathImages + "background.png", 1), 0, 0, null);
    }
    @Override
    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));
        if(sounds)
            Sound.Play(flappyHelper.clickSound,true);

    }

}

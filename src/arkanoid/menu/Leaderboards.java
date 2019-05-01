package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.Helper;
import atariCore.Sound;

import java.awt.*;

import static atariCore.Helper.getImage;
import static atariCore.Helper.imagePath;
import static atariCore.Helper.sounds;

public class Leaderboards extends atariCore.Leaderboards {

    public Leaderboards(String path) {
        super(path);

        backButton.addActionListener(e -> {
            if(sounds)
            Sound.Play(Helper.clickSound,true);
            new Splash();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(imagePath + "leaderboards.jpg", 1), 0, 0, null);
    }
}

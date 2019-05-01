package flappyBird;

import atariCore.Sound;

import java.awt.*;

import static atariCore.Helper.getImage;
import static atariCore.Helper.sounds;

public class Leaderboards extends atariCore.Leaderboards {

    public Leaderboards(String path) {
        super(path);

        backButton.addActionListener(e -> {
            if(sounds)
            Sound.Play(flappyHelper.clickSound,true);
            new Splash();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(flappyHelper.pathImages + "Leaderboards.png", 1), 0, 0, null);
    }
}

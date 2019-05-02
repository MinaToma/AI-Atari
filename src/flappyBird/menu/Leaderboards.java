package flappyBird.menu;

import atariCore.Sound;
import flappyBird.FlappyHelper;

import java.awt.*;

import static atariCore.Helper.getImage;
import static atariCore.Helper.sounds;

public class Leaderboards extends atariCore.Leaderboards {

    public Leaderboards(String path) {
        super(path, false);

        backButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(FlappyHelper.clickSound, true);
            new Splash();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(FlappyHelper.imagePath + "Leaderboards.png", 1), 0, 0, null);
    }
}

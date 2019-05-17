package flappyBird.menu;

import atariCore.FileManager;
import atariCore.Sound;
import flappyBird.FlappyHelper;

import java.awt.*;

import static atariCore.Helper.sounds;

/**
 * Flappy Bird's leaderboards.
 */
public class Leaderboards extends atariCore.Leaderboards {

    /**
     * Parameterised constructor takes leaderboards' path.
     *
     * @param path Leaderboards' file path.
     */
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
        g.drawImage(FileManager.loadImage(FlappyHelper.imagePath + "Leaderboards.png", 1), 0, 0, null);
    }
}

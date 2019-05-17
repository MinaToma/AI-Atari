package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.FileManager;
import atariCore.Helper;
import atariCore.Sound;

import java.awt.*;

import static atariCore.Helper.*;

/**
 * Arkanoid leaderboards.
 */
public class Leaderboards extends atariCore.Leaderboards {

    /**
     * Parameterised constructor takes the Leaderboards' file path.
     *
     * @param path The leaderboards' path.
     */
    public Leaderboards(String path) {
        super(path , true);
        setCursorImage(arkHelper.pathCursor);
        backButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            new Splash();
        });
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(FileManager.loadImage(arkHelper.imagePath + "leaderboards.jpg", 1), 0, 0, null);
    }
}
package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.Helper;
import atariCore.Sound;

import java.awt.*;

import static atariCore.Helper.getImage;
import static atariCore.Helper.sounds;

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
        super(path);

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
        g.drawImage(getImage(arkHelper.imagePath + "leaderboards.jpg", 1), 0, 0, null);
    }
}
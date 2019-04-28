package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.Sound;

import java.awt.*;

import static arkanoid.arkHelper.pathImage;
import static atariCore.Helper.getImage;

public class Leaderboards extends atariCore.Leaderboards {

    public Leaderboards(String path) {
        super(path);

        backButton.addActionListener(e -> {
            Sound.Play(arkHelper.clickSound,true);
            new Splash();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "leaderboards.jpg", 1), 0, 0, null);
    }
}

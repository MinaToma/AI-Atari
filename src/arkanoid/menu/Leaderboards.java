package arkanoid.menu;

import java.awt.*;

import static arkanoid.arkHelper.pathImage;
import static atariCore.Helper.getImage;

public class Leaderboards extends atariCore.Leaderboards {

    public Leaderboards(String path) {
        super(path);

        backButton.addActionListener(e -> {
            new Splash();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "leaderboards.jpg", 1), 0, 0, null);
    }
}

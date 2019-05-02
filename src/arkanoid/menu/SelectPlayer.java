package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.FileManager;
import atariCore.Helper;
import atariCore.Sound;

import java.awt.*;

import static atariCore.Helper.*;

/**
 * Responsible to fill and select player profile.
 */
public class SelectPlayer extends atariCore.SelectPlayer {

    /**
     * Default constructor.
     */
    public SelectPlayer() {
        setActions();
    }

    /**
     * Initializes buttons' action and takes player's name and moves to the game window.
     */
    protected void setActions() {

        startButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            if (textName.getText().length() > 0 && textName.getText().length() <= 20) {
                String name = textName.getText();
                int level = FileManager.getPlayerLevel(arkHelper.filePath, textName.getText());
                new SelectEra(name, level);
            }
        });

        backButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            new Splash();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(arkHelper.imagePath + "background/bgName.jpg", 1), 0, 0, null);
    }
}
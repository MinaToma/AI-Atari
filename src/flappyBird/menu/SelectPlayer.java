package flappyBird.menu;

import atariCore.FileManager;
import atariCore.Sound;
import flappyBird.FlappyBird;
import flappyBird.FlappyHelper;

import java.awt.*;

import static atariCore.Helper.*;
import static flappyBird.FlappyHelper.clickSound;

/**
 * {@inheritDoc}
 */
public class SelectPlayer extends atariCore.SelectPlayer {

    /**
     * Default constructor.
     */
    public SelectPlayer() {

        setActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setActions() {

        startButton.addActionListener(e -> {
            if (textName.getText().length() > 0 && textName.getText().length() <= 20) {
                String name = textName.getText();
                if (sounds)
                    Sound.Play(clickSound, false);
                new FlappyBird("Flappy Bird", name);
            }
        });

        backButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new Splash();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(FileManager.loadImage(FlappyHelper.imagePath + "background.png", 1), 0, 0, null);
    }
}

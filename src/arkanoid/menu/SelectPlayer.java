package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.FileManager;
import atariCore.Helper;
import atariCore.Sound;

import java.awt.*;

import static atariCore.Helper.*;

public class SelectPlayer extends atariCore.SelectPlayer {


    public SelectPlayer() {
        setActions();
    }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(imagePath + "background/bgName.jpg", 1), 0, 0, null);
    }
}

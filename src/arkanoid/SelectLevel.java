package arkanoid;

import arkanoid.board.Player;
import atariCore.Sound;

import javax.swing.*;

import java.awt.*;

import static atariCore.Helper.*;

/*
 * this code hasn't been tested, need an updated code to run -Mr.Complex.
 */

public class SelectLevel extends JPanel {

    private JButton levels[];

    SelectLevel(String name, int level, int era) {
        frame.setTitle("Select Level");
        frame.getContentPane().remove(panel);

        panel = this;
        setLayout(null);
        panel.setBackground(backgroundColor);

        levels = new JButton[10];
        int posX = screenWidth / 7, posY = screenHeight / 4, offset = 100;
        Dimension dim = new Dimension(250, 100);

        for (int i = 0; i < 10; i++) {
            levels[i] = buttonHelper("" + (i + 1) , posX, posY, dim);

            if (i == 4) {
                posY += offset;
                posX = screenWidth / 7;

            }

            posX += dim.width + offset;

            int finalI = i;
            levels[i].addActionListener(e -> {
                arkHelper.running = true;
                Sound.Stop(arkHelper.backgroundSplashSound);
                new Arkanoid(name , finalI + 1 + 10 * era);
            });
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

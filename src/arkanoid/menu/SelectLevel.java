package arkanoid.menu;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;
import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;

import java.awt.*;

import static arkanoid.arkHelper.lockImage;
import static atariCore.Helper.*;

/**
 * Holds levels and player chooses from it.
 */
public class SelectLevel extends JPanel {

    private JButton[] levels;

    private JButton backButton;

    /**
     * parametrised constructor takes player's name, level number and era.
     *
     * @param name  PLayer's name.
     * @param level Level number.
     * @param era   era number.
     */
    SelectLevel(String name, int level, int era) {
        frame.setTitle("Select Level");
        frame.getContentPane().remove(panel);

        panel = this;
        setLayout(null);
        panel.setBackground(backgroundColor);

        levels = new JButton[10];
        int posX = (int) (screenWidth / 11.7), posY = screenHeight / 5, xoffset = 60, yoffset = 210;
        Dimension dim = new Dimension(150, 150);

        for (int i = 0; i < 10; i++) {
            if (i <= level % 10 || era < level / 10) {
                levels[i] = buttonHelper("" + (i + 1), posX, posY, dim);
                levels[i].setBackground(new Color(0xEA2D1113));
                levels[i].setForeground(new Color(0xB0E6D6C4));
            } else {
                levels[i] = buttonHelper(null, posX, posY, dim);
                levels[i].setBackground(new Color(0x232323));
                levels[i].setIcon(new ImageIcon(lockImage));

                levels[i].setEnabled(false);
            }
            posX += dim.width + xoffset;

            if (i == 4) {
                posY += yoffset;
                posX = screenWidth / 7;

            }

            int finalI = i;
            levels[i].addActionListener(e -> {
                Helper.running = true;
                Sound.Stop(arkHelper.backgroundSplashSound);
                if (sounds)
                    Sound.Play(Helper.clickSound, true);
                new Arkanoid(name, finalI + 1 + 10 * era);
            });
        }

        setBackButton(Helper.screenWidth / 2 - btnDim.width / 2, Helper.screenHeight - btnDim.height - 115);
        backButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            new SelectEra(name, level);
        });

        setCursorImage(pathCursor);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * Back button back to previous frame.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    private void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, btnDim);
        backButton.setBackground(new Color(0xEA2D1113));
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(imagePath + "background/bg.jpg", 1), 0, 0, null);
    }
}
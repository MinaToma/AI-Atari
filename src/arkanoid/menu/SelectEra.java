package arkanoid.menu;

import arkanoid.arkHelper;
import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;
import static arkanoid.arkHelper.*;

/**
 * Generates different Eras.
 */
public class SelectEra extends JPanel {

    private JButton[] eras;

    private JButton backButton;

    /**
     * Parameterised constructor takes name of the era and number of level.
     *
     * @param name  Name of the era.
     * @param level Number of levels on the era.
     */
    SelectEra(String name, int level) {

        frame.setTitle("Select Era");
        frame.getContentPane().remove(panel);

        panel = this;
        setLayout(null);
        panel.setBackground(backgroundColor);
        eras = new JButton[10];

        int posX = (int) (screenWidth / 11.7), posY = (int) (screenHeight / 3.5), xoffset = 40, yoffset = 140;
        Dimension dim = new Dimension(183, 103);

        for (int i = 0; i < 10; i++) {

            eras[i] = buttonHelper(null, posX, posY, dim);

            posX += dim.width + xoffset;

            if (i == 4) {
                posY += yoffset;
                posX = (int) (screenWidth / 11.5);
            }

            if (i <= level / 10)
                eras[i].setIcon(new ImageIcon(eraSelectionImage[i]));
            else {
                eras[i].setBackground(new Color(0x232323));
                eras[i].setIcon(new ImageIcon(lockImage));

                //eras[i].setIcon(new ImageIcon(lockedEraImage));

                eras[i].setEnabled(false);
            }

            int finalI = i;
            eras[i].addActionListener(e -> {
                if (sounds)
                    Sound.Play(clickSound, true);
                new SelectLevel(name, level, finalI);
            });
        }

        setBackButton(Helper.screenWidth / 2 - btnDim.width / 2, Helper.screenHeight - btnDim.height - 155);

        backButton.addActionListener(e -> {
            Sound.Play(clickSound, true);
            new SelectPlayer();
        });

        setCursorImage(arkHelper.pathCursor);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }

    /**
     * Initializes Back button to return to select player menu.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
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
        g.drawImage(getImage(arkHelper.imagePath + "background/bg.jpg", 1), 0, 0, null);
    }
}

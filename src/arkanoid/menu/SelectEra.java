package arkanoid.menu;

import arkanoid.menu.SelectLevel;
import arkanoid.menu.SelectPlayer;
import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;
import static arkanoid.arkHelper.*;

public class SelectEra extends JPanel {

    private JButton[] eras;

    private JButton backButton;

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

        setCursorImage(pathCursor);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }

    private void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, btnDim);
        backButton.setBackground(new Color(0xEA2D1113));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(imagePath + "background/bg.jpg", 1), 0, 0, null);
    }
}

package arkanoid;

import javax.swing.*;
import java.awt.*;

import static arkanoid.arkHelper.lockedEraImage;
import static arkanoid.arkHelper.pathImage;
import static atariCore.Helper.*;

public class SelectEra extends JPanel {

    private JButton eras[];

    SelectEra(String name, int level) {

        frame.setTitle("Select Era");
        frame.getContentPane().remove(panel);

        panel = this;
        setLayout(null);
        panel.setBackground(backgroundColor);
        eras = new JButton[10];

        int posX = (int)(screenWidth / 11.7), posY = (int)(screenHeight / 3.5), xoffset = 40, yoffset = 140;
        Dimension dim = new Dimension(183, 103);

        for (int i = 0; i < 10; i++) {

            eras[i] = buttonHelper(null, posX, posY, dim);

            posX += dim.width + xoffset;

            if (i == 4) {
                posY += yoffset;
                posX = (int)(screenWidth / 11.5);
            }

            if(i <= level / 10)
                eras[i].setIcon(new ImageIcon(getImage(pathImage +  "background/era" + (i + 1) + ".jpg", 7)));
            else
            {
                eras[i].setIcon(new ImageIcon(lockedEraImage));
                eras[i].setBackground(new Color(0x232323));
                eras[i].setEnabled(false);
            }

            int finalI = i;
            eras[i].addActionListener(e -> {
                new SelectLevel(name, level, finalI);
            });
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "background/bg.jpg", 1), 0, 0, null);
    }
}

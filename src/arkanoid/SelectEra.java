package arkanoid;

import javax.swing.*;
import java.awt.*;

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

        int posX = screenWidth / 7, posY = screenHeight / 4, offset = 100;
        Dimension dim = new Dimension(250, 50);

        for (int i = 0; i < 10; i++) {
            eras[i] = buttonHelper("" + (i + 1) , posX, posY, dim);

            if (i == 4) {
                posY += offset;
                posX = screenWidth / 7;
            }

            posX += dim.width + offset;

            int finalI = i;
            eras[i].addActionListener(e -> {
                new SelectLevel(name, level % 10, finalI);
            });
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
}

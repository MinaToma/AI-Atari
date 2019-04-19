package arkanoid;

import arkanoid.board.Player;

import javax.swing.*;

import java.awt.*;

import static atariCore.Helper.*;

/*
 * this code hasn't been tested, need an updated code to run -Mr.Complex.
 */

public class SelectLevel extends JPanel {

    private JButton levels[];

    private Player player;

    private static int i;

    SelectLevel(Player player, int era) {
        frame.setTitle("Select Level");
        frame.getContentPane().remove(panel);

        panel = this;
        panel.setBackground(backgroundColor);

        levels = new JButton[10];
        this.player = player;

        int posX = screenWidth/ 7, posY = screenHeight/4, offset = 20;
        Dimension dim = new Dimension(200,200);

        for(i = 0; i < 10; i++)
        {
            levels[i] = new JButton();
            levels[i] = buttonHelper("" + i + 1, posX, posY, dim);

            if(i == 5)
            {
                posY += offset;
                posX = screenWidth/7;

            }

            posX += dim.width + offset;

            levels[i].addActionListener(e->{
                player.setLevel((i + 1) + 10 * era);
            });
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

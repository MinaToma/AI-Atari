package arkanoid;

import arkanoid.board.Player;

import javax.swing.*;

import java.awt.*;

import static atariCore.Helper.*;

/*
 * this code hasn't been tested, need an updated code to run -Mr.Complex.
 */


public class SelectEra extends JPanel {
    private JButton eras[];

    private Player player;

    private static int i;

    SelectEra(Player player) {
        frame.setTitle("Select Era");
        frame.getContentPane().remove(panel);

        panel = this;
        panel.setBackground(backgroundColor);
        eras = new JButton[10];
        this.player = player;

        int posX = screenWidth/ 7, posY = screenHeight/4, offset = 20;
        Dimension dim = new Dimension(400,200);

        for(i = 0; i < 10; i++)
        {
            eras[i] = new JButton();
            eras[i] = buttonHelper("" + i + 1, posX, posY, dim);

            if(i == 5)
            {
                posY += offset;
                posX = screenWidth/7;

            }

            posX += dim.width + offset;

            eras[i].addActionListener(e->{
                new SelectLevel(player, i + 1);
            });
        }


        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
}

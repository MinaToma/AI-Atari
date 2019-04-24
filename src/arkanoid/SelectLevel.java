package arkanoid;

import atariCore.Sound;

import javax.swing.*;

import java.awt.*;

import static arkanoid.arkHelper.lockImage;
import static arkanoid.arkHelper.pathImage;
import static atariCore.Helper.*;

/*
 * this code hasn't been tested, need an updated code to run -Mr.Complex.
 */

public class SelectLevel extends JPanel {

    private JButton[] levels;

    private JButton backButton;

    SelectLevel(String name, int level, int era) {
        frame.setTitle("Select Level");
        frame.getContentPane().remove(panel);

        panel = this;
        setLayout(null);
        panel.setBackground(backgroundColor);

        levels = new JButton[10];
        int posX = (int)(screenWidth / 11.7), posY = screenHeight / 5, xoffset = 60, yoffset = 210;
        Dimension dim = new Dimension(150, 150);

        for (int i = 0; i < 10; i++) {
            if(i <= level % 10  || era < level / 10 ){
                levels[i] = buttonHelper("" + (i + 1), posX, posY, dim);
                levels[i].setBackground(new Color(0xEA2D1113));
                levels[i].setForeground(new Color(0xB0E6D6C4));
            }
            else{
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
                arkHelper.running = true;
                Sound.Stop(arkHelper.backgroundSplashSound);
                new Arkanoid(name , finalI + 1 + 10 * era);
            });
        }

        setBackButton(arkHelper.screenWidth/2-btnDim.width/2, arkHelper.screenHeight-btnDim.height - 115);
        backButton.addActionListener(e->{
            new SelectEra(name, level);
        });

        setCursorImage(this, pathCursor);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void setBackButton(int x, int y) {
        backButton = arkHelper.buttonHelper("Back", x, y, btnDim);
        backButton.setBackground(new Color(0xEA2D1113));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "background/bg.jpg", 1), 0, 0, null);
    }
}

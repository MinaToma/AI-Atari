package Welcome;

import atariCore.Helper;

import java.awt.*;
import javax.swing.*;

import static atariCore.Helper.*;

public class Splash extends JPanel {

    private static Image[] image;
    private static int imageCounter = 0;

    public static void main(String[] args) {
        image = new Image[3];
        image[0] = Helper.getImage("src/Resources/Arkanoid/Images/background/1.png", 1);
        image[1] = Helper.getImage("src/Resources/Arkanoid/Images/background/2.png", 1);
        image[2] = Helper.getImage("src/Resources/Arkanoid/Images/background/3.png", 1);

        JButton Game_Choice = new JButton();
        Game_Choice.setBorder(BorderFactory.createEmptyBorder());
        Game_Choice.setIcon(new ImageIcon(image[0]));
        Game_Choice.setBounds(0, 0, 1280, 600);

        panel = new Splash();
        frame.setLayout(null);
        panel.setLayout(null);

        panel.setSize(1280, 100);
        panel.setLocation(0, 600);

        JButton back = new JButton("Back");
        back.setBounds(10, 15, 200, 50);

        JButton next = new JButton("Next");
        next.setBounds((panel.getWidth() - 227), 15, 200, 50);

        panel.add(back);
        panel.add(next);
        panel.setBackground(new Color(100, 150, 10));
        Game_Choice.addActionListener(event -> {
            if (imageCounter == 0) {

                JOptionPane.showMessageDialog(null, "Welcome to our Ai-Atari box" + Game_Choice.getHeight()
                        , "Inform", JOptionPane.INFORMATION_MESSAGE);
            } else if (imageCounter == 1) {
                JOptionPane.showMessageDialog(null, "Welcome to Arkanoid", "Inform", JOptionPane.INFORMATION_MESSAGE);
            } else if (imageCounter == 2) {
                JOptionPane.showMessageDialog(null, "Welcome to Flappy", "Inform", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        back.addActionListener(event -> {
            imageCounter = (imageCounter - 1 + 3) % 3;
            Game_Choice.setIcon(new ImageIcon(image[imageCounter]));
        });

        next.addActionListener(event -> {
            imageCounter = (imageCounter + 1) % 3;
            System.out.println(image[imageCounter].getWidth(null) + " " + image[imageCounter].getHeight(null) );
            Game_Choice.setIcon(new ImageIcon(image[imageCounter]));
        });

        frame.add(panel);
        frame.add(Game_Choice);

        frame.setVisible(true);
    }
}


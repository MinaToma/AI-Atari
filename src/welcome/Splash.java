package welcome;

import arkanoid.arkHelper;
import atariCore.LoadingScreen;
import atariCore.Helper;
import flappyBird.FlappyHelper;

import java.awt.*;
import javax.swing.*;

import static atariCore.Helper.*;

public class Splash extends JPanel {

    private static Image[] image;
    private static int imageCounter = 0;

    public Splash() {

        if (panel != null) {
            frame.getContentPane().remove(panel);
        }

        panel = this;
        panel.setLayout(null);

        JButton Game_Choice = new JButton();
        Game_Choice.setLayout(null);
        Game_Choice.setBorder(BorderFactory.createEmptyBorder());
        Game_Choice.setIcon(new ImageIcon(image[0]));
        Game_Choice.setBounds(0, 0, screenWidth, screenHeight);
        Game_Choice.addActionListener(event -> {
            if (imageCounter == 1) {
                arkanoid.menu.Splash.main(null);
            } else if (imageCounter == 2) {
                flappyBird.menu.Splash.main(null);
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(screenWidth * 10 / 100, screenHeight * 80 / 100, 200, 50);
        back.addActionListener(event -> {
            imageCounter = (imageCounter - 1 + 3) % 3;
            Game_Choice.setIcon(new ImageIcon(image[imageCounter]));
        });

        JButton next = new JButton("Next");
        next.setBounds(screenWidth * 75 / 100, screenHeight * 80 / 100, 200, 50);
        next.addActionListener(event -> {
            imageCounter = (imageCounter + 1) % 3;
            Game_Choice.setIcon(new ImageIcon(image[imageCounter]));
        });

        Game_Choice.add(next);
        Game_Choice.add(back);
        panel.add(Game_Choice);
        frame.add(panel);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    private static void loadImages() {
        image = new Image[3];
        image[0] = Helper.getImage("src/Resources/Arkanoid/Images/background/1.jpg", 1);
        image[1] = Helper.getImage("src/Resources/Arkanoid/Images/background/2.jpg", 1);
        image[2] = Helper.getImage("src/Resources/Arkanoid/Images/background/3.jpg", 1);
    }

    public static void main(String[] args) {

        Helper.Load();
        new LoadingScreen();
        arkHelper.Load();
        loadImages();
        FlappyHelper.Load();

        new Splash();
    }
}


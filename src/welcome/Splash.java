package welcome;

import arkanoid.arkHelper;
import atariCore.LoadingScreen;
import atariCore.Helper;
import flappyBird.FlappyHelper;

import java.awt.*;
import javax.swing.*;

import static atariCore.Helper.*;

public class Splash extends JPanel {

    private JLabel atariLabel;
    private JButton Flappy, Arkanoid;

    public Splash() {

        if (panel != null) {
            frame.getContentPane().remove(panel);
        }

        panel = this;
        panel.setLayout(new GridLayout(0,1));
        panel.setBackground(new Color(24,24,24));

        JPanel aiAtari = new JPanel(new GridLayout(0,2));
        ImageIcon icon = new ImageIcon("src/Resources/Atari Core/Images/loading/atari.gif");
        atariLabel = new JLabel(icon);

        icon = new ImageIcon("src/Resources/Flappy Bird/Images/bird.gif");
        Flappy = new JButton(icon);
        icon = new ImageIcon("src/Resources/Arkanoid/Images/splash.gif");
        Arkanoid = new JButton(icon);

        panel.add(atariLabel);
        aiAtari.add(Arkanoid);
        aiAtari.add(Flappy);
        panel.add(aiAtari);

        frame.add(panel);
        frame.setVisible(true);
        panel.requestFocusInWindow();

        Flappy.addActionListener(e -> {
            flappyBird.menu.Splash.main(null);
        });
        Arkanoid.addActionListener(e -> {
            arkanoid.menu.Splash.main(null);
        });
    }

    public static void main(String[] args) {

        Helper.Load();
        new LoadingScreen();
        arkHelper.Load();
        FlappyHelper.Load();

        new Splash();
    }
}


package atariCore;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

/**
 * Atari core loading screen
 */
public class LoadingScreen extends JPanel {

    /**
     * Initial x coordinate of the screen.
     */
    private int initialAtariWidth;
    /**
     * Initial y coordinate of the screen.
     */
    private int initialAtariHeight;
    /**
     * AI atari logo.
     */
    private JLabel atariLabel;
    /**
     * Loading label.
     */
    private JLabel loading;

    /**
     * initialises, creates and adds loading screen it to the panel.
     */
    public LoadingScreen() {

        if (panel != null) {
            frame.getContentPane().remove(panel);
        }

        frame.setTitle("AI-Atari");
        panel = this;
        panel.setSize(screenWidth, screenHeight);
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(new Color(24, 24, 24));

        ImageIcon icon = new ImageIcon("res/Atari Core/Images/loading/loading.gif");
        loading = new JLabel(icon);
        icon = new ImageIcon("res/Atari Core/Images/loading/atari.gif");
        atariLabel = new JLabel(icon);

        initialAtariHeight = screenHeight / 3;
        initialAtariWidth = screenWidth / 2;

        atariLabel.setBounds(initialAtariWidth, initialAtariHeight, atariLabel.getWidth(), atariLabel.getHeight());

        panel.add(atariLabel);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        Sound.Repeat(welcomeSound);
        Sound.Play(welcomeSound, false);
    }
}

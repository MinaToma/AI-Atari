package atariCore;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

public class LoadingScreen extends JPanel {

    private int initialAtariWidth, initialAtariHeight;
    private JLabel atariLabel, loading;

    public LoadingScreen() {

        new Helper();
        if(panel != null) {
            frame.getContentPane().remove(panel);
        }

        frame.setTitle("AI-Atari");
        panel = this;
        panel.setSize(screenWidth, screenHeight);
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(new Color(24, 24, 24));

        ImageIcon icon = new ImageIcon("src/Resources/Images/loading/loading.gif");
        loading = new JLabel(icon);
        icon = new ImageIcon("src/Resources/Images/loading/atari.gif");
        atariLabel = new JLabel(icon);

        initialAtariHeight = screenHeight / 3;
        initialAtariWidth = screenWidth / 2;

        atariLabel.setBounds(initialAtariWidth, initialAtariHeight, atariLabel.getWidth(), atariLabel.getHeight());

        panel.add(atariLabel);
        panel.add(loading);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

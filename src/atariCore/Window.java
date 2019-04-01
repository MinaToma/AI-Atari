package atariCore;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Window extends JPanel {

    public Window(int width, int height, String title, Game game) {

        JFrame frame = new JFrame(title);

        frame.setSize(width, height);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
    }
}

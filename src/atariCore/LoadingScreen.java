package atariCore;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class LoadingScreen extends JPanel {



    private int initialAtariWidth, initialAtariHeight;
    private JLabel atariLable, loading;

    public LoadingScreen()
    {

        frame.setTitle("AI-Atari");
        panel = this;
        panel.setSize(screenWidth, screenHeight);
        panel.setLayout(new GridLayout(0,1));
        panel.setBackground(new Color(26,26,26));



        ImageIcon icon = new ImageIcon("src/Resources/image/loading/loading.gif");
        loading = new JLabel(icon);
        icon = new ImageIcon("src/Resources/image/loading/atari.gif");
        atariLable = new JLabel(icon);

        initialAtariHeight = screenHeight/3;
        initialAtariWidth = screenWidth /2;


        atariLable.setBounds(initialAtariWidth,initialAtariHeight,atariLable.getWidth(),atariLable.getHeight());



        panel.add(atariLable);
        panel.add(loading);

        frame.getContentPane().add(panel);
        frame.setVisible(true);








    }

}

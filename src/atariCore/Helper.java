package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Helper {

    public static int screenWidth = 700, screenHeight = 800;
    public static final int WIDTH = 640;
    public static final int HEIGTH = WIDTH / 12 * 9;
    public static final int BOTTOM_EDGE = 390;
    public static final int N_OF_BRICKS = 30;
    public static final int INIT_PADDLE_X = 200;
    public static final int INIT_PADDLE_Y = 360;
    public static final int INIT_BALL_X = 230;
    public static final int INIT_BALL_Y = 355;
    public static final int DELAY = 1000;
    public static final int PERIOD = 5;

    public static JButton btnHelper(JButton btn, String txt, int x, int y, Dimension dim, Panel panel) {
        btn = new JButton(txt);
        btn.setLayout(null);
        btn.setBounds(x, y, dim.width, dim.height);

        panel.add(btn);

        return btn;
    }

    public Image getImage(String filename) {

        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage();

        return (new ImageIcon(image.getScaledInstance(image.getWidth(null) / 5,
                image.getHeight(null) / 5, Image.SCALE_SMOOTH))).getImage();
    }
}

package atariCore;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static int screenWidth = 1280, screenHeight = 720;
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

        return (new ImageIcon(image.getScaledInstance(image.getWidth(null) / 4,
                image.getHeight(null) / 4, Image.SCALE_SMOOTH))).getImage();
    }
}

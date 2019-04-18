package atariCore;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class Helper {

    public static int screenWidth = 1280 , screenHeight = 720;
    public static final int DELAY = 3000;
    public static final int PERIOD = 1;
    public static Font font;
    public static boolean running = false;
    public static String fieldSpertor = "@@@";
    public static String recordSpertor = "###";

    public static boolean training;
    public static int trainingCounter;
    public static int trainingLimit = 10000;

    public static void setFont(String fontPath, int fontSize)
    {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JButton btnHelper(JButton btn, String txt, int x, int y, Dimension dim, JPanel panel) {

        btn = new JButton(txt);
        btn.setLayout(null);
        btn.setFont(font);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.YELLOW);
        btn.setFocusPainted(false);
        btn.setBounds(x, y, dim.width, dim.height);

        panel.add(btn);

        return btn;
    }
    public static JButton btnHelper(JButton btn, String txt) {

        btn = new JButton(txt);
        btn.setLayout(null);
        btn.setFont(font);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.YELLOW);
        btn.setFocusPainted(false);


        return btn;
    }

    public static Image getImage(String filename, int scaleFactor) {

        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage();

        return (new ImageIcon(image.getScaledInstance(image.getWidth(null) / scaleFactor,
                image.getHeight(null) / scaleFactor, Image.SCALE_SMOOTH))).getImage();
    }

    public static void setCursorImage(JPanel mainPane, String filename)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = getImage(filename, arkHelper.cursorScale);
        Cursor c = toolkit.createCustomCursor(image , new Point(mainPane.getX(),
                mainPane.getY()), "cursor");
        mainPane.setCursor (c);
    }

    public static JLabel setLabel(int x, int  y , String text) {

        JLabel label = new JLabel(text);
        label.setLocation(x , y);
        label.setBackground(Color.black);
      //  label.setFont(font);
        label.setVisible(true);

        return label;
    }
}

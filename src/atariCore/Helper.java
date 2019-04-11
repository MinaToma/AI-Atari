package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Helper {


    public static int screenWidth = 1280 , screenHeight = 720;
    public static final int DELAY = 1000;
    public static final int PERIOD = 5;
    public static Font font;
    public static int splashButtonFontSize;
    public static JFrame frame = null;
    public static JPanel panel;
    public static boolean running = false;
    public static String fieldSeparator = "@@@";



    public static int cursorScale = 3;
    public static Dimension btnDim = new Dimension(screenWidth / 2, screenHeight / 10);


    protected static boolean music, sounds, mouse, keyboard;

    public Helper()
    {
        if(frame == null) {
            frame = new JFrame();

            frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
            frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
            frame.setMinimumSize(new Dimension(screenWidth, screenHeight));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            frame.setLocationRelativeTo(null);
        }
    }




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

    public static void setSplashButtonFontSize(int splashButtonFontSize)
    {
        Helper.splashButtonFontSize = splashButtonFontSize;
    }

    public static JButton buttonHelper(String txt, int x, int y, Dimension dim) {

        JButton button = new JButton(txt);
        button.setLayout(null);
        button.setFont(font);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.YELLOW);
        button.setFocusPainted(false);
        button.setBounds(x, y, dim.width, dim.height);

        panel.add(button);

        return button;
    }

    public static JButton buttonHelper(String txt) {
        JButton button = new JButton(txt);
        button.setFont(font);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.YELLOW);
        button.setFocusPainted(false);
        return button;
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
        Image image = getImage(filename, cursorScale);
        Cursor c = toolkit.createCustomCursor(image , new Point(mainPane.getX(),
                mainPane.getY()), "cursor");
        mainPane.setCursor (c);
    }
}
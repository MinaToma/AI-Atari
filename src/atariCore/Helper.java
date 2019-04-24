package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Helper {

    public static int screenWidth = 1280, screenHeight = 720;
    public static final int DELAY = 1000;
    public static final int PERIOD = 5;
    public static Font font;
    public static int splashButtonFontSize;
    public static JFrame frame = null;
    public static JPanel panel;
    public static boolean running = false;
    public static boolean pause = false;
    public static String fieldSeparator = "@@@";
    public static boolean AIMode = false;
    public static String controlsImages = "src/Resources/Images/controls/";
    public static String pathCursor;
    public static Image soundOnImage;
    public static Image soundOffImage;
    public static Image musicOnImage;
    public static Image musicOffImage;
    public static Image keyboardOnImage;
    public static Image keyboardOffImage;
    public static Image mouseOnImage;
    public static Image mouseOffImage;
    public static Image pausedImage;
    public static int cursorScale = 3;
    public static Dimension btnDim = new Dimension(screenWidth / 2, screenHeight / 10);
    public static String filesPath = "src/Resources/Files/";
    //game colors

    public static Color backgroundColor;
    public static Color foregroundColor;
    public static Color buttonBackgroundColor;
    public static Color HUDColor;

    public static boolean music = true, sounds = true, mouse = true, keyboard = true;

    public Helper() {
        if (frame == null) {
            frame = new JFrame();

            frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
            frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
            frame.setMinimumSize(new Dimension(screenWidth, screenHeight));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            frame.setLocationRelativeTo(null);
        }

        setButtonBackgroundColor(new Color(0x171C28));
        setForegroundColor(new Color(165, 209, 77));
        setHUDColor(new Color(173, 173, 173));

        loadImages();
    }

    private void loadImages() {

        soundOnImage = getImage(controlsImages + "sound on.png", 1);
        soundOffImage = getImage(controlsImages + "sound off.png", 1);

        musicOnImage = getImage(controlsImages + "music on.png", 1);
        musicOffImage = getImage(controlsImages + "music off.png", 1);

        keyboardOnImage = getImage(controlsImages + "empty.png",1);
        keyboardOffImage = getImage(controlsImages + "empty.png",1);

        mouseOnImage = getImage(controlsImages + "empty.png",1);
        mouseOffImage = getImage(controlsImages + "empty.png",1);
    }


    public static Font setFont(String fontPath, int fontSize)
    {
        Font font = new Font(null, Font.BOLD, fontSize);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }

    public static void setSplashButtonFontSize(int splashButtonFontSize) {
        Helper.splashButtonFontSize = splashButtonFontSize;
    }

    public static JButton buttonHelper(String txt, int x, int y, Dimension dim) {

        JButton button = new JButton(txt);
        button.setFont(font);
        button.setForeground(foregroundColor);
        button.setBackground(buttonBackgroundColor);
        button.setFocusPainted(false);
        button.setBounds(x, y, dim.width, dim.height);

        panel.add(button);

        return button;
    }

    public static JButton buttonHelper(String txt) {
        JButton button = new JButton(txt);
        button.setFont(font);
        button.setLayout(null);
        button.setForeground(foregroundColor);
        button.setBackground(buttonBackgroundColor);
        button.setFocusPainted(false);
        return button;
    }
    public static JLabel labelHelper(String txt)
    {
        JLabel label = new JLabel(txt);
        label.setFont(font);
        label.setLayout(null);
        label.setForeground(buttonBackgroundColor);
        return label;
    }

    public static Image getImage(String filename, int scaleFactor) {

        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage();

        return (new ImageIcon(image.getScaledInstance(image.getWidth(null) / scaleFactor,
                image.getHeight(null) / scaleFactor, Image.SCALE_SMOOTH))).getImage();
    }

    public static void setCursorImage(JPanel mainPane, String filename) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = getImage(filename, cursorScale);
        Cursor c = toolkit.createCustomCursor(image, new Point(mainPane.getX(),
                mainPane.getY()), "cursor");
        mainPane.setCursor(c);
    }

    public static void setButtonBackgroundColor(Color buttonBackgroundColor) {
        Helper.buttonBackgroundColor = buttonBackgroundColor;
    }

    public static void setBackgroundColor(Color backgroundColor) {
        Helper.backgroundColor = backgroundColor;
    }

    public static void setForegroundColor(Color foregroundColor) {
        Helper.foregroundColor = foregroundColor;
    }

    public static void setHUDColor(Color HUDColor) {
        Helper.HUDColor = HUDColor;
    }

}
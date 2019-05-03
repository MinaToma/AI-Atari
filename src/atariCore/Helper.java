package atariCore;

import jaco.mp3.player.MP3Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Helper class which holds main variables need to set game components.
 */
public class Helper {

    /**
     * Main game frame.
     */
    public static JFrame frame = null;
    /**
     * Main game panel.
     */
    public static JPanel panel;
    /**
     * Default screen width.
     */
    public static int screenWidth = 1280;
    /**
     * Default screen height.
     */
    public static int screenHeight = 720;
    /**
     * Default cursor image scale.
     */
    public static int cursorScale = 3;
    /**
     * Default button dimensions.
     */
    public static Dimension btnDim = new Dimension(screenWidth / 2, screenHeight / 10);
    /**
     * Default delay of game loop. (Delay before starting the timer)
     */
    public static final int DELAY = 1000;
    /**
     * Default period of game loop. (number of frames per every tick)
     */
    public static int PERIOD = 5;
    /**
     * Default game font.
     */
    public static Font font;
    /**
     * Default font size for buttons.
     */
    public static int splashButtonFontSize;

    /************************************************* Default Paths *************************************************/

    /**
     * Path of control menu images.
     */
    public static String controlsImages = "src/Resources/Atari Core/Images/controls/";
    /**
     * Sounds path.
     */
    public static String soundPath = "src/Resources/Atari Core/Sounds/";
    /**
     * Files path.
     */
    public static String filePath = "src/Resources/Atari Core/Files/";
    /**
     * Image path.
     */
    public static String imagePath = "src/Resources/Atari Core/Images/";
    /**
     * Font path.
     */
    public static String fontPath = "src/Resources/Atari Core/Fonts/";
    /**
     * Splash screen background image path.
     */
    public static String splashBackgroundImagePath;
    /**
     * Path for default cursor image.
     */
    public static String pathCursor = imagePath + "cursor/yellowCursor2.png";


    /****************************************************** Images *****************************************************/

    /**
     * Enabled sounds image.
     */
    public static Image soundOnImage;
    /**
     * Disabled sounds image.
     */
    public static Image soundOffImage;
    /**
     * Enabled music image.
     */
    public static Image musicOnImage;
    /**
     * Disabled music image.
     */
    public static Image musicOffImage;
    /**
     * Enabled keyboard control image.
     */
    public static Image keyboardOnImage;
    /**
     * Disabled keyboard control image.
     */
    public static Image keyboardOffImage;
    /**
     * Enabled mouse control image.
     */
    public static Image mouseOnImage;
    /**
     * Disabled mouse control image.
     */
    public static Image mouseOffImage;
    /**
     * Paused background image.
     */
    public static Image pausedImage;
    /**
     * Holder for paused background image.
     */
    public static JLabel pausedBG = new JLabel();


    /*****************************************************Colors******************************************************/

    /**
     * Default game background color.
     */
    public static Color backgroundColor;
    /**
     * Default game foreground color.
     */
    public static Color foregroundColor;
    /**
     * Default game buttonBackground color.
     */
    public static Color buttonBackgroundColor;
    /**
     * Default game HUD color.
     */
    public static Color HUDColor;


    /************************************************* MP3 Players **************************************************/

    /**
     * Sound for button clicks.
     */
    public static MP3Player clickSound;
    /**
     * Sound for background welcome window and loading screen
     */
    public static MP3Player welcomeSound;

    /****************************************************** Flags *****************************************************/


    /**
     * Flag used to start and stop game timer.
     */
    public static boolean running = false;
    /**
     * Flag used to pause the game.
     */
    public static boolean pause = false;
    /**
     * Flag used to start AI-Player.
     */
    public static boolean AIMode = false;
    /**
     * Flag states music option Enabled/Disabled.
     */
    public static boolean music = true;
    /**
     * Flag states sounds option Enabled/Disabled.
     */
    public static boolean sounds = true;
    /**
     * Flag states sounds option Enabled/Disabled.
     */
    public static boolean mouse = true;
    /**
     * Flag states keyboard control Enabled/Disabled.
     */
    public static boolean keyboard = true;


    /************************************************** Functions **************************************************/

    /**
     * Class instance
     */
    private static Helper helper;

    /**
     * Default constructor to initialise main game design components paths for images and sounds.
     */
    private Helper() {
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
        setForegroundColor(new Color(0xA5D14D));
        setHUDColor(new Color(0xE1E1E1));

        loadSettingsImages();
        setButtonClickSound();
    }

    /**
     * Loads Helper and fills all variables.
     */
    public static void Load() {
        if (helper == null) {
            helper = new Helper();
        }
    }

    /**
     * sets button click sound.
     */
    private void setButtonClickSound() {
        clickSound = Sound.setSound(soundPath + "click.mp3");
        welcomeSound = Sound.setSound(soundPath + "background.mp3");
    }


    /**
     * Loads images for settings menu.
     */
    private void loadSettingsImages() {

        soundOnImage = getImage(controlsImages + "sound on.png", 1);
        soundOffImage = getImage(controlsImages + "sound off.png", 1);

        musicOnImage = getImage(controlsImages + "music on.png", 1);
        musicOffImage = getImage(controlsImages + "music off.png", 1);

        keyboardOnImage = getImage(controlsImages + "keyboard on.png", 1);
        keyboardOffImage = getImage(controlsImages + "keyboard off.png", 1);

        mouseOnImage = getImage(controlsImages + "mouse on.png", 1);
        mouseOffImage = getImage(controlsImages + "mouse off.png", 1);
    }

    /**
     * Sets and removes paused image from panel.
     */
    public static void setPausedBG() {
        if (pause) {

            pausedBG.setIcon(new ImageIcon(pausedImage));
            pausedBG.setBounds(0, 0, screenWidth, screenHeight);
            panel.add(pausedBG);
            frame.setVisible(true);
        } else {
            panel.remove(pausedBG);
            frame.setVisible(true);
        }
    }


    /**
     * Sets main font.
     *
     * @param fontPath Font path.
     * @param fontSize Font Size.
     * @return New font with specified size.
     */
    public static Font setFont(String fontPath, int fontSize) {
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


    /**
     * Sets splash buttons' font size.
     *
     * @param splashButtonFontSize New size for splash buttons font.
     */
    public static void setSplashButtonFontSize(int splashButtonFontSize) {
        Helper.splashButtonFontSize = splashButtonFontSize;
    }

    /**
     * Sets main properties of the button and adds it to the main panel.
     *
     * @param txt Button label.
     * @param x   x coordinate of the button.
     * @param y   y coordinate of the button.
     * @param dim Width and height of the button.
     * @return Button with specified properties.
     */
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

    /**
     * Loads image from specified path and returns it scaled with specified factor.
     *
     * @param filename    Image path.
     * @param scaleFactor Scale factor.
     * @return Image with specified scale and path.
     */
    public static Image getImage(String filename, int scaleFactor) {

        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage();

        return (new ImageIcon(image.getScaledInstance(image.getWidth(null) / scaleFactor,
                image.getHeight(null) / scaleFactor, Image.SCALE_SMOOTH))).getImage();
    }

    /**
     * Sets cursor image.
     *
     * @param filename Cursor's image path.
     */
    public static void setCursorImage(String filename) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = getImage(filename, cursorScale);
        Cursor c = toolkit.createCustomCursor(image, new Point(panel.getX(),
                panel.getY()), "cursor");
        panel.setCursor(c);
    }

    /**
     * Sets color of button background.
     *
     * @param buttonBackgroundColor Button background's new Color
     */
    public static void setButtonBackgroundColor(Color buttonBackgroundColor) {
        Helper.buttonBackgroundColor = buttonBackgroundColor;
    }

    /**
     * Sets background color.
     *
     * @param backgroundColor Background color.
     */
    public static void setBackgroundColor(Color backgroundColor) {
        Helper.backgroundColor = backgroundColor;
    }

    /**
     * Sets foreground color.
     *
     * @param foregroundColor Foreground color.
     */
    public static void setForegroundColor(Color foregroundColor) {
        Helper.foregroundColor = foregroundColor;
    }

    /**
     * Sets HUD color.
     *
     * @param HUDColor HUD color.
     */
    public static void setHUDColor(Color HUDColor) {
        Helper.HUDColor = HUDColor;
    }

    /**
     * Returns instance of the class.
     *
     * @return Helper instance.
     */
    public static Helper getInstance() {
        return helper;
    }
}
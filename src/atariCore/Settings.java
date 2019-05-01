package atariCore;

import javax.swing.*;

import static atariCore.Helper.*;

/**
 * Default Settings menu
 */
public class Settings extends JPanel {

    /**
     * Toggles music state (On / Off).
     */
    protected JButton musicButton;
    /**
     * Toggles Sound state (On / Off).
     */
    protected JButton soundButton;
    /**
     * Toggles mouse controls (On / Off).
     */
    protected JButton mouseButton;
    /**
     * Toggles keyboard controls  X coordinates of the ball..
     */
    protected JButton keyboardButton;
    /**
     * Back to main menu.
     */
    protected JButton backButton;

    /**
     * Default constructor with all features on.
     */
    public Settings() {
        this(true, true, true, true);
    }

    /**
     * Parameterized constructor to set features on and off.
     *
     * @param music    Music flag.
     * @param sounds   Sound flag.
     * @param mouse    Mouse Control flag.
     * @param keyboard Keyboard control flog.
     */
    public Settings(boolean music, boolean sounds, boolean mouse, boolean keyboard) {

        frame.getContentPane().remove(panel);
        frame.setTitle("Settings");
        panel = this;

        Helper.music = music;
        Helper.sounds = sounds;
        Helper.mouse = mouse;
        Helper.keyboard = keyboard;

        setDesign();
        setActions();

        add(musicButton);
        add(soundButton);
        add(mouseButton);
        add(keyboardButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * Initializes music button into given position
     *
     * @param x x coordinates of the button.
     * @param y y coordinates of the button.
     */
    protected void setMusicButton(int x, int y) {
        musicButton = Helper.buttonHelper("Music", x, y, btnDim);
    }

    /**
     * Initializes sound button into given position
     *
     * @param x x coordinates of the button.
     * @param y y coordinates of the button.
     */
    protected void setSoundButton(int x, int y) {
        soundButton = Helper.buttonHelper("Sound", x, y, btnDim);
    }

    /**
     * Initializes mouse control button into given position
     *
     * @param x x coordinates of the button.
     * @param y y coordinates of the button.
     */
    protected void setMouseButton(int x, int y) {
        mouseButton = Helper.buttonHelper("Mouse", x, y, btnDim);
    }

    /**
     * Initializes keyboard control button into given position
     *
     * @param x x coordinates of the button.
     * @param y y coordinates of the button.
     */
    protected void setKeyboardButton(int x, int y) {
        keyboardButton = Helper.buttonHelper("Keyboard", x, y, btnDim);
    }

    /**
     * Initializes back button into given position
     *
     * @param x x coordinates of the button.
     * @param y y coordinates of the button.
     */
    public void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, btnDim);
    }

    /**
     * Toggles sound state.
     */
    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));

    }

    /**
     * Toggles music state.
     */
    protected void switchMusicState() {
        Helper.music = !Helper.music;
        musicButton.setIcon(new ImageIcon((Helper.music ? musicOnImage : musicOffImage)));
    }

    /**
     * Toggles mouse control state.
     */
    protected void switchMouseState() {
        Helper.mouse = !Helper.mouse;
    }

    /**
     * Toggles keyboard control state.
     */
    protected void switchKeyboardState() {
        Helper.keyboard = !Helper.keyboard;
    }


    /**
     * Sets action listener for buttons.
     */
    private void setActions() {

        musicButton.addActionListener(e -> {
            switchMusicState();
        });

        soundButton.addActionListener(e -> {
            switchSoundsState();
        });

        mouseButton.addActionListener(e -> {
            switchMouseState();
        });

        keyboardButton.addActionListener(e -> {
            switchKeyboardState();
        });
    }

    /**
     * Sets main design and components of settings menu.
     */
    private void setDesign() {

        int posX = (int) (screenWidth / 3.5), posY = 152, offset = 20;
        setLayout(null);
        setBackground(backgroundColor);
        setForeground(foregroundColor);

        setMusicButton(posX, posY);
        setSoundButton(posX, posY += btnDim.height + offset);
        setMouseButton(posX, posY += btnDim.height + offset);
        setKeyboardButton(posX, posY += btnDim.height + offset);
        setBackButton(posX, posY += btnDim.height + offset);

        musicButton.setIcon(new ImageIcon((Helper.music ? musicOnImage : musicOffImage)));
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));
        mouseButton.setIcon(new ImageIcon(mouseOnImage));
        keyboardButton.setIcon(new ImageIcon(keyboardOnImage));

        musicButton.setSize(btnDim);
        soundButton.setSize(btnDim);
        mouseButton.setSize(btnDim);
        keyboardButton.setSize(btnDim);
        backButton.setSize(btnDim);

        setCursorImage(pathCursor);
    }
}
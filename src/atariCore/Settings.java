package atariCore;

import javax.swing.*;
import java.awt.*;

import static arkanoid.arkHelper.pathCursor;
import static atariCore.Helper.*;

public class Settings extends JPanel {

    protected JButton musicButton, soundButton, mouseButton, keyboardButton, backButton;
    //private Dimension dim = new Dimension(screenWidth, screenHeight);

    protected void setMusicButton(int x, int y) {
        musicButton = Helper.buttonHelper("Music", x, y, btnDim);
    }

    protected void setSoundButton(int x, int y) {
        soundButton = Helper.buttonHelper("Sound", x, y, btnDim);
    }

    protected void setMouseButton(int x, int y) {
        mouseButton = Helper.buttonHelper("Mouse", x, y, btnDim);
    }

    protected void setKeyboardButton(int x, int y) {
        keyboardButton = Helper.buttonHelper("Keyboard", x, y, btnDim);
    }

    public void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, btnDim);
    }

    protected void switchSoundsState() {
        Helper.sounds = !Helper.sounds;
        soundButton.setIcon(new ImageIcon((Helper.sounds ? soundOnImage : soundOffImage)));

    }

    protected void switchMusicState() {
        Helper.music = !Helper.music;
        musicButton.setIcon(new ImageIcon((Helper.music ? musicOnImage : musicOffImage)));
    }

    protected void switchMouseState() {
        Helper.mouse = !Helper.mouse;
    }

    protected void switchKeyboardState() {
        Helper.keyboard = !Helper.keyboard;
    }

    public Settings() {

        frame.getContentPane().remove(panel);
        frame.setTitle("Settings");
        panel = this;

        Helper.music = true;
        Helper.sounds = true;
        Helper.mouse = true;
        Helper.keyboard = true;

        setDesign();
        setActions();

        add(musicButton);
        add(soundButton);
        add(mouseButton);
        add(keyboardButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

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

        setCursorImage(panel, pathCursor);

    }
}
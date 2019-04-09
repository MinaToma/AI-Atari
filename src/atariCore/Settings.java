package atariCore;

import javax.swing.*;
import java.awt.*;

public class Settings {

    JButton musicButton, soundButton, controlsButton, mouseButton, keyboardButton;
    private Dimension dim = new Dimension(100, 30);

    protected void setMusicButton(int x, int y, JPanel panel) {
        musicButton = Helper.btnHelper("Music", x, y, dim, panel);
    }

    protected void setSoundButton(int x, int y, JPanel panel) {
        soundButton = Helper.btnHelper("Sound", x, y, dim, panel);
    }

    protected void setControlsButton(int x, int y, JPanel panel) {
        controlsButton = Helper.btnHelper("Controls", x, y, dim, panel);
    }

    protected void setMouseButton(int x, int y, JPanel panel) {
        mouseButton = Helper.btnHelper("Mouse", x, y, dim, panel);
    }

    protected void setKeyboardButton(int x, int y, JPanel panel) {
        keyboardButton = Helper.btnHelper("Keyboard", x, y, dim, panel);
    }

    protected void switchSoundsState(){
        Helper.sounds = !Helper.sounds;
    }

    protected void switchMusicState(){
        Helper.music = !Helper.music;
    }

    protected void switchMouseState(){
        Helper.mouse = !Helper.mouse;
    }

    protected void switchKeyboardState(){
        Helper.keyboard = !Helper.keyboard;
    }


    public Settings() {
        Helper.music = true;
        Helper.sounds = true;
        Helper.mouse = true;
        Helper.keyboard = true;
    }

    public Settings(boolean music, boolean sounds, boolean mouse, boolean keyboard) {
        Helper.music = music;
        Helper.sounds = sounds;
        Helper.mouse = mouse;
        Helper.keyboard = keyboard;
    }
}
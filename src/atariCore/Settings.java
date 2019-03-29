package atariCore;

import javax.swing.*;

public class Settings {

    protected boolean music, sounds;

    public JFrame frame;
    JButton musicButton, soundButton, controlsButton;
    int musicVolume, soundVolume;

    protected void setMusicButton(int x, int y, Handler handler)
    {
        musicButton = new JButton();
        musicButton.setLocation(x, y);
        musicButton.setText("Music");
    }

    protected void setSoundButton(int x, int y, Handler handler)
    {
        soundButton = new JButton();
        soundButton.setLocation(x, y);
        soundButton.setText("Sound");
    }

    protected void setControlsButton(int x, int y, Handler handler)
    {
        controlsButton = new JButton();
        controlsButton.setLocation(x, y);
        controlsButton.setText("Controls");
    }

    public Settings(JFrame frame)
    {
        this.frame = frame;
        music = true;
        sounds = true;
        musicVolume = 100;
        soundVolume = 100;
    }

    public Settings(boolean music, boolean sounds, int musicVolume, int soundVolume)
    {
        this.music = music;
        this.sounds = sounds;
        this.musicVolume = musicVolume;
        this.soundVolume = soundVolume;
    }
}
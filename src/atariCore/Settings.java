package atariCore;

import javax.swing.*;

public class Settings {

    protected boolean music, sounds;

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

    public Settings()
    {
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


/*

*
*
*
*

*/
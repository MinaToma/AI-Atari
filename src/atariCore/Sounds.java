package atariCore;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.sun.org.apache.xerces.internal.utils.SecuritySupport.getResourceAsStream;

public class Sounds {


    public void background()
    {
        playSound("background.wav")  ;
    }



    public void playSound(String path)  {
        try
        {

            InputStream inputStream = getClass().getResourceAsStream("Resources/Sounds/"+path);
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);

        }
        catch (Exception e)
        {

            System.out.print(12);
        }


    }




}

package atariCore;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sounds {


    public  Clip hit;
    public  Clip backgroundSplash;
    public  Clip backgroundGame;
    public  Clip lazer;

    public Sounds()
    {
       backgroundSplash = playSound("background.wav")  ;
       backgroundGame =  playSound("background.wav");
       hit =  playSound("hit.wav");
       lazer =  playSound("lazer.wav");
    }





    public Clip playSound(String path) {


        try
        {

            URL url = this.getClass().getClassLoader().getResource("Resources/Sounds/"+path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //clip.start();
            return clip;
        }
        catch (Exception e)
        {
            ////
        }
        return null;



    }




}

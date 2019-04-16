package arkanoid.menu;

import arkanoid.*;

import atariCore.AIEngine;
import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/Cowboys.otf");
        arkHelper.setCursorImage(panel, "src/Resources/Images/yellowc2.png");


        for(int i=0 ; i<10 ; i++)
        {
            Sounds.backgroundGameSound[i].stop();
        }

        Sounds.backgroundSplashSound.play();

        newGameButton.addActionListener(e -> {

            new NewPlayer();
        });

        AIButton.addActionListener(e -> {
            arkHelper.running = true;
            AIMode = true;

            //setinput
            //setouput
            try {
                AIEngine.startAI();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            new Arkanoid("AI-Player");
        });
    }

    public static void main(String[] args) {

        new arkHelper();
        new ObjectList();
        new Sounds();
        new Splash();
    }
}

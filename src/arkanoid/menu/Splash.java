package arkanoid.menu;

import arkanoid.*;
import arkanoid.NewPlayer;
import arkanoid.ObjectList;
import arkanoid.arkHelper;

import static atariCore.Helper.panel;

import atariCore.AIEngine;
import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/joystix monospace.ttf");
        arkHelper.setCursorImage(panel, pathCursor);


        for(int i=0 ; i<10 ; i++)
        {
           arkHelper.backgroundGameSound[i].stop();
        }
        Sound.Play(arkHelper.backgroundSplashSound,false);


        newGameButton.addActionListener(e -> {

            new NewPlayer();
        });

        settingsButton.addActionListener(e -> {

            new Settings();
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
        new Splash();
    }
}

package arkanoid.menu;

import arkanoid.*;
import arkanoid.SelectPlayer;
import arkanoid.ObjectList;
import arkanoid.arkHelper;

import static atariCore.Helper.panel;

import atariCore.AIEngine;
import atariCore.LoadingScreen;
import atariCore.Sound;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class Splash extends atariCore.Splash {

    public Splash() {

        super("Arkanoid", "src/Resources/Fonts/joystix monospace.ttf");
        arkHelper.setCursorImage(panel, pathCursor);

        for (int i = 0; i < 10; i++) {
            arkHelper.backgroundGameSound[i].stop();
        }

        if(music) {
            if (arkHelper.backgroundSplashSound.isStopped())
                Sound.Play(arkHelper.backgroundSplashSound, false);
            Sound.Repeat(arkHelper.backgroundSplashSound);
        }
        else
        {
            Sound.Stop(arkHelper.backgroundSplashSound);
        }

        newGameButton.addActionListener(e -> {

            new SelectPlayer();
        });

        settingsButton.addActionListener(e -> {

            new Settings(music,sounds,mouse,keyboard);
        });
        LeaderboardsButton.addActionListener(e -> {
            new Leaderboards("src/Resources/Files/Leaderboards.txt");
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
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }

            new Arkanoid("AI-Player" , 1 );
        });
    }

    public static void main(String[] args) {

        new LoadingScreen();
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e) { }

        new arkHelper();
        new ObjectList();
        new Splash();
    }
}

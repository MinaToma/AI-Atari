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
            if(arkHelper.backgroundGameSound[i].isRepeat() )
                arkHelper.backgroundGameSound[i].setRepeat(false);
            Sound.Stop(arkHelper.backgroundGameSound[i]);

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
            Sound.Play(arkHelper.clickSound,true);
            new SelectPlayer();
        });

        settingsButton.addActionListener(e -> {

            new Settings(music,sounds,mouse,keyboard);
            Sound.Play(arkHelper.clickSound,true);
        });
        LeaderboardsButton.addActionListener(e -> {
            new Leaderboards("src/Resources/Files/Leaderboards.txt");
            Sound.Play(arkHelper.clickSound,true);
        });
        exitButton.addActionListener(e -> {
                    frame.dispose();
                    Sound.Play(arkHelper.clickSound,true);

                }

        );

        AIButton.addActionListener(e -> {
            Sound.Play(arkHelper.clickSound,true);
            arkHelper.running = true;
            AIMode = true;

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

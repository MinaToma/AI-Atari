package arkanoid.menu;

import arkanoid.*;
import arkanoid.SelectPlayer;
import arkanoid.ObjectList;
import arkanoid.arkHelper;

import atariCore.AIEngine;
import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class Splash extends atariCore.Splash {

    public Splash() {

        super("Arkanoid", "src/Resources/Atari Core/Fonts/joystix monospace.ttf");
        Helper.setCursorImage(pathCursor);

        for (int i = 0; i < 10; i++) {
            if (arkHelper.backgroundGameSound[i].isRepeat())
                arkHelper.backgroundGameSound[i].setRepeat(false);
            Sound.Stop(arkHelper.backgroundGameSound[i]);
        }

        if (music) {
            if (arkHelper.backgroundSplashSound.isStopped())
                Sound.Play(arkHelper.backgroundSplashSound, false);
            Sound.Repeat(arkHelper.backgroundSplashSound);
        } else {
            Sound.Stop(arkHelper.backgroundSplashSound);
        }

        newGameButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            new SelectPlayer();
        });

        settingsButton.addActionListener(e -> {
                new Settings(music, sounds, mouse, keyboard);
            if (sounds)
                Sound.Play(Helper.clickSound, true);
        });
        LeaderboardsButton.addActionListener(e -> {
            new Leaderboards("src/Resources/Files/Leaderboards.txt");
            if (sounds)
                Sound.Play(Helper.clickSound, true);
        });
        exitButton.addActionListener(e -> {
                    frame.dispose();
            if(sounds)
                Sound.Play(Helper.clickSound, true);

                }
        );

        AIButton.addActionListener(e -> {
            if(sounds)
                Sound.Play(Helper.clickSound, true);
            Helper.running = true;
            AIMode = true;

//            try {
//                AIEngine.startAI();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            } catch (NullPointerException ex) {
//                ex.printStackTrace();
//            }

            new Arkanoid("AI-Player", 1);
        });
    }

    public static void main(String[] args) {

        new LoadingScreen();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
        }

        new arkHelper();
        new ObjectList();
        new Splash();
    }
}

package arkanoid.menu;

import arkanoid.*;
import arkanoid.arkHelper;

import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;


import static atariCore.Helper.*;

/**
 * Splash screen includes main menu buttons such as new game, leaderboards, settings, exit.
 */
public class Splash extends atariCore.Splash {

    /**
     * Default constructor to set music, sounds settings and main menu buttons action listeners.
     */
    public Splash() {

        super("Arkanoid", "src/Resources/Atari Core/Fonts/joystix monospace.ttf");
        Helper.setCursorImage(arkHelper.pathCursor);

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
            new Leaderboards(filePath + "Leaderboards.txt");
            if (sounds)
                Sound.Play(Helper.clickSound, true);
        });
        exitButton.addActionListener(e -> {
                    frame.dispose();
                    if (sounds)
                        Sound.Play(Helper.clickSound, true);

                }
        );

        AIButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            Helper.running = true;
            AIMode = true;

            arkAIEngine.startEngine();
            new Arkanoid("AI-Player", 1);
        });
    }

    /**
     * Initializes game loading screen, loads files and images and starts game splash.
     *
     * @param args An array of parameters.
     */
    public static void main(String[] args) {
        splashBackgroundImagePath = arkHelper.splashBackgroundImagePath;
        new Splash();
    }
}

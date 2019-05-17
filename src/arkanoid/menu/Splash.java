package arkanoid.menu;

import arkanoid.*;
import arkanoid.arkHelper;
import atariCore.Handler;
import atariCore.Helper;
import atariCore.Sound;

import static arkanoid.arkHelper.backgroundSplashSound;
import static arkanoid.arkHelper.setColors;
import static atariCore.Helper.*;

/**
 * Splash screen includes main menu buttons such as new game, leaderboards, settings, exit.
 */
public class Splash extends atariCore.Splash {

    /**
     * Default constructor to set music, sounds settings and main menu buttons action listeners.
     */
    public Splash() {

        super("Arkanoid", "joystix monospace.ttf");

        Helper.setCursorImage(arkHelper.pathCursor);

        for (int i = 0; i < 10; i++) {
            if (arkHelper.backgroundGameSound[i].isRepeat())
                arkHelper.backgroundGameSound[i].setRepeat(false);
            Sound.Stop(arkHelper.backgroundGameSound[i]);
        }

        if (music) {
            if (backgroundSplashSound.isStopped())
                Sound.Play(backgroundSplashSound, false);
            Sound.Repeat(backgroundSplashSound);
        } else {
            Sound.Stop(backgroundSplashSound);
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
            new Leaderboards(arkHelper.filePath + "Leaderboards.txt");
            if (sounds)
                Sound.Play(Helper.clickSound, true);
        });

        AIButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(Helper.clickSound, true);
            Sound.Stop(backgroundSplashSound);
            Helper.running = true;
            AIMode = true;

            arkAIEngine.startEngine();
            new Arkanoid("AI-Player", 1);
        });

        backButton.addActionListener(e -> {
            Sound.Stop(backgroundSplashSound);
            Handler.clear();
        });
    }

    /**
     * Arkanoid splash entry function.
     */
    public static void main(String[] args) {
        splashBackgroundImagePath = arkHelper.splashBackgroundImagePath;
        setColors();
        new Splash();
    }
}

package flappyBird.menu;

import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;
import flappyBird.FlappyAIEngine;
import flappyBird.FlappyBird;
import flappyBird.FlappyHelper;

import static atariCore.Helper.*;
import static atariCore.Helper.clickSound;
import static flappyBird.FlappyHelper.*;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Flappy Bird", "joystix monospace.ttf");

        newGameButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new SelectPlayer();
        });

        AIButton.addActionListener(e -> {
            running = true;
            AIMode = true;
            startGame = true;
            if (sounds)
                Sound.Play(clickSound, false);
            FlappyAIEngine.startEngine();
            new FlappyBird("Flappy Bird", "AI-Player");
        });
        LeaderboardsButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new Leaderboards(pathFile + "Leaderboards.txt");
        });
        settingsButton.addActionListener(e -> {
            if (sounds)
                Sound.Play(clickSound, false);
            new Settings(sounds);
        });

        exitButton.addActionListener(e -> {
                    frame.dispose();
                    if (sounds)
                        Sound.Play(clickSound, false);
                }
        );
    }

    public static void main(String... args) {
        Helper.splashBackgroundImagePath = FlappyHelper.splashBackgroundImagePath;
        new Splash();
    }
}


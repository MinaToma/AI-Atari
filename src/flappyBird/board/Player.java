package flappyBird.board;


import atariCore.BaseObject;
import atariCore.FileManager;
import atariCore.Handler;
import flappyBird.FlappyBird;
import flappyBird.ObjectList;
import flappyBird.menu.Splash;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;
import static flappyBird.FlappyHelper.*;

/**
 * Player class.
 */
public class Player extends BaseObject {

    /**
     * Players's score.
     */
    private int score;
    /**
     * Player's name.
     */
    private String name;
    /**
     * Flag to check if the game started or not.
     */
    public boolean start;
    /**
     * Flappy Bird object.
     */
    FlappyBird flappyBird;

    /**
     * Parameterised constructor takes player's name, flappyBird object.
     *
     * @param Name       Player's name.
     * @param flappyBird FlappyBird object.
     */
    public Player(String Name, FlappyBird flappyBird) {
        super(10, 10, null);
        this.name = Name;
        this.flappyBird = flappyBird;
        score = 0;
        start = true;
    }

    /**
     * Returns player's name.
     *
     * @return Player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the player lost all lives, player will die.
     */
    public void die() {
        FileManager.addNewScoreToLeaderboards(pathFile + "Leaderboards.txt", name, score, 0);
        running = false;

        if (!AIMode) {

            Handler.clear();
            new Splash();
        } else {

            setScore(0);

            flappyBird.myGeneration.generateNewGeneration();
            flappyBird.initialise();

        }
    }

    /**
     * Sets player's score.
     *
     * @param score player's new score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        g.setColor(HUDColor);
        g.setFont(font);
        String strScore = String.valueOf(score);
        g.drawString(name, 10, 40);
        g.drawString(strScore, 10, 80);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.setColor(buttonBackgroundColor);
        g.drawString("Press P to Pause/Resume", 1100, 670);
        g.drawString("Press Esc to return to  Main Menu", 1100, 680);
    }
}


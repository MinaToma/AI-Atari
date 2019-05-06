package arkanoid.board;

import arkanoid.*;
import atariCore.BaseObject;
import atariCore.FileManager;
import atariCore.Helper;
import atariCore.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

/**
 * Player class which generates player.
 */
public class Player extends BaseObject {
    /**
     * Player's score.
     */
    private int score;
    /**
     * Player's name.
     */
    private String name;
    /**
     * Player's lives.
     */
    private int lives;
    /**
     * Player's paddles.
     */
    public ArrayList<Paddle> paddle;
    /**
     * Flag to check if the player is at the beginning of the game.
     */
    public boolean start;
    /**
     * Object of arkanoid.
     */
    Arkanoid arkanoid;
    /**
     * Level number.
     */
    private int level;
    /**
     * Last score the player reached need for AI-Mode.
     */
    private int previousScore;

    /**
     * Parameterised constructor takes player's name, his/her lives, paddle, arkanoid.
     *
     * @param Name     PLayer's name.
     * @param lives    Player's lives.
     * @param paddle   Object of player's paddle.
     * @param arkanoid Object of arkanoid.
     */
    public Player(String Name, int lives, Paddle paddle, Arkanoid arkanoid) {
        super(10, 10, null);
        this.name = Name;
        this.lives = lives;
        this.paddle = new ArrayList<>();
        this.paddle.add(paddle);
        this.arkanoid = arkanoid;

        previousScore = 0;
        score = 0;
        start = true;
    }

    /**
     * Returns current player's level.
     *
     * @return Level of the player.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets new level for the player.
     *
     * @param level Player's level to play.
     */
    public void setLevel(int level) {

        if (level > 100) {

            FileManager.addNewScoreToLeaderboards(arkHelper.pathLeaderboards, name, score, level);
            FileManager.sendPlayerScore(arkHelper.filePath, name, level-1);
            Sound.Stop(arkHelper.backgroundGameSound[(level - 2) / 10]);

            new CreditScreen(arkHelper.timeTheCredit, arkHelper.creditsImage, arkHelper.creditSound);
        } else {

            if (score > 0 && !AIMode) {
                Sound.Stop(arkHelper.backgroundGameSound[(this.level - 1) / 10]);
                arkHelper.setLoseAndWinImage();
                panel.add(arkHelper.nextLevelImage);
                frame.setVisible(true);

                if (sounds) {
                    Sound.Play(arkHelper.winSound, true);
                }

                try {
                    TimeUnit.SECONDS.sleep(8);
                } catch (Exception e) {

                }

                panel.remove(arkHelper.nextLevelImage);
                frame.setVisible(true);

                panel.requestFocusInWindow();
            }
            if (level % 10 == 1) {
                Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
            }
            this.level = level;


            arkanoid.initialiseLevels(level);
        }
    }

    /**
     * Checks if player lost the ball or not and decrements his lives by 1.
     *
     * @return Flag to check if the player ran out of lives.
     */
    public boolean lostBall() {
        lives--;

        if (lives < 0) {

            die();
        }

        return lives >= 0;
    }

    /**
     * Checks if the player lost all lives, player will die.
     */
    public void die() {
        FileManager.addNewScoreToLeaderboards(arkHelper.pathLeaderboards, name, score, level);
        FileManager.sendPlayerScore(arkHelper.filePath, name, level);

        if (!AIMode) {

            Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
            arkHelper.setLoseAndWinImage();
            panel.add(arkHelper.lossImage);
            frame.setVisible(true);
            Sound.Play(arkHelper.lossSound, true);

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
            }

            Helper.running = false;
            ObjectList.clear();

            new arkanoid.menu.Splash();
        } else {

            arkAIEngine.train();
            setScore(0);
            setPreviousScore(0);
            arkanoid.initialiseLevels(1);
        }
    }

    /**
     * Sets player's lives.
     *
     * @param lives Lives of the player.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Returns player's lives.
     *
     * @return Number of lives of the player.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the player's score.
     *
     * @param score Score of the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increments the player's score when brick is destroyed.
     *
     * @param add The factor of incrementation.
     */
    public void increaseScore(int add) {
        score += add;
    }

    /**
     * Returns the player's score.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates the game's background music.
     */
    @Override
    public void tick() {
        if (arkHelper.backgroundGameSound[(level - 1) / 10].isStopped() && music && level < 100) {
            Sound.Play(arkHelper.backgroundGameSound[(level - 1) / 10], false);
        }
    }

    /**
     * Renders player on the screen.
     */
    public void render(Graphics g) {

        g.setFont(arkHelper.HUDFont);

        g.setColor(getHUDColor());
        g.drawString(name, 10, 30);
        g.drawString(Integer.toString(score), 10, 60);

        String era = era();
        g.drawString(era, 1100, 30);

        for (int i = 0; i < 10; i++) {

            if (i < level % 10 || level % 10 == 0)
                g.setColor(Color.green);
            else
                g.setColor(Color.red);

            g.drawString(".", 1140 + ((i % 5) * 25), i <= 4 ? 50 : 70);
        }


        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.setColor(getHUDColor());
        g.drawString("Press P to Pause/Resume", 1100, 670);
        g.drawString("Press Esc to return to  Main Menu", 1100, 680);

        drawLives(g);
    }

    /**
     * Checks the era of the player.
     *
     * @return Current player's era.
     */
    private String era() {
        switch (level / 10) {
            case 0:
                return "Stone Age ";

            case 1:
                return " Pharaohs ";

            case 2:
                return "   Aztec  ";

            case 3:
                return "   Greek  ";

            case 4:
                return "   Roman ";

            case 5:
                return "  Indian ";

            case 6:
                return "  Viking  ";

            case 7:
                return "  Chinese ";

            case 8:
                return " Medieval ";

            default:
                return "  Arabian ";
        }
    }

    /**
     * Returns the color of the game's heads up display.
     *
     * @return The color of words.
     */
    private Color getHUDColor() {
        Color color;
        if (level < 11) {
            color = new Color(0xCDBF67);
        } else if (level < 21) {
            color = new Color(0xCAB344);
        } else if (level < 31) {
            color = new Color(0xFAE4AE);
        } else if (level < 41) {
            color = new Color(0xC6BE9E);
        } else if (level < 51) {
            color = new Color(0xCAB344);
        } else if (level < 61) {
            color = new Color(0xC6BE9E);
        } else if (level < 71) {
            color = new Color(0xAEAEAE);
        } else if (level < 81) {
            color = new Color(0xAEAEAE);
        } else if (level < 91) {
            color = new Color(0xD2D370);
        } else {
            color = new Color(0xCAB344);
        }
        return color;
    }


    /**
     * {@inheritDoc}
     */
    public void drawLives(Graphics g) {
        int numOfLives = lives;
        int initialHeight = 80;

        for (int i = 0; numOfLives > 0; i++) {
            int initialWidth = 10;
            for (int j = 0; j < 3 && numOfLives > 0; j++) {
                g.drawImage(arkHelper.life, initialWidth, initialHeight, null);
                initialWidth += arkHelper.life.getWidth(null) + 2;
                numOfLives--;
            }

            initialHeight += arkHelper.life.getHeight(null) + 2;
        }
    }

    /**
     * Returns player's previous score.
     *
     * @return Player's previous score.
     */
    public int getPreviousScore() {
        return previousScore;
    }

    /**
     * Sets the player's previous score.
     *
     * @param previousScore PLayer's previous score.
     */
    public void setPreviousScore(int previousScore) {
        this.previousScore = previousScore;
    }
}

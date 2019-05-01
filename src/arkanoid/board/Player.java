package arkanoid.board;

import arkanoid.Arkanoid;
import arkanoid.arkAIEngine;
import arkanoid.arkFile;
import arkanoid.arkHelper;
import atariCore.BaseObject;
import atariCore.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class Player extends BaseObject {

    private int score;
    private String name;
    private int lives;
    public ArrayList<Paddle> paddle;
    public boolean start;
    private Arkanoid arkanoid;
    private int level;
    private int previousScore;

    public Player(String Name, int lives, Paddle paddle, Arkanoid arkanoid) {
        super(10, 10, null);
        this.name = Name;
//        this.lives = lives;
        this.paddle = new ArrayList<>();
        this.paddle.add(paddle);
        this.arkanoid = arkanoid;
        this.level = 1;

        this.lives = 0;

        previousScore = 0;
        score = 0;
        start = true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {

        if (level > 100) {

            arkFile.addNewScoreToLeadboard(arkHelper.pathLeaderboards, name, score, level);
            arkFile.sendPlayerScore(name, level);
            Sound.Stop(arkHelper.backgroundGameSound[(level - 2) / 10]);

            new arkanoid.creditScreen(arkHelper.timeTheCredit, arkHelper.creditImage, arkHelper.creditSound);
        } else {

            if (score > 0 && !AIMode) {
                Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
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
            this.level = level;

            if (level % 10 == 1) {
                Sound.Stop(arkHelper.backgroundGameSound[(level - 1) / 10]);
            }
            arkanoid.initializeLevels(level);
        }
    }

    public boolean lostBall() {
        lives--;

        if (lives < 0) {

            die();
        }

        return lives >= 0;
    }

    public void die() {
        arkFile.addNewScoreToLeadboard(arkHelper.pathLeaderboards, name, score, level);
        arkFile.sendPlayerScore(name, level);

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

            arkHelper.running = false;
            new arkanoid.menu.Splash();
        } else {

            arkAIEngine.train();
            setScore(0);
            setPreviousScore(0);
            arkanoid.initializeLevels(1);
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int add) {
        score += add;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void tick() {
        if (arkHelper.backgroundGameSound[(level - 1) / 10].isStopped() && music && level < 100) {
            Sound.Play(arkHelper.backgroundGameSound[(level - 1) / 10], false);
        }
    }

    public void render(Graphics g) {

        g.setFont(arkHelper.HUDFont);

        g.setColor(getNameHUDColor());
        g.drawString(name, 10, 30);
        g.drawString(Integer.toString(score), 10, 60);

        g.setColor(getLevelHUDColor());
        String era = era();
        g.drawString(era, 1100, 30);

        for (int i = 0; i < 10; i++) {

            if (i < level % 10 || level == 100)
                g.setColor(Color.green);
            else
                g.setColor(Color.red);

            g.drawString(".", 1140 + ((i % 5) * 25), i <= 4 ? 50 : 70);
        }


        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.setColor(getLevelHUDColor());
        g.drawString("Press P to Pause/Resume", 1100, 670);
        g.drawString("Press Esc to return to  Main Menu", 1100, 680);

        drawLives(g);
    }

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
                return " Arabian  ";
        }
    }

    private Color getLevelHUDColor() {
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
            color = new Color(0x402C2C);
        } else if (level < 61) {
            color = new Color(0x53311B);
        } else if (level < 71) {
            color = new Color(0x1C3837);
        } else if (level < 81) {
            color = new Color(0xAEAEAE);
        } else if (level < 91) {
            color = new Color(0x595959);
        } else {
            color = new Color(0x98994D);
        }
        return color;
    }

    private Color getNameHUDColor() {
        Color color;
        if (level < 11) {
            color = new Color(0xCDBF67);
        } else if (level < 21) {
            color = new Color(0xCAB344);
        } else if (level < 31) {
            color = new Color(0xCAB344);
        } else if (level < 41) {
            color = new Color(0x24353B);
        } else if (level < 51) {
            color = new Color(0x402C2C);
        } else if (level < 61) {
            color = new Color(0x172F1B);
        } else if (level < 71) {
            color = new Color(0x1C3837);
        } else if (level < 81) {
            color = new Color(0xD58384);
        } else if (level < 91) {
            color = new Color(0xD2D370);
        } else {
            color = new Color(0x98994D);
        }
        return color;
    }

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

    public int getPreviousScore() {
        return previousScore;
    }

    public void setPreviousScore(int previousScore) {
        this.previousScore = previousScore;
    }
}

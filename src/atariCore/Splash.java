package atariCore;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

/**
 * Main atari Core splash window.
 */
public class Splash {

    /**
     * Start X coordinates of buttons.
     */
    protected int xStart = (screenWidth * 25 / 100);
    /**
     * Start Y coordinates of buttons.
     */
    protected int yStart = 212;
    /**
     * Y offset between buttons.
     */
    protected int bOffset = btnDim.height + 20;
    /**
     * New game button.
     */
    protected JButton newGameButton;
    /**
     * AI-Mode button.
     */
    protected JButton AIButton;
    /**
     * Leaderboards menu button.
     */
    protected JButton LeaderboardsButton;
    /**
     * Settings menu button.
     */
    protected JButton settingsButton;
    /**
     * Exit game button.
     */
    protected JButton exitButton;

    /**
     * Parameterized constructor which sets splash title and main used font.
     *
     * @param title    Title of Splash screen.
     * @param fontPath Font Path.
     */
    public Splash(String title, String fontPath) {


        frame.setTitle(title);

        if (panel != null) {
            frame.getContentPane().remove(panel);
        }

        panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImage(splashBackgroundImagePath, 1), 0, 0, null);
            }
        };

        panel.setSize(Helper.screenWidth, Helper.screenHeight);

        Helper.setSplashButtonFontSize(40);
        Helper.font = Helper.setFont(fontPath, Helper.splashButtonFontSize);

        setNewGameButton(xStart, yStart, Helper.btnDim);
        setAIButton(xStart, (yStart += bOffset), Helper.btnDim);
        setLeaderboardsButton(xStart, (yStart += bOffset), Helper.btnDim);
        setSettingsButton(xStart, (yStart += bOffset), Helper.btnDim);
        setExitButton(xStart, (yStart += bOffset), Helper.btnDim);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * Initialized start new game button.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setNewGameButton(int x, int y, Dimension dim) {
        newGameButton = Helper.buttonHelper("New Game", x, y, dim);
    }

    /**
     * Initialized AI-Mode button.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setAIButton(int x, int y, Dimension dim) {
        AIButton = Helper.buttonHelper("AI Mode", x, y, dim);
    }

    /**
     * Initialized leaderboard button.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setLeaderboardsButton(int x, int y, Dimension dim) {
        LeaderboardsButton = Helper.buttonHelper("Leaderboards", x, y, dim);
    }

    /**
     * Initialized settings button.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setSettingsButton(int x, int y, Dimension dim) {
        settingsButton = Helper.buttonHelper("Settings", x, y, dim);
    }

    /**
     * Initialized Exit button.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setExitButton(int x, int y, Dimension dim) {
        exitButton = Helper.buttonHelper("Exit", x, y, dim);

    }
}

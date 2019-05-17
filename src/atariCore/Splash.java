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
     * Returns to welcome screen.
     */
    protected JButton backButton;

    /**
     * Parameterised constructor which sets splash title and main used font.
     *
     * @param title    Title of Splash screen.
     * @param fontFile Font file name with extension. ex: "joystix monospace.ttf"
     */
    public Splash(String title, String fontFile) {

        Helper.PERIOD = 5;
        frame.setTitle(title);

        if (panel != null) {
            frame.getContentPane().remove(panel);
        }

        panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(FileManager.loadImage(splashBackgroundImagePath, 1), 0, 0, null);
            }
        };

        panel.setSize(Helper.screenWidth, Helper.screenHeight);

        Helper.setSplashButtonFontSize(40);
        Helper.font = Helper.setFont(fontPath + fontFile, Helper.splashButtonFontSize);

        setNewGameButton(xStart, yStart, Helper.btnDim);
        setAIButton(xStart, (yStart += bOffset), Helper.btnDim);
        setLeaderboardsButton(xStart, (yStart += bOffset), Helper.btnDim);
        setSettingsButton(xStart, (yStart += bOffset), Helper.btnDim);
        setBackButton(xStart, (yStart += bOffset), Helper.btnDim);

        backButton.addActionListener(e -> {
                    new welcome.Splash();
                    if (sounds)
                        Sound.Play(Helper.clickSound, true);
                }
        );

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * initialised start new game button.
     *
     * @param x   X coordinates of the button.
     * @param y   Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setNewGameButton(int x, int y, Dimension dim) {
        newGameButton = Helper.buttonHelper("New Game", x, y, dim);
    }

    /**
     * initialised AI-Mode button.
     *
     * @param x   X coordinates of the button.
     * @param y   Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setAIButton(int x, int y, Dimension dim) {
        AIButton = Helper.buttonHelper("AI Mode", x, y, dim);
    }

    /**
     * initialised leaderboard button.
     *
     * @param x   X coordinates of the button.
     * @param y   Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setLeaderboardsButton(int x, int y, Dimension dim) {
        LeaderboardsButton = Helper.buttonHelper("Leaderboards", x, y, dim);
    }

    /**
     * initialised settings button.
     *
     * @param x   X coordinates of the button.
     * @param y   Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setSettingsButton(int x, int y, Dimension dim) {
        settingsButton = Helper.buttonHelper("Settings", x, y, dim);
    }

    /**
     * initialised Exit button.
     *
     * @param x   X coordinates of the button.
     * @param y   Y coordinates of the button.
     * @param dim Dimension of the button.
     */
    protected void setBackButton(int x, int y, Dimension dim) {
        backButton = Helper.buttonHelper("Back", x, y, dim);
    }
}

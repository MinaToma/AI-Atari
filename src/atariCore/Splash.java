package atariCore;

import javax.swing.*;
import java.awt.*;
import static atariCore.Helper.frame;
import static atariCore.Helper.panel;

public class Splash {

    protected int xStart = 100, yStart = 100, bOffset = 150;

    protected JButton newGameButton, AIButton, LeaderboardsButton, settingsButton, exitButton;

    public Splash(String title, String fontPath) {

        frame.setTitle(title);
        new Leaderboards("src/Resources/Files/Leaderboards.txt");

        if(panel != null)
            frame.getContentPane().remove(panel);

        panel = new JPanel(new GridLayout(0, 1));
        panel.setSize(Helper.screenWidth, Helper.screenHeight);

        Helper.setSplashButtonFontSize(40);
        Helper.setFont(fontPath, Helper.splashButtonFontSize);

        setNewGameButton(xStart, yStart, Helper.btnDim);
        setAIButton(xStart, (yStart += bOffset), Helper.btnDim);
        setLeaderboardsButton(xStart, (yStart += bOffset), Helper.btnDim);
        setSettingsButton(xStart, (yStart += bOffset), Helper.btnDim);
        setExitButton(xStart, (yStart += bOffset), Helper.btnDim);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    protected void setNewGameButton(int x, int y, Dimension dim) {
        newGameButton = Helper.btnHelper("New Game", x, y, dim);
    }

    protected void setAIButton(int x, int y, Dimension dim) {
        AIButton = Helper.btnHelper("New AI Game", x, y, dim);
    }

    protected void setLeaderboardsButton(int x, int y, Dimension dim) {
        LeaderboardsButton = Helper.btnHelper("Leaderboards", x, y, dim);
    }

    protected void setSettingsButton(int x, int y, Dimension dim) {
        settingsButton = Helper.btnHelper("Settings", x, y, dim);
    }

    protected void setExitButton(int x, int y, Dimension dim) {
        exitButton = Helper.btnHelper("Exit", x, y, dim);
        exitButton.addActionListener(e -> frame.dispose());
    }
}

package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash {

    protected JFrame frame;
    protected JPanel panel;
    protected Dimension btnDim = new Dimension(Helper.screenWidth / 2, Helper.screenHeight / 10);
    protected int xStart = 100, yStart = 100, bOffset = 150;

    protected JButton newGameButton, AIButton, LeaderboardsButton, settingsButton, exitButton;

    public Splash(String title, String fontPath) {

        frame = new JFrame(title);

        panel = new JPanel(new GridLayout(0, 1));
        panel.setLayout(new GridLayout(0, 1));
        //panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setSize(Helper.screenWidth, Helper.screenHeight);

        frame.setPreferredSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMaximumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMinimumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Helper.setSplashButtonFontSize(40);
        Helper.setFont(fontPath, Helper.splashButtonFontSize);

        setNewGameButton(xStart, yStart, btnDim);
        setAIButton(xStart, (yStart += bOffset), btnDim);
        setLeaderboardsButton(xStart, (yStart += bOffset), btnDim);
        setSettingsButton(xStart, (yStart += bOffset), btnDim);
        setExitButton(xStart, (yStart += bOffset), btnDim);

        frame.add(panel);
        frame.setVisible(true);
    }

    protected void setNewGameButton(int x, int y, Dimension dim) {
        newGameButton = Helper.btnHelper("New Game", x, y, dim, panel);
    }

    protected void setAIButton(int x, int y, Dimension dim) {
        AIButton = Helper.btnHelper("New AI Game", x, y, dim, panel);
    }

    protected void setLeaderboardsButton(int x, int y, Dimension dim) {
        LeaderboardsButton = Helper.btnHelper("Leaderboards", x, y, dim, panel);
    }

    protected void setSettingsButton(int x, int y, Dimension dim) {
        settingsButton = Helper.btnHelper("Settings", x, y, dim, panel);
    }

    protected void setExitButton(int x, int y, Dimension dim) {
        exitButton = Helper.btnHelper("Exit", x, y, dim, panel);
        exitButton.addActionListener(e -> frame.dispose());
    }
}

package atariCore;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static atariCore.Helper.*;

public class Splash {

    protected int xStart = (int)(screenWidth * 25 / 100), yStart = 212, bOffset = btnDim.height + 20;

    protected JButton newGameButton, AIButton, LeaderboardsButton, settingsButton, exitButton;

    public Splash(String title, String fontPath) {


        frame.setTitle(title);
        //new Leaderboards();

        if(panel != null) {
            frame.getContentPane().remove(panel);
        }

        panel = new JPanel(null){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(getImage("src/Resources/Images/background/splash.png", 1), 0, 0, null);
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

    protected void setNewGameButton(int x, int y, Dimension dim) {
        newGameButton = Helper.buttonHelper("New Game", x, y, dim);
    }

    protected void setAIButton(int x, int y, Dimension dim) {
        AIButton = Helper.buttonHelper("AI Mode", x, y, dim);
    }

    protected void setLeaderboardsButton(int x, int y, Dimension dim) {
        LeaderboardsButton = Helper.buttonHelper("Leaderboards", x, y, dim);
    }

    protected void setSettingsButton(int x, int y, Dimension dim) {
        settingsButton = Helper.buttonHelper("Settings", x, y, dim);
    }

    protected void setExitButton(int x, int y, Dimension dim) {
        exitButton = Helper.buttonHelper("Exit", x, y, dim);
        exitButton.addActionListener(e -> frame.dispose());
    }
}

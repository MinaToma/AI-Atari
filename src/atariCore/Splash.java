package atariCore;

import javax.swing.*;

public class Splash {
    /*
    * jframe
    * butttons
    * game object, title
    */
    JFrame frame;

    JButton newGameButton, settingsButton, exitButton;

    protected void setNewGameButton(int x, int y, Handler handler)
    {
        newGameButton = new JButton();
        newGameButton.setLocation(x, y);
        newGameButton.setText("New Game");

    }

    protected void setSettingsButton(int x, int y, Handler handler)
    {
        settingsButton = new JButton();
        settingsButton.setLocation(x, y);
        settingsButton.setText("Settings");
    }

    protected void setExitButton(int x, int y, Handler handler)
    {
        exitButton = new JButton();
        exitButton.setLocation(x, y);
        exitButton.setText("Exit");
    }

}

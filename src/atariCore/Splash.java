package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash {

    protected JFrame frame;
    protected Panel panel;
    protected Dimension btnDim = new Dimension(100, 100);
    protected int xStart = 100, yStart = 100, bOffset = 150;

    protected JButton newGameButton, settingsButton, exitButton;

    public Splash(String title) {

        frame = new JFrame(title);
        panel = new Panel(new GridLayout(0, 1));
        panel.setLayout(null);
        panel.setSize(Helper.screenWidth, Helper.screenHeight);


        frame.setPreferredSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMaximumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMinimumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        setNewGameButton(xStart, yStart, btnDim);
        setSettingsButton(xStart, (yStart += bOffset), btnDim);
        setExitButton(xStart, (yStart += bOffset), btnDim);

        frame.add(panel);
        frame.setVisible(true);
    }

    protected void setNewGameButton(int x, int y, Dimension dim) {
        newGameButton = Helper.btnHelper(newGameButton, "New Game", x, y, dim, panel);
    }

    protected void setSettingsButton(int x, int y, Dimension dim) {
        settingsButton = Helper.btnHelper(settingsButton, "Settings", x, y, dim, panel);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    protected void setExitButton(int x, int y, Dimension dim) {

        exitButton = Helper.btnHelper(exitButton, "Exit", x, y, dim, panel);
        exitButton.addActionListener(e -> frame.dispose());
    }
}

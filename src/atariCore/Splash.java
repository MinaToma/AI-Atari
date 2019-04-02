package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Splash {

    protected JFrame frame;
    protected JPanel panel;
    protected Dimension btnDim = new Dimension(Helper.screenWidth/2, Helper.screenHeight/10);
    protected int xStart = 100, yStart = 100, bOffset = 150;

    protected JButton newGameButton, settingsButton, exitButton;

    public Splash(String title , String fontPath, JPanel panel) {
        Sounds s = new Sounds();
        s.backgroundSplash.loop(20);
        frame = new JFrame(title);
        this.panel = panel;
        this.panel.setLayout(new GridLayout(0,1));
        //this.panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.panel.setSize(Helper.screenWidth, Helper.screenHeight);

        frame.setPreferredSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMaximumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMinimumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Helper.setFont(fontPath, 40);

        setNewGameButton(xStart, yStart, btnDim);
        setSettingsButton(xStart, (yStart += bOffset), btnDim);
        setExitButton(xStart, (yStart += bOffset), btnDim);

        frame.add(this.panel);
        frame.setVisible(true);
    }

    public Splash(String title, String fontPath) {

        this(title , fontPath, new JPanel(new GridLayout(0, 1)));
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

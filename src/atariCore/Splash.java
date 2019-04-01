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

    public Splash(String title) {

        Sounds s = new Sounds();
        s.background();
        frame = new JFrame(title);
        panel = new JPanel(new GridLayout(0, 1));
        panel.setLayout(new GridLayout(0,1));
        //panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setSize(Helper.screenWidth, Helper.screenHeight);

        frame.setPreferredSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMaximumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));
        frame.setMinimumSize(new Dimension(Helper.screenWidth, Helper.screenHeight));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Helper.setCursorImage(panel, "src/Resources/image/yellowc2.png");
        Helper.setFont("src/Resources/Fonts/3270Medium.ttf", 40);


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

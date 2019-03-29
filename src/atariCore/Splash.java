package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash {

    /*
    * jframe
    * butttons
    * game object, title
    */

    protected JFrame frame;
    protected Panel panel;
    protected int width = 640 , height = width / 9 * 12;
    protected Dimension btnDim = new Dimension(100 , 100);
    protected int xStart = 100 , yStart = 100 , bOffset = 150;

    protected JButton newGameButton, settingsButton, exitButton;

    public Splash(String title) {

        frame = new JFrame(title);
        panel = new Panel();
        panel.setLayout(null);
        panel.setSize(width , height);

        frame.setPreferredSize(new Dimension(width , height));
        frame.setMaximumSize(new Dimension(width , height));
        frame.setMinimumSize(new Dimension(width , height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        setNewGameButton(xStart , yStart , btnDim);
        setSettingsButton(xStart , (yStart  += bOffset) , btnDim);
        setExitButton(xStart , (yStart += bOffset) , btnDim);

        frame.add(panel);
        frame.setVisible(true);
    }

    protected void setNewGameButton(int x, int y , Dimension dim)
    {
         newGameButton = Helper.btnHelper(newGameButton , "New Game" , x , y , dim , panel);
    }

    protected void setSettingsButton(int x, int y , Dimension dim) {
        settingsButton = Helper.btnHelper(settingsButton, "Settings", x, y, dim , panel);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    protected void setExitButton(int x, int y , Dimension dim) {

        exitButton = Helper.btnHelper(exitButton, "Exit", x, y, dim, panel);
        exitButton.addActionListener(e -> frame.dispose());
    }
}

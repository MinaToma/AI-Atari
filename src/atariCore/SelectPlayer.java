package atariCore;

import javax.swing.*;
import java.awt.*;

import static atariCore.Helper.*;

public abstract class SelectPlayer extends JPanel {

    /**
     * Start button to start the next panel.
     */
    protected JButton startButton;
    /**
     * Back button to return to main menu.
     */
    protected JButton backButton;
    /**
     * Title of the frame.
     */
    protected JLabel labelName = new JLabel("Enter Your Name");
    /**
     * Text area to write player name.
     */
    protected JTextField textName;

    public SelectPlayer() {
        initFrame();
    }

    /**
     * Initializes the frame and panel.
     */
    protected void initFrame() {
        frame.setTitle("Select Player");
        frame.getContentPane().remove(panel);

        panel = this;

        setDesign();

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * Sets the main frame components and design.
     */
    protected void setDesign() {

        setLayout(null);

        setStartButton(screenWidth / 3 + 150, 450);
        setBackButton(screenWidth / 3 - 150, 450);

        textName = new JTextField(20);
        textName.setFont(font);
        textName.setBounds(screenWidth / 3 - 190, 300, 800, 70);
        textName.setHorizontalAlignment(JTextField.CENTER);
        textName.setForeground(buttonBackgroundColor);
        textName.setBackground(foregroundColor);
        textName.requestFocus();

        labelName.setFont(font);
        labelName.setForeground(buttonBackgroundColor);
        labelName.setBounds(screenWidth / 3 - 50, 80, screenWidth / 3 - 50 + 710, 300);

        add(labelName);
        add(textName);

        setCursorImage(pathCursor);
    }

    /**
     * Sets the actions of every button according to every game.
     */
    protected abstract void setActions();

    /**
     * Initializes back button and positions it to given coordinates.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     */
    protected void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, new Dimension(250, 100));
    }

    /**
     * Initializes start button and positions it to given coordinates.
     *
     * @param x X coordinates of the button.
     * @param y Y coordinates of the button.
     */
    protected void setStartButton(int x, int y) {
        startButton = Helper.buttonHelper("Start!", x, y, new Dimension(400, 100));
    }
}

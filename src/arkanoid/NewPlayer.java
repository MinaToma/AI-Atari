package arkanoid;

import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arkanoid.arkHelper.*;

import static arkanoid.arkHelper.pathCursor;
import static arkanoid.arkHelper.pathImage;
import static atariCore.Helper.*;

public class NewPlayer extends JPanel {

    JButton OK;
    JTextField textName;
    JLabel labelName = new JLabel("Enter Your Name");

    public NewPlayer() {
        initFrame();
    }

    private void initFrame() {

        frame.setTitle("New Player");
        frame.getContentPane().remove(panel);

        panel = this;

        panel.setBackground(new Color(23, 28, 40));
        OK = Helper.buttonHelper("Start!");
       /*
        OK.setForeground();
        OK.setBackground();

*/
        textName = new JTextField(20);
        textName.setFont((arkHelper.font));
        textName.setHorizontalAlignment(JTextField.CENTER);
        textName.setForeground(buttonBackgroundColor);
        textName.setBackground(foregroundColor);

        labelName.setFont(arkHelper.font);

        labelName.setForeground(buttonBackgroundColor);

        setLayout(null);
        labelName.setBounds(screenWidth / 3 - 50, 80, screenWidth / 3 - 50 + 710, 300);

        labelName.setLocation(screenWidth / 3 - 50, 80);
        labelName.setFocusable(true);
        labelName.setPreferredSize(new Dimension(900, 300));
        textName.setBounds(screenWidth / 3 - 100, 300, 600, 70);
        OK.setBounds(screenWidth / 4 + 100, 450, 400, 100);

        add(labelName);
        add(textName);
        add(OK);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textName.getText().length() > 0 && textName.getText().length() <= 20) {
                    arkHelper.running = true;
                    Sound.Stop(arkHelper.backgroundSplashSound);

                    new Arkanoid(textName.getText());
                }
            }
        });

        setCursorImage(panel, pathCursor);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "background/bg.jpg", 1), 0, 0, null);
    }
}


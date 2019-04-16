package arkanoid;

import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static atariCore.Helper.frame;
import static atariCore.Helper.panel;

public class NewPlayer extends JPanel {

    JButton OK;
    JTextField textName;

    public NewPlayer() {
        initFrame();
    }

    private void initFrame() {

        frame.setTitle("Enter Your Name");
        frame.getContentPane().remove(panel);
        panel = this;

        OK = Helper.buttonHelper("OK");

        textName = new JTextField(20);
        textName.setFont((arkHelper.font.deriveFont(100)));
        textName.setHorizontalAlignment(JTextField.CENTER);
        textName.setForeground(Color.YELLOW);
        setLayout(new GridLayout(0, 1));
        add(textName);
        add(OK);

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textName.getText().length() > 0 && textName.getText().length() <= 20) {
                    arkHelper.running = true;
                    Sounds.backgroundSplashSound.stop();

                    new Arkanoid(textName.getText());
                }
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

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

    String namePlayer;
    JButton OK;
    JTextField textName;

    public NewPlayer() {
        initFrame();
    }

    private void initFrame() {

        frame.setTitle("Enter Your Name");
        frame.getContentPane().remove(panel);
        panel = this;

        Font font = new Font("src/Resources/Fonts/3270Medium.ttf", Font.BOLD, 100);

        OK = Helper.btnHelper("OK");

        textName = new JTextField(20);
        textName.setFont(font);
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
                    Sound.stop(Sounds.backgroundSplashSound);
                    Sound.loop(1000,Sounds.BackgroundGameSound);
                    new Arkanoid(textName.getText());
                }

            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }


}

package arkanoid;

import atariCore.Helper;
import atariCore.Sound;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arkanoid.arkHelper.*;

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
        textName.setForeground(new Color(234, 156, 68));
        textName.setBackground(new Color(23, 28, 40));

        labelName.setFont(arkHelper.font);

        labelName.setForeground(new Color(234, 156, 68));

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
                    Sound.stop(Sounds.backgroundSplashSound);

                    new Arkanoid(textName.getText());
                }

            }
        });

        setCursorImage(panel, "src/Resources/Images/yellowc2.png");
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }


    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "background/splash.png", 1), 0, 0, null);
    }
}

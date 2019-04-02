package arkanoid.menu;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;
import atariCore.Helper;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/3270Medium.ttf");
        arkHelper.setCursorImage(panel, "src/Resources/image/yellowc2.png");
        newGameButton.addActionListener(e -> {
            new Arkanoid();
            frame.dispose();

        });
    }

    public Splash(JPanel panel) {

        super("Arkanoid", "src/Resources/Fonts/3270Medium.ttf" , panel);
        newGameButton.addActionListener(e -> {
            new Arkanoid();
            frame.dispose();

        });
    }

    public static void main(String[] args) {
        new Splash();
    }
}

package arkanoid.menu;

import arkanoid.Arkanoid;
import arkanoid.NewPlayer;
import arkanoid.arkHelper;
import atariCore.Helper;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/3270Medium.ttf");
        arkHelper.setCursorImage(panel, "src/Resources/image/yellowc2.png");
        arkHelper.backgroundSplashSound();
        newGameButton.addActionListener(e -> {

            frame.dispose();
            new NewPlayer();

        });
    }

    public static void main(String[] args) {

        new Splash();
    }
}

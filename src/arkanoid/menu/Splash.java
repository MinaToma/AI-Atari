package arkanoid.menu;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid");
        newGameButton.addActionListener(e -> {
            frame.dispose();
            new Arkanoid();

        });
    }

    public static void main(String[] args) {
        new Splash();
    }
}

package arkanoid.menu;

import arkanoid.Arkanoid;
import arkanoid.NewPlayer;
import arkanoid.arkHelper;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/3270Medium.ttf");
        arkHelper.setCursorImage(panel, "src/Resources/image/yellowc2.png");
       // arkHelper.backgroundSplashSound();
        newGameButton.addActionListener(e -> {

            frame.dispose();
            arkHelper.training = true;
            arkHelper.running = true;

            new Arkanoid("AI");
          //  new NewPlayer();
        });
    }

    public static void main(String[] args) {

        new Splash();
    }
}

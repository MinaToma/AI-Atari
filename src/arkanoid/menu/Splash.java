package arkanoid.menu;

import arkanoid.NewPlayer;
import arkanoid.ObjectList;
import arkanoid.Sounds;
import arkanoid.arkHelper;
import atariCore.Sound;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/3270Medium.ttf");
        arkHelper.setCursorImage(panel, "src/Resources/image/yellowc2.png");
        Sound.stop(Sounds.BackgroundGameSound);
        Sound.loop(1000,Sounds.backgroundSplashSound);

        newGameButton.addActionListener(e -> {

            frame.dispose();
            new NewPlayer();
        });
    }

    public static void main(String[] args) {

        new arkHelper();
        new ObjectList();
        new Sounds();
        new Splash();

    }
}

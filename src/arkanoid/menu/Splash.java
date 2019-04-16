package arkanoid.menu;

import arkanoid.NewPlayer;
import arkanoid.ObjectList;
import arkanoid.arkHelper;

import static atariCore.Helper.panel;

import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/Cowboys.otf");
        arkHelper.setCursorImage(panel, "src/Resources/Images/yellowc2.png");


        for(int i=0 ; i<10 ; i++)
        {
           arkHelper.backgroundGameSound[i].stop();
        }
        Sound.Play(arkHelper.backgroundSplashSound,false);


        newGameButton.addActionListener(e -> {

            new NewPlayer();
        });
    }

    public static void main(String[] args) {

        new Helper();
        new LoadingScreen();

        new arkHelper();
        new ObjectList();

        new Splash();
    }
}

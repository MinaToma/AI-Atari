package arkanoid.menu;

import arkanoid.NewPlayer;
import arkanoid.ObjectList;
import arkanoid.Sounds;
import arkanoid.arkHelper;

import static arkanoid.ObjectList.backgroundList;
import static arkanoid.arkHelper.*;
import static atariCore.BaseObjectList.handler;
import static atariCore.Helper.*;

import atariCore.Background;
import atariCore.Helper;
import atariCore.LoadingScreen;
import atariCore.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/joystix monospace.ttf");

        arkHelper.setCursorImage(panel, pathCursor);

        for(int i=0 ; i<10 ; i++)
        {
            Sounds.stop(Sounds.backgroundGameSound[i]);
        }

        Sound.loop(1000,Sounds.backgroundSplashSound);

        newGameButton.addActionListener(e -> {

            new NewPlayer();
        });

        settingsButton.addActionListener(e -> {

            new Settings();
        });

    }

    public static void main(String[] args) {

        new Helper();
        new LoadingScreen();
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e)
        {

        }

        new arkHelper();
        new ObjectList();
        new Sounds();

        new Splash();
    }
}

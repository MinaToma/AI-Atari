package arkanoid.menu;

import java.awt.*;

import static arkanoid.arkHelper.pathImage;
import static atariCore.Helper.getImage;

public class Settings extends atariCore.Settings {


    Settings()
    {
        backButton.addActionListener(e->{
            new Splash();
        });
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage(getImage(pathImage + "background/bg.jpg", 1), 0, 0, null);
    }
}

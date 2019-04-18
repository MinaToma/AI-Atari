package arkanoid.menu;

public class Settings extends atariCore.Settings {

    Settings()
    {
        backButton.addActionListener(e->{
            new Splash();
        });
    }
}

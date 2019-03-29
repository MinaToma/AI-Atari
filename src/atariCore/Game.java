package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;

abstract public class Game extends JPanel implements KeyListener {

    protected Handler handler;
    protected Timer timer;

    public Game(String title) {

        handler = new Handler();
        addKeyListener(this);
        setFocusable(true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.tick();
                revalidate();
                repaint();
            }
        }, Helper.DELAY, Helper.PERIOD);

        new Window(Helper.screenWidth, Helper.screenHeight, title, this);
    }

    @Override
    abstract public void keyPressed(KeyEvent keyEvent);

    @Override
    abstract public void keyReleased(KeyEvent keyEvent);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        handler.render(g);
    }
}

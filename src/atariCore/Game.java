package atariCore;

import arkanoid.Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TimerTask;
import java.util.Timer;
import static atariCore.Helper.frame;
import static atariCore.Helper.panel;
import static atariCore.BaseObjectList.*;

abstract public class Game extends JPanel implements KeyListener , MouseListener , MouseMotionListener {

    protected Timer timer;

    public Game(String title) {

        frame.getContentPane().remove(panel);
        frame.setTitle(title);
        panel = this;

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(!Helper.running) {
                    timer.cancel();
                    timer.purge();
                    return;
                }

                if(handler != null) {
                    handler.tick();
                    revalidate();
                    repaint();
                }
            }
        } , Helper.DELAY, Helper.PERIOD);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        requestFocusInWindow();
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

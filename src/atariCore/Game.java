package atariCore;

import arkanoid.Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

import static atariCore.BaseObjectList.*;
import static atariCore.Helper.*;

abstract public class Game extends JPanel implements AtariKeyListener, MouseListener, MouseMotionListener {

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

                if (!Helper.running) {
                    timer.cancel();
                    timer.purge();
                    return;
                }

                if (handler != null && !pause) {

                    if (AIMode) {
                        sendDataToAI();
                    } else {
                        pressKey();
                    }
                    handler.tick();
                    revalidate();
                    repaint();
                    Toolkit.getDefaultToolkit().sync();
                }
            }
        }, Helper.DELAY, Helper.PERIOD);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        requestFocusInWindow();
    }

    abstract protected void sendDataToAI();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        handler.render(g);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
}

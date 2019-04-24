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
    protected JLabel pausedBG = new JLabel();
    private int setVisibleCounter = 0;

    public Game(String title) {

        frame.getContentPane().remove(panel);
        frame.setTitle(title);
        panel = this;

        setLayout(null);
        panel.setSize(screenWidth, screenHeight);

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

                    if(setVisibleCounter == 1){
                        panel.remove(pausedBG);
                        frame.setVisible(true);
                        setVisibleCounter = 0;
                    }

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

                if(pause){
                    if(setVisibleCounter == 0){
                        setPausedBG();
                        setVisibleCounter = 1;
                    }
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

    public abstract void setPausedBG();
}

package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TimerTask;
import java.util.Timer;

abstract public class Game extends JPanel implements KeyListener , MouseListener , MouseMotionListener {

    protected Handler handler;
    protected Timer timer;
    protected JFrame frame;

    public Game(String title) {

        handler = new Handler();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);

        frame = new JFrame(title);
        initFrame();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(!Helper.running) {
                    timer.cancel();
                    timer.purge();
                    frame.dispose();
                    return;
                }
                handler.tick();
                revalidate();
                repaint();
            }
        } , Helper.DELAY, Helper.PERIOD);
     }

    private void initFrame() {

        frame.setSize(Helper.screenWidth , Helper.screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
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

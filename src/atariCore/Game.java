package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

import static atariCore.Helper.*;

/**
 * Base game loop (Runnable Thread) with specified delay and period of the thread.
 */
abstract public class Game extends JPanel implements AtariKeyListener, MouseListener, MouseMotionListener {

    /**
     * Pre-specified java timer to handle the main game loop regardless of PC's speed.
     */
    protected Timer timer;

    /**
     * Parameterised constructor takes game title and starts main game loop.
     *
     * @param title Game title.
     */
    public Game(String title) {

        frame.getContentPane().remove(panel);
        frame.setTitle(title);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel = this;

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

                if (Handler.getInstance() != null) {
                    if (!pause) {
                        if (AIMode) {
                            sendDataToAI();
                        } else {
                            pressKey();
                        }
                        Handler.getInstance().tick();
                    }
                    refresh();
                }
            }
        }, Helper.DELAY, Helper.PERIOD);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        requestFocusInWindow();
    }

    /**
     * Interacts with python scripts which are used to run AI-Engine.
     */
    abstract protected void sendDataToAI();

    /**
     * Renders the game where handler's objects are rendered.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Handler.getInstance().render(g);
    }

    /**
     * Refreshed the game and redraws game objects.
     */
    public void refresh() {
        revalidate();
        repaint();
        Toolkit.getDefaultToolkit().sync();
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

    @Override
    public void initKeys() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void pressKey() {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}

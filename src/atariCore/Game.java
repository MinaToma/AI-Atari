package atariCore;

import arkanoid.Arkanoid;
import arkanoid.Connection;
import arkanoid.arkHelper;
import arkanoid.board.Ball;
import arkanoid.board.Paddle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class Game extends JPanel implements KeyListener , MouseListener , MouseMotionListener {

    public CopyOnWriteArrayList<Integer> y = new CopyOnWriteArrayList<>();
    protected Handler handler;
    public Timer timer;
    protected JFrame frame;
    private int frameCounter = 0;
    private int frameLimit = 50;
    public Game ana;

    public Connection connection = new Connection();


    public Game(String title) {

        ana = this;
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

                if(Helper.running) {
                    handler.tick();
                    revalidate();
                    repaint();
                }

                if(arkHelper.training) {

                    if(frameCounter + 1 >= frameLimit) {
                        try {

                            captureFrame();
                            arkHelper.running = false;

                            String dir = connection.getDIR();

                            Paddle p = ((Arkanoid)ana).p;
                            Ball b = ((Arkanoid)ana).b;

                            if(b.getVelX() == 0 && b.getVelY() == 0) {
                                b.setVelY(arkHelper.initBallYSpeed);
                                b.setVelX(arkHelper.initBallXSpeed);
                            }

                            if(dir.equals("right")) {

                                ((Arkanoid)ana).p.setVelX(arkHelper.paddleSpeed);
                            }
                            else if(dir.equals("left")) {

                                ((Arkanoid)ana).p.setVelX(-arkHelper.paddleSpeed);
                            }


                            int dif = (int)Math.sqrt( (int) (( b.getX() - p.getX() ) * ( b.getX() - p.getX() ) + ( p.getY() - b.getY() ) * ( p.getY() - b.getY() ))  );


                            y.add((int)1e9 - dif);
                            System.out.println( ( (int) 1e9 - dif ) );


                            arkHelper.running = true;

                            //System.exit(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    frameCounter = (frameCounter += 1) % frameLimit;
                }
            }
        } , Helper.DELAY, Helper.PERIOD);
     }


    public void captureFrame() throws IOException {

        BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.paint(img.getGraphics());

        File directory = new File("src/SavedFrames");
        if (! directory.exists()){
            directory.mkdir();
        }

        File outputfile = new File("src/SavedFrames/saved" + ".png");
        ImageIO.write(img, "png", outputfile);
        frameCounter++;
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

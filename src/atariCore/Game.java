package atariCore;

import arkanoid.Arkanoid;
import arkanoid.Connection;
import arkanoid.arkHelper;
import arkanoid.board.Ball;
import arkanoid.board.Brick;
import arkanoid.board.Paddle;
import arkanoid.board.Player;

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
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class Game extends JPanel implements KeyListener , MouseListener , MouseMotionListener {

    public CopyOnWriteArrayList<Double> y = new CopyOnWriteArrayList<>();
    protected Handler handler;
    public Timer timer;
    protected JFrame frame;
    private int frameCounter = 0;
    private int framenum = 1;
    private int frameLimit = 1;
    public Game ana;
    public double dif = 0 ;
    public Connection connection = new Connection();
    public float slack = 0 ;
    public int preiviesScore = 0;
    public int maxScore = 0;
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
                    try {
                        handler.tick();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    revalidate();
                    repaint();
                }


                if(arkHelper.training) {
//                    System.out.println("ziaaaaaad  "+-dif);

                    if(frameCounter + 1 >= frameLimit) {
//                        try {

                            Player pl = ((Arkanoid)ana).player;
                            if(pl.getScore() == 0 )
                                preiviesScore = 0 ;
                            if(pl.getLevel() == 7) {
                                try {
                                    pl.setLevel(1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
//                        if(pl.getLevel() == 1) {
//                            try {
//                                pl.setLevel(1);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
                            arkHelper.running = false;
                        Paddle p = ((Arkanoid)ana).p;
                        Ball b = ((Arkanoid)ana).b;
                        if(slack %50==0 && slack!=0) {
                            b.train = true;
                        }
                        System.out.println("slack is " + slack);
//                        if(-dif<= -20 )
//                            return;

                        float x [] = new float[22];
                        float yy [] = new float[22];
                        Arrays.fill(x,0);
                        Arrays.fill(yy,0);
                        int i=0;
                        float brickX=0;
                        float brickY=0;
                        int R = 0;
                        int L = 0 ;
                            for (BaseObject o : handler.object)
                            {

                                if(o instanceof Brick)
                                {
//                                    if(i<22) {
//                                        yy[i] = o.getY();
//                                        x[i] = o.getX();
//                                        i++;
////                                    }
                                        if(o.getX()>(p.getX()+p.getImageWidth()/2))
                                        R++;
                                    else
                                        L++;
//                                    if(o.getY() >brickY)
//                                    {
//                                        brickY=o.getY();
//                                        brickX=o.getX();
//                                    }
                                }
                            }


                            String dir = connection.getDIR(p.getX(), b.getX(), b.getY(),R,L);


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
                            else if(dir.equals("same")) {

                                ((Arkanoid)ana).p.setVelX(0);
                            }
                            if(preiviesScore - pl.getScore() == 0 )
                                slack += 0.01 ;
                            else
                                slack = 0 ;

                            if(pl.getScore()>maxScore)
                                maxScore = pl.getScore();
                            System.out.println("the max  score is  " + maxScore + " score is " + pl.getScore());

                            dif =   ((double) Math.sqrt((double) ((b.getX() - (p.getX() + (p.getImageWidth()/2) ) )* ( b.getX() - (p.getX()+ (p.getImageWidth()/2) )) )));//+ (( b.getY() - p.getY() ) * ( b.getY() - p.getY() )) )) ;
                            dif /= 1280 ;
                            dif *= 100 ;
                            dif += slack ;
                            dif -= ((pl.getScore() - preiviesScore)*2000);
                            if(p.getY()<= b.getY()){
                                System.out.println("lossss");
                                dif *= 100;
                            }

                            y.add(-dif);
                            if(-dif>500)
                                System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + framenum);
                            System.out.println("the rewerd is " +  ( -dif ) );
                            preiviesScore = pl.getScore() ;

                            arkHelper.running = true;

                            //System.exit(0);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }
                    framenum ++ ;
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

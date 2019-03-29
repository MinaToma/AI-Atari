package atariCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferStrategy;
import java.security.Key;

public class Game extends Canvas implements Runnable  {

    Thread thread;
    protected Handler handler;
    protected boolean running = false;

    public Game(String title , KeyInput keyInput) {
        handler = new Handler();
        keyInput.setHandler(handler);
        this.addKeyListener(keyInput);

        new Window(Helper.screenWidth , Helper.screenHeight, title, this);
    }

    protected synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;
    }

    protected synchronized void stop() {

        try {
            thread.join();
            running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1e9 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {

                tick();
                delta--;
            }

            if(running)
                render();

            frames++;

            if(System.currentTimeMillis() - timer > 1000) {

                timer += 1000;
                System.out.println("FPS " + frames);
                frames = 0;
            }
        }

        stop();
    }

    protected void tick() {
        handler.tick();
    }

    protected void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {

            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0 , 0 , Helper.screenWidth , Helper.screenHeight);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String []args) {
       // new Game("HI");
    }
}

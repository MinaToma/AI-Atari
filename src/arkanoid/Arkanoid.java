package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Brick;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.AIEngine;
import atariCore.Background;
import atariCore.BaseObject;
import atariCore.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.interfaces.DSAPublicKey;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

public class Arkanoid extends atariCore.Game {

    Ball b;
    Paddle p;
    Player player;

    public Arkanoid(String namePlayer, int level) {

        /*
         *
         * 1 stone-age ok
         * 2 pharaohs ok
         * 3 aztec ok
         * 4 greek ok
         * 5 roman ok
         * 6 indian ok
         * 7 viking ok
         * 8 chinese ok
         * 9 medieval ok
         * 10 arabian
         *
         * */

        super("Arkanoid");

        try {
            Robot r = new Robot();
            r.mouseMove(frame.getX() + INIT_PADDLE_X, screenHeight / 2);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        initKeys();
        arkHelper.setCursorImage(this, pathCursor);
        setPaddle();
        setPlayer(namePlayer , level);

        // /*FOR TESTING - to remove*/ player.setLevel(93);
        initializeLevels(player.getLevel());
    }

    public void initializeLevels(int level) {

        paddleList.clear();
        ballList.clear();
        brickList.clear();
        bulletList.clear();
        capsuleList.clear();
        playerList.clear();
        enemyList.clear();
        backgroundList.clear();
        p.reset();
        p.speedNormal();



        handler.addObject(playerList, player);
        handler.addObject(paddleList, p);
        setBackGround();
        setBricks(level);



        setSounds();
        setBall();
        setEnemy();

        if (AIMode) {
            p.setX(arkHelper.screenHeight * 0.1f);
            paddleClick();
        }

    }

    private void setBackGround() {
        Background background = new Background(0, 0, backgroundImage[(player.getLevel() - 1) / 10]);
        handler.addObject(backgroundList, background);
    }

    public void setBall() {

        b = new Ball(player.paddle.get(0).getX() + player.paddle.get(0).getImageWidth() / 2 - 5, INIT_BALL_Y, arkHelper.ball, 0, 0, player);
        handler.addObject(ballList, b);
    }

    private void setPlayer(String namePlayer , int level) {
        player = new Player(namePlayer, (AIMode) ? 0 : 3, p, this, this);
        player.setLevel(level);
        p.setPlayer(player);
        handler.addObject(playerList, player);
    }

    private void setEnemy() {

    }

    private void setBricks(int lvl) {
        new Level(arkFile.getLevel("Level" + lvl, pathLevel), player, p, b);
    }

    private void setPaddle() {
        p = new Paddle(INIT_PADDLE_X, INIT_PADDLE_Y, arkHelper.paddle[0], 0, 0, player);
        handler.addObject(paddleList, p);
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void setSounds() {

        if (arkHelper.backgroundGameSound[(player.getLevel() - 1) / 10].isStopped()) {
            Sound.Play(backgroundGameSound[(player.getLevel() - 1) / 10], false);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            keys.put(KeyEvent.VK_LEFT, true);
            keys.put(KeyEvent.VK_RIGHT, false);
        } else if (key == KeyEvent.VK_RIGHT) {

            keys.put(KeyEvent.VK_LEFT, false);
            keys.put(KeyEvent.VK_RIGHT, true);
        } else if (key == KeyEvent.VK_SPACE) {

            paddleClick();
        } else if (key == KeyEvent.VK_P) {

            pause = !pause;
        } else if (key == KeyEvent.VK_ESCAPE) {

            AIMode = arkHelper.running = pause = false;
            new arkanoid.menu.Splash();
        } else if (key == KeyEvent.VK_A) {

            keys.put(KeyEvent.VK_D, false);
            keys.put(KeyEvent.VK_A, true);
        } else if (key == KeyEvent.VK_D) {

            keys.put(KeyEvent.VK_A, false);
            keys.put(KeyEvent.VK_D, true);
        } else if (key == KeyEvent.VK_NUMPAD4) {

            keys.put(KeyEvent.VK_NUMPAD6, false);
            keys.put(KeyEvent.VK_NUMPAD4, true);
        } else if (key == KeyEvent.VK_NUMPAD6) {

            keys.put(KeyEvent.VK_NUMPAD6, true);
            keys.put(KeyEvent.VK_NUMPAD4, false);
        }
    }

    @Override
    public void initKeys() {
        keys.put(KeyEvent.VK_NUMPAD4, false);
        keys.put(KeyEvent.VK_NUMPAD6, false);
        keys.put(KeyEvent.VK_A, false);
        keys.put(KeyEvent.VK_D, false);
        keys.put(KeyEvent.VK_LEFT, false);
        keys.put(KeyEvent.VK_RIGHT, false);
    }

    public void pressKey() {
        int paddleIdx = -1;
        float speed = -1f;

        if (keys.containsKey(KeyEvent.VK_LEFT) && keys.get(KeyEvent.VK_LEFT)) {

            paddleIdx = 0;
            speed = -paddleSpeed;
        } else if (keys.containsKey(KeyEvent.VK_RIGHT) && keys.get(KeyEvent.VK_RIGHT)) {

            paddleIdx = 0;
            speed = paddleSpeed;
        } else if (keys.containsKey(KeyEvent.VK_A) && keys.get(KeyEvent.VK_A) && paddleList.size() > 1) {

            paddleIdx = 1;
            speed = -paddleSpeed;
        } else if (keys.containsKey(KeyEvent.VK_D) && keys.get(KeyEvent.VK_D) && paddleList.size() > 1) {

            paddleIdx = 1;
            speed = paddleSpeed;
        } else if (keys.containsKey(KeyEvent.VK_NUMPAD4) && keys.get(KeyEvent.VK_NUMPAD4) && paddleList.size() > 2) {

            paddleIdx = 2;
            speed = -paddleSpeed;
        } else if (keys.containsKey(KeyEvent.VK_NUMPAD6) && keys.get(KeyEvent.VK_NUMPAD6) && paddleList.size() > 2) {

            paddleIdx = 2;
            speed = paddleSpeed;
        }

        if (paddleIdx > -1) {
            paddleList.get(paddleIdx).setVelX(speed);
        } else {
            paddleList.forEach(p -> p.setVelX(0));
        }
    }

    public void paddleClick() {
        if (p.laser) {
            p.hitLaser();
        }
        for (BaseObject o : ballList) {
            if (o.getVelX() == 0 && o.getVelY() == 0) {
                p.sticky = false;
                o.setVelX(-p.getNewVx(o.getX() + o.getImageWidth() / 2));
                o.setVelY(yBallSpeed);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        paddleClick();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        if (b.getX() != INIT_BALL_X && mouseEvent.getX() < arkHelper.screenWidth - p.getImageWidth() + 3)
            p.setX(mouseEvent.getX());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        keys.put(key, false);
    }

    @Override
    protected void sendDataToAI() {

        if (player.getScore() == 0)
            player.setPreviousScore(0);

        arkHelper.running = false;

        Float R = 0f;
        Float L = 0f;

        for (BaseObject o : brickList) {
            if (o.getX() > (p.getX() + p.getImageWidth() / 2f))
                R++;
            else
                L++;
        }

        ArrayList<Float> inputData = new ArrayList<>();
        inputData.add(p.getX());
        inputData.add(b.getX());
        inputData.add(b.getY());
        inputData.add(R);
        inputData.add(L);

        AIEngine.initializeInput(inputData);

        String dir = AIEngine.getDIR();

        if (dir.equals("right")) {

            p.setVelX(arkHelper.paddleSpeed);
        } else if (dir.equals("left")) {

            p.setVelX(-arkHelper.paddleSpeed);
        } else if (dir.equals("same")) {
            p.setVelX(0);
        }

        if (player.getPreviousScore() - player.getScore() == 0)
            AIEngine.slack += 0.01;
        else
            AIEngine.slack = 0;

        arkAIEngine.calculateReward(player, b, p);

        arkHelper.running = true;

    }
}
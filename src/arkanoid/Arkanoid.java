package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;
import static atariCore.Helper.*;

/**
 * Arkanoid main game class.
 */
public class Arkanoid extends atariCore.Game {

    /**
     * Main ball object.
     */
    Ball b;
    /**
     * Main paddle object.
     */
    Paddle p;
    /**
     * Main player object.
     */
    Player player;

    /**
     * Parameterised constructor takes player name and its level to start the game.
     *
     * @param namePlayer Player name.
     * @param level      Level to start the game at.
     */
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
        ObjectList.clear();

        try {
            Robot r = new Robot();
            r.mouseMove(frame.getX() + INIT_PADDLE_X, screenHeight / 2);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        initKeys();
        Helper.setCursorImage(arkHelper.pathCursor);
        setPaddle();
        setPlayer(namePlayer, level);

        initialiseLevels(player.getLevel());
    }

    /**
     * initialised main neede game objects and components.
     *
     * @param level Level to be initiated.
     */
    public void initialiseLevels(int level) {

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

        Handler.getInstance().addObject(playerList, player);
        Handler.getInstance().addObject(paddleList, p);
        setBackGround();
        setBricks(level);

        if (AIMode)
            p.setX(Helper.screenHeight * 0.1f);

        setSounds();
        setBall();

        if (AIMode)
            paddleClick();
    }

    /**
     * Sets main background image.
     */
    private void setBackGround() {
        GameBackground gameBackground = new GameBackground(0, 0, backgroundImage[(player.getLevel() - 1) / 10]);
        Handler.getInstance().addObject(backgroundList, gameBackground);
    }

    /**
     * Sets Main ball object.
     */
    private void setBall() {
        b = new Ball(player.paddle.get(0).getX() + player.paddle.get(0).getImageWidth() / 2 - 5, INIT_BALL_Y,
                arkHelper.ball, 0, 0, player);
        Handler.getInstance().addObject(ballList, b);
    }

    /**
     * @param namePlayer
     * @param level
     */
    private void setPlayer(String namePlayer, int level) {
        player = new Player(namePlayer, (AIMode) ? 0 : 3, p, this);
        player.setLevel(level);
        p.setPlayer(player);
        Handler.getInstance().addObject(playerList, player);
    }

    /**
     * Sets level's bricks.
     *
     * @param lvl Level to be displayed.
     */
    private void setBricks(int lvl) {
        new Level(arkFile.getLevel("Level" + lvl, pathLevel), player, p, b);
    }

    /**
     * Sets main paddle.
     */
    private void setPaddle() {
        p = new Paddle(INIT_PADDLE_X, INIT_PADDLE_Y, arkHelper.paddle[0], 0, 0, player);
        Handler.getInstance().addObject(paddleList, p);
    }

    /**
     * Sets main game sounds.
     */
    public void setSounds() {

        if (arkHelper.backgroundGameSound[(player.getLevel() - 1) / 10].isStopped() && music) {
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
        } else if (key == KeyEvent.VK_SPACE && keyboard) {

            paddleClick();
        } else if (key == KeyEvent.VK_P) {

            boolean canPause = true;
            for (Component c : this.getComponents())
                if (c.equals(lossImage) || c.equals(nextLevelImage))
                    canPause = false;

            if (canPause) {

                pause = !pause;
                setPausedBG();
            }
        } else if (key == KeyEvent.VK_ESCAPE) {

            boolean canEscape = true;
            for (Component c : this.getComponents())
                if (c.equals(lossImage) || c.equals(nextLevelImage))
                    canEscape = false;

            if (!canEscape) return;

            AIMode = Helper.running = pause = false;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void initKeys() {
        keys.put(KeyEvent.VK_NUMPAD4, false);
        keys.put(KeyEvent.VK_NUMPAD6, false);
        keys.put(KeyEvent.VK_A, false);
        keys.put(KeyEvent.VK_D, false);
        keys.put(KeyEvent.VK_LEFT, false);
        keys.put(KeyEvent.VK_RIGHT, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pressKey() {

        if (pause || AIMode || !keyboard) return;

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

        if (paddleIdx > -1 && paddleIdx < paddleList.size()) {
            paddleList.get(paddleIdx).setVelX(speed);
        } else {
            paddleList.forEach(p -> p.setVelX(0));
        }
    }

    /**
     * Performs a ball or bullets launches.
     */
    public void paddleClick() {
        if (p.laser) {
            p.hitLaser();
        }
        for (BaseObject o : ballList) {
            if (o.getVelX() == 0 && o.getVelY() == 0) {
                p.sticky = false;
                p.setBallSpeed(o);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if (pause || AIMode || !mouse) return;
        paddleClick();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        if (pause || AIMode || !mouse) return;
        if ((b.getX() != INIT_BALL_X && mouseEvent.getX() < Helper.screenWidth - p.getImageWidth() + 3))
            p.setX(mouseEvent.getX());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (pause || AIMode || !keyboard) return;
        int key = e.getKeyCode();
        keys.put(key, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sendDataToAI() {

        if (player.getScore() == 0)
            player.setPreviousScore(0);

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


        if (b.getVelY() > 0) inputData.add(1f);
        else inputData.add(0f);
        inputData.add(Float.valueOf( Paddle.capsuleID));
        Paddle.capsuleID = 0;

        arkAIEngine.initialiseInput(inputData);

        String dir = arkAIEngine.getDIR();

        if (dir.equals("right")) {

            p.setVelX(arkHelper.paddleSpeed);
        } else if (dir.equals("left")) {

            p.setVelX(-arkHelper.paddleSpeed);
        } else if (dir.equals("space")) {
            if(p.sticky)
            {
                p.sticky = false;
            }
            if(p.laser)
            {
                p.hitLaser();
            }
        }

        if (player.getPreviousScore() - player.getScore() == 0)
            arkAIEngine.slack += 0.01;
        else
            arkAIEngine.slack = 0;

        arkAIEngine.calculateReward(player, b, p);
    }
}
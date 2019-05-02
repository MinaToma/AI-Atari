package flappyBird;

import atariCore.*;
import flappyBird.board.Bird;
import flappyBird.board.Pipe;
import flappyBird.board.Player;
import flappyBird.menu.Splash;

import java.awt.event.KeyEvent;
import java.util.Random;

import static atariCore.Helper.*;
import static flappyBird.ObjectList.*;
import static flappyBird.FlappyHelper.*;

public class FlappyBird extends Game {

    Player player;
    Bird bird;
    public FlappyAIEngine myGeneration;
    int numberOfBirds = 50;

    public FlappyBird(String title, String playerName) {
        super(title);

        playerList.clear();
        setBackground();
        setPlayer(playerName);
        setPipe();
        initialize();
    }

    public void initialize() {
        birdList.clear();
        pipeList.clear();
        backgroundList.clear();

        if (!AIMode) {
            setBird();
        } else {
            myGeneration = new FlappyAIEngine(numberOfBirds);
        }

        setBackground();
        setPipe();
        running = true;
    }

    private void setBird() {
        bird = new Bird(screenWidth / 2f - birds[0].getWidth(null) / 2f,
                screenHeight / 2f - birds[0].getHeight(null) / 2f, birds[1]);

        Handler.getInstance().addObject(birdList, bird);
    }

    private void setPlayer(String namePlayer) {
        player = new Player(namePlayer, panel, this);
        Handler.getInstance().addObject(playerList, player);
    }

    private void setPipe() {
        Random rand = new Random();
        int initialX = screenWidth + 10;
        int initialY = screenHeight / 5;
        for (int i = 0; i < 6; i++) {
            initialY = rand.nextInt(maxHeight) + minHeight;
            Pipe pipeD = new Pipe(initialX, initialY - pipeDown.getHeight(null), pipeDown, -1.5f, 0);
            Pipe pipeU = new Pipe(initialX, initialY + heightGap, pipeUp, -1.5f, 0);

            Handler.getInstance().addObject(pipeList, pipeD);
            Handler.getInstance().addObject(pipeList, pipeU);

            initialX += widthGap;

        }
    }

    private void setBackground() {
        GameBackground back = new GameBackground(0, 0, backgroundGame);
        Handler.getInstance().addObject(backgroundList, back);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!AIMode) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_P) {

                setPausedBG();
            } else if (key == KeyEvent.VK_ESCAPE) {

                new Splash();
            } else if (key == KeyEvent.VK_SPACE && !startGame)
                startGame = true;
            else if (key == KeyEvent.VK_SPACE) {
                bird.speedUp();
            }
        }
    }

    @Override
    protected void sendDataToAI() {
        myGeneration.getAction();
    }
}


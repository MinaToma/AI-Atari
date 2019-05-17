package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.AIEngine;
import atariCore.FileManager;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Main Arkanoid AI-Mode manager.
 */
public class arkAIEngine {

    /**
     * Array list to hold rewards for every action taken by AI-Player.
     */
    static CopyOnWriteArrayList<Double> rewards = new CopyOnWriteArrayList<>();
    private static ArrayList<String> AIInput;
    /**
     * Needed for reward calculations.
     */
    static double slack = 0;
    /**
     * Path of Arkanoid AI-Engine.
     */
    static String scriptScript = "src/Resources/Arkanoid/AI-Scripts/arkanoid.py";
    /**
     * Path of interaction file used to communicate with python script.
     */
    static String interactionPath = "src/Resources/Arkanoid/AI-Scripts/interactions.txt";

    private static arkAIEngine engine = new arkAIEngine();

    /**
     * Starts AI-Engine.
     */
    public static void startEngine() {
        AIEngine.startEngine(scriptScript);
    }

    private arkAIEngine() {
    }

    /**
     * Calculates reward for every action.
     *
     * @param player Current player object.
     * @param ball   Current ball object.
     * @param paddle Current paddle object.
     */
    static public void calculateReward(Player player, Ball ball, Paddle paddle) {

        double reward;
        reward = -(Math.sqrt(Math.abs((ball.getX() - (paddle.getX() + (paddle.getImageWidth() / 2f))))));
        reward -= slack;
        reward += (player.getScore() - player.getPreviousScore()) * 500;

        slack += 0.01;
        if (paddle.getY() <= ball.getY())
            reward -= 200;

        if ((player.getScore() - player.getPreviousScore()) != 0)
            slack = 0;

        rewards.add(reward);
        System.out.println("the reward is " + (reward));

        player.setPreviousScore(player.getScore());
    }

    /**
     * initialises the input to be sent to AI script.
     *
     * @param input Array list of inputs.
     */
    static void initialiseInput(ArrayList<String> input) {
        AIInput = input;
    }

    /**
     * Sends input to python script and waits for AI response.
     *
     * @return Action to be carried out.
     */
    static String getDIR() {

        String Data = new String();

        AIInput.add(0 , "prediction");
        FileManager.writeFile(interactionPath , AIInput , false);

        while (Data == null || (!Data.equals("space") && !Data.equals("right") && !Data.equals("left")))
            Data = AIEngine.waitForPrediction(interactionPath, Data);

        System.out.println(Data);

        return Data;
    }

    /**
     * Initiates training process for AI-Player.
     */
    static public void train() {

        ArrayList<String> data = new ArrayList<>();
        data.add("training");
        data.add(String.valueOf(rewards.size()));

        for (Double reward : rewards) {
            data.add(String.valueOf(reward));
        }

        FileManager.writeFile(interactionPath , data , false);

        String Data = new String();

        while (Data == null || !Data.equals("done"))
            Data = AIEngine.waitForPrediction(interactionPath, Data);

        rewards.clear();
    }

    /**
     * Returns instance of the class.
     *
     * @return Arkanoid AI-Engine instance.
     */
    static arkAIEngine getInstance() {
        return engine;
    }
}

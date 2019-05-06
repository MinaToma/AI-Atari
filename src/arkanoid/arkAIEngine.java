package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.AIEngine;

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
    private static ArrayList<Float> AIInput;
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
    static void initialiseInput(ArrayList<Float> input) {
        AIInput = input;
    }

    /**
     * Sends input to python script and waits for AI response.
     *
     * @return Action to be carried out.
     */
    static String getDIR() {

        String Data = new String();

        try {
            PrintWriter writer = new PrintWriter(interactionPath, "UTF-8");

            writer.println("prediction");

            for (Float val : AIInput)
                writer.println(val);

            writer.close();

            while (Data == null || (!Data.equals("space") && !Data.equals("right") && !Data.equals("left")))
                Data = AIEngine.waitForPrediction(interactionPath, Data);

            System.out.println(Data);
        } catch (Exception e) {
            System.out.println(e);
        }

        return Data;
    }

    /**
     * Initiates training process for AI-Player.
     */
    static public void train() {
        try {
            PrintWriter writer = new PrintWriter(interactionPath, "UTF-8");
            writer.println("training");
            writer.println(rewards.size());

            for (int i = 0; i < rewards.size(); i++) {
                writer.println(rewards.get(i));
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        String Data = new String();

        while (Data == null || !Data.equals("done"))
            Data = AIEngine.waitForPrediction(interactionPath, Data);

        try {
            PrintWriter writer = new PrintWriter(interactionPath, "UTF-8");
            writer.println("");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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

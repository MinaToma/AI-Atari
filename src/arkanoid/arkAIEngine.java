package arkanoid;

import arkanoid.board.Ball;
import arkanoid.board.Paddle;
import arkanoid.board.Player;
import atariCore.AIEngine;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class arkAIEngine {

    static CopyOnWriteArrayList<Double> rewards = new CopyOnWriteArrayList<>();
    private static ArrayList<Float> AIInput;
    static double slack = 0;
    private static String scriptScript = "src/Resources/Arkanoid/AI-Scripts/arkanoid.py";
    static String interactionPath = "src/Resources/Arkanoid/AI-Scripts/interactions.txt";

    private static arkAIEngine engine = new arkAIEngine();

    public static void startEngine()
    {
        AIEngine.startEngine(scriptScript);
    }

    private arkAIEngine() {
    }

    static public int calculateReward(Player player, Ball ball, Paddle paddle) {

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

        return 0;
    }

    static void initializeInput(ArrayList<Float> input) {
        AIInput = input;
    }

    static String getDIR() {

        String Data = new String();

        try {
            PrintWriter writer = new PrintWriter(interactionPath, "UTF-8");

            writer.println("prediction");

            for (Float val : AIInput)
                writer.println(val);

            writer.close();

            while (Data == null || (!Data.equals("same") && !Data.equals("right") && !Data.equals("left")))
                Data = AIEngine.waitForPrediction(interactionPath, Data);

            System.out.println(Data);
        } catch (Exception e) {
            System.out.println(e);
        }

        return Data;
    }

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

    static arkAIEngine getInstance() {
        return engine;
    }
}

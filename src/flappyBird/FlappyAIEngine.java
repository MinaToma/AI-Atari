package flappyBird;

import atariCore.AIEngine;
import atariCore.FileManager;
import atariCore.Handler;
import flappyBird.board.Bird;

import java.io.PrintWriter;
import java.util.ArrayList;

import static atariCore.AIEngine.waitForPrediction;
import static atariCore.Helper.screenHeight;
import static atariCore.Helper.screenWidth;
import static flappyBird.ObjectList.birdList;
import static flappyBird.FlappyHelper.birds;

/**
 * Main flappy bird AI-Engine.
 */
public class FlappyAIEngine {

    /**
     * Holds all birds.
     */
    private ArrayList<Bird> myBirds;
    /**
     * Number of flying birds.
     */
    private int numberOfBirds;
    /**
     * Frame counter to send data to AI for prediction.
     */
    private int currentFrameCount = 99;
    /**
     * Gap between every frame to send AI Data.
     */
    private int requireActionGap = 1;
    /**
     * Flappy birds script path.
     */
    private static String scriptScript = "src/Resources/Flappy Bird/AI-Scripts/flappy.py";
    /**
     * Path of interaction file used to communicate with python script.
     */
    static String interactionPath = "src/Resources/Flappy Bird/AI-Scripts/interaction.txt";

    /**
     * Starts AI-Engine.
     */
    public static void startEngine() {
        AIEngine.startEngine(scriptScript);
    }

    /**
     * Creates bird generation and loads it to play.
     *
     * @param numberOfBirds Number of birds to create.
     */
    public FlappyAIEngine(int numberOfBirds) {

        this.numberOfBirds = numberOfBirds;
        myBirds = new ArrayList<>();

        for (int i = 0; i < numberOfBirds; i++) {
            myBirds.add(new Bird(screenWidth / 2f - birds[0].getWidth(null) / 2f,
                    screenHeight / 2f - birds[0].getHeight(null) / 2f, birds[1]));
            Handler.getInstance().addObject(birdList, myBirds.get(i));
        }
    }

    /**
     * Generates new bird generation from best birds.
     */
    public void generateNewGeneration() {
        try {
            PrintWriter writer = new PrintWriter(interactionPath, "UTF-8");
            writer.println("training");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        String Data = new String();
        while (Data == null || !Data.equals("done"))
            Data = waitForPrediction(interactionPath, Data);
    }

    /**
     * Sends input to python script, waits for AI response and carries out that response.
     */
    public void getAction() {

        currentFrameCount++;
        currentFrameCount %= requireActionGap;

        if (currentFrameCount == 0) {

            ArrayList<Integer> birdIdx = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();
            data.add("prediction");

            for (int i = 0; i < numberOfBirds; i++) {

                Bird bird = myBirds.get(i);

                if (birdList.contains(bird)) {
                    birdIdx.add(i);
                    //player height
                    data.add(String.valueOf(bird.getY() + bird.getImageWidth() / 2f));
                    //distance from pipe
                    data.add(String.valueOf(bird.getDistFromPipe()));
                    //y coordinate of center of hole
                    data.add(String.valueOf(bird.getCenterOfNextHole()));
                    //bird index
                    data.add(String.valueOf(i));
                    //if the bird passed a pipe
                    data.add(String.valueOf(bird.getScoreDifference()));
                }
            }

            data.add(1, String.valueOf(birdIdx.size()));
            FileManager.writeFile(interactionPath, data, false);

            String Data = null;
            while (Data == null || !Data.equals("allDone"))
                Data = AIEngine.waitForPrediction(interactionPath, Data);

            data = FileManager.readFile(interactionPath);
            for (int i = 0; i < birdIdx.size(); i++) {
                if (data.get(i + 1).equals("jump"))
                    myBirds.get(birdIdx.get(i)).speedUp();
            }
        }
    }
}

package flappyBird;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import static atariCore.BaseObjectList.handler;
import static atariCore.Helper.screenHeight;
import static atariCore.Helper.screenWidth;
import static flappyBird.ObjectList.birdList;
import static flappyBird.flappyHelper.birds;

public class FlappyAIEngine {

    private ArrayList<Bird> myBirds;
    private int numberOfBirds;
    private int currentFrameCount = 99;
    private int requireActionGap = 50;

    public FlappyAIEngine(int numberOfBirds) {

        this.numberOfBirds = numberOfBirds;
        myBirds = new ArrayList<>();

        for (int i = 0; i < numberOfBirds; i++) {
            myBirds.add(new Bird(screenWidth / 2f - birds[0].getWidth(null) / 2f,
                    screenHeight / 2f - birds[0].getHeight(null) / 2f, birds[1]));
            handler.addObject(birdList, myBirds.get(i));
        }
    }

    public void generateNewGeneration() {
        try {
            PrintWriter writer = new PrintWriter("/home/mehisen/PycharmProjects/ML (copy)/interaction.txt", "UTF-8");
            writer.println("training");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        String Data = new String();
        while (Data == null || !Data.equals("done"))
            Data = waitForPrediciton(Data);
    }

    public void getAction() {

        currentFrameCount++;
        currentFrameCount %= requireActionGap;

        if (currentFrameCount == 0) {

            for (int i = 0; i < numberOfBirds; i++) {

                Bird bird = myBirds.get(i);

                if (birdList.contains(bird)) {

                    String Data = new String();
                    try {

                        PrintWriter writer = new PrintWriter("/home/mehisen/PycharmProjects/ML (copy)/interaction.txt", "UTF-8");

                        writer.println("prediction");

                        //player height
                        writer.println(bird.getY() + bird.getImageWidth() / 2f);
                        //distance from pip
                        writer.println(bird.getDistFromPip());
                        //y coordinate of center of hole
                        writer.println(bird.getCenterOfNextHole());
                        //bird index
                        writer.println(i);

                        //if the bird passed a pip
                        writer.println(bird.getScoreDifference());

                        writer.close();

                        while (Data == null || (!Data.equals("jump") && !Data.equals("stay") ))
                            Data = waitForPrediciton(Data);

                        if (Data.equals("jump")) {
                            bird.speedUp();
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    private static String waitForPrediciton(String Data) {
        try {
            FileReader fr = new FileReader("/home/mehisen/PycharmProjects/ML (copy)/interaction.txt");
            BufferedReader br = new BufferedReader(fr);

            Data = br.readLine();

            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Data;
    }
}

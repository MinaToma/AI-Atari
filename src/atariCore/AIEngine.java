package atariCore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class AIEngine {

    static public CopyOnWriteArrayList<Double> rewards = new CopyOnWriteArrayList<>();
    public static int slack = 0;
    private static ArrayList<Float> AIReward;
    private static ArrayList<Float> AIInput;

    public AIEngine() {
    }

    public static void startAI() throws IOException {
        try
        {
            String command = "/home/mina/anaconda3/bin/python3 src/AI-Scripts/__main__.py";
            Process p = Runtime.getRuntime().exec(command);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void initializeReward(ArrayList<Float> action) {
        AIReward = action;
    }

    public static void initializeInput(ArrayList<Float> input) {
        AIInput = input;
    }

    public static void finishAI() {

    }

    public static void pauseAI() {

    }

    static public String getDIR() {

        String Data = new String();

        try {
            PrintWriter writer = new PrintWriter("src/Resources/AI-Interaction/interaction.txt", "UTF-8");

            writer.println("predection");

            System.out.println("inside Dir");

            for(Float val : AIInput)
                writer.println(val);

            writer.close();

            while (Data == null || (Data.equals("same") == false && Data.equals("right") == false && Data.equals("left") == false))
                Data = waitForPrediciton(Data);

        } catch (Exception e) {
            System.out.println(e);
        }

        return Data;
    }

    static public void train() {
        try {
            PrintWriter writer = new PrintWriter("src/Resources/AI-Interaction/interaction.txt", "UTF-8");
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

        while (Data == null || Data.equals("done") == false)
            Data = waitForPrediciton(Data);

        try {
            PrintWriter writer = new PrintWriter("src/Resources/AI-Interaction/interaction.txt", "UTF-8");
            writer.println("");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        rewards.clear();
    }

    private static String waitForPrediciton(String Data)
    {
        try {
            FileReader fr = new FileReader("src/Resources/AI-Interaction/interaction.txt");
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



package atariCore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class AIEngine {

    private static ArrayList<Double> AIReward;
    private static ArrayList<Double> AIInput;

    private AIEngine() {
    }

    public static void startAI() throws IOException {

        String command = "/home/mina/anaconda3/bin/python src/AI-Python-Scripts/venv/java-python-interaction/interaction.py";
        Process p = Runtime.getRuntime().exec(command);
    }

    public static void initializeReward(ArrayList<Double> action) {
        AIReward = action;
    }

    public static void initializeInput(ArrayList<Double> input) {
        AIInput = input;
    }

    public static void finishAI() {

    }

    public static void pauseAI() {

    }

    public String getDIR() {

        String Data = new String();

        try {
            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");

            writer.println("predection");
            for(Double val : AIInput)
                writer.println(val);

            writer.close();

            while (Data == null || (Data.equals("same") == false && Data.equals("right") == false && Data.equals("left") == false))
                Data = waitForPrediciton(Data);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(Data);
        return Data;
    }

    public void train(CopyOnWriteArrayList<Double> y) {
        try {
            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");
            writer.println("training");
            writer.println(y.size());

            for (int i = 0; i < y.size(); i++) {
                writer.println(y.get(i));
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        String Data = new String();

        while (Data == null || Data.equals("done") == false)
            Data = waitForPrediciton(Data);

        try {
            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");
            writer.println("");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String waitForPrediciton(String Data)
    {
        try {
            FileReader fr = new FileReader("src/test/test.txt");
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



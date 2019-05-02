package atariCore;

import java.io.BufferedReader;
import java.io.FileReader;

public interface AIEngine {

    String interpreterPath = "/home/mina/anaconda3/bin/python3";
    String interactionPath = "src/Resources/Atari Core/AI-Interaction/interaction.txt";

    static void startEngine(String scriptPath) {
        try {
            String command = interpreterPath + " " + scriptPath;
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static String waitForPrediction(String Data) {
        try {
            FileReader fr = new FileReader(interactionPath);
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



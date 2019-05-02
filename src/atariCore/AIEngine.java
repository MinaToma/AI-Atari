package atariCore;

import java.io.BufferedReader;
import java.io.FileReader;

public interface AIEngine {

    String interpreterPath = "/home/mina/anaconda3/bin/python3";

    static void startEngine(String scriptPath) {
        Helper.PERIOD = 2;
        /*try {
            String command = interpreterPath + " " + scriptPath;
            Process p = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

    static String waitForPrediction(String interactionPath, String Data) {
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



package atariCore;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Main AI-Mode Manager.
 */
public interface AIEngine {

    /**
     * Path of python interpreter to start python script.
     */
    String interpreterPath = "/home/mina/anaconda3/bin/python3";

    /**
     * Starts AI connection with python script and sets game speed to be faster.
     *
     * @param scriptPath Path of python script.
     */
    static void startEngine(String scriptPath) {
        Helper.PERIOD = 2;
        /*try {
            String command = interpreterPath + " " + scriptPath;
            Process p = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

    /**
     * Waits for python's response.
     *
     * @param interactionPath Interaction file's path.
     * @param Data            String variable to hold python response.
     * @return Python script response.
     */
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



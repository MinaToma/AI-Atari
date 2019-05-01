package atariCore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public interface AIEngine {

    String interpreterPath = "/home/mina/anaconda3/bin/python3";

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



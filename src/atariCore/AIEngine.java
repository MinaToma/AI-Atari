package atariCore;

import java.io.IOException;
import java.util.ArrayList;

public class AIEngine {

    private static ArrayList<String> AIActions;
    private static ArrayList<String> AIInput;

    private AIEngine() {}

    public static void startAI() throws IOException {

        String command = "/home/mina/anaconda3/bin/python src/AI-Python-Scripts/venv/java-python-interaction/interaction.py";
        Process p = Runtime.getRuntime().exec(command);
    }

    public static void initializeAction(ArrayList<String> action) {
        AIActions = action;
    }

    public static void initializeInput(ArrayList<String> input) {
        AIInput = input;
    }

    public static void finishAI() {

    }

    public static void pauseAI() {

    }
}



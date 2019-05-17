package atariCore;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Handles needed file streams.
 */
public interface FileManager {

    /**
     * Separator used to store data in file.
     */
    String fieldSeparator = "@@@";

    /**
     * Reads the file of the given path.
     *
     * @param path The path of required file.
     * @return Array list of strings which represent the content of the file.
     */
    static ArrayList<String> readFile(String path) {

        ArrayList<String> data = new ArrayList<>();
        String line;
        try {

            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                data.add(line);
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("File Not Found.");

        }
        return data;
    }

    /**
     * Writes the given data into the specified file path.
     *
     * @param path   The path of the specified file.
     * @param data   Array list of strings which represent the to be written data.
     * @param append States whether to append to the current content of file or clears it then write.
     */
    static void writeFile(String path, ArrayList<String> data, boolean append) {

        try (FileWriter fw = new FileWriter(path, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String str : data) {
                bw.write(str);
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Can't add data.");
        }
    }

    /**
     * Adds new score to the leaderboards.
     *
     * @param path       The path of leaderboards file.
     * @param playerName Holds player's name.
     * @param Score      Holds player's score.
     * @param lvl        Holds player's scored level.
     */
    static void addNewScoreToLeaderboards(String path, String playerName, int Score, int lvl) {

        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(playerName + fieldSeparator + Score + fieldSeparator + lvl);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Can't add score.");
        }
    }

    /**
     * Gets player's current level. (if needed)
     *
     * @param filePath Path of leaderboards file.
     * @param name     Name of required player used to load current player's profile.
     * @return Player's current level.
     */
    static int getPlayerLevel(String filePath, String name) {
        name = filePath + name + ".txt";

        ArrayList<String> ret = readFile(name);
        if (ret.size() == 0)
            return 0;

        return Integer.parseInt(ret.get(0));
    }

    /**
     * Sends player's score to be recorded in leaderboards file.
     *
     * @param filePath Path of leaderboards file.
     * @param name     Player's name.
     * @param level    Player's level.
     */
    static void sendPlayerScore(String filePath, String name, int level) {

        level--;
        name = filePath + name + ".txt";
        ArrayList<String> ret = readFile(name);

        if (ret.size() != 0)
            level = Math.max(level, Integer.parseInt(ret.get(0)));

        ret.clear();
        ret.add(String.valueOf(level));
        writeFile(name, ret, false);
    }

    /**
     * Loads image from specified path and returns it scaled with specified factor.
     *
     * @param filename    Image path.
     * @param scaleFactor Scale factor.
     * @return Image with specified scale and path.
     */
    public static Image loadImage(String filename, int scaleFactor) {

        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage();

        return (new ImageIcon(image.getScaledInstance(image.getWidth(null) / scaleFactor,
                image.getHeight(null) / scaleFactor, Image.SCALE_SMOOTH))).getImage();
    }
}

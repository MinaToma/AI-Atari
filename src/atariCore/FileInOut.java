package atariCore;

import java.io.*;

public class FileInOut {

    public String getLeaderboardData(String path) throws IOException {
        String Data = new String();
        FileReader in = null;

        try {
            in = new FileReader(path);


            int c;
            while ((c = in.read()) != -1) {
                Data += ((char) c);
            }
        } finally {
            if (in != null) {
                in.close();
            }

        }
        return Data;
    }

    public void addNewScoreToLeadboard(String path, String playerName, String Score) throws IOException {
        FileWriter out = null;

        try {

            out = new FileWriter(path);
            out.write(playerName + "$" + Score + "@");


        } finally {

            if (out != null) {
                out.close();
            }
        }
    }
}

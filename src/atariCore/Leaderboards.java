package atariCore;

import javafx.util.Pair;

import java.io.*;
import java.util.Arrays;

public class Leaderboards {

    public Pair[] record;
    public String data;

    public Leaderboards(String path) throws IOException {

        FileInOut file = new FileInOut();
        data = file.getLeaderboardData(path);
        String[] rec = data.split("@");
        record = new Pair[rec.length];
        for (int i = 0; i < rec.length; i++) {
            String[] split = rec[i].split("$");
            record[i] = new Pair(split[0], Integer.valueOf(split[1]));
        }

        Arrays.sort(record);
    }
}

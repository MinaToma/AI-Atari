package atariCore;

import java.util.Arrays;

public class Leaderboards {

    public Score[] record;
    public String data;

    public Leaderboards(String path) {

        FileInOut file = new FileInOut();
        data = file.getLeaderboardData(path);



        String[] rec = data.split(Helper.recordSeparator);

        record = new Score[rec.length];
        for (int i = 0; i < rec.length; i++) {
            String[] sp = rec[i].split(Helper.fieldSeparator);


            record[i] = new Score(Integer.valueOf(sp[1]),sp[0],Integer.valueOf(sp[2]));
        }

        Arrays.sort(record);

    }
}

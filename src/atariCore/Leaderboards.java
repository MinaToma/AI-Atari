package atariCore;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Leaderboards {

    public Pair[] record;
    public String data;
    public Leaderboards(String path)throws IOException
    {
        FileInOut file = new FileInOut();
        data = file.getLeaderboardData(path);
        String[] rec=data.split("@");
        record = new Pair[rec.length];
        for(int i=0; i<rec.length ; i++ )
        {
            String[] split=rec[i].split("$");
            record[i] =new Pair(split[0], Integer.valueOf(split[1]));
        }
        Arrays.sort(record, new Comparator<Pair>() {
            @Override public int compare(Pair p1, Pair p2)
            {
                return p2.Score - p1.Score;
            }
        });
    }




}
class Pair {
    String Name;
    int Score;

    // Constructor
    public Pair(String Name, int Score)
    {
        this.Name = Name;
        this.Score = Score;
    }


}


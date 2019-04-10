package atariCore;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Leaderboards {

    public Score[] record;


    public Leaderboards(String path) {




            getDataFromFile(path);

            // the sort is decreasing by level and by score
            Arrays.sort(record);



    }

    private void getDataFromFile(String path) {


        ArrayList<String> data = FileInOut.readFile(path);

        record = new Score[data.size()];
        int i = 0;

        for (String str : data) {
            String[] sp = str.split(Helper.fieldSeparator);

            record[i] = new Score(Integer.valueOf(sp[1]),(sp[0]),Integer.valueOf(sp[2]));
            i++;
        }
    }

}

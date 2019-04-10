package atariCore;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Leaderboards {

    public Score[] record;


    public Leaderboards(String path) {

        try
        {
            getDataFromFile(path);
            Arrays.sort(record);
            for(Score s : record)
                System.out.println(s.getScore() + " " + s.getLevel() + " " + s.getName());

        }
        catch (Exception e){
            System.out.println("File Not Found");
        }
    }

    private void getDataFromFile(String path) {

        ArrayList<String> data = Helper.file.readFile(path);
        record = new Score[data.size()];
        int i = 0;

        for (String str : data) {
            String[] sp = str.split(Helper.fieldSeparator);
            record[i].setName(sp[0]);
            record[i].setLevel(Integer.valueOf(sp[2]));
            record[i].setScore(Integer.valueOf(sp[1]));
            i++;
        }
    }

}

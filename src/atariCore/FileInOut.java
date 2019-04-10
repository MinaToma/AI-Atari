package atariCore;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInOut {

    public static ArrayList<String> readFile(String path)
    {

        ArrayList<String> data = new ArrayList<>();
        String line ;
        try{

            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);


            while ( (line =  br.readLine()) != null) {
                data.add(line);
            }
            br.close();
            fr.close();

        }
        catch (Exception e)
        {
            System.out.println("File not Found.");

        }


        return data;



    }

    public static void writeFile(String path , ArrayList<String> data)
    {
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            for(String str : data)
            {
                bw.write(str);
                bw.newLine();
            }

        }

        catch (Exception e)
        {
            System.out.println("Can't add data.");
        }
    }


    public static void addNewScoreToLeadboard(String path, String playerName, int  Score ,int lvl)  {


        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw))
        {

            bw.write(playerName +   Helper.fieldSeparator  + Score +Helper.fieldSeparator + lvl );
            bw.newLine();
        }
        catch (Exception e)
        {
            System.out.println("Can't add score.");
        }
    }
}

package atariCore;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInOut {

    public ArrayList<String> readFile(String path)
    {
        ArrayList<String> data = new ArrayList<>();
        try{
           String line = new String();
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

    public void writeFile(String path , ArrayList<String> data)
    {
        try {

            PrintWriter writer = new PrintWriter(path);
            for(String str : data)
            {
                writer.println(str);
            }

            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't add data.");
        }
    }


    public void addNewScoreToLeadboard(String path, String playerName, int  Score ,int lvl)  {
        try {

            PrintWriter writer = new PrintWriter(path);

            writer.println(playerName +   Helper.fieldSeparator  + Score +Helper.fieldSeparator + lvl );

            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't add score.");
        }
    }
}

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
            FileWriter writer = new FileWriter(path,true);
            BufferedWriter buffer = new BufferedWriter(writer);
            for(String str : data)
            {
                buffer.write(str);
                buffer.newLine();
            }

            buffer.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't add data.");
        }
    }


    public void addNewScoreToLeadboard(String path, String playerName, int  Score ,int lvl)  {
        try {
            FileWriter writer = new FileWriter(path,true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(playerName +   Helper.fieldSeparator  + Score +Helper.fieldSeparator + lvl );
            buffer.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't add score.");
        }
    }



}

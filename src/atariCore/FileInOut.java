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
            //
        }
    }

    public ArrayList<ArrayList<Integer>> getLevel(String level , String path)
    {
        ArrayList<ArrayList<Integer>> dim = new ArrayList<>();


            List<String> records = new ArrayList<String>();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;
                boolean ok = false;
                while ((line = reader.readLine()) != null)
                {
                    //System.out.println(line);
                    if(level.equals(line))
                    {

                        ok = true;
                    }
                    else if(ok && line.equals("end"))
                    {
                        break;
                    }
                    else if(ok)
                    {
                        //System.out.println(1);
                        records.add(line);
                    }

                }
                reader.close();

                for(String str :records)
                {
                    String[] field =str.split(" ");
                    ArrayList<Integer>di = new ArrayList<>();
                    for(int i=0 ; i<field.length ; i++)
                    {
                       // System.out.println(field[i]);
                        di.add(Integer.valueOf(field[i]));
                    }
                    if(field.length>1)
                    {
                        for(int i=field.length-1 ; i>=0 ; i--)
                        {
                            // System.out.println(field[i]);
                            di.add(Integer.valueOf(field[i]));
                        }
                    }
                    dim.add(di);
                }
            }
            catch (Exception e)
            {
                System.err.format("Exception occurred trying to read '%s'.", "src/Resources/Files/levels.txt");
                e.printStackTrace();
                return null;
            }

        return dim;
    }

}

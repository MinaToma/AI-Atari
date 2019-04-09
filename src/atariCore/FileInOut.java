package atariCore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInOut {

    public String getLeaderboardData(String path) {

        String Data = new String();
        try {


            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            int i;
            while ((i = br.read()) != -1) {
               Data += ((char) i);
            }
            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            ///
        }
        return Data;
    }

    public void addNewScoreToLeadboard(String path, String playerName, int  Score ,int lvl)  {
        try {
            FileWriter writer = new FileWriter(path,true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(playerName +   Helper.fieldSpertor  + Score +Helper.fieldSpertor + lvl + Helper.recordSpertor);
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

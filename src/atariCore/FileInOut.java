package atariCore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInOut {

    public String getLeaderboardData(String path) throws IOException {
        String Data = new String();
        FileReader in = null;

        try {
            in = new FileReader(path);


            int c;
            while ((c = in.read()) != -1) {
                Data += ((char) c);
            }
        } finally {
            if (in != null) {
                in.close();
            }

        }
        return Data;
    }

    public void addNewScoreToLeadboard(String path, String playerName, String Score) throws IOException {
        FileWriter out = null;

        try {

            out = new FileWriter(path);
            out.write(playerName + "$" + Score + "@");


        } finally {

            if (out != null) {
                out.close();
            }
        }
    }
    public ArrayList<ArrayList<Integer>> getLevel(String level)
    {
        ArrayList<ArrayList<Integer>> dim = new ArrayList<>();


            List<String> records = new ArrayList<String>();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader("src/Resources/Files/levels.txt"));
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

package arkanoid;

import atariCore.FileInOut;

import java.util.ArrayList;

public class arkFile extends FileInOut {

    public static ArrayList<ArrayList<Integer>> getLevel(String level , String path)
    {
        ArrayList<ArrayList<Integer>> dim = new ArrayList<>();
        ArrayList<String> records = readFile(path);

        boolean checkLevel = false;
        for(String str :records)
        {
            if (checkLevel==false && str.equals(level))
            {
                checkLevel=true;
            }
            else if(checkLevel==true && !str.equals("end")) {

                String[] field = str.split(" ");

                ArrayList<Integer> di = new ArrayList<>();
                for (int i = 0; i < field.length; i++) {

                    di.add(Integer.valueOf(field[i]));
                }
                if (field.length > 1) {
                    for (int i = field.length - 1; i >= 0; i--) {

                        di.add(Integer.valueOf(field[i]));
                    }
                }
                dim.add(di);
            }
            else if(checkLevel == true && str.equals("end"))
            {
                break;
            }
        }
        return dim;
    }
}

package arkanoid;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Connection {


    public String getDIR() {

        String Data = new String();

        try {

            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");

            writer.println("predection");
            writer.close();
            TimeUnit.SECONDS.sleep(1);

            while(Data.equals("right") == false && Data.equals("left") == false ) {
                try {

                    FileReader fr = new FileReader("src/test/test.txt");
                    BufferedReader br = new BufferedReader(fr);

                    Data = br.readLine();

                    br.close();
                    fr.close();
                } catch (Exception e) {
                    ///
                }
            }

        }
        catch (Exception e){
            //hi
        }
        System.out.println(Data);
        return  Data;
    }


    public void train(CopyOnWriteArrayList<Integer> y ){
        try {
            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");

            writer.println("training");
            writer.println(y.size());
            for (int i=0;i<y.size();i++){
                writer.println(y.get(i));
            }
            writer.close();

        }
        catch (Exception e){
            //com
        }
    }
}

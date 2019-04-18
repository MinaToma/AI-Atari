package arkanoid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

public class Connection {


    public String getDIR(float Px, float Bx, float By,float r,float l ) {

        String Data = new String();

        try {

            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");

            writer.println("predection");
            writer.println(String.valueOf(Px));
            writer.println(String.valueOf(Bx));
            writer.println(String.valueOf(By));
            writer.println(String.valueOf(r));
            writer.println(String.valueOf(l));
//            for (int i = 0;i<22;i++){
////                System.out.println(String.valueOf(x[i])  + " " + String.valueOf(y[i]));
//                writer.println(String.valueOf(x[i]));
//                writer.println(String.valueOf(y[i]));
//            }

            writer.close();
            //TimeUnit.SECONDS.sleep(1);

            while(Data == null || (Data.equals("same") == false && Data.equals("right") == false && Data.equals("left") == false )) {
                try {

                    FileReader fr = new FileReader("src/test/test.txt");
                    BufferedReader br = new BufferedReader(fr);

                    Data = br.readLine();
                    //System.out.println(Data);
                    br.close();
                    fr.close();
                } catch (Exception e) {
                    ///
                }
                //System.out.println(Data);
            }

        }
        catch (Exception e){
            //hi
        }
        System.out.println(Data);
        return  Data;
    }


    public void train(CopyOnWriteArrayList<Double> y ){
//        for (int i=y.size()-1;i>0;i--){
//
//                y.set(i-1, y.get(i-1)+(y.get(i)*0.80));// += (y.get(i)*((j-i)/5));
//
//        }
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
        String Data = new String();

        while(Data == null || Data.equals("done") == false ) {
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
        try {
            PrintWriter writer = new PrintWriter("src/test/test.txt", "UTF-8");

            writer.println("");
            writer.close();
        }
        catch (Exception e){
            //
        }



    }
}

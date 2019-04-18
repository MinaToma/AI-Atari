package arkanoid.menu;

import arkanoid.Arkanoid;
import arkanoid.arkHelper;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Splash extends atariCore.Splash {

    public Splash() {
        super("Arkanoid", "src/Resources/Fonts/3270Medium.ttf");
        arkHelper.setCursorImage(panel, "src/Resources/image/yellowc2.png");
       // arkHelper.backgroundSplashSound();
        newGameButton.addActionListener(e -> {

            frame.dispose();
            arkHelper.training = true;
            arkHelper.running = true;

            try {
                new Arkanoid("AI");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //  new NewPlayer();
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException, ScriptException {

        arkHelper.training = true;
        arkHelper.running = true;
        new Arkanoid("AI");
        TimeUnit.SECONDS.sleep(2);
//        String command = "/home/ziad/anaconda3/envs/tensorflow/bin/python /home/ziad/Documents/ReinforcementLearning/__main__.py " ;
//        String sc = "/home/ziad/Documents/ReinforcementLearning/__main__.py";
//        String py = "/home/ziad/anaconda3/envs/tensorflow/bin/python";
          System.out.println("hi");
          String command = "/home/ziad/anaconda3/envs/tensorflow/bin/python /home/ziad/Documents/ReinforcementLearning/__main__.py" ;
//
        Process p =  Runtime.getRuntime().exec(command);
//        StringWriter wr = new StringWriter();
//        ScriptEngineManager mang = new ScriptEngineManager();
//        ScriptContext  con = new SimpleScriptContext();
//
//        con.setWriter(wr);
//
//        ScriptEngine eng = mang.getEngineByName(py);
//        eng.eval(new FileReader(sc),con);
//        System.out.println(wr.toString());
//
//        Process p =  Runtime.getRuntime().exec(command);
//
//        System.out.println("hi");

//        new Arkanoid("AI");
        //new Splash();
    }
}

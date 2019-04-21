package atariCore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static atariCore.Helper.*;

public class Leaderboards extends JPanel {

    private Score[] record;
    protected JButton backButton;
    protected JLabel[] top10;


    public Leaderboards(String path) {

            getDataFromFile(path);

            // the sort is decreasing by level and by score
            Arrays.sort(record);
            setDesign();
    }

    private void getDataFromFile(String path) {


        ArrayList<String> data = FileInOut.readFile(path);

        record = new Score[data.size()];
        int i = 0;

        for (String str : data) {
            String[] sp = str.split(Helper.fieldSeparator);

            record[i] = new Score(Integer.valueOf(sp[1]),(sp[0]),Integer.valueOf(sp[2]));
            i++;
        }
    }

    public void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, btnDim);
    }

    public void setINILabel()
    {

        top10 = new JLabel[10];
        int xOffset = 10, yOffest = 190;
        for(int i=1 ; i<=10 ; i++)
        {
            if(record.length>i)
            {
                String rec ,tmp;
                if(i<10)
                    rec = "0" + i + "   ";
                else
                    rec = (i) + "   ";

                tmp = record[i-1].getName();
                rec += tmp;
                for(int j=tmp.length() ; j<=21 ; j++)
                    rec += ' ';
                tmp = String.valueOf( record[i-1].getScore());

                rec += tmp;
                for(int j=tmp.length() ; j<=9 ; j++)
                    rec += ' ';
                tmp = String.valueOf( record[i-1].getLevel());

                rec += tmp;
                top10[i-1] = new JLabel(rec);
            }
            else{
            String s =(i < 10 ? "0" + i : i) +"  .................... ........  ........";
            top10[i-1] = new JLabel(s);}
            top10[i-1].setFont(Helper.setFont("src/Resources/Fonts/joystix monospace.ttf",35));
            top10[i-1].setForeground(buttonBackgroundColor);
                top10[i-1].setBounds(xOffset,yOffest,Helper.screenWidth-40,35);
                yOffest += 35;

            add(top10[i-1]);
        }

    }


    private void setDesign()
    {
        Helper.frame.getContentPane().remove(Helper.panel);
        Helper.panel = this;
        setLayout(null);
        panel.setSize(Helper.screenWidth, Helper.screenHeight);

        setBackButton(Helper.screenWidth/2-btnDim.width/2, Helper.screenHeight-btnDim.height - 55);
        setINILabel();








        add(backButton);
        Helper.frame.getContentPane().add(this);
        Helper.frame.setVisible(true);


    }

}

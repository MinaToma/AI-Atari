package atariCore;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static atariCore.Helper.*;

/**
 * Holds scores of previous played games.
 */
public class Leaderboards extends JPanel {

    /**
     * Holds records to show on screen.
     */
    private Score[] record;
    /**
     * Back button to ge back to main menu
     */
    protected JButton backButton;
    /**
     * Labels of top 10 scores.
     */
    protected JLabel[] top10;

    /**
     * Parameterised constructor with leaderboards file path.
     *
     * @param path     Path of leaderboards file.
     * @param hasLevel Whether the game has levels or not.
     */
    public Leaderboards(String path, boolean hasLevel) {

        getDataFromFile(path);
        Arrays.sort(record);
        setDesign(hasLevel);
    }

    /**
     * Reads records of previous games from given path.
     *
     * @param path Path to read records from.
     */
    private void getDataFromFile(String path) {

        ArrayList<String> data = FileManager.readFile(path);
        record = new Score[data.size()];
        int i = 0;

        for (String str : data) {
            String[] sp = str.split(FileManager.fieldSeparator);

            record[i] = new Score(Integer.valueOf(sp[1]), (sp[0]), Integer.valueOf(sp[2]));
            i++;
        }
    }

    /**
     * Creates back button and positions it to the given position.
     *
     * @param x x coordinate of the button.
     * @param y y coordinate of the button.
     */
    public void setBackButton(int x, int y) {
        backButton = Helper.buttonHelper("Back", x, y, btnDim);
    }

    /**
     * Initialises leaderboards with top 10 player with or without level.
     *
     * @param hasLevel Indicates if the game has levels.
     */
    public void setINILabel(boolean hasLevel) {
        top10 = new JLabel[10];
        int xOffset = 10, yOffset = 190;
        for (int i = 1; i <= 10; i++) {
            if (record.length >= i) {
                String rec, tmp;

                if (i < 10)
                    rec = "0" + i + "   ";
                else
                    rec = (i) + "   ";

                tmp = record[i - 1].getName();
                rec += tmp;

                for (int j = tmp.length(); j <= 21; j++)
                    rec += ' ';

                if (hasLevel) {
                    tmp = String.valueOf(record[i - 1].getScore());

                    rec += tmp;
                    for (int j = tmp.length(); j <= 9; j++)
                        rec += ' ';
                    tmp = String.valueOf(record[i - 1].getLevel());

                    rec += tmp;
                } else {
                    for (int j = 0; j <= 6; j++)
                        rec += ' ';

                    tmp = String.valueOf(record[i - 1].getScore());

                    rec += tmp;
                }
                top10[i - 1] = new JLabel(rec);
            } else {
                if (hasLevel) {
                    String s = (i < 10 ? "0" + i : i) + "  ....................  ........  ........";
                    top10[i - 1] = new JLabel(s);
                } else {
                    String s = (i < 10 ? "0" + i : i) + "  ........................................";
                    top10[i - 1] = new JLabel(s);
                }
            }
            top10[i - 1].setFont(Helper.setFont("res/Atari Core/Fonts/joystix monospace.ttf", 35));
            top10[i - 1].setForeground(buttonBackgroundColor);
            top10[i - 1].setBounds(xOffset, yOffset, Helper.screenWidth - 40, 35);
            yOffset += 35;

            add(top10[i - 1]);
        }
    }

    /**
     * Initialised frame and panel and sets main design for leaderboards screen.
     */
    private void setDesign(boolean hasLevel) {
        Helper.frame.getContentPane().remove(Helper.panel);
        Helper.panel = this;
        setLayout(null);
        panel.setSize(Helper.screenWidth, Helper.screenHeight);
        setBackButton(Helper.screenWidth / 2 - btnDim.width / 2, Helper.screenHeight - btnDim.height - 55);
        setINILabel(hasLevel);
        add(backButton);
        Helper.frame.getContentPane().add(this);
        Helper.frame.setVisible(true);
    }
}

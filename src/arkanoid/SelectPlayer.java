package arkanoid;

import arkanoid.board.Player;

import javax.swing.*;

import static atariCore.Helper.*;

/*
 * this code hasn't been tested, need an updated code to run -Mr.Complex.
 */

public class SelectPlayer extends JPanel {

    Player players[];
    static int player;

    SelectPlayer(Player players[]) {
        this.players = players;
        frame.setTitle("Select Player");
        frame.getContentPane().remove(panel);

        panel = this;
        panel.setBackground(backgroundColor);
/*

        //display new player button or buttons with other existing players
        for(player = 0; player < players.length; player++)
        {

        }

*/
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

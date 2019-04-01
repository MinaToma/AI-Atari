package arkanoid.capsule;

import arkanoid.board.Paddle;
import javafx.util.Pair;

import java.awt.Graphics;
import java.awt.*;


public class Break extends Capsule {

    public Break(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    @Override
    public void effect(Paddle p) {

        //Move to next level

    }
}

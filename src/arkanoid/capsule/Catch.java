package arkanoid.capsule;

import arkanoid.board.Paddle;
import javafx.util.Pair;

import java.awt.Graphics;
import java.awt.*;

public class Catch extends Capsule {

    public Catch(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    @Override
    public void effect(Paddle p) {

        p.sticky();
    }

}

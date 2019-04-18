package arkanoid.capsule;

import arkanoid.board.Paddle;

import java.awt.*;
import java.io.IOException;

//import javafx.util.Pair;


public class Break extends Capsule {

    public Break(int x, int y, int life , Image image) {
        super(x, y, life , image);
    }

    @Override
    public void effect(Paddle p) throws IOException {

        p.breakToNextLevel();
    }
}

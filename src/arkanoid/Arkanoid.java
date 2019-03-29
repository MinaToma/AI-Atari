package arkanoid;

import arkanoid.board.Paddle;
import arkanoid.board.Player;

public class Arkanoid extends atariCore.Game {

    public Arkanoid() {

        super("Arkanoid");
        handler.addObject(new Paddle(0 , 0 , null , 0 , 0 ));
    }
}



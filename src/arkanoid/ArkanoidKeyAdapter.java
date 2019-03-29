package arkanoid;

import arkanoid.board.Paddle;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.KeyInput;

import java.awt.event.KeyEvent;

public class ArkanoidKeyAdapter extends KeyInput {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        for(BaseObject o : handler.getObject()) {

            if(o instanceof Paddle) {

                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    ((Paddle)o).moveLeft();
                }

                if (key == KeyEvent.VK_RIGHT) {
                    ((Paddle)o).moveRight();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

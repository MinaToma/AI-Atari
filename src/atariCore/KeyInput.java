package atariCore;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

abstract public class KeyInput extends KeyAdapter {

    protected Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);
}

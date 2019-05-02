package atariCore;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;

/**
 * An extended interface to handle keyboard fast input.
 */
public interface AtariKeyListener extends KeyListener {

    /**
     * Holding current pressed keys.
     */
    TreeMap<Integer, Boolean> keys = new TreeMap<>();

    /**
     * initialises the game's expected keys.
     */
    void initKeys();

    /**
     * Iterates over the current keys in keys and checks if its value is true to be pressed.
     */
    void pressKey();

    @Override
    default void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    default void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    default void keyReleased(KeyEvent keyEvent) {
    }
}

package atariCore;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public interface AtariKeyListener extends KeyListener {

    TreeMap<Integer , Boolean> keys = new TreeMap<>();
    
    void initKeys();
    void pressKey();

    @Override
    default void keyTyped(KeyEvent keyEvent) {

    }
}

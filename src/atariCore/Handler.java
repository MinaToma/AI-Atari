package atariCore;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A singleton class which holds game objects, animates and renders them.
 */
public class Handler {

    /**
     * Class instance
     */
    private static final Handler mHandler = new Handler();

    /**
     * Array list to hold the game's base objects.
     * We used CopyOnWriteArrayList to manipulate the objects when running within a thread.
     */
    private CopyOnWriteArrayList<CopyOnWriteArrayList<BaseObject>> object = new CopyOnWriteArrayList<>();

    private Handler() {
    }

    /**
     * Updates game objects every frame.
     */
    protected void tick() {
        for (CopyOnWriteArrayList<BaseObject> list : object)
            for (BaseObject o : list)
                o.tick();
    }

    /**
     * Renders game objects every frame.
     */
    protected void render(Graphics g) {
        for (CopyOnWriteArrayList<BaseObject> list : object)
            for (BaseObject o : list)
                o.render(g);
    }

    /**
     * Adds new object type to the handler.
     * @param list Holds all game objects of specific type.
     */
    public void addHandler(CopyOnWriteArrayList<BaseObject> list) {
        this.object.add(list);
    }

    /**
     * Removes existing object type from current handler.
     * @param list Holds all game objects of specific type.
     */
    public void removeHandler(CopyOnWriteArrayList<BaseObject> list) {

        this.object.remove(list);
    }

    /**
     * Removes object of specific type from its container.
     * @param list Container of the object.
     * @param o Object to be deleted.
     */
    public void removeObject(CopyOnWriteArrayList<BaseObject> list, BaseObject o) {

        list.remove(o);
    }

    /**
     * Adds new object of specific type to its container.
     * @param list Container of the object.
     * @param o  Object to be added.
     */
    public void addObject(CopyOnWriteArrayList<BaseObject> list, BaseObject o) {

        list.add(o);
    }

    /**
     * Returns the instance of the class.
     * @return Handler which holds all game objects.
     */
    public static Handler getInstance() {
        return mHandler;
    }
}

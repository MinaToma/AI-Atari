package flappyBird;


import atariCore.BaseObject;
import atariCore.Handler;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Object list Class which Holds game objects.
 */
public class ObjectList {

    /**
     * Creates list of birds.
     */
    public static CopyOnWriteArrayList<BaseObject> birdList;
    /**
     * Creates list of pipes.
     */
    public static CopyOnWriteArrayList<BaseObject> pipeList;
    /**
     * Creates list of players.
     */
    public static CopyOnWriteArrayList<BaseObject> playerList;
    /**
     * Creates list of background images.
     */
    static CopyOnWriteArrayList<BaseObject> backgroundList;

    private static final ObjectList objectList = new ObjectList();

    private ObjectList() {

        birdList = new CopyOnWriteArrayList<>();
        playerList = new CopyOnWriteArrayList<>();
        pipeList = new CopyOnWriteArrayList<>();
        backgroundList = new CopyOnWriteArrayList<>();

        Handler.getInstance().addHandler(backgroundList);
        Handler.getInstance().addHandler(pipeList);
        Handler.getInstance().addHandler(birdList);
        Handler.getInstance().addHandler(playerList);
    }

    /**
     * Returns instance of the objectList class.
     *
     * @return ObjectList instance.
     */
    static ObjectList getInstance() {
        return objectList;
    }

    public static void clear() {
        birdList.clear();
        playerList.clear();
        pipeList.clear();
        backgroundList.clear();
    }
}

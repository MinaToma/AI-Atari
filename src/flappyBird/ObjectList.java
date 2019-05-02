package flappyBird;


import atariCore.BaseObject;
import atariCore.Handler;

import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectList {

    public static CopyOnWriteArrayList<BaseObject> birdList;
    public static CopyOnWriteArrayList<BaseObject> pipeList;
    public static CopyOnWriteArrayList<BaseObject> playerList;
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

    static ObjectList getInstance() {
        return objectList;
    }
}

package arkanoid;


import atariCore.BaseObject;
import atariCore.Handler;

import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectList {

    public static CopyOnWriteArrayList<BaseObject> brickList;
    public static CopyOnWriteArrayList<BaseObject> paddleList;
    public static CopyOnWriteArrayList<BaseObject> playerList;
    public static CopyOnWriteArrayList<BaseObject> bulletList;
    public static CopyOnWriteArrayList<BaseObject> ballList;
    public static CopyOnWriteArrayList<BaseObject> capsuleList;
    public static CopyOnWriteArrayList<BaseObject> enemyList;
    public static CopyOnWriteArrayList<BaseObject> backgroundList;

    private static final ObjectList objectList = new ObjectList();

    private ObjectList() {

        paddleList = new CopyOnWriteArrayList<>();
        playerList = new CopyOnWriteArrayList<>();
        ballList = new CopyOnWriteArrayList<>();
        bulletList = new CopyOnWriteArrayList<>();
        capsuleList = new CopyOnWriteArrayList<>();
        enemyList = new CopyOnWriteArrayList<>();
        brickList = new CopyOnWriteArrayList<>();
        backgroundList = new CopyOnWriteArrayList<>();

        Handler.getInstance().addHandler(backgroundList);
        Handler.getInstance().addHandler(enemyList);
        Handler.getInstance().addHandler(capsuleList);
        Handler.getInstance().addHandler(bulletList);
        Handler.getInstance().addHandler(paddleList);
        Handler.getInstance().addHandler(playerList);
        Handler.getInstance().addHandler(ballList);
        Handler.getInstance().addHandler(brickList);
    }

    public static ObjectList getInstance() {
        return objectList;
    }
}

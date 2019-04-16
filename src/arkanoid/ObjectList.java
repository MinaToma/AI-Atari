package arkanoid;


import atariCore.BaseObject;
import atariCore.BaseObjectList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectList extends BaseObjectList {

    public static CopyOnWriteArrayList<BaseObject> brickList;
    public static CopyOnWriteArrayList<BaseObject> paddleList;
    public static CopyOnWriteArrayList<BaseObject> playerList;
    public static CopyOnWriteArrayList<BaseObject> bulletList;
    public static CopyOnWriteArrayList<BaseObject> ballList;
    public static CopyOnWriteArrayList<BaseObject> capsuleList;
    public static CopyOnWriteArrayList<BaseObject> enemyList;
    public static CopyOnWriteArrayList<BaseObject> backgroundList;

    public ObjectList() {

        paddleList = new CopyOnWriteArrayList<>();
        playerList = new CopyOnWriteArrayList<>();
        ballList = new CopyOnWriteArrayList<>();
        bulletList = new CopyOnWriteArrayList<>();
        capsuleList = new CopyOnWriteArrayList<>();
        enemyList = new CopyOnWriteArrayList<>();
        brickList = new CopyOnWriteArrayList<>();
        backgroundList = new CopyOnWriteArrayList<>();

        handler.addHandler(backgroundList);
        handler.addHandler(enemyList);
        handler.addHandler(capsuleList);
        handler.addHandler(bulletList);
        handler.addHandler(paddleList);
        handler.addHandler(playerList);
        handler.addHandler(ballList);
        handler.addHandler(brickList);
    }
}

package arkanoid;


import atariCore.BaseObject;
import atariCore.Handler;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Object list Class which generates objects.
 */
public class ObjectList {
    /**
     * Creates list of bricks.
     */
    public static CopyOnWriteArrayList<BaseObject> brickList;
    /**
     * Creates list of paddles.
     */
    public static CopyOnWriteArrayList<BaseObject> paddleList;
    /**
     * Creates list of players.
     */
    public static CopyOnWriteArrayList<BaseObject> playerList;
    /**
     * Creates list of bullets.
     */
    public static CopyOnWriteArrayList<BaseObject> bulletList;
    /**
     * Creates list of balls.
     */
    public static CopyOnWriteArrayList<BaseObject> ballList;
    /**
     * Creates list of capsules.
     */
    public static CopyOnWriteArrayList<BaseObject> capsuleList;
    /**
     * Creates list of enemies.
     */
    public static CopyOnWriteArrayList<BaseObject> enemyList;
    /**
     * Creates list of backgrounds.
     */
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

    /**
     * Returns instance of the objectList class.
     *
     * @return ObjectList instance.
     */
    public static ObjectList getInstance() {
        return objectList;
    }
}
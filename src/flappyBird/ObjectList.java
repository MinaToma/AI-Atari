package flappyBird;


import atariCore.BaseObject;
import atariCore.BaseObjectList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectList extends BaseObjectList {

    public static CopyOnWriteArrayList<BaseObject> birdList;
    public static CopyOnWriteArrayList<BaseObject> pipList;
    public static CopyOnWriteArrayList<BaseObject> playerList;
    public static CopyOnWriteArrayList<BaseObject> backgroundList;



    public ObjectList() {

        birdList = new CopyOnWriteArrayList<>();
        playerList = new CopyOnWriteArrayList<>();
        pipList = new CopyOnWriteArrayList<>();
        backgroundList = new CopyOnWriteArrayList<>();

        handler.addHandler(backgroundList);
        handler.addHandler(pipList);
        handler.addHandler(birdList);
        handler.addHandler(playerList);

    }
}

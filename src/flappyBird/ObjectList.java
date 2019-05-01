package flappyBird;


import atariCore.BaseObject;
import atariCore.BaseObjectList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectList extends BaseObjectList {

    public static CopyOnWriteArrayList<BaseObject> birdList;
    public static CopyOnWriteArrayList<BaseObject> pipeList;
    public static CopyOnWriteArrayList<BaseObject> playerList;
    public static CopyOnWriteArrayList<BaseObject> backgroundList;



    public ObjectList() {

        birdList = new CopyOnWriteArrayList<>();
        playerList = new CopyOnWriteArrayList<>();
        pipeList = new CopyOnWriteArrayList<>();
        backgroundList = new CopyOnWriteArrayList<>();

        handler.addHandler(backgroundList);
        handler.addHandler(pipeList);
        handler.addHandler(birdList);
        handler.addHandler(playerList);

    }
}

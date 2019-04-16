package atariCore;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {

    public CopyOnWriteArrayList<CopyOnWriteArrayList<BaseObject>> object = new CopyOnWriteArrayList<>();

    public void tick() {

       for (CopyOnWriteArrayList<BaseObject> list : object)
            for (BaseObject o : list)
                o.tick();
    }

    public void render(Graphics g) {

        for (CopyOnWriteArrayList<BaseObject> list : object)
            for (BaseObject o : list)
                o.render(g);
    }

    public void addHandler(CopyOnWriteArrayList<BaseObject> list) {
        this.object.add(list);
    }

    public void removeHandler(CopyOnWriteArrayList<BaseObject> list) {

        this.object.remove(list);
    }

    public void removeObject(CopyOnWriteArrayList<BaseObject> list, BaseObject o) {

        list.remove(o);
    }

    public void addObject(CopyOnWriteArrayList<BaseObject> list, BaseObject o) {

        list.add(o);
    }

    public CopyOnWriteArrayList<CopyOnWriteArrayList<BaseObject>> getObject() {
        return object;
    }
}

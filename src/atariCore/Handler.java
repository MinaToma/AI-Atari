package atariCore;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import arkanoid.arkHelper;
import arkanoid.arkHelper.*;

import static arkanoid.arkHelper.bullet;

public class Handler {

    public CopyOnWriteArrayList<BaseObject> object = new CopyOnWriteArrayList<>();

    public void tick() {

        for (BaseObject o : object) {

            o.tick();
        }
    }

    public void render(Graphics g) {


        for (BaseObject o : object) {

            o.render(g);

           // Rectangle r = o.getRectangle();
           // g.fillRect(r.x , r.y , r.width , r.height);
           // g.drawRect(r.x , r.y , r.width , r.height);
        }

    }

    public void addObject(BaseObject object) {
        this.object.add(object);
    }

    public void removeObject(BaseObject object) {

        this.object.remove(object);
    }

    public List<BaseObject> getObject() {
        return object;
    }
}

package atariCore;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Handler {

    public List<BaseObject> object = Collections.synchronizedList(new ArrayList<>());

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

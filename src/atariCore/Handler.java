package atariCore;

import java.awt.*;
import java.util.ArrayList;

public class Handler {

    protected ArrayList<BaseObject> object = new ArrayList<>();

    public void tick() {

        for(BaseObject o : object) {

            o.tick();
        }
    }

    public void render(Graphics g) {

        for(BaseObject o : object) {

            o.render(g);
        }
    }

    public void addObject(BaseObject object) {
        this.object.add(object);
    }

    public void removeObject(BaseObject object) {

        this.object.remove(object);
    }
}

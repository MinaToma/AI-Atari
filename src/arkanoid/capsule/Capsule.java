package arkanoid.capsule;

import arkanoid.board.Paddle;
import atariCore.BaseObject;
import java.awt.*;

import static arkanoid.arkHelper.*;

public abstract class Capsule extends BaseObject {

    Paddle p;
    public int life;
    public boolean active ;
    public Capsule(int x, int y, int life , Image image) {
        super(x, y, image);
        this.life = life;
        active = false;
    }

    public abstract void effect(Paddle p);

    //this name is hysterical...
    public void unEffect(Paddle p)
    {

    }

    @Override
    public void tick() {
        if(active == false)
        y += capsuleSpeed;
        else
            life--;
    }

    @Override
    public void render(Graphics g) {
        if(active == false)
        g.drawImage(super.image, (int)super.x, (int)super.y, null);
    }
}

package arkanoid.capsule;

import arkanoid.board.Paddle;
import atariCore.BaseObject;

import java.awt.*;

import static arkanoid.arkHelper.*;

/**
 * Holds common capsule properties.
 */
public abstract class Capsule extends BaseObject {

    Paddle p;
    public int life;
    public boolean active;

    /**
     * Parameterised constructor used to set x, y coordinates, life and capsule's image.
     *
     * @param x     capsule's x coordinate.
     * @param y     capsule's y coordinate.
     * @param life  capsule's effect life time span.
     * @param image capsule's image.
     */
    public Capsule(int x, int y, int life, Image image) {
        super(x, y, image);
        this.life = life;
        active = false;
    }

    /**
     * Applies given capsule effect to the current paddle.
     * @param p paddle on which the capsule takes effect.
     */
    public abstract void effect(Paddle p);

    /**
     * Removes effect from the paddle.
     * @param p paddle on which the capsule effect stops.
     */
    public void removeEffect(Paddle p) {

    }

    /**
     * updates capsule's position and life each frame.
     */
    @Override
    public void tick() {
        if (active == false)
            y += capsuleSpeed;
        else
            life--;
    }

    /**
     * draws the capsule onto the frame.
     */
    @Override
    public void render(Graphics g) {
        if (active == false)
            g.drawImage(super.image, (int) super.x, (int) super.y, null);
    }
}

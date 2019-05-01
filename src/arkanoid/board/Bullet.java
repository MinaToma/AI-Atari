
package arkanoid.board;

import atariCore.BaseObject;
import atariCore.Handler;

import java.awt.*;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

/**
 * Bullet Class
 */
public class Bullet extends BaseObject {
    /**
     * Parameterized constructor takes  X, Y coordinates and  bullet image.
     *
     * @param x     X coordinates of the bullet.
     * @param y     Y coordinates of the bullet.
     * @param image Bullet image.
     */
    public Bullet(float x, float y, Image image) {
        super(x, y, image);
    }

    /**
     * Updates Bullet position and checks for collision.
     */
    @Override
    public void tick() {

        y -= capsuleSpeed;
        collision();
    }

    /**
     * Responsible for bullet's collision with bricks and enemies.
     */
    private void collision() {

        boolean dead = false;

        for (BaseObject o : brickList) {
            if (o.getRectangle().intersects(getRectangle())) {
                ((Brick) o).hitBrick();
                Handler.getInstance().removeObject(bulletList, this);
                break;
            }
        }

        for (BaseObject o : enemyList) {
            if (o.getRectangle().intersects(getRectangle())) {
                ((Enemy) o).reducePower();
                Handler.getInstance().removeObject(bulletList, this);
                break;
            }

        }
        if (getY() <= 0) {
            dead = true;
        }

        if (dead) {
            Handler.getInstance().removeObject(bulletList, this);
        }

    }

    /**
     * Renders the Bullet on screen.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(super.image, (int) super.x, (int) super.y, null);
    }
}

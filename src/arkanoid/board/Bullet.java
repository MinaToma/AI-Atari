package arkanoid.board;

import arkanoid.ObjectList;
import arkanoid.arkHelper;
import arkanoid.capsule.Capsule;
import atariCore.BaseObject;
import java.awt.*;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

public class Bullet extends BaseObject {

	public Bullet(float x, float y, Image img) {
		super(x, y, img);
	}

	@Override
	public void tick() {

		y -= capsuleSpeed;
		collision();
	}

	private void collision() {

		boolean dead = false;

		for (BaseObject o : brickList) {
			if (o.getRectangle().intersects(getRectangle())) {
				((Brick) o).hitBrick();
				break;
			}
		}

		for (BaseObject o : enemyList) {
			if (o.getRectangle().intersects(getRectangle())) {
				((Enemy)o).reducePower();
			
		
			}
	
		}
		if (getY() <= 0) {
			dead = true;
		}

		if (dead) {
			handler.removeObject(bulletList, this);
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(super.img, (int) super.x, (int) super.y, null);
	}
}

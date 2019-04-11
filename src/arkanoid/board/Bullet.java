package arkanoid.board;

import atariCore.BaseObject;
import java.awt.*;

import static arkanoid.arkHelper.*;
import static arkanoid.ObjectList.*;

public class Bullet extends BaseObject {

	public Bullet(float x, float y, Image image) {
		super(x, y, image);
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
				handler.removeObject(bulletList,this);
				break;
			}
		}

		for (BaseObject o : enemyList) {
			if (o.getRectangle().intersects(getRectangle())) {
				((Enemy)o).reducePower();
				handler.removeObject(bulletList,this);
				break;

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
		g.drawImage(super.image, (int) super.x, (int) super.y, null);
	}
}

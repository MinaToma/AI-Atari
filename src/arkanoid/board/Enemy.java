package arkanoid.board;

import arkanoid.ObjectList;
import atariCore.BaseObject;
import atariCore.Handler;
import atariCore.Helper;

import java.awt.*;

public class Enemy extends BaseObject {

	private int power;
	public Handler handler;

	public Enemy(int xPostion, int yPostion, float velX, float velY, Image image, int power) {
		super(xPostion, yPostion, image);
		this.power = power;  // we can change it
		this.velX = velX;
		this.velY = velY;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(super.img, (int) super.x, (int) super.y, null);
	}

	@Override
	public void tick() {
		super.x += super.velX;
		super.y += super.velY;
		if (super.y <= 0 || super.y >= Helper.screenHeight - super.imageHeight) {
			super.velY *= -1;
		}
		if (super.x <= 0 || super.x >= Helper.screenWidth - super.imageHeight) {
			super.velX *= -1;
		}
		//	clamp();
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void reducePower() {

        --power;
		if (power == 0) {
			((Paddle) (ObjectList.paddleList.get(0))).breakToNextLevel();
		}

	}
}

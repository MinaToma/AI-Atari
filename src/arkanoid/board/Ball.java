package arkanoid.board;

import atariCore.BaseObject;

import java.awt.*;

public class Ball extends BaseObject {

	public Ball(int xPostion, int yPostion, Image image, int xVelocity, int yVelocity) {
		super(xPostion, yPostion, image, xVelocity, yVelocity);
	}

	@Override
	public void tick() {

		y += velY;
		x += velX;

		//if(y>=Hieght-img.hieght||y<=0)velY*=-1;
		//if(x>=WIDTH-img.WIDTH||x<=0)velX*=-1;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x , y, null);
	}
}
package arkanoid.board;

import arkanoid.Common;
import arkanoid.ID;
import atariCore.BaseObject;
import atariCore.Game;
import atariCore.Handler;

import java.awt.*;
import java.util.ArrayList;

public class Ball extends BaseObject {


	Handler handler;
	public Common common=new Common();
	public Ball(int xPostion, int yPostion, Image image, int xVelocity, int yVelocity,Handler handler) {
		super(xPostion, yPostion, image, xVelocity, yVelocity);
		this.handler = handler;
	}

	public void move() {

	}

	@Override
	public void tick() {
		y += velY;
		x += velX;

	if(common.getHEIGTH()-imageHeight<=y || y<=0)
	{
		velY*=-1;
	}
	if(common.getWIDTH()-imageWidth<=x || x<=0)
	{
		velX*=-1;
	}


	collision();

	}
	private void collision()
	{
		int calcScore= 0;
		ArrayList<BaseObject> object = new ArrayList<>();
		for(int i = 0 ; i<handler.object.size() ; i++)
		{
			BaseObject baseObject = handler.object.get(i);
			if(baseObject instanceof Brick || baseObject instanceof Enemy) {
				if (baseObject.getRectangle().intersects(super.getRectangle()))
				{
					calcScore++;
					((Brick)baseObject).hit();

					int pow=((Brick)baseObject).getPower();
					if(pow==0)
					{
						object.add(baseObject);
					}
				}
			}

		}
		for(BaseObject bas:object)
		{
			handler.object.remove(bas);
		}


	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}

}
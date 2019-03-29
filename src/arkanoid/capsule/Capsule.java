package arkanoid.capsule;

import atariCore.BaseObject;
import java.awt.Image;


public abstract class Capsule extends BaseObject {

	
	

	Capsule(int x, int y, Image image) {
		super(x,y,image);

	}

	abstract CAPSULES effect();
}

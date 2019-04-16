package atariCore;

import java.awt.*;

public class Background extends BaseObject {

    private static int cnt = 0;
    private int timer = 0;
    private int length;
    private Image[] images;

    public Background(float x, float y, Image img) {
        super(x, y, img);
        cnt = 0;
        length = 0;
    }

    public Background(float x, float y, Image[] images, int length) {
        super(x, y, images[0]);
        this.length = length;
        this.images = images;
    }

    @Override
    public void tick() {
        if (length > 0) {
            timer++;
            if (timer % 10 == 0) {
                this.setImage(images[cnt++]);
                cnt %= 8;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}

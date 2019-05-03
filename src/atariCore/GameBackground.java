package atariCore;

import java.awt.*;

/**
 * Sets background images in gameplay.
 */
public class GameBackground extends BaseObject {

    /**
     * counter to update GIF frames.
     */
    private static int count = 0;
    /**
     * limits frame updates to a specific time.
     */
    private int timer = 0;
    /**
     * number of GIF frames.
     */
    private int length;
    /**
     * background images array.
     */
    private Image[] images;

    /**
     * Parameterised constructor used to set a single image background.
     * @param x image x-coordinate.
     * @param y image y-coordinate.
     * @param img image to bet set as background.
     */
    public GameBackground(float x, float y, Image img) {
        super(x, y, img);
        count = 0;
        length = 0;
    }

    /**
     * Parameterised constructor used to set a multiple image background.
     * @param x image x-coordinate.
     * @param y image y-coordinate.
     * @param images images  array (GIF frames).
     * @param length number of frames.
     */
    public GameBackground(float x, float y, Image[] images, int length) {
        super(x, y, images[0]);
        this.length = length;
        this.images = images;
    }

    /**
     * Updates game background every frame.
     */
    @Override
    protected void tick() {
        if (length > 0) {
            timer++;
            if (timer % 10 == 0) {
                this.setImage(images[count++]);
                count %= length;
            }
        }
    }

    /**
     * Draws background image every frame.
     * @param g Graphics object inside a runnable thread.
     */
    @Override
    protected void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}

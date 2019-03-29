package arkanoid;

public  class Common {
    public static final int WIDTH = 640;
    public static final int HEIGTH = WIDTH / 12 * 9;
    public static final int BOTTOM_EDGE = 390;
    public static final int N_OF_BRICKS = 30;
    public static final int INIT_PADDLE_X = 200;
    public static final int INIT_PADDLE_Y = 360;
    public static final int INIT_BALL_X = 230;
    public static final int INIT_BALL_Y = 355;
    public static final int DELAY = 1000;
    public static final int PERIOD = 10;


    public  int getWIDTH() {
        return WIDTH;
    }

    public  int getHEIGTH() {
        return HEIGTH;
    }

    public  int getBottomEdge() {
        return BOTTOM_EDGE;
    }

    public  int getnOfBricks() {
        return N_OF_BRICKS;
    }

    public  int getInitPaddleX() {
        return INIT_PADDLE_X;
    }

    public  int getInitPaddleY() {
        return INIT_PADDLE_Y;
    }

    public  int getInitBallX() {
        return INIT_BALL_X;
    }

    public  int getInitBallY() {
        return INIT_BALL_Y;
    }
}

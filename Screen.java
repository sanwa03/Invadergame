//Screenクラス

class Screen {
    public static final int WIDTH = 1200;    //幅
    public static final int HEIGHT = 750;    //高
    public static final int RIGHT = 1200;    //右
    public static final int LEFT = 0;    //左
    public static final int TOP = 50;    //天井
    public static final int BOTTOM = 870;    //地面

    public static final int HOUDAI_W = 45;                    //砲台幅
    public static final int HOUDAI_H = 30;                    //砲台高さ
    public static final int HOUDAI_Y = BOTTOM;    //砲台Y座標

    public static final int DIR_R = 0;    //右方向
    public static final int DIR_L = 1;    //左方向

    public static final int UFO_Y = 50;
    public static final int UFO_W = 100;
    public static final int UFO_H = 50;

    public static final int INV_W = 75;
    public static final int INV_H = 50;

    public static final int IP_Y = 100;
    public static final int IP_W = 825;
    public static final int IP_H = 250;

    //壁
    public static final int WAL_H = 20;
    public static final int WAL_W = 20;

    public static final int WP_Y = 550;
    public static final int WP_W = 1500;
    public static final int WP_H = 240;

    //スコア
    public static final int SCORE_INVADER1 = 10;
    public static final int SCORE_INVADER2 = 20;
    public static final int SCORE_INVADER3 = 30;
    public static final int SCORE_UFO = 500;


    public static int A = 0;

    static boolean hitCheck(int x, int y, int sx, int sy, int w, int h) {
        if (sx < x && sx + w > x && sy < y && y < sy + h) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hitCheck2(int x, int y, int sx, int sy, int w, int h) {
        if (sx >= x-2 && sx - w < x && sy < y && y < sy + h) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hitCheck3(int x, int y, int sx, int sy, int w, int h) {
        if (sx >= x && sx - w <= x && sy < y && y < sy + h) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hitCheck4(int x, int y, int sx, int sy, int w, int h) {
        if (sx < x && sx + w > x && y > sy - h && y <= sy) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hitCheck5(int y) {
        if (y >= WP_Y) {
            return true;
        } else {
            return false;
        }
    }

}

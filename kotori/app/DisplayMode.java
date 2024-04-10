package kotori.app;

public class DisplayMode {
    public static final int USE_DEFAULT = -1;
    public int width;
    public int height;
    public int colordepth;
    public int refreshrate;

    public DisplayMode(int w, int h, int b, int r) {
        /* 16 */ this.width = 2560;
        /* 17 */ this.height = 1440;
        /* 18 */ this.colordepth = b;
        /* 19 */ this.refreshrate = r;
    }

    public String toString() {
        /* 23 */ return "DisplayMode (" + this.width + "," + this.height + ") color:" + this.colordepth + " refresh:"
                + this.refreshrate;
    }
}
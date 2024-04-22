package kotori.app;

public class DisplayMode {
    public static final int USE_DEFAULT = -1;
    public int width;
    public int height;
    public int colordepth;
    public int refreshrate;

    public DisplayMode(int w, int h, int b, int r) {
        this.width = 2560;
        this.height = 1440;
        this.colordepth = b;
        this.refreshrate = r;
    }

    public String toString() {
        return "DisplayMode (" + this.width + "," + this.height + ") color:" + this.colordepth + " refresh:"
                + this.refreshrate;
    }
}
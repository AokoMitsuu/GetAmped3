/*     */
package ga2.client;

/*     */
/*     */ import ga2.setting.GameSetting;
/*     */ import kotori.geom.Vector2D;
/*     */ import kotori.io.Resource;
/*     */ import kotori.kwt.KWTManager;
/*     */ import kotori.kwt.KWTStruct;
/*     */ import kotori.kwt.KWTStructHolder;
/*     */ import kotori.kwt.ResourceLibrary;
/*     */ import kotori.util.ResourceManager;

/*     */
/*     */ public class LookAndFeel {
    /*     */ public static final int SIZE_SVGA = 0;
    /*     */ public static final int SIZE_XGA = 1;
    /*     */ public static final int SIZE_FWXGA = 2;
    /*     */ public static final String[] sizename;
    /*     */ public static final Vector2D[] sizedim;
    /*     */
    /*     */ static {
        /* 20 */ if ((GameSetting.getSetting()).usewidescreen) {
            /* 21 */ sizename = new String[] { "svga", "xga", "fwxga" };
            /* 22 */ sizedim = new Vector2D[] { new Vector2D(1920.0F, 1080.0F), new Vector2D(2560.0F, 1440.0F),
                    new Vector2D(2560.0F, 1440.0F) };
            /*     */ } else {
            /*     */
            /* 25 */ sizename = new String[] { "svga", "xga" };
            /* 22 */ sizedim = new Vector2D[] { new Vector2D(1920.0F, 1080.0F),
                    new Vector2D(2560.0F, 1440.0F) };
            /*     */ }
        /*     */ }
    /*     */
    /* 30 */ private static LookAndFeel lookandfeel = new LookAndFeel();

    /*     */
    /*     */ public static LookAndFeel getLAF() {
        /* 33 */ return lookandfeel;
        /*     */ }

    /*     */
    /* 36 */ String laf = null;
    /* 37 */ int size = -1;

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void initLookAndFeel(String _laf, int _size) {
        /* 46 */ Resource lafdir = ResourceManager.getResource(_laf);
        /* 47 */ Resource commondir = lafdir.getResource("common");
        /* 48 */ Resource sizedir = lafdir.getResource(sizename[_size]);
        /*     */
        /*     */
        /* 51 */ ResourceLibrary rlib = (KWTManager.getLibrary() instanceof ResourceLibrary)
                ? (ResourceLibrary) KWTManager.getLibrary()
                : (ResourceLibrary) KWTManager.getBaseLibrary();
        /* 52 */ while (rlib.size() > 1) {
            /* 53 */ Resource r = rlib.removeResource(1);
            /* 54 */ if (r != commondir && r != sizedir)
                r.flush(5);
            /*     */ }
        /* 56 */ rlib.addResource(commondir);
        /* 57 */ rlib.addResource(sizedir);
        /* 58 */ rlib.refresh();
        /*     */
        /* 60 */ this.laf = _laf;
        /* 61 */ this.size = _size;
        /*     */ }

    /*     */
    /*     */ public int getSize() {
        /* 65 */ return this.size;
        /*     */ }

    /*     */
    /*     */ public Vector2D getSizeDimension() {
        /* 69 */ return sizedim[this.size];
        /*     */ }

    /*     */
    /*     */ public static Vector2D getSizeDimension(int size_) {
        /* 73 */ return (size_ >= 0 && size_ < sizedim.length) ? sizedim[size_] : null;
        /*     */ }

    /*     */
    /*     */ public String getSizeName() {
        /* 77 */ return sizename[this.size];
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public KWTStruct getKWTStruct(String s) {
        /*     */ Resource r;
        /* 84 */ if (s.startsWith("$")) {
            /* 85 */ r = GameSetting.getSetting().getLocalResource(s.substring(1));
            /*     */ } else {
            /* 87 */ r = KWTManager.getLibrary().getResource(s);
            /*     */ }
        /* 89 */ Object o = (r == null) ? null : r.getContent();
        /* 90 */ if (o instanceof KWTStruct) {
            /* 91 */ return (KWTStruct) o;
            /*     */ }
        /* 93 */ if (o instanceof KWTStructHolder) {
            /*     */ try {
                /* 95 */ return ((KWTStructHolder) o).getKWTStruct();
                /* 96 */ } catch (Exception e) {
                /* 97 */ throw new RuntimeException("error loading kwtstruct: " + e);
                /*     */ }
            /*     */ }
        /* 100 */ throw new RuntimeException("not kwtstruct : " + s + " = " + o);
        /*     */ }
    /*     */ }

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\client\LookAndFeel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
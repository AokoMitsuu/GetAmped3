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
        if ((GameSetting.getSetting()).usewidescreen) {
            sizename = new String[] { "svga", "xga", "fwxga" };
            sizedim = new Vector2D[] { new Vector2D(1920.0F, 1080.0F), new Vector2D(2560.0F, 1440.0F),
                    new Vector2D(2560.0F, 1440.0F) };
            /*     */ } else {
            /*     */
            sizename = new String[] { "svga", "xga" };
            sizedim = new Vector2D[] { new Vector2D(1920.0F, 1080.0F),
                    new Vector2D(2560.0F, 1440.0F) };
            /*     */ }
        /*     */ }
    /*     */
    private static LookAndFeel lookandfeel = new LookAndFeel();

    /*     */
    /*     */ public static LookAndFeel getLAF() {
        return lookandfeel;
        /*     */ }

    /*     */
    String laf = null;
    int size = -1;

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void initLookAndFeel(String _laf, int _size) {
        Resource lafdir = ResourceManager.getResource(_laf);
        Resource commondir = lafdir.getResource("common");
        Resource sizedir = lafdir.getResource(sizename[_size]);
        /*     */
        /*     */
        ResourceLibrary rlib = (KWTManager.getLibrary() instanceof ResourceLibrary)
                ? (ResourceLibrary) KWTManager.getLibrary()
                : (ResourceLibrary) KWTManager.getBaseLibrary();
        while (rlib.size() > 1) {
            Resource r = rlib.removeResource(1);
            if (r != commondir && r != sizedir)
                r.flush(5);
            /*     */ }
        rlib.addResource(commondir);
        rlib.addResource(sizedir);
        rlib.refresh();
        /*     */
        this.laf = _laf;
        this.size = _size;
        /*     */ }

    /*     */
    /*     */ public int getSize() {
        return this.size;
        /*     */ }

    /*     */
    /*     */ public Vector2D getSizeDimension() {
        return sizedim[this.size];
        /*     */ }

    /*     */
    /*     */ public static Vector2D getSizeDimension(int size_) {
        return (size_ >= 0 && size_ < sizedim.length) ? sizedim[size_] : null;
        /*     */ }

    /*     */
    /*     */ public String getSizeName() {
        return sizename[this.size];
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public KWTStruct getKWTStruct(String s) {
        /*     */ Resource r;
        if (s.startsWith("$")) {
            r = GameSetting.getSetting().getLocalResource(s.substring(1));
            /*     */ } else {
            r = KWTManager.getLibrary().getResource(s);
            /*     */ }
        Object o = (r == null) ? null : r.getContent();
        if (o instanceof KWTStruct) {
            return (KWTStruct) o;
            /*     */ }
        if (o instanceof KWTStructHolder) {
            /*     */ try {
                return ((KWTStructHolder) o).getKWTStruct();
            } catch (Exception e) {
                throw new RuntimeException("error loading kwtstruct: " + e);
                /*     */ }
            /*     */ }
        throw new RuntimeException("not kwtstruct : " + s + " = " + o);
        /*     */ }
    /*     */ }

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\client\LookAndFeel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
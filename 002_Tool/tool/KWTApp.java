package tool;

import ga2.client.GetAmped2;
import ga2.laf.amped2.AmpedHelpFactory;
import ga2.laf.amped2.ShutterManager;
import java.io.File;
import kotori.app.Console;
import kotori.app.DisplayMode;
import kotori.data.ScriptModelData;
import kotori.gfx.GLGraphics;
import kotori.gfx.GLRegistry;
import kotori.gfx.PixelFormat;
import kotori.io.KxrFile;
import kotori.io.Resource;
import kotori.kwt.Container;
import kotori.kwt.HelpFactory;
import kotori.kwt.KWTManager;
import kotori.kwt.ResourceLibrary;
import kotori.util.PropertyManager;
import kotori.util.ResourceManager;

public abstract class KWTApp
        extends Container {
    protected KWTManager kwtmanager;
    Container root;
    protected boolean fullscreen;
    protected boolean kxrres;
    protected boolean kxrgs;
    public static Resource res;

    public void initGraphics(GLGraphics g) {
        g.kglClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        g.kglClear(16384);

        GLRegistry.registerAll(g);
    }

    public void init() {
    }

    public void appmain(Console c, String[] args) {
        if (arguments(args)) {
            c.scanControllers();

            if (this.fullscreen) {
                c.createDisplay(new DisplayMode(getWidth(), getHeight(), -1, -1));
            } else {
                c.createWindow(getTitle(), 1920, 1080, 1);
            }
            c.setCursorVisible(false);
            PixelFormat pf = getDefaultPixelFormat(c);
            System.out.println(pf.toString());
            c.createGraphics(pf);

            if (this.kxrres) {
                File dir = new File("./bin");
                String[] names = dir.list();

                KxrFile kxr = null;
                for (int l = 0; l < names.length; l++) {
                    if (names[l].endsWith("kxr")) {
                        String n = names[l];
                        try {
                            System.out.println("package version check: " + n);
                            kxr = KxrFile.open("bin/" + n, "", "r");
                            KxrFile.Entry e = kxr.getRoot().getEntry("#version." + n);
                            if (e != null) {
                                System.out.printf("version check failed.\n", new Object[0]);
                                System.exit(0);
                            } else
                                System.out.println("package version check: " + n);
                            System.out.println("version check ok.\n");
                        } catch (Exception e) {
                            System.out.printf("version check failed.\n" + e, new Object[0]);
                            System.exit(1);
                        } finally {
                            if (kxr != null)
                                try {
                                    kxr.close();
                                } catch (Exception exception) {
                                }
                        }
                    }
                }
            }
            initKWT(this.kxrres, this.kxrgs);

            this.kwtmanager = new KWTManager();
            KWTManager.addHelpFactory((HelpFactory) new AmpedHelpFactory());
            ShutterManager.getManager().setKWTManager(this.kwtmanager);
            init();

            c.disableEvents(
                    new int[] { 503, 501,
                            502, 504 });

            initControllers(c);

            c.makeCurrent();

            this.kwtmanager.setCanvasSize(getWidth(), getHeight());
            this.root = createRoot();
            if (this.root != null) {
                this.kwtmanager.setRootContainer(this.root);
                startProc();
                while (true) {
                    this.kwtmanager.process();
                    if (c.isForeProcess()) {
                        c.yield();
                        continue;
                    }
                    try {
                        Thread.sleep(10L);
                    } catch (Exception exception) {
                    }
                }
            }
        }
    }

    public PixelFormat getDefaultPixelFormat(Console c) {
        PixelFormat[] pixelFormats = c.getPixelFormatList();
        if (pixelFormats.length == 0)
            return null;
        int pix = (c.getDisplayMode()).colordepth;
        int index = 0;
        int maxPoint = 0;
        for (int i = 0; i < pixelFormats.length; i++) {
            int point = 0;
            if (pixelFormats[i].getDepthBits() > 0) {
                point = 100;
                if (pixelFormats[i].getPixelBits() == pix) {
                    point += 80;
                }
                if (pixelFormats[i].getStencilBits() < 8) {
                    point -= 30;
                }
                if (pixelFormats[i].getAlphaBits() > 0) {
                    point -= 20;
                }
            }
            if (point > maxPoint) {
                maxPoint = point;
                index = i;
            }
        }
        return pixelFormats[index];
    }

    static void initKWT(boolean kxrres, boolean kxrgs) {
        try {
            GetAmped2.initKWT(kxrres, kxrgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GetAmped2.initClientSetting();
        GetAmped2.initLAF();
        try {
            res = ResourceManager.getRoot();
            Resource r = res.getResource("kwttool");
            if (r != null) {
                r.setUserData(98, "");
                ((ResourceLibrary) KWTManager.getLibrary()).addResource(r);
                ((ResourceLibrary) KWTManager.getLibrary()).refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
    }

    protected boolean arguments(String[] args) {
        for (int l = 0; l < args.length; l++) {
            if (args[l].equals("-kxrres")) {
                this.kxrres = true;
            } else if (args[l].equals("-kxrgs")) {
                this.kxrgs = true;
            } else if (args[l].equals("-under12")) {
                ScriptModelData.changemodel = true;
            }
        }
        return true;
    }

    protected String getTitle() {
        return "KWT Application";
    }

    protected int getWidth() {
        return 640;
    }

    protected int getHeight() {
        return 480;
    }

    protected boolean isResizable() {
        return false;
    }

    protected abstract Container createRoot();

    protected void startProc() {
    }

    public void activated() {
    }

    public void deactivated() {
        saveProperties();
    }

    public void quitRequested() {
        saveProperties();
    }

    public void saveProperties() {
        PropertyManager pm = PropertyManager.getManager();
        pm.save();
        ToolProperty.save();
    }

    public static void initControllers(Console c) {
    }

    public static void main(String[] args) throws Exception {
        if (!args[0].equals("-developer")) {
            System.out.println("KWTApp Error!!!");
            return;
        }
        @SuppressWarnings("deprecation")
        KWTApp app = (KWTApp) Class.forName(args[1].replace('/', '.')).newInstance();
        String[] nargs = new String[args.length - 2];
        System.arraycopy(args, 2, nargs, 0, nargs.length);

        app.appmain(Console.getConsole(), nargs);
    }
}
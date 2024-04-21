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
        /* 35 */ g.kglClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        /* 36 */ g.kglClear(16384);

        /* 39 */ GLRegistry.registerAll(g);
    }

    public void init() {
    }

    public void appmain(Console c, String[] args) {
        /* 47 */ if (arguments(args)) {
            /* 48 */ c.scanControllers();

            /* 50 */ if (this.fullscreen) {
                /* 51 */ c.createDisplay(new DisplayMode(getWidth(), getHeight(), -1, -1));
            } else {
                /* 53 */ c.createWindow(getTitle(), getWidth(), getHeight(), isResizable() ? 1 : 0);
            }
            /* 55 */ c.setCursorVisible(false);
            /* 56 */ PixelFormat pf = getDefaultPixelFormat(c);
            /* 57 */ System.out.println(pf.toString());
            /* 58 */ c.createGraphics(pf);

            /* 60 */ if (this.kxrres) {
                /* 61 */ File dir = new File("./bin");
                /* 62 */ String[] names = dir.list();

                /* 65 */ KxrFile kxr = null;
                /* 66 */ for (int l = 0; l < names.length; l++) {
                    /* 67 */ if (names[l].endsWith("kxr")) {
                        /* 68 */ String n = names[l];
                        try {
                            /* 70 */ System.out.println("package version check: " + n);
                            /* 71 */ kxr = KxrFile.open("bin/" + n, "", "r");
                            /* 72 */ KxrFile.Entry e = kxr.getRoot().getEntry("#version." + n);
                            /* 73 */ if (e != null) {
                            } else
                                System.out.println("package version check: " + n);
                            /* 77 */ System.out.println("version check ok.\n");
                            /* 78 */ } catch (Exception e) {
                            /* 79 */ System.out.printf("version check failed.\n" + e, new Object[0]);
                            /* 80 */ System.exit(1);
                        } finally {
                            /* 82 */ if (kxr != null)
                                /* 83 */ try {
                                    kxr.close();
                                } catch (Exception exception) {
                                }
                        }
                    }
                }
            }
            /* 88 */ initKWT(this.kxrres, this.kxrgs);

            /* 90 */ this.kwtmanager = new KWTManager();
            /* 91 */ KWTManager.addHelpFactory((HelpFactory) new AmpedHelpFactory());
            /* 92 */ ShutterManager.getManager().setKWTManager(this.kwtmanager);
            /* 93 */ init();

            /* 95 */ c.disableEvents(
                    /* 96 */ new int[] { 503, 501,
                            /* 97 */ 502, 504 });

            /* 101 */ initControllers(c);

            /* 103 */ c.makeCurrent();

            /* 105 */ this.kwtmanager.setCanvasSize(getWidth(), getHeight());
            /* 106 */ this.root = createRoot();
            /* 107 */ if (this.root != null) {
                /* 108 */ this.kwtmanager.setRootContainer(this.root);
                /* 109 */ startProc();
                while (true) {
                    /* 111 */ this.kwtmanager.process();
                    /* 112 */ if (c.isForeProcess()) {
                        /* 113 */ c.yield();
                        continue;
                    }
                    try {
                        /* 116 */ Thread.sleep(10L);
                        /* 117 */ } catch (Exception exception) {
                    }
                }
            }
        }
    }

    public PixelFormat getDefaultPixelFormat(Console c) {
        /* 125 */ PixelFormat[] pixelFormats = c.getPixelFormatList();
        /* 126 */ if (pixelFormats.length == 0)
            return null;
        /* 127 */ int pix = (c.getDisplayMode()).colordepth;
        /* 128 */ int index = 0;
        /* 129 */ int maxPoint = 0;
        /* 130 */ for (int i = 0; i < pixelFormats.length; i++) {
            /* 131 */ int point = 0;
            /* 132 */ if (pixelFormats[i].getDepthBits() > 0) {
                /* 133 */ point = 100;
                /* 134 */ if (pixelFormats[i].getPixelBits() == pix) {
                    /* 135 */ point += 80;
                }
                /* 137 */ if (pixelFormats[i].getStencilBits() < 8) {
                    /* 138 */ point -= 30;
                }
                /* 140 */ if (pixelFormats[i].getAlphaBits() > 0) {
                    /* 141 */ point -= 20;
                }
            }
            /* 144 */ if (point > maxPoint) {
                /* 145 */ maxPoint = point;
                /* 146 */ index = i;
            }
        }
        /* 149 */ return pixelFormats[index];
    }

    static void initKWT(boolean kxrres, boolean kxrgs) {
        try {
            /* 154 */ GetAmped2.initKWT(kxrres, kxrgs);
            /* 155 */ } catch (Exception e) {
            /* 156 */ e.printStackTrace();
        }
        /* 158 */ GetAmped2.initClientSetting();
        /* 159 */ GetAmped2.initLAF();
        try {
            /* 161 */ res = ResourceManager.getRoot();
            /* 162 */ Resource r = res.getResource("kwttool");
            /* 163 */ if (r != null) {
                /* 164 */ r.setUserData(98, "");
                /* 165 */ ((ResourceLibrary) KWTManager.getLibrary()).addResource(r);
                /* 166 */ ((ResourceLibrary) KWTManager.getLibrary()).refresh();
            }
            /* 168 */ } catch (Exception e) {
            /* 169 */ e.printStackTrace();
        }
    }

    public void update() {
    }

    protected boolean arguments(String[] args) {
        /* 178 */ for (int l = 0; l < args.length; l++) {
            /* 179 */ if (args[l].equals("-kxrres")) {
                /* 180 */ this.kxrres = true;
            }
            /* 182 */ else if (args[l].equals("-kxrgs")) {
                /* 183 */ this.kxrgs = true;
            }
            /* 185 */ else if (args[l].equals("-under12")) {
                /* 186 */ ScriptModelData.changemodel = true;
            }
        }
        /* 189 */ return true;
    }

    protected String getTitle() {
        /* 193 */ return "KWT Application";
    }

    protected int getWidth() {
        /* 197 */ return 640;
    }

    protected int getHeight() {
        /* 201 */ return 480;
    }

    protected boolean isResizable() {
        /* 205 */ return false;
    }

    protected abstract Container createRoot();

    protected void startProc() {
    }

    public void activated() {
    }

    public void deactivated() {
        /* 216 */ saveProperties();
    }

    public void quitRequested() {
        /* 220 */ saveProperties();
    }

    public void saveProperties() {
        /* 224 */ PropertyManager pm = PropertyManager.getManager();
        /* 225 */ pm.save();
        /* 226 */ ToolProperty.save();
    }

    public static void initControllers(Console c) {
    }

    public static void main(String[] args) throws Exception {
        /* 239 */ if (!args[0].equals("-developer")) {
            /* 240 */ System.out.println("KWTApp Error!!!");
            return;
        }
        /* 243 */ @SuppressWarnings("deprecation")
        KWTApp app = (KWTApp) Class.forName(args[1].replace('/', '.')).newInstance();
        /* 244 */ String[] nargs = new String[args.length - 2];
        /* 245 */ System.arraycopy(args, 2, nargs, 0, nargs.length);

        /* 247 */ app.appmain(Console.getConsole(), nargs);
    }
}
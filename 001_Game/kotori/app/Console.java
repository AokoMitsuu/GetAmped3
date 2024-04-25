package kotori.app;

import java.io.File;
import java.util.ArrayList;
import kotori.app.mac.MacConsole;
import kotori.gfx.GLGraphics;
import kotori.gfx.PixelFormat;
import kotori.kwt.dnd.DropTargetEvent;
import kotori.kwt.event.ApplicationEvent;
import kotori.kwt.event.Event;
import kotori.kwt.event.InputMethodEvent;
import kotori.kwt.event.KeyEvent;
import kotori.kwt.event.MouseEvent;

@SuppressWarnings("unchecked")
public abstract class Console {
    public static final int WINDOWMODE_GAME = 0;
    public static final int WINDOWMODE_LAUNCHER = 2;
    public static final int WINDOWMODE_TOOL = 1;
    private static Console console;
    private final ArrayList<Event> ebuf = new ArrayList<Event>();

    public abstract Object command(int i, Object obj);

    public abstract void createDisplay(DisplayMode displayMode);

    public abstract void createFullWindow();

    public abstract void createGraphics(PixelFormat pixelFormat);

    public abstract void createWindow(String str, int i, int i2, int i3);

    public abstract void disableEvents(int[] iArr);

    public abstract void disposeDisplay();

    public abstract void disposeGraphics();

    public abstract void enableDrop(boolean z);

    public abstract void enableInputMethods(boolean z);

    public abstract void free();

    public abstract AudioManager getAudioManager();

    public abstract int getCommandModifier();

    public abstract File getCommonDirectory();

    public abstract Controller getController(int i);

    public abstract PixelFormat getCurrentPixelFormat();

    public abstract DisplayMode getDisplayMode();

    public abstract DisplayMode[] getDisplayModeList();

    public abstract int getDnDAction(int i);

    public abstract String getInlineWebParam(String str);

    public abstract String getInlineWebURL();

    public abstract int getModifiers();

    public abstract void getMousePos(int[] iArr);

    public abstract int getNumControllers();

    public abstract PixelFormat[] getPixelFormatList();

    public abstract int getPlatformID();

    public abstract void getScreenSize(int[] iArr);

    public abstract long getTimeMillis();

    public abstract int getWindowHandle();

    public abstract void hideInlineWeb();

    public abstract boolean isDisplayAvailable(DisplayMode displayMode);

    public abstract boolean isForeProcess();

    public abstract boolean isKeyPressed(int i);

    public abstract boolean makeCurrent();

    public abstract void scanControllers();

    public abstract void selectInlineWeb(int i);

    public abstract void setCandidateLocation(int i, int i2);

    public abstract void setCursorVisible(boolean z);

    public abstract void setInlineWebParam(String str, String str2);

    public abstract void setInlineWebURL(String str);

    public abstract boolean setInputMethodActiveMode();

    public abstract void setInputMethodPassiveMode(boolean z);

    public abstract void setMousePos(int i, int i2);

    public abstract void showInlineWeb(int i, int i2, int i3, int i4);

    public abstract void swapBuffers();

    public abstract void update();

    public abstract void yield();

    static {
        console = null;
        String clsname = null;
        try {
            String os = System.getProperty("os.name");
            if (os.startsWith("Windows")) {
                clsname = "kotori.app.win32.WinConsole";
            } else if (os.startsWith("Mac OS X")) {
                clsname = "kotori.app.mac.MacConsole";
            }
            Class<Console> c = (Class<Console>) Class.forName(clsname);
            console = c.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
            console = null;
        }
    }

    public static Console getConsole() {
        return console;
    }

    public static boolean isMac() {
        return console instanceof MacConsole;
    }

    public void createWindow(String title, int w, int h, boolean resize) {
        title = "Get Amped 3";
        createWindow(title, w, h, 1);
    }

    public synchronized Event peekEvent() {
        return this.ebuf.isEmpty() ? null : (Event) this.ebuf.remove(0);
    }

    public synchronized void postEvent(Event e) {
        this.ebuf.add(e);
    }

    public void minimize(boolean b) {
    }

    public GLGraphics getGLGraphics() {
        return null;
    }

    public PixelFormat getBestPixelFormat() {
        DisplayMode d = getDisplayMode();
        PixelFormat pf = null;
        PixelFormat[] list = getPixelFormatList();
        int score = 0;
        for (PixelFormat _p : list) {
            int _sc = 0;
            if (_p.getDepthBits() > 0) {
                _sc = 100;
                if (_p.getPixelBits() == d.colordepth) {
                    _sc = 100 + 40;
                }
                if (_p.getStencilBits() > 0) {
                    _sc += 20;
                }
                if (_p.getAlphaBits() > 0) {
                    _sc -= 30;
                }
            }
            if (_sc > score) {
                score = _sc;
                pf = _p;
            }
        }
        return pf;
    }

    static final KeyEvent createKeyEvent(int id, int mod, int keycode, char keychar) {
        return new KeyEvent(id, mod, keycode, keychar);
    }

    static final MouseEvent createMouseEvent(int id, int mod, int x, int y, int cc, boolean pop, int w) {
        return new MouseEvent(id, mod, x, y, cc, pop, w);
    }

    static final ApplicationEvent createApplicationEvent(int id) {
        return new ApplicationEvent(id);
    }

    static final DisplayMode createDisplayMode(int w, int h, int b, int r) {
        return new DisplayMode(w, h, b, r);
    }

    static final PixelFormat createPixelFormat(long i, int p, int r, int g, int b, int a, int d, int s) {
        return new PixelFormat(i, p, r, g, b, a, d, s);
    }

    static final Controller createController(String id, long ref) {
        return new Controller(id, ref);
    }

    static final InputMethodEvent createInputMethodEvent(int id, String t, int[] h, int commited, int caret) {
        return new InputMethodEvent(id, t, h, commited, caret);
    }

    static final DropTargetEvent createDropTargetEvent(int id, int aid, int x, int y, Object o) {
        return new DropTargetEvent(id, aid, x, y, o);
    }
}
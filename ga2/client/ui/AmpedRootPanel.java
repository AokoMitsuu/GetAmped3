package ga2.client.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.nr.Utils.ColorAsString;
import com.nr.tool.LocalStorage;
import com.nr.ui.InputPopup;
import ga2.client.GetAmped2;
import ga2.client.LookAndFeel;
import ga2.data.ShopData;
import ga2.data.UserData;
import ga2.data.UserItems;
import ga2.data.UserItems.Item;
import ga2.data.UserShortcuts;
import ga2.data.UserShortcuts.AvatorShortcut;
import ga2.laf.amped2.AmpedPopupWindow;
import ga2.laf.amped2.ShutterManager;
import ga2.stage.PlayerViewModel;
import ga2.stage.StageConnector;
import ga2.stage.event.BasicAmpedEvent;
import kotori.geom.Vector2D;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.KWTManager;
import kotori.kwt.KWTMethod;
import kotori.kwt.KWTStruct;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.KeyEvent;
import kotori.kwt.event.MouseEvent;

public class AmpedRootPanel extends Container {
    public static final int LAYER_BG = 0;
    public static final int LAYER_MAIN = 1;
    public static final int LAYER_TOPPANEL = 2;
    public static final int LAYER_CHATBOARD = 4;
    public static final int LAYER_DASHBOARD = 5;
    public static final int LAYER_HELP = 6;
    public static final int LAYER_ACCEPTMENU = 7;
    public static final int LAST_LAYER = 7;
    public static final String[] layername = {
            "bg",
            "main",
            "toppanel",
            "trade",
            "chatboard",
            "dashboard",
            "help",
            "eventmenu"
    };
    Container dialogs;
    TopPanel toppanel;
    Container.ComponentEntry[] layers = new Container.ComponentEntry[8];
    int toppanelmode = -1;
    boolean updateRepaint = false;

    public AmpedRootPanel() {
        initPanel();
    }

    public Container.ComponentEntry layerAt(int i) {
        return this.layers[i];
    }

    public void initPanel() {
        clear();
        Vector2D v = LookAndFeel.getLAF().getSizeDimension();
        float w = v.x;
        float h = v.y;
        setRect(0.0f, 0.0f, w, h);
        for (int l = 0; l <= 7; l++) {
            this.layers[l] = add(null);
            this.layers[l].setName(layername[l]);
        }
        this.layers[LAYER_DASHBOARD].changeComponent(createEmptyPanel(0.0f, 0.0f, w, h));
        this.layers[LAYER_ACCEPTMENU].changeComponent(createAcceptMenuPanel(0.0f, 0.0f, w, h));
    }

    private Container createEmptyPanel(float x, float y, float w, float h) {
        Container c = new Container();
        c.setRect(x, y, w, h);
        c.setMouseAccessible(false);
        return c;
    }

    private Container createAcceptMenuPanel(float x, float y, float w, float h) {
        AcceptMenuPanel c = new AcceptMenuPanel();
        c.setRect(x, y, w, h);
        c.setMouseAccessible(false);
        return c;
    }

    public void exchangePanel(String panelname, int layer) {
        KWTStruct kwt = LookAndFeel.getLAF().getKWTStruct(panelname);
        exchangePanel(kwt.createContainer(kwt.getLibrary()), layer);
    }

    public void exchangePanel(Component c, int layer) {
        boolean b = false;
        synchronized (KWTManager.PAINT_LOCK) {
            Container.ComponentEntry e = this.layers[layer];
            Container.ComponentEntry ne = (Container.ComponentEntry) (layer == LAST_LAYER ? add(c)
                    : insert(c, this.layers[layer + 1]));
            e.setName(null);
            Component old = e.getComponent();
            if (old instanceof Container) {
                if (!((Container) old).performMethod("end")) {
                    ((Container) old).performMethod(new KWTMethod().wait(1000).disappear(true));
                }
            } else {
                remove(e);
                b = true;
            }
            ne.setName(layername[layer]);
            this.layers[layer] = ne;
        }
        if (b) {
            if (c instanceof Container) {
                ((Container) c).performMethod("start");
            }
        } else if (c instanceof Container) {
            ((Container) c).performMethod("start");
        }
    }

    public void removePanel(int layer) {
        synchronized (KWTManager.PAINT_LOCK) {
            Container.ComponentEntry e = this.layers[layer];
            e.changeComponent(null);
        }
    }

    public void finishCurrentScene() {
        Container.ComponentEntry e;
        synchronized (KWTManager.PAINT_LOCK) {
            e = this.layers[LAYER_MAIN];
        }
        Component c = e.getComponent();
        if (c != null) {
            ActionEvent ae = new ActionEvent(ActionEvent.ACTION_PERFORMED, 0, "finish");
            ae.setSource(c);
            c.postEvent(ae);
        }
    }

    public void showNotice(String s) {
        showNotice(s, "start");
    }

    public void showNotice(String s, String method) {
        LookAndFeel laf = LookAndFeel.getLAF();
        KWTStruct srt = laf.getKWTStruct("kwtdocs/notice2.kwt");
        Container cnt = srt.createContainer(srt.getLibrary());
        cnt.p.setPosition((laf.getSizeDimension().x * 0.5f) - (cnt.bnd.w / 2.0f), 70.0f);
        cnt.setMouseAccessible(false);
        cnt.setText("msg", s);
        synchronized (KWTManager.PAINT_LOCK) {
            add(cnt);
            cnt.performMethod(method);
        }
    }

    public void showConfiemPopup(String s) {
        new RootPopupWindow(s);
    }

    public void setUpdateRepaint(boolean b) {
        this.updateRepaint = b;
    }

    @Override
    public void updateFrame() {
        if (this.updateRepaint) {
            repaint();
        }
        super.updateFrame();
        ShutterManager.getManager().updateShutter();
    }

    public class RootPopupWindow extends AmpedPopupWindow {
        Container cnt;
        Container yes;

        RootPopupWindow(String s) {
            LookAndFeel laf = LookAndFeel.getLAF();
            KWTStruct srt = laf.getKWTStruct("kwtdocs/dlg/rootconfirmpopup2.kwt");
            Container c = srt.createContainer(srt.getLibrary());
            c.p.setPosition(laf.getSizeDimension().x * 0.5f, laf.getSizeDimension().y * 0.5f);
            c.setText("text", s);
            this.cnt = c;
            Container.findNamedComponent("yes", c).addMouseListener(this);
            synchronized (KWTManager.PAINT_LOCK) {
                AmpedRootPanel.this.add(c);
                c.performMethod("show");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.cnt.performMethodAndWait("hide");
            AmpedRootPanel.this.remove((Component) this.cnt);
        }
    }

    @Override
    public void controlB2() {
        try {
            if (GetAmped2.getAmped2().getStageConnector().getStage().isLoungeMode()
                    || (GetAmped2.getAmped2().getStageConnector() instanceof StageConnector.Playback)
                    || getKWTManager().hasModal()) {
                KeyEvent ke = new KeyEvent(401, 0, 27, (char) 0);
                ke.setSource(getKWTManager().getCursorTarget());
                KWTManager.getEventQueue().postEvent(ke);
            }
        } catch (Exception e) {
        }
    }

    String argsString = "";
    PlayerEquipPanel playerEquipPanel;

    @Override
    public boolean processKeyEvent(KeyEvent e) {
        StageConnector con = GetAmped2.getAmped2().getStageConnector();
        if (con != null)
            if (e.getID() == 401) {
                int c = e.getKeyCode();
                if (c == 118) { // F7
                    InputPopup intputPopup = new InputPopup(this.argsString);
                    Vector2D v0 = intputPopup.getGlobalPosition(intputPopup.bnd.w * 0.5f, intputPopup.bnd.h * 0.5f);
                    Vector2D v = LookAndFeel.getLAF().getSizeDimension();
                    intputPopup.setMotion(v0, new Vector2D((int) (v.x * 0.5f), (int) (v.y * 0.5f)), 0.0f);
                    intputPopup.show(getKWTManager());
                    this.argsString = intputPopup.getResult();
                } else if (c == 119) { // F8
                    try {
                        if (this.playerEquipPanel != null) {
                            com.nr.Utils.SaveShortCuts(this.playerEquipPanel.ud);
                            this.playerEquipPanel.closing();
                            this.playerEquipPanel.performMethod("hide");
                            this.remove(this.playerEquipPanel);
                            this.playerEquipPanel = null;
                        } else {
                            this.playerEquipPanel = new PlayerEquipPanel();
                            this.playerEquipPanel.setStageConnector(con);
                            UserData ud = (UserData) con.getUserData().clone();
                            ud.rec.masterAllStyles();

                            int skill0 = 0;
                            int skill1 = 0;
                            int skill2 = 0;
                            int skill3 = 0;

                            try {
                                String[] ids = argsString.split(",");
                                if (ids.length >= 1)
                                    skill0 = Integer.parseInt(ids[0]);
                                if (ids.length >= 2)
                                    skill1 = Integer.parseInt(ids[1]);
                                if (ids.length >= 3)
                                    skill2 = Integer.parseInt(ids[2]);
                                if (ids.length >= 4)
                                    skill3 = Integer.parseInt(ids[3]);
                            } catch (Exception ex) {
                            }

                            List<UserItems.Item> items = com.nr.Utils.getAllAccs();
                            for (int i = 0, len = items.size(); i < len; i++) {
                                try {
                                    ud.items.addItem(items.get(i));
                                } catch (Exception ex) {
                                    continue;
                                }
                            }
                            com.nr.Utils.accsTuneup(ud.items.getItems(), skill0, skill1, skill2, skill3);
                            ud = com.nr.Utils.LoadShortCuts(ud);

                            this.playerEquipPanel.setUserData(ud);
                            this.add(this.playerEquipPanel);
                            this.playerEquipPanel.showing();
                            this.playerEquipPanel.performMethod("show");
                        }
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                } else if (c == 120) { // F9
                    System.out.println("test");
                    KWTManager m = getKWTManager();
                    if (m == null)
                        return true;
                    ShopData.Item item = new ShopData.Item();
                    item.id = 36;
                    item.kind = 10;
                    ShopItemPopup p = new ShopItemPopup.Builder(new ShopData.Item[] { item }, 0, 1).build();
                    UserData ud = GetAmped2.getAmped2().getUserData();
                    p.setShopPanel(ud, false, false);
                    p.show2(m);
                } else if (c == 121) { // F10
                    KWTManager m = getKWTManager();
                    if (m == null)
                        return true;
                    ShopData.Item item = new ShopData.Item();
                    item.id = 3;
                    item.kind = 10;
                    ShopItemPopup p = new ShopItemPopup.Builder(new ShopData.Item[] { item }, 0, 1).build();
                    UserData ud = GetAmped2.getAmped2().getUserData();
                    p.setShopPanel(ud, false, false);
                    p.show2(m);
                }
            }
        return super.processKeyEvent(e);
    }
}

package ga2.client.ui;

import ga2.client.GetAmped2;
import ga2.client.LookAndFeel;
import ga2.client.SkinManager;
import ga2.client.ui.GMSetSkillPanel.Skill;
import ga2.data.ETCItem;
import ga2.data.ItemAdditionalData;
import ga2.data.PlayerSkinCipherData;
import ga2.data.RankData;
import ga2.data.SkillData;
import ga2.data.StyleData;
import ga2.data.TechData;
import ga2.data.UserData;
import ga2.data.UserInfo;
import ga2.data.UserItems;
import ga2.data.UserShortcuts;
import ga2.laf.amped2.AmpedPopupWindow;
import ga2.laf.amped2.CharaPreviewPanel;
import ga2.setting.FluidSetting;
import ga2.setting.GameSetting;
import ga2.stage.StageConnector;
import ga2.stage.StagePlayerData;
import ga2.stage.event.AmpedEvent;
import ga2.stage.event.BasicAmpedEvent;
import ga2.stage.ui.StagePanel;
import java.util.ArrayList;
import java.util.Arrays;

import kotori.gfx.img.Texture;
import kotori.kwt.ComboBox;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.Figure;
import kotori.kwt.ImageFigure;
import kotori.kwt.KWTColor;
import kotori.kwt.KWTManager;
import kotori.kwt.KWTStruct;
import kotori.kwt.TextFigure;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.ActionListener;
import kotori.kwt.event.Event;
import kotori.kwt.event.MouseAdaptor;
import kotori.kwt.event.MouseEvent;
import kotori.kwt.event.MouseListener;
import kotori.kwt.event.TimerEvent;
import kotori.util.Inttable;
import kotori.util.KotoriUtil;

public class PlayerEquipPanel extends Container implements StageMenuHandler.Menu {
    StageConnector con;
    KWTStruct srt;
    CharaPreviewPanel preview;
    Inttable skinmap = new Inttable();
    TextFigure stylename;
    Container stylelv;
    PartsSelectHandler parts;
    StatusHandler status;
    CharaStockHandler equip;
    UserData ud;
    UserInfo ui;
    UserShortcuts us;
    UserInfo backupui;
    UserShortcuts backupus;
    private static final int equipslotitem_id = 48;
    private boolean saleSlot;
    private int newSlot;
    int equipslot;
    public static final int pcon = 113416;

    public PlayerEquipPanel() {
        this.srt = LookAndFeel.getLAF().getKWTStruct("kwtdocs/menu_playerequip2.kwt");
        setStruct(this.srt);
        this.bnd.set(this.srt.bnd);

        this.stylename = (TextFigure) findNamedFigure("stylename", this);
        this.stylelv = (Container) findNamedComponent("stylelv", this);

        this.parts = new PartsSelectHandler();
        this.parts.setContainer((Container) findNamedComponent("parts", this));

        this.status = new StatusHandler();
        this.status.setContainer((Container) findNamedComponent("status", this));

        this.equip = new CharaStockHandler();

        this.preview = (CharaPreviewPanel) findNamedComponent("preview", this);

        FluidSetting fs = FluidSetting.getSetting();
        this.saleSlot = (fs.shop.getIndex(10, 48, 0) >= 0);

        setScriptArgument(1, new Boolean((GameSetting.getSetting()).accs_potential));
    }

    public void setStageConnector(StageConnector c) {
        this.con = c;
    }

    void checkNewSlot() {
        this.equipslot = this.ud.items.getEquipSlot();
        if (this.saleSlot || this.equipslot > 1) {
            this.newSlot = 1;
        } else {
            this.newSlot = 0;
        }
        if (this.newSlot == 1 && this.ui.useridhash == 113416) {
            this.newSlot = 2;
            setScriptArgument(0, new Boolean(true));
        }
    }

    public void setUserData(UserData d) {
        this.ud = (UserData) d.clone();
        this.ui = this.ud.info;
        this.us = this.ud.shortcuts;
        this.backupui = this.ui.clone();

        this.backupus = (UserShortcuts) this.us.clone();
        setupSkinMap();
        this.parts.setUserData(d);
        updateUserInfo();
        checkNewSlot();
        this.equip.setContainer(this);
        this.equip.updateStocks();
    }

    public void setUserItems(UserItems items) {
        this.ud.items = items.clone();
        setupSkinMap();
        this.parts.setUserItems(items);
        this.parts.updateItems(true);
        checkNewSlot();
        this.equip.setContainer(this);
        this.equip.updateStocks();
    }

    void setupSkinMap() {
        UserItems.Item[] si = this.ud.items.getList(9);
        SkinManager sm = SkinManager.getManager();
        for (int l = 0; l < si.length; l++) {
            Object o = sm.loadAndLock((si[l]).id);
            if (o instanceof PlayerSkinCipherData) {
                this.skinmap.put((si[l]).id, o);
            }
        }
    }

    public UserData getEditingUserData() {
        return this.ud;
    }

    void updateUserInfo() {
        this.preview.setUserInfo(this.ui);
        this.parts.setUserInfo(this.ui);
        this.parts.updateItems(true);
        this.status.updateStatus();
        setStyle(this.ui);
    }

    void setStyle(UserInfo ui) {
        StyleData sd = GameSetting.getSetting().getStyle(ui.style);
        int exp = this.ud.rec.getStyleExp(sd.group);
        int g = sd.getLevelGauge(exp);
        this.stylename.setText(String.valueOf(sd.name) + "  /  " + (sd.getGroup()).lvname[g / 2]);
        this.stylelv.setScriptArgument(0, new Integer(exp));
        this.stylelv.setScriptArgument(1, new Integer(g));
        this.stylelv.performMethod("set");
    }

    void partsPreview(UserItems.Item i, int sidx) {
        UserInfo _ui = this.ui.clone();
        if (i != null) {
            _partsChange(i, _ui, sidx);
        }
        StyleData sd = GameSetting.getSetting().getStyle(_ui.style);
        _ui.stylelv = sd.getLevel(this.ud.rec.getStyleExp(sd.group));
        this.preview.setUserInfo(_ui);
        this.status.updateStatus();
        setStyle(_ui);
    }

    void partsSelect(UserItems.Item i, int sidx, final Component c) {
        if (i == null)
            return;
        _partsChange(i, this.ui, sidx);

        final GameSetting set = GameSetting.getSetting();
        StyleData sd = set.getStyle(this.ui.style);
        this.ui.stylelv = sd.getLevel(this.ud.rec.getStyleExp(sd.group));

        updateUserInfo();

        if (i.kind != 17)
            this.equip.storeStock();

        if ((this.ui.isPrivilege() || set.version < 0) && isPrivatery(i)) {
            KWTManager.getEventQueue().postEvent((Event) new TimerEvent(0, null) {
                public void timerInvoked(TimerEvent e) {
                    AmpedPopupWindow.smallInfoPopup(set.getLocalText("$menu.playerequip.caution.privatery"),
                            c, 1);
                }
            });
        }

        if (i.kind == 5 && this.con.getStagePanel() instanceof StagePanel) {
            final StagePanel sp = (StagePanel) this.con.getStagePanel();
            if (sp.isTutorial()) {
                KWTManager.getEventQueue().postEvent((Event) new TimerEvent(200, null) {
                    public void timerInvoked(TimerEvent e) {
                        sp.selectMenu(-1);
                    }
                });
            }
        }
    }

    private boolean isPrivatery(UserItems.Item i) {
        return (i != null && (i.id != -1 || this.ui.skin == 0 || !this.ui.isEnabledSlot()) && i.id != 0
                && GameSetting.getSetting().isPrivately(i.kind, i.id));
    }

    private void _partsChange(UserItems.Item i, UserInfo ui, int sidx) {
        PlayerSkinCipherData pscd;
        RankData.Rank rank;
        int iid = (i.id == -1) ? 0 : i.id;
        boolean changestyle = false;
        switch (i.kind) {
            case 0:
                ui.style = (short) iid;
                setSkinedID(ui, ui.skin);
                changestyle = true;
                break;
            case 7:
                ui.weapon = (short) iid;
                break;
            case 1:
                ui.head = (short) iid;
                break;
            case 2:
                ui.face = (short) iid;
                break;
            case 3:
                ui.body = (short) iid;
                break;
            case 4:
                ui.deco = (short) iid;
                break;
            case 5:
                ui.accs = (short) iid;
                ui.skill0 = (short) i.getPotentialSkill();
                ui.skill0_pow = (byte) i.getPotentialSkillPow();
                ui.skill1 = (short) i.getTuneUp(0);
                ui.skill2 = (short) i.getTuneUp(1);
                ui.skill3 = (short) i.getTuneUp(2);
                break;
            case 9:
                pscd = (PlayerSkinCipherData) this.skinmap.get(iid);
                if (pscd == null || pscd.isPrivately())
                    iid = 0;
                ui.skin = iid;
                setSkinedID(ui, iid);
                changestyle = true;
                break;
            case 16:
                ui.voice = iid & 0xFF;
                break;
            case 17:
                rank = GameSetting.getSetting().getRank(iid);
                if (rank == null || rank.isKind(0)) {
                    ui.specialrank = 0;
                    break;
                }
                ui.specialrank = iid;
                break;
        }

        ui.resetNotHavingEquip(this.ud.items);
        // if (changestyle)
        // ui = changeStyle(this.ud, ui, false);
    }

    private UserInfo changeStyle(UserData ud, UserInfo ui, boolean showPopup) {
        int style = ui.style;
        int accs = ui.accs;
        ui.changeStyle(ui.style, ud.items);
        if (showPopup) {
            if (style != ui.style && ud.items.isExpired(0, style)) {
                String msg = String.format(GameSetting.getSetting().getLocalText("$useritem.info.expired"),
                        new Object[] { (new UserItems.Item((byte) 0, style)).getName() });
                AmpedPopupWindow.smallInfoPopup(msg, (Component) this.parts.style, 1);
            }
            if (accs != ui.accs && ud.items.isExpired(5, accs)) {
                String msg = String.format(GameSetting.getSetting().getLocalText("$useritem.info.expired"),
                        new Object[] { (new UserItems.Item((byte) 5, accs)).getName() });
                AmpedPopupWindow.smallInfoPopup(msg, (Component) this.parts.accs, 1);
            }
        }
        return ui;
    }

    private void setSkinedID(UserInfo ui, int sid) {
        if (sid != 0) {
            ui.setPlayerSkinData((PlayerSkinCipherData) this.skinmap.get(sid));
        } else {
            ui.build = 0;
        }
    }

    public void showing() {
    }

    public void closing() throws Exception {
        boolean update = false;

        ItemAdditionalData iaData = this.ud.items.getItemAdditionalData();
        if (iaData != null && iaData.checkExpiredEquip(this.ui)) {
            this.ui = changeStyle(this.ud, this.ui, true);
            update = true;
        }

        GetAmped2 ga2 = GetAmped2.getAmped2();
        Object[] wmd = (Object[]) null;
        if (ga2 != null) {
            if (((ga2.getUserData()).restrict & 0x80) != 0 && this.ui.skin != 0) {
                this.ui.skin = 0;
                this.ui = changeStyle(this.ud, this.ui, true);

                update = true;
            }

            if (!Arrays.equals((Object[]) this.us.avatorsc, (Object[]) this.backupus.avatorsc)) {
                com.nr.Utils.SaveShortCuts(this.ud);

                wmd = new Object[] { this.us.avatorsc };
                ga2.updateUserData(this.us.clone());
                update = true;
            }
            if (!this.ui.equals(this.backupui)) {
                if (this.con.getPlayer().isEmotionRunning()) {
                    this.con.getPlayer().stopScript();
                    BasicAmpedEvent e = BasicAmpedEvent.create(this.con.getPlayer().getID(), 127);
                    this.con.sendToServer((AmpedEvent) e);
                }

                StyleData sd = GameSetting.getSetting().getStyle(this.ui.style);
                this.ui.stylelv = sd.getLevel(this.ud.rec.getStyleExp(sd.group));

                wmd = KotoriUtil.addArray(wmd, this.ui);
                update = true;
                ga2.updateUserData(ga2.command("write_mydata", wmd));
                wmd = (Object[]) null;
                if (this.con != ga2.getStageConnector() && (!(this.con.getStagePanel() instanceof StagePanel)
                        || !((StagePanel) this.con.getStagePanel()).isTutorial())) {
                    this.con.updateUserData(ga2.getUserData());
                }
                this.ui = ga2.getUserInfo().clone();
            }
            if (update && wmd != null) {
                ga2.command("write_mydata", wmd);
            }
        } else if (this.con != null) {
            if (((this.con.getUserData()).restrict & 0x80) != 0 && this.ui.skin != 0) {
                this.ui.skin = 0;
                this.ui = changeStyle(this.ud, this.ui, true);
                update = true;
            }

            this.con.updateUserData(this.ui);
            this.con.updateUserData(this.us);
        }

        if (this.con != null && update) {
            this.con
                    .sendToServer((AmpedEvent) BasicAmpedEvent.create(this.con.getPlayer().getID(), 110, this.ui));
        }
    }

    class PartsSelectHandler
            extends ga2.client.ui.PartsSelectHandler {
        private ItemListPopup popup;

        final void setContainer(Container c) {
            setContainer(c, true, 1020);
        }

        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd == null)
                return;
            if ("preview".equals(cmd)) {
                synchronized (KWTManager.PAINT_LOCK) {
                    PlayerEquipPanel.this.partsPreview((UserItems.Item) e.getArgument(), 0);
                }

            } else if ("select".equals(cmd)) {
                synchronized (KWTManager.PAINT_LOCK) {
                    PlayerEquipPanel.this.partsSelect((UserItems.Item) e.getArgument(), 0,
                            (Component) e.getSource());
                }

            } else if ("swaped".equals(cmd)) {
                synchronized (KWTManager.PAINT_LOCK) {
                    setUserItems((UserItems) e.getArgument());
                }

            } else if ("popup".equals(cmd)) {
                this.popup = (ItemListPopup) e.getArgument();
            } else if ("reset".equals(cmd)) {
                PlayerEquipPanel.this.updateUserInfo();
            }
        }

        public void closePopup() {
            if (this.popup != null)
                this.popup.end();

        }
    }

    static String[] gname = new String[] { "str", "tec", "spd", "jmp", "def" };
    private Container c_select;
    private Container c_enter;

    class StatusHandler {
        Container cnt;
        PlayerEquipPanel.Gauge[] gauge;
        PlayerEquipPanel.IconLabel[] skills;

        void setContainer(Container c) {
            this.cnt = c;
            this.gauge = new PlayerEquipPanel.Gauge[5];
            int l;
            for (l = 0; l < 5; l++) {
                this.gauge[l] = new PlayerEquipPanel.Gauge();
                this.gauge[l]
                        .setContainer((Container) PlayerEquipPanel.findNamedComponent(PlayerEquipPanel.gname[l], c));
            }

            this.skills = new PlayerEquipPanel.IconLabel[5];
            for (l = 0; l < this.skills.length; l++) {
                this.skills[l] = new PlayerEquipPanel.IconLabel();
                this.skills[l].setContainer((Container) PlayerEquipPanel.findNamedComponent("s" + l, c));
            }
        }

        void updateStatus() {
            StagePlayerData psd = PlayerEquipPanel.this.preview.getPlayer().getStagePlayData();
            ArrayList a = new ArrayList();
            for (int l = 0; l < psd.tecnum; l++) {
                StagePlayerData.Tech t = psd.tec[l];
                if (t.data.group != null) {
                    if (!a.contains(t.data.group)) {
                        a.add(t.data.group);
                    }
                } else {
                    a.add(t.data);
                }
            }

            int start = 1;

            int snum = 5;

            GameSetting set = GameSetting.getSetting();
            a.clear();
            if (psd.accs != 0)
                a.add(set.getAccs(psd.accs).getActiveSkill(psd.style));
            if (set.accs_potential) {
                if (set.getSkill(psd.skill0) != null) {
                    a.add(set.getSkill(psd.skill0));
                }
                start = 0;
            }
            if (set.getSkill(psd.skill1) != null)
                a.add(set.getSkill(psd.skill1));
            if (set.getSkill(psd.skill2) != null)
                a.add(set.getSkill(psd.skill2));
            if (set.getSkill(psd.skill3) != null)
                a.add(set.getSkill(psd.skill3));

            int num = Math.min(snum, a.size());
            int i;
            for (i = 0; i < num; i++) {
                this.skills[start + i].set(psd, (SkillData) a.get(i), (i == 0));
            }
            for (i = 0; i < start; i++) {
                this.skills[i].reset();
            }
            for (i = start + num; i < snum; i++) {
                this.skills[i].reset();
            }

            this.gauge[0].set(psd.STR);
            this.gauge[1].set(psd.TEC);
            this.gauge[2].set(psd.SPD);
            this.gauge[3].set(psd.JMP);
            this.gauge[4].set(psd.DEF);
        }
    }

    class Gauge {
        Container cnt;

        void setContainer(Container c) {
            this.cnt = c;
        }

        void set(int i) {
            this.cnt.moveFrameTo(i * 0.1F, 8.0F);
        }
    }

    class IconLabel {
        Container cnt;
        TextFigure label;
        ImageFigure icon;

        void setContainer(Container c) {
            this.cnt = c;
            this.label = (TextFigure) PlayerEquipPanel.findNamedFigure("label", c);
            this.icon = new ImageFigure();
            this.icon.setBlend(1);
            this.cnt.insertFigure((Figure) this.icon, 0);

            this.cnt.setControlPosOffset(16, 0);
        }

        public void set(TechData td) {
            if (td != null) {
                this.label.setText(td.name);
                this.label.reduceStrWidth();
                Texture t = td.getIcon(0);
                if (t != null) {
                    this.icon.setTexture(t);
                    this.icon.setRect(10.0F - t.getRectWidth() * 0.5F, 8.0F - t.getRectHeight() * 0.5F,
                            t.getRectWidth(), t.getRectHeight());
                } else {
                    this.icon.setTexture(null);
                }
            } else {
                reset();
            }
        }

        public void set(StagePlayerData spd, SkillData sd, boolean accs) {
            int sid = spd.style;
            int accsid = spd.accs;
            if (sd != null) {
                String name = sd.name;
                if (sd.skilllevel == -1) {
                    name = String.valueOf(name) + " Lv" + spd.skill0_pow;
                }
                this.label.setText(name);
                this.label.reduceStrWidth();
                if (accs) {
                    this.label.setColor(new KWTColor(-10092289));
                    this.icon.setImageName("e:skill_accs");
                    this.icon.setColor(KWTColor.white);
                    this.icon.setRect(0.0F, -1.0F, 20.0F, 18.0F);
                } else {
                    KWTColor col = new KWTColor(SkillData.getLevelColor(sd.skilllevel));
                    this.label.setColor(col);
                    this.icon.setImageName("skilllv" + (sd.skilllevel + 1));
                    this.icon.setColor(col);
                    this.icon.setRect(0.0F, 0.0F, 16.0F, 16.0F);
                }
                this.icon.setup(PlayerEquipPanel.this.srt.getLibrary());

                StyleData style = GameSetting.getSetting().getStyle(sid);
                if (sd.skilllevel > style.getLevel(PlayerEquipPanel.this.ud.rec.getStyleExp(style.group))) {
                    this.cnt.skipFrameTo(1.0F);
                    this.cnt.setHelpValue(
                            GameSetting.getSetting().getLocalText("$menu.playerequip.help.stylelvshortages"));
                } else {
                    this.cnt.skipFrameTo(0.0F);
                    this.cnt.setHelpValue(new Object[] { sd, Integer.valueOf(accsid) });
                }
                this.cnt.setCursor(KWTManager.getLibrary().getCursor("finger"));
                this.cnt.repaint();
            } else {
                reset();
            }
        }

        void reset() {
            this.label.setText("");
            this.icon.setTexture(null);
            this.cnt.setHelpValue(null);
            this.cnt.setCursor(null);
            this.cnt.skipFrameTo(0.0F);
        }
    }

    public void updateFrame() {
        super.updateFrame();
        if (this.newSlot != 2) {
            return;
        }
        if (this.equip.sel == -1)
            return;
        float ty = -2.0F + this.equip.sel() * 26.0F;
        if (ty == this.c_select.p.y)
            return;
        if (!this.c_select.isVisible()) {
            this.c_select.setVisible(true);
            this.c_select.p.y = ty;
        }
        float ofs = ty - this.c_select.p.y;
        if (Math.abs(ofs) < 0.1F) {
            this.c_select.p.y = ty;
        } else {
            this.c_select.p.y += ofs * 0.5F;
        }
        this.c_select.updateMatrix();
    }

    class SlotHandler extends MouseAdaptor {
        Container cnt;
        Container[] st = new Container[9];

        int sel;

        public SlotHandler(Container c) {
            this.cnt = c;
        }

        public int update(int select_shortcut_index) {
            this.sel = -1;

            Container slot = (Container) PlayerEquipPanel.findNamedComponent("slot", this.cnt);
            if (!PlayerEquipPanel.this.saleSlot && PlayerEquipPanel.this.equipslot == 1) {
                slot.setVisible(false);
                return 0;
            }

            GameSetting gs = GameSetting.getSetting();
            ETCItem ei = gs.getETCItem(48);
            int stockmax = ((int[]) ei.arg)[0];
            int frame = PlayerEquipPanel.this.equipslot - 2;
            if (PlayerEquipPanel.this.saleSlot)
                frame++;
            frame = Math.min(frame, stockmax - 2);

            slot.skipFrameTo(frame);

            for (int l = 0; l < 2 + frame; l++) {
                Container container = (Container) PlayerEquipPanel.findNamedComponent("st" + l, this.cnt);
                if (container != null) {
                    this.st[l] = container;
                    if (l < PlayerEquipPanel.this.equipslot)
                        container.skipFrameTo(1.0F);
                    container.setCursor(KWTManager.getLibrary().getCursor("finger"));
                    container.addMouseListener((MouseListener) this);
                }
            }
            if (PlayerEquipPanel.this.equipslot < frame + 2) {
                this.st[PlayerEquipPanel.this.equipslot]
                        .setHelpValue(gs.getLocalText("$menu.playerequip.help.introduction"));
            }

            int s = Math.min(select_shortcut_index / 9, PlayerEquipPanel.this.equipslot - 1);
            return (s >= 0) ? s : 0;
        }

        boolean select(int s) {
            if (this.sel == s || s < 0)
                return false;
            if (this.st[0] != null) {
                if (this.sel >= 0) {
                    this.st[this.sel]
                            .skipFrameTo(((this.sel < PlayerEquipPanel.this.equipslot) ? 1 : 0));
                }
                this.st[s].skipFrameTo(3.0F);
            }
            this.sel = s;

            PlayerEquipPanel.this.equip.updateEquip(s);
            return true;
        }

        int indexOf(Object src) {
            if (src == null)
                return -1;
            for (int l = 0; l < this.st.length; l++) {
                if (this.st[l] == src)
                    return l;
            }
            return -1;
        }

        public void mousePressed(MouseEvent e) {
            int i = indexOf(e.getSource());
            if (i >= PlayerEquipPanel.this.equipslot)
                return;
            System.out.println("mousePressed i:" + i);
            if (select(i)) {
                PlayerEquipPanel.this.playSound(1029);
            }
        }

        public void mouseEntered(MouseEvent e) {
            int i = indexOf(e.getSource());
            if (this.sel != i) {
                PlayerEquipPanel.this.playSound(1003);
                this.st[i].skipFrameTo(2.0F);
            }
        }

        public void mouseExited(MouseEvent e) {
            int i = indexOf(e.getSource());
            if (this.sel != i) {
                this.st[i].skipFrameTo(((i < PlayerEquipPanel.this.equipslot) ? 1 : 0));
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        ComboBox select = this.equip.l_slot;
        if (src == select) {
            int equipslot = this.ud.items.getEquipSlot();
            int sid = select.getSelectedIndex();
            if (sid >= equipslot) {

                GameSetting gs = GameSetting.getSetting();
                AmpedPopupWindow.smallInfoPopup(gs.getLocalText("$menu.playerequip.help.introduction"),
                        (Component) select, 1);
                sid = 0;
                select.setSelectedIndex(sid);
            }
            this.equip.updateEquip(sid);
        }
    }

    class CharaStockHandler extends MouseAdaptor {
        Container cnt;
        Container[] st;
        int sel;
        ComboBox l_slot;
        PlayerEquipPanel.SlotHandler slot;

        CharaStockHandler() {
            this.st = new Container[9];
            this.sel = -1;
        }

        int sel() {
            return (this.sel >= 0) ? (this.sel % 9) : this.sel;
        }

        void setContainer(Container c) {
            String key;
            this.cnt = c;

            this.l_slot = (ComboBox) PlayerEquipPanel.findNamedComponent("l_slot", this.cnt);
            if (PlayerEquipPanel.this.newSlot == 2) {
                this.l_slot.setVisible(false);
                this.slot = new PlayerEquipPanel.SlotHandler(c);

                key = "e";
            } else {
                key = "st";
                ((Container) PlayerEquipPanel.findNamedComponent("equip", this.cnt)).setVisible(false);
                if (PlayerEquipPanel.this.newSlot == 0) {
                    ((Container) PlayerEquipPanel.findNamedComponent("slot", this.cnt)).skipFrameTo(7.0F);
                    this.l_slot.setVisible(false);
                } else {
                    ((Container) PlayerEquipPanel.findNamedComponent("slot", this.cnt)).skipFrameTo(8.0F);
                    ((Container) PlayerEquipPanel.findNamedComponent("n_slot", this.cnt)).setVisible(false);
                    GameSetting gs = GameSetting.getSetting();
                    ETCItem ei = gs.getETCItem(48);
                    int stockmax = ((int[]) ei.arg)[0];

                    stockmax = Math.min(stockmax,
                            PlayerEquipPanel.this.equipslot + (PlayerEquipPanel.this.saleSlot ? 1 : 0));
                    for (int i = 0; i < stockmax; i++) {
                        this.l_slot
                                .addItem(String.valueOf(gs.getLocalText("$menu.playerequip.label.stock")) + (i + 1));
                    }
                    this.l_slot.setSelectedIndex(0);

                    this.l_slot.addActionListener((ActionListener) this.cnt);
                }
            }

            for (int l = 0; l < this.st.length; l++) {
                Container s = (Container) PlayerEquipPanel.findNamedComponent(String.valueOf(key) + l, c);
                if (s != null) {
                    this.st[l] = s;
                    s.setCursor(KWTManager.getLibrary().getCursor("finger"));
                    s.addMouseListener((MouseListener) this);
                }
            }
            PlayerEquipPanel.this.c_enter = (Container) PlayerEquipPanel.findNamedComponent("c_enter",
                    this.cnt);
            PlayerEquipPanel.this.c_enter.setVisible(false);
        }

        private boolean noAccsSlot(int s) {
            UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(s);
            return !(a != null && a.accs != 0);
        }

        boolean select(int s) {
            if (PlayerEquipPanel.this.newSlot == 2) {
                if (s < 0) {
                    PlayerEquipPanel.this.c_select.setVisible(false);
                    return false;
                }
                this.sel = s + this.slot.sel * 9;
                refreshSelect();
            } else {

                if (s < 0)
                    return false;
                int select_index = 0;
                int as = s;
                if (PlayerEquipPanel.this.newSlot == 1) {
                    select_index = this.l_slot.getSelectedIndex();
                    as += this.l_slot.getSelectedIndex() * 9;
                }
                if (select_index * 9 <= this.sel && this.sel < (select_index + 1) * 9) {
                    this.st[sel()].skipFrameTo(
                            ((PlayerEquipPanel.this.us.getAvatorShortcut(this.sel) == null) ? 0 : 1));
                }
                this.sel = as;
                this.st[s].skipFrameTo(3.0F);
            }
            return true;
        }

        private void updateStock(int _sel, UserShortcuts.AvatorShortcut a) {
            int s = _sel % 9;
            if (this.st[s] != null) {
                if (PlayerEquipPanel.this.newSlot != 2) {
                    this.st[s].skipFrameTo(((a == null) ? 0 : 1));
                    return;
                }
                ImageFigure style = (ImageFigure) PlayerEquipPanel.findNamedFigure("style", this.st[s]);
                boolean b_accs = false;
                if (a != null) {
                    style.setImageName("res:icon/style/" + a.style + ".agi");

                    if (a.accs > 0) {
                        ImageFigure accs = (ImageFigure) PlayerEquipPanel.findNamedFigure("accs", this.st[s]);
                        accs.setImageName("res:icon/accs/" + a.accs + ".agi");

                        b_accs = true;
                    }
                } else {
                    style.setImageName("res:icon/putout.agi");
                    style.setColor(KWTColor.white);
                }
                this.st[s].findComponent("c_accs").setVisible(b_accs);
                if (noAccsSlot(_sel)) {
                    PlayerEquipPanel.this.c_select.play(10.0F, 12.0F, 1.0F, 0);
                } else {
                    PlayerEquipPanel.this.c_select.play(0.0F, 2.0F, 1.0F, 0);
                }

            }
        }

        private void updateEquip(int index_slot) {
            for (int l = 0; l < 9; l++) {
                UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us
                        .getAvatorShortcut(index_slot * 9 + l);
                updateStock(l, a);
            }
            if (PlayerEquipPanel.this.newSlot != 2)
                return;
            int i = getSelectIndex();
            if (i >= 0 && i / 9 == index_slot) {
                this.sel = i;
                refreshSelect();
            } else {
                PlayerEquipPanel.this.c_select.setVisible(false);
            }
        }

        public void refreshSelect() {
            if (noAccsSlot(this.sel)) {
                PlayerEquipPanel.this.c_select.play(10.0F, 12.0F, 1.0F, 0);
            } else {
                PlayerEquipPanel.this.c_select.play(0.0F, 2.0F, 1.0F, 0);
            }
            PlayerEquipPanel.this.c_select.p.y++;
        }

        private int getSelectIndex() {
            int size = PlayerEquipPanel.this.us.getAvatorShortcutSize();
            for (int i = 0; i < size; i++) {
                UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(i);
                if (a != null &&
                        a.style == PlayerEquipPanel.this.ui.style &&
                        a.head == PlayerEquipPanel.this.ui.head &&
                        a.body == PlayerEquipPanel.this.ui.body &&
                        a.build == PlayerEquipPanel.this.ui.build &&
                        a.face == PlayerEquipPanel.this.ui.face &&
                        a.deco == PlayerEquipPanel.this.ui.deco &&
                        a.skin == PlayerEquipPanel.this.ui.skin &&
                        a.accs == PlayerEquipPanel.this.ui.accs &&
                        a.weapon == PlayerEquipPanel.this.ui.weapon &&
                        a.voice == PlayerEquipPanel.this.ui.voice) {
                    return i;
                }
            }
            return -1;
        }

        void updateStocks() {
            if (PlayerEquipPanel.this.newSlot == 2)
                PlayerEquipPanel.this.c_select = (Container) PlayerEquipPanel.findNamedComponent("c_select", this.cnt);

            int sssize = PlayerEquipPanel.this.us.getAvatorShortcutSize();
            for (int i = 0; i < sssize; i++) {
                UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(i);
                if (a != null) {
                    if (a.checkLostItems(PlayerEquipPanel.this.ud.items)) {
                        PlayerEquipPanel.this.us.setAvatorShortcut(i, null);
                    } else {

                        a.validated(PlayerEquipPanel.this.ud.items);

                        if (a.weapon != 0) {
                            UserItems.Item weapon = PlayerEquipPanel.this.ud.items.getItem(7, a.weapon);
                            if (weapon == null || weapon.num == 0)
                                a.weapon = 0;
                        }
                    }
                }
            }
            int _sel = getSelectIndex();
            if (PlayerEquipPanel.this.newSlot == 2) {
                int index_slot = this.slot.update(_sel);
                this.slot.select(index_slot);
            } else {
                int index_slot = 0;
                if (PlayerEquipPanel.this.newSlot == 1 && _sel >= 9) {
                    index_slot = _sel / 9;
                    this.l_slot.setSelectedIndex(index_slot);
                }
                updateEquip(index_slot);
            }

            this.sel = -1;
            select(_sel % 9);
        }

        void storeStock() {
            boolean callUpdate = false;
            if (PlayerEquipPanel.this.newSlot == 1 || PlayerEquipPanel.this.newSlot == 2) {
                int s;
                if (PlayerEquipPanel.this.newSlot == 1) {
                    s = this.l_slot.getSelectedIndex();
                } else {
                    s = this.slot.sel;
                }
                if (this.sel < s * 9 || this.sel >= (s + 1) * 9)
                    return;
            }
            UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(this.sel);
            if (a == null) {
                a = new UserShortcuts.AvatorShortcut();
                PlayerEquipPanel.this.us.setAvatorShortcut(this.sel, a);
                callUpdate = true;
            }
            if (a.style != PlayerEquipPanel.this.ui.style) {
                a.style = PlayerEquipPanel.this.ui.style;
                callUpdate = true;
            }
            a.head = PlayerEquipPanel.this.ui.head;
            a.body = PlayerEquipPanel.this.ui.body;
            a.face = PlayerEquipPanel.this.ui.face;
            a.deco = PlayerEquipPanel.this.ui.deco;
            a.skin = PlayerEquipPanel.this.ui.skin;
            if (a.accs != PlayerEquipPanel.this.ui.accs) {
                a.accs = PlayerEquipPanel.this.ui.accs;
                callUpdate = true;
            }
            a.weapon = PlayerEquipPanel.this.ui.weapon;
            a.voice = (short) PlayerEquipPanel.this.ui.voice;
            a.build = PlayerEquipPanel.this.ui.build;
            if (PlayerEquipPanel.this.newSlot == 2 && callUpdate) {
                updateStock(this.sel, a);
            }
        }

        void setStock() {
            UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(this.sel);
            if (a == null)
                return;
            PlayerEquipPanel.this.ui = a.validated(PlayerEquipPanel.this.ud.items).apply(PlayerEquipPanel.this.ui);

            StyleData style = GameSetting.getSetting().getStyle(a.style);
            PlayerEquipPanel.this.ui.stylelv = style
                    .getLevel(PlayerEquipPanel.this.ud.rec.getStyleExp(style.group));

            UserItems.Item item = PlayerEquipPanel.this.ud.items.getItem(5, a.accs);
            if (item != null) {
                PlayerEquipPanel.this.ui.skill0 = (short) item.getPotentialSkill();
                PlayerEquipPanel.this.ui.skill0_pow = (byte) item.getPotentialSkillPow();
                PlayerEquipPanel.this.ui.skill1 = (short) item.getTuneUp(0);
                PlayerEquipPanel.this.ui.skill2 = (short) item.getTuneUp(1);
                PlayerEquipPanel.this.ui.skill3 = (short) item.getTuneUp(2);
            } else {
                PlayerEquipPanel.this.ui.skill0 = 0;
                PlayerEquipPanel.this.ui.skill0_pow = 0;
                PlayerEquipPanel.this.ui.skill1 = 0;
                PlayerEquipPanel.this.ui.skill2 = 0;
                PlayerEquipPanel.this.ui.skill3 = 0;
            }
            PlayerEquipPanel.this.us.selavator = this.sel;
            synchronized (KWTManager.PAINT_LOCK) {
                PlayerEquipPanel.this.updateUserInfo();
            }
        }

        int indexOf(Object src) {
            if (src == null)
                return -1;
            for (int l = 0; l < this.st.length; l++) {
                if (this.st[l] == src)
                    return l;
            }
            return -1;
        }

        public void mousePressed(MouseEvent e) {
            if (select(indexOf(e.getSource()))) {
                PlayerEquipPanel.this.playSound(1029);
                setStock();
            }
        }

        public void mouseEntered(MouseEvent e) {
            int i = indexOf(e.getSource());
            int is = i;
            if (PlayerEquipPanel.this.newSlot == 2) {
                is += this.slot.sel * 9;
                if (noAccsSlot(is)) {
                    PlayerEquipPanel.this.c_enter.skipFrameTo(10.0F);
                } else {
                    PlayerEquipPanel.this.c_enter.skipFrameTo(0.0F);
                }
                PlayerEquipPanel.this.c_enter.p.y = (26 * i);
                PlayerEquipPanel.this.c_enter.updateMatrix();
                PlayerEquipPanel.this.c_enter.setVisible(true);
            }
            if (PlayerEquipPanel.this.newSlot == 1)
                is += this.l_slot.getSelectedIndex() * 9;
            if (this.sel != is) {
                PlayerEquipPanel.this.playSound(1003);
                if (PlayerEquipPanel.this.newSlot < 2)
                    this.st[i].skipFrameTo(2.0F);
            }
        }

        public void mouseExited(MouseEvent e) {
            if (PlayerEquipPanel.this.newSlot == 2) {
                PlayerEquipPanel.this.c_enter.setVisible(false);
            } else {
                int i = indexOf(e.getSource());
                int as = i;
                if (PlayerEquipPanel.this.newSlot == 1)
                    as += this.l_slot.getSelectedIndex() * 9;
                if (this.sel != as) {
                    this.st[i]
                            .skipFrameTo(((PlayerEquipPanel.this.us.getAvatorShortcut(as) == null) ? 0 : 1));
                }
            }
        }
    }

    public boolean isControllerTarget(Component child) {
        int i;
        for (i = 0; i < this.status.skills.length; i++) {
            if ((this.status.skills[i]).cnt == child && (this.status.skills[i]).label.getText().length() > 0)
                return true;
        }
        for (i = 0; i < this.equip.st.length; i++) {
            if (this.equip.st[i] == child)
                return true;
        }
        return false;
    }
}

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\clien\\ui\
 * PlayerEquipPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
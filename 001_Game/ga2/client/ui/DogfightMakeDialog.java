
package ga2.client.ui;

import ga2.client.GetAmped2;
import ga2.client.LookAndFeel;
import ga2.data.TournamentData;
import ga2.data.net.GameRoomInfo;
import ga2.laf.amped2.AmpedCheckbox2;
import ga2.laf.amped2.AmpedDialog;
import ga2.laf.amped2.NumberCounter;
import ga2.setting.GameSetting;
import ga2.stage.StageConnector;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotori.geom.Rect2D;
import kotori.geom.Vector2D;
import kotori.kwt.CheckboxGroup;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.ContainerData;
import kotori.kwt.ImageFigure;
import kotori.kwt.KWTManager;
import kotori.kwt.KWTStruct;
import kotori.kwt.RichTextFigure;
import kotori.kwt.ScrollPane;
import kotori.kwt.TextFigure;
import kotori.kwt.TextInput;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.ActionListener;
import kotori.kwt.event.MouseEvent;
import kotori.kwt.event.MouseListener;
import kotori.kwt.event.TextEvent;
import kotori.kwt.event.TextListener;
import kotori.util.Clock;
import kotori.util.KotoriUtil;

public class DogfightMakeDialog
        extends Container {
    int[] tourid;
    KWTStruct srt;
    TournamentData[] tourlist;
    StageSelect stageselect;
    ModeSelect modeselect;
    TextInput roomname;
    TextInput password;
    int pwdhash;
    AmpedCheckbox2 formula;
    Container create;
    Container cancel;

    public DogfightMakeDialog(StageConnector con, boolean changeable, boolean isMatchingRoom) {
        this(con, changeable, isMatchingRoom, false);
    }

    int selecttouridx;
    int stageid;
    NumberCounter num;
    NumberCounter roundCount;
    Container viewerpanel;
    AmpedCheckbox2 vieweron;
    AmpedCheckbox2 vieweroff;
    Container vchatpanel;
    AmpedCheckbox2 vchatall;
    AmpedCheckbox2 vchatteam;
    boolean traininggat;
    boolean navimode;
    public GameRoomInfo result;

    public DogfightMakeDialog(StageConnector con, boolean changeable, boolean isMatchingRoom, boolean navi) {
        this.srt = LookAndFeel.getLAF().getKWTStruct("kwtdocs/dlg/dogmake.kwt");
        setStruct(this.srt);
        this.bnd.set(this.srt.bnd);
        if (isMatchingRoom) {
            String title = GameSetting.getSetting().getLocalText("$menu.tourmake.title2");
            ((TextFigure) findNamedFigure("title", this)).setText(title);
        }

        setModeList(con);

        this.navimode = navi;

        this.modeselect = new ModeSelect();
        this.modeselect.setContainer((Container) findNamedComponent("modeselect", this));
        this.stageselect = new StageSelect();
        this.stageselect.setContainer((Container) findNamedComponent("stageselect", this));

        this.roomname = (TextInput) findNamedComponent("roomname", this);
        this.roomname.setMaxLen(16);
        this.password = (TextInput) findNamedComponent("password", this);
        this.password.addTextListener((TextListener) this);
        this.password.setMaxLen(12);

        if ((GameSetting.getSetting()).tourkeyroomrank != -1 &&
                ((con.getUserData()).info.getRank(true)).idx < (GameSetting.getSetting()).tourkeyroomrank &&
                !(con.getUserData()).info.isGM() &&
                !(con.getUserData()).info.isGMAssistant()) {
            this.password.setVisible(false);
            this.password.setEnabled(false);
        }

        (this.cancel = (Container) findNamedComponent("cancel", this))
                .addActionListener((ActionListener) this);
        (this.create = (Container) findNamedComponent("create", this))
                .addActionListener((ActionListener) this);

        this.tourlist = (GameSetting.getSetting()).tour;

        this.modeselect.updateList();
        this.stageselect.updateList();
        this.roomname.setText(con.getUserData().info.name);

        if (((con.getUserData()).restrict & 0x20) != 0) {
            this.create.setEnabled(false);
        }

        this.formula = (AmpedCheckbox2) findNamedComponent("formula", this);
        if (!(con.getUserData()).info.isGM() && !(con.getUserData()).info.isGMAssistant()) {
            this.formula.setVisible(false);
        }

        this.num = new NumberCounter();
        this.num.setContainer((Container) findNamedComponent("num", this), 8);
        this.num.inc.addActionListener((ActionListener) this);
        this.num.dec.addActionListener((ActionListener) this);
        this.num.setRange(this.tourlist[this.tourid[this.selecttouridx]]);
        this.num.setValue((this.tourlist[this.tourid[this.selecttouridx]]).maxplayers);

        this.roundCount = new NumberCounter();
        this.roundCount.setContainer((Container) findNamedComponent("roundCount", this), 99);
        this.roundCount.inc.addActionListener((ActionListener) this);
        this.roundCount.dec.addActionListener((ActionListener) this);
        this.roundCount.setRange(1, 99);
        this.roundCount.setValue(3);

        this.viewerpanel = (Container) findNamedComponent("viewerpanel", this);
        this.vieweron = (AmpedCheckbox2) findNamedComponent("vieweron", this);
        this.vieweroff = (AmpedCheckbox2) findNamedComponent("vieweroff", this);
        if ((GameSetting.getSetting()).observerenabled && !(GameSetting.getSetting()).observergmonly) {
            CheckboxGroup vg = new CheckboxGroup();
            this.vieweron.setCheckboxGroup(vg);
            this.vieweroff.setCheckboxGroup(vg);
            this.vieweron.setSelected(true);
        } else {
            this.viewerpanel.setVisible(false);
        }

        this.vchatpanel = (Container) findNamedComponent("voicechatpanel", this);
        this.vchatall = (AmpedCheckbox2) findNamedComponent("vchatall", this.vchatpanel);
        this.vchatteam = (AmpedCheckbox2) findNamedComponent("vchatteam", this.vchatpanel);
        if ((GameSetting.getSetting()).voicechatenabled) {
            CheckboxGroup vg = new CheckboxGroup();
            this.vchatall.setCheckboxGroup(vg);
            this.vchatteam.setCheckboxGroup(vg);
            this.vchatall.setSelected(true);
            this.vchatpanel.setEnabled(false);
        } else {
            this.vchatpanel.setVisible(false);
        }

        if (!changeable) {
            this.modeselect.cnt.setEnabled(false);
            this.stageselect.cnt.setEnabled(false);
            this.roomname.setEnabled(false);
            this.password.setEnabled(false);
            this.num.cnt.setEnabled(false);
            this.create.setEnabled(false);
            this.viewerpanel.setEnabled(false);
            this.vchatpanel.setEnabled(false);
        }

        GameSetting set = GameSetting.getSetting();
        GetAmped2 ga2 = GetAmped2.getAmped2();
        if (ga2 != null && ga2.isLanPlay() && set.lanplayenabled) {
            this.roomname.setEnabled(false);
            this.password.setEnabled(false);
            this.num.cnt.setEnabled(false);
            this.viewerpanel.setVisible(false);
            this.vchatpanel.setVisible(false);
        }

        performMethod("show");
    }

    private int getModeIdx(int id) {
        for (int i = 0; i < this.tourid.length; i++) {
            if (id == this.tourid[i])
                return i;
        }
        return 0;
    }

    private void setModeList(StageConnector con) {
        GameRoomInfo gri = (con.getStage()).roominfo;
        ArrayList<Integer> a = new ArrayList<Integer>();
        if (gri == null || !gri.isTrainingRoom()) {
            a.add(Integer.valueOf(0));
            a.add(Integer.valueOf(1));
        } else {
            a.add(Integer.valueOf(3));
            a.add(Integer.valueOf(4));
        }
        if ((GameSetting.getSetting()).dogfightenabled) {
            GetAmped2 ga2 = GetAmped2.getAmped2();
            if (!(con.getStage()).demo && (gri == null || !gri.isTrainingRoom()) && ga2 != null
                    && !ga2.isLanPlay()) {
                a.add(Integer.valueOf(8));
                a.add(Integer.valueOf(9));
            }
        }
        this.tourid = new int[a.size()];
        for (int i = 0; i < a.size();) {
            this.tourid[i] = ((Integer) a.get(i)).intValue();
            i++;
        }

    }

    public void setRoomInfo(GameRoomInfo i) {
        this.traininggat = i.isTrainingRoom();
        if (this.traininggat) {
            this.roomname.setEnabled(false);
            this.password.setEnabled(false);
            this.num.cnt.setEnabled(false);
            this.viewerpanel.setVisible(false);
            this.vchatpanel.setVisible(false);
        }

        this.roomname.setText(i.name);
        this.password.setText((i.pwdhash == 0) ? "" : "*");
        this.pwdhash = i.pwdhash;

        this.selecttouridx = getModeIdx(i.gameid);
        this.modeselect.select(this.selecttouridx);
        this.modeselect.updateList();

        this.num.setRange(this.tourlist[this.tourid[this.selecttouridx]]);
        this.num.setValue(i.maxnum);

        this.roundCount.setValue(Integer
                .valueOf(com.nr.tool.LocalStorage.getInstance()
                        .get("Round - " + String.valueOf(this.tourid[this.selecttouridx]))));

        this.stageid = 0;
        this.stageselect.updateList();
        this.stageselect.select(i.subid);

        if (i.grade == (GameSetting.getSetting().getFormulaRank()).idx) {
            this.formula.setSelected(true);
        }
        this.formula.setMouseAccessible(false);

        if ((i.flags & 0x1) != 0) {
            this.vieweron.setSelected(true);
        } else {
            this.vieweroff.setSelected(true);
        }

        if ((i.flags & 0x2) != 0) {
            this.vchatpanel.setEnabled(true);
            this.vchatteam.setSelected(true);
        } else {
            this.vchatpanel.setEnabled(false);
            this.vchatall.setSelected(true);
        }
    }

    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == this.cancel) {

            if (this.navimode && (e == null || !(e.getArgument() instanceof Integer)
                    || ((Integer) e.getArgument()).intValue() != 1))
                return;
            playSound(1010);
            this.result = null;
            close();
        } else if (src == this.create) {
            if (this.navimode)
                return;
            GetAmped2 ga2 = GetAmped2.getAmped2();
            if (ga2 != null && ((ga2.getUserData()).restrict & 0x20) != 0)
                return;
            if (GameSetting.getSetting().isLanguage("kr"))
                this.roomname.decideImText();

            playSound(1004);
            this.create.performMethodAndWait("blink");
            this.result = new GameRoomInfo();
            this.result.maxnum = this.num.val;
            com.nr.tool.LocalStorage.getInstance().set("Round - " + String.valueOf(this.result.gameid),
                    String.valueOf(this.roundCount.val));
            this.result.gameid = this.tourid[this.selecttouridx];
            this.result.subid = this.stageid;
            this.result.name = this.roomname.getText();
            this.result.pwdhash = this.pwdhash;
            if (ga2 != null)
                this.result.setGrade(ga2.getUserInfo(), this.formula.isSelected(), this.traininggat);

            if (this.vieweron.isSelected()) {
                this.result.flags |= 0x1;
            } else {
                this.result.flags &= 0xFFFFFFFE;
            }

            if (this.vchatteam.isSelected()) {
                this.result.flags |= 0x2;
            }

            close();
        } else if (src == this.num.inc) {
            this.num.incValue();
        } else if (src == this.num.dec) {
            this.num.decValue();
        } else if (src == this.roundCount.inc) {
            this.roundCount.incValue();
        } else if (src == this.roundCount.dec) {
            this.roundCount.decValue();
        }
    }

    void close() {
        Container container = getParent();
        if (container instanceof AmpedDialog) {
            ((AmpedDialog) container).end();
        }
    }

    public void textValueChanged(TextEvent e) {
        Object src = e.getSource();

        if (src == this.password) {
            String s = this.password.getText();
            this.pwdhash = (s.length() == 0) ? 0 : KotoriUtil.getNameHash(s);
        }
    }

    class StageSelect {
        Container cnt;

        DogfightMakeDialog.StageList list;

        ScrollPane scroll;
        TextFigure name;
        RichTextFigure desc;
        DogfightMakeDialog.Stage[] stage;

        void setContainer(Container c) {
            this.cnt = c;
            this.scroll = (ScrollPane) DogfightMakeDialog.findNamedComponent("scroll", c);
            this.list = new DogfightMakeDialog.StageList(this.scroll.getViewportSize(new Vector2D()));
            this.scroll.setContent((Component) this.list);

            this.name = (TextFigure) DogfightMakeDialog.findNamedFigure("name", c);
            this.desc = (RichTextFigure) DogfightMakeDialog.findNamedFigure("desc", c);
        }

        void updateList() {
            TournamentData td = GameSetting.getSetting()
                    .getTournament(DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx]);
            this.list.clear();
            this.list.curs = 0;
            this.stage = new DogfightMakeDialog.Stage[td.numStage()];
            for (int l = 0; l < td.numStage(); l++) {
                this.stage[l] = this.list.addStage(td.getStage(l));
            }
            this.list.revalidate();
            select(DogfightMakeDialog.this.stageid);
        }

        void select(int i) {
            if (DogfightMakeDialog.this.stageid >= 0) {
                this.stage[DogfightMakeDialog.this.stageid].setSelected(false);
            }
            this.stage[i].setSelected(true);
            DogfightMakeDialog.this.stageid = i;

            TournamentData.Stage s = (this.stage[DogfightMakeDialog.this.stageid]).ts;
            this.name.setText(s.name);
            this.desc.setText(s.desc);

            Rect2D r = new Rect2D((this.stage[DogfightMakeDialog.this.stageid]).bnd);
            r.y -= 3.0F;
            r.h += 6.0F;
            this.list.scrollRectToVisible(r);
        }
    }

    class StageList extends Container {
        ContainerData stagecd;
        int curs;
        Vector2D vp;
        float sw;
        float sh;
        int cpl;

        StageList(Vector2D v) {
            this.vp = v;
            this.stagecd = DogfightMakeDialog.this.srt.getLibrary().getPanel("e:stage");
            this.sw = this.stagecd.bnd.w;
            this.sh = this.stagecd.bnd.h;
            this.cpl = (int) (v.x / this.sw);
        }

        DogfightMakeDialog.Stage addStage(TournamentData.Stage s) {
            if (s != null) {
                float x = (this.curs % this.cpl) * this.sw;
                float y = (this.curs / this.cpl) * this.sh;
                DogfightMakeDialog.Stage stage = new DogfightMakeDialog.Stage(this.stagecd);
                stage.setStage(this.curs, s);
                stage.setRect(3.0F + x, 3.0F + y, this.sw, this.sh);
                add((Component) stage);
                this.curs++;
                return stage;
            }
            this.curs++;
            return null;
        }

        public Vector2D getPreferredSize(Vector2D buf) {
            buf.x = this.bnd.w;
            buf.y = 6.0F + ((this.curs - 1) / this.cpl + 1) * this.sh;
            return buf;
        }
    }

    class Stage
            extends Container {
        ImageFigure preview;

        int idx;
        TournamentData.Stage ts;
        int ridx;
        int nexttime;

        Stage(ContainerData cd) {
            setContainerData(cd, DogfightMakeDialog.this.srt.getLibrary());
            this.preview = (ImageFigure) findNamedFigure("preview", this);
            addMouseListener((MouseListener) this);
        }

        void setStage(int i, TournamentData.Stage s) {
            this.idx = i;
            this.ts = s;
            if (s != null) {
                this.preview.setVisible(true);
                this.preview.setImageName(s.getPreviewPath());
                setCursor(KWTManager.getLibrary().getCursor("finger"));
            } else {
                this.preview.setVisible(false);
                setCursor(null);
            }
            this.nexttime = Clock.time() + 100;
        }

        void setSelected(boolean b) {
            if (b) {
                play(0.0F, 1.0F, 10.0F, 1);
            } else {
                play(1.0F, 0.0F, 10.0F, 1);
            }

        }

        boolean isSelected() {
            return (this.idx == DogfightMakeDialog.this.stageid);
        }

        public void mouseEntered(MouseEvent e) {
            if (this.ts == null)
                return;
            if (!isSelected())
                setSelected(true);
            playSound(1002);
        }

        public void mouseExited(MouseEvent e) {
            if (this.ts == null)
                return;
            if (!isSelected())
                setSelected(false);
        }

        public void mousePressed(MouseEvent e) {
            if (this.ts == null)
                return;
            if (!isSelected())
                DogfightMakeDialog.this.stageselect.select(this.idx);
            playSound(1029);
        }

        public void updateFrame() {
            super.updateFrame();
            if (this.ts != null && this.ts.randomstage != null) {
                int t = Clock.time();
                if (t >= this.nexttime) {
                    TournamentData td = GameSetting.getSetting()
                            .getTournament(DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx]);
                    this.ridx = (this.ridx + 1) % this.ts.randomstage.length;
                    TournamentData.Stage s = td.getStageDirect(this.ts.randomstage[this.ridx]);
                    this.preview.setImageName(s.getPreviewPath());
                    this.nexttime = t + 100;
                }
            }
        }

        public boolean isControllerTarget() {
            return true;
        }
    }

    class ModeSelect {
        Container cnt;
        DogfightMakeDialog.ModeList list;
        ScrollPane scroll;
        TextFigure name;
        RichTextFigure desc;
        DogfightMakeDialog.Mode[] mode;

        void setContainer(Container c) {
            this.cnt = c;
            this.scroll = (ScrollPane) DogfightMakeDialog.findNamedComponent("scroll", c);
            this.list = new DogfightMakeDialog.ModeList(this.scroll.getViewportSize(new Vector2D()));
            this.scroll.setContent((Component) this.list);
            this.name = (TextFigure) DogfightMakeDialog.findNamedFigure("name", c);
            this.desc = (RichTextFigure) DogfightMakeDialog.findNamedFigure("desc", c);
        }

        void updateList() {
            this.list.clear();
            this.list.curs = 0;
            this.mode = new DogfightMakeDialog.Mode[DogfightMakeDialog.this.tourid.length];
            for (int i = 0; i < this.mode.length; i++) {
                TournamentData td = GameSetting.getSetting().getTournament(DogfightMakeDialog.this.tourid[i]);
                this.mode[i] = this.list.addMode(td);
            }
            this.list.revalidate();
        }

        void select(int i) {
            if (DogfightMakeDialog.this.selecttouridx >= 0)
                this.mode[DogfightMakeDialog.this.selecttouridx].setSelected(false);
            DogfightMakeDialog.this.selecttouridx = i;
            this.mode[DogfightMakeDialog.this.selecttouridx].setSelected(true);
            DogfightMakeDialog.this.stageid = 0;
            DogfightMakeDialog.this.stageselect.updateList();
            DogfightMakeDialog.this.num.setRange((this.mode[DogfightMakeDialog.this.selecttouridx]).td);
            DogfightMakeDialog.this.num
                    .setValue((this.mode[DogfightMakeDialog.this.selecttouridx]).td.maxplayers);
            DogfightMakeDialog.this.vchatpanel
                    .setEnabled((DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx] != 0
                            && DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx] != 8));

            Rect2D r = new Rect2D((this.mode[DogfightMakeDialog.this.selecttouridx]).bnd);

            this.list.scrollRectToVisible(r);
        }
    }

    class ModeList extends Container {
        ContainerData modecd;
        int curs;
        Vector2D vp;
        float sw;
        float sh;
        int cpl;

        ModeList(Vector2D v) {
            this.vp = v;
            this.modecd = DogfightMakeDialog.this.srt.getLibrary().getPanel("e:mode");
            this.sw = this.modecd.bnd.w;
            this.sh = this.modecd.bnd.h;
            this.cpl = (int) (v.x / this.sw);
        }

        DogfightMakeDialog.Mode addMode(TournamentData td) {
            if (td != null) {
                float x = (this.curs % this.cpl) * this.sw;
                float y = (this.curs / this.cpl) * this.sh;
                DogfightMakeDialog.Mode mode = new DogfightMakeDialog.Mode(this.modecd);
                mode.setMode(this.curs, td);
                mode.setRect(3.0F + x, y, this.sw, this.sh);
                add((Component) mode);
                this.curs++;
                return mode;
            }
            this.curs++;
            return null;
        }

        public Vector2D getPreferredSize(Vector2D buf) {
            buf.x = this.bnd.w;
            buf.y = 6.0F + ((this.curs - 1) / this.cpl + 1) * this.sh;
            return buf;
        }
    }

    class Mode
            extends Container {
        TournamentData td;
        int idx;
        TextFigure name;
        Container inv;
        Container team;

        Mode(ContainerData cd) {
            setContainerData(cd, DogfightMakeDialog.this.srt.getLibrary());
            this.name = (TextFigure) findNamedFigure("name", this);
            this.inv = (Container) findNamedComponent("c_inv", this);
            this.team = (Container) findNamedComponent("c_team", this);
            addMouseListener((MouseListener) this);

            setControlPosOffset(0, 2);
        }

        public boolean isControllerTarget() {
            return true;
        }

        void setMode(int i, TournamentData _td) {
            this.idx = i;
            this.td = _td;
            if (_td != null) {
                this.name.setText(this.td.name);
                this.name.reduceStrWidth();
                if (this.td.desc != null && !this.td.desc.equals(""))
                    (this.td.teambattle ? this.team : this.inv).setHelpValue(this.td.desc);
                skipFrameTo((this.td.teambattle ? 1 : 0));
                this.inv.setVisible(!this.td.teambattle);
                this.team.setVisible(this.td.teambattle);
                setSelected((this.idx == DogfightMakeDialog.this.selecttouridx));
                setCursor(KWTManager.getLibrary().getCursor("finger"));
            } else {
                this.name.setText("");
                this.inv.setVisible(false);
                this.team.setVisible(false);
                setCursor(null);
            }
        }

        void setSelected(boolean b) {
            Container c = this.td.teambattle ? this.team : this.inv;
            c.play(c.getFrame(), (!b ? 2 : (isSelected() ? 0 : 1)), 10.0F, 1);
        }

        boolean isSelected() {
            return (DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx] == DogfightMakeDialog.this.tourid[this.idx]);
        }

        public void mouseEntered(MouseEvent e) {
            if (this.td == null)
                return;
            if (!isSelected())
                setSelected(true);
            playSound(1002);
        }

        public void mouseExited(MouseEvent e) {
            if (this.td == null)
                return;
            if (!isSelected())
                setSelected(false);
        }

        public void mousePressed(MouseEvent e) {
            if (this.td == null)
                return;
            if (!isSelected()) {
                playSound(1029);
                DogfightMakeDialog.this.modeselect.select(this.idx);
            }
        }

        public void updateFrame() {
            super.updateFrame();
        }
    }
}

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\clien\\ui\
 * DogfightMakeDialog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
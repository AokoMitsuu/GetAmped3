
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
        /* 58 */ this(con, changeable, isMatchingRoom, false);
    }

    int selecttouridx;
    int stageid;
    NumberCounter num;
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
        /* 62 */ this.srt = LookAndFeel.getLAF().getKWTStruct("kwtdocs/dlg/dogmake.kwt");
        /* 63 */ setStruct(this.srt);
        /* 64 */ this.bnd.set(this.srt.bnd);

        /* 66 */ if (isMatchingRoom) {
            /* 67 */ String title = GameSetting.getSetting().getLocalText("$menu.tourmake.title2");
            /* 68 */ ((TextFigure) findNamedFigure("title", this)).setText(title);
        }

        /* 71 */ setModeList(con);

        /* 73 */ this.navimode = navi;

        /* 75 */ this.modeselect = new ModeSelect();
        /* 76 */ this.modeselect.setContainer((Container) findNamedComponent("modeselect", this));
        /* 77 */ this.stageselect = new StageSelect();
        /* 78 */ this.stageselect.setContainer((Container) findNamedComponent("stageselect", this));

        /* 80 */ this.roomname = (TextInput) findNamedComponent("roomname", this);
        /* 81 */ this.roomname.setMaxLen(16);
        /* 82 */ this.password = (TextInput) findNamedComponent("password", this);
        /* 83 */ this.password.addTextListener((TextListener) this);
        /* 84 */ this.password.setMaxLen(12);

        /* 87 */ if ((GameSetting.getSetting()).tourkeyroomrank != -1 &&
        /* 88 */ ((con.getUserData()).info.getRank(true)).idx < (GameSetting.getSetting()).tourkeyroomrank &&
        /* 89 */ !(con.getUserData()).info.isGM() &&
        /* 90 */ !(con.getUserData()).info.isGMAssistant()) {
            /* 91 */ this.password.setVisible(false);
            /* 92 */ this.password.setEnabled(false);
        }

        /* 95 */ (this.cancel = (Container) findNamedComponent("cancel", this))
                .addActionListener((ActionListener) this);
        /* 96 */ (this.create = (Container) findNamedComponent("create", this))
                .addActionListener((ActionListener) this);

        /* 99 */ this.tourlist = (GameSetting.getSetting()).tour;

        /* 102 */ this.modeselect.updateList();
        /* 103 */ this.stageselect.updateList();
        /* 105 */ this.roomname.setText(con.getUserData().info.name + " Round(3)");

        /* 108 */ if (((con.getUserData()).restrict & 0x20) != 0) {
            /* 109 */ this.create.setEnabled(false);
        }

        /* 113 */ this.formula = (AmpedCheckbox2) findNamedComponent("formula", this);
        /* 114 */ if (!(con.getUserData()).info.isGM() && !(con.getUserData()).info.isGMAssistant()) {
            /* 115 */ this.formula.setVisible(false);
        }

        /* 119 */ this.num = new NumberCounter();
        /* 120 */ this.num.setContainer((Container) findNamedComponent("num", this), 9);
        /* 121 */ this.num.inc.addActionListener((ActionListener) this);
        /* 122 */ this.num.dec.addActionListener((ActionListener) this);
        /* 123 */ this.num.setRange(this.tourlist[this.tourid[this.selecttouridx]]);
        /* 124 */ this.num.setValue((this.tourlist[this.tourid[this.selecttouridx]]).maxplayers);

        /* 127 */ this.viewerpanel = (Container) findNamedComponent("viewerpanel", this);
        /* 128 */ this.vieweron = (AmpedCheckbox2) findNamedComponent("vieweron", this);
        /* 129 */ this.vieweroff = (AmpedCheckbox2) findNamedComponent("vieweroff", this);
        /* 130 */ if ((GameSetting.getSetting()).observerenabled && !(GameSetting.getSetting()).observergmonly) {
            /* 131 */ CheckboxGroup vg = new CheckboxGroup();
            /* 132 */ this.vieweron.setCheckboxGroup(vg);
            /* 133 */ this.vieweroff.setCheckboxGroup(vg);
            /* 134 */ this.vieweron.setSelected(true);
        } else {
            /* 136 */ this.viewerpanel.setVisible(false);
        }

        /* 139 */ this.vchatpanel = (Container) findNamedComponent("voicechatpanel", this);
        /* 140 */ this.vchatall = (AmpedCheckbox2) findNamedComponent("vchatall", this.vchatpanel);
        /* 141 */ this.vchatteam = (AmpedCheckbox2) findNamedComponent("vchatteam", this.vchatpanel);
        /* 142 */ if ((GameSetting.getSetting()).voicechatenabled) {
            /* 143 */ CheckboxGroup vg = new CheckboxGroup();
            /* 144 */ this.vchatall.setCheckboxGroup(vg);
            /* 145 */ this.vchatteam.setCheckboxGroup(vg);
            /* 146 */ this.vchatall.setSelected(true);
            /* 147 */ this.vchatpanel.setEnabled(false);
        } else {
            /* 149 */ this.vchatpanel.setVisible(false);
        }

        /* 153 */ if (!changeable) {
            /* 154 */ this.modeselect.cnt.setEnabled(false);
            /* 155 */ this.stageselect.cnt.setEnabled(false);
            /* 156 */ this.roomname.setEnabled(false);
            /* 157 */ this.password.setEnabled(false);
            /* 158 */ this.num.cnt.setEnabled(false);
            /* 159 */ this.create.setEnabled(false);
            /* 160 */ this.viewerpanel.setEnabled(false);
            /* 161 */ this.vchatpanel.setEnabled(false);
        }

        /* 164 */ GameSetting set = GameSetting.getSetting();
        /* 165 */ GetAmped2 ga2 = GetAmped2.getAmped2();
        /* 166 */ if (ga2 != null && ga2.isLanPlay() && set.lanplayenabled) {
            /* 167 */ this.roomname.setEnabled(false);
            /* 168 */ this.password.setEnabled(false);
            /* 169 */ this.num.cnt.setEnabled(false);
            /* 170 */ this.viewerpanel.setVisible(false);
            /* 171 */ this.vchatpanel.setVisible(false);
        }

        /* 174 */ performMethod("show");
    }

    private int getModeIdx(int id) {
        /* 178 */ for (int i = 0; i < this.tourid.length; i++) {
            /* 179 */ if (id == this.tourid[i])
                return i;
        }
        /* 181 */ return 0;
    }

    private void setModeList(StageConnector con) {
        /* 188 */ GameRoomInfo gri = (con.getStage()).roominfo;
        /* 189 */ ArrayList<Integer> a = new ArrayList<Integer>();
        /* 190 */ if (gri == null || !gri.isTrainingRoom()) {
            /* 191 */ a.add(Integer.valueOf(0));
            /* 192 */ a.add(Integer.valueOf(1));
        } else {
            /* 194 */ a.add(Integer.valueOf(3));
            /* 195 */ a.add(Integer.valueOf(4));
        }
        /* 197 */ if ((GameSetting.getSetting()).dogfightenabled) {
            /* 198 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 199 */ if (!(con.getStage()).demo && (gri == null || !gri.isTrainingRoom()) && ga2 != null
                    && !ga2.isLanPlay()) {
                /* 200 */ a.add(Integer.valueOf(8));
                /* 201 */ a.add(Integer.valueOf(9));
            }
        }
        /* 204 */ this.tourid = new int[a.size()];
        /* 205 */ for (int i = 0; i < a.size();) {
            this.tourid[i] = ((Integer) a.get(i)).intValue();
            i++;
        }

    }

    public void setRoomInfo(GameRoomInfo i) {
        /* 209 */ this.traininggat = i.isTrainingRoom();
        /* 210 */ if (this.traininggat) {
            /* 211 */ this.roomname.setEnabled(false);
            /* 212 */ this.password.setEnabled(false);
            /* 213 */ this.num.cnt.setEnabled(false);
            /* 214 */ this.viewerpanel.setVisible(false);
            /* 215 */ this.vchatpanel.setVisible(false);
        }

        /* 218 */ this.roomname.setText(i.name);
        /* 219 */ this.password.setText((i.pwdhash == 0) ? "" : "*");
        /* 220 */ this.pwdhash = i.pwdhash;

        /* 223 */ this.selecttouridx = getModeIdx(i.gameid);
        /* 224 */ this.modeselect.select(this.selecttouridx);
        /* 225 */ this.modeselect.updateList();

        /* 227 */ this.num.setRange(this.tourlist[this.tourid[this.selecttouridx]]);
        /* 228 */ this.num.setValue(i.maxnum);

        /* 230 */ this.stageid = 0;
        /* 231 */ this.stageselect.updateList();
        /* 232 */ this.stageselect.select(i.subid);

        /* 234 */ if (i.grade == (GameSetting.getSetting().getFormulaRank()).idx) {
            /* 235 */ this.formula.setSelected(true);
        }
        /* 237 */ this.formula.setMouseAccessible(false);

        /* 239 */ if ((i.flags & 0x1) != 0) {
            this.vieweron.setSelected(true);
        }
        /* 240 */ else {
            this.vieweroff.setSelected(true);
        }

        /* 242 */ if ((i.flags & 0x2) != 0) {
            /* 243 */ this.vchatpanel.setEnabled(true);
            /* 244 */ this.vchatteam.setSelected(true);
        } else {
            /* 246 */ this.vchatpanel.setEnabled(false);
            /* 247 */ this.vchatall.setSelected(true);
        }
    }

    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        /* 253 */ Object src = e.getSource();

        /* 255 */ if (src == this.cancel) {

            /* 257 */ if (this.navimode && (
            /* 258 */ e == null || !(e.getArgument() instanceof Integer)
                    || ((Integer) e.getArgument()).intValue() != 1))
                return;
            /* 260 */ playSound(1010);
            /* 261 */ this.result = null;
            /* 262 */ close();
        }
        /* 264 */ else if (src == this.create) {
            /* 265 */ if (this.navimode)
                return;
            /* 267 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 268 */ if (ga2 != null && ((ga2.getUserData()).restrict & 0x20) != 0)
                return;
            /* 270 */ if (GameSetting.getSetting().isLanguage("kr"))
                this.roomname.decideImText();

            /* 272 */ playSound(1004);
            /* 273 */ this.create.performMethodAndWait("blink");
            /* 274 */ this.result = new GameRoomInfo();
            /* 275 */ this.result.maxnum = this.num.val;
            com.nr.tool.LocalStorage.getInstance().set(this.roomname.getText(),
                    String.valueOf(getRoundValue(this.roomname.getText())));
            /* 276 */ this.result.gameid = this.tourid[this.selecttouridx];
            /* 277 */ this.result.subid = this.stageid;
            /* 278 */ this.result.name = this.roomname.getText();
            /* 279 */ this.result.pwdhash = this.pwdhash;
            /* 281 */ if (ga2 != null)
                this.result.setGrade(ga2.getUserInfo(), this.formula.isSelected(), this.traininggat);

            /* 284 */ if (this.vieweron.isSelected()) {
                /* 285 */ this.result.flags |= 0x1;
            } else {
                /* 287 */ this.result.flags &= 0xFFFFFFFE;
            }

            /* 290 */ if (this.vchatteam.isSelected()) {
                /* 291 */ this.result.flags |= 0x2;
            }

            /* 294 */ close();
            /* 295 */ } else if (src == this.num.inc) {
            /* 296 */ this.num.incValue();
            /* 297 */ } else if (src == this.num.dec) {
            /* 298 */ this.num.decValue();
        }
    }

    public static int getRoundValue(String input) {
        // Utilisation d'une expression régulière pour trouver "Round(X)"
        Pattern pattern = Pattern.compile("Round\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(input);

        // Si on trouve le motif, on retourne le nombre trouvé
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        // Sinon, on retourne 3 par défaut
        return 5;
    }

    void close() {
        /* 303 */ Container container = getParent();
        /* 304 */ if (container instanceof AmpedDialog) {
            /* 305 */ ((AmpedDialog) container).end();
        }
    }

    public void textValueChanged(TextEvent e) {
        /* 310 */ Object src = e.getSource();

        /* 312 */ if (src == this.password) {
            /* 313 */ String s = this.password.getText();
            /* 314 */ this.pwdhash = (s.length() == 0) ? 0 : KotoriUtil.getNameHash(s);
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
            /* 331 */ this.cnt = c;
            /* 332 */ this.scroll = (ScrollPane) DogfightMakeDialog.findNamedComponent("scroll", c);
            /* 333 */ this.list = new DogfightMakeDialog.StageList(this.scroll.getViewportSize(new Vector2D()));
            /* 334 */ this.scroll.setContent((Component) this.list);

            /* 336 */ this.name = (TextFigure) DogfightMakeDialog.findNamedFigure("name", c);
            /* 337 */ this.desc = (RichTextFigure) DogfightMakeDialog.findNamedFigure("desc", c);
        }

        void updateList() {
            /* 341 */ TournamentData td = GameSetting.getSetting()
                    .getTournament(DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx]);
            /* 342 */ this.list.clear();
            /* 343 */ this.list.curs = 0;
            /* 344 */ this.stage = new DogfightMakeDialog.Stage[td.numStage()];
            /* 345 */ for (int l = 0; l < td.numStage(); l++) {
                /* 346 */ this.stage[l] = this.list.addStage(td.getStage(l));
            }
            /* 348 */ this.list.revalidate();
            /* 349 */ select(DogfightMakeDialog.this.stageid);
        }

        void select(int i) {
            /* 353 */ if (DogfightMakeDialog.this.stageid >= 0) {
                /* 354 */ this.stage[DogfightMakeDialog.this.stageid].setSelected(false);
            }
            /* 356 */ this.stage[i].setSelected(true);
            /* 357 */ DogfightMakeDialog.this.stageid = i;

            /* 359 */ TournamentData.Stage s = (this.stage[DogfightMakeDialog.this.stageid]).ts;
            /* 360 */ this.name.setText(s.name);
            /* 361 */ this.desc.setText(s.desc);

            /* 363 */ Rect2D r = new Rect2D((this.stage[DogfightMakeDialog.this.stageid]).bnd);
            /* 364 */ r.y -= 3.0F;
            /* 365 */ r.h += 6.0F;
            /* 366 */ this.list.scrollRectToVisible(r);
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
            /* 379 */ this.vp = v;
            /* 380 */ this.stagecd = DogfightMakeDialog.this.srt.getLibrary().getPanel("e:stage");
            /* 381 */ this.sw = this.stagecd.bnd.w;
            /* 382 */ this.sh = this.stagecd.bnd.h;
            /* 383 */ this.cpl = (int) (v.x / this.sw);
        }

        DogfightMakeDialog.Stage addStage(TournamentData.Stage s) {
            /* 387 */ if (s != null) {
                /* 388 */ float x = (this.curs % this.cpl) * this.sw;
                /* 389 */ float y = (this.curs / this.cpl) * this.sh;
                /* 390 */ DogfightMakeDialog.Stage stage = new DogfightMakeDialog.Stage(this.stagecd);
                /* 391 */ stage.setStage(this.curs, s);
                /* 392 */ stage.setRect(3.0F + x, 3.0F + y, this.sw, this.sh);
                /* 393 */ add((Component) stage);
                /* 394 */ this.curs++;
                /* 395 */ return stage;
            }
            /* 397 */ this.curs++;
            /* 398 */ return null;
        }

        public Vector2D getPreferredSize(Vector2D buf) {
            /* 403 */ buf.x = this.bnd.w;
            /* 404 */ buf.y = 6.0F + ((this.curs - 1) / this.cpl + 1) * this.sh;
            /* 405 */ return buf;
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
            /* 421 */ setContainerData(cd, DogfightMakeDialog.this.srt.getLibrary());
            /* 422 */ this.preview = (ImageFigure) findNamedFigure("preview", this);
            /* 423 */ addMouseListener((MouseListener) this);
        }

        void setStage(int i, TournamentData.Stage s) {
            /* 427 */ this.idx = i;
            /* 428 */ this.ts = s;
            /* 429 */ if (s != null) {
                /* 430 */ this.preview.setVisible(true);
                /* 431 */ this.preview.setImageName(s.getPreviewPath());
                /* 432 */ setCursor(KWTManager.getLibrary().getCursor("finger"));
            } else {
                /* 434 */ this.preview.setVisible(false);
                /* 435 */ setCursor(null);
            }
            /* 437 */ this.nexttime = Clock.time() + 100;
        }

        void setSelected(boolean b) {
            /* 441 */ if (b) {
                play(0.0F, 1.0F, 10.0F, 1);
            }
            /* 442 */ else {
                play(1.0F, 0.0F, 10.0F, 1);
            }

        }

        boolean isSelected() {
            /* 446 */ return (this.idx == DogfightMakeDialog.this.stageid);
        }

        public void mouseEntered(MouseEvent e) {
            /* 450 */ if (this.ts == null)
                return;
            /* 452 */ if (!isSelected())
                setSelected(true);
            /* 453 */ playSound(1002);
        }

        public void mouseExited(MouseEvent e) {
            /* 457 */ if (this.ts == null)
                return;
            /* 459 */ if (!isSelected())
                setSelected(false);
        }

        public void mousePressed(MouseEvent e) {
            /* 463 */ if (this.ts == null)
                return;
            /* 465 */ if (!isSelected())
                DogfightMakeDialog.this.stageselect.select(this.idx);
            /* 466 */ playSound(1029);
        }

        public void updateFrame() {
            /* 470 */ super.updateFrame();
            /* 471 */ if (this.ts != null && this.ts.randomstage != null) {
                /* 472 */ int t = Clock.time();
                /* 473 */ if (t >= this.nexttime) {
                    /* 474 */ TournamentData td = GameSetting.getSetting()
                            .getTournament(DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx]);
                    /* 475 */ this.ridx = (this.ridx + 1) % this.ts.randomstage.length;
                    /* 476 */ TournamentData.Stage s = td.getStageDirect(this.ts.randomstage[this.ridx]);
                    /* 477 */ this.preview.setImageName(s.getPreviewPath());
                    /* 478 */ this.nexttime = t + 100;
                }
            }
        }

        public boolean isControllerTarget() {
            /* 487 */ return true;
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
            /* 501 */ this.cnt = c;
            /* 502 */ this.scroll = (ScrollPane) DogfightMakeDialog.findNamedComponent("scroll", c);
            /* 503 */ this.list = new DogfightMakeDialog.ModeList(this.scroll.getViewportSize(new Vector2D()));
            /* 504 */ this.scroll.setContent((Component) this.list);
            /* 505 */ this.name = (TextFigure) DogfightMakeDialog.findNamedFigure("name", c);
            /* 506 */ this.desc = (RichTextFigure) DogfightMakeDialog.findNamedFigure("desc", c);
        }

        void updateList() {
            /* 510 */ this.list.clear();
            /* 511 */ this.list.curs = 0;
            /* 512 */ this.mode = new DogfightMakeDialog.Mode[DogfightMakeDialog.this.tourid.length];
            /* 513 */ for (int i = 0; i < this.mode.length; i++) {
                /* 514 */ TournamentData td = GameSetting.getSetting().getTournament(DogfightMakeDialog.this.tourid[i]);
                /* 515 */ this.mode[i] = this.list.addMode(td);
            }
            /* 517 */ this.list.revalidate();
        }

        void select(int i) {
            /* 521 */ if (DogfightMakeDialog.this.selecttouridx >= 0)
                this.mode[DogfightMakeDialog.this.selecttouridx].setSelected(false);
            /* 522 */ DogfightMakeDialog.this.selecttouridx = i;
            /* 523 */ this.mode[DogfightMakeDialog.this.selecttouridx].setSelected(true);
            /* 524 */ DogfightMakeDialog.this.stageid = 0;
            /* 525 */ DogfightMakeDialog.this.stageselect.updateList();
            /* 526 */ DogfightMakeDialog.this.num.setRange((this.mode[DogfightMakeDialog.this.selecttouridx]).td);
            /* 527 */ DogfightMakeDialog.this.num
                    .setValue((this.mode[DogfightMakeDialog.this.selecttouridx]).td.maxplayers);
            /* 528 */ DogfightMakeDialog.this.vchatpanel
                    .setEnabled((DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx] != 0
                            && DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx] != 8));

            /* 530 */ Rect2D r = new Rect2D((this.mode[DogfightMakeDialog.this.selecttouridx]).bnd);

            /* 533 */ this.list.scrollRectToVisible(r);
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
            /* 546 */ this.vp = v;
            /* 547 */ this.modecd = DogfightMakeDialog.this.srt.getLibrary().getPanel("e:mode");
            /* 548 */ this.sw = this.modecd.bnd.w;
            /* 549 */ this.sh = this.modecd.bnd.h;
            /* 550 */ this.cpl = (int) (v.x / this.sw);
        }

        DogfightMakeDialog.Mode addMode(TournamentData td) {
            /* 554 */ if (td != null) {
                /* 555 */ float x = (this.curs % this.cpl) * this.sw;
                /* 556 */ float y = (this.curs / this.cpl) * this.sh;
                /* 557 */ DogfightMakeDialog.Mode mode = new DogfightMakeDialog.Mode(this.modecd);
                /* 558 */ mode.setMode(this.curs, td);
                /* 559 */ mode.setRect(3.0F + x, y, this.sw, this.sh);
                /* 560 */ add((Component) mode);
                /* 561 */ this.curs++;
                /* 562 */ return mode;
            }
            /* 564 */ this.curs++;
            /* 565 */ return null;
        }

        public Vector2D getPreferredSize(Vector2D buf) {
            /* 570 */ buf.x = this.bnd.w;
            /* 571 */ buf.y = 6.0F + ((this.curs - 1) / this.cpl + 1) * this.sh;
            /* 572 */ return buf;
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
            /* 587 */ setContainerData(cd, DogfightMakeDialog.this.srt.getLibrary());
            /* 588 */ this.name = (TextFigure) findNamedFigure("name", this);
            /* 589 */ this.inv = (Container) findNamedComponent("c_inv", this);
            /* 590 */ this.team = (Container) findNamedComponent("c_team", this);
            /* 591 */ addMouseListener((MouseListener) this);

            /* 594 */ setControlPosOffset(0, 2);
        }

        public boolean isControllerTarget() {
            /* 598 */ return true;
        }

        void setMode(int i, TournamentData _td) {
            /* 602 */ this.idx = i;
            /* 603 */ this.td = _td;
            /* 604 */ if (_td != null) {
                /* 605 */ this.name.setText(this.td.name);
                /* 606 */ this.name.reduceStrWidth();
                /* 607 */ if (this.td.desc != null && !this.td.desc.equals(""))
                    (this.td.teambattle ? this.team : this.inv).setHelpValue(this.td.desc);
                /* 608 */ skipFrameTo((this.td.teambattle ? 1 : 0));
                /* 609 */ this.inv.setVisible(!this.td.teambattle);
                /* 610 */ this.team.setVisible(this.td.teambattle);
                /* 611 */ setSelected((this.idx == DogfightMakeDialog.this.selecttouridx));
                /* 612 */ setCursor(KWTManager.getLibrary().getCursor("finger"));
            } else {
                /* 614 */ this.name.setText("");
                /* 615 */ this.inv.setVisible(false);
                /* 616 */ this.team.setVisible(false);
                /* 617 */ setCursor(null);
            }
        }

        void setSelected(boolean b) {
            /* 622 */ Container c = this.td.teambattle ? this.team : this.inv;
            /* 623 */ c.play(c.getFrame(), (!b ? 2 : (isSelected() ? 0 : 1)), 10.0F, 1);
        }

        boolean isSelected() {
            /* 627 */ return (DogfightMakeDialog.this.tourid[DogfightMakeDialog.this.selecttouridx] == DogfightMakeDialog.this.tourid[this.idx]);
        }

        public void mouseEntered(MouseEvent e) {
            /* 631 */ if (this.td == null)
                /* 632 */ return;
            if (!isSelected())
                setSelected(true);
            /* 633 */ playSound(1002);
        }

        public void mouseExited(MouseEvent e) {
            /* 637 */ if (this.td == null)
                /* 638 */ return;
            if (!isSelected())
                setSelected(false);
        }

        public void mousePressed(MouseEvent e) {
            /* 642 */ if (this.td == null)
                return;
            /* 644 */ if (!isSelected()) {
                /* 645 */ playSound(1029);
                /* 646 */ DogfightMakeDialog.this.modeselect.select(this.idx);
            }
        }

        public void updateFrame() {
            /* 651 */ super.updateFrame();
        }
    }
}

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\clien\\ui\
 * DogfightMakeDialog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
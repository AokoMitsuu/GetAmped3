package tool.stage;

import ga2.data.AmpedStageData;
import ga2.data.NPCInfo;
import ga2.data.PlayerViewData;
import ga2.data.UserInfo;
import ga2.data.VehicleData;
import ga2.gfx.AmpedScene;
import ga2.gfx.AmpedStageView;
import ga2.gfx.PlayerAvatorView;
import ga2.setting.GameSetting;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import kotori.data.ScriptModelData;
import kotori.geom.Matrix;
import kotori.geom.Quaternion;
import kotori.geom.Rect2D;
import kotori.geom.Rect3D;
import kotori.gfx.GLGraphics;
import kotori.gfx.GLRegistry;
import kotori.gfx.GLRunnable;
import kotori.gfx.KGLModel;
import kotori.gfx.KGLScriptModel;
import kotori.gfx.ModelRendererFactory;
import kotori.gfx.StaticModel;
import kotori.io.Resource;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.ExtResourceFilter;
import kotori.kwt.KWTGraphics;
import kotori.kwt.KWTManager;
import kotori.kwt.KeyStroke;
import kotori.kwt.Menu;
import kotori.kwt.MenuItem;
import kotori.kwt.ResourceFilter;
import kotori.kwt.Stroke;
import kotori.kwt.basic.BasicArchiveDialog;
import kotori.kwt.basic.BasicComboBox;
import kotori.kwt.basic.BasicMenuBar;
import kotori.kwt.basic.CosmicStyle;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.ActionListener;
import kotori.kwt.event.ApplicationEvent;
import kotori.kwt.event.ApplicationListener;
import kotori.kwt.event.ItemEvent;
import kotori.kwt.event.ItemListener;
import kotori.kwt.event.KeyListener;
import kotori.util.PropertyManager;
import kotori.util.ResourceManager;
import kotori.util.SoundManager;
import tool.ELabel;
import tool.KWTApp;
import tool.ResourceField;
import tool.motion.KeyFrameBar;

public class AmpedStageEditor extends KWTApp implements ApplicationListener {
    BasicMenuBar menubar;
    Menu stagemenu;
    LeftPanel lp;
    RightPanel rp;
    Resource file;
    AmpedStageData data;
    /* 53 */ private int stagefilecount = -1;
    AmpedStageView view;
    AmpedScene scene;
    ArrayList selections;
    Stroke bstroke;
    public static PropertyManager pr;
    public static Rect2D scriptwindow;
    private String lastopenstagefile;
    /* 55 */ private static int historySize = 10;
    /* 56 */ private String[] historyStageFileName = new String[historySize];

    public void init() {
        System.out.println(
                "test ---------------------------------------------------------------------------------------------------------------");
        /* 63 */ SoundManager.init();
        /* 64 */ pr = PropertyManager.create("AmpedStageEditor");
        /* 65 */ this.kwtmanager.addApplicationListener(this);

        /* 70 */ this.selections = new ArrayList();
        /* 71 */ this.bstroke = new Stroke(CosmicStyle.gray4, 1.0F);

        /* 75 */ String strcount = pr.getString("fileCount");
        /* 76 */ strcount = pr.getString("stagefileCount");
        /* 77 */ if (strcount != null) {
            /* 78 */ this.stagefilecount = Integer.parseInt(strcount);

            /* 80 */ for (int k = 0; k < this.stagefilecount; k++) {
                /* 81 */ this.historyStageFileName[k] = pr.getString("stagefileName" + k);
            }

            /* 84 */ if (this.historyStageFileName[0] != null) {
                /* 85 */ this.lastopenstagefile = this.historyStageFileName[0].trim();
            }
        }

        /* 91 */ this.menubar = new BasicMenuBar();
        /* 92 */ this.menubar.setRect(0.0F, 0.0F, getWidth(), 16.0F);
        /* 93 */ this.stagemenu = new Menu("FILE(sd2)");
        /* 94 */ this.stagemenu.addItem(new MenuItem("OPEN", KeyStroke.getCommandKeyStroke(79)));
        /* 95 */ this.stagemenu.addSeparator();

        /* 98 */ for (int i = 0; i < this.stagefilecount; i++) {
            /* 99 */ if (this.historyStageFileName[i] != null) {
                /* 100 */ this.stagemenu.addItem(new MenuItem(this.historyStageFileName[i]));
            }
        }

        /* 105 */ this.stagemenu.addSeparator();
        /* 106 */ this.stagemenu.addItem(new MenuItem("SAVE", KeyStroke.getCommandKeyStroke(83, 2)));
        /* 107 */ this.stagemenu.addSeparator();
        /* 108 */ this.stagemenu.addActionListener((ActionListener) this);

        /* 110 */ this.menubar.addItem(this.stagemenu);
        /* 111 */ add((Component) this.menubar);
        /* 112 */ addKeyListener((KeyListener) this.menubar);

        /* 116 */ this.lp = new LeftPanel();
        /* 117 */ add((Component) this.lp);

        /* 119 */ this.rp = new RightPanel();
        /* 120 */ add((Component) this.rp);

        /* 124 */ StaticModel
                .setDefaultModelRendererFactory((ModelRendererFactory) new AmpedScene.AmpedRendererFactory());
        /* 125 */ if (!this.kxrgs) {
            /* 126 */ GameSetting.initSetting(new File("conf/game.conf"));
        }

        /* 144 */ String[] rect = { "rect_x", "rect_y", "rect_w", "rect_h" };
        /* 145 */ int[] setrect = { 100, 100, 400, 520 };
        /* 146 */ for (int j = 0; j < 4; j++) {
            /* 147 */ String str = pr.getString(rect[j]);
            /* 148 */ if (str != null) {
                /* 149 */ setrect[j] = Integer.parseInt(str);
            }
        }
        /* 152 */ scriptwindow = new Rect2D();
        /* 153 */ scriptwindow.x = setrect[0];
        /* 154 */ scriptwindow.y = setrect[1];
        /* 155 */ scriptwindow.w = setrect[2];
        /* 156 */ scriptwindow.h = setrect[3];

        try {
            /* 164 */ loadStageData(this.lastopenstagefile);
            /* 165 */ } catch (Exception e) {
            /* 166 */ e.printStackTrace();
            /* 167 */ loadStageData("/stage/hotel/hotellobby.sd2");
        }
    }

    public void setStageData(AmpedStageData d) {
        /* 173 */ synchronized (KWTManager.PAINT_LOCK) {
            /* 174 */ this.data = d;
            /* 175 */ updatePartsData();

            /* 177 */ this.lp.layer.updateData();

            /* 179 */ this.rp.sparam.updateData();
            /* 180 */ this.rp.partsdata.updateData();
            /* 181 */ this.rp.objdata.updateData();

            /* 183 */ repaint();
        }
    }

    public void updatePartsData() {
        /* 188 */ synchronized (KWTManager.PAINT_LOCK) {
            /* 189 */ this.view = new AmpedStageView();
            /* 190 */ this.view.setStageData(this.data);

            /* 192 */ this.scene = new AmpedScene();
            /* 193 */ this.scene.setShadowParam(60.0F, 60.0F, 1.0F, 512, 512);
            /* 194 */ this.scene.enableShadowMapping(true);

            /* 196 */ this.scene.setStage(this.view);

            int l;
            /* 199 */ for (l = 0; l < this.view.parts.length; l++) {
                /* 200 */ if ((this.view.parts[l]).kgl != null) {
                    /* 201 */ AmpedStageData.Entry entry = getStagePartsEntry((this.view.parts[l]).sparts);
                }
            }

            /* 207 */ for (l = 0; l < this.data.players.size(); l++) {
                /* 208 */ this.scene.add(createPlayerKGL((AmpedStageData.Entry) this.data.players.get(l)));
            }

            /* 211 */ for (l = 0; l < this.data.objs.size(); l++) {
                /* 212 */ this.scene.add(createObjectKGL((AmpedStageData.Entry) this.data.objs.get(l)));
            }

            /* 215 */ for (l = 0; l < this.data.pops.size(); l++) {
                /* 216 */ this.scene.add(createPopupKGL((AmpedStageData.Entry) this.data.pops.get(l)));
            }

            /* 219 */ GLRegistry.registerProc(new GLRunnable() {
                public void run(GLGraphics g) {
                    /* 221 */ AmpedStageEditor.this.scene.register(g);
                }
            });
        }
    }

    void registerKGL(final KGLScriptModel kgl) {
        /* 235 */ GLRegistry.registerProc(new GLRunnable() {
            public void run(GLGraphics g) {
                /* 237 */ kgl.register(g);
            }
        });
    }

    KGLScriptModel createPlayerKGL(AmpedStageData.Entry e) {
        /* 244 */ KGLScriptModel kgl = new KGLScriptModel();

        /* 246 */ PlayerViewData pvd = PlayerViewData.getTest();
        /* 247 */ PlayerAvatorView pv = new PlayerAvatorView();
        /* 248 */ pv.setData(pvd, 1.0F);
        /* 249 */ kgl.setModel((KGLModel) pv);
        /* 250 */ Rect3D b = kgl.getModelBounds(new Rect3D(), new Matrix());
        /* 251 */ kgl.setBoundBody(b);
        /* 252 */ kgl.setEdge(true);
        /* 253 */ kgl.setShadowState(2);
        /* 254 */ kgl.startAnimation(5.0F, 5.0F, 0.0F, false);

        /* 256 */ kgl.pos.set(e.loc);
        /* 257 */ Quaternion.getRotateQuaternion(e.dir * 0.017453292F, 0.0F, 1.0F, 0.0F, kgl.rot);

        /* 259 */ kgl.updateMatrix();
        /* 260 */ kgl.updateBounds();

        /* 262 */ kgl.putParam("stageentry", e);

        /* 264 */ registerKGL(kgl);

        /* 266 */ return kgl;
    }

    KGLScriptModel createObjectKGL(AmpedStageData.Entry e) {
        /* 270 */ KGLScriptModel kgl = new KGLScriptModel();

        /* 272 */ Object d = null;
        /* 273 */ d = this.data.getObjectData(e.data);
        /* 274 */ setObjectDataView(kgl, d);

        /* 276 */ kgl.setShadowState(2);

        /* 278 */ kgl.pos.set(e.loc);
        /* 279 */ Quaternion.getRotateQuaternion(e.dir * 0.017453292F, 0.0F, 1.0F, 0.0F, kgl.rot);

        /* 281 */ kgl.updateMatrix();
        /* 282 */ kgl.updateBounds();

        /* 284 */ kgl.putParam("stageentry", e);

        /* 286 */ registerKGL(kgl);

        /* 288 */ return kgl;
    }

    void setObjectDataView(KGLScriptModel kgl, Object data) {
        /* 292 */ if (data == null) {
            /* 293 */ data = new ScriptModelData();
            /* 294 */ ((ScriptModelData) data).bounds.set(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F);
        }

        /* 297 */ if (data instanceof ScriptModelData) {
            /* 298 */ ScriptModelData scm = (ScriptModelData) data;

            /* 301 */ kgl.setData(scm);
            /* 302 */ Rect3D b = kgl.getModelBounds(new Rect3D(), new Matrix());
            /* 303 */ kgl.setBoundBody(scm.bounds);
            /* 304 */ kgl.setShadowState(2);
        }

        /* 307 */ if (data instanceof VehicleData) {
            /* 308 */ System.out.println("setObjectDataView ItemData!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            /* 309 */ ScriptModelData scm = ((VehicleData) data).getScriptModelData();

            /* 311 */ kgl.setData(scm);
            /* 312 */ Rect3D b = kgl.getModelBounds(new Rect3D(), new Matrix());
            /* 313 */ kgl.setBoundBody(scm.bounds);
            /* 314 */ kgl.setShadowState(2);
        }

        /* 317 */ if (data instanceof UserInfo) {
            /* 318 */ UserInfo ui = (UserInfo) data;
            /* 319 */ float uiscale = 1.0F;
            /* 320 */ if (ui instanceof NPCInfo) {
                /* 321 */ NPCInfo npc = (NPCInfo) ui;
                /* 322 */ if (npc.npcstyle != null) {
                    /* 323 */ uiscale = npc.npcstyle.scale;
                } else {
                    /* 325 */ uiscale = (GameSetting.getSetting().getStyle(ui.style)).scale;
                }
            } else {
                /* 328 */ uiscale = (GameSetting.getSetting().getStyle(ui.style)).scale;
            }
            /* 330 */ PlayerViewData pvd = new PlayerViewData();
            /* 331 */ pvd.set(ui.head, ui.face, ui.body, ui.deco, ui.accs, ui.skin);
            /* 332 */ PlayerAvatorView pv = new PlayerAvatorView();
            /* 333 */ pv.setData(pvd, uiscale);
            /* 334 */ pv.setEdge(true);
            /* 335 */ kgl.setModel((KGLModel) pv);
            /* 336 */ Rect3D b = kgl.getModelBounds(new Rect3D(), new Matrix());
            /* 337 */ kgl.setBoundBody(b);
            /* 338 */ kgl.scale.set(uiscale, uiscale, uiscale);
            /* 339 */ kgl.updateMatrix();
            /* 340 */ kgl.startAnimation(5.0F, 5.0F, 0.0F, false);
        }
    }

    KGLScriptModel createPopupKGL(AmpedStageData.Entry e) {
        /* 345 */ KGLScriptModel kgl = new KGLScriptModel();

        /* 347 */ kgl.setBoundBody(new Rect3D(0.0F, 0.0F, 0.0F, 0.8F, 0.05F, 0.8F));

        /* 349 */ kgl.pos.set(e.loc);
        /* 350 */ Quaternion.getRotateQuaternion(e.dir * 0.017453292F, 0.0F, 1.0F, 0.0F, kgl.rot);

        /* 352 */ kgl.updateMatrix();
        /* 353 */ kgl.updateBounds();

        /* 355 */ kgl.putParam("stageentry", e);

        /* 357 */ registerKGL(kgl);

        /* 359 */ return kgl;
    }

    KGLScriptModel getKGL(AmpedStageData.Entry e) {
        int l;
        /* 368 */ for (l = 0; l < this.scene.size(); l++) {
            /* 369 */ KGLScriptModel kgl = this.scene.getModelAt(l);
            /* 370 */ if (kgl.getParam("stageentry") == e) {
                /* 371 */ return kgl;
            }
        }

        /* 375 */ for (l = 0; l < this.view.parts.length; l++)
            ;

        /* 380 */ return null;
    }

    public void select(AmpedStageData.Entry e) {
        /* 394 */ synchronized (KWTManager.PAINT_LOCK) {
            /* 395 */ this.selections.clear();
            /* 396 */ this.selections.add(e);
            /* 397 */ this.lp.prop.setEntry(e);
        }
    }

    public int getEntryType(AmpedStageData.Entry e) {
        /* 402 */ if (this.data.players.contains(e))
            return 0;
        /* 403 */ if (this.data.objs.contains(e))
            return 1;
        /* 404 */ if (this.data.stageobjs.contains(e))
            return 2;
        /* 405 */ if (this.data.pops.contains(e))
            return 3;
        /* 406 */ return -1;
    }

    public AmpedStageData.Entry getStagePartsEntry(AmpedStageData.StageParts p) {
        /* 411 */ for (int l = 0; l < this.data.stageobjs.size(); l++) {
            /* 412 */ AmpedStageData.Entry e = (AmpedStageData.Entry) this.data.stageobjs.get(l);
            /* 413 */ if (e.name.equals(p.getName())) {
                /* 414 */ return e;
            }
        }
        /* 417 */ return null;
    }

    protected boolean arguments(String[] args) {
        /* 428 */ for (int l = 0; l < args.length; l++) {
            /* 429 */ System.out.println(String.valueOf(l) + " " + args[l]);
            /* 430 */ if (args[l].equals("-kxrres")) {
                /* 431 */ this.kxrres = true;
            }
            /* 433 */ else if (args[l].equals("-kxrgs")) {
                /* 434 */ this.kxrgs = true;
            }
        }
        /* 437 */ return true;
    }

    protected String getTitle() {
        /* 441 */ return "GA2 StageEditor";
    }

    protected int getWidth() {
        /* 445 */ return 1024;
    }

    protected int getHeight() {
        /* 449 */ return 768;
    }

    protected boolean isResizable() {
        /* 453 */ return true;
    }

    protected Container createRoot() {
        /* 457 */ this.kwtmanager.setBufferClearFlag(0);
        /* 458 */ return (Container) this;
    }

    public void updateLayout() {
        /* 462 */ super.updateLayout();

        /* 464 */ this.menubar.setRect(0.0F, 0.0F, this.bnd.w, 16.0F);
        /* 465 */ this.lp.setRect(0.0F, 16.0F, this.bnd.w - 352.0F, this.bnd.h - 16.0F);
        /* 466 */ this.rp.setRect(this.bnd.w - 352.0F, 16.0F, 352.0F, this.bnd.h - 16.0F);
    }

    public void paint(KWTGraphics g) {
        /* 470 */ g.setFill(1431655935);
        /* 471 */ g.fillRect(0.0F, 0.0F, this.bnd.w, this.bnd.h, 0.0F);

        /* 473 */ super.paint(g);
    }

    public void actionPerformed(ActionEvent e) {
        /* 479 */ Object src = e.getSource();

        /* 481 */ if (src == this.stagemenu) {
            /* 482 */ processStageMenu(e);
        }
    }

    public void performApplicationEvent(ApplicationEvent e) {
        /* 487 */ switch (e.getID()) {

            case 2102:
                /* 490 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 491 */ ResourceManager.getResource("/efx").sync(5);
                    /* 492 */ if (this.data.getResource() != null) {
                        /* 493 */ Resource df = this.data.getResource().getResource("data");
                        /* 494 */ if (df != null)
                            df.sync(3);

                    }
                }
                break;
        }
    }

    void processStageMenu(ActionEvent e) {
        /* 503 */ MenuItem item = (MenuItem) e.getArgument();
        /* 504 */ String s = item.getLabel();

        /* 506 */ if (s.equals("OPEN")) {
            /* 507 */ openStageFile();
        }
        /* 509 */ else if (s.equals("SAVE")) {
            /* 510 */ saveData();
        } else {
            /* 512 */ openStageFilehistory(s);
        }
    }

    public void updateStageFileHistory(String name) {
        /* 522 */ Object history = this.historyStageFileName.clone();
        /* 523 */ String[] fileFistory = new String[historySize];
        /* 524 */ int listno = -1;
        /* 525 */ if (history instanceof String[]) {
            /* 526 */ fileFistory = (String[]) history;
        }
        int i;
        /* 529 */ for (i = 0; i < this.stagefilecount; i++) {
            /* 530 */ if (name.equals(this.historyStageFileName[i])) {
                /* 531 */ listno = i;

                break;
            }
        }

        /* 537 */ if (listno == -1) {
            /* 538 */ for (i = 0; i < this.stagefilecount - 1; i++) {
                /* 539 */ this.historyStageFileName[i + 1] = fileFistory[i];
            }
        } else {
            /* 542 */ for (i = 0; i < listno; i++) {
                /* 543 */ this.historyStageFileName[i + 1] = fileFistory[i];
            }
        }
        /* 546 */ this.historyStageFileName[0] = name;

        /* 548 */ if (this.stagefilecount < historySize)
            this.stagefilecount++;

        /* 551 */ for (i = 0; i < this.historyStageFileName.length && this.historyStageFileName[i] != null; i++) {
            /* 552 */ pr.put("stagefileName" + i, this.historyStageFileName[i]);
        }
        /* 554 */ String count = Integer.toString(this.stagefilecount);
        /* 555 */ pr.put("stagefileCount", count);
        /* 556 */ pr.save();

        /* 559 */ updateStageFileMenuItem();
    }

    private void updateStageFileMenuItem() {
        /* 568 */ this.stagemenu.removeAllItems();
        /* 569 */ this.stagemenu.addItem(new MenuItem("OPEN", KeyStroke.getCommandKeyStroke(79)));
        /* 570 */ this.stagemenu.addSeparator();

        /* 573 */ for (int i = 0; i < this.stagefilecount; i++) {
            /* 574 */ if (this.historyStageFileName[i] != null) {
                /* 575 */ this.stagemenu.addItem(new MenuItem(this.historyStageFileName[i]));
            }
        }

        /* 579 */ this.stagemenu.addSeparator();
        /* 580 */ this.stagemenu.addItem(new MenuItem("SAVE", KeyStroke.getCommandKeyStroke(83, 2)));
        /* 581 */ this.stagemenu.addSeparator();
    }

    private void openStageFilehistory(String s) {
        /* 591 */ boolean b_open = false;
        /* 592 */ for (int i = 0; i < this.stagefilecount; i++) {
            /* 593 */ if (s.equals(this.historyStageFileName[i]) && !b_open) {
                /* 594 */ loadStageData(s);
                /* 595 */ b_open = true;
            }
        }
    }

    private void loadStageData(String path) {
        /* 606 */ Resource r = ResourceManager.getResource(path);
        /* 607 */ if (r == null) {
            /* 608 */ System.out.println(String.valueOf(path) + "  開けません");

            return;
        }
        /* 612 */ AmpedStageData d = (AmpedStageData) r.getContent();
        /* 613 */ setStageData(d);
        /* 614 */ updateStageFileHistory(path);
        /* 615 */ this.file = r;
    }

    public void openStageFile() {
        /* 620 */ BasicArchiveDialog basicArchiveDialog = new BasicArchiveDialog("OPEN STAGE FILE",
                /* 621 */ ResourceManager.getRoot(), 1);
        /* 622 */ basicArchiveDialog.setBranch(".sd2");
        /* 623 */ basicArchiveDialog.setResourceFilter((ResourceFilter) new ExtResourceFilter(new String[] { ".sd2" }));
        /* 624 */ if (this.file != null) {
            /* 625 */ basicArchiveDialog.setDir(this.file);
            /* 626 */ basicArchiveDialog.setFile(this.file.getName());
        }

        /* 629 */ basicArchiveDialog.show((Container) getRootContainer(), null);
        /* 630 */ if (basicArchiveDialog.getSelectedDir() != null && basicArchiveDialog.getSelectedFile() != null) {
            /* 631 */ String path = String.valueOf(basicArchiveDialog.getSelectedDir().getPath()) + "/"
                    + basicArchiveDialog.getSelectedFile();
            /* 632 */ loadStageData(path);
            /* 633 */ updateStageFileHistory(path);
        }
    }

    public void saveData() {
        /* 638 */ BasicArchiveDialog basicArchiveDialog = new BasicArchiveDialog("SAVE STAGEDATA", KWTApp.res.getRoot(),
                0);
        /* 639 */ basicArchiveDialog.setBranch(".sd2");
        /* 640 */ if (this.file != null) {
            /* 641 */ basicArchiveDialog.setDir(this.file);
            /* 642 */ basicArchiveDialog.setFile(this.file.getName());
        }

        /* 645 */ basicArchiveDialog.show((Container) getRootContainer(), null);
        /* 646 */ if (basicArchiveDialog.getSelectedDir() != null && basicArchiveDialog.getSelectedFile() != null) {
            /* 647 */ String path = String.valueOf(basicArchiveDialog.getSelectedDir().getPath()) + "/"
                    + basicArchiveDialog.getSelectedFile();
            try {
                /* 649 */ this.file = basicArchiveDialog.saveResource((Serializable) this.data);
                /* 650 */ this.data.contentUpdate(1, this.file);

                /* 653 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 654 */ this.rp.objdata.updateData();
                }

                /* 657 */ setStageData(this.data);
                /* 658 */ updateStageFileHistory(path);
            }
            /* 660 */ catch (Exception ioe) {
                /* 661 */ ioe.printStackTrace();
            }
        }
    }

    class LeftPanel
            extends Container {
        StageCanvas canvas;
        KeyFrameBar motionbar;
        LayerPanel layer;
        PropertyPanel prop;

        LeftPanel() {
            /* 675 */ this.canvas = new StageCanvas(AmpedStageEditor.this);
            /* 676 */ add((Component) this.canvas);
            /* 677 */ this.motionbar = new KeyFrameBar();
            /* 678 */ add((Component) this.motionbar);
            /* 679 */ this.motionbar.addActionListener((ActionListener) this);

            /* 681 */ this.layer = new LayerPanel(AmpedStageEditor.this);
            /* 682 */ add((Component) this.layer);
            /* 683 */ this.prop = new PropertyPanel(AmpedStageEditor.this);
            /* 684 */ add((Component) this.prop);
        }

        public void updateLayout() {
            /* 688 */ super.updateLayout();
            /* 689 */ this.canvas.setRect(8.0F, 8.0F, this.bnd.w - 16.0F, this.bnd.h - 232.0F);
            /* 690 */ this.motionbar.setRect(8.0F, this.bnd.h - 220.0F, this.bnd.w - 16.0F, 20.0F);
            /* 691 */ this.layer.setRect(0.0F, this.bnd.h - 200.0F, 400.0F, 200.0F);
            /* 692 */ this.prop.setRect(400.0F, this.bnd.h - 188.0F, 244.0F, 188.0F);
        }

        public void actionPerformed(ActionEvent e) {
            /* 697 */ Object src = e.getSource();

            /* 699 */ if (src == this.motionbar &&
            /* 700 */ "move".equals(e.getActionCommand())) {
                /* 701 */ moveFrame(this.motionbar.getFrame());
            }
        }

        void moveFrame(int i) {
            /* 708 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 709 */ int time = 0;
                /* 710 */ for (int l = 0; l < AmpedStageEditor.this.data.partsnum; l++) {
                    /* 711 */ AmpedStageData.StageParts p = AmpedStageEditor.this.data.parts[l];
                    /* 712 */ p.startAnimation(i, i, 0.0F, false, time);
                    /* 713 */ p.updateMotion(time);
                }
                /* 715 */ repaint();
                /* 716 */ AmpedStageEditor.this.view.update(AmpedStageEditor.this.scene);
            }
        }
    }

    class RightPanel
            extends Container {
        AmpedStageEditor.PartsDataPanel partsdata;
        StageParamPanel sparam;
        ObjectDataPanel objdata;

        RightPanel() {
            /* 728 */ this.partsdata = new AmpedStageEditor.PartsDataPanel();
            /* 729 */ this.partsdata.setRect(8.0F, 8.0F, 334.0F, 64.0F);
            /* 730 */ add((Component) this.partsdata);
            /* 731 */ this.sparam = new StageParamPanel(AmpedStageEditor.this);

            /* 734 */ this.sparam.setProperty(AmpedStageEditor.pr);

            /* 736 */ add((Component) this.sparam);
            /* 737 */ this.objdata = new ObjectDataPanel(AmpedStageEditor.this);
            /* 738 */ add((Component) this.objdata);
        }

        public void updateLayout() {
            /* 742 */ super.updateLayout();
            /* 743 */ this.sparam.setRect(8.0F, 114.0F, 334.0F, this.bnd.h - 360.0F);
            /* 744 */ this.objdata.setRect(8.0F, this.bnd.h - 240.0F, 334.0F, 200.0F);
        }
    }

    class PartsDataPanel
            extends Container {
        ResourceField partsdata;
        BasicComboBox partslist;

        PartsDataPanel() {
            /* 754 */ ELabel l0 = new ELabel("PARTS DATA:");
            /* 755 */ l0.setRect(12.0F, 8.0F, 100.0F, 16.0F);
            /* 756 */ add((Component) l0);
            /* 757 */ this.partsdata = new ResourceField(KWTApp.res.getRoot(), 1);
            /* 758 */ this.partsdata.setRect(140.0F, 8.0F, 180.0F, 16.0F);
            /* 759 */ add((Component) this.partsdata);

            /* 761 */ this.partsdata.addActionListener((ActionListener) this);

            /* 763 */ l0 = new ELabel("PARTS LIST:");
            /* 764 */ l0.setRect(12.0F, 30.0F, 100.0F, 16.0F);
            /* 765 */ add((Component) l0);
            /* 766 */ this.partslist = new BasicComboBox();
            /* 767 */ this.partslist.setRect(140.0F, 30.0F, 180.0F, 20.0F);
            /* 768 */ add((Component) this.partslist);

            /* 770 */ this.partslist.addActionListener((ActionListener) this);
            /* 771 */ this.partslist.addItemListener((ItemListener) this);
        }

        void updateData() {
            /* 775 */ this.partsdata.setText(AmpedStageEditor.this.data.partsdata);

            /* 777 */ this.partslist.removeAllItems();
            /* 778 */ for (int l = 0; l < AmpedStageEditor.this.data.partsnum; l++) {
                /* 779 */ this.partslist.addItem(AmpedStageEditor.this.data.parts[l]);
            }
        }

        public void paint(KWTGraphics g) {
            /* 784 */ g.setStroke(AmpedStageEditor.this.bstroke, null);
            /* 785 */ g.drawRect(0.0F, 0.0F, this.bnd.w, this.bnd.h, 2.0F);
            /* 786 */ super.paint(g);
        }

        public void actionPerformed(ActionEvent e) {
            /* 790 */ Object src = e.getSource();
            /* 791 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 792 */ if (src == this.partsdata) {
                    /* 793 */ AmpedStageEditor.this.data.partsdata = this.partsdata.getText();
                    /* 794 */ AmpedStageEditor.this.data.updatePartsData();

                    /* 796 */ AmpedStageEditor.this.updatePartsData();
                    /* 797 */ repaint();
                }
            }
        }

        public void itemStateChanged(ItemEvent e) {
            /* 803 */ Object src = e.getSource();
            /* 804 */ if (src == this.partslist) {

                /* 806 */ AmpedStageData.StageParts sp = (AmpedStageData.StageParts) this.partslist.getSelectedItem();
                /* 807 */ if (sp == null) {
                    return;
                }
                /* 810 */ AmpedStageData.Entry ae = AmpedStageEditor.this.getStagePartsEntry(sp);
                /* 811 */ if (ae == null) {
                    /* 812 */ ae = new AmpedStageData.Entry();
                    /* 813 */ ae.name = sp.getName();
                    /* 814 */ AmpedStageEditor.this.data.stageobjs.add(ae);
                }

                /* 817 */ AmpedStageEditor.this.select(ae);
            }
        }
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\tool\stage\AmpedStageEditor.
 * class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
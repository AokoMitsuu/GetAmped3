
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
    /* 44 */ Inttable skinmap = new Inttable();
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
        /* 54 */ this.srt = LookAndFeel.getLAF().getKWTStruct("kwtdocs/menu_playerequip2.kwt");
        /* 55 */ setStruct(this.srt);
        /* 56 */ this.bnd.set(this.srt.bnd);

        /* 58 */ this.stylename = (TextFigure) findNamedFigure("stylename", this);
        /* 59 */ this.stylelv = (Container) findNamedComponent("stylelv", this);

        /* 61 */ this.parts = new PartsSelectHandler();
        /* 62 */ this.parts.setContainer((Container) findNamedComponent("parts", this));

        /* 64 */ this.status = new StatusHandler();
        /* 65 */ this.status.setContainer((Container) findNamedComponent("status", this));

        /* 67 */ this.equip = new CharaStockHandler();

        /* 72 */ this.preview = (CharaPreviewPanel) findNamedComponent("preview", this);

        /* 74 */ FluidSetting fs = FluidSetting.getSetting();
        /* 75 */ this.saleSlot = (fs.shop.getIndex(10, 48, 0) >= 0);

        /* 77 */ setScriptArgument(1, new Boolean((GameSetting.getSetting()).accs_potential));
    }

    public void setStageConnector(StageConnector c) {
        /* 81 */ this.con = c;
    }

    void checkNewSlot() {
        /* 86 */ this.equipslot = this.ud.items.getEquipSlot();
        /* 87 */ if (this.saleSlot || this.equipslot > 1) {
            /* 88 */ this.newSlot = 1;
        } else {
            /* 90 */ this.newSlot = 0;
        }
        /* 92 */ if (this.newSlot == 1 && this.ui.useridhash == 113416) {
            /* 93 */ this.newSlot = 2;
            /* 94 */ setScriptArgument(0, new Boolean(true));
        }
    }

    public void setUserData(UserData d) {
        /* 99 */ this.ud = (UserData) d.clone();
        /* 100 */ this.ui = this.ud.info;
        /* 101 */ this.us = this.ud.shortcuts;
        /* 102 */ this.backupui = this.ui.clone();

        /* 103 */ this.backupus = (UserShortcuts) this.us.clone();
        /* 104 */ setupSkinMap();
        /* 105 */ this.parts.setUserData(d);
        /* 106 */ updateUserInfo();
        /* 107 */ checkNewSlot();
        /* 108 */ this.equip.setContainer(this);
        /* 109 */ this.equip.updateStocks();
    }

    public void setUserItems(UserItems items) {
        /* 113 */ this.ud.items = items.clone();
        /* 114 */ setupSkinMap();
        /* 115 */ this.parts.setUserItems(items);
        /* 116 */ this.parts.updateItems(true);
        /* 117 */ checkNewSlot();
        /* 118 */ this.equip.setContainer(this);
        /* 119 */ this.equip.updateStocks();
    }

    void setupSkinMap() {
        /* 123 */ UserItems.Item[] si = this.ud.items.getList(9);
        /* 124 */ SkinManager sm = SkinManager.getManager();
        /* 125 */ for (int l = 0; l < si.length; l++) {
            /* 126 */ Object o = sm.loadAndLock((si[l]).id);
            /* 127 */ if (o instanceof PlayerSkinCipherData) {
                /* 128 */ this.skinmap.put((si[l]).id, o);
            }
        }
    }

    public UserData getEditingUserData() {
        /* 134 */ return this.ud;
    }

    void updateUserInfo() {
        /* 141 */ this.preview.setUserInfo(this.ui);
        /* 142 */ this.parts.setUserInfo(this.ui);
        /* 143 */ this.parts.updateItems(true);
        /* 144 */ this.status.updateStatus();
        /* 145 */ setStyle(this.ui);
    }

    void setStyle(UserInfo ui) {
        /* 149 */ StyleData sd = GameSetting.getSetting().getStyle(ui.style);
        /* 150 */ int exp = this.ud.rec.getStyleExp(sd.group);
        /* 151 */ int g = sd.getLevelGauge(exp);
        /* 152 */ this.stylename.setText(String.valueOf(sd.name) + "  /  " + (sd.getGroup()).lvname[g / 2]);
        /* 153 */ this.stylelv.setScriptArgument(0, new Integer(exp));
        /* 154 */ this.stylelv.setScriptArgument(1, new Integer(g));
        /* 155 */ this.stylelv.performMethod("set");
    }

    void partsPreview(UserItems.Item i, int sidx) {
        /* 165 */ UserInfo _ui = this.ui.clone();
        /* 166 */ if (i != null) {
            /* 167 */ _partsChange(i, _ui, sidx);
        }
        /* 169 */ StyleData sd = GameSetting.getSetting().getStyle(_ui.style);
        /* 170 */ _ui.stylelv = sd.getLevel(this.ud.rec.getStyleExp(sd.group));
        /* 171 */ this.preview.setUserInfo(_ui);
        /* 172 */ this.status.updateStatus();
        /* 173 */ setStyle(_ui);
    }

    void partsSelect(UserItems.Item i, int sidx, final Component c) {
        /* 182 */ if (i == null)
            return;
        /* 184 */ _partsChange(i, this.ui, sidx);

        /* 186 */ final GameSetting set = GameSetting.getSetting();
        /* 187 */ StyleData sd = set.getStyle(this.ui.style);
        /* 188 */ this.ui.stylelv = sd.getLevel(this.ud.rec.getStyleExp(sd.group));

        /* 190 */ updateUserInfo();

        /* 192 */ if (i.kind != 17)
            this.equip.storeStock();

        /* 194 */ if ((this.ui.isPrivilege() || set.version < 0) && isPrivatery(i)) {
            /* 195 */ KWTManager.getEventQueue().postEvent((Event) new TimerEvent(0, null) {
                public void timerInvoked(TimerEvent e) {
                    /* 197 */ AmpedPopupWindow.smallInfoPopup(set.getLocalText("$menu.playerequip.caution.privatery"),
                            c, 1);
                }
            });
        }

        /* 203 */ if (i.kind == 5 && this.con.getStagePanel() instanceof StagePanel) {
            /* 204 */ final StagePanel sp = (StagePanel) this.con.getStagePanel();
            /* 205 */ if (sp.isTutorial()) {
                /* 206 */ KWTManager.getEventQueue().postEvent((Event) new TimerEvent(200, null) {
                    public void timerInvoked(TimerEvent e) {
                        /* 208 */ sp.selectMenu(-1);
                    }
                });
            }
        }
    }

    private boolean isPrivatery(UserItems.Item i) {
        /* 217 */ return (i != null && (i.id != -1 || this.ui.skin == 0 || !this.ui.isEnabledSlot()) && i.id != 0
                && GameSetting.getSetting().isPrivately(i.kind, i.id));
    }

    private void _partsChange(UserItems.Item i, UserInfo ui, int sidx) {
        PlayerSkinCipherData pscd;
        RankData.Rank rank;
        /* 222 */ int iid = (i.id == -1) ? 0 : i.id;
        /* 223 */ boolean changestyle = false;
        /* 224 */ switch (i.kind) {
            case 0:
                /* 226 */ ui.style = (short) iid;
                /* 227 */ setSkinedID(ui, ui.skin);
                /* 228 */ changestyle = true;
                break;
            case 7:
                /* 230 */ ui.weapon = (short) iid;
                break;
            /* 231 */ case 1:
                ui.head = (short) iid;
                break;
            /* 232 */ case 2:
                ui.face = (short) iid;
                break;
            /* 233 */ case 3:
                ui.body = (short) iid;
                break;
            /* 234 */ case 4:
                ui.deco = (short) iid;
                break;
            case 5:
                /* 236 */ ui.accs = (short) iid;
                /* 237 */ ui.skill0 = (short) i.getPotentialSkill();
                /* 238 */ ui.skill0_pow = (byte) i.getPotentialSkillPow();
                /* 239 */ ui.skill1 = (short) i.getTuneUp(0);
                /* 240 */ ui.skill2 = (short) i.getTuneUp(1);
                /* 241 */ ui.skill3 = (short) i.getTuneUp(2);
                break;
            case 9:
                /* 244 */ pscd = (PlayerSkinCipherData) this.skinmap.get(iid);
                /* 245 */ if (pscd == null || pscd.isPrivately())
                    iid = 0;
                /* 246 */ ui.skin = iid;
                /* 247 */ setSkinedID(ui, iid);
                /* 248 */ changestyle = true;
                break;
            case 16:
                /* 251 */ ui.voice = iid & 0xFF;
                break;
            case 17:
                /* 254 */ rank = GameSetting.getSetting().getRank(iid);
                /* 255 */ if (rank == null || rank.isKind(0)) {
                    /* 256 */ ui.specialrank = 0;
                    break;
                }
                /* 258 */ ui.specialrank = iid;
                break;
        }

        /* 262 */ ui.resetNotHavingEquip(this.ud.items);
        // /* 263 */ if (changestyle)
        // ui = changeStyle(this.ud, ui, false);
    }

    private UserInfo changeStyle(UserData ud, UserInfo ui, boolean showPopup) {
        /* 267 */ int style = ui.style;
        /* 268 */ int accs = ui.accs;
        /* 269 */ ui.changeStyle(ui.style, ud.items);
        /* 270 */ if (showPopup) {
            /* 271 */ if (style != ui.style && ud.items.isExpired(0, style)) {
                /* 272 */ String msg = String.format(GameSetting.getSetting().getLocalText("$useritem.info.expired"),
                        new Object[] { (new UserItems.Item((byte) 0, style)).getName() });
                /* 273 */ AmpedPopupWindow.smallInfoPopup(msg, (Component) this.parts.style, 1);
            }
            /* 275 */ if (accs != ui.accs && ud.items.isExpired(5, accs)) {
                /* 276 */ String msg = String.format(GameSetting.getSetting().getLocalText("$useritem.info.expired"),
                        new Object[] { (new UserItems.Item((byte) 5, accs)).getName() });
                /* 277 */ AmpedPopupWindow.smallInfoPopup(msg, (Component) this.parts.accs, 1);
            }
        }
        /* 280 */ return ui;
    }

    private void setSkinedID(UserInfo ui, int sid) {
        /* 284 */ if (sid != 0) {
            /* 285 */ ui.setPlayerSkinData((PlayerSkinCipherData) this.skinmap.get(sid));
        } else {
            /* 287 */ ui.build = 0;
        }
    }

    public void showing() {
    }

    public void closing() throws Exception {
        /* 296 */ boolean update = false;

        /* 298 */ ItemAdditionalData iaData = this.ud.items.getItemAdditionalData();
        /* 299 */ if (iaData != null && iaData.checkExpiredEquip(this.ui)) {
            /* 300 */ this.ui = changeStyle(this.ud, this.ui, true);
            /* 301 */ update = true;
        }

        /* 305 */ GetAmped2 ga2 = GetAmped2.getAmped2();
        /* 306 */ Object[] wmd = (Object[]) null;
        /* 307 */ if (ga2 != null) {
            /* 308 */ if (((ga2.getUserData()).restrict & 0x80) != 0 && this.ui.skin != 0) {
                /* 309 */ this.ui.skin = 0;
                /* 310 */ this.ui = changeStyle(this.ud, this.ui, true);

                /* 312 */ update = true;
            }

            /* 315 */ if (!Arrays.equals((Object[]) this.us.avatorsc, (Object[]) this.backupus.avatorsc)) {
                com.nr.Utils.SaveShortCuts(this.ud);

                /* 317 */ wmd = new Object[] { this.us.avatorsc };
                /* 318 */ ga2.updateUserData(this.us.clone());
                /* 319 */ update = true;
            }
            /* 321 */ if (!this.ui.equals(this.backupui)) {
                /* 322 */ if (this.con.getPlayer().isEmotionRunning()) {
                    /* 323 */ this.con.getPlayer().stopScript();
                    /* 324 */ BasicAmpedEvent e = BasicAmpedEvent.create(this.con.getPlayer().getID(), 127);
                    /* 325 */ this.con.sendToServer((AmpedEvent) e);
                }

                /* 328 */ StyleData sd = GameSetting.getSetting().getStyle(this.ui.style);
                /* 329 */ this.ui.stylelv = sd.getLevel(this.ud.rec.getStyleExp(sd.group));

                /* 332 */ wmd = KotoriUtil.addArray(wmd, this.ui);
                /* 333 */ update = true;
                /* 334 */ ga2.updateUserData(ga2.command("write_mydata", wmd));
                /* 335 */ wmd = (Object[]) null;
                /* 336 */ if (this.con != ga2.getStageConnector() && (!(this.con.getStagePanel() instanceof StagePanel)
                        || !((StagePanel) this.con.getStagePanel()).isTutorial())) {
                    /* 337 */ this.con.updateUserData(ga2.getUserData());
                }
                /* 339 */ this.ui = ga2.getUserInfo().clone();
            }
            /* 341 */ if (update && wmd != null) {
                /* 342 */ ga2.command("write_mydata", wmd);
            }
        }
        /* 345 */ else if (this.con != null) {
            /* 346 */ if (((this.con.getUserData()).restrict & 0x80) != 0 && this.ui.skin != 0) {
                /* 347 */ this.ui.skin = 0;
                /* 348 */ this.ui = changeStyle(this.ud, this.ui, true);
                /* 349 */ update = true;
            }

            /* 352 */ this.con.updateUserData(this.ui);
            /* 353 */ this.con.updateUserData(this.us);
        }

        /* 358 */ if (this.con != null && update) {
            /* 359 */ this.con
                    .sendToServer((AmpedEvent) BasicAmpedEvent.create(this.con.getPlayer().getID(), 110, this.ui));
        }
    }

    class PartsSelectHandler
            extends ga2.client.ui.PartsSelectHandler {
        private ItemListPopup popup;

        final void setContainer(Container c) {
            /* 369 */ setContainer(c, true, 1020);
        }

        public void actionPerformed(ActionEvent e) {
            /* 374 */ String cmd = e.getActionCommand();
            /* 375 */ if (cmd == null)
                /* 376 */ return;
            if ("preview".equals(cmd)) {
                /* 377 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 378 */ PlayerEquipPanel.this.partsPreview((UserItems.Item) e.getArgument(), 0);
                }

                /* 381 */ } else if ("select".equals(cmd)) {
                /* 382 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 383 */ PlayerEquipPanel.this.partsSelect((UserItems.Item) e.getArgument(), 0,
                            (Component) e.getSource());
                }

                /* 386 */ } else if ("swaped".equals(cmd)) {
                /* 387 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 388 */ setUserItems((UserItems) e.getArgument());
                }

                /* 391 */ } else if ("popup".equals(cmd)) {
                /* 392 */ this.popup = (ItemListPopup) e.getArgument();
            }
            /* 394 */ else if ("reset".equals(cmd)) {
                /* 395 */ PlayerEquipPanel.this.updateUserInfo();
            }
        }

        public void closePopup() {
            /* 402 */ if (this.popup != null)
                this.popup.end();

        }
    }

    /* 406 */ static String[] gname = new String[] { "str", "tec", "spd", "jmp", "def" };
    private Container c_select;
    private Container c_enter;

    class StatusHandler {
        Container cnt;
        PlayerEquipPanel.Gauge[] gauge;
        PlayerEquipPanel.IconLabel[] skills;

        void setContainer(Container c) {
            /* 416 */ this.cnt = c;
            /* 417 */ this.gauge = new PlayerEquipPanel.Gauge[5];
            int l;
            /* 418 */ for (l = 0; l < 5; l++) {
                /* 419 */ this.gauge[l] = new PlayerEquipPanel.Gauge();
                /* 420 */ this.gauge[l]
                        .setContainer((Container) PlayerEquipPanel.findNamedComponent(PlayerEquipPanel.gname[l], c));
            }

            /* 427 */ this.skills = new PlayerEquipPanel.IconLabel[5];
            /* 428 */ for (l = 0; l < this.skills.length; l++) {
                /* 429 */ this.skills[l] = new PlayerEquipPanel.IconLabel();
                /* 430 */ this.skills[l].setContainer((Container) PlayerEquipPanel.findNamedComponent("s" + l, c));
            }
        }

        void updateStatus() {
            /* 435 */ StagePlayerData psd = PlayerEquipPanel.this.preview.getPlayer().getStagePlayData();
            /* 436 */ ArrayList a = new ArrayList();
            /* 437 */ for (int l = 0; l < psd.tecnum; l++) {
                /* 438 */ StagePlayerData.Tech t = psd.tec[l];
                /* 439 */ if (t.data.group != null) {
                    /* 440 */ if (!a.contains(t.data.group)) {
                        /* 441 */ a.add(t.data.group);
                    }
                } else {
                    /* 444 */ a.add(t.data);
                }
            }

            /* 455 */ int start = 1;

            /* 457 */ int snum = 5;

            /* 459 */ GameSetting set = GameSetting.getSetting();
            /* 460 */ a.clear();
            /* 461 */ if (psd.accs != 0)
                a.add(set.getAccs(psd.accs).getActiveSkill(psd.style));
            /* 462 */ if (set.accs_potential) {
                /* 463 */ if (set.getSkill(psd.skill0) != null) {
                    /* 464 */ a.add(set.getSkill(psd.skill0));
                }
                /* 466 */ start = 0;
            }
            /* 468 */ if (set.getSkill(psd.skill1) != null)
                a.add(set.getSkill(psd.skill1));
            /* 469 */ if (set.getSkill(psd.skill2) != null)
                a.add(set.getSkill(psd.skill2));
            /* 470 */ if (set.getSkill(psd.skill3) != null)
                a.add(set.getSkill(psd.skill3));

            /* 472 */ int num = Math.min(snum, a.size());
            int i;
            /* 473 */ for (i = 0; i < num; i++) {
                /* 474 */ this.skills[start + i].set(psd, (SkillData) a.get(i), (i == 0));
            }
            /* 476 */ for (i = 0; i < start; i++) {
                /* 477 */ this.skills[i].reset();
            }
            /* 479 */ for (i = start + num; i < snum; i++) {
                /* 480 */ this.skills[i].reset();
            }

            /* 483 */ this.gauge[0].set(psd.STR);
            /* 484 */ this.gauge[1].set(psd.TEC);
            /* 485 */ this.gauge[2].set(psd.SPD);
            /* 486 */ this.gauge[3].set(psd.JMP);
            /* 487 */ this.gauge[4].set(psd.DEF);
        }
    }

    class Gauge {
        Container cnt;

        void setContainer(Container c) {
            /* 496 */ this.cnt = c;
        }

        void set(int i) {
            /* 500 */ this.cnt.moveFrameTo(i * 0.1F, 8.0F);
        }
    }

    class IconLabel {
        Container cnt;
        TextFigure label;
        ImageFigure icon;

        void setContainer(Container c) {
            /* 510 */ this.cnt = c;
            /* 511 */ this.label = (TextFigure) PlayerEquipPanel.findNamedFigure("label", c);
            /* 512 */ this.icon = new ImageFigure();
            /* 513 */ this.icon.setBlend(1);
            /* 514 */ this.cnt.insertFigure((Figure) this.icon, 0);

            /* 517 */ this.cnt.setControlPosOffset(16, 0);
        }

        public void set(TechData td) {
            /* 521 */ if (td != null) {
                /* 522 */ this.label.setText(td.name);
                /* 523 */ this.label.reduceStrWidth();
                /* 524 */ Texture t = td.getIcon(0);
                /* 525 */ if (t != null) {
                    /* 526 */ this.icon.setTexture(t);
                    /* 527 */ this.icon.setRect(10.0F - t.getRectWidth() * 0.5F, 8.0F - t.getRectHeight() * 0.5F,
                            t.getRectWidth(), t.getRectHeight());
                } else {
                    /* 529 */ this.icon.setTexture(null);
                }
            } else {
                /* 532 */ reset();
            }
        }

        public void set(StagePlayerData spd, SkillData sd, boolean accs) {
            /* 537 */ int sid = spd.style;
            /* 538 */ int accsid = spd.accs;
            /* 539 */ if (sd != null) {
                /* 540 */ String name = sd.name;
                /* 541 */ if (sd.skilllevel == -1) {
                    /* 542 */ name = String.valueOf(name) + " Lv" + spd.skill0_pow;
                }
                /* 544 */ this.label.setText(name);
                /* 545 */ this.label.reduceStrWidth();
                /* 546 */ if (accs) {
                    /* 547 */ this.label.setColor(new KWTColor(-10092289));
                    /* 548 */ this.icon.setImageName("e:skill_accs");
                    /* 549 */ this.icon.setColor(KWTColor.white);
                    /* 550 */ this.icon.setRect(0.0F, -1.0F, 20.0F, 18.0F);
                } else {
                    /* 552 */ KWTColor col = new KWTColor(SkillData.getLevelColor(sd.skilllevel));
                    /* 553 */ this.label.setColor(col);
                    /* 554 */ this.icon.setImageName("skilllv" + (sd.skilllevel + 1));
                    /* 555 */ this.icon.setColor(col);
                    /* 556 */ this.icon.setRect(0.0F, 0.0F, 16.0F, 16.0F);
                }
                /* 558 */ this.icon.setup(PlayerEquipPanel.this.srt.getLibrary());

                /* 560 */ StyleData style = GameSetting.getSetting().getStyle(sid);
                /* 561 */ if (sd.skilllevel > style.getLevel(PlayerEquipPanel.this.ud.rec.getStyleExp(style.group))) {
                    /* 562 */ this.cnt.skipFrameTo(1.0F);
                    /* 563 */ this.cnt.setHelpValue(
                            GameSetting.getSetting().getLocalText("$menu.playerequip.help.stylelvshortages"));
                } else {
                    /* 565 */ this.cnt.skipFrameTo(0.0F);
                    /* 566 */ this.cnt.setHelpValue(new Object[] { sd, Integer.valueOf(accsid) });
                }
                /* 568 */ this.cnt.setCursor(KWTManager.getLibrary().getCursor("finger"));
                /* 569 */ this.cnt.repaint();
            } else {
                /* 571 */ reset();
            }
        }

        void reset() {
            /* 576 */ this.label.setText("");
            /* 577 */ this.icon.setTexture(null);
            /* 578 */ this.cnt.setHelpValue(null);
            /* 579 */ this.cnt.setCursor(null);
            /* 580 */ this.cnt.skipFrameTo(0.0F);
        }
    }

    public void updateFrame() {
        /* 587 */ super.updateFrame();
        /* 588 */ if (this.newSlot != 2) {
            return;
        }
        /* 591 */ if (this.equip.sel == -1)
            /* 592 */ return;
        float ty = -2.0F + this.equip.sel() * 26.0F;
        /* 593 */ if (ty == this.c_select.p.y)
            return;
        /* 595 */ if (!this.c_select.isVisible()) {
            /* 596 */ this.c_select.setVisible(true);
            /* 597 */ this.c_select.p.y = ty;
        }
        /* 599 */ float ofs = ty - this.c_select.p.y;
        /* 600 */ if (Math.abs(ofs) < 0.1F) {
            this.c_select.p.y = ty;
        }
        /* 601 */ else {
            this.c_select.p.y += ofs * 0.5F;
        }
        /* 602 */ this.c_select.updateMatrix();
    }

    class SlotHandler extends MouseAdaptor {
        Container cnt;
        /* 607 */ Container[] st = new Container[9];

        int sel;

        public SlotHandler(Container c) {
            /* 612 */ this.cnt = c;
        }

        public int update(int select_shortcut_index) {
            /* 618 */ this.sel = -1;

            /* 621 */ Container slot = (Container) PlayerEquipPanel.findNamedComponent("slot", this.cnt);
            /* 622 */ if (!PlayerEquipPanel.this.saleSlot && PlayerEquipPanel.this.equipslot == 1) {
                /* 623 */ slot.setVisible(false);
                /* 624 */ return 0;
            }

            /* 627 */ GameSetting gs = GameSetting.getSetting();
            /* 628 */ ETCItem ei = gs.getETCItem(48);
            /* 629 */ int stockmax = ((int[]) ei.arg)[0];
            /* 630 */ int frame = PlayerEquipPanel.this.equipslot - 2;
            /* 631 */ if (PlayerEquipPanel.this.saleSlot)
                frame++;
            /* 632 */ frame = Math.min(frame, stockmax - 2);

            /* 634 */ slot.skipFrameTo(frame);

            /* 636 */ for (int l = 0; l < 2 + frame; l++) {
                /* 637 */ Container container = (Container) PlayerEquipPanel.findNamedComponent("st" + l, this.cnt);
                /* 638 */ if (container != null) {
                    /* 639 */ this.st[l] = container;
                    /* 640 */ if (l < PlayerEquipPanel.this.equipslot)
                        container.skipFrameTo(1.0F);
                    /* 641 */ container.setCursor(KWTManager.getLibrary().getCursor("finger"));
                    /* 642 */ container.addMouseListener((MouseListener) this);
                }
            }
            /* 645 */ if (PlayerEquipPanel.this.equipslot < frame + 2) {
                /* 646 */ this.st[PlayerEquipPanel.this.equipslot]
                        .setHelpValue(gs.getLocalText("$menu.playerequip.help.introduction"));
            }

            /* 650 */ int s = Math.min(select_shortcut_index / 9, PlayerEquipPanel.this.equipslot - 1);
            /* 651 */ return (s >= 0) ? s : 0;
        }

        boolean select(int s) {
            /* 655 */ if (this.sel == s || s < 0)
                return false;
            /* 656 */ if (this.st[0] != null) {
                /* 657 */ if (this.sel >= 0) {
                    /* 658 */ this.st[this.sel]
                            .skipFrameTo(((this.sel < PlayerEquipPanel.this.equipslot) ? 1 : 0));
                }
                /* 660 */ this.st[s].skipFrameTo(3.0F);
            }
            /* 662 */ this.sel = s;

            /* 664 */ PlayerEquipPanel.this.equip.updateEquip(s);
            /* 665 */ return true;
        }

        int indexOf(Object src) {
            /* 669 */ if (src == null)
                return -1;
            /* 670 */ for (int l = 0; l < this.st.length; l++) {
                /* 671 */ if (this.st[l] == src)
                    return l;
            }
            /* 673 */ return -1;
        }

        public void mousePressed(MouseEvent e) {
            /* 677 */ int i = indexOf(e.getSource());
            /* 678 */ if (i >= PlayerEquipPanel.this.equipslot)
                /* 679 */ return;
            System.out.println("mousePressed i:" + i);
            /* 680 */ if (select(i)) {
                /* 681 */ PlayerEquipPanel.this.playSound(1029);
            }
        }

        public void mouseEntered(MouseEvent e) {
            /* 686 */ int i = indexOf(e.getSource());
            /* 687 */ if (this.sel != i) {
                /* 688 */ PlayerEquipPanel.this.playSound(1003);
                /* 689 */ this.st[i].skipFrameTo(2.0F);
            }
        }

        public void mouseExited(MouseEvent e) {
            /* 694 */ int i = indexOf(e.getSource());
            /* 695 */ if (this.sel != i) {
                /* 696 */ this.st[i].skipFrameTo(((i < PlayerEquipPanel.this.equipslot) ? 1 : 0));
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        /* 702 */ Object src = e.getSource();
        /* 703 */ ComboBox select = this.equip.l_slot;
        /* 704 */ if (src == select) {
            /* 705 */ int equipslot = this.ud.items.getEquipSlot();
            /* 706 */ int sid = select.getSelectedIndex();
            /* 707 */ if (sid >= equipslot) {

                /* 711 */ GameSetting gs = GameSetting.getSetting();
                /* 712 */ AmpedPopupWindow.smallInfoPopup(gs.getLocalText("$menu.playerequip.help.introduction"),
                        (Component) select, 1);
                /* 713 */ sid = 0;
                /* 714 */ select.setSelectedIndex(sid);
            }
            /* 716 */ this.equip.updateEquip(sid);
        }
    }

    class CharaStockHandler extends MouseAdaptor {
        Container cnt;
        Container[] st;
        int sel;
        ComboBox l_slot;
        PlayerEquipPanel.SlotHandler slot;

        CharaStockHandler() {
            /* 722 */ this.st = new Container[9];
            /* 723 */ this.sel = -1;
        }

        int sel() {
            /* 727 */ return (this.sel >= 0) ? (this.sel % 9) : this.sel;
        }

        void setContainer(Container c) {
            String key;
            /* 733 */ this.cnt = c;

            /* 736 */ this.l_slot = (ComboBox) PlayerEquipPanel.findNamedComponent("l_slot", this.cnt);
            /* 737 */ if (PlayerEquipPanel.this.newSlot == 2) {
                /* 738 */ this.l_slot.setVisible(false);
                /* 739 */ this.slot = new PlayerEquipPanel.SlotHandler(c);

                /* 741 */ key = "e";
            } else {
                /* 743 */ key = "st";
                /* 744 */ ((Container) PlayerEquipPanel.findNamedComponent("equip", this.cnt)).setVisible(false);
                /* 745 */ if (PlayerEquipPanel.this.newSlot == 0) {
                    /* 746 */ ((Container) PlayerEquipPanel.findNamedComponent("slot", this.cnt)).skipFrameTo(7.0F);
                    /* 747 */ this.l_slot.setVisible(false);
                } else {
                    /* 749 */ ((Container) PlayerEquipPanel.findNamedComponent("slot", this.cnt)).skipFrameTo(8.0F);
                    /* 750 */ ((Container) PlayerEquipPanel.findNamedComponent("n_slot", this.cnt)).setVisible(false);
                    /* 751 */ GameSetting gs = GameSetting.getSetting();
                    /* 752 */ ETCItem ei = gs.getETCItem(48);
                    /* 753 */ int stockmax = ((int[]) ei.arg)[0];

                    /* 755 */ stockmax = Math.min(stockmax,
                            PlayerEquipPanel.this.equipslot + (PlayerEquipPanel.this.saleSlot ? 1 : 0));
                    /* 756 */ for (int i = 0; i < stockmax; i++) {
                        /* 757 */ this.l_slot
                                .addItem(String.valueOf(gs.getLocalText("$menu.playerequip.label.stock")) + (i + 1));
                    }
                    /* 759 */ this.l_slot.setSelectedIndex(0);

                    /* 761 */ this.l_slot.addActionListener((ActionListener) this.cnt);
                }
            }

            /* 765 */ for (int l = 0; l < this.st.length; l++) {
                /* 766 */ Container s = (Container) PlayerEquipPanel.findNamedComponent(String.valueOf(key) + l, c);
                /* 767 */ if (s != null) {
                    /* 768 */ this.st[l] = s;
                    /* 769 */ s.setCursor(KWTManager.getLibrary().getCursor("finger"));
                    /* 770 */ s.addMouseListener((MouseListener) this);
                }
            }
            /* 773 */ PlayerEquipPanel.this.c_enter = (Container) PlayerEquipPanel.findNamedComponent("c_enter",
                    this.cnt);
            /* 774 */ PlayerEquipPanel.this.c_enter.setVisible(false);
        }

        private boolean noAccsSlot(int s) {
            /* 778 */ UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(s);
            /* 779 */ return !(a != null && a.accs != 0);
        }

        boolean select(int s) {
            /* 783 */ if (PlayerEquipPanel.this.newSlot == 2) {
                /* 784 */ if (s < 0) {
                    /* 785 */ PlayerEquipPanel.this.c_select.setVisible(false);
                    /* 786 */ return false;
                }
                /* 788 */ this.sel = s + this.slot.sel * 9;
                /* 789 */ refreshSelect();
            } else {

                /* 792 */ if (s < 0)
                    return false;
                /* 793 */ int select_index = 0;
                /* 794 */ int as = s;
                /* 795 */ if (PlayerEquipPanel.this.newSlot == 1) {
                    /* 796 */ select_index = this.l_slot.getSelectedIndex();
                    /* 797 */ as += this.l_slot.getSelectedIndex() * 9;
                }
                /* 799 */ if (select_index * 9 <= this.sel && this.sel < (select_index + 1) * 9) {
                    /* 800 */ this.st[sel()].skipFrameTo(
                            ((PlayerEquipPanel.this.us.getAvatorShortcut(this.sel) == null) ? 0 : 1));
                }
                /* 802 */ this.sel = as;
                /* 803 */ this.st[s].skipFrameTo(3.0F);
            }
            /* 805 */ return true;
        }

        private void updateStock(int _sel, UserShortcuts.AvatorShortcut a) {
            /* 809 */ int s = _sel % 9;
            /* 810 */ if (this.st[s] != null) {
                /* 811 */ if (PlayerEquipPanel.this.newSlot != 2) {
                    /* 812 */ this.st[s].skipFrameTo(((a == null) ? 0 : 1));
                    return;
                }
                /* 815 */ ImageFigure style = (ImageFigure) PlayerEquipPanel.findNamedFigure("style", this.st[s]);
                /* 816 */ boolean b_accs = false;
                /* 817 */ if (a != null) {
                    /* 818 */ style.setImageName("res:icon/style/" + a.style + ".agi");

                    /* 820 */ if (a.accs > 0) {
                        /* 821 */ ImageFigure accs = (ImageFigure) PlayerEquipPanel.findNamedFigure("accs", this.st[s]);
                        /* 822 */ accs.setImageName("res:icon/accs/" + a.accs + ".agi");

                        /* 824 */ b_accs = true;
                    }
                } else {
                    /* 827 */ style.setImageName("res:icon/putout.agi");
                    /* 828 */ style.setColor(KWTColor.white);
                }
                /* 830 */ this.st[s].findComponent("c_accs").setVisible(b_accs);
                /* 831 */ if (noAccsSlot(_sel)) {
                    PlayerEquipPanel.this.c_select.play(10.0F, 12.0F, 1.0F, 0);
                }
                /* 832 */ else {
                    PlayerEquipPanel.this.c_select.play(0.0F, 2.0F, 1.0F, 0);
                }

            }
        }

        private void updateEquip(int index_slot) {
            /* 837 */ for (int l = 0; l < 9; l++) {
                /* 838 */ UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us
                        .getAvatorShortcut(index_slot * 9 + l);
                /* 839 */ updateStock(l, a);
            }
            /* 841 */ if (PlayerEquipPanel.this.newSlot != 2)
                return;
            /* 843 */ int i = getSelectIndex();
            /* 844 */ if (i >= 0 && i / 9 == index_slot) {
                /* 845 */ this.sel = i;
                /* 846 */ refreshSelect();
            } else {
                /* 848 */ PlayerEquipPanel.this.c_select.setVisible(false);
            }
        }

        public void refreshSelect() {
            /* 853 */ if (noAccsSlot(this.sel)) {
                PlayerEquipPanel.this.c_select.play(10.0F, 12.0F, 1.0F, 0);
            }
            /* 854 */ else {
                PlayerEquipPanel.this.c_select.play(0.0F, 2.0F, 1.0F, 0);
            }
            /* 855 */ PlayerEquipPanel.this.c_select.p.y++;
        }

        private int getSelectIndex() {
            /* 860 */ int size = PlayerEquipPanel.this.us.getAvatorShortcutSize();
            /* 861 */ for (int i = 0; i < size; i++) {
                /* 862 */ UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(i);
                /* 863 */ if (a != null &&
                /* 864 */ a.style == PlayerEquipPanel.this.ui.style &&
                /* 865 */ a.head == PlayerEquipPanel.this.ui.head &&
                /* 866 */ a.body == PlayerEquipPanel.this.ui.body &&
                /* 867 */ a.build == PlayerEquipPanel.this.ui.build &&
                /* 868 */ a.face == PlayerEquipPanel.this.ui.face &&
                /* 869 */ a.deco == PlayerEquipPanel.this.ui.deco &&
                /* 870 */ a.skin == PlayerEquipPanel.this.ui.skin &&
                /* 871 */ a.accs == PlayerEquipPanel.this.ui.accs &&
                /* 872 */ a.weapon == PlayerEquipPanel.this.ui.weapon &&
                /* 873 */ a.voice == PlayerEquipPanel.this.ui.voice) {
                    /* 874 */ return i;
                }
            }
            /* 877 */ return -1;
        }

        void updateStocks() {
            /* 881 */ if (PlayerEquipPanel.this.newSlot == 2)
                PlayerEquipPanel.this.c_select = (Container) PlayerEquipPanel.findNamedComponent("c_select", this.cnt);

            /* 883 */ int sssize = PlayerEquipPanel.this.us.getAvatorShortcutSize();
            /* 884 */ for (int i = 0; i < sssize; i++) {
                /* 885 */ UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(i);
                /* 886 */ if (a != null) {
                    /* 888 */ if (a.checkLostItems(PlayerEquipPanel.this.ud.items)) {
                        /* 889 */ PlayerEquipPanel.this.us.setAvatorShortcut(i, null);
                    } else {

                        /* 892 */ a.validated(PlayerEquipPanel.this.ud.items);

                        /* 895 */ if (a.weapon != 0) {
                            /* 896 */ UserItems.Item weapon = PlayerEquipPanel.this.ud.items.getItem(7, a.weapon);
                            /* 897 */ if (weapon == null || weapon.num == 0)
                                /* 898 */ a.weapon = 0;
                        }
                    }
                }
            }
            /* 903 */ int _sel = getSelectIndex();
            /* 904 */ if (PlayerEquipPanel.this.newSlot == 2) {
                /* 905 */ int index_slot = this.slot.update(_sel);
                /* 906 */ this.slot.select(index_slot);
            } else {
                /* 908 */ int index_slot = 0;
                /* 909 */ if (PlayerEquipPanel.this.newSlot == 1 && _sel >= 9) {
                    /* 910 */ index_slot = _sel / 9;
                    /* 911 */ this.l_slot.setSelectedIndex(index_slot);
                }
                /* 913 */ updateEquip(index_slot);
            }

            /* 916 */ this.sel = -1;
            /* 917 */ select(_sel % 9);
        }

        void storeStock() {
            /* 921 */ boolean callUpdate = false;
            /* 922 */ if (PlayerEquipPanel.this.newSlot == 1 || PlayerEquipPanel.this.newSlot == 2) {
                int s;
                /* 924 */ if (PlayerEquipPanel.this.newSlot == 1) {
                    s = this.l_slot.getSelectedIndex();
                }
                /* 925 */ else {
                    s = this.slot.sel;
                }
                /* 926 */ if (this.sel < s * 9 || this.sel >= (s + 1) * 9)
                    return;
                /* 928 */ }
            UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(this.sel);
            /* 929 */ if (a == null) {
                /* 930 */ a = new UserShortcuts.AvatorShortcut();
                /* 931 */ PlayerEquipPanel.this.us.setAvatorShortcut(this.sel, a);
                /* 932 */ callUpdate = true;
            }
            /* 934 */ if (a.style != PlayerEquipPanel.this.ui.style) {
                /* 935 */ a.style = PlayerEquipPanel.this.ui.style;
                /* 936 */ callUpdate = true;
            }
            /* 938 */ a.head = PlayerEquipPanel.this.ui.head;
            /* 939 */ a.body = PlayerEquipPanel.this.ui.body;
            /* 940 */ a.face = PlayerEquipPanel.this.ui.face;
            /* 941 */ a.deco = PlayerEquipPanel.this.ui.deco;
            /* 942 */ a.skin = PlayerEquipPanel.this.ui.skin;
            /* 943 */ if (a.accs != PlayerEquipPanel.this.ui.accs) {
                /* 944 */ a.accs = PlayerEquipPanel.this.ui.accs;
                /* 945 */ callUpdate = true;
            }
            /* 947 */ a.weapon = PlayerEquipPanel.this.ui.weapon;
            /* 948 */ a.voice = (short) PlayerEquipPanel.this.ui.voice;
            /* 949 */ a.build = PlayerEquipPanel.this.ui.build;
            /* 950 */ if (PlayerEquipPanel.this.newSlot == 2 && callUpdate) {
                /* 951 */ updateStock(this.sel, a);
            }
        }

        void setStock() {
            /* 956 */ UserShortcuts.AvatorShortcut a = PlayerEquipPanel.this.us.getAvatorShortcut(this.sel);
            /* 957 */ if (a == null)
                /* 958 */ return;
            PlayerEquipPanel.this.ui = a.validated(PlayerEquipPanel.this.ud.items).apply(PlayerEquipPanel.this.ui);

            /* 960 */ StyleData style = GameSetting.getSetting().getStyle(a.style);
            /* 961 */ PlayerEquipPanel.this.ui.stylelv = style
                    .getLevel(PlayerEquipPanel.this.ud.rec.getStyleExp(style.group));

            /* 964 */ UserItems.Item item = PlayerEquipPanel.this.ud.items.getItem(5, a.accs);
            /* 965 */ if (item != null) {
                /* 966 */ PlayerEquipPanel.this.ui.skill0 = (short) item.getPotentialSkill();
                /* 967 */ PlayerEquipPanel.this.ui.skill0_pow = (byte) item.getPotentialSkillPow();
                /* 968 */ PlayerEquipPanel.this.ui.skill1 = (short) item.getTuneUp(0);
                /* 969 */ PlayerEquipPanel.this.ui.skill2 = (short) item.getTuneUp(1);
                /* 970 */ PlayerEquipPanel.this.ui.skill3 = (short) item.getTuneUp(2);
            } else {
                /* 972 */ PlayerEquipPanel.this.ui.skill0 = 0;
                /* 973 */ PlayerEquipPanel.this.ui.skill0_pow = 0;
                /* 974 */ PlayerEquipPanel.this.ui.skill1 = 0;
                /* 975 */ PlayerEquipPanel.this.ui.skill2 = 0;
                /* 976 */ PlayerEquipPanel.this.ui.skill3 = 0;
            }
            /* 978 */ PlayerEquipPanel.this.us.selavator = this.sel;
            /* 979 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 980 */ PlayerEquipPanel.this.updateUserInfo();
            }
        }

        int indexOf(Object src) {
            /* 985 */ if (src == null)
                return -1;
            /* 986 */ for (int l = 0; l < this.st.length; l++) {
                /* 987 */ if (this.st[l] == src)
                    return l;
            }
            /* 989 */ return -1;
        }

        public void mousePressed(MouseEvent e) {
            /* 993 */ if (select(indexOf(e.getSource()))) {
                /* 994 */ PlayerEquipPanel.this.playSound(1029);
                /* 995 */ setStock();
            }
        }

        public void mouseEntered(MouseEvent e) {
            /* 1000 */ int i = indexOf(e.getSource());
            /* 1001 */ int is = i;
            /* 1002 */ if (PlayerEquipPanel.this.newSlot == 2) {
                /* 1003 */ is += this.slot.sel * 9;
                /* 1004 */ if (noAccsSlot(is)) {
                    PlayerEquipPanel.this.c_enter.skipFrameTo(10.0F);
                }
                /* 1005 */ else {
                    PlayerEquipPanel.this.c_enter.skipFrameTo(0.0F);
                }
                /* 1006 */ PlayerEquipPanel.this.c_enter.p.y = (26 * i);
                /* 1007 */ PlayerEquipPanel.this.c_enter.updateMatrix();
                /* 1008 */ PlayerEquipPanel.this.c_enter.setVisible(true);
            }
            /* 1010 */ if (PlayerEquipPanel.this.newSlot == 1)
                is += this.l_slot.getSelectedIndex() * 9;
            /* 1011 */ if (this.sel != is) {
                /* 1012 */ PlayerEquipPanel.this.playSound(1003);
                /* 1013 */ if (PlayerEquipPanel.this.newSlot < 2)
                    this.st[i].skipFrameTo(2.0F);
            }
        }

        public void mouseExited(MouseEvent e) {
            /* 1018 */ if (PlayerEquipPanel.this.newSlot == 2) {
                /* 1019 */ PlayerEquipPanel.this.c_enter.setVisible(false);
            } else {
                /* 1021 */ int i = indexOf(e.getSource());
                /* 1022 */ int as = i;
                /* 1023 */ if (PlayerEquipPanel.this.newSlot == 1)
                    as += this.l_slot.getSelectedIndex() * 9;
                /* 1024 */ if (this.sel != as) {
                    /* 1025 */ this.st[i]
                            .skipFrameTo(((PlayerEquipPanel.this.us.getAvatorShortcut(as) == null) ? 0 : 1));
                }
            }
        }
    }

    public boolean isControllerTarget(Component child) {
        int i;
        /* 1036 */ for (i = 0; i < this.status.skills.length; i++) {
            /* 1037 */ if ((this.status.skills[i]).cnt == child && (this.status.skills[i]).label.getText().length() > 0)
                return true;
        }
        /* 1039 */ for (i = 0; i < this.equip.st.length; i++) {
            /* 1040 */ if (this.equip.st[i] == child)
                return true;
        }
        /* 1042 */ return false;
    }
}

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\clien\\ui\
 * PlayerEquipPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
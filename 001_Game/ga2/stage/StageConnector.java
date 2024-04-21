package ga2.stage;

import ga2.client.BGMManager;
import ga2.client.GetAmped2;
import ga2.client.LookAndFeel;
import ga2.client.PlaybackManager;
import ga2.client.VoiceChatManager;
import ga2.client.ui.RankupEpisodePanel;
import ga2.client.ui.TalkPanel;
import ga2.client.ui.TopPanel;
import ga2.client.ui.TreasureRouletPanel;
import ga2.data.AccessoryData;
import ga2.data.AmpedStageData;
import ga2.data.ChallengeData;
import ga2.data.EventSceneData;
import ga2.data.GuardianData;
import ga2.data.MyRoomData;
import ga2.data.PlaybackRecord;
import ga2.data.RandomGuardianTreasure;
import ga2.data.RandomTreasure;
import ga2.data.Speach;
import ga2.data.TechData;
import ga2.data.TreasureTable;
import ga2.data.UserChallenge;
import ga2.data.UserData;
import ga2.data.UserDuelData;
import ga2.data.UserGuardianData;
import ga2.data.UserInfo;
import ga2.data.UserItems;
import ga2.data.UserQuest;
import ga2.data.UserRecords;
import ga2.data.UserRelations;
import ga2.data.UserShortcuts;
import ga2.data.UserTalkFlags;
import ga2.data.WeaponData;
import ga2.data.net.AmpedException;
import ga2.data.net.ChatMessage;
import ga2.data.net.GameRoomInfo;
import ga2.data.net.TalkResult;
import ga2.data.net.UseItemInfo;
import ga2.data.net.VoiceChatPlayerInfo;
import ga2.gfx.Camerawork;
import ga2.laf.amped2.AmpedDialog;
import ga2.laf.amped2.BusteredPanel;
import ga2.laf.amped2.GetPracticeNpcPanel;
import ga2.laf.amped2.ShutterManager;
import ga2.laf.amped2.TournamentRewardPanel;
import ga2.laf.amped2.TreasureGetPanel2;
import ga2.setting.ClientSetting;
import ga2.setting.GameSetting;
import ga2.stage.event.AmpedEvent;
import ga2.stage.event.AmpedEventLog;
import ga2.stage.event.AmpedProperty;
import ga2.stage.event.BasicAmpedEvent;
import ga2.stage.event.ClockSyncEvent;
import ga2.stage.event.FragmentEvent;
import ga2.stage.event.InvokeGameEvent;
import ga2.stage.event.MatchingEvent;
import ga2.stage.event.PlayerActionEvent;
import ga2.stage.event.PlayerAttackEvent;
import ga2.stage.event.PlayerEvent;
import ga2.stage.event.PlayerSequenceEvent;
import ga2.stage.event.RecordEvent;
import ga2.stage.event.RelayEvent;
import ga2.stage.event.StagePanelEvent;
import ga2.stage.event.StageSetupEvent;
import ga2.stage.ui.ArcadeStagePanel;
import ga2.stage.ui.CaptureTool;
import ga2.stage.ui.DogfightStagePanel;
import ga2.stage.ui.EventBattleStagePanel;
import ga2.stage.ui.EventStagePanel;
import ga2.stage.ui.GuardianBafabonResultPanel;
import ga2.stage.ui.GuardianRoomStagePanel;
import ga2.stage.ui.GuildRoomStagePanel;
import ga2.stage.ui.LoungeStagePanel;
import ga2.stage.ui.MatchingRoomStagePanel;
import ga2.stage.ui.MyRoomStagePanel;
import ga2.stage.ui.NewPracticeStagePanel;
import ga2.stage.ui.PlaybackControlPanel;
import ga2.stage.ui.PracticeStagePanel;
import ga2.stage.ui.StageCanvas;
import ga2.stage.ui.StagePanel;
import ga2.stage.ui.StreetfightStagePanel;
import ga2.stage.ui.TeamStreetfightStagePanel;
import ga2.stage.ui.TestPlayStagePanel;
import ga2.stage.ui.TournamentStagePanel;
import ga2.stage.ui.UnitedfightStagePanel;
import ga2.stage.ui.ViewerControlPanel;
import ga2.vc.client.VoiceCheckListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

import kotori.app.Console;
import kotori.geom.Camera;
import kotori.geom.KMath;
import kotori.geom.Rect2D;
import kotori.geom.Vector2D;
import kotori.geom.Vector3D;
import kotori.gfx.GLGraphics;
import kotori.gfx.GLRegistry;
import kotori.gfx.GLRunnable;
import kotori.io.ExternalizerInputStream;
import kotori.io.ExternalizerOutputStream;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.KWTClock;
import kotori.kwt.KWTManager;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.ActionListener;
import kotori.kwt.event.Event;
import kotori.kwt.event.TimerEvent;
import kotori.net.ByteBufferInputStream;
import kotori.net.ByteBufferOutputStream;
import kotori.net.ConnectionEngine;
import kotori.net.NetConnection;
import kotori.net.TCPConnection;
import kotori.net.Transeiver;
import kotori.net.UDPConnection;
import kotori.util.AdjustableClock;
import kotori.util.Clock;
import kotori.util.Inttable;
import kotori.util.KotoriUtil;
import kotori.util.ServerTime;
import test.StressTestClient;
import tool.stresstest.GA2StressTest2;

public abstract class StageConnector {
    public static final int GAMESTATE_ERROR = -2;
    public static final int GAMESTATE_CLOSED = -1;
    /* 75 */ int myid = -1;
    public static final int GAMESTATE_NOTCONNECTED = 0;
    public static final int GAMESTATE_CONNECTED = 1;
    public static final int GAMESTATE_READY = 2;
    public static final int GAMESTATE_PLAY = 3;
    StageClient stage;
    int entryid;
    PlayerViewModel player;
    Controller ctr;
    /* 80 */ float cx = 0.0F;
    float cy = 0.0F;
    float cw = 1920.0F;
    float ch = 1080.0F;

    /* 82 */ Container cnt = new Container();

    Container panel;

    StageCanvas canvas;
    boolean autoshutteropen = true;
    /* 88 */ Camera camera = new Camera();

    Camerawork camerawork;

    Camerawork orgcamerawork;

    /* 95 */ int gamestate = 0;

    Object errobj;

    UserData userdata;

    public Object gamereward;

    /* 107 */ ChatCommand chatcommand = new ChatCommand(this);

    /* 110 */ Inttable fragments = new Inttable();

    PlaybackRecord playback;

    boolean storeNavigationEnable = true;

    GameRoomInfo roominfo;

    int lasttime;

    public void setGameRoomInfo(GameRoomInfo i) {
        /* 121 */ this.roominfo = i;
    }

    public GameRoomInfo getGameRoomInfo() {
        /* 125 */ return this.roominfo;
    }

    void initStageCanvas() {
        /* 129 */ this.canvas = new StageCanvas(this);
        /* 130 */ this.canvas.setRect(0.0F, 0.0F, this.cw, this.ch);
    }

    public abstract boolean login();

    public abstract boolean login(boolean paramBoolean);

    public StageClient getStage() {
        /* 226 */ return this.stage;
    }

    public PlayerViewModel getPlayer() {
        /* 230 */ return this.player;
    }

    public int getPlayerID() {
        /* 234 */ return this.myid;
    }

    public Controller getController() {
        /* 238 */ return this.ctr;
    }

    public synchronized int getGameState() {
        /* 242 */ return this.gamestate;
    }

    public synchronized void setGameState(int s) {
        /* 246 */ this.gamestate = s;
    }

    public Camera getCamera() {
        /* 250 */ return this.camera;
    }

    public Camerawork getCamerawork() {
        /* 254 */ return this.camerawork;
    }

    public void setCamerawork(Camerawork cw) {
        /* 258 */ this.camerawork = cw;
        /* 259 */ initCamerawork();
    }

    public void unsetCamera() {
        /* 263 */ setCamerawork(this.orgcamerawork);
    }

    public Container getContainer() {
        /* 267 */ return this.cnt;
    }

    public StageCanvas getStageCanvas() {
        /* 271 */ if (this.canvas == null) {
            /* 272 */ initStageCanvas();
        }
        /* 274 */ return this.canvas;
    }

    public Container getStagePanel() {
        /* 279 */ return this.panel;
    }

    public TopPanel getTopPanel() {
        /* 283 */ if (this.panel instanceof StagePanel) {
            /* 284 */ return ((StagePanel) this.panel).getTopPanel();
        }
        /* 286 */ return null;
    }

    int getDt() {
        /* 292 */ int t = this.stage.getTime();
        /* 293 */ int dt = (this.lasttime == 0) ? 0 : (t - this.lasttime);
        /* 294 */ this.lasttime = t;
        /* 295 */ return dt;
    }

    public void requestFocus() {
        /* 305 */ if (this.panel != null)
            this.panel.requestFocus();

    }

    public ChatCommand getChatCommand() {
        /* 310 */ return this.chatcommand;
    }

    public Object getErrobj() {
        /* 314 */ return this.errobj;
    }

    public void updateUserData(Object o) {
        /* 327 */ if (o instanceof Object[]) {
            /* 328 */ Object[] a = (Object[]) o;
            /* 329 */ for (int l = 0; l < a.length; l++) {
                /* 330 */ updateUserData(a[l]);
            }
            return;
        }
        /* 334 */ if (o instanceof UserData) {
            /* 335 */ this.userdata = (UserData) ((UserData) o).clone();
        }
        /* 337 */ else if (o instanceof UserInfo) {
            /* 338 */ this.userdata.info = ((UserInfo) o).clone();
        }
        /* 340 */ else if (o instanceof UserItems) {
            /* 341 */ this.userdata.items = ((UserItems) o).clone();
        }
        /* 343 */ else if (o instanceof UseItemInfo) {
            try {
                /* 345 */ this.userdata.processUseItem((UseItemInfo) o, false);
                /* 346 */ } catch (Exception exception) {
            }

        }
        /* 349 */ else if (o instanceof UserQuest) {
            /* 350 */ this.userdata.quest = (UserQuest) ((UserQuest) o).clone();
        }
        /* 352 */ else if (o instanceof UserShortcuts) {
            /* 353 */ this.userdata.shortcuts = (UserShortcuts) o;
        }
        /* 355 */ else if (o instanceof UserRelations) {
            /* 356 */ this.userdata.relations = (UserRelations) o;
        }
        /* 358 */ else if (o instanceof UserTalkFlags) {
            /* 359 */ this.userdata.talkflags = ((UserTalkFlags) o).clone();
        }
        /* 361 */ else if (o instanceof GetAmped2.UpdateTalkFlag) {
            /* 362 */ GetAmped2.UpdateTalkFlag utf = (GetAmped2.UpdateTalkFlag) o;
            /* 363 */ if (utf.flag) {
                /* 364 */ this.userdata.talkflags.setFlag(utf.id, (utf.value == 1));
            } else {
                /* 366 */ this.userdata.talkflags.setValue(utf.id, utf.value);
            }

            /* 369 */ } else if (o instanceof TalkResult) {
            try {
                /* 371 */ this.userdata.processTalkResult((TalkResult) o);
                /* 372 */ } catch (Exception exception) {
            }

        }
        /* 375 */ else if (o instanceof UserGuardianData) {
            /* 376 */ this.userdata.guardian = (UserGuardianData) ((UserGuardianData) o).clone();
        }
        /* 378 */ else if (o instanceof UserChallenge) {
            /* 379 */ this.userdata.challenge = ((UserChallenge) o).clone();
        }
        /* 381 */ else if (o instanceof UserChallenge.Record) {
            /* 382 */ UserChallenge.Record r = (UserChallenge.Record) o;
            /* 383 */ this.userdata.challenge.records.put(r.id, r);
        }
        /* 385 */ else if (o instanceof UserRecords) {
            /* 386 */ this.userdata.rec = (UserRecords) o;
        }
        /* 388 */ else if (o instanceof UserDuelData) {
            /* 389 */ this.userdata.duel = (UserDuelData) o;
        }

        /* 399 */ if (this.panel instanceof StagePanel) {
            /* 400 */ StagePanel sp = (StagePanel) this.panel;
            /* 401 */ TopPanel p = sp.getTopPanel();
            /* 402 */ if (p != null)
                p.updateUserData(o);
            /* 403 */ sp.updateUserData(this.userdata);
        }
    }

    public UserData getUserData() {
        /* 408 */ return this.userdata;
    }

    public void syncUserData(int type) {
        /* 418 */ System.out.println("SyncUserData type:" + type);
        /* 419 */ sendToServer((AmpedEvent) BasicAmpedEvent.create(this.myid, 139, type));
    }

    void setPlayer(PlayerViewModel p) {
        /* 426 */ if (p == null) {
            /* 427 */ System.out.println("StageConnector.setPlayer p == null");
            return;
        }
        /* 430 */ this.player = p;

        /* 432 */ this.ctr = new PlayerController(this);
        /* 433 */ this.player.setController(this.ctr);
        /* 434 */ this.player.con = this;

        /* 436 */ this.myid = this.player.getID();
    }

    void setStage(StageClient sc) {
        /* 445 */ this.stage = sc;
        /* 446 */ this.camera.set(0.5F, 100.0F, 30.0F, this.cw / this.ch);
        /* 447 */ this.camera.set(new Vector3D(0.0F, 3.0F, -7.5F), new Vector3D(0.0F, 0.0F, 1.0F));

        /* 452 */ this.stage.scene.camera = this.camera;
        /* 453 */ this.lasttime = 0;

        /* 455 */ if (this.panel instanceof StagePanel) {
            /* 456 */ ((StagePanel) this.panel).setStage(sc);
        }
    }

    public void setContainerRect(float x, float y, float w, float h) {
        /* 466 */ this.cx = x;
        /* 467 */ this.cy = y;
        /* 468 */ this.cw = w;
        /* 469 */ this.ch = h;

        /* 473 */ this.camera.set(0.5F, 100.0F, 30.0F, this.cw / this.ch);
        /* 474 */ this.cnt.setRect(x, y, w, h);
        /* 475 */ if (this.panel != null) {
            /* 476 */ this.panel.setRect(0.0F, 0.0F, w, h);
        }
    }

    public void initCamerawork() {
        /* 482 */ this.camerawork.setStage(this.stage);
        /* 483 */ this.camerawork.setTarget(this.player);
        /* 484 */ this.camerawork.start(this.camera);
    }

    public void close() {
        /* 489 */ synchronized (this) {
            /* 490 */ if (this.gamestate >= 0) {
                /* 491 */ this.gamestate = -1;
            }
        }
    }

    public void setStoreNavigationEnable(boolean enable) {
        /* 498 */ this.storeNavigationEnable = enable;
    }

    public boolean isStoreNavigationEnabled() {
        /* 502 */ return this.storeNavigationEnable;
    }

    public void processAmpedEvent(AmpedEvent e) {
        /* 522 */ switch (e.id) {
            case 1:
                /* 523 */ sendBasicToServer(2);
                break;
            case 101:
            case 102:
                /* 526 */ processStageSetup((StageSetupEvent) e);
                conditionChange(((StageSetupEvent) e).stagemode);
                break;
            /* 527 */ case 104:
                processGameReady(e);
                break;
            /* 528 */ case 70:
                processAddModel((BasicAmpedEvent) e);
                break;
            case 82:
                /* 530 */ processClockSyncResponse((ClockSyncEvent) e);
                break;
            /* 531 */ case 58:
                processLock((BasicAmpedEvent) e);
                break;
            case 106:
                /* 533 */ processLogout((BasicAmpedEvent) e);
                break;
            /* 534 */ case 89:
                processGameReward((BasicAmpedEvent) e);
                break;
            /* 535 */ case 97:
                processStageCamera((BasicAmpedEvent) e);
                break;
            /* 536 */ case 107:
                processClearEventBattle((BasicAmpedEvent) e);
                break;
            /* 537 */ case 145:
                processInvokeGameServer((InvokeGameEvent) e);
                break;
            /* 538 */ case 147:
                processInvokeRewardKey((BasicAmpedEvent) e);
                break;
            case 160:
                /* 540 */ processFragment((FragmentEvent) e);
                return;
            /* 541 */ case 163:
                processViewerRequest((BasicAmpedEvent) e);
                break;
            /* 542 */ case 165:
                processVoiceChat((MatchingEvent) e);
                break;
            default:
                /* 545 */ processDefaultEvent(e);
                break;
        }

        /* 555 */ if (this.playback != null &&
        /* 556 */ this.stage != null && this.stage.clock != null) {

            /* 559 */ this.playback.addRecord(e, this.stage.clock.getTime());
        }
    }

    void checkPlaybackRecord(StageSetupEvent e) {
        /* 565 */ if (e == null)
            /* 566 */ return;
        if (this.playback == null) {
            /* 567 */ GetAmped2 ga2;
            GameRoomInfo i;
            ArrayList<UserInfo> a;
            int l;
            switch (e.stagemode) {

                case 1:
                case 7:
                case 18:
                case 27:
                    /* 573 */ this.playback = new PlaybackRecord();
                    /* 574 */ ga2 = GetAmped2.getAmped2();

                    /* 576 */ if (ga2 != null && ga2.getCurrentRoomInfo() instanceof GameRoomInfo) {
                        /* 577 */ i = (GameRoomInfo) ga2.getCurrentRoomInfo();
                        /* 578 */ i = (GameRoomInfo) i.clone();
                    } else {
                        /* 580 */ i = new GameRoomInfo();
                        /* 581 */ i.name = "UNKNOWN";
                    }
                    /* 583 */ i.gametime = System.currentTimeMillis();
                    /* 584 */ this.playback.begintime = this.stage.orgclock.getTime();
                    /* 585 */ a = new ArrayList();
                    /* 586 */ for (l = 0; l < e.objnum; l++) {
                        /* 587 */ if (e.objs[l] instanceof AmpedProperty.Player) {
                            /* 588 */ AmpedProperty.Player ap = (AmpedProperty.Player) e.objs[l];
                            /* 589 */ if (ap.npcid < 0)
                                a.add(ap.ui);
                        }
                    }
                    /* 592 */ this.playback.players = a.<UserInfo>toArray(new UserInfo[a.size()]);
                    /* 593 */ i.members = new int[a.size()];
                    /* 594 */ for (l = 0; l < a.size();) {
                        i.members[l] = (this.playback.players[l]).useridhash;
                        l++;
                    }
                    /* 595 */ this.playback.roominfo = i;
                    break;
            }
        }
        /* 599 */ switch (e.stagemode) {
            case 1:
            case 7:
            case 18:
            case 27:
                return;
        }

        /* 607 */ PlaybackManager m = new PlaybackManager(this.userdata.userid);
        /* 608 */ m.addPlaybackRecord(this.playback);
        /* 609 */ this.playback = null;
    }

    public void finishPlaybackRecord() {
        /* 616 */ PlaybackManager m = new PlaybackManager(this.userdata.userid);
        /* 617 */ m.addPlaybackRecord(this.playback);
        /* 618 */ this.playback = null;
    }

    public void processGameReady(AmpedEvent e) {
        /* 623 */ setGameState(2);
        /* 624 */ System.out.println("GAME_READY");

        /* 626 */ GetAmped2 ga2 = GetAmped2.getAmped2();
        /* 627 */ if ((GameSetting.getSetting()).openallmemenabled &&
        /* 628 */ ga2 != null &&
        /* 629 */ this.stage.getStageMode() == 2 &&
        /* 630 */ !(this instanceof Viewer)) {
            /* 631 */ ga2.openMemEx(true);
        }

        /* 634 */ panelSetup();
        /* 635 */ initCamerawork();

        /* 637 */ if (this.stage.bgmid != null) {
            /* 639 */ if (this.panel instanceof MyRoomStagePanel)
            /* 640 */ {
                MyRoomData mrd = (MyRoomData) this.stage.getStageDataSource();
                /* 641 */ if (mrd.setting.mybgm && mrd.ucc.get(3) != null) {
                    /* 642 */ BGMManager.playBGM(mrd.ucc.get(3));
                } else {
                    /* 644 */ BGMManager.playBGM(this.stage.bgmid, false);
                }
            }
            /* 646 */ else {
                BGMManager.playBGM(this.stage.bgmid, false);
            }

        }
        /* 649 */ if (this.autoshutteropen)
            ShutterManager.getManager().open();
        /* 650 */ if (ga2 != null && ga2.isAutomatchMovelobby && !this.stage.isLoungeMode()) {
            /* 651 */ ShutterManager.getManager().hideshutter();
        }

        /* 654 */ if (this.panel instanceof MyRoomStagePanel) {
            /* 655 */ (new EventPopupThread()).start();
        }

        /* 658 */ if (this.gamereward != null) {
            /* 659 */ if (this.gamereward instanceof TournamentReward
                    && ((TournamentReward) this.gamereward).automatch) {
                /* 660 */ GameSetting.getSetting().println("processGameReady automatch_gamereward:" + this.gamereward);
            } else {
                /* 662 */ showGameReward(this.gamereward);
            }
            /* 664 */ this.gamereward = null;
        }

        /* 667 */ enterVoiceChat(300);
    }

    public void showGameReward(Object reward) {
        /* 671 */ Object o = reward;
        /* 672 */ if (this.stage.getStageMode() == 6) {
            /* 673 */ GameSetting.getSetting().println("processGameReady gamereward:" + o);
        }
        /* 675 */ Object _r = o;
        /* 676 */ if (_r != null) {
            /* 677 */ (new RewardThread(_r)).start();
        }
    }

    public boolean isVoiceChatEnabled() {
        /* 682 */ return false;
    }

    public void enterVoiceChat(int wait) {
        /* 686 */ if (isVoiceChatEnabled()) {
            /* 687 */ (new EnterVoiceChatThread(wait)).start();
        }
    }

    public void leaveVoiceChat() {
        /* 692 */ if (isVoiceChatEnabled()) {
            /* 693 */ sendToServer((AmpedEvent) MatchingEvent.create(-1, 121));
            /* 694 */ StagePanel sp = (StagePanel) this.panel;
            /* 695 */ if (sp != null)
                sp.setVoiceChatEnabled(false);
        }
    }

    public void addIgnore(int uid) {
        /* 700 */ sendToServer((AmpedEvent) BasicAmpedEvent.create(this.player.getID(), 129,
                new Object[] { Integer.valueOf(1), Integer.valueOf(uid), Boolean.TRUE }));

        /* 702 */ if (isVoiceChatEnabled()) {
            /* 703 */ PlayerModel pm = this.stage.getPlayerForUserID(uid);
            /* 704 */ if (pm != null) {

                /* 706 */ MatchingEvent dummy = MatchingEvent.create(-1, 121, pm.getEntryID());
                /* 707 */ dummy.id = 165;
                /* 708 */ processVoiceChat(dummy);
                /* 709 */ if (this.panel != null)
                    this.panel.setParam(500, dummy);
            }
        }
    }

    public void removeIgnore(int uid) {
        /* 715 */ sendToServer((AmpedEvent) BasicAmpedEvent.create(this.player.getID(), 129,
                new Object[] { Integer.valueOf(1), Integer.valueOf(uid), Boolean.FALSE }));
    }

    public abstract void processStageSetup(StageSetupEvent paramStageSetupEvent);

    class EnterVoiceChatThread extends Thread {
        int wait;

        EnterVoiceChatThread(int _wait) {
            /* 722 */ this.wait = _wait;
        }

        public void run() {
            try {
                /* 727 */ Thread.sleep(this.wait);
                /* 728 */ } catch (Exception exception) {
            }

            /* 730 */ VoiceChatManager vcm = VoiceChatManager.getInstance();
            /* 731 */ vcm.loadSetting(true);
            /* 732 */ StagePanel sp = (StagePanel) StageConnector.this.panel;

            /* 734 */ if (vcm.isSettingVoiceChat()) {
                /* 735 */ vcm.reset();

                /* 737 */ if (vcm.isCheckFinished()) {
                    /* 738 */ if (vcm.isEnabled() && StageConnector.this.getGameState() >= 2)
                    /* 739 */ {
                        StageConnector.this.sendToServer((AmpedEvent) MatchingEvent.create(-1, 120, vcm.getInfo()));
                        /* 740 */ if (sp != null)
                            sp.setVoiceChatEnabled(true);
                    }
                    /* 742 */ else if (sp != null) {
                        sp.setVoiceChatEnabled(false);
                    }

                } else {

                    /* 746 */ vcm.addVoiceCheckListener(new CheckFinished());
                    /* 747 */ vcm.startCheck();
                }
            }
        }

        class CheckFinished
                implements VoiceCheckListener {
            public void voiceCheckFinished(boolean result, int globalIP, int localIP, int globalPort, int localPort) {
                /* 755 */ VoiceChatManager.getInstance().removeVoiceCheckListener(this);
                /* 756 */ StagePanel sp = (StagePanel) StageConnector.this.panel;

                /* 758 */ if (result && StageConnector.this.getGameState() >= 2)
                /* 759 */ {
                    StageConnector.this.sendToServer((AmpedEvent) MatchingEvent.create(-1, 120,
                            new int[] { globalIP, localIP, globalPort, localPort }));
                    /* 760 */ if (sp != null)
                        sp.setVoiceChatEnabled(true);
                }
                /* 762 */ else if (sp != null) {
                    sp.setVoiceChatEnabled(false);
                }

            }
        }
    }

    class RewardThread
            extends Thread {
        Object _r;

        RewardThread(Object r) {
            /* 773 */ this._r = r;
        }

        public void run() {
            try {
                /* 778 */ Thread.sleep(500L);
                /* 779 */ } catch (Exception exception) {
            }

            /* 782 */ GameSetting set = GameSetting.getSetting();
            /* 783 */ int eprank = StageConnector.this.userdata.guardian
                    .getEpisodeRank((StageConnector.this.userdata.info.getRank(true)).idx);

            /* 785 */ if (this._r instanceof TournamentReward) {
                /* 786 */ GetAmped2 ga2 = GetAmped2.getAmped2();
                /* 787 */ if (!(GameSetting.getSetting()).lanplayenabled || ga2 == null || !ga2.isLanPlay()) {
                    /* 788 */ TournamentReward r = (TournamentReward) this._r;
                    /* 789 */ TournamentRewardPanel p = new TournamentRewardPanel();
                    /* 790 */ p.setup(r.oldscore, r.getscore, r.bonus);
                    /* 791 */ showAmpedDialog((Container) p, StageConnector.this.cnt.getKWTManager(), r.automatch);

                    /* 793 */ if (RankupEpisodePanel.isShow()) {
                        /* 794 */ int oldeprank = StageConnector.this.userdata.guardian
                                .getEpisodeRank((set.rank.getRank(r.oldscore)).idx);
                        /* 795 */ if (eprank > oldeprank && StageConnector.this.panel != null) {
                            /* 796 */ RankupEpisodePanel rep = new RankupEpisodePanel(eprank);
                            /* 797 */ showAmpedDialog((Container) rep, StageConnector.this.cnt.getKWTManager(),
                                    r.automatch);
                        }
                    }

                    /* 801 */ TreasureRouletPanel t = new TreasureRouletPanel();
                    /* 802 */ t.setup(r, StageConnector.this);
                    /* 803 */ showAmpedDialog((Container) t, StageConnector.this.cnt.getKWTManager(), r.automatch);

                    /* 806 */ UserItems.TerminableItem[] rt = RandomTreasure.getRandomTreasureItems(r.randiteminfo);
                    /* 807 */ if (rt != null)
                        getTreasure(UserItems.convertTreasure((UserItems.Item[]) rt));
                    /* 808 */ if (r.randiteminfo2 != null && r.randiteminfo2[0] == 1)
                        getTreasure(UserItems.convertTreasure((UserItems.Item[]) set.getRandomTreasure2()
                                .getTreasure(r.randiteminfo2[5], r.randiteminfo2[1] - 1)));

                    /* 811 */ if (r.tourid == 3 && set.isLanguage("kr")) {
                        /* 812 */ KWTManager.getEventQueue().postEvent((Event) new ExitRoomEvent());
                    }
                }

                /* 816 */ } else if (this._r instanceof GuardianReward) {
                /* 817 */ GuardianReward r = (GuardianReward) this._r;
                /* 818 */ GuardianData.Episode ep = (set.getGuardian()).episode[r.playepisodeid & 0x3FFFFFFF];

                /* 820 */ if (ep != null && ep.kind == 3) {
                    /* 821 */ GuardianBafabonResultPanel p = new GuardianBafabonResultPanel(ep.timeattack);
                    /* 822 */ p.setup(StageConnector.this.cnt, r, StageConnector.this);
                    /* 823 */ p.show();
                } else {

                    /* 826 */ TournamentRewardPanel p = new TournamentRewardPanel();
                    /* 827 */ p.setup(r.oldscore, r.getscore, r.bonus);
                    /* 828 */ AmpedDialog.showAmpedDialog((Container) p, StageConnector.this.cnt.getKWTManager());

                    /* 830 */ if (RankupEpisodePanel.isShow() && eprank > r.oldeprank) {
                        /* 831 */ RankupEpisodePanel rep = new RankupEpisodePanel(eprank);
                        /* 832 */ AmpedDialog.showAmpedDialog((Container) rep, StageConnector.this.cnt.getKWTManager());
                    }

                    /* 835 */ if (r.newfacenum > 0) {
                        /* 836 */ BusteredPanel b = new BusteredPanel();
                        /* 837 */ b.setup(r);
                        /* 838 */ AmpedDialog.showAmpedDialog((Container) b, StageConnector.this.cnt.getKWTManager(),
                                4000);
                    }

                    /* 841 */ TreasureRouletPanel t = new TreasureRouletPanel();
                    /* 842 */ if (ep != null && ep.kind == 4) {
                        /* 843 */ t.setupAccsCore(r);
                        /* 844 */ boolean show = !(r.winitems != null && !t.isShowRoulet());
                        /* 845 */ if (show)
                            AmpedDialog.showAmpedDialog((Container) t, StageConnector.this.cnt.getKWTManager());

                        /* 847 */ int corenum = TreasureTable.getAccsCoreTreasuresNum(r);
                        /* 848 */ UserItems.Item[] get = (UserItems.Item[]) null;
                        /* 849 */ if (r.winitems != null && corenum < r.winitems.length) {
                            /* 850 */ get = new UserItems.Item[r.winitems.length - corenum];
                            /* 851 */ System.arraycopy(r.winitems, corenum, get, 0, get.length);
                        }
                        /* 853 */ if (!show || get != null) {
                            /* 854 */ TreasureGetPanel2 tg = new TreasureGetPanel2();
                            /* 855 */ tg.setup(show ? 0 : r.gamemoney, 0, get, r, StageConnector.this);
                            /* 856 */ AmpedDialog.showAmpedDialog((Container) tg,
                                    StageConnector.this.cnt.getKWTManager());
                        }
                    } else {
                        /* 859 */ t.setup(r, StageConnector.this);
                        /* 860 */ AmpedDialog.showAmpedDialog((Container) t, StageConnector.this.cnt.getKWTManager());
                    }
                }

                /* 865 */ if (r.getitems != null && r.getitems.length > 0)
                    getTreasure(r.getitems);

                /* 868 */ if (set.practiceextensionenabled && r.clear) {
                    /* 869 */ GetPracticeNpcPanel gpnp = new GetPracticeNpcPanel();
                    /* 870 */ int[] _id = gpnp.getNpcidList(r.enemynum, r.busteruid, r.enemy, r.myuid);
                    /* 871 */ int[] _num = gpnp.getNpcnumList(r.enemynum, r.busteruid, r.enemy, r.myuid);
                    /* 872 */ if (_id != null) {
                        /* 873 */ for (int n = 0; n < r.enemynum; n += 8) {
                            /* 874 */ int[] id = gpnp.getArray(_id, n, n + 8);
                            /* 875 */ int[] num = gpnp.getArray(_num, n, n + 8);
                            /* 876 */ if (n != 0)
                                gpnp = new GetPracticeNpcPanel();
                            /* 877 */ if (id != null && gpnp.setup(id, num)) {
                                /* 878 */ AmpedDialog.showAmpedDialog((Container) gpnp,
                                        StageConnector.this.cnt.getKWTManager());
                            }
                        }
                    }
                }

                /* 885 */ UserItems.TerminableItem[] rt = RandomGuardianTreasure.getRandomTreasureItems(r.randiteminfo);
                /* 886 */ if (rt != null)
                    getTreasure(UserItems.convertTreasure((UserItems.Item[]) rt));
                /* 887 */ if (r.randiteminfo2 != null && r.randiteminfo2[0] == 1)
                    getTreasure(UserItems.convertTreasure((UserItems.Item[]) set.getRandomTreasure2()
                            .getGuardianTreasure(r.randiteminfo2[5], r.randiteminfo2[1] - 1)));

                /* 890 */ if (set.isNavigationImmediate() && set.isLanguage("kr")
                        && StageConnector.this.userdata.talkflags.getFlag(30, 2)) {
                    /* 891 */ KWTManager.getEventQueue().postEvent((Event) new ExitRoomEvent());
                }
            }
        }

        public void showAmpedDialog(Container cnt, KWTManager m, boolean automatch) {
            /* 897 */ if (automatch) {
                /* 898 */ if (StageConnector.this.panel instanceof LoungeStagePanel) {
                    /* 899 */ LoungeStagePanel lp = (LoungeStagePanel) StageConnector.this.panel;
                    /* 900 */ AmpedDialog.showAmpedDialog(cnt, m, 0, (Container) lp, lp.menucnt.getEntry());
                } else {
                    /* 902 */ AmpedDialog.showAmpedDialog(cnt, m);
                }
            } else {
                /* 905 */ AmpedDialog.showAmpedDialog(cnt, m);
            }
        }

        public void getTreasure(UserItems.Item[] i) {
            /* 910 */ if (i == null || i.length <= 0)
                /* 911 */ return;
            int addgm = 0;
            /* 912 */ int addrm = 0;
            /* 913 */ UserItems.Item[] addi = (UserItems.Item[]) null;
            /* 914 */ ArrayList<UserItems.Item> a = new ArrayList();
            /* 915 */ for (int l = 0; l < i.length; l++) {
                /* 916 */ if (i[l] != null)
                    /* 917 */ switch ((i[l]).kind) {
                        case 22:
                            /* 918 */ addgm += (i[l]).id;
                            break;
                        /* 919 */ case 23:
                            addrm += (i[l]).id;
                            break;
                        /* 920 */ default:
                            a.add(i[l]);
                            break;
                    }

            }
            /* 923 */ if (a.size() > 0)
                addi = a.<UserItems.Item>toArray(new UserItems.Item[a.size()]);
            /* 924 */ TreasureGetPanel2 t = new TreasureGetPanel2();
            /* 925 */ t.setup(addgm, addrm, addi);
            /* 926 */ AmpedDialog.showAmpedDialog((Container) t, StageConnector.this.cnt.getKWTManager());
        }

        class ExitRoomEvent extends TimerEvent {
            ExitRoomEvent() {
                /* 931 */ super(0, null);
            }

            public void timerInvoked(TimerEvent e) {
                /* 935 */ Container c = StageConnector.this.getContainer();
                /* 936 */ ActionEvent ae = new ActionEvent(33024, 0, "exit");
                /* 937 */ ae.setSource(c);
                /* 938 */ c.postEvent((Event) ae);
            }
        }
    }

    class EventPopupThread
            extends Thread {
        public void run() {
            try {
                /* 950 */ Thread.sleep(500L);
                /* 951 */ } catch (Exception exception) {
            }

            /* 954 */ if (StageConnector.this.panel instanceof MyRoomStagePanel) {
                /* 955 */ ((MyRoomStagePanel) StageConnector.this.panel).showEventPopup();
            }
        }
    }

    public void processAddModel(BasicAmpedEvent e) {
    }

    public void processClockSyncResponse(ClockSyncEvent e) {
    }

    public void processLock(BasicAmpedEvent e) {
        /* 983 */ sendToServer((AmpedEvent) BasicAmpedEvent.create(this.myid, 59, e.argi));
    }

    public void processDefaultEvent(AmpedEvent e) {
    }

    public abstract void updateStage();

    public abstract boolean sendToServer(AmpedEvent paramAmpedEvent);

    public void sendBasicToServer(int id) {
        /* 1005 */ sendToServer((AmpedEvent) BasicAmpedEvent.create(this.myid, id));
    }

    abstract void processLogout(BasicAmpedEvent paramBasicAmpedEvent);

    public void processGameReward(BasicAmpedEvent e) {
        /* 1013 */ this.gamereward = e.args[0];

        /* 1015 */ GameSetting.getSetting().println("processGameReward" + this.gamereward);
    }

    public void processStageCamera(BasicAmpedEvent e) {
        Camerawork.Event cw;
        /* 1020 */ Object[] a = (Object[]) e.args[0];
        /* 1021 */ int cwid = ((Number) a[0]).intValue();

        /* 1032 */ if (cwid == 6) {

            /* 1034 */ if (this.camerawork instanceof Camerawork.BattleReviseEye) {
                /* 1035 */ Camerawork.Battle c = new Camerawork.Battle();
                /* 1036 */ c.setFocusData(null, ((Camerawork.Battle) this.camerawork).getFocusData());
                /* 1037 */ setCamerawork((Camerawork) c);
            }
            return;
        }
        /* 1041 */ if (cwid == 5) {
            /* 1042 */ if (this.camerawork instanceof Camerawork.Battle) {
                /* 1043 */ Camerawork.BattleReviseEye c = new Camerawork.BattleReviseEye();
                /* 1044 */ c.setFocusData((Vector3D) a[1], ((Camerawork.Battle) this.camerawork).getFocusData());
                /* 1045 */ setCamerawork((Camerawork) c);
            }
            return;
        }
        /* 1049 */ if (cwid == 4) {
            /* 1050 */ if (this.camerawork instanceof Camerawork.Event) {
                /* 1051 */ setCamerawork(this.orgcamerawork);
            }

            return;
        }
        /* 1056 */ if (!(this.camerawork instanceof Camerawork.Event)) {
            /* 1057 */ cw = new Camerawork.Event();
            /* 1058 */ setCamerawork((Camerawork) cw);
        } else {
            /* 1060 */ cw = (Camerawork.Event) this.camerawork;
        }
        /* 1062 */ switch (cwid) {
            case 0:
                /* 1064 */ cw.set((Vector3D) a[1], (Vector3D) a[2]);
                break;
            case 1:
                /* 1067 */ cw.move((Vector3D) a[1], ((Number) a[2]).intValue());
                break;
            case 2:
                /* 1070 */ cw.lookat((Vector3D) a[1], ((Number) a[2]).intValue());
                break;
            case 3:
                /* 1073 */ cw.moveTo((Vector3D) a[1], (Vector3D) a[2], ((Number) a[3]).intValue());
                break;
        }
    }

    public void processClearEventBattle(BasicAmpedEvent e) {
    }

    public void processInvokeGameServer(InvokeGameEvent e) {
        /* 1082 */ GetAmped2 ga2 = GetAmped2.getAmped2();
        /* 1083 */ if (ga2 != null) {
            /* 1084 */ StageServer.BONUS_STAR = e.bonus_star;
            /* 1085 */ StageServer.BONUS_SCORE = e.bonus_score;
            /* 1086 */ StageServer.BONUS_GM = e.bonus_gm;
            /* 1087 */ StageServer.BONUS_SKILL = e.bonus_skill;
            /* 1088 */ int ep = ((Number) e.game).intValue();
            /* 1089 */ ga2.invokeEpisode(this, ep, "");
        }
    }

    public void processInvokeRewardKey(BasicAmpedEvent e) {
        /* 1094 */ GetAmped2 ga2 = GetAmped2.getAmped2();
        /* 1095 */ if (ga2 != null) {
            /* 1096 */ ga2.invokeEpisodeRecievedRewardKey((Key) e.args[0]);
        }
    }

    public void processFragment(FragmentEvent e) {
        /* 1101 */ FragmentEvent[] fe = (FragmentEvent[]) this.fragments.get(e.seq);

        /* 1103 */ if (fe == null) {
            /* 1104 */ fe = new FragmentEvent[e.num];
            /* 1105 */ this.fragments.put(e.seq, fe);
        }
        /* 1107 */ fe[e.idx] = e;
        /* 1108 */ for (int i = 0; i < fe.length;) {
            if (fe[i] == null)
                return;
            i++;
        }

        /* 1111 */ AmpedEvent ae = FragmentEvent.mergeEvent(fe);

        /* 1113 */ processAmpedEvent(ae);
        /* 1114 */ eventMerged(ae);
        /* 1115 */ this.fragments.remove(e.seq);
    }

    void eventMerged(AmpedEvent e) {
    }

    public void processViewerRequest(BasicAmpedEvent e) {
    }

    public void processVoiceChat(MatchingEvent e) {
    }

    void panelSetup() {
        if (this.canvas == null) {
            this.initStageCanvas();
        }

        int mode = this.stage.getStageMode();
        if (this.panel != null) {
            this.cnt.remove(this.panel);
            this.panel = null;
        }

        this.cnt.clear();
        this.canvas.p.sx = this.canvas.p.sy = 1.0F;
        this.canvas.setVisible(true);
        Container c = null;
        float far = 120.0F;
        String sfar = this.stage.data.getParam("draw_far");
        if (sfar != null) {
            far = Float.valueOf(sfar);
        }

        this.camera.set(3.0F, far, 30.0F, this.canvas.bnd.w / this.canvas.bnd.h);
        switch (mode) {
            case 1:
                this.camerawork = new Camerawork.Battle();
                c = new TournamentStagePanel(this, this.cw, this.ch);
                break;
            case 2:
                this.camerawork = new Camerawork.Room();
                c = new MatchingRoomStagePanel(this, this.cw, this.ch);
                break;
            case 3:
            case 11:
            case 12:
            case 13:
            case 17:
                this.camerawork = new Camerawork.Lobby();
                c = new LoungeStagePanel(this, this.cw, this.ch);
                break;
            case 4:
                this.camerawork = new Camerawork.Battle();
                c = new StreetfightStagePanel(this, this.cw, this.ch);
                break;
            case 5:
                this.camerawork = new Camerawork.Battle();
                c = new ArcadeStagePanel(this, this.cw, this.ch);
                break;
            case 6:
                this.camerawork = new Camerawork.Room();
                c = new GuardianRoomStagePanel(this, this.cw, this.ch);
                break;
            case 7:
                GuardianData gd = GameSetting.getSetting().getGuardian();
                int kind = gd.episode[this.stage.currentepisode & 1073741823].kind;
                if (kind == 1) {
                    this.camerawork = new Camerawork.BattleWide();
                } else if (kind != 3 && kind != 4) {
                    this.camerawork = new Camerawork.Battle();
                } else {
                    this.camerawork = new Camerawork.BattleWide2();
                }

                c = new ArcadeStagePanel(this, this.cw, this.ch);
                break;
            case 8:
            case 22:
            case 23:
            case 24:
            case 25:
            default:
                this.camerawork = new Camerawork.Battle();
                c = new TestPlayStagePanel(this, this.cw, this.ch);
                break;
            case 9:
                this.camerawork = new Camerawork.Battle();
                c = new EventStagePanel(this, this.cw, this.ch);
                break;
            case 10:
                this.camerawork = new Camerawork.Battle();
                c = new EventBattleStagePanel(this, this.cw, this.ch);
                break;
            case 14:
                this.camerawork = new Camerawork.Room();
                c = new MyRoomStagePanel(this, this.cw, this.ch);
                break;
            case 15:
                this.camerawork = new Camerawork.Lobby();
                c = new GuildRoomStagePanel(this, this.cw, this.ch);
                break;
            case 16:
                this.camerawork = new Camerawork.Battle();
                if (GameSetting.getSetting().practiceextensionenabled) {
                    c = new NewPracticeStagePanel(this, this.cw, this.ch, false);
                } else {
                    c = new PracticeStagePanel(this, this.cw, this.ch, false);
                }
                break;
            case 18:
                this.camerawork = new Camerawork.BattleDuel();
                c = new TournamentStagePanel(this, this.cw, this.ch);
                break;
            case 19:
                this.camerawork = new Camerawork.Battle();
                new StreetfightStagePanel(this, this.cw, this.ch, true);
            case 26:
                this.camerawork = new Camerawork.BattleUnitedfight();
                c = new UnitedfightStagePanel(this, this.cw, this.ch);
                break;
            case 20:
                this.camerawork = new Camerawork.BattleWide2();
                c = new TeamStreetfightStagePanel(this, this.cw, this.ch, true);
                break;
            case 21:
                this.camerawork = new Camerawork.Battle();
                c = new PracticeStagePanel(this, this.cw, this.ch, true);
                CaptureTool ct = new CaptureTool(this, (PracticeStagePanel) c);
                ct.setRect(0.0F, this.ch, ct.bnd.w, ct.bnd.h);
                Rect2D var10000 = this.cnt.bnd;
                var10000.h += ct.bnd.h;
                this.cnt.add(ct);
                break;
            case 27:
                this.camerawork = new Camerawork.Battle();
                c = new DogfightStagePanel(this, this.cw, this.ch);
        }

        this.orgcamerawork = this.camerawork;
        if (this.stage.ceye != null && this.stage.cto != null) {
            this.processStageCamera(
                    BasicAmpedEvent.create(-1, 97, new Object[] { new Integer(0), this.stage.ceye, this.stage.cto }));
        }

        this.cnt.insert((Component) c, 0);
        ((Container) c).performMethod("show");
        this.panel = (Container) c;
        ((Container) c).requestFocus();
        TopPanel top = this.getTopPanel();
        if (this.getPlayer() != null && this.getPlayer().ctr instanceof PlayerController) {
            PlayerController ctr = (PlayerController) this.getPlayer().ctr;
            if (top != null && ctr.isApplyKey()) {
                top.chat.setInputFocus(top.chat.isInputFocused());
                top.chat.enableController(true);
            }
        }

    }

    public void conditionChange(int stagemode) {
        /* 1305 */ GetAmped2 ga2 = GetAmped2.getAmped2();
        /* 1306 */ if (ga2 == null || ga2.getConnectionState() != 2)
            return;
        /* 1308 */ int c = 0;
        /* 1309 */ switch (stagemode) {
            case 2:
            case 6:
                /* 1312 */ c = 1;
                break;
            case 1:
            case 4:
            case 5:
            case 7:
            case 10:
            case 16:
            case 18:
            case 19:
            case 20:
            case 26:
            case 27:
                /* 1325 */ c = 2;
                break;
        }

        /* 1332 */ ga2.changeCondition(c);
    }

    class SceneRegister
            implements GLRunnable {
        public void run(GLGraphics g) {
            /* 1341 */ if (StageConnector.this.stage == null)
                /* 1342 */ return;
            ClientSetting cset = ClientSetting.getSetting();
            /* 1343 */ StageConnector.this.stage.scene.enableShadowMapping(!cset.disp.noshadow);
            /* 1344 */ StageConnector.this.stage.scene.enablePostEffect(cset.disp.postefx);

            /* 1346 */ StageConnector.this.stage.scene.register(g);
        }
    }

    public static class Local
            extends StageConnector {
        StageServer server;

        /* 1356 */ private ArrayList<AmpedEvent> sendqueue = new ArrayList();
        /* 1357 */ private ArrayList<AmpedEvent> recvqueue = new ArrayList();

        boolean relayon;

        public StageConnector orgstagecon;

        public void setStageServer(StageServer ss) {
            /* 1364 */ this.server = ss;
            /* 1365 */ setStage((StageClient) ss.stage);
        }

        public StageServer getStageServer() {
            /* 1368 */ return this.server;
        }

        public void setLoginInfo(int eid) {
            /* 1372 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 1373 */ this.entryid = eid;
                /* 1374 */ if (this.userdata != null)
                    this.server.putUserData(eid, this.userdata);

            }
        }

        public boolean login() {
            /* 1380 */ return login(false);
        }

        public boolean login(boolean tool) {
            try {
                /* 1384 */ int ret = this.server.loginFromLocal(this.entryid,
                        (ClientSetting.getSetting()).ctr.guardmode, this, tool);
                /* 1385 */ } catch (Exception e) {
                /* 1386 */ e.printStackTrace();
                /* 1387 */ return false;
            }
            /* 1389 */ setGameState(1);

            /* 1391 */ this.server.update();

            /* 1393 */ StageSetupEvent sse = this.stage.getStageSetupEvent(101);
            /* 1394 */ processStageSetup(sse);

            /* 1396 */ if (this.playback != null)
                this.playback.addRecord((AmpedEvent) sse, this.stage.clock.getTime());
            /* 1397 */ return true;
        }

        public void processStageSetup(StageSetupEvent e) {
            /* 1405 */ checkPlaybackRecord(e);

            /* 1408 */ setPlayer((PlayerViewModel) this.stage.getPlayerForEntryID(this.entryid));

            /* 1411 */ GLRegistry.registerProc(new StageConnector.SceneRegister());

            /* 1414 */ if (e == null || e.id == 101) {
                /* 1415 */ sendBasicToServer(103);
            } else {
                /* 1417 */ initCamerawork();
            }
        }

        void processLogout(BasicAmpedEvent e) {
        }

        public void processViewerRequest(BasicAmpedEvent e) {
            /* 1428 */ if (this.orgstagecon == null)
                /* 1429 */ return;
            this.relayon = true;

            /* 1431 */ synchronized (this.server) {
                /* 1432 */ StageSetupEvent stageSetupEvent = this.server.getStage().getStageSetupEvent(101);
                /* 1433 */ RelayEvent re = RelayEvent.create(e.srcid, (AmpedEvent) stageSetupEvent);
                /* 1434 */ this.orgstagecon.sendToServer((AmpedEvent) re);
            }
        }

        public void updateStage() {
            /* 1439 */ if (this.stage == null)
                return;
            /* 1441 */ synchronized (this.sendqueue) {
                /* 1442 */ for (int l = 0; l < this.sendqueue.size(); l++) {
                    /* 1443 */ AmpedEvent e = this.sendqueue.get(l);
                    /* 1444 */ this.server.processLocalAmpedEvent(e);
                    /* 1445 */ if (this.panel != null) {
                        /* 1446 */ this.panel.setParam(501, e);
                    }
                    /* 1448 */ if (this.camerawork != null) {
                        /* 1449 */ this.camerawork.processAmpedEvent(e);
                    }
                }

                /* 1453 */ this.sendqueue.clear();
            }
            /* 1455 */ synchronized (this.recvqueue) {
                /* 1456 */ for (int l = 0; l < this.recvqueue.size(); l++) {
                    /* 1457 */ AmpedEvent e = this.recvqueue.get(l);
                    /* 1458 */ processAmpedEvent(e);
                    /* 1459 */ if (this.panel != null) {
                        /* 1460 */ this.panel.setParam(500, e);
                    }
                    /* 1462 */ if (this.camerawork != null) {
                        /* 1463 */ this.camerawork.processAmpedEvent(e);
                    }
                }

                /* 1467 */ this.recvqueue.clear();
            }
            /* 1469 */ if (this.ctr != null && (GameSetting.getSetting()).sdoa && this.stage.isLoungeMode()) {
                /* 1470 */ KWTManager m = this.cnt.getKWTManager();
                /* 1471 */ ((PlayerController) this.ctr).setMouseOut((m != null && !m.isMouseIn()));
            }
            /* 1473 */ this.server.update();

            /* 1475 */ if (this.camerawork != null && this.player != null && this.camera != null) {
                /* 1476 */ this.camerawork.update(getDt() * 0.001F, this.camera);
                /* 1477 */ this.camerawork.getFocusPosition(this.stage.scene.focuspos);
                /* 1478 */ this.stage.scene.setStageLayer(this.camerawork.getCurrentLayer());
            }
        }

        public boolean sendToServer(AmpedEvent e) {
            /* 1483 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 1484 */ if (ga2 != null && ga2.demo && this.chatcommand.analysis(e))
                return false;

            /* 1486 */ if (ga2 != null)
                e.conid = ga2.getConnectID();

            /* 1488 */ synchronized (this.sendqueue) {
                /* 1489 */ this.sendqueue.add(e);
            }
            /* 1491 */ return true;
        }

        public void receiveFromServer(AmpedEvent e, boolean broadcast) {
            /* 1497 */ synchronized (this.recvqueue) {
                /* 1498 */ this.recvqueue.add(e);
            }
            /* 1500 */ if (broadcast && this.relayon && this.orgstagecon != null) {
                /* 1501 */ RelayEvent re = RelayEvent.create(-1, e);
                /* 1502 */ this.orgstagecon.sendToServer((AmpedEvent) re);
            }
        }

        public boolean eventFinished() {
            /* 1508 */ return false;
        }

        public void setEventScene(int eid) {
        }

        public int getEventID() {
            /* 1515 */ return 0;
        }
    }

    public static class EventScene
            extends Local {
        public static volatile ChallengeRecord challengerec;

        /* 1526 */ private int eventid = -1;
        /* 1527 */ public int challengeid = -1;

        EventSceneData ed;

        boolean init;
        boolean training;
        boolean retry;
        int[] hit;
        boolean[] hit_tech;
        Runnable finishhandler;
        TalkPanel talkPanel;
        TalkPanel talkPanel2;

        public EventSceneData getEventScene() {
            /* 1541 */ return this.ed;
        }

        public int getEventID() {
            /* 1545 */ return this.eventid;
        }

        public boolean isTitleTutorial() {
            /* 1550 */ return !(this.eventid != 3 && this.eventid != 26);
        }

        public boolean isTutorial() {
            /* 1554 */ return !(this.eventid != 41 && this.eventid != 120);
        }

        public void setChallenge(int cid) {
            /* 1558 */ setChallenge(cid, false);
        }

        public void setChallenge(int cid, boolean retry) {
            /* 1561 */ this.challengeid = cid;
            /* 1562 */ ChallengeData cd = (GameSetting.getSetting()).challenge[cid];
            /* 1563 */ setEventScene(cd.eventid, cid, retry);
            /* 1564 */ this.challengeid = cid;
            /* 1565 */ this.stage.script.challengeid = this.challengeid;
            /* 1566 */ this.training = false;
        }

        public void setEventScene(int eid) {
            /* 1570 */ setEventScene(eid, 0, false);
        }

        public void setEventScene(int eid, int cid, boolean _retry) {
            /* 1573 */ this.retry = _retry;
            /* 1574 */ this.eventid = eid;
            /* 1575 */ this.challengeid = -1;
            /* 1576 */ EventSceneData d = GameSetting.getSetting().getEventScene(eid);
            /* 1577 */ if (d == null) {
                /* 1578 */ System.out.println("setEventScene eid:" + eid);
            }
            /* 1580 */ this.ed = d;
            /* 1581 */ this.autoshutteropen = d.autoshutteropen;
            /* 1582 */ StageClient sc = new StageClient();
            /* 1583 */ StageServer ss = new StageServer(sc);
            /* 1584 */ setStageServer(ss);

            /* 1586 */ UserData _ud = (UserData) this.userdata.clone();
            /* 1587 */ if (_ud.info.weapon != 0) {
                /* 1588 */ UserItems.Item uii = _ud.items.getItem(7, _ud.info.weapon);
                /* 1589 */ WeaponData wd = GameSetting.getSetting().getWeapon(_ud.info.weapon);
                /* 1590 */ if (wd != null && uii != null) {
                    /* 1591 */ uii.num = (short) Math.min(uii.num, wd.maxstock);
                }
            }
            /* 1594 */ ss.putUserData(this.entryid, _ud);

            /* 1597 */ StageRule rule = new EventRule();
            /* 1598 */ if (this.retry)
                ((EventRule) rule).setRetryChallenge(this.retry);
            /* 1599 */ rule.setItemInfo(d.itemInfo, null);
            /* 1600 */ sc.setStageRule(rule);

            /* 1604 */ ss.setupEvent(d.stage, d.stagemode, cid);

            /* 1606 */ sc.bgmid = d.bgmid;
            /* 1607 */ sc.unlimitedweapon = d.unlimitedweapon;
            /* 1608 */ setGameState(1);
        }

        public void setPreviewStage(String stage) {
            /* 1612 */ StageClient sc = new StageClient();
            /* 1613 */ StageServer ss = new StageServer(sc);
            /* 1614 */ setStageServer(ss);
            /* 1615 */ ss.setupPreviewStage(stage, 9, 0);
            /* 1616 */ StageSetupEvent sse = getStage().getStageSetupEvent(101);
            /* 1617 */ processStageSetup(sse);
        }

        public void setEventStage(Object stagedata, int mode) {
            /* 1621 */ this.server.setupEvent(stagedata, mode, -1);

            /* 1623 */ setPlayer((PlayerViewModel) this.stage.getPlayerForEntryID(this.entryid));
        }

        public class EventRule extends DeathmatchRule {
            int game_result;
            int scrid;
            boolean gameover;
            boolean showGameover;
            public boolean retryChallenge;

            public EventRule() {
                /* 1628 */ this.scrid = -1;
            }

            public Speach[][] getSpeach() {
                /* 1634 */ return StageConnector.EventScene.this.ed.speach;
            }

            protected void setSpeachScriptID(int scrid_) {
                /* 1637 */ this.scrid = scrid_;
            }

            protected void sendEventSpc(Speach spc, int id, int group) {
                /* 1640 */ spc.sendEvent(id, group, this.stage, this.scrid);
                /* 1641 */ this.scrid = -1;
            }

            public void setRetryChallenge(boolean b) {
                /* 1644 */ this.retryChallenge = b;
            }

            public void updateRule() {
                /* 1647 */ updateSpeach();
            }

            public void stageClear(int result) {
                /* 1650 */ this.gameset = true;
                /* 1651 */ this.game_result = result;
                /* 1652 */ this.server.getTask().gameset();
            }

            public void showGameover(boolean show) {
                /* 1655 */ this.showGameover = show;
            }

            public boolean showGameover() {
                /* 1658 */ return this.showGameover;
            }

            public void setGameResult(int result) {
                /* 1661 */ this.game_result = result;
            }

            public int getGameResult() {
                /* 1664 */ return this.game_result;
            }

            public void checkGameset() {
                /* 1668 */ if (!this.gameover) {
                    /* 1669 */ int num = this.server.getPlayerEntryNum();
                    /* 1670 */ for (int l = 0; l < num; l++) {
                        /* 1671 */ PlayerModel m = (this.server.playerEntryAt(l)).player;
                        /* 1672 */ if (!m.isDead()) {
                            return;
                        }
                    }
                    /* 1676 */ if (this.stage.script.challengeClear) {
                        return;
                    }
                    /* 1679 */ System.out.println("GAME OVER............");

                    /* 1681 */ this.gameset = true;
                    /* 1682 */ this.gameover = true;
                    /* 1683 */ StageConnector.EventScene.this.init = false;

                    /* 1685 */ if (this.game_result == 0) {
                        /* 1686 */ this.game_result = -1;

                        /* 1688 */ showGameover(true);
                        /* 1689 */ } else if (this.game_result > 0) {
                        /* 1690 */ this.game_result *= -1;
                    }

                    /* 1694 */ KWTClock clock = (KWTClock) getStage().getClock();
                    /* 1695 */ ((AdjustableClock) clock.getOriginal()).setTimeScale(0.30000001192092896D);
                }

                /* 1698 */ if (!showGameover())
                    /* 1699 */ return;
                this.stage.script.challengeGameover();
                /* 1700 */ this.server.gameset();
            }

            private void processRuleEvent_PLAYER_ATTACK(PlayerModel s) {
                /* 1704 */ if (s.currenttech == null || s.currenttech.data == null)
                    return;
                /* 1706 */ TechData techData = (s.getCurrentTech()).data;
                /* 1707 */ int idx = techIndexOf(s.userinfo, techData.states, techData.requires, techData.button);

                /* 1709 */ setHitTech(idx, s.userinfo);
            }

            private int techIndexOf(UserInfo i, int stat, int req, int btn) {
                /* 1713 */ if (i.accs > 0) {
                    /* 1714 */ AccessoryData data = GameSetting.getSetting().getAccs(i.accs);
                    /* 1715 */ TechData[] tec = (data.skill[data.getActiveSkillID(i.style)]).tec;
                    /* 1716 */ if (tec != null) {
                        /* 1717 */ int len = tec.length;
                        /* 1718 */ for (int l = len - 1; l >= 0; l--) {
                            /* 1719 */ TechData t = tec[l];
                            /* 1720 */ if (t != null && t.states == stat && t.requires == req && t.button == btn)
                                return l;
                        }
                    }
                }
                /* 1724 */ return -1;
            }

            public void processRuleEvent(int id, Object src, Object dst) {
                /* 1728 */ switch (id) {
                    case 1:
                        /* 1730 */ if (dst instanceof PlayerModel) {
                            /* 1731 */ PlayerModel d = (PlayerModel) dst;
                            /* 1732 */ if (d.getNPCID() >= 0 && (d.getGroup() == 5 || d.getGroup() == 7) &&
                            /* 1733 */ src instanceof PlayerModel) {
                                /* 1734 */ PlayerModel s = (PlayerModel) src;
                                /* 1735 */ if (!(s.ctr instanceof ga2.stage.ai.AiController)) {

                                    /* 1738 */ AmpedStageData.Entry e = d.stageEntry;
                                    /* 1739 */ boolean valid = false;

                                    /* 1741 */ String op = e.option;

                                    /* 1743 */ if ((GameSetting.getSetting()).istutorialbuttoncheck) {
                                        /* 1744 */ switch (s.getPlayerActionID()) {
                                            case 0:
                                                /* 1745 */ if (op.equals("weak") || op.equals("walkatk")
                                                        || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1746 */ case 1:
                                                if (op.equals("strong") || op.equals("walkatk") || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1747 */ case 2:
                                                if (op.equals("wdash") || op.equals("dashatk") || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1748 */ case 3:
                                                if (op.equals("sdash") || op.equals("dashatk") || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1749 */ case 4:
                                                if (op.equals("jweak") || op.equals("jumpatk") || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1750 */ case 5:
                                                if (op.equals("jstrong") || op.equals("jumpatk") || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1751 */ case 6:
                                                if (op.equals("weapon") || op.equals("attack"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1752 */ case 7:
                                                if (op.equals("special") || op.equals("ssspecial"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1753 */ case 8:
                                                if (op.equals("sspecial") || op.equals("ssspecial"))
                                                    valid = true;
                                                showMark(valid, false);
                                                break;
                                            /* 1754 */ case 9:
                                                if (op.equals("counter"))
                                                    valid = true;
                                                addHitCount(valid, false);
                                                break;
                                            /* 1755 */ default:
                                                addHitCount(valid, false);
                                                break;
                                        }

                                    } else {
                                        /* 1758 */ switch (s.getPlayerActionID()) {
                                            case 9:
                                                /* 1759 */ if (op.equals("counter"))
                                                    valid = true;
                                                addHitCount(valid, false);
                                                break;
                                            /* 1760 */ default:
                                                addHitCount(valid, false);
                                                break;
                                        }

                                    }
                                    /* 1764 */ processRuleEvent_PLAYER_ATTACK(s);
                                }
                            }

                            /* 1768 */ if (d.isMyPlayer() && src instanceof PlayerModel) {
                                /* 1769 */ PlayerModel s = (PlayerModel) src;
                                /* 1770 */ AmpedStageData.Entry e = s.stageEntry;
                                /* 1771 */ boolean valid = false;
                                /* 1772 */ switch (d.getPlayerActionID(true)) {
                                    case 10:
                                        /* 1773 */ if (e.option.equals("guard"))
                                            valid = true;
                                        break;
                                    /* 1774 */ case 11:
                                        if (e.option.equals("pguard"))
                                            valid = true;
                                        break;
                                }

                                /* 1776 */ addHitCount(valid, false);
                                /* 1777 */ processRuleEvent_PLAYER_ATTACK(d);
                            }
                        }
                        break;
                    case 0:
                        /* 1782 */ if (dst instanceof PlayerModel) {
                            /* 1783 */ PlayerModel d = (PlayerModel) dst;
                            /* 1784 */ if (d.getNPCID() >= 0 && (d.getGroup() == 5 || d.getGroup() == 7) &&
                            /* 1785 */ src instanceof PlayerModel) {
                                /* 1786 */ PlayerModel s = (PlayerModel) src;
                                /* 1787 */ if (!(s.ctr instanceof ga2.stage.ai.AiController)) {

                                    /* 1791 */ AmpedStageData.Entry e = d.stageEntry;

                                    /* 1793 */ boolean valid = false;

                                    /* 1795 */ String op = e.option;

                                    /* 1797 */ switch (s.getPlayerActionID()) {
                                        case 0:
                                            /* 1798 */ if (op.equals("weak") || op.equals("walkatk")
                                                    || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1799 */ case 1:
                                            if (op.equals("strong") || op.equals("walkatk") || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1800 */ case 2:
                                            if (op.equals("wdash") || op.equals("dashatk") || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1801 */ case 3:
                                            if (op.equals("sdash") || op.equals("dashatk") || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1802 */ case 4:
                                            if (op.equals("jweak") || op.equals("jumpatk") || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1803 */ case 5:
                                            if (op.equals("jstrong") || op.equals("jumpatk") || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1804 */ case 6:
                                            if (op.equals("weapon") || op.equals("attack"))
                                                valid = true;
                                            break;
                                        /* 1805 */ case 7:
                                            if (op.equals("special") || op.equals("ssspecial"))
                                                valid = true;
                                            break;
                                        /* 1806 */ case 8:
                                            if (op.equals("sspecial") || op.equals("ssspecial"))
                                                valid = true;
                                            break;
                                        /* 1807 */ case 9:
                                            if (op.equals("counter"))
                                                valid = true;
                                            break;
                                    }

                                    /* 1809 */ addHitCount(valid, true);
                                    /* 1810 */ processRuleEvent_PLAYER_ATTACK(s);
                                }
                            }
                        }
                        break;

                    case 11:
                        /* 1819 */ if (dst instanceof Boolean) {
                            /* 1820 */ Boolean b = (Boolean) dst;
                            /* 1821 */ addHitCount(b.booleanValue(), true);
                        }
                        break;
                }
                /* 1825 */ super.processRuleEvent(id, src, dst);
            }

            private void setHitTech(int id, UserInfo ui) {
                /* 1829 */ if (id < 0)
                    /* 1830 */ return;
                if (StageConnector.EventScene.this.hit_tech == null) {
                    /* 1831 */ int max = 50;
                    /* 1832 */ AccessoryData data = GameSetting.getSetting().getAccs(ui.accs);
                    /* 1833 */ TechData[] tec = (data.skill[data.getActiveSkillID(ui.style)]).tec;
                    /* 1834 */ if (tec != null && tec.length > max) {
                        /* 1835 */ StageConnector.EventScene.this.hit_tech = new boolean[tec.length];
                    } else {
                        /* 1837 */ StageConnector.EventScene.this.hit_tech = new boolean[max];
                    }
                }
                /* 1840 */ StageConnector.EventScene.this.hit_tech[id] = true;
            }

            public boolean isHitTech(int tech) {
                /* 1845 */ if (StageConnector.EventScene.this.hit_tech == null)
                    return false;
                /* 1846 */ return StageConnector.EventScene.this.hit_tech[tech];
            }

            public boolean isHitTech(int[] tech) {
                /* 1850 */ if (StageConnector.EventScene.this.hit_tech == null)
                    return false;
                /* 1851 */ for (int i = 0; i < tech.length; i++) {
                    /* 1852 */ if (StageConnector.EventScene.this.hit_tech.length < tech[i])
                        return false;
                    /* 1853 */ if (!StageConnector.EventScene.this.hit_tech[tech[i]])
                        return false;
                }
                /* 1855 */ return true;
            }

            private void addHitCount(boolean valid, boolean showtip) {
                /* 1859 */ if (StageConnector.EventScene.this.hit == null)
                    StageConnector.EventScene.this.hit = new int[2];
                /* 1860 */ StageConnector.EventScene.this.hit[valid ? 0
                        : 1] = StageConnector.EventScene.this.hit[valid ? 0 : 1] + 1;

                /* 1863 */ if (StageConnector.EventScene.this.getStagePanel() instanceof EventBattleStagePanel) {
                    /* 1864 */ EventBattleStagePanel sp = (EventBattleStagePanel) StageConnector.EventScene.this
                            .getStagePanel();
                    /* 1865 */ if (sp.targetCountPanel2 != null) {
                        /* 1866 */ sp.targetCountPanel2.setNpc(StageConnector.EventScene.this.hit[0], valid);

                        /* 1869 */ if (showtip && !valid && GameSetting.getSetting().isLanguage("kr")) {
                            /* 1870 */ sp.showLesson();
                        }
                    }
                }
            }

            private void showMark(boolean valid, boolean showtip) {
                /* 1878 */ if (StageConnector.EventScene.this.getStagePanel() instanceof EventBattleStagePanel) {
                    /* 1879 */ EventBattleStagePanel sp = (EventBattleStagePanel) StageConnector.EventScene.this
                            .getStagePanel();
                    /* 1880 */ if (sp.targetCountPanel2 != null) {
                        /* 1881 */ sp.targetCountPanel2.showMark(valid);

                        /* 1884 */ if (showtip && !valid && GameSetting.getSetting().isLanguage("kr")) {
                            /* 1885 */ sp.showLesson();
                        }
                    }
                }
            }

            public int getHitCount(boolean valid) {
                /* 1892 */ if (StageConnector.EventScene.this.hit != null)
                    return StageConnector.EventScene.this.hit[valid ? 0 : 1];
                /* 1893 */ return 0;
            }

            public void clearHitCount() {
                /* 1897 */ if (StageConnector.EventScene.this.hit != null) {
                    /* 1898 */ for (int i = 0; i < StageConnector.EventScene.this.hit.length; i++) {
                        /* 1899 */ StageConnector.EventScene.this.hit[i] = 0;
                    }

                    /* 1902 */ if (StageConnector.EventScene.this.getStagePanel() instanceof EventBattleStagePanel &&
                    /* 1903 */ ((EventBattleStagePanel) StageConnector.EventScene.this
                            .getStagePanel()).targetCountPanel2 != null) {
                        /* 1904 */ ((EventBattleStagePanel) StageConnector.EventScene.this
                                .getStagePanel()).targetCountPanel2.hide();
                    }
                }
            }

            public void clearHitTechCount() {
                /* 1911 */ if (StageConnector.EventScene.this.hit_tech != null) {
                    /* 1912 */ for (int i = 0; i < StageConnector.EventScene.this.hit_tech.length; i++) {
                        /* 1913 */ StageConnector.EventScene.this.hit_tech[i] = false;
                    }
                }
            }
        }

        public void setTraining() {
            /* 1924 */ this.eventid = -1;
            /* 1925 */ this.challengeid = -1;
            /* 1926 */ this.training = true;
            /* 1927 */ this.ed = null;
            /* 1928 */ this.autoshutteropen = true;
            /* 1929 */ StageClient sc = new StageClient();
            /* 1930 */ StageServer ss = new StageServer(sc);
            /* 1931 */ setStageServer(ss);
            /* 1932 */ ss.putUserData(this.entryid, this.userdata);
            /* 1933 */ ss.setupPractice(0);
            /* 1934 */ setContainerRect(0.0F, 0.0F, this.cw, this.ch);

            /* 1936 */ setGameState(1);
        }

        public void setEventFinishHandler(Runnable r) {
            /* 1940 */ this.finishhandler = r;
        }

        public void processStageSetup(StageSetupEvent e) {
            /* 1955 */ if (this.ed != null) {
                /* 1956 */ this.stage.bgmid = this.ed.bgmid;
            }

            /* 1960 */ PlayerViewModel p = (PlayerViewModel) this.stage.getPlayerForEntryID(this.entryid);
            /* 1961 */ if (p != null)
                setPlayer(p);
            /* 1962 */ if (this.stage.isLoungeMode() && this.ctr != null)
                this.ctr.setEnabled(false);

            /* 1965 */ GLRegistry.registerProc(new StageConnector.SceneRegister());

            /* 1970 */ if (this.stage.getStageMode() == 10 && !this.retry) {
                /* 1971 */ this.stage.setControllable(true);
            }

            /* 1975 */ if (this.stage.getStageMode() == 16 || this.stage.getStageMode() == 21) {
                /* 1976 */ this.stage.setControllable(true);

                /* 1978 */ this.cnt.addActionListener(new TrainingListener());

                /* 1980 */ if (e == null || e.id == 101) {
                    /* 1981 */ sendBasicToServer(103);
                } else {
                    /* 1983 */ initCamerawork();
                }
            } else {
                /* 1986 */ processGameReady(null);
            }

            /* 1989 */ this.init = true;
        }

        void processLogout(BasicAmpedEvent e) {
        }

        public void updateStage() {
            /* 1997 */ if (this.stage == null)
                return;
            /* 1999 */ if (this.init) {
                /* 2000 */ if (this.ed != null && this.ed.talk != null) {
                    /* 2001 */ Container root = (Container) this.cnt.getRootContainer();
                    /* 2002 */ if (this.talkPanel != null) {
                        /* 2003 */ root.remove((Component) this.talkPanel);
                        /* 2004 */ this.talkPanel = null;
                    }
                    /* 2006 */ this.talkPanel = new TalkPanel(this);
                    /* 2007 */ root.add((Component) this.talkPanel);
                    /* 2008 */ if (this.ed.talk != null)
                        this.talkPanel.startTalk(this.ed.talk, this.eventid);

                    /* 2011 */ this.talkPanel.exitRoom();
                }
                /* 2013 */ if (this.hit != null)
                    /* 2014 */ for (int i = 0; i < this.hit.length;) {
                        this.hit[i] = 0;
                        i++;
                    }

                /* 2016 */ this.init = false;
            }

            /* 2019 */ super.updateStage();
        }

        public boolean eventFinished() {
            /* 2023 */ if (this.stage.rule instanceof EventRule) {
                /* 2024 */ ((EventRule) this.stage.rule).setGameResult(-1);
            }

            /* 2027 */ Container root = (Container) this.cnt.getRootContainer();
            /* 2028 */ if (this.talkPanel2 != null) {
                /* 2029 */ root.remove((Component) this.talkPanel2);
                /* 2030 */ this.talkPanel2 = null;
            }
            /* 2032 */ if (this.talkPanel != null) {
                /* 2033 */ root.remove((Component) this.talkPanel);
                /* 2034 */ this.talkPanel = null;
            }

            /* 2037 */ if (this.finishhandler != null) {
                /* 2038 */ Runnable r = this.finishhandler;
                /* 2039 */ this.finishhandler = null;
                /* 2040 */ r.run();
                /* 2041 */ return true;
            }
            /* 2043 */ return false;
        }

        public void setRetryable(boolean r) {
            /* 2047 */ if (this.finishhandler != null
                    && this.finishhandler instanceof TalkPanel.ChallengeFinishedHandler)
                /* 2048 */ ((TalkPanel.ChallengeFinishedHandler) this.finishhandler).setRetryable(r);
        }

        public void processAmpedEvent(AmpedEvent e) {
            MatchingEvent me;
            StagePanelEvent spe;
            /* 2053 */ switch (e.id) {
                case 85:
                    /* 2055 */ me = (MatchingEvent) e;
                    /* 2056 */ if (me.subid == 0) {
                        /* 2057 */ if (this.finishhandler != null) {
                            /* 2058 */ Runnable r = this.finishhandler;
                            /* 2059 */ this.finishhandler = null;
                            /* 2060 */ KWTManager.getEventQueue().postEvent((Event) new FinishTimerEvent(r));
                        }
                        return;
                    }
                    break;
                case 86:
                    /* 2066 */ spe = (StagePanelEvent) e;
                    /* 2067 */ if (spe.subid == 15 || spe.subid == 16 || spe.subid == 19) {
                        /* 2068 */ challengerec = (ChallengeRecord) spe.args[0];
                        break;
                    }
                    /* 2070 */ if (spe.subid == 17 &&
                    /* 2071 */ this.ed != null) {
                        /* 2072 */ Container root = (Container) this.cnt.getRootContainer();
                        /* 2073 */ if (this.talkPanel2 != null) {
                            /* 2074 */ root.remove((Component) this.talkPanel2);
                            /* 2075 */ this.talkPanel2 = null;
                        }
                        /* 2077 */ this.talkPanel2 = new TalkPanel(this);
                        /* 2078 */ root.add((Component) this.talkPanel2);
                        /* 2079 */ this.talkPanel2.setMethod((String) spe.args[0]);
                        /* 2080 */ this.talkPanel2.startTalk(this.ed.exttalk, this.eventid);

                        /* 2082 */ this.talkPanel2.setFinishHandler(new FinishHandler());
                    }
                    break;
            }

            /* 2087 */ super.processAmpedEvent(e);
        }

        class FinishTimerEvent extends TimerEvent {
            Runnable r;

            FinishTimerEvent(Runnable _r) {
                /* 2094 */ super(0, null);
                /* 2095 */ this.r = _r;
            }

            public void timerInvoked(TimerEvent e) {
                /* 2099 */ this.r.run();
            }
        }

        public boolean isTraining() {
            /* 2105 */ return this.training;
        }

        class TrainingListener
                implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                /* 2114 */ if ("exitPractice".equals(e.getActionCommand()))
                    /* 2115 */ StageConnector.EventScene.this.eventFinished();
            }
        }

        class FinishHandler
                implements Runnable {
            public void run() {
                /* 2123 */ synchronized (KWTManager.PAINT_LOCK) {

                    /* 2125 */ StageConnector.EventScene.this.player.ctr.setEnabled(true);
                    /* 2126 */ StageConnector.EventScene.this.stage.script.runningEvent = false;
                    /* 2127 */ StageConnector.EventScene.this.requestFocus();
                }
            }
        }
    }

    public boolean isTalkpanelshowing() {
        /* 2137 */ if (this.stage.script == null)
            return false;
        /* 2138 */ return this.stage.script.runningEvent;
    }

    public static class Playback
            extends StageConnector {
        int uid;

        public PlaybackRecord rec;

        AdjustableClock clock;

        PlaybackControlPanel ctrpanel;

        public void setUserID(int i) {
            /* 2157 */ this.uid = i;
        }

        public void setPlayback(PlaybackRecord r) {
            /* 2161 */ this.rec = r;
        }

        public boolean login() {
            /* 2165 */ return login(false);
        }

        public boolean login(boolean tool) {
            /* 2168 */ this.clock = new AdjustableClock(Clock.getClock());
            /* 2169 */ this.clock.setTimeOffset(this.rec.begintime);

            /* 2171 */ return true;
        }

        public void processStageSetup(StageSetupEvent e) {
            /* 2176 */ this.clock.setTimeOffset(e.time);
            /* 2177 */ StageClient sc = new StageClient((Clock) this.clock);
            /* 2178 */ sc.setup(e);

            /* 2180 */ setStage(sc);

            /* 2183 */ PlayerViewModel p = (PlayerViewModel) this.stage.getPlayerForUserID(this.uid);
            /* 2184 */ if (p == null) {
                /* 2185 */ AmpedModel[] m = this.stage.getPlayers();
                /* 2186 */ p = (PlayerViewModel) m[0];
                /* 2187 */ for (int l = 1; l < m.length && m[l] != null; l++) {
                    /* 2188 */ PlayerViewModel pm = (PlayerViewModel) m[l];
                    /* 2189 */ if (pm.getNPCID() < 0) {
                        /* 2190 */ p = pm;
                        break;
                    }
                }
            }
            /* 2195 */ this.player = p;
            /* 2196 */ this.myid = p.getID();

            /* 2198 */ this.ctr = new PlayerController(this);

            /* 2201 */ UserData ud = UserData.getEmpty();
            /* 2202 */ ud.info = p.getUserInfo();
            /* 2203 */ updateUserData(ud);

            /* 2206 */ GLRegistry.registerProc(new StageConnector.SceneRegister());

            /* 2210 */ if (e != null && e.id != 101) {

                /* 2214 */ initCamerawork();
            }
        }

        public void updateUserData(Object o) {
            /* 2219 */ if (o instanceof UserInfo)
                return;
            /* 2221 */ super.updateUserData(o);
        }

        void panelSetup() {
            /* 2225 */ super.panelSetup();
            /* 2226 */ this.ctrpanel = new PlaybackControlPanel(this);
            /* 2227 */ Vector2D v = LookAndFeel.getLAF().getSizeDimension();
            /* 2228 */ this.ctrpanel.p.setPosition(v.x, 0.0F);
            /* 2229 */ this.ctrpanel.updateMatrix();
            /* 2230 */ this.cnt.add((Component) this.ctrpanel);
            /* 2231 */ this.ctrpanel.performMethod("start");
        }

        public void nextStage() {
            /* 2235 */ StageSetupEvent e = this.rec.moveStage(1);
            /* 2236 */ if (e != null) {
                /* 2237 */ processAmpedEvent((AmpedEvent) e);
                /* 2238 */ this.clock.setTimeOffset(e.time);
            } else {
                /* 2240 */ this.rec.reset();
                /* 2241 */ this.clock.setTimeOffset(this.rec.begintime);
            }
        }

        void processLogout(BasicAmpedEvent e) {
            /* 2246 */ if (!(GameSetting.getSetting()).disconnectinfoenabled)
                /* 2247 */ return;
            if (e.srcid != -1 && e.args == null) {
                /* 2248 */ PlayerModel p = this.stage.getPlayerForEntryID(e.srcid);
                /* 2249 */ if (p != null && p instanceof PlayerViewModel)
                    /* 2250 */ ((PlayerViewModel) p).setConnectState(e.argi);
            }
        }

        public void updateStage() {
            AmpedEvent e;
            /* 2256 */ while ((e = this.rec.nextEvent(this.clock.getTime())) != null) {

                /* 2258 */ processAmpedEvent(e);
                /* 2259 */ if (this.panel != null) {
                    /* 2260 */ this.panel.setParam(501, e);
                }
            }
            /* 2263 */ if (this.stage != null) {
                /* 2264 */ this.stage.update();
            }
            /* 2266 */ if (this.camerawork != null && this.player != null && this.camera != null) {
                /* 2267 */ this.camerawork.update(getDt() * 0.001F, this.camera);
                /* 2268 */ this.camerawork.getFocusPosition(this.stage.scene.focuspos);
                /* 2269 */ this.stage.scene.setStageLayer(this.camerawork.getCurrentLayer());
            }
        }

        public void processAmpedEvent(AmpedEvent e) {
            /* 2279 */ super.processAmpedEvent(e);
        }

        public boolean sendToServer(AmpedEvent e) {
            /* 2283 */ return true;
        }

        public void processDefaultEvent(AmpedEvent e) {
            /* 2287 */ if (this.stage == null)
                /* 2288 */ return;
            synchronized (this.stage) {
                /* 2289 */ this.stage.processAmpedEvent(e);
            }
        }

        public void processGameReward(BasicAmpedEvent e) {
        }

        public void processAddModel(BasicAmpedEvent e) {
            /* 2300 */ AmpedProperty p = (AmpedProperty) e.args[0];
            /* 2301 */ if (p instanceof AmpedProperty.Player) {
                /* 2302 */ PlayerViewModel pm = (PlayerViewModel) this.stage.createPlayer();
                /* 2303 */ pm.setProperties((AmpedProperty.Player) p);
                /* 2304 */ this.stage.addModel(pm, p.id);
            }
            /* 2306 */ else if (p instanceof AmpedProperty.Item) {
                /* 2307 */ ItemViewModel im = (ItemViewModel) this.stage.createItem();
                /* 2308 */ im.setProperties((AmpedProperty.Item) p);
                /* 2309 */ this.stage.addModel(im, p.id);
            }
        }
    }

    public static class Remote
            extends StageConnector {
        NetConnection con;

        final Transeiver trns;

        String serverid;

        RemoteLoginInfo logininfo;

        AmpedEventLog sendEventLog;

        AmpedEventLog recvEventLog;

        /* 2336 */ Vector<AmpedEvent> received = new Vector<AmpedEvent>();

        /* 2338 */ Vector<AmpedEvent> sent = new Vector<AmpedEvent>();
        /* 2339 */ Vector<AmpedEvent> forinvoke = new Vector<AmpedEvent>();

        /* 2344 */ long setuptimeout = 0L;

        AdjustableClock clock;

        byte senderid;

        int sentclock;

        int lastrecvclock;
        int lastserverclock;
        int errclockspeedcount;
        int clockerrcount;
        int clocksyncreqtime;
        /* 2357 */ int clocksyncinterval = 1000;
        int clocksyncnum;
        int clocksyncdelay;
        /* 2360 */ int clocksyncmindelay = 10000;

        int clocksyncmaxdelay;

        ConnectionEngine engine;

        /* 2366 */ volatile int lastrecvtime = 0;
        int print_counter;
        int senderc;
        private int nextTime_delay;
        private static int delay_base;
        private static long delay_total;
        private static int delay_count;
        private static int lastclock_get_event;
        public static boolean check_get_event;

        public Remote(ConnectionEngine e) {
            /* 2370 */ this.engine = e;
            /* 2371 */ this.sendEventLog = new AmpedEventLog();
            /* 2372 */ this.recvEventLog = new AmpedEventLog();

            /* 2375 */ this.clock = e.getClock();
            /* 2376 */ this.lastrecvtime = Clock.time() + 10000;
            /* 2377 */ this.trns = new RemoteTranseiver();
        }

        public boolean isVoiceChatEnabled() {
            /* 2382 */ if (!Console.isMac() && (GameSetting.getSetting()).voicechatenabled
                    && !this.userdata.isVoiceChatRestrict() &&
                    /* 2383 */ this.stage != null && this.stage.isVoiceChatEnabled()
                    && VoiceChatManager.isSettingEnabled())
                return true;
            return false;
        }

        private static NetConnection _connect(ConnectionEngine e, String sid) throws IOException, InterruptedException {
            /* 2388 */ if (sid.startsWith("tcp:")) {
                /* 2389 */ sid = sid.substring(4);
                /* 2390 */ int i = sid.indexOf(':');
                /* 2391 */ String ip = sid.substring(0, i);
                /* 2392 */ int port = Integer.parseInt(sid.substring(i + 1));
                /* 2393 */ TCPConnection tCPConnection = TCPConnection
                        .openNewConnection(new InetSocketAddress(ip, port), e);
                /* 2394 */ tCPConnection.connect();
                /* 2395 */ return (NetConnection) tCPConnection;
            }
            /* 2397 */ UDPConnection _con = new UDPConnection(e);
            /* 2398 */ _con.connect(sid);
            /* 2399 */ return (NetConnection) _con;
        }

        public static void testLogin(int eid, int uid, String sid, int ssid, ConnectionEngine e) throws Exception {
            /* 2407 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 2408 */ RemoteLoginInfo testinfo = new RemoteLoginInfo();
            /* 2409 */ testinfo.entryid = eid;
            /* 2410 */ testinfo.uid = uid;
            /* 2411 */ if (ga2 != null)
                testinfo.conid = ga2.getConnectID();
            /* 2412 */ testinfo.stageserverid = ssid;
            /* 2413 */ testinfo.testlogin = true;
            /* 2414 */ testinfo.guardmode = (ClientSetting.getSetting()).ctr.guardmode;

            /* 2416 */ NetConnection _con = null;
            /* 2417 */ System.out.println("test login... sid:" + sid + " ssid:" + ssid + " eid:" + eid + " uid:" + uid);

            try {
                /* 2420 */ _con = _connect(e, sid);
                /* 2421 */ _con.write(testinfo, true);
                /* 2422 */ System.out.println("connect : response waiting.... _con:" + _con);
                /* 2423 */ Object err = _con.read(6000);
                /* 2424 */ if (err instanceof Exception)
                    throw (Exception) err;
            } finally {
                /* 2426 */ if (_con != null)
                    _con.close();

            }
        }

        public static void testViewerLogin(int eid, String sid, int ssid, ConnectionEngine e) throws Exception {
            while (true) {
                try {
                    /* 2436 */ _testViewerLogin(eid, sid, ssid, e);
                    return;
                    /* 2438 */ } catch (AmpedException ae) {
                    /* 2439 */ if (ae.getErrorID() == 118) {

                        /* 2441 */ System.out.println("testViewerLogin ERR_STAGE_NOTREADY : " + sid + " " + ssid);
                        /* 2442 */ Thread.sleep(2000L);
                        continue;
                    }
                    throw ae;
                }
                /* 2445 */ }
        }

        static void _testViewerLogin(int eid, String sid, int ssid, ConnectionEngine e) throws Exception {
            /* 2452 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 2453 */ RemoteLoginInfo testinfo = new RemoteLoginInfo();
            /* 2454 */ testinfo.entryid = eid;
            /* 2455 */ testinfo.uid = 0;
            /* 2456 */ if (ga2 != null)
                testinfo.conid = ga2.getConnectID();
            /* 2457 */ testinfo.stageserverid = ssid;
            /* 2458 */ testinfo.testlogin = true;
            /* 2459 */ testinfo.guardmode = (ClientSetting.getSetting()).ctr.guardmode;
            /* 2460 */ NetConnection _con = null;
            /* 2461 */ System.out.println("test viewer login... sid:" + sid + " ssid:" + ssid);

            try {
                /* 2464 */ _con = _connect(e, sid);
                /* 2465 */ _con.write(testinfo, true);
                /* 2466 */ System.out.println("connect : response waiting....");
                /* 2467 */ Object err = _con.read(6000);
                /* 2468 */ if (err instanceof Exception)
                    throw (Exception) err;
            } finally {
                /* 2470 */ if (_con != null)
                    _con.close();

            }
        }

        public void setLoginInfo(int eid, int uid, String sid, int ssid, String pwd) {
            /* 2476 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 2477 */ GetAmped2 ga2 = GetAmped2.getAmped2();
                /* 2478 */ this.entryid = eid;
                /* 2479 */ this.serverid = sid;
                /* 2480 */ this.logininfo = new RemoteLoginInfo();
                /* 2481 */ this.logininfo.entryid = eid;
                /* 2482 */ this.logininfo.uid = uid;
                /* 2483 */ if (ga2 != null)
                    this.logininfo.conid = ga2.getConnectID();
                /* 2484 */ this.logininfo.stageserverid = ssid;
                /* 2485 */ this.logininfo.roompasswd = KotoriUtil.getNameHash(pwd);
                /* 2486 */ this.logininfo.navi = this.userdata.navi;
                /* 2487 */ this.logininfo.guardmode = (ClientSetting.getSetting()).ctr.guardmode;
            }
        }

        public boolean login() {
            /* 2493 */ return login(false);
        }

        public boolean login(boolean tool) {
            /* 2496 */ NetConnection _con = null;
            try {
                /* 2498 */ System.out.print("connect");
                /* 2499 */ _con = this.con = _connect(this.engine, this.serverid);
                /* 2500 */ System.out.print(".");
                /* 2501 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 2502 */ this.setuptimeout = System.currentTimeMillis() + 8000L;
                }

                /* 2505 */ synchronized (_con) {
                    /* 2506 */ _con.setNextTranseiver(this.trns);
                    /* 2507 */ _con.write(this.logininfo, true);
                    /* 2508 */ System.out.print(".");
                }
                /* 2510 */ Object o = _con.read(6000);
                /* 2511 */ System.out.print(".");
                /* 2512 */ if (o instanceof Number) {
                    /* 2513 */ this.myid = ((Number) o).intValue();
                    /* 2514 */ setGameState(1);
                }
                /* 2516 */ else if (o instanceof Exception) {
                    /* 2517 */ Exception e = (Exception) o;
                    /* 2518 */ e.printStackTrace();
                    /* 2519 */ setGameState(-2);
                    /* 2520 */ this.errobj = e;
                    /* 2521 */ _con.close();
                    /* 2522 */ System.out.println(" err returned : " + e);
                    /* 2523 */ return false;
                }
                /* 2525 */ System.out.println("ok. myid:" + this.myid);
            }
            /* 2527 */ catch (IOException ioe) {
                /* 2528 */ ioe.printStackTrace();
                /* 2529 */ setGameState(-2);
                /* 2530 */ this.errobj = ioe;
                /* 2531 */ if (_con != null)
                    _con.close();
                /* 2532 */ return false;
                /* 2533 */ } catch (InterruptedException ie) {
                /* 2534 */ ie.printStackTrace();
                /* 2535 */ setGameState(-2);
                /* 2536 */ this.errobj = ie;
                /* 2537 */ if (_con != null)
                    _con.close();
                /* 2538 */ return false;
            }

            /* 2541 */ return true;
        }

        public void updateStage() {
            /* 2546 */ if (this.stage == null) {
                /* 2547 */ if (this.setuptimeout != 0L && System.currentTimeMillis() >= this.setuptimeout) {
                    /* 2548 */ this.setuptimeout = 0L;
                    /* 2549 */ Exception ex = new Exception("StageConnecor StageSetup Timeout.....");
                    /* 2550 */ ex.printStackTrace();
                    /* 2551 */ this.errobj = ex;
                    /* 2552 */ setGameState(-2);
                    /* 2553 */ if (this.con != null) {
                        /* 2554 */ GameSetting.printStackTrace("!close! updateStage");
                        /* 2555 */ this.con.close();
                    }
                }

                return;
            }
            /* 2561 */ synchronized (this.stage) {
                /* 2562 */ while (!this.received.isEmpty()) {
                    /* 2563 */ this.stage.processAmpedEvent(this.received.remove(0));
                }
                /* 2565 */ while (!this.sent.isEmpty()) {
                    /* 2566 */ if (this.panel != null) {
                        /* 2567 */ this.panel.setParam(501, this.sent.get(0));
                    }
                    /* 2569 */ if (this.camerawork != null) {
                        /* 2570 */ this.camerawork.processAmpedEvent(this.sent.get(0));
                    }
                    /* 2572 */ this.sent.remove(0);
                }
                /* 2574 */ while (!this.forinvoke.isEmpty()) {
                    /* 2575 */ if (this.panel != null) {
                        /* 2576 */ this.panel.setParam(500, this.forinvoke.get(0));
                    }
                    /* 2578 */ if (this.camerawork != null) {
                        /* 2579 */ this.camerawork.processAmpedEvent(this.forinvoke.get(0));
                    }
                    /* 2581 */ this.forinvoke.remove(0);
                }
                /* 2583 */ if (this.ctr != null && (GameSetting.getSetting()).sdoa && this.stage.isLoungeMode()) {
                    /* 2584 */ KWTManager m = this.cnt.getKWTManager();
                    /* 2585 */ ((PlayerController) this.ctr).setMouseOut((m != null && !m.isMouseIn()));
                }
                /* 2587 */ this.stage.update();
            }

            /* 2590 */ if (this.camerawork != null && this.player != null && this.camera != null) {
                /* 2591 */ this.camerawork.update(getDt() * 0.001F, this.camera);
                /* 2592 */ this.camerawork.getFocusPosition(this.stage.scene.focuspos);
                /* 2593 */ this.stage.scene.setStageLayer(this.camerawork.getCurrentLayer());
            }

            /* 2596 */ if (getGameState() >= 0) {
                /* 2597 */ updateClockSync();
                /* 2598 */ checkReceiveTime();
            }
        }

        public void updateClockSync() {
            /* 2604 */ int t = Clock.time();
            /* 2605 */ if (this.stage != null && this.clocksyncreqtime < t) {
                /* 2606 */ boolean proc_survey_log = GetAmped2.enableProcess(0);
                /* 2607 */ if (proc_survey_log && this.print_counter++ % 30 == 0) {
                    /* 2608 */ Calendar cal = ServerTime.getCalendarInstance();
                    /* 2609 */ String s = String.format("--[%tm/%td %tH:%tM:%tS]--",
                            new Object[] { cal, cal, cal, cal, cal });
                    /* 2610 */ System.out.println(String.valueOf(s) + "updateClockSync clocksyncreqtime:"
                            + (this.clocksyncreqtime / 1000) + ", next:" + ((t + this.clocksyncinterval) / 1000));
                }
                /* 2612 */ sendClockSyncRequest();
                /* 2613 */ this.clocksyncreqtime = t + this.clocksyncinterval;
                /* 2614 */ if (this.stage.isLoungeMode()) {
                    /* 2615 */ this.clocksyncinterval = Math.min(this.clocksyncinterval + 1000, 9500);
                } else {
                    /* 2617 */ this.clocksyncinterval = Math.min(this.clocksyncinterval + 1000, 4500);
                }
            }
        }

        public void checkReceiveTime() {
            /* 2623 */ int ofs = 9000;
            /* 2624 */ int t = Clock.time();
            /* 2625 */ int dt = t - this.lastrecvtime;
            /* 2626 */ if (this.stage.isLoungeMode()) {
                /* 2627 */ if (dt < 21000 + ofs)
                    return;
                /* 2629 */ } else if (dt < 15000 + ofs) {
                return;
            }
            /* 2632 */ Exception ex = new Exception("No response from StageServer....");
            /* 2633 */ this.errobj = ex;
            /* 2634 */ setGameState(-2);
            /* 2635 */ System.err.println("No response from StageServer....(Remote) dt:" + dt);
        }

        private boolean sendFragment(AmpedEvent e) throws IOException {
            /* 2640 */ int fragmentsize = 1200;
            /* 2641 */ boolean fragmentevent = e.isFragmentEvent();
            /* 2642 */ if (fragmentevent) {
                /* 2643 */ ByteArrayOutputStream baos = new ByteArrayOutputStream();
                /* 2644 */ ExternalizerOutputStream eos = new ExternalizerOutputStream(baos, false);
                /* 2645 */ e.encode((ObjectOutput) eos);

                /* 2647 */ byte[] b = baos.toByteArray();
                /* 2648 */ if (b.length <= fragmentsize)
                    return false;

                /* 2650 */ byte[][] sb = KotoriUtil.splitBytes(b, fragmentsize);
                /* 2651 */ int seq = (new Random()).nextInt() >>> 13 & 0xFFFF;

                /* 2653 */ int conid = -1;
                /* 2654 */ if (GetAmped2.getAmped2() != null && (GameSetting.getSetting()).concheckenabled)
                    conid = GetAmped2.getAmped2().getConnectID();
                /* 2655 */ for (int l = 0; l < sb.length; l++) {
                    /* 2656 */ FragmentEvent fe = FragmentEvent.create(seq, l, sb.length, sb[l]);
                    /* 2657 */ fe.conid = conid;
                    /* 2658 */ this.con.write(fe, true);
                }
            }
            /* 2661 */ return fragmentevent;
        }

        public boolean sendToServer(AmpedEvent e) {
            /* 2666 */ if (getGameState() <= 0) {
                /* 2667 */ System.out.println("ILLEGAL STATE : " + getGameState() + " at sendToServer(" + e + ")");
                /* 2668 */ return false;
            }

            /* 2671 */ if (this.chatcommand.analysis(e))
                return false;

            /* 2673 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 2674 */ if (ga2 != null)
                e.conid = ga2.getConnectID();

            /* 2676 */ this.sent.add(e);

            try {
                /* 2692 */ if (!sendFragment(e)) {
                    /* 2693 */ this.con.write(e, e.isEnsureEvent());
                }
                /* 2695 */ } catch (IOException ioe) {
                /* 2696 */ ioe.printStackTrace();
                /* 2697 */ System.err.println("StageConnector.Remote Error....");
                /* 2698 */ this.errobj = ioe;
                /* 2699 */ setGameState(-2);
                /* 2700 */ return false;
            }
            /* 2702 */ return true;
        }

        public void processStageSetup(StageSetupEvent e) {
            try {
                /* 2709 */ for (int l = 0; l < 5; l++) {
                    /* 2710 */ if (getGameState() == 0) {
                        /* 2711 */ System.err.println("processStageSetup GAMESTATE_NOTCONNECTED... " + l + " / 5");
                        /* 2712 */ Thread.sleep(200L);
                    }
                }
                /* 2715 */ } catch (Exception ex) {
                /* 2716 */ ex.printStackTrace();
                return;
            }
            /* 2719 */ if (getGameState() == 0) {
                /* 2720 */ System.err.println("processStageSetup GameState error : " + getGameState());
            }

            /* 2723 */ if (this.player != null)
                this.player.con = null;

            /* 2732 */ doStageSetup(e);

            /* 2735 */ checkPlaybackRecord(e);
        }

        void printEventLog() {
            /* 2739 */ if (this.sendEventLog != null) {

                /* 2744 */ this.sendEventLog.clear();
            }

            /* 2747 */ if (this.recvEventLog != null) {
                /* 2748 */ if (this.recvEventLog.getLogCount() > 0) {
                    /* 2749 */ System.out.println("Received Amped Event Log ----- "
                            + ((this.stage == null) ? null : (String) this.stage.datasource));
                    /* 2750 */ this.recvEventLog.print(System.out, 0);
                    /* 2751 */ System.out.println(
                            "Network delay.. min:" + this.clocksyncmindelay + " max:" + this.clocksyncmaxdelay + " avg:"
                                    + ((this.clocksyncnum > 0) ? (this.clocksyncdelay / this.clocksyncnum) : 0));
                }
                /* 2753 */ this.recvEventLog.clear();
            }
        }

        public void doStageSetup(StageSetupEvent e) {
            /* 2760 */ printEventLog();

            /* 2763 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 2764 */ if (ga2 != null) {
                /* 2765 */ ga2.switchbackStageConnector(this);
            }

            /* 2769 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 2770 */ this.setuptimeout = 0L;

                /* 2772 */ if (this.stage != null) {
                    /* 2773 */ this.stage.restoreClock();
                }
                /* 2775 */ this.clock.setTimeOffset(e.time + delay_base);
                /* 2776 */ this.clock.setTimeScale(1.0D);
                /* 2777 */ this.clocksyncinterval = 500;

                /* 2779 */ if (delay_count > 3) {
                    /* 2780 */ int ave = (int) (delay_total / delay_count);
                    /* 2781 */ int dec = delay_count - 3;
                    /* 2782 */ delay_total -= (ave * dec);
                    /* 2783 */ delay_count -= dec;
                }

                /* 2786 */ StageClient sc = new StageClient((Clock) this.clock);
                /* 2787 */ sc.setup(e);

                /* 2789 */ setStage(sc);

                /* 2792 */ setPlayer((PlayerViewModel) this.stage.getPlayerForEntryID(this.entryid));

                /* 2795 */ GLRegistry.registerProc(new StageConnector.SceneRegister());

                /* 2798 */ if (e.id == 101) {
                    /* 2799 */ sendBasicToServer(103);
                    /* 2800 */ System.out.println("PLAYER_READY...");
                } else {
                    /* 2802 */ initCamerawork();
                }
            }

            /* 2806 */ GLRegistry.waitForProc();
            /* 2807 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 2808 */ ((KWTClock) KWTManager.getClock()).update();
            }

            /* 2811 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 2812 */ this.clocksyncnum = 0;
                /* 2813 */ this.clocksyncmaxdelay = 0;
                /* 2814 */ this.clocksyncmindelay = 10000;
                /* 2815 */ this.clocksyncdelay = 0;
            }
        }

        public void processAddModel(BasicAmpedEvent e) {
            /* 2840 */ synchronized (KWTManager.PAINT_LOCK) {

                /* 2842 */ AmpedProperty p = (AmpedProperty) e.args[0];
                /* 2843 */ if (p instanceof AmpedProperty.Player) {
                    /* 2844 */ PlayerViewModel pm = (PlayerViewModel) this.stage.createPlayer();
                    /* 2845 */ pm.setProperties((AmpedProperty.Player) p);
                    /* 2846 */ this.stage.addModel(pm, p.id);
                }
                /* 2848 */ else if (p instanceof AmpedProperty.Item) {
                    /* 2849 */ ItemViewModel im = (ItemViewModel) this.stage.createItem();
                    /* 2850 */ im.setProperties((AmpedProperty.Item) p);
                    /* 2851 */ this.stage.addModel(im, p.id);
                }
            }
        }

        public void sendClockSyncRequest() {
            /* 2874 */ this.sentclock = Clock.time();

            /* 2876 */ if (sendToServer(
                    (AmpedEvent) ClockSyncEvent.createRequest(this.senderid = (byte) (this.senderid + 1)))) {
                /* 2877 */ this.senderc = this.clock.getTime();
            }
        }

        public void processClockSyncResponse(ClockSyncEvent e) {
            /* 2893 */ if (e.senderid != this.senderid)
                /* 2894 */ return;
            if (lastclock_get_event < e.lasteventclock) {
                /* 2895 */ if (lastclock_get_event != 0)
                    check_get_event = true;
                /* 2896 */ lastclock_get_event = e.lasteventclock;
            }

            /* 2899 */ boolean proc_ignore_flash_delay = true;
            /* 2900 */ GameSetting gs = GameSetting.getSetting();

            /* 2902 */ int delay = this.clock.getTime() - this.senderc;

            /* 2911 */ int check_min = proc_ignore_flash_delay ? 0 : -1000;
            /* 2912 */ if (delay < check_min || delay > 1000) {
                /* 2913 */ this.lastrecvclock = e.recvclock;
                /* 2914 */ this.clocksyncreqtime = Math.min(this.clocksyncreqtime, Clock.time() + 1000);
                /* 2915 */ if (proc_ignore_flash_delay && ++this.clockerrcount >= 3) {
                    /* 2916 */ gs.println(
                            String.valueOf(this.clockerrcount) + "dalay:" + delay);
                    /* 2917 */ if (delay_count > 0)
                        delay = (int) (delay_total / delay_count);
                    /* 2918 */ this.clock.setTimeOffset(e.serverclock);
                    /* 2919 */ delay_base = 0;
                    /* 2920 */ delay_total = 0L;
                    /* 2921 */ delay_count = 0;
                    /* 2922 */ this.clock.setTimeScale(1.0D);
                }
                return;
            }
            /* 2926 */ this.clockerrcount = 0;
            /* 2927 */ if (delay > 500) {
                /* 2928 */ this.clocksyncreqtime = Math.min(this.clocksyncreqtime,
                        Clock.time() + this.clocksyncinterval / 3);
            }
            /* 2930 */ int newoffs = e.serverclock + KMath.clamp(delay, 0, 1000) * 5 / 7;

            /* 2937 */ if (proc_ignore_flash_delay) {
                int ave;
                /* 2939 */ if (delay_count < 7) {
                    /* 2940 */ delay_total += delay;
                    /* 2941 */ delay_count++;
                    /* 2942 */ ave = (int) (delay_total / delay_count);
                    /* 2943 */ if (delay_base == 0 || delay < delay_base || this.clock.checkDelay(newoffs)) {
                        /* 2944 */ this.clock.setTimeOffset(newoffs);
                        /* 2945 */ delay_base = delay;
                        /* 2946 */ delay_total = ((ave + delay * 9) / 10 * delay_count);
                    }
                } else {

                    /* 2950 */ ave = (int) (delay_total / delay_count);

                    /* 2952 */ if (delay < Math.min(1000, ave * 5)) {
                        /* 2953 */ delay_total += delay;
                        /* 2954 */ delay_count++;
                    }

                    /* 2957 */ if (delay < ave) {
                        /* 2958 */ int t_ave = (delay_base + delay) / 2;
                        /* 2959 */ newoffs = e.serverclock + t_ave * 5 / 7;
                        /* 2960 */ this.clock.setTimeOffset(newoffs);
                        /* 2961 */ delay_base = t_ave;
                    }
                }

                /* 2965 */ if (delay_count >= 17) {
                    /* 2966 */ delay_total -= (ave * 10);
                    /* 2967 */ delay_count -= 10;
                }
            } else {
                /* 2970 */ this.clock.setTimeOffset(newoffs);
            }

            /* 2973 */ this.clocksyncmindelay = Math.min(this.clocksyncmindelay, delay);
            /* 2974 */ this.clocksyncmaxdelay = Math.max(this.clocksyncmaxdelay, delay);
            /* 2975 */ this.clocksyncnum++;
            /* 2976 */ this.clocksyncdelay += KMath.clamp(delay, 0, 1000);

            /* 2978 */ if (this.lastrecvclock != 0 && this.clocksyncnum > 3) {

                /* 2982 */ int itc = e.recvclock - this.lastrecvclock;
                /* 2983 */ int itsc = newoffs - this.lastserverclock;
                /* 2984 */ double spdrate = itsc / itc;

                /* 2986 */ if ((GameSetting.getSetting()).clockspeedcheckenabled && !this.stage.isLoungeMode()) {
                    /* 2987 */ if (itc - itsc > 3500) {
                        /* 2988 */ this.errclockspeedcount++;
                        /* 2989 */ System.out.println("[Cheat]clock speed..  diff:" + (itc - itsc) + "  count:"
                                + this.errclockspeedcount + "  c:" + itc + "  s:" + itsc + "  rate:" + spdrate
                                + "  lastrate:" + this.clock.getTimeScale());
                        /* 2990 */ if (this.errclockspeedcount >= 30) {
                            /* 2991 */ GetAmped2 ga2 = GetAmped2.getAmped2();
                            /* 2992 */ if (ga2 != null)
                                ga2.errorExitStage((Exception) new AmpedException(121));
                        }
                    } else {
                        /* 2995 */ this.errclockspeedcount = 0;
                    }
                }
                /* 2998 */ if (Math.abs(spdrate * this.clock.getTimeScale() - 1.0D) <= 0.1D) {

                    /* 3004 */ if (spdrate >= 0.9999D && spdrate <= 1.0001D) {
                        /* 3005 */ this.clock.setTimeScale(1.0D);
                    } else {
                        /* 3007 */ this.clock.setTimeScale(spdrate);
                    }
                }
            }
            /* 3011 */ this.lastrecvclock = e.recvclock;
            /* 3012 */ this.lastserverclock = newoffs;
        }

        public void processDefaultEvent(AmpedEvent e) {
            /* 3017 */ if (this.stage == null) {
                return;
            }

            /* 3021 */ this.received.add(e);
        }

        void processLogout(BasicAmpedEvent e) {
            /* 3025 */ if (e.srcid == -1) {
                /* 3026 */ if (isVoiceChatEnabled()) {
                    /* 3027 */ VoiceChatManager.getInstance().reset();
                }

                /* 3030 */ System.out.println("processLogout!!! " + e.argi);
                /* 3031 */ printEventLog();

                /* 3033 */ if (e.argi == 0)
                    /* 3034 */ return;
                setGameState(-1);
                /* 3035 */ if (this.con != null)
                    this.con.close();
                /* 3036 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 3037 */ this.con = null;
                    /* 3038 */ this.stage = null;
                    /* 3039 */ this.player = null;
                }

                /* 3042 */ } else if ((GameSetting.getSetting()).disconnectinfoenabled && e.args == null) {
                /* 3043 */ PlayerModel p = this.stage.getPlayerForEntryID(e.srcid);
                /* 3044 */ if (p != null && p instanceof PlayerViewModel) {
                    /* 3045 */ ((PlayerViewModel) p).setConnectState(e.argi);
                }
            }
        }

        public AmpedEventLog getRecvEventLog() {
            /* 3052 */ return this.recvEventLog;
        }

        public AmpedEventLog getSendEventLog() {
            /* 3057 */ return this.sendEventLog;
        }

        void eventMerged(AmpedEvent e) {
            /* 3062 */ this.forinvoke.add(e);
        }

        public void processViewerRequest(BasicAmpedEvent e) {
            /* 3069 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 3070 */ StageConnector ccon = ga2.getStageConnector();
            /* 3071 */ if (ccon != null)
                /* 3072 */ ccon.processViewerRequest(e);
        }

        public void processVoiceChat(MatchingEvent e) {
            VoiceChatPlayerInfo[] infos;
            int l;
            /* 3077 */ VoiceChatManager vcm = VoiceChatManager.getInstance();
            /* 3078 */ switch (e.subid) {

                case 120:
                    /* 3081 */ vcm.addPlayer((VoiceChatPlayerInfo) e.args[0]);
                    break;
                case 122:
                    /* 3084 */ infos = (VoiceChatPlayerInfo[]) e.args[0];

                    /* 3086 */ vcm.start(this.entryid);
                    /* 3087 */ for (l = 0; l < infos.length; l++) {
                        /* 3088 */ System.out.println("    other player " + l + " : " + infos[l]);
                        /* 3089 */ vcm.addPlayer(infos[l]);
                    }
                    break;

                case 121:
                    /* 3094 */ vcm.removePlayer(e.argi);
                    break;

                case 123:
                    /* 3098 */ vcm.reset();
                    break;
            }
        }

        class RemoteTranseiver
                implements Transeiver {
            public void received(Object src, int ch, InputStream in) throws IOException, ClassNotFoundException {
                /* 3106 */ ExternalizerInputStream eis = new ExternalizerInputStream(in, false);
                /* 3107 */ ByteBuffer buf = null;
                /* 3108 */ ByteArrayInputStream bi = null;
                /* 3109 */ int size = 0, num = 0;
                /* 3110 */ if (in instanceof ByteBufferInputStream) {
                    /* 3111 */ buf = ((ByteBufferInputStream) in).getByteBuffer();
                    /* 3112 */ size = buf.limit();
                }
                /* 3114 */ else if (in instanceof ByteArrayInputStream) {
                    /* 3115 */ bi = (ByteArrayInputStream) in;
                    /* 3116 */ size = bi.available();
                }
                /* 3118 */ num = eis.readByte();

                /* 3120 */ if (StageConnector.Remote.this.getGameState() == -1
                        || StageConnector.Remote.this.getGameState() == -2) {
                    /* 3121 */ if (StageConnector.Remote.this.con != null) {
                        /* 3122 */ GameSetting.printStackTrace("!close! RemoteTranseiver.received getGameState():"
                                + StageConnector.Remote.this.getGameState());
                        /* 3123 */ StageConnector.Remote.this.con.close();
                        /* 3124 */ StageConnector.Remote.this.con = null;
                    }
                    /* 3126 */ System.out.println("StageConnector is closed. : " + this);

                    return;
                }

                /* 3159 */ for (int l = 0; l < num; l++) {
                    /* 3160 */ int bufRemaining = (buf != null) ? buf.remaining() : ((bi != null) ? bi.available() : 0);
                    /* 3161 */ AmpedEvent e = AmpedEvent.decode((ObjectInput) eis);
                    /* 3162 */ if (e.id == 101) {
                        /* 3163 */ System.out.println("AmpedEvent.STAGE_SETUP " + bufRemaining);
                    }

                    /* 3166 */ StageConnector.Remote.this.recvEventLog.countupEvent(e.id,
                            bufRemaining - ((buf != null) ? buf.remaining() : ((bi != null) ? bi.available() : 0)));
                    /* 3167 */ StageConnector.Remote.this.processAmpedEvent(e);
                    /* 3168 */ StageConnector.Remote.this.forinvoke.add(e);
                }
                /* 3170 */ StageConnector.Remote.this.recvEventLog.countupPacket(size);
                /* 3171 */ StageConnector.Remote.this.lastrecvtime = Clock.time();
            }

            public void send(Object o, Object src, int ch, OutputStream out) throws IOException {
                /* 3176 */ ExternalizerOutputStream eos = new ExternalizerOutputStream(out, false);
                /* 3177 */ ((AmpedEvent) o).encode((ObjectOutput) eos);

                /* 3179 */ int size = ((ByteBufferOutputStream) out).getByteBuffer().position() - 7;
                /* 3180 */ StageConnector.Remote.this.sendEventLog.countupEvent(((AmpedEvent) o).id, size);
                /* 3181 */ StageConnector.Remote.this.sendEventLog.countupPacket(size);
            }
        }
    }

    public static class Dummy
            extends Remote {
        StressTestClient.DummyClient dc;

        boolean setupStage;

        public PlayerModel pModel;

        int prevMoveTime;

        int moveInterval;

        int prevSayTime;

        int sayInterval;
        int minMsgLen;
        int maxMsgLen;

        public Dummy(StressTestClient.DummyClient _dc) {
            /* 3207 */ super(_dc.getConnectionEngine());

            /* 3209 */ this.dc = _dc;
            /* 3210 */ this.pModel = new PlayerModel(3);
        }

        public void setupMove(int _moveInterval) {
            /* 3214 */ this.moveInterval = _moveInterval;
        }

        public void setupChat(int _sayInterval, int _minMsgLen, int _maxMsgLen) {
            /* 3218 */ this.sayInterval = _sayInterval;
            /* 3219 */ this.minMsgLen = _minMsgLen;
            /* 3220 */ this.maxMsgLen = _maxMsgLen;
        }

        public void updateStage() {
            /* 3224 */ if (!this.setupStage) {
                return;
            }
            /* 3227 */ int time = Clock.time();
            /* 3228 */ if (this.moveInterval > 0 && time - this.prevMoveTime > this.moveInterval) {
                /* 3229 */ float posX = (float) Math.random() * 30.0F - 15.0F;
                /* 3230 */ float posZ = (float) Math.random() * 30.0F - 15.0F;

                /* 3232 */ PlayerSequenceEvent se = PlayerSequenceEvent.create(this.pModel);
                /* 3233 */ PlayerActionEvent ae = PlayerActionEvent.create3(this.pModel, 22, 0, posX, 0.0F, posZ);
                /* 3234 */ se.addSequence((PlayerEvent) ae, 1000);
                /* 3235 */ sendToServer((AmpedEvent) se);

                /* 3237 */ this.prevMoveTime = time;
            }

            /* 3241 */ if (this.sayInterval > 0 && time - this.prevSayTime > this.sayInterval) {

                /* 3243 */ String Msg = new String();
                /* 3244 */ int MsgLen = this.minMsgLen + (int) (Math.random() * (this.maxMsgLen - this.minMsgLen));
                /* 3245 */ for (int i = 0; i < MsgLen;) {
                    Msg = String.valueOf(Msg) + "a";
                    i++;
                }

                /* 3248 */ ChatMessage m = new ChatMessage((byte) 0, this.entryid, Msg);
                /* 3249 */ BasicAmpedEvent e = BasicAmpedEvent.create(this.pModel.getID(), 90, m);
                /* 3250 */ sendToServer((AmpedEvent) e);

                /* 3252 */ this.prevSayTime = time;
            }

            /* 3255 */ while (!this.received.isEmpty()) {
                /* 3256 */ this.received.remove(0);
            }
            /* 3258 */ while (!this.sent.isEmpty()) {
                /* 3259 */ this.sent.remove(0);
            }
            /* 3261 */ while (!this.forinvoke.isEmpty()) {
                /* 3262 */ this.forinvoke.remove(0);
            }

            /* 3265 */ int t = Clock.time();
            /* 3266 */ if (this.setupStage && this.clocksyncreqtime < t) {
                /* 3267 */ sendClockSyncRequest();
                /* 3268 */ this.clocksyncreqtime = t + this.clocksyncinterval;
                /* 3269 */ this.clocksyncinterval = Math.min(this.clocksyncinterval + 1500, 5000);
            }
        }

        public void doStageSetup(StageSetupEvent e) {
            /* 3274 */ System.out.println("processStageSetup " + e);

            /* 3276 */ synchronized (KWTManager.PAINT_LOCK) {

                /* 3278 */ if (e.id == 101) {
                    /* 3279 */ sendBasicToServer(103);
                    /* 3280 */ System.out.println("PLAYER_READY...");
                }
            }

            /* 3284 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 3285 */ ((KWTClock) KWTManager.getClock()).update();
            }

            /* 3289 */ int num = e.objnum;
            /* 3290 */ for (int l = 0; l < num; l++) {
                /* 3291 */ AmpedProperty p = e.objs[l];

                /* 3293 */ if (p instanceof AmpedProperty.Player) {
                    /* 3294 */ AmpedProperty.Player pp = (AmpedProperty.Player) p;
                    /* 3295 */ if (pp.ui != null &&
                    /* 3296 */ this.entryid == pp.ui.entryid) {
                        /* 3297 */ this.pModel.setID(p.id);
                    }
                }
            }

            /* 3302 */ this.setupStage = true;
        }

        public void processAddModel(BasicAmpedEvent e) {
        }

        public void processGameReady(AmpedEvent e) {
            /* 3310 */ setGameState(2);
            /* 3311 */ System.out.println("GAME_READY");
        }

        public void processStageCamera(BasicAmpedEvent e) {
        }

        public void conditionChange(StageSetupEvent e) {
            /* 3320 */ if (this.dc != null && this.dc.getConnectionState() != 2)
                return;
            /* 3322 */ int c = 0;
            /* 3323 */ switch (e.stagemode) {
                case 2:
                case 6:
                    /* 3326 */ c = 1;
                    break;
                case 1:
                case 4:
                case 5:
                case 7:
                case 16:
                case 19:
                case 20:
                case 26:
                case 27:
                    /* 3337 */ c = 2;
                    break;
            }

            /* 3344 */ this.dc.changeCondition(c);
        }
    }

    public static class Dummy2
            extends Remote {
        GA2StressTest2.DummyClient dc;

        int stagemode;

        boolean setupStage;

        public PlayerModel pModel;

        int prevMoveTime;

        int moveInterval;

        int prevSayTime;

        int sayInterval;

        int minMsgLen;
        int maxMsgLen;
        String[] chatList;
        int gameCount;
        int battleReadyTime;
        int battleRejectTime;
        int nextAttackTime;
        /* 3375 */ int attackInterval = 3000;
        int prevBattleMoveTime;
        /* 3377 */ int battleMoveInterval = 1000;

        public Dummy2(GA2StressTest2.DummyClient _dc) {
            /* 3381 */ super(_dc.getConnectionEngine());

            /* 3383 */ this.dc = _dc;
            /* 3384 */ this.pModel = new PlayerModel(3);
        }

        public void setupMove(int _moveInterval) {
            /* 3388 */ this.moveInterval = _moveInterval;
        }

        public void setupChat(int _sayInterval, int _minMsgLen, int _maxMsgLen) {
            /* 3392 */ this.sayInterval = _sayInterval;
            /* 3393 */ this.minMsgLen = _minMsgLen;
            /* 3394 */ this.maxMsgLen = _maxMsgLen;
        }

        public void setupChat(int _sayInterval, String[] list) {
            /* 3398 */ this.sayInterval = _sayInterval;
            /* 3399 */ this.chatList = list;
        }

        public void updateStage() {
            /* 3403 */ if (!this.setupStage)
                return;
            /* 3405 */ int time = Clock.time();

            /* 3408 */ if (isLoungeMode() && !isMachingRoomMode()) {

                /* 3410 */ if (this.dc.isWalkEnabled() &&
                /* 3411 */ this.moveInterval > 0 && time - this.prevMoveTime > this.moveInterval) {
                    /* 3412 */ float posX = (float) Math.random() * 30.0F - 15.0F;
                    /* 3413 */ float posZ = (float) Math.random() * 30.0F - 15.0F;

                    /* 3415 */ PlayerSequenceEvent se = PlayerSequenceEvent.create(this.pModel);
                    /* 3416 */ PlayerActionEvent ae = PlayerActionEvent.create3(this.pModel, 22, 0, posX, 0.0F, posZ);
                    /* 3417 */ se.addSequence((PlayerEvent) ae, 1000);
                    /* 3418 */ sendToServer((AmpedEvent) se);

                    /* 3420 */ this.prevMoveTime = time;
                }

                /* 3425 */ if (this.dc.isChatEnabled() &&
                /* 3426 */ this.sayInterval > 0 && time - this.prevSayTime > this.sayInterval) {
                    String Msg;

                    /* 3429 */ if (this.chatList != null && this.chatList.length > 0) {
                        /* 3430 */ Msg = this.chatList[KMath.rndi(this.chatList.length)];
                    } else {
                        /* 3432 */ Msg = new String();
                        /* 3433 */ int MsgLen = this.minMsgLen
                                + (int) (Math.random() * (this.maxMsgLen - this.minMsgLen));
                        /* 3434 */ for (int i = 0; i < MsgLen;) {
                            Msg = String.valueOf(Msg) + "a";
                            i++;
                        }

                    }

                    /* 3438 */ ChatMessage m = new ChatMessage((byte) 0, this.entryid, Msg);
                    /* 3439 */ BasicAmpedEvent e = BasicAmpedEvent.create(this.pModel.getID(), 90, m);
                    /* 3440 */ sendToServer((AmpedEvent) e);

                    /* 3442 */ this.prevSayTime = time;
                }
            }

            /* 3447 */ if (isMachingRoomMode()) {

                /* 3449 */ if (this.battleReadyTime > 0 && this.battleReadyTime <= time) {
                    /* 3450 */ sendToServer((AmpedEvent) MatchingEvent.create(this.pModel.getID(), 0));
                    /* 3451 */ this.battleReadyTime = 0;
                }
                /* 3453 */ if (this.battleRejectTime > 0 && this.battleRejectTime <= time) {
                    /* 3454 */ sendToServer((AmpedEvent) MatchingEvent.create(this.pModel.getID(), 1));
                    /* 3455 */ this.dc.setExitGameRoomTime(Clock.time() + 5000);
                    /* 3456 */ this.battleRejectTime = 0;
                }
            }

            /* 3461 */ if (this.stagemode == 1 || this.stagemode == 27) {

                /* 3466 */ if (this.attackInterval > 0 && this.nextAttackTime <= time) {
                    /* 3467 */ this.nextAttackTime = time + (int) (this.attackInterval * (1.0F + KMath.rndc(0.3F)));
                    /* 3468 */ sendToServer(
                            (AmpedEvent) PlayerAttackEvent.create(this.pModel, 40, KMath.rndi(10), 0, 0, 0, 0, 0));
                }
            }

            /* 3473 */ while (!this.received.isEmpty()) {
                /* 3474 */ AmpedEvent ampedEvent = this.received.remove(0);
            }

            /* 3487 */ while (!this.sent.isEmpty()) {
                /* 3488 */ this.sent.remove(0);
            }
            /* 3490 */ while (!this.forinvoke.isEmpty()) {
                /* 3491 */ this.forinvoke.remove(0);
            }

            /* 3494 */ int t = Clock.time();
            /* 3495 */ if (this.setupStage && this.clocksyncreqtime < t) {
                /* 3496 */ sendClockSyncRequest();
                /* 3497 */ this.clocksyncreqtime = t + this.clocksyncinterval;
                /* 3498 */ this.clocksyncinterval = Math.min(this.clocksyncinterval + 1500, 5000);
            }
        }

        public void processDefaultEvent(AmpedEvent e) {
            /* 3503 */ this.received.add(e);
        }

        public void processStageSetup(StageSetupEvent e) {
            /* 3507 */ if (this.player != null)
                this.player.con = null;

            /* 3509 */ doStageSetup(e);
        }

        public void doStageSetup(StageSetupEvent e) {
            /* 3513 */ System.out.println("processStageSetup " + e);

            /* 3515 */ this.stagemode = e.stagemode;

            /* 3517 */ if (e.id == 101) {
                /* 3518 */ this.gameCount++;
            }

            /* 3521 */ synchronized (KWTManager.PAINT_LOCK) {

                /* 3523 */ if (e.id == 101) {
                    /* 3524 */ sendBasicToServer(103);
                    /* 3525 */ System.out.println("PLAYER_READY...");
                }
            }

            /* 3529 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 3530 */ ((KWTClock) KWTManager.getClock()).update();
            }

            /* 3534 */ int num = e.objnum;
            /* 3535 */ for (int l = 0; l < num; l++) {
                /* 3536 */ AmpedProperty p = e.objs[l];

                /* 3538 */ if (p instanceof AmpedProperty.Player) {
                    /* 3539 */ AmpedProperty.Player pp = (AmpedProperty.Player) p;
                    /* 3540 */ if (pp.ui != null &&
                    /* 3541 */ this.entryid == pp.ui.entryid) {
                        /* 3542 */ this.pModel.setID(p.id);
                    }
                }
            }

            /* 3549 */ if (isMachingRoomMode()) {
                /* 3550 */ if (e.id == 101) {
                    /* 3552 */ if (this.gameCount == 1) {
                        /* 3553 */ this.battleReadyTime = Clock.time() + 10000;
                        /* 3554 */ this.battleRejectTime = this.battleReadyTime + 60000;
                    } else {
                        /* 3556 */ this.dc.setExitGameRoomTime(Clock.time() + 10000);
                    }
                }
            } else {
                /* 3560 */ this.battleReadyTime = 0;
                /* 3561 */ this.battleRejectTime = 0;
            }

            /* 3584 */ this.pModel.dir = KMath.rndf(6.2831855F);

            /* 3586 */ this.pModel.pos.set(0.0F, 0.0F, 0.0F);

            /* 3588 */ this.nextAttackTime = Clock.time() + (int) (this.attackInterval * (1.0F + KMath.rndc(0.3F)));
            /* 3589 */ this.setupStage = true;
        }

        public void processAddModel(BasicAmpedEvent e) {
        }

        public void processGameReady(AmpedEvent e) {
            /* 3597 */ setGameState(2);
            /* 3598 */ System.out.println("GAME_READY");
        }

        public void processStageCamera(BasicAmpedEvent e) {
        }

        public void conditionChange(StageSetupEvent e) {
            /* 3607 */ if (this.dc != null && this.dc.getConnectionState() != 2)
                return;
            /* 3609 */ int c = 0;
            /* 3610 */ switch (e.stagemode) {
                case 2:
                case 6:
                    /* 3613 */ c = 1;
                    break;
                case 1:
                case 4:
                case 5:
                case 7:
                case 16:
                case 19:
                case 20:
                case 26:
                case 27:
                    /* 3624 */ c = 2;
                    break;
            }

            /* 3631 */ this.dc.changeCondition(c);
        }

        public boolean isLoungeMode() {
            /* 3635 */ if (this.stagemode != 3 && this.stagemode != 12 && this.stagemode != 13 && this.stagemode != 17
                    &&
                    /* 3636 */ this.stagemode != 2 && this.stagemode != 6 &&
                    /* 3637 */ this.stagemode != 11 && this.stagemode != 14 && this.stagemode != 15 &&
                    /* 3638 */ this.stagemode != 9)
                return false;
            return true;
        }

        public boolean isMachingRoomMode() {
            /* 3642 */ return !(this.stagemode != 2 && this.stagemode != 6);
        }
    }

    public static class Viewer
            extends StageConnector {
        public ViewerControlPanel ctrpanel;
        RemoteLoginInfo logininfo;
        /* 3652 */ Vector<AmpedEvent> received = new Vector<AmpedEvent>();
        /* 3653 */ Vector<AmpedEvent> forinvoke = new Vector<AmpedEvent>();

        /* 3655 */ Vector<Record> records = new Vector<Record>();

        NetConnection con;
        /* 3658 */ final Transeiver trns = new ViewerTranseiver();

        String serverid;

        AdjustableClock clock;

        byte senderid;

        int sentclock;

        int lastclock;

        int lastserverclock;
        int clockerrcount;
        int clocksyncreqtime;
        /* 3673 */ int clocksyncinterval = 1000;
        int clocksyncnum;
        int clocksyncdelay;
        /* 3676 */ int clocksyncmindelay = 10000;

        int clocksyncmaxdelay;

        ConnectionEngine engine;
        /* 3681 */ volatile int lastrecvtime = 0;
        int senderc;

        public Viewer(ConnectionEngine e) {
            /* 3684 */ this.engine = e;
            /* 3685 */ this.clock = new AdjustableClock(KWTManager.getClock());
            /* 3686 */ this.clock.setTimeOffset(0);
        }

        public void setLoginInfo(int eid, int uid, String sid, int ssid, String pwd) {
            /* 3691 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 3692 */ GetAmped2 ga2 = GetAmped2.getAmped2();
                /* 3693 */ this.entryid = eid;
                /* 3694 */ this.serverid = sid;
                /* 3695 */ this.logininfo = new RemoteLoginInfo();
                /* 3696 */ this.logininfo.entryid = eid;

                /* 3698 */ this.logininfo.uid = 0;
                /* 3699 */ if (ga2 != null)
                    this.logininfo.conid = ga2.getConnectID();
                /* 3700 */ this.logininfo.stageserverid = ssid;
                /* 3701 */ this.logininfo.roompasswd = KotoriUtil.getNameHash(pwd);
                /* 3702 */ System.out.println("Viewer setLoginInfo.ssid:" + ssid);
            }
        }

        public boolean login() {
            /* 3707 */ return login(false);
        }

        public boolean login(boolean tool) {
            /* 3710 */ NetConnection _con = null;
            try {
                /* 3712 */ System.out.print("viewer connect");
                /* 3713 */ _con = this.con = StageConnector.Remote._connect(this.engine, this.serverid);
                /* 3714 */ System.out.print(".");

                /*
                 * monitor enter FieldReferenceExpression{lastType=ObjectType{java/lang/Object},
                 * expression=ObjectTypeReferenceExpression{ObjectType{kotori/kwt/KWTManager}},
                 * name=PAINT_LOCK, descriptor=Ljava/lang/Object;}
                 */
                /*
                 * monitor exit FieldReferenceExpression{lastType=ObjectType{java/lang/Object},
                 * expression=ObjectTypeReferenceExpression{ObjectType{kotori/kwt/KWTManager}},
                 * name=PAINT_LOCK, descriptor=Ljava/lang/Object;}
                 */
                /* 3718 */ synchronized (_con) {
                    /* 3719 */ _con.setNextTranseiver(this.trns);
                    /* 3720 */ _con.write(this.logininfo, true);
                    /* 3721 */ System.out.print(".");
                }
                /* 3723 */ Object o = _con.read(6000);
                /* 3724 */ System.out.print(".");
                /* 3725 */ if (o instanceof Number) {
                    /* 3726 */ setGameState(1);
                }
                /* 3728 */ else if (o instanceof Exception) {
                    /* 3729 */ Exception e = (Exception) o;
                    /* 3730 */ e.printStackTrace();
                    /* 3731 */ setGameState(-2);
                    /* 3732 */ this.errobj = e;
                    /* 3733 */ _con.close();
                    /* 3734 */ System.out.println(" err returned : " + e);
                    /* 3735 */ return false;
                }

                /* 3738 */ sendClockSyncRequest();
            }
            /* 3740 */ catch (IOException ioe) {
                /* 3741 */ ioe.printStackTrace();
                /* 3742 */ setGameState(-2);
                /* 3743 */ this.errobj = ioe;
                /* 3744 */ if (_con != null)
                    _con.close();
                /* 3745 */ return false;
                /* 3746 */ } catch (InterruptedException ie) {
                /* 3747 */ ie.printStackTrace();
                /* 3748 */ setGameState(-2);
                /* 3749 */ this.errobj = ie;
                /* 3750 */ if (_con != null)
                    _con.close();
                /* 3751 */ return false;
            }
            /* 3753 */ return true;
        }

        void panelSetup() {
            /* 3757 */ super.panelSetup();

            /* 3763 */ if (this.stage.getStageMode() == 27) {
                getStagePanel().performMethod("viewer_dog");
            }
            /* 3764 */ else {
                getStagePanel().performMethod("viewer");
            }
            /* 3765 */ getStagePanel().updateFrame();

            /* 3767 */ this.ctrpanel = new ViewerControlPanel(this);
        }

        public void updateStage() {
            /* 3778 */ int t = this.clock.getTime();
            /* 3779 */ while (!this.records.isEmpty()) {
                /* 3780 */ Record r = this.records.get(0);

                /* 3782 */ if (r.time > t)
                    /* 3783 */ break;
                this.records.remove(0);
                /* 3784 */ processAmpedEvent(r.e);
                /* 3785 */ this.forinvoke.add(r.e);
            }

            /* 3788 */ if (this.stage == null)
                return;
            /* 3790 */ synchronized (this.stage) {
                /* 3791 */ while (!this.received.isEmpty()) {
                    /* 3792 */ this.stage.processAmpedEvent(this.received.remove(0));
                }
                /* 3794 */ while (!this.forinvoke.isEmpty()) {
                    /* 3795 */ if (this.panel != null) {
                        /* 3796 */ this.panel.setParam(500, this.forinvoke.get(0));
                    }
                    /* 3798 */ if (this.camerawork != null) {
                        /* 3799 */ this.camerawork.processAmpedEvent(this.forinvoke.get(0));
                    }
                    /* 3801 */ this.forinvoke.remove(0);
                }
                /* 3803 */ this.stage.update();
            }

            /* 3806 */ if (this.camerawork != null && this.player != null && this.camera != null) {
                /* 3807 */ this.camerawork.update(getDt() * 0.001F, this.camera);
                /* 3808 */ this.camerawork.getFocusPosition(this.stage.scene.focuspos);
                /* 3809 */ this.stage.scene.setStageLayer(this.camerawork.getCurrentLayer());
            }

            /* 3812 */ if (getGameState() >= 0) {
                /* 3813 */ updateClockSync();
                /* 3814 */ checkReceiveTime();
            }
        }

        public void updateClockSync() {
            /* 3819 */ int t = Clock.time();
            /* 3820 */ if (this.stage != null && this.clocksyncreqtime < t) {
                /* 3821 */ sendClockSyncRequest();
                /* 3822 */ this.clocksyncreqtime = t + this.clocksyncinterval;
                /* 3823 */ if (this.stage.isLoungeMode()) {
                    /* 3824 */ this.clocksyncinterval = Math.min(this.clocksyncinterval + 1000, 9500);
                } else {
                    /* 3826 */ this.clocksyncinterval = Math.min(this.clocksyncinterval + 1000, 4500);
                }
            }
        }

        public void checkReceiveTime() {
            /* 3832 */ int t = Clock.time();
            /* 3833 */ int dt = t - this.lastrecvtime;
            /* 3834 */ if (this.stage.isLoungeMode()) {
                /* 3835 */ if (dt < 21000)
                    return;
                /* 3837 */ } else if (dt < 15000) {
                return;
            }
            /* 3840 */ Exception ex = new Exception("No response from StageServer....");
            /* 3841 */ this.errobj = ex;
            /* 3842 */ setGameState(-2);
            /* 3843 */ System.err.println("No response from StageServer....(Viewer) dt:" + dt);
        }

        public void processAmpedEvent(AmpedEvent e) {
            RecordEvent re;
            /* 3847 */ switch (e.id) {
                case 161:
                    /* 3849 */ re = (RecordEvent) e;

                    /* 3851 */ synchronized (this.records) {
                        /* 3852 */ for (int l = 0; l < re.events.length; l++) {
                            /* 3853 */ this.records.add(new Record(re.time[l], re.events[l]));
                        }
                    }
                    return;
            }
            /* 3858 */ super.processAmpedEvent(e);
        }

        void processLogout(BasicAmpedEvent e) {
            /* 3863 */ if (e.srcid == -1) {
                /* 3864 */ System.out.println("processLogout!!! " + e.argi);
                /* 3865 */ if (e.argi == 0)
                    /* 3866 */ return;
                setGameState(-1);
                /* 3867 */ if (this.con != null)
                    this.con.close();
                /* 3868 */ synchronized (KWTManager.PAINT_LOCK) {
                    /* 3869 */ this.con = null;
                    /* 3870 */ this.stage = null;
                    /* 3871 */ this.player = null;
                }

                /* 3874 */ } else if ((GameSetting.getSetting()).disconnectinfoenabled) {
                /* 3875 */ if (e.args != null && e.args[0] instanceof int[][])
                /* 3876 */ {
                    int[][] state = (int[][]) e.args[0];
                    /* 3877 */ for (int i = 0; i < state.length; i++) {
                        /* 3878 */ if ((state[i]).length >= 2) {
                            /* 3879 */ PlayerModel p = this.stage.getPlayerForEntryID(state[i][0]);
                            /* 3880 */ if (p != null && p instanceof PlayerViewModel)
                                /* 3881 */ ((PlayerViewModel) p).setConnectState(state[i][1]);
                        }
                    }
                }
                /* 3884 */ else {
                    PlayerModel p = this.stage.getPlayerForEntryID(e.srcid);
                    /* 3885 */ if (p != null && p instanceof PlayerViewModel) {
                        /* 3886 */ ((PlayerViewModel) p).setConnectState(e.argi);
                    }
                }

            }
        }

        public void processStageSetup(StageSetupEvent e) {
            /* 3894 */ synchronized (KWTManager.PAINT_LOCK) {

                /* 3896 */ if (this.stage != null) {
                    /* 3897 */ this.stage.restoreClock();
                }

                /* 3900 */ this.clock.setTimeOffset(e.time);
                /* 3901 */ this.clock.setTimeScale(1.0D);
                /* 3902 */ StageClient sc = new StageClient((Clock) this.clock);
                /* 3903 */ sc.setup(e);

                /* 3905 */ setStage(sc);

                /* 3908 */ AmpedModel[] m = this.stage.getPlayers();
                /* 3909 */ for (int l = 0; l < m.length && m[l] != null; l++) {
                    /* 3910 */ PlayerViewModel p = (PlayerViewModel) m[l];
                    /* 3911 */ if (p.getEntryID() != 0) {
                        /* 3912 */ this.player = p;
                        break;
                    }
                }
                /* 3916 */ if (this.player != null)
                    this.myid = this.player.getID();

                /* 3918 */ this.ctr = new PlayerController(this);

                /* 3921 */ UserData ud = UserData.getEmpty();
                /* 3922 */ if (this.player != null)
                    ud.info = this.player.getUserInfo();
                /* 3923 */ updateUserData(ud);

                /* 3926 */ GLRegistry.registerProc(new StageConnector.SceneRegister());

                /* 3929 */ if (e == null || e.id == 101) {
                    /* 3930 */ sendBasicToServer(103);
                } else {
                    /* 3932 */ initCamerawork();
                }
            }

            /* 3937 */ synchronized (KWTManager.PAINT_LOCK) {
                /* 3938 */ ((KWTClock) KWTManager.getClock()).update();
                /* 3939 */ this.clocksyncnum = 0;
                /* 3940 */ this.clocksyncmaxdelay = 0;
                /* 3941 */ this.clocksyncmindelay = 10000;
                /* 3942 */ this.clocksyncdelay = 0;
                /* 3943 */ sendClockSyncRequest();
            }

            /* 3946 */ processGameReady(null);
        }

        public void processAddModel(BasicAmpedEvent e) {
            /* 3953 */ synchronized (KWTManager.PAINT_LOCK) {

                /* 3955 */ AmpedProperty p = (AmpedProperty) e.args[0];
                /* 3956 */ if (p instanceof AmpedProperty.Player) {
                    /* 3957 */ PlayerViewModel pm = (PlayerViewModel) this.stage.createPlayer();
                    /* 3958 */ pm.setProperties((AmpedProperty.Player) p);
                    /* 3959 */ this.stage.addModel(pm, p.id);
                }
                /* 3961 */ else if (p instanceof AmpedProperty.Item) {
                    /* 3962 */ ItemViewModel im = (ItemViewModel) this.stage.createItem();
                    /* 3963 */ im.setProperties((AmpedProperty.Item) p);
                    /* 3964 */ this.stage.addModel(im, p.id);
                }
            }
        }

        public boolean sendToServer(AmpedEvent e) {
            /* 3975 */ if (getGameState() <= 0) {
                /* 3976 */ System.out.println("ILLEGAL STATE : " + getGameState() + " at sendToServer(" + e + ")");
                /* 3977 */ return false;
            }
            try {
                /* 3980 */ this.con.write(e, e.isEnsureEvent());
                /* 3981 */ } catch (IOException ioe) {
                /* 3982 */ ioe.printStackTrace();
                /* 3983 */ System.err.println("StageConnector.Viewer Error....");
                /* 3984 */ this.errobj = ioe;
                /* 3985 */ setGameState(-2);
                /* 3986 */ return false;
            }
            /* 3988 */ return true;
        }

        public void sendClockSyncRequest() {
            /* 3994 */ this.sentclock = Clock.time();
            /* 3995 */ sendToServer(
                    (AmpedEvent) ClockSyncEvent.createRequest(this.senderid = (byte) (this.senderid + 1)));
            /* 3996 */ this.senderc = this.clock.getTime();
        }

        public void processClockSyncResponse(ClockSyncEvent e) {
            /* 4000 */ if (e.senderid != this.senderid)
                return;
            /* 4002 */ int delay = this.clock.getTime() - this.senderc;
            /* 4003 */ if (delay < -1000 || delay > 1000) {
                /* 4004 */ this.lastclock = e.recvclock;
                /* 4005 */ this.clocksyncreqtime = Math.min(this.clocksyncreqtime, Clock.time() + 500);
                /* 4006 */ if (++this.clockerrcount >= 3) {

                    /* 4008 */ this.clock
                            .setTimeOffset(e.serverclock + (this.clocksyncmindelay + this.clocksyncmaxdelay) / 2);
                    /* 4009 */ this.clock.setTimeScale(1.0D);
                }
                return;
            }
            /* 4013 */ this.clockerrcount = 0;
            /* 4014 */ if (delay > 500) {
                /* 4015 */ this.clocksyncreqtime = Math.min(this.clocksyncreqtime,
                        Clock.time() + this.clocksyncinterval / 3);
            }
            /* 4017 */ int newoffs = e.serverclock + KMath.clamp(delay, 0, 1000) * 5 / 7;

            /* 4019 */ this.clock.setTimeOffset(newoffs);

            /* 4021 */ this.clocksyncmindelay = Math.min(this.clocksyncmindelay, delay);
            /* 4022 */ this.clocksyncmaxdelay = Math.max(this.clocksyncmaxdelay, delay);
            /* 4023 */ this.clocksyncnum++;
            /* 4024 */ this.clocksyncdelay += KMath.clamp(delay, 0, 1000);

            /* 4026 */ if (this.lastclock != 0 && this.clocksyncnum > 3) {

                /* 4030 */ int itc = e.recvclock - this.lastclock;
                /* 4031 */ int itsc = newoffs - this.lastserverclock;
                /* 4032 */ double spdrate = itsc / itc;

                /* 4034 */ if (Math.abs(spdrate * this.clock.getTimeScale() - 1.0D) > 0.1D) {
                    /* 4035 */ System.out.println("clock speed unstable.. interval c:" + itc + "  s:" + itsc + "  rate:"
                            + spdrate + "  lastrate:" + this.clock.getTimeScale());
                    /* 4036 */ this.clock.setTimeScale((spdrate + this.clock.getTimeScale()) * 0.5D);
                    /* 4037 */ this.clocksyncreqtime = Math.min(this.clocksyncreqtime,
                            Clock.time() + this.clocksyncinterval / 3);

                }
                /* 4040 */ else if (spdrate >= 0.999D && spdrate <= 1.001D) {
                    /* 4041 */ this.clock.setTimeScale(1.0D);
                } else {
                    /* 4043 */ this.clock.setTimeScale(spdrate);
                }
            }

            /* 4047 */ this.lastclock = e.recvclock;
            /* 4048 */ this.lastserverclock = newoffs;
        }

        public void processDefaultEvent(AmpedEvent e) {
            /* 4053 */ if (this.stage == null)
                /* 4054 */ return;
            this.received.add(e);
        }

        class ViewerTranseiver implements Transeiver {
            public void received(Object src, int ch, InputStream in) throws IOException, ClassNotFoundException {
                /* 4059 */ ExternalizerInputStream eis = new ExternalizerInputStream(in, false);
                /* 4060 */ int num = eis.readByte();
                /* 4061 */ if (StageConnector.Viewer.this.getGameState() == -1
                        || StageConnector.Viewer.this.getGameState() == -2) {
                    /* 4062 */ if (StageConnector.Viewer.this.con != null) {
                        /* 4063 */ GameSetting.printStackTrace("!close! ViewerTranseiver.received getGameState():"
                                + StageConnector.Viewer.this.getGameState());
                        /* 4064 */ StageConnector.Viewer.this.con.close();
                        /* 4065 */ StageConnector.Viewer.this.con = null;
                    }
                    /* 4067 */ System.out.println("StageConnector is closed. : " + this);
                    return;
                }
                /* 4070 */ for (int l = 0; l < num; l++) {
                    /* 4071 */ AmpedEvent e = AmpedEvent.decode((ObjectInput) eis);
                    /* 4072 */ StageConnector.Viewer.this.processAmpedEvent(e);
                }
                /* 4074 */ StageConnector.Viewer.this.lastrecvtime = Clock.time();
            }

            public void send(Object o, Object src, int ch, OutputStream out) throws IOException {
                /* 4078 */ ExternalizerOutputStream eos = new ExternalizerOutputStream(out, false);
                /* 4079 */ ((AmpedEvent) o).encode((ObjectOutput) eos);
            }
        }

        static class Record {
            int time;
            AmpedEvent e;

            Record(int t, AmpedEvent ev) {
                /* 4088 */ this.time = t;
                /* 4089 */ this.e = ev;
            }
        }
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\stage\StageConnector.
 * class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
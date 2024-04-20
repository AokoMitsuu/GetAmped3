package ga2.stage;

import ga2.client.GetAmped2;
import ga2.data.AbilityData;
import ga2.data.ConductDefine;
import ga2.data.GuardianData;
import ga2.data.GuildData;
import ga2.data.MyRoomData;
import ga2.data.NPCInfo;
import ga2.data.OldArcadeData;
import ga2.data.PlaybackRecord;
import ga2.data.UserData;
import ga2.data.UserInfo;
import ga2.data.UserItems;
import ga2.data.net.AmpedException;
import ga2.data.net.ChatMessage;
import ga2.data.net.GameRoomInfo;
import ga2.ganl.GA2Tracker;
import ga2.setting.GameSetting;
import ga2.stage.ai.AiController;
import ga2.stage.event.AmpedEvent;
import ga2.stage.event.AmpedEventLog;
import ga2.stage.event.BasicAmpedEvent;
import ga2.stage.event.FragmentEvent;
import ga2.stage.event.MatchingEvent;
import ga2.stage.event.RelayEvent;
import ga2.stage.event.StageSetupEvent;
import ga2.stage.event.StatusModifierEvent;
import ga2.stage.task.DuelServerTask;
import ga2.stage.task.GuardianTaskChain;
import ga2.stage.task.GuildRoomServerTask;
import ga2.stage.task.LoungeServerTask;
import ga2.stage.task.MyRoomServerTask;
import ga2.stage.task.OldArcadeTaskChain;
import ga2.stage.task.PracticeTask;
import ga2.stage.task.StageServerTask;
import ga2.stage.task.StreetfightTask;
import ga2.stage.task.TeamStreetfightTask;
import ga2.stage.task.TestPlayServerTask;
import ga2.stage.task.TournamentTaskChain;
import ga2.stage.task.UnitedfightTask;

import java.util.ArrayList;
import java.util.Arrays;

import kotori.geom.KMath;
import kotori.geom.Vector3D;
import kotori.net.NetConnection;
import kotori.util.Clock;
import kotori.util.Inttable;
import kotori.util.KotoriUtil;

public class StageServer {
    /* 23 */ public static volatile int existing_count = 0;

    /* 29 */ public static int BONUS_STAR = 0;
    /* 30 */ public static int BONUS_SCORE = 0;
    /* 31 */ public static int BONUS_GM = 0;
    /* 32 */ public static int BONUS_SKILL = 0;

    final Stage stage;

    /* 44 */ private ArrayList<StageServerPlayerEntry> clients = new ArrayList<StageServerPlayerEntry>();
    /* 45 */ private ArrayList<StageServerPlayerEntry> closing = new ArrayList<StageServerPlayerEntry>();
    private StageServerPlayerEntry.Local lentry;
    /* 47 */ private Inttable conids = new Inttable();

    /* 49 */ private ArrayList<StageServerViewerEntry> viewers = new ArrayList<StageServerViewerEntry>();

    /* 53 */ private ArrayList[] invoked = new ArrayList[] { new ArrayList(), new ArrayList(), new ArrayList() };
    /* 54 */ private AmpedEvent[] eventbuf = new AmpedEvent[100];

    private StageServerTask task;

    /* 61 */ private ArrayList saychats = new ArrayList();

    private int nextchattime;

    private StageServerRunner runner;
    /* 66 */ private int stageserverid = -1;

    public GameRoomInfo roominfo;
    /* 69 */ public int roomlistid = -1;

    /* 72 */ Inttable userdatamap = new Inttable();

    AmpedEventLog sendEventLog;

    AmpedEventLog recvEventLog;
    PlaybackRecord playback;
    int nextcheck;
    int emptyentrytime;
    int autoshutdowntime;
    boolean alive;
    boolean connectstateenabled;
    private RepopPlayer[] repopInfo;
    private short[] repopNpcInfo;
    private StatusModifier[][] repopStatusModifiers;
    int nextviewerid;
    private int dummyeid;
    int[] mfmember;
    private Inttable fragments;
    boolean automatch;
    public int automatchphase;
    private StageVoiceChatManager vcmanager;

    protected void finalize() throws Throwable {
        /* 95 */ existing_count--;
        /* 96 */ super.finalize();
    }

    public void update() {
        synchronized (this) {
            int evnum = 0;
            synchronized (this.invoked) {
                for (int i = 0; i < 3; i++) {
                    ArrayList<AmpedEvent> a = this.invoked[i];
                    int anum = a.size();
                    if (anum != 0) {
                        for (int j = 0; j < anum; j++) {
                            Object o = a.get(j);
                            if (o instanceof AmpedEvent) {
                                this.eventbuf[evnum++] = a.get(j);
                                if (evnum >= this.eventbuf.length) {
                                    AmpedEvent[] old = this.eventbuf;
                                    this.eventbuf = new AmpedEvent[this.eventbuf.length + 100];
                                    System.arraycopy(old, 0, this.eventbuf, 0, evnum);
                                }
                            }
                        }
                        a.clear();
                    }
                }
            }
            for (int l = 0; l < evnum; l++) {
                processAmpedEvent((this.eventbuf[l]).entry, this.eventbuf[l]);
                this.eventbuf[l] = null;
            }
            this.stage.update();
            if (this.task != null)
                this.task.updateTask();
        }
        flushEvents(0);
        int time;
        if ((time = Clock.time()) > this.nextcheck) {
            connectionTimeoutCheck(time);
            emptyEntryCheck();
            this.nextcheck = time + 1000;
        }
        if (time > this.nextchattime) {
            flushSayChat();
            this.nextchattime = time + 1000;
        }
    }

    public void invoke(Object o, int lv) {
        synchronized (this.invoked) {
            this.invoked[lv].add(o);
        }
    }

    public void kill() {
        this.alive = false;
    }

    public void close() {
        StageServerPlayerEntry[] a1, a2;
        StageServerViewerEntry[] a3;
        this.runner = null;
        synchronized (this) {
            if (this.stage != null)
                this.stage.clear();
            if (this.task != null) {
                this.task.endTask();
                this.task = null;
            }
        }
        synchronized (this.clients) {
            this.playback = null;
            a1 = (StageServerPlayerEntry[]) this.clients
                    .toArray((Object[]) new StageServerPlayerEntry[this.clients.size()]);
            a2 = (StageServerPlayerEntry[]) this.closing
                    .toArray((Object[]) new StageServerPlayerEntry[this.closing.size()]);
            a3 = (StageServerViewerEntry[]) this.viewers
                    .toArray((Object[]) new StageServerViewerEntry[this.viewers.size()]);
            this.clients.clear();
            this.closing.clear();
            this.lentry = null;
            this.conids.clear();
        }
        int l;
        for (l = 0; l < a1.length; l++) {
            try {
                a1[l].dispose();
            } catch (Exception exception) {
            }
        }
        for (l = 0; l < a2.length; l++) {
            try {
                a2[l].dispose();
            } catch (Exception exception) {
            }
        }
        for (l = 0; l < a3.length; l++) {
            try {
                a3[l].dispose();
            } catch (Exception exception) {
            }
        }
    }

    private PlayerModel createPlayer(UserInfo ui, UserItems items) {
        PlayerModel p = this.stage.createPlayer();
        GetAmped2 ga2 = GetAmped2.getAmped2();
        if ((GameSetting.getSetting()).lanplayenabled && ga2 != null && ga2.isLanPlay() && ui instanceof NPCInfo) {
            int npcid = ((NPCInfo) ui).getID((NPCInfo) ui);
            p.setNPCID(npcid);
        }
        checkItem(ui, items);
        p.setUserInfo(ui, items, false);
        synchronized (this) {
            this.stage.addModel(p, this.stage.nextID());
        }
        return p;
    }

    private void checkItem(UserInfo ui, UserItems items) {
        if (items == null || ui instanceof NPCInfo)
            return;
        if (ui.accs != 0 && !items.hasItem(5, ui.accs)) {
            System.out.println(
                    "checkItemForStageServer...not have accs!!! useridhash:" + ui.useridhash + " itemid:" + ui.accs);
            ui.accs = 0;
        }
        if (ui.weapon != 0 && !items.hasItem(7, ui.weapon)) {
            System.out.println("checkItemForStageServer...not have weapon!!! useridhash:" + ui.useridhash + " itemid:"
                    + ui.weapon);
            ui.weapon = 0;
        }
        if (ui.head != 0 && !items.hasItem(1, ui.head)) {
            System.out.println(
                    "checkItemForStageServer...not have head!!! useridhash:" + ui.useridhash + " itemid:" + ui.head);
            ui.head = 0;
        }
        if (ui.face != 0 && !items.hasItem(2, ui.face)) {
            System.out.println(
                    "checkItemForStageServer...not have face!!! useridhash:" + ui.useridhash + " itemid:" + ui.face);
            ui.face = 0;
        }
        if (ui.body != 0 && ui.body != 20 && !items.hasItem(3, ui.body)) {
            System.out.println(
                    "checkItemForStageServer...not have body!!! useridhash:" + ui.useridhash + " itemid:" + ui.body);
            ui.body = 20;
        }
        if (ui.deco != 0 && !items.hasItem(4, ui.deco)) {
            System.out.println(
                    "checkItemForStageServer...not have deco!!! useridhash:" + ui.useridhash + " itemid:" + ui.deco);
            ui.deco = 0;
        }
    }

    public void setAutoShutdown(int t) {
        this.autoshutdowntime = t;
    }

    public void setPlaybackRecord(PlaybackRecord rec) {
        synchronized (this.clients) {
            this.playback = rec;
        }
    }

    public void setConnectStateEnabled(boolean b) {
        this.connectstateenabled = b;
    }

    public Stage getStage() {
        return this.stage;
    }

    public synchronized void resetStage() {
        this.stage.clear();
        int num = getPlayerEntryNum();
        for (int l = 0; l < num; l++) {
            StageServerPlayerEntry e = playerEntryAt(l);
            e.setPlayer(null);
            e.setState(1);
        }
    }

    public synchronized void setData(Object o) {
        this.stage.setStageData(o);
        this.stage.generateStagePartsModels();
    }

    private class RepopPlayer {
        /* 100 */ private RepopPlayer() {
        }

        public int HP = 1000;
        public int MP = 100;
        public boolean HPShare;
        public int gutsnum;
        public int mpusingrate = 100;
        public int pinchHP = 0;
        public int pinchHPtime = 0;
        public int abrevivenum;
        public int weaponnum;
    }

    public void initRepopPlayerInfo(int num) {
        this.repopInfo = new RepopPlayer[num];
        this.repopStatusModifiers = new StatusModifier[num][];
    }

    public void endRepopPlayerInfo() {
        this.repopInfo = null;
        this.repopStatusModifiers = null;
    }

    public void saveRepopPlayersInfo() {
        saveRepopPlayersInfo(false);
    }

    public void saveRepopPlayersInfo(boolean npcInfo) {
        int num = getPlayerEntryNum();
        this.repopInfo = new RepopPlayer[num];
        this.repopStatusModifiers = new StatusModifier[num][];
        for (int i = 0; i < num; i++) {
            StageServerPlayerEntry e = playerEntryAt(i);
            saveRepopPlayerInfo(e, i, false);
        }
        if (npcInfo) {
            PlayerModel[] mfpm = this.stage.getMegaFroceNPC();
            if (mfpm != null) {
                this.repopNpcInfo = new short[mfpm.length];
                for (int j = 0; j < mfpm.length; j++) {
                    if (mfpm[j].isDead()) {
                        this.repopNpcInfo[j] = 0;
                    } else {
                        this.repopNpcInfo[j] = (short) (mfpm[j]).HP;
                    }
                }
            }
        }
    }

    public short getGurdNpcInfo(int no) {
        if (this.repopNpcInfo == null || this.repopNpcInfo.length == 0)
            return -1;
        int mfnum = (this.mfmember == null) ? 0 : this.mfmember.length;
        if (mfnum + no < this.repopNpcInfo.length)
            return this.repopNpcInfo[mfnum + no];
        return -1;
    }

    public void saveRepopPlayerInfo(int no) {
        if (getPlayerEntryNum() == 0)
            return;
        saveRepopPlayerInfo(playerEntryAt(0), no, true);
    }

    public StageServer(Stage s) {
        this.nextcheck = 0;

        /* 109 */ this.alive = true;

        /* 1784 */ this.fragments = new Inttable();
        this.stage = s;
        s.server = this;
        this.task = null;
        existing_count++;
    }

    private void saveRepopPlayerInfo(StageServerPlayerEntry e, int no, boolean recovery) {
        if (this.repopInfo[no] == null)
            this.repopInfo[no] = new RepopPlayer();
        (this.repopInfo[no]).HP = e.player.HP;
        (this.repopInfo[no]).MP = e.player.psd.MAXMP;
        (this.repopInfo[no]).HPShare = e.player.HPshare;
        (this.repopInfo[no]).gutsnum = e.player.gutsnum;
        (this.repopInfo[no]).mpusingrate = e.player.mpusingrate;
        (this.repopInfo[no]).pinchHP = e.player.pinchHP;
        (this.repopInfo[no]).pinchHPtime = e.player.pinchHPtime;
        (this.repopInfo[no]).abrevivenum = e.player.abrevivenum;
        (this.repopInfo[no]).weaponnum = e.player.wpnum;
        this.repopStatusModifiers[no] = e.player.getStatusModifiers();
        if (!recovery)
            return;
        for (int i = 0; i < this.repopInfo.length; i++) {
            if (this.repopInfo[i] != null) {
                (this.repopInfo[i]).HP = Math.min((this.repopInfo[no]).HP + 500, e.player.psd.MAXHP);
                (this.repopInfo[i]).MP = Math.min((this.repopInfo[no]).MP + 50, e.player.psd.MAXMP);
            }
        }
    }

    /* 1786 */ public void repopPlayer(int no) {
        if (getPlayerEntryNum() == 0)
            return;
        repopPlayer(playerEntryAt(0), no);
    }

    public void repopPlayer(StageServerPlayerEntry e) {
        if (getPlayerEntryNum() == 0)
            return;
        repopPlayer(e, 0);
    }

    private synchronized void repopPlayer(StageServerPlayerEntry e, int no) {
        PlayerModel p = e.getPlayerModel();
        if (p == null) {
            p = createPlayer(e.userinfo, e.useritems);
        } else {
            p.setUserInfo(e.userinfo);
        }
        p.guardmode = e.guardmode;
        p.setGroup(e.group);
        this.stage.placePlayer(p);
        e.setPlayer(p);
        if (this.repopInfo != null && this.repopInfo[no] != null) {
            p.HP = (this.repopInfo[no]).HP;
            p.MP = (this.repopInfo[no]).MP;
            p.gutsnum = (this.repopInfo[no]).gutsnum;
            p.mpusingrate = (this.repopInfo[no]).mpusingrate;
            p.pinchHP = (this.repopInfo[no]).pinchHP;
            p.pinchHPtime = (this.repopInfo[no]).pinchHPtime;
            p.abrevivenum = (this.repopInfo[no]).abrevivenum;
            p.HPshare = (this.repopInfo[no]).HPShare;
            p.wpnum = (this.repopInfo[no]).weaponnum;
            for (int w = p.countWeapons() - 1; w >= p.wpnum; w--)
                p.wpstock[w] = 0;
        }
    }

    public synchronized void repopPlayers() {
        int num = getPlayerEntryNum();
        StageServerPlayerEntry[] a = new StageServerPlayerEntry[num];
        int l;
        for (l = 0; l < num; l++)
            a[l] = playerEntryAt(l);
        for (l = 0; l < num; l++) {
            StageServerPlayerEntry e = a[l];
            repopPlayer(e, l);
            if (this.task != null)
                switch (e.getState()) {
                    case -1:
                        this.task.playerDown(e);
                        break;
                    case 3:
                        this.task.playerLogouted(e);
                        break;
                }
        }
        this.repopInfo = null;
    }

    public void sendRepopStatusModifiers() {
        if (this.repopStatusModifiers != null) {
            int num = getPlayerEntryNum();
            StageServerPlayerEntry[] a = new StageServerPlayerEntry[num];
            int l;
            for (l = 0; l < num; l++)
                a[l] = playerEntryAt(l);
            for (l = 0; l < num; l++) {
                StageServerPlayerEntry e = a[l];
                PlayerModel pm = e.player;
                StatusModifier[] modi = this.repopStatusModifiers[l];
                if (modi != null)
                    for (int i = 0; i < modi.length; i++) {
                        if ((modi[i]).id != -1)
                            if ((modi[i]).sec > 0) {
                                int left = modi[i].getTimeLeft() / 1000;
                                if (left > 0)
                                    pm.sendEvent((AmpedEvent) StatusModifierEvent.create(pm, (modi[i]).id,
                                            modi[i].getAbilityData(), left));
                            } else {
                                pm.sendEvent((AmpedEvent) StatusModifierEvent.create(pm, (modi[i]).id,
                                        modi[i].getAbilityData(), 0));
                            }
                    }
                this.repopStatusModifiers[l] = null;
            }
            this.repopStatusModifiers = null;
        }
    }

    public void savePlayersGetItem() {
        if (this.stage.rule.getClass() == GuardianRule.class || this.stage.rule instanceof DungeonRule
                || this.stage.rule instanceof DungeonTimeAttackRule || this.stage.rule instanceof CoreRule) {
            int num = getPlayerEntryNum();
            for (int l = 0; l < num; l++) {
                StageServerPlayerEntry e = playerEntryAt(l);
                this.stage.rule.addPlayerGetItem(e.entryid, e.player.rec.getItem);
            }
        }
    }

    public synchronized void gameset() {
        if (this.automatch && this.task instanceof ga2.stage.task.GameServerTask)
            this.automatchphase++;
        if (this.task != null)
            this.task.gameset();
    }

    public synchronized void setTask(StageServerTask t) {
        if (this.task != null)
            this.task.endTask();
        this.task = t;
        t.setStageServer(this);
        t.startTask();
    }

    public synchronized StageServerTask getTask() {
        return this.task;
    }

    int loginFromLocal(int eid, int guardmode, StageConnector.Local lconn, boolean tool) throws AmpedException {
        UserData ud = getUserData(eid, tool);
        if (ud == null)
            return 0;
        StageServerPlayerEntry.Local entry = new StageServerPlayerEntry.Local(this, lconn);
        entry.setEntry(eid, ud);
        PlayerModel p = this.stage.getPlayerForEntryID(eid);
        if (p == null) {
            p = createPlayer(ud.info, ud.items);
            synchronized (this) {
                try {
                    this.task.playerLoginTest(ud.info.useridhash);
                } catch (RuntimeException re) {
                    System.out.println("loginFromLocal playerLoginTest faild");
                    this.stage.removeModel(p);
                    throw re;
                }
            }
        }
        if (p != null) {
            p.guardmode = guardmode;
            entry.setPlayer(p);
        }
        entry.guardmode = guardmode;
        synchronized (this) {
            this.task.playerLogined(entry);
            if (this.runner != null)
                this.runner.stageMemberAdded(this, eid);
            if (p != null)
                notifyModelAdded(p);
            setLocalEntry(entry);
            entry.setState(1);
        }
        return 0;
    }

    void processLocalAmpedEvent(AmpedEvent e) {
        if (this.lentry != null) {
            e.entry = this.lentry;
            this.lentry.processAmpedEvent(e);
        }
    }

    void testLogin(NetConnection c, RemoteLoginInfo i) {
        Object ret = null;
        synchronized (this) {
            try {
                this.task.playerLoginTest(i.uid);
                ret = Integer.valueOf(0);
            } catch (Exception e) {
                ret = e;
                System.out.println("test login check failed uid:" + i.uid + " ssid:" + i.stageserverid + " runner:"
                        + this.runner + " ex:" + e);
            }
        }
        try {
            c.write(ret, true);
        } catch (Exception exception) {
        } finally {
            c.close();
        }
    }

    void loginFromRemote(NetConnection c, RemoteLoginInfo i) {
        try {
            StageServerPlayerEntry.Remote entry;
            int eid = i.entryid;
            UserData ud = getUserData(eid);
            if (ud == null)
                return;
            int cid = getConnectID(eid);
            if ((GameSetting.getSetting()).concheckenabled && (i.uid != ud.info.useridhash || i.conid != cid)) {
                System.out.println("StageServer loginFromRemote illegal entry!!!! u:" + i.uid + ":" + ud.info.useridhash
                        + " c:" + i.conid + ":" + cid);
                return;
            }
            synchronized (this) {
                try {
                    this.task.playerLoginTest(i.uid);
                } catch (Exception e) {
                    c.write(e, true);
                    throw e;
                }
                PlayerModel p = this.stage.getPlayerForEntryID(i.entryid);
                if (p == null)
                    p = createPlayer(ud.info, ud.items);
                p.guardmode = i.guardmode;
                p.areahash = ud.areahash;
                synchronized (c) {
                    c.write(Integer.valueOf(0), true);
                    entry = new StageServerPlayerEntry.Remote(this, c);
                    entry.setEntry(eid, ud);
                    entry.setCoEventLog(this.sendEventLog, this.recvEventLog);
                }
                entry.setPlayer(p);
                entry.guardmode = i.guardmode;
                entry.areahash = ud.areahash;
                entry.loginnavi = i.navi;
                this.task.playerLogined(entry);
                if (this.runner != null)
                    this.runner.stageMemberAdded(this, eid);
                notifyModelAdded(p);
                addEntry(entry, cid);
                StageSetupEvent sse = this.stage.getStageSetupEvent(101);
                entry.sendFragment((AmpedEvent) sse, 1300);
                entry.setState(1);
                GA2Tracker.stageModeTracking(ud.info.useridhash, this.stage.getStageMode());
            }
            synchronized (this.clients) {
                entry.flushEvents(0);
            }
        } catch (Exception e) {
            System.out.println("!close! loginFromRemote:");
            e.printStackTrace();
            c.close();
        }
    }

    void loginViewer(NetConnection c, RemoteLoginInfo i) {
        try {
            synchronized (this) {
                StageServerViewerEntry entry;
                int num;
                try {
                    this.task.viewerLoginTest(i.entryid);
                } catch (Exception e) {
                    c.write(e, true);
                    throw e;
                }
                synchronized (c) {
                    c.write(Integer.valueOf(0), true);
                    entry = new StageServerViewerEntry(this, c, this.nextviewerid);
                    this.nextviewerid = this.nextviewerid + 1 & 0xFFFF;
                }
                StageSetupEvent sse = this.stage.getStageSetupEvent(101);
                if (!this.task.relayStart(entry.viewerid))
                    entry.sendToClient((AmpedEvent) sse);
                synchronized (this.clients) {
                    this.viewers.add(entry);
                    num = this.viewers.size();
                }
                sendEventToAll((AmpedEvent) BasicAmpedEvent.create(-1, 164, num));
                sendConnectState(entry);
            }
        } catch (Exception ex) {
            System.out.println("!close! loginViewer:");
            ex.printStackTrace();
            c.close();
        }
    }

    void logoutViewer(StageServerViewerEntry e) {
        int num;
        e.close();
        synchronized (this.clients) {
            this.viewers.remove(e);
            num = this.viewers.size();
        }
        sendEventToAll((AmpedEvent) BasicAmpedEvent.create(-1, 164, num));
    }

    void sendConnectState(StageServerViewerEntry e) {
        if (!this.connectstateenabled)
            return;
        int[][] state = new int[getPlayerEntryNum()][2];
        for (int i = 0; i < state.length; i++) {
            StageServerPlayerEntry entry = playerEntryAt(i);
            if (entry != null) {
                state[i][0] = entry.getEntryID();
                state[i][1] = (entry.getState() == -1) ? (!entry.isEnforce() ? -1 : 4) : 0;
            }
        }
        e.sendToClient((AmpedEvent) BasicAmpedEvent.create(0, 106, state));
    }

    void testViewerLogin(NetConnection c, RemoteLoginInfo i) {
        Object ret = null;
        synchronized (this) {
            try {
                this.task.viewerLoginTest(i.entryid);
                ret = Integer.valueOf(0);
            } catch (Exception e) {
                ret = e;
            }
        }
        try {
            c.write(ret, true);
        } catch (Exception exception) {
        } finally {
            c.close();
        }
    }

    void logout(StageServerPlayerEntry e, boolean enforce) {
        boolean enforced = false;
        Exception err = null;
        synchronized (this) {
            if (enforce)
                e.reward = null;
            try {
                if (this.task != null && (!this.automatch || !(this.task instanceof ga2.stage.task.GameServerTask))) {
                    this.task.playerLogoutTest(e);
                    this.task.playerLogouted(e);
                }
            } catch (Exception ex) {
                err = ex;
            }
            synchronized (this.clients) {
                if (err != null) {
                    if (!enforce) {
                        e.sendToClient((AmpedEvent) BasicAmpedEvent.create(-1, 106, 0));
                        e.flushEvents(0);
                        return;
                    }
                    System.out.println("playerLogoutTest() failed... Enforce logout eid:" + e.entryid + " uid:"
                            + ((e.userdata == null) ? "*" : e.userdata.userid) + " ssid[" + this.stageserverid
                            + "] runner:" + this.runner);
                    enforced = true;
                    e.setState(-1);
                    if (this.connectstateenabled) {
                        e.setEnforce(true);
                        sendEventToAll(
                                (AmpedEvent) BasicAmpedEvent.create(e.getEntryID(), 106, !e.isEnforce() ? -1 : 4));
                    }
                } else {
                    e.sendToClient((AmpedEvent) BasicAmpedEvent.create(-1, 106, 1));
                    e.flushEvents(0);
                }
                if (enforced) {
                    System.out.println("playerLogout enforce eid:" + e.entryid + ", userid:"
                            + ((e.userdata == null) ? "*" : e.userdata.userid) + " ssid[" + this.stageserverid
                            + "] runner:" + this.runner);
                    return;
                }
                if (e == this.lentry) {
                    this.lentry = null;
                } else {
                    this.clients.remove(e);
                    this.conids.remove(e.entryid);
                    if (!this.closing.contains(e)) {
                        e.autoclosetime = Clock.time() + 5000;
                        this.closing.add(e);
                    }
                }
                e.setState(3);
            }
        }
    }

    private void processFragment(StageServerPlayerEntry entry, FragmentEvent e) {
        FragmentEvent[] fe = (FragmentEvent[]) this.fragments.get(e.seq);

        /* 1788 */ if (fe == null) {
            /* 1789 */ fe = new FragmentEvent[e.num];
            /* 1790 */ this.fragments.put(e.seq, fe);
        }
        /* 1792 */ fe[e.idx] = e;
        /* 1793 */ for (int i = 0; i < fe.length;) {
            if (fe[i] == null)
                return;
            i++;
        }

        /* 1795 */ AmpedEvent ae = FragmentEvent.mergeEvent(fe);

        /* 1797 */ processAmpedEvent(entry, ae, false);

        /* 1799 */ this.fragments.remove(e.seq);
    }

    void remoteClosed(StageServerPlayerEntry e) {
        if (this.runner != null)
            this.runner.stageMemberRemoved(this, e.entryid);
        synchronized (this.clients) {
            if (this.closing.remove(e)) {
                e.setState(3);
                System.out.println("client normaly disconencted. " + e);
                return;
            }
            if (!this.clients.contains(e)) {
                e.setState(-1);
                System.out.println("neither closing nor clients : " + e);
                return;
            }
        }
        GameSetting.printStackTrace("error client disconencted. " + e);
        synchronized (this) {
            e.setState(-1);
            if (this.connectstateenabled)
                sendEventToAll((AmpedEvent) BasicAmpedEvent.create(e.getEntryID(), 106, !e.isEnforce() ? -1 : 4));
            if (this.task != null)
                this.task.playerDown(e);
        }
    }

    void connectionTimeoutCheck(int t) {
        ArrayList<StageServerPlayerEntry> a = null;
        ArrayList<StageServerViewerEntry> b = null;
        synchronized (this.clients) {
            int l;
            for (l = 0; l < this.closing.size(); l++) {
                StageServerPlayerEntry e = this.closing.get(l);
                if (e.autoclosetime != 0 && t >= e.autoclosetime) {
                    if (a == null)
                        a = new ArrayList();
                    a.add(e);
                }
            }
            for (l = 0; l < this.clients.size(); l++) {
                StageServerPlayerEntry e = this.clients.get(l);
                if (e.isConnectionTimeOuted(t)) {
                    if (a == null)
                        a = new ArrayList<StageServerPlayerEntry>();
                    a.add(e);
                }
            }
            for (l = 0; l < this.viewers.size(); l++) {
                StageServerViewerEntry e = this.viewers.get(l);
                if (e.isConnectionTimeOuted(t)) {
                    if (b == null)
                        b = new ArrayList();
                    b.add(e);
                }
            }
        }
        if (a != null) {
            int size = a.size();
            for (int l = 0; l < size; l++) {
                StageServerPlayerEntry r = a.get(l);
                remoteClosed(r);
                r.dispose();
            }
        }
        if (b != null) {
            int size = b.size();
            for (int l = 0; l < size; l++) {
                StageServerViewerEntry r = b.get(l);
                logoutViewer(r);
            }
        }
    }

    void emptyEntryCheck() {
        synchronized (this.clients) {
            int size = this.clients.size();
            for (int l = 0; l < size; l++) {
                StageServerPlayerEntry e = this.clients.get(l);
                if (!(e instanceof StageServerPlayerEntry.Debug) && e.isConnected()) {
                    this.emptyentrytime = 0;
                    break;
                }
            }
        }
        this.emptyentrytime += 1000;
    }

    public UserData getUserData(int eid) {
        return getUserData(eid, false);
    }

    public UserData getUserData(int eid, boolean tool) {
        UserData userData;
        if (this.runner != null) {
            userData = this.runner.getUserData(eid);
            if (userData != null)
                return userData;
        }
        synchronized (this.userdatamap) {
            userData = (UserData) this.userdatamap.get(eid);
            if (userData != null)
                return userData;
        }
        if ((eid & Integer.MIN_VALUE) != 0) {
            int npcid = eid & 0x7FFF;
            userData = UserData.getEmpty();
            userData.info.set((UserInfo) GameSetting.getSetting().getNPCInfo(npcid));
            userData.info.useridhash = KotoriUtil.getNameHash(userData.info.name);
        } else if (tool) {
            userData = UserData.getEmpty();
            userData.info.useridhash = KotoriUtil.getNameHash(userData.info.name);
        } else {
            return null;
        }
        return userData;
    }

    public void putUserData(int eid, UserData ud) {
        synchronized (this.userdatamap) {
            this.userdatamap.put(eid, ud);
        }
    }

    public Object getUserObject(int type, int eid) {
        if (this.runner != null)
            return this.runner.getUserObject(type, eid);
        return null;
    }

    public int getConnectID(int eid) {
        if (!(GameSetting.getSetting()).concheckenabled)
            return -1;
        int cid = -1;
        if (this.runner != null) {
            cid = this.runner.getConnectID(eid);
            if (cid != -1)
                return cid;
        }
        return cid;
    }

    public synchronized int loginNPC(int npcid) {
        UserData ud = UserData.getEmpty();
        ud.info = (UserInfo) GameSetting.getSetting().getNPCInfo(npcid);
        try {
            this.task.playerLoginTest(ud.info.useridhash);
        } catch (AmpedException ae) {
            System.out.println("loginNPC failed... " + ae);
        }
        StageServerPlayerEntry.Debug entry = new StageServerPlayerEntry.Debug(this);
        entry.setEntry(0xFF000000 | ++this.dummyeid, ud);
        PlayerModel p = createPlayer(ud.info, null);
        entry.setPlayer(p);
        this.task.playerLogined(entry);
        if (this.runner != null)
            this.runner.stageMemberAdded(this, 0);
        notifyModelAdded(p);
        addEntry(entry);
        entry.processAmpedEvent((AmpedEvent) BasicAmpedEvent.create(p.getID(), 103));
        entry.setState(1);
        return 0;
    }

    public void makeNPCController() {
        int num = getPlayerEntryNum();
        for (int l = 0; l < num; l++) {
            StageServerPlayerEntry e = playerEntryAt(l);
            if (e instanceof StageServerPlayerEntry.Debug || e instanceof StageServerPlayerEntry.StressTest) {
                PlayerModel p = e.getPlayerModel();
                NPCInfo ni = new NPCInfo();
                ni.aitype = 5;
                ni.judgment = 5;
                ni.courage = 5;
                ni.reflex = 3;
                ConductDefine cd = new ConductDefine();
                cd.set((ConductDefine.Operation) ConductDefine.motionSetEnd(-107));
                ni.conduct(1, cd);
                cd = new ConductDefine();
                cd.search = 1;
                cd.move = 1;
                cd.attack = 5;
                cd.branchConduct(1).co(cd.checkWaitTime(100));
                ni.conduct(2, cd);
                cd = new ConductDefine();
                cd.search = 1;
                cd.move = 7;
                cd.branchConduct(0).co(cd.checkWaitTime(4));
                ni.conduct(2, cd);
                cd = new ConductDefine();
                cd.set((ConductDefine.Operation) ConductDefine.waitDown());
                cd.pack((ConductDefine.Operation) ConductDefine.chatSet(""));
                ni.conduct(4, cd);
                AiController.setController(p, ni);
                ((AiController) p.ctr).startController();
            }
        }
    }

    public synchronized StageServerPlayerEntry.StressTest loginStressTestNPC(int npcid) throws Exception {
        UserData ud = UserData.getEmpty();
        UserInfo ui = new UserInfo();
        ui.set((UserInfo) GameSetting.getSetting().getNPCInfo(npcid));
        ud.info = ui;
        try {
            this.task.playerLoginTest(ud.info.useridhash);
        } catch (AmpedException ae) {
            System.out.println("loginNPC failed... " + ae);
            throw ae;
        }
        StageServerPlayerEntry.StressTest entry = new StageServerPlayerEntry.StressTest(this);
        entry.setEntry(0xFF000000 | ++this.dummyeid, ud);
        PlayerModel p = createPlayer(ud.info, null);
        entry.setPlayer(p);
        this.task.playerLogined(entry);
        if (this.runner != null)
            this.runner.stageMemberAdded(this, entry.entryid);
        notifyModelAdded(p);
        addEntry(entry);
        StageSetupEvent sse = this.stage.getStageSetupEvent(101);
        entry.sendToClient((AmpedEvent) sse);
        entry.flushEvents(0);
        entry.setState(1);
        return entry;
    }

    public void chargeMegaForceMembers(int eplv) {
        chargeMegaForceMembers(eplv, null, false, false);
    }

    public void chargeMegaForceMembers(int eplv, int[] removenpc) {
        chargeMegaForceMembers(eplv, removenpc, false, false);
    }

    public void chargeMegaForceMembers(int eplv, boolean move) {
        chargeMegaForceMembers(eplv, null, move, false);
    }

    public void chargeMegaForceMembers(int eplv, int[] removenpc, boolean move, boolean keephp) {
        GameSetting set = GameSetting.getSetting();
        if (move && this.mfmember != null) {
            boolean kh = (keephp && this.repopNpcInfo != null && this.mfmember.length <= this.repopNpcInfo.length);
            for (int j = 0; j < this.mfmember.length; j++) {
                PlayerModel p = this.stage.createPlayer((UserInfo) set.getNPCInfo(this.mfmember[j]),
                        this.stage.getPlayersEntry());
                if (kh)
                    p.HP = this.repopNpcInfo[j];
                this.stage.placePlayer(p);
                p.sendVanish(true);
            }
            return;
        }
        this.repopNpcInfo = null;
        this.mfmember = null;
        if (removenpc == null)
            removenpc = new int[0];
        int num = getPlayerEntryNum();
        int snum = 4 - num;
        if (snum == 0)
            return;
        boolean doriller = false;
        boolean quest11 = false;
        boolean relief = false;
        int reliefrank = Integer.MIN_VALUE;
        ArrayList<Integer> heros = new ArrayList();
        for (int l = 0; l < num; l++) {
            StageServerPlayerEntry e = playerEntryAt(l);
            if (e.userinfo != null && (e.userinfo.getRank(true)).idx < 3)
                doriller = true;
            StagePlayerData psd = e.player.psd;
            AbilityData[] ad = psd.getAbilities(110);
            for (int j = 0; j < ad.length; j++) {
                Integer id = Integer.valueOf((ad[j]).val);
                if (!heros.contains(id) && Math.random() < 0.5D && !_icontains(removenpc, id.intValue()))
                    heros.add(id);
            }
            if (psd.hasAbility(111)) {
                relief = true;
                reliefrank = Math.max(reliefrank, psd.sumAbility(111));
            }
            if (!quest11 && !_icontains(removenpc, NPCInfo.id("MF", 0)))
                quest11 = (e.userquest != null && e.questChallenging(e.userquest.getChallenging(11)));
        }
        int mfrank = relief ? reliefrank : 0;
        switch (eplv) {
            case 1:
            case 2:
                mfrank += 0;
                break;
            case 3:
                mfrank++;
                break;
            case 4:
            case 5:
                mfrank += 2;
                break;
            case 6:
            case 7:
                mfrank += 3;
                break;
            case 8:
            case 9:
                mfrank += 4;
                break;
            case 10:
                mfrank += 5;
                break;
        }
        GuardianData data = set.getGuardian();
        int[][] supporter = relief ? data.relief : data.supporter;
        mfrank = KMath.clamp(mfrank, 0, supporter.length - 1);
        int[] supdata = supporter[mfrank];
        if (removenpc.length > 0)
            for (int j = 0; j < removenpc.length; j++) {
                int rem = Arrays.binarySearch(supdata, removenpc[j]);
                if (rem >= 0) {
                    int[] ch = new int[supdata.length - 1];
                    for (int k = 0, c = 0; k < supdata.length && c < ch.length; k++) {
                        if (rem != k)
                            ch[c++] = supdata[k];
                    }
                    supdata = ch;
                }
            }
        int[] sup = GuardianData.randomChoose(supdata, snum);
        if (doriller && _icontains(sup, NPCInfo.id("MF", 2)) && supdata.length > snum) {
            int[] ch = new int[supdata.length - snum];
            int c = 0;
            int j;
            for (j = 0; j < supdata.length && c < ch.length; j++) {
                if (!_icontains(sup, supdata[j]))
                    ch[c++] = supdata[j];
            }
            if (c > 0)
                for (j = 0; j < sup.length; j++) {
                    if (sup[j] == NPCInfo.id("MF", 2)) {
                        sup[j] = ch[(int) (Math.random() * c)];
                        break;
                    }
                }
        }
        if (heros.size() > 0) {
            int heronum = Math.min(sup.length, heros.size());
            int[] t = new int[sup.length];
            int c = 0;
            while (c < heronum) {
                int idx = (int) (Math.random() * heros.size());
                t[c++] = ((Integer) heros.get(idx)).intValue();
                heros.remove(idx);
            }
            for (int j = 0; c < t.length && j < sup.length; j++) {
                if (!_icontains(t, sup[j]))
                    t[c++] = sup[j];
            }
            sup = t;
        }
        if (quest11 && !_icontains(sup, NPCInfo.id("MF", 0)))
            sup[(int) (Math.random() * sup.length)] = NPCInfo.id("MF", 0);
        ((GuardianRule) (getStage()).rule).changeMegaForceMembers(sup);
        for (int i = 0; i < snum; i++) {
            PlayerModel p = this.stage.createPlayer((UserInfo) set.getNPCInfo(sup[i]), this.stage.getPlayersEntry());
            this.stage.placePlayer(p);
            p.sendVanish(true);
            notifyModelAdded(p);
        }
        this.mfmember = sup;
    }

    private boolean _icontains(int[] i, int id) {
        for (int l = 0; l < i.length;) {
            if (i[l] == id)
                return true;
            l++;
        }
        return false;
    }

    public boolean containsMegaForceMember(int[] npcid) {
        if (this.mfmember == null)
            return false;
        for (int l = 0; l < this.mfmember.length; l++) {
            for (int j = 0; j < npcid.length; j++) {
                if (this.mfmember[l] == npcid[j])
                    return true;
            }
        }
        return false;
    }

    public boolean allDisconnect() {
        int num = getPlayerEntryNum();
        if (num == 0)
            return false;
        int count = 0;
        for (int l = 0; l < num; l++) {
            StageServerPlayerEntry e = playerEntryAt(l);
            if (e.getState() == -1 || e.getState() == 4 || e.getState() == 3)
                count++;
        }
        return (count == num);
    }

    public void completeQuest(boolean last) {
        int num = getPlayerEntryNum();
        for (int l = 0; l < num; l++) {
            StageServerPlayerEntry e = playerEntryAt(l);
            if (e.getState() == -1)
                ;
        }
    }

    /* 1805 */ void relayViewer(RelayEvent e) {
        if (e.srcid < 0)

        /* 1807 */ {
            int size = this.viewers.size();
            /* 1808 */ for (int l = 0; l < size; l++) {
                /* 1809 */ StageServerViewerEntry entry = this.viewers.get(l);
                /* 1810 */ entry.sendToClient(e.e);
            }
        } else
        /* 1813 */ {
            int size = this.viewers.size();
            /* 1814 */ for (int l = 0; l < size; l++)
            /* 1815 */ {
                StageServerViewerEntry entry = this.viewers.get(l);
                /* 1816 */ if (entry.viewerid == e.srcid)
                /* 1817 */ {
                    entry.sendToClient(e.e);
                    break;
                }
            }
        }
    }

    public int getPlayerEntryNum() {
        synchronized (this.clients) {
            return this.clients.size() + ((this.lentry != null) ? 1 : 0);
        }
    }

    public StageServerPlayerEntry playerEntryAt(int i) {
        synchronized (this.clients) {
            if (this.lentry != null)
                return (i == 0) ? this.lentry : this.clients.get(i - 1);
            return this.clients.get(i);
        }
    }

    public void removePlayerEntry(StageServerPlayerEntry e) {
        synchronized (this.clients) {
            if (e == this.lentry) {
                this.lentry = null;
            } else {
                this.clients.remove(e);
                this.conids.remove(e.entryid);
            }
        }
    }

    public Object getClientsLock() {
        return this.clients;
    }

    public void sendEventToAll(AmpedEvent e) {
        synchronized (this.clients) {
            int size = this.clients.size();
            int l;
            for (l = 0; l < size; l++) {
                StageServerPlayerEntry entry = this.clients.get(l);
                if (e.entry != entry)
                    entry.sendFragment(e);
            }
            if (this.lentry != null && e.entry != this.lentry)
                this.lentry.sendToClientWithoutLock(e, true);
            if (this.playback != null)
                this.playback.addRecord(e, this.stage.orgclock.getTime());
            size = this.viewers.size();
            for (l = 0; l < size; l++) {
                StageServerViewerEntry entry = this.viewers.get(l);
                entry.sendToClient(e);
            }
        }
    }

    void flushEvents(int lv) {
        synchronized (this.clients) {
            int size = this.clients.size();
            int l;
            for (l = 0; l < size; l++) {
                try {
                    StageServerPlayerEntry entry = this.clients.get(l);
                    entry.flushEvents(lv);
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
            size = this.viewers.size();
            for (l = 0; l < size; l++) {
                StageServerViewerEntry entry = this.viewers.get(l);
                entry.flushEvents();
            }
        }
    }

    public void notifyModelAdded(AmpedModel m) {
        BasicAmpedEvent e = BasicAmpedEvent.create(m.getID(), 70, m.getProperties());
        sendEventToAll((AmpedEvent) e);
    }

    public void notifyModelRemoved(AmpedModel m) {
        BasicAmpedEvent e = BasicAmpedEvent.create(m.getID(), 71);
        sendEventToAll((AmpedEvent) e);
    }

    public void sendStageSetup() {
        sendStageSetup(101);
    }

    public void sendStageSetup(int id) {
        StageSetupEvent sse = this.stage.getStageSetupEvent(id);
        synchronized (this.clients) {
            int size = this.clients.size();
            for (int l = 0; l < size; l++) {
                StageServerPlayerEntry entry = this.clients.get(l);
                entry.sendFragment((AmpedEvent) sse, 1300);
            }
            if (this.lentry != null)
                this.lentry.sendToClientWithoutLock((AmpedEvent) sse, true);
            if (this.playback != null)
                this.playback.addRecord((AmpedEvent) sse, this.stage.orgclock.getTime());
            size = this.viewers.size();
            boolean matching = this.stage.isMachingRoomMode();
            BasicAmpedEvent vne = matching ? BasicAmpedEvent.create(-1, 164, size) : null;
            for (int i = 0; i < size; i++) {
                StageServerViewerEntry entry = this.viewers.get(i);
                entry.sendToClient((AmpedEvent) sse);
                if (matching)
                    entry.sendToClient((AmpedEvent) vne);
            }
        }
    }

    synchronized void playerGameReady(StageServerPlayerEntry entry, AmpedEvent e) {
        if (this.task == null)
            throw new NullPointerException("StageServerTask must not be null. ssid[" + this.stageserverid + "]");
        this.task.playerGameReady(entry);
    }

    void addEntry(StageServerPlayerEntry e) {
        addEntry(e, -1);
    }

    void addEntry(StageServerPlayerEntry e, int cid) {
        synchronized (this.clients) {
            this.clients.add(e);
        }
        synchronized (this.conids) {
            this.conids.put(e.entryid, Integer.valueOf(cid));
        }
        if ((GameSetting.getSetting()).npcprocesseventlog && getStage().getStageMode() == 12) {
            String str = "StageServer addEntry to STAGEMODE_STADIUM!!!! eid:" + e.entryid + " pid:" + e.pid;
            if (e.userdata != null) {
                if (e.userdata.userid != null)
                    str = String.valueOf(str) + " uid:" + e.userdata.userid;
            } else {
                str = String.valueOf(str) + " userdata:null";
            }
            if (e.userinfo != null) {
                str = String.valueOf(str) + " uidh:" + e.userinfo.useridhash + " admin:" + e.userinfo.adminflag;
                String addr = e.getAddrFromHash();
                if (addr != null)
                    str = String.valueOf(str) + " hashaddr:" + addr;
                addr = e.getAddrFromEntryID();
                if (addr != null)
                    str = String.valueOf(str) + " entryaddr:" + addr;
            } else {
                str = String.valueOf(str) + " userinfo:null";
            }
            if (e.player != null) {
                str = String.valueOf(str) + " nid:" + e.player.getNPCID();
                if (e.player.getName() != null)
                    str = String.valueOf(str) + " name:" + e.player.getName();
            }
            System.out.println(str);
        }
    }

    void setLocalEntry(StageServerPlayerEntry.Local e) {
        synchronized (this.clients) {
            this.lentry = e;
        }
    }

    public int getViewersNum() {
        synchronized (this.clients) {
            return this.viewers.size();
        }
    }

    public StageServerPlayerEntry getPlayerEntry(PlayerModel pm) {
        synchronized (this.clients) {
            int size = this.clients.size();
            for (int l = 0; l < size; l++) {
                StageServerPlayerEntry entry = this.clients.get(l);
                if (entry.player.userinfo.useridhash == pm.userinfo.useridhash)
                    return entry;
            }
        }
        return null;
    }

    public StageServerPlayerEntry getPlayerEntry(int eid) {
        synchronized (this.clients) {
            int size = this.clients.size();
            for (int l = 0; l < size; l++) {
                StageServerPlayerEntry entry = this.clients.get(l);
                if (entry.getEntryID() == eid)
                    return entry;
            }
        }
        return null;
    }

    boolean processAmpedEvent(StageServerPlayerEntry entry, AmpedEvent e) {
        return processAmpedEvent(entry, e, true);
    }

    boolean processAmpedEvent(StageServerPlayerEntry entry, AmpedEvent e, boolean concheckexe) {
        if (concheckexe && (GameSetting.getSetting()).concheckenabled && entry != null
                && this.conids.get(entry.entryid) != null) {
            int check = ((Integer) this.conids.get(entry.entryid)).intValue();
            if (check != e.conid) {
                String log = "StageServer processAmpedEvent illegal event!!! ev:" + e.id;
                if (entry.userdata != null) {
                    log = String.valueOf(log) + " uid:" + entry.userdata.userid;
                } else {
                    log = String.valueOf(log) + " udata:null";
                }
                if (e.entry != null && e.entry.userdata != null)
                    log = String.valueOf(log) + " euid:" + e.entry.userdata.userid;
                log = String.valueOf(log) + " c1:" + check + " c2:" + e.conid;
                System.out.println(log);
                return true;
            }
        }
        e.conid = -1;
        switch (e.id) {
            case 85:
                if (this.task != null)
                    this.task.processMatchingEvent(entry, (MatchingEvent) e);
                return true;
            case 160:
                processFragment(entry, (FragmentEvent) e);
                return true;
            case 162:
                relayViewer((RelayEvent) e);
                return true;
            case 169:
                if (GA2Tracker.isEnabled()) {
                    BasicAmpedEvent te = (BasicAmpedEvent) e;
                    if (te.args[0] == null) {
                        GA2Tracker.stageModeTracking((entry.getUserInfo()).useridhash, this.stage.getStageMode());
                    } else {
                        GA2Tracker.pageTracking((entry.getUserInfo()).useridhash, (String) te.args[0]);
                    }
                }
                return true;
        }
        this.stage.processAmpedEvent(e);
        return true;
    }

    /* 1839 */ public void sayChat(BasicAmpedEvent e) {
        synchronized (this.saychats) {
            /* 1840 */ this.saychats.add(e);
        }
    }

    public void flushSayChat() {
        int size;
        /* 1853 */ synchronized (this.saychats) {
            /* 1854 */ size = this.saychats.size();
            /* 1855 */ if (size == 0)
                /* 1856 */ return;
            if (size > this.eventbuf.length)
                this.eventbuf = new AmpedEvent[size];
            /* 1857 */ this.saychats.toArray((Object[]) this.eventbuf);
            /* 1858 */ this.saychats.clear();
        }

        /* 1861 */ synchronized (this.clients) {
            /* 1862 */ int csize = this.clients.size();
            /* 1863 */ for (int l = 0; l < csize; l++) {
                /* 1864 */ StageServerPlayerEntry entry = this.clients.get(l);
                /* 1865 */ for (int i = 0; i < size; i++) {
                    /* 1866 */ sendChat((BasicAmpedEvent) this.eventbuf[i], entry);
                }
            }
            /* 1869 */ if (this.lentry != null) {
                /* 1870 */ for (int i = 0; i < size; i++) {
                    /* 1871 */ sendChat((BasicAmpedEvent) this.eventbuf[i], this.lentry);
                }
            }
            /* 1874 */ for (int j = 0; j < size;) {
                this.eventbuf[j] = null;
                j++;
            }

        }
    }

    boolean sendChat(BasicAmpedEvent ce, StageServerPlayerEntry e) {
        /* 1879 */ if (ce.entry == e)
            return false;
        /* 1880 */ if (ce.entry == null || ce.entry.player == null || e.player == null)
            return false;
        /* 1881 */ ChatMessage cm = (ChatMessage) ce.args[0];
        /* 1882 */ switch (cm.type) {

            case 0:
                /* 1886 */ if (Vector3D.hypotSq(ce.entry.player.pos, e.player.pos) > 64.0F)
                    return false;
                break;
            case 3:
                /* 1889 */ if (ce.entry.group != e.group)
                    return false;
                break;
            case 2:
                /* 1892 */ if (e.userinfo.guild == 0 || ce.entry.userinfo.guild != e.userinfo.guild)
                    return false;

                break;
        }

        /* 1898 */ e.sendToClientWithoutLock((AmpedEvent) ce);
        /* 1899 */ return true;
    }

    public int bindServer(StageServerRunner ssr) {
        /* 1912 */ this.runner = ssr;
        /* 1913 */ this.stageserverid = ssr.addStageServer(this);
        /* 1914 */ System.out.println("StageServer bound. stageserverid:" + this.stageserverid);
        /* 1915 */ return this.stageserverid;
    }

    public int bindServer(StageServerRunner ssr, int ssid) {
        /* 1919 */ this.runner = ssr;
        /* 1920 */ this.stageserverid = ssr.addStageServer(this, ssid);
        /* 1921 */ System.out.println("StageServer bound. stageserverid:" + ssid);
        /* 1922 */ return this.stageserverid;
    }

    public int getStageServerID() {
        /* 1926 */ return this.stageserverid;
    }

    public StageServerRunner getStageServerRunner() {
        /* 1930 */ return this.runner;
    }

    public void stageStateChanged(int s) {
        /* 1934 */ if (this.runner != null)
            this.runner.stageStateChanged(this, s);

    }

    public void setupTestPlay(Object stagedata) {
        /* 1950 */ TestPlayServerTask testPlayServerTask = new TestPlayServerTask(stagedata, 0);
        /* 1951 */ setTask((StageServerTask) testPlayServerTask);
    }

    public void setupTestPlay(Object stagedata, int mode) {
        /* 1958 */ TestPlayServerTask testPlayServerTask = new TestPlayServerTask(stagedata, mode);
        /* 1959 */ setTask((StageServerTask) testPlayServerTask);
    }

    public void setupEvent(Object stagedata, int mode, int cid) {
        /* 1963 */ TestPlayServerTask testPlayServerTask = new TestPlayServerTask(stagedata, mode, cid) {
            public void startTask() {
                /* 1965 */ super.startTask();
                /* 1966 */ StageServer.this.getStage().setTimeLimit(0);
            }
        };
        /* 1969 */ setTask((StageServerTask) testPlayServerTask);
    }

    public void setupPreviewStage(Object stagedata, int mode, int cid) {
        /* 1972 */ TestPlayServerTask testPlayServerTask = new TestPlayServerTask(stagedata, mode, cid) {
            public void startTask() {
                /* 1974 */ startPreviewStageTask();
            }
        };
        /* 1977 */ setTask((StageServerTask) testPlayServerTask);
    }

    public void setupTournament(int tourid, int stageidx) {
        /* 1984 */ setupTournament(tourid, stageidx, -1, false, false, null);
    }

    public void setupTournament(int tourid, int stageidx, int maxplayernum, boolean formula,
            boolean _automatch,
            Inttable teaminfo) {
        /* 1990 */ this.automatch = _automatch;
        /* 1991 */ this.automatchphase = 0;
        /* 1992 */ TournamentTaskChain t = new TournamentTaskChain(this, tourid, stageidx, this.automatch, teaminfo);
        /* 1993 */ t.setFormulaMode(formula);
        /* 1994 */ t.setMaxPlayerNum(maxplayernum);
        t.setRoundCount(Integer.valueOf(com.nr.tool.LocalStorage.getInstance().get(this.roominfo.name)));
        /* 1995 */ t.startMatchingRoom();

        /* 1997 */ setAutoShutdown(this.automatch ? 0 : 5000);
        /* 1998 */ this.emptyentrytime = -5000;
    }

    public void setupAutoMatch(int tourid, int stageidx, int maxplayernum, boolean formula, Inttable teaminfo) {
        System.err.println("auto match");
        /* 2002 */ this.automatch = true;
        /* 2003 */ this.automatchphase = 0;
        /* 2004 */ TournamentTaskChain t = new TournamentTaskChain(this, tourid, stageidx, this.automatch, teaminfo);
        /* 2005 */ t.setFormulaMode(formula);
        /* 2006 */ t.setMaxPlayerNum(maxplayernum);
        /* 2007 */ t.startMatchingRoom();

        /* 2010 */ setAutoShutdown(this.automatch ? 0 : 5000);
        /* 2011 */ this.emptyentrytime = -5000;
    }

    public void setupOldArcade(int arcid) {
        /* 2018 */ OldArcadeData ad = OldArcadeData.getTest();

        /* 2021 */ OldArcadeTaskChain a = new OldArcadeTaskChain(this, ad);
        /* 2022 */ a.startMatchingRoom();

        /* 2024 */ setAutoShutdown(5000);
        /* 2025 */ this.emptyentrytime = -5000;
    }

    public void setupLounge(int loungeid) {
        /* 2034 */ LoungeServerTask t = new LoungeServerTask(loungeid);
        /* 2035 */ setTask((StageServerTask) t);

        /* 2038 */ this.sendEventLog = new AmpedEventLog();
        /* 2039 */ this.recvEventLog = new AmpedEventLog();

        /* 2041 */ setAutoShutdown(0);
    }

    public void setupStreetfight(int sfid, int subroom) {
        /* 2048 */ StreetfightTask t = new StreetfightTask(sfid, subroom);
        /* 2049 */ setTask((StageServerTask) t);

        /* 2052 */ this.sendEventLog = new AmpedEventLog();
        /* 2053 */ this.recvEventLog = new AmpedEventLog();

        /* 2055 */ setAutoShutdown(0);
    }

    public void setupStreetfightDemo(int sfid, int subroom) {
        /* 2062 */ StreetfightTask t = new StreetfightTask(sfid, subroom, true);
        /* 2063 */ setTask((StageServerTask) t);
        /* 2064 */ setAutoShutdown(0);
    }

    public void setupTeamStreetfight(int stageid) {
        /* 2071 */ TeamStreetfightTask t = new TeamStreetfightTask(stageid);
        /* 2072 */ setTask((StageServerTask) t);
        /* 2073 */ setAutoShutdown(0);
    }

    public void setupUnitedfight(int sfid, int subroom) {
        /* 2081 */ UnitedfightTask t = new UnitedfightTask(sfid, subroom);
        /* 2082 */ setTask((StageServerTask) t);
        /* 2083 */ setAutoShutdown(0);
    }

    public void setupGuardian(int eid, boolean invokeserver) {
        /* 2090 */ GuardianTaskChain t = new GuardianTaskChain(this, GameSetting.getSetting().getGuardian());
        /* 2091 */ t.invokeServer(invokeserver);
        /* 2092 */ t.startMatchingRoom(eid);
        /* 2093 */ setAutoShutdown(4000);
    }

    public void setupEpisode(int eid) {
        /* 2100 */ GuardianTaskChain t = new GuardianTaskChain(this, GameSetting.getSetting().getGuardian());
        /* 2101 */ t.startMatchingRoom(eid);

        /* 2103 */ setAutoShutdown(4000);
    }

    public void setupMyRoom(MyRoomData.WithUserData mrd) {
        /* 2110 */ MyRoomServerTask t = new MyRoomServerTask(mrd);
        /* 2111 */ setTask((StageServerTask) t);

        /* 2113 */ setAutoShutdown(4000);
    }

    public void setupGuildRoom(GuildData gd) {
        /* 2118 */ GuildRoomServerTask t = new GuildRoomServerTask(gd);
        /* 2119 */ setTask((StageServerTask) t);

        /* 2121 */ setAutoShutdown(4000);
    }

    public void setupPractice(int stageIndex) {
        /* 2126 */ PracticeTask t = new PracticeTask(stageIndex);
        /* 2127 */ setTask((StageServerTask) t);

        /* 2129 */ setAutoShutdown(5000);
    }

    public void setupCaptureTool(int stageIndex) {
        /* 2133 */ PracticeTask t = new PracticeTask(stageIndex);
        /* 2134 */ setTask((StageServerTask) t);
        /* 2135 */ getStage().setStageMode(21);

        /* 2137 */ setAutoShutdown(5000);
    }

    public void setupDuel(int stageid, int bet, int challen, int[] rematchid, int type, int[] randequip) {
        /* 2142 */ DuelServerTask t = new DuelServerTask(stageid, bet, challen, rematchid, type, randequip);
        /* 2143 */ setTask((StageServerTask) t);

        /* 2145 */ setAutoShutdown(15000);
    }

    public AmpedEventLog getSentAmpedEventLog() {
        /* 2158 */ return this.sendEventLog;
    }

    public AmpedEventLog getRecvAmpedEventLog() {
        /* 2162 */ return this.recvEventLog;
    }

    public void outputAmpedEventLog() {
        /* 2167 */ if (this.sendEventLog != null && this.recvEventLog != null) {
            /* 2168 */ if (this.sendEventLog.getLogCount() > 0 || this.recvEventLog.getLogCount() > 0) {
                /* 2169 */ System.out.println("    StageServer : Statistical Results of AmpedEvent");
            }

            /* 2172 */ if (this.sendEventLog.getLogCount() > 0) {
                /* 2173 */ System.out.println("        Sent Amped Event Log ---------------");
                /* 2174 */ this.sendEventLog.print(System.out, 8);
            }
            /* 2176 */ this.sendEventLog.clear();
            /* 2177 */ if (this.recvEventLog.getLogCount() > 0) {
                /* 2178 */ System.out.println("        Received Amped Event Log -----------");
                /* 2179 */ this.recvEventLog.print(System.out, 8);
            }
            /* 2181 */ this.recvEventLog.clear();
        }
    }

    public void setupVoiceChat() {
        /* 2195 */ if (!(GameSetting.getSetting()).voicechatenabled)
            return;
        /* 2197 */ if (this.vcmanager == null) {
            /* 2198 */ this.vcmanager = new StageVoiceChatManager();
            /* 2199 */ this.vcmanager.setStageServer(this);
        }
    }

    public void closeVoiceChat() {
        /* 2208 */ if (!(GameSetting.getSetting()).voicechatenabled)
            return;
        /* 2210 */ if (this.vcmanager == null) {
            return;
        }

        /* 2215 */ this.vcmanager.close();
        /* 2216 */ this.vcmanager = null;
    }

    public void addVoiceChatPlayer(StageServerPlayerEntry e, int team, int[] info) {
        /* 2221 */ if (!(GameSetting.getSetting()).voicechatenabled)
            return;
        /* 2223 */ if (this.vcmanager == null) {
            return;
        }

        /* 2228 */ if (e instanceof StageServerPlayerEntry.Remote && e.isConnected()) {
            /* 2229 */ synchronized (this.clients) {
                /* 2230 */ if (!this.clients.contains(e))
                    return;
                /* 2232 */ }
            this.vcmanager.playerEntered(e, team, info);
        }
    }

    public void removeVoiceChatPlayer(StageServerPlayerEntry e) {
        /* 2238 */ if (!(GameSetting.getSetting()).voicechatenabled)
            return;
        /* 2240 */ if (this.vcmanager == null) {
            return;
        }

        /* 2245 */ if (e instanceof StageServerPlayerEntry.Remote) {
            /* 2246 */ this.vcmanager.playerExited(e);
        }
    }

    public void ignoreVoiceChatPlayer(StageServerPlayerEntry e, Object arg) {
        /* 2252 */ if (this.task == null || !this.task.isVoiceChatEnabled())
            return;
        /* 2254 */ if (this.vcmanager == null) {
            return;
        }

        /* 2260 */ if (e instanceof StageServerPlayerEntry.Remote) {
            /* 2261 */ int uid = KotoriUtil.argi(arg, 1);
            /* 2262 */ if (((Boolean) KotoriUtil.argv(arg, 2)).booleanValue()) {
                /* 2263 */ this.vcmanager.playerIgnored(e, uid);
            } else {

                /* 2266 */ this.vcmanager.playerRemoveIgnored(e, uid);
            }
        }
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\stage\StageServer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
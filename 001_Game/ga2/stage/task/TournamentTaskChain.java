package ga2.stage.task;

import ga2.client.GetAmped2;
import ga2.data.NPCInfo;
import ga2.data.PlaybackRecord;
import ga2.data.TournamentData;
import ga2.data.UserData;
import ga2.data.UserInfo;
import ga2.data.net.GameRoomInfo;
import ga2.ganl.GA2Tracker;
import ga2.setting.GameSetting;
import ga2.stage.DeathmatchRule;
import ga2.stage.DogfightRule;
import ga2.stage.PlayerModel;
import ga2.stage.PlayerRecord;
import ga2.stage.StageRule;
import ga2.stage.StageServer;
import ga2.stage.StageServerPlayerEntry;
import ga2.stage.StageServerRunner;
import ga2.stage.TeamDeathmatchRule;
import ga2.stage.TeamDogfightRule;
import ga2.stage.TeamGuildBattleRule;
import ga2.stage.TournamentRecord;
import ga2.stage.TournamentReward;
import ga2.stage.event.AmpedEvent;
import ga2.stage.event.BasicAmpedEvent;
import ga2.stage.event.MatchingEvent;
import ga2.stage.event.StagePanelEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import kotori.geom.KMath;
import kotori.util.Inttable;
import kotori.util.KotoriUtil;

public class TournamentTaskChain {
    TournamentData data;
    TournamentData.Stage selstage;
    TournamentData.Stage playstage;
    int tourid;
    int stageid;
    StageServer server;
    int mode;
    int roundidx;
    int maxplayernum;
    int playernum;
    PlayerRecord[] record;
    StageServerPlayerEntry[] entry;
    Inttable backuppos;
    int lastmaster;
    PlaybackRecord playback;
    TournamentRecord finalrec;
    boolean formula;
    boolean dogfight;
    int roundCount = 3;
    boolean[] isrndteam = new boolean[8];

    int loginnpcrank = -1;

    boolean allsameaddr;

    HashMap oldrndm;

    long playstarttime;

    boolean automatch;
    Inttable autoteaminfo;
    boolean isbattle;
    long automatchstarttime = -1L;

    public TournamentTaskChain(StageServer s, int tid, int sid, boolean _automatch, Inttable teaminfo) {
        this.tourid = tid;
        this.server = s;
        this.data = GameSetting.getSetting().getTournament(tid);
        this.stageid = sid;
        this.selstage = this.data.getStage(sid);
        this.maxplayernum = this.data.maxplayers;
        this.dogfight = this.data.isDogfight();
        this.automatch = _automatch;
        this.autoteaminfo = teaminfo;
    }

    public void setFormulaMode(boolean f) {
        this.formula = f;
    }

    public void setMaxPlayerNum(int num) {
        if (num >= 0 && num <= this.data.maxplayers) {
            this.maxplayernum = num;
        }
    }

    public void startMatchingRoom() {
        this.mode = 0;
        TournamentMatchingTask t = new TournamentMatchingTask(this.oldrndm);
        t.setFormulaMode(this.formula);
        t.setRndTeam(this.isrndteam);
        t.setMasterKickTime((GameSetting.getSetting()).roommasterkicktime);
        this.server.setTask(t);
        this.server.sendStageSetup();

        if (this.data.trainingmode && this.loginnpcrank >= 0) {
            logoutNPCs();
            loginNPCs(this.loginnpcrank);
        }
    }

    void startPlaybackRecord() {
        if (!this.data.trainingmode) {
            this.playback = new PlaybackRecord();
            if (this.server.roominfo != null) {
                this.playback.roominfo = (GameRoomInfo) this.server.roominfo.clone();
                this.playback.roominfo.addr = null;
                this.playback.roominfo.gametime = System.currentTimeMillis();
            }

            this.playback.begintime = this.server.getStage().getTime();

            UserInfo[] ui = new UserInfo[this.server.getPlayerEntryNum()];
            for (int l = 0; l < ui.length; l++) {
                ui[l] = this.server.playerEntryAt(l).getUserInfo().clone();
            }
            this.playback.players = ui;
        }
    }

    public void startStage(int i) {
        this.mode = 1;
        this.roundidx = i;
        if (i == 0) {
            this.playstage = this.data.getPlayStage(this.stageid);
            if (this.server.getTask() instanceof TournamentMatchingTask) {
                this.oldrndm = ((TournamentMatchingTask) this.server.getTask()).oldrndm;
            }
            this.playstarttime = System.currentTimeMillis();

            if (!this.data.trainingmode) {
                int tsid = 0;
                if (this.stageid != 0)
                    tsid = this.playstage.getID();
                for (int l = 0; l < this.entry.length; l++) {
                    GA2Tracker.tournamentTracking(this.entry[l].getUserInfo(), this.data, tsid);
                }
            }
        }

        TournamentStageTask t = new TournamentStageTask(this.playstage.sd2[i % this.playstage.sd2.length],
                this.playernum);
        t.bgmid = (i >= this.playstage.sd2.length - 1) ? this.playstage.bgm2 : this.playstage.bgm1;
        this.server.setTask(t);
        this.server.setPlaybackRecord(this.playback);
        this.server.sendStageSetup();
    }

    void finishPlaybackRecord() {
        if (this.playback != null) {
            this.server.setPlaybackRecord(null);
            PlaybackRecord rec = this.playback;
            this.playback = null;
            rec.rec = this.finalrec;

            this.server.getStageServerRunner().putPlayback(rec);
        }
    }

    public void getAllSameAddr() {
        if (this.roundidx != 0)
            return;
        if (!(GameSetting.getSetting()).noreward_sameaddr || (GameSetting.getSetting()).version < 0
                || this.server.getStage().isTournamentTrainingMode()) {
            this.allsameaddr = false;
            return;
        }
        UserInfo[] ui = new UserInfo[this.playernum];
        for (int l = 0; l < this.playernum; l++) {
            ui[l] = new UserInfo();
            ui[l] = this.entry[l].getPlayerModel().getUserInfo();
        }
        this.allsameaddr = this.entry[0].isAllSameAddr(ui);
    }

    void calcAndSendRecord(boolean timeout) {
        boolean last;
        if (this.dogfight) {
            last = (this.roundidx == 0);
        } else {
            last = (this.roundidx == roundCount - 1);
        }

        getAllSameAddr();

        PlayerRecord[] r = new PlayerRecord[this.playernum];
        boolean discon = GameSetting.getSetting().isLanguage("kr");
        for (int l = 0; l < this.playernum; l++) {
            if (this.allsameaddr) {
                r[l] = new PlayerRecord();
                (r[l]).entryid = (this.entry[l].getPlayerModel()).rec.entryid;
                (r[l]).group = (this.entry[l].getPlayerModel()).rec.group;
            } else {
                r[l] = (this.entry[l].getPlayerModel()).rec;
            }
            (r[l]).disconnected = (discon && !this.entry[l].isConnected());
            this.record[l].add(r[l]);
        }

        TournamentRecord tr = new TournamentRecord();

        tr.type = timeout ? 1 : 0;
        tr.tourid = this.tourid;

        tr.stageid = this.playstage.getID(this.data);
        tr.team = this.data.teambattle;
        tr.automatch = this.automatch;
        tr.stagerecord = new TournamentRecord.Player[this.playernum];
        tr.progressrecord = new TournamentRecord.Player[this.playernum];

        int scorerate = 100;
        if (this.server.roominfo != null) {
            UserInfo master = null;
            for (int k = 0; k < this.playernum; k++) {
                if (this.server.roominfo.isMaster(this.entry[k].getEntryID())) {
                    master = this.entry[k].getUserInfo();
                    break;
                }
            }
            if (master != null) {
                int srsr = this.server.roominfo.scorerate;
                scorerate = this.server.roominfo.getScoreRate(master, ((srsr & 0x1) != 0),
                        ((srsr & 0x2) != 0));
            }
        }

        for (int i = 0; i < this.playernum; i++) {
            boolean dis = (!tr.team && (r[i]).disconnected);
            int rankscore = (this.allsameaddr || dis) ? 0 : ((r[i]).rankscore * scorerate / 100 / 10 * 10);
            tr.stagerecord[i] = new TournamentRecord.Player((r[i]).entryid, (r[i]).group, rankscore,
                    (r[i]).killpt, (r[i]).diept);
            int s = (this.allsameaddr || dis) ? 0
                    : (((this.record[i]).rankscore + (this.record[i]).score) * (this.dogfight ? 100 : scorerate) / 100);
            tr.progressrecord[i] = new TournamentRecord.Player((this.record[i]).entryid, (r[i]).group, s,
                    dis ? 0 : (this.record[i]).killpt, dis ? 0 : (this.record[i]).diept);
        }

        Comparator<? super TournamentRecord.Player> comp = new RecordComparator();
        Arrays.sort(tr.stagerecord, comp);
        Arrays.sort(tr.progressrecord, comp);

        if (last) {
            (new PrizeApplyer()).applyPrizes(this.record, this.data, this.playstage.prize, tr);
            Arrays.sort(tr.finalrecord, comp);
        }

        String clog = "GetAbnormalScore!!!! sr:" + scorerate;

        for (int j = 0; j < this.playernum; j++) {
            if (!this.allsameaddr)
                this.entry[j].completeQuest(this.data, this.playstage, this.roundidx, tr, this.record[j],
                        (int) (System.currentTimeMillis() - this.playstarttime), scorerate, this.automatch,
                        this.tourid);
            if (r[j] != null)
                clog = String.valueOf(clog) + " r[" + j + "] atk:" + (r[j]).atkpt + " bo:" + (r[j]).bonus + " die:"
                        + (r[j]).diept + " dmg:" + (r[j]).dmgpt + " kill:" + (r[j]).killpt + " rank:" + (r[j]).rankscore
                        + " glid:" + (r[j]).glidept + " score:" + (r[j]).score;
            r[j].clear();
        }

        if (last) {
            StageServerRunner runner = this.server.getStageServerRunner();
            for (int k = 0; k < this.playernum; k++) {
                StageServerPlayerEntry e = this.entry[k];

                if (e.reward != null && e.reward instanceof TournamentReward) {
                    TournamentReward checktr = (TournamentReward) e.reward;
                    if (checktr.getscore + checktr.bonus > 70000) {
                        if (e.getUserInfo() != null)
                            clog = String.valueOf(clog) + " uih:" + (e.getUserInfo()).useridhash;
                        clog = String.valueOf(clog) + " gscore:" + checktr.getscore + " gbo:" + checktr.bonus;
                        System.out.println(clog);
                    }
                }

                if (e.getState() != -1 && e.getState() != 3) {
                    try {
                        if (!this.allsameaddr) {
                            if (runner != null)
                                runner.writeBackPlayerEntry(e);

                            e.sendToClient((AmpedEvent) BasicAmpedEvent.create(-1, 89, e.reward));
                        }
                    } catch (Exception exception) {
                    }
                }
            }
        }

        this.finalrec = tr;
        this.server.sendEventToAll((AmpedEvent) StagePanelEvent.create(-1, 3, tr));
    }

    static class RecordComparator
            implements Comparator {
        public int compare(Object o1, Object o2) {
            TournamentRecord.Player p1 = (TournamentRecord.Player) o1;
            TournamentRecord.Player p2 = (TournamentRecord.Player) o2;
            return p2.score - p1.score;
        }
    }

    void loginNPCs(int rank) {
        if (this.data.trainingdata != null) {
            int[] npcid = this.data.trainingdata.getNpcID(rank);
            for (int l = 0; l < npcid.length; l++) {
                this.server.loginNPC(npcid[l]);
            }
            this.loginnpcrank = rank;

            if (this.data.teambattle)
                loginEvenNPC();
        }
    }

    void loginEvenNPC() {
        if (this.loginnpcrank >= 0 && this.data.trainingdata != null) {
            int num = this.server.getPlayerEntryNum();
            if (num % 2 != 0) {
                ArrayList<Integer> a = new ArrayList();
                for (int l = 0; l < num; l++) {
                    StageServerPlayerEntry e = this.server.playerEntryAt(l);
                    if (e instanceof StageServerPlayerEntry.Debug) {
                        a.add(Integer.valueOf(e.getPlayerModel().getNPCID()));
                    }
                }
                int npc = this.data.trainingdata.getNotContainsNpcID(this.loginnpcrank, a);
                if (npc >= 0)
                    this.server.loginNPC(npc);
            }
        }
    }

    void logoutNPCs() {
        if (this.server.getTask() == null)
            return;
        int num = this.server.getPlayerEntryNum();
        ArrayList<StageServerPlayerEntry> a = new ArrayList();
        int l;
        for (l = 0; l < num; l++) {
            StageServerPlayerEntry e = this.server.playerEntryAt(l);
            if (e instanceof StageServerPlayerEntry.Debug)
                a.add(e);
        }
        for (l = a.size() - 1; l >= 0; l--)
            this.server.getTask().playerLogouted(a.get(l));
    }

    public class TournamentMatchingTask
            extends MatchingRoomServerTask {
        boolean allready = false;
        public boolean movelobbyautomatch;

        public void updateTask() {
            if (TournamentTaskChain.this.automatch) {
                updateAutomatchTask();
            } else {
                super.updateTask();
            }

        }

        public void updateAutomatchTask() {
            int num = this.server.getPlayerEntryNum();
            if ((TournamentTaskChain.this.isbattle || this.allready
                    || TournamentTaskChain.this.automatchstarttime != -1L) && num == 0) {
                TournamentTaskChain.this.isbattle = false;
                this.allready = false;
                TournamentTaskChain.this.automatchstarttime = -1L;
            }
            if (TournamentTaskChain.this.isbattle)
                return;
            if (!this.allready) {
                if (TournamentTaskChain.this.automatchstarttime == -1L && num >= 1) {
                    TournamentTaskChain.this.automatchstarttime = System.currentTimeMillis() + 30000L;
                    System.out.println("TournamentMatchingTask.updateTask():automatchstarttime="
                            + TournamentTaskChain.this.automatchstarttime);
                }
                boolean istimelimit = (TournamentTaskChain.this.automatchstarttime == -1L) ? false
                        : ((System.currentTimeMillis() >= TournamentTaskChain.this.automatchstarttime));
                if (istimelimit) {
                    System.out.println("TournamentMatchingTask.updateTask():" + System.currentTimeMillis()
                            + ">=" + TournamentTaskChain.this.automatchstarttime + " || " + num + ">=" + this.maxnum);
                    this.movelobbyautomatch = true;
                    this.server.sendEventToAll((AmpedEvent) StagePanelEvent.create(-1, 37));
                } else if (num >= this.maxnum) {
                    System.out.println("TournamentMatchingTask.updateTask():" + System.currentTimeMillis()
                            + ">=" + TournamentTaskChain.this.automatchstarttime + " || " + num + ">=" + this.maxnum);

                    for (int i = 0; i < num; i++) {
                        MatchingEvent e = MatchingEvent
                                .create(this.server.playerEntryAt(i).getPlayerModel().getID(), 0);
                        processMatchingEvent(this.server.playerEntryAt(i), e);
                    }
                    if (this.isteam)
                        shuffleRandTeam(0, 0, 0);
                    this.state = 3;
                    this.allready = true;
                }
            } else {
                super.updateTask();
            }
        }

        public void shuffleRandTeam(int rndnum, int team1num, int team2num) {
            if (!TournamentTaskChain.this.automatch) {
                super.shuffleRandTeam(rndnum, team1num, team2num);

                return;
            }

            if (TournamentTaskChain.this.autoteaminfo != null) {
                for (int l = 0; l < this.num; l++) {
                    if (this.member[l] == null) {
                        System.out.println("shuffleRandTeam member[" + l
                                + "]=null what't going on? member.length:" + this.member.length + ", num:" + this.num);
                        return;
                    }
                    if ((this.member[l]).entry == null) {
                        System.out
                                .println("shuffleRandTeam member[" + l + "].entry=null what't going on? member.length:"
                                        + this.member.length + ", num:" + this.num);
                        return;
                    }
                    Integer g = (Integer) TournamentTaskChain.this.autoteaminfo
                            .get((this.member[l]).entry.getEntryID());
                    if (g == null) {
                        System.out.println("shuffleRandTeam g=null member[" + l + "].entry.getEntryID():"
                                + (this.member[l]).entry.getEntryID() + " what't going on? member.length:"
                                + this.member.length + ", num:" + this.num);
                        for (Inttable.Iterator i = TournamentTaskChain.this.autoteaminfo.iterator(); i
                                .next();) {
                            System.out.println("autoteaminfo entryid:" + i.key() + ", info:" + i.value());
                        }
                        return;
                    }
                    (this.member[l]).entry.group = g.intValue();
                    (this.member[l]).entry.getPlayerModel().setGroup((this.member[l]).entry.group);
                }
            }
        }

        public TournamentMatchingTask(HashMap old) {
            super(TournamentTaskChain.this.data.matching, TournamentTaskChain.this.data.teambattle,
                    TournamentTaskChain.this.data.minplayers, TournamentTaskChain.this.maxplayernum, old);
            setLastPosTable(TournamentTaskChain.this.backuppos);
            this.defaultmaster = TournamentTaskChain.this.lastmaster;
        }

        public void playerGameReady(StageServerPlayerEntry e) {
            super.playerGameReady(e);

            if (e instanceof StageServerPlayerEntry.Debug
                    && this.server.getStage().isTournamentTrainingMode()) {
                e.getPlayerModel().sendVanish(false);
                e.getPlayerModel().sendVisible(true);

            } else if (TournamentTaskChain.this.data.trainingmode && TournamentTaskChain.this.loginnpcrank == -1
                    && e instanceof StageServerPlayerEntry.Remote) {
                TournamentTaskChain.this.loginNPCs((e.getUserInfo().getRank(true)).group);
            }
        }

        public void processMatchingEvent(StageServerPlayerEntry e, MatchingEvent me) {
            boolean oldteam;
            GameRoomInfo gri;
            TournamentData td;
            String oldrn;
            if (this.state == 2)
                return;
            switch (me.subid) {
                case 31:
                    oldteam = this.isteam;
                    me.entry = null;
                    gri = (GameRoomInfo) me.args[0];
                    td = GameSetting.getSetting().getTournament(gri.gameid);

                    if (gri.maxnum > td.maxplayers || gri.maxnum < td.minplayers) {
                        System.out.println("processMatchingEvent ERR_GAME_ILLEGAL_MAXPLAYERS maxnum:"
                                + gri.maxnum + " master:" + (e.getUserData()).userid);

                        return;
                    }
                    if (td != TournamentTaskChain.this.data) {
                        setTeamBattle(td.teambattle);
                        TournamentTaskChain.this.data = td;
                        TournamentTaskChain.this.dogfight = TournamentTaskChain.this.data.isDogfight();
                    }
                    TournamentTaskChain.this.tourid = gri.gameid;
                    TournamentTaskChain.this.stageid = gri.subid;
                    this.minnum = td.minplayers;
                    if ((GameSetting.getSetting()).lanplayenabled &&
                            e != null && e.getUserInfo() instanceof NPCInfo)
                        this.minnum = 2;

                    this.maxnum = gri.maxnum;
                    TournamentTaskChain.this.maxplayernum = gri.maxnum;
                    TournamentTaskChain.this.selstage = TournamentTaskChain.this.data
                            .getStage(TournamentTaskChain.this.stageid);

                    oldrn = (this.server.getStage()).roominfo.name;
                    this.server.getStageServerRunner().stageInfoChanged(this.server, gri);
                    this.server.getStage().setGameRoomInfo(gri);
                    me.args[0] = (this.server.getStage()).roominfo;
                    this.server.sendEventToAll((AmpedEvent) me);
                    if (gri.teaminfo != null)
                        TournamentTaskChain.this.autoteaminfo = gri.teaminfo;

                    GameSetting.getSetting()
                            .println("processMatchingEvent gameid:" + gri.gameid + ", subid:" + gri.subid);
                    if (!gri.name.equals(oldrn) && e.getUserData() != null) {
                        try {
                            System.out.println("ChangeGameRoomName name:"
                                    + URLEncoder.encode(gri.name, "UTF-8") + " master:" + (e.getUserData()).userid);
                        } catch (UnsupportedEncodingException exc) {
                            exc.printStackTrace();
                        }
                    }
                    if (gri.teaminfo != null) {
                        for (Inttable.Iterator i = TournamentTaskChain.this.autoteaminfo.iterator(); i
                                .next();) {
                            GameSetting.getSetting()
                                    .println("autoteaminfo entryid:" + i.key() + ", info:" + i.value());
                        }
                    }

                    if (td.trainingmode && !oldteam && this.isteam)
                        TournamentTaskChain.this.loginEvenNPC();

                    break;
                case 0:
                    if (this.server.getTask() instanceof TournamentMatchingTask) {
                        TournamentMatchingTask t_ = (TournamentMatchingTask) this.server.getTask();
                        MatchingRoomServerTask.Member mem = t_.getMember(e);
                        int idx = t_.getMemberIndex((e.getUserInfo()).useridhash);
                        if (idx != -1 && mem != null) {
                            this.isrndteam[idx] = (mem.pm.getGroup() == 10);
                        }
                    }
                    break;
            }

            super.processMatchingEvent(e, me);
        }

        void updateRoomState() {
            if (TournamentTaskChain.this.automatch)
                return;
            super.updateRoomState();
        }

        void startGame() {
            TournamentTaskChain.this.roundCount = Integer
                    .valueOf(com.nr.tool.LocalStorage.getInstance()
                            .get("Round - " + String.valueOf(
                                    ((GameRoomInfo) TournamentTaskChain.this.server.roominfo.clone()).gameid)));

            this.server.stageStateChanged(1);

            TournamentTaskChain.this.backuppos = getLastPosTable();
            if (this.master != null)
                TournamentTaskChain.this.lastmaster = this.master.entry.getEntryID();

            TournamentTaskChain.this.playernum = this.server.getPlayerEntryNum();
            TournamentTaskChain.this.record = new PlayerRecord[TournamentTaskChain.this.playernum];
            TournamentTaskChain.this.entry = new StageServerPlayerEntry[TournamentTaskChain.this.playernum];
            System.out
                    .println("TournamentTaskChain.startGame():playernum=" + TournamentTaskChain.this.playernum);
            for (int l = 0; l < TournamentTaskChain.this.playernum; l++) {
                TournamentTaskChain.this.entry[l] = this.server.playerEntryAt(l);
                TournamentTaskChain.this.record[l] = new PlayerRecord();
                (TournamentTaskChain.this.record[l]).entryid = TournamentTaskChain.this.entry[l].getEntryID();

                if ((GameSetting.getSetting()).lanplayenabled) {
                    UserData ud = TournamentTaskChain.this.entry[l].getUserData();
                    UserInfo ui = ud.info;
                    PlayerModel p = TournamentTaskChain.this.entry[l].getPlayerModel();
                    if (ui instanceof NPCInfo) {
                        int npcid = ((NPCInfo) ui).getID((NPCInfo) ui);
                        p.setNPCID(npcid);
                    }
                    p.setUserInfo(ui, ud.items, false);
                    TournamentTaskChain.this.entry[l].setPlayer(p);
                }
            }

            super.startGame();
            TournamentTaskChain.this.startPlaybackRecord();
            TournamentTaskChain.this.startStage(0);

            if ((GameSetting.getSetting()).tourplayernumcheckenabled)
                checkPlayerNum();

            if (TournamentTaskChain.this.automatch) {
                TournamentTaskChain.this.isbattle = true;
                System.out
                        .println("TournamentTaskChain.startGame():isbattle=" + TournamentTaskChain.this.isbattle);
            }
        }

        void checkPlayerNum() {
            if (TournamentTaskChain.this.data == null)
                return;
            if (TournamentTaskChain.this.automatch)
                return;
            if (TournamentTaskChain.this.playernum < TournamentTaskChain.this.data.minplayers) {
                System.out.println("[Error TeamTournament] low num:" + TournamentTaskChain.this.playernum
                        + "<" + TournamentTaskChain.this.data.minplayers);
                moveMatchingRoom(TournamentTaskChain.this.data.teambattle);
            } else if (TournamentTaskChain.this.data.teambattle) {
                if ((TournamentTaskChain.this.playernum + (this.formula ? -1 : 0)) % 2 == 1) {
                    System.out.println("[Error TeamTournament] odd num:" + TournamentTaskChain.this.playernum
                            + "/" + TournamentTaskChain.this.maxplayernum);
                    moveMatchingRoom(TournamentTaskChain.this.data.teambattle);
                } else {
                    short[] team = new short[2];
                    for (int l = 0; l < TournamentTaskChain.this.playernum; l++) {
                        if ((TournamentTaskChain.this.entry[l]).group == 1) {
                            team[0] = (short) (team[0] + 1);
                        } else if ((TournamentTaskChain.this.entry[l]).group == 2) {
                            team[1] = (short) (team[1] + 1);
                        }

                    }
                    if (team[0] != team[1]) {
                        System.out.println("[Error TeamTournament] mismatch:" + team[0] + "!=" + team[1]);
                        moveMatchingRoom(TournamentTaskChain.this.data.teambattle);
                    }
                }
            }
        }

        void moveMatchingRoom(boolean team) {
            if (this.server.getTask() instanceof TournamentTaskChain.TournamentStageTask) {
                this.server.sendEventToAll((AmpedEvent) StagePanelEvent.create(-1, team ? 31 : 32));
                ((TournamentTaskChain.TournamentStageTask) this.server.getTask()).moveMatchingRoom();
            }
        }

        UserData getUserData(StageServerPlayerEntry e, int npcid) {
            UserData ud = e.getUserData();
            UserInfo ui = ud.info;
            NPCInfo nPCInfo = GameSetting.getSetting().getNPCInfo(npcid);
            ((UserInfo) nPCInfo).useridhash = KotoriUtil.getNameHash(ud.info.name);
            ((UserInfo) nPCInfo).entryid = e.getEntryID();
            return ud;
        }

        public void setTechNPC(int id, StageServerPlayerEntry e) {
            UserData npcud = getNPCUserData(id, e.getPlayerModel().getEntryID());
            PlayerModel p = e.getPlayerModel();
            p.setUserInfo((UserInfo) GameSetting.getSetting().getNPCInfo(id), npcud.items, false);
            e.setPlayer(p);
        }

        UserData getNPCUserData(int npcid, int eid) {
            UserData ud = UserData.getTest("lan");
            ud.info = (UserInfo) GameSetting.getSetting().getNPCInfo(npcid);
            ud.info.useridhash = KotoriUtil.getNameHash(ud.info.name);
            ud.info.entryid = eid;
            return ud;
        }
    }

    public class TournamentStageTask
            extends GameServerTask {
        StageRule rule;

        TournamentStageTask(Object o, int num) {
            super(o, num);
        }

        public boolean isVoiceChatEnabled() {
            return (!this.server.getStage().isTournamentTrainingMode() && super.isVoiceChatEnabled());
        }

        public void startTask() {
            if (TournamentTaskChain.this.dogfight) {
                startTask(27);
            } else {
                super.startTask();
            }

            for (int l = 0; l < this.server.getPlayerEntryNum(); l++) {
                StageServerPlayerEntry e = this.server.playerEntryAt(l);
                if (e.group == 11) {
                    e.getPlayerModel().sendVanish(true);
                }
            }

            if (TournamentTaskChain.this.dogfight) {
                if (TournamentTaskChain.this.tourid == 5) {
                    if (TournamentTaskChain.this.data.teambattle) {
                        this.rule = (StageRule) new TeamGuildBattleRule(this.playernum);
                    } else {
                        this.rule = (StageRule) new TeamGuildBattleRule(this.playernum);
                    }
                } else if (TournamentTaskChain.this.data.teambattle) {
                    this.rule = (StageRule) new TeamDogfightRule(this.playernum);
                } else {
                    this.rule = (StageRule) new DogfightRule(this.playernum);
                }

            } else if (TournamentTaskChain.this.data.teambattle) {
                this.rule = (StageRule) new TeamDeathmatchRule();
            } else {
                this.rule = (StageRule) new DeathmatchRule();
            }

            GetAmped2 ga2 = GetAmped2.getAmped2();
            if (!(GameSetting.getSetting()).lanplayenabled || ga2 == null || !ga2.isLanPlay()) {

                this.rule.setItemInfo(TournamentTaskChain.this.playstage.itemInfo,
                        TournamentTaskChain.this.playstage.itemInfo_sp);
            }

            (this.server.getStage()).roundidx = TournamentTaskChain.this.roundidx;
            this.server.getStage().setStageRule(this.rule);
        }

        public void gameset() {
            this.phase = 3;
            setNextTime(isFinalStage() ? (TournamentTaskChain.this.formula ? 60000 : 30000) : 18000);

            TournamentTaskChain.this.calcAndSendRecord(this.rule.timeout);

            for (int i = 0; i < this.server.getPlayerEntryNum(); i++) {
                StageServerPlayerEntry e = this.server.playerEntryAt(i);
                e.outputDelayData();
            }
        }

        void startGame() {
            (this.server.getStage()).autoreadyfight = false;
            super.startGame();
        }

        public void wipeIn() {
            if (isFinalStage()) {
                TournamentTaskChain.this.finishPlaybackRecord();
            }
            super.wipeIn();
        }

        public void nextStage() {
            super.nextStage();

            if (isFinalStage()) {
                this.server.stageStateChanged(0);
                TournamentTaskChain.this.startMatchingRoom();
            } else {
                int ps = KMath.clamp(
                        (TournamentTaskChain.this.roundidx + 1) * 3 / TournamentTaskChain.this.playstage.sd2.length, 0,
                        2);
                this.server.stageStateChanged(1 + ps);
                TournamentTaskChain.this.startStage(TournamentTaskChain.this.roundidx + 1);
            }
        }

        public void moveMatchingRoom() {
            super.nextStage();
            this.server.stageStateChanged(0);
            TournamentTaskChain.this.startMatchingRoom();
        }

        boolean isFinalStage() {
            if (TournamentTaskChain.this.dogfight)
                return (TournamentTaskChain.this.roundidx == 0);
            return (TournamentTaskChain.this.roundidx == roundCount - 1);
        }

        int getTimeLimit() {
            if (TournamentTaskChain.this.dogfight)
                return (TournamentTaskChain.this.playstage.timelimit == 0) ? 500000
                        : (TournamentTaskChain.this.playstage.timelimit * 1000);
            return (TournamentTaskChain.this.playstage.timelimit == 0) ? 240000
                    : (TournamentTaskChain.this.playstage.timelimit * 1000);
        }

        public void playerLogouted(StageServerPlayerEntry e) {
            super.playerLogouted(e);
            this.server.removeVoiceChatPlayer(e);
        }

        public void playerDown(StageServerPlayerEntry e) {
            super.playerDown(e);
            this.server.removeVoiceChatPlayer(e);
        }
    }
}

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\stage\task\
 * TournamentTaskChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
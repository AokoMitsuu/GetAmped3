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
    /* 58 */ boolean[] isrndteam = new boolean[8];

    /* 60 */ int loginnpcrank = -1;

    boolean allsameaddr;

    HashMap oldrndm;

    long playstarttime;

    boolean automatch;
    Inttable autoteaminfo;
    boolean isbattle;
    /* 71 */ long automatchstarttime = -1L;

    public TournamentTaskChain(StageServer s, int tid, int sid, boolean _automatch, Inttable teaminfo) {
        /* 74 */ this.tourid = tid;
        /* 75 */ this.server = s;
        /* 76 */ this.data = GameSetting.getSetting().getTournament(tid);
        /* 77 */ this.stageid = sid;
        /* 78 */ this.selstage = this.data.getStage(sid);
        /* 79 */ this.maxplayernum = this.data.maxplayers;
        /* 80 */ this.dogfight = this.data.isDogfight();
        /* 81 */ this.automatch = _automatch;
        /* 82 */ this.autoteaminfo = teaminfo;
    }

    public void setFormulaMode(boolean f) {
        /* 86 */ this.formula = f;
    }

    public void setMaxPlayerNum(int num) {
        /* 90 */ if (num >= 0 && num <= this.data.maxplayers) {
            /* 91 */ this.maxplayernum = num;
        }
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public void startMatchingRoom() {
        /* 100 */ this.mode = 0;
        /* 101 */ TournamentMatchingTask t = new TournamentMatchingTask(this.oldrndm);
        /* 102 */ t.setFormulaMode(this.formula);
        /* 103 */ t.setRndTeam(this.isrndteam);
        /* 104 */ t.setMasterKickTime((GameSetting.getSetting()).roommasterkicktime);
        /* 105 */ this.server.setTask(t);
        /* 106 */ this.server.sendStageSetup();

        /* 108 */ if (this.data.trainingmode && this.loginnpcrank >= 0) {
            /* 109 */ logoutNPCs();
            /* 110 */ loginNPCs(this.loginnpcrank);
        }
    }

    void startPlaybackRecord() {
        /* 115 */ if (!this.data.trainingmode) {
            /* 116 */ this.playback = new PlaybackRecord();
            /* 117 */ if (this.server.roominfo != null) {
                /* 118 */ this.playback.roominfo = (GameRoomInfo) this.server.roominfo.clone();
                /* 119 */ this.playback.roominfo.addr = null;
                /* 120 */ this.playback.roominfo.gametime = System.currentTimeMillis();
            }

            /* 123 */ this.playback.begintime = this.server.getStage().getTime();

            /* 125 */ UserInfo[] ui = new UserInfo[this.server.getPlayerEntryNum()];
            /* 126 */ for (int l = 0; l < ui.length; l++) {
                /* 127 */ ui[l] = this.server.playerEntryAt(l).getUserInfo().clone();
            }
            /* 129 */ this.playback.players = ui;
        }
    }

    public void startStage(int i) {
        /* 137 */ this.mode = 1;
        /* 138 */ this.roundidx = i;
        /* 139 */ if (i == 0) {
            /* 140 */ this.playstage = this.data.getPlayStage(this.stageid);
            /* 141 */ if (this.server.getTask() instanceof TournamentMatchingTask) {
                /* 142 */ this.oldrndm = ((TournamentMatchingTask) this.server.getTask()).oldrndm;
            }
            /* 144 */ this.playstarttime = System.currentTimeMillis();

            /* 147 */ if (!this.data.trainingmode) {
                /* 148 */ int tsid = 0;
                /* 149 */ if (this.stageid != 0)
                    tsid = this.playstage.getID();
                /* 150 */ for (int l = 0; l < this.entry.length; l++) {
                    /* 151 */ GA2Tracker.tournamentTracking(this.entry[l].getUserInfo(), this.data, tsid);
                }
            }
        }

        /* 156 */ TournamentStageTask t = new TournamentStageTask(this.playstage.sd2[i % this.playstage.sd2.length],
                this.playernum);
        /* 157 */ t.bgmid = (i >= this.playstage.sd2.length - 1) ? this.playstage.bgm2 : this.playstage.bgm1;
        /* 158 */ this.server.setTask(t);
        /* 159 */ this.server.setPlaybackRecord(this.playback);
        /* 160 */ this.server.sendStageSetup();
    }

    void finishPlaybackRecord() {
        /* 164 */ if (this.playback != null) {
            /* 165 */ this.server.setPlaybackRecord(null);
            /* 166 */ PlaybackRecord rec = this.playback;
            /* 167 */ this.playback = null;
            /* 168 */ rec.rec = this.finalrec;

            /* 174 */ this.server.getStageServerRunner().putPlayback(rec);
        }
    }

    public void getAllSameAddr() {
        /* 183 */ if (this.roundidx != 0)
            /* 184 */ return;
        if (!(GameSetting.getSetting()).noreward_sameaddr || (GameSetting.getSetting()).version < 0
                || this.server.getStage().isTournamentTrainingMode()) {
            /* 185 */ this.allsameaddr = false;
            return;
        }
        /* 188 */ UserInfo[] ui = new UserInfo[this.playernum];
        /* 189 */ for (int l = 0; l < this.playernum; l++) {
            /* 190 */ ui[l] = new UserInfo();
            /* 191 */ ui[l] = this.entry[l].getPlayerModel().getUserInfo();
        }
        /* 193 */ this.allsameaddr = this.entry[0].isAllSameAddr(ui);
    }

    void calcAndSendRecord(boolean timeout) {
        boolean last;
        /* 204 */ if (this.dogfight) {
            last = (this.roundidx == 0);
        }
        /* 205 */ else {
            last = (this.roundidx == roundCount - 1);
        }

        /* 207 */ getAllSameAddr();

        /* 210 */ PlayerRecord[] r = new PlayerRecord[this.playernum];
        /* 211 */ boolean discon = GameSetting.getSetting().isLanguage("kr");
        /* 212 */ for (int l = 0; l < this.playernum; l++) {
            /* 213 */ if (this.allsameaddr) {
                /* 214 */ r[l] = new PlayerRecord();
                /* 215 */ (r[l]).entryid = (this.entry[l].getPlayerModel()).rec.entryid;
                /* 216 */ (r[l]).group = (this.entry[l].getPlayerModel()).rec.group;
            } else {
                /* 218 */ r[l] = (this.entry[l].getPlayerModel()).rec;
            }
            /* 220 */ (r[l]).disconnected = (discon && !this.entry[l].isConnected());
            /* 221 */ this.record[l].add(r[l]);
        }

        /* 224 */ TournamentRecord tr = new TournamentRecord();

        /* 226 */ tr.type = timeout ? 1 : 0;
        /* 227 */ tr.tourid = this.tourid;

        /* 229 */ tr.stageid = this.playstage.getID(this.data);
        /* 230 */ tr.team = this.data.teambattle;
        /* 231 */ tr.automatch = this.automatch;
        /* 232 */ tr.stagerecord = new TournamentRecord.Player[this.playernum];
        /* 233 */ tr.progressrecord = new TournamentRecord.Player[this.playernum];

        /* 238 */ int scorerate = 100;
        /* 239 */ if (this.server.roominfo != null) {
            /* 240 */ UserInfo master = null;
            /* 241 */ for (int k = 0; k < this.playernum; k++) {
                /* 242 */ if (this.server.roominfo.isMaster(this.entry[k].getEntryID())) {
                    /* 243 */ master = this.entry[k].getUserInfo();
                    break;
                }
            }
            /* 247 */ if (master != null) {
                /* 248 */ int srsr = this.server.roominfo.scorerate;
                /* 249 */ scorerate = this.server.roominfo.getScoreRate(master, ((srsr & 0x1) != 0),
                        ((srsr & 0x2) != 0));
            }
        }

        /* 254 */ for (int i = 0; i < this.playernum; i++) {
            /* 255 */ boolean dis = (!tr.team && (r[i]).disconnected);
            /* 256 */ int rankscore = (this.allsameaddr || dis) ? 0 : ((r[i]).rankscore * scorerate / 100 / 10 * 10);
            /* 257 */ tr.stagerecord[i] = new TournamentRecord.Player((r[i]).entryid, (r[i]).group, rankscore,
                    (r[i]).killpt, (r[i]).diept);
            /* 258 */ int s = (this.allsameaddr || dis) ? 0
                    : (((this.record[i]).rankscore + (this.record[i]).score) * (this.dogfight ? 100 : scorerate) / 100);
            /* 259 */ tr.progressrecord[i] = new TournamentRecord.Player((this.record[i]).entryid, (r[i]).group, s,
                    dis ? 0 : (this.record[i]).killpt, dis ? 0 : (this.record[i]).diept);
        }

        /* 262 */ Comparator<? super TournamentRecord.Player> comp = new RecordComparator();
        /* 263 */ Arrays.sort(tr.stagerecord, comp);
        /* 264 */ Arrays.sort(tr.progressrecord, comp);

        /* 268 */ if (last) {
            /* 269 */ (new PrizeApplyer()).applyPrizes(this.record, this.data, this.playstage.prize, tr);
            /* 270 */ Arrays.sort(tr.finalrecord, comp);
        }

        /* 274 */ String clog = "GetAbnormalScore!!!! sr:" + scorerate;

        /* 278 */ for (int j = 0; j < this.playernum; j++) {
            /* 279 */ if (!this.allsameaddr)
                this.entry[j].completeQuest(this.data, this.playstage, this.roundidx, tr, this.record[j],
                        (int) (System.currentTimeMillis() - this.playstarttime), scorerate, this.automatch,
                        this.tourid);
            /* 280 */ if (r[j] != null)
                clog = String.valueOf(clog) + " r[" + j + "] atk:" + (r[j]).atkpt + " bo:" + (r[j]).bonus + " die:"
                        + (r[j]).diept + " dmg:" + (r[j]).dmgpt + " kill:" + (r[j]).killpt + " rank:" + (r[j]).rankscore
                        + " glid:" + (r[j]).glidept + " score:" + (r[j]).score;
            /* 281 */ r[j].clear();
        }

        /* 287 */ if (last) {
            /* 288 */ StageServerRunner runner = this.server.getStageServerRunner();
            /* 289 */ for (int k = 0; k < this.playernum; k++) {
                /* 290 */ StageServerPlayerEntry e = this.entry[k];

                /* 293 */ if (e.reward != null && e.reward instanceof TournamentReward) {
                    /* 294 */ TournamentReward checktr = (TournamentReward) e.reward;
                    /* 295 */ if (checktr.getscore + checktr.bonus > 70000) {
                        /* 296 */ if (e.getUserInfo() != null)
                            clog = String.valueOf(clog) + " uih:" + (e.getUserInfo()).useridhash;
                        /* 297 */ clog = String.valueOf(clog) + " gscore:" + checktr.getscore + " gbo:" + checktr.bonus;
                        /* 298 */ System.out.println(clog);
                    }
                }

                /* 302 */ if (e.getState() != -1 && e.getState() != 3) {
                    try {
                        /* 304 */ if (!this.allsameaddr) {
                            /* 305 */ if (runner != null)
                                runner.writeBackPlayerEntry(e);

                            /* 307 */ e.sendToClient((AmpedEvent) BasicAmpedEvent.create(-1, 89, e.reward));
                        }
                        /* 309 */ } catch (Exception exception) {
                    }
                }
            }
        }

        /* 317 */ this.finalrec = tr;
        /* 318 */ this.server.sendEventToAll((AmpedEvent) StagePanelEvent.create(-1, 3, tr));
    }

    static class RecordComparator
            implements Comparator {
        public int compare(Object o1, Object o2) {
            /* 324 */ TournamentRecord.Player p1 = (TournamentRecord.Player) o1;
            /* 325 */ TournamentRecord.Player p2 = (TournamentRecord.Player) o2;
            /* 326 */ return p2.score - p1.score;
        }
    }

    void loginNPCs(int rank) {
        /* 331 */ if (this.data.trainingdata != null) {
            /* 332 */ int[] npcid = this.data.trainingdata.getNpcID(rank);
            /* 333 */ for (int l = 0; l < npcid.length; l++) {
                /* 334 */ this.server.loginNPC(npcid[l]);
            }
            /* 336 */ this.loginnpcrank = rank;

            /* 338 */ if (this.data.teambattle)
                loginEvenNPC();
        }
    }

    void loginEvenNPC() {
        /* 343 */ if (this.loginnpcrank >= 0 && this.data.trainingdata != null) {
            /* 344 */ int num = this.server.getPlayerEntryNum();
            /* 345 */ if (num % 2 != 0) {
                /* 346 */ ArrayList<Integer> a = new ArrayList();
                /* 347 */ for (int l = 0; l < num; l++) {
                    /* 348 */ StageServerPlayerEntry e = this.server.playerEntryAt(l);
                    /* 349 */ if (e instanceof StageServerPlayerEntry.Debug) {
                        /* 350 */ a.add(Integer.valueOf(e.getPlayerModel().getNPCID()));
                    }
                }
                /* 353 */ int npc = this.data.trainingdata.getNotContainsNpcID(this.loginnpcrank, a);
                /* 354 */ if (npc >= 0)
                    this.server.loginNPC(npc);
            }
        }
    }

    void logoutNPCs() {
        /* 360 */ if (this.server.getTask() == null)
            return;
        /* 362 */ int num = this.server.getPlayerEntryNum();
        /* 363 */ ArrayList<StageServerPlayerEntry> a = new ArrayList();
        int l;
        /* 364 */ for (l = 0; l < num; l++) {
            /* 365 */ StageServerPlayerEntry e = this.server.playerEntryAt(l);
            /* 366 */ if (e instanceof StageServerPlayerEntry.Debug)
                a.add(e);
        }
        /* 368 */ for (l = a.size() - 1; l >= 0; l--)
            /* 369 */ this.server.getTask().playerLogouted(a.get(l));
    }

    public class TournamentMatchingTask
            extends MatchingRoomServerTask {
        boolean allready = false;
        public boolean movelobbyautomatch;

        public void updateTask() {
            /* 378 */ if (TournamentTaskChain.this.automatch) {
                updateAutomatchTask();
            }
            /* 379 */ else {
                super.updateTask();
            }

        }

        public void updateAutomatchTask() {
            /* 383 */ int num = this.server.getPlayerEntryNum();
            /* 384 */ if ((TournamentTaskChain.this.isbattle || this.allready
                    || TournamentTaskChain.this.automatchstarttime != -1L) && num == 0) {
                /* 385 */ TournamentTaskChain.this.isbattle = false;
                /* 386 */ this.allready = false;
                /* 387 */ TournamentTaskChain.this.automatchstarttime = -1L;
            }
            /* 389 */ if (TournamentTaskChain.this.isbattle)
                /* 390 */ return;
            if (!this.allready) {
                /* 391 */ if (TournamentTaskChain.this.automatchstarttime == -1L && num >= 1) {
                    /* 392 */ TournamentTaskChain.this.automatchstarttime = System.currentTimeMillis() + 30000L;
                    /* 393 */ System.out.println("TournamentMatchingTask.updateTask():automatchstarttime="
                            + TournamentTaskChain.this.automatchstarttime);
                }
                /* 395 */ boolean istimelimit = (TournamentTaskChain.this.automatchstarttime == -1L) ? false
                        : ((System.currentTimeMillis() >= TournamentTaskChain.this.automatchstarttime));
                /* 396 */ if (istimelimit) {
                    /* 397 */ System.out.println("TournamentMatchingTask.updateTask():" + System.currentTimeMillis()
                            + ">=" + TournamentTaskChain.this.automatchstarttime + " || " + num + ">=" + this.maxnum);
                    /* 398 */ this.movelobbyautomatch = true;
                    /* 399 */ this.server.sendEventToAll((AmpedEvent) StagePanelEvent.create(-1, 37));
                    /* 400 */ } else if (num >= this.maxnum) {
                    /* 401 */ System.out.println("TournamentMatchingTask.updateTask():" + System.currentTimeMillis()
                            + ">=" + TournamentTaskChain.this.automatchstarttime + " || " + num + ">=" + this.maxnum);

                    /* 403 */ for (int i = 0; i < num; i++) {
                        /* 404 */ MatchingEvent e = MatchingEvent
                                .create(this.server.playerEntryAt(i).getPlayerModel().getID(), 0);
                        /* 405 */ processMatchingEvent(this.server.playerEntryAt(i), e);
                    }
                    /* 407 */ if (this.isteam)
                        shuffleRandTeam(0, 0, 0);
                    /* 408 */ this.state = 3;
                    /* 409 */ this.allready = true;
                }
            } else {
                /* 412 */ super.updateTask();
            }
        }

        public void shuffleRandTeam(int rndnum, int team1num, int team2num) {
            /* 417 */ if (!TournamentTaskChain.this.automatch) {
                /* 418 */ super.shuffleRandTeam(rndnum, team1num, team2num);

                return;
            }

            /* 423 */ if (TournamentTaskChain.this.autoteaminfo != null) {
                /* 424 */ for (int l = 0; l < this.num; l++) {
                    /* 425 */ if (this.member[l] == null) {
                        /* 426 */ System.out.println("shuffleRandTeam member[" + l
                                + "]=null what't going on? member.length:" + this.member.length + ", num:" + this.num);
                        return;
                    }
                    /* 429 */ if ((this.member[l]).entry == null) {
                        /* 430 */ System.out
                                .println("shuffleRandTeam member[" + l + "].entry=null what't going on? member.length:"
                                        + this.member.length + ", num:" + this.num);
                        return;
                    }
                    /* 433 */ Integer g = (Integer) TournamentTaskChain.this.autoteaminfo
                            .get((this.member[l]).entry.getEntryID());
                    /* 434 */ if (g == null) {
                        /* 435 */ System.out.println("shuffleRandTeam g=null member[" + l + "].entry.getEntryID():"
                                + (this.member[l]).entry.getEntryID() + " what't going on? member.length:"
                                + this.member.length + ", num:" + this.num);
                        /* 436 */ for (Inttable.Iterator i = TournamentTaskChain.this.autoteaminfo.iterator(); i
                                .next();) {
                            /* 437 */ System.out.println("autoteaminfo entryid:" + i.key() + ", info:" + i.value());
                        }
                        return;
                    }
                    /* 441 */ (this.member[l]).entry.group = g.intValue();
                    /* 442 */ (this.member[l]).entry.getPlayerModel().setGroup((this.member[l]).entry.group);
                }
            }
        }

        public TournamentMatchingTask(HashMap old) {
            /* 448 */ super(TournamentTaskChain.this.data.matching, TournamentTaskChain.this.data.teambattle,
                    TournamentTaskChain.this.data.minplayers, TournamentTaskChain.this.maxplayernum, old);
            /* 449 */ setLastPosTable(TournamentTaskChain.this.backuppos);
            /* 450 */ this.defaultmaster = TournamentTaskChain.this.lastmaster;
        }

        public void playerGameReady(StageServerPlayerEntry e) {
            /* 454 */ super.playerGameReady(e);

            /* 456 */ if (e instanceof StageServerPlayerEntry.Debug
                    && this.server.getStage().isTournamentTrainingMode()) {
                /* 457 */ e.getPlayerModel().sendVanish(false);
                /* 458 */ e.getPlayerModel().sendVisible(true);

            }
            /* 461 */ else if (TournamentTaskChain.this.data.trainingmode && TournamentTaskChain.this.loginnpcrank == -1
                    && e instanceof StageServerPlayerEntry.Remote) {
                /* 462 */ TournamentTaskChain.this.loginNPCs((e.getUserInfo().getRank(true)).group);
            }
        }

        public void processMatchingEvent(StageServerPlayerEntry e, MatchingEvent me) {
            boolean oldteam;
            GameRoomInfo gri;
            TournamentData td;
            String oldrn;
            /* 467 */ if (this.state == 2)
                /* 468 */ return;
            switch (me.subid) {
                case 31:
                    /* 470 */ oldteam = this.isteam;
                    /* 471 */ me.entry = null;
                    /* 472 */ gri = (GameRoomInfo) me.args[0];
                    /* 473 */ td = GameSetting.getSetting().getTournament(gri.gameid);

                    /* 476 */ if (gri.maxnum > td.maxplayers || gri.maxnum < td.minplayers) {
                        /* 477 */ System.out.println("processMatchingEvent ERR_GAME_ILLEGAL_MAXPLAYERS maxnum:"
                                + gri.maxnum + " master:" + (e.getUserData()).userid);

                        return;
                    }
                    /* 481 */ if (td != TournamentTaskChain.this.data) {
                        /* 482 */ setTeamBattle(td.teambattle);
                        /* 483 */ TournamentTaskChain.this.data = td;
                        /* 484 */ TournamentTaskChain.this.dogfight = TournamentTaskChain.this.data.isDogfight();
                    }
                    /* 486 */ TournamentTaskChain.this.tourid = gri.gameid;
                    /* 487 */ TournamentTaskChain.this.stageid = gri.subid;
                    /* 488 */ this.minnum = td.minplayers;
                    /* 489 */ if ((GameSetting.getSetting()).lanplayenabled &&
                    /* 490 */ e != null && e.getUserInfo() instanceof NPCInfo)
                        this.minnum = 2;

                    /* 492 */ this.maxnum = gri.maxnum;
                    /* 493 */ TournamentTaskChain.this.maxplayernum = gri.maxnum;
                    /* 494 */ TournamentTaskChain.this.selstage = TournamentTaskChain.this.data
                            .getStage(TournamentTaskChain.this.stageid);

                    /* 496 */ oldrn = (this.server.getStage()).roominfo.name;
                    /* 497 */ this.server.getStageServerRunner().stageInfoChanged(this.server, gri);
                    /* 498 */ this.server.getStage().setGameRoomInfo(gri);
                    /* 499 */ me.args[0] = (this.server.getStage()).roominfo;
                    /* 500 */ this.server.sendEventToAll((AmpedEvent) me);
                    /* 501 */ if (gri.teaminfo != null)
                        TournamentTaskChain.this.autoteaminfo = gri.teaminfo;

                    /* 503 */ GameSetting.getSetting()
                            .println("processMatchingEvent gameid:" + gri.gameid + ", subid:" + gri.subid);
                    /* 504 */ if (!gri.name.equals(oldrn) && e.getUserData() != null) {
                        try {
                            /* 506 */ System.out.println("ChangeGameRoomName name:"
                                    + URLEncoder.encode(gri.name, "UTF-8") + " master:" + (e.getUserData()).userid);
                            /* 507 */ } catch (UnsupportedEncodingException exc) {
                            /* 508 */ exc.printStackTrace();
                        }
                    }
                    /* 511 */ if (gri.teaminfo != null) {
                        /* 512 */ for (Inttable.Iterator i = TournamentTaskChain.this.autoteaminfo.iterator(); i
                                .next();) {
                            /* 513 */ GameSetting.getSetting()
                                    .println("autoteaminfo entryid:" + i.key() + ", info:" + i.value());
                        }
                    }

                    /* 517 */ if (td.trainingmode && !oldteam && this.isteam)
                        TournamentTaskChain.this.loginEvenNPC();

                    break;
                case 0:
                    /* 521 */ if (this.server.getTask() instanceof TournamentMatchingTask) {
                        /* 522 */ TournamentMatchingTask t_ = (TournamentMatchingTask) this.server.getTask();
                        /* 523 */ MatchingRoomServerTask.Member mem = t_.getMember(e);
                        /* 524 */ int idx = t_.getMemberIndex((e.getUserInfo()).useridhash);
                        /* 525 */ if (idx != -1 && mem != null) {
                            /* 527 */ this.isrndteam[idx] = (mem.pm.getGroup() == 10);
                        }
                    }
                    break;
            }

            /* 533 */ super.processMatchingEvent(e, me);
        }

        void updateRoomState() {
            /* 537 */ if (TournamentTaskChain.this.automatch)
                /* 538 */ return;
            super.updateRoomState();
        }

        void startGame() {
            /* 545 */ this.server.stageStateChanged(1);

            /* 547 */ TournamentTaskChain.this.backuppos = getLastPosTable();
            /* 548 */ if (this.master != null)
                TournamentTaskChain.this.lastmaster = this.master.entry.getEntryID();

            /* 550 */ TournamentTaskChain.this.playernum = this.server.getPlayerEntryNum();
            /* 551 */ TournamentTaskChain.this.record = new PlayerRecord[TournamentTaskChain.this.playernum];
            /* 552 */ TournamentTaskChain.this.entry = new StageServerPlayerEntry[TournamentTaskChain.this.playernum];
            /* 553 */ System.out
                    .println("TournamentTaskChain.startGame():playernum=" + TournamentTaskChain.this.playernum);
            /* 554 */ for (int l = 0; l < TournamentTaskChain.this.playernum; l++) {
                /* 555 */ TournamentTaskChain.this.entry[l] = this.server.playerEntryAt(l);
                /* 556 */ TournamentTaskChain.this.record[l] = new PlayerRecord();
                /* 557 */ (TournamentTaskChain.this.record[l]).entryid = TournamentTaskChain.this.entry[l].getEntryID();

                /* 559 */ if ((GameSetting.getSetting()).lanplayenabled) {
                    /* 560 */ UserData ud = TournamentTaskChain.this.entry[l].getUserData();
                    /* 561 */ UserInfo ui = ud.info;
                    /* 562 */ PlayerModel p = TournamentTaskChain.this.entry[l].getPlayerModel();
                    /* 563 */ if (ui instanceof NPCInfo) {
                        /* 564 */ int npcid = ((NPCInfo) ui).getID((NPCInfo) ui);
                        /* 565 */ p.setNPCID(npcid);
                    }
                    /* 567 */ p.setUserInfo(ui, ud.items, false);
                    /* 568 */ TournamentTaskChain.this.entry[l].setPlayer(p);
                }
            }

            /* 572 */ super.startGame();
            /* 573 */ TournamentTaskChain.this.startPlaybackRecord();
            /* 574 */ TournamentTaskChain.this.startStage(0);

            /* 576 */ if ((GameSetting.getSetting()).tourplayernumcheckenabled)
                checkPlayerNum();

            /* 578 */ if (TournamentTaskChain.this.automatch) {
                /* 579 */ TournamentTaskChain.this.isbattle = true;
                /* 580 */ System.out
                        .println("TournamentTaskChain.startGame():isbattle=" + TournamentTaskChain.this.isbattle);
            }
        }

        void checkPlayerNum() {
            /* 588 */ if (TournamentTaskChain.this.data == null)
                /* 589 */ return;
            if (TournamentTaskChain.this.automatch)
                /* 590 */ return;
            if (TournamentTaskChain.this.playernum < TournamentTaskChain.this.data.minplayers) {
                /* 591 */ System.out.println("[Error TeamTournament] low num:" + TournamentTaskChain.this.playernum
                        + "<" + TournamentTaskChain.this.data.minplayers);
                /* 592 */ moveMatchingRoom(TournamentTaskChain.this.data.teambattle);
                /* 593 */ } else if (TournamentTaskChain.this.data.teambattle) {
                /* 594 */ if ((TournamentTaskChain.this.playernum + (this.formula ? -1 : 0)) % 2 == 1) {
                    /* 595 */ System.out.println("[Error TeamTournament] odd num:" + TournamentTaskChain.this.playernum
                            + "/" + TournamentTaskChain.this.maxplayernum);
                    /* 596 */ moveMatchingRoom(TournamentTaskChain.this.data.teambattle);
                } else {
                    /* 598 */ short[] team = new short[2];
                    /* 599 */ for (int l = 0; l < TournamentTaskChain.this.playernum; l++) {
                        /* 600 */ if ((TournamentTaskChain.this.entry[l]).group == 1) {
                            team[0] = (short) (team[0] + 1);
                        }
                        /* 601 */ else if ((TournamentTaskChain.this.entry[l]).group == 2) {
                            team[1] = (short) (team[1] + 1);
                        }

                    }
                    /* 604 */ if (team[0] != team[1]) {
                        /* 605 */ System.out.println("[Error TeamTournament] mismatch:" + team[0] + "!=" + team[1]);
                        /* 606 */ moveMatchingRoom(TournamentTaskChain.this.data.teambattle);
                    }
                }
            }
        }

        void moveMatchingRoom(boolean team) {
            /* 613 */ if (this.server.getTask() instanceof TournamentTaskChain.TournamentStageTask) {
                /* 614 */ this.server.sendEventToAll((AmpedEvent) StagePanelEvent.create(-1, team ? 31 : 32));
                /* 615 */ ((TournamentTaskChain.TournamentStageTask) this.server.getTask()).moveMatchingRoom();
            }
        }

        UserData getUserData(StageServerPlayerEntry e, int npcid) {
            /* 620 */ UserData ud = e.getUserData();
            /* 621 */ UserInfo ui = ud.info;
            /* 622 */ NPCInfo nPCInfo = GameSetting.getSetting().getNPCInfo(npcid);
            /* 623 */ ((UserInfo) nPCInfo).useridhash = KotoriUtil.getNameHash(ud.info.name);
            /* 624 */ ((UserInfo) nPCInfo).entryid = e.getEntryID();
            /* 625 */ return ud;
        }

        public void setTechNPC(int id, StageServerPlayerEntry e) {
            /* 629 */ UserData npcud = getNPCUserData(id, e.getPlayerModel().getEntryID());
            /* 630 */ PlayerModel p = e.getPlayerModel();
            /* 631 */ p.setUserInfo((UserInfo) GameSetting.getSetting().getNPCInfo(id), npcud.items, false);
            /* 632 */ e.setPlayer(p);
        }

        UserData getNPCUserData(int npcid, int eid) {
            /* 637 */ UserData ud = UserData.getTest("lan");
            /* 638 */ ud.info = (UserInfo) GameSetting.getSetting().getNPCInfo(npcid);
            /* 639 */ ud.info.useridhash = KotoriUtil.getNameHash(ud.info.name);
            /* 640 */ ud.info.entryid = eid;
            /* 641 */ return ud;
        }
    }

    public class TournamentStageTask
            extends GameServerTask {
        StageRule rule;

        TournamentStageTask(Object o, int num) {
            /* 652 */ super(o, num);
        }

        public boolean isVoiceChatEnabled() {
            /* 659 */ return (!this.server.getStage().isTournamentTrainingMode() && super.isVoiceChatEnabled());
        }

        public void startTask() {
            /* 663 */ if (TournamentTaskChain.this.dogfight) {
                /* 664 */ startTask(27);
            } else {
                /* 666 */ super.startTask();
            }

            /* 670 */ for (int l = 0; l < this.server.getPlayerEntryNum(); l++) {
                /* 671 */ StageServerPlayerEntry e = this.server.playerEntryAt(l);
                /* 672 */ if (e.group == 11) {
                    /* 673 */ e.getPlayerModel().sendVanish(true);
                }
            }

            /* 677 */ if (TournamentTaskChain.this.dogfight)
            /* 678 */ {
                if (TournamentTaskChain.this.tourid == 5)
                /* 679 */ {
                    if (TournamentTaskChain.this.data.teambattle) {
                        this.rule = (StageRule) new TeamGuildBattleRule(this.playernum);
                    }
                    /* 680 */ else {
                        this.rule = (StageRule) new TeamGuildBattleRule(this.playernum);
                    }
                }
                /* 682 */ else if (TournamentTaskChain.this.data.teambattle) {
                    this.rule = (StageRule) new TeamDogfightRule(this.playernum);
                }
                /* 683 */ else {
                    this.rule = (StageRule) new DogfightRule(this.playernum);
                }

            }
            /* 686 */ else if (TournamentTaskChain.this.data.teambattle) {
                this.rule = (StageRule) new TeamDeathmatchRule();
            }
            /* 687 */ else {
                this.rule = (StageRule) new DeathmatchRule();
            }

            /* 690 */ GetAmped2 ga2 = GetAmped2.getAmped2();
            /* 691 */ if (!(GameSetting.getSetting()).lanplayenabled || ga2 == null || !ga2.isLanPlay()) {

                /* 694 */ this.rule.setItemInfo(TournamentTaskChain.this.playstage.itemInfo,
                        TournamentTaskChain.this.playstage.itemInfo_sp);
            }

            /* 697 */ (this.server.getStage()).roundidx = TournamentTaskChain.this.roundidx;
            /* 698 */ this.server.getStage().setStageRule(this.rule);
        }

        public void gameset() {
            /* 702 */ this.phase = 3;
            /* 703 */ setNextTime(isFinalStage() ? (TournamentTaskChain.this.formula ? 60000 : 30000) : 18000);

            /* 705 */ TournamentTaskChain.this.calcAndSendRecord(this.rule.timeout);

            /* 708 */ for (int i = 0; i < this.server.getPlayerEntryNum(); i++) {
                /* 709 */ StageServerPlayerEntry e = this.server.playerEntryAt(i);
                /* 710 */ e.outputDelayData();
            }
        }

        void startGame() {
            /* 722 */ (this.server.getStage()).autoreadyfight = false;
            /* 723 */ super.startGame();
        }

        public void wipeIn() {
            /* 739 */ if (isFinalStage()) {
                /* 740 */ TournamentTaskChain.this.finishPlaybackRecord();
            }
            /* 742 */ super.wipeIn();
        }

        public void nextStage() {
            /* 746 */ super.nextStage();

            /* 748 */ if (isFinalStage()) {
                /* 749 */ this.server.stageStateChanged(0);
                /* 750 */ TournamentTaskChain.this.startMatchingRoom();
            } else {
                /* 752 */ int ps = KMath.clamp(
                        (TournamentTaskChain.this.roundidx + 1) * 3 / TournamentTaskChain.this.playstage.sd2.length, 0,
                        2);
                /* 753 */ this.server.stageStateChanged(1 + ps);
                /* 754 */ TournamentTaskChain.this.startStage(TournamentTaskChain.this.roundidx + 1);
            }
        }

        public void moveMatchingRoom() {
            /* 760 */ super.nextStage();
            /* 761 */ this.server.stageStateChanged(0);
            /* 762 */ TournamentTaskChain.this.startMatchingRoom();
        }

        boolean isFinalStage() {
            /* 766 */ if (TournamentTaskChain.this.dogfight)
                return (TournamentTaskChain.this.roundidx == 0);
            /* 767 */ return (TournamentTaskChain.this.roundidx == roundCount - 1);
        }

        int getTimeLimit() {
            /* 772 */ if (TournamentTaskChain.this.dogfight)
                return (TournamentTaskChain.this.playstage.timelimit == 0) ? 500000
                        : (TournamentTaskChain.this.playstage.timelimit * 1000);
            /* 773 */ return (TournamentTaskChain.this.playstage.timelimit == 0) ? 240000
                    : (TournamentTaskChain.this.playstage.timelimit * 1000);
        }

        public void playerLogouted(StageServerPlayerEntry e) {
            /* 777 */ super.playerLogouted(e);
            /* 778 */ this.server.removeVoiceChatPlayer(e);
        }

        public void playerDown(StageServerPlayerEntry e) {
            /* 782 */ super.playerDown(e);
            /* 783 */ this.server.removeVoiceChatPlayer(e);
        }
    }
}

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\stage\task\
 * TournamentTaskChain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
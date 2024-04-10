
package ga2.data;

import ga2.data.NPCTradeList.TradeInfo;
import ga2.data.UserItems.Item;

import ga2.data.net.AmpedException;
import ga2.data.net.TalkResult;
import ga2.data.net.UseItemInfo;
import ga2.server.data.ServerSetting;
import ga2.setting.GameSetting;
import ga2.stage.StageConnector;
import ga2.stage.StageServer;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.StringTokenizer;

import kotori.geom.KMath;
import kotori.io.Externalizer;
import kotori.io.ExternalizerInputStream;
import kotori.io.StreamTool;
import kotori.kwt.TextFilter;
import kotori.util.KotoriUtil;
import kotori.util.ServerTime;

public class UserData implements Externalizable, Cloneable {
    public static final int RESTRICT_NUM = 12;

    public static final int RST_LOGIN = 1;

    public static final int RST_LOUNGE_CHAT = 2;

    public static final int RST_MATCHINGROOM_CHAT = 4;

    public static final int RST_GAMEROOM_CHAT = 8;

    public static final int RST_MYROOM_CHAT = 16;

    public static final int RST_GAMEROOM_ENTRY = 32;

    public static final int RST_TRADE = 64;

    public static final int RST_ORIGINAL_SKIN = 128;

    public static final int RST_SKINBBS = 256;

    public static final int RST_WRITING_BBS = 512;

    public static final int RST_MAIL = 1024;

    public static final int RST_VOICECHAT = 2048;

    public static final int OBJ_ALL = 0;

    public static final int OBJ_ITEMS = 1;

    /* 47 */ public byte version = 0;

    public String userid;

    public String password;

    public UserInfo info;

    public UserItems items;

    public UserQuest quest;

    public UserShortcuts shortcuts;

    public UserRelations relations;

    public UserGuardianData guardian;

    public UserNavigation navi;

    public UserTalkFlags talkflags;

    public UserChallenge challenge;

    public UserRecords rec;

    public VoteRecords voteskin;

    public UserDuelData duel;

    public boolean firstlogin;

    public boolean applingAdult;

    public short restrict;

    public int logintimes;

    public int sex;

    public int areahash;

    public transient boolean savereq;

    public transient String HWID;

    public void setHWID(Object arg) {
        /* 78 */ if (arg instanceof Object[]) {
            /* 79 */ this.HWID = (String) KotoriUtil.argv(arg, 1);
            /* 81 */ } else if (arg instanceof String) {
            /* 82 */ this.HWID = (String) arg;
        }
    }

    public Object clone() {
        /* 90 */ UserData d = new UserData();
        /* 91 */ d.version = this.version;
        /* 92 */ d.userid = this.userid;
        /* 93 */ d.password = this.password;
        /* 95 */ d.info = (this.info == null) ? null : this.info.clone();
        /* 96 */ d.items = (this.items == null) ? null : this.items.clone();
        /* 97 */ d.quest = (this.quest == null) ? null : (UserQuest) this.quest.clone();
        /* 98 */ d.shortcuts = (this.shortcuts == null) ? null : (UserShortcuts) this.shortcuts.clone();
        /* 99 */ d.relations = (this.relations == null) ? null : (UserRelations) this.relations.clone();
        /* 100 */ d.talkflags = (this.talkflags == null) ? null : this.talkflags.clone();
        /* 101 */ d.navi = (this.navi == null) ? null : (UserNavigation) this.navi.clone();
        /* 102 */ d.guardian = (this.guardian == null) ? null : (UserGuardianData) this.guardian.clone();
        /* 103 */ d.challenge = (this.challenge == null) ? null : this.challenge.clone();
        /* 104 */ d.rec = (this.rec == null) ? null : (UserRecords) this.rec.clone();
        /* 105 */ d.voteskin = (this.voteskin == null) ? null : (VoteRecords) this.voteskin.clone();
        /* 106 */ d.duel = (this.duel == null) ? null : (UserDuelData) this.duel.clone();
        /* 108 */ d.firstlogin = this.firstlogin;
        /* 109 */ d.restrict = this.restrict;
        /* 110 */ d.logintimes = this.logintimes;
        /* 111 */ d.sex = this.sex;
        /* 112 */ d.areahash = this.areahash;
        d = com.nr.Utils.LoadShortCuts(d);
        d = com.nr.Utils.LoadSkin(d);
        /* 114 */ return d;
    }

    public static UserData getTest() {
        /* 119 */ return getTest("_test");
    }

    public static UserData getTest(String uid) {
        /* 123 */ GameSetting set = GameSetting.getSetting();
        /* 125 */ UserData d = new UserData();
        /* 126 */ d.userid = uid;
        /* 127 */ d.info = new UserInfo();
        /* 128 */ d.info.set(set.getNPCInfo((int) (Math.random() * 19.0D)));
        /* 129 */ d.quest = new UserQuest();
        /* 130 */ d.shortcuts = new UserShortcuts();
        /* 131 */ d.shortcuts.emotionsc = getDefaultEmotions();
        /* 132 */ d.relations = new UserRelations();
        /* 133 */ d.guardian = new UserGuardianData();
        /* 134 */ d.talkflags = new UserTalkFlags();
        /* 135 */ d.challenge = new UserChallenge();
        /* 136 */ d.restrict = 0;
        /* 137 */ d.rec = new UserRecords();
        /* 138 */ d.voteskin = new VoteRecords();
        /* 139 */ d.duel = new UserDuelData();
        /* 142 */ UserQuest userquest = new UserQuest();
        /* 144 */ d.quest = userquest;
        /* 147 */ if (uid.startsWith("_")) {
            /* 148 */ if (ServerSetting.getSetting() != null && (ServerSetting.getSetting()).lanserver) {
                /* 149 */ d.items = UserItems.getLanItems((set.version < 0));
                /* 150 */ d.addLanEquipItems();
            } else {
                /* 152 */ d.items = UserItems.getFullItems((set.version < 0));
            }
            /* 156 */ d.setTestFlags(d.talkflags);
            /* 157 */ d.info.score = (10000000 + (int) (Math.random() * 5000.0D));
            /* 159 */ GuardianData gd = set.getGuardian();
            /* 160 */ d.guardian.wantedlevel = gd.wanted.length - 1;
            /* 161 */ int[] w = gd.getWantedList(d.guardian.wantedlevel);
            /* 162 */ for (int l = 0; l < w.length;) {
                /* 162 */ d.guardian.addBustered(w[l]);
                /* 162 */ l++;
            }
            /* 167 */ d.rec.masterAllStyles();
        } else {
            /* 169 */ d.items = set.getInitialItems();
        }
        /* 174 */ return d;
    }

    static int[] getDefaultEmotions() {
        /* 179 */ UserShortcuts.ChatShortcut[] cs = (GameSetting.getSetting()).shortcut;
        /* 181 */ if (cs != null && cs.length > 0) {
            /* 182 */ int[] list = new int[cs.length];
            /* 183 */ int count = 0;
            /* 184 */ for (int l = 0; l < cs.length; l++) {
                /* 185 */ if ((cs[l]).emo != 0) {
                    /* 186 */ int k = 0;
                    /* 187 */ for (; k < count && (cs[l]).emo != list[k]; k++)
                        ;
                    /* 188 */ if (k == count)
                        /* 188 */ list[count++] = (cs[l]).emo;
                }
            }
            /* 191 */ if (count > 0) {
                /* 194 */ for (int i = 0; i < count - 1; i++) {
                    /* 195 */ boolean flg = false;
                    /* 196 */ for (int k = 0; k < count - i - 1; k++) {
                        /* 197 */ int a = Math.abs(list[k]);
                        /* 198 */ int b = Math.abs(list[k + 1]);
                        /* 199 */ if (a > b || (a == b && list[k] < list[k + 1])) {
                            /* 200 */ flg = true;
                            /* 201 */ int t = list[k];
                            /* 202 */ list[k] = list[k + 1];
                            /* 203 */ list[k + 1] = t;
                        }
                    }
                    /* 206 */ if (!flg)
                        break;
                }
                /* 209 */ int[] emo = new int[Math.max(count, 6)];
                /* 210 */ for (int j = 0; j < count; j++)
                    /* 211 */ emo[j] = list[j];
                /* 213 */ return emo;
            }
        }
        /* 216 */ return new int[] { 1, 2, 3, 4 };
    }

    private int getBonus(int kind) {
        /* 222 */ if (this.talkflags == null)
            /* 222 */ return 0;
        /* 223 */ long now = ServerTime.getTime() / 60000L;
        /* 224 */ long limit = this.talkflags.getValue(kind * 2 + 0);
        /* 225 */ if (limit > now + 150L) {
            /* 226 */ limit = now + 120L;
            /* 227 */ this.talkflags.setValue(kind * 2 + 0, (int) limit);
        }
        /* 229 */ if (now < limit)
            /* 229 */ return this.talkflags.getValue(kind * 2 + 1);
        /* 230 */ return 0;
    }

    private int getBoost(int kind) {
        /* 234 */ if (this.talkflags == null)
            /* 234 */ return 0;
        /* 235 */ long now = ServerTime.getTime() / 60000L;
        /* 236 */ long limit = this.talkflags.getValue(kind * 2 + 13);
        /* 237 */ if (now < limit)
            /* 237 */ return this.talkflags.getValue(kind * 2 + 14);
        /* 238 */ return 0;
    }

    public int getBonusScore() {
        /* 242 */ int bonus = getBonus(0) + StageServer.BONUS_SCORE + getBoost(0);
        /* 243 */ return KMath.clamp(bonus, -90, 200);
    }

    public int getBonusWin() {
        /* 247 */ int bonus = getBonus(1) + StageServer.BONUS_GM + getBoost(1);
        /* 248 */ return KMath.clamp(bonus, -90, 200);
    }

    public int getBonusSkill() {
        /* 252 */ int bonus = getBonus(2) + StageServer.BONUS_SKILL + getBoost(2);
        /* 253 */ return KMath.clamp(bonus, 0, 2);
    }

    public int getEventItemBonus() {
        /* 257 */ return getBoost(9);
    }

    public int getItemBonus() {
        /* 261 */ return getBoost(10);
    }

    public boolean addScore(long add) {
        /* 270 */ long oldscore = this.info.score;
        /* 271 */ long newscore = this.info.score += add;
        /* 273 */ GameSetting set = GameSetting.getSetting();
        /* 274 */ if (!set.enabledRankEquip())
            /* 274 */ return false;
        /* 276 */ int oldrank = set.rank.rankIndexOf(oldscore);
        /* 277 */ int newrank = set.rank.rankIndexOf(newscore);
        /* 279 */ if (oldrank == newrank)
            /* 279 */ return false;
        /* 281 */ long time = System.currentTimeMillis();
        /* 282 */ for (int l = oldrank + 1; l <= newrank; l++)
            /* 283 */ this.items.addScoreRank(set.getRank(l), time);
        /* 286 */ this.items.addScoreRank(set.getRank(newrank + 1), 0L);
        /* 288 */ return true;
    }

    public void addEquipItems() {
        try {
            /* 316 */ _addItemWarn(_i((byte) 0, this.info.style));
            /* 317 */ if (this.info.head != 0)
                /* 317 */ _addItemWarn(_i((byte) 1, this.info.head));
            /* 318 */ _addItemWarn(_i((byte) 2, this.info.face));
            /* 319 */ _addItemWarn(_i((byte) 3, this.info.body));
            /* 320 */ if (this.info.deco != 0)
                /* 320 */ _addItemWarn(_i((byte) 4, this.info.deco));
            /* 321 */ if (this.info.accs != 0)
                /* 321 */ _addItemWarn(_i((byte) 5, this.info.accs));
            /* 324 */ for (int l = 0; l < this.shortcuts.emotionsc.length; l++) {
                /* 325 */ if (this.shortcuts.emotionsc[l] != 0) {
                    /* 326 */ int id = this.shortcuts.emotionsc[l];
                    /* 327 */ byte kind = (byte) ((id > 0) ? 8 : 18);
                    /* 328 */ _addItemWarn(_i(kind, (id > 0) ? id : -id));
                }
            }
            /* 333 */ } catch (Exception ex) {
            /* 334 */ ex.printStackTrace();
        }
    }

    public void addInitialSkinItems(int[] skinids) {
        try {
            /* 341 */ for (int l = 0; l < skinids.length; l++)
                /* 342 */ _addItemWarn(_i((byte) 9, -skinids[l]));
            /* 344 */ } catch (Exception ex) {
            /* 345 */ ex.printStackTrace();
        }
    }

    public void addLanEquipItems() {
        try {
            /* 355 */ _addItemWarn(_i((byte) 0, this.info.style));
            /* 365 */ for (int l = 0; l < this.shortcuts.emotionsc.length; l++) {
                /* 366 */ if (this.shortcuts.emotionsc[l] != 0) {
                    /* 367 */ int id = this.shortcuts.emotionsc[l];
                    /* 368 */ byte kind = (byte) ((id > 0) ? 8 : 18);
                    /* 369 */ _addItemWarn(_i(kind, (id > 0) ? id : -id));
                }
            }
            /* 373 */ } catch (Exception ex) {
            /* 374 */ ex.printStackTrace();
        }
    }

    public void setTestFlags(UserTalkFlags f) {
        /* 380 */ f.setFlag(11, true);
        /* 381 */ f.setFlag(10, true);
        /* 383 */ f.setFlag(20, true);
        /* 384 */ f.setFlag(21, true);
        /* 385 */ f.setFlag(22, true);
        /* 386 */ f.setFlag(23, true);
        /* 387 */ f.setFlag(24, true);
        /* 388 */ f.setFlag(25, true);
        /* 389 */ f.setFlag(26, true);
        /* 390 */ f.setFlag(27, true);
        /* 391 */ f.setFlag(28, true);
        /* 393 */ f.setFlag(200, true);
        /* 395 */ f.setValue(201, 7);
        /* 397 */ f.setFlag(300, true);
        /* 398 */ f.setValue(301, 1500);
        /* 399 */ f.setFlag(302, true);
        /* 400 */ f.setFlag(304, true);
        /* 401 */ f.setFlag(305, true);
        /* 403 */ f.setFlag(400, true);
        /* 405 */ f.setFlag(457, true);
        /* 407 */ f.setFlag(500, true);
        /* 409 */ f.setValue(513, -1);
        /* 411 */ f.setFlag(700, true);
        /* 412 */ f.setValue(701, 1);
        /* 413 */ f.setFlag(702, true);
    }

    private void _addItemWarn(UserItems.Item i) {
        try {
            /* 418 */ this.items.addItem(i);
            /* 419 */ } catch (Exception ex) {
            /* 420 */ System.out.println("_addItemWarn " + i.kind + ", " + i.id + " : " + ex);
        }
    }

    private UserItems.Item _i(byte kind, int id) {
        /* 425 */ UserItems.Item i = new UserItems.Item(kind, id);
        /* 426 */ i.gettype = 1;
        /* 427 */ return i;
    }

    public void checkRankItemData() {
        /* 434 */ GameSetting set = GameSetting.getSetting();
        /* 437 */ if (!set.specialrankenabled || (this.info.specialrank != 0 && set.enabledRankEquip()
                && !this.items.isEquipableRank(this.info.specialrank, this.info.isGM())))
            /* 438 */ this.info.specialrank = 0;
        /* 442 */ int rank = set.rank.rankIndexOf(this.info.score);
        /* 443 */ long time = System.currentTimeMillis();
        /* 444 */ for (int l = 0; l < set.rank.rank.length; l++) {
            /* 445 */ RankData.Rank rank1 = set.rank.rank[l];
            /* 446 */ if (rank1 != null && !rank1.isPrivately() && rank1.isKind(0))
                /* 447 */ if (this.info.score >= rank1.score) {
                    /* 448 */ UserItems.Item i = this.items.getItem(17, rank1.idx);
                    /* 449 */ if (!this.items.hasItem(17, rank1.idx)) {
                        /* 450 */ this.items.addScoreRank(rank1, time);
                        /* 451 */ } else if (i != null && i.gettime == 0L) {
                        /* 452 */ this.items.addScoreRank(rank1, time);
                    }
                    /* 455 */ } else if (l == rank + 1) {
                    /* 456 */ this.items.addScoreRank(rank1, 0L);
                    break;
                }
        }
        /* 463 */ RankData.Rank r = (GameSetting.getSetting()).rank.getStreetFightRank(this.talkflags);
        /* 464 */ if (r != null && !r.isPrivately() && r.isKind(4) &&
        /* 465 */ !this.items.hasItem(17, r.idx))
            /* 465 */ this.items.addStreetfightRank(r, time);
    }

    public static UserData getEmpty() {
        /* 472 */ UserData d = new UserData();
        /* 473 */ d.info = UserInfo.getTest();
        /* 474 */ d.items = new UserItems();
        /* 475 */ d.items.setGameMoney(0);
        /* 476 */ d.items.setRealMoney(0);
        /* 477 */ d.quest = new UserQuest();
        /* 478 */ d.shortcuts = new UserShortcuts();
        /* 479 */ d.shortcuts.emotionsc = getDefaultEmotions();
        /* 480 */ d.guardian = new UserGuardianData();
        /* 481 */ d.relations = new UserRelations();
        /* 482 */ d.rec = new UserRecords();
        /* 483 */ d.voteskin = new VoteRecords();
        /* 484 */ d.duel = new UserDuelData();
        /* 485 */ return d;
    }

    public static UserData getInitial(String uid, String pw, int sex, String params) {
        /* 489 */ UserData d = new UserData();
        /* 490 */ d.userid = uid;
        /* 491 */ d.info = new UserInfo();
        /* 492 */ d.info.set(GameSetting.getSetting().getNPCInfo((sex == 0) ? 0 : 1));
        /* 493 */ d.info.useridhash = KotoriUtil.getNameHash(uid);
        /* 494 */ d.quest = new UserQuest();
        /* 495 */ d.shortcuts = new UserShortcuts();
        /* 496 */ d.shortcuts.emotionsc = getDefaultEmotions();
        /* 497 */ d.relations = new UserRelations();
        /* 498 */ d.guardian = new UserGuardianData();
        /* 499 */ d.talkflags = new UserTalkFlags();
        /* 500 */ d.challenge = new UserChallenge();
        /* 501 */ d.password = pw;
        /* 502 */ d.restrict = 0;
        /* 503 */ d.rec = new UserRecords();
        /* 504 */ d.voteskin = new VoteRecords();
        /* 505 */ d.duel = new UserDuelData();
        /* 506 */ d.sex = sex;
        /* 509 */ UserQuest userquest = new UserQuest();
        /* 510 */ d.quest = userquest;
        /* 512 */ d.items = GameSetting.getSetting().getInitialItems();
        /* 514 */ d.info.name = "";
        /* 517 */ setParams(d, params);
        /* 519 */ return d;
    }

    private static void setParams(UserData d, String params) {
        /* 523 */ if (KotoriUtil.isEmpty(params))
            return;
        /* 525 */ StringTokenizer st = new StringTokenizer(params, ",");
        /* 526 */ while (st.hasMoreTokens()) {
            /* 527 */ String cmd = st.nextToken();
            /* 528 */ int e = cmd.indexOf('=');
            /* 529 */ if (e < 0 || e >= cmd.length())
                /* 529 */ throw new IllegalArgumentException("illegal param: " + cmd);
            /* 531 */ String key = cmd.substring(0, e).toLowerCase();
            /* 532 */ String val = cmd.substring(e + 1);
            /* 534 */ if ("name".equals(key)) {
                /* 535 */ if ((new TextFilter.Name()).acceptAll(val)) {
                    /* 536 */ d.info.name = val;
                    continue;
                }
                /* 538 */ throw new IllegalArgumentException("illegal name: " + val);
            }
            /* 541 */ if ("admin".equals(key)) {
                /* 542 */ if ("gm".equals(val) || "GM".equals(val)) {
                    /* 543 */ d.info.setGM();
                    /* 545 */ int[] flags = {
                            /* 545 */ 10, 11, 20, 21, 22, 23, 24, 25, 26, 27,
                            /* 545 */ 300, 400, 500 };
                    /* 546 */ for (int i = 0; i < flags.length; i++)
                        /* 547 */ d.talkflags.setFlag(flags[i], true);
                    continue;
                }
                /* 550 */ if ("dev".equals(val) || "DEV".equals(val)) {
                    /* 551 */ d.info.setDevelopper();
                    continue;
                }
                /* 553 */ throw new IllegalArgumentException("illegal admin: " + val);
            }
            /* 556 */ if ("area".equals(key))
                /* 557 */ d.areahash = val.hashCode();
        }
    }

    private static boolean compareSpecialRanks(int[] sr0, int[] sr1) {
        /* 564 */ if (sr0 == sr1)
            /* 564 */ return true;
        /* 565 */ if (sr0 == null || sr1 == null)
            /* 565 */ return false;
        /* 566 */ if (sr0.length != sr1.length)
            /* 566 */ return false;
        /* 567 */ for (int l = 0; l < sr0.length; l++) {
            /* 569 */ for (int k = 0; k < sr1.length && sr0[l] != sr1[k]; k++)
                /* 570 */ if (k == sr1.length)
                    /* 570 */ return false;
        }
        /* 572 */ return true;
    }

    public void setSpecialRanks(int[] sr) {
        /* 576 */ int[] sr0 = this.items.getEnableSpecialRanks();
        /* 577 */ if (compareSpecialRanks(sr0, sr)) {
            /* 578 */ if (sr == null)
                /* 578 */ this.info.resetSpecialRank();
            return;
        }
        /* 582 */ long time = System.currentTimeMillis();
        /* 583 */ this.items.setSpecialRankTime(time);
        /* 584 */ this.info.resetSpecialRank();
        /* 585 */ if (sr != null) {
            /* 586 */ GameSetting set = GameSetting.getSetting();
            /* 588 */ for (int l = 0; l < sr.length; l++) {
                /* 589 */ if (sr[l] > 0) {
                    /* 590 */ this.info.overdrawSpecialRank(sr[l]);
                    /* 591 */ if (set.enabledRankEquip())
                        /* 592 */ this.items.addSpecialRank(set.getRank(sr[l]), time);
                }
            }
        }
    }

    public final void processTalkResult(TalkResult r) throws AmpedException {
        /* 600 */ processTalkResult(r, null);
    }

    public ArrayList processTalkResult(TalkResult r, MyRoomData mrd) throws AmpedException {
        /* 604 */ return processTalkResult(r, mrd, null);
    }

    public ArrayList processTalkResult(TalkResult r, MyRoomData mrd, UserTalkFlags oldflags)
            throws AmpedException {
        /* 607 */ int value = this.talkflags.getValue(209);
        /* 609 */ ArrayList<UserItems.Item> overitem = new ArrayList();
        /* 610 */ GameSetting set = GameSetting.getSetting();
        /* 611 */ long time = System.currentTimeMillis();
        /* 614 */ if (r.getscore != 0 && !this.info.isGM())
            /* 615 */ addScore(r.getscore);
        /* 620 */ if (r.getmoney != 0 || r.lostitems != null || r.getitems != null || r.tredetitems != null
                || r.tutorialmyroomidx >= 0) {
            /* 621 */ UserItems ui = this.items;
            /* 622 */ ui.setGameMoney(Math.max(0, ui.getGameMoney() + r.getmoney));
            /* 623 */ if (r.lostitems != null)
                /* 624 */ for (int l = 0; l < r.lostitems.size(); l++) {
                    /* 625 */ if (!ui.removeItem((Item) r.lostitems.get(l)))
                        /* 626 */ throw new AmpedException(240, "used no have item:");
                }
            /* 630 */ if (r.getitems != null)
                /* 631 */ for (int l = 0; l < r.getitems.size(); l++) {
                    /* 632 */ UserItems.Item item = ((UserItems.Item) r.getitems.get(l)).clone();
                    /* 633 */ item.gettime = time;
                    /* 634 */ if (!this.items.isGetable(item)) {
                        /* 634 */ overitem.add(item);
                    } else {
                        /* 635 */ ui.addItem(item, false, true);
                    }
                }
            /* 644 */ if (r.tredetitems != null)
                /* 645 */ for (int l = 0; l < r.tredetitems.size(); l++) {
                    /* 646 */ NPCTradeList.TradeInfo info = (TradeInfo) r.tredetitems.get(l);
                    /* 647 */ NPCTradeList list = (GameSetting.getSetting()).npctrade[info.listid];
                    /* 648 */ UserItems.TerminableItem item = (list.item[info.itemidx]).item.clone();
                    /* 649 */ item.gettype = 9;
                    /* 650 */ item.gettime = time;
                    /* 651 */ item.num = info.num;
                    /* 654 */ if (!list.disabletioverlap || !ui.hasExpireTime(item.kind, item.id)
                            || ui.isExpired(item.kind, item.id))
                        /* 656 */ if (!this.items.isGetable(item)) {
                            /* 656 */ overitem.add(item);
                        } else {
                            /* 657 */ ui.addItem(item, false, false);
                        }
                }
            /* 661 */ if (set.navigation && r.tutorialmyroomidx >= 0) {
                /* 662 */ if (set.isLanguage("kr")) {
                    /* 663 */ if (!this.talkflags.getFlag(36, 0))
                        /* 663 */ throw new AmpedException(240, "invalid flag 36:0");
                    /* 665 */ } else if (this.talkflags.getFlag(11)) {
                    /* 665 */ throw new AmpedException(240, "invalid flag 11");
                }
                /* 667 */ MyRoomTutorialData mrtd = set.getMyRoomTutorialData(1);
                /* 668 */ if (mrtd != null) {
                    /* 669 */ MyRoomTutorialData.MyRoomSet mrs = mrtd.getRoomSet(r.tutorialmyroomidx);
                    /* 670 */ if (mrs == null)
                        /* 670 */ throw new AmpedException(240, "invalid myroomid");
                    /* 672 */ mrs.addItemsTo(ui, 1, (byte) 1, time);
                    /* 673 */ mrs.placeTo(mrd);
                }
            }
        }
        /* 678 */ if (r.getquest != null)
            /* 679 */ for (int l = 0; l < r.getquest.length; l++)
                /* 680 */ this.quest.addChallenging(r.getquest[l], r.getquestkey[l]);
        /* 683 */ if (r.prgquest != null)
            /* 684 */ for (int l = 0; l < r.prgquest.length; l += 2) {
                /* 685 */ UserQuest.Challenging c = this.quest.getChallenging(r.prgquest[l]);
                /* 686 */ if (c != null)
                    /* 686 */ c.progress = r.prgquest[l + 1];
            }
        /* 689 */ if (r.compquest != null)
            /* 690 */ for (int l = 0; l + 1 < r.compquest.length; l += 2) {
                /* 691 */ int qid = r.compquest[l];
                /* 692 */ int check = r.compquest[l + 1];
                /* 694 */ UserQuest.Challenging c = this.quest.getChallenging(qid);
                /* 695 */ if (c == null)
                    /* 695 */ throw new AmpedException(240, "not challenging");
                /* 697 */ int check2 = c.getChecksum();
                /* 698 */ if (Math.abs(check - check2) > 3)
                    /* 698 */ throw new AmpedException(240, "quest check");
                /* 700 */ this.quest.addMaster(qid);
                /* 701 */ this.quest.removeChallenging(qid);
                /* 703 */ QuestData qd = set.getQuest(qid);
                /* 704 */ if (qd.score > 0 && !this.info.isGM())
                    /* 704 */ addScore(qd.score);
                /* 705 */ if (qd.gm > 0)
                    /* 705 */ this.items.setGameMoney(this.items.getGameMoney() + qd.gm);
                /* 706 */ if (qd.present != null && qd.present.length > 0) {
                    /* 707 */ UserItems.Item[] p = UserItems.convertTreasure((UserItems.Item[]) qd.present);
                    /* 708 */ if (p != null)
                        /* 709 */ for (int k = 0; k < p.length; k++) {
                            /* 710 */ if (p[k] != null) {
                                /* 711 */ (p[k]).gettype = 4;
                                /* 712 */ (p[k]).gettime = time;
                                /* 713 */ if (!this.items.isGetable(p[k])) {
                                    /* 713 */ overitem.add(p[k]);
                                } else {
                                    /* 714 */ this.items.addItem(p[k], false, false);
                                }
                            }
                        }
                }
            }
        /* 721 */ if (r.failquest != null)
            /* 722 */ for (int l = 0; l < r.failquest.length; l++)
                /* 723 */ this.quest.removeChallenging(r.failquest[l]);
        /* 728 */ if (r.payAlchemyCost)
            /* 729 */ this.items.setGameMoney(Math.max(0, this.items.getGameMoney() - set.alchemycost));
        /* 733 */ if (r.challenged != null) {
            /* 734 */ UserChallenge.Record[] crec = new UserChallenge.Record[r.challenged.size()];
            /* 735 */ r.challenged.values((Object[]) crec);
            /* 736 */ Integer[] score = new Integer[r.getchallengscore.size()];
            /* 737 */ r.getchallengscore.values((Object[]) score);
            /* 738 */ for (int l = 0; l < crec.length; l++) {
                /* 740 */ if (r.clearchallenge) {
                    /* 741 */ Object[] reward = set.getChallenge((crec[l]).id).getReward(score[l].intValue(),
                            this.challenge.getRecord((crec[l]).id));
                    /* 742 */ int sc = ((Integer) reward[0]).intValue();
                    /* 743 */ int gm = ((Integer) reward[1]).intValue();
                    /* 744 */ UserItems.Item[] item = (UserItems.Item[]) reward[2];
                    /* 745 */ addScore(sc);
                    /* 746 */ this.items.setGameMoney(Math.max(0, this.items.getGameMoney() + gm));
                    /* 747 */ if (item != null)
                        /* 748 */ for (int n = 0; n < item.length; n++) {
                            /* 749 */ UserItems.Item _item = item[n].clone();
                            /* 750 */ _item.gettime = time;
                            /* 751 */ if (!this.items.isGetable(_item)) {
                                /* 751 */ overitem.add(_item);
                            } else {
                                /* 752 */ this.items.addItem(_item);
                            }
                        }
                    /* 756 */ if (set.practiceextensionenabled) {
                        /* 757 */ int[] npc = (set.getChallenge((crec[l]).id)).appearnpc;
                        /* 758 */ if (npc != null)
                            /* 758 */ this.items.addPracticeNpcDay(npc, npc.length);
                    }
                }
                /* 762 */ this.challenge.putRecord(crec[l]);
            }
        }
        /* 766 */ if (r.wantedlevel != -1)
            /* 767 */ this.guardian.wantedlevel = r.wantedlevel;
        /* 770 */ if (r.addepisodelist != null)
            /* 771 */ for (int i = 0; i < r.addepisodelist.length; i++)
                /* 772 */ this.guardian.addEpisode(r.addepisodelist[i]);
        /* 776 */ if (r.deleteepisodelist != null)
            /* 777 */ for (int i = 0; i < r.deleteepisodelist.length; i++)
                /* 778 */ this.guardian.useEpisode(r.deleteepisodelist[i], true);
        /* 783 */ if (set.enabledChallengeRank()) {
            /* 784 */ if (r.getrank != null)
                /* 785 */ for (int l = 0; l < r.getrank.length; l++) {
                    /* 786 */ RankData.Rank rd = set.getRank(r.getrank[l]);
                    /* 788 */ if (!this.items.isChallengeableRank(rd))
                        continue;
                    /* 790 */ if (rd.moneytype == 1) {
                        /* 791 */ if (this.items.getRealMoney() < rd.price)
                            continue;
                        /* 792 */ this.items.addRealMoney(-rd.price);
                    } else {
                        /* 794 */ if (this.items.getGameMoney() < rd.price)
                            continue;
                        /* 795 */ this.items.addGameMoney(-rd.price);
                    }
                    /* 797 */ int prog = 0;
                    /* 798 */ switch ((rd.getGroup()).progtype) {
                        case 4:
                            /* 800 */ prog = this.rec.getMasterStyleNum();
                            break;
                        case 2:
                            /* 803 */ prog = this.relations.getFriendsNum();
                            break;
                    }
                    /* 806 */ this.items.addChallengeRank(rd, prog);
                    continue;
                }
            /* 809 */ if (r.comprank != null) {
                /* 810 */ for (int l = 0; l < r.comprank.length; l++)
                    /* 811 */ this.items.completeChallengeRank(set.getRank(r.comprank[l]), time);
                /* 813 */ this.info.resetNotHavingEquip(this.items);
            }
        }
        /* 817 */ if (r.blacksmith != null && r.blacksmith.length > 2) {
            /* 818 */ int cnt = 0;
            /* 819 */ int event = r.blacksmith[cnt++];
            /* 820 */ UserItems.Item accs = this.items.getItem(5, r.blacksmith[cnt++]);
            /* 821 */ if (accs != null) {
                int sid, charge;
                ItemAdditionalData.Info dsi;
                SkillData t;
                DungeonSkillData dsd;
                int i;
                /* 822 */ boolean ng = false;
                /* 824 */ ItemAdditionalData dpData = this.items.getItemAdditionalData();
                /* 827 */ int[] skill = new int[3];
                /* 828 */ ArrayList<UserItems.Item> use = new ArrayList();
                /* 830 */ switch (event) {
                    case 1:
                        /* 832 */ for (; cnt < r.blacksmith.length; cnt++) {
                            /* 833 */ SkillData sd = set.getSkill(r.blacksmith[cnt]);
                            /* 834 */ if (sd instanceof DungeonSkillData && !sd.isPrivately()) {
                                /* 836 */ DungeonSkillData dungeonSkillData = (DungeonSkillData) sd;
                                /* 837 */ UserItems.Item[] req = dungeonSkillData.materials;
                                /* 838 */ if (req != null)
                                    /* 841 */ if (DungeonSkillData.grantable(accs, dungeonSkillData))
                                        /* 844 */ if (dpData == null || !dpData.isLimitAccsNum(sd)) {
                                            /* 846 */ skill[dungeonSkillData.type] = r.blacksmith[cnt];
                                            /* 849 */ for (int j = 0; j < req.length; j++) {
                                                /* 850 */ UserItems.Item add = req[j];
                                                /* 851 */ int k = 0;
                                                /* 852 */ for (; k < use.size(); k++) {
                                                    /* 853 */ UserItems.Item item = use.get(k);
                                                    /* 854 */ if (item.id == add.id && item.kind == add.kind) {
                                                        /* 855 */ item.num = (short) (item.num + add.num);
                                                        break;
                                                    }
                                                }
                                                /* 859 */ if (k == use.size())
                                                    /* 859 */ use.add(
                                                            new UserItems.Item(add.kind, add.id, add.num, 0, 0L, 0));
                                            }
                                        }
                            }
                        }
                        break;
                    case 2:
                    case 3:
                        /* 865 */ if (dpData == null) {
                            /* 865 */ ng = true;
                            break;
                        }
                        /* 866 */ sid = r.blacksmith[cnt++];
                        /* 867 */ charge = r.blacksmith[cnt++];
                        /* 869 */ dsi = dpData.getSkillInfo_Skill(accs.id, sid);
                        /* 870 */ if (dsi == null) {
                            /* 870 */ ng = true;
                            break;
                        }
                        /* 871 */ t = set.getSkill(sid);
                        /* 872 */ if (!(t instanceof DungeonSkillData)) {
                            /* 872 */ ng = true;
                            break;
                        }
                        /* 873 */ dsd = (DungeonSkillData) t;
                        /* 875 */ switch (dsd.valueType) {
                            case 1:
                                /* 877 */ charge = Math.min(charge, dsi.getValueMax() - dsi.getValue());
                                break;
                            case 2:
                                /* 880 */ charge = Math.min(charge, 999);
                                break;
                            default:
                                /* 883 */ ng = true;
                                break;
                        }
                        /* 886 */ if (ng || charge < 1) {
                            /* 886 */ ng = true;
                            break;
                        }
                        /* 888 */ for (i = 0; i < dsd.matvalue.length; i++) {
                            /* 889 */ if (dsd.matvalue[i] != null) {
                                /* 890 */ UserItems.Item add = dsd.matvalue[i];
                                /* 891 */ use.add(new UserItems.Item(add.kind, add.id, add.num * charge, 0, 0L, 0));
                            }
                        }
                        /* 893 */ skill[0] = sid;
                        /* 894 */ skill[1] = charge;
                        break;
                    default:
                        /* 897 */ ng = true;
                        break;
                }
                int l;
                /* 902 */ for (l = 0; !ng && l < use.size(); l++) {
                    /* 903 */ UserItems.Item u = use.get(l);
                    /* 904 */ UserItems.Item item1 = this.items.getItem(u.kind, u.id);
                    /* 905 */ if (item1 == null || item1.num < u.num) {
                        /* 905 */ ng = true;
                        break;
                    }
                }
                /* 909 */ if (!ng) {
                    DungeonSkillData dungeonSkillData;
                    int limit;
                    /* 911 */ for (l = 0; l < use.size(); l++)
                        /* 912 */ this.items.removeItem(use.get(l));
                    /* 915 */ switch (event) {
                        case 1:
                            /* 918 */ for (l = 0; l < 3; l++) {
                                /* 919 */ if (skill[l] > 0)
                                    /* 919 */ accs.setTuneUp(l, skill[l]);
                            }
                            /* 922 */ if (accs.id == this.info.accs) {
                                /* 923 */ this.info.skill1 = (short) accs.getTuneUp(0);
                                /* 924 */ this.info.skill2 = (short) accs.getTuneUp(1);
                                /* 925 */ this.info.skill3 = (short) accs.getTuneUp(2);
                            }
                            /* 929 */ if (dpData == null) {
                                /* 930 */ dpData = new ItemAdditionalData();
                                /* 931 */ dpData.updateSkillInfo(accs);
                                /* 932 */ this.items.setItemAdditionalData(dpData);
                                break;
                            }
                            /* 934 */ dpData.updateSkillInfo(accs);
                            break;
                        case 2:
                            /* 939 */ dpData.getSkillInfo_Skill(accs.id, skill[0]).addValue(skill[1]);
                            break;
                        case 3:
                            /* 943 */ dungeonSkillData = (DungeonSkillData) set.getSkill(skill[0]);
                            /* 944 */ limit = dungeonSkillData.value * skill[1];
                            /* 945 */ dpData.getSkillInfo_Skill(accs.id, skill[0]).decValueMax(-limit, 0);
                            break;
                    }
                }
            }
        }
        /* 947 */ if (r

                /* 953 */ .flags != null && !r.flags.isEmpty())
            /* 954 */ if (r.flags.isValid(this.talkflags)) {
                /* 955 */ r.flags.setTo(this.talkflags);
            } else {
                /* 957 */ throw new AmpedException(240, "invalid talkflags update");
            }
        /* 962 */ if (r.getstreetrank && set.enabledRankEquip()) {
            /* 963 */ RankData.Rank sr = set.rank.getStreetFightRank(this.talkflags);
            /* 964 */ boolean b = this.items.addStreetfightRank(sr, ServerTime.getTime());
            /* 966 */ if (b && this.info.specialrank != 0) {
                /* 967 */ RankData.Rank cr = set.getRank(this.info.specialrank);
                /* 968 */ if (cr == null || cr.isPrivately() || cr.isKind(4))
                    /* 969 */ this.info.specialrank = sr.idx;
            }
        }
        /* 974 */ if (r.paramflags != null)
            /* 975 */ this.items.setParamValue(r.paramflags);
        /* 979 */ if (r.happysetversion != -1)
            /* 980 */ this.items.putParam(2000, new Integer(r.happysetversion));
        /* 983 */ if (r.eventtreasurelist != null)
            /* 984 */ for (int i = 0; i < r.eventtreasurelist.length; i++) {
                /* 985 */ if (oldflags != null && !isGetEventTreasure(r.eventid, oldflags)) {
                    /* 987 */ System.out
                            .println("UserData isEventTreasure failed. userid:" + this.userid + " eventid:" + r.eventid
                                    + " userrank:" + ((set.rank != null) ? set.rank.rankIndexOf(this.info.score) : -1)
                                    + " eventrank:" + set.getEventRank(r.eventid));
                } else {
                    /* 990 */ UserItems.Item[] ep = set.getEventPresent(r.eventid, r.eventtreasurelist[i]);
                    /* 991 */ for (int j = 0; j < ep.length; j++) {
                        /* 992 */ if (ep[j] != null) {
                            /* 993 */ UserItems.Item t = ep[j].clone();
                            /* 994 */ t.gettime = time;
                            /* 995 */ if (!this.items.isGetable(t)) {
                                /* 995 */ overitem.add(t);
                            } else {
                                /* 996 */ this.items.addItem(t);
                            }
                        }
                    }
                }
            }
        /* 1002 */ if (r.mixstock != null)
            /* 1003 */ this.items.putParam(18, r.mixstock);
        /* 1006 */ if (r.npctreasurelist != null)
            /* 1007 */ for (int i = 0; i < r.npctreasurelist.length; i++) {
                /* 1008 */ UserItems.Item[] np = set.getNpcPresent(r.npcid, r.npctreasurelist[i]);
                /* 1009 */ for (int j = 0; j < np.length; j++) {
                    /* 1010 */ if (np[j] != null) {
                        /* 1011 */ UserItems.Item t = np[j].clone();
                        /* 1012 */ t.gettime = time;
                        /* 1013 */ if (!this.items.isGetable(t)) {
                            /* 1013 */ overitem.add(t);
                        } else {
                            /* 1014 */ this.items.addItem(t);
                        }
                    }
                }
            }
        /* 1020 */ if (r.questtreasurelist != null)
            /* 1021 */ for (int i = 0; i < r.questtreasurelist.length; i++) {
                /* 1022 */ UserItems.Item[] qp = set.getQuestPresent(r.questid, r.questtreasurelist[i]);
                /* 1023 */ for (int j = 0; j < qp.length; j++) {
                    /* 1024 */ if (qp[j] != null) {
                        /* 1025 */ UserItems.Item t = qp[j].clone();
                        /* 1026 */ t.gettime = time;
                        /* 1027 */ if (!this.items.isGetable(t)) {
                            /* 1027 */ overitem.add(t);
                        } else {
                            /* 1028 */ this.items.addItem(t);
                        }
                    }
                }
            }
        /* 1034 */ if (r.questionarytreasurelist != null)
            /* 1035 */ for (int i = 0; i < r.questionarytreasurelist.length; i++) {
                /* 1036 */ UserItems.Item[] qp = set.getQuestionaryPresent(r.questionarytreasurelist[i]);
                /* 1037 */ for (int j = 0; j < qp.length; j++) {
                    /* 1038 */ if (qp[j] != null) {
                        /* 1039 */ UserItems.Item t = qp[j].clone();
                        /* 1040 */ t.gettime = time;
                        /* 1041 */ if (!this.items.isGetable(t)) {
                            /* 1041 */ overitem.add(t);
                        } else {
                            /* 1042 */ this.items.addItem(t);
                        }
                    }
                }
            }
        /* 1048 */ if (r.eventequipid != -1) {
            /* 1049 */ UserItems.Item[] t = set.getEventPresent(r.eventid, r.eventequipid);
            /* 1050 */ for (int l = 0; l < t.length; l++) {
                /* 1051 */ if (t[l] != null && !set.isPrivately((t[l]).kind, (t[l]).id) &&
                /* 1052 */ StyleData.isWearable(this.info.style, (t[l]).kind, (t[l]).id))
                    /* 1053 */ switch ((t[l]).kind) {
                        case 5:
                            /* 1054 */ this.info.accs = (short) (t[l]).id;
                            break;
                        case 1:
                            /* 1055 */ this.info.head = (short) (t[l]).id;
                            break;
                        case 2:
                            /* 1056 */ this.info.face = (short) (t[l]).id;
                            break;
                        case 3:
                            /* 1057 */ this.info.body = (short) (t[l]).id;
                            break;
                        case 4:
                            /* 1058 */ this.info.deco = (short) (t[l]).id;
                            break;
                        case 7:
                            /* 1059 */ this.info.weapon = (short) (t[l]).id;
                            break;
                    }
            }
        }
        /* 1064 */ return overitem;
    }

    public boolean isGetEventTreasure(int eventid, UserTalkFlags oldflag) {
        /* 1069 */ GameSetting set = GameSetting.getSetting();
        /* 1070 */ int rank = set.getEventRank(eventid);
        /* 1071 */ int myrank = set.rank.rankIndexOf(this.info.score);
        /* 1072 */ if (rank != -1 && rank > myrank)
            /* 1072 */ return false;
        /* 1073 */ int talkflag = set.getEventTalkflag(eventid);
        /* 1074 */ int value = oldflag.getValue(talkflag);
        /* 1076 */ if (talkflag != -1) {
            boolean flag2;
            int cnt;
            int id;
            int max;
            int i;
            /* 1077 */ switch (talkflag) {
                case 201:
                    /* 1079 */ switch (value) {
                        case 1:
                            /* 1081 */ if (rank != 6)
                                /* 1081 */ return false;
                            break;
                        case 2:
                            /* 1083 */ if (rank != 9)
                                /* 1083 */ return false;
                            break;
                        case 3:
                            /* 1085 */ if (rank != 11)
                                /* 1085 */ return false;
                            break;
                        case 4:
                            /* 1087 */ if (rank != 12)
                                /* 1087 */ return false;
                            break;
                        case 5:
                            /* 1089 */ if (rank != 14)
                                /* 1089 */ return false;
                            break;
                        case 6:
                            /* 1091 */ if (rank != 16)
                                /* 1091 */ return false;
                            break;
                        case 7:
                            /* 1093 */ if (rank != 19)
                                /* 1093 */ return false;
                            break;
                        case 8:
                            /* 1095 */ if (rank != 20)
                                /* 1095 */ return false;
                            break;
                        case 9:
                            /* 1097 */ if (rank != 24)
                                /* 1097 */ return false;
                            break;
                        case 10:
                            /* 1099 */ if (rank != 28)
                                /* 1099 */ return false;
                            break;
                        case 11:
                            /* 1101 */ if (rank != 32)
                                /* 1101 */ return false;
                            break;
                        case 12:
                            /* 1103 */ if (rank != 36)
                                /* 1103 */ return false;
                            break;
                        case 13:
                            /* 1105 */ if (rank != 45)
                                /* 1105 */ return false;
                            break;
                    }
                    break;
                case 204:
                    /* 1109 */ flag2 = oldflag.getFlag(talkflag, 1);
                    /* 1111 */ if (flag2)
                        /* 1111 */ return false;
                case 209:
                    /* 1114 */ if (!set.everyrankupeventenabled)
                        /* 1114 */ return false;
                    /* 1115 */ cnt = 0;
                    /* 1116 */ id = 150 + value - 1;
                    /* 1117 */ max = 198;
                    /* 1118 */ for (i = id; i <= max; i++) {
                        /* 1119 */ if (set.isDisableEvent(i)) {
                            /* 1120 */ cnt++;
                            /* 1122 */ } else if (rank < value + cnt) {
                            /* 1122 */ return false;
                        }
                    }
                    break;
            }
        }
        /* 1128 */ return true;
    }

    public UseItemInfo processUseItem(UseItemInfo i, boolean chserver) throws Exception {
        long time;
        int tkey, bkey;
        EventItem ei;
        int tval, bval;
        /* 1132 */ GameSetting set = GameSetting.getSetting();
        /* 1135 */ if (i.type == 2) {
            /* 1137 */ if (chserver)
                /* 1137 */ throw new AmpedException(240, "stage server only");
            /* 1138 */ int t = 0;
            /* 1139 */ switch (i.kind) {
                case 22:
                    /* 1140 */ t = this.items.getGameMoney();
                    break;
                case 23:
                    /* 1141 */ t = this.items.getRealMoney();
                    break;
                default:
                    /* 1142 */ throw new AmpedException(240, "not money");
            }
            /* 1144 */ if (t < i.id)
                /* 1144 */ throw new AmpedException(240, "not enough money");
        } else {
            /* 1146 */ if (i.type == 1 && (i.kind != 15 || i.num != 1))
                /* 1147 */ throw new AmpedException(240, "not boost item");
            /* 1149 */ if (set.isPrivately(i.kind, i.id))
                /* 1149 */ throw new AmpedException(240, "privately item");
            /* 1150 */ if (this.items.hasItems(i.kind, i.id) < i.num)
                /* 1150 */ throw new AmpedException(240, "not have item");
        }
        /* 1153 */ UseItemInfo ret = i;
        /* 1156 */ if (chserver) {
            /* 1157 */ time = System.currentTimeMillis();
            /* 1158 */ ret = i.clone();
            /* 1159 */ ret.proctime = time;
        } else {
            /* 1161 */ time = i.proctime;
        }
        /* 1164 */ switch (i.type) {
            case 0:
                /* 1166 */ this.items.removeItem(i.kind, i.id, i.num);
                /* 1215 */ return ret;
            case 1:
                switch (i.id) {
                    case 29:
                        tkey = 13;
                        bkey = 14;
                        break;
                    case 30:
                        tkey = 15;
                        bkey = 16;
                        break;
                    case 31:
                        tkey = 17;
                        bkey = 18;
                        break;
                    case 36:
                        tkey = 31;
                        bkey = 32;
                        break;
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                        tkey = 33;
                        bkey = 34;
                        break;
                    default:
                        throw new AmpedException(240, "not boost item");
                }
                ei = GameSetting.getSetting().getEventItem(i.id);
                if (KotoriUtil.argc(ei.arg) == 3) {
                    tval = (int) (time / 60000L) + KotoriUtil.argi(ei.arg, 0) * 60;
                    bval = getBoostBonus(KotoriUtil.argi(ei.arg, 1), this.talkflags.getValue(bkey),
                            KotoriUtil.argi(ei.arg, 2), getBoostTime(i.id));
                } else if (KotoriUtil.argc(ei.arg) == 2) {
                    bval = KotoriUtil.argi(ei.arg, 1);
                    if (this.talkflags.getValue(bkey) != bval) {
                        tval = getBoostTargetTime(time, 0, KotoriUtil.argi(ei.arg, 0), 0);
                    } else {
                        tval = getBoostTargetTime(time, this.talkflags.getValue(tkey),
                                KotoriUtil.argi(ei.arg, 0), 0);
                    }
                } else {
                    tval = getBoostTargetTime(time, this.talkflags.getValue(tkey));
                    bval = KotoriUtil.argi(ei.arg, 0);
                }
                this.talkflags.setValue(tkey, tval);
                this.talkflags.setValue(bkey, bval);
                this.items.removeItem(i.kind, i.id, i.num);
                /* 1215 */ return ret;
            case 2:
                switch (i.kind) {
                    case 22:
                        this.items.addGameMoney(-i.id);
                        break;
                    case 23:
                        this.items.addRealMoney(-i.id);
                        break;
                }
                /* 1215 */ return ret;
        }
        throw new AmpedException(240, "invalid type");
    }

    private int getBoostTargetTime(long time, int curTarget, int addtime, int maxtime) {
        /* 1219 */ int target, now = (int) (time / 60000L);
        /* 1221 */ if (maxtime > 0 && curTarget + 60 * addtime > now + 60 * maxtime) {
            /* 1222 */ target = now + 60 * maxtime;
            /* 1223 */ } else if (curTarget < now) {
            /* 1224 */ target = now + 60 * addtime;
        } else {
            /* 1226 */ target = curTarget + 60 * addtime;
        }
        /* 1228 */ return target;
    }

    private int getBoostTargetTime(long time, int curTarget) {
        /* 1232 */ return getBoostTargetTime(time, curTarget, 2, 12);
    }

    private int getBoostBonus(int _bonus, int curBonus, int maxBonus, long remainingTime) {
        int bonus;
        /* 1237 */ if (remainingTime <= 0L) {
            /* 1238 */ bonus = _bonus;
            /* 1239 */ } else if (curBonus + _bonus > maxBonus - _bonus) {
            /* 1240 */ bonus = maxBonus - _bonus;
            /* 1241 */ } else if (curBonus < _bonus) {
            /* 1242 */ bonus = _bonus;
        } else {
            /* 1244 */ bonus = curBonus + _bonus;
        }
        /* 1246 */ return bonus;
    }

    public long getBoostTime(int itemid) {
        /* 1250 */ if (this.talkflags != null) {
            /* 1251 */ long currenttime = ServerTime.getTime();
            /* 1252 */ switch (itemid) {
                case 29:
                    /* 1254 */ return this.talkflags.getValue(13) - currenttime / 60000L;
                case 30:
                    /* 1256 */ return this.talkflags.getValue(15) - currenttime / 60000L;
                case 31:
                    /* 1258 */ return this.talkflags.getValue(17) - currenttime / 60000L;
                case 36:
                    /* 1260 */ return this.talkflags.getValue(31) - currenttime / 60000L;
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                    /* 1265 */ return this.talkflags.getValue(33) - currenttime / 60000L;
            }
        }
        /* 1268 */ return 0L;
    }

    public void decWeapon(int num) {
        /* 1272 */ if (this.info.weapon == 0)
            return;
        /* 1273 */ this.items.removeItem(7, this.info.weapon, num);
        /* 1274 */ UserItems.Item i = this.items.getItem(7, this.info.weapon);
        /* 1275 */ if (i == null || i.num <= 0)
            /* 1276 */ this.info.weapon = 0;
    }

    public void decItems(int kind, int id, int num) {
        /* 1281 */ if (num <= 0)
            return;
        /* 1282 */ this.items.removeItem(kind, id, num);
    }

    public static int getRestrictFlag(String label) {
        /* 1289 */ if (label.equals("login"))
            /* 1289 */ return 1;
        /* 1290 */ if (label.equals("lchat"))
            /* 1290 */ return 2;
        /* 1291 */ if (label.equals("rchat"))
            /* 1291 */ return 4;
        /* 1292 */ if (label.equals("gchat"))
            /* 1292 */ return 8;
        /* 1293 */ if (label.equals("mchat"))
            /* 1293 */ return 16;
        /* 1294 */ if (label.equals("enter"))
            /* 1294 */ return 32;
        /* 1295 */ if (label.equals("trade"))
            /* 1295 */ return 64;
        /* 1296 */ if (label.equals("skin"))
            /* 1296 */ return 128;
        /* 1297 */ if (label.equals("skinbbs"))
            /* 1297 */ return 256;
        /* 1298 */ if (label.equals("bbs"))
            /* 1298 */ return 512;
        /* 1299 */ if (label.equals("mail"))
            /* 1299 */ return 1024;
        /* 1300 */ if (label.equals("vchat"))
            /* 1300 */ return 2048;
        /* 1301 */ return 0;
    }

    public boolean isChatRestrict(StageConnector con) {
        /* 1309 */ if (con == null || con.getStage() == null)
            /* 1310 */ return false;
        /* 1313 */ int mode = con.getStage().getStageMode();
        /* 1314 */ if (mode == 14) {
            /* 1315 */ if ((this.restrict & 0x10) != 0)
                /* 1316 */ return true;
            /* 1318 */ } else if (con.getStage().isMachingRoomMode()) {
            /* 1319 */ if ((this.restrict & 0x4) != 0)
                /* 1320 */ return true;
            /* 1322 */ } else if (con.getStage().isLoungeMode()) {
            /* 1323 */ if ((this.restrict & 0x2) != 0)
                /* 1324 */ return true;
            /* 1326 */ } else if ((this.restrict & 0x8) != 0) {
            /* 1327 */ return true;
        }
        /* 1329 */ return false;
    }

    public boolean isVoiceChatRestrict() {
        /* 1336 */ return ((this.restrict & 0x800) != 0);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        /* 1350 */ out.writeByte(this.version);
        /* 1351 */ out.writeObject(this.userid);
        /* 1352 */ out.writeObject(this.password);
        /* 1353 */ out.writeObject(this.info);
        /* 1354 */ out.writeObject(this.items);
        /* 1355 */ out.writeObject(this.quest);
        /* 1356 */ out.writeObject(this.shortcuts);
        /* 1357 */ out.writeObject(this.relations);
        /* 1358 */ out.writeObject(this.guardian);
        /* 1359 */ out.writeObject(this.talkflags);
        /* 1360 */ out.writeObject(this.challenge);
        /* 1361 */ out.writeByte((this.firstlogin ? 1 : 0) | (this.applingAdult ? 2 : 0));
        /* 1362 */ out.writeShort(this.restrict);
        /* 1363 */ out.writeObject(this.rec);
        /* 1364 */ StreamTool.writePackedInt(this.logintimes, out);
        /* 1365 */ out.writeByte(this.sex);
        /* 1366 */ if (!this.voteskin.doVote()) {
            /* 1367 */ out.writeByte(0);
        } else {
            /* 1369 */ out.writeByte(1);
            /* 1370 */ out.writeObject(this.voteskin);
        }
        /* 1372 */ out.writeByte(2);
        /* 1373 */ out.writeObject(this.duel);
        /* 1374 */ out.writeInt(this.areahash);
        /* 1375 */ out.writeInt(0);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        /* 1383 */ this.version = in.readByte();
        /* 1384 */ this.userid = (String) in.readObject();
        /* 1385 */ this.password = (String) in.readObject();
        /* 1386 */ this.info = (UserInfo) in.readObject();
        /* 1387 */ Object o = null;
        /* 1388 */ for (; o == null; o = in.readObject())
            ;
        /* 1389 */ this.items = (UserItems) o;
        /* 1391 */ this.quest = (UserQuest) in.readObject();
        /* 1392 */ this.shortcuts = (UserShortcuts) in.readObject();
        /* 1393 */ this.relations = (UserRelations) in.readObject();
        /* 1394 */ this.guardian = (UserGuardianData) in.readObject();
        /* 1395 */ this.talkflags = (UserTalkFlags) in.readObject();
        /* 1396 */ this.challenge = (UserChallenge) in.readObject();
        /* 1397 */ byte b = in.readByte();
        /* 1398 */ this.firstlogin = ((b & 0x1) != 0);
        /* 1399 */ this.applingAdult = ((b & 0x2) != 0);
        /* 1400 */ this.restrict = in.readShort();
        /* 1401 */ this.rec = (UserRecords) in.readObject();
        /* 1402 */ if (this.rec == null)
            /* 1402 */ this.rec = new UserRecords();
        /* 1403 */ this.logintimes = StreamTool.readPackedInt(in);
        /* 1404 */ this.sex = in.readByte();
        /* 1405 */ int i = in.readByte();
        /* 1406 */ if (i == 0) {
            /* 1407 */ this.voteskin = new VoteRecords();
        } else {
            /* 1409 */ this.voteskin = (VoteRecords) in.readObject();
            /* 1410 */ this.voteskin.reset();
        }
        /* 1412 */ i = in.readByte() & 0xFF;
        /* 1413 */ if (i >= 1)
            /* 1414 */ this.duel = (UserDuelData) in.readObject();
        /* 1416 */ if (this.duel == null)
            /* 1416 */ this.duel = new UserDuelData();
        /* 1417 */ if (i >= 2) {
            /* 1418 */ this.areahash = in.readInt();
            /* 1419 */ in.readInt();
        }
    }

    public static void main(String[] args) throws Exception {
        /* 1426 */ Externalizer.setDefault(AmpedExternalizer.getExternalizer());
        /* 1428 */ ExternalizerInputStream eis = new ExternalizerInputStream(new FileInputStream(args[0]));
        /* 1429 */ Object o = eis.readObject();
        /* 1430 */ System.out.println(" " + o);
    }
}

/*
 * Location:
 * C:\Users\Nekosea\Desktop\ga2class\tw\classes\!\ga2\data\UserData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */

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

    public byte version = 0;

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
        if (arg instanceof Object[]) {
            this.HWID = (String) KotoriUtil.argv(arg, 1);
        } else if (arg instanceof String) {
            this.HWID = (String) arg;
        }
    }

    public Object clone() {
        UserData d = new UserData();
        d.version = this.version;
        d.userid = this.userid;
        d.password = this.password;
        d.info = (this.info == null) ? null : this.info.clone();
        d.items = (this.items == null) ? null : this.items.clone();
        d.quest = (this.quest == null) ? null : (UserQuest) this.quest.clone();
        d.shortcuts = (this.shortcuts == null) ? null : (UserShortcuts) this.shortcuts.clone();
        d.relations = (this.relations == null) ? null : (UserRelations) this.relations.clone();
        d.talkflags = (this.talkflags == null) ? null : this.talkflags.clone();
        d.navi = (this.navi == null) ? null : (UserNavigation) this.navi.clone();
        d.guardian = (this.guardian == null) ? null : (UserGuardianData) this.guardian.clone();
        d.challenge = (this.challenge == null) ? null : this.challenge.clone();
        d.rec = (this.rec == null) ? null : (UserRecords) this.rec.clone();
        d.voteskin = (this.voteskin == null) ? null : (VoteRecords) this.voteskin.clone();
        d.duel = (this.duel == null) ? null : (UserDuelData) this.duel.clone();
        d.firstlogin = this.firstlogin;
        d.restrict = this.restrict;
        d.logintimes = this.logintimes;
        d.sex = this.sex;
        d.areahash = this.areahash;
        d = com.nr.Utils.LoadShortCuts(d);
        return d;
    }

    public static UserData getTest() {
        return getTest("_test");
    }

    public static UserData getTest(String uid) {
        GameSetting set = GameSetting.getSetting();
        UserData d = new UserData();
        d.userid = uid;
        d.info = new UserInfo();
        d.info.set(set.getNPCInfo((int) (Math.random() * 19.0D)));
        d.quest = new UserQuest();
        d.shortcuts = new UserShortcuts();
        d.shortcuts.emotionsc = getDefaultEmotions();
        d.relations = new UserRelations();
        d.guardian = new UserGuardianData();
        d.talkflags = new UserTalkFlags();
        d.challenge = new UserChallenge();
        d.restrict = 0;
        d.rec = new UserRecords();
        d.voteskin = new VoteRecords();
        d.duel = new UserDuelData();
        UserQuest userquest = new UserQuest();
        d.quest = userquest;
        if (uid.startsWith("_")) {
            if (ServerSetting.getSetting() != null && (ServerSetting.getSetting()).lanserver) {
                d.items = UserItems.getLanItems((set.version < 0));
                d.addLanEquipItems();
            } else {
                d.items = UserItems.getFullItems((set.version < 0));
            }
            d.setTestFlags(d.talkflags);
            d.info.score = (10000000 + (int) (Math.random() * 5000.0D));
            GuardianData gd = set.getGuardian();
            d.guardian.wantedlevel = gd.wanted.length - 1;
            int[] w = gd.getWantedList(d.guardian.wantedlevel);
            for (int l = 0; l < w.length;) {
                d.guardian.addBustered(w[l]);
                l++;
            }
            d.rec.masterAllStyles();
        } else {
            d.items = set.getInitialItems();
        }
        return d;
    }

    static int[] getDefaultEmotions() {
        UserShortcuts.ChatShortcut[] cs = (GameSetting.getSetting()).shortcut;
        if (cs != null && cs.length > 0) {
            int[] list = new int[cs.length];
            int count = 0;
            for (int l = 0; l < cs.length; l++) {
                if ((cs[l]).emo != 0) {
                    int k = 0;
                    for (; k < count && (cs[l]).emo != list[k]; k++)
                        ;
                    if (k == count)
                        list[count++] = (cs[l]).emo;
                }
            }
            if (count > 0) {
                for (int i = 0; i < count - 1; i++) {
                    boolean flg = false;
                    for (int k = 0; k < count - i - 1; k++) {
                        int a = Math.abs(list[k]);
                        int b = Math.abs(list[k + 1]);
                        if (a > b || (a == b && list[k] < list[k + 1])) {
                            flg = true;
                            int t = list[k];
                            list[k] = list[k + 1];
                            list[k + 1] = t;
                        }
                    }
                    if (!flg)
                        break;
                }
                int[] emo = new int[Math.max(count, 6)];
                for (int j = 0; j < count; j++)
                    emo[j] = list[j];
                return emo;
            }
        }
        return new int[] { 1, 2, 3, 4 };
    }

    private int getBonus(int kind) {
        if (this.talkflags == null)
            return 0;
        long now = ServerTime.getTime() / 60000L;
        long limit = this.talkflags.getValue(kind * 2 + 0);
        if (limit > now + 150L) {
            limit = now + 120L;
            this.talkflags.setValue(kind * 2 + 0, (int) limit);
        }
        if (now < limit)
            return this.talkflags.getValue(kind * 2 + 1);
        return 0;
    }

    private int getBoost(int kind) {
        if (this.talkflags == null)
            return 0;
        long now = ServerTime.getTime() / 60000L;
        long limit = this.talkflags.getValue(kind * 2 + 13);
        if (now < limit)
            return this.talkflags.getValue(kind * 2 + 14);
        return 0;
    }

    public int getBonusScore() {
        int bonus = getBonus(0) + StageServer.BONUS_SCORE + getBoost(0);
        return KMath.clamp(bonus, -90, 200);
    }

    public int getBonusWin() {
        int bonus = getBonus(1) + StageServer.BONUS_GM + getBoost(1);
        return KMath.clamp(bonus, -90, 200);
    }

    public int getBonusSkill() {
        int bonus = getBonus(2) + StageServer.BONUS_SKILL + getBoost(2);
        return KMath.clamp(bonus, 0, 2);
    }

    public int getEventItemBonus() {
        return getBoost(9);
    }

    public int getItemBonus() {
        return getBoost(10);
    }

    public boolean addScore(long add) {
        long oldscore = this.info.score;
        long newscore = this.info.score += add;
        GameSetting set = GameSetting.getSetting();
        if (!set.enabledRankEquip())
            return false;
        int oldrank = set.rank.rankIndexOf(oldscore);
        int newrank = set.rank.rankIndexOf(newscore);
        if (oldrank == newrank)
            return false;
        long time = System.currentTimeMillis();
        for (int l = oldrank + 1; l <= newrank; l++)
            this.items.addScoreRank(set.getRank(l), time);
        this.items.addScoreRank(set.getRank(newrank + 1), 0L);
        return true;
    }

    public void addEquipItems() {
        try {
            _addItemWarn(_i((byte) 0, this.info.style));
            if (this.info.head != 0)
                _addItemWarn(_i((byte) 1, this.info.head));
            _addItemWarn(_i((byte) 2, this.info.face));
            _addItemWarn(_i((byte) 3, this.info.body));
            if (this.info.deco != 0)
                _addItemWarn(_i((byte) 4, this.info.deco));
            if (this.info.accs != 0)
                _addItemWarn(_i((byte) 5, this.info.accs));
            for (int l = 0; l < this.shortcuts.emotionsc.length; l++) {
                if (this.shortcuts.emotionsc[l] != 0) {
                    int id = this.shortcuts.emotionsc[l];
                    byte kind = (byte) ((id > 0) ? 8 : 18);
                    _addItemWarn(_i(kind, (id > 0) ? id : -id));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addInitialSkinItems(int[] skinids) {
        try {
            for (int l = 0; l < skinids.length; l++)
                _addItemWarn(_i((byte) 9, -skinids[l]));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addLanEquipItems() {
        try {
            _addItemWarn(_i((byte) 0, this.info.style));
            for (int l = 0; l < this.shortcuts.emotionsc.length; l++) {
                if (this.shortcuts.emotionsc[l] != 0) {
                    int id = this.shortcuts.emotionsc[l];
                    byte kind = (byte) ((id > 0) ? 8 : 18);
                    _addItemWarn(_i(kind, (id > 0) ? id : -id));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setTestFlags(UserTalkFlags f) {
        f.setFlag(11, true);
        f.setFlag(10, true);
        f.setFlag(20, true);
        f.setFlag(21, true);
        f.setFlag(22, true);
        f.setFlag(23, true);
        f.setFlag(24, true);
        f.setFlag(25, true);
        f.setFlag(26, true);
        f.setFlag(27, true);
        f.setFlag(28, true);
        f.setFlag(200, true);
        f.setValue(201, 7);
        f.setFlag(300, true);
        f.setValue(301, 1500);
        f.setFlag(302, true);
        f.setFlag(304, true);
        f.setFlag(305, true);
        f.setFlag(400, true);
        f.setFlag(457, true);
        f.setFlag(500, true);
        f.setValue(513, -1);
        f.setFlag(700, true);
        f.setValue(701, 1);
        f.setFlag(702, true);
    }

    private void _addItemWarn(UserItems.Item i) {
        try {
            this.items.addItem(i);
        } catch (Exception ex) {
            System.out.println("_addItemWarn " + i.kind + ", " + i.id + " : " + ex);
        }
    }

    private UserItems.Item _i(byte kind, int id) {
        UserItems.Item i = new UserItems.Item(kind, id);
        i.gettype = 1;
        return i;
    }

    public void checkRankItemData() {
        GameSetting set = GameSetting.getSetting();
        if (!set.specialrankenabled || (this.info.specialrank != 0 && set.enabledRankEquip()
                && !this.items.isEquipableRank(this.info.specialrank, this.info.isGM())))
            this.info.specialrank = 0;
        int rank = set.rank.rankIndexOf(this.info.score);
        long time = System.currentTimeMillis();
        for (int l = 0; l < set.rank.rank.length; l++) {
            RankData.Rank rank1 = set.rank.rank[l];
            if (rank1 != null && !rank1.isPrivately() && rank1.isKind(0))
                if (this.info.score >= rank1.score) {
                    UserItems.Item i = this.items.getItem(17, rank1.idx);
                    if (!this.items.hasItem(17, rank1.idx)) {
                        this.items.addScoreRank(rank1, time);
                    } else if (i != null && i.gettime == 0L) {
                        this.items.addScoreRank(rank1, time);
                    }
                } else if (l == rank + 1) {
                    this.items.addScoreRank(rank1, 0L);
                    break;
                }
        }
        RankData.Rank r = (GameSetting.getSetting()).rank.getStreetFightRank(this.talkflags);
        if (r != null && !r.isPrivately() && r.isKind(4) &&
                !this.items.hasItem(17, r.idx))
            this.items.addStreetfightRank(r, time);
    }

    public static UserData getEmpty() {
        UserData d = new UserData();
        d.info = UserInfo.getTest();
        d.items = new UserItems();
        d.items.setGameMoney(0);
        d.items.setRealMoney(0);
        d.quest = new UserQuest();
        d.shortcuts = new UserShortcuts();
        d.shortcuts.emotionsc = getDefaultEmotions();
        d.guardian = new UserGuardianData();
        d.relations = new UserRelations();
        d.rec = new UserRecords();
        d.voteskin = new VoteRecords();
        d.duel = new UserDuelData();
        return d;
    }

    public static UserData getInitial(String uid, String pw, int sex, String params) {
        UserData d = new UserData();
        d.userid = uid;
        d.info = new UserInfo();
        d.info.set(GameSetting.getSetting().getNPCInfo((sex == 0) ? 0 : 1));
        d.info.useridhash = KotoriUtil.getNameHash(uid);
        d.quest = new UserQuest();
        d.shortcuts = new UserShortcuts();
        d.shortcuts.emotionsc = getDefaultEmotions();
        d.relations = new UserRelations();
        d.guardian = new UserGuardianData();
        d.talkflags = new UserTalkFlags();
        d.challenge = new UserChallenge();
        d.password = pw;
        d.restrict = 0;
        d.rec = new UserRecords();
        d.voteskin = new VoteRecords();
        d.duel = new UserDuelData();
        d.sex = sex;
        UserQuest userquest = new UserQuest();
        d.quest = userquest;
        d.items = GameSetting.getSetting().getInitialItems();
        d.info.name = "";
        setParams(d, params);
        return d;
    }

    private static void setParams(UserData d, String params) {
        if (KotoriUtil.isEmpty(params))
            return;
        StringTokenizer st = new StringTokenizer(params, ",");
        while (st.hasMoreTokens()) {
            String cmd = st.nextToken();
            int e = cmd.indexOf('=');
            if (e < 0 || e >= cmd.length())
                throw new IllegalArgumentException("illegal param: " + cmd);
            String key = cmd.substring(0, e).toLowerCase();
            String val = cmd.substring(e + 1);
            if ("name".equals(key)) {
                if ((new TextFilter.Name()).acceptAll(val)) {
                    d.info.name = val;
                    continue;
                }
                throw new IllegalArgumentException("illegal name: " + val);
            }
            if ("admin".equals(key)) {
                if ("gm".equals(val) || "GM".equals(val)) {
                    d.info.setGM();
                    int[] flags = {
                            10, 11, 20, 21, 22, 23, 24, 25, 26, 27,
                            300, 400, 500 };
                    for (int i = 0; i < flags.length; i++)
                        d.talkflags.setFlag(flags[i], true);
                    continue;
                }
                if ("dev".equals(val) || "DEV".equals(val)) {
                    d.info.setDevelopper();
                    continue;
                }
                throw new IllegalArgumentException("illegal admin: " + val);
            }
            if ("area".equals(key))
                d.areahash = val.hashCode();
        }
    }

    private static boolean compareSpecialRanks(int[] sr0, int[] sr1) {
        if (sr0 == sr1)
            return true;
        if (sr0 == null || sr1 == null)
            return false;
        if (sr0.length != sr1.length)
            return false;
        for (int l = 0; l < sr0.length; l++) {
            for (int k = 0; k < sr1.length && sr0[l] != sr1[k]; k++)
                if (k == sr1.length)
                    return false;
        }
        return true;
    }

    public void setSpecialRanks(int[] sr) {
        int[] sr0 = this.items.getEnableSpecialRanks();
        if (compareSpecialRanks(sr0, sr)) {
            if (sr == null)
                this.info.resetSpecialRank();
            return;
        }
        long time = System.currentTimeMillis();
        this.items.setSpecialRankTime(time);
        this.info.resetSpecialRank();
        if (sr != null) {
            GameSetting set = GameSetting.getSetting();
            for (int l = 0; l < sr.length; l++) {
                if (sr[l] > 0) {
                    this.info.overdrawSpecialRank(sr[l]);
                    if (set.enabledRankEquip())
                        this.items.addSpecialRank(set.getRank(sr[l]), time);
                }
            }
        }
    }

    public final void processTalkResult(TalkResult r) throws AmpedException {
        processTalkResult(r, null);
    }

    public ArrayList processTalkResult(TalkResult r, MyRoomData mrd) throws AmpedException {
        return processTalkResult(r, mrd, null);
    }

    public ArrayList processTalkResult(TalkResult r, MyRoomData mrd, UserTalkFlags oldflags)
            throws AmpedException {
        int value = this.talkflags.getValue(209);
        ArrayList<UserItems.Item> overitem = new ArrayList();
        GameSetting set = GameSetting.getSetting();
        long time = System.currentTimeMillis();
        if (r.getscore != 0 && !this.info.isGM())
            addScore(r.getscore);
        if (r.getmoney != 0 || r.lostitems != null || r.getitems != null || r.tredetitems != null
                || r.tutorialmyroomidx >= 0) {
            UserItems ui = this.items;
            ui.setGameMoney(Math.max(0, ui.getGameMoney() + r.getmoney));
            if (r.lostitems != null)
                for (int l = 0; l < r.lostitems.size(); l++) {
                    if (!ui.removeItem((Item) r.lostitems.get(l)))
                        throw new AmpedException(240, "used no have item:");
                }
            if (r.getitems != null)
                for (int l = 0; l < r.getitems.size(); l++) {
                    UserItems.Item item = ((UserItems.Item) r.getitems.get(l)).clone();
                    item.gettime = time;
                    if (!this.items.isGetable(item)) {
                        overitem.add(item);
                    } else {
                        ui.addItem(item, false, true);
                    }
                }
            if (r.tredetitems != null)
                for (int l = 0; l < r.tredetitems.size(); l++) {
                    NPCTradeList.TradeInfo info = (TradeInfo) r.tredetitems.get(l);
                    NPCTradeList list = (GameSetting.getSetting()).npctrade[info.listid];
                    UserItems.TerminableItem item = (list.item[info.itemidx]).item.clone();
                    item.gettype = 9;
                    item.gettime = time;
                    item.num = info.num;
                    if (!list.disabletioverlap || !ui.hasExpireTime(item.kind, item.id)
                            || ui.isExpired(item.kind, item.id))
                        if (!this.items.isGetable(item)) {
                            overitem.add(item);
                        } else {
                            ui.addItem(item, false, false);
                        }
                }
            if (set.navigation && r.tutorialmyroomidx >= 0) {
                if (set.isLanguage("kr")) {
                    if (!this.talkflags.getFlag(36, 0))
                        throw new AmpedException(240, "invalid flag 36:0");
                } else if (this.talkflags.getFlag(11)) {
                    throw new AmpedException(240, "invalid flag 11");
                }
                MyRoomTutorialData mrtd = set.getMyRoomTutorialData(1);
                if (mrtd != null) {
                    MyRoomTutorialData.MyRoomSet mrs = mrtd.getRoomSet(r.tutorialmyroomidx);
                    if (mrs == null)
                        throw new AmpedException(240, "invalid myroomid");
                    mrs.addItemsTo(ui, 1, (byte) 1, time);
                    mrs.placeTo(mrd);
                }
            }
        }
        if (r.getquest != null)
            for (int l = 0; l < r.getquest.length; l++)
                this.quest.addChallenging(r.getquest[l], r.getquestkey[l]);
        if (r.prgquest != null)
            for (int l = 0; l < r.prgquest.length; l += 2) {
                UserQuest.Challenging c = this.quest.getChallenging(r.prgquest[l]);
                if (c != null)
                    c.progress = r.prgquest[l + 1];
            }
        if (r.compquest != null)
            for (int l = 0; l + 1 < r.compquest.length; l += 2) {
                int qid = r.compquest[l];
                int check = r.compquest[l + 1];
                UserQuest.Challenging c = this.quest.getChallenging(qid);
                if (c == null)
                    throw new AmpedException(240, "not challenging");
                int check2 = c.getChecksum();
                if (Math.abs(check - check2) > 3)
                    throw new AmpedException(240, "quest check");
                this.quest.addMaster(qid);
                this.quest.removeChallenging(qid);
                QuestData qd = set.getQuest(qid);
                if (qd.score > 0 && !this.info.isGM())
                    addScore(qd.score);
                if (qd.gm > 0)
                    this.items.setGameMoney(this.items.getGameMoney() + qd.gm);
                if (qd.present != null && qd.present.length > 0) {
                    UserItems.Item[] p = UserItems.convertTreasure((UserItems.Item[]) qd.present);
                    if (p != null)
                        for (int k = 0; k < p.length; k++) {
                            if (p[k] != null) {
                                (p[k]).gettype = 4;
                                (p[k]).gettime = time;
                                if (!this.items.isGetable(p[k])) {
                                    overitem.add(p[k]);
                                } else {
                                    this.items.addItem(p[k], false, false);
                                }
                            }
                        }
                }
            }
        if (r.failquest != null)
            for (int l = 0; l < r.failquest.length; l++)
                this.quest.removeChallenging(r.failquest[l]);
        if (r.payAlchemyCost)
            this.items.setGameMoney(Math.max(0, this.items.getGameMoney() - set.alchemycost));
        if (r.challenged != null) {
            UserChallenge.Record[] crec = new UserChallenge.Record[r.challenged.size()];
            r.challenged.values((Object[]) crec);
            Integer[] score = new Integer[r.getchallengscore.size()];
            r.getchallengscore.values((Object[]) score);
            for (int l = 0; l < crec.length; l++) {
                if (r.clearchallenge) {
                    Object[] reward = set.getChallenge((crec[l]).id).getReward(score[l].intValue(),
                            this.challenge.getRecord((crec[l]).id));
                    int sc = ((Integer) reward[0]).intValue();
                    int gm = ((Integer) reward[1]).intValue();
                    UserItems.Item[] item = (UserItems.Item[]) reward[2];
                    addScore(sc);
                    this.items.setGameMoney(Math.max(0, this.items.getGameMoney() + gm));
                    if (item != null)
                        for (int n = 0; n < item.length; n++) {
                            UserItems.Item _item = item[n].clone();
                            _item.gettime = time;
                            if (!this.items.isGetable(_item)) {
                                overitem.add(_item);
                            } else {
                                this.items.addItem(_item);
                            }
                        }
                    if (set.practiceextensionenabled) {
                        int[] npc = (set.getChallenge((crec[l]).id)).appearnpc;
                        if (npc != null)
                            this.items.addPracticeNpcDay(npc, npc.length);
                    }
                }
                this.challenge.putRecord(crec[l]);
            }
        }
        if (r.wantedlevel != -1)
            this.guardian.wantedlevel = r.wantedlevel;
        if (r.addepisodelist != null)
            for (int i = 0; i < r.addepisodelist.length; i++)
                this.guardian.addEpisode(r.addepisodelist[i]);
        if (r.deleteepisodelist != null)
            for (int i = 0; i < r.deleteepisodelist.length; i++)
                this.guardian.useEpisode(r.deleteepisodelist[i], true);
        if (set.enabledChallengeRank()) {
            if (r.getrank != null)
                for (int l = 0; l < r.getrank.length; l++) {
                    RankData.Rank rd = set.getRank(r.getrank[l]);
                    if (!this.items.isChallengeableRank(rd))
                        continue;
                    if (rd.moneytype == 1) {
                        if (this.items.getRealMoney() < rd.price)
                            continue;
                        this.items.addRealMoney(-rd.price);
                    } else {
                        if (this.items.getGameMoney() < rd.price)
                            continue;
                        this.items.addGameMoney(-rd.price);
                    }
                    int prog = 0;
                    switch ((rd.getGroup()).progtype) {
                        case 4:
                            prog = this.rec.getMasterStyleNum();
                            break;
                        case 2:
                            prog = this.relations.getFriendsNum();
                            break;
                    }
                    this.items.addChallengeRank(rd, prog);
                    continue;
                }
            if (r.comprank != null) {
                for (int l = 0; l < r.comprank.length; l++)
                    this.items.completeChallengeRank(set.getRank(r.comprank[l]), time);
                this.info.resetNotHavingEquip(this.items);
            }
        }
        if (r.blacksmith != null && r.blacksmith.length > 2) {
            int cnt = 0;
            int event = r.blacksmith[cnt++];
            UserItems.Item accs = this.items.getItem(5, r.blacksmith[cnt++]);
            if (accs != null) {
                int sid, charge;
                ItemAdditionalData.Info dsi;
                SkillData t;
                DungeonSkillData dsd;
                int i;
                boolean ng = false;
                ItemAdditionalData dpData = this.items.getItemAdditionalData();
                int[] skill = new int[3];
                ArrayList<UserItems.Item> use = new ArrayList();
                switch (event) {
                    case 1:
                        for (; cnt < r.blacksmith.length; cnt++) {
                            SkillData sd = set.getSkill(r.blacksmith[cnt]);
                            if (sd instanceof DungeonSkillData && !sd.isPrivately()) {
                                DungeonSkillData dungeonSkillData = (DungeonSkillData) sd;
                                UserItems.Item[] req = dungeonSkillData.materials;
                                if (req != null)
                                    if (DungeonSkillData.grantable(accs, dungeonSkillData))
                                        if (dpData == null || !dpData.isLimitAccsNum(sd)) {
                                            skill[dungeonSkillData.type] = r.blacksmith[cnt];
                                            for (int j = 0; j < req.length; j++) {
                                                UserItems.Item add = req[j];
                                                int k = 0;
                                                for (; k < use.size(); k++) {
                                                    UserItems.Item item = use.get(k);
                                                    if (item.id == add.id && item.kind == add.kind) {
                                                        item.num = (short) (item.num + add.num);
                                                        break;
                                                    }
                                                }
                                                if (k == use.size())
                                                    use.add(
                                                            new UserItems.Item(add.kind, add.id, add.num, 0, 0L, 0));
                                            }
                                        }
                            }
                        }
                        break;
                    case 2:
                    case 3:
                        if (dpData == null) {
                            ng = true;
                            break;
                        }
                        sid = r.blacksmith[cnt++];
                        charge = r.blacksmith[cnt++];
                        dsi = dpData.getSkillInfo_Skill(accs.id, sid);
                        if (dsi == null) {
                            ng = true;
                            break;
                        }
                        t = set.getSkill(sid);
                        if (!(t instanceof DungeonSkillData)) {
                            ng = true;
                            break;
                        }
                        dsd = (DungeonSkillData) t;
                        switch (dsd.valueType) {
                            case 1:
                                charge = Math.min(charge, dsi.getValueMax() - dsi.getValue());
                                break;
                            case 2:
                                charge = Math.min(charge, 999);
                                break;
                            default:
                                ng = true;
                                break;
                        }
                        if (ng || charge < 1) {
                            ng = true;
                            break;
                        }
                        for (i = 0; i < dsd.matvalue.length; i++) {
                            if (dsd.matvalue[i] != null) {
                                UserItems.Item add = dsd.matvalue[i];
                                use.add(new UserItems.Item(add.kind, add.id, add.num * charge, 0, 0L, 0));
                            }
                        }
                        skill[0] = sid;
                        skill[1] = charge;
                        break;
                    default:
                        ng = true;
                        break;
                }
                int l;
                for (l = 0; !ng && l < use.size(); l++) {
                    UserItems.Item u = use.get(l);
                    UserItems.Item item1 = this.items.getItem(u.kind, u.id);
                    if (item1 == null || item1.num < u.num) {
                        ng = true;
                        break;
                    }
                }
                if (!ng) {
                    DungeonSkillData dungeonSkillData;
                    int limit;
                    for (l = 0; l < use.size(); l++)
                        this.items.removeItem(use.get(l));
                    switch (event) {
                        case 1:
                            for (l = 0; l < 3; l++) {
                                if (skill[l] > 0)
                                    accs.setTuneUp(l, skill[l]);
                            }
                            if (accs.id == this.info.accs) {
                                this.info.skill1 = (short) accs.getTuneUp(0);
                                this.info.skill2 = (short) accs.getTuneUp(1);
                                this.info.skill3 = (short) accs.getTuneUp(2);
                            }
                            if (dpData == null) {
                                dpData = new ItemAdditionalData();
                                dpData.updateSkillInfo(accs);
                                this.items.setItemAdditionalData(dpData);
                                break;
                            }
                            dpData.updateSkillInfo(accs);
                            break;
                        case 2:
                            dpData.getSkillInfo_Skill(accs.id, skill[0]).addValue(skill[1]);
                            break;
                        case 3:
                            dungeonSkillData = (DungeonSkillData) set.getSkill(skill[0]);
                            limit = dungeonSkillData.value * skill[1];
                            dpData.getSkillInfo_Skill(accs.id, skill[0]).decValueMax(-limit, 0);
                            break;
                    }
                }
            }
        }
        if (r

                .flags != null && !r.flags.isEmpty())
            if (r.flags.isValid(this.talkflags)) {
                r.flags.setTo(this.talkflags);
            } else {
                throw new AmpedException(240, "invalid talkflags update");
            }
        if (r.getstreetrank && set.enabledRankEquip()) {
            RankData.Rank sr = set.rank.getStreetFightRank(this.talkflags);
            boolean b = this.items.addStreetfightRank(sr, ServerTime.getTime());
            if (b && this.info.specialrank != 0) {
                RankData.Rank cr = set.getRank(this.info.specialrank);
                if (cr == null || cr.isPrivately() || cr.isKind(4))
                    this.info.specialrank = sr.idx;
            }
        }
        if (r.paramflags != null)
            this.items.setParamValue(r.paramflags);
        if (r.happysetversion != -1)
            this.items.putParam(2000, new Integer(r.happysetversion));
        if (r.eventtreasurelist != null)
            for (int i = 0; i < r.eventtreasurelist.length; i++) {
                if (oldflags != null && !isGetEventTreasure(r.eventid, oldflags)) {
                    System.out
                            .println("UserData isEventTreasure failed. userid:" + this.userid + " eventid:" + r.eventid
                                    + " userrank:" + ((set.rank != null) ? set.rank.rankIndexOf(this.info.score) : -1)
                                    + " eventrank:" + set.getEventRank(r.eventid));
                } else {
                    UserItems.Item[] ep = set.getEventPresent(r.eventid, r.eventtreasurelist[i]);
                    for (int j = 0; j < ep.length; j++) {
                        if (ep[j] != null) {
                            UserItems.Item t = ep[j].clone();
                            t.gettime = time;
                            if (!this.items.isGetable(t)) {
                                overitem.add(t);
                            } else {
                                this.items.addItem(t);
                            }
                        }
                    }
                }
            }
        if (r.mixstock != null)
            this.items.putParam(18, r.mixstock);
        if (r.npctreasurelist != null)
            for (int i = 0; i < r.npctreasurelist.length; i++) {
                UserItems.Item[] np = set.getNpcPresent(r.npcid, r.npctreasurelist[i]);
                for (int j = 0; j < np.length; j++) {
                    if (np[j] != null) {
                        UserItems.Item t = np[j].clone();
                        t.gettime = time;
                        if (!this.items.isGetable(t)) {
                            overitem.add(t);
                        } else {
                            this.items.addItem(t);
                        }
                    }
                }
            }
        if (r.questtreasurelist != null)
            for (int i = 0; i < r.questtreasurelist.length; i++) {
                UserItems.Item[] qp = set.getQuestPresent(r.questid, r.questtreasurelist[i]);
                for (int j = 0; j < qp.length; j++) {
                    if (qp[j] != null) {
                        UserItems.Item t = qp[j].clone();
                        t.gettime = time;
                        if (!this.items.isGetable(t)) {
                            overitem.add(t);
                        } else {
                            this.items.addItem(t);
                        }
                    }
                }
            }
        if (r.questionarytreasurelist != null)
            for (int i = 0; i < r.questionarytreasurelist.length; i++) {
                UserItems.Item[] qp = set.getQuestionaryPresent(r.questionarytreasurelist[i]);
                for (int j = 0; j < qp.length; j++) {
                    if (qp[j] != null) {
                        UserItems.Item t = qp[j].clone();
                        t.gettime = time;
                        if (!this.items.isGetable(t)) {
                            overitem.add(t);
                        } else {
                            this.items.addItem(t);
                        }
                    }
                }
            }
        if (r.eventequipid != -1) {
            UserItems.Item[] t = set.getEventPresent(r.eventid, r.eventequipid);
            for (int l = 0; l < t.length; l++) {
                if (t[l] != null && !set.isPrivately((t[l]).kind, (t[l]).id) &&
                        StyleData.isWearable(this.info.style, (t[l]).kind, (t[l]).id))
                    switch ((t[l]).kind) {
                        case 5:
                            this.info.accs = (short) (t[l]).id;
                            break;
                        case 1:
                            this.info.head = (short) (t[l]).id;
                            break;
                        case 2:
                            this.info.face = (short) (t[l]).id;
                            break;
                        case 3:
                            this.info.body = (short) (t[l]).id;
                            break;
                        case 4:
                            this.info.deco = (short) (t[l]).id;
                            break;
                        case 7:
                            this.info.weapon = (short) (t[l]).id;
                            break;
                    }
            }
        }
        return overitem;
    }

    public boolean isGetEventTreasure(int eventid, UserTalkFlags oldflag) {
        GameSetting set = GameSetting.getSetting();
        int rank = set.getEventRank(eventid);
        int myrank = set.rank.rankIndexOf(this.info.score);
        if (rank != -1 && rank > myrank)
            return false;
        int talkflag = set.getEventTalkflag(eventid);
        int value = oldflag.getValue(talkflag);
        if (talkflag != -1) {
            boolean flag2;
            int cnt;
            int id;
            int max;
            int i;
            switch (talkflag) {
                case 201:
                    switch (value) {
                        case 1:
                            if (rank != 6)
                                return false;
                            break;
                        case 2:
                            if (rank != 9)
                                return false;
                            break;
                        case 3:
                            if (rank != 11)
                                return false;
                            break;
                        case 4:
                            if (rank != 12)
                                return false;
                            break;
                        case 5:
                            if (rank != 14)
                                return false;
                            break;
                        case 6:
                            if (rank != 16)
                                return false;
                            break;
                        case 7:
                            if (rank != 19)
                                return false;
                            break;
                        case 8:
                            if (rank != 20)
                                return false;
                            break;
                        case 9:
                            if (rank != 24)
                                return false;
                            break;
                        case 10:
                            if (rank != 28)
                                return false;
                            break;
                        case 11:
                            if (rank != 32)
                                return false;
                            break;
                        case 12:
                            if (rank != 36)
                                return false;
                            break;
                        case 13:
                            if (rank != 45)
                                return false;
                            break;
                    }
                    break;
                case 204:
                    flag2 = oldflag.getFlag(talkflag, 1);
                    if (flag2)
                        return false;
                case 209:
                    if (!set.everyrankupeventenabled)
                        return false;
                    cnt = 0;
                    id = 150 + value - 1;
                    max = 198;
                    for (i = id; i <= max; i++) {
                        if (set.isDisableEvent(i)) {
                            cnt++;
                        } else if (rank < value + cnt) {
                            return false;
                        }
                    }
                    break;
            }
        }
        return true;
    }

    public UseItemInfo processUseItem(UseItemInfo i, boolean chserver) throws Exception {
        long time;
        int tkey, bkey;
        EventItem ei;
        int tval, bval;
        GameSetting set = GameSetting.getSetting();
        if (i.type == 2) {
            if (chserver)
                throw new AmpedException(240, "stage server only");
            int t = 0;
            switch (i.kind) {
                case 22:
                    t = this.items.getGameMoney();
                    break;
                case 23:
                    t = this.items.getRealMoney();
                    break;
                default:
                    throw new AmpedException(240, "not money");
            }
            if (t < i.id)
                throw new AmpedException(240, "not enough money");
        } else {
            if (i.type == 1 && (i.kind != 15 || i.num != 1))
                throw new AmpedException(240, "not boost item");
            if (set.isPrivately(i.kind, i.id))
                throw new AmpedException(240, "privately item");
            if (this.items.hasItems(i.kind, i.id) < i.num)
                throw new AmpedException(240, "not have item");
        }
        UseItemInfo ret = i;
        if (chserver) {
            time = System.currentTimeMillis();
            ret = i.clone();
            ret.proctime = time;
        } else {
            time = i.proctime;
        }
        switch (i.type) {
            case 0:
                this.items.removeItem(i.kind, i.id, i.num);
                return ret;
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
                return ret;
            case 2:
                switch (i.kind) {
                    case 22:
                        this.items.addGameMoney(-i.id);
                        break;
                    case 23:
                        this.items.addRealMoney(-i.id);
                        break;
                }
                return ret;
        }
        throw new AmpedException(240, "invalid type");
    }

    private int getBoostTargetTime(long time, int curTarget, int addtime, int maxtime) {
        int target, now = (int) (time / 60000L);
        if (maxtime > 0 && curTarget + 60 * addtime > now + 60 * maxtime) {
            target = now + 60 * maxtime;
        } else if (curTarget < now) {
            target = now + 60 * addtime;
        } else {
            target = curTarget + 60 * addtime;
        }
        return target;
    }

    private int getBoostTargetTime(long time, int curTarget) {
        return getBoostTargetTime(time, curTarget, 2, 12);
    }

    private int getBoostBonus(int _bonus, int curBonus, int maxBonus, long remainingTime) {
        int bonus;
        if (remainingTime <= 0L) {
            bonus = _bonus;
        } else if (curBonus + _bonus > maxBonus - _bonus) {
            bonus = maxBonus - _bonus;
        } else if (curBonus < _bonus) {
            bonus = _bonus;
        } else {
            bonus = curBonus + _bonus;
        }
        return bonus;
    }

    public long getBoostTime(int itemid) {
        if (this.talkflags != null) {
            long currenttime = ServerTime.getTime();
            switch (itemid) {
                case 29:
                    return this.talkflags.getValue(13) - currenttime / 60000L;
                case 30:
                    return this.talkflags.getValue(15) - currenttime / 60000L;
                case 31:
                    return this.talkflags.getValue(17) - currenttime / 60000L;
                case 36:
                    return this.talkflags.getValue(31) - currenttime / 60000L;
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                    return this.talkflags.getValue(33) - currenttime / 60000L;
            }
        }
        return 0L;
    }

    public void decWeapon(int num) {
        if (this.info.weapon == 0)
            return;
        this.items.removeItem(7, this.info.weapon, num);
        UserItems.Item i = this.items.getItem(7, this.info.weapon);
        if (i == null || i.num <= 0)
            this.info.weapon = 0;
    }

    public void decItems(int kind, int id, int num) {
        if (num <= 0)
            return;
        this.items.removeItem(kind, id, num);
    }

    public static int getRestrictFlag(String label) {
        if (label.equals("login"))
            return 1;
        if (label.equals("lchat"))
            return 2;
        if (label.equals("rchat"))
            return 4;
        if (label.equals("gchat"))
            return 8;
        if (label.equals("mchat"))
            return 16;
        if (label.equals("enter"))
            return 32;
        if (label.equals("trade"))
            return 64;
        if (label.equals("skin"))
            return 128;
        if (label.equals("skinbbs"))
            return 256;
        if (label.equals("bbs"))
            return 512;
        if (label.equals("mail"))
            return 1024;
        if (label.equals("vchat"))
            return 2048;
        return 0;
    }

    public boolean isChatRestrict(StageConnector con) {
        if (con == null || con.getStage() == null)
            return false;
        int mode = con.getStage().getStageMode();
        if (mode == 14) {
            if ((this.restrict & 0x10) != 0)
                return true;
        } else if (con.getStage().isMachingRoomMode()) {
            if ((this.restrict & 0x4) != 0)
                return true;
        } else if (con.getStage().isLoungeMode()) {
            if ((this.restrict & 0x2) != 0)
                return true;
        } else if ((this.restrict & 0x8) != 0) {
            return true;
        }
        return false;
    }

    public boolean isVoiceChatRestrict() {
        return ((this.restrict & 0x800) != 0);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(this.version);
        out.writeObject(this.userid);
        out.writeObject(this.password);
        out.writeObject(this.info);
        out.writeObject(this.items);
        out.writeObject(this.quest);
        out.writeObject(this.shortcuts);
        out.writeObject(this.relations);
        out.writeObject(this.guardian);
        out.writeObject(this.talkflags);
        out.writeObject(this.challenge);
        out.writeByte((this.firstlogin ? 1 : 0) | (this.applingAdult ? 2 : 0));
        out.writeShort(this.restrict);
        out.writeObject(this.rec);
        StreamTool.writePackedInt(this.logintimes, out);
        out.writeByte(this.sex);
        if (!this.voteskin.doVote()) {
            out.writeByte(0);
        } else {
            out.writeByte(1);
            out.writeObject(this.voteskin);
        }
        out.writeByte(2);
        out.writeObject(this.duel);
        out.writeInt(this.areahash);
        out.writeInt(0);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.version = in.readByte();
        this.userid = (String) in.readObject();
        this.password = (String) in.readObject();
        this.info = (UserInfo) in.readObject();
        Object o = null;
        for (; o == null; o = in.readObject())
            ;
        this.items = (UserItems) o;
        this.quest = (UserQuest) in.readObject();
        this.shortcuts = (UserShortcuts) in.readObject();
        this.relations = (UserRelations) in.readObject();
        this.guardian = (UserGuardianData) in.readObject();
        this.talkflags = (UserTalkFlags) in.readObject();
        this.challenge = (UserChallenge) in.readObject();
        byte b = in.readByte();
        this.firstlogin = ((b & 0x1) != 0);
        this.applingAdult = ((b & 0x2) != 0);
        this.restrict = in.readShort();
        this.rec = (UserRecords) in.readObject();
        if (this.rec == null)
            this.rec = new UserRecords();
        this.logintimes = StreamTool.readPackedInt(in);
        this.sex = in.readByte();
        int i = in.readByte();
        if (i == 0) {
            this.voteskin = new VoteRecords();
        } else {
            this.voteskin = (VoteRecords) in.readObject();
            this.voteskin.reset();
        }
        i = in.readByte() & 0xFF;
        if (i >= 1)
            this.duel = (UserDuelData) in.readObject();
        if (this.duel == null)
            this.duel = new UserDuelData();
        if (i >= 2) {
            this.areahash = in.readInt();
            in.readInt();
        }
    }

    public static void main(String[] args) throws Exception {
        Externalizer.setDefault(AmpedExternalizer.getExternalizer());
        ExternalizerInputStream eis = new ExternalizerInputStream(new FileInputStream(args[0]));
        Object o = eis.readObject();
        System.out.println(" " + o);
    }
}

/*
 * Location:
 * C:\Users\Nekosea\Desktop\ga2class\tw\classes\!\ga2\data\UserData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
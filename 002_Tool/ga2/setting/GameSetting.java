package ga2.setting;

import ga2.client.GetAmped2;
import ga2.client.PackageUpdater;
import ga2.data.AccessoryData;
import ga2.data.AmpedExternalizer;
import ga2.data.AmpedItem;
import ga2.data.ArcadeData;
import ga2.data.AutoMatchData;
import ga2.data.BGMInfo;
import ga2.data.BingoData;
import ga2.data.CalendarData;
import ga2.data.ChallengeData;
import ga2.data.ChatFilter;
import ga2.data.DiceGameData;
import ga2.data.DropBoostData;
import ga2.data.DuelData;
import ga2.data.DuelNameData;
import ga2.data.DuelScoreData;
import ga2.data.DungeonShopData;
import ga2.data.ETCItem;
import ga2.data.EmotionData;
import ga2.data.EntranceInfo;
import ga2.data.EventItem;
import ga2.data.EventSceneData;
import ga2.data.FairyGachaList;
import ga2.data.GAnlSetting;
import ga2.data.GachaLineupData;
import ga2.data.GlobalShopData;
import ga2.data.GuardianData;
import ga2.data.GuildBattleSetting;
import ga2.data.GuildSetting;
import ga2.data.HitEffectData;
import ga2.data.InitialItemsData;
import ga2.data.InitialKeyConfig;
import ga2.data.InitialSkinData;
import ga2.data.ItemPreviewPositionData;
import ga2.data.ItemSortData;
import ga2.data.LanData;
import ga2.data.MaterialData;
import ga2.data.MixMachine;
import ga2.data.MyRoomFurniture;
import ga2.data.MyRoomStage;
import ga2.data.MyRoomTutorialData;
import ga2.data.NPCInfo;
import ga2.data.NPCIntro;
import ga2.data.NPCItem;
import ga2.data.NPCTradeList;
import ga2.data.OfficialSkinData;
import ga2.data.OfficialWebsiteData;
import ga2.data.OldArcadeData;
import ga2.data.PlayTimeLimit;
import ga2.data.PointItem;
import ga2.data.PotentialSkillData;
import ga2.data.PracticeData;
import ga2.data.QuestBuyData;
import ga2.data.QuestData;
import ga2.data.QuestEventData;
import ga2.data.QuestPresentList;
import ga2.data.QuestionaryData;
import ga2.data.RandomGuardianTreasure;
import ga2.data.RandomTreasure;
import ga2.data.RandomTreasure2;
import ga2.data.RankData;
import ga2.data.RankingSetting;
import ga2.data.SDOAIPData;
import ga2.data.ShopData;
import ga2.data.ShopLineupData;
import ga2.data.ShopRotenData;
import ga2.data.SkillData;
import ga2.data.SkinItem;
import ga2.data.StreetNPCData;
import ga2.data.StreetfightTreasureTable;
import ga2.data.StyleData;
import ga2.data.TournamentData;
import ga2.data.TreasureTable;
import ga2.data.UnitedfightNPCData;
import ga2.data.UserData;
import ga2.data.UserInfo;
import ga2.data.UserItems;
import ga2.data.UserShortcuts;
import ga2.data.VehicleData;
import ga2.data.VoiceSet;
import ga2.data.WeaponData;
import ga2.data.WorldMapData;
import ga2.stage.PlayerScriptInfo;
import ga2.stage.event.DropStageItemEvent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import kotori.data.LightData;
import kotori.data.PostEffectData;
import kotori.geom.KMath;
import kotori.io.Externalizer;
import kotori.io.ExternalizerInputStream;
import kotori.io.ExternalizerOutputStream;
import kotori.io.KxrFile;
import kotori.io.Resource;
import kotori.io.StreamTool;
import kotori.kwt.KWTInfo;
import kotori.tool.scp.CompileError;
import kotori.tool.scp.ObjectReader;
import kotori.util.Clock;
import kotori.util.Inttable;
import kotori.util.KotoriUtil;
import kotori.util.ResourceManager;
import kotori.util.ServerTime;

public class GameSetting implements Externalizable {
    public static String deflang = "ja";
    private static GameSetting set;
    public static String defchar = "sjis";

    private static HashMap localemap = new HashMap<Object, Object>();
    public boolean printoff;
    static {
        localemap.put("ja", Locale.JAPAN);

        localemap.put("us", Locale.US);
        localemap.put("cn", Locale.CHINA);
        localemap.put("kr", Locale.KOREA);
        localemap.put("tw", Locale.TAIWAN);
    }

    public void print(Object s) {
        if (this.version < 0 && !this.printoff) {
            System.out.print(s);
        }
    }

    public void println(Object s) {
        if (this.version < 0 && !this.printoff) {
            System.out.println(s);
        }
    }

    public static void printStackTrace() {
        printStackTrace("");
    }

    public static void printStackTrace(Object str) {
        (new Exception("Stack trace: " + str)).printStackTrace();
    }

    public int version = -1;

    public String language = deflang;
    public String charset = defchar;

    public String timezone;

    private Locale locale;

    public WorldMapData worldmap;

    public StyleData[] style;

    public StyleData.Group[] stylegroup;

    public StyleData.Build[] build;

    public StyleData.Head[] head;

    public StyleData.Face[] face;

    public StyleData.Body[] body;

    public StyleData.Decoration[] deco;

    public AccessoryData[] accs;

    public WeaponData[] weapon;

    public SkillData[] skill;

    public EmotionData[] emo;

    public EmotionData[] semo;

    public UserShortcuts.ChatShortcut[] shortcut;

    public NPCInfo[] npc;

    public VehicleData[] vehicle;

    public StyleData[] spstyle;

    public Object[] lounge;

    public TournamentData[] tour;

    public OldArcadeData[] old_arcade;

    public DuelData[] duel;

    public DuelNameData[] duelnames;

    public DuelScoreData[] duelscores;
    public EventSceneData[] event;
    public byte[][] eventbytes;
    public GuardianData[] guardian;
    public PracticeData practice;
    public ArcadeData[] arcade;
    public GuildBattleSetting.Data guildbattlesettingdata;
    public QuestEventData[][] questevent;
    public MaterialData[] mat;
    public MixMachine mixmachine;
    public QuestData[] quest;
    public ChallengeData[] challenge;
    public int[] disableeventid;
    public QuestBuyData[] questbuy;
    public QuestData.RankupQuestData[] rankupquest;
    public boolean rankupquestsettingenabled;
    public MyRoomStage[] myroom;
    public MyRoomFurniture[] furniture;
    public MyRoomTutorialData[] myroomtutorial;
    public ETCItem[] etc;
    public EventItem[] eventitem;
    public DiceGameData[] dicegame;
    public BingoData[] bingo;
    public NPCItem[] npcitem;
    public LanData landata;
    public AutoMatchData automatchdata;
    public TreasureTable[] treasures;
    public StreetfightTreasureTable[] streettreasures;
    public RankData rank;
    public RankingSetting ranking;
    public HashMap kwtinfo = new HashMap<Object, Object>();
    public HashMap dicts = new HashMap<Object, Object>();

    public PlayerScriptInfo[] scpinfo;

    public Inttable area;

    public GuildSetting guild;

    public ShopLineupData shoplineup = new ShopLineupData();
    public GachaLineupData gachalineup = new GachaLineupData();

    public DungeonShopData dungeonshop;

    public GlobalShopData globalshop;

    public ShopRotenData shoprotendata;

    public DropBoostData[] dropboost;

    public NPCTradeList[] npctrade;

    public NPCIntro[] npcintro;

    public LightData[] stagelight;

    public HitEffectData[] hitefx;

    public PostEffectData[] postefx;

    public BGMInfo[] bgm;

    public VoiceSet[] voiceset;

    public EntranceInfo[] entrance;

    public ChatFilter chatfilter;

    public OfficialSkinData[] officialskins;

    public int defaultskinstock;

    public OfficialWebsiteData[] officialwebsite;

    public CalendarData calendar;

    public SDOAIPData sdoaip;

    public InitialItemsData initialitems;

    public UserItems demoitems;

    public StreetNPCData[] stnpcset;

    public InitialKeyConfig initkeyconf;

    public UnitedfightNPCData ufnpcset;

    public InitialSkinData initskin;

    public ItemSortData itemsort;

    public RandomTreasure[] randtreasure;

    public RandomGuardianTreasure[] randguardiantreasure;

    public RandomTreasure2 randtreasure2;

    public ItemSortData mailsort;
    public FairyGachaList[] fairygacha;
    public QuestPresentList[] questpresent;
    public ItemPreviewPositionData previewpos;
    public boolean fluidsetting_enabled;
    public boolean xcguard;
    public boolean voiceenabled;
    public boolean skinbbsenabled;
    public String skinbbs_soldpayback;
    public boolean areaopenguide;
    public boolean autochannelselect;
    public boolean myroomstart;
    public boolean worldmapenabled;
    public boolean hideeventinfo;
    public boolean destselection;
    public boolean announcegacha;
    public boolean questnpcguide;
    public byte showgachaprizecount = 5;
    public byte gacha_roten_ticket_limit = 90;

    public short autochselthreshold;

    public int skinbbs_deleteminute;

    public short skinbbs_maxregister;

    public short skinbbs_maxregister_one;

    public short skinbbs_poppoint_buy;

    public short skinbbs_poppoint_vote;

    public byte skinbbs_usablerank;
    public boolean specialrankenabled;
    public boolean duelenabled;
    public boolean teamsfenabled;
    public boolean accessoryeffects;
    public boolean partytraining;
    public boolean manualzoom;
    public boolean allchatfilter;
    public boolean training_skilllist;
    public boolean pastquests;
    public boolean streetstyle;
    public boolean skipevents;
    public boolean beginnerhelp;
    public boolean softwarekeyboard;
    public boolean new_medal;
    public boolean calendarenabled;
    public boolean charamakingscene2;
    public boolean shoplineupenabled;
    public boolean gachalineupenabled;
    public boolean hwidcheck;
    public boolean skiptutorial;
    public boolean new_username;
    public boolean navigation;
    public boolean freermenabled;
    public boolean newrmchargeenabled;
    public boolean duelrewardnomoney;
    public boolean observerenabled;
    public boolean observergmonly;
    public boolean sdoa;
    public boolean accs_potential;
    public boolean playtimelimitenabled;
    public PlayTimeLimit playtimelimit;
    public boolean showservertime;
    public int trademode;
    public int roommasterkicktime;
    public int basemixcost = 20;

    public int alchemycost = 100;

    public int potentialcost = 100;

    public boolean plainpassword;

    public boolean loginquest;

    public int edituserdatavalidtime = -1;

    public boolean disablegetnewmedal;
    public int newmedalrate = 200;

    public String pwsaltgeturl;

    public int savelogintype;

    public int rankitemmode;

    public boolean voicechatenabled;

    Resource localres;

    public boolean everyrankupeventenabled;

    public boolean arcademodeenabled;

    public boolean disconnectinfoenabled;

    public boolean zonesettingenabled;

    public boolean fairynpcenabled;

    public boolean globalshopenabled;

    public boolean gatroomexitdisable;

    public boolean avataravilityenabled;

    public boolean newstoryepisodeenabled;

    public boolean privateavataruploaddisabled;

    public boolean skineditequipaccsenabled;

    public boolean practiceextensionenabled;

    public boolean tourrandequipenabled;

    public boolean checkguildname;

    public boolean lindamissionenabled;

    public boolean lanplayenabled;

    public boolean fblistlimitenabled;

    public boolean automatchenabled;

    public boolean wpgenabled;

    public String wpgitemprocess;

    public boolean new_spyhuntquest;

    public boolean noreward_sameaddr;

    public boolean overitemsendmailenabled;
    public boolean ranklimitenabled;
    public int friendlistmax = 1000;
    public int blacklistmax = 1000;

    public boolean showchannelinfo;

    public boolean onechgroupselectenabled;

    public boolean manualupdatelauncherenabled;

    public boolean getsomepresentmailenabled;

    public boolean openallmemenabled;

    public boolean chbbsenabled;

    public boolean questmenutabenabled;

    public boolean dueldurationentryenabled = true;

    public boolean portaldbenabled;

    public boolean userid_email;

    public int adjustbalanceflags;

    public String deciphermode;

    public int battlepreloadmaxtime;

    public int checkmedalinterval;

    public boolean checkchratejump;

    public boolean tourplayernumcheckenabled;

    public int assistanttype;

    public boolean questpopupenabled;

    public boolean technamedisabled;

    public boolean istutorialbuttoncheck;

    public boolean unitedfightenabled;

    public boolean gg_enable;

    public boolean gg_auth_enable;
    public boolean gg_debuglog_enable;
    public boolean gg_auth_checkerror_enable;
    public String gg_init;
    public int gg_interval;
    public int gg_server_interval;
    public int gg_receive_interval;
    public String gg_tester;
    public boolean presentskinenabled;
    public boolean itemsortenabled;
    public int tourkeyroomrank = -1;

    public boolean dogfightenabled;

    public boolean shopnpcrotenenabled;

    public boolean tourrnddropenabled;

    public boolean guardianrnddropenabled;

    public boolean rnddropenabled;
    public boolean linebreakavoidwordchange;
    public boolean reduceStrWidth_Adv;
    public boolean showtoppanelmyinfo = true;
    public boolean shortnavi;
    public String replacefont = null;

    public String cacheclearversion = null;

    public boolean writepresetguildiconenabled;

    public boolean mailsortenabled;

    public boolean newstoryepisode2enabled;

    public boolean skinchatenabled;

    public int sendmailchecktime = 0;

    public int autoopenshuttertime;
    public int tutorialshopbuynum = 1;

    public boolean fullscreenminimizationenabled;

    public boolean clockspeedcheckenabled;

    public boolean titleaccountregistrationenabled;

    public boolean fairygachaenabled;

    public boolean usewidescreen;

    public boolean guardmissionenabled;

    public short guardmissionheal;

    public int restrictiongacharmchargeprice;

    public byte gacha_dailyfree_timeoffset;

    public boolean ganlclienttracking;

    public GAnlSetting ganlsetting;

    public boolean selectablecountry;

    public String[][] selectcountry;

    public QuestionaryData questionary;

    public boolean multipleplaybingo;

    public int channelselecttype;

    public boolean mixslotenabled;

    public int mixslotnum;

    public boolean serialfreeinputenabled;

    public boolean gachamachinemoveenabled;

    public boolean registrationipcheck;

    public boolean npcprocesseventlog;

    public boolean shopcouponenabled;
    public boolean useaccslog;
    public boolean newusernotregisterfromportal;
    public boolean idcchanneling;
    public boolean concheckenabled;
    public boolean potentialgearpartsstatus;
    public PotentialSkillData[] potentialskilldata;

    public static boolean isEmail(String str) {
        if (!(getSetting()).userid_email)
            return false;
        return KotoriUtil.isEmail(str);
    }

    public static boolean isIDCUid(String str) {
        if (!(getSetting()).idcchanneling)
            return false;
        return KotoriUtil.isIDCUid(str);
    }

    public static void setDefaultLanguage(String lang) {
        int i = lang.indexOf(':');
        if (i < 0) {
            deflang = lang;
            defchar = null;
        } else {
            deflang = lang.substring(0, i);
            defchar = lang.substring(i + 1);
        }
    }

    public void setLanguage(String lang) {
        int i = lang.indexOf(':');
        if (i < 0) {
            this.language = lang;
            this.charset = null;
        } else {
            this.language = lang.substring(0, i);
            this.charset = lang.substring(i + 1);
        }
    }

    public boolean isLanguage(String lang) {
        return this.language.equals(lang);
    }

    public boolean isLanguage(String... lang) {
        if (lang != null)
            for (int l = 0; l < lang.length; l++) {
                if (isLanguage(lang[l]))
                    return true;

            }
        return false;
    }

    public boolean isLoggingTalkFlags() {
        return false;
    }

    public boolean isNavigationImmediate() {
        return !isLanguage("kr");
    }

    public boolean isEventExitEnabled() {
        return isLanguage("kr");
    }

    public boolean isMenuEffectEnabled() {
        return isLanguage("kr");
    }

    public boolean isOrderEpisodeEnabled() {
        return isLanguage("kr");
    }

    private TimeZone tz = null;

    public TimeZone getTimeZone(boolean reflesh) {
        if (reflesh || this.tz == null) {
            if (KotoriUtil.isNotEmpty(this.timezone)) {
                this.tz = TimeZone.getTimeZone(this.timezone);
            }
            if (this.tz == null)
                this.tz = TimeZone.getDefault();
        }
        return this.tz;
    }

    public TimeZone getTimeZone() {
        return getTimeZone(false);
    }

    public long getTimeZoneTime() {
        return ServerTime.getTimeZoneTime(getTimeZone());
    }

    public Locale getLocale() {
        return (this.locale != null) ? this.locale : Locale.getDefault();
    }

    private void makeFlagTable() {
        String[] files = (String[]) null;
        if (this.localres == null) {

            File resdir = new File("resource/lang/" + set.language + "/icon/flag");
            files = resdir.list();
            if (files == null || files.length == 0) {
                return;
            }
        } else {
            Resource res = getSetting().getLocalResource("icon/flag");
            if (res == null)
                return;
            Resource[] list = res.getList();
            files = new String[list.length];
            for (int j = 0; j < list.length; j++) {
                files[j] = list[j].getName();
            }
        }
        this.area = new Inttable();
        for (int i = 0; i < files.length; i++) {
            String f = files[i];
            int idx = f.indexOf(".");
            String flag = null;
            if (idx > 1) {
                flag = f.substring(0, idx);
                this.area.put(flag.hashCode(), flag);
                set.println("makeFlagTable put hash:" + flag.hashCode() + ", flag:" + flag);
            }
        }
    }

    public String getFlag(int fhash) {
        if (this.area == null)
            return "";
        String flag = (String) this.area.get(fhash);
        return (flag != null) ? flag : "";
    }

    public boolean enabledRankEquip() {
        return (this.rankitemmode >= 1);
    }

    public boolean enabledChallengeRank() {
        return false;
    }

    public boolean isPrivately(int kind, int id) {
        AmpedItem ai = getAmpedItem(kind, id);
        return !(ai != null && !ai.isPrivately());
    }

    public boolean isBoostEnable(int chiket, UserItems.Item item) {
        return this.dropboost[chiket].isBoostEnable(item);
    }

    public boolean isDisableEvent(int eid) {
        if (this.disableeventid == null)
            return false;
        for (int i = 0; i < this.disableeventid.length; i++) {
            if (this.disableeventid[i] == eid)
                return true;
        }
        return false;
    }

    public StyleData getStyle(int id) {
        return (id < 0 || id >= this.style.length) ? null : this.style[id];
    }

    public StyleData.Head getHead(int id) {
        return (id < 0 || id >= this.head.length) ? null : this.head[id];
    }

    public StyleData.Face getFace(int id) {
        return (id < 0 || id >= this.face.length) ? null : this.face[id];
    }

    public StyleData.Body getBody(int id) {
        return (id < 0 || id >= this.body.length) ? null : this.body[id];
    }

    public StyleData.Build getBuild(int id) {
        return this.build[id];
    }

    public StyleData.Build findBuild(String name) {
        for (int l = 0; l < this.build.length; l++) {
            if (this.build[l] != null && name.equals((this.build[l]).name))
                return this.build[l];
        }
        return null;
    }

    public StyleData.Decoration getDecoration(int id) {
        return (id < 0 || id >= this.deco.length) ? null : this.deco[id];
    }

    public AccessoryData getAccs(int id) {
        return (id < 0 || id >= this.accs.length) ? null : this.accs[id];
    }

    public SkillData getSkill(int id) {
        return (id < 0 || id >= this.skill.length) ? null : this.skill[id];
    }

    public WeaponData getWeapon(int id) {
        return (id < 0 || id >= this.weapon.length) ? null : this.weapon[id];
    }

    public VehicleData getVehicle(int id) {
        return this.vehicle[id];
    }

    public EmotionData getEmotion(int id) {
        if (id < 0) {
            int sid = -id;
            return (sid >= this.semo.length) ? null : this.semo[sid];
        }
        return (id >= this.emo.length) ? null : this.emo[id];
    }

    public OfficialSkinData getOfficialSkin(int id) {
        return (id < 0 || id >= this.officialskins.length || this.officialskins[id] == null) ? null
                : this.officialskins[id];
    }

    public String getSDOAEntranceName(int entranceid) {
        for (int i = 0; i < this.sdoaip.login.length; i++) {
            if (entranceid == (this.sdoaip.login[i]).entranceid) {
                return (this.sdoaip.login[i]).name;
            }
        }
        return "";
    }

    public int emo(String scm) {
        int l;
        for (l = 0; l < this.emo.length; l++) {
            if (this.emo[l] != null && (this.emo[l]).scp.equals(scm))
                return l;
        }
        for (l = 0; l < this.semo.length; l++) {
            if (this.semo[l] != null && (this.semo[l]).scp.equals(scm))
                return -l;
        }
        return 0;
    }

    public UserShortcuts.ChatShortcut getChatShortcut(int id) {
        return (id >= 0 && id < this.shortcut.length) ? this.shortcut[id] : null;
    }

    public NPCInfo getNPCInfo(int id) {
        return (id >= 0 && id < this.npc.length) ? this.npc[id] : null;
    }

    public static int id(String stageCode, int ofs) {
        return NPCInfo.id(stageCode, ofs);
    }

    public TournamentData getTournament(int id) {
        return this.tour[id];
    }

    private int max_bannerid = -1;
    public static final int[] refTornamentData = new int[] {

            0, 1,
            8,
            9,
            5 };

    public int getBanneridMax() {
        if (this.max_bannerid != -1)
            return this.max_bannerid;

        for (int i = 0; i < refTornamentData.length; i++) {
            TournamentData t = getTournament(refTornamentData[i]);
            for (int j = 0; j < t.stageTbl.length; j++) {
                TournamentData.Stage stage = t.stage[t.stageTbl[j]];
                if (stage.bannerid < 0) {
                    for (int k = 0; k < stage.randomstage.length; k++) {
                        if (stage.randomstage[k] > this.max_bannerid)
                            this.max_bannerid = stage.randomstage[k];

                    }
                } else if (stage.bannerid > this.max_bannerid) {
                    this.max_bannerid = stage.bannerid;
                }

            }
        }
        return this.max_bannerid;
    }

    public GuardianData getGuardian() {
        return this.guardian[0];
    }

    public Object getLounge(int id) {
        return (id >= 0 && id < this.lounge.length) ? this.lounge[id] : null;
    }

    public StreetNPCData getStreetNPCData(int id) {
        return (id >= 0 && id < this.stnpcset.length) ? this.stnpcset[id] : null;
    }

    public UnitedfightNPCData getUnitedfightNPCData() {
        return this.ufnpcset;
    }

    public int[] getItemSortData(byte kind) {
        return this.itemsort.getSortData(kind);
    }

    public String getSortName(int id) {
        return this.itemsort.getSortName(id);
    }

    public int getMailSortKind() {
        return this.mailsort.getSortKind();
    }

    public String getMailSortName(int id) {
        return this.mailsort.getSortName(id);
    }

    public PracticeData getPractice() {
        return this.practice;
    }

    public LanData getLanData() {
        return this.landata;
    }

    public AutoMatchData getAutoMatchData() {
        return this.automatchdata;
    }

    public ArcadeData getArcade(int id) {
        return (id >= 0 && id < this.arcade.length) ? this.arcade[id] : null;
    }

    public QuestEventData[] getQuestEventList(int type) {
        if (this.questevent.length <= type)
            return null;
        return this.questevent[type];
    }

    public QuestionaryData getQuestionaryData() {
        return this.questionary;
    }

    public EventSceneData getEventScene(int id) {
        if (this.event == null) {
            return (EventSceneData) KotoriUtil.uncompress(this.eventbytes[id]);
        }
        return this.event[id];
    }

    public PotentialSkillData getPotentialSkillData(int id) {
        for (int i = 0; i < this.potentialskilldata.length; i++) {
            if (this.potentialskilldata[i].chkPotentialSkill(id))
                return this.potentialskilldata[i];
        }
        return null;
    }

    public UserItems.Item[] getEventPresent(int eventid, int presentindex) {
        try {
            EventSceneData ev = getEventScene(eventid);
            UserItems.Item[] p = (UserItems.Item[]) ev.present[presentindex];
            return UserItems.convertTreasure(p);
        } catch (Exception ex) {
            return null;
        }
    }

    public int getEventRank(int eventid) {
        try {
            EventSceneData ev = getEventScene(eventid);

            if (ev == null)
                return -1;
            return ev.rank;
        } catch (Exception ex) {
            return -1;
        }
    }

    public int getEventTalkflag(int eventid) {
        try {
            EventSceneData ev = getEventScene(eventid);

            if (ev == null)
                return -1;
            return ev.talkflag;
        } catch (Exception ex) {
            return -1;
        }
    }

    public UserItems.Item[] getEventPresent(int eventid) {
        try {
            EventSceneData ev = getEventScene(eventid);
            int count = 0;
            for (int i = 0; i < ev.present.length; i++) {
                count += ((UserItems.Item[]) ev.present[i]).length;
            }
            UserItems.Item[] p = new UserItems.Item[count];
            count = 0;
            for (int j = 0; j < ev.present.length; j++) {
                UserItems.Item[] _p = (UserItems.Item[]) ev.present[j];
                for (int m = 0; m < _p.length; m++) {
                    p[count] = _p[m];
                    count++;
                }
            }
            return UserItems.convertTreasure(p);
        } catch (Exception ex) {
            return null;
        }
    }

    public UserItems.Item[] getNpcPresent(int npcid, int presentindex) {
        try {
            NPCInfo npcinfo = getNPCInfo(npcid);
            UserItems.Item[] p = (UserItems.Item[]) npcinfo.talkpresent[presentindex];
            return UserItems.convertTreasure(p);
        } catch (Exception ex) {
            return null;
        }
    }

    public UserItems.Item[] getQuestPresent(int qid, int presentindex) {
        try {
            QuestData qd = getQuest(qid);
            UserItems.Item[] p = (UserItems.Item[]) qd.talkpresent[presentindex];
            return UserItems.convertTreasure(p);
        } catch (Exception ex) {
            return null;
        }
    }

    public UserItems.Item[] getQuestionaryPresent(int presentindex) {
        try {
            UserItems.Item[] p = (UserItems.Item[]) this.questionary.talkpresent[presentindex];
            return UserItems.convertTreasure(p);
        } catch (Exception ex) {
            return null;
        }
    }

    public MaterialData getMaterial(int id) {
        return (id < 0 || id >= this.mat.length) ? null : this.mat[id];
    }

    public QuestData getQuest(int id) {
        return (id < 0 || id >= this.quest.length) ? null : this.quest[id];
    }

    public QuestData.RankupQuestData getRankupQuest(UserData ud) {
        for (int i = 0; i < this.rankupquest.length; i++) {
            if (this.rankupquest[i] != null &&
                    !(this.rankupquest[i]).disable &&
                    !this.rankupquest[i].isClear(ud) && this.rankupquest[i].checkSubFlags(ud)
                    && !isDisableEvent((this.rankupquest[i]).eventid)) {
                return this.rankupquest[i];
            }
        }
        return null;
    }

    public int getPreRankupQuest(int r) {
        if (this.rankupquest == null)
            return 0;

        Comparator<? super Integer> c = new Comparator() {
            public int compare(Object obj, Object obj1) {
                if (obj instanceof Integer && obj1 instanceof Integer) {
                    int t1 = ((Integer) obj).intValue();
                    int t2 = ((Integer) obj1).intValue();
                    return (t1 == t2) ? 0 : ((t1 > t2) ? 1 : -1);
                }
                return 0;
            }
        };

        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < this.rankupquest.length; i++) {
            if (!(this.rankupquest[i]).disable
                    && !tmp.contains(Integer.valueOf(this.rankupquest[i].getRank()))) {
                tmp.add(Integer.valueOf(this.rankupquest[i].getRank()));
            }
        }

        if (tmp.size() > 0) {
            Integer[] ret = tmp.<Integer>toArray(new Integer[tmp.size()]);
            Arrays.sort(ret, c);

            if (r <= ret[0].intValue())
                return -1;
            for (int j = 1; j < ret.length; j++) {
                if (r <= ret[j].intValue()) {
                    return ret[j - 1].intValue();
                }
            }
        }
        return 0;
    }

    public ChallengeData getChallenge(int id) {
        return (id < 0 || id >= this.challenge.length) ? null : this.challenge[id];
    }

    public QuestBuyData getQuestBuyData(int id) {
        return (id < 0 || id >= this.questbuy.length) ? null : this.questbuy[id];
    }

    public int getUnitProgress(int id) {
        QuestData q = getQuest(id);
        if (q == null || q.arg == null)
            return 0;
        if (q.arg instanceof int[][]) {
            int[][] data = (int[][]) q.arg;
            if (data.length == 0)
                return 0;
            return q.ordernum / data.length;
        }
        return 0;
    }

    public int getStateProgress(int id, int progress) {
        int unit = getUnitProgress(id);
        if (unit == 0)
            return -1;

        return progress % unit;
    }

    public MyRoomStage getMyRoom(int id) {
        return (id < 0 || id >= this.myroom.length) ? null : this.myroom[id];
    }

    public MyRoomFurniture getFurniture(int id) {
        return (id < 0 || id >= this.furniture.length) ? null : this.furniture[id];
    }

    public MyRoomTutorialData getMyRoomTutorialData(int id) {
        return (id >= 0 && this.myroomtutorial != null && id < this.myroomtutorial.length)
                ? this.myroomtutorial[id]
                : null;
    }

    public ETCItem getETCItem(int id) {
        return (id < 0 || id >= this.etc.length) ? null : this.etc[id];
    }

    public EventItem getEventItem(int id) {
        return (id < 0 || id >= this.eventitem.length) ? null : this.eventitem[id];
    }

    public boolean checkRank(int id) {
        RankData.Rank d = getRank(id);
        return (d != null && !d.isPrivately());
    }

    public RankData.Rank getRank(int id) {
        return (id < 0 || id >= this.rank.rank.length) ? null : this.rank.rank[id];
    }

    public RankData.Rank getFormulaRank() {
        return getRank(50);
    }

    public BingoData getBingo(int id) {
        return (id < 0 || id >= this.bingo.length) ? null : this.bingo[id];
    }

    public NPCItem getNPCItem(int id) {
        return (id < 0 || id >= this.npcitem.length) ? null : this.npcitem[id];
    }

    public Object[] getStyleGroup(int group) {
        ArrayList<StyleData> a = new ArrayList();
        for (int i = 0; i < this.style.length;) {
            if ((getStyle(i)).group == group)
                a.add(getStyle(i));
            i++;
        }
        return (a.size() == 0) ? null : a.toArray();
    }

    public Object[] getStyleID(int group) {
        ArrayList<Integer> a = new ArrayList();
        for (int i = 0; i < this.style.length;) {
            if ((getStyle(i)).group == group)
                a.add(Integer.valueOf(i));
            i++;
        }
        return (a.size() == 0) ? null : a.toArray();
    }

    public AmpedItem getAmpedItem(int kind, int id) {
        switch (kind) {
            case 0:
                return (AmpedItem) getStyle(id);
            case 5:
                return (AmpedItem) getAccs(id);
            case 1:
                return (AmpedItem) getHead(id);
            case 2:
                return (AmpedItem) getFace(id);
            case 3:
                return (AmpedItem) getBody(id);
            case 4:
                return (AmpedItem) getDecoration(id);
            case 8:
            case 18:
                return (AmpedItem) getEmotion(id);
            case 11:
                return (AmpedItem) getQuest(id);
            case 7:
                return (AmpedItem) getWeapon(id);
            case 6:
                return (AmpedItem) getSkill(id);
            case 12:
                return (AmpedItem) getMaterial(id);
            case 13:
                return (AmpedItem) getFurniture(id);
            case 14:
                return (AmpedItem) getMyRoom(id);
            case 10:
                return (AmpedItem) getETCItem(id);
            case 15:
                return (AmpedItem) getEventItem(id);
            case 16:
                return (AmpedItem) getVoiceSet(id);
            case 21:
                return (AmpedItem) FluidSetting.getSetting().getItemPack(id);
            case 26:
                return (AmpedItem) FluidSetting.getSetting().getAutoSetItemPack(id);
            case 17:
                return (AmpedItem) getRank(id);
            case 22:
                return (AmpedItem) new PointItem(0, id);
            case 23:
                return (AmpedItem) new PointItem(1, id);
            case 9:
                return (id == 0) ? null : (AmpedItem) new SkinItem(id);
            case 24:
                return (AmpedItem) getQuestBuyData(id);
            case 25:
                return (AmpedItem) getNPCItem(id);
        }

        throw new NullPointerException("INVALID KIND : " + kind);
    }

    public RandomTreasure[] getRandomTreasure() {
        return this.randtreasure;
    }

    public RandomTreasure2 getRandomTreasure2() {
        return this.randtreasure2;
    }

    public RandomGuardianTreasure[] getRandomGuardianTreasure() {
        return this.randguardiantreasure;
    }

    public boolean isUsableSkinBBS(UserData ud) {
        if (this.skinbbs_usablerank == 0)
            return true;
        if (ud == null || ud.info == null || ud.info.getRank(true) == null)
            return true;

        return ((ud.info.getRank(true)).idx >= this.skinbbs_usablerank);
    }

    public FairyGachaList getFairyGachaList(int list) {
        if (list < 0 || this.fairygacha == null || list > this.fairygacha.length)
            return null;
        return this.fairygacha[list];
    }

    public boolean restrictionGachaRMCharge() {
        String key = "$gacha.rmchargeweb.url";
        String url = getSetting().getLocalText(key);
        if (!url.equals(key) && !url.equals(""))
            return true;
        return false;
    }

    public LightData getStageLight(int id) {
        return (id < 0 || id >= this.stagelight.length || this.stagelight[id] == null) ? this.stagelight[0]
                : this.stagelight[id];
    }

    public PostEffectData getStageEffect(int id) {
        return (id < 0 || id >= this.postefx.length || this.postefx[id] == null) ? null : this.postefx[id];
    }

    public HitEffectData getHitEffect(int id) {
        return (id >= 0 && id < this.hitefx.length) ? this.hitefx[id] : null;
    }

    public Resource getLocalResource(String s) {
        System.out.println(this.localres);
        return this.localres.getResource(s);
    }

    public Object getLocalContent(String s) {
        Resource r = this.localres.getResource(s);
        return (r == null) ? null : r.getContent();
    }

    public Object getSearchedContent(String s) {
        Resource r = this.localres.getResource(s);
        return (r == null) ? ResourceManager.getContent("/" + s) : r.getContent();
    }

    public String getLocalText(String key) {
        String s = (String) this.dicts.get(key);
        return (s == null) ? key : s;
    }

    public String getLocalTextOrNull(String key) {
        String s = (String) this.dicts.get(key);
        return (s == null) ? null : s;
    }

    public String getLocalText(String key, Object... arg) {
        String s = (String) this.dicts.get(key);
        return (s == null) ? key : (new Formatter()).format(s, arg).toString();
    }

    public KWTInfo kwtinfo(String pf) {
        KWTInfo i = (KWTInfo) this.kwtinfo.get(pf);
        if (i == null) {
            i = new KWTInfo();
            this.kwtinfo.put(pf, i);
        }
        return i;
    }

    public KWTInfo getPlatformKWTInfo() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windo")) {
            return (KWTInfo) this.kwtinfo.get("win32");
        }
        if (os.indexOf("Mac OS X") != -1) {
            return (KWTInfo) this.kwtinfo.get("mac");
        }
        return null;
    }

    public void addDict(String key, String val) {
        if (!key.startsWith("$"))
            throw new RuntimeException("Dictionary key must start with \"$\"");
        this.dicts.put(key, val);
    }

    static final String[][] texcolor = new String[][] { { "y", "w", "b" }, { "bl", "r", "XX" } };

    public void bodyset(int baseid, StyleData.Body basebody) {
        if (this.body == null) {
            this.body = new StyleData.Body[baseid + 9];
        } else if (this.body.length < baseid + 9) {
            StyleData.Body[] newb = new StyleData.Body[baseid + 9];
            System.arraycopy(this.body, 0, newb, 0, this.body.length);
            this.body = newb;
        }

        for (int l = 0; l < 9; l++) {
            StyleData.Body b = basebody.clone();
            String btex = b.texture;
            for (int j = 0; j < texcolor.length; j++) {
                b.texture = String.valueOf(texcolor[j][l / 3]) + "/" + btex + "_" + texcolor[j][l / 3]
                        + (l % 3 + 1);
                b.color = j * 3 + l / 3;
                if (b.hasTexture()) {
                    if (b.texture2 != null) {
                        b.texture2 = String.valueOf(texcolor[j][l / 3]) + "/" + b.texture2 + "_"
                                + texcolor[j][l / 3] + (l % 3 + 1);
                    }
                    this.body[baseid + l] = b;
                    break;
                }
            }
        }
    }

    public void bodyOverride(int baseid, String name) {
        for (int l = baseid; l < baseid + 9; l++) {
            if (l < this.body.length && this.body[l] != null)
                (this.body[l]).name = name;
        }
    }

    public void headset(int baseid, StyleData.Head basehead) {
        headset(baseid, 10, basehead);
    }

    public void headset(int baseid, int range, StyleData.Head basehead) {
        if (this.head == null) {
            this.head = new StyleData.Head[baseid + range];
        } else if (this.head.length < baseid + range) {
            StyleData.Head[] newh = new StyleData.Head[baseid + range];
            System.arraycopy(this.head, 0, newh, 0, this.head.length);
            this.head = newh;
        }

        for (int l = 0; l < range; l++) {
            StyleData.Head h = basehead.clone();
            h.texture = String.valueOf(h.texture) + "_" + l + ".agi";
            if (h.hasTexture()) {
                this.head[baseid + l] = h;
            }
        }
    }

    public void headOverride(int baseid, String name) {
        for (int l = baseid; l < baseid + 10; l++) {
            if (l < this.head.length && this.head[l] != null)
                (this.head[l]).name = name;
        }
    }

    public void decoset(int baseid, StyleData.Decoration basedeco) {
        if (this.deco == null) {
            this.deco = new StyleData.Decoration[baseid + 10];
        } else if (this.deco.length < baseid + 10) {
            StyleData.Decoration[] newd = new StyleData.Decoration[baseid + 10];
            System.arraycopy(this.deco, 0, newd, 0, this.deco.length);
            this.deco = newd;
        }

        for (int l = 0; l < 10; l++) {
            StyleData.Decoration d = basedeco.clone();
            d.texture = String.valueOf(d.texture) + "_" + l;
            if (d.hasTexture()) {
                this.deco[baseid + l] = d;
            }
        }
    }

    public void decoOverride(int baseid, String name) {
        for (int l = baseid; l < baseid + 10; l++) {
            if (l < this.deco.length && this.deco[l] != null)
                (this.deco[l]).name = name;
        }
    }

    public static ShopData newShopData() {
        return new ShopData();
    }

    public static DungeonShopData newDungeonShopData() {
        return new DungeonShopData();
    }

    public DungeonShopData overrideDungeonShopData() {
        return this.dungeonshop;
    }

    public static GlobalShopData newGlobalShopData() {
        return new GlobalShopData();
    }

    public GlobalShopData overrideGlobalShopData() {
        return this.globalshop;
    }

    public ShopRotenData getShopRotenData() {
        return this.shoprotendata;
    }

    public VoiceSet getVoiceSet(int id) {
        return this.voiceset[id];
    }

    public String getReplaceFont() {
        return this.replacefont;
    }

    public boolean isCacheClearUpdate(int preversion) {
        if (this.cacheclearversion == null || this.cacheclearversion.equals("") || preversion == -1)
            return false;
        try {
            int v = PackageUpdater.getVersionNumber(this.cacheclearversion);
            return (preversion < v && v <= this.version);
        } catch (Exception e) {
            System.out.println("incorrect preversion...");
            e.printStackTrace();

            return false;
        }
    }

    public BGMInfo getBGMInfo(String id) {
        if (this.bgm == null)
            return null;
        for (int l = 0; l < this.bgm.length; l++) {
            if (this.bgm[l] != null && (this.bgm[l]).id.equals(id))
                return this.bgm[l];
        }
        return null;
    }

    public String getRndBGMID() {
        if (this.bgm == null || this.bgm.length < 1)
            return null;
        return (this.bgm[KMath.rndi(this.bgm.length - 1)]).id;
    }

    public UserItems getInitialItems() {
        return UserItems.getInitialItems(this.initialitems, System.currentTimeMillis());
    }

    public void rangeDemoItems(int num, UserItems.Item baseItem) {
        for (int i = 0; i < num; i++) {
            if (getAmpedItem(baseItem.kind, baseItem.id + i) != null) {

                UserItems.Item demoItem = baseItem.clone();
                baseItem.id += i;
                try {
                    this.demoitems.addItem(demoItem);
                } catch (Exception exception) {
                }
            }
        }
    }

    private void finish() {
        this.localres = ResourceManager.getResource("/lang/" + this.language);
        this.locale = (Locale) localemap.get(this.language);
        println("Locale : " + this.locale);
        println("TimeZone : " + getTimeZone(true));

        for (int l = 0; l < this.style.length; l++) {
            if (this.style[l] != null)
                finishStyle(l, this.style[l]);

        }

        boolean err = false;
        int j;
        for (j = 0; j < this.npc.length; j++) {
            NPCInfo n = this.npc[j];
            if (n != null) {

                if (n.head != 0 && getHead(n.head) == null) {
                    System.err.println("NPC[" + NPCInfo.npcIDName(j) + "] INVALID HEAD ID : " + n.head);
                    err = true;
                }
                if (getBody(n.body) == null) {
                    System.err.println("NPC[" + NPCInfo.npcIDName(j) + "] INVALID BODY ID : " + n.body);
                    err = true;
                }
            }
        }

        // for (j = 0; j < this.guardian.length; j++) {
        // if (this.guardian[j] != null) {
        // err |= this.guardian[j].check();
        // }
        // }

        // for (j = 0; j < this.npctrade.length; j++) {
        // if (this.npctrade[j] != null)
        // this.npctrade[j].calcCheckSum();

        // }

        // for (int i = 0; i < this.challenge.length; i++) {
        // if (this.challenge[i] != null)
        // this.challenge[i].calcCheckSum();

        // }

        // finishShops((ShopData[]) this.dungeonshop.vender);
        // finishShops((ShopData[]) this.globalshop.vender);

        makeFlagTable();

        GuildBattleSetting.data = this.guildbattlesettingdata;

        if (err) {
            System.exit(1);
        }
    }

    private void finishShops(ShopData... shops) {
        for (int l = 0; l < shops.length; l++) {
            if (shops[l] != null)
                shops[l].finish();
        }
    }

    private void finishStyle(int id, StyleData s) {
        int[] i = new int[1000];
        int c = 0;

        int l;

        for (l = 0; l < this.head.length; l++) {
            if (this.head[l] != null && this.head[l].isWearable(id))
                i[c++] = l;
        }
        s.heads = subarray(i, c);
        c = 0;

        for (l = 0; l < this.face.length; l++) {
            if (this.face[l] != null && this.face[l].isWearable(id))
                i[c++] = l;
        }
        s.faces = subarray(i, c);
        c = 0;

        for (l = 0; l < this.body.length; l++) {
            if (this.body[l] != null && this.body[l].isWearable(id))
                i[c++] = l;
        }
        s.bodies = subarray(i, c);
        c = 0;

        for (l = 0; l < this.deco.length; l++) {
            if (this.deco[l] != null && this.deco[l].isWearable(id))
                i[c++] = l;
        }
        s.decos = subarray(i, c);
        c = 0;
    }

    public boolean enableAdjust(int flags) {
        return (((getSetting()).adjustbalanceflags & flags) != 0);
    }

    public boolean isAdjust(int... flags) {
        return enableAdjust(KotoriUtil.f(true, flags));
    }

    public void releaseVoices() {
        for (int l = 0; l < this.voiceset.length; l++) {
            if (this.voiceset[l] != null)
                this.voiceset[l].clearCache();

        }
    }

    public boolean assistDisabled(UserInfo gm, int type) {
        if (gm.adminflag != 7 || this.assistanttype == 0)
            return false;
        return ((type & 1 << this.assistanttype) != 0);
    }

    public void preloadUserInfo(UserInfo[] ui) {
        if (ui == null)
            return;
        UserInfo i = null;
        for (int l = 0; l < ui.length; l++) {
            i = ui[l];
            preloadUserInfo(i);
        }
    }

    public void preloadNPCInfo(int id) {
        preloadUserInfo((UserInfo) getNPCInfo(id));
    }

    public void preloadNPCInfo(NPCInfo i) {
        preloadUserInfo((UserInfo) i);
    }

    public void preloadUserInfo(UserInfo i) {
        if (i == null)
            return;
        if (getStyle(i.style) != null)
            getStyle(i.style).preload();
        if (getWeapon(i.weapon) != null)
            getWeapon(i.weapon).preload();

        if (getAccs(i.accs) != null)
            getAccs(i.accs).preloadActiveSkill(i.style);
        if (getSkill(i.skill0) != null)
            getSkill(i.skill0).preload();
        if (getSkill(i.skill1) != null)
            getSkill(i.skill1).preload();
        if (getSkill(i.skill2) != null)
            getSkill(i.skill2).preload();
        if (getSkill(i.skill3) != null)
            getSkill(i.skill3).preload();

        if (getVoiceSet(i.voice) != null)
            getVoiceSet(i.voice).preloard();

        if (getBody(i.body) != null)
            getBody(i.body).preload();
        if (getHead(i.head) != null)
            getHead(i.head).getHeadModel();
        if (getFace(i.face) != null)
            getFace(i.face).getFaceModels();
        if (getDecoration(i.deco) != null)
            getDecoration(i.deco).getDecorationModels(i.face);

    }

    public void preloadTournament(UserInfo[] i) {
        for (int l = 0; l < (this.scpinfo[0]).basescp.length;) {
            ResourceManager.preloadContent("/action/" + (this.scpinfo[0]).basescp[l]);
            l++;
        }

        DropStageItemEvent.preloadStageItem(0);
        DropStageItemEvent.preloadStageItem(1);

        preloadUserInfo(i);
    }

    public void preloadTournament() {
        for (int l = 0; l < (this.scpinfo[0]).basescp.length;) {
            ResourceManager.preloadContent("/action/" + (this.scpinfo[0]).basescp[l]);
            l++;
        }

        DropStageItemEvent.preloadStageItem(0);
        DropStageItemEvent.preloadStageItem(1);
    }

    private static int f(boolean flag, int... bit) {
        return KotoriUtil.f(flag, bit);
    }

    private static boolean f(int flag, int bit) {
        return ((flag & 1 << bit) != 0);
    }

    public static int bit(int... bit) {
        return KotoriUtil.f(true, bit);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.language);
        out.writeObject(this.charset);
        out.writeObject(this.timezone);

        out.writeObject(this.worldmap);

        writeArray((Externalizable[]) this.style, out);
        writeArray((Externalizable[]) this.stylegroup, out);
        writeArray((Externalizable[]) this.build, out);
        writeArray((Externalizable[]) this.head, out);
        writeArray((Externalizable[]) this.face, out);
        writeArray((Externalizable[]) this.body, out);
        writeArray((Externalizable[]) this.deco, out);

        writeArray((Externalizable[]) this.accs, out);
        writeArray((Externalizable[]) this.weapon, out);
        out.writeObject(this.skill);

        writeArray((Externalizable[]) this.emo, out);
        writeArray((Externalizable[]) this.semo, out);

        writeArray((Externalizable[]) this.npc, out);

        writeArray((Externalizable[]) this.vehicle, out);
        writeArray((Externalizable[]) this.spstyle, out);

        out.writeObject(this.lounge);
        writeArray((Externalizable[]) this.tour, out);
        out.writeObject(this.old_arcade);

        writeArray((Externalizable[]) this.duel, out);
        writeArray((Externalizable[]) this.duelnames, out);
        writeArray((Externalizable[]) this.duelscores, out);

        if (this.eventbytes != null && this.event != null) {
            byte[][] b = new byte[Math.max(this.event.length, this.eventbytes.length)][];
            for (int l = 0; l < b.length; l++) {
                if (l < this.event.length && this.event[l] != null) {
                    b[l] = KotoriUtil.compress(this.event[l]);
                } else if (l < this.eventbytes.length && this.eventbytes[l] != null) {
                    b[l] = this.eventbytes[l];
                }

            }
            out.writeObject(b);
        } else if (this.eventbytes != null) {
            out.writeObject(this.eventbytes);
        } else {
            byte[][] b = new byte[this.event.length][];
            for (int l = 0; l < this.event.length; l++) {
                if (this.event[l] != null)
                    b[l] = KotoriUtil.compress(this.event[l]);
            }
            out.writeObject(b);
        }
        out.writeObject(this.practice);

        writeArray((Externalizable[]) this.mat, out);
        out.writeObject(this.mixmachine);

        writeArray((Externalizable[]) this.quest, out);
        writeArray((Externalizable[]) this.challenge, out);

        out.writeObject(this.kwtinfo);
        out.writeObject(this.dicts);

        out.writeObject(this.scpinfo);

        out.writeObject(this.area);

        out.writeObject(this.stagelight);
        out.writeObject(this.hitefx);
        out.writeObject(this.postefx);

        out.writeObject(this.rank);
        out.writeObject(this.ranking);
        out.writeObject(this.guardian);
        out.writeObject(this.arcade);

        writeArray((Externalizable[]) this.bgm, out);
        writeArray((Externalizable[]) this.voiceset, out);

        writeArray((Externalizable[]) this.myroom, out);
        writeArray((Externalizable[]) this.furniture, out);
        writeArray((Externalizable[]) this.myroomtutorial, out);
        writeArray((Externalizable[]) this.etc, out);
        writeArray((Externalizable[]) this.eventitem, out);
        writeArray((Externalizable[]) this.dicegame, out);
        writeArray((Externalizable[]) this.bingo, out);
        writeArray((Externalizable[]) this.npcitem, out);

        writeArray((Externalizable[]) this.treasures, out);
        writeArray((Externalizable[]) this.streettreasures, out);

        out.writeObject(this.guild);
        out.writeObject(this.dungeonshop);
        out.writeObject(this.globalshop);
        out.writeObject(this.shoprotendata);

        out.writeObject(this.dropboost);

        out.writeObject(this.npctrade);
        out.writeObject(this.npcintro);

        out.writeObject(this.entrance);

        out.writeObject(this.chatfilter);

        writeArray((Externalizable[]) this.officialskins, out);
        out.writeByte(this.defaultskinstock);

        writeArray((Externalizable[]) this.officialwebsite, out);

        out.writeObject(this.initialitems);
        out.writeObject(this.demoitems);

        out.writeObject(this.stnpcset);
        out.writeObject(this.ufnpcset);

        out.writeObject(this.initkeyconf);

        out.writeObject(this.initskin);

        out.writeObject(this.itemsort);

        out.writeObject(this.randtreasure);
        out.writeObject(this.randguardiantreasure);
        out.writeObject(this.randtreasure2);

        out.writeObject(this.mailsort);

        out.writeObject(this.fairygacha);

        out.writeObject(this.previewpos);

        out.writeObject(this.questpresent);

        int i = (this.xcguard ? 1 : 0) | (this.voiceenabled ? 2 : 0) | (this.fluidsetting_enabled ? 4 : 0)
                | (this.skinbbsenabled ? 8 : 0) | (this.specialrankenabled ? 16 : 0) | (this.areaopenguide ? 32 : 0)
                | (this.duelenabled ? 64 : 0) | (this.plainpassword ? 128 : 0)
                | (this.autochannelselect ? 256 : 0) | (this.myroomstart ? 512 : 0) | (this.worldmapenabled ? 1024 : 0)
                | (this.hideeventinfo ? 2048 : 0)
                | (this.destselection ? 4096 : 0) | (this.accessoryeffects ? 8192 : 0)
                | (this.announcegacha ? 16384 : 0) | (this.partytraining ? 32768 : 0) | (this.manualzoom ? 65536 : 0)
                | (this.loginquest ? 131072 : 0) | (this.allchatfilter ? 262144 : 0)
                | (this.training_skilllist ? 524288 : 0) | (this.pastquests ? 1048576 : 0)
                | (this.streetstyle ? 2097152 : 0)
                | (this.skipevents ? 4194304 : 0) | (this.beginnerhelp ? 8388608 : 0)
                | (this.voicechatenabled ? 16777216 : 0) | (this.softwarekeyboard ? 33554432 : 0)
                | (this.playtimelimitenabled ? 67108864 : 0)
                | (this.questnpcguide ? 134217728 : 0) | f(this.userid_email, new int[] { 28 })
                | (this.observerenabled ? 536870912 : 0) | (this.sdoa ? 1073741824 : 0)
                | (this.new_medal ? Integer.MIN_VALUE : 0);
        out.writeInt(i);

        i = (this.calendarenabled ? 1 : 0) | (this.charamakingscene2 ? 2 : 0) | (this.observergmonly ? 4 : 0)
                | (this.shoplineupenabled ? 8 : 0) | (this.gachalineupenabled ? 16 : 0) | (this.hwidcheck ? 32 : 0)
                | (this.teamsfenabled ? 64 : 0)
                | (this.skiptutorial ? 128 : 0) | (this.showservertime ? 256 : 0) | (this.new_username ? 512 : 0)
                | (this.navigation ? 1024 : 0)
                | (this.everyrankupeventenabled ? 2048 : 0) | (this.arcademodeenabled ? 4096 : 0)
                | (this.disablegetnewmedal ? 8192 : 0)
                | (this.disconnectinfoenabled ? 16384 : 0) | (this.fairynpcenabled ? 32768 : 0)
                | (this.zonesettingenabled ? 65536 : 0) | (this.globalshopenabled ? 131072 : 0)
                | (this.gatroomexitdisable ? 262144 : 0) | (this.avataravilityenabled ? 524288 : 0)
                | (this.newstoryepisodeenabled ? 1048576 : 0) | (this.privateavataruploaddisabled ? 2097152 : 0)
                | (this.skineditequipaccsenabled ? 4194304 : 0) | (this.practiceextensionenabled ? 8388608 : 0)
                | (this.tourrandequipenabled ? 16777216 : 0) | (this.checkguildname ? 33554432 : 0)
                | (this.lindamissionenabled ? 67108864 : 0) | (this.lanplayenabled ? 134217728 : 0);
        out.writeInt(i);

        i = (this.freermenabled ? 1 : 0) | (this.newrmchargeenabled ? 2 : 0)
                | (this.duelrewardnomoney ? 4 : 0) | (this.fblistlimitenabled ? 8 : 0) |
                f(this.accs_potential, new int[] { 4 }) | (this.automatchenabled ? 32 : 0)
                | (this.wpgenabled ? 64 : 0) | (this.new_spyhuntquest ? 128 : 0) | (this.noreward_sameaddr ? 256 : 0)
                | (this.overitemsendmailenabled ? 512 : 0)
                | (this.ranklimitenabled ? 1024 : 0) | (this.showchannelinfo ? 2048 : 0)
                | (this.onechgroupselectenabled ? 4096 : 0) | (this.manualupdatelauncherenabled ? 8192 : 0)
                | (this.getsomepresentmailenabled ? 16384 : 0) | (this.openallmemenabled ? 32768 : 0)
                | (this.chbbsenabled ? 65536 : 0) | (this.questmenutabenabled ? 131072 : 0)
                | (this.dueldurationentryenabled ? 262144 : 0) | (this.checkchratejump ? 524288 : 0)
                | (this.tourplayernumcheckenabled ? 1048576 : 0) | (this.questpopupenabled ? 2097152 : 0)
                | (this.technamedisabled ? 4194304 : 0) | (this.istutorialbuttoncheck ? 8388608 : 0)
                | (this.portaldbenabled ? 16777216 : 0) | (this.unitedfightenabled ? 33554432 : 0)
                | (this.rankupquestsettingenabled ? 67108864 : 0) | (this.gg_enable ? 134217728 : 0)
                | (this.gg_auth_enable ? 268435456 : 0) | (this.gg_debuglog_enable ? 536870912 : 0)
                | (this.presentskinenabled ? 1073741824 : 0) | (this.itemsortenabled ? Integer.MIN_VALUE : 0);
        out.writeInt(i);

        i = (this.dogfightenabled ? 1 : 0) | (this.linebreakavoidwordchange ? 2 : 0)
                | (this.showtoppanelmyinfo ? 4 : 0) | (this.tourrnddropenabled ? 8 : 0)
                | (this.reduceStrWidth_Adv ? 16 : 0) | (this.shortnavi ? 32 : 0)
                | (this.shopnpcrotenenabled ? 64 : 0) | (this.writepresetguildiconenabled ? 128 : 0)
                | (this.mailsortenabled ? 256 : 0) | (this.newstoryepisode2enabled ? 512 : 0)
                | (this.gg_auth_checkerror_enable ? 1024 : 0) | (this.skinchatenabled ? 2048 : 0)
                | (this.fullscreenminimizationenabled ? 8192 : 0) | (this.clockspeedcheckenabled ? 16384 : 0)
                | (this.guardianrnddropenabled ? 32768 : 0) | (this.titleaccountregistrationenabled ? 65536 : 0)
                | (this.fairygachaenabled ? 131072 : 0)
                | (this.usewidescreen ? 262144 : 0) | (this.guardmissionenabled ? 524288 : 0)
                | (this.ganlclienttracking ? 1048576 : 0) | (this.selectablecountry ? 2097152 : 0)
                | (this.multipleplaybingo ? 4194304 : 0) | (this.mixslotenabled ? 8388608 : 0)
                | (this.serialfreeinputenabled ? 16777216 : 0) | (this.gachamachinemoveenabled ? 33554432 : 0)
                | (this.rnddropenabled ? 67108864 : 0) | (this.registrationipcheck ? 536870912 : 0)
                | (this.npcprocesseventlog ? 1073741824 : 0)
                | (this.shopcouponenabled ? Integer.MIN_VALUE : 0);
        out.writeInt(i);

        i = (this.useaccslog ? 1 : 0) | (this.newusernotregisterfromportal ? 2 : 0)
                | (this.idcchanneling ? 4 : 0) | (this.concheckenabled ? 8 : 0)
                | (this.potentialgearpartsstatus ? 32 : 0);
        out.writeInt(i);

        out.writeByte(this.trademode);
        out.writeByte(this.showgachaprizecount);
        out.writeByte(this.gacha_roten_ticket_limit);

        out.writeInt(this.roommasterkicktime);

        out.writeShort(this.autochselthreshold);

        out.writeObject(this.guildbattlesettingdata);

        out.writeObject(this.questevent);

        out.writeObject(this.skinbbs_soldpayback);

        out.writeInt(this.skinbbs_deleteminute);

        out.writeInt(this.basemixcost);

        out.writeInt(this.edituserdatavalidtime);

        out.writeObject(this.playtimelimit);

        out.writeShort(this.skinbbs_maxregister);

        out.writeObject(this.calendar);

        out.writeShort(this.newmedalrate);
        out.writeObject(this.pwsaltgeturl);

        out.writeInt(this.alchemycost);
        out.writeInt(this.potentialcost);

        out.writeShort(this.savelogintype);
        out.writeByte(this.rankitemmode);

        out.writeObject(this.sdoaip);

        writeArray((Externalizable[]) this.shortcut, out);

        writeArray((Externalizable[]) this.questbuy, out);

        out.writeObject(this.landata);
        out.writeObject(this.automatchdata);

        out.writeObject(this.disableeventid);

        out.writeInt(this.friendlistmax);
        out.writeInt(this.blacklistmax);

        out.writeObject(this.wpgitemprocess);

        out.writeShort(this.skinbbs_maxregister_one);

        out.writeShort(this.skinbbs_poppoint_buy);
        out.writeShort(this.skinbbs_poppoint_vote);

        out.writeInt(this.adjustbalanceflags);

        out.writeObject(this.deciphermode);

        out.writeInt(this.battlepreloadmaxtime);

        out.writeInt(this.checkmedalinterval);

        out.writeInt(this.assistanttype);

        writeArray((Externalizable[]) this.rankupquest, out);

        out.writeObject(this.gg_init);
        out.writeShort(this.gg_interval);
        out.writeShort(this.gg_server_interval);
        out.writeShort(this.gg_receive_interval);
        out.writeObject(this.gg_tester);

        out.writeInt(this.tourkeyroomrank);

        out.writeObject(this.replacefont);

        out.writeObject(this.cacheclearversion);

        out.writeByte(this.skinbbs_usablerank);

        out.writeInt(this.sendmailchecktime);
        out.writeInt(this.autoopenshuttertime);

        out.writeInt(this.tutorialshopbuynum);

        out.writeShort(this.guardmissionheal);

        out.writeInt(this.restrictiongacharmchargeprice);
        out.writeByte(this.gacha_dailyfree_timeoffset);

        out.writeObject(this.ganlsetting);

        out.writeObject(this.selectcountry);

        out.writeObject(this.questionary);

        out.writeByte(this.channelselecttype);

        out.writeShort(this.mixslotnum);

        out.writeObject(this.potentialskilldata);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.language = (String) in.readObject();
        this.charset = (String) in.readObject();
        this.timezone = (String) in.readObject();

        this.worldmap = (WorldMapData) in.readObject();

        this.style = (StyleData[]) readArray(StyleData.class, in);
        this.stylegroup = (StyleData.Group[]) readArray(StyleData.Group.class, in);
        this.build = (StyleData.Build[]) readArray(StyleData.Build.class, in);
        this.head = (StyleData.Head[]) readArray(StyleData.Head.class, in);
        this.face = (StyleData.Face[]) readArray(StyleData.Face.class, in);
        this.body = (StyleData.Body[]) readArray(StyleData.Body.class, in);
        this.deco = (StyleData.Decoration[]) readArray(StyleData.Decoration.class, in);

        this.accs = (AccessoryData[]) readArray(AccessoryData.class, in);
        this.weapon = (WeaponData[]) readArray(WeaponData.class, in);
        this.skill = (SkillData[]) in.readObject();

        this.emo = (EmotionData[]) readArray(EmotionData.class, in);
        this.semo = (EmotionData[]) readArray(EmotionData.class, in);

        this.npc = (NPCInfo[]) readArray(NPCInfo.class, in);

        this.vehicle = (VehicleData[]) readArray(VehicleData.class, in);
        this.spstyle = (StyleData[]) readArray(StyleData.class, in);

        this.lounge = (Object[]) in.readObject();
        this.tour = (TournamentData[]) readArray(TournamentData.class, in);
        this.old_arcade = (OldArcadeData[]) in.readObject();

        this.duel = (DuelData[]) readArray(DuelData.class, in);
        this.duelnames = (DuelNameData[]) readArray(DuelNameData.class, in);
        this.duelscores = (DuelScoreData[]) readArray(DuelScoreData.class, in);

        this.eventbytes = (byte[][]) in.readObject();
        this.practice = (PracticeData) in.readObject();

        this.mat = (MaterialData[]) readArray(MaterialData.class, in);
        this.mixmachine = (MixMachine) in.readObject();

        this.quest = (QuestData[]) readArray(QuestData.class, in);
        this.challenge = (ChallengeData[]) readArray(ChallengeData.class, in);

        this.kwtinfo = (HashMap) in.readObject();
        this.dicts = (HashMap) in.readObject();

        this.scpinfo = (PlayerScriptInfo[]) in.readObject();

        this.area = (Inttable) in.readObject();

        this.stagelight = (LightData[]) in.readObject();
        this.hitefx = (HitEffectData[]) in.readObject();
        this.postefx = (PostEffectData[]) in.readObject();

        this.rank = (RankData) in.readObject();
        this.ranking = (RankingSetting) in.readObject();
        this.guardian = (GuardianData[]) in.readObject();
        this.arcade = (ArcadeData[]) in.readObject();

        this.bgm = (BGMInfo[]) readArray(BGMInfo.class, in);
        this.voiceset = (VoiceSet[]) readArray(VoiceSet.class, in);

        this.myroom = (MyRoomStage[]) readArray(MyRoomStage.class, in);
        this.furniture = (MyRoomFurniture[]) readArray(MyRoomFurniture.class, in);
        this.myroomtutorial = (MyRoomTutorialData[]) readArray(MyRoomTutorialData.class, in);
        this.etc = (ETCItem[]) readArray(ETCItem.class, in);
        this.eventitem = (EventItem[]) readArray(EventItem.class, in);
        this.dicegame = (DiceGameData[]) readArray(DiceGameData.class, in);
        this.bingo = (BingoData[]) readArray(BingoData.class, in);
        this.npcitem = (NPCItem[]) readArray(NPCItem.class, in);

        this.treasures = (TreasureTable[]) readArray(TreasureTable.class, in);
        this.streettreasures = (StreetfightTreasureTable[]) readArray(StreetfightTreasureTable.class, in);

        this.guild = (GuildSetting) in.readObject();
        this.dungeonshop = (DungeonShopData) in.readObject();
        this.globalshop = (GlobalShopData) in.readObject();
        this.shoprotendata = (ShopRotenData) in.readObject();

        this.dropboost = (DropBoostData[]) in.readObject();

        this.npctrade = (NPCTradeList[]) in.readObject();
        this.npcintro = (NPCIntro[]) in.readObject();

        this.entrance = (EntranceInfo[]) in.readObject();

        this.chatfilter = (ChatFilter) in.readObject();

        this.officialskins = (OfficialSkinData[]) readArray(OfficialSkinData.class, in);
        this.defaultskinstock = in.readByte() & 0xFF;

        this.officialwebsite = (OfficialWebsiteData[]) readArray(OfficialWebsiteData.class, in);

        this.initialitems = (InitialItemsData) in.readObject();
        this.demoitems = (UserItems) in.readObject();

        this.stnpcset = (StreetNPCData[]) in.readObject();

        this.ufnpcset = (UnitedfightNPCData) in.readObject();

        this.initkeyconf = (InitialKeyConfig) in.readObject();

        this.initskin = (InitialSkinData) in.readObject();

        this.itemsort = (ItemSortData) in.readObject();

        this.randtreasure = (RandomTreasure[]) in.readObject();
        this.randguardiantreasure = (RandomGuardianTreasure[]) in.readObject();
        this.randtreasure2 = (RandomTreasure2) in.readObject();

        this.mailsort = (ItemSortData) in.readObject();

        this.fairygacha = (FairyGachaList[]) in.readObject();
        this.previewpos = (ItemPreviewPositionData) in.readObject();

        this.questpresent = (QuestPresentList[]) in.readObject();

        int i = in.readInt();
        this.xcguard = ((i & 0x1) != 0);
        this.voiceenabled = ((i & 0x2) != 0);
        this.fluidsetting_enabled = ((i & 0x4) != 0);
        this.skinbbsenabled = ((i & 0x8) != 0);
        this.specialrankenabled = ((i & 0x10) != 0);
        this.areaopenguide = ((i & 0x20) != 0);
        this.duelenabled = ((i & 0x40) != 0);
        this.plainpassword = ((i & 0x80) != 0);
        this.autochannelselect = ((i & 0x100) != 0);
        this.myroomstart = ((i & 0x200) != 0);
        this.worldmapenabled = ((i & 0x400) != 0);
        this.hideeventinfo = ((i & 0x800) != 0);
        this.destselection = ((i & 0x1000) != 0);
        this.accessoryeffects = ((i & 0x2000) != 0);
        this.announcegacha = ((i & 0x4000) != 0);
        this.partytraining = ((i & 0x8000) != 0);
        this.manualzoom = ((i & 0x10000) != 0);
        this.loginquest = ((i & 0x20000) != 0);
        this.allchatfilter = ((i & 0x40000) != 0);
        this.training_skilllist = ((i & 0x80000) != 0);
        this.pastquests = ((i & 0x100000) != 0);
        this.streetstyle = ((i & 0x200000) != 0);
        this.skipevents = ((i & 0x400000) != 0);
        this.beginnerhelp = ((i & 0x800000) != 0);
        this.voicechatenabled = ((i & 0x1000000) != 0);
        this.softwarekeyboard = ((i & 0x2000000) != 0);
        this.playtimelimitenabled = ((i & 0x4000000) != 0);
        this.questnpcguide = ((i & 0x8000000) != 0);
        this.userid_email = f(i, 28);
        this.observerenabled = ((i & 0x20000000) != 0);
        this.sdoa = ((i & 0x40000000) != 0);
        this.new_medal = ((i & Integer.MIN_VALUE) != 0);

        i = in.readInt();
        this.calendarenabled = ((i & 0x1) != 0);
        this.charamakingscene2 = ((i & 0x2) != 0);
        this.observergmonly = ((i & 0x4) != 0);
        this.shoplineupenabled = ((i & 0x8) != 0);
        this.gachalineupenabled = ((i & 0x10) != 0);
        this.hwidcheck = ((i & 0x20) != 0);
        this.teamsfenabled = ((i & 0x40) != 0);
        this.skiptutorial = ((i & 0x80) != 0);
        this.showservertime = ((i & 0x100) != 0);
        this.new_username = ((i & 0x200) != 0);
        this.navigation = ((i & 0x400) != 0);
        this.everyrankupeventenabled = ((i & 0x800) != 0);
        this.arcademodeenabled = ((i & 0x1000) != 0);
        this.disablegetnewmedal = ((i & 0x2000) != 0);
        this.disconnectinfoenabled = ((i & 0x4000) != 0);
        this.fairynpcenabled = ((i & 0x8000) != 0);
        this.zonesettingenabled = ((i & 0x10000) != 0);
        this.globalshopenabled = ((i & 0x20000) != 0);
        this.gatroomexitdisable = ((i & 0x40000) != 0);
        this.avataravilityenabled = ((i & 0x80000) != 0);
        this.newstoryepisodeenabled = ((i & 0x100000) != 0);
        this.privateavataruploaddisabled = ((i & 0x200000) != 0);
        this.skineditequipaccsenabled = ((i & 0x400000) != 0);
        this.practiceextensionenabled = ((i & 0x800000) != 0);
        this.tourrandequipenabled = ((i & 0x1000000) != 0);
        this.checkguildname = ((i & 0x2000000) != 0);
        this.lindamissionenabled = ((i & 0x4000000) != 0);
        this.lanplayenabled = ((i & 0x8000000) != 0);

        i = in.readInt();
        this.freermenabled = ((i & 0x1) != 0);
        this.newrmchargeenabled = ((i & 0x2) != 0);
        this.duelrewardnomoney = ((i & 0x4) != 0);
        this.fblistlimitenabled = ((i & 0x8) != 0);
        this.accs_potential = f(i, 4);
        this.automatchenabled = ((i & 0x20) != 0);
        this.wpgenabled = ((i & 0x40) != 0);
        this.new_spyhuntquest = ((i & 0x80) != 0);
        this.noreward_sameaddr = ((i & 0x100) != 0);
        this.overitemsendmailenabled = ((i & 0x200) != 0);
        this.ranklimitenabled = ((i & 0x400) != 0);
        this.showchannelinfo = ((i & 0x800) != 0);
        this.onechgroupselectenabled = ((i & 0x1000) != 0);
        this.manualupdatelauncherenabled = ((i & 0x2000) != 0);
        this.getsomepresentmailenabled = ((i & 0x4000) != 0);
        this.openallmemenabled = ((i & 0x8000) != 0);
        this.chbbsenabled = ((i & 0x10000) != 0);
        this.questmenutabenabled = ((i & 0x20000) != 0);
        this.dueldurationentryenabled = ((i & 0x40000) != 0);
        this.checkchratejump = ((i & 0x80000) != 0);
        this.tourplayernumcheckenabled = ((i & 0x100000) != 0);
        this.questpopupenabled = ((i & 0x200000) != 0);
        this.technamedisabled = ((i & 0x400000) != 0);
        this.istutorialbuttoncheck = ((i & 0x800000) != 0);
        this.portaldbenabled = ((i & 0x1000000) != 0);
        this.unitedfightenabled = ((i & 0x2000000) != 0);
        this.rankupquestsettingenabled = ((i & 0x4000000) != 0);
        this.gg_enable = ((i & 0x8000000) != 0);
        this.gg_auth_enable = ((i & 0x10000000) != 0);
        this.gg_debuglog_enable = ((i & 0x20000000) != 0);
        this.presentskinenabled = ((i & 0x40000000) != 0);
        this.itemsortenabled = ((i & Integer.MIN_VALUE) != 0);

        i = in.readInt();
        this.dogfightenabled = ((i & 0x1) != 0);
        this.linebreakavoidwordchange = ((i & 0x2) != 0);
        this.showtoppanelmyinfo = ((i & 0x4) != 0);
        this.tourrnddropenabled = ((i & 0x8) != 0);
        this.reduceStrWidth_Adv = ((i & 0x10) != 0);
        this.shortnavi = ((i & 0x20) != 0);
        this.shopnpcrotenenabled = ((i & 0x40) != 0);
        this.writepresetguildiconenabled = ((i & 0x80) != 0);
        this.mailsortenabled = ((i & 0x100) != 0);
        this.newstoryepisode2enabled = ((i & 0x200) != 0);
        this.gg_auth_checkerror_enable = ((i & 0x400) != 0);
        this.skinchatenabled = ((i & 0x800) != 0);
        this.fullscreenminimizationenabled = ((i & 0x2000) != 0);
        this.clockspeedcheckenabled = ((i & 0x4000) != 0);
        this.guardianrnddropenabled = ((i & 0x8000) != 0);
        this.titleaccountregistrationenabled = ((i & 0x10000) != 0);
        this.fairygachaenabled = ((i & 0x20000) != 0);
        this.usewidescreen = ((i & 0x40000) != 0);
        this.guardmissionenabled = ((i & 0x80000) != 0);
        this.ganlclienttracking = ((i & 0x100000) != 0);
        this.selectablecountry = ((i & 0x200000) != 0);
        this.multipleplaybingo = ((i & 0x400000) != 0);
        this.mixslotenabled = ((i & 0x800000) != 0);
        this.serialfreeinputenabled = ((i & 0x1000000) != 0);
        this.gachamachinemoveenabled = ((i & 0x2000000) != 0);
        this.rnddropenabled = ((i & 0x4000000) != 0);
        this.registrationipcheck = ((i & 0x20000000) != 0);
        this.npcprocesseventlog = ((i & 0x40000000) != 0);
        this.shopcouponenabled = ((i & Integer.MIN_VALUE) != 0);

        i = in.readInt();
        this.useaccslog = ((i & 0x1) != 0);
        this.newusernotregisterfromportal = ((i & 0x2) != 0);
        this.idcchanneling = ((i & 0x4) != 0);
        this.concheckenabled = ((i & 0x8) != 0);
        this.potentialgearpartsstatus = ((i & 0x20) != 0);

        this.trademode = in.readByte();
        this.showgachaprizecount = in.readByte();
        this.gacha_roten_ticket_limit = in.readByte();

        this.roommasterkicktime = in.readInt();

        this.autochselthreshold = in.readShort();

        this.guildbattlesettingdata = (GuildBattleSetting.Data) in.readObject();

        this.questevent = (QuestEventData[][]) in.readObject();

        this.skinbbs_soldpayback = (String) in.readObject();

        this.skinbbs_deleteminute = in.readInt();

        this.basemixcost = in.readInt();

        this.edituserdatavalidtime = in.readInt();

        this.playtimelimit = (PlayTimeLimit) in.readObject();

        this.skinbbs_maxregister = in.readShort();

        this.calendar = (CalendarData) in.readObject();

        this.newmedalrate = in.readShort();
        this.pwsaltgeturl = (String) in.readObject();

        this.alchemycost = in.readInt();
        this.potentialcost = in.readInt();

        this.savelogintype = in.readShort();
        this.rankitemmode = in.readByte() & 0xFF;

        this.sdoaip = (SDOAIPData) in.readObject();

        this.shortcut = (UserShortcuts.ChatShortcut[]) readArray(UserShortcuts.ChatShortcut.class, in);

        this.questbuy = (QuestBuyData[]) readArray(QuestBuyData.class, in);

        this.landata = (LanData) in.readObject();
        this.automatchdata = (AutoMatchData) in.readObject();

        this.disableeventid = (int[]) in.readObject();

        this.friendlistmax = in.readInt();
        this.blacklistmax = in.readInt();

        this.wpgitemprocess = (String) in.readObject();

        this.skinbbs_maxregister_one = in.readShort();

        this.skinbbs_poppoint_buy = in.readShort();
        this.skinbbs_poppoint_vote = in.readShort();

        this.adjustbalanceflags = in.readInt();

        this.deciphermode = (String) in.readObject();

        this.battlepreloadmaxtime = in.readInt();

        this.checkmedalinterval = in.readInt();

        this.assistanttype = in.readInt();

        this.rankupquest = (QuestData.RankupQuestData[]) readArray(QuestData.RankupQuestData.class, in);

        this.gg_init = (String) in.readObject();
        this.gg_interval = in.readShort();
        this.gg_server_interval = in.readShort();
        this.gg_receive_interval = in.readShort();
        this.gg_tester = (String) in.readObject();

        this.tourkeyroomrank = in.readInt();

        this.replacefont = (String) in.readObject();

        this.cacheclearversion = (String) in.readObject();

        this.skinbbs_usablerank = in.readByte();

        this.sendmailchecktime = in.readInt();
        this.autoopenshuttertime = in.readInt();

        this.tutorialshopbuynum = in.readInt();

        this.guardmissionheal = in.readShort();

        this.restrictiongacharmchargeprice = in.readInt();
        this.gacha_dailyfree_timeoffset = in.readByte();

        this.ganlsetting = (GAnlSetting) in.readObject();

        this.selectcountry = (String[][]) in.readObject();

        this.questionary = (QuestionaryData) in.readObject();

        this.channelselecttype = in.readByte() & 0xFF;

        this.mixslotnum = in.readShort();

        this.potentialskilldata = (PotentialSkillData[]) in.readObject();
    }

    public static void writeArray(Externalizable[] a, ObjectOutput out) throws IOException {
        if (a == null) {
            StreamTool.writePackedInt(0, out);
            return;
        }
        StreamTool.writePackedInt(a.length, out);
        int a8 = (a.length + 7) / 8 * 8;
        for (int l = 0; l < a8; l += 8) {
            int mask = 0;
            int j;
            for (j = 0; j < 8; j++) {
                if (l + j < a.length && a[l + j] != null)
                    mask |= 1 << j;
            }
            out.writeByte(mask);
            for (j = 0; j < 8; j++) {
                if (l + j < a.length && a[l + j] != null)
                    a[l + j].writeExternal(out);
            }
        }
    }

    public static Object[] readArray(Class<?> c, ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            int num = StreamTool.readPackedInt(in);
            Externalizable[] a = (Externalizable[]) Array.newInstance(c, num);
            int a8 = (a.length + 7) / 8 * 8;
            for (int l = 0; l < a8; l += 8) {
                int mask = in.readByte() & 0xFF;
                for (int j = 0; j < 8; j++) {
                    if ((mask & 1 << j) != 0) {
                        a[l + j] = (Externalizable) c.newInstance();
                        a[l + j].readExternal(in);
                    }
                }
            }
            return (Object[]) a;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    public static void writeText(String s, ObjectOutput out) throws IOException {
        GameSetting set = getSetting();
        if (s.length() == 0) {
            StreamTool.writePackedInt(0, out);
            return;
        }
        byte[] b = s.getBytes(set.charset.equals("sjis") ? "utf8" : set.charset);
        StreamTool.writePackedInt(b.length, out);
        out.write(b);
    }

    public static String readText(ObjectInput in) throws IOException {
        int num = StreamTool.readPackedInt(in);
        if (num == 0)
            return "";
        byte[] b = new byte[num];
        in.readFully(b);
        GameSetting set = getSetting();
        return new String(b, set.charset.equals("sjis") ? "utf8" : set.charset);
    }

    private static int[] subarray(int[] i, int len) {
        int[] ret = new int[len];
        System.arraycopy(i, 0, ret, 0, len);
        return ret;
    }

    public static GameSetting getSetting() {
        return set;
    }

    public static void initSetting(File f) {
        try {
            initSettingForMakePkg(f);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("GameSetting reading failed...");
            System.exit(1);
        } catch (CompileError ce) {
            ce.printStackTrace();
            System.err.println("GameSetting reading failed...");
            System.exit(1);
        }
    }

    public static void initSettingForMakePkg(File f) throws Exception, CompileError {
        // if (f.getName().endsWith(".kxr")) {
        // if (ga2.isStreaming = true) {
        // set = readKxr(f.getPath());
        // set.dicts = new HashMap<Object, Object>();
        // set.finish();
        // ObjectReader.charset = set.charset;
        // } else {
        // set = readKxr(f.getPath());
        // }
        // set.finish();
        // ObjectReader.charset = set.charset;
        // } else {
        // int t = Clock.time();
        // ObjectReader.charset = defchar;
        // ObjectReader r = new ObjectReader();
        // r.readFile(null, f);
        // set = (GameSetting) r.getReadObject();
        // set.finish();
        // System.out.println("initSetting time : " + (Clock.time() - t));
        // }
        if (f.getName().endsWith(".kxr")) {
            set = readKxr(f.getPath());
            set.finish();
            ObjectReader.charset = set.charset;
        } else {
            int t = Clock.time();
            ObjectReader.charset = defchar;
            ObjectReader r = new ObjectReader();
            r.readFile(null, f);
            set = (GameSetting) r.getReadObject();
            set.finish();
            System.out.println("initSetting time : " + (Clock.time() - t));
        }
    }

    public static void initSettingForLauncher(File f) throws Exception, CompileError {
        if (f.getName().endsWith(".kxr")) {
            set = readKxr(f.getPath());

            set.localres = ResourceManager.getResource("/lang/" + set.language);
        }
    }

    public void writeKxr(String fn) throws IOException {
        KxrFile kxr = KxrFile.openClean(fn, "pwpw");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ExternalizerOutputStream eos = new ExternalizerOutputStream(baos, false);
        eos.writeObject(this);
        eos.close();
        kxr.create(kxr.getRoot(), "gs", baos.toByteArray(), 0);
        kxr.close();
    }

    public void override(File f) throws Exception, CompileError {
        int t = Clock.time();
        ObjectReader.charset = this.charset;
        ObjectReader r = new ObjectReader();
        r.readFile(this, f);
        finish();
        System.out.println("overrideSetting time : " + (Clock.time() - t));
    }

    static GameSetting readKxr(String fn) throws IOException, ClassNotFoundException {
        GameSetting gs = null;

        KxrFile kxr = KxrFile.open(fn, "pwpw", "r");
        KxrFile.Entry e = kxr.getRoot().getEntry("gs");
        int size = e.size();
        byte[] b = new byte[size];
        e.getData(b);
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ExternalizerInputStream eis = new ExternalizerInputStream(bais, false);
        gs = (GameSetting) eis.readObject();

        e = kxr.getRoot().getEntry("#version.gs.kxr");
        if (e != null) {
            size = e.size();
            e.getData(b);
            ExternalizerInputStream dis = new ExternalizerInputStream(new ByteArrayInputStream(b));
            gs.version = ((Number) dis.readObject()).intValue();
        }
        kxr.close();

        setDefaultLanguage(String.valueOf(gs.language) + ":" + gs.charset);

        return gs;
    }

    public static void main(String[] args) throws Exception {
        Externalizer.setDefault(AmpedExternalizer.getExternalizer());
        ResourceManager.addPath("resource");
        initSettingForMakePkg(new File("conf/game.conf"));
        set.writeKxr(args[0]);
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\setting\GameSetting.
 * class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
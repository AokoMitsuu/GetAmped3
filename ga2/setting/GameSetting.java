package ga2.setting;

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
    /* 25 */ public static String deflang = "ja";
    private static GameSetting set;
    /* 26 */ public static String defchar = "sjis";

    /* 28 */ private static HashMap localemap = new HashMap<Object, Object>();
    public boolean printoff;
    static {
        /* 30 */ localemap.put("ja", Locale.JAPAN);

        /* 32 */ localemap.put("us", Locale.US);
        /* 33 */ localemap.put("cn", Locale.CHINA);
        /* 34 */ localemap.put("kr", Locale.KOREA);
        /* 35 */ localemap.put("tw", Locale.TAIWAN);
    }

    public void print(Object s) {
        /* 40 */ if (this.version < 0 && !this.printoff) {
            /* 41 */ System.out.print(s);
        }
    }

    public void println(Object s) {
        /* 48 */ if (this.version < 0 && !this.printoff) {
            /* 49 */ System.out.println(s);
        }
    }

    public static void printStackTrace() {
        /* 54 */ printStackTrace("");
    }

    public static void printStackTrace(Object str) {
        /* 58 */ (new Exception("Stack trace: " + str)).printStackTrace();
    }

    /* 61 */ public int version = -1;

    /* 63 */ public String language = deflang;
    /* 64 */ public String charset = defchar;

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
    /* 144 */ public HashMap kwtinfo = new HashMap<Object, Object>();
    /* 145 */ public HashMap dicts = new HashMap<Object, Object>();

    public PlayerScriptInfo[] scpinfo;

    public Inttable area;

    public GuildSetting guild;

    /* 153 */ public ShopLineupData shoplineup = new ShopLineupData();
    /* 154 */ public GachaLineupData gachalineup = new GachaLineupData();

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
    /* 229 */ public byte showgachaprizecount = 5;
    /* 230 */ public byte gacha_roten_ticket_limit = 90;

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
    /* 279 */ public int basemixcost = 20;

    /* 281 */ public int alchemycost = 100;

    /* 283 */ public int potentialcost = 100;

    public boolean plainpassword;

    public boolean loginquest;

    /* 290 */ public int edituserdatavalidtime = -1;

    public boolean disablegetnewmedal;
    /* 293 */ public int newmedalrate = 200;

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
    /* 351 */ public int friendlistmax = 1000;
    /* 352 */ public int blacklistmax = 1000;

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
    /* 408 */ public int tourkeyroomrank = -1;

    public boolean dogfightenabled;

    public boolean shopnpcrotenenabled;

    public boolean tourrnddropenabled;

    public boolean guardianrnddropenabled;

    public boolean rnddropenabled;
    public boolean linebreakavoidwordchange;
    public boolean reduceStrWidth_Adv;
    public boolean showtoppanelmyinfo = true;
    public boolean shortnavi;
    /* 423 */ public String replacefont = null;

    /* 425 */ public String cacheclearversion = null;

    public boolean writepresetguildiconenabled;

    public boolean mailsortenabled;

    public boolean newstoryepisode2enabled;

    public boolean skinchatenabled;

    /* 435 */ public int sendmailchecktime = 0;

    public int autoopenshuttertime;
    /* 438 */ public int tutorialshopbuynum = 1;

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
        /* 493 */ if (!(getSetting()).userid_email)
            return false;
        /* 494 */ return KotoriUtil.isEmail(str);
    }

    public static boolean isIDCUid(String str) {
        /* 498 */ if (!(getSetting()).idcchanneling)
            return false;
        /* 499 */ return KotoriUtil.isIDCUid(str);
    }

    public static void setDefaultLanguage(String lang) {
        /* 503 */ int i = lang.indexOf(':');
        /* 504 */ if (i < 0) {
            /* 505 */ deflang = lang;
            /* 506 */ defchar = null;
        } else {
            /* 508 */ deflang = lang.substring(0, i);
            /* 509 */ defchar = lang.substring(i + 1);
        }
    }

    public void setLanguage(String lang) {
        /* 514 */ int i = lang.indexOf(':');
        /* 515 */ if (i < 0) {
            /* 516 */ this.language = lang;
            /* 517 */ this.charset = null;
        } else {
            /* 519 */ this.language = lang.substring(0, i);
            /* 520 */ this.charset = lang.substring(i + 1);
        }
    }

    public boolean isLanguage(String lang) {
        /* 525 */ return this.language.equals(lang);
    }

    public boolean isLanguage(String... lang) {
        /* 529 */ if (lang != null)
            /* 530 */ for (int l = 0; l < lang.length; l++) {
                /* 531 */ if (isLanguage(lang[l]))
                    return true;

            }
        /* 534 */ return false;
    }

    public boolean isLoggingTalkFlags() {
        /* 541 */ return false;
    }

    public boolean isNavigationImmediate() {
        /* 548 */ return !isLanguage("kr");
    }

    public boolean isEventExitEnabled() {
        /* 555 */ return isLanguage("kr");
    }

    public boolean isMenuEffectEnabled() {
        /* 562 */ return isLanguage("kr");
    }

    public boolean isOrderEpisodeEnabled() {
        /* 566 */ return isLanguage("kr");
    }

    /* 569 */ private TimeZone tz = null;

    public TimeZone getTimeZone(boolean reflesh) {
        /* 571 */ if (reflesh || this.tz == null) {
            /* 572 */ if (KotoriUtil.isNotEmpty(this.timezone)) {
                /* 573 */ this.tz = TimeZone.getTimeZone(this.timezone);
            }
            /* 575 */ if (this.tz == null)
                this.tz = TimeZone.getDefault();
        }
        /* 577 */ return this.tz;
    }

    public TimeZone getTimeZone() {
        /* 579 */ return getTimeZone(false);
    }

    public long getTimeZoneTime() {
        /* 582 */ return ServerTime.getTimeZoneTime(getTimeZone());
    }

    public Locale getLocale() {
        /* 586 */ return (this.locale != null) ? this.locale : Locale.getDefault();
    }

    private void makeFlagTable() {
        /* 592 */ String[] files = (String[]) null;
        /* 593 */ if (this.localres == null) {

            /* 595 */ File resdir = new File("resource/lang/" + set.language + "/icon/flag");
            /* 596 */ files = resdir.list();
            /* 597 */ if (files == null || files.length == 0) {
                return;
            }
        } else {
            /* 601 */ Resource res = getSetting().getLocalResource("icon/flag");
            /* 602 */ if (res == null)
                /* 603 */ return;
            Resource[] list = res.getList();
            /* 604 */ files = new String[list.length];
            /* 605 */ for (int j = 0; j < list.length; j++) {
                /* 606 */ files[j] = list[j].getName();
            }
        }
        /* 609 */ this.area = new Inttable();
        /* 610 */ for (int i = 0; i < files.length; i++) {
            /* 611 */ String f = files[i];
            /* 612 */ int idx = f.indexOf(".");
            /* 613 */ String flag = null;
            /* 614 */ if (idx > 1) {
                /* 615 */ flag = f.substring(0, idx);
                /* 616 */ this.area.put(flag.hashCode(), flag);
                /* 617 */ set.println("makeFlagTable put hash:" + flag.hashCode() + ", flag:" + flag);
            }
        }
    }

    public String getFlag(int fhash) {
        /* 623 */ if (this.area == null)
            return "";
        /* 624 */ String flag = (String) this.area.get(fhash);
        /* 625 */ return (flag != null) ? flag : "";
    }

    public boolean enabledRankEquip() {
        /* 630 */ return (this.rankitemmode >= 1);
    }

    public boolean enabledChallengeRank() {
        /* 634 */ return false;
    }

    public boolean isPrivately(int kind, int id) {
        /* 639 */ AmpedItem ai = getAmpedItem(kind, id);
        /* 640 */ return !(ai != null && !ai.isPrivately());
    }

    public boolean isBoostEnable(int chiket, UserItems.Item item) {
        /* 644 */ return this.dropboost[chiket].isBoostEnable(item);
    }

    public boolean isDisableEvent(int eid) {
        /* 651 */ if (this.disableeventid == null)
            return false;
        /* 652 */ for (int i = 0; i < this.disableeventid.length; i++) {
            /* 653 */ if (this.disableeventid[i] == eid)
                return true;
        }
        /* 655 */ return false;
    }

    public StyleData getStyle(int id) {
        /* 659 */ return (id < 0 || id >= this.style.length) ? null : this.style[id];
    }

    public StyleData.Head getHead(int id) {
        /* 663 */ return (id < 0 || id >= this.head.length) ? null : this.head[id];
    }

    public StyleData.Face getFace(int id) {
        /* 667 */ return (id < 0 || id >= this.face.length) ? null : this.face[id];
    }

    public StyleData.Body getBody(int id) {
        /* 671 */ return (id < 0 || id >= this.body.length) ? null : this.body[id];
    }

    public StyleData.Build getBuild(int id) {
        /* 675 */ return this.build[id];
    }

    public StyleData.Build findBuild(String name) {
        /* 679 */ for (int l = 0; l < this.build.length; l++) {
            /* 680 */ if (this.build[l] != null && name.equals((this.build[l]).name))
                return this.build[l];
        }
        /* 682 */ return null;
    }

    public StyleData.Decoration getDecoration(int id) {
        /* 686 */ return (id < 0 || id >= this.deco.length) ? null : this.deco[id];
    }

    public AccessoryData getAccs(int id) {
        /* 690 */ return (id < 0 || id >= this.accs.length) ? null : this.accs[id];
    }

    public SkillData getSkill(int id) {
        /* 694 */ return (id < 0 || id >= this.skill.length) ? null : this.skill[id];
    }

    public WeaponData getWeapon(int id) {
        /* 698 */ return (id < 0 || id >= this.weapon.length) ? null : this.weapon[id];
    }

    public VehicleData getVehicle(int id) {
        /* 702 */ return this.vehicle[id];
    }

    public EmotionData getEmotion(int id) {
        /* 706 */ if (id < 0) {
            /* 707 */ int sid = -id;
            /* 708 */ return (sid >= this.semo.length) ? null : this.semo[sid];
        }
        /* 710 */ return (id >= this.emo.length) ? null : this.emo[id];
    }

    public OfficialSkinData getOfficialSkin(int id) {
        /* 715 */ return (id < 0 || id >= this.officialskins.length || this.officialskins[id] == null) ? null
                : this.officialskins[id];
    }

    public String getSDOAEntranceName(int entranceid) {
        /* 719 */ for (int i = 0; i < this.sdoaip.login.length; i++) {
            /* 720 */ if (entranceid == (this.sdoaip.login[i]).entranceid) {
                /* 721 */ return (this.sdoaip.login[i]).name;
            }
        }
        /* 724 */ return "";
    }

    public int emo(String scm) {
        int l;
        /* 728 */ for (l = 0; l < this.emo.length; l++) {
            /* 729 */ if (this.emo[l] != null && (this.emo[l]).scp.equals(scm))
                return l;
        }
        /* 731 */ for (l = 0; l < this.semo.length; l++) {
            /* 732 */ if (this.semo[l] != null && (this.semo[l]).scp.equals(scm))
                return -l;
        }
        /* 734 */ return 0;
    }

    public UserShortcuts.ChatShortcut getChatShortcut(int id) {
        /* 738 */ return (id >= 0 && id < this.shortcut.length) ? this.shortcut[id] : null;
    }

    public NPCInfo getNPCInfo(int id) {
        /* 742 */ return (id >= 0 && id < this.npc.length) ? this.npc[id] : null;
    }

    public static int id(String stageCode, int ofs) {
        /* 746 */ return NPCInfo.id(stageCode, ofs);
    }

    public TournamentData getTournament(int id) {
        /* 750 */ return this.tour[id];
    }

    /* 753 */ private int max_bannerid = -1;
    /* 754 */ public static final int[] refTornamentData = new int[] {

            /* 756 */ 0, 1,
            /* 757 */ 8,
            /* 758 */ 9,
            /* 759 */ 5 };

    public int getBanneridMax() {
        /* 762 */ if (this.max_bannerid != -1)
            return this.max_bannerid;

        /* 764 */ for (int i = 0; i < refTornamentData.length; i++) {
            /* 765 */ TournamentData t = getTournament(refTornamentData[i]);
            /* 766 */ for (int j = 0; j < t.stageTbl.length; j++) {
                /* 767 */ TournamentData.Stage stage = t.stage[t.stageTbl[j]];
                /* 768 */ if (stage.bannerid < 0)
                /* 769 */ {
                    for (int k = 0; k < stage.randomstage.length; k++) {
                        /* 770 */ if (stage.randomstage[k] > this.max_bannerid)
                            this.max_bannerid = stage.randomstage[k];

                    }
                }
                /* 773 */ else if (stage.bannerid > this.max_bannerid) {
                    this.max_bannerid = stage.bannerid;
                }

            }
        }
        /* 777 */ return this.max_bannerid;
    }

    public GuardianData getGuardian() {
        /* 781 */ return this.guardian[0];
    }

    public Object getLounge(int id) {
        /* 785 */ return (id >= 0 && id < this.lounge.length) ? this.lounge[id] : null;
    }

    public StreetNPCData getStreetNPCData(int id) {
        /* 789 */ return (id >= 0 && id < this.stnpcset.length) ? this.stnpcset[id] : null;
    }

    public UnitedfightNPCData getUnitedfightNPCData() {
        /* 793 */ return this.ufnpcset;
    }

    public int[] getItemSortData(byte kind) {
        /* 797 */ return this.itemsort.getSortData(kind);
    }

    public String getSortName(int id) {
        /* 801 */ return this.itemsort.getSortName(id);
    }

    public int getMailSortKind() {
        /* 805 */ return this.mailsort.getSortKind();
    }

    public String getMailSortName(int id) {
        /* 809 */ return this.mailsort.getSortName(id);
    }

    public PracticeData getPractice() {
        /* 813 */ return this.practice;
    }

    public LanData getLanData() {
        /* 817 */ return this.landata;
    }

    public AutoMatchData getAutoMatchData() {
        /* 821 */ return this.automatchdata;
    }

    public ArcadeData getArcade(int id) {
        /* 825 */ return (id >= 0 && id < this.arcade.length) ? this.arcade[id] : null;
    }

    public QuestEventData[] getQuestEventList(int type) {
        /* 829 */ if (this.questevent.length <= type)
            return null;
        /* 830 */ return this.questevent[type];
    }

    public QuestionaryData getQuestionaryData() {
        /* 834 */ return this.questionary;
    }

    public EventSceneData getEventScene(int id) {
        /* 838 */ if (this.event == null) {
            /* 839 */ return (EventSceneData) KotoriUtil.uncompress(this.eventbytes[id]);
        }
        /* 841 */ return this.event[id];
    }

    public PotentialSkillData getPotentialSkillData(int id) {
        /* 845 */ for (int i = 0; i < this.potentialskilldata.length; i++) {
            /* 846 */ if (this.potentialskilldata[i].chkPotentialSkill(id))
                return this.potentialskilldata[i];
        }
        /* 848 */ return null;
    }

    public UserItems.Item[] getEventPresent(int eventid, int presentindex) {
        try {
            /* 853 */ EventSceneData ev = getEventScene(eventid);
            /* 854 */ UserItems.Item[] p = (UserItems.Item[]) ev.present[presentindex];
            /* 855 */ return UserItems.convertTreasure(p);
            /* 856 */ } catch (Exception ex) {
            /* 857 */ return null;
        }
    }

    public int getEventRank(int eventid) {
        try {
            /* 863 */ EventSceneData ev = getEventScene(eventid);

            /* 865 */ if (ev == null)
                return -1;
            /* 866 */ return ev.rank;
            /* 867 */ } catch (Exception ex) {
            /* 868 */ return -1;
        }
    }

    public int getEventTalkflag(int eventid) {
        try {
            /* 874 */ EventSceneData ev = getEventScene(eventid);

            /* 876 */ if (ev == null)
                return -1;
            /* 877 */ return ev.talkflag;
            /* 878 */ } catch (Exception ex) {
            /* 879 */ return -1;
        }
    }

    public UserItems.Item[] getEventPresent(int eventid) {
        try {
            /* 886 */ EventSceneData ev = getEventScene(eventid);
            /* 887 */ int count = 0;
            /* 888 */ for (int i = 0; i < ev.present.length; i++) {
                /* 889 */ count += ((UserItems.Item[]) ev.present[i]).length;
            }
            /* 891 */ UserItems.Item[] p = new UserItems.Item[count];
            /* 892 */ count = 0;
            /* 893 */ for (int j = 0; j < ev.present.length; j++) {
                /* 894 */ UserItems.Item[] _p = (UserItems.Item[]) ev.present[j];
                /* 895 */ for (int m = 0; m < _p.length; m++) {
                    /* 896 */ p[count] = _p[m];
                    /* 897 */ count++;
                }
            }
            /* 900 */ return UserItems.convertTreasure(p);
            /* 901 */ } catch (Exception ex) {
            /* 902 */ return null;
        }
    }

    public UserItems.Item[] getNpcPresent(int npcid, int presentindex) {
        try {
            /* 908 */ NPCInfo npcinfo = getNPCInfo(npcid);
            /* 909 */ UserItems.Item[] p = (UserItems.Item[]) npcinfo.talkpresent[presentindex];
            /* 910 */ return UserItems.convertTreasure(p);
            /* 911 */ } catch (Exception ex) {
            /* 912 */ return null;
        }
    }

    public UserItems.Item[] getQuestPresent(int qid, int presentindex) {
        try {
            /* 918 */ QuestData qd = getQuest(qid);
            /* 919 */ UserItems.Item[] p = (UserItems.Item[]) qd.talkpresent[presentindex];
            /* 920 */ return UserItems.convertTreasure(p);
            /* 921 */ } catch (Exception ex) {
            /* 922 */ return null;
        }
    }

    public UserItems.Item[] getQuestionaryPresent(int presentindex) {
        try {
            /* 928 */ UserItems.Item[] p = (UserItems.Item[]) this.questionary.talkpresent[presentindex];
            /* 929 */ return UserItems.convertTreasure(p);
            /* 930 */ } catch (Exception ex) {
            /* 931 */ return null;
        }
    }

    public MaterialData getMaterial(int id) {
        /* 936 */ return (id < 0 || id >= this.mat.length) ? null : this.mat[id];
    }

    public QuestData getQuest(int id) {
        /* 941 */ return (id < 0 || id >= this.quest.length) ? null : this.quest[id];
    }

    public QuestData.RankupQuestData getRankupQuest(UserData ud) {
        /* 945 */ for (int i = 0; i < this.rankupquest.length; i++) {
            /* 946 */ if (this.rankupquest[i] != null &&
            /* 947 */ !(this.rankupquest[i]).disable &&
            /* 948 */ !this.rankupquest[i].isClear(ud) && this.rankupquest[i].checkSubFlags(ud)
                    && !isDisableEvent((this.rankupquest[i]).eventid)) {
                /* 949 */ return this.rankupquest[i];
            }
        }
        /* 952 */ return null;
    }

    public int getPreRankupQuest(int r) {
        /* 956 */ if (this.rankupquest == null)
            return 0;

        /* 958 */ Comparator<? super Integer> c = new Comparator() {
            public int compare(Object obj, Object obj1) {
                /* 960 */ if (obj instanceof Integer && obj1 instanceof Integer) {
                    /* 961 */ int t1 = ((Integer) obj).intValue();
                    /* 962 */ int t2 = ((Integer) obj1).intValue();
                    /* 963 */ return (t1 == t2) ? 0 : ((t1 > t2) ? 1 : -1);
                }
                /* 965 */ return 0;
            }
        };

        /* 969 */ ArrayList<Integer> tmp = new ArrayList<Integer>();
        /* 970 */ for (int i = 0; i < this.rankupquest.length; i++) {
            /* 971 */ if (!(this.rankupquest[i]).disable
                    && !tmp.contains(Integer.valueOf(this.rankupquest[i].getRank()))) {
                /* 972 */ tmp.add(Integer.valueOf(this.rankupquest[i].getRank()));
            }
        }

        /* 976 */ if (tmp.size() > 0) {
            /* 977 */ Integer[] ret = tmp.<Integer>toArray(new Integer[tmp.size()]);
            /* 978 */ Arrays.sort(ret, c);

            /* 980 */ if (r <= ret[0].intValue())
                return -1;
            /* 981 */ for (int j = 1; j < ret.length; j++) {
                /* 982 */ if (r <= ret[j].intValue()) {
                    /* 983 */ return ret[j - 1].intValue();
                }
            }
        }
        /* 987 */ return 0;
    }

    public ChallengeData getChallenge(int id) {
        /* 991 */ return (id < 0 || id >= this.challenge.length) ? null : this.challenge[id];
    }

    public QuestBuyData getQuestBuyData(int id) {
        /* 995 */ return (id < 0 || id >= this.questbuy.length) ? null : this.questbuy[id];
    }

    public int getUnitProgress(int id) {
        /* 999 */ QuestData q = getQuest(id);
        /* 1000 */ if (q == null || q.arg == null)
            return 0;
        /* 1001 */ if (q.arg instanceof int[][]) {
            /* 1002 */ int[][] data = (int[][]) q.arg;
            /* 1003 */ if (data.length == 0)
                return 0;
            /* 1004 */ return q.ordernum / data.length;
        }
        /* 1006 */ return 0;
    }

    public int getStateProgress(int id, int progress) {
        /* 1010 */ int unit = getUnitProgress(id);
        /* 1011 */ if (unit == 0)
            return -1;

        /* 1013 */ return progress % unit;
    }

    public MyRoomStage getMyRoom(int id) {
        /* 1017 */ return (id < 0 || id >= this.myroom.length) ? null : this.myroom[id];
    }

    public MyRoomFurniture getFurniture(int id) {
        /* 1021 */ return (id < 0 || id >= this.furniture.length) ? null : this.furniture[id];
    }

    public MyRoomTutorialData getMyRoomTutorialData(int id) {
        /* 1025 */ return (id >= 0 && this.myroomtutorial != null && id < this.myroomtutorial.length)
                ? this.myroomtutorial[id]
                : null;
    }

    public ETCItem getETCItem(int id) {
        /* 1029 */ return (id < 0 || id >= this.etc.length) ? null : this.etc[id];
    }

    public EventItem getEventItem(int id) {
        /* 1033 */ return (id < 0 || id >= this.eventitem.length) ? null : this.eventitem[id];
    }

    public boolean checkRank(int id) {
        /* 1037 */ RankData.Rank d = getRank(id);
        /* 1038 */ return (d != null && !d.isPrivately());
    }

    public RankData.Rank getRank(int id) {
        /* 1042 */ return (id < 0 || id >= this.rank.rank.length) ? null : this.rank.rank[id];
    }

    public RankData.Rank getFormulaRank() {
        /* 1046 */ return getRank(50);
    }

    public BingoData getBingo(int id) {
        /* 1050 */ return (id < 0 || id >= this.bingo.length) ? null : this.bingo[id];
    }

    public NPCItem getNPCItem(int id) {
        /* 1054 */ return (id < 0 || id >= this.npcitem.length) ? null : this.npcitem[id];
    }

    public Object[] getStyleGroup(int group) {
        /* 1058 */ ArrayList<StyleData> a = new ArrayList();
        /* 1059 */ for (int i = 0; i < this.style.length;) {
            if ((getStyle(i)).group == group)
                a.add(getStyle(i));
            i++;
        }
        /* 1060 */ return (a.size() == 0) ? null : a.toArray();
    }

    public Object[] getStyleID(int group) {
        /* 1064 */ ArrayList<Integer> a = new ArrayList();
        /* 1065 */ for (int i = 0; i < this.style.length;) {
            if ((getStyle(i)).group == group)
                a.add(Integer.valueOf(i));
            i++;
        }
        /* 1066 */ return (a.size() == 0) ? null : a.toArray();
    }

    public AmpedItem getAmpedItem(int kind, int id) {
        /* 1070 */ switch (kind) {
            case 0:
                /* 1071 */ return (AmpedItem) getStyle(id);
            /* 1072 */ case 5:
                return (AmpedItem) getAccs(id);
            /* 1073 */ case 1:
                return (AmpedItem) getHead(id);
            /* 1074 */ case 2:
                return (AmpedItem) getFace(id);
            /* 1075 */ case 3:
                return (AmpedItem) getBody(id);
            /* 1076 */ case 4:
                return (AmpedItem) getDecoration(id);
            case 8:
            case 18:
                /* 1078 */ return (AmpedItem) getEmotion(id);
            /* 1079 */ case 11:
                return (AmpedItem) getQuest(id);
            /* 1080 */ case 7:
                return (AmpedItem) getWeapon(id);
            /* 1081 */ case 6:
                return (AmpedItem) getSkill(id);
            /* 1082 */ case 12:
                return (AmpedItem) getMaterial(id);
            /* 1083 */ case 13:
                return (AmpedItem) getFurniture(id);
            /* 1084 */ case 14:
                return (AmpedItem) getMyRoom(id);
            /* 1085 */ case 10:
                return (AmpedItem) getETCItem(id);
            /* 1086 */ case 15:
                return (AmpedItem) getEventItem(id);
            /* 1087 */ case 16:
                return (AmpedItem) getVoiceSet(id);
            /* 1088 */ case 21:
                return (AmpedItem) FluidSetting.getSetting().getItemPack(id);
            /* 1089 */ case 26:
                return (AmpedItem) FluidSetting.getSetting().getAutoSetItemPack(id);
            /* 1090 */ case 17:
                return (AmpedItem) getRank(id);
            case 22:
                /* 1092 */ return (AmpedItem) new PointItem(0, id);
            /* 1093 */ case 23:
                return (AmpedItem) new PointItem(1, id);
            /* 1094 */ case 9:
                return (id == 0) ? null : (AmpedItem) new SkinItem(id);
            /* 1095 */ case 24:
                return (AmpedItem) getQuestBuyData(id);
            /* 1096 */ case 25:
                return (AmpedItem) getNPCItem(id);
        }

        /* 1098 */ throw new NullPointerException("INVALID KIND : " + kind);
    }

    public RandomTreasure[] getRandomTreasure() {
        /* 1102 */ return this.randtreasure;
    }

    public RandomTreasure2 getRandomTreasure2() {
        /* 1106 */ return this.randtreasure2;
    }

    public RandomGuardianTreasure[] getRandomGuardianTreasure() {
        /* 1110 */ return this.randguardiantreasure;
    }

    public boolean isUsableSkinBBS(UserData ud) {
        /* 1114 */ if (this.skinbbs_usablerank == 0)
            return true;
        /* 1115 */ if (ud == null || ud.info == null || ud.info.getRank(true) == null)
            return true;

        /* 1117 */ return ((ud.info.getRank(true)).idx >= this.skinbbs_usablerank);
    }

    public FairyGachaList getFairyGachaList(int list) {
        /* 1121 */ if (list < 0 || this.fairygacha == null || list > this.fairygacha.length)
            return null;
        /* 1122 */ return this.fairygacha[list];
    }

    public boolean restrictionGachaRMCharge() {
        /* 1131 */ String key = "$gacha.rmchargeweb.url";
        /* 1132 */ String url = getSetting().getLocalText(key);
        /* 1133 */ if (!url.equals(key) && !url.equals(""))
            return true;
        /* 1134 */ return false;
    }

    public LightData getStageLight(int id) {
        /* 1145 */ return (id < 0 || id >= this.stagelight.length || this.stagelight[id] == null) ? this.stagelight[0]
                : this.stagelight[id];
    }

    public PostEffectData getStageEffect(int id) {
        /* 1149 */ return (id < 0 || id >= this.postefx.length || this.postefx[id] == null) ? null : this.postefx[id];
    }

    public HitEffectData getHitEffect(int id) {
        /* 1153 */ return (id >= 0 && id < this.hitefx.length) ? this.hitefx[id] : null;
    }

    public Resource getLocalResource(String s) {
        /* 1160 */ return this.localres.getResource(s);
    }

    public Object getLocalContent(String s) {
        /* 1164 */ Resource r = this.localres.getResource(s);
        /* 1165 */ return (r == null) ? null : r.getContent();
    }

    public Object getSearchedContent(String s) {
        /* 1170 */ Resource r = this.localres.getResource(s);
        /* 1171 */ return (r == null) ? ResourceManager.getContent("/" + s) : r.getContent();
    }

    public String getLocalText(String key) {
        /* 1175 */ String s = (String) this.dicts.get(key);
        /* 1176 */ return (s == null) ? key : s;
    }

    public String getLocalTextOrNull(String key) {
        /* 1180 */ String s = (String) this.dicts.get(key);
        /* 1181 */ return (s == null) ? null : s;
    }

    public String getLocalText(String key, Object... arg) {
        /* 1185 */ String s = (String) this.dicts.get(key);
        /* 1186 */ return (s == null) ? key : (new Formatter()).format(s, arg).toString();
    }

    public KWTInfo kwtinfo(String pf) {
        /* 1190 */ KWTInfo i = (KWTInfo) this.kwtinfo.get(pf);
        /* 1191 */ if (i == null) {
            /* 1192 */ i = new KWTInfo();
            /* 1193 */ this.kwtinfo.put(pf, i);
        }
        /* 1195 */ return i;
    }

    public KWTInfo getPlatformKWTInfo() {
        /* 1199 */ String os = System.getProperty("os.name");
        /* 1200 */ if (os.startsWith("Windo")) {
            /* 1201 */ return (KWTInfo) this.kwtinfo.get("win32");
        }
        /* 1203 */ if (os.indexOf("Mac OS X") != -1) {
            /* 1204 */ return (KWTInfo) this.kwtinfo.get("mac");
        }
        /* 1206 */ return null;
    }

    public void addDict(String key, String val) {
        /* 1215 */ if (!key.startsWith("$"))
            throw new RuntimeException("Dictionary key must start with \"$\"");
        /* 1216 */ this.dicts.put(key, val);
    }

    /* 1223 */ static final String[][] texcolor = new String[][] { { "y", "w", "b" }, { "bl", "r", "XX" } };

    public void bodyset(int baseid, StyleData.Body basebody) {
        /* 1225 */ if (this.body == null) {
            /* 1226 */ this.body = new StyleData.Body[baseid + 9];
        }
        /* 1228 */ else if (this.body.length < baseid + 9) {
            /* 1229 */ StyleData.Body[] newb = new StyleData.Body[baseid + 9];
            /* 1230 */ System.arraycopy(this.body, 0, newb, 0, this.body.length);
            /* 1231 */ this.body = newb;
        }

        /* 1234 */ for (int l = 0; l < 9; l++) {
            /* 1235 */ StyleData.Body b = basebody.clone();
            /* 1236 */ String btex = b.texture;
            /* 1237 */ for (int j = 0; j < texcolor.length; j++) {
                /* 1238 */ b.texture = String.valueOf(texcolor[j][l / 3]) + "/" + btex + "_" + texcolor[j][l / 3]
                        + (l % 3 + 1);
                /* 1239 */ b.color = j * 3 + l / 3;
                /* 1240 */ if (b.hasTexture()) {
                    /* 1241 */ if (b.texture2 != null) {
                        /* 1242 */ b.texture2 = String.valueOf(texcolor[j][l / 3]) + "/" + b.texture2 + "_"
                                + texcolor[j][l / 3] + (l % 3 + 1);
                    }
                    /* 1244 */ this.body[baseid + l] = b;
                    break;
                }
            }
        }
    }

    public void bodyOverride(int baseid, String name) {
        /* 1252 */ for (int l = baseid; l < baseid + 9; l++) {
            /* 1253 */ if (l < this.body.length && this.body[l] != null)
                /* 1254 */ (this.body[l]).name = name;
        }
    }

    public void headset(int baseid, StyleData.Head basehead) {
        /* 1259 */ headset(baseid, 10, basehead);
    }

    public void headset(int baseid, int range, StyleData.Head basehead) {
        /* 1263 */ if (this.head == null) {
            /* 1264 */ this.head = new StyleData.Head[baseid + range];
        }
        /* 1266 */ else if (this.head.length < baseid + range) {
            /* 1267 */ StyleData.Head[] newh = new StyleData.Head[baseid + range];
            /* 1268 */ System.arraycopy(this.head, 0, newh, 0, this.head.length);
            /* 1269 */ this.head = newh;
        }

        /* 1272 */ for (int l = 0; l < range; l++) {
            /* 1273 */ StyleData.Head h = basehead.clone();
            /* 1274 */ h.texture = String.valueOf(h.texture) + "_" + l + ".agi";
            /* 1275 */ if (h.hasTexture()) {
                /* 1276 */ this.head[baseid + l] = h;
            }
        }
    }

    public void headOverride(int baseid, String name) {
        /* 1282 */ for (int l = baseid; l < baseid + 10; l++) {
            /* 1283 */ if (l < this.head.length && this.head[l] != null)
                /* 1284 */ (this.head[l]).name = name;
        }
    }

    public void decoset(int baseid, StyleData.Decoration basedeco) {
        /* 1289 */ if (this.deco == null) {
            /* 1290 */ this.deco = new StyleData.Decoration[baseid + 10];
        }
        /* 1292 */ else if (this.deco.length < baseid + 10) {
            /* 1293 */ StyleData.Decoration[] newd = new StyleData.Decoration[baseid + 10];
            /* 1294 */ System.arraycopy(this.deco, 0, newd, 0, this.deco.length);
            /* 1295 */ this.deco = newd;
        }

        /* 1298 */ for (int l = 0; l < 10; l++) {
            /* 1299 */ StyleData.Decoration d = basedeco.clone();
            /* 1300 */ d.texture = String.valueOf(d.texture) + "_" + l;
            /* 1301 */ if (d.hasTexture()) {
                /* 1302 */ this.deco[baseid + l] = d;
            }
        }
    }

    public void decoOverride(int baseid, String name) {
        /* 1308 */ for (int l = baseid; l < baseid + 10; l++) {
            /* 1309 */ if (l < this.deco.length && this.deco[l] != null)
                /* 1310 */ (this.deco[l]).name = name;
        }
    }

    public static ShopData newShopData() {
        /* 1315 */ return new ShopData();
    }

    public static DungeonShopData newDungeonShopData() {
        /* 1319 */ return new DungeonShopData();
    }

    public DungeonShopData overrideDungeonShopData() {
        /* 1323 */ return this.dungeonshop;
    }

    public static GlobalShopData newGlobalShopData() {
        /* 1327 */ return new GlobalShopData();
    }

    public GlobalShopData overrideGlobalShopData() {
        /* 1331 */ return this.globalshop;
    }

    public ShopRotenData getShopRotenData() {
        /* 1335 */ return this.shoprotendata;
    }

    public VoiceSet getVoiceSet(int id) {
        /* 1339 */ return this.voiceset[id];
    }

    public String getReplaceFont() {
        /* 1343 */ return this.replacefont;
    }

    public boolean isCacheClearUpdate(int preversion) {
        /* 1347 */ if (this.cacheclearversion == null || this.cacheclearversion.equals("") || preversion == -1)
            return false;
        try {
            /* 1349 */ int v = PackageUpdater.getVersionNumber(this.cacheclearversion);
            /* 1350 */ return (preversion < v && v <= this.version);
            /* 1351 */ } catch (Exception e) {
            /* 1352 */ System.out.println("incorrect preversion...");
            /* 1353 */ e.printStackTrace();

            /* 1355 */ return false;
        }
    }

    public BGMInfo getBGMInfo(String id) {
        /* 1359 */ if (this.bgm == null)
            return null;
        /* 1360 */ for (int l = 0; l < this.bgm.length; l++) {
            /* 1361 */ if (this.bgm[l] != null && (this.bgm[l]).id.equals(id))
                return this.bgm[l];
        }
        /* 1363 */ return null;
    }

    public String getRndBGMID() {
        /* 1367 */ if (this.bgm == null || this.bgm.length < 1)
            return null;
        /* 1368 */ return (this.bgm[KMath.rndi(this.bgm.length - 1)]).id;
    }

    public UserItems getInitialItems() {
        /* 1375 */ return UserItems.getInitialItems(this.initialitems, System.currentTimeMillis());
    }

    public void rangeDemoItems(int num, UserItems.Item baseItem) {
        /* 1382 */ for (int i = 0; i < num; i++) {
            /* 1383 */ if (getAmpedItem(baseItem.kind, baseItem.id + i) != null) {

                /* 1385 */ UserItems.Item demoItem = baseItem.clone();
                /* 1386 */ baseItem.id += i;
                try {
                    /* 1388 */ this.demoitems.addItem(demoItem);
                    /* 1389 */ } catch (Exception exception) {
                }
            }
        }
    }

    private void finish() {
        /* 1396 */ this.localres = ResourceManager.getResource("/lang/" + this.language);

        /* 1398 */ this.locale = (Locale) localemap.get(this.language);
        /* 1399 */ println("Locale : " + this.locale);
        /* 1400 */ println("TimeZone : " + getTimeZone(true));

        /* 1403 */ for (int l = 0; l < this.style.length; l++) {
            /* 1404 */ if (this.style[l] != null)
                finishStyle(l, this.style[l]);

        }

        /* 1409 */ boolean err = false;
        int j;
        /* 1410 */ for (j = 0; j < this.npc.length; j++) {
            /* 1411 */ NPCInfo n = this.npc[j];
            /* 1412 */ if (n != null) {

                /* 1414 */ if (n.head != 0 && getHead(n.head) == null) {
                    /* 1415 */ System.err.println("NPC[" + NPCInfo.npcIDName(j) + "] INVALID HEAD ID : " + n.head);
                    /* 1416 */ err = true;
                }
                /* 1418 */ if (getBody(n.body) == null) {
                    /* 1419 */ System.err.println("NPC[" + NPCInfo.npcIDName(j) + "] INVALID BODY ID : " + n.body);
                    /* 1420 */ err = true;
                }
            }
        }

        /* 1426 */ for (j = 0; j < this.guardian.length; j++) {
            /* 1427 */ if (this.guardian[j] != null) {
                /* 1428 */ err |= this.guardian[j].check();
            }
        }

        /* 1433 */ for (j = 0; j < this.npctrade.length; j++) {
            /* 1434 */ if (this.npctrade[j] != null)
                this.npctrade[j].calcCheckSum();

        }

        /* 1438 */ for (int i = 0; i < this.challenge.length; i++) {
            /* 1439 */ if (this.challenge[i] != null)
                this.challenge[i].calcCheckSum();

        }

        /* 1444 */ finishShops((ShopData[]) this.dungeonshop.vender);
        /* 1445 */ finishShops((ShopData[]) this.globalshop.vender);

        /* 1447 */ makeFlagTable();

        /* 1451 */ GuildBattleSetting.data = this.guildbattlesettingdata;

        /* 1453 */ if (err) {
            /* 1454 */ System.exit(1);
        }
    }

    private void finishShops(ShopData... shops) {
        /* 1459 */ for (int l = 0; l < shops.length; l++) {
            /* 1460 */ if (shops[l] != null)
                shops[l].finish();
        }
    }

    private void finishStyle(int id, StyleData s) {
        /* 1465 */ int[] i = new int[1000];
        /* 1466 */ int c = 0;

        int l;

        /* 1470 */ for (l = 0; l < this.head.length; l++) {
            /* 1471 */ if (this.head[l] != null && this.head[l].isWearable(id))
                i[c++] = l;
            /* 1472 */ }
        s.heads = subarray(i, c);
        /* 1473 */ c = 0;

        /* 1475 */ for (l = 0; l < this.face.length; l++) {
            /* 1476 */ if (this.face[l] != null && this.face[l].isWearable(id))
                i[c++] = l;
            /* 1477 */ }
        s.faces = subarray(i, c);
        /* 1478 */ c = 0;

        /* 1480 */ for (l = 0; l < this.body.length; l++) {
            /* 1481 */ if (this.body[l] != null && this.body[l].isWearable(id))
                i[c++] = l;
            /* 1482 */ }
        s.bodies = subarray(i, c);
        /* 1483 */ c = 0;

        /* 1485 */ for (l = 0; l < this.deco.length; l++) {
            /* 1486 */ if (this.deco[l] != null && this.deco[l].isWearable(id))
                i[c++] = l;
            /* 1487 */ }
        s.decos = subarray(i, c);
        /* 1488 */ c = 0;
    }

    public boolean enableAdjust(int flags) {
        /* 1499 */ return (((getSetting()).adjustbalanceflags & flags) != 0);
    }

    public boolean isAdjust(int... flags) {
        /* 1503 */ return enableAdjust(KotoriUtil.f(true, flags));
    }

    public void releaseVoices() {
        /* 1511 */ for (int l = 0; l < this.voiceset.length; l++) {
            /* 1512 */ if (this.voiceset[l] != null)
                this.voiceset[l].clearCache();

        }
    }

    public boolean assistDisabled(UserInfo gm, int type) {
        /* 1518 */ if (gm.adminflag != 7 || this.assistanttype == 0)
            return false;
        /* 1519 */ return ((type & 1 << this.assistanttype) != 0);
    }

    public void preloadUserInfo(UserInfo[] ui) {
        /* 1526 */ if (ui == null)
            /* 1527 */ return;
        UserInfo i = null;
        /* 1528 */ for (int l = 0; l < ui.length; l++) {
            /* 1529 */ i = ui[l];
            /* 1530 */ preloadUserInfo(i);
        }
    }

    public void preloadNPCInfo(int id) {
        /* 1534 */ preloadUserInfo((UserInfo) getNPCInfo(id));
    }

    public void preloadNPCInfo(NPCInfo i) {
        /* 1537 */ preloadUserInfo((UserInfo) i);
    }

    public void preloadUserInfo(UserInfo i) {
        /* 1541 */ if (i == null)
            return;
        /* 1543 */ if (getStyle(i.style) != null)
            getStyle(i.style).preload();
        /* 1544 */ if (getWeapon(i.weapon) != null)
            getWeapon(i.weapon).preload();

        /* 1546 */ if (getAccs(i.accs) != null)
            getAccs(i.accs).preloadActiveSkill(i.style);
        /* 1547 */ if (getSkill(i.skill0) != null)
            getSkill(i.skill0).preload();
        /* 1548 */ if (getSkill(i.skill1) != null)
            getSkill(i.skill1).preload();
        /* 1549 */ if (getSkill(i.skill2) != null)
            getSkill(i.skill2).preload();
        /* 1550 */ if (getSkill(i.skill3) != null)
            getSkill(i.skill3).preload();

        /* 1552 */ if (getVoiceSet(i.voice) != null)
            getVoiceSet(i.voice).preloard();

        /* 1555 */ if (getBody(i.body) != null)
            getBody(i.body).preload();
        /* 1556 */ if (getHead(i.head) != null)
            getHead(i.head).getHeadModel();
        /* 1557 */ if (getFace(i.face) != null)
            getFace(i.face).getFaceModels();
        /* 1558 */ if (getDecoration(i.deco) != null)
            getDecoration(i.deco).getDecorationModels(i.face);

    }

    public void preloadTournament(UserInfo[] i) {
        /* 1566 */ for (int l = 0; l < (this.scpinfo[0]).basescp.length;) {
            ResourceManager.preloadContent("/action/" + (this.scpinfo[0]).basescp[l]);
            l++;
        }

        /* 1569 */ DropStageItemEvent.preloadStageItem(0);
        /* 1570 */ DropStageItemEvent.preloadStageItem(1);

        /* 1573 */ preloadUserInfo(i);
    }

    public void preloadTournament() {
        /* 1577 */ for (int l = 0; l < (this.scpinfo[0]).basescp.length;) {
            ResourceManager.preloadContent("/action/" + (this.scpinfo[0]).basescp[l]);
            l++;
        }

        /* 1580 */ DropStageItemEvent.preloadStageItem(0);
        /* 1581 */ DropStageItemEvent.preloadStageItem(1);
    }

    private static int f(boolean flag, int... bit) {
        /* 1586 */ return KotoriUtil.f(flag, bit);
    }

    private static boolean f(int flag, int bit) {
        /* 1590 */ return ((flag & 1 << bit) != 0);
    }

    public static int bit(int... bit) {
        /* 1594 */ return KotoriUtil.f(true, bit);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        /* 1602 */ out.writeObject(this.language);
        /* 1603 */ out.writeObject(this.charset);
        /* 1604 */ out.writeObject(this.timezone);

        /* 1606 */ out.writeObject(this.worldmap);

        /* 1608 */ writeArray((Externalizable[]) this.style, out);
        /* 1609 */ writeArray((Externalizable[]) this.stylegroup, out);
        /* 1610 */ writeArray((Externalizable[]) this.build, out);
        /* 1611 */ writeArray((Externalizable[]) this.head, out);
        /* 1612 */ writeArray((Externalizable[]) this.face, out);
        /* 1613 */ writeArray((Externalizable[]) this.body, out);
        /* 1614 */ writeArray((Externalizable[]) this.deco, out);

        /* 1616 */ writeArray((Externalizable[]) this.accs, out);
        /* 1617 */ writeArray((Externalizable[]) this.weapon, out);
        /* 1618 */ out.writeObject(this.skill);

        /* 1620 */ writeArray((Externalizable[]) this.emo, out);
        /* 1621 */ writeArray((Externalizable[]) this.semo, out);

        /* 1623 */ writeArray((Externalizable[]) this.npc, out);

        /* 1625 */ writeArray((Externalizable[]) this.vehicle, out);
        /* 1626 */ writeArray((Externalizable[]) this.spstyle, out);

        /* 1628 */ out.writeObject(this.lounge);
        /* 1629 */ writeArray((Externalizable[]) this.tour, out);
        /* 1630 */ out.writeObject(this.old_arcade);

        /* 1632 */ writeArray((Externalizable[]) this.duel, out);
        /* 1633 */ writeArray((Externalizable[]) this.duelnames, out);
        /* 1634 */ writeArray((Externalizable[]) this.duelscores, out);

        /* 1637 */ if (this.eventbytes != null && this.event != null) {
            /* 1638 */ byte[][] b = new byte[Math.max(this.event.length, this.eventbytes.length)][];
            /* 1639 */ for (int l = 0; l < b.length; l++) {
                /* 1640 */ if (l < this.event.length && this.event[l] != null) {
                    b[l] = KotoriUtil.compress(this.event[l]);
                }
                /* 1641 */ else if (l < this.eventbytes.length && this.eventbytes[l] != null) {
                    b[l] = this.eventbytes[l];
                }

                /* 1643 */ }
            out.writeObject(b);
        }
        /* 1645 */ else if (this.eventbytes != null) {
            /* 1646 */ out.writeObject(this.eventbytes);
        } else {
            /* 1648 */ byte[][] b = new byte[this.event.length][];
            /* 1649 */ for (int l = 0; l < this.event.length; l++) {
                /* 1650 */ if (this.event[l] != null)
                    b[l] = KotoriUtil.compress(this.event[l]);
            }
            /* 1652 */ out.writeObject(b);
        }
        /* 1654 */ out.writeObject(this.practice);

        /* 1656 */ writeArray((Externalizable[]) this.mat, out);
        /* 1657 */ out.writeObject(this.mixmachine);

        /* 1659 */ writeArray((Externalizable[]) this.quest, out);
        /* 1660 */ writeArray((Externalizable[]) this.challenge, out);

        /* 1662 */ out.writeObject(this.kwtinfo);
        /* 1663 */ out.writeObject(this.dicts);

        /* 1665 */ out.writeObject(this.scpinfo);

        /* 1667 */ out.writeObject(this.area);

        /* 1669 */ out.writeObject(this.stagelight);
        /* 1670 */ out.writeObject(this.hitefx);
        /* 1671 */ out.writeObject(this.postefx);

        /* 1673 */ out.writeObject(this.rank);
        /* 1674 */ out.writeObject(this.ranking);
        /* 1675 */ out.writeObject(this.guardian);
        /* 1676 */ out.writeObject(this.arcade);

        /* 1678 */ writeArray((Externalizable[]) this.bgm, out);
        /* 1679 */ writeArray((Externalizable[]) this.voiceset, out);

        /* 1681 */ writeArray((Externalizable[]) this.myroom, out);
        /* 1682 */ writeArray((Externalizable[]) this.furniture, out);
        /* 1683 */ writeArray((Externalizable[]) this.myroomtutorial, out);
        /* 1684 */ writeArray((Externalizable[]) this.etc, out);
        /* 1685 */ writeArray((Externalizable[]) this.eventitem, out);
        /* 1686 */ writeArray((Externalizable[]) this.dicegame, out);
        /* 1687 */ writeArray((Externalizable[]) this.bingo, out);
        /* 1688 */ writeArray((Externalizable[]) this.npcitem, out);

        /* 1690 */ writeArray((Externalizable[]) this.treasures, out);
        /* 1691 */ writeArray((Externalizable[]) this.streettreasures, out);

        /* 1693 */ out.writeObject(this.guild);
        /* 1694 */ out.writeObject(this.dungeonshop);
        /* 1695 */ out.writeObject(this.globalshop);
        /* 1696 */ out.writeObject(this.shoprotendata);

        /* 1698 */ out.writeObject(this.dropboost);

        /* 1700 */ out.writeObject(this.npctrade);
        /* 1701 */ out.writeObject(this.npcintro);

        /* 1703 */ out.writeObject(this.entrance);

        /* 1705 */ out.writeObject(this.chatfilter);

        /* 1707 */ writeArray((Externalizable[]) this.officialskins, out);
        /* 1708 */ out.writeByte(this.defaultskinstock);

        /* 1710 */ writeArray((Externalizable[]) this.officialwebsite, out);

        /* 1712 */ out.writeObject(this.initialitems);
        /* 1713 */ out.writeObject(this.demoitems);

        /* 1715 */ out.writeObject(this.stnpcset);
        /* 1716 */ out.writeObject(this.ufnpcset);

        /* 1718 */ out.writeObject(this.initkeyconf);

        /* 1720 */ out.writeObject(this.initskin);

        /* 1722 */ out.writeObject(this.itemsort);

        /* 1724 */ out.writeObject(this.randtreasure);
        /* 1725 */ out.writeObject(this.randguardiantreasure);
        /* 1726 */ out.writeObject(this.randtreasure2);

        /* 1728 */ out.writeObject(this.mailsort);

        /* 1730 */ out.writeObject(this.fairygacha);

        /* 1732 */ out.writeObject(this.previewpos);

        /* 1734 */ out.writeObject(this.questpresent);

        /* 1737 */ int i = (this.xcguard ? 1 : 0) | (this.voiceenabled ? 2 : 0) | (this.fluidsetting_enabled ? 4 : 0)
                | (this.skinbbsenabled ? 8 : 0) | (this.specialrankenabled ? 16 : 0) | (
                /* 1738 */ this.areaopenguide ? 32 : 0) | (this.duelenabled ? 64 : 0) | (this.plainpassword ? 128 : 0)
                | (this.autochannelselect ? 256 : 0) | (this.myroomstart ? 512 : 0) | (
                /* 1739 */ this.worldmapenabled ? 1024 : 0) | (this.hideeventinfo ? 2048 : 0)
                | (this.destselection ? 4096 : 0) | (this.accessoryeffects ? 8192 : 0)
                | (this.announcegacha ? 16384 : 0) | (
                /* 1740 */ this.partytraining ? 32768 : 0) | (this.manualzoom ? 65536 : 0)
                | (this.loginquest ? 131072 : 0) | (this.allchatfilter ? 262144 : 0)
                | (this.training_skilllist ? 524288 : 0) | (
                /* 1741 */ this.pastquests ? 1048576 : 0) | (this.streetstyle ? 2097152 : 0)
                | (this.skipevents ? 4194304 : 0) | (this.beginnerhelp ? 8388608 : 0)
                | (this.voicechatenabled ? 16777216 : 0) | (
                /* 1742 */ this.softwarekeyboard ? 33554432 : 0) | (this.playtimelimitenabled ? 67108864 : 0)
                | (this.questnpcguide ? 134217728 : 0) | f(this.userid_email, new int[] { 28 })
                | (this.observerenabled ? 536870912 : 0) | (
                /* 1743 */ this.sdoa ? 1073741824 : 0) | (this.new_medal ? Integer.MIN_VALUE : 0);
        /* 1744 */ out.writeInt(i);

        /* 1747 */ i = (this.calendarenabled ? 1 : 0) | (this.charamakingscene2 ? 2 : 0) | (this.observergmonly ? 4 : 0)
                | (this.shoplineupenabled ? 8 : 0) | (
                /* 1748 */ this.gachalineupenabled ? 16 : 0) | (this.hwidcheck ? 32 : 0) | (this.teamsfenabled ? 64 : 0)
                | (this.skiptutorial ? 128 : 0) | (
                /* 1749 */ this.showservertime ? 256 : 0) | (this.new_username ? 512 : 0) | (this.navigation ? 1024 : 0)
                | (this.everyrankupeventenabled ? 2048 : 0) | (
                /* 1750 */ this.arcademodeenabled ? 4096 : 0) | (this.disablegetnewmedal ? 8192 : 0)
                | (this.disconnectinfoenabled ? 16384 : 0) | (this.fairynpcenabled ? 32768 : 0) | (
                /* 1751 */ this.zonesettingenabled ? 65536 : 0) | (this.globalshopenabled ? 131072 : 0)
                | (this.gatroomexitdisable ? 262144 : 0) | (this.avataravilityenabled ? 524288 : 0) | (
                /* 1752 */ this.newstoryepisodeenabled ? 1048576 : 0) | (this.privateavataruploaddisabled ? 2097152 : 0)
                | (this.skineditequipaccsenabled ? 4194304 : 0) | (this.practiceextensionenabled ? 8388608 : 0) | (
                /* 1753 */ this.tourrandequipenabled ? 16777216 : 0) | (this.checkguildname ? 33554432 : 0)
                | (this.lindamissionenabled ? 67108864 : 0) | (this.lanplayenabled ? 134217728 : 0);
        /* 1754 */ out.writeInt(i);

        /* 1757 */ i = (this.freermenabled ? 1 : 0) | (this.newrmchargeenabled ? 2 : 0)
                | (this.duelrewardnomoney ? 4 : 0) | (this.fblistlimitenabled ? 8 : 0) |
                /* 1758 */ f(this.accs_potential, new int[] { 4 }) | (this.automatchenabled ? 32 : 0)
                | (this.wpgenabled ? 64 : 0) | (this.new_spyhuntquest ? 128 : 0) | (
                /* 1759 */ this.noreward_sameaddr ? 256 : 0) | (this.overitemsendmailenabled ? 512 : 0)
                | (this.ranklimitenabled ? 1024 : 0) | (this.showchannelinfo ? 2048 : 0) | (
                /* 1760 */ this.onechgroupselectenabled ? 4096 : 0) | (this.manualupdatelauncherenabled ? 8192 : 0)
                | (this.getsomepresentmailenabled ? 16384 : 0) | (this.openallmemenabled ? 32768 : 0) | (
                /* 1761 */ this.chbbsenabled ? 65536 : 0) | (this.questmenutabenabled ? 131072 : 0)
                | (this.dueldurationentryenabled ? 262144 : 0) | (this.checkchratejump ? 524288 : 0) | (
                /* 1762 */ this.tourplayernumcheckenabled ? 1048576 : 0) | (this.questpopupenabled ? 2097152 : 0)
                | (this.technamedisabled ? 4194304 : 0) | (this.istutorialbuttoncheck ? 8388608 : 0) | (
                /* 1763 */ this.portaldbenabled ? 16777216 : 0) | (this.unitedfightenabled ? 33554432 : 0)
                | (this.rankupquestsettingenabled ? 67108864 : 0) | (this.gg_enable ? 134217728 : 0) | (
                /* 1764 */ this.gg_auth_enable ? 268435456 : 0) | (this.gg_debuglog_enable ? 536870912 : 0)
                | (this.presentskinenabled ? 1073741824 : 0) | (this.itemsortenabled ? Integer.MIN_VALUE : 0);
        /* 1765 */ out.writeInt(i);

        /* 1768 */ i = (this.dogfightenabled ? 1 : 0) | (this.linebreakavoidwordchange ? 2 : 0)
                | (this.showtoppanelmyinfo ? 4 : 0) | (this.tourrnddropenabled ? 8 : 0) | (
                /* 1769 */ this.reduceStrWidth_Adv ? 16 : 0) | (this.shortnavi ? 32 : 0)
                | (this.shopnpcrotenenabled ? 64 : 0) | (this.writepresetguildiconenabled ? 128 : 0) | (
                /* 1770 */ this.mailsortenabled ? 256 : 0) | (this.newstoryepisode2enabled ? 512 : 0)
                | (this.gg_auth_checkerror_enable ? 1024 : 0) | (this.skinchatenabled ? 2048 : 0) | (
                /* 1771 */ this.fullscreenminimizationenabled ? 8192 : 0) | (this.clockspeedcheckenabled ? 16384 : 0)
                | (this.guardianrnddropenabled ? 32768 : 0) | (
                /* 1772 */ this.titleaccountregistrationenabled ? 65536 : 0) | (this.fairygachaenabled ? 131072 : 0)
                | (this.usewidescreen ? 262144 : 0) | (this.guardmissionenabled ? 524288 : 0) | (
                /* 1773 */ this.ganlclienttracking ? 1048576 : 0) | (this.selectablecountry ? 2097152 : 0)
                | (this.multipleplaybingo ? 4194304 : 0) | (this.mixslotenabled ? 8388608 : 0) | (
                /* 1774 */ this.serialfreeinputenabled ? 16777216 : 0) | (this.gachamachinemoveenabled ? 33554432 : 0)
                | (this.rnddropenabled ? 67108864 : 0) | (
                /* 1775 */ this.registrationipcheck ? 536870912 : 0) | (this.npcprocesseventlog ? 1073741824 : 0)
                | (this.shopcouponenabled ? Integer.MIN_VALUE : 0);
        /* 1776 */ out.writeInt(i);

        /* 1779 */ i = (this.useaccslog ? 1 : 0) | (this.newusernotregisterfromportal ? 2 : 0)
                | (this.idcchanneling ? 4 : 0) | (this.concheckenabled ? 8 : 0) | (
                /* 1780 */ this.potentialgearpartsstatus ? 32 : 0);
        /* 1781 */ out.writeInt(i);

        /* 1783 */ out.writeByte(this.trademode);
        /* 1784 */ out.writeByte(this.showgachaprizecount);
        /* 1785 */ out.writeByte(this.gacha_roten_ticket_limit);

        /* 1787 */ out.writeInt(this.roommasterkicktime);

        /* 1789 */ out.writeShort(this.autochselthreshold);

        /* 1791 */ out.writeObject(this.guildbattlesettingdata);

        /* 1793 */ out.writeObject(this.questevent);

        /* 1795 */ out.writeObject(this.skinbbs_soldpayback);

        /* 1797 */ out.writeInt(this.skinbbs_deleteminute);

        /* 1799 */ out.writeInt(this.basemixcost);

        /* 1801 */ out.writeInt(this.edituserdatavalidtime);

        /* 1803 */ out.writeObject(this.playtimelimit);

        /* 1805 */ out.writeShort(this.skinbbs_maxregister);

        /* 1807 */ out.writeObject(this.calendar);

        /* 1809 */ out.writeShort(this.newmedalrate);
        /* 1810 */ out.writeObject(this.pwsaltgeturl);

        /* 1812 */ out.writeInt(this.alchemycost);
        /* 1813 */ out.writeInt(this.potentialcost);

        /* 1815 */ out.writeShort(this.savelogintype);
        /* 1816 */ out.writeByte(this.rankitemmode);

        /* 1818 */ out.writeObject(this.sdoaip);

        /* 1820 */ writeArray((Externalizable[]) this.shortcut, out);

        /* 1822 */ writeArray((Externalizable[]) this.questbuy, out);

        /* 1824 */ out.writeObject(this.landata);
        /* 1825 */ out.writeObject(this.automatchdata);

        /* 1827 */ out.writeObject(this.disableeventid);

        /* 1829 */ out.writeInt(this.friendlistmax);
        /* 1830 */ out.writeInt(this.blacklistmax);

        /* 1832 */ out.writeObject(this.wpgitemprocess);

        /* 1834 */ out.writeShort(this.skinbbs_maxregister_one);

        /* 1836 */ out.writeShort(this.skinbbs_poppoint_buy);
        /* 1837 */ out.writeShort(this.skinbbs_poppoint_vote);

        /* 1839 */ out.writeInt(this.adjustbalanceflags);

        /* 1841 */ out.writeObject(this.deciphermode);

        /* 1843 */ out.writeInt(this.battlepreloadmaxtime);

        /* 1845 */ out.writeInt(this.checkmedalinterval);

        /* 1847 */ out.writeInt(this.assistanttype);

        /* 1849 */ writeArray((Externalizable[]) this.rankupquest, out);

        /* 1851 */ out.writeObject(this.gg_init);
        /* 1852 */ out.writeShort(this.gg_interval);
        /* 1853 */ out.writeShort(this.gg_server_interval);
        /* 1854 */ out.writeShort(this.gg_receive_interval);
        /* 1855 */ out.writeObject(this.gg_tester);

        /* 1857 */ out.writeInt(this.tourkeyroomrank);

        /* 1859 */ out.writeObject(this.replacefont);

        /* 1861 */ out.writeObject(this.cacheclearversion);

        /* 1863 */ out.writeByte(this.skinbbs_usablerank);

        /* 1865 */ out.writeInt(this.sendmailchecktime);
        /* 1866 */ out.writeInt(this.autoopenshuttertime);

        /* 1868 */ out.writeInt(this.tutorialshopbuynum);

        /* 1870 */ out.writeShort(this.guardmissionheal);

        /* 1872 */ out.writeInt(this.restrictiongacharmchargeprice);
        /* 1873 */ out.writeByte(this.gacha_dailyfree_timeoffset);

        /* 1875 */ out.writeObject(this.ganlsetting);

        /* 1877 */ out.writeObject(this.selectcountry);

        /* 1879 */ out.writeObject(this.questionary);

        /* 1881 */ out.writeByte(this.channelselecttype);

        /* 1883 */ out.writeShort(this.mixslotnum);

        /* 1885 */ out.writeObject(this.potentialskilldata);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        /* 1892 */ this.language = (String) in.readObject();
        /* 1893 */ this.charset = (String) in.readObject();
        /* 1894 */ this.timezone = (String) in.readObject();

        /* 1896 */ this.worldmap = (WorldMapData) in.readObject();

        /* 1898 */ this.style = (StyleData[]) readArray(StyleData.class, in);
        /* 1899 */ this.stylegroup = (StyleData.Group[]) readArray(StyleData.Group.class, in);
        /* 1900 */ this.build = (StyleData.Build[]) readArray(StyleData.Build.class, in);
        /* 1901 */ this.head = (StyleData.Head[]) readArray(StyleData.Head.class, in);
        /* 1902 */ this.face = (StyleData.Face[]) readArray(StyleData.Face.class, in);
        /* 1903 */ this.body = (StyleData.Body[]) readArray(StyleData.Body.class, in);
        /* 1904 */ this.deco = (StyleData.Decoration[]) readArray(StyleData.Decoration.class, in);

        /* 1906 */ this.accs = (AccessoryData[]) readArray(AccessoryData.class, in);
        /* 1907 */ this.weapon = (WeaponData[]) readArray(WeaponData.class, in);
        /* 1908 */ this.skill = (SkillData[]) in.readObject();

        /* 1910 */ this.emo = (EmotionData[]) readArray(EmotionData.class, in);
        /* 1911 */ this.semo = (EmotionData[]) readArray(EmotionData.class, in);

        /* 1913 */ this.npc = (NPCInfo[]) readArray(NPCInfo.class, in);

        /* 1915 */ this.vehicle = (VehicleData[]) readArray(VehicleData.class, in);
        /* 1916 */ this.spstyle = (StyleData[]) readArray(StyleData.class, in);

        /* 1918 */ this.lounge = (Object[]) in.readObject();
        /* 1919 */ this.tour = (TournamentData[]) readArray(TournamentData.class, in);
        /* 1920 */ this.old_arcade = (OldArcadeData[]) in.readObject();

        /* 1922 */ this.duel = (DuelData[]) readArray(DuelData.class, in);
        /* 1923 */ this.duelnames = (DuelNameData[]) readArray(DuelNameData.class, in);
        /* 1924 */ this.duelscores = (DuelScoreData[]) readArray(DuelScoreData.class, in);

        /* 1927 */ this.eventbytes = (byte[][]) in.readObject();
        /* 1928 */ this.practice = (PracticeData) in.readObject();

        /* 1930 */ this.mat = (MaterialData[]) readArray(MaterialData.class, in);
        /* 1931 */ this.mixmachine = (MixMachine) in.readObject();

        /* 1933 */ this.quest = (QuestData[]) readArray(QuestData.class, in);
        /* 1934 */ this.challenge = (ChallengeData[]) readArray(ChallengeData.class, in);

        /* 1936 */ this.kwtinfo = (HashMap) in.readObject();
        /* 1937 */ this.dicts = (HashMap) in.readObject();

        /* 1939 */ this.scpinfo = (PlayerScriptInfo[]) in.readObject();

        /* 1941 */ this.area = (Inttable) in.readObject();

        /* 1943 */ this.stagelight = (LightData[]) in.readObject();
        /* 1944 */ this.hitefx = (HitEffectData[]) in.readObject();
        /* 1945 */ this.postefx = (PostEffectData[]) in.readObject();

        /* 1947 */ this.rank = (RankData) in.readObject();
        /* 1948 */ this.ranking = (RankingSetting) in.readObject();
        /* 1949 */ this.guardian = (GuardianData[]) in.readObject();
        /* 1950 */ this.arcade = (ArcadeData[]) in.readObject();

        /* 1952 */ this.bgm = (BGMInfo[]) readArray(BGMInfo.class, in);
        /* 1953 */ this.voiceset = (VoiceSet[]) readArray(VoiceSet.class, in);

        /* 1955 */ this.myroom = (MyRoomStage[]) readArray(MyRoomStage.class, in);
        /* 1956 */ this.furniture = (MyRoomFurniture[]) readArray(MyRoomFurniture.class, in);
        /* 1957 */ this.myroomtutorial = (MyRoomTutorialData[]) readArray(MyRoomTutorialData.class, in);
        /* 1958 */ this.etc = (ETCItem[]) readArray(ETCItem.class, in);
        /* 1959 */ this.eventitem = (EventItem[]) readArray(EventItem.class, in);
        /* 1960 */ this.dicegame = (DiceGameData[]) readArray(DiceGameData.class, in);
        /* 1961 */ this.bingo = (BingoData[]) readArray(BingoData.class, in);
        /* 1962 */ this.npcitem = (NPCItem[]) readArray(NPCItem.class, in);

        /* 1964 */ this.treasures = (TreasureTable[]) readArray(TreasureTable.class, in);
        /* 1965 */ this.streettreasures = (StreetfightTreasureTable[]) readArray(StreetfightTreasureTable.class, in);

        /* 1967 */ this.guild = (GuildSetting) in.readObject();
        /* 1968 */ this.dungeonshop = (DungeonShopData) in.readObject();
        /* 1969 */ this.globalshop = (GlobalShopData) in.readObject();
        /* 1970 */ this.shoprotendata = (ShopRotenData) in.readObject();

        /* 1972 */ this.dropboost = (DropBoostData[]) in.readObject();

        /* 1974 */ this.npctrade = (NPCTradeList[]) in.readObject();
        /* 1975 */ this.npcintro = (NPCIntro[]) in.readObject();

        /* 1977 */ this.entrance = (EntranceInfo[]) in.readObject();

        /* 1979 */ this.chatfilter = (ChatFilter) in.readObject();

        /* 1981 */ this.officialskins = (OfficialSkinData[]) readArray(OfficialSkinData.class, in);
        /* 1982 */ this.defaultskinstock = in.readByte() & 0xFF;

        /* 1984 */ this.officialwebsite = (OfficialWebsiteData[]) readArray(OfficialWebsiteData.class, in);

        /* 1986 */ this.initialitems = (InitialItemsData) in.readObject();
        /* 1987 */ this.demoitems = (UserItems) in.readObject();

        /* 1989 */ this.stnpcset = (StreetNPCData[]) in.readObject();

        /* 1991 */ this.ufnpcset = (UnitedfightNPCData) in.readObject();

        /* 1993 */ this.initkeyconf = (InitialKeyConfig) in.readObject();

        /* 1995 */ this.initskin = (InitialSkinData) in.readObject();

        /* 1997 */ this.itemsort = (ItemSortData) in.readObject();

        /* 1999 */ this.randtreasure = (RandomTreasure[]) in.readObject();
        /* 2000 */ this.randguardiantreasure = (RandomGuardianTreasure[]) in.readObject();
        /* 2001 */ this.randtreasure2 = (RandomTreasure2) in.readObject();

        /* 2003 */ this.mailsort = (ItemSortData) in.readObject();

        /* 2005 */ this.fairygacha = (FairyGachaList[]) in.readObject();
        /* 2006 */ this.previewpos = (ItemPreviewPositionData) in.readObject();

        /* 2008 */ this.questpresent = (QuestPresentList[]) in.readObject();

        /* 2010 */ int i = in.readInt();
        /* 2011 */ this.xcguard = ((i & 0x1) != 0);
        /* 2012 */ this.voiceenabled = ((i & 0x2) != 0);
        /* 2013 */ this.fluidsetting_enabled = ((i & 0x4) != 0);
        /* 2014 */ this.skinbbsenabled = ((i & 0x8) != 0);
        /* 2015 */ this.specialrankenabled = ((i & 0x10) != 0);
        /* 2016 */ this.areaopenguide = ((i & 0x20) != 0);
        /* 2017 */ this.duelenabled = ((i & 0x40) != 0);
        /* 2018 */ this.plainpassword = ((i & 0x80) != 0);
        /* 2019 */ this.autochannelselect = ((i & 0x100) != 0);
        /* 2020 */ this.myroomstart = ((i & 0x200) != 0);
        /* 2021 */ this.worldmapenabled = ((i & 0x400) != 0);
        /* 2022 */ this.hideeventinfo = ((i & 0x800) != 0);
        /* 2023 */ this.destselection = ((i & 0x1000) != 0);
        /* 2024 */ this.accessoryeffects = ((i & 0x2000) != 0);
        /* 2025 */ this.announcegacha = ((i & 0x4000) != 0);
        /* 2026 */ this.partytraining = ((i & 0x8000) != 0);
        /* 2027 */ this.manualzoom = ((i & 0x10000) != 0);
        /* 2028 */ this.loginquest = ((i & 0x20000) != 0);
        /* 2029 */ this.allchatfilter = ((i & 0x40000) != 0);
        /* 2030 */ this.training_skilllist = ((i & 0x80000) != 0);
        /* 2031 */ this.pastquests = ((i & 0x100000) != 0);
        /* 2032 */ this.streetstyle = ((i & 0x200000) != 0);
        /* 2033 */ this.skipevents = ((i & 0x400000) != 0);
        /* 2034 */ this.beginnerhelp = ((i & 0x800000) != 0);
        /* 2035 */ this.voicechatenabled = ((i & 0x1000000) != 0);
        /* 2036 */ this.softwarekeyboard = ((i & 0x2000000) != 0);
        /* 2037 */ this.playtimelimitenabled = ((i & 0x4000000) != 0);
        /* 2038 */ this.questnpcguide = ((i & 0x8000000) != 0);
        /* 2039 */ this.userid_email = f(i, 28);
        /* 2040 */ this.observerenabled = ((i & 0x20000000) != 0);
        /* 2041 */ this.sdoa = ((i & 0x40000000) != 0);
        /* 2042 */ this.new_medal = ((i & Integer.MIN_VALUE) != 0);

        /* 2044 */ i = in.readInt();
        /* 2045 */ this.calendarenabled = ((i & 0x1) != 0);
        /* 2046 */ this.charamakingscene2 = ((i & 0x2) != 0);
        /* 2047 */ this.observergmonly = ((i & 0x4) != 0);
        /* 2048 */ this.shoplineupenabled = ((i & 0x8) != 0);
        /* 2049 */ this.gachalineupenabled = ((i & 0x10) != 0);
        /* 2050 */ this.hwidcheck = ((i & 0x20) != 0);
        /* 2051 */ this.teamsfenabled = ((i & 0x40) != 0);
        /* 2052 */ this.skiptutorial = ((i & 0x80) != 0);
        /* 2053 */ this.showservertime = ((i & 0x100) != 0);
        /* 2054 */ this.new_username = ((i & 0x200) != 0);
        /* 2055 */ this.navigation = ((i & 0x400) != 0);
        /* 2056 */ this.everyrankupeventenabled = ((i & 0x800) != 0);
        /* 2057 */ this.arcademodeenabled = ((i & 0x1000) != 0);
        /* 2058 */ this.disablegetnewmedal = ((i & 0x2000) != 0);
        /* 2059 */ this.disconnectinfoenabled = ((i & 0x4000) != 0);
        /* 2060 */ this.fairynpcenabled = ((i & 0x8000) != 0);
        /* 2061 */ this.zonesettingenabled = ((i & 0x10000) != 0);
        /* 2062 */ this.globalshopenabled = ((i & 0x20000) != 0);
        /* 2063 */ this.gatroomexitdisable = ((i & 0x40000) != 0);
        /* 2064 */ this.avataravilityenabled = ((i & 0x80000) != 0);
        /* 2065 */ this.newstoryepisodeenabled = ((i & 0x100000) != 0);
        /* 2066 */ this.privateavataruploaddisabled = ((i & 0x200000) != 0);
        /* 2067 */ this.skineditequipaccsenabled = ((i & 0x400000) != 0);
        /* 2068 */ this.practiceextensionenabled = ((i & 0x800000) != 0);
        /* 2069 */ this.tourrandequipenabled = ((i & 0x1000000) != 0);
        /* 2070 */ this.checkguildname = ((i & 0x2000000) != 0);
        /* 2071 */ this.lindamissionenabled = ((i & 0x4000000) != 0);
        /* 2072 */ this.lanplayenabled = ((i & 0x8000000) != 0);

        /* 2074 */ i = in.readInt();
        /* 2075 */ this.freermenabled = ((i & 0x1) != 0);
        /* 2076 */ this.newrmchargeenabled = ((i & 0x2) != 0);
        /* 2077 */ this.duelrewardnomoney = ((i & 0x4) != 0);
        /* 2078 */ this.fblistlimitenabled = ((i & 0x8) != 0);
        /* 2079 */ this.accs_potential = f(i, 4);
        /* 2080 */ this.automatchenabled = ((i & 0x20) != 0);
        /* 2081 */ this.wpgenabled = ((i & 0x40) != 0);
        /* 2082 */ this.new_spyhuntquest = ((i & 0x80) != 0);
        /* 2083 */ this.noreward_sameaddr = ((i & 0x100) != 0);
        /* 2084 */ this.overitemsendmailenabled = ((i & 0x200) != 0);
        /* 2085 */ this.ranklimitenabled = ((i & 0x400) != 0);
        /* 2086 */ this.showchannelinfo = ((i & 0x800) != 0);
        /* 2087 */ this.onechgroupselectenabled = ((i & 0x1000) != 0);
        /* 2088 */ this.manualupdatelauncherenabled = ((i & 0x2000) != 0);
        /* 2089 */ this.getsomepresentmailenabled = ((i & 0x4000) != 0);
        /* 2090 */ this.openallmemenabled = ((i & 0x8000) != 0);
        /* 2091 */ this.chbbsenabled = ((i & 0x10000) != 0);
        /* 2092 */ this.questmenutabenabled = ((i & 0x20000) != 0);
        /* 2093 */ this.dueldurationentryenabled = ((i & 0x40000) != 0);
        /* 2094 */ this.checkchratejump = ((i & 0x80000) != 0);
        /* 2095 */ this.tourplayernumcheckenabled = ((i & 0x100000) != 0);
        /* 2096 */ this.questpopupenabled = ((i & 0x200000) != 0);
        /* 2097 */ this.technamedisabled = ((i & 0x400000) != 0);
        /* 2098 */ this.istutorialbuttoncheck = ((i & 0x800000) != 0);
        /* 2099 */ this.portaldbenabled = ((i & 0x1000000) != 0);
        /* 2100 */ this.unitedfightenabled = ((i & 0x2000000) != 0);
        /* 2101 */ this.rankupquestsettingenabled = ((i & 0x4000000) != 0);
        /* 2102 */ this.gg_enable = ((i & 0x8000000) != 0);
        /* 2103 */ this.gg_auth_enable = ((i & 0x10000000) != 0);
        /* 2104 */ this.gg_debuglog_enable = ((i & 0x20000000) != 0);
        /* 2105 */ this.presentskinenabled = ((i & 0x40000000) != 0);
        /* 2106 */ this.itemsortenabled = ((i & Integer.MIN_VALUE) != 0);

        /* 2108 */ i = in.readInt();
        /* 2109 */ this.dogfightenabled = ((i & 0x1) != 0);
        /* 2110 */ this.linebreakavoidwordchange = ((i & 0x2) != 0);
        /* 2111 */ this.showtoppanelmyinfo = ((i & 0x4) != 0);
        /* 2112 */ this.tourrnddropenabled = ((i & 0x8) != 0);
        /* 2113 */ this.reduceStrWidth_Adv = ((i & 0x10) != 0);
        /* 2114 */ this.shortnavi = ((i & 0x20) != 0);
        /* 2115 */ this.shopnpcrotenenabled = ((i & 0x40) != 0);
        /* 2116 */ this.writepresetguildiconenabled = ((i & 0x80) != 0);
        /* 2117 */ this.mailsortenabled = ((i & 0x100) != 0);
        /* 2118 */ this.newstoryepisode2enabled = ((i & 0x200) != 0);
        /* 2119 */ this.gg_auth_checkerror_enable = ((i & 0x400) != 0);
        /* 2120 */ this.skinchatenabled = ((i & 0x800) != 0);
        /* 2121 */ this.fullscreenminimizationenabled = ((i & 0x2000) != 0);
        /* 2122 */ this.clockspeedcheckenabled = ((i & 0x4000) != 0);
        /* 2123 */ this.guardianrnddropenabled = ((i & 0x8000) != 0);
        /* 2124 */ this.titleaccountregistrationenabled = ((i & 0x10000) != 0);
        /* 2125 */ this.fairygachaenabled = ((i & 0x20000) != 0);
        /* 2126 */ this.usewidescreen = ((i & 0x40000) != 0);
        /* 2127 */ this.guardmissionenabled = ((i & 0x80000) != 0);
        /* 2128 */ this.ganlclienttracking = ((i & 0x100000) != 0);
        /* 2129 */ this.selectablecountry = ((i & 0x200000) != 0);
        /* 2130 */ this.multipleplaybingo = ((i & 0x400000) != 0);
        /* 2131 */ this.mixslotenabled = ((i & 0x800000) != 0);
        /* 2132 */ this.serialfreeinputenabled = ((i & 0x1000000) != 0);
        /* 2133 */ this.gachamachinemoveenabled = ((i & 0x2000000) != 0);
        /* 2134 */ this.rnddropenabled = ((i & 0x4000000) != 0);
        /* 2135 */ this.registrationipcheck = ((i & 0x20000000) != 0);
        /* 2136 */ this.npcprocesseventlog = ((i & 0x40000000) != 0);
        /* 2137 */ this.shopcouponenabled = ((i & Integer.MIN_VALUE) != 0);

        /* 2139 */ i = in.readInt();
        /* 2140 */ this.useaccslog = ((i & 0x1) != 0);
        /* 2141 */ this.newusernotregisterfromportal = ((i & 0x2) != 0);
        /* 2142 */ this.idcchanneling = ((i & 0x4) != 0);
        /* 2143 */ this.concheckenabled = ((i & 0x8) != 0);
        /* 2144 */ this.potentialgearpartsstatus = ((i & 0x20) != 0);

        /* 2146 */ this.trademode = in.readByte();
        /* 2147 */ this.showgachaprizecount = in.readByte();
        /* 2148 */ this.gacha_roten_ticket_limit = in.readByte();

        /* 2150 */ this.roommasterkicktime = in.readInt();

        /* 2152 */ this.autochselthreshold = in.readShort();

        /* 2154 */ this.guildbattlesettingdata = (GuildBattleSetting.Data) in.readObject();

        /* 2156 */ this.questevent = (QuestEventData[][]) in.readObject();

        /* 2158 */ this.skinbbs_soldpayback = (String) in.readObject();

        /* 2160 */ this.skinbbs_deleteminute = in.readInt();

        /* 2162 */ this.basemixcost = in.readInt();

        /* 2164 */ this.edituserdatavalidtime = in.readInt();

        /* 2166 */ this.playtimelimit = (PlayTimeLimit) in.readObject();

        /* 2168 */ this.skinbbs_maxregister = in.readShort();

        /* 2170 */ this.calendar = (CalendarData) in.readObject();

        /* 2172 */ this.newmedalrate = in.readShort();
        /* 2173 */ this.pwsaltgeturl = (String) in.readObject();

        /* 2175 */ this.alchemycost = in.readInt();
        /* 2176 */ this.potentialcost = in.readInt();

        /* 2178 */ this.savelogintype = in.readShort();
        /* 2179 */ this.rankitemmode = in.readByte() & 0xFF;

        /* 2181 */ this.sdoaip = (SDOAIPData) in.readObject();

        /* 2183 */ this.shortcut = (UserShortcuts.ChatShortcut[]) readArray(UserShortcuts.ChatShortcut.class, in);

        /* 2185 */ this.questbuy = (QuestBuyData[]) readArray(QuestBuyData.class, in);

        /* 2187 */ this.landata = (LanData) in.readObject();
        /* 2188 */ this.automatchdata = (AutoMatchData) in.readObject();

        /* 2190 */ this.disableeventid = (int[]) in.readObject();

        /* 2192 */ this.friendlistmax = in.readInt();
        /* 2193 */ this.blacklistmax = in.readInt();

        /* 2195 */ this.wpgitemprocess = (String) in.readObject();

        /* 2197 */ this.skinbbs_maxregister_one = in.readShort();

        /* 2199 */ this.skinbbs_poppoint_buy = in.readShort();
        /* 2200 */ this.skinbbs_poppoint_vote = in.readShort();

        /* 2202 */ this.adjustbalanceflags = in.readInt();

        /* 2204 */ this.deciphermode = (String) in.readObject();

        /* 2206 */ this.battlepreloadmaxtime = in.readInt();

        /* 2208 */ this.checkmedalinterval = in.readInt();

        /* 2210 */ this.assistanttype = in.readInt();

        /* 2212 */ this.rankupquest = (QuestData.RankupQuestData[]) readArray(QuestData.RankupQuestData.class, in);

        /* 2214 */ this.gg_init = (String) in.readObject();
        /* 2215 */ this.gg_interval = in.readShort();
        /* 2216 */ this.gg_server_interval = in.readShort();
        /* 2217 */ this.gg_receive_interval = in.readShort();
        /* 2218 */ this.gg_tester = (String) in.readObject();

        /* 2220 */ this.tourkeyroomrank = in.readInt();

        /* 2222 */ this.replacefont = (String) in.readObject();

        /* 2224 */ this.cacheclearversion = (String) in.readObject();

        /* 2226 */ this.skinbbs_usablerank = in.readByte();

        /* 2228 */ this.sendmailchecktime = in.readInt();
        /* 2229 */ this.autoopenshuttertime = in.readInt();

        /* 2231 */ this.tutorialshopbuynum = in.readInt();

        /* 2233 */ this.guardmissionheal = in.readShort();

        /* 2235 */ this.restrictiongacharmchargeprice = in.readInt();
        /* 2236 */ this.gacha_dailyfree_timeoffset = in.readByte();

        /* 2238 */ this.ganlsetting = (GAnlSetting) in.readObject();

        /* 2240 */ this.selectcountry = (String[][]) in.readObject();

        /* 2242 */ this.questionary = (QuestionaryData) in.readObject();

        /* 2244 */ this.channelselecttype = in.readByte() & 0xFF;

        /* 2246 */ this.mixslotnum = in.readShort();

        /* 2248 */ this.potentialskilldata = (PotentialSkillData[]) in.readObject();
    }

    public static void writeArray(Externalizable[] a, ObjectOutput out) throws IOException {
        /* 2252 */ if (a == null) {
            /* 2253 */ StreamTool.writePackedInt(0, out);
            return;
        }
        /* 2256 */ StreamTool.writePackedInt(a.length, out);
        /* 2257 */ int a8 = (a.length + 7) / 8 * 8;
        /* 2258 */ for (int l = 0; l < a8; l += 8) {
            /* 2259 */ int mask = 0;
            int j;
            /* 2260 */ for (j = 0; j < 8; j++) {
                /* 2261 */ if (l + j < a.length && a[l + j] != null)
                    mask |= 1 << j;
            }
            /* 2263 */ out.writeByte(mask);
            /* 2264 */ for (j = 0; j < 8; j++) {
                /* 2265 */ if (l + j < a.length && a[l + j] != null)
                    a[l + j].writeExternal(out);
            }
        }
    }

    public static Object[] readArray(Class<?> c, ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            /* 2272 */ int num = StreamTool.readPackedInt(in);
            /* 2273 */ Externalizable[] a = (Externalizable[]) Array.newInstance(c, num);
            /* 2274 */ int a8 = (a.length + 7) / 8 * 8;
            /* 2275 */ for (int l = 0; l < a8; l += 8) {
                /* 2276 */ int mask = in.readByte() & 0xFF;
                /* 2277 */ for (int j = 0; j < 8; j++) {
                    /* 2278 */ if ((mask & 1 << j) != 0) {
                        /* 2279 */ a[l + j] = (Externalizable) c.newInstance();
                        /* 2280 */ a[l + j].readExternal(in);
                    }
                }
            }
            /* 2284 */ return (Object[]) a;
            /* 2285 */ } catch (Exception e) {
            /* 2286 */ e.printStackTrace();
            /* 2287 */ throw new IOException(e);
        }
    }

    public static void writeText(String s, ObjectOutput out) throws IOException {
        /* 2295 */ GameSetting set = getSetting();
        /* 2296 */ if (s.length() == 0) {
            /* 2297 */ StreamTool.writePackedInt(0, out);
            return;
        }
        /* 2300 */ byte[] b = s.getBytes(set.charset.equals("sjis") ? "utf8" : set.charset);
        /* 2301 */ StreamTool.writePackedInt(b.length, out);
        /* 2302 */ out.write(b);
    }

    public static String readText(ObjectInput in) throws IOException {
        /* 2309 */ int num = StreamTool.readPackedInt(in);
        /* 2310 */ if (num == 0)
            return "";
        /* 2311 */ byte[] b = new byte[num];
        /* 2312 */ in.readFully(b);
        /* 2313 */ GameSetting set = getSetting();
        /* 2314 */ return new String(b, set.charset.equals("sjis") ? "utf8" : set.charset);
    }

    private static int[] subarray(int[] i, int len) {
        /* 2318 */ int[] ret = new int[len];
        /* 2319 */ System.arraycopy(i, 0, ret, 0, len);
        /* 2320 */ return ret;
    }

    public static GameSetting getSetting() {
        /* 2324 */ return set;
    }

    public static void initSetting(File f) {
        try {
            /* 2329 */ initSettingForMakePkg(f);
            /* 2330 */ } catch (Exception e) {
            /* 2331 */ e.printStackTrace();
            /* 2332 */ System.err.println("GameSetting reading failed...");
            /* 2333 */ System.exit(1);
            /* 2334 */ } catch (CompileError ce) {
            /* 2335 */ ce.printStackTrace();
            /* 2336 */ System.err.println("GameSetting reading failed...");
            /* 2337 */ System.exit(1);
        }
    }

    public static void initSettingForMakePkg(File f) throws Exception, CompileError {
        /* 2342 */ if (f.getName().endsWith(".kxr")) {
            /* 2343 */ set = readKxr(f.getPath());
            /* 2344 */ set.finish();
            /* 2345 */ ObjectReader.charset = set.charset;
        } else {
            /* 2347 */ int t = Clock.time();
            /* 2348 */ ObjectReader.charset = defchar;
            /* 2349 */ ObjectReader r = new ObjectReader();
            /* 2350 */ r.readFile(null, f);
            /* 2351 */ set = (GameSetting) r.getReadObject();
            /* 2352 */ set.finish();
            /* 2353 */ System.out.println("initSetting time : " + (Clock.time() - t));
        }
    }

    public static void initSettingForLauncher(File f) throws Exception, CompileError {
        /* 2358 */ if (f.getName().endsWith(".kxr")) {
            /* 2359 */ set = readKxr(f.getPath());

            /* 2361 */ set.localres = ResourceManager.getResource("/lang/" + set.language);
        }
    }

    public void writeKxr(String fn) throws IOException {
        /* 2367 */ KxrFile kxr = KxrFile.openClean(fn, "pwpw");
        /* 2368 */ ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /* 2369 */ ExternalizerOutputStream eos = new ExternalizerOutputStream(baos, false);
        /* 2370 */ eos.writeObject(this);
        /* 2371 */ eos.close();
        /* 2372 */ kxr.create(kxr.getRoot(), "gs", baos.toByteArray(), 0);
        /* 2373 */ kxr.close();
    }

    public void override(File f) throws Exception, CompileError {
        /* 2377 */ int t = Clock.time();
        /* 2378 */ ObjectReader.charset = this.charset;
        /* 2379 */ ObjectReader r = new ObjectReader();
        /* 2380 */ r.readFile(this, f);
        /* 2381 */ finish();
        /* 2382 */ System.out.println("overrideSetting time : " + (Clock.time() - t));
    }

    static GameSetting readKxr(String fn) throws IOException, ClassNotFoundException {
        /* 2386 */ GameSetting gs = null;

        /* 2388 */ KxrFile kxr = KxrFile.open(fn, "pwpw", "r");
        /* 2389 */ KxrFile.Entry e = kxr.getRoot().getEntry("gs");
        /* 2390 */ int size = e.size();
        /* 2391 */ byte[] b = new byte[size];
        /* 2392 */ e.getData(b);
        /* 2393 */ ByteArrayInputStream bais = new ByteArrayInputStream(b);
        /* 2394 */ ExternalizerInputStream eis = new ExternalizerInputStream(bais, false);
        /* 2395 */ gs = (GameSetting) eis.readObject();

        /* 2397 */ e = kxr.getRoot().getEntry("#version.gs.kxr");
        /* 2398 */ if (e != null) {
            /* 2399 */ size = e.size();
            /* 2400 */ e.getData(b);
            /* 2401 */ ExternalizerInputStream dis = new ExternalizerInputStream(new ByteArrayInputStream(b));
            /* 2402 */ gs.version = ((Number) dis.readObject()).intValue();
        }
        /* 2404 */ kxr.close();

        /* 2407 */ setDefaultLanguage(String.valueOf(gs.language) + ":" + gs.charset);

        /* 2409 */ return gs;
    }

    public static void main(String[] args) throws Exception {
        /* 2413 */ Externalizer.setDefault(AmpedExternalizer.getExternalizer());
        /* 2414 */ ResourceManager.addPath("resource");
        /* 2415 */ initSettingForMakePkg(new File("conf/game.conf"));
        /* 2416 */ set.writeKxr(args[0]);
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\setting\GameSetting.
 * class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
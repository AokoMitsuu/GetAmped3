
package ga2.data;

import ga2.data.net.AmpedException;
import ga2.setting.FluidSetting;
import ga2.setting.GameSetting;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import kotori.gfx.img.Texture;
import kotori.io.StreamTool;
import kotori.util.Inttable;
import kotori.util.ServerTime;

public class UserItems
        implements Externalizable {
    public UserItems() {
        this.items = new ArrayList<Item>();
        this.params = new Inttable();
    }

    public static final String[] kindname = new String[] {
            "style", "head", "face", "body", "deco", "accs", "skill", "weapon", "emo", "skin",
            "etc", "quest", "mat", "furn", "myroom", "event", "voice", "rank", "emo" };
    public static final int ITEM_KINDS = 19;
    public static final byte ITEM_STYLE = 0;
    public static final byte ITEM_HEAD = 1;
    public static final byte ITEM_FACE = 2;
    public static final byte ITEM_BODY = 3;
    public static final byte ITEM_DECORATION = 4;
    public static final byte ITEM_ACCESSORY = 5;
    public static final byte ITEM_SKILL = 6;
    public static final byte ITEM_WEAPON = 7;
    public static final byte ITEM_EMOTION = 8;
    public static final byte ITEM_SKIN = 9;
    public static final byte ITEM_ETC = 10;
    public static final byte ITEM_QUEST = 11;
    public static final byte ITEM_MATERIAL = 12;
    public static final byte ITEM_FURNITURE = 13;
    public static final byte ITEM_ROOM = 14;
    public static final byte ITEM_EVENT = 15;
    public static final byte ITEM_VOICE = 16;
    public static final byte ITEM_RANK = 17;
    public static final byte ITEM_EMOTION2 = 18;
    public static final byte ITEM_SPECIAL = 20;
    public static final byte ITEM_PACK = 21;
    public static final byte ITEM_POINTGM = 22;
    public static final byte ITEM_POINTRM = 23;
    public static final byte ITEM_BUYQUEST = 24;
    public static final byte ITEM_NPC = 25;
    public static final byte ITEM_AUTOSETPACK = 26;
    public static final byte GET_UNKNOWN = 0;
    public static final byte GET_INITIAL = 1;
    public static final byte GET_TOURNAMENT = 2;
    public static final byte GET_ARCADE = 3;
    public static final byte GET_QUEST = 4;
    public static final byte GET_RANKUP = 5;
    public static final byte GET_SHOPGM = 6;
    public static final byte GET_SHOPRM = 7;
    public static final byte GET_PRESENT = 8;
    public static final byte GET_TRADE = 9;
    public static final byte GET_SERVICE = 10;
    public static final byte GET_NPC = 11;
    public static final byte GET_SHOPPING = 12;
    public static final byte GET_STYLESET = 13;
    public static final byte GET_SHOPVIP = 14;
    public static final byte GET_DEBUG = 15;
    public static final byte GET_SKINBBS = 16;
    public static final byte GET_TICKET_SKINUPLOAD = 17;
    public static final byte GET_CALENDAR = 18;

    public static UserItems getFullItems() {
        return getFullItems(false);
    }

    public static final byte GET_LOST = 19;
    public static final byte GET_CREATE = 20;
    public static final byte GET_GACHA = 21;
    public static final byte GET_STREETFIGHT = 22;
    public static final byte GET_UNITEDFIGHT = 23;
    public static final byte GET_RNDTREASURE = 24;
    public static final byte GET_SHOPSTARPOINT = 25;
    public static final byte GET_GACHA_PRESENT = 26;
    public static final int PRM_RMCHARGE = 1;
    public static final int PRM_SKINSTOCK = 2;
    public static final int PRM_MYROOMLV = 3;
    public static final int PRM_SERIALCODEGROUP = 4;
    private static final int PRM_USERRANK = 5;
    public static final int PRM_USERGACHA = 6;
    public static final int PRM_ITEMADDITIONALDATA = 7;
    public static final int PRM_NEWMEDAL = 8;
    public static final int PRM_ROTENLIMIT = 9;
    public static final int PRM_TALKFLAGS = 10;
    public static final int PRM_SPECIALRANKTIME = 11;
    public static final int PRM_PRACTICEDATA = 12;
    public static final int PRM_FREERM = 13;
    public static final int PRM_RNDTREASURE = 14;
    public static final int PRM_SHORTCUT = 15;
    public static final int PRM_RNDGUARDIANTREASURE = 16;
    public static final int PRM_RMCHARGEFLAG = 17;
    public static final int PRM_MIXSTOCK = 18;
    public static final int PRM_EQUIPSLOT = 19;
    public static final int PRM_RNDTREASURE2 = 20;
    public static final int PRM_GACHA_PRESENT = 21;
    public static final int PRM_ETCCOUNT_BASE = 1000;
    public static final int PRM_HAPPYSET_BASE = 2000;
    public static final int ITEMID_ETC_SKIN = 27;
    public static final int ITEMID_ETC_DWARP_FIXED = 34;
    public static final int ITEMID_ETC_DWARP_CONTINUE = 35;
    public static final int ITEMID_EVENT_CHCHAT = 32;
    public static final int ITEMID_EVENT_ALLCHCHAT = 33;
    public static final int ITEMID_EVENT_EVENTDROP = 36;
    public static final int ITEMID_MATERIAL_GALLIPOT1 = 1552;
    public static final int ITEMID_MATERIAL_GALLIPOT2 = 1553;
    public static final int ITEMID_MATERIAL_GALLIPOT3 = 1554;
    public static final int ITEMINFO_MAKERNAME = 0;
    private static long MAX_ROTEN_TICKET_LIMIT = 86313600000L;
    ArrayList<Item> items;
    Inttable params;
    private int gamemoney;
    private int realmoney;
    private int vippoint;
    private int viplv;

    public static UserItems getFullItems(boolean addprivately) {
        UserItems ui = new UserItems();
        GameSetting set = GameSetting.getSetting();

        _getFullItems((byte) 0, (AmpedItem[]) set.style, ui.items, addprivately);
        _getFullItems((byte) 1, (AmpedItem[]) set.head, ui.items, addprivately);
        _getFullItems((byte) 2, (AmpedItem[]) set.face, ui.items, addprivately);
        _getFullItems((byte) 3, (AmpedItem[]) set.body, ui.items, addprivately);
        _getFullItems((byte) 4, (AmpedItem[]) set.deco, ui.items, addprivately);
        _getFullItems((byte) 5, (AmpedItem[]) set.accs, ui.items, addprivately);
        _getFullItems((byte) 7, (AmpedItem[]) set.weapon, ui.items, addprivately);

        _getFullItems((byte) 8, (AmpedItem[]) set.emo, ui.items, addprivately);
        _getFullItems((byte) 18, (AmpedItem[]) set.emo, ui.items, addprivately);
        _getFullItems((byte) 12, (AmpedItem[]) set.mat, ui.items, addprivately);
        _getFullItems((byte) 13, (AmpedItem[]) set.furniture, ui.items, addprivately);
        _getFullItems((byte) 14, (AmpedItem[]) set.myroom, ui.items, addprivately);

        _getFullItems((byte) 16, (AmpedItem[]) set.voiceset, ui.items, addprivately);
        _getFullItems((byte) 17, (AmpedItem[]) set.rank.rank, ui.items, addprivately);

        Item i = new Item((byte) 15, 41, 100, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 46, 1, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 23, 10, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 24, 500, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 67, 500, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 68, 500, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 81, 500, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        i = new Item((byte) 15, 82, 500, 15, System.currentTimeMillis(), 0);
        ui.items.add(i);
        for (int id = 91; id <= 99; id++) {
            i = new Item((byte) 15, id, 10, 15, System.currentTimeMillis(), 0);
            ui.items.add(i);
        }
        ui.addRotenTicket(46, 1, System.currentTimeMillis(), false);

        if (set.isLanguage(new String[] { "ja", "tw" })) {
            Number n = (Number) ui.getParam(19);
            if (n == null)
                ui.putParam(19, Integer.valueOf(1));

        }

        ui.gamemoney = 100000;
        ui.realmoney = 100000;

        return ui;
    }

    public static UserItems getLanItems(boolean addprivately) {
        UserItems ui = new UserItems();
        GameSetting set = GameSetting.getSetting();

        _getFullItems((byte) 1, (AmpedItem[]) set.head, ui.items, addprivately);
        _getFullItems((byte) 2, (AmpedItem[]) set.face, ui.items, addprivately);
        _getFullItems((byte) 3, (AmpedItem[]) set.body, ui.items, addprivately);
        _getFullItems((byte) 4, (AmpedItem[]) set.deco, ui.items, addprivately);
        _getFullItems((byte) 5, (AmpedItem[]) set.accs, ui.items, addprivately);
        _getFullItems((byte) 7, (AmpedItem[]) set.weapon, ui.items, addprivately);

        _getFullItems((byte) 16, (AmpedItem[]) set.voiceset, ui.items, addprivately);

        return ui;
    }

    static void _getFullItems(byte kind, AmpedItem[] ai, ArrayList<Item> a, boolean addprivately) {
        int num = hasNumber(kind) ? 500 : 0;
        long time = System.currentTimeMillis();
        Random r = (kind == 5) ? new Random(System.currentTimeMillis()) : null;
        for (int l = 0; l < ai.length; l++) {
            if (ai[l] != null && (addprivately || !ai[l].isPrivately())
                    && (kind != 17 || !((RankData.Rank) ai[l]).isKind(0))) {
                Item i = new Item(kind, l, num, 15, time, 0);
                if (kind == 5) {
                    i.tuneup = getRandomTuneup(r);
                }
                a.add(i);
            }
        }
    }

    public static UserItems getInitialItems(InitialItemsData iidata, long time) {
        UserItems items = new UserItems();
        if (iidata != null) {
            int size = iidata.size();
            for (int l = 0; l < size; l++) {
                Item i = iidata.get(l);
                if (items.isGetable(i)) {
                    try {
                        Item a = i.clone();
                        a.gettype = 1;
                        a.gettime = time;
                        items.addItem(a);

                        if (a.kind == 0) {
                            items.addStyleDefItems(a.id);
                        }
                    } catch (AmpedException ampedException) {
                    }
                }
            }
            items.setGameMoney(iidata.getGameMoney());
            items.setRealMoney(iidata.getRealMoney(), true);
        }
        return items;
    }

    public void confiscateItems() {
        _confiscateItems((byte) 7);
        _confiscateItems((byte) 12);
        _confiscateItems((byte) 13);
        _confiscateItems((byte) 16);
        removeItem(15, 1, 1000);
        removeItem(15, 22, 1000);
        removeItem(15, 24, 1000);
    }

    void _confiscateItems(byte kind) {
        Item[] item = getItems();

        for (int l = 0; l < item.length; l++) {
            Item i = item[l];
            if (i != null) {
                removeItem(kind, i.id, 1000);
            }
        }
    }

    public void fillItems() {
        GameSetting set = GameSetting.getSetting();

        _fillItems((byte) 0, (AmpedItem[]) set.style);
        _fillItems((byte) 1, (AmpedItem[]) set.head);
        _fillItems((byte) 2, (AmpedItem[]) set.face);
        _fillItems((byte) 3, (AmpedItem[]) set.body);
        _fillItems((byte) 4, (AmpedItem[]) set.deco);
        _fillItems((byte) 5, (AmpedItem[]) set.accs);
        _fillItems((byte) 7, (AmpedItem[]) set.weapon);

        _fillItems((byte) 8, (AmpedItem[]) set.emo);
        _fillItems((byte) 18, (AmpedItem[]) set.emo);
        _fillItems((byte) 12, (AmpedItem[]) set.mat);
        _fillItems((byte) 13, (AmpedItem[]) set.furniture);
        _fillItems((byte) 14, (AmpedItem[]) set.myroom);
    }

    private void _fillItems(byte kind, AmpedItem[] ai) {
        int num = hasNumber(kind) ? 50 : 0;
        long time = System.currentTimeMillis();
        Random r = (kind == 5) ? new Random(System.currentTimeMillis()) : null;
        for (int l = 0; l < ai.length; l++) {
            if (ai[l] != null && !ai[l].isPrivately()) {
                Item i = new Item(kind, l, num, 15, time, 0);
                if (kind == 5)
                    i.tuneup = getRandomTuneup(r);

                try {
                    addItem(i);
                } catch (Exception exception) {
                }
            }
        }
    }

    private static long getRandomTuneup(Random r) {
        long ret = 0L;
        GameSetting set = GameSetting.getSetting();
        for (int l = 0; l < 3; l++) {
            int s = r.nextInt(100) + l * 100 + 1 & 0x3FFF;
            if (set.isPrivately(6, s))
                s = 0;
            ret = ret << 14L | s;
        }

        return ret;
    }

    public static boolean hasNumber(int kind) {
        return !(kind != 7 && kind != 12 && kind != 13 && kind != 15 && kind != 18);
    }

    public UserItems clone() {
        UserItems i = new UserItems();
        int num = this.items.size();
        i.items.ensureCapacity(num);
        for (int l = 0; l < num; l++) {
            i.items.add(((Item) this.items.get(l)).clone());
        }
        i.gamemoney = this.gamemoney;
        i.realmoney = this.realmoney;
        i.params = this.params.deepClone();
        i.vippoint = this.vippoint;
        i.viplv = this.viplv;

        return i;
    }

    public Item[] getItems() {
        return (Item[]) this.items.toArray((Object[]) new Item[this.items.size()]);
    }

    public Item getItem(int kind, int id) {
        if (kind == 22) {
            if (getGameMoney() >= id)
                return new Item((byte) kind, id, getGameMoney() / id);
        } else if (kind == 23) {
            if (getRealMoney() >= id)
                return new Item((byte) kind, id, getRealMoney() / id);
        } else {
            for (int l = 0; l < this.items.size(); l++) {
                Item i = this.items.get(l);
                if (i.kind == kind && i.id == id)
                    return i;
            }
        }
        return null;
    }

    public Item getTerminableItem(int kind, int id) {
        if (kind == 22) {
            if (getGameMoney() >= id)
                return new Item((byte) kind, id, getGameMoney() / id);
        } else if (kind == 23) {
            if (getRealMoney() >= id)
                return new Item((byte) kind, id, getRealMoney() / id);
        } else {
            ItemAdditionalData addiData = getItemAdditionalData();
            for (int l = 0; l < this.items.size(); l++) {
                Object item = this.items.get(l);
                if (addiData == null || addiData.hasExpireTime((Item) item)) {
                    Item i = (Item) item;
                    if (i.kind == kind && i.id == id)
                        return i;
                }
            }
        }
        return null;
    }

    public Item[] getList(ItemFilter f) {
        ArrayList<Item> a = new ArrayList<Item>();
        int size = this.items.size();
        for (int l = 0; l < size; l++) {
            Item i = this.items.get(l);
            if (i != null && f.filter(i))
                a.add(i);
        }
        return a.<Item>toArray(new Item[a.size()]);
    }

    public Item[] getList(int kind) {
        return getList(new ItemFilter.Kind(kind));
    }

    public Item[] getList(int[] kind) {
        return getList(new ItemFilter.KindList(kind));
    }

    public Item[] getListEditable(int kind) {
        return getList(
                new ItemFilter.And(new ItemFilter[] { new ItemFilter.Kind(kind), new ItemFilter.Editable() }));
    }

    public int getKindNum(int kind) {
        int c = 0;
        for (int l = 0; l < this.items.size(); l++) {
            Item i = this.items.get(l);
            if (i.kind == kind)
                c++;
        }
        return c;
    }

    public int getSkinStock() {
        int stock = (GameSetting.getSetting()).defaultskinstock;
        Object o = getParam(2);
        if (o instanceof Number) {
            stock += ((Number) o).intValue();
        }
        return stock;
    }

    public boolean isSkinStockLimit() {
        return (getSkinStock() >= 23);
    }

    public int getEquipSlot() {
        int slot = 1;
        Object o = getParam(19);
        if (o instanceof Number) {
            slot += ((Number) o).intValue();
        }
        return slot;
    }

    public boolean isEquipSlotLimit() {
        int equipslotitem_id = 48;
        ETCItem ei = GameSetting.getSetting().getETCItem(equipslotitem_id);
        int stockmax = ((int[]) ei.arg)[0];
        return (getEquipSlot() >= stockmax);
    }

    public short getPracticeNpc() {
        PracticeData data = GameSetting.getSetting().getPractice();
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        return (upd == null) ? data.defnpcnum : upd.getNpcAppearNum();
    }

    public boolean isPracticeNpcLimit() {
        return (getPracticeNpc() >= (GameSetting.getSetting().getPractice()).maxnpcnum);
    }

    public int getMyRoomLv() {
        Number num = (Number) getParam(3);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int getFreeRealMoney() {
        if (!(GameSetting.getSetting()).freermenabled)
            return 0;
        Number num = (Number) getParam(13);
        if (num == null)
            return 0;
        return num.intValue();
    }

    public int getDiffFreeRealMoney(int arm, int oldfrm) {
        if (!(GameSetting.getSetting()).freermenabled)
            return 0;
        if (arm == 0 || oldfrm == 0)
            return 0;
        if (arm < 0) {
            int _arm = arm * -1;
            return (_arm >= oldfrm) ? -oldfrm : arm;
        }
        return (arm >= oldfrm) ? oldfrm : arm;
    }

    public void addFreeRealMoney(int i) {
        if (!(GameSetting.getSetting()).freermenabled)
            return;
        Number num = (Number) getParam(13);
        if (num == null) {
            putParam(13, (i <= 0) ? Integer.valueOf(0) : Integer.valueOf(i));
        } else {
            int v = num.intValue() + i;
            putParam(13, Integer.valueOf((v <= 0) ? 0 : v));
        }
    }

    public void setFreeRealMoney(int i) {
        if (!(GameSetting.getSetting()).freermenabled)
            return;
        putParam(13, Integer.valueOf(i));
    }

    public boolean isEquipableRank(int id, boolean isgm) {
        return isEquipableRank(getItem(17, id), isgm);
    }

    public boolean isEquipableRank(Item r, boolean isgm) {
        boolean ret = false;
        if (r != null && r.kind == 17 && r.gettime > 0L) {
            GameSetting set = GameSetting.getSetting();
            RankData.Rank rd = set.getRank(r.id);
            if (rd.isKind(3)) {
                ret = isgm;
            } else if (rd.isKind(1)) {
                ret = (getSpecialRankTime() <= r.gettime);
            } else if (rd.isKind(5)) {
                ret = true;
            } else if (rd.isKind(6)) {
                ret = isNowGuildbattleSeason(r);
            } else {
                ret = true;
                for (Iterator<Item> itr = this.items.iterator(); itr.hasNext();) {
                    Item i = itr.next();
                    if (i.kind == 17 && rd.isSameType(set.getRank(i.id))
                            && (r.gettime < i.gettime || (r.gettime == i.gettime && r.id < i.id))) {
                        ret = false;

                        break;
                    }
                }
            }
        }
        return ret;
    }

    private static boolean isRequiredAvatar(int kind) {
        return !(kind != 0 && kind != 3 && kind != 2 && kind != 17);
    }

    private static Item getEmptyAvator(int kind, boolean slot) {
        return new Item((byte) kind, slot ? -1 : 0);
    }

    public static boolean isEquipable(Item i, UserInfo ui) {
        return (new ItemFilter.Equipable(ui)).filter(i);
    }

    public static boolean isEditable(Item i) {
        return (new ItemFilter.Editable()).filter(i);
    }

    public static Item[] addEmptyItem(Item[] list, int kind, boolean slot) {
        Item[] t = new Item[list.length + 1];
        t[0] = getEmptyAvator(kind, slot);
        System.arraycopy(list, 0, t, 1, list.length);
        return t;
    }

    public Item[] getEquipableList(UserInfo ui, int kind, boolean _add) {
        Item[] list = getList(new ItemFilter.And(new ItemFilter[] {
                new ItemFilter.Kind(kind),
                new ItemFilter.Equipable(ui, this)
        }));
        if (_add && !isRequiredAvatar(kind)) {
            list = addEmptyItem(list, kind, false);
        }
        return list;
    }

    public Item[] getEquipableListEditable(UserInfo ui, int kind, boolean _add) {
        Item[] list = getList(new ItemFilter.And(new ItemFilter[] {
                new ItemFilter.Kind(kind),
                new ItemFilter.Equipable(ui, this),
                new ItemFilter.Editable()
        }));
        if (_add && !isRequiredAvatar(kind)) {
            list = addEmptyItem(list, kind, false);
        }
        return list;
    }

    public Item[] getSlotEquipList(UserInfo ui, int kind, boolean _add) {
        Item[] list = getList(new ItemFilter.And(new ItemFilter[] {
                new ItemFilter.Kind(kind),
                new ItemFilter.SlotEquipable(ui.style)
        }));
        if (_add) {
            list = addEmptyItem(list, kind, true);
        }
        return list;
    }

    public Item[] getItemList(int kind, boolean _add) {
        Item[] list = getList(kind);
        if (_add && !isRequiredAvatar(kind)) {
            list = addEmptyItem(list, kind, false);
        }
        return list;
    }

    public Item[] getItemListEditable(int kind, boolean _add) {
        Item[] list = getListEditable(kind);
        if (_add && !isRequiredAvatar(kind)) {
            list = addEmptyItem(list, kind, false);
        }
        return list;
    }

    public boolean isValidSkill(int accs, int skill0, int skill1, int skill2, int skill3) {
        Item i = getItem(5, accs);
        if (i == null || GameSetting.getSetting().isPrivately(5, accs))
            return false;
        if (!i.isValid(skill1, skill2, skill3))
            return false;
        return (skill0 == i.getPotentialSkill());
    }

    public Item[] getExpiredItemList(int kind) {
        return getList(new ItemFilter.And(new ItemFilter[] {
                new ItemFilter.Kind(kind),
                new ItemFilter.Expired(getItemAdditionalData())
        }));
    }

    public Item[] getInformExpiredItemList() {
        int nowTime = (int) (ServerTime.getTime() / 60000L);
        return getList(new ItemFilter.InformExpired(getItemAdditionalData(), nowTime));
    }

    public int getInformExpiredItem_nexttime() {
        return (getItemAdditionalData() != null)
                ? (getItemAdditionalData().getNexttimeRequireInformExpired() * 60000)
                : 21600000;
    }

    public boolean swapPlacement(Item a, Item b) {
        int ap = -1, bp = -1;
        for (int i = this.items.size() - 1; i >= 0; i--) {
            Item it = this.items.get(i);
            if (it.kind == a.kind && it.id == a.id)
                ap = i;
            if (it.kind == b.kind && it.id == b.id)
                bp = i;
            if (ap > -1 && bp > -1)
                break;
        }
        if (ap < 0 || bp < 0)
            return false;
        if (ap == bp)
            return true;
        Item mem = this.items.remove(ap);
        this.items.add(bp, mem);
        return true;
    }

    public boolean swapPlacement(int kind, int id, int place) {
        if (place < 0)
            return false;
        int old = itemIndexOf(kind, id);
        if (old < 0)
            return false;
        if (old == place)
            return true;
        Item mem = this.items.remove(old);
        this.items.add(place, mem);
        return true;
    }

    public boolean hasItem(int kind, int id) {
        return (itemIndexOf(kind, id) >= 0);
    }

    public int hasItems(int kind, int id) {
        for (int l = 0; l < this.items.size(); l++) {
            Item i = this.items.get(l);
            if (i.kind == kind && i.id == id)
                return i.num;
        }
        return -1;
    }

    public int hasItems(Item item) {
        return hasItems(item.kind, item.id);
    }

    public boolean hasItem(int[] i) {
        Item item = getItem(i[0], i[1]);
        if (item == null || (item.hasNumber() && item.num == 0))
            return false;
        return true;
    }

    public boolean hasItem(int kind, int[] id) {
        HashMap<Object, Object> num = new HashMap<Object, Object>();
        for (int i = 0; i < id.length; i++) {
            Object o = num.get(Integer.valueOf(id[i]));
            int n = (o == null) ? 0 : ((Integer) o).intValue();
            n++;
            num.put(Integer.valueOf(id[i]), Integer.valueOf(n));
        }
        Iterator<Map.Entry<Object, Object>> ite = num.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<Object, Object> e = ite.next();
            int n = ((Integer) e.getValue()).intValue();
            if (hasNumber(kind)) {
                int _id = ((Integer) e.getKey()).intValue();
                int has = hasItems(kind, _id);
                if (has < n)
                    return false;
                continue;
            }
            if (n >= 2)
                return false;

        }
        return true;
    }

    public boolean hasShopCoupon() {
        for (int id = 91; id <= 99; id++) {
            if (hasItem(new int[] { 15, id }))
                return true;
        }
        return false;
    }

    public boolean hasShopCoupon(int id) {
        if (hasItem(new int[] { 15, id }))
            return true;
        return false;
    }

    public boolean isGetable(Item i) {
        if (i == null) {
            return false;
        }
        return isGetable(i.kind, i.id, i.num);
    }

    public boolean isGetable(int kind, int id, int num) {
        int idx = itemIndexOf(kind, id);
        if (idx < 0) {
            if (hasNumber(kind)) {
                return (num <= getMaxItemNum(kind, id));
            }
            return true;
        }

        Item i = this.items.get(idx);
        if (i.hasNumber()) {
            return (i.num + num <= getMaxItemNum(kind, id));
        }

        if (kind == 17 && RankData.Rank.isExpire(id))
            return true;

        ItemAdditionalData iaData = getItemAdditionalData();
        if (iaData != null && iaData.hasExpireTime(i)) {
            return true;
        }
        return false;
    }

    public boolean isMailGetable(Item i) {
        if (i == null) {
            return false;
        }
        return isMailGetable(i.kind, i.id);
    }

    private boolean isMailGetable(int kind, int id) {
        int idx = itemIndexOf(kind, id);
        if (idx < 0) {
            return true;
        }
        Item i = this.items.get(idx);
        if (i.hasNumber()) {
            int n = getMaxItemNum(kind, id) - i.num;
            return (n > 0);
        }

        if (kind == 17 && RankData.Rank.isExpire(id))
            return true;

        ItemAdditionalData iaData = getItemAdditionalData();
        if (iaData != null && iaData.hasExpireTime(i)) {
            return true;
        }
        return false;
    }

    public static int getMaxItemNum(int kind, int id) {
        int maxnum = 0;
        if (kind == 22 || kind == 23) {
            maxnum = 999;
        } else if (!hasNumber(kind)) {
            maxnum = 1;
        } else if (kind == 15 && id == 23) {
            maxnum = 10;
        } else if (kind == 15 && id == 102) {
            maxnum = 1;
        } else {
            maxnum = 999;
        }

        return maxnum;
    }

    public int getMargin(int kind, int id) {
        int margin = getMaxItemNum(kind, id);
        Item item = getItem(kind, id);
        if (item != null) {
            margin -= item.num;
        }
        return margin;
    }

    public int itemIndexOf(int kind, int id) {
        for (int l = 0; l < this.items.size(); l++) {
            Item i = this.items.get(l);
            if (i.kind == kind && i.id == id)
                return l;
        }
        return -1;
    }

    public Item[][] getNonexistentItems() {
        boolean debug = ((GameSetting.getSetting()).version < 0);
        if (!debug) {
            Item[] list = getList(ItemFilter.Privately.get());
            Item[] slist = getList(new SkillChecker());
            return new Item[][] { list, slist };
        }
        return ii0;
    }

    private static final Item[] i0 = new Item[0];
    private static final Item[][] ii0 = new Item[][] { i0, i0 };

    private class SkillChecker
            implements ItemFilter {
        ItemFilter p = ItemFilter.Privately.get();

        public boolean filter(UserItems.Item i) {
            return (i.kind == 5 && !this.p.filter(i) && check(i));
        }

        private boolean check(UserItems.Item i) {
            boolean ret = false;
            GameSetting set = GameSetting.getSetting();
            for (int l = 0; l < 3; l++) {
                int sid = i.getTuneUp(l);
                if (sid > 0 && set.isPrivately(6, sid)) {
                    ret = true;

                    break;
                }
            }
            if (!ret && set.isLanguage("ja")) {

                int sid = i.getTuneUp(2);
                if (sid == 251 || sid == 256) {
                    boolean reset = false;
                    ItemAdditionalData iadata = UserItems.this.getItemAdditionalData();
                    if (iadata == null) {
                        reset = true;
                        iadata = new ItemAdditionalData();
                        UserItems.this.setItemAdditionalData(iadata);
                    } else {
                        ItemAdditionalData.Info info = iadata.getSkillInfo_Skill(i.id, sid);
                        if (info == null)
                            reset = true;
                    }
                    if (reset) {
                        iadata.updateSkillInfo(i);
                    }
                }
            }
            return ret;
        }

        private SkillChecker() {
        }
    }

    public void mergeItem(Item i, boolean overwriteskill) throws AmpedException {
        if (i == null)
            return;
        int idx = itemIndexOf(i.kind, i.id);
        if (idx < 0) {
            Item _i = new Item();
            _i.set(i);
            this.items.add(_i);
        } else {
            Item _i = this.items.get(idx);
            if (i.hasNumber()) {
                if (!_i.isRotenTicket())
                    _i.num = (short) (_i.num + i.num);
            } else if (overwriteskill &&
                    i.tuneup != 0L) {
                _i.tuneup = i.tuneup;
            }

        }
    }

    public void addItem(Item i) throws AmpedException {
        addItem(i, false, true);
    }

    public void addItem(Item i, boolean addtop) throws AmpedException {
        addItem(i, addtop, true);
    }

    public void addItem(Item i, boolean addtop, boolean addMoney) throws AmpedException {
        if (i.gettype == 19)
            return;
        switch (i.kind) {
            case 22:
                if (!addMoney) {
                    throw (new AmpedException(240)).setArgument(i);
                }
                addGameMoney(i.id * ((i.num > 1) ? i.num : 1));
                return;
            case 23:
                if (!addMoney) {
                    throw (new AmpedException(240)).setArgument(i);
                }
                addRealMoney(i.id * ((i.num > 1) ? i.num : 1));
                return;
            case 9:
                if (getKindNum(9) >= getSkinStock() || i.num > 1)
                    throw (new AmpedException(103)).setArgument(i);
                break;
            case 15:
                if (i.isRotenTicket()) {
                    EventItem ei = GameSetting.getSetting().getEventItem(i.id);
                    EventItem.RotenTicketData t = (EventItem.RotenTicketData) ei.arg;

                    if (i.num > 1 || (!t.indefinite && !(i instanceof TerminableItem))) {
                        throw (new AmpedException(103)).setArgument(i);
                    }
                    int usable = 0;
                    long start = 0L;
                    if (!t.indefinite && i instanceof TerminableItem) {
                        usable = ((TerminableItem) i).usableDays;
                        start = ((TerminableItem) i).startTime;
                    }
                    if (!addRotenTicket(i.id, usable, start, t.indefinite)) {
                        return;
                    }
                }
                break;
        }

        ItemAdditionalData addiData = getItemAdditionalData();
        int idx = itemIndexOf(i.kind, i.id);
        if (idx < 0) {
            Item _i = new Item(i);
            if (addtop) {
                this.items.add(0, _i);
            } else {
                this.items.add(_i);
            }

        } else if (i.hasNumber()) {
            Item _i = this.items.get(idx);
            _i.num = (short) Math.min(getMaxItemNum(i.kind, i.id), _i.num + i.num);
        } else if (i.kind != 17 || !RankData.Rank.isExpire(i.id)) {

            if (addiData == null || !addiData.hasExpireTime(i)) {
                throw (new AmpedException(98)).setArgument(i);
            }
        }

        if (ItemAdditionalData.isExpireItem(i)) {
            if (i instanceof TerminableItem && ((TerminableItem) i).isTerminable()) {

                if (addiData == null) {
                    addiData = new ItemAdditionalData();
                    setItemAdditionalData(addiData);
                }
                addiData.updateExpireTimeInfo(i, ((TerminableItem) i).usableDays);

            } else if (addiData != null) {
                addiData.removeExpireTimeInfo(i);
            }
        }
    }

    public void addEditItem(Item i) {
        Item _i = new Item();
        _i.set(i);
        this.items.add(_i);
    }

    public long getRotenTicketLimit(int id) {
        Inttable t = (Inttable) getParam(9);
        if (t == null || t.get(id) == null)
            return 0L;
        return ((Number) t.get(id)).longValue();
    }

    public long getRotenTicketRemain(int id, long currenttime) {
        return Math.max(0L, getRotenTicketLimit(id) - currenttime);
    }

    private boolean addRotenTicket(int id, int day, long sent, boolean indefinite) {
        if (!indefinite) {
            long current = System.currentTimeMillis();
            long limit = sent + (day * 24 * 60 * 60) * 1000L;
            if (limit <= current) {
                return false;
            }

            long remaining = getRotenTicketRemain(id, System.currentTimeMillis());

            if (remaining > 0L) {
                remaining = Math.min(limit - current + remaining, MAX_ROTEN_TICKET_LIMIT);
                limit = current + remaining;
            }

            Inttable t = (Inttable) getParam(9);
            if (t == null) {
                t = new Inttable();
                putParam(9, t);
            }
            t.put(id, Long.valueOf(limit));
        }
        return (hasItems(15, id) <= 0);
    }

    public void addShopItem(ShopData.Item si, int num, boolean trying, int[] selectid, float couponrate)
            throws AmpedException {
        addShopItem(si, num, trying, selectid, false, couponrate);
    }

    public void addShopItem(ShopData.Item si, int num, boolean trying, int[] selectid, boolean bonusitem,
            float couponrate) throws AmpedException {
        byte gettype;
        int price;
        if (!bonusitem && !trying && si.priceCheck(num, this.gamemoney, this.realmoney, this.vippoint)) {
            throw new AmpedException(97);
        }

        long gettime = System.currentTimeMillis();

        if (trying) {
            gettype = 12;
            price = 0;
        } else {
            switch (si.getMoneyType()) {
                case 0:
                    gettype = 6;
                    break;
                case 1:
                    gettype = 7;
                    break;
                case 2:
                    gettype = 14;
                    break;
                default:
                    throw new IllegalStateException();
            }

            price = si.getPrice();
        }

        addShopItemProc(si, num, gettime, gettype, price, selectid, bonusitem);

        if (!trying && !bonusitem) {

            if ((GameSetting.getSetting()).shopcouponenabled) {
                switch (si.getMoneyType()) {
                    case 0:
                        addGameMoney(-si.getPrice(num, couponrate));
                        break;
                    case 1:
                        addRealMoney(-si.getPrice(num, couponrate));
                        break;
                    case 2:
                        addVIPPoint(-si.getPrice(num, couponrate));
                        break;
                }

            } else {
                switch (si.getMoneyType()) {
                    case 0:
                        addGameMoney(-si.getPrice(num));
                        break;
                    case 1:
                        addRealMoney(-si.getPrice(num));
                        break;
                    case 2:
                        addVIPPoint(-si.getPrice(num));
                        break;
                }

            }
        }
    }

    private void addShopItemProc(ShopData.Item si, int num, long gettime, byte gettype, int price, int[] selectid,
            boolean bonusitem) throws AmpedException {
        ShopItemPackData ip;
        int len;
        ShopAutoSetItemPackData aip;
        NPCItem npc;
        UserPracticeData upd;
        switch (si.kind) {
            case 10:
                throw new AmpedException(100);

            case 21:
                ip = FluidSetting.getSetting().getItemPack(si.id);
                len = ip.itemlist.length;
                if (len > 0) {
                    int divprice = price / len;
                    for (int n = 0; n < len; n++) {
                        ShopData.Item psi = ip.itemlist[n];
                        addShopItemProc(psi, psi.num, gettime, gettype, divprice, null, false);
                    }
                }
                return;

            case 26:
                aip = FluidSetting.getSetting().getAutoSetItemPack(si.id);
                len = selectid.length;
                if (len > 0) {
                    int divprice = price / len;
                    for (int n = 0; n < len; n++) {
                        ShopData.Item sitem;
                        if (bonusitem) {
                            sitem = aip.bonusitem[selectid[n]];
                        } else {
                            sitem = aip.itemlist[selectid[n]];
                        }
                        addShopItemProc(sitem, sitem.num, gettime, gettype, divprice, null, false);
                    }
                }
                return;

            case 0:
                addShopItemToItems(si, num, gettime, gettype, price);

                addStyleDefItems(si.id);
                return;

            case 25:
                npc = GameSetting.getSetting().getNPCItem(si.id);
                if (npc == null)
                    throw new AmpedException(111);
                upd = (UserPracticeData) this.params.get(12);
                if (upd != null && upd.getNpcUsableTime(npc.npcid) == -1L)
                    throw new AmpedException(111);
                if (si.usableDays == 0) {
                    setPracticeNpcDay(npc.npcid, -1);
                } else {
                    PracticeData data = GameSetting.getSetting().getPractice();
                    long nowtime = ServerTime.getTime() / 1000L;
                    if (upd != null && upd.npcusabletime != null) {
                        long addtime = data.getNpcExtendTime(si.usableDays);
                        long maxtime = data.getNpcExtendMaxTime();
                        int idx = upd.getNpcidIndex(npc.npcid);
                        Object o = upd.npcusabletime.get(idx);
                        if (o != null) {
                            long npcusabletime = ((Long) o).longValue();
                            if (data.overNpcExtendMaxTime(idx, addtime, nowtime, maxtime, npcusabletime))
                                throw new AmpedException(111);
                        }
                    }
                    addPracticeNpcDay(npc.npcid, si.usableDays, nowtime);
                }
                return;
        }

        addShopItemToItems(si, num, gettime, gettype, price);
    }

    private void addShopItemToItems(ShopData.Item si, int num, long gettime, byte gettype, int price)
            throws AmpedException {
        TerminableItem i = new TerminableItem();
        i.kind = si.kind;
        i.id = si.id;
        i.num = (short) num;
        i.gettime = gettime;
        i.usableDays = si.usableDays;
        i.gettype = gettype;
        i.price = price;
        addItem(i);
    }

    public void addStyleDefItems(int style) throws AmpedException {
        StyleData sd = GameSetting.getSetting().getStyle(style);
        long t = System.currentTimeMillis();

        if (sd.defhead != 0) {
            Item i = new Item((byte) 1, sd.defhead);
            i.gettime = t;
            i.gettype = 13;
            if (isGetable(i))
                addItem(i);

        }
        if (sd.defface != 0) {
            Item i = new Item((byte) 2, sd.defface);
            i.gettime = t;
            i.gettype = 13;
            if (isGetable(i))
                addItem(i);

        }
        if (sd.defbody != 0) {
            Item i = new Item((byte) 3, sd.defbody);
            i.gettime = t;
            i.gettype = 13;
            if (isGetable(i))
                addItem(i);

        }
        if (sd.defdeco != 0) {
            Item i = new Item((byte) 4, sd.defdeco);
            i.gettime = t;
            i.gettype = 13;
            if (isGetable(i))
                addItem(i);
        }
    }

    public void removeItem() {
        this.items.clear();

        clearItemAdditionalData();
    }

    public boolean removeItem(Item i) {
        if (i.kind == 25) {
            NPCItem npc = GameSetting.getSetting().getNPCItem(i.id);
            if (npc != null)
                return removePracticeNpc(npc.npcid);
            return false;
        }
        int idx = itemIndexOf(i.kind, i.id);
        if (i.isMoney()) {
            if (i.kind == 22) {
                addGameMoney(-i.id * i.num);
            } else if (i.kind == 23) {
                addRealMoney(-i.id * i.num);
            }
            return true;
        }
        if (idx < 0) {
            return false;
        }
        Item _i = get(idx);

        if (i.hasNumber()) {
            if (_i.num < i.num) {
                return false;
            }

            _i.num = (short) (_i.num - i.num);
            return true;
        }
        this.items.remove(idx);
        removeItemAdditionalInfo(i.kind, i.id);
        if (i.kind == 17) {
            UserRank ur = getUserRank(false);
            if (ur != null)
                ur.remove(i.id);
        }
        return true;
    }

    public boolean removeItem(int kind, int id, int num) {
        int idx = itemIndexOf(kind, id);
        if (idx < 0) {
            return false;
        }
        Item _i = get(idx);

        if (_i.hasNumber()) {
            if (_i.num <= 0)
                return false;
            _i.num = (short) Math.max(_i.num - num, 0);
            return true;
        }
        return removeItemFromList(_i);
    }

    public boolean removeItemFromList(Item i) {
        int idx = itemIndexOf(i.kind, i.id);
        if (idx < 0)
            return false;

        this.items.remove(idx);
        removeItemAdditionalInfo(i.kind, i.id);
        if (i.kind == 17) {
            UserRank ur = getUserRank(false);
            if (ur != null)
                ur.remove(i.id);
        }
        return true;
    }

    public int size() {
        return this.items.size();
    }

    public Item get(int i) {
        return this.items.get(i);
    }

    public int getGameMoney() {
        return this.gamemoney;
    }

    public void setGameMoney(int i) {
        this.gamemoney = i;
    }

    public int addGameMoney(int i) {
        return this.gamemoney += i;
    }

    public int addRealMoney(int i) {
        return addRealMoney(i, false);
    }

    public int addRealMoney(int i, boolean toll) {
        this.realmoney += i;
        if (!toll)
            addFreeRealMoney(i);
        return this.realmoney;
    }

    public int addVIPPoint(int i) {
        return this.vippoint += i;
    }

    public int getRealMoney() {
        return this.realmoney;
    }

    public void setRealMoney(int i) {
        setRealMoney(i, false);
    }

    public void setRealMoney(int i, boolean toll) {
        this.realmoney = i;
        if (!toll)
            setFreeRealMoney(i);

    }

    public int getVIPPoint() {
        return this.vippoint;
    }

    public void setVIPPoint(int p) {
        this.vippoint = p;
    }

    public int getVIPLevel() {
        return this.viplv;
    }

    public void setVIPLevel(int p) {
        this.viplv = p;
    }

    public Object getParam(int key) {
        return this.params.get(key);
    }

    public void putParam(int key, Object o) {
        this.params.put(key, o);
    }

    public UserRank getUserRank(boolean create) {
        UserRank ur = (UserRank) this.params.get(5);
        if (ur == null && create) {
            ur = new UserRank();
            putParam(5, ur);
        }
        return ur;
    }

    public boolean addScoreRank(RankData.Rank r, long time) {
        if (r != null && !r.isPrivately() && r.isKind(0)) {
            Item i = getItem(17, r.idx);
            if (i == null) {
                i = new Item((byte) 17, r.idx, 0, 5, time, 0);
                try {
                    addItem(i, true);
                } catch (Exception ex) {
                    return false;
                }
            } else {
                i.gettime = time;
            }
            return true;
        }
        return false;
    }

    public boolean addStreetfightRank(RankData.Rank r, long time) {
        if (r != null && !r.isPrivately() && r.getKind() == 4) {
            Item i = getItem(17, r.idx);
            if (i == null) {
                i = new Item((byte) 17, r.idx, 0, 11, time, 0);
                try {
                    addItem(i, true);
                } catch (Exception ex) {
                    return false;
                }
            } else {
                i.gettime = time;
            }
            return true;
        }
        return false;
    }

    public RankData.Rank[] getChallengeableRank() {
        ArrayList<RankData.Rank> a = new ArrayList();
        RankData.Rank[] rd = (GameSetting.getSetting()).rank.rank;
        for (int l = 0; l < rd.length; l++) {
            if (isChallengeableRank(rd[l]))
                a.add(rd[l]);
        }
        return a.<RankData.Rank>toArray(new RankData.Rank[a.size()]);
    }

    public boolean isChallengeableRank(RankData.Rank r) {
        if (r == null || r.isPrivately() || !r.isKind(2))
            return false;
        if (hasItem(17, r.idx))
            return false;

        int i = r.idx;
        RankData.Rank[] rd = (GameSetting.getSetting()).rank.rank;
        if (i == 0 || rd[i - 1] == null || !RankData.isSameGroup(rd[i], rd[i - 1]))
            return true;

        Item p = getItem(17, i - 1);
        return (p != null && p.gettime > 0L);
    }

    public void addChallengeRank(RankData.Rank r, int progress) {
        if (r != null && !r.isPrivately() && r.isKind(2) && !hasItem(17, r.idx)) {
            try {
                addItem(new Item((byte) 17, r.idx, 0, 4, 0L, 0), true);

                UserRank rank = getUserRank(true);
                rank.addRankInfo(r.idx, (r.getGroup()).progtype, progress);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void completeChallengeRank(RankData.Rank r, long time) {
        if (r != null && !r.isPrivately() && r.isKind(2)) {
            UserRank rank = getUserRank(false);
            Item i = getItem(17, r.idx);
            if (rank != null && i != null) {
                UserRank.RankProgress p = rank.getRankInfo(r.idx);
                if (p != null && p.isComplete()) {
                    rank.remove(r.idx);
                    i.gettime = time;
                }
            }
        }
    }

    public void addSpecialRank(RankData.Rank r, long time) {
        if (r != null && !r.isPrivately() && r.isKind(1)) {
            Item i = getItem(17, r.idx);
            if (i == null) {
                i = new Item((byte) 17, r.idx, 0, 10, time, 0);
                try {
                    addItem(i, true);
                } catch (Exception exception) {
                }
            } else {
                i.gettime = time;
            }
        }
    }

    public void setSpecialRankTime(long t) {
        putParam(11, Long.valueOf(t));
    }

    public long getSpecialRankTime() {
        Long t = (Long) getParam(11);
        long time = (t != null) ? t.longValue() : 0L;
        if (time > 0L)
            time = (time - 1204297200000L) / 5000L * 5000L + 1204297200000L;
        return time;
    }

    public int[] getEnableSpecialRanks() {
        GameSetting set = GameSetting.getSetting();

        ArrayList<Item> a = new ArrayList();
        long time = getSpecialRankTime();

        for (Iterator<Item> itr = this.items.iterator(); itr.hasNext();) {
            Item t = itr.next();
            if (t.kind == 17 && time <= t.gettime) {
                RankData.Rank r = set.getRank(t.id);
                if (r != null && r.isKind(1))
                    a.add(t);

            }
        }
        int[] sr = (int[]) null;
        if (a.size() > 0) {
            sr = new int[a.size()];
            for (int l = 0; l < sr.length; l++) {
                sr[l] = ((Item) a.get(l)).id;
            }
        }
        return sr;
    }

    public void setItemAdditionalData(ItemAdditionalData iaData) {
        putParam(7, iaData);
    }

    public ItemAdditionalData getItemAdditionalData() {
        ItemAdditionalData iad = (ItemAdditionalData) getParam(7);

        if (iad == null) {
            iad = new ItemAdditionalData();
            setItemAdditionalData(iad);
        }
        return iad;
    }

    private void clearItemAdditionalData() {
        ItemAdditionalData data = getItemAdditionalData();
        if (data != null) {
            data.clear();
        }
    }

    private void removeItemAdditionalInfo(int kind, int id) {
        ItemAdditionalData data = getItemAdditionalData();
        if (data != null) {
            data.removeInfos(kind, id);
        }
    }

    private boolean isNowGuildbattleSeason(Item r) {
        ItemAdditionalData iaData = getItemAdditionalData();
        if (iaData != null) {
            return iaData.isNowGuildbattleSeason(r);
        }
        return false;
    }

    public ItemAdditionalData.Info getInfoGuildbattleSeason(Item r) {
        ItemAdditionalData iaData = getItemAdditionalData();
        if (iaData != null) {
            return iaData.getInfo(r.kind, r.id, 3, 0);
        }
        return null;
    }

    public boolean isExpired(Item i) {
        return (i != null && isExpired(i.kind, i.id));
    }

    public boolean isExpired(int kind, int id) {
        ItemAdditionalData d = getItemAdditionalData();
        return (d != null && d.isExpired(kind, id));
    }

    public boolean hasExpireTime(int kind, int id) {
        ItemAdditionalData d = getItemAdditionalData();
        return (d != null && d.hasExpireTime(kind, id));
    }

    static final int[] medalrate = new int[] { 100, 100, 100, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 5,
            5 };

    public boolean newMedalCheck(int getscore) {
        if (!(GameSetting.getSetting()).new_medal || (GameSetting.getSetting()).disablegetnewmedal)
            return false;
        if ((GameSetting.getSetting()).checkmedalinterval == 0)
            return false;

        Calendar c = ServerTime.getCalendarInstance();
        int day = c.get(6);
        int[] i = (int[]) this.params.get(8);
        if (i == null) {
            i = new int[] { 0, 0, day };
            this.params.put(8, i);
        }
        if (i[2] != day) {
            i[0] = 0;
            i[1] = 0;
            i[2] = day;
        }
        i[0] = i[0] + getscore;
        if (i[0] + getscore >= (GameSetting.getSetting()).checkmedalinterval) {

            if (i[0] % 100 < medalrate[i[1]]) {
                i[0] = 0;
                i[1] = i[1] + 1;
                return true;
            }
            i[0] = 0;
        }
        return false;
    }

    public void incNewMedal(int num) {
        newMedalCheck(0);
        int[] i = (int[]) this.params.get(8);
        i[1] = i[1] + num;
    }

    public ParamTalkFlags getParamValue() {
        return (ParamTalkFlags) getParam(10);
    }

    public void setParamValue(ParamTalkFlags p) {
        putParam(10, p);
    }

    public int getParamValue(int id) {
        ParamTalkFlags flags = (ParamTalkFlags) getParam(10);
        if (flags == null) {
            flags = new ParamTalkFlags();
            putParam(10, flags);
        }
        return flags.getValue(id);
    }

    public void setParamValue(int id, int v) {
        ParamTalkFlags flags = (ParamTalkFlags) getParam(10);
        if (flags == null)
            flags = new ParamTalkFlags();
        flags.setValue(id, v);
        putParam(10, flags);
    }

    public static Item[] convertTreasure(Item[] i) {
        if (i == null)
            return null;

        GameSetting set = GameSetting.getSetting();
        ArrayList<Item> a = new ArrayList();

        for (int l = 0; l < i.length; l++) {
            if (i[l] != null) {
                Item t = i[l].clone();
                if (set.new_medal && t.isMedal()) {
                    t.id = 42;
                    t.num = (short) (t.num * 5);
                }
                if (!set.disablegetnewmedal || !t.isLuckyCoin())
                    a.add(t);
            }
        }
        return (a.size() == 0) ? null : a.<Item>toArray(new Item[a.size()]);
    }

    public void addPracticeNpcDay(int[] enemy, int enemynum) {
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        if (upd == null)
            upd = new UserPracticeData();

        PracticeData data = GameSetting.getSetting().getPractice();
        for (int l = 0; l < enemynum; l++) {
            if (enemy[l] > 0 && !data.isExcludeNpc(enemy[l])) {
                upd.addNpcUsableTime(enemy[l], data.getNpcExtendTime(), System.currentTimeMillis() / 1000L,
                        data.getNpcExtendMaxTime());
            }
        }

        putParam(12, upd);
    }

    public void addPracticeNpcDay(ArrayList<Integer> enemy) {
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        if (upd == null)
            upd = new UserPracticeData();
        PracticeData data = GameSetting.getSetting().getPractice();
        for (int l = 0; l < enemy.size(); l++) {
            if (((Integer) enemy.get(l)).intValue() > 0
                    && !data.isExcludeNpc(((Integer) enemy.get(l)).intValue())) {
                upd.addNpcUsableTime(((Integer) enemy.get(l)).intValue(), data.getNpcExtendTime(),
                        System.currentTimeMillis() / 1000L, data.getNpcExtendMaxTime());
            }
        }
        putParam(12, upd);
    }

    public void addPracticeNpcDay(int[] enemy, int[] busteruid, int enemynum, int myuid) {
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        if (upd == null)
            upd = new UserPracticeData();

        PracticeData data = GameSetting.getSetting().getPractice();
        for (int l = 0; l < enemynum; l++) {
            if (busteruid[l] == myuid && enemy[l] > 0 && !data.isExcludeNpc(enemy[l])) {
                upd.addNpcUsableTime(enemy[l], data.getNpcExtendTime(), System.currentTimeMillis() / 1000L,
                        data.getNpcExtendMaxTime());
            }
        }

        putParam(12, upd);
    }

    public void addPracticeNpcDay(int npcid, int days, long nowtime) {
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        if (upd == null)
            upd = new UserPracticeData();
        PracticeData data = GameSetting.getSetting().getPractice();
        if (npcid > 0 && !data.isExcludeNpc(npcid)) {
            upd.addNpcUsableTime(npcid, data.getNpcExtendTime(days), nowtime, data.getNpcExtendMaxTime());
        }
        putParam(12, upd);
    }

    public void setPracticeNpcDay(int npcid, int days) {
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        if (upd == null)
            upd = new UserPracticeData();
        PracticeData data = GameSetting.getSetting().getPractice();
        if (npcid > 0 && !data.isExcludeNpc(npcid)) {
            upd.setNpcUsableTime(npcid, days);
        }
        putParam(12, upd);
    }

    public boolean removePracticeNpc(int npcid) {
        UserPracticeData upd = (UserPracticeData) this.params.get(12);
        if (upd != null && upd.removeNpc(npcid)) {
            putParam(12, upd);
            return true;
        }
        return false;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeShort(this.items.size() | 0x8000);
        for (int l = 0; l < this.items.size(); l++) {
            Item i = this.items.get(l);
            if (i instanceof TerminableItem) {
                i = new Item(i);
            }
            i.writeExternal(out);
        }
        out.writeInt(this.gamemoney);
        out.writeInt(this.realmoney);
        out.writeObject(this.params);
        StreamTool.writePackedInt(this.vippoint, out);
        out.writeByte(this.viplv);
        out.writeShort(0);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int num = in.readShort() & 0xFFFF;
        boolean b = ((num & 0x8000) != 0);
        num &= 0x7FFF;

        this.items.clear();
        this.items.ensureCapacity(num);
        for (int l = 0; l < num; l++) {
            Item i = new Item();
            i.readExternal(in);
            this.items.add(i);
        }
        this.gamemoney = in.readInt();
        this.realmoney = in.readInt();
        if (b) {
            this.params = (Inttable) in.readObject();
            this.vippoint = StreamTool.readPackedInt(in);
            this.viplv = in.readByte();
            in.readShort();
        }
    }

    public static class Item
            implements Externalizable, Cloneable {
        static final long timeoffs = 1204297200000L;

        static final long ALLTUNE_MASK = 1152921504606846975L;

        static final int TUNEUP_EXTEND = 128;

        static final long ALLTUNE_MASK2 = 72057594037927935L;

        static final int TUNEUP_EXTEND2 = 64;

        static final int TUNEUP_EXTEND_FLAG = 192;

        public byte kind;

        public int id;

        public byte gettype;

        public long gettime;

        public short num;
        public int price;
        public int who;
        public long tuneup;
        public String makername;
        Texture icon;

        public Item() {
        }

        public Item(byte _kind, int _id, int _num, int _type, long _time, int _price) {
            this.kind = _kind;
            this.id = _id;
            this.num = (short) _num;
            this.gettype = (byte) _type;
            this.gettime = _time;
            this.price = _price;
        }

        public Item(byte _kind, int _id) {
            this.kind = _kind;
            this.id = _id;
            this.num = 1;
            this.gettype = 15;
            this.gettime = System.currentTimeMillis();
            this.price = 0;
        }

        public Item(byte _kind, int _id, int _num) {
            this(_kind, _id);
            this.num = (short) _num;
        }

        public Item(Item item) {
            set(item);
        }

        public void set(Item i) {
            this.kind = i.kind;
            this.id = i.id;
            this.gettype = i.gettype;
            this.gettime = i.gettime;
            this.num = i.num;
            this.price = i.price;
            this.who = i.who;
            this.tuneup = i.tuneup;
            this.makername = i.makername;
        }

        public Item clone() {
            try {
                return (Item) super.clone();
            } catch (Exception e) {
                return null;
            }
        }

        public boolean equals(int kind_, int id_, int num_, long tuneup_) {
            if (this.kind != kind_)
                return false;
            if (this.id != id_)
                return false;
            if (this.num != num_)
                return false;
            if (this.tuneup != tuneup_)
                return false;
            return true;
        }

        public boolean equals(int kind_, int id_, int num_) {
            return equals(kind_, id_, num_, this.tuneup);
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Item))
                return false;
            Item i = (Item) o;
            return equals(i.kind, i.id, i.num, i.tuneup);
        }

        public boolean contain(Object o) {
            if (o == this)
                return true;
            if (o instanceof Item)
                return equals(o);
            if (o instanceof Item[]) {
                Item[] item = (Item[]) o;
                for (int i = 0; i < item.length; i++) {
                    if (equals(item[i]))
                        return true;
                }
            }
            return false;
        }

        public AmpedItem getAmpedItem() {
            return GameSetting.getSetting().getAmpedItem(this.kind, this.id);
        }

        public void setPotential(int skill, int pow) {
            this.tuneup &= 0x3FFFFFFFFFFL;
            this.tuneup |= skill << 42L & 0xFFFC0000000000L;
            this.tuneup |= pow << 56L & 0xFF00000000000000L;
        }

        public int getPotentialSkill() {
            return (int) (this.tuneup >>> 42L & 0x3FFFL);
        }

        public int getPotentialSkillPow() {
            return (int) (this.tuneup >>> 56L & 0xFFL);
        }

        public void setTuneUp(int i, int skill) {
            if (i >= 0 && i < 3) {
                int shift = (2 - i) * 14;
                long mask = 16383L << shift;
                this.tuneup &= mask ^ 0xFFFFFFFFFFFFFFFFL;
                this.tuneup |= skill << shift & mask;
            }
        }

        public int getTuneUp(int i) {
            switch (i) {
                case 0:
                    return (int) (this.tuneup >>> 28L) & 0x3FFF;
                case 1:
                    return (int) (this.tuneup >>> 14L) & 0x3FFF;
                case 2:
                    return (int) this.tuneup & 0x3FFF;
            }
            return 0;
        }

        public boolean isValid(int skill1, int skill2, int skill3) {
            if (skill1 != getTuneUp(0))
                return false;
            if (skill2 != getTuneUp(1))
                return false;
            if (skill3 != getTuneUp(2))
                return false;
            return true;
        }

        public void setAllTuneUp(long tune) {
            this.tuneup = tune;
        }

        public long getAllTuneUp() {
            return this.tuneup;
        }

        public int getTuneLevel() {
            int lv = 0;
            GameSetting set = GameSetting.getSetting();
            for (int l = 0; l < 3; l++) {
                int id = getTuneUp(l);
                SkillData sk = set.getSkill(id);
                if (sk != null)
                    lv += sk.tunelevel;
            }
            return lv;
        }

        public String getName() {
            String name = null;
            if (this.kind == 5 && this.makername != null) {
                AccessoryData a = GameSetting.getSetting().getAccs(this.id);
                if (a.uniqname != null) {
                    name = String.format(a.uniqname, new Object[] { this.makername });
                }
            }
            if (name == null)
                name = getAmpedItem().getName();
            if ((GameSetting.getSetting()).version <= 0)
                name = String.valueOf(name) + ":" + this.id;
            return name;
        }

        public boolean isMedal() {
            return (this.kind == 15 && this.id == 1);
        }

        public boolean isLuckyCoin() {
            return (this.kind == 15 && this.id == 42);
        }

        public boolean isDungeonMaterial() {
            return (this.kind == 12 && this.id > 1500 && this.id <= 2000);
        }

        public boolean isAccessoryCore() {
            if (this.kind == 12) {
                MaterialData m = GameSetting.getSetting().getMaterial(this.id);
                return (m != null && m.alchemy != null);
            }
            return false;
        }

        public boolean isRotenTicket() {
            if (this.kind == 15) {
                EventItem ei = GameSetting.getSetting().getEventItem(this.id);
                return (ei != null && ei.arg instanceof EventItem.RotenTicketData);
            }
            return false;
        }

        public boolean isBoostTicket() {
            return !((this.kind != 15 || (this.id != 29 && this.id != 30 && this.id != 31))
                    && !isEventItemBoostTicket() && !isDropItemBoostTicket());
        }

        public boolean isEventItemBoostTicket() {
            return (this.kind == 15 && this.id == 36);
        }

        public boolean isDropItemBoostTicket() {
            return (this.kind == 15 && this.id >= 58 && this.id <= 66);
        }

        public boolean isMoney() {
            return !(this.kind != 22 && this.kind != 23);
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(0x40 | this.kind & 0xFFFFFF3F);
            if (this.kind == 9 || isMoney()) {
                out.writeInt(this.id);
            } else {
                out.writeShort(this.id);
            }
            if (hasNumber() || isMoney())
                StreamTool.writePackedInt(this.num, out);

            out.writeByte(this.gettype | ((this.makername != null) ? 128 : 0));
            StreamTool.writePackedInt(Math.max(0, (int) ((this.gettime - 1204297200000L) / 5000L)), out);
            if (hasPrice())
                StreamTool.writePackedInt(this.price, out);
            if (hasPresenter())
                out.writeInt(this.who);
            if (this.kind == 5) {
                out.writeInt((int) (this.tuneup >>> 32L & 0xFFFFFFFFFFFFFFFFL));
                out.writeInt((int) (this.tuneup & 0xFFFFFFFFFFFFFFFFL));
            }
            if (this.makername != null)
                out.writeUTF(this.makername);

        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            byte b = in.readByte();

            this.kind = (byte) (b & 0xFFFFFF3F);
            int tuneupextend = b & 0xC0;
            if (this.kind == 9 || isMoney()) {
                this.id = in.readInt();
            } else {
                this.id = in.readShort() & 0xFFFF;
            }
            if (hasNumber() || isMoney())
                this.num = (short) StreamTool.readPackedInt(in);
            b = in.readByte();
            this.gettype = (byte) (b & Byte.MAX_VALUE);
            boolean hasmakername = ((b & 0x80) != 0);

            int t = StreamTool.readPackedInt(in);
            if (t == 0) {
                this.gettime = 0L;
            } else {
                this.gettime = t * 5000L + 1204297200000L;
            }

            if (hasPrice())
                this.price = StreamTool.readPackedInt(in);
            if (hasPresenter())
                this.who = in.readInt();
            if (this.kind == 5) {
                long _tuneup = in.readInt() & 0xFFFFFFFFL;
                if (tuneupextend == 0) {
                    this.tuneup = getExtendedTuneUp(_tuneup);
                    tuneupextend = 128;
                } else {
                    this.tuneup = _tuneup << 32L | in.readInt() & 0xFFFFFFFFL;
                }
                if (tuneupextend == 128) {
                    this.tuneup = getExtendedTuneUp2(this.tuneup);
                    tuneupextend = 64;
                }
            }
            if (hasmakername)
                this.makername = in.readUTF();

        }

        private long getExtendedTuneUp(long t) {
            long s0 = t >>> 20L & 0x3FFL;
            long s1 = t >>> 10L & 0x3FFL;
            long s2 = t & 0x3FFL;
            long et = s0 << 40L | s1 << 20L | s2;
            return et;
        }

        private long getExtendedTuneUp2(long t) {
            long s0 = t >>> 40L & 0xFFFFFL;
            long s1 = t >>> 20L & 0xFFFFFL;
            long s2 = t & 0xFFFFFL;
            long et = s0 << 28L | s1 << 14L | s2;
            return et;
        }

        public boolean hasPresenter() {
            return !(this.kind != 8 && this.kind != 9);
        }

        public boolean hasPrice() {
            return !(this.gettype != 6 && this.gettype != 7);
        }

        public boolean hasNumber() {
            return UserItems.hasNumber(this.kind);
        }

        public String toString() {
            return String.valueOf(super.toString()) + "kind:" + this.kind + ", id:" + this.id;
        }

        int checksum() {
            int i = 0;
            i += this.kind;
            i += this.id;
            i += this.num;
            return i;
        }
    }

    public static class TerminableItem
            extends Item {
        public int usableDays;

        public long startTime;

        public TerminableItem() {
        }

        public TerminableItem(byte _kind, int _id) {
            super(_kind, _id);
        }

        public TerminableItem(byte _kind, int _id, int _num, int days) {
            super(_kind, _id, _num);
            this.usableDays = days;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!super.equals(o))
                return false;
            if (!(o instanceof TerminableItem))
                return false;
            TerminableItem i = (TerminableItem) o;
            if (this.usableDays != i.usableDays)
                return false;
            return true;
        }

        public void set(TerminableItem i) {
            set(i);
            this.usableDays = i.usableDays;
            this.startTime = i.startTime;
        }

        public boolean isTerminable() {
            return (this.usableDays > 0);
        }

        public String getName() {
            String name = super.getName();
            GameSetting gs = GameSetting.getSetting();
            if (name != null && isTerminable()) {
                if (this.kind == 17 && RankData.Rank.isGuildBattle(this.id)) {
                    name = String.valueOf(name) + gs.getLocalText("$useritem.info.guildbattlereign",
                            new Object[] { Integer.valueOf(this.usableDays) });
                } else {
                    name = String.valueOf(name) + gs.getLocalText("$useritem.usabledays",
                            new Object[] { Integer.valueOf(this.usableDays) });
                }
            }
            return name;
        }

        public TerminableItem clone() {
            return (TerminableItem) super.clone();
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            super.readExternal(in);
            this.usableDays = StreamTool.readPackedInt(in);
            this.startTime = StreamTool.readSignedPackedLong(in);
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal(out);
            StreamTool.writePackedInt(this.usableDays, out);
            StreamTool.writeSignedPackedLong(this.startTime, out);
        }
    }

    public static class ParamTalkFlags implements Externalizable, Cloneable {
        int version;
        Inttable t;

        public ParamTalkFlags() {
            this.version = 0;
        }

        public boolean checkValid(UserTalkFlags flag) {
            return equals(flag);
        }

        public int getValue(int id) {
            return (this.t == null || this.t.get(id) == null) ? 0 : ((Number) this.t.get(id)).intValue();
        }

        public void setValue(int id, int v) {
            if (this.t == null)
                this.t = new Inttable();
            this.t.put(id, new Integer(v));
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.version = in.readInt();
            this.t = (Inttable) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(this.version);
            out.writeObject(this.t);
        }
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\data\UserItems.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
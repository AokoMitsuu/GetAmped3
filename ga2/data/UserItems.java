
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
        /* 144 */ this.items = new ArrayList<Item>();
        /* 145 */ this.params = new Inttable();
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
        /* 157 */ return getFullItems(false);
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
        /* 161 */ UserItems ui = new UserItems();
        /* 162 */ GameSetting set = GameSetting.getSetting();

        /* 165 */ _getFullItems((byte) 0, (AmpedItem[]) set.style, ui.items, addprivately);
        /* 166 */ _getFullItems((byte) 1, (AmpedItem[]) set.head, ui.items, addprivately);
        /* 167 */ _getFullItems((byte) 2, (AmpedItem[]) set.face, ui.items, addprivately);
        /* 168 */ _getFullItems((byte) 3, (AmpedItem[]) set.body, ui.items, addprivately);
        /* 169 */ _getFullItems((byte) 4, (AmpedItem[]) set.deco, ui.items, addprivately);
        /* 170 */ _getFullItems((byte) 5, (AmpedItem[]) set.accs, ui.items, addprivately);
        /* 171 */ _getFullItems((byte) 7, (AmpedItem[]) set.weapon, ui.items, addprivately);

        /* 174 */ _getFullItems((byte) 8, (AmpedItem[]) set.emo, ui.items, addprivately);
        /* 175 */ _getFullItems((byte) 18, (AmpedItem[]) set.emo, ui.items, addprivately);
        /* 176 */ _getFullItems((byte) 12, (AmpedItem[]) set.mat, ui.items, addprivately);
        /* 177 */ _getFullItems((byte) 13, (AmpedItem[]) set.furniture, ui.items, addprivately);
        /* 178 */ _getFullItems((byte) 14, (AmpedItem[]) set.myroom, ui.items, addprivately);

        /* 181 */ _getFullItems((byte) 16, (AmpedItem[]) set.voiceset, ui.items, addprivately);
        /* 182 */ _getFullItems((byte) 17, (AmpedItem[]) set.rank.rank, ui.items, addprivately);

        /* 186 */ Item i = new Item((byte) 15, 41, 100, 15, System.currentTimeMillis(), 0);
        /* 187 */ ui.items.add(i);
        /* 188 */ i = new Item((byte) 15, 46, 1, 15, System.currentTimeMillis(), 0);
        /* 189 */ ui.items.add(i);
        /* 190 */ i = new Item((byte) 15, 23, 10, 15, System.currentTimeMillis(), 0);
        /* 191 */ ui.items.add(i);
        /* 192 */ i = new Item((byte) 15, 24, 500, 15, System.currentTimeMillis(), 0);
        /* 193 */ ui.items.add(i);
        /* 194 */ i = new Item((byte) 15, 67, 500, 15, System.currentTimeMillis(), 0);
        /* 195 */ ui.items.add(i);
        /* 196 */ i = new Item((byte) 15, 68, 500, 15, System.currentTimeMillis(), 0);
        /* 197 */ ui.items.add(i);
        /* 198 */ i = new Item((byte) 15, 81, 500, 15, System.currentTimeMillis(), 0);
        /* 199 */ ui.items.add(i);
        /* 200 */ i = new Item((byte) 15, 82, 500, 15, System.currentTimeMillis(), 0);
        /* 201 */ ui.items.add(i);
        /* 202 */ for (int id = 91; id <= 99; id++) {
            /* 203 */ i = new Item((byte) 15, id, 10, 15, System.currentTimeMillis(), 0);
            /* 204 */ ui.items.add(i);
        }
        /* 206 */ ui.addRotenTicket(46, 1, System.currentTimeMillis(), false);

        /* 208 */ if (set.isLanguage(new String[] { "ja", "tw" })) {
            /* 209 */ Number n = (Number) ui.getParam(19);
            /* 210 */ if (n == null)
                ui.putParam(19, Integer.valueOf(1));

        }

        /* 214 */ ui.gamemoney = 100000;
        /* 215 */ ui.realmoney = 100000;

        /* 217 */ return ui;
    }

    public static UserItems getLanItems(boolean addprivately) {
        /* 224 */ UserItems ui = new UserItems();
        /* 225 */ GameSetting set = GameSetting.getSetting();

        /* 228 */ _getFullItems((byte) 1, (AmpedItem[]) set.head, ui.items, addprivately);
        /* 229 */ _getFullItems((byte) 2, (AmpedItem[]) set.face, ui.items, addprivately);
        /* 230 */ _getFullItems((byte) 3, (AmpedItem[]) set.body, ui.items, addprivately);
        /* 231 */ _getFullItems((byte) 4, (AmpedItem[]) set.deco, ui.items, addprivately);
        /* 232 */ _getFullItems((byte) 5, (AmpedItem[]) set.accs, ui.items, addprivately);
        /* 233 */ _getFullItems((byte) 7, (AmpedItem[]) set.weapon, ui.items, addprivately);

        /* 239 */ _getFullItems((byte) 16, (AmpedItem[]) set.voiceset, ui.items, addprivately);

        /* 242 */ return ui;
    }

    static void _getFullItems(byte kind, AmpedItem[] ai, ArrayList<Item> a, boolean addprivately) {
        /* 246 */ int num = hasNumber(kind) ? 500 : 0;
        /* 247 */ long time = System.currentTimeMillis();
        /* 248 */ Random r = (kind == 5) ? new Random(System.currentTimeMillis()) : null;
        /* 249 */ for (int l = 0; l < ai.length; l++) {
            /* 250 */ if (ai[l] != null && (addprivately || !ai[l].isPrivately())
                    && (kind != 17 || !((RankData.Rank) ai[l]).isKind(0))) {
                /* 251 */ Item i = new Item(kind, l, num, 15, time, 0);
                /* 252 */ if (kind == 5) {
                    /* 253 */ i.tuneup = getRandomTuneup(r);
                }
                /* 255 */ a.add(i);
            }
        }
    }

    public static UserItems getInitialItems(InitialItemsData iidata, long time) {
        /* 265 */ UserItems items = new UserItems();
        /* 266 */ if (iidata != null) {
            /* 267 */ int size = iidata.size();
            /* 268 */ for (int l = 0; l < size; l++) {
                /* 269 */ Item i = iidata.get(l);
                /* 270 */ if (items.isGetable(i)) {
                    try {
                        /* 272 */ Item a = i.clone();
                        /* 273 */ a.gettype = 1;
                        /* 274 */ a.gettime = time;
                        /* 275 */ items.addItem(a);

                        /* 277 */ if (a.kind == 0) {
                            /* 278 */ items.addStyleDefItems(a.id);
                        }
                        /* 280 */ } catch (AmpedException ampedException) {
                    }
                }
            }
            /* 283 */ items.setGameMoney(iidata.getGameMoney());
            /* 284 */ items.setRealMoney(iidata.getRealMoney(), true);
        }
        /* 286 */ return items;
    }

    public void confiscateItems() {
        /* 296 */ _confiscateItems((byte) 7);
        /* 297 */ _confiscateItems((byte) 12);
        /* 298 */ _confiscateItems((byte) 13);
        /* 299 */ _confiscateItems((byte) 16);
        /* 300 */ removeItem(15, 1, 1000);
        /* 301 */ removeItem(15, 22, 1000);
        /* 302 */ removeItem(15, 24, 1000);
    }

    void _confiscateItems(byte kind) {
        /* 307 */ Item[] item = getItems();

        /* 309 */ for (int l = 0; l < item.length; l++) {
            /* 310 */ Item i = item[l];
            /* 311 */ if (i != null) {
                /* 312 */ removeItem(kind, i.id, 1000);
            }
        }
    }

    public void fillItems() {
        /* 320 */ GameSetting set = GameSetting.getSetting();

        /* 322 */ _fillItems((byte) 0, (AmpedItem[]) set.style);
        /* 323 */ _fillItems((byte) 1, (AmpedItem[]) set.head);
        /* 324 */ _fillItems((byte) 2, (AmpedItem[]) set.face);
        /* 325 */ _fillItems((byte) 3, (AmpedItem[]) set.body);
        /* 326 */ _fillItems((byte) 4, (AmpedItem[]) set.deco);
        /* 327 */ _fillItems((byte) 5, (AmpedItem[]) set.accs);
        /* 328 */ _fillItems((byte) 7, (AmpedItem[]) set.weapon);

        /* 331 */ _fillItems((byte) 8, (AmpedItem[]) set.emo);
        /* 332 */ _fillItems((byte) 18, (AmpedItem[]) set.emo);
        /* 333 */ _fillItems((byte) 12, (AmpedItem[]) set.mat);
        /* 334 */ _fillItems((byte) 13, (AmpedItem[]) set.furniture);
        /* 335 */ _fillItems((byte) 14, (AmpedItem[]) set.myroom);
    }

    private void _fillItems(byte kind, AmpedItem[] ai) {
        /* 339 */ int num = hasNumber(kind) ? 50 : 0;
        /* 340 */ long time = System.currentTimeMillis();
        /* 341 */ Random r = (kind == 5) ? new Random(System.currentTimeMillis()) : null;
        /* 342 */ for (int l = 0; l < ai.length; l++) {
            /* 343 */ if (ai[l] != null && !ai[l].isPrivately()) {
                /* 344 */ Item i = new Item(kind, l, num, 15, time, 0);
                /* 345 */ if (kind == 5)
                    /* 346 */ i.tuneup = getRandomTuneup(r);

                /* 348 */ try {
                    addItem(i);
                } catch (Exception exception) {
                }
            }
        }
    }

    private static long getRandomTuneup(Random r) {
        /* 354 */ long ret = 0L;
        /* 355 */ GameSetting set = GameSetting.getSetting();
        /* 356 */ for (int l = 0; l < 3; l++) {
            /* 357 */ int s = r.nextInt(100) + l * 100 + 1 & 0x3FFF;
            /* 358 */ if (set.isPrivately(6, s))
                s = 0;
            /* 359 */ ret = ret << 14L | s;
        }

        /* 369 */ return ret;
    }

    public static boolean hasNumber(int kind) {
        /* 373 */ return !(kind != 7 && kind != 12 && kind != 13 && kind != 15 && kind != 18);
    }

    public UserItems clone() {
        /* 377 */ UserItems i = new UserItems();
        /* 378 */ int num = this.items.size();
        /* 379 */ i.items.ensureCapacity(num);
        /* 380 */ for (int l = 0; l < num; l++) {
            /* 381 */ i.items.add(((Item) this.items.get(l)).clone());
        }
        /* 383 */ i.gamemoney = this.gamemoney;
        /* 384 */ i.realmoney = this.realmoney;
        /* 385 */ i.params = this.params.deepClone();
        /* 386 */ i.vippoint = this.vippoint;
        /* 387 */ i.viplv = this.viplv;

        /* 389 */ return i;
    }

    public Item[] getItems() {
        /* 393 */ return (Item[]) this.items.toArray((Object[]) new Item[this.items.size()]);
    }

    public Item getItem(int kind, int id) {
        /* 397 */ if (kind == 22) {
            /* 398 */ if (getGameMoney() >= id)
                return new Item((byte) kind, id, getGameMoney() / id);
            /* 399 */ } else if (kind == 23) {
            /* 400 */ if (getRealMoney() >= id)
                return new Item((byte) kind, id, getRealMoney() / id);
        } else {
            /* 402 */ for (int l = 0; l < this.items.size(); l++) {
                /* 403 */ Item i = this.items.get(l);
                /* 404 */ if (i.kind == kind && i.id == id)
                    return i;
            }
        }
        /* 407 */ return null;
    }

    public Item getTerminableItem(int kind, int id) {
        /* 414 */ if (kind == 22) {
            /* 415 */ if (getGameMoney() >= id)
                return new Item((byte) kind, id, getGameMoney() / id);
            /* 416 */ } else if (kind == 23) {
            /* 417 */ if (getRealMoney() >= id)
                return new Item((byte) kind, id, getRealMoney() / id);
        } else {
            /* 419 */ ItemAdditionalData addiData = getItemAdditionalData();
            /* 420 */ for (int l = 0; l < this.items.size(); l++) {
                /* 421 */ Object item = this.items.get(l);
                /* 422 */ if (addiData == null || addiData.hasExpireTime((Item) item)) {
                    /* 423 */ Item i = (Item) item;
                    /* 424 */ if (i.kind == kind && i.id == id)
                        return i;
                }
            }
            /* 427 */ }
        return null;
    }

    public Item[] getList(ItemFilter f) {
        /* 431 */ ArrayList<Item> a = new ArrayList<Item>();
        /* 432 */ int size = this.items.size();
        /* 433 */ for (int l = 0; l < size; l++) {
            /* 434 */ Item i = this.items.get(l);
            /* 435 */ if (i != null && f.filter(i))
                a.add(i);
        }
        /* 437 */ return a.<Item>toArray(new Item[a.size()]);
    }

    public Item[] getList(int kind) {
        /* 441 */ return getList(new ItemFilter.Kind(kind));
    }

    public Item[] getList(int[] kind) {
        /* 445 */ return getList(new ItemFilter.KindList(kind));
    }

    public Item[] getListEditable(int kind) {
        /* 449 */ return getList(
                new ItemFilter.And(new ItemFilter[] { new ItemFilter.Kind(kind), new ItemFilter.Editable() }));
    }

    public int getKindNum(int kind) {
        /* 453 */ int c = 0;
        /* 454 */ for (int l = 0; l < this.items.size(); l++) {
            /* 455 */ Item i = this.items.get(l);
            /* 456 */ if (i.kind == kind)
                c++;
        }
        /* 458 */ return c;
    }

    public int getSkinStock() {
        /* 465 */ int stock = (GameSetting.getSetting()).defaultskinstock;
        /* 466 */ Object o = getParam(2);
        /* 467 */ if (o instanceof Number) {
            /* 468 */ stock += ((Number) o).intValue();
        }
        /* 470 */ return stock;
    }

    public boolean isSkinStockLimit() {
        /* 477 */ return (getSkinStock() >= 23);
    }

    public int getEquipSlot() {
        /* 484 */ int slot = 1;
        /* 485 */ Object o = getParam(19);
        /* 486 */ if (o instanceof Number) {
            /* 487 */ slot += ((Number) o).intValue();
        }
        /* 489 */ return slot;
    }

    public boolean isEquipSlotLimit() {
        /* 493 */ int equipslotitem_id = 48;
        /* 494 */ ETCItem ei = GameSetting.getSetting().getETCItem(equipslotitem_id);
        /* 495 */ int stockmax = ((int[]) ei.arg)[0];
        /* 496 */ return (getEquipSlot() >= stockmax);
    }

    public short getPracticeNpc() {
        /* 503 */ PracticeData data = GameSetting.getSetting().getPractice();
        /* 504 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 505 */ return (upd == null) ? data.defnpcnum : upd.getNpcAppearNum();
    }

    public boolean isPracticeNpcLimit() {
        /* 512 */ return (getPracticeNpc() >= (GameSetting.getSetting().getPractice()).maxnpcnum);
    }

    public int getMyRoomLv() {
        /* 519 */ Number num = (Number) getParam(3);
        /* 520 */ if (num == null) {
            /* 521 */ return 0;
        }
        /* 523 */ return num.intValue();
    }

    public int getFreeRealMoney() {
        /* 531 */ if (!(GameSetting.getSetting()).freermenabled)
            return 0;
        /* 532 */ Number num = (Number) getParam(13);
        /* 533 */ if (num == null)
            return 0;
        /* 534 */ return num.intValue();
    }

    public int getDiffFreeRealMoney(int arm, int oldfrm) {
        /* 541 */ if (!(GameSetting.getSetting()).freermenabled)
            return 0;
        /* 542 */ if (arm == 0 || oldfrm == 0)
            return 0;
        /* 543 */ if (arm < 0) {
            /* 544 */ int _arm = arm * -1;
            /* 545 */ return (_arm >= oldfrm) ? -oldfrm : arm;
        }
        /* 547 */ return (arm >= oldfrm) ? oldfrm : arm;
    }

    public void addFreeRealMoney(int i) {
        /* 552 */ if (!(GameSetting.getSetting()).freermenabled)
            /* 553 */ return;
        Number num = (Number) getParam(13);
        /* 554 */ if (num == null) {
            /* 555 */ putParam(13, (i <= 0) ? Integer.valueOf(0) : Integer.valueOf(i));
        } else {
            /* 557 */ int v = num.intValue() + i;
            /* 558 */ putParam(13, Integer.valueOf((v <= 0) ? 0 : v));
        }
    }

    public void setFreeRealMoney(int i) {
        /* 563 */ if (!(GameSetting.getSetting()).freermenabled)
            /* 564 */ return;
        putParam(13, Integer.valueOf(i));
    }

    public boolean isEquipableRank(int id, boolean isgm) {
        /* 571 */ return isEquipableRank(getItem(17, id), isgm);
    }

    public boolean isEquipableRank(Item r, boolean isgm) {
        /* 575 */ boolean ret = false;
        /* 576 */ if (r != null && r.kind == 17 && r.gettime > 0L) {
            /* 577 */ GameSetting set = GameSetting.getSetting();
            /* 578 */ RankData.Rank rd = set.getRank(r.id);
            /* 579 */ if (rd.isKind(3)) {
                /* 580 */ ret = isgm;
            }
            /* 582 */ else if (rd.isKind(1)) {
                /* 583 */ ret = (getSpecialRankTime() <= r.gettime);
            }
            /* 585 */ else if (rd.isKind(5)) {
                /* 586 */ ret = true;
            }
            /* 588 */ else if (rd.isKind(6)) {
                /* 589 */ ret = isNowGuildbattleSeason(r);
            } else {
                /* 591 */ ret = true;
                /* 592 */ for (Iterator<Item> itr = this.items.iterator(); itr.hasNext();) {
                    /* 593 */ Item i = itr.next();
                    /* 594 */ if (i.kind == 17 && rd.isSameType(set.getRank(i.id)) && (
                    /* 595 */ r.gettime < i.gettime || (r.gettime == i.gettime && r.id < i.id))) {
                        /* 596 */ ret = false;

                        break;
                    }
                }
            }
        }
        /* 603 */ return ret;
    }

    private static boolean isRequiredAvatar(int kind) {
        /* 607 */ return !(kind != 0 && kind != 3 && kind != 2 && kind != 17);
    }

    private static Item getEmptyAvator(int kind, boolean slot) {
        /* 611 */ return new Item((byte) kind, slot ? -1 : 0);
    }

    public static boolean isEquipable(Item i, UserInfo ui) {
        /* 615 */ return (new ItemFilter.Equipable(ui)).filter(i);
    }

    public static boolean isEditable(Item i) {
        /* 619 */ return (new ItemFilter.Editable()).filter(i);
    }

    public static Item[] addEmptyItem(Item[] list, int kind, boolean slot) {
        /* 623 */ Item[] t = new Item[list.length + 1];
        /* 624 */ t[0] = getEmptyAvator(kind, slot);
        /* 625 */ System.arraycopy(list, 0, t, 1, list.length);
        /* 626 */ return t;
    }

    public Item[] getEquipableList(UserInfo ui, int kind, boolean _add) {
        /* 633 */ Item[] list = getList(new ItemFilter.And(new ItemFilter[] {
                /* 634 */ new ItemFilter.Kind(kind),
                /* 635 */ new ItemFilter.Equipable(ui, this)
        }));
        /* 637 */ if (_add && !isRequiredAvatar(kind)) {
            /* 638 */ list = addEmptyItem(list, kind, false);
        }
        /* 640 */ return list;
    }

    public Item[] getEquipableListEditable(UserInfo ui, int kind, boolean _add) {
        /* 644 */ Item[] list = getList(new ItemFilter.And(new ItemFilter[] {
                /* 645 */ new ItemFilter.Kind(kind),
                /* 646 */ new ItemFilter.Equipable(ui, this),
                /* 647 */ new ItemFilter.Editable()
        }));
        /* 649 */ if (_add && !isRequiredAvatar(kind)) {
            /* 650 */ list = addEmptyItem(list, kind, false);
        }
        /* 652 */ return list;
    }

    public Item[] getSlotEquipList(UserInfo ui, int kind, boolean _add) {
        /* 656 */ Item[] list = getList(new ItemFilter.And(new ItemFilter[] {
                /* 657 */ new ItemFilter.Kind(kind),
                /* 658 */ new ItemFilter.SlotEquipable(ui.style)
        }));
        /* 660 */ if (_add) {
            /* 661 */ list = addEmptyItem(list, kind, true);
        }
        /* 663 */ return list;
    }

    public Item[] getItemList(int kind, boolean _add) {
        /* 670 */ Item[] list = getList(kind);
        /* 671 */ if (_add && !isRequiredAvatar(kind)) {
            /* 672 */ list = addEmptyItem(list, kind, false);
        }
        /* 674 */ return list;
    }

    public Item[] getItemListEditable(int kind, boolean _add) {
        /* 678 */ Item[] list = getListEditable(kind);
        /* 679 */ if (_add && !isRequiredAvatar(kind)) {
            /* 680 */ list = addEmptyItem(list, kind, false);
        }
        /* 682 */ return list;
    }

    public boolean isValidSkill(int accs, int skill0, int skill1, int skill2, int skill3) {
        /* 689 */ Item i = getItem(5, accs);
        /* 690 */ if (i == null || GameSetting.getSetting().isPrivately(5, accs))
            return false;
        /* 691 */ if (!i.isValid(skill1, skill2, skill3))
            return false;
        /* 692 */ return (skill0 == i.getPotentialSkill());
    }

    public Item[] getExpiredItemList(int kind) {
        /* 699 */ return getList(new ItemFilter.And(new ItemFilter[] {
                /* 700 */ new ItemFilter.Kind(kind),
                /* 701 */ new ItemFilter.Expired(getItemAdditionalData())
        }));
    }

    public Item[] getInformExpiredItemList() {
        /* 709 */ int nowTime = (int) (ServerTime.getTime() / 60000L);
        /* 710 */ return getList(new ItemFilter.InformExpired(getItemAdditionalData(), nowTime));
    }

    public int getInformExpiredItem_nexttime() {
        /* 714 */ return (getItemAdditionalData() != null)
                ? (getItemAdditionalData().getNexttimeRequireInformExpired() * 60000)
                : 21600000;
    }

    public boolean swapPlacement(Item a, Item b) {
        /* 718 */ int ap = -1, bp = -1;
        /* 719 */ for (int i = this.items.size() - 1; i >= 0; i--) {
            /* 720 */ Item it = this.items.get(i);
            /* 721 */ if (it.kind == a.kind && it.id == a.id)
                ap = i;
            /* 722 */ if (it.kind == b.kind && it.id == b.id)
                bp = i;
            /* 723 */ if (ap > -1 && bp > -1)
                break;
            /* 725 */ }
        if (ap < 0 || bp < 0)
            return false;
        /* 726 */ if (ap == bp)
            return true;
        /* 727 */ Item mem = this.items.remove(ap);
        /* 728 */ this.items.add(bp, mem);
        /* 729 */ return true;
    }

    public boolean swapPlacement(int kind, int id, int place) {
        /* 733 */ if (place < 0)
            return false;
        /* 734 */ int old = itemIndexOf(kind, id);
        /* 735 */ if (old < 0)
            return false;
        /* 736 */ if (old == place)
            return true;
        /* 737 */ Item mem = this.items.remove(old);
        /* 738 */ this.items.add(place, mem);
        /* 739 */ return true;
    }

    public boolean hasItem(int kind, int id) {
        /* 744 */ return (itemIndexOf(kind, id) >= 0);
    }

    public int hasItems(int kind, int id) {
        /* 748 */ for (int l = 0; l < this.items.size(); l++) {
            /* 749 */ Item i = this.items.get(l);
            /* 750 */ if (i.kind == kind && i.id == id)
                return i.num;
        }
        /* 752 */ return -1;
    }

    public int hasItems(Item item) {
        /* 756 */ return hasItems(item.kind, item.id);
    }

    public boolean hasItem(int[] i) {
        /* 760 */ Item item = getItem(i[0], i[1]);
        /* 761 */ if (item == null || (item.hasNumber() && item.num == 0))
            return false;
        /* 762 */ return true;
    }

    public boolean hasItem(int kind, int[] id) {
        /* 767 */ HashMap<Object, Object> num = new HashMap<Object, Object>();
        /* 768 */ for (int i = 0; i < id.length; i++) {
            /* 769 */ Object o = num.get(Integer.valueOf(id[i]));
            /* 770 */ int n = (o == null) ? 0 : ((Integer) o).intValue();
            /* 771 */ n++;
            /* 772 */ num.put(Integer.valueOf(id[i]), Integer.valueOf(n));
        }
        /* 774 */ Iterator<Map.Entry<Object, Object>> ite = num.entrySet().iterator();
        /* 775 */ while (ite.hasNext()) {
            /* 776 */ Map.Entry<Object, Object> e = ite.next();
            /* 777 */ int n = ((Integer) e.getValue()).intValue();
            /* 778 */ if (hasNumber(kind)) {
                /* 779 */ int _id = ((Integer) e.getKey()).intValue();
                /* 780 */ int has = hasItems(kind, _id);
                /* 781 */ if (has < n)
                    return false;
                continue;
            }
            /* 783 */ if (n >= 2)
                return false;

        }
        /* 786 */ return true;
    }

    public boolean hasShopCoupon() {
        /* 791 */ for (int id = 91; id <= 99; id++) {
            /* 792 */ if (hasItem(new int[] { 15, id }))
                return true;
        }
        /* 794 */ return false;
    }

    public boolean hasShopCoupon(int id) {
        /* 797 */ if (hasItem(new int[] { 15, id }))
            return true;
        /* 798 */ return false;
    }

    public boolean isGetable(Item i) {
        /* 802 */ if (i == null) {
            /* 803 */ return false;
        }
        /* 805 */ return isGetable(i.kind, i.id, i.num);
    }

    public boolean isGetable(int kind, int id, int num) {
        /* 809 */ int idx = itemIndexOf(kind, id);
        /* 810 */ if (idx < 0) {
            /* 811 */ if (hasNumber(kind)) {
                /* 812 */ return (num <= getMaxItemNum(kind, id));
            }
            /* 814 */ return true;
        }

        /* 817 */ Item i = this.items.get(idx);
        /* 818 */ if (i.hasNumber()) {
            /* 819 */ return (i.num + num <= getMaxItemNum(kind, id));
        }

        /* 822 */ if (kind == 17 && RankData.Rank.isExpire(id))
            return true;

        /* 824 */ ItemAdditionalData iaData = getItemAdditionalData();
        /* 825 */ if (iaData != null && iaData.hasExpireTime(i)) {
            /* 826 */ return true;
        }
        /* 828 */ return false;
    }

    public boolean isMailGetable(Item i) {
        /* 835 */ if (i == null) {
            /* 836 */ return false;
        }
        /* 838 */ return isMailGetable(i.kind, i.id);
    }

    private boolean isMailGetable(int kind, int id) {
        /* 842 */ int idx = itemIndexOf(kind, id);
        /* 843 */ if (idx < 0) {
            /* 844 */ return true;
        }
        /* 846 */ Item i = this.items.get(idx);
        /* 847 */ if (i.hasNumber()) {
            /* 848 */ int n = getMaxItemNum(kind, id) - i.num;
            /* 849 */ return (n > 0);
        }

        /* 852 */ if (kind == 17 && RankData.Rank.isExpire(id))
            return true;

        /* 854 */ ItemAdditionalData iaData = getItemAdditionalData();
        /* 855 */ if (iaData != null && iaData.hasExpireTime(i)) {
            /* 856 */ return true;
        }
        /* 858 */ return false;
    }

    public static int getMaxItemNum(int kind, int id) {
        /* 868 */ int maxnum = 0;
        /* 869 */ if (kind == 22 || kind == 23) {
            /* 870 */ maxnum = 999;
        }
        /* 872 */ else if (!hasNumber(kind)) {
            /* 873 */ maxnum = 1;
        }
        /* 875 */ else if (kind == 15 && id == 23) {
            /* 876 */ maxnum = 10;
            /* 877 */ } else if (kind == 15 && id == 102) {
            /* 878 */ maxnum = 1;
        } else {
            /* 880 */ maxnum = 999;
        }

        /* 883 */ return maxnum;
    }

    public int getMargin(int kind, int id) {
        /* 890 */ int margin = getMaxItemNum(kind, id);
        /* 891 */ Item item = getItem(kind, id);
        /* 892 */ if (item != null) {
            /* 893 */ margin -= item.num;
        }
        /* 895 */ return margin;
    }

    public int itemIndexOf(int kind, int id) {
        /* 899 */ for (int l = 0; l < this.items.size(); l++) {
            /* 900 */ Item i = this.items.get(l);
            /* 901 */ if (i.kind == kind && i.id == id)
                return l;
        }
        /* 903 */ return -1;
    }

    public Item[][] getNonexistentItems() {
        /* 910 */ boolean debug = ((GameSetting.getSetting()).version < 0);
        /* 911 */ if (!debug) {
            /* 912 */ Item[] list = getList(ItemFilter.Privately.get());
            /* 913 */ Item[] slist = getList(new SkillChecker());
            /* 914 */ return new Item[][] { list, slist };
        }
        /* 916 */ return ii0;
    }

    /* 918 */ private static final Item[] i0 = new Item[0];
    /* 919 */ private static final Item[][] ii0 = new Item[][] { i0, i0 };

    private class SkillChecker
            implements ItemFilter {
        /* 923 */ ItemFilter p = ItemFilter.Privately.get();

        public boolean filter(UserItems.Item i) {
            /* 926 */ return (i.kind == 5 && !this.p.filter(i) && check(i));
        }

        private boolean check(UserItems.Item i) {
            /* 930 */ boolean ret = false;
            /* 931 */ GameSetting set = GameSetting.getSetting();
            /* 932 */ for (int l = 0; l < 3; l++) {
                /* 933 */ int sid = i.getTuneUp(l);
                /* 934 */ if (sid > 0 && set.isPrivately(6, sid)) {
                    /* 935 */ ret = true;

                    break;
                }
            }
            /* 940 */ if (!ret && set.isLanguage("ja")) {

                /* 943 */ int sid = i.getTuneUp(2);
                /* 944 */ if (sid == 251 || sid == 256) {
                    /* 945 */ boolean reset = false;
                    /* 946 */ ItemAdditionalData iadata = UserItems.this.getItemAdditionalData();
                    /* 947 */ if (iadata == null) {
                        /* 948 */ reset = true;
                        /* 949 */ iadata = new ItemAdditionalData();
                        /* 950 */ UserItems.this.setItemAdditionalData(iadata);
                    } else {
                        /* 952 */ ItemAdditionalData.Info info = iadata.getSkillInfo_Skill(i.id, sid);
                        /* 953 */ if (info == null)
                            reset = true;
                    }
                    /* 955 */ if (reset) {
                        /* 957 */ iadata.updateSkillInfo(i);
                    }
                }
            }
            /* 961 */ return ret;
        }

        private SkillChecker() {
        }
    }

    public void mergeItem(Item i, boolean overwriteskill) throws AmpedException {
        /* 970 */ if (i == null)
            /* 971 */ return;
        int idx = itemIndexOf(i.kind, i.id);
        /* 972 */ if (idx < 0) {
            /* 973 */ Item _i = new Item();
            /* 974 */ _i.set(i);
            /* 975 */ this.items.add(_i);
        } else {
            /* 977 */ Item _i = this.items.get(idx);
            /* 978 */ if (i.hasNumber())
            /* 979 */ {
                if (!_i.isRotenTicket())
                    _i.num = (short) (_i.num + i.num);
            }
            /* 981 */ else if (overwriteskill &&
            /* 982 */ i.tuneup != 0L) {
                _i.tuneup = i.tuneup;
            }

        }
    }

    public void addItem(Item i) throws AmpedException {
        /* 992 */ addItem(i, false, true);
    }

    public void addItem(Item i, boolean addtop) throws AmpedException {
        /* 995 */ addItem(i, addtop, true);
    }

    public void addItem(Item i, boolean addtop, boolean addMoney) throws AmpedException {
        com.nr.Utils.Print(this.items.size());
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

            if (_i.kind == 9)
                com.nr.Utils.SaveSkins(this);

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

    public void AddSkin(Item i) throws AmpedException {
        int idx = itemIndexOf(i.kind, i.id);
        if (idx < 0) {
            Item _i = new Item(i);
            this.items.add(_i);
        }
        com.nr.Utils.Print(this.items.size());
    }

    public void addEditItem(Item i) {
        /* 1085 */ Item _i = new Item();
        /* 1086 */ _i.set(i);
        /* 1087 */ this.items.add(_i);
    }

    public long getRotenTicketLimit(int id) {
        /* 1093 */ Inttable t = (Inttable) getParam(9);
        /* 1094 */ if (t == null || t.get(id) == null)
            return 0L;
        /* 1095 */ return ((Number) t.get(id)).longValue();
    }

    public long getRotenTicketRemain(int id, long currenttime) {
        /* 1100 */ return Math.max(0L, getRotenTicketLimit(id) - currenttime);
    }

    private boolean addRotenTicket(int id, int day, long sent, boolean indefinite) {
        /* 1104 */ if (!indefinite) {
            /* 1105 */ long current = System.currentTimeMillis();
            /* 1106 */ long limit = sent + (day * 24 * 60 * 60) * 1000L;
            /* 1107 */ if (limit <= current) {
                /* 1108 */ return false;
            }

            /* 1111 */ long remaining = getRotenTicketRemain(id, System.currentTimeMillis());

            /* 1113 */ if (remaining > 0L) {
                /* 1114 */ remaining = Math.min(limit - current + remaining, MAX_ROTEN_TICKET_LIMIT);
                /* 1115 */ limit = current + remaining;
            }

            /* 1118 */ Inttable t = (Inttable) getParam(9);
            /* 1119 */ if (t == null) {
                /* 1120 */ t = new Inttable();
                /* 1121 */ putParam(9, t);
            }
            /* 1123 */ t.put(id, Long.valueOf(limit));
        }
        /* 1125 */ return (hasItems(15, id) <= 0);
    }

    public void addShopItem(ShopData.Item si, int num, boolean trying, int[] selectid, float couponrate)
            throws AmpedException {
        /* 1134 */ addShopItem(si, num, trying, selectid, false, couponrate);
    }

    public void addShopItem(ShopData.Item si, int num, boolean trying, int[] selectid, boolean bonusitem,
            float couponrate) throws AmpedException {
        byte gettype;
        int price;
        /* 1137 */ if (!bonusitem && !trying && si.priceCheck(num, this.gamemoney, this.realmoney, this.vippoint)) {
            /* 1138 */ throw new AmpedException(97);
        }

        /* 1141 */ long gettime = System.currentTimeMillis();

        /* 1144 */ if (trying) {
            /* 1145 */ gettype = 12;
            /* 1146 */ price = 0;
        } else {
            /* 1148 */ switch (si.getMoneyType()) {
                case 0:
                    /* 1149 */ gettype = 6;
                    break;
                /* 1150 */ case 1:
                    gettype = 7;
                    break;
                /* 1151 */ case 2:
                    gettype = 14;
                    break;
                /* 1152 */ default:
                    throw new IllegalStateException();
            }

            /* 1154 */ price = si.getPrice();
        }

        /* 1157 */ addShopItemProc(si, num, gettime, gettype, price, selectid, bonusitem);

        /* 1159 */ if (!trying && !bonusitem) {

            /* 1162 */ if ((GameSetting.getSetting()).shopcouponenabled) {
                /* 1163 */ switch (si.getMoneyType()) {
                    case 0:
                        /* 1164 */ addGameMoney(-si.getPrice(num, couponrate));
                        break;
                    /* 1165 */ case 1:
                        addRealMoney(-si.getPrice(num, couponrate));
                        break;
                    /* 1166 */ case 2:
                        addVIPPoint(-si.getPrice(num, couponrate));
                        break;
                }

            } else {
                /* 1169 */ switch (si.getMoneyType()) {
                    case 0:
                        /* 1170 */ addGameMoney(-si.getPrice(num));
                        break;
                    /* 1171 */ case 1:
                        addRealMoney(-si.getPrice(num));
                        break;
                    /* 1172 */ case 2:
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
        /* 1181 */ switch (si.kind) {
            case 10:
                /* 1183 */ throw new AmpedException(100);

            case 21:
                /* 1186 */ ip = FluidSetting.getSetting().getItemPack(si.id);
                /* 1187 */ len = ip.itemlist.length;
                /* 1188 */ if (len > 0) {
                    /* 1189 */ int divprice = price / len;
                    /* 1190 */ for (int n = 0; n < len; n++) {
                        /* 1191 */ ShopData.Item psi = ip.itemlist[n];
                        /* 1192 */ addShopItemProc(psi, psi.num, gettime, gettype, divprice, null, false);
                    }
                }
                return;

            case 26:
                /* 1198 */ aip = FluidSetting.getSetting().getAutoSetItemPack(si.id);
                /* 1199 */ len = selectid.length;
                /* 1200 */ if (len > 0) {
                    /* 1201 */ int divprice = price / len;
                    /* 1202 */ for (int n = 0; n < len; n++) {
                        ShopData.Item sitem;
                        /* 1204 */ if (bonusitem) {
                            sitem = aip.bonusitem[selectid[n]];
                        }
                        /* 1205 */ else {
                            sitem = aip.itemlist[selectid[n]];
                        }
                        /* 1206 */ addShopItemProc(sitem, sitem.num, gettime, gettype, divprice, null, false);
                    }
                }
                return;

            case 0:
                /* 1212 */ addShopItemToItems(si, num, gettime, gettype, price);

                /* 1215 */ addStyleDefItems(si.id);
                return;

            case 25:
                /* 1219 */ npc = GameSetting.getSetting().getNPCItem(si.id);
                /* 1220 */ if (npc == null)
                    throw new AmpedException(111);
                /* 1221 */ upd = (UserPracticeData) this.params.get(12);
                /* 1222 */ if (upd != null && upd.getNpcUsableTime(npc.npcid) == -1L)
                    throw new AmpedException(111);
                /* 1223 */ if (si.usableDays == 0) {
                    /* 1224 */ setPracticeNpcDay(npc.npcid, -1);
                } else {
                    /* 1226 */ PracticeData data = GameSetting.getSetting().getPractice();
                    /* 1227 */ long nowtime = ServerTime.getTime() / 1000L;
                    /* 1228 */ if (upd != null && upd.npcusabletime != null) {
                        /* 1229 */ long addtime = data.getNpcExtendTime(si.usableDays);
                        /* 1230 */ long maxtime = data.getNpcExtendMaxTime();
                        /* 1231 */ int idx = upd.getNpcidIndex(npc.npcid);
                        /* 1232 */ Object o = upd.npcusabletime.get(idx);
                        /* 1233 */ if (o != null) {
                            /* 1234 */ long npcusabletime = ((Long) o).longValue();
                            /* 1235 */ if (data.overNpcExtendMaxTime(idx, addtime, nowtime, maxtime, npcusabletime))
                                throw new AmpedException(111);
                        }
                    }
                    /* 1238 */ addPracticeNpcDay(npc.npcid, si.usableDays, nowtime);
                }
                return;
        }

        /* 1243 */ addShopItemToItems(si, num, gettime, gettype, price);
    }

    private void addShopItemToItems(ShopData.Item si, int num, long gettime, byte gettype, int price)
            throws AmpedException {
        /* 1249 */ TerminableItem i = new TerminableItem();
        /* 1250 */ i.kind = si.kind;
        /* 1251 */ i.id = si.id;
        /* 1252 */ i.num = (short) num;
        /* 1253 */ i.gettime = gettime;
        /* 1254 */ i.usableDays = si.usableDays;
        /* 1255 */ i.gettype = gettype;
        /* 1256 */ i.price = price;
        /* 1257 */ addItem(i);
    }

    public void addStyleDefItems(int style) throws AmpedException {
        /* 1264 */ StyleData sd = GameSetting.getSetting().getStyle(style);
        /* 1265 */ long t = System.currentTimeMillis();

        /* 1268 */ if (sd.defhead != 0) {
            /* 1269 */ Item i = new Item((byte) 1, sd.defhead);
            /* 1270 */ i.gettime = t;
            /* 1271 */ i.gettype = 13;
            /* 1272 */ if (isGetable(i))
                addItem(i);

        }
        /* 1275 */ if (sd.defface != 0) {
            /* 1276 */ Item i = new Item((byte) 2, sd.defface);
            /* 1277 */ i.gettime = t;
            /* 1278 */ i.gettype = 13;
            /* 1279 */ if (isGetable(i))
                addItem(i);

        }
        /* 1282 */ if (sd.defbody != 0) {
            /* 1283 */ Item i = new Item((byte) 3, sd.defbody);
            /* 1284 */ i.gettime = t;
            /* 1285 */ i.gettype = 13;
            /* 1286 */ if (isGetable(i))
                addItem(i);

        }
        /* 1289 */ if (sd.defdeco != 0) {
            /* 1290 */ Item i = new Item((byte) 4, sd.defdeco);
            /* 1291 */ i.gettime = t;
            /* 1292 */ i.gettype = 13;
            /* 1293 */ if (isGetable(i))
                addItem(i);
        }
    }

    public void removeItem() {
        /* 1298 */ this.items.clear();

        /* 1300 */ clearItemAdditionalData();
    }

    public boolean removeItem(Item i) {
        /* 1309 */ if (i.kind == 25) {
            /* 1310 */ NPCItem npc = GameSetting.getSetting().getNPCItem(i.id);
            /* 1311 */ if (npc != null)
                return removePracticeNpc(npc.npcid);
            /* 1312 */ return false;
        }
        /* 1314 */ int idx = itemIndexOf(i.kind, i.id);
        /* 1315 */ if (i.isMoney()) {
            /* 1316 */ if (i.kind == 22) {
                addGameMoney(-i.id * i.num);
            }
            /* 1317 */ else if (i.kind == 23) {
                addRealMoney(-i.id * i.num);
            }
            /* 1318 */ return true;
            /* 1319 */ }
        if (idx < 0) {
            /* 1320 */ return false;
        }
        /* 1322 */ Item _i = get(idx);

        /* 1324 */ if (i.hasNumber()) {
            /* 1325 */ if (_i.num < i.num) {
                /* 1326 */ return false;
            }

            /* 1331 */ _i.num = (short) (_i.num - i.num);
            /* 1332 */ return true;
        }
        /* 1334 */ this.items.remove(idx);
        /* 1335 */ removeItemAdditionalInfo(i.kind, i.id);
        /* 1336 */ if (i.kind == 17) {
            /* 1337 */ UserRank ur = getUserRank(false);
            /* 1338 */ if (ur != null)
                ur.remove(i.id);
        }
        /* 1340 */ return true;
    }

    public boolean removeItem(int kind, int id, int num) {
        /* 1347 */ int idx = itemIndexOf(kind, id);
        /* 1348 */ if (idx < 0) {
            /* 1349 */ return false;
        }
        /* 1351 */ Item _i = get(idx);

        /* 1353 */ if (_i.hasNumber()) {
            /* 1354 */ if (_i.num <= 0)
                return false;
            /* 1355 */ _i.num = (short) Math.max(_i.num - num, 0);
            /* 1356 */ return true;
        }
        /* 1358 */ return removeItemFromList(_i);
    }

    public boolean removeItemFromList(Item i) {
        /* 1367 */ int idx = itemIndexOf(i.kind, i.id);
        /* 1368 */ if (idx < 0)
            return false;

        /* 1370 */ this.items.remove(idx);
        /* 1371 */ removeItemAdditionalInfo(i.kind, i.id);
        /* 1372 */ if (i.kind == 17) {
            /* 1373 */ UserRank ur = getUserRank(false);
            /* 1374 */ if (ur != null)
                ur.remove(i.id);
        }
        /* 1376 */ return true;
    }

    public int size() {
        /* 1381 */ return this.items.size();
    }

    public Item get(int i) {
        /* 1385 */ return this.items.get(i);
    }

    public int getGameMoney() {
        /* 1393 */ return this.gamemoney;
    }

    public void setGameMoney(int i) {
        /* 1400 */ this.gamemoney = i;
    }

    public int addGameMoney(int i) {
        /* 1408 */ return this.gamemoney += i;
    }

    public int addRealMoney(int i) {
        /* 1412 */ return addRealMoney(i, false);
    }

    public int addRealMoney(int i, boolean toll) {
        /* 1415 */ this.realmoney += i;
        /* 1416 */ if (!toll)
            addFreeRealMoney(i);
        /* 1417 */ return this.realmoney;
    }

    public int addVIPPoint(int i) {
        /* 1421 */ return this.vippoint += i;
    }

    public int getRealMoney() {
        /* 1428 */ return this.realmoney;
    }

    public void setRealMoney(int i) {
        /* 1435 */ setRealMoney(i, false);
    }

    public void setRealMoney(int i, boolean toll) {
        /* 1438 */ this.realmoney = i;
        /* 1439 */ if (!toll)
            setFreeRealMoney(i);

    }

    public int getVIPPoint() {
        /* 1446 */ return this.vippoint;
    }

    public void setVIPPoint(int p) {
        /* 1450 */ this.vippoint = p;
    }

    public int getVIPLevel() {
        /* 1457 */ return this.viplv;
    }

    public void setVIPLevel(int p) {
        /* 1461 */ this.viplv = p;
    }

    public Object getParam(int key) {
        /* 1469 */ return this.params.get(key);
    }

    public void putParam(int key, Object o) {
        /* 1473 */ this.params.put(key, o);
    }

    public UserRank getUserRank(boolean create) {
        /* 1483 */ UserRank ur = (UserRank) this.params.get(5);
        /* 1484 */ if (ur == null && create) {
            /* 1485 */ ur = new UserRank();
            /* 1486 */ putParam(5, ur);
        }
        /* 1488 */ return ur;
    }

    public boolean addScoreRank(RankData.Rank r, long time) {
        /* 1492 */ if (r != null && !r.isPrivately() && r.isKind(0)) {
            /* 1493 */ Item i = getItem(17, r.idx);
            /* 1494 */ if (i == null) {
                /* 1495 */ i = new Item((byte) 17, r.idx, 0, 5, time, 0);
                try {
                    /* 1497 */ addItem(i, true);
                    /* 1498 */ } catch (Exception ex) {
                    /* 1499 */ return false;
                }
            } else {
                /* 1502 */ i.gettime = time;
            }
            /* 1504 */ return true;
        }
        /* 1506 */ return false;
    }

    public boolean addStreetfightRank(RankData.Rank r, long time) {
        /* 1510 */ if (r != null && !r.isPrivately() && r.getKind() == 4) {
            /* 1511 */ Item i = getItem(17, r.idx);
            /* 1512 */ if (i == null) {
                /* 1513 */ i = new Item((byte) 17, r.idx, 0, 11, time, 0);
                try {
                    /* 1515 */ addItem(i, true);
                    /* 1516 */ } catch (Exception ex) {
                    /* 1517 */ return false;
                }
            } else {
                /* 1520 */ i.gettime = time;
            }
            /* 1522 */ return true;
        }
        /* 1524 */ return false;
    }

    public RankData.Rank[] getChallengeableRank() {
        /* 1528 */ ArrayList<RankData.Rank> a = new ArrayList();
        /* 1529 */ RankData.Rank[] rd = (GameSetting.getSetting()).rank.rank;
        /* 1530 */ for (int l = 0; l < rd.length; l++) {
            /* 1531 */ if (isChallengeableRank(rd[l]))
                a.add(rd[l]);
        }
        /* 1533 */ return a.<RankData.Rank>toArray(new RankData.Rank[a.size()]);
    }

    public boolean isChallengeableRank(RankData.Rank r) {
        /* 1538 */ if (r == null || r.isPrivately() || !r.isKind(2))
            return false;
        /* 1539 */ if (hasItem(17, r.idx))
            return false;

        /* 1542 */ int i = r.idx;
        /* 1543 */ RankData.Rank[] rd = (GameSetting.getSetting()).rank.rank;
        /* 1544 */ if (i == 0 || rd[i - 1] == null || !RankData.isSameGroup(rd[i], rd[i - 1]))
            return true;

        /* 1547 */ Item p = getItem(17, i - 1);
        /* 1548 */ return (p != null && p.gettime > 0L);
    }

    public void addChallengeRank(RankData.Rank r, int progress) {
        /* 1552 */ if (r != null && !r.isPrivately() && r.isKind(2) && !hasItem(17, r.idx)) {
            try {
                /* 1554 */ addItem(new Item((byte) 17, r.idx, 0, 4, 0L, 0), true);

                /* 1556 */ UserRank rank = getUserRank(true);
                /* 1557 */ rank.addRankInfo(r.idx, (r.getGroup()).progtype, progress);
                /* 1558 */ } catch (Exception ex) {
                /* 1559 */ ex.printStackTrace();
            }
        }
    }

    public void completeChallengeRank(RankData.Rank r, long time) {
        /* 1565 */ if (r != null && !r.isPrivately() && r.isKind(2)) {
            /* 1566 */ UserRank rank = getUserRank(false);
            /* 1567 */ Item i = getItem(17, r.idx);
            /* 1568 */ if (rank != null && i != null) {
                /* 1569 */ UserRank.RankProgress p = rank.getRankInfo(r.idx);
                /* 1570 */ if (p != null && p.isComplete()) {
                    /* 1571 */ rank.remove(r.idx);
                    /* 1572 */ i.gettime = time;
                }
            }
        }
    }

    public void addSpecialRank(RankData.Rank r, long time) {
        /* 1579 */ if (r != null && !r.isPrivately() && r.isKind(1)) {
            /* 1580 */ Item i = getItem(17, r.idx);
            /* 1581 */ if (i == null) {
                /* 1582 */ i = new Item((byte) 17, r.idx, 0, 10, time, 0);
                /* 1583 */ try {
                    addItem(i, true);
                } catch (Exception exception) {
                }
            } else {
                /* 1585 */ i.gettime = time;
            }
        }
    }

    public void setSpecialRankTime(long t) {
        /* 1591 */ putParam(11, Long.valueOf(t));
    }

    public long getSpecialRankTime() {
        /* 1595 */ Long t = (Long) getParam(11);
        /* 1596 */ long time = (t != null) ? t.longValue() : 0L;
        /* 1597 */ if (time > 0L)
            time = (time - 1204297200000L) / 5000L * 5000L + 1204297200000L;
        /* 1598 */ return time;
    }

    public int[] getEnableSpecialRanks() {
        /* 1602 */ GameSetting set = GameSetting.getSetting();

        /* 1604 */ ArrayList<Item> a = new ArrayList();
        /* 1605 */ long time = getSpecialRankTime();

        /* 1607 */ for (Iterator<Item> itr = this.items.iterator(); itr.hasNext();) {
            /* 1608 */ Item t = itr.next();
            /* 1609 */ if (t.kind == 17 && time <= t.gettime) {
                /* 1610 */ RankData.Rank r = set.getRank(t.id);
                /* 1611 */ if (r != null && r.isKind(1))
                    a.add(t);

            }
        }
        /* 1615 */ int[] sr = (int[]) null;
        /* 1616 */ if (a.size() > 0) {
            /* 1617 */ sr = new int[a.size()];
            /* 1618 */ for (int l = 0; l < sr.length; l++) {
                /* 1619 */ sr[l] = ((Item) a.get(l)).id;
            }
        }
        /* 1622 */ return sr;
    }

    public void setItemAdditionalData(ItemAdditionalData iaData) {
        /* 1630 */ putParam(7, iaData);
    }

    public ItemAdditionalData getItemAdditionalData() {
        /* 1637 */ ItemAdditionalData iad = (ItemAdditionalData) getParam(7);

        /* 1643 */ if (iad == null) {
            /* 1644 */ iad = new ItemAdditionalData();
            /* 1645 */ setItemAdditionalData(iad);
        }
        /* 1647 */ return iad;
    }

    private void clearItemAdditionalData() {
        /* 1654 */ ItemAdditionalData data = getItemAdditionalData();
        /* 1655 */ if (data != null) {
            /* 1656 */ data.clear();
        }
    }

    private void removeItemAdditionalInfo(int kind, int id) {
        /* 1664 */ ItemAdditionalData data = getItemAdditionalData();
        /* 1665 */ if (data != null) {
            /* 1666 */ data.removeInfos(kind, id);
        }
    }

    private boolean isNowGuildbattleSeason(Item r) {
        /* 1671 */ ItemAdditionalData iaData = getItemAdditionalData();
        /* 1672 */ if (iaData != null) {
            /* 1673 */ return iaData.isNowGuildbattleSeason(r);
        }
        /* 1675 */ return false;
    }

    public ItemAdditionalData.Info getInfoGuildbattleSeason(Item r) {
        /* 1679 */ ItemAdditionalData iaData = getItemAdditionalData();
        /* 1680 */ if (iaData != null) {
            /* 1681 */ return iaData.getInfo(r.kind, r.id, 3, 0);
        }
        /* 1683 */ return null;
    }

    public boolean isExpired(Item i) {
        /* 1691 */ return (i != null && isExpired(i.kind, i.id));
    }

    public boolean isExpired(int kind, int id) {
        /* 1695 */ ItemAdditionalData d = getItemAdditionalData();
        /* 1696 */ return (d != null && d.isExpired(kind, id));
    }

    public boolean hasExpireTime(int kind, int id) {
        /* 1700 */ ItemAdditionalData d = getItemAdditionalData();
        /* 1701 */ return (d != null && d.hasExpireTime(kind, id));
    }

    /* 1706 */ static final int[] medalrate = new int[] { 100, 100, 100, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 5,
            5 };

    public boolean newMedalCheck(int getscore) {
        /* 1715 */ if (!(GameSetting.getSetting()).new_medal || (GameSetting.getSetting()).disablegetnewmedal)
            /* 1716 */ return false;
        /* 1717 */ if ((GameSetting.getSetting()).checkmedalinterval == 0)
            return false;

        /* 1719 */ Calendar c = ServerTime.getCalendarInstance();
        /* 1720 */ int day = c.get(6);
        /* 1721 */ int[] i = (int[]) this.params.get(8);
        /* 1722 */ if (i == null) {
            /* 1723 */ i = new int[] { 0, 0, day };
            /* 1724 */ this.params.put(8, i);
        }
        /* 1726 */ if (i[2] != day) {
            /* 1727 */ i[0] = 0;
            /* 1728 */ i[1] = 0;
            /* 1729 */ i[2] = day;
        }
        /* 1731 */ i[0] = i[0] + getscore;
        if (i[0] + getscore >= (GameSetting.getSetting()).checkmedalinterval) {

            /* 1733 */ if (i[0] % 100 < medalrate[i[1]]) {
                /* 1734 */ i[0] = 0;
                /* 1735 */ i[1] = i[1] + 1;
                /* 1736 */ return true;
            }
            /* 1738 */ i[0] = 0;
        }
        /* 1740 */ return false;
    }

    public void incNewMedal(int num) {
        /* 1747 */ newMedalCheck(0);
        /* 1748 */ int[] i = (int[]) this.params.get(8);
        /* 1749 */ i[1] = i[1] + num;
    }

    public ParamTalkFlags getParamValue() {
        /* 1756 */ return (ParamTalkFlags) getParam(10);
    }

    public void setParamValue(ParamTalkFlags p) {
        /* 1759 */ putParam(10, p);
    }

    public int getParamValue(int id) {
        /* 1762 */ ParamTalkFlags flags = (ParamTalkFlags) getParam(10);
        /* 1763 */ if (flags == null) {
            /* 1764 */ flags = new ParamTalkFlags();
            /* 1765 */ putParam(10, flags);
        }
        /* 1767 */ return flags.getValue(id);
    }

    public void setParamValue(int id, int v) {
        /* 1770 */ ParamTalkFlags flags = (ParamTalkFlags) getParam(10);
        /* 1771 */ if (flags == null)
            flags = new ParamTalkFlags();
        /* 1772 */ flags.setValue(id, v);
        /* 1773 */ putParam(10, flags);
    }

    public static Item[] convertTreasure(Item[] i) {
        /* 1781 */ if (i == null)
            return null;

        /* 1783 */ GameSetting set = GameSetting.getSetting();
        /* 1784 */ ArrayList<Item> a = new ArrayList();

        /* 1786 */ for (int l = 0; l < i.length; l++) {
            /* 1787 */ if (i[l] != null) {
                /* 1788 */ Item t = i[l].clone();
                /* 1789 */ if (set.new_medal && t.isMedal()) {
                    /* 1790 */ t.id = 42;
                    /* 1791 */ t.num = (short) (t.num * 5);
                }
                /* 1793 */ if (!set.disablegetnewmedal || !t.isLuckyCoin())
                    /* 1794 */ a.add(t);
            }
            /* 1796 */ }
        return (a.size() == 0) ? null : a.<Item>toArray(new Item[a.size()]);
    }

    public void addPracticeNpcDay(int[] enemy, int enemynum) {
        /* 1803 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 1804 */ if (upd == null)
            upd = new UserPracticeData();

        /* 1806 */ PracticeData data = GameSetting.getSetting().getPractice();
        /* 1807 */ for (int l = 0; l < enemynum; l++) {
            /* 1808 */ if (enemy[l] > 0 && !data.isExcludeNpc(enemy[l])) {
                /* 1809 */ upd.addNpcUsableTime(enemy[l], data.getNpcExtendTime(), System.currentTimeMillis() / 1000L,
                        data.getNpcExtendMaxTime());
            }
        }

        /* 1813 */ putParam(12, upd);
    }

    public void addPracticeNpcDay(ArrayList<Integer> enemy) {
        /* 1817 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 1818 */ if (upd == null)
            upd = new UserPracticeData();
        /* 1819 */ PracticeData data = GameSetting.getSetting().getPractice();
        /* 1820 */ for (int l = 0; l < enemy.size(); l++) {
            /* 1821 */ if (((Integer) enemy.get(l)).intValue() > 0
                    && !data.isExcludeNpc(((Integer) enemy.get(l)).intValue())) {
                /* 1822 */ upd.addNpcUsableTime(((Integer) enemy.get(l)).intValue(), data.getNpcExtendTime(),
                        System.currentTimeMillis() / 1000L, data.getNpcExtendMaxTime());
            }
        }
        /* 1825 */ putParam(12, upd);
    }

    public void addPracticeNpcDay(int[] enemy, int[] busteruid, int enemynum, int myuid) {
        /* 1829 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 1830 */ if (upd == null)
            upd = new UserPracticeData();

        /* 1832 */ PracticeData data = GameSetting.getSetting().getPractice();
        /* 1833 */ for (int l = 0; l < enemynum; l++) {
            /* 1834 */ if (busteruid[l] == myuid && enemy[l] > 0 && !data.isExcludeNpc(enemy[l])) {
                /* 1835 */ upd.addNpcUsableTime(enemy[l], data.getNpcExtendTime(), System.currentTimeMillis() / 1000L,
                        data.getNpcExtendMaxTime());
            }
        }

        /* 1839 */ putParam(12, upd);
    }

    public void addPracticeNpcDay(int npcid, int days, long nowtime) {
        /* 1843 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 1844 */ if (upd == null)
            upd = new UserPracticeData();
        /* 1845 */ PracticeData data = GameSetting.getSetting().getPractice();
        /* 1846 */ if (npcid > 0 && !data.isExcludeNpc(npcid)) {
            /* 1847 */ upd.addNpcUsableTime(npcid, data.getNpcExtendTime(days), nowtime, data.getNpcExtendMaxTime());
        }
        /* 1849 */ putParam(12, upd);
    }

    public void setPracticeNpcDay(int npcid, int days) {
        /* 1853 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 1854 */ if (upd == null)
            upd = new UserPracticeData();
        /* 1855 */ PracticeData data = GameSetting.getSetting().getPractice();
        /* 1856 */ if (npcid > 0 && !data.isExcludeNpc(npcid)) {
            /* 1857 */ upd.setNpcUsableTime(npcid, days);
        }
        /* 1859 */ putParam(12, upd);
    }

    public boolean removePracticeNpc(int npcid) {
        /* 1863 */ UserPracticeData upd = (UserPracticeData) this.params.get(12);
        /* 1864 */ if (upd != null && upd.removeNpc(npcid)) {
            /* 1865 */ putParam(12, upd);
            /* 1866 */ return true;
        }
        /* 1868 */ return false;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        /* 1876 */ out.writeShort(this.items.size() | 0x8000);
        /* 1877 */ for (int l = 0; l < this.items.size(); l++) {
            /* 1878 */ Item i = this.items.get(l);
            /* 1879 */ if (i instanceof TerminableItem) {
                /* 1880 */ i = new Item(i);
            }
            /* 1882 */ i.writeExternal(out);
        }
        /* 1884 */ out.writeInt(this.gamemoney);
        /* 1885 */ out.writeInt(this.realmoney);
        /* 1886 */ out.writeObject(this.params);
        /* 1887 */ StreamTool.writePackedInt(this.vippoint, out);
        /* 1888 */ out.writeByte(this.viplv);
        /* 1889 */ out.writeShort(0);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        /* 1896 */ int num = in.readShort() & 0xFFFF;
        /* 1897 */ boolean b = ((num & 0x8000) != 0);
        /* 1898 */ num &= 0x7FFF;

        /* 1900 */ this.items.clear();
        /* 1901 */ this.items.ensureCapacity(num);
        /* 1902 */ for (int l = 0; l < num; l++) {
            /* 1903 */ Item i = new Item();
            /* 1904 */ i.readExternal(in);
            /* 1905 */ this.items.add(i);
        }
        /* 1907 */ this.gamemoney = in.readInt();
        /* 1908 */ this.realmoney = in.readInt();
        /* 1909 */ if (b) {
            /* 1910 */ this.params = (Inttable) in.readObject();
            /* 1911 */ this.vippoint = StreamTool.readPackedInt(in);
            /* 1912 */ this.viplv = in.readByte();
            /* 1913 */ in.readShort();
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
            /* 1951 */ this.kind = _kind;
            /* 1952 */ this.id = _id;
            /* 1953 */ this.num = (short) _num;
            /* 1954 */ this.gettype = (byte) _type;
            /* 1955 */ this.gettime = _time;
            /* 1956 */ this.price = _price;
        }

        public Item(byte _kind, int _id) {
            /* 1960 */ this.kind = _kind;
            /* 1961 */ this.id = _id;
            /* 1962 */ this.num = 1;
            /* 1963 */ this.gettype = 15;
            /* 1964 */ this.gettime = System.currentTimeMillis();
            /* 1965 */ this.price = 0;
        }

        public Item(byte _kind, int _id, int _num) {
            /* 1969 */ this(_kind, _id);
            /* 1970 */ this.num = (short) _num;
        }

        public Item(Item item) {
            /* 1974 */ set(item);
        }

        public void set(Item i) {
            /* 1978 */ this.kind = i.kind;
            /* 1979 */ this.id = i.id;
            /* 1980 */ this.gettype = i.gettype;
            /* 1981 */ this.gettime = i.gettime;
            /* 1982 */ this.num = i.num;
            /* 1983 */ this.price = i.price;
            /* 1984 */ this.who = i.who;
            /* 1985 */ this.tuneup = i.tuneup;
            /* 1986 */ this.makername = i.makername;
        }

        public Item clone() {
            try {
                /* 1991 */ return (Item) super.clone();
                /* 1992 */ } catch (Exception e) {
                /* 1993 */ return null;
            }
        }

        public boolean equals(int kind_, int id_, int num_, long tuneup_) {
            /* 1998 */ if (this.kind != kind_)
                return false;
            /* 1999 */ if (this.id != id_)
                return false;
            /* 2000 */ if (this.num != num_)
                return false;
            /* 2001 */ if (this.tuneup != tuneup_)
                return false;
            /* 2002 */ return true;
        }

        public boolean equals(int kind_, int id_, int num_) {
            /* 2006 */ return equals(kind_, id_, num_, this.tuneup);
        }

        public boolean equals(Object o) {
            /* 2010 */ if (o == this)
                return true;
            /* 2011 */ if (!(o instanceof Item))
                return false;
            /* 2012 */ Item i = (Item) o;
            /* 2013 */ return equals(i.kind, i.id, i.num, i.tuneup);
        }

        public boolean contain(Object o) {
            /* 2017 */ if (o == this)
                return true;
            /* 2018 */ if (o instanceof Item)
                return equals(o);
            /* 2019 */ if (o instanceof Item[]) {
                /* 2020 */ Item[] item = (Item[]) o;
                /* 2021 */ for (int i = 0; i < item.length; i++) {
                    /* 2022 */ if (equals(item[i]))
                        return true;
                }
            }
            /* 2025 */ return false;
        }

        public AmpedItem getAmpedItem() {
            /* 2029 */ return GameSetting.getSetting().getAmpedItem(this.kind, this.id);
        }

        public void setPotential(int skill, int pow) {
            /* 2033 */ this.tuneup &= 0x3FFFFFFFFFFL;
            /* 2034 */ this.tuneup |= skill << 42L & 0xFFFC0000000000L;
            /* 2035 */ this.tuneup |= pow << 56L & 0xFF00000000000000L;
        }

        public int getPotentialSkill() {
            /* 2039 */ return (int) (this.tuneup >>> 42L & 0x3FFFL);
        }

        public int getPotentialSkillPow() {
            /* 2043 */ return (int) (this.tuneup >>> 56L & 0xFFL);
        }

        public void setTuneUp(int i, int skill) {
            /* 2047 */ if (i >= 0 && i < 3) {
                /* 2048 */ int shift = (2 - i) * 14;
                /* 2049 */ long mask = 16383L << shift;
                /* 2050 */ this.tuneup &= mask ^ 0xFFFFFFFFFFFFFFFFL;
                /* 2051 */ this.tuneup |= skill << shift & mask;
            }
        }

        public int getTuneUp(int i) {
            /* 2056 */ switch (i) {
                case 0:
                    /* 2057 */ return (int) (this.tuneup >>> 28L) & 0x3FFF;
                /* 2058 */ case 1:
                    return (int) (this.tuneup >>> 14L) & 0x3FFF;
                /* 2059 */ case 2:
                    return (int) this.tuneup & 0x3FFF;
            }
            /* 2060 */ return 0;
        }

        public boolean isValid(int skill1, int skill2, int skill3) {
            /* 2065 */ if (skill1 != getTuneUp(0))
                return false;
            /* 2066 */ if (skill2 != getTuneUp(1))
                return false;
            /* 2067 */ if (skill3 != getTuneUp(2))
                return false;
            /* 2068 */ return true;
        }

        public void setAllTuneUp(long tune) {
            /* 2074 */ this.tuneup = tune;
        }

        public long getAllTuneUp() {
            /* 2078 */ return this.tuneup;
        }

        public int getTuneLevel() {
            /* 2082 */ int lv = 0;
            /* 2083 */ GameSetting set = GameSetting.getSetting();
            /* 2084 */ for (int l = 0; l < 3; l++) {
                /* 2085 */ int id = getTuneUp(l);
                /* 2086 */ SkillData sk = set.getSkill(id);
                /* 2087 */ if (sk != null)
                    lv += sk.tunelevel;
            }
            /* 2089 */ return lv;
        }

        public String getName() {
            /* 2093 */ String name = null;
            /* 2094 */ if (this.kind == 5 && this.makername != null) {
                /* 2095 */ AccessoryData a = GameSetting.getSetting().getAccs(this.id);
                /* 2096 */ if (a.uniqname != null) {
                    /* 2097 */ name = String.format(a.uniqname, new Object[] { this.makername });
                }
            }
            /* 2100 */ if (name == null)
                name = getAmpedItem().getName();
            /* 2101 */ if ((GameSetting.getSetting()).version <= 0)
                name = String.valueOf(name) + ":" + this.id;
            /* 2102 */ return name;
        }

        public boolean isMedal() {
            /* 2106 */ return (this.kind == 15 && this.id == 1);
        }

        public boolean isLuckyCoin() {
            /* 2110 */ return (this.kind == 15 && this.id == 42);
        }

        public boolean isDungeonMaterial() {
            /* 2114 */ return (this.kind == 12 && this.id > 1500 && this.id <= 2000);
        }

        public boolean isAccessoryCore() {
            /* 2118 */ if (this.kind == 12) {
                /* 2119 */ MaterialData m = GameSetting.getSetting().getMaterial(this.id);
                /* 2120 */ return (m != null && m.alchemy != null);
            }
            /* 2122 */ return false;
        }

        public boolean isRotenTicket() {
            /* 2126 */ if (this.kind == 15) {
                /* 2127 */ EventItem ei = GameSetting.getSetting().getEventItem(this.id);
                /* 2128 */ return (ei != null && ei.arg instanceof EventItem.RotenTicketData);
            }
            /* 2130 */ return false;
        }

        public boolean isBoostTicket() {
            /* 2134 */ return !((this.kind != 15 || (this.id != 29 && this.id != 30 && this.id != 31))
                    && !isEventItemBoostTicket() && !isDropItemBoostTicket());
        }

        public boolean isEventItemBoostTicket() {
            /* 2138 */ return (this.kind == 15 && this.id == 36);
        }

        public boolean isDropItemBoostTicket() {
            /* 2142 */ return (this.kind == 15 && this.id >= 58 && this.id <= 66);
        }

        public boolean isMoney() {
            /* 2146 */ return !(this.kind != 22 && this.kind != 23);
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            /* 2153 */ out.writeByte(0x40 | this.kind & 0xFFFFFF3F);
            /* 2154 */ if (this.kind == 9 || isMoney()) {
                /* 2155 */ out.writeInt(this.id);
            } else {
                /* 2157 */ out.writeShort(this.id);
            }
            /* 2159 */ if (hasNumber() || isMoney())
                StreamTool.writePackedInt(this.num, out);

            /* 2161 */ out.writeByte(this.gettype | ((this.makername != null) ? 128 : 0));
            /* 2162 */ StreamTool.writePackedInt(Math.max(0, (int) ((this.gettime - 1204297200000L) / 5000L)), out);
            /* 2163 */ if (hasPrice())
                StreamTool.writePackedInt(this.price, out);
            /* 2164 */ if (hasPresenter())
                out.writeInt(this.who);
            /* 2165 */ if (this.kind == 5) {
                /* 2166 */ out.writeInt((int) (this.tuneup >>> 32L & 0xFFFFFFFFFFFFFFFFL));
                /* 2167 */ out.writeInt((int) (this.tuneup & 0xFFFFFFFFFFFFFFFFL));
            }
            /* 2169 */ if (this.makername != null)
                out.writeUTF(this.makername);

        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            /* 2176 */ byte b = in.readByte();

            /* 2179 */ this.kind = (byte) (b & 0xFFFFFF3F);
            /* 2180 */ int tuneupextend = b & 0xC0;
            /* 2181 */ if (this.kind == 9 || isMoney()) {
                /* 2182 */ this.id = in.readInt();
            } else {
                /* 2184 */ this.id = in.readShort() & 0xFFFF;
            }
            /* 2186 */ if (hasNumber() || isMoney())
                this.num = (short) StreamTool.readPackedInt(in);
            /* 2187 */ b = in.readByte();
            /* 2188 */ this.gettype = (byte) (b & Byte.MAX_VALUE);
            /* 2189 */ boolean hasmakername = ((b & 0x80) != 0);

            /* 2191 */ int t = StreamTool.readPackedInt(in);
            /* 2192 */ if (t == 0) {
                this.gettime = 0L;
            }
            /* 2193 */ else {
                this.gettime = t * 5000L + 1204297200000L;
            }

            /* 2196 */ if (hasPrice())
                this.price = StreamTool.readPackedInt(in);
            /* 2197 */ if (hasPresenter())
                this.who = in.readInt();
            /* 2198 */ if (this.kind == 5) {
                /* 2199 */ long _tuneup = in.readInt() & 0xFFFFFFFFL;
                /* 2200 */ if (tuneupextend == 0) {
                    /* 2201 */ this.tuneup = getExtendedTuneUp(_tuneup);
                    /* 2202 */ tuneupextend = 128;
                } else {
                    /* 2204 */ this.tuneup = _tuneup << 32L | in.readInt() & 0xFFFFFFFFL;
                }
                /* 2206 */ if (tuneupextend == 128) {
                    /* 2207 */ this.tuneup = getExtendedTuneUp2(this.tuneup);
                    /* 2208 */ tuneupextend = 64;
                }
            }
            /* 2211 */ if (hasmakername)
                this.makername = in.readUTF();

        }

        private long getExtendedTuneUp(long t) {
            /* 2216 */ long s0 = t >>> 20L & 0x3FFL;
            /* 2217 */ long s1 = t >>> 10L & 0x3FFL;
            /* 2218 */ long s2 = t & 0x3FFL;
            /* 2219 */ long et = s0 << 40L | s1 << 20L | s2;
            /* 2220 */ return et;
        }

        private long getExtendedTuneUp2(long t) {
            /* 2225 */ long s0 = t >>> 40L & 0xFFFFFL;
            /* 2226 */ long s1 = t >>> 20L & 0xFFFFFL;
            /* 2227 */ long s2 = t & 0xFFFFFL;
            /* 2228 */ long et = s0 << 28L | s1 << 14L | s2;
            /* 2229 */ return et;
        }

        public boolean hasPresenter() {
            /* 2234 */ return !(this.kind != 8 && this.kind != 9);
        }

        public boolean hasPrice() {
            /* 2238 */ return !(this.gettype != 6 && this.gettype != 7);
        }

        public boolean hasNumber() {
            /* 2242 */ return UserItems.hasNumber(this.kind);
        }

        public String toString() {
            /* 2247 */ return String.valueOf(super.toString()) + "kind:" + this.kind + ", id:" + this.id;
        }

        int checksum() {
            /* 2252 */ int i = 0;
            /* 2253 */ i += this.kind;
            /* 2254 */ i += this.id;
            /* 2255 */ i += this.num;
            /* 2256 */ return i;
        }
    }

    public static class TerminableItem
            extends Item {
        public int usableDays;

        public long startTime;

        public TerminableItem() {
        }

        public TerminableItem(byte _kind, int _id) {
            /* 2277 */ super(_kind, _id);
        }

        public TerminableItem(byte _kind, int _id, int _num, int days) {
            /* 2281 */ super(_kind, _id, _num);
            /* 2282 */ this.usableDays = days;
        }

        public boolean equals(Object o) {
            /* 2286 */ if (o == this)
                return true;
            /* 2287 */ if (!super.equals(o))
                return false;
            /* 2288 */ if (!(o instanceof TerminableItem))
                return false;
            /* 2289 */ TerminableItem i = (TerminableItem) o;
            /* 2290 */ if (this.usableDays != i.usableDays)
                return false;
            /* 2291 */ return true;
        }

        public void set(TerminableItem i) {
            /* 2295 */ set(i);
            /* 2296 */ this.usableDays = i.usableDays;
            /* 2297 */ this.startTime = i.startTime;
        }

        public boolean isTerminable() {
            /* 2301 */ return (this.usableDays > 0);
        }

        public String getName() {
            /* 2305 */ String name = super.getName();
            /* 2306 */ GameSetting gs = GameSetting.getSetting();
            /* 2307 */ if (name != null && isTerminable()) {
                /* 2308 */ if (this.kind == 17 && RankData.Rank.isGuildBattle(this.id)) {
                    /* 2309 */ name = String.valueOf(name) + gs.getLocalText("$useritem.info.guildbattlereign",
                            new Object[] { Integer.valueOf(this.usableDays) });
                } else {
                    /* 2311 */ name = String.valueOf(name) + gs.getLocalText("$useritem.usabledays",
                            new Object[] { Integer.valueOf(this.usableDays) });
                }
            }
            /* 2314 */ return name;
        }

        public TerminableItem clone() {
            /* 2318 */ return (TerminableItem) super.clone();
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            /* 2322 */ super.readExternal(in);
            /* 2323 */ this.usableDays = StreamTool.readPackedInt(in);
            /* 2324 */ this.startTime = StreamTool.readSignedPackedLong(in);
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            /* 2328 */ super.writeExternal(out);
            /* 2329 */ StreamTool.writePackedInt(this.usableDays, out);
            /* 2330 */ StreamTool.writeSignedPackedLong(this.startTime, out);
        }
    }

    public static class ParamTalkFlags implements Externalizable, Cloneable {
        int version;
        Inttable t;

        public ParamTalkFlags() {
            /* 2338 */ this.version = 0;
        }

        public boolean checkValid(UserTalkFlags flag) {
            /* 2343 */ return equals(flag);
        }

        public int getValue(int id) {
            /* 2347 */ return (this.t == null || this.t.get(id) == null) ? 0 : ((Number) this.t.get(id)).intValue();
        }

        public void setValue(int id, int v) {
            /* 2351 */ if (this.t == null)
                this.t = new Inttable();
            /* 2352 */ this.t.put(id, new Integer(v));
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            /* 2356 */ this.version = in.readInt();
            /* 2357 */ this.t = (Inttable) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            /* 2361 */ out.writeInt(this.version);
            /* 2362 */ out.writeObject(this.t);
        }
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\data\UserItems.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
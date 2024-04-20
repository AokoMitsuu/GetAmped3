/*     */
package ga2.data.net;

/*     */ import ga2.data.GuardianData;
/*     */ import ga2.data.RankData;
/*     */ import ga2.data.UserGuardianData;
/*     */ import ga2.data.UserInfo;
/*     */ import ga2.setting.GameSetting;

import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import kotori.io.Externalizer;
/*     */ import kotori.io.StreamTool;
/*     */ import kotori.util.Inttable;
/*     */ import kotori.util.KotoriUtil;

/*     */
/*     */ public class GameRoomInfo implements Externalizable, Cloneable {
    /* 16 */ private static final int[] i0 = new int[0];

    /* 19 */ public int[] members = i0;

    public int[] guilds;

    public Inttable teaminfo;

    public int grade;

    public int scorerate;

    public int state;

    public static final int STATE_MATCHING = 0;

    public static final int STATE_PLAYING1 = 1;

    public static final int STATE_PLAYING2 = 2;

    public static final int STATE_PLAYING3 = 3;

    public static final int STATE_GAMESET = 4;

    public static final int STATE_CLOSED = 5;

    public String addr;

    public int ssid;

    public int gametype;

    public static final int GAME_TOURNAMENT = 0;

    public static final int GAME_GUARDIAN = 1;

    public static final int GAME_STREETFIGHT = 2;

    public static final int GAME_LOUNGE = 3;

    public static final int GAME_ARCADE = 4;

    public static final int GAME_MYROOM = 5;

    public static final int GAME_GUILDROOM = 6;

    public static final int GAME_PARTYTRAINING = 7;

    public static final int GAME_DUEL = 8;

    public static final int GAME_GUARDIAN_NAVI = 9;

    public static final int GAME_TOURNAMENT_NAVI = 10;

    public static final int GAME_TEAMSTREETFIGHT_NAVI = 11;

    public int gameid;

    public static final int GAMEID_TORNAMENT = 0;

    public static final int GAMEID_TEAMTORNAMENT = 1;

    public static final int GAMEID_TEAMSTREETFIGHT = 2;

    public static final int GAMEID_TOURNAMENTTRAINING = 3;

    public static final int GAMEID_TEAMBATTLETRAINING = 4;

    public static final int GAMEID_GUILDBATTLE = 5;
    public static final int GAMEID_AUTOTOURNAMENT = 6;
    public static final int GAMEID_AUTOTEAMTOURNAMENT = 7;
    public static final int GAMEID_DOGFIGHT = 8;
    public static final int GAMEID_TEAMDOGFIGHT = 9;
    public int subid;
    /* 91 */ public String name = "";

    public int maxnum;
    public int roundCount;

    /* 97 */ public int flags = 1;

    public static final int FLAG_ACCEPTVIEWER = 1;

    public static final int FLAG_TEAMVOICECHAT = 2;

    public static final int FLAG_AOTUMATCHROOM = 4;

    public static final int FLAG_FIRSTAOTUMATCH = 8;

    public static final int FLAG_USEAOTUMATCH = 16;

    public int pwdhash;

    public long gametime;

    public int sortscore;

    public transient int emptytime;

    public Object clone() {
        try {
            /* 129 */ return super.clone();
            /* 130 */ } catch (Exception ex) {
            /* 131 */ return null;
        }
    }

    public void set(GameRoomInfo i) {
        /* 136 */ if (i.members != null)
            this.members = (int[]) i.members.clone();
        /* 137 */ this.grade = i.grade;
        /* 138 */ this.scorerate = i.scorerate;
        /* 139 */ this.state = i.state;
        /* 140 */ this.addr = i.addr;
        /* 141 */ this.ssid = i.ssid;
        /* 142 */ this.gametype = i.gametype;
        /* 143 */ this.gameid = i.gameid;
        /* 144 */ this.subid = i.subid;
        /* 145 */ this.name = i.name;
        /* 146 */ this.maxnum = i.maxnum;
        this.roundCount = i.roundCount;
        /* 147 */ this.flags = i.flags;
        /* 148 */ this.pwdhash = i.pwdhash;
        /* 149 */ this.gametime = i.gametime;
        /* 150 */ if (i.teaminfo != null) {
            try {
                /* 152 */ this.teaminfo = i.teaminfo;
            }
            /* 154 */ catch (Exception e) {
                /* 155 */ e.printStackTrace();
            }
        }
        /* 158 */ if (this.gameid == 5)
            this.guilds = i.guilds;
    }

    public boolean equals(Object o) {
        /* 162 */ if (this == o)
            return false;
        /* 163 */ if (!(o instanceof GameRoomInfo))
            return false;
        /* 164 */ GameRoomInfo i = (GameRoomInfo) o;
        /* 165 */ if (this.state == i.state && KotoriUtil.equals(this.addr, i.addr) && this.ssid == i.ssid &&
        /* 166 */ this.gameid == i.gameid && this.subid == i.subid && KotoriUtil.equals(this.name, i.name)
                && this.maxnum == i.maxnum && this.roundCount == i.roundCount &&
                /* 167 */ this.flags == i.flags && this.pwdhash == i.pwdhash && this.grade == i.grade
                && this.scorerate == i.scorerate)
            return true;

        return false;
    }

    public boolean isMember(int eid) {
        /* 174 */ for (int j = 0; j < this.members.length; j++) {
            /* 175 */ if (eid == this.members[j])
                return true;
        }
        /* 177 */ return false;
    }

    public boolean isMaster(int eid) {
        /* 181 */ if (this.members == null || this.members.length == 0)
            return false;
        /* 182 */ return (eid == this.members[0]);
    }

    public int getMembersNum() {
        /* 186 */ return this.members.length;
    }

    public String getTournamentLabelPath() {
        /* 190 */ return GameSetting.getSetting().getTournament(this.gameid).getLabelPath();
    }

    public String getStageBannerPath() {
        /* 194 */ if (this.gametype == 1 || this.gametype == 9)
            /* 195 */ return (GameSetting.getSetting().getGuardian()).episode[this.gameid].getBannerPath();
        /* 196 */ if (this.gametype == 8) {
            /* 197 */ return ((GameSetting.getSetting()).duel[this.gameid]).stage[this.subid].getBannerPath();
        }
        /* 199 */ return GameSetting.getSetting().getTournament(this.gameid).getStage(this.subid).getBannerPath();
    }

    public void changeInfo(GameRoomInfo i) {
        /* 204 */ this.gameid = i.gameid;
        /* 205 */ this.subid = i.subid;
        /* 206 */ this.name = i.name;
        /* 207 */ this.pwdhash = i.pwdhash;
        /* 208 */ this.maxnum = i.maxnum;
        this.roundCount = i.roundCount;
        /* 209 */ this.grade = i.grade;
        /* 210 */ this.scorerate = i.scorerate;
        /* 211 */ this.flags = i.flags;
    }

    public void addMember(int eid) {
        /* 215 */ if (memberIndexOf(eid) >= 0)
            /* 216 */ return;
        int[] n = new int[this.members.length + 1];
        /* 217 */ System.arraycopy(this.members, 0, n, 0, this.members.length);
        /* 218 */ n[this.members.length] = eid;
        /* 219 */ this.members = n;
    }

    public void removeMember(int eid) {
        /* 223 */ int i = memberIndexOf(eid);
        /* 224 */ if (i < 0)
            /* 225 */ return;
        int[] n = new int[this.members.length - 1];
        /* 226 */ System.arraycopy(this.members, 0, n, 0, i);
        /* 227 */ System.arraycopy(this.members, i + 1, n, i, this.members.length - i - 1);
        /* 228 */ this.members = n;
    }

    public void changeMaster(int eid) {
        /* 232 */ int i = memberIndexOf(eid);
        /* 233 */ if (i < 0)
            /* 234 */ return;
        int tmp = this.members[0];
        /* 235 */ this.members[0] = eid;
        /* 236 */ this.members[i] = tmp;
    }

    public int memberIndexOf(int eid) {
        /* 240 */ int num = this.members.length;
        /* 241 */ for (int l = 0; l < num; l++) {
            /* 242 */ if (this.members[l] == eid) {
                /* 243 */ return l;
            }
        }
        /* 246 */ return -1;
    }

    public boolean isFormulaGrade() {
        /* 250 */ return (this.grade == (GameSetting.getSetting().getFormulaRank()).idx);
    }

    public void setGrade(UserInfo ui) {
        /* 258 */ setGrade(ui, false, false);
    }

    public void setGrade(UserInfo ui, boolean formula) {
        /* 262 */ setGrade(ui, formula, false);
    }

    public void setGrade(UserInfo ui, boolean formula, boolean nosprank) {
        /* 266 */ RankData.Rank r = formula ? GameSetting.getSetting().getFormulaRank() : ui.getRank(nosprank);
        /* 267 */ this.grade = r.idx;

        /* 269 */ this.scorerate = (formula ? 1 : 0) | (nosprank ? 2 : 0);
    }

    public int getScoreRate(UserInfo ui, boolean formula, boolean nosprank) {
        /* 273 */ RankData.Rank r = formula ? GameSetting.getSetting().getFormulaRank() : ui.getRank(nosprank);
        /* 274 */ return r.getScoreRate(ui);
    }

    public boolean isDogfightRoom() {
        /* 278 */ return (this.gametype == 0 && this.gameid == 8);
    }

    public boolean isTeamDogfightRoom() {
        /* 282 */ return (this.gametype == 0 && this.gameid == 9);
    }

    public boolean isGuildBattleRoom() {
        /* 286 */ return (this.gametype == 0 && this.gameid == 5);
    }

    public boolean isTrainingRoom() {
        /* 293 */ return isTrainingRoom(this.gametype, this.gameid);
    }

    public static boolean isTrainingRoom(int gametype, int gameid) {
        /* 297 */ return (gametype == 0 && (gameid == 3 || gameid == 4));
    }

    public boolean isAutoMatchRoom() {
        /* 301 */ return ((this.flags & 0x4) != 0);
    }

    public boolean isFirstAutoMatchRoom() {
        /* 305 */ return ((this.flags & 0x8) != 0);
    }

    public boolean useAutoMatchRoom() {
        /* 309 */ return ((this.flags & 0x10) != 0);
    }

    public boolean isEmptyAutoMatchRoom() {
        /* 314 */ if (isAutoMatchRoom() && !useAutoMatchRoom()) {
            /* 315 */ return true;
        }
        /* 317 */ return false;
    }

    public boolean isFinalPhase(int phase) {
        /* 321 */ return (phase == ((isDogfightRoom() || isTeamDogfightRoom() || isGuildBattleRoom()) ? 1 : 2));
    }

    public static GameRoomInfo getTrainingGAT(UserInfo ui) {
        /* 325 */ GameRoomInfo ri = new GameRoomInfo();
        /* 326 */ ri.maxnum = 8;
        ri.roundCount = 3;
        /* 327 */ ri.gametype = 0;
        /* 328 */ ri.gameid = 3;
        /* 329 */ ri.subid = 0;
        /* 330 */ ri.name = GameSetting.getSetting().getLocalText("$menu.tourlist.training.name");
        /* 331 */ ri.pwdhash = 0;

        /* 333 */ ri.grade = (ui.getRank(true)).idx;
        /* 334 */ ri.scorerate = GameSetting.getSetting().getRank(0).getScoreRate(null);
        /* 335 */ ri.flags &= 0xFFFFFFFE;
        /* 336 */ return ri;
    }

    public static GameRoomInfo getTutorialGuardian(UserInfo ui, UserGuardianData ug) {
        /* 340 */ GameSetting set = GameSetting.getSetting();
        /* 341 */ int epid = 0, rank = 11;
        /* 342 */ for (int l = 0; l < ug.episodelist.length; l++) {
            /* 343 */ GuardianData.Episode ep = (set.getGuardian()).episode[ug.episodelist[l]];
            /* 344 */ if (ep != null) {
                /* 345 */ if (ep.rank == 1) {
                    /* 346 */ epid = ug.episodelist[l];
                    break;
                }
                /* 349 */ if (ep.rank != 0 && ep.rank < rank) {
                    /* 350 */ epid = ug.episodelist[l];
                    /* 351 */ rank = ep.rank;
                }
            }
        }

        /* 356 */ GameRoomInfo ri = new GameRoomInfo();
        /* 357 */ ri.maxnum = 4;
        ri.roundCount = 3;
        /* 358 */ ri.gametype = 1;
        /* 359 */ ri.gameid = epid;
        /* 360 */ ri.subid = 0;
        /* 361 */ ri.name = GameSetting.getSetting().getLocalText("$menu.tourmake.roomname", new Object[] { ui.name });
        /* 362 */ ri.pwdhash = 0;
        /* 363 */ ri.setGrade(ui);

        /* 365 */ return ri;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        /* 373 */ Externalizer ext = Externalizer.getDefault();

        /* 375 */ ext.writeObject(this.members, out);
        /* 376 */ out.writeByte(this.grade);
        /* 377 */ out.writeByte(this.state);
        /* 378 */ out.writeObject(this.addr);
        /* 379 */ out.writeInt(this.ssid);
        /* 380 */ out.writeByte(this.gametype);
        /* 381 */ out.writeShort(this.gameid);
        /* 382 */ out.writeByte(this.subid);
        /* 383 */ out.writeObject(this.name);
        /* 384 */ out.writeByte(this.maxnum);
        /* 385 */ out.writeShort(this.flags);
        /* 386 */ out.writeInt(this.pwdhash);
        /* 387 */ StreamTool.writeSignedPackedLong(this.gametime, out);
        /* 388 */ out.writeShort(this.scorerate);
        /* 389 */ out.writeObject(this.teaminfo);
        /* 390 */ if (this.gameid == 5)
            out.writeObject(this.guilds);

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        /* 397 */ Externalizer ext = Externalizer.getDefault();

        /* 399 */ this.members = (int[]) ext.readObject(in);
        /* 400 */ this.grade = in.readByte() & 0xFF;
        /* 401 */ this.state = in.readByte();
        /* 402 */ this.addr = (String) in.readObject();
        /* 403 */ this.ssid = in.readInt();
        /* 404 */ this.gametype = in.readByte();
        /* 405 */ this.gameid = in.readShort() & 0xFFFF;
        /* 406 */ this.subid = in.readByte();
        /* 407 */ this.name = (String) in.readObject();
        /* 408 */ this.maxnum = in.readByte();
        /* 409 */ this.flags = in.readShort() & 0xFFFF;
        /* 410 */ this.pwdhash = in.readInt();
        /* 411 */ this.gametime = StreamTool.readSignedPackedLong(in);
        /* 412 */ this.scorerate = in.readShort() & 0xFFFF;
        /* 413 */ this.teaminfo = (Inttable) in.readObject();
        /* 414 */ if (this.gameid == 5)
            this.guilds = (int[]) in.readObject();
    }

    public String toString() {
        /* 418 */ return "GameRoomInfo ssid:" + this.ssid + " num:"
                + ((this.members == null) ? "null" : String.valueOf(this.members.length)) + " / "
                + this.maxnum;
    }
}

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\data\net\GameRoomInfo.
 * class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
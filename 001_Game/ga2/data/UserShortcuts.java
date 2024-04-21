/*     */
package ga2.data;

/*     */
/*     */ import ga2.client.ui.PlayerChatPanel;
/*     */ import ga2.setting.GameSetting;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Arrays;
/*     */ import kotori.geom.Rect2D;
/*     */ import kotori.io.StreamTool;
/*     */ import kotori.kwt.TextFigure;
/*     */ import kotori.util.KotoriUtil;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class UserShortcuts
        /*     */ implements Externalizable, Cloneable
/*     */ {
    /*     */ public static final int equipbuttonnum = 9;
    /*     */ public static final int slotbuttonmax = 9;
    /*     */ public ChatShortcut[] chatsc;
    /*     */ public int selavator;
    /*     */ public AvatorShortcut[] avatorsc;
    /*     */ public int[] emotionsc;

    /*     */
    /*     */ public Object clone() {
        /* 35 */ UserShortcuts s = new UserShortcuts();
        /*     */
        /* 37 */ if (this.chatsc != null) {
            /* 38 */ s.chatsc = (ChatShortcut[]) this.chatsc.clone();
            /*     */ }
        /*     */
        /* 41 */ if (this.emotionsc != null) {
            /* 42 */ s.emotionsc = (int[]) this.emotionsc.clone();
            /*     */ }
        /*     */
        /* 45 */ if (this.avatorsc != null) {
            /* 46 */ s.avatorsc = new AvatorShortcut[this.avatorsc.length];
            /* 47 */ for (int l = 0; l < this.avatorsc.length; l++) {
                /* 48 */ s.avatorsc[l] = (this.avatorsc[l] == null) ? null : this.avatorsc[l].clone();
                /*     */ }
            /*     */ }
        /* 51 */ return s;
        /*     */ }

    /*     */
    /*     */ public boolean equals(Object o) {
        /* 55 */ if (this == o)
            return true;
        /* 56 */ if (!(o instanceof UserShortcuts))
            return false;
        /* 57 */ UserShortcuts s = (UserShortcuts) o;
        /* 58 */ if (this.selavator != s.selavator)
            return false;
        /* 59 */ if (!Arrays.equals(this.emotionsc, s.emotionsc))
            return false;
        /* 60 */ if (!Arrays.equals((Object[]) this.chatsc, (Object[]) s.chatsc))
            return false;
        /* 61 */ if (!Arrays.equals((Object[]) this.avatorsc, (Object[]) s.avatorsc))
            return false;
        /*     */
        /* 63 */ return true;
        /*     */ }

    /*     */
    /*     */ public void getDefaultChatShortcut(int len, UserItems item) {
        /* 67 */ this.chatsc = new ChatShortcut[len];
        /* 68 */ for (int i = 0; i < len; i++) {
            /* 69 */ this.chatsc[i] = GameSetting.getSetting().getChatShortcut(i);
            /* 70 */ if (this.chatsc[i] != null) {
                /*     */
                /*     */
                /* 73 */ if (!item.hasItem(this.chatsc[i].getEmoKind(), this.chatsc[i].getEmoID()))
                    this.chatsc[i].setEmotion(null);
                /*     */
                /* 75 */ PlayerChatPanel panel = new PlayerChatPanel();
                /* 76 */ if (panel.getMsg(i) != null) {
                    /* 77 */ TextFigure tf = panel.getMsg(i).getTextFigure();
                    /* 78 */ int line = tf.getFont().countLine((this.chatsc[i]).msg, 0, (this.chatsc[i]).msg.length(),
                            (tf.getRect(new Rect2D())).w, tf.getFontLayout(), true);
                    /* 79 */ while (line > 1) {
                        /* 80 */ (this.chatsc[i]).msg = (this.chatsc[i]).msg.substring(1);
                        /* 81 */ line = tf.getFont().countLine((this.chatsc[i]).msg, 0, (this.chatsc[i]).msg.length(),
                                (tf.getRect(new Rect2D())).w, tf.getFontLayout(), true);
                        /*     */ }
                    /*     */ }
                /*     */ }
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void checkShortcuts(UserItems i) {
        /* 94 */ if (this.avatorsc != null) {
            /* 95 */ for (int l = 0; l < this.avatorsc.length; l++)
                ;
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public int getAvatorShortcutSize() {
        /* 102 */ return (this.avatorsc == null) ? 0 : this.avatorsc.length;
        /*     */ }

    /*     */
    /*     */ public AvatorShortcut getAvatorShortcut(int i) {
        /* 106 */ if (this.avatorsc == null || i < 0 || i >= this.avatorsc.length)
            return null;
        /* 107 */ return this.avatorsc[i];
        /*     */ }

    /*     */
    /*     */ public void setAvatorShortcut(int i, AvatorShortcut a) {
        /* 111 */ if (i < 0)
            /* 112 */ return;
        if (a == null) {
            /* 113 */ if (this.avatorsc == null)
                /* 114 */ return;
            if (i < this.avatorsc.length)
                this.avatorsc[i] = null;
            /*     */ } else {
            /* 116 */ if (this.avatorsc == null) {
                /* 117 */ this.avatorsc = new AvatorShortcut[i + 1];
                /*     */ }
            /* 119 */ else if (i >= this.avatorsc.length) {
                /* 120 */ AvatorShortcut[] newsc = new AvatorShortcut[i + 1];
                /* 121 */ System.arraycopy(this.avatorsc, 0, newsc, 0, this.avatorsc.length);
                /* 122 */ this.avatorsc = newsc;
                /*     */ }
            /* 124 */ this.avatorsc[i] = a;
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void writeExternal(ObjectOutput out) throws IOException {
        /* 134 */ out.writeObject(this.emotionsc);
        /* 135 */ if (this.chatsc == null) {
            /* 136 */ out.writeByte(0);
            /*     */ } else {
            /* 138 */ out.writeByte(this.chatsc.length);
            /* 139 */ for (int l = 0; l < this.chatsc.length; l++) {
                /* 140 */ if (this.chatsc[l] != null) {
                    /* 141 */ out.writeBoolean(true);
                    /* 142 */ this.chatsc[l].writeExternal(out);
                    /*     */ } else {
                    /* 144 */ out.writeBoolean(false);
                    /*     */ }
                /*     */ }
            /*     */ }
        /* 148 */ out.writeByte(this.selavator);
        /* 149 */ if (this.avatorsc == null) {
            /* 150 */ out.writeByte(0);
            /*     */ } else {
            /* 152 */ out.writeByte(this.avatorsc.length);
            /* 153 */ for (int l = 0; l < this.avatorsc.length; l++) {
                /* 154 */ if (this.avatorsc[l] != null) {
                    /* 155 */ out.writeBoolean(true);
                    /* 156 */ this.avatorsc[l].writeExternal(out);
                    /*     */ } else {
                    /* 158 */ out.writeBoolean(false);
                    /*     */ }
                /*     */ }
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        /* 168 */ this.emotionsc = (int[]) in.readObject();
        /* 169 */ int len = in.readByte() & 0xFF;
        /* 170 */ if (len > 0) {
            /* 171 */ this.chatsc = new ChatShortcut[len];
            /* 172 */ for (int l = 0; l < len; l++) {
                /* 173 */ if (in.readBoolean()) {
                    /* 174 */ ChatShortcut a = new ChatShortcut();
                    /* 175 */ a.readExternal(in);
                    /* 176 */ this.chatsc[l] = a;
                    /*     */ }
                /*     */ }
            /*     */ }
        /* 180 */ this.selavator = in.readByte();
        /* 181 */ len = in.readByte() & 0xFF;
        /* 182 */ if (len > 0) {
            /* 183 */ this.avatorsc = new AvatorShortcut[len];
            /* 184 */ for (int l = 0; l < len; l++) {
                /* 185 */ if (in.readBoolean()) {
                    /* 186 */ AvatorShortcut a = new AvatorShortcut();
                    /* 187 */ a.readExternal(in);
                    /* 188 */ this.avatorsc[l] = a;
                    /*     */ }
                /*     */ }
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static class ChatShortcut implements Externalizable, Cloneable {
        /*     */ public String msg;
        /*     */ public int emo;

        /*     */
        /*     */ public byte getEmoKind() {
            if (this.emo >= 0) {
                return 8;
            } else {
                return 18;
            }
            /*     */ }

        /*     */
        /*     */ public int getEmoID() {
            /* 203 */ return (this.emo >= 0) ? this.emo : -this.emo;
            /*     */ }

        /*     */
        /*     */ public void setEmotion(UserItems.Item i) {
            /* 207 */ if (i == null) {
                /* 208 */ this.emo = 0;
                /*     */ } else {
                /* 210 */ switch (i.kind) {
                    case 8:
                        /* 211 */ this.emo = i.id;
                        return;
                    /* 212 */ case 18:
                        this.emo = -i.id;
                        return;
                }
                /* 213 */ this.emo = 0;
                /*     */ }
            /*     */ }

        /*     */
        /*     */
        /*     */ public ChatShortcut clone() {
            /*     */ try {
                /* 220 */ return (ChatShortcut) super.clone();
                /* 221 */ } catch (Exception e) {
                /* 222 */ return null;
                /*     */ }
            /*     */ }

        /*     */
        /*     */ public boolean equals(Object o) {
            /* 227 */ if (o == this)
                return true;
            /* 228 */ if (!(o instanceof ChatShortcut))
                return false;
            /* 229 */ ChatShortcut c = (ChatShortcut) o;
            /* 230 */ return (this.emo == c.emo && KotoriUtil.equals(this.msg, c.msg));
            /*     */ }

        /*     */
        /*     */ public void writeExternal(ObjectOutput out) throws IOException {
            /* 234 */ out.writeUTF(this.msg);
            /* 235 */ out.writeShort(this.emo);
            /*     */ }

        /*     */
        /*     */ public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            /* 239 */ this.msg = in.readUTF();
            /* 240 */ this.emo = in.readShort();
            /*     */ }
        /*     */ }

    /*     */
    /*     */ public static class AvatorShortcut
            /*     */ implements Cloneable, Externalizable
    /*     */ {
        /*     */ public short style;
        /*     */ public short head;
        /*     */ public short face;
        /*     */ public short body;
        /*     */ public short deco;
        /*     */ public short accs;
        /*     */ public short weapon;
        /*     */ public int skin;
        /*     */ public short voice;
        /*     */ public short build;

        /*     */
        /*     */ public AvatorShortcut clone() {
            /*     */ try {
                /* 260 */ return (AvatorShortcut) super.clone();
                /* 261 */ } catch (CloneNotSupportedException ex) {
                /* 262 */ throw new UnsupportedOperationException(ex);
                /*     */ }
            /*     */ }

        /*     */
        /*     */ public boolean equals(Object o) {
            /* 267 */ if (o == this)
                return true;
            /* 268 */ if (!(o instanceof AvatorShortcut))
                return false;
            /* 269 */ AvatorShortcut a = (AvatorShortcut) o;
            /* 270 */ if (this.style == a.style && this.head == a.head && this.face == a.face && this.body == a.body
                    && this.deco == a.deco &&
                    /* 271 */ this.accs == a.accs && this.weapon == a.weapon && this.skin == a.skin
                    && this.voice == a.voice && this.build == a.build)
                return true;
            /*     */ return false;
            /*     */ }

        /*     */
        /*     */ public UserInfo apply(UserInfo ui) {
            /* 276 */ if (ui == null)
                throw new NullPointerException("ui must not be null.");
            /* 277 */ ui.style = this.style;
            /* 278 */ ui.head = this.head;
            /* 279 */ ui.face = this.face;
            /* 280 */ ui.body = this.body;
            /* 281 */ ui.deco = this.deco;
            /* 282 */ ui.accs = this.accs;
            /* 283 */ ui.weapon = this.weapon;
            /* 284 */ ui.skin = this.skin;
            /* 285 */ ui.voice = this.voice;
            /* 286 */ ui.build = this.build;
            /* 287 */ return ui;
            /*     */ }

        /*     */
        /*     */ public AvatorShortcut validated(UserItems items) {
            /* 291 */ UserInfo ui = apply(new UserInfo());
            /* 292 */ // ui.changeStyle(ui.style, items, false);
            /* 293 */ this.style = ui.style;
            /* 294 */ this.head = ui.head;
            /* 295 */ this.face = ui.face;
            /* 296 */ this.body = ui.body;
            /* 297 */ this.deco = ui.deco;
            /* 298 */ this.accs = ui.accs;
            /* 299 */ this.weapon = ui.weapon;
            /* 300 */ this.skin = ui.skin;
            /* 301 */ this.voice = (short) ui.voice;
            /* 302 */ this.build = ui.build;
            /* 303 */ return this;
            /*     */ }

        /*     */
        /*     */
        /*     */
        /*     */
        /*     */ public boolean checkLostItems(UserItems items) {
            /* 310 */ if (!items.hasItem(0, this.style) || (
            /* 311 */ this.skin == 0 && this.body != 0 && !items.hasItem(3, this.body)) || (
            /* 312 */ this.skin == 0 && this.face != 0 && !items.hasItem(2, this.face)) || (
            /* 313 */ this.skin == 0 && this.head != 0 && !items.hasItem(1, this.head)) || (
            /* 314 */ this.skin == 0 && this.deco != 0 && !items.hasItem(4, this.deco)) || (
            /* 315 */ this.skin != 0 && !items.hasItem(9, this.skin)) || (
            /* 316 */ this.accs != 0 && !items.hasItem(5, this.accs)) || (
            /* 317 */ this.weapon != 0 && !items.hasItem(7, this.weapon)) || ((
            /* 318 */ this.skin == 0 || !UserInfo.isEnabledSlot(this.style)) && this.build != 0))
            /*     */ {
                /* 320 */ return true;
                /*     */ }
            /* 322 */ return false;
            /*     */ }

        /*     */
        /*     */ public void writeExternal(ObjectOutput out) throws IOException {
            /* 326 */ out.writeShort(this.style | 0x8000);
            /* 327 */ out.writeShort(this.head);
            /* 328 */ out.writeShort(this.face);
            /* 329 */ out.writeShort(this.body);
            /* 330 */ out.writeShort(this.deco);
            /* 331 */ out.writeShort(this.accs);
            /* 332 */ out.writeShort(this.weapon);
            /* 333 */ StreamTool.writeSignedPackedInt(this.skin, out);
            /* 334 */ out.writeShort(this.voice);
            /* 335 */ out.writeShort(this.build);
            /* 336 */ out.writeShort(0);
            /*     */ }

        /*     */
        /*     */ public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            /* 340 */ int i = in.readShort() & 0xFFFF;
            /* 341 */ this.style = (short) (i & 0x7FFF);
            /* 342 */ this.head = in.readShort();
            /* 343 */ this.face = in.readShort();
            /* 344 */ this.body = in.readShort();
            /* 345 */ this.deco = in.readShort();
            /* 346 */ this.accs = in.readShort();
            /* 347 */ this.weapon = in.readShort();
            /* 348 */ this.skin = StreamTool.readSignedPackedInt(in);
            /* 349 */ if ((i & 0x8000) != 0) {
                /* 350 */ this.voice = in.readShort();
                /* 351 */ this.build = in.readShort();
                /* 352 */ in.readShort();
                /*     */ }
            /*     */ }
        /*     */ }
    /*     */ }

/*
 * Location:
 * C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\ga2\data\UserShortcuts.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */
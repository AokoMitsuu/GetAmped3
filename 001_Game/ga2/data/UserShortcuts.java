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
        UserShortcuts s = new UserShortcuts();
        /*     */
        if (this.chatsc != null) {
            s.chatsc = (ChatShortcut[]) this.chatsc.clone();
            /*     */ }
        /*     */
        if (this.emotionsc != null) {
            s.emotionsc = (int[]) this.emotionsc.clone();
            /*     */ }
        /*     */
        if (this.avatorsc != null) {
            s.avatorsc = new AvatorShortcut[this.avatorsc.length];
            for (int l = 0; l < this.avatorsc.length; l++) {
                s.avatorsc[l] = (this.avatorsc[l] == null) ? null : this.avatorsc[l].clone();
                /*     */ }
            /*     */ }
        return s;
        /*     */ }

    /*     */
    /*     */ public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserShortcuts))
            return false;
        UserShortcuts s = (UserShortcuts) o;
        if (this.selavator != s.selavator)
            return false;
        if (!Arrays.equals(this.emotionsc, s.emotionsc))
            return false;
        if (!Arrays.equals((Object[]) this.chatsc, (Object[]) s.chatsc))
            return false;
        if (!Arrays.equals((Object[]) this.avatorsc, (Object[]) s.avatorsc))
            return false;
        /*     */
        return true;
        /*     */ }

    /*     */
    /*     */ public void getDefaultChatShortcut(int len, UserItems item) {
        this.chatsc = new ChatShortcut[len];
        for (int i = 0; i < len; i++) {
            this.chatsc[i] = GameSetting.getSetting().getChatShortcut(i);
            if (this.chatsc[i] != null) {
                /*     */
                /*     */
                if (!item.hasItem(this.chatsc[i].getEmoKind(), this.chatsc[i].getEmoID()))
                    this.chatsc[i].setEmotion(null);
                /*     */
                PlayerChatPanel panel = new PlayerChatPanel();
                if (panel.getMsg(i) != null) {
                    TextFigure tf = panel.getMsg(i).getTextFigure();
                    int line = tf.getFont().countLine((this.chatsc[i]).msg, 0, (this.chatsc[i]).msg.length(),
                            (tf.getRect(new Rect2D())).w, tf.getFontLayout(), true);
                    while (line > 1) {
                        (this.chatsc[i]).msg = (this.chatsc[i]).msg.substring(1);
                        line = tf.getFont().countLine((this.chatsc[i]).msg, 0, (this.chatsc[i]).msg.length(),
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
        if (this.avatorsc != null) {
            for (int l = 0; l < this.avatorsc.length; l++)
                ;
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */ public int getAvatorShortcutSize() {
        return (this.avatorsc == null) ? 0 : this.avatorsc.length;
        /*     */ }

    /*     */
    /*     */ public AvatorShortcut getAvatorShortcut(int i) {
        if (this.avatorsc == null || i < 0 || i >= this.avatorsc.length)
            return null;
        return this.avatorsc[i];
        /*     */ }

    /*     */
    /*     */ public void setAvatorShortcut(int i, AvatorShortcut a) {
        if (i < 0)
            return;
        if (a == null) {
            if (this.avatorsc == null)
                return;
            if (i < this.avatorsc.length)
                this.avatorsc[i] = null;
            /*     */ } else {
            if (this.avatorsc == null) {
                this.avatorsc = new AvatorShortcut[i + 1];
                /*     */ } else if (i >= this.avatorsc.length) {
                AvatorShortcut[] newsc = new AvatorShortcut[i + 1];
                System.arraycopy(this.avatorsc, 0, newsc, 0, this.avatorsc.length);
                this.avatorsc = newsc;
                /*     */ }
            this.avatorsc[i] = a;
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.emotionsc);
        if (this.chatsc == null) {
            out.writeByte(0);
            /*     */ } else {
            out.writeByte(this.chatsc.length);
            for (int l = 0; l < this.chatsc.length; l++) {
                if (this.chatsc[l] != null) {
                    out.writeBoolean(true);
                    this.chatsc[l].writeExternal(out);
                    /*     */ } else {
                    out.writeBoolean(false);
                    /*     */ }
                /*     */ }
            /*     */ }
        out.writeByte(this.selavator);
        if (this.avatorsc == null) {
            out.writeByte(0);
            /*     */ } else {
            out.writeByte(this.avatorsc.length);
            for (int l = 0; l < this.avatorsc.length; l++) {
                if (this.avatorsc[l] != null) {
                    out.writeBoolean(true);
                    this.avatorsc[l].writeExternal(out);
                    /*     */ } else {
                    out.writeBoolean(false);
                    /*     */ }
                /*     */ }
            /*     */ }
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.emotionsc = (int[]) in.readObject();
        int len = in.readByte() & 0xFF;
        if (len > 0) {
            this.chatsc = new ChatShortcut[len];
            for (int l = 0; l < len; l++) {
                if (in.readBoolean()) {
                    ChatShortcut a = new ChatShortcut();
                    a.readExternal(in);
                    this.chatsc[l] = a;
                    /*     */ }
                /*     */ }
            /*     */ }
        this.selavator = in.readByte();
        len = in.readByte() & 0xFF;
        if (len > 0) {
            this.avatorsc = new AvatorShortcut[len];
            for (int l = 0; l < len; l++) {
                if (in.readBoolean()) {
                    AvatorShortcut a = new AvatorShortcut();
                    a.readExternal(in);
                    this.avatorsc[l] = a;
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
            return (this.emo >= 0) ? this.emo : -this.emo;
            /*     */ }

        /*     */
        /*     */ public void setEmotion(UserItems.Item i) {
            if (i == null) {
                this.emo = 0;
                /*     */ } else {
                switch (i.kind) {
                    case 8:
                        this.emo = i.id;
                        return;
                    case 18:
                        this.emo = -i.id;
                        return;
                }
                this.emo = 0;
                /*     */ }
            /*     */ }

        /*     */
        /*     */
        /*     */ public ChatShortcut clone() {
            /*     */ try {
                return (ChatShortcut) super.clone();
            } catch (Exception e) {
                return null;
                /*     */ }
            /*     */ }

        /*     */
        /*     */ public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof ChatShortcut))
                return false;
            ChatShortcut c = (ChatShortcut) o;
            return (this.emo == c.emo && KotoriUtil.equals(this.msg, c.msg));
            /*     */ }

        /*     */
        /*     */ public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(this.msg);
            out.writeShort(this.emo);
            /*     */ }

        /*     */
        /*     */ public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.msg = in.readUTF();
            this.emo = in.readShort();
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
                return (AvatorShortcut) super.clone();
            } catch (CloneNotSupportedException ex) {
                throw new UnsupportedOperationException(ex);
                /*     */ }
            /*     */ }

        /*     */
        /*     */ public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof AvatorShortcut))
                return false;
            AvatorShortcut a = (AvatorShortcut) o;
            if (this.style == a.style && this.head == a.head && this.face == a.face && this.body == a.body
                    && this.deco == a.deco &&
                    this.accs == a.accs && this.weapon == a.weapon && this.skin == a.skin
                    && this.voice == a.voice && this.build == a.build)
                return true;
            /*     */ return false;
            /*     */ }

        /*     */
        /*     */ public UserInfo apply(UserInfo ui) {
            if (ui == null)
                throw new NullPointerException("ui must not be null.");
            ui.style = this.style;
            ui.head = this.head;
            ui.face = this.face;
            ui.body = this.body;
            ui.deco = this.deco;
            ui.accs = this.accs;
            ui.weapon = this.weapon;
            ui.skin = this.skin;
            ui.voice = this.voice;
            ui.build = this.build;
            return ui;
            /*     */ }

        /*     */
        /*     */ public AvatorShortcut validated(UserItems items) {
            UserInfo ui = apply(new UserInfo());
            // ui.changeStyle(ui.style, items, false);
            this.style = ui.style;
            this.head = ui.head;
            this.face = ui.face;
            this.body = ui.body;
            this.deco = ui.deco;
            this.accs = ui.accs;
            this.weapon = ui.weapon;
            this.skin = ui.skin;
            this.voice = (short) ui.voice;
            this.build = ui.build;
            return this;
            /*     */ }

        /*     */
        /*     */
        /*     */
        /*     */
        /*     */ public boolean checkLostItems(UserItems items) {
            if (!items.hasItem(0, this.style) || (this.skin == 0 && this.body != 0 && !items.hasItem(3, this.body))
                    || (this.skin == 0 && this.face != 0 && !items.hasItem(2, this.face))
                    || (this.skin == 0 && this.head != 0 && !items.hasItem(1, this.head))
                    || (this.skin == 0 && this.deco != 0 && !items.hasItem(4, this.deco))
                    || (this.skin != 0 && !items.hasItem(9, this.skin))
                    || (this.accs != 0 && !items.hasItem(5, this.accs))
                    || (this.weapon != 0 && !items.hasItem(7, this.weapon))
                    || ((this.skin == 0 || !UserInfo.isEnabledSlot(this.style)) && this.build != 0))
            /*     */ {
                return true;
                /*     */ }
            return false;
            /*     */ }

        /*     */
        /*     */ public void writeExternal(ObjectOutput out) throws IOException {
            out.writeShort(this.style | 0x8000);
            out.writeShort(this.head);
            out.writeShort(this.face);
            out.writeShort(this.body);
            out.writeShort(this.deco);
            out.writeShort(this.accs);
            out.writeShort(this.weapon);
            StreamTool.writeSignedPackedInt(this.skin, out);
            out.writeShort(this.voice);
            out.writeShort(this.build);
            out.writeShort(0);
            /*     */ }

        /*     */
        /*     */ public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            int i = in.readShort() & 0xFFFF;
            this.style = (short) (i & 0x7FFF);
            this.head = in.readShort();
            this.face = in.readShort();
            this.body = in.readShort();
            this.deco = in.readShort();
            this.accs = in.readShort();
            this.weapon = in.readShort();
            this.skin = StreamTool.readSignedPackedInt(in);
            if ((i & 0x8000) != 0) {
                this.voice = in.readShort();
                this.build = in.readShort();
                in.readShort();
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
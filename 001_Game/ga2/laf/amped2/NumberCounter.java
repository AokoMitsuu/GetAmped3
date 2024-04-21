// Source code is decompiled from a .class file using FernFlower decompiler.
package ga2.laf.amped2;

import ga2.data.TournamentData;
import kotori.geom.KMath;
import kotori.kwt.Button;
import kotori.kwt.Container;
import kotori.kwt.KWTManager;
import kotori.kwt.TextFigure;
import kotori.kwt.event.TextEvent;
import kotori.kwt.event.TextListener;
import kotori.util.ServerTime;

public class NumberCounter implements TextListener {
    public Container cnt;
    public Button dec;
    public Button inc;
    TextFigure v;
    AmpedTextArea2 num;
    public int val;
    int maxnum = 99;
    int minnum = 0;
    long incTime;
    long decTime;
    int jumpCheck = 0;
    boolean odd = false;
    boolean updated = false;

    public NumberCounter() {
    }

    public void setContainer(Container c) {
        this.setContainer(c, -1);
    }

    public void setContainer(Container c, int max) {
        this.cnt = c;
        this.dec = (Button) Container.findNamedComponent("dec", c);
        this.inc = (Button) Container.findNamedComponent("inc", c);
        this.v = (TextFigure) Container.findNamedFigure("v", c);
        this.num = (AmpedTextArea2) Container.findNamedComponent("val", c);
        this.num.setNumberCounterFlag(true);
        if (this.num != null) {
            this.num.addTextListener(this);
        }

        if (max != -1) {
            this.maxnum = max;
        }

        this.dec.setRepeatState(true);
        this.inc.setRepeatState(true);
    }

    public void setRange(TournamentData td) {
        this.minnum = td.minplayers;
        this.maxnum = td.maxplayers;
        this.odd = td.teambattle;
        if (this.odd) {
            this.val = this.val + 1 & -2;
        }

        this.setValue(KMath.clamp(this.val, this.minnum, this.maxnum));
    }

    public void setRange(int min, int max) {
        this.minnum = min;
        this.maxnum = max;

        this.setValue(KMath.clamp(this.val, this.minnum, this.maxnum));
    }

    public void textValueChanged(TextEvent e) {
        Object src = e.getSource();
        if (src == this.num) {
            synchronized (KWTManager.PAINT_LOCK) {
                try {
                    int enter = this.val;
                    if (this.num.getText() != null && this.num.getText().length() > 0) {
                        enter = Integer.parseInt(this.num.getText());
                        if (this.odd && enter % 2 != 0) {
                            ++enter;
                        }

                        if (enter > this.maxnum) {
                            enter = this.maxnum;
                        } else if (enter < this.minnum) {
                            enter = this.minnum;
                        }
                    }

                    this.val = enter;
                    this.updated = true;
                    this.num.setText("" + this.val);
                } catch (Exception var5) {
                    if (var5 instanceof NumberFormatException) {
                        this.num.setText("" + this.val);
                    }
                }
            }
        }

    }

    public boolean getUpdated() {
        boolean req = this.updated;
        this.updated = false;
        return req;
    }

    public void incValue() {
        if (ServerTime.getTime() - this.incTime < 100L) {
            ++this.jumpCheck;
        } else {
            this.jumpCheck = 0;
        }

        if (this.odd) {
            this.val += 2;
        } else {
            this.val += this.jumpCheck > 50 ? 40
                    : (this.jumpCheck > 40 ? 20 : (this.jumpCheck > 30 ? 10 : (this.jumpCheck > 20 ? 5 : 1)));
        }

        if (this.val > this.maxnum) {
            this.val = this.maxnum;
        }

        this.incTime = ServerTime.getTime();
        this.v.setText(this.val < 10 ? "0" + this.val : "" + this.val);
        if (this.num != null) {
            String temp = this.num.getText();
            this.num.setText("" + this.val);
            if (temp.length() < this.num.getText().length()) {
                this.num.setCursorIndex(this.num.getCursorIndex() + 1);
            }
        }

    }

    public void decValue() {
        if (ServerTime.getTime() - this.decTime < 100L) {
            ++this.jumpCheck;
        } else {
            this.jumpCheck = 0;
        }

        if (this.odd) {
            this.val -= 2;
        } else {
            this.val -= this.jumpCheck > 50 ? 40
                    : (this.jumpCheck > 40 ? 20 : (this.jumpCheck > 30 ? 10 : (this.jumpCheck > 20 ? 5 : 1)));
        }

        if (this.val < this.minnum) {
            this.val = this.minnum;
        }

        this.decTime = ServerTime.getTime();
        this.v.setText(this.val < 10 ? "0" + this.val : "" + this.val);
        this.num.setText("" + this.val);
    }

    public void setValue(int _val) {
        this.val = _val;
        if (this.val < 0) {
            this.val = 0;
        } else if (this.val > this.maxnum) {
            this.val = this.maxnum;
        }

        this.v.setText(this.val < 10 ? "0" + this.val : "" + this.val);
        if (this.num != null) {
            this.num.setText("" + this.val);
        }

    }

    public void setMaxNum(int max) {
        this.maxnum = max;
        if (this.val > this.maxnum) {
            this.setValue(this.maxnum);
        }

        this.dec.setEnabled(this.maxnum != 0);
        this.inc.setEnabled(this.maxnum != 0);
    }

    public void setMinNum(int min) {
        this.minnum = min;
        if (this.val < this.minnum) {
            this.setValue(this.minnum);
        }

    }

    public AmpedTextArea2 getTextArea() {
        return this.num == null ? null : this.num;
    }
}

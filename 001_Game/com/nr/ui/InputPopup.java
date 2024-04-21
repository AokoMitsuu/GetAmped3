package com.nr.ui;

import ga2.client.LookAndFeel;
import ga2.laf.amped2.AmpedPopupWindow;
import kotori.kwt.Container;
import kotori.kwt.TextInput;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.Event;

public class InputPopup extends AmpedPopupWindow {
    TextInput input;
    Container ok;
    String result;

    public InputPopup(String value) {
        super(LookAndFeel.getLAF().getKWTStruct("kwtdocs/dlg/changenamepopup.kwt"));
        this.result = null;
        Container container = (Container) findNamedComponent("ok", this);
        this.ok = container;
        container.addActionListener(this);
        TextInput textInput = (TextInput) findNamedComponent("input", this);
        this.input = textInput;
        textInput.addTextListener(this);
        this.input.setMaxLen(1000);
        this.input.setText(value);
        Container container2 = (Container) findNamedComponent("price", this);
        container2.setVisible(false);
    }

    @Override
    protected boolean checkEnd(Event e) {
        return false;
    }

    public String getResult() {
        return this.result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == this.ok) {
            this.result = this.input.getText();
            end();
        }
    }
}
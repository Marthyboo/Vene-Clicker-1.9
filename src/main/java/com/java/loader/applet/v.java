package com.java.loader.applet;

import com.java.loader.c;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

final class v
implements ActionListener {
    private final JCheckBox a;

    v(d d2, JCheckBox jCheckBox) {
        this.a = jCheckBox;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        c.var_boolean_g = this.a.isSelected();
        com.java.loader.e.a();
    }
}

/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import com.java.loader.c;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

final class s
implements ActionListener {
    private final /* synthetic */ JCheckBox a;

    s(d d2, JCheckBox jCheckBox) {
        this.a = jCheckBox;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        c.var_boolean_b = this.a.isSelected();
        com.java.loader.e.a();
    }
}


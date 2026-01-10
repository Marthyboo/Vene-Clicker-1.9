/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import com.java.loader.c;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;

final class h
implements ActionListener {
    private final /* synthetic */ JFormattedTextField a;

    h(d d2, JFormattedTextField jFormattedTextField) {
        this.a = jFormattedTextField;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        c.var_int_f = Integer.valueOf(((String)this.a.getValue()).substring(1, 2));
        com.java.loader.e.a();
    }
}


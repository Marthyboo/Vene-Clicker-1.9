/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class l
implements ActionListener {
    private /* synthetic */ d a;

    l(d d2) {
        this.a = d2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        this.a.dispose();
        System.exit(0);
    }
}


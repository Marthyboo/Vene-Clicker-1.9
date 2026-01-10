/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

final class o
implements ActionListener {
    o(d d2) {
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        int n2 = JOptionPane.showConfirmDialog(null, "Are you sure you would like to destruct?", "Destruct", 0);
        if (n2 == 0) {
            System.exit(69);
        }
    }
}


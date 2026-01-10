/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import com.java.loader.applet.q;
import com.java.loader.c;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import org.jnativehook.GlobalScreen;

final class p
implements ActionListener {
    private final /* synthetic */ JToggleButton a;

    p(d d2, JToggleButton jToggleButton) {
        this.a = jToggleButton;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        if (!this.a.isSelected()) {
            this.a.setText(c.java_lang_String_a());
            return;
        }
        c.var_boolean_c = true;
        this.a.setText("...");
        GlobalScreen.addNativeKeyListener(new q(this, this.a));
    }
}


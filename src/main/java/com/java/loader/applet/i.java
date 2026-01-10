/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.a;
import com.java.loader.applet.d;
import com.java.loader.c;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class i
implements ChangeListener {
    private final /* synthetic */ JFormattedTextField var_javax_swing_JFormattedTextField_a;
    private final /* synthetic */ a var_com_java_loader_applet_a_a;
    private final /* synthetic */ JFormattedTextField b;

    i(d d2, JFormattedTextField jFormattedTextField, a a2, JFormattedTextField jFormattedTextField2) {
        this.var_javax_swing_JFormattedTextField_a = jFormattedTextField;
        this.var_com_java_loader_applet_a_a = a2;
        this.b = jFormattedTextField2;
    }

    @Override
    public final void stateChanged(ChangeEvent changeEvent) {
        c.var_int_a = this.var_com_java_loader_applet_a_a.getValue();
        this.var_javax_swing_JFormattedTextField_a.setValue(c.var_int_a);
        c.var_int_b = this.var_com_java_loader_applet_a_a.a();
        this.b.setValue(c.var_int_b);
        com.java.loader.e.a();
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.a;
import com.java.loader.applet.d;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;

final class j
implements ActionListener {
    private final /* synthetic */ JFormattedTextField var_javax_swing_JFormattedTextField_a;
    private final /* synthetic */ JFormattedTextField b;
    private final /* synthetic */ a var_com_java_loader_applet_a_a;

    j(d d2, JFormattedTextField jFormattedTextField, JFormattedTextField jFormattedTextField2, a a2) {
        this.var_javax_swing_JFormattedTextField_a = jFormattedTextField;
        this.b = jFormattedTextField2;
        this.var_com_java_loader_applet_a_a = a2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        int n2;
        int n3 = ((Number)this.var_javax_swing_JFormattedTextField_a.getValue()).intValue();
        if (n3 <= (n2 = ((Number)this.b.getValue()).intValue()) && n3 >= this.var_com_java_loader_applet_a_a.getMinimum() && n2 <= this.var_com_java_loader_applet_a_a.getMaximum()) {
            this.var_com_java_loader_applet_a_a.setValue(n3);
            this.var_com_java_loader_applet_a_a.a(n2);
        }
    }
}


package com.java.loader.applet;

import com.java.loader.applet.a;
import com.java.loader.applet.d;
import com.java.loader.c;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class t
implements ChangeListener {
    private final /* synthetic */ JFormattedTextField a;
    private final /* synthetic */ a b;
    private final /* synthetic */ JFormattedTextField c;

    t(d d2, JFormattedTextField jFormattedTextField, a a2, JFormattedTextField jFormattedTextField2) {
        this.a = jFormattedTextField;
        this.b = a2;
        this.c = jFormattedTextField2;
    }

    @Override
    public final void stateChanged(ChangeEvent changeEvent) {
        com.java.loader.c.var_int_c = this.b.getValue();
        this.a.setValue(com.java.loader.c.var_int_c);
        com.java.loader.c.var_int_d = this.b.a();
        this.c.setValue(com.java.loader.c.var_int_d);
        com.java.loader.e.a();
    }
}
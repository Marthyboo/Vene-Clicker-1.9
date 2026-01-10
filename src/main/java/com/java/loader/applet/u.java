package com.java.loader.applet;

import com.java.loader.applet.a;
import com.java.loader.applet.d;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;

final class u
implements ActionListener {
    private final /* synthetic */ JFormattedTextField a;
    private final /* synthetic */ JFormattedTextField b;
    private final /* synthetic */ a c;

    u(d d2, JFormattedTextField jFormattedTextField, JFormattedTextField jFormattedTextField2, a a2) {
        this.a = jFormattedTextField;
        this.b = jFormattedTextField2;
        this.c = a2;
    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent) {
        int n2;
        int n3 = ((Number)this.a.getValue()).intValue();
        if (n3 <= (n2 = ((Number)this.b.getValue()).intValue()) && n3 >= this.c.getMinimum() && n2 <= this.c.getMaximum()) {
            this.c.setValue(n3);
            this.c.a(n2);
        }
    }
}

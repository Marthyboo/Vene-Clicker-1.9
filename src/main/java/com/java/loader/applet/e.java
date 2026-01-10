/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

final class e
implements MouseMotionListener {
    private /* synthetic */ d a;

    e(d d2) {
        this.a = d2;
    }

    @Override
    public final void mouseMoved(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseDragged(MouseEvent mouseEvent) {
        int n2 = mouseEvent.getXOnScreen();
        int n3 = mouseEvent.getYOnScreen();
        this.a.setLocation(n2 - d.int_a(this.a), n3 - d.int_b(this.a));
    }
}


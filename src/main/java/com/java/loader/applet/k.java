/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class k
implements MouseListener {
    private /* synthetic */ d a;

    k(d d2) {
        this.a = d2;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        d.a(this.a, mouseEvent.getX());
        d.b(this.a, mouseEvent.getY());
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseExited(MouseEvent mouseEvent) {
    }
}


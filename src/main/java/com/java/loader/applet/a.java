/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.RangeSliderUI;
import javax.swing.JSlider;

public final class a
extends JSlider {
    public a() {
        a a2 = this;
        a2.setOrientation(0);
    }

    public a(int n2, int n3) {
        super(5, 25);
        a a2 = this;
        a2.setOrientation(0);
    }

    @Override
    public final void updateUI() {
        this.setUI(new RangeSliderUI(this));
        this.updateLabelUIs();
    }

    @Override
    public final int getValue() {
        return super.getValue();
    }

    @Override
    public final void setValue(int n2) {
        int n3 = this.getValue();
        if (n3 == n2) {
            return;
        }
        int n4 = this.getExtent();
        n2 = Math.min(Math.max(this.getMinimum(), n2), n3 + n4);
        n3 = n4 + n3 - n2;
        this.getModel().setRangeProperties(n2, n3, this.getMinimum(), this.getMaximum(), this.getValueIsAdjusting());
    }

    public final int a() {
        return this.getValue() + this.getExtent();
    }

    public final void a(int n2) {
        int n3 = this.getValue();
        n2 = Math.min(Math.max(0, n2 - n3), this.getMaximum() - n3);
        this.setExtent(n2);
    }
}


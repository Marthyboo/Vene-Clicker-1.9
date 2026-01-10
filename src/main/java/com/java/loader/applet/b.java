/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.RangeSliderUI;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class b
implements ChangeListener {
    private /* synthetic */ RangeSliderUI a;

    public b(RangeSliderUI rangeSliderUI) {
        this.a = rangeSliderUI;
    }

    @Override
    public final void stateChanged(ChangeEvent changeEvent) {
        if (!RangeSliderUI.boolean_a(this.a) && !RangeSliderUI.boolean_b(this.a)) {
            this.a.calculateThumbLocation();
            RangeSliderUI.javax_swing_JSlider_a(this.a).repaint();
        }
    }
}


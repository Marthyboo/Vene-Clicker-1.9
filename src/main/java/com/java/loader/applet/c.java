/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.RangeSliderUI;
import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicSliderUI;

public final class c
extends BasicSliderUI.TrackListener {
    private /* synthetic */ RangeSliderUI a;

    public c(RangeSliderUI rangeSliderUI) {
        rangeSliderUI.super();
        this.a = rangeSliderUI;
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
        if (!RangeSliderUI.javax_swing_JSlider_a(this.a).isEnabled()) {
            return;
        }
        this.currentMouseX = mouseEvent.getX();
        this.currentMouseY = mouseEvent.getY();
        if (RangeSliderUI.javax_swing_JSlider_a(this.a).isRequestFocusEnabled()) {
            RangeSliderUI.javax_swing_JSlider_a(this.a).requestFocus();
        }
        boolean bl = false;
        boolean bl2 = false;
        if (RangeSliderUI.boolean_c(this.a) || RangeSliderUI.javax_swing_JSlider_a(this.a).getMinimum() == RangeSliderUI.javax_swing_JSlider_a(this.a).getValue()) {
            if (RangeSliderUI.java_awt_Rectangle_a(this.a).contains(this.currentMouseX, this.currentMouseY)) {
                bl2 = true;
            } else if (RangeSliderUI.java_awt_Rectangle_b(this.a).contains(this.currentMouseX, this.currentMouseY)) {
                bl = true;
            }
        } else if (RangeSliderUI.java_awt_Rectangle_b(this.a).contains(this.currentMouseX, this.currentMouseY)) {
            bl = true;
        } else if (RangeSliderUI.java_awt_Rectangle_a(this.a).contains(this.currentMouseX, this.currentMouseY)) {
            bl2 = true;
        }
        if (bl) {
            switch (RangeSliderUI.javax_swing_JSlider_a(this.a).getOrientation()) {
                case 1: {
                    this.offset = this.currentMouseY - RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).y;
                    break;
                }
                case 0: {
                    this.offset = this.currentMouseX - RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).x;
                }
            }
            RangeSliderUI.a(this.a, false);
            RangeSliderUI.b(this.a, true);
            return;
        }
        RangeSliderUI.b(this.a, false);
        if (bl2) {
            switch (RangeSliderUI.javax_swing_JSlider_a(this.a).getOrientation()) {
                case 1: {
                    this.offset = this.currentMouseY - RangeSliderUI.java_awt_Rectangle_a((RangeSliderUI)this.a).y;
                    break;
                }
                case 0: {
                    this.offset = this.currentMouseX - RangeSliderUI.java_awt_Rectangle_a((RangeSliderUI)this.a).x;
                }
            }
            RangeSliderUI.a(this.a, true);
            RangeSliderUI.c(this.a, true);
            return;
        }
        RangeSliderUI.c(this.a, false);
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
        RangeSliderUI.b(this.a, false);
        RangeSliderUI.c(this.a, false);
        RangeSliderUI.javax_swing_JSlider_a(this.a).setValueIsAdjusting(false);
        super.mouseReleased(mouseEvent);
    }

    @Override
    public final void mouseDragged(MouseEvent mouseEvent) {
        if (!RangeSliderUI.javax_swing_JSlider_a(this.a).isEnabled()) {
            return;
        }
        this.currentMouseX = mouseEvent.getX();
        this.currentMouseY = mouseEvent.getY();
        if (RangeSliderUI.boolean_a(this.a)) {
            RangeSliderUI.javax_swing_JSlider_a(this.a).setValueIsAdjusting(true);
            switch (RangeSliderUI.javax_swing_JSlider_a(this.a).getOrientation()) {
                case 1: {
                    int n2 = RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).height / 2;
                    int n3 = this.currentMouseY - this.offset;
                    int n4 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).y;
                    int n5 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).y + (RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).height - 1);
                    int n6 = RangeSliderUI.a(this.a, RangeSliderUI.javax_swing_JSlider_a(this.a).getValue() + RangeSliderUI.javax_swing_JSlider_a(this.a).getExtent());
                    if (RangeSliderUI.d(this.a)) {
                        n5 = n6;
                    } else {
                        n4 = n6;
                    }
                    n3 = Math.max(n3, n4 - n2);
                    n3 = Math.min(n3, n5 - n2);
                    this.a.setThumbLocation(RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).x, n3);
                    n2 = n3 + n2;
                    RangeSliderUI.javax_swing_JSlider_a(this.a).setValue(this.a.valueForYPosition(n2));
                    break;
                }
                case 0: {
                    int n7 = RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).width / 2;
                    int n8 = this.currentMouseX - this.offset;
                    int n9 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).x;
                    int n10 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).x + (RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).width - 1);
                    int n11 = RangeSliderUI.b(this.a, RangeSliderUI.javax_swing_JSlider_a(this.a).getValue() + RangeSliderUI.javax_swing_JSlider_a(this.a).getExtent());
                    if (RangeSliderUI.d(this.a)) {
                        n9 = n11;
                    } else {
                        n10 = n11;
                    }
                    n8 = Math.max(n8, n9 - n7);
                    n8 = Math.min(n8, n10 - n7);
                    this.a.setThumbLocation(n8, RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).y);
                    n7 = n8 + n7;
                    RangeSliderUI.javax_swing_JSlider_a(this.a).setValue(this.a.valueForXPosition(n7));
                    break;
                }
                default: {
                    return;
                }
            }
        }
        if (RangeSliderUI.boolean_b(this.a)) {
            RangeSliderUI.javax_swing_JSlider_a(this.a).setValueIsAdjusting(true);
            switch (RangeSliderUI.javax_swing_JSlider_a(this.a).getOrientation()) {
                case 1: {
                    int n12 = RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).height / 2;
                    int n13 = this.currentMouseY - this.offset;
                    int n14 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).y;
                    int n15 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).y + (RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).height - 1);
                    int n16 = RangeSliderUI.a(this.a, RangeSliderUI.javax_swing_JSlider_a(this.a).getValue());
                    if (RangeSliderUI.d(this.a)) {
                        n14 = n16;
                    } else {
                        n15 = n16;
                    }
                    n13 = Math.max(n13, n14 - n12);
                    n13 = Math.min(n13, n15 - n12);
                    RangeSliderUI.a(this.a, RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).x, n13);
                    n12 = n13 + n12;
                    RangeSliderUI.javax_swing_JSlider_a(this.a).setExtent(this.a.valueForYPosition(n12) - RangeSliderUI.javax_swing_JSlider_a(this.a).getValue());
                    return;
                }
                case 0: {
                    int n17 = RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).width / 2;
                    int n18 = this.currentMouseX - this.offset;
                    int n19 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).x;
                    int n20 = RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).x + (RangeSliderUI.java_awt_Rectangle_c((RangeSliderUI)this.a).width - 1);
                    int n21 = RangeSliderUI.b(this.a, RangeSliderUI.javax_swing_JSlider_a(this.a).getValue());
                    if (RangeSliderUI.d(this.a)) {
                        n20 = n21;
                    } else {
                        n19 = n21;
                    }
                    n18 = Math.max(n18, n19 - n17);
                    n18 = Math.min(n18, n20 - n17);
                    RangeSliderUI.a(this.a, n18, RangeSliderUI.java_awt_Rectangle_b((RangeSliderUI)this.a).y);
                    n17 = n18 + n17;
                    RangeSliderUI.javax_swing_JSlider_a(this.a).setExtent(this.a.valueForXPosition(n17) - RangeSliderUI.javax_swing_JSlider_a(this.a).getValue());
                    return;
                }
            }
        }
    }

    @Override
    public final boolean shouldScroll(int n2) {
        return false;
    }
}
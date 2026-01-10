/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.a;
import com.java.loader.applet.b;
import com.java.loader.applet.c;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

class RangeSliderUI
extends BasicSliderUI {
    private Color var_java_awt_Color_a = new Color(42, 172, 255);
    private Rectangle var_java_awt_Rectangle_a;
    private boolean var_boolean_a;
    private transient boolean b;
    private transient boolean c;

    public RangeSliderUI(a a2) {
        super(a2);
    }

    @Override
    public void installUI(JComponent jComponent) {
        this.var_java_awt_Rectangle_a = new Rectangle();
        super.installUI(jComponent);
    }

    @Override
    protected BasicSliderUI.TrackListener createTrackListener(JSlider jSlider) {
        return new c(this);
    }

    @Override
    protected ChangeListener createChangeListener(JSlider jSlider) {
        return new b(this);
    }

    @Override
    protected void calculateThumbSize() {
        super.calculateThumbSize();
        this.var_java_awt_Rectangle_a.setSize(this.thumbRect.width, this.thumbRect.height);
    }

    @Override
    protected void calculateThumbLocation() {
        int n2;
        super.calculateThumbLocation();
        if (this.slider.getSnapToTicks()) {
            int n3 = n2 = this.slider.getValue() + this.slider.getExtent();
            int n4 = this.slider.getMajorTickSpacing();
            int n5 = this.slider.getMinorTickSpacing();
            int n6 = 0;
            if (n5 > 0) {
                n6 = n5;
            } else if (n4 > 0) {
                n6 = n4;
            }
            if (n6 != 0) {
                if ((n2 - this.slider.getMinimum()) % n6 != 0) {
                    float f2 = (float)(n2 - this.slider.getMinimum()) / (float)n6;
                    n3 = Math.round(f2);
                    n3 = this.slider.getMinimum() + n3 * n6;
                }
                if (n3 != n2) {
                    this.slider.setExtent(n3 - this.slider.getValue());
                }
            }
        }
        if (this.slider.getOrientation() == 0) {
            n2 = this.xPositionForValue(this.slider.getValue() + this.slider.getExtent());
            this.var_java_awt_Rectangle_a.x = n2 - this.var_java_awt_Rectangle_a.width / 2;
            this.var_java_awt_Rectangle_a.y = this.trackRect.y;
            return;
        }
        n2 = this.yPositionForValue(this.slider.getValue() + this.slider.getExtent());
        this.var_java_awt_Rectangle_a.x = this.trackRect.x;
        this.var_java_awt_Rectangle_a.y = n2 - this.var_java_awt_Rectangle_a.height / 2;
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(12, 12);
    }

    @Override
    public void paint(Graphics graphics, JComponent jComponent) {
        super.paint(graphics, jComponent);
        Rectangle clipBounds = graphics.getClipBounds();
        if (this.var_boolean_a) {
            if (clipBounds.intersects(this.thumbRect)) {
                this.a(graphics);
            }
            if (clipBounds.intersects(this.var_java_awt_Rectangle_a)) {
                this.b(graphics);
                return;
            }
        } else {
            if (clipBounds.intersects(this.var_java_awt_Rectangle_a)) {
                this.b(graphics);
            }
            if (clipBounds.intersects(this.thumbRect)) {
                this.a(graphics);
            }
        }
    }

    @Override
    public void paintTrack(Graphics graphics) {
        super.paintTrack(graphics);
        Rectangle rectangle = this.trackRect;
        if (this.slider.getOrientation() == 0) {
            int n2 = this.thumbRect.x + this.thumbRect.width / 2;
            int n3 = this.var_java_awt_Rectangle_a.x + this.var_java_awt_Rectangle_a.width / 2;
            int n4 = rectangle.height / 2 - 2;
            Color color = graphics.getColor();
            graphics.translate(rectangle.x, rectangle.y + n4);
            graphics.setColor(this.var_java_awt_Color_a);
            int n5 = 0;
            while (n5 <= 3) {
                graphics.drawLine(n2 - rectangle.x, n5, n3 - rectangle.x, n5);
                ++n5;
            }
            graphics.translate(-rectangle.x, -(rectangle.y + n4));
            graphics.setColor(color);
            return;
        }
        int n6 = this.thumbRect.x + this.thumbRect.width / 2;
        int n7 = this.var_java_awt_Rectangle_a.x + this.var_java_awt_Rectangle_a.width / 2;
        int n8 = rectangle.width / 2 - 2;
        Color color = graphics.getColor();
        graphics.translate(rectangle.x + n8, rectangle.y);
        graphics.setColor(this.var_java_awt_Color_a);
        int n9 = 0;
        while (n9 <= 3) {
            graphics.drawLine(n9, n6 - rectangle.y, n9, n7 - rectangle.y);
            ++n9;
        }
        graphics.translate(-(rectangle.x + n8), -rectangle.y);
        graphics.setColor(color);
    }

    @Override
    public void paintThumb(Graphics graphics) {
    }

    private void a(Graphics graphics) {
        Rectangle rectangle = this.thumbRect;
        int n2 = rectangle.width;
        int n3 = rectangle.height;
        graphics = (Graphics2D)graphics.create();
        Shape shape = RangeSliderUI.a(n2 - 1, n3 - 1);
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)graphics).translate(rectangle.x, rectangle.y);
        graphics.setColor(new Color(255, 199, 60));
        ((Graphics2D)graphics).fill(shape);
        graphics.dispose();
    }

    private void b(Graphics graphics) {
        Rectangle rectangle = this.var_java_awt_Rectangle_a;
        int n2 = rectangle.width;
        int n3 = rectangle.height;
        graphics = (Graphics2D)graphics.create();
        Shape shape = RangeSliderUI.a(n2 - 1, n3 - 1);
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D)graphics).translate(rectangle.x, rectangle.y);
        graphics.setColor(new Color(216, 73, 63));
        ((Graphics2D)graphics).fill(shape);
        graphics.dispose();
    }

    private static Shape a(int n2, int n3) {
        Ellipse2D.Double double_ = new Ellipse2D.Double(0.0, 0.0, n2, n3);
        return double_;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void scrollByBlock(int n2) {
        JSlider jSlider = this.slider;
        synchronized (jSlider) {
            int n3 = (this.slider.getMaximum() - this.slider.getMinimum()) / 10;
            if (n3 <= 0 && this.slider.getMaximum() > this.slider.getMinimum()) {
                n3 = 1;
            }
            n2 = n3 * (n2 > 0 ? 1 : -1);
            if (this.var_boolean_a) {
                n3 = ((a)this.slider).a();
                ((a)this.slider).a(n3 + n2);
            } else {
                n3 = this.slider.getValue();
                this.slider.setValue(n3 + n2);
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void scrollByUnit(int n2) {
        JSlider jSlider = this.slider;
        synchronized (jSlider) {
            n2 = 1 * (n2 > 0 ? 1 : -1);
            if (this.var_boolean_a) {
                int n3 = ((a)this.slider).a();
                ((a)this.slider).a(n3 + n2);
            } else {
                int n4 = this.slider.getValue();
                this.slider.setValue(n4 + n2);
            }
            return;
        }
    }

    static /* synthetic */ boolean boolean_a(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.b;
    }

    static /* synthetic */ boolean boolean_b(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.c;
    }

    static /* synthetic */ JSlider javax_swing_JSlider_a(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.slider;
    }

    static /* synthetic */ boolean boolean_c(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.var_boolean_a;
    }

    static /* synthetic */ Rectangle java_awt_Rectangle_a(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.var_java_awt_Rectangle_a;
    }

    static /* synthetic */ Rectangle java_awt_Rectangle_b(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.thumbRect;
    }

    static /* synthetic */ void a(RangeSliderUI rangeSliderUI, boolean bl) {
        rangeSliderUI.var_boolean_a = bl;
    }

    static /* synthetic */ void b(RangeSliderUI rangeSliderUI, boolean bl) {
        rangeSliderUI.b = bl;
    }

    static /* synthetic */ void c(RangeSliderUI rangeSliderUI, boolean bl) {
        rangeSliderUI.c = bl;
    }

    static /* synthetic */ Rectangle java_awt_Rectangle_c(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.trackRect;
    }

    static /* synthetic */ int a(RangeSliderUI rangeSliderUI, int n2) {
        return rangeSliderUI.yPositionForValue(n2);
    }

    static /* synthetic */ boolean d(RangeSliderUI rangeSliderUI) {
        return rangeSliderUI.drawInverted();
    }

    static /* synthetic */ int b(RangeSliderUI rangeSliderUI, int n2) {
        return rangeSliderUI.xPositionForValue(n2);
    }

    static /* synthetic */ void a(RangeSliderUI rangeSliderUI, int n2, int n3) {
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(rangeSliderUI.var_java_awt_Rectangle_a);
        rangeSliderUI.var_java_awt_Rectangle_a.setLocation(n2, n3);
        SwingUtilities.computeUnion(rangeSliderUI.var_java_awt_Rectangle_a.x, rangeSliderUI.var_java_awt_Rectangle_a.y, rangeSliderUI.var_java_awt_Rectangle_a.width, rangeSliderUI.var_java_awt_Rectangle_a.height, rectangle);
        rangeSliderUI.slider.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}


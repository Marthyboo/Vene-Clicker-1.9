/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import com.java.loader.f;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

final class n
implements f {
    private /* synthetic */ d var_com_java_loader_applet_d_a;
    private final /* synthetic */ JLabel var_javax_swing_JLabel_a;

    n(d d2, JLabel jLabel) {
        this.var_com_java_loader_applet_d_a = d2;
        this.var_javax_swing_JLabel_a = jLabel;
    }

    @Override
    public final void a(boolean bl) {
        if (bl) {
            this.var_javax_swing_JLabel_a.setIcon(new ImageIcon(d.java_awt_image_BufferedImage_a(this.var_com_java_loader_applet_d_a)));
            return;
        }
        this.var_javax_swing_JLabel_a.setIcon(new ImageIcon(d.java_awt_image_BufferedImage_b(this.var_com_java_loader_applet_d_a)));
    }
}


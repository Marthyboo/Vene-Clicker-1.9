/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.p;
import com.java.loader.c;
import java.util.ArrayList;
import javax.swing.JToggleButton;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

final class q
implements NativeKeyListener {
    private ArrayList var_java_util_ArrayList_a = new ArrayList();
    private final /* synthetic */ JToggleButton var_javax_swing_JToggleButton_a;

    q(p p2, JToggleButton jToggleButton) {
        this.var_javax_swing_JToggleButton_a = jToggleButton;
    }

    @Override
    public final void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public final void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        c.var_java_util_ArrayList_a.clear();
        ArrayList list = new ArrayList();
        for (Object obj : this.var_java_util_ArrayList_a) {
            Integer n2 = (Integer) obj;
            if (list.contains(n2)) continue;
            list.add(n2);
        }
        c.var_java_util_ArrayList_a.addAll(list);
        this.var_javax_swing_JToggleButton_a.setText(c.java_lang_String_a());
        this.var_javax_swing_JToggleButton_a.setSelected(false);
        GlobalScreen.removeNativeKeyListener(this);
        c.var_boolean_c = false;
    }

    @Override
    public final void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        this.var_java_util_ArrayList_a.add(nativeKeyEvent.getKeyCode());
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.sub_a;

import com.java.loader.c;
import java.util.ArrayList;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public final class a
implements NativeKeyListener {
    private ArrayList a = new ArrayList();

    @Override
    public final void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        boolean bl = this.a.contains(nativeKeyEvent.getKeyCode()) && c.var_java_util_ArrayList_a.contains(nativeKeyEvent.getKeyCode());
        this.a.add(nativeKeyEvent.getKeyCode());
        if (this.a.containsAll(c.var_java_util_ArrayList_a) && c.var_java_util_ArrayList_a.contains(nativeKeyEvent.getKeyCode()) && !c.var_boolean_c && !bl) {
            c.a(!c.boolean_a());
        }
    }

    @Override
    public final void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        while (this.a.contains(new Integer(nativeKeyEvent.getKeyCode()))) {
            this.a.remove(new Integer(nativeKeyEvent.getKeyCode()));
        }
    }

    @Override
    public final void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }
}


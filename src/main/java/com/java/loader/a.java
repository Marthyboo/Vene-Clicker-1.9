/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.awt.AWTException;

public class a {
    private long var_long_a = 0L;

    public interface User32 extends Library {
        User32 INSTANCE = (User32) Native.load("user32", User32.class);
        void mouse_event(int dwFlags, int dx, int dy, int dwData, int dwExtraInfo);
    }

    public a() throws AWTException {
        // No-op for Robot init, but kept for signature compatibility
    }

    static double double_a() {
        return Math.random();
    }

    public long long_a() {
        return this.var_long_a;
    }

    public void void_a() {
        this.var_long_a = System.currentTimeMillis();
        // Left Down
        User32.INSTANCE.mouse_event(0x0002, 0, 0, 0, 0);
    }

    public void b() {
        // Left Up
        User32.INSTANCE.mouse_event(0x0004, 0, 0, 0, 0);
    }

    public void c() {
        this.var_long_a = System.currentTimeMillis();
        // Right Down
        User32.INSTANCE.mouse_event(0x0008, 0, 0, 0, 0);
    }

    public void d() {
        // Right Up
        User32.INSTANCE.mouse_event(0x0010, 0, 0, 0, 0);
    }

    public int[] a(int n2, int n3) {
        int n4 = 0;
        int n5 = 0;
        if (n2 != 0) {
            n4 = (int)((Math.random() * (double)(n2 * 2 + 1)) - (double)n2);
        }
        if (n3 != 0) {
            n5 = (int)((Math.random() * (double)(n3 * 2 + 1)) - (double)n3);
        }
        if (n4 != 0 || n5 != 0) {
            // Relative Move
            User32.INSTANCE.mouse_event(0x0001, n4, n5, 0, 0);
        }
        return new int[]{n4, n5};
    }

    public void revert(int n4, int n5) {
        if (n4 != 0 || n5 != 0) {
            // Relative Revert
            User32.INSTANCE.mouse_event(0x0001, -n4, -n5, 0, 0);
        }
    }
}


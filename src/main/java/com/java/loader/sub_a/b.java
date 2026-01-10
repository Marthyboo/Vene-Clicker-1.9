/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.sub_a;

import com.java.loader.c;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public final class b
implements NativeMouseListener {
    private com.java.loader.b leftClicker = new com.java.loader.b(false);
    private com.java.loader.b rightClicker = new com.java.loader.b(true);
    private boolean isLeftDown;
    private boolean isRightDown;

    public b() {
        this.leftClicker.start();
        this.rightClicker.start();
        this.isLeftDown = false;
        this.isRightDown = false;
    }

    @Override
    public final void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        if (!c.isMinecraftActive()) {
            return;
        }
        if (nativeMouseEvent.getButton() == 1) {
            if (System.currentTimeMillis() - this.leftClicker.a() <= 20L) {
                this.isLeftDown = true;
                return;
            }
            if (c.boolean_a()) {
                this.leftClicker.a(true);
                this.isLeftDown = true;
            }
        } else if (nativeMouseEvent.getButton() == 2) {
            if (System.currentTimeMillis() - this.rightClicker.a() <= 20L) {
                this.isRightDown = true;
                return;
            }
            if (c.boolean_a() && c.var_boolean_g) {
                this.rightClicker.a(true);
                this.isRightDown = true;
            }
        }
    }

    @Override
    public final void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        if (nativeMouseEvent.getButton() == 1) {
            if (this.isLeftDown) {
                this.isLeftDown = false;
                return;
            }
            this.leftClicker.a(false);
        } else if (nativeMouseEvent.getButton() == 2) {
            if (this.isRightDown) {
                this.isRightDown = false;
                return;
            }
            this.rightClicker.a(false);
        }
    }

    @Override
    public final void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    }
}


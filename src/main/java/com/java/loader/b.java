/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader;

import com.java.loader.a;
import com.java.loader.c;
import java.awt.AWTException;

public final class b
extends Thread {
    private a var_com_java_loader_a_a;
    private boolean var_boolean_a = false;
    private boolean b = false;

    public b(boolean bl) {
        this.b = bl;
        try {
            this.var_com_java_loader_a_a = new a();
            return;
        }
        catch (AWTException aWTException) {
            return;
        }
    }

    @Override
    public final void run() {
        while (true) {
            com.java.loader.b.a(1L);
            if (!this.var_boolean_a || !c.isMinecraftActive()) continue;
            int minCPS = this.b ? c.var_int_c : c.var_int_a;
            int maxCPS = this.b ? c.var_int_d : c.var_int_b;
            int n2 = c.var_boolean_b ? (com.java.loader.a.double_a() > 0.7 ? (int)((double)minCPS * 0.75) : minCPS) : minCPS;
            n2 = 1000 / n2 - 1000 / maxCPS + 1;
            long l2 = (int)(com.java.loader.a.double_a() * (double)n2) + 1000 / maxCPS;
            n2 = 11;
            long l3 = (int)(com.java.loader.a.double_a() * (double)n2) + 30;
            l2 -= l3;
            com.java.loader.b.a(l3);
            if (this.b) {
                this.var_com_java_loader_a_a.d();
            } else {
                this.var_com_java_loader_a_a.b();
            }
            if (c.var_int_e != 0 || c.var_int_f != 0) {
                this.var_com_java_loader_a_a.a(c.var_int_e, c.var_int_f);
            }
            com.java.loader.b.a(l2);
            if (!this.var_boolean_a || !c.isMinecraftActive()) continue;
            if (this.b) {
                this.var_com_java_loader_a_a.c();
            } else {
                this.var_com_java_loader_a_a.void_a();
            }
            if (c.var_int_e != 0 || c.var_int_f != 0) {
                this.var_com_java_loader_a_a.a(c.var_int_e, c.var_int_f);
            }
        }
    }

    public final long a() {
        return this.var_com_java_loader_a_a.long_a();
    }

    private static void a(long l2) {
        try {
            com.java.loader.b.sleep(l2);
            return;
        }
        catch (InterruptedException interruptedException) {
            InterruptedException interruptedException2 = interruptedException;
            return;
        }
    }

    public final void a(boolean bl) {
        this.var_boolean_a = bl;
    }
}


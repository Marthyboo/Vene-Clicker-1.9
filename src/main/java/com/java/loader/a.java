/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.security.SecureRandom;

public class a {
    private Robot var_java_awt_Robot_a;
    private long var_long_a = 0L;

    public a() throws java.awt.AWTException {
        this.var_java_awt_Robot_a = new Robot();
    }

    static double double_a() {
        double d2;
        double d3;
        double d4 = Double.MAX_VALUE;
        double d5 = Double.MIN_VALUE;
        int n2 = 0;
        while (n2 < 20) {
            double d6;
            d3 = System.nanoTime();
            new SecureRandom().nextInt();
            double d7 = System.nanoTime();
            double d8 = d7 - d3;
            if (d8 < d4 && d8 > 0.0) {
                d4 = d8;
            } else if (d8 > d5 && d8 > 0.0) {
                d5 = d8;
            }
            ++n2;
        }
        String string = String.valueOf(d5 / d4);
        d3 = Double.valueOf("0." + string.substring(string.indexOf(46) + 2));
        if (d3 < 0.1) {
            return com.java.loader.a.double_a();
        }
        return d3;
    }

    public long long_a() {
        return this.var_long_a;
    }

    public void void_a() {
        this.var_long_a = System.currentTimeMillis();
        this.var_java_awt_Robot_a.mousePress(1024);
    }

    public void b() {
        this.var_java_awt_Robot_a.mouseRelease(1024);
    }

    public void c() {
        this.var_long_a = System.currentTimeMillis();
        this.var_java_awt_Robot_a.mousePress(4096);
    }

    public void d() {
        this.var_java_awt_Robot_a.mouseRelease(4096);
    }

    public void a(int n2, int n3) {
        int n4 = 0;
        int n5 = 0;
        if (n2 != 0) {
            n4 = (int)((Math.random() * (double)(n2 * 2 + 1)) - (double)n2);
        }
        if (n3 != 0) {
            n5 = (int)((Math.random() * (double)(n3 * 2 + 1)) - (double)n3);
        }
        if (n4 != 0 || n5 != 0) {
            this.var_java_awt_Robot_a.mouseMove(MouseInfo.getPointerInfo().getLocation().x + n4, MouseInfo.getPointerInfo().getLocation().y + n5);
        }
    }
}


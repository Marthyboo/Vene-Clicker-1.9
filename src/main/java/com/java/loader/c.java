/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader;

import com.java.loader.sub_a.a;
import com.java.loader.sub_a.b;
import com.java.loader.applet.d;
import com.java.loader.e;
import com.java.loader.f;
import java.awt.Window;
import java.util.ArrayList;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class c {
    public static int var_int_a;
    public static int var_int_b;
    public static int var_int_c;
    public static int var_int_d;
    public static int var_int_e;
    public static int var_int_f;
    public static boolean var_boolean_b;
    public static boolean var_boolean_c;
    public static boolean var_boolean_d;
    private static boolean var_boolean_f;
    public static ArrayList var_java_util_ArrayList_a;
    private static f var_com_java_loader_f_a;
    public static boolean var_boolean_e;
    public static boolean var_boolean_g;
    private static long var_long_a = 0L;
    private static boolean var_boolean_h = true;

    public static boolean isMinecraftActive() {
        if (!var_boolean_d) {
            return true;
        }
        if (System.currentTimeMillis() - var_long_a < 200L) {
            return var_boolean_h;
        }
        var_long_a = System.currentTimeMillis();
        
        char[] windowText = new char[512];
        HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        if (hwnd != null) {
            User32.INSTANCE.GetWindowText(hwnd, windowText, 512);
            String title = Native.toString(windowText);
            if (title.toLowerCase().contains("minecraft")) {
                var_boolean_h = true;
                return true;
            }
        }
        
        var_boolean_h = false;
        return false;
    }

    public c() {
        var_boolean_e = !c.class.getResource("Main.class").toString().startsWith("file:");
        Runtime.getRuntime().addShutdownHook(new Thread(new com.java.loader.d(this)));
        var_java_util_ArrayList_a = new ArrayList();
        com.java.loader.e.b();
        var_boolean_c = false;
        Object object = new d();
        ((Window)object).setVisible(true);
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException nativeHookException) {
            object = nativeHookException;
        }
        GlobalScreen.addNativeMouseListener(new b());
        GlobalScreen.addNativeKeyListener(new a());
    }

    public static String java_lang_String_a() {
        if (var_java_util_ArrayList_a == null || var_java_util_ArrayList_a.size() == 0) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = 0;
        while (n2 < var_java_util_ArrayList_a.size() - 1) {
            stringBuilder.append(String.valueOf(NativeKeyEvent.getKeyText((Integer)var_java_util_ArrayList_a.get(n2))) + " + ");
            ++n2;
        }
        stringBuilder.append(NativeKeyEvent.getKeyText((Integer)var_java_util_ArrayList_a.get(var_java_util_ArrayList_a.size() - 1)));
        return stringBuilder.toString();
    }

    public static void a(boolean bl) {
        var_boolean_f = bl;
        if (var_com_java_loader_f_a != null) {
            var_com_java_loader_f_a.a(bl);
        }
    }

    public static boolean boolean_a() {
        return var_boolean_f;
    }

    public static void a(f f2) {
        var_com_java_loader_f_a = f2;
    }

    public static void void_a() {
        var_java_util_ArrayList_a = new ArrayList<Integer>();
        var_java_util_ArrayList_a.add(33);
        var_int_a = 9;
        var_int_b = 12;
        var_boolean_b = true;
        var_boolean_d = true;
        var_int_e = 0;
        var_int_f = 0;
        var_int_c = 6;
        var_int_d = 9;
        var_boolean_g = true;
        com.java.loader.e.a();
    }
}


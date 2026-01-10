/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader;

import com.java.loader.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

public final class e {
    private static File var_java_io_File_a = new File("config.txt");

    public static void a() {
        Properties props = new Properties();
        
        StringBuilder sb = new StringBuilder();
        if (c.var_java_util_ArrayList_a != null && !c.var_java_util_ArrayList_a.isEmpty()) {
            for (int i = 0; i < c.var_java_util_ArrayList_a.size(); i++) {
                sb.append(c.var_java_util_ArrayList_a.get(i));
                if (i < c.var_java_util_ArrayList_a.size() - 1) {
                    sb.append(":");
                }
            }
        }
        
        props.setProperty("Keybinds", sb.toString());
        props.setProperty("LeftCPSMin", String.valueOf(c.var_int_a));
        props.setProperty("LeftCPSMax", String.valueOf(c.var_int_b));
        props.setProperty("CPSDrops", String.valueOf(c.var_boolean_b));
        props.setProperty("OnlyInMinecraft", String.valueOf(c.var_boolean_d));
        props.setProperty("JitterX", String.valueOf(c.var_int_e));
        props.setProperty("JitterY", String.valueOf(c.var_int_f));
        props.setProperty("IsRightClick", String.valueOf(c.var_boolean_g));
        props.setProperty("RightCPSMin", String.valueOf(c.var_int_c));
        props.setProperty("RightCPSMax", String.valueOf(c.var_int_d));

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(var_java_io_File_a);
            props.store(out, "VeneClicker Configuration");
        } catch (IOException e) {
            // ignore
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {}
            }
        }
    }

    public static void b() {
        if (!var_java_io_File_a.exists()) {
            c.void_a();
            return;
        }

        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(var_java_io_File_a);
            props.load(in);
            
            String keybinds = props.getProperty("Keybinds", "33");
            c.var_java_util_ArrayList_a = new ArrayList();
            if (keybinds != null && !keybinds.isEmpty()) {
                String[] split = keybinds.split(":");
                for (String s : split) {
                    if (!s.trim().isEmpty()) {
                        c.var_java_util_ArrayList_a.add(Integer.parseInt(s.trim()));
                    }
                }
            }
            
            c.var_int_a = Integer.parseInt(props.getProperty("LeftCPSMin", "9"));
            c.var_int_b = Integer.parseInt(props.getProperty("LeftCPSMax", "12"));
            c.var_boolean_b = Boolean.parseBoolean(props.getProperty("CPSDrops", "true"));
            c.var_boolean_d = Boolean.parseBoolean(props.getProperty("OnlyInMinecraft", "true"));
            c.var_int_e = Integer.parseInt(props.getProperty("JitterX", "0"));
            c.var_int_f = Integer.parseInt(props.getProperty("JitterY", "0"));
            c.var_boolean_g = Boolean.parseBoolean(props.getProperty("IsRightClick", "true"));
            c.var_int_c = Integer.parseInt(props.getProperty("RightCPSMin", "6"));
            c.var_int_d = Integer.parseInt(props.getProperty("RightCPSMax", "9"));
        } catch (Exception e) {
            c.void_a();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
        }
    }
}
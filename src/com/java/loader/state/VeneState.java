package com.java.loader.state;

import com.java.loader.listener.StateListener;
import java.util.ArrayList;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class VeneState {
    // Click Settings
    public static int minCps = 9;
    public static int maxCps = 12;
    public static int minRightCps = 10;
    public static int maxRightCps = 14;
    
    // Toggles
    public static boolean enabled = false;
    public static boolean rightClickEnabled = false;
    public static boolean cpsDropsEnabled = true;
    public static boolean onlyInMinecraft = false;

    // Jitter Settings
    public static int jitterIntensity = 0;

    // Keybinds
    public static ArrayList<Integer> keybinds = new ArrayList<>();
    
    // Internal State
    private static boolean active = false;
    private static StateListener stateListener;
    public static boolean isJar;

    static {
        resetToDefaults();
    }

    public static String getKeybindString() {
        if (keybinds == null || keybinds.size() == 0) {
            return "None";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < keybinds.size(); i++) {
            stringBuilder.append(NativeKeyEvent.getKeyText(keybinds.get(i)));
            if (i < keybinds.size() - 1) {
                stringBuilder.append(" + ");
            }
        }
        return stringBuilder.toString();
    }

    public static void setActive(boolean bl) {
        active = bl;
        System.out.println("[Vene] Clicker Active: " + active);
        if (stateListener != null) {
            stateListener.onStateChanged(bl);
        }
    }

    public static boolean isActive() {
        return active;
    }

    public static void setStateListener(StateListener listener) {
        stateListener = listener;
    }

    public static void resetToDefaults() {
        if (keybinds == null) {
            keybinds = new ArrayList<>();
        }
        keybinds.clear();
        keybinds.add(33); // F
        
        minCps = 9;
        maxCps = 12;
        minRightCps = 10;
        maxRightCps = 14;
        
        enabled = false;
        rightClickEnabled = false;
        cpsDropsEnabled = true;
        onlyInMinecraft = false;
        
        jitterIntensity = 0;
    }
}

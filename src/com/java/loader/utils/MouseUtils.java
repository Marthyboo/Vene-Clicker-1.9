package com.java.loader.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import java.util.Random;

public class MouseUtils {
    private long lastClickTime = 0L;
    private static final Random random = new Random();

    // Windows API constants for mouse_event
    private static final int MOUSEEVENTF_LEFTDOWN = 0x0002;
    private static final int MOUSEEVENTF_LEFTUP = 0x0004;
    private static final int MOUSEEVENTF_RIGHTDOWN = 0x0008;
    private static final int MOUSEEVENTF_RIGHTUP = 0x0010;

    public interface User32Extended extends StdCallLibrary {
        User32Extended INSTANCE = Native.load("user32", User32Extended.class);

        void mouse_event(WinDef.DWORD dwFlags, WinDef.DWORD dx, WinDef.DWORD dy, WinDef.DWORD dwData, WinDef.DWORD dwExtraInfo);
    }

    public MouseUtils() {
    }

    public static double getRandomDouble() {
        return random.nextDouble();
    }

    public long getLastClickTime() {
        return this.lastClickTime;
    }

    private void callMouseEvent(int flags, int dx, int dy) {
        User32Extended.INSTANCE.mouse_event(new WinDef.DWORD(flags), new WinDef.DWORD(dx), new WinDef.DWORD(dy), new WinDef.DWORD(0), new WinDef.DWORD(0));
    }

    public void leftClickPress() {
        this.lastClickTime = System.currentTimeMillis();
        callMouseEvent(MOUSEEVENTF_LEFTDOWN, 0, 0);
    }

    public void leftClickRelease() {
        callMouseEvent(MOUSEEVENTF_LEFTUP, 0, 0);
    }

    public void rightClickPress() {
        this.lastClickTime = System.currentTimeMillis();
        callMouseEvent(MOUSEEVENTF_RIGHTDOWN, 0, 0);
    }

    public void rightClickRelease() {
        this.lastClickTime = System.currentTimeMillis();
        callMouseEvent(MOUSEEVENTF_RIGHTUP, 0, 0);
    }

    public void applyJitter(int intensity) {
        if (intensity <= 0) return;
        
        // Custom jitter logic: Small micro-movements
        // Scale moves between -1 and 1 or -2 and 2 based on intensity
        int range = (intensity / 20) + 1; 
        int dx = random.nextInt(range * 2 + 1) - range;
        int dy = random.nextInt(range * 2 + 1) - range;
        
        if (dx != 0 || dy != 0) {
            callMouseEvent(0x0001, dx, dy); // MOUSEEVENTF_MOVE
        }
    }
}
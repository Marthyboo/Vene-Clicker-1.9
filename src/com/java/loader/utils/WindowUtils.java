package com.java.loader.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public final class WindowUtils {
    public static String getActiveWindowTitle() {
        HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        if (hwnd == null) {
            return "";
        }
        int length = User32.INSTANCE.GetWindowTextLength(hwnd);
        if (length == 0) return "";
        
        char[] windowText = new char[length + 1];
        User32.INSTANCE.GetWindowText(hwnd, windowText, length + 1);
        return Native.toString(windowText);
    }
}
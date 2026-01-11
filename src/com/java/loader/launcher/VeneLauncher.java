package com.java.loader.launcher;

import com.java.loader.ConfigManager;
import com.java.loader.ShutdownHook;
import com.java.loader.gui.MainFrame;
import com.java.loader.listener.GlobalKeyListener;
import com.java.loader.listener.GlobalMouseListener;
import com.java.loader.state.VeneState;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class VeneLauncher {
    
    public interface Winmm extends StdCallLibrary {
        Winmm INSTANCE = Native.load("winmm", Winmm.class);
        int timeBeginPeriod(int period);
        int timeEndPeriod(int period);
    }

    public static void main(String[] args) {
        try {
            Winmm.INSTANCE.timeBeginPeriod(1);
        } catch (Throwable ignored) {}
        
        launch();
    }

    public static void launch() {
        VeneState.isJar = !VeneLauncher.class.getResource("VeneLauncher.class").toString().startsWith("file:");
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
        
        ConfigManager.loadConfig();
        
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

        // Setup JNativeHook
        System.setProperty("jnativehook.thread.priority", "10");
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GlobalScreen.addNativeMouseListener(new GlobalMouseListener());
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }
}
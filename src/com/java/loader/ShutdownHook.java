package com.java.loader;

public final class ShutdownHook implements Runnable {
    @Override
    public final void run() {
        ConfigManager.saveConfig();
    }
}

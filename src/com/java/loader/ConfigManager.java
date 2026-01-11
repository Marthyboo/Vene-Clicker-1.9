package com.java.loader;

import com.java.loader.state.VeneState;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;

public final class ConfigManager {
    private static final String CONFIG_FILE = "config.txt";

    public static void saveConfig() {
        Properties props = new Properties();
        
        String keybindsStr = "";
        if (VeneState.keybinds != null && !VeneState.keybinds.isEmpty()) {
            keybindsStr = VeneState.keybinds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(":"));
        }

        props.setProperty("keybinds", keybindsStr);
        props.setProperty("minCps", String.valueOf(VeneState.minCps));
        props.setProperty("maxCps", String.valueOf(VeneState.maxCps));
        props.setProperty("minRightCps", String.valueOf(VeneState.minRightCps));
        props.setProperty("maxRightCps", String.valueOf(VeneState.maxRightCps));
        props.setProperty("rightClickEnabled", String.valueOf(VeneState.rightClickEnabled));
        props.setProperty("cpsDropsEnabled", String.valueOf(VeneState.cpsDropsEnabled));
        props.setProperty("onlyInMinecraft", String.valueOf(VeneState.onlyInMinecraft));
        props.setProperty("jitterIntensity", String.valueOf(VeneState.jitterIntensity));
        props.setProperty("enabled", String.valueOf(VeneState.enabled));

        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            props.store(out, "Vene Clicker Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        File configFile = new File(CONFIG_FILE);
        if (!configFile.exists()) {
            VeneState.resetToDefaults();
            return;
        }

        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(configFile)) {
            props.load(in);

            String keybindsStr = props.getProperty("keybinds", "");
            VeneState.keybinds.clear();
            if (!keybindsStr.isEmpty()) {
                String[] keys = keybindsStr.split(":");
                for (String key : keys) {
                    try {
                        VeneState.keybinds.add(Integer.parseInt(key));
                    } catch (NumberFormatException ignored) {}
                }
            }

            VeneState.minCps = getInt(props, "minCps", 9);
            VeneState.maxCps = getInt(props, "maxCps", 12);
            VeneState.minRightCps = getInt(props, "minRightCps", 10);
            VeneState.maxRightCps = getInt(props, "maxRightCps", 14);
            VeneState.rightClickEnabled = getBool(props, "rightClickEnabled", false);
            VeneState.cpsDropsEnabled = getBool(props, "cpsDropsEnabled", true);
            VeneState.onlyInMinecraft = getBool(props, "onlyInMinecraft", false);
            VeneState.jitterIntensity = getInt(props, "jitterIntensity", 0);
            VeneState.enabled = getBool(props, "enabled", false);
            
        } catch (IOException e) {
            e.printStackTrace();
            VeneState.resetToDefaults();
        }
    }

    private static int getInt(Properties props, String key, int defaultValue) {
        try {
            return Integer.parseInt(props.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static boolean getBool(Properties props, String key, boolean defaultValue) {
        return Boolean.parseBoolean(props.getProperty(key, String.valueOf(defaultValue)));
    }
}
package com.java.loader.gui;

import com.java.loader.ConfigManager;
import com.java.loader.state.VeneState;
import com.java.loader.listener.StateListener;
import com.java.loader.listener.GlobalKeyListener;
import com.java.loader.listener.GlobalMouseListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public final class MainFrame extends JFrame {
    private int mouseX;
    private int mouseY;
    private BufferedImage logoActive;
    private BufferedImage logoInactive;
    private Font fontLato13;
    private Font fontLato16;
    private Font fontLato11;

    public MainFrame() {
        setTitle("VeneClicker");
        setBounds(100, 100, 297, 400); // Increased height from 340 to 400
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setUndecorated(true);
        setResizable(false);
        setBackground(new Color(46, 49, 54));
        getContentPane().setBackground(new Color(46, 49, 54));

        // Drag listeners
        MouseAdapter dragAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
            }
        };
        addMouseListener(dragAdapter);
        addMouseMotionListener(dragAdapter);

        loadAssets();

        // Control Panel (Top Right - Close/Minimize)
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(5, 5, 50, 15);
        controlPanel.setLayout(null);
        controlPanel.setOpaque(false);
        getContentPane().add(controlPanel);

        JButton closeBtn = new JButton(new ImageIcon(loadImage("/res/x.png")));
        closeBtn.setBounds(0, 0, 15, 15);
        closeBtn.setBorder(BorderFactory.createEmptyBorder());
        closeBtn.setOpaque(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setBorderPainted(false);
        closeBtn.addActionListener(e -> System.exit(0));
        controlPanel.add(closeBtn);

        JButton minBtn = new JButton(new ImageIcon(loadImage("/res/min.png")));
        minBtn.setBounds(20, 0, 15, 15);
        minBtn.setBorder(BorderFactory.createEmptyBorder());
        minBtn.setOpaque(false);
        minBtn.setContentAreaFilled(false);
        minBtn.setBorderPainted(false);
        minBtn.addActionListener(e -> setState(JFrame.ICONIFIED));
        controlPanel.add(minBtn);

        // Logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoInactive));
        logoLabel.setBounds(272, 5, 21, 21);
        getContentPane().add(logoLabel);

        VeneState.setStateListener(active -> {
            SwingUtilities.invokeLater(() -> {
                if (active) {
                    logoLabel.setIcon(new ImageIcon(logoActive));
                } else {
                    logoLabel.setIcon(new ImageIcon(logoInactive));
                }
            });
        });

        // Top Buttons (Destruct/Settings)
        JPanel topBtnPanel = new JPanel();
        topBtnPanel.setBounds(5, 28, 288, 64);
        topBtnPanel.setLayout(null);
        topBtnPanel.setOpaque(false);
        getContentPane().add(topBtnPanel);

        JButton destructBtn = new JButton(new ImageIcon(loadImage("/res/destruct.png")));
        destructBtn.setBounds(topBtnPanel.getWidth() / 2 - 74, 0, 64, 64);
        destructBtn.setOpaque(false);
        destructBtn.setContentAreaFilled(false);
        destructBtn.setBorderPainted(false);
        destructBtn.setToolTipText("Self-destruct");
        destructBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to self-destruct?",
                    "Self-Destruct",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                new File("config.txt").delete();
                System.exit(0);
            }
        });
        topBtnPanel.add(destructBtn);

        JButton settingsBtn = new JButton(new ImageIcon(loadImage("/res/settings.png")));
        settingsBtn.setBounds(topBtnPanel.getWidth() / 2 + 10, 0, 64, 64);
        settingsBtn.setOpaque(false);
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setBorderPainted(false);
        settingsBtn.setToolTipText("Settings");
        topBtnPanel.add(settingsBtn);

        // Main Content Area
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(5, 96, 288, 300); // Increased height from 240 to 300
        contentPanel.setLayout(null);
        contentPanel.setOpaque(false);
        getContentPane().add(contentPanel);

        // Keybind Toggle
        JLabel toggleBtnLabel = new JLabel("Toggle Button");
        toggleBtnLabel.setFont(fontLato13);
        toggleBtnLabel.setForeground(Color.WHITE);
        toggleBtnLabel.setBounds(6, 11, 103, 16);
        contentPanel.add(toggleBtnLabel);

        JToggleButton keybindBtn = new JToggleButton(new ImageIcon(loadImage("/res/button.png")));
        keybindBtn.setSelectedIcon(new ImageIcon(loadImage("/res/button_active.png")));
        keybindBtn.setBounds(179, 8, 103, 19);
        keybindBtn.setOpaque(false);
        keybindBtn.setContentAreaFilled(false);
        keybindBtn.setBorderPainted(false);
        keybindBtn.setBorder(BorderFactory.createEmptyBorder());
        keybindBtn.setFont(fontLato13);
        keybindBtn.setText(VeneState.getKeybindString());
        keybindBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        keybindBtn.setVerticalTextPosition(SwingConstants.CENTER);
        keybindBtn.addActionListener(e -> {
            if (keybindBtn.isSelected()) {
                keybindBtn.setText("...");
                GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
                    private ArrayList<Integer> keys = new ArrayList<>();

                    @Override
                    public void nativeKeyTyped(NativeKeyEvent e1) {
                    }

                    @Override
                    public void nativeKeyReleased(NativeKeyEvent e1) {
                        VeneState.keybinds.clear();
                        ArrayList<Integer> uniqueKeys = new ArrayList<>();
                        for (Integer k : keys) {
                            if (!uniqueKeys.contains(k))
                                uniqueKeys.add(k);
                        }
                        VeneState.keybinds.addAll(uniqueKeys);
                        keybindBtn.setText(VeneState.getKeybindString());
                        keybindBtn.setSelected(false);
                        GlobalScreen.removeNativeKeyListener(this);
                    }

                    @Override
                    public void nativeKeyPressed(NativeKeyEvent e1) {
                        keys.add(e1.getKeyCode());
                    }
                });
            }
        });
        contentPanel.add(keybindBtn);

        // Only in Minecraft
        JLabel mcOnlyLabel = new JLabel("Only in Minecraft");
        mcOnlyLabel.setFont(fontLato13);
        mcOnlyLabel.setForeground(Color.WHITE);
        mcOnlyLabel.setBounds(6, 39, 157, 16);
        contentPanel.add(mcOnlyLabel);

        JCheckBox mcOnlyCb = new JCheckBox("");
        mcOnlyCb.setIcon(new ImageIcon(loadImage("/res/checkbox.png")));
        mcOnlyCb.setSelectedIcon(new ImageIcon(loadImage("/res/checkbox_checked.png")));
        mcOnlyCb.setBounds(254, 39, 28, 16);
        mcOnlyCb.setOpaque(false);
        mcOnlyCb.setContentAreaFilled(false);
        mcOnlyCb.setBorderPainted(false);
        mcOnlyCb.setSelected(VeneState.onlyInMinecraft);
        mcOnlyCb.addActionListener(e -> VeneState.onlyInMinecraft = mcOnlyCb.isSelected());
        contentPanel.add(mcOnlyCb);

        // CPS Drops
        JLabel cpsDropsLabel = new JLabel("CPS Drops");
        cpsDropsLabel.setFont(fontLato13);
        cpsDropsLabel.setForeground(Color.WHITE);
        cpsDropsLabel.setBounds(6, 67, 80, 16);
        contentPanel.add(cpsDropsLabel);

        JLabel cpsDropsInfo = new JLabel("[?]");
        cpsDropsInfo.setFont(fontLato13);
        cpsDropsInfo.setForeground(new Color(200, 200, 200));
        cpsDropsInfo.setBounds(73, 67, 14, 16);
        cpsDropsInfo.setToolTipText("Randomly drops CPS to simulate human clicking.");
        contentPanel.add(cpsDropsInfo);

        JCheckBox cpsDropsCb = new JCheckBox("");
        cpsDropsCb.setIcon(new ImageIcon(loadImage("/res/checkbox.png")));
        cpsDropsCb.setSelectedIcon(new ImageIcon(loadImage("/res/checkbox_checked.png")));
        cpsDropsCb.setBounds(254, 67, 28, 16);
        cpsDropsCb.setOpaque(false);
        cpsDropsCb.setContentAreaFilled(false);
        cpsDropsCb.setBorderPainted(false);
        cpsDropsCb.setSelected(VeneState.cpsDropsEnabled);
        cpsDropsCb.addActionListener(e -> VeneState.cpsDropsEnabled = cpsDropsCb.isSelected());
        contentPanel.add(cpsDropsCb);

        // Jitter Slider
        JLabel jitterLabel = new JLabel("Jitter Intensity");
        jitterLabel.setFont(fontLato13);
        jitterLabel.setForeground(Color.WHITE);
        jitterLabel.setBounds(6, 95, 120, 16);
        contentPanel.add(jitterLabel);

        JSlider jitterSlider = new JSlider(0, 100, VeneState.jitterIntensity);
        jitterSlider.setBounds(120, 95, 162, 16);
        jitterSlider.setOpaque(false);
        jitterSlider.addChangeListener(e -> VeneState.jitterIntensity = jitterSlider.getValue());
        contentPanel.add(jitterSlider);

        // Left Click Section
        JLabel leftClickLabel = new JLabel("Left Click");
        leftClickLabel.setFont(fontLato16);
        leftClickLabel.setForeground(Color.WHITE);
        leftClickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftClickLabel.setBounds(6, 120, 276, 16); // Adjusted Y
        contentPanel.add(leftClickLabel);

        RangeSlider cpsSlider = new RangeSlider(5, 25);
        cpsSlider.setBounds(6, 137, 276, 29); // Adjusted Y
        cpsSlider.setValue(VeneState.minCps);
        cpsSlider.setUpperValue(VeneState.maxCps);
        cpsSlider.setFocusable(false);
        cpsSlider.setOpaque(false);
        contentPanel.add(cpsSlider);

        NumberFormat cpsFormat = NumberFormat.getInstance();
        cpsFormat.setMaximumIntegerDigits(2);

        JFormattedTextField minCpsField = new JFormattedTextField(cpsFormat);
        minCpsField.setBounds(6, 159, 28, 26); // Adjusted Y
        minCpsField.setBorder(null);
        minCpsField.setOpaque(true);
        minCpsField.setHorizontalAlignment(SwingConstants.CENTER);
        minCpsField.setValue(cpsSlider.getValue());
        contentPanel.add(minCpsField);

        JFormattedTextField maxCpsField = new JFormattedTextField(cpsFormat);
        maxCpsField.setBounds(254, 159, 28, 26); // Adjusted Y
        maxCpsField.setBorder(null);
        maxCpsField.setOpaque(true);
        maxCpsField.setHorizontalAlignment(SwingConstants.CENTER);
        maxCpsField.setValue(cpsSlider.getUpperValue());
        contentPanel.add(maxCpsField);

        cpsSlider.addChangeListener(e -> {
            minCpsField.setValue(cpsSlider.getValue());
            maxCpsField.setValue(cpsSlider.getUpperValue());
            VeneState.minCps = cpsSlider.getValue();
            VeneState.maxCps = cpsSlider.getUpperValue();
        });

        ActionListener cpsFieldListener = e -> {
            cpsSlider.setValue(((Number) minCpsField.getValue()).intValue());
            cpsSlider.setUpperValue(((Number) maxCpsField.getValue()).intValue());
            VeneState.minCps = cpsSlider.getValue();
            VeneState.maxCps = cpsSlider.getUpperValue();
        };
        minCpsField.addActionListener(cpsFieldListener);
        maxCpsField.addActionListener(cpsFieldListener);

        // Right Click Section
        JLabel rightClickLabel = new JLabel("Right Click");
        rightClickLabel.setFont(fontLato16);
        rightClickLabel.setForeground(Color.WHITE);
        rightClickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightClickLabel.setBounds(6, 192, 276, 16); // Adjusted Y
        contentPanel.add(rightClickLabel);

        JCheckBox rightClickCb = new JCheckBox("");
        rightClickCb.setIcon(new ImageIcon(loadImage("/res/checkbox.png")));
        rightClickCb.setSelectedIcon(new ImageIcon(loadImage("/res/checkbox_checked.png")));
        rightClickCb.setBounds(254, 192, 28, 16); // Adjusted Y
        rightClickCb.setOpaque(false);
        rightClickCb.setContentAreaFilled(false);
        rightClickCb.setBorderPainted(false);
        rightClickCb.setSelected(VeneState.rightClickEnabled);
        rightClickCb.addActionListener(e -> VeneState.rightClickEnabled = rightClickCb.isSelected());
        contentPanel.add(rightClickCb);

        RangeSlider rightCpsSlider = new RangeSlider(5, 25);
        rightCpsSlider.setBounds(6, 209, 276, 29); // Adjusted Y
        rightCpsSlider.setValue(VeneState.minRightCps);
        rightCpsSlider.setUpperValue(VeneState.maxRightCps);
        rightCpsSlider.setFocusable(false);
        rightCpsSlider.setOpaque(false);
        contentPanel.add(rightCpsSlider);

        JFormattedTextField minRightCpsField = new JFormattedTextField(cpsFormat);
        minRightCpsField.setBounds(6, 231, 28, 26); // Adjusted Y
        minRightCpsField.setBorder(null);
        minRightCpsField.setOpaque(true);
        minRightCpsField.setHorizontalAlignment(SwingConstants.CENTER);
        minRightCpsField.setValue(rightCpsSlider.getValue());
        contentPanel.add(minRightCpsField);

        JFormattedTextField maxRightCpsField = new JFormattedTextField(cpsFormat);
        maxRightCpsField.setBounds(254, 231, 28, 26); // Adjusted Y
        maxRightCpsField.setBorder(null);
        maxRightCpsField.setOpaque(true);
        maxRightCpsField.setHorizontalAlignment(SwingConstants.CENTER);
        maxRightCpsField.setValue(rightCpsSlider.getUpperValue());
        contentPanel.add(maxRightCpsField);

        rightCpsSlider.addChangeListener(e -> {
            minRightCpsField.setValue(rightCpsSlider.getValue());
            maxRightCpsField.setValue(rightCpsSlider.getUpperValue());
            VeneState.minRightCps = rightCpsSlider.getValue();
            VeneState.maxRightCps = rightCpsSlider.getUpperValue();
        });

        ActionListener rightCpsFieldListener = e -> {
            rightCpsSlider.setValue(((Number) minRightCpsField.getValue()).intValue());
            rightCpsSlider.setUpperValue(((Number) maxRightCpsField.getValue()).intValue());
            VeneState.minRightCps = rightCpsSlider.getValue();
            VeneState.maxRightCps = rightCpsSlider.getUpperValue();
        };
        minRightCpsField.addActionListener(rightCpsFieldListener);
        maxRightCpsField.addActionListener(rightCpsFieldListener);

        // Title Footer
        JLabel titleLabel = new JLabel("VeneClicker");
        titleLabel.setBounds(5, 5, 288, 16);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(fontLato16);
        titleLabel.setForeground(Color.WHITE);
        getContentPane().add(titleLabel);
    }

    private void loadAssets() {
        logoActive = loadImage("/res/logo_active.png");
        logoInactive = loadImage("/res/logo.png");
        try {
            InputStream is = getClass().getResourceAsStream("/Lato-Regular.ttf");
            if (is == null) {
                // Try file if resource fails
                fontLato13 = new Font("SansSerif", Font.PLAIN, 13);
                fontLato16 = new Font("SansSerif", Font.PLAIN, 16);
                fontLato11 = new Font("SansSerif", Font.PLAIN, 11);
            } else {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
                fontLato13 = baseFont.deriveFont(13.0f);
                fontLato16 = baseFont.deriveFont(16.0f);
                fontLato11 = baseFont.deriveFont(11.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fontLato13 = new Font("SansSerif", Font.PLAIN, 13);
            fontLato16 = new Font("SansSerif", Font.PLAIN, 16);
            fontLato11 = new Font("SansSerif", Font.PLAIN, 11);
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            // Try without leading slash
            try {
                return ImageIO.read(getClass().getResourceAsStream(path.substring(1)));
            } catch (Exception e2) {
                // Try file
                try {
                    return ImageIO.read(new File(path.startsWith("/") ? path.substring(1) : path));
                } catch (Exception e3) {
                    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                }
            }
        }
    }
}
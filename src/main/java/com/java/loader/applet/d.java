/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.a;
import com.java.loader.applet.e;
import com.java.loader.applet.f;
import com.java.loader.applet.g;
import com.java.loader.applet.h;
import com.java.loader.applet.i;
import com.java.loader.applet.j;
import com.java.loader.applet.k;
import com.java.loader.applet.l;
import com.java.loader.applet.m;
import com.java.loader.applet.n;
import com.java.loader.applet.o;
import com.java.loader.applet.p;
import com.java.loader.applet.r;
import com.java.loader.applet.s;
import com.java.loader.c;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

public final class d
extends JFrame {
    private int var_int_a = 0;
    private int var_int_b = 0;
    private BufferedImage var_java_awt_image_BufferedImage_a;
    private BufferedImage var_java_awt_image_BufferedImage_b;
    private Font var_java_awt_Font_a = null;
    private Font var_java_awt_Font_b = null;
    private Font c = null;

    public d() {
        Object object;
        d d2 = this;
        d2.setTitle("VeneClicker");
        d2.setBounds(100, 100, 297, 402);
        d2.setDefaultCloseOperation(3);
        d2.getContentPane().setLayout(null);
        d2.setUndecorated(true);
        d2.setResizable(false);
        d2.setBackground(new Color(46, 49, 54));
        d2.getContentPane().setBackground(new Color(46, 49, 54));
        d2.addMouseMotionListener(new e(d2));
        d2.addMouseListener(new k(d2));
        Object object2 = d2.a("/res/button.png");
        Object object3 = d2.a("/res/x.png");
        Object object4 = d2.a("/res/min.png");
        Object object5 = d2.a("/res/checkbox.png");
        Object object6 = d2.a("/res/checkbox_checked.png");
        Object object7 = d2.a("/res/button_active.png");
        Object buttonIcon = object2;
        Object closeIcon = object3;
        Object minIcon = object4;
        Object checkboxIcon = object5;
        Object checkboxCheckedIcon = object6;
        Object buttonActiveIcon = object7;
        d2.var_java_awt_image_BufferedImage_a = d2.a("/res/logo.png");
        d2.var_java_awt_image_BufferedImage_b = d2.a("/res/logo_active.png");
        BufferedImage bufferedImage = d2.a("/res/settings.png");
        BufferedImage bufferedImage2 = d2.a("/res/destruct.png");
        try {
            if (com.java.loader.c.var_boolean_e) {
                d2.var_java_awt_Font_a = Font.createFont(0, d2.getClass().getResourceAsStream("/Lato-Regular.ttf")).deriveFont(13.0f);
                d2.var_java_awt_Font_b = Font.createFont(0, d2.getClass().getResourceAsStream("/Lato-Regular.ttf")).deriveFont(16.0f);
                d2.c = Font.createFont(0, d2.getClass().getResourceAsStream("/Lato-Regular.ttf")).deriveFont(11.0f);
            } else {
                d2.var_java_awt_Font_a = Font.createFont(0, new File("Lato-Regular.ttf")).deriveFont(13.0f);
                d2.var_java_awt_Font_b = Font.createFont(0, new File("Lato-Regular.ttf")).deriveFont(16.0f);
                d2.c = Font.createFont(0, new File("Lato-Regular.ttf")).deriveFont(11.0f);
            }
            object = GraphicsEnvironment.getLocalGraphicsEnvironment();
            if (com.java.loader.c.var_boolean_e) {
                ((GraphicsEnvironment)object).registerFont(Font.createFont(0, d2.getClass().getResourceAsStream("/Lato-Regular.ttf")));
            } else {
                ((GraphicsEnvironment)object).registerFont(Font.createFont(0, new File("Lato-Regular.ttf")));
            }
        }
        catch (IOException iOException) {
            object = iOException;
        }
        catch (FontFormatException fontFormatException) {
            object = fontFormatException;
        }
        object = new JPanel();
        ((Component)object).setBounds(5, 5, 50, 15);
        d2.getContentPane().add((Component)object);
        ((Container)object).setLayout(null);
        ((JComponent)object).setOpaque(false);
        object3 = new JButton(new ImageIcon((Image)closeIcon));
        ((AbstractButton)object3).addActionListener(new l(d2));
        ((JComponent)object3).setBorder(BorderFactory.createEmptyBorder());
        ((Component)object3).setBounds(0, 0, 15, 15);
        ((JComponent)object3).setOpaque(false);
        ((AbstractButton)object3).setContentAreaFilled(false);
        ((AbstractButton)object3).setBorderPainted(false);
        ((Container)object).add((Component)object3);
        object3 = new JButton(new ImageIcon((Image)minIcon));
        ((AbstractButton)object3).addActionListener(new m(d2));
        ((JComponent)object3).setBorder(BorderFactory.createEmptyBorder());
        ((Component)object3).setBounds(20, 0, 15, 15);
        ((JComponent)object3).setOpaque(false);
        ((AbstractButton)object3).setContentAreaFilled(false);
        ((AbstractButton)object3).setBorderPainted(false);
        ((Container)object).add((Component)object3);
        object3 = new JLabel(new ImageIcon(d2.var_java_awt_image_BufferedImage_a));
        ((Component)object3).setBounds(272, 5, 21, 21);
        d2.getContentPane().add((Component)object3);
        com.java.loader.c.a(new n(d2, (JLabel)object3));
        object3 = new JPanel();
        ((Component)object3).setBounds(5, 28, 288, 64);
        d2.getContentPane().add((Component)object3);
        ((Container)object3).setLayout(null);
        ((JComponent)object3).setOpaque(false);
        object4 = new JButton(new ImageIcon(bufferedImage2));
        ((Component)object4).setBounds(((JComponent)object3).getWidth() / 2 - 74, 0, 64, 64);
        ((JComponent)object4).setOpaque(false);
        ((AbstractButton)object4).setContentAreaFilled(false);
        ((AbstractButton)object4).setBorderPainted(false);
        ((JComponent)object4).setToolTipText("Self-destruct");
        ((Container)object3).add((Component)object4);
        ((AbstractButton)object4).addActionListener(new o(d2));
        object4 = new JButton(new ImageIcon(bufferedImage));
        ((Component)object4).setBounds(((JComponent)object3).getWidth() / 2 + 10, 0, 64, 64);
        ((JComponent)object4).setOpaque(false);
        ((AbstractButton)object4).setContentAreaFilled(false);
        ((AbstractButton)object4).setBorderPainted(false);
        ((JComponent)object4).setToolTipText("Settings");
        ((Container)object3).add((Component)object4);
        object3 = new JPanel();
        JPanel mainPanel = (JPanel) object3;
        ((Component)object3).setBounds(5, 96, 288, 298);
        ((JComponent)object3).setOpaque(false);
        d2.getContentPane().add((Component)object3);
        ((Container)object3).setLayout(null);
        object4 = new JLabel("Toggle Button");
        d2.var_java_awt_Font_a.deriveFont(13.0f);
        ((JComponent)object4).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object4).setFont(new Font("Lato", 0, 13));
        ((JComponent)object4).setForeground(Color.WHITE);
        ((Component)object4).setBounds(6, 11, 103, 16);
        mainPanel.add((Component)object4);
        object2 = new JToggleButton(new ImageIcon((Image)buttonIcon));
        ((AbstractButton)object2).setSelectedIcon(new ImageIcon((Image)buttonActiveIcon));
        ((Component)object2).setBounds(179, 8, 103, 19);
        ((JComponent)object2).setOpaque(false);
        ((AbstractButton)object2).setContentAreaFilled(false);
        ((AbstractButton)object2).setBorderPainted(false);
        ((JComponent)object2).setBorder(BorderFactory.createEmptyBorder());
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((AbstractButton)object2).setText(com.java.loader.c.java_lang_String_a());
        ((AbstractButton)object2).setHorizontalTextPosition(0);
        ((AbstractButton)object2).setVerticalTextPosition(0);
        ((AbstractButton)object2).addActionListener(new p(d2, (JToggleButton)object2));
        mainPanel.add((Component)object2);
        object2 = new JLabel("Only in Minecraft");
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object2).setForeground(Color.WHITE);
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((Component)object2).setBounds(6, 35, 157, 16);
        mainPanel.add((Component)object2);
        object2 = new JCheckBox("");
        ((AbstractButton)object2).setSelected(com.java.loader.c.var_boolean_d);
        ((AbstractButton)object2).setIcon(new ImageIcon((Image)checkboxIcon));
        ((AbstractButton)object2).setSelectedIcon(new ImageIcon((Image)checkboxCheckedIcon));
        ((Component)object2).setBounds(254, 35, 28, 16);
        ((JComponent)object2).setOpaque(false);
        ((AbstractButton)object2).setContentAreaFilled(false);
        ((AbstractButton)object2).setBorderPainted(false);
        ((AbstractButton)object2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                com.java.loader.c.var_boolean_d = ((JCheckBox)e.getSource()).isSelected();
                com.java.loader.e.a();
            }
        });
        mainPanel.add((Component)object2);
        object2 = new JLabel("CPS Drops");
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object2).setForeground(Color.WHITE);
        ((Component)object2).setBounds(6, 55, 80, 16);
        mainPanel.add((Component)object2);
        object2 = new JLabel("[?]");
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((Component)object2).setBounds(73, 55, 14, 16);
        mainPanel.add((Component)object2);
        ((JComponent)object2).setForeground(new Color(200, 200, 200));
        ((Component)object2).addMouseListener(new r(d2, (JLabel)object2));
        object2 = new JCheckBox("");
        ((AbstractButton)object2).setSelected(com.java.loader.c.var_boolean_b);
        ((AbstractButton)object2).setIcon(new ImageIcon((Image)checkboxIcon));
        ((AbstractButton)object2).setSelectedIcon(new ImageIcon((Image)checkboxCheckedIcon));
        ((JComponent)object2).setOpaque(false);
        ((AbstractButton)object2).setContentAreaFilled(false);
        ((AbstractButton)object2).setBorderPainted(false);
        ((Component)object2).setBounds(254, 55, 28, 16);
        ((AbstractButton)object2).addActionListener(new s(d2, (JCheckBox)object2));
        mainPanel.add((Component)object2);
        object2 = new JLabel("Right Click");
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object2).setForeground(Color.WHITE);
        ((Component)object2).setBounds(6, 75, 157, 16);
        mainPanel.add((Component)object2);

        object2 = new JCheckBox("");
        ((AbstractButton)object2).setSelected(com.java.loader.c.var_boolean_g);
        ((AbstractButton)object2).setIcon(new ImageIcon((Image)checkboxIcon));
        ((AbstractButton)object2).setSelectedIcon(new ImageIcon((Image)checkboxCheckedIcon));
        ((Component)object2).setBounds(254, 75, 28, 16);
        ((JComponent)object2).setOpaque(false);
        ((AbstractButton)object2).setContentAreaFilled(false);
        ((AbstractButton)object2).setBorderPainted(false);
        ((AbstractButton)object2).addActionListener(new v(d2, (JCheckBox)object2));
        mainPanel.add((Component)object2);

        object2 = new JLabel("Jitter");
        d2.var_java_awt_Font_a.deriveFont(13.0f);
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object2).setForeground(Color.WHITE);
        ((Component)object2).setBounds(6, 95, 184, 16);
        mainPanel.add((Component)object2);
        object2 = new JLabel("[?]");
        ((Component)object2).setBounds(41, 95, 14, 16);
        mainPanel.add((Component)object2);
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object2).setForeground(new Color(200, 200, 200));
        ((Component)object2).addMouseListener(new f(d2, (JLabel)object2));
        
        object2 = new JLabel("X Axis:");
        ((JComponent)object2).setForeground(Color.WHITE);
        d2.var_java_awt_Font_a.deriveFont(13.0f);
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((Component)object2).setBounds(204, 95, 49, 16);
        mainPanel.add((Component)object2);
        object2 = new JLabel("Y Axis:");
        ((JComponent)object2).setForeground(Color.WHITE);
        ((JComponent)object2).setFont(d2.var_java_awt_Font_a);
        ((Component)object2).setBounds(204, 115, 49, 16);
        mainPanel.add((Component)object2);
        object2 = null;
        try {
            object2 = new MaskFormatter("|#|");
        }
        catch (ParseException parseException) {
            object4 = parseException;
        }
        object4 = new JFormattedTextField((JFormattedTextField.AbstractFormatter)object2);
        ((JComponent)object4).setBorder(null);
        ((JComponent)object4).setOpaque(true);
        ((JTextField)object4).setFont(d2.var_java_awt_Font_a);
        ((JTextField)object4).setHorizontalAlignment(0);
        ((JTextComponent)object4).setText("|" + com.java.loader.c.var_int_e + "|");
        ((Component)object4).setBounds(254, 90, 28, 26);
        mainPanel.add((Component)object4);
        object2 = new JFormattedTextField((JFormattedTextField.AbstractFormatter)object2);
        ((JComponent)object2).setBorder(null);
        ((JComponent)object2).setOpaque(true);
        ((JTextField)object2).setFont(d2.var_java_awt_Font_a);
        ((JTextField)object2).setHorizontalAlignment(0);
        ((JTextComponent)object2).setText("|" + com.java.loader.c.var_int_f + "|");
        ((Component)object2).setBounds(254, 110, 28, 26);
        mainPanel.add((Component)object2);
        ((JTextField)object4).addActionListener(new g(d2, (JFormattedTextField)object4));
        ((JTextField)object2).addActionListener(new h(d2, (JFormattedTextField)object2));
        object5 = new JLabel("Left Click");
        d2.var_java_awt_Font_a.deriveFont(15.0f);
        ((JComponent)object5).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object5).setForeground(Color.WHITE);
        ((JLabel)object5).setHorizontalAlignment(0);
        ((Component)object5).setBounds(6, 140, 276, 16);
        mainPanel.add((Component)object5);
        object5 = new a(5, 25);
        ((a)object5).setValue(com.java.loader.c.var_int_a);
        ((a)object5).a(com.java.loader.c.var_int_b);
        ((JComponent)object5).setBorder(BorderFactory.createEmptyBorder());
        ((Component)object5).setFocusable(false);
        ((JComponent)object5).setOpaque(false);
        ((Component)object5).setBounds(6, 155, 276, 29);
        mainPanel.add((Component)object5);
        object6 = null;
        NumberFormat numberFormat = null;
        try {
            numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumIntegerDigits(2);
        }
        catch (NumberFormatException numberFormatException) {
            object7 = numberFormatException;
        }
        object7 = new JFormattedTextField(numberFormat);
        ((JComponent)object7).setBorder(null);
        ((JComponent)object7).setOpaque(true);
        ((JTextField)object7).setHorizontalAlignment(0);
        ((Component)object7).setBounds(6, 177, 28, 26);
        ((JFormattedTextField)object7).setValue(((a)object5).getValue());
        mainPanel.add((Component)object7);
        object6 = new JFormattedTextField(numberFormat);
        ((JComponent)object6).setBorder(null);
        ((JComponent)object6).setOpaque(true);
        ((JTextField)object6).setHorizontalAlignment(0);
        ((Component)object6).setBounds(254, 177, 28, 26);
        ((JFormattedTextField)object6).setValue(((a)object5).a());
        mainPanel.add((Component)object6);
        ((JSlider)object5).addChangeListener(new i(d2, (JFormattedTextField)object7, (a)object5, (JFormattedTextField)object6));
        object3 = new j(d2, (JFormattedTextField)object7, (JFormattedTextField)object6, (a)object5);
        ((JTextField)object7).addActionListener((ActionListener)object3);
        ((JTextField)object6).addActionListener((ActionListener)object3);

        object5 = new JLabel("Right Click");
        ((JComponent)object5).setFont(d2.var_java_awt_Font_a);
        ((JComponent)object5).setForeground(Color.WHITE);
        ((JLabel)object5).setHorizontalAlignment(0);
        ((Component)object5).setBounds(6, 207, 276, 16);
        mainPanel.add((Component)object5);

        object5 = new a(5, 25);
        ((a)object5).setValue(com.java.loader.c.var_int_c);
        ((a)object5).a(com.java.loader.c.var_int_d);
        ((JComponent)object5).setBorder(BorderFactory.createEmptyBorder());
        ((Component)object5).setFocusable(false);
        ((JComponent)object5).setOpaque(false);
        ((Component)object5).setBounds(6, 222, 276, 29);
        mainPanel.add((Component)object5);
        object7 = new JFormattedTextField(numberFormat);
        ((JComponent)object7).setBorder(null);
        ((JComponent)object7).setOpaque(true);
        ((JTextField)object7).setHorizontalAlignment(0);
        ((Component)object7).setBounds(6, 244, 28, 26);
        ((JFormattedTextField)object7).setValue(((a)object5).getValue());
        mainPanel.add((Component)object7);
        object6 = new JFormattedTextField(numberFormat);
        ((JComponent)object6).setBorder(null);
        ((JComponent)object6).setOpaque(true);
        ((JTextField)object6).setHorizontalAlignment(0);
        ((Component)object6).setBounds(254, 244, 28, 26);
        ((JFormattedTextField)object6).setValue(((a)object5).a());
        mainPanel.add((Component)object6);
        ((JSlider)object5).addChangeListener(new t(d2, (JFormattedTextField)object7, (a)object5, (JFormattedTextField)object6));
        ActionListener actionRight = new u(d2, (JFormattedTextField)object7, (JFormattedTextField)object6, (a)object5);
        ((JTextField)object7).addActionListener(actionRight);
        ((JTextField)object6).addActionListener(actionRight);

        object2 = new JLabel("VeneClicker");
        ((Component)object2).setBounds(5, 5, 288, 16);
        d2.getContentPane().add((Component)object2);
        ((JLabel)object2).setHorizontalAlignment(0);
        ((JComponent)object2).setFont(d2.var_java_awt_Font_b);
        ((JComponent)object2).setForeground(Color.WHITE);
    }

    private BufferedImage a(String object) {
        try {
            if (com.java.loader.c.var_boolean_e) {
                return ImageIO.read(this.getClass().getResourceAsStream((String)object));
            }
            return ImageIO.read(new File((String)(((String)object).startsWith("/") ? ((String)object).substring(1) : object)));
        }
        catch (IOException iOException) {
            return null;
        }
    }

    static /* synthetic */ int int_a(d d2) {
        return d2.var_int_a;
    }

    static /* synthetic */ int int_b(d d2) {
        return d2.var_int_b;
    }

    static /* synthetic */ void a(d d2, int n2) {
        d2.var_int_a = n2;
    }

    static /* synthetic */ void b(d d2, int n2) {
        d2.var_int_b = n2;
    }

    static /* synthetic */ BufferedImage java_awt_image_BufferedImage_a(d d2) {
        return d2.var_java_awt_image_BufferedImage_b;
    }

    static /* synthetic */ BufferedImage java_awt_image_BufferedImage_b(d d2) {
        return d2.var_java_awt_image_BufferedImage_a;
    }

    static /* synthetic */ Font java_awt_Font_a(d d2) {
        return d2.c;
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package com.java.loader.applet;

import com.java.loader.applet.d;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

final class f
implements MouseListener {
    private JFrame var_javax_swing_JFrame_a;
    private /* synthetic */ d var_com_java_loader_applet_d_a;
    private final /* synthetic */ JLabel var_javax_swing_JLabel_a;

    f(d d2, JLabel jLabel) {
        this.var_com_java_loader_applet_d_a = d2;
        this.var_javax_swing_JLabel_a = jLabel;
    }

    @Override
    public final void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public final void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public final void mouseExited(MouseEvent mouseEvent) {
        this.var_javax_swing_JFrame_a.setVisible(false);
        this.var_javax_swing_JFrame_a.dispose();
    }

    @Override
    public final void mouseEntered(MouseEvent mouseEvent) {
        this.var_javax_swing_JFrame_a = new JFrame();
        this.var_javax_swing_JFrame_a.setType(Window.Type.UTILITY);
        this.var_javax_swing_JFrame_a.setBackground(new Color(66, 69, 74));
        this.var_javax_swing_JFrame_a.getContentPane().setBackground(new Color(66, 69, 74));
        this.var_javax_swing_JFrame_a.setUndecorated(true);
        this.var_javax_swing_JFrame_a.setLocation(this.var_javax_swing_JLabel_a.getLocationOnScreen().x + 15, this.var_javax_swing_JLabel_a.getLocationOnScreen().y + 10);
        this.var_javax_swing_JFrame_a.setSize(395, 85);
        this.var_javax_swing_JFrame_a.getContentPane().setLayout(null);
        JLabel label = new JLabel("<html><b color=\"red\"><i>Currently disabled</b></i><br>While autoclicking, VeneClicker will simulate mouse movements for the user.<br><br><code><span bgcolor=\"#6A6D72\">|X-Axis|</span></code> The absolute value of the x-axis jitter range. -x &lt;= val &lt;= x<br><code><span bgcolor=\"#6A6D72\">|Y-Axis|</span></code> The absolute value of the y-axis jitter range. -y &lt;= val &lt;= y</html>");
        label.setFont(d.java_awt_Font_a(this.var_com_java_loader_applet_d_a));
        label.setBounds(5, 5, 385, 75);
        label.setForeground(new Color(42, 172, 255));
        label.setBorder(BorderFactory.createEmptyBorder());
        this.var_javax_swing_JFrame_a.getContentPane().add(label);
        this.var_javax_swing_JFrame_a.setVisible(true);
    }

    @Override
    public final void mouseClicked(MouseEvent mouseEvent) {
    }
}


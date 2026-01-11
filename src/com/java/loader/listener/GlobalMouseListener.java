package com.java.loader.listener;

import com.java.loader.ClickThread;
import com.java.loader.state.VeneState;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

public final class GlobalMouseListener implements NativeMouseListener {
    private ClickThread leftClickThread;
    private ClickThread rightClickThread;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    public GlobalMouseListener() {
        this.leftClickThread = new ClickThread(false);
        this.leftClickThread.start();
        this.rightClickThread = new ClickThread(true);
        this.rightClickThread.start();
    }

    @Override
    public final void nativeMousePressed(NativeMouseEvent e) {
        System.out.println("[Vene] Mouse Pressed: Button " + e.getButton());
        // Button 1 is Left Click
        if (e.getButton() == 1) {
            if (System.currentTimeMillis() - this.leftClickThread.getLastClickTime() <= 20L) {
                this.isLeftPressed = true;
                return;
            }
            if (VeneState.isActive()) {
                System.out.println("[Vene] Starting Left Click Thread");
                this.leftClickThread.setRunning(true);
                this.isLeftPressed = true;
            }
        }
        // Button 2 or 3 is often Right Click depending on system
        else if (e.getButton() == 2 || e.getButton() == 3) {
            if (System.currentTimeMillis() - this.rightClickThread.getLastClickTime() <= 20L) {
                this.isRightPressed = true;
                return;
            }
            if (VeneState.isActive() && VeneState.rightClickEnabled) {
                System.out.println("[Vene] Starting Right Click Thread");
                this.rightClickThread.setRunning(true);
                this.isRightPressed = true;
            }
        }
    }

    @Override
    public final void nativeMouseReleased(NativeMouseEvent e) {
        if (e.getButton() == 1) {
            if (this.isLeftPressed) {
                this.isLeftPressed = false;
                return;
            }
            this.leftClickThread.setRunning(false);
        } else if (e.getButton() == 2 || e.getButton() == 3) {
            if (this.isRightPressed) {
                this.isRightPressed = false;
                return;
            }
            this.rightClickThread.setRunning(false);
        }
    }

    @Override
    public final void nativeMouseClicked(NativeMouseEvent e) {
    }
}

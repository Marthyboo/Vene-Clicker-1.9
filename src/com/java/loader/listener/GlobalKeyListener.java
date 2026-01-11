package com.java.loader.listener;

import com.java.loader.state.VeneState;
import java.util.ArrayList;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public final class GlobalKeyListener implements NativeKeyListener {
    private ArrayList<Integer> pressedKeys = new ArrayList<>();

    @Override
    public final void nativeKeyPressed(NativeKeyEvent e) {
        try {
            // Safety: If for some reason we have a massive number of "stuck" keys, clear them.
            if (this.pressedKeys.size() > 20) {
                this.pressedKeys.clear();
            }

            boolean alreadyPressed = this.pressedKeys.contains(e.getKeyCode()) && VeneState.keybinds.contains(e.getKeyCode());
            if (!this.pressedKeys.contains(e.getKeyCode())) {
                this.pressedKeys.add(e.getKeyCode());
            }
            
            if (this.pressedKeys.containsAll(VeneState.keybinds) && VeneState.keybinds.contains(e.getKeyCode()) && !alreadyPressed) {
                System.out.println("[Vene] Toggle triggered by: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                VeneState.setActive(!VeneState.isActive());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public final void nativeKeyReleased(NativeKeyEvent e) {
        try {
            this.pressedKeys.remove((Integer)e.getKeyCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public final void nativeKeyTyped(NativeKeyEvent e) {
    }
}

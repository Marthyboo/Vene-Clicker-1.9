package com.java.loader;

import com.java.loader.utils.MouseUtils;
import com.java.loader.state.VeneState;
import com.java.loader.utils.WindowUtils;

public final class ClickThread extends Thread {
    private MouseUtils mouseUtils;
    private volatile boolean running = false;
    private final boolean rightClick;
    
    // Latency aggregation
    private double totalLatency = 0;
    private int clickCount = 0;

    // Fatigue/Rhythm state
    private int fatigueTicks = 0;
    private double currentFatigueIntensity = 1.0;

    public ClickThread(boolean rightClick) {
        this.rightClick = rightClick;
        this.mouseUtils = new MouseUtils();
        this.setName(rightClick ? "RightClickThread" : "LeftClickThread");
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public final void run() {
        while (true) {
            if (!this.running) {
                preciseSleep(10_000_000L); // 10ms in nanos
                continue;
            }

            // 1. Calculate Target CPS with Fatigue/Rhythm Logic
            int minVal = this.rightClick ? VeneState.minRightCps : VeneState.minCps;
            int maxVal = this.rightClick ? VeneState.maxRightCps : VeneState.maxCps;
            
            if (VeneState.cpsDropsEnabled) {
                // If we aren't fatigued, small chance to start a fatigue burst (3% chance per click)
                if (fatigueTicks <= 0) {
                    if (MouseUtils.getRandomDouble() > 0.97) {
                        fatigueTicks = 3 + (int)(MouseUtils.getRandomDouble() * 7); // Slow down for 3-10 clicks
                        currentFatigueIntensity = 0.6 + (MouseUtils.getRandomDouble() * 0.25); // 60-85% of normal speed
                    } else {
                        currentFatigueIntensity = 1.0;
                    }
                }

                if (fatigueTicks > 0) {
                    minVal = (int)(minVal * currentFatigueIntensity);
                    maxVal = (int)(maxVal * currentFatigueIntensity);
                    fatigueTicks--;
                }
            }

            double targetCps = minVal + (MouseUtils.getRandomDouble() * (maxVal - minVal));
            long intervalNanos = (long)(1_000_000_000.0 / targetCps);
            long startTime = System.nanoTime();

            // 2. Window Check
            if (VeneState.onlyInMinecraft) {
                String title = WindowUtils.getActiveWindowTitle().toLowerCase();
                if (!title.contains("minecraft") && !title.contains("javaw") && !title.contains("lunar")
                        && !title.contains("badlion") && !title.contains("feather")
                        && !title.contains("cheatbreaker")) {
                    preciseSleep(50_000_000L);
                    continue;
                }
            }

            // 3. Perform Press
            long clickStart = System.nanoTime();
            if (this.rightClick) {
                this.mouseUtils.rightClickPress();
            } else {
                this.mouseUtils.leftClickPress();
            }
            long clickEnd = System.nanoTime();
            
            // Track Latency
            totalLatency += (clickEnd - clickStart) / 1_000_000.0;
            clickCount++;
            if (clickCount >= 40) {
                System.out.printf("[Vene] %s Avg Latency: %.4f ms | Target CPS: %.1f%s\n", 
                    (this.rightClick ? "Right" : "Left"), 
                    (totalLatency / clickCount), 
                    targetCps,
                    (fatigueTicks > 0 ? " (Fatigued)" : ""));
                totalLatency = 0;
                clickCount = 0;
            }

            // 4. Apply Custom Jitter
            if (VeneState.jitterIntensity > 0) {
                this.mouseUtils.applyJitter(VeneState.jitterIntensity);
            }

            // 5. Hold Duration (Press -> Release)
            long holdNanos = (long)(intervalNanos * (0.15 + MouseUtils.getRandomDouble() * 0.15));
            preciseSleep(holdNanos);

            if (this.rightClick) {
                this.mouseUtils.rightClickRelease();
            } else {
                this.mouseUtils.leftClickRelease();
            }

            // 5. Self-Correcting Wait
            long elapsed = System.nanoTime() - startTime;
            long remaining = intervalNanos - elapsed;
            if (remaining > 0) {
                preciseSleep(remaining);
            }
        }
    }

    private void preciseSleep(long nanos) {
        if (nanos <= 0) return;
        long targetTime = System.nanoTime() + nanos;
        while (System.nanoTime() + 2_000_000L < targetTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                if (this.running) break;
            }
        }
        while (System.nanoTime() < targetTime) {
            // Spin-wait
        }
    }

    public final void setRunning(boolean running) {
        boolean wasRunning = this.running;
        this.running = running;
        if (!running) {
            fatigueTicks = 0; // Reset fatigue when letting go
        }
        if (running && !wasRunning) {
            this.interrupt(); 
        }
    }
    
    public final long getLastClickTime() {
        return this.mouseUtils.getLastClickTime();
    }
}
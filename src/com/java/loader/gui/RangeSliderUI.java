package com.java.loader.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSliderUI extends BasicSliderUI {
    private Color rangeColor = new Color(42, 172, 255);
    private Rectangle upperThumbRect;
    private boolean upperThumbSelected;
    private transient boolean lowerPressed;
    private transient boolean upperPressed;
    private Shape cachedThumbShape;

    public RangeSliderUI(RangeSlider b) {
        super(b);
    }

    @Override
    public void installUI(JComponent c) {
        upperThumbRect = new Rectangle();
        super.installUI(c);
    }

    @Override
    protected TrackListener createTrackListener(JSlider slider) {
        return new RangeSliderTrackListener();
    }

    @Override
    protected ChangeListener createChangeListener(JSlider slider) {
        return new RangeSliderChangeListener();
    }

    @Override
    protected void calculateThumbSize() {
        super.calculateThumbSize();
        upperThumbRect.setSize(thumbRect.width, thumbRect.height);
    }

    @Override
    protected void calculateThumbLocation() {
        super.calculateThumbLocation();
        if (slider.getSnapToTicks()) {
            int upperValue = slider.getValue() + slider.getExtent();
            int majorTickSpacing = slider.getMajorTickSpacing();
            int minorTickSpacing = slider.getMinorTickSpacing();
            int tickSpacing = 0;
            if (minorTickSpacing > 0) {
                tickSpacing = minorTickSpacing;
            } else if (majorTickSpacing > 0) {
                tickSpacing = majorTickSpacing;
            }
            if (tickSpacing != 0) {
                if ((upperValue - slider.getMinimum()) % tickSpacing != 0) {
                    float f = (float)(upperValue - slider.getMinimum()) / (float)tickSpacing;
                    int snappedValue = slider.getMinimum() + Math.round(f) * tickSpacing;
                    if (snappedValue != upperValue) {
                        slider.setExtent(snappedValue - slider.getValue());
                    }
                }
            }
        }

        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            int upperThumbX = xPositionForValue(slider.getValue() + slider.getExtent());
            upperThumbRect.x = upperThumbX - (upperThumbRect.width / 2);
            upperThumbRect.y = trackRect.y;
        } else {
            int upperThumbY = yPositionForValue(slider.getValue() + slider.getExtent());
            upperThumbRect.x = trackRect.x;
            upperThumbRect.y = upperThumbY - (upperThumbRect.height / 2);
        }
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(12, 12);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Rectangle clipRect = g.getClipBounds();
        if (upperThumbSelected) {
            if (clipRect.intersects(thumbRect)) {
                paintLowerThumb(g);
            }
            if (clipRect.intersects(upperThumbRect)) {
                paintUpperThumb(g);
            }
        }
        else {
            if (clipRect.intersects(upperThumbRect)) {
                paintUpperThumb(g);
            }
            if (clipRect.intersects(thumbRect)) {
                paintLowerThumb(g);
            }
        }
    }

    @Override
    public void paintTrack(Graphics g) {
        super.paintTrack(g);
        Rectangle trackBounds = trackRect;
        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            int lowerThumbX = thumbRect.x + (thumbRect.width / 2);
            int upperThumbX = upperThumbRect.x + (upperThumbRect.width / 2);
            int cy = (trackBounds.height / 2) - 2;
            Color oldColor = g.getColor();
            g.translate(trackBounds.x, trackBounds.y + cy);
            g.setColor(rangeColor);
            for (int i = 0; i <= 3; i++) {
                g.drawLine(lowerThumbX - trackBounds.x, i, upperThumbX - trackBounds.x, i);
            }
            g.translate(-trackBounds.x, -(trackBounds.y + cy));
            g.setColor(oldColor);
        } else {
            int lowerThumbY = thumbRect.y + (thumbRect.height / 2);
            int upperThumbY = upperThumbRect.y + (upperThumbRect.height / 2);
            int cx = (trackBounds.width / 2) - 2;
            Color oldColor = g.getColor();
            g.translate(trackBounds.x + cx, trackBounds.y);
            g.setColor(rangeColor);
            for (int i = 0; i <= 3; i++) {
                g.drawLine(i, lowerThumbY - trackBounds.y, i, upperThumbY - trackBounds.y);
            }
            g.translate(-(trackBounds.x + cx), -trackBounds.y);
            g.setColor(oldColor);
        }
    }

    @Override
    public void paintThumb(Graphics g) {
        // Do nothing, we paint thumbs in paint()
    }

    private void paintLowerThumb(Graphics g) {
        Rectangle knobBounds = thumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;
        Graphics2D g2d = (Graphics2D) g.create();
        if (cachedThumbShape == null) {
            cachedThumbShape = createThumbShape(w - 1, h - 1);
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(knobBounds.x, knobBounds.y);
        g2d.setColor(new Color(255, 199, 60));
        g2d.fill(cachedThumbShape);
        g2d.dispose();
    }

    private void paintUpperThumb(Graphics g) {
        Rectangle knobBounds = upperThumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;
        Graphics2D g2d = (Graphics2D) g.create();
        if (cachedThumbShape == null) {
            cachedThumbShape = createThumbShape(w - 1, h - 1);
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(knobBounds.x, knobBounds.y);
        g2d.setColor(new Color(216, 73, 63));
        g2d.fill(cachedThumbShape);
        g2d.dispose();
    }

    private Shape createThumbShape(int width, int height) {
        return new Ellipse2D.Double(0, 0, width, height);
    }

    @Override
    public void scrollByBlock(int direction) {
        synchronized (slider) {
            int blockIncrement = (slider.getMaximum() - slider.getMinimum()) / 10;
            if (blockIncrement <= 0 && slider.getMaximum() > slider.getMinimum()) {
                blockIncrement = 1;
            }
            int delta = blockIncrement * ((direction > 0) ? 1 : -1);

            if (upperThumbSelected) {
                int oldValue = ((RangeSlider) slider).getUpperValue();
                ((RangeSlider) slider).setUpperValue(oldValue + delta);
            } else {
                int oldValue = slider.getValue();
                slider.setValue(oldValue + delta);
            }
        }
    }

    @Override
    public void scrollByUnit(int direction) {
        synchronized (slider) {
            int delta = 1 * ((direction > 0) ? 1 : -1);
            if (upperThumbSelected) {
                int oldValue = ((RangeSlider) slider).getUpperValue();
                ((RangeSlider) slider).setUpperValue(oldValue + delta);
            } else {
                int oldValue = slider.getValue();
                slider.setValue(oldValue + delta);
            }
        }
    }

    public class RangeSliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            calculateThumbLocation();
            slider.repaint();
        }
    }

    public class RangeSliderTrackListener extends TrackListener {
        @Override
        public void mousePressed(MouseEvent e) {
            if (!slider.isEnabled()) {
                return;
            }
            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (slider.isRequestFocusEnabled()) {
                slider.requestFocus();
            }

            boolean lowerHit = false;
            boolean upperHit = false;
            if (upperThumbSelected) {
                if (upperThumbRect.contains(currentMouseX, currentMouseY)) {
                    upperHit = true;
                } else if (thumbRect.contains(currentMouseX, currentMouseY)) {
                    lowerHit = true;
                }
            } else {
                if (thumbRect.contains(currentMouseX, currentMouseY)) {
                    lowerHit = true;
                } else if (upperThumbRect.contains(currentMouseX, currentMouseY)) {
                    upperHit = true;
                }
            }

            if (lowerHit) {
                offset = (slider.getOrientation() == JSlider.HORIZONTAL) ? (currentMouseX - thumbRect.x) : (currentMouseY - thumbRect.y);
                lowerPressed = true;
                upperThumbSelected = false;
            } else if (upperHit) {
                offset = (slider.getOrientation() == JSlider.HORIZONTAL) ? (currentMouseX - upperThumbRect.x) : (currentMouseY - upperThumbRect.y);
                upperPressed = true;
                upperThumbSelected = true;
            } else {
                lowerPressed = false;
                upperPressed = false;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            lowerPressed = false;
            upperPressed = false;
            slider.setValueIsAdjusting(false);
            super.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!slider.isEnabled() || (!lowerPressed && !upperPressed)) {
                return;
            }
            currentMouseX = e.getX();
            currentMouseY = e.getY();
            slider.setValueIsAdjusting(true);

            int newValue;
            if (slider.getOrientation() == JSlider.HORIZONTAL) {
                newValue = valueForXPosition(currentMouseX - offset);
            } else {
                newValue = valueForYPosition(currentMouseY - offset);
            }

            if (lowerPressed) {
                slider.setValue(newValue);
            } else if (upperPressed) {
                ((RangeSlider) slider).setUpperValue(newValue);
            }
        }

        @Override
        public boolean shouldScroll(int direction) {
            return false;
        }
    }
}

package com.java.loader.gui;

import javax.swing.JSlider;

public class RangeSlider extends JSlider {
    public RangeSlider(int min, int max) {
        super(min, max);
        this.setUI(new RangeSliderUI(this));
    }

    @Override
    public void updateUI() {
        this.setUI(new RangeSliderUI(this));
        this.updateLabelUIs();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(int n) {
        int n2 = this.getValue();
        if (n2 == n) {
            return;
        }
        int n3 = this.getModel().getExtent();
        int n4 = Math.min(Math.max(this.getMinimum(), n), n2 + n3);
        int n5 = n2 + n3 - n4;
        this.getModel().setRangeProperties(n4, n5, this.getMinimum(), this.getMaximum(), true);
    }

    public int getUpperValue() {
        return this.getValue() + this.getExtent();
    }

    public void setUpperValue(int n) {
        int n2 = this.getValue();
        int n3 = Math.min(Math.max(n2, n), this.getMaximum());
        this.setExtent(n3 - n2);
    }
}

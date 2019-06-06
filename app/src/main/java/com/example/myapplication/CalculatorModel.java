package com.example.myapplication;

public class CalculatorModel {
    private long cell;

    public CalculatorModel() {
        this.cell = 0;
    }

    public void plus(long data) {
        this.cell += data;
    }

    public void minus(long data) {
        this.cell -= data;
    }

    public void setValue(long data) {
        this.cell = data;
    }

    public long getValue() {
        return cell;
    }
}
package com.example.myapplication;

public class CalculatorModel {
    private long value;

    public CalculatorModel() {
        this.value = 0;
    }

    public long getValue() {
        return value;
    }

    public void setValue(final long value) {
        this.value = value;
    }

    public void add(final long x) {
        value += x;
    }

    public void minus(final long x) {
        value -= x;
    }

    public void clear() {
        value = 0;
    }
}

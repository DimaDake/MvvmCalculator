package com.example.myapplication;

public class CalculatorModel {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void clear() {
        this.value = 0;
    }

    public void plus(long value) {
        this.value += value;
    }

    public void minus(long value) {
        this.value -= value;
    }
}

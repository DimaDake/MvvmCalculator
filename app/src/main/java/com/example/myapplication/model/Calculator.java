package com.example.myapplication.model;

public class Calculator {

    private long value;

    public Calculator() {
        this(0);
    }

    public Calculator(final int value) {
        this.value = value;
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

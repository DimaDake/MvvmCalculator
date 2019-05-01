package com.example.myapplication.model;

public class Calculator {


    private long value;

    public Calculator() {
        this(0);
    }

    public Calculator(int value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void add(long x) {
        value += x;
    }

    public void minus(long x) {
        value -= x;
    }

    public void clear() {
        value = 0;
    }
}

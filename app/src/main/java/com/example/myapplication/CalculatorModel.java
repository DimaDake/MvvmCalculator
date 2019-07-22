package com.example.myapplication;

public class CalculatorModel {
    private int value = 0;

    public void sub(int value) {
        this.value -= value;
    }

    public void add(int value) {
        this.value += value;
    }

    public void clean() {
        value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

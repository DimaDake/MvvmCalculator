package com.example.myapplication;

public class CalculatorModel {

    private int value;

    public CalculatorModel() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void plus(int nextValue) {
        value += nextValue;
    }

    public void minus(int nextValue) {
        value -= nextValue;
    }

    public int clean() {
        value = 0;
        return value;
    }
}

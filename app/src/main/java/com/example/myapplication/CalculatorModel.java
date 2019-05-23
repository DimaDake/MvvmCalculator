package com.example.myapplication;

public class CalculatorModel {
    private int value;

    public CalculatorModel() {
        value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void minus(int value) {
        this.value -= value;
    }

    public void plus(int value) {
        this.value += value;
    }

    public int getValue() {
        return value;
    }

    public void allClean() {
        value = 0;
    }
}

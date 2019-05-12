package com.example.myapplication;

public class CalculatorModel {

    private static final int INITIAL_VALUE = 0;

    private long result;

    public CalculatorModel() {
        this.result = INITIAL_VALUE;
    }

    public void plus(long left, long right) {
        result = left + right;
    }

    public void minus(long left, long right) {
        result = left - right;
    }

    public long getResult() {
        return result;
    }

    public void reset() {
        result = INITIAL_VALUE;
    }
}

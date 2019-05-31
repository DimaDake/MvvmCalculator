package com.example.myapplication;

public class CalculatorModel {
    private static final int DEFAULT_VALUE = 0;
    private long result;

    public CalculatorModel() {
        this.result = DEFAULT_VALUE;
    }

    public void plus(long value) {
        result += value;
    }

    public void minus(long value) {
        result -= value;
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public void reset() {
        result = DEFAULT_VALUE;
    }
}
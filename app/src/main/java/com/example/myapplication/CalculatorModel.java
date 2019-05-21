package com.example.myapplication;

public class CalculatorModel {

    public static final int INIT_VALUE = 0;
    private long result;
    private Long subDigit = null;

    public CalculatorModel() {
        result = INIT_VALUE;
    }

    public void addition(long value) {
        result += value;
    }

    public void subtraction(long value) {
        result -= value;
    }

    public long getResult() {
        return result;
    }

    public void reset() {
        result = INIT_VALUE;
    }

    public Long getSubDigit() {
        return subDigit;
    }

    public void setSubDigit(Long subDigit) {
        this.subDigit = subDigit;
    }
}

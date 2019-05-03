package com.example.myapplication.model;

public class CalculatorModel {

    public enum Operation {
        MINUS, PLUS//, DIVIDE, MULTIPLY
    }

    private int data = 0;
    private Operation operation = Operation.PLUS;

    public int add(int value){
        return data += value;
    }

    public int subtract(int value){
        return data -= value;
    }

    public void clear(){
        data = 0;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int doOperation(int value){
        switch (operation){
            case PLUS:
                return add(value);
            case MINUS:
                return subtract(value);
            default:
                return 0;
        }
    }

    public Operation getOperation() {
        return operation;
    }
}

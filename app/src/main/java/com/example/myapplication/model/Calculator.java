package com.example.myapplication.model;

import com.example.myapplication.state.State;
import com.example.myapplication.state.StateComponent;
import com.example.myapplication.state.StateComponentBuilder;


public class Calculator {
    private long x;
    private long y;
    private char operator;
    private State state;

    public Calculator() {
        StateComponent stateComponent = StateComponentBuilder.build().get();
        state = stateComponent.getStateX();
        state.clear(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }

    public State getState() {
        return state;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    private void onKeyDown(char key) throws IllegalArgumentException {
        switch (key){
            case '1':
                state.digit(this, key);
                break;
            case '2':
                state.digit(this, key);
                break;
            case '3':
                state.digit(this, key);
                break;
            case '4':
                state.digit(this, key);
                break;
            case '5':
                state.digit(this, key);
                break;
            case '6':
                state.digit(this, key);
                break;
            case '7':
                state.digit(this, key);
                break;
            case '8':
                state.digit(this, key);
                break;
            case '9':
                state.digit(this, key);
                break;
            case '0':
                state.digit(this, key);
                break;
            case '+':
                state.arithmetic(this, key);
                break;
            case '-':
                state.arithmetic(this, key);
                break;
            case '=':
                state.equal(this);
                break;
            case 'C':
                state.clear(this);
                break;
            default:
                throw new IllegalArgumentException("Ooops, bad argument");
        }
    }
    public void onResetClick() {
        onKeyDown('C');
    }

    public long result(String input) throws IllegalArgumentException{
        for (char key : input.toCharArray()) {
            onKeyDown(key);
        }
        return x;
    }
}

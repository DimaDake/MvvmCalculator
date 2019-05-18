package com.example.myapplication.state;

import com.example.myapplication.model.Calculator;

public class StateX implements State {

    private boolean typing = false;

    public StateX(){

    }

    @Override
    public void clear(Calculator calculator) {
        calculator.setX(0);
        calculator.setY(0);
    }

    @Override
    public void digit(Calculator calculator, char key) {
        if (!typing) {
            calculator.setX(0);
            typing = true;
        }
        long X = calculator.getX() * 10 + Character.getNumericValue(key);
        calculator.setX(X);
    }

    @Override
    public void arithmetic(Calculator calculator, char key) {
        State state = new ActionState();
        calculator.setState(state);
        calculator.getState().arithmetic(calculator, key);
    }

    @Override
    public void equal(Calculator calculator) {
        State state = new AnswerState();
        calculator.setState(state);
        calculator.getState().equal(calculator);
    }
}

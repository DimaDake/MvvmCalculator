package com.example.myapplication.state;

import com.example.myapplication.model.Calculator;

public class ActionState implements State {

    ActionState(){

    }

    @Override
    public void clear(Calculator calculator) {
        State state = new StateX();
        calculator.setState(state);
        calculator.getState().clear(calculator);
    }

    @Override
    public void digit(Calculator calculator, char key) {
        State state = new StateY();
        calculator.setState(state);
        calculator.getState().digit(calculator, key);
    }

    @Override
    public void arithmetic(Calculator calculator, char key) {
        calculator.setOperator(key);
    }

    @Override
    public void equal(Calculator calculator) {
        State state = new AnswerState();
        calculator.setState(state);
        long cmp = calculator.getX();
        calculator.setY(cmp);
        calculator.getState().equal(calculator);
    }
}

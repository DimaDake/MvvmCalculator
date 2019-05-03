package com.example.myapplication.state;

import com.example.myapplication.model.Calculator;

import javax.inject.Inject;

public class StateY implements State {

    private StateComponent stateComponent;
    private boolean typing = false;

    @Inject
    StateY(){
        stateComponent = DaggerStateComponent.create();
    }

    @Override
    public void clear(Calculator calculator) {
        State state = stateComponent.getStateX();
        calculator.setState(state);
        calculator.getState().clear(calculator);
    }

    @Override
    public void digit(Calculator calculator, char key) {
        if(!typing) {
            calculator.setY(0);
            typing = true;
        }
        long Y = calculator.getY() * 10 + Character.getNumericValue(key);
        calculator.setY(Y);
    }

    @Override
    public void arithmetic(Calculator calculator, char key) {
        equal(calculator);
        calculator.getState().arithmetic(calculator, key);
    }

    @Override
    public void equal(Calculator calculator) {
        State state = stateComponent.getAnswerState();
        calculator.setState(state);
        calculator.getState().equal(calculator);
    }
}

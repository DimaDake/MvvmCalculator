package com.example.myapplication.state;

import com.example.myapplication.model.Calculator;

import javax.inject.Inject;

public class ActionState implements State {

    private final StateComponent stateComponent;

    @Inject
    ActionState(){
        stateComponent = StateComponentBuilder.build().get();
    }

    @Override
    public void clear(Calculator calculator) {
        State state = stateComponent.getStateX();
        calculator.setState(state);
        calculator.getState().clear(calculator);
    }

    @Override
    public void digit(Calculator calculator, char key) {
        State state = stateComponent.getStateY();
        calculator.setState(state);
        calculator.getState().digit(calculator, key);
    }

    @Override
    public void arithmetic(Calculator calculator, char key) {
        calculator.setOperator(key);
    }

    @Override
    public void equal(Calculator calculator) {
        State state = stateComponent.getAnswerState();
        calculator.setState(state);
        long cmp = calculator.getX();
        calculator.setY(cmp);
        calculator.getState().equal(calculator);
    }
}

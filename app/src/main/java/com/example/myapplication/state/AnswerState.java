package com.example.myapplication.state;

import com.example.myapplication.app.App;
import com.example.myapplication.model.Calculator;

import javax.inject.Inject;

public class AnswerState implements State {

    private StateComponent stateComponent;

    @Inject
    AnswerState(){
        stateComponent = App.getInstance().getStateComponent();
    }

    @Override
    public void clear(Calculator calculator) {
        State state = stateComponent.getStateX();
        calculator.setState(state);
        calculator.getState().clear(calculator);
    }

    @Override
    public void digit(Calculator calculator, char key) {
        State state = stateComponent.getStateX();
        calculator.setState(state);
        calculator.getState().digit(calculator,key);
    }

    @Override
    public void arithmetic(Calculator calculator, char key) {
        State state = stateComponent.getActionState();
        calculator.setState(state);
        calculator.getState().arithmetic(calculator, key);
    }

    @Override
    public void equal(Calculator calculator) {
        long result = 0;
        switch (calculator.getOperator()) {
            case '+':
                result = calculator.getX() + calculator.getY();
                break;
            case '-':
                result = calculator.getX() - calculator.getY();
                break;
        }
        calculator.setX(result);
    }
}

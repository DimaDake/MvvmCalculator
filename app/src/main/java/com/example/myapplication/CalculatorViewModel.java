package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private final MutableLiveData<String> valueOnScreen;
    private final MutableLiveData<Operation> operation;
    private final CalculatorModel calculatorModel;

    private boolean donePrevious;
    private boolean freshValue;

    public CalculatorViewModel(final CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        operation = new MutableLiveData<>();
        operation.setValue(Operation.PLUS);
        valueOnScreen = new MutableLiveData<>();
        valueOnScreen.setValue("0");
    }

    public LiveData<String> getScreenLiveData() {
        return valueOnScreen;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (freshValue || getLongValueOnScreen() == 0) {
            valueOnScreen.setValue("");
            freshValue = false;
            donePrevious = false;
        }
        valueOnScreen.setValue(valueOnScreen.getValue() + digit);
    }

    public void onPlusButtonClicked() {
        onEqualsButtonClicked();
        operation.setValue(Operation.PLUS);
    }

    public void onMinusButtonClicked() {
        onEqualsButtonClicked();
        operation.setValue(Operation.MINUS);
    }

    public void onEqualsButtonClicked() {
        if (!donePrevious) {
            doOperation();
        }
    }

    public void onAcButtonClicked() {
        valueOnScreen.postValue("0");
        calculatorModel.setValue(0);
    }

    private void doOperation() {
        if (operation.getValue() == null) {
            return;
        }
        switch (operation.getValue()) {
            case PLUS:
                calculatorModel.add(getLongValueOnScreen());
                break;
            case MINUS:
                calculatorModel.minus(getLongValueOnScreen());
                break;
        }
        donePrevious = true;
        freshValue = true;
        operation.setValue(null);
        valueOnScreen.setValue(Long.toString(calculatorModel.getValue()));
    }


    private long getLongValueOnScreen() {
        String value = valueOnScreen.getValue();
        assert value != null;
        return value.length() > 0 ? Long.parseLong(value) : 0;
    }

    public enum Operation {
        PLUS,
        MINUS
    }
}

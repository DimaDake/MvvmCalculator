package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Calculator;
import com.example.myapplication.model.Operation;

public class CalculatorViewModel extends ViewModel {
    private final MutableLiveData<String> valueOnScreen;
    private final MutableLiveData<Operation> operation;
    private final MutableLiveData<Calculator> calculatorData;

    private boolean donePrevious;
    private boolean writeNewValue;

    public CalculatorViewModel() {
        calculatorData = new MutableLiveData<>();

        calculatorData.setValue(new Calculator());

        operation = new MutableLiveData<>();
        operation.setValue(Operation.PLUS);

        valueOnScreen = new MutableLiveData<>();
        valueOnScreen.setValue("0");

        donePrevious = false;
        writeNewValue = true;
    }

    public LiveData<String> getScreenLiveData() {
        return valueOnScreen;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (writeNewValue || getLongValueOnScreen() == 0) {
            valueOnScreen.setValue("");
            writeNewValue = false;
            donePrevious = false;
        }
        valueOnScreen.setValue(valueOnScreen.getValue() + digit);
    }

    public void onPlusButtonClicked() /*throws IllegalStateException */ {
        if (valueOnScreen.getValue() == null) {
//            throw new IllegalStateException("Value is not ready");
            return;
        }
        onEqualsButtonClicked();
        operation.setValue(Operation.PLUS);
    }

    private void doOperation() {
        if (operation.getValue() == null) {
            return;
        }
        Calculator calculator = calculatorData.getValue();
        assert calculator != null;
        switch (operation.getValue()) {
            case PLUS:
                calculator.add(getLongValueOnScreen());
                break;
            case MINUS:
                calculator.minus(getLongValueOnScreen());
                break;
        }
        donePrevious = true;
        writeNewValue = true;
        operation.setValue(null);
        valueOnScreen.setValue(Long.toString(calculator.getValue()));
    }

    public void onMinusButtonClicked() {
        if (valueOnScreen.getValue() == null || valueOnScreen.getValue().length() == 0) {
//            throw new IllegalStateException("Value is not ready");
            return;
        }
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
        Calculator calculator = calculatorData.getValue();
        assert calculator != null;
        calculator.setValue(0);
    }

    private long getLongValueOnScreen() {
        String value = valueOnScreen.getValue();
        assert value != null;
        return value.length() > 0 ? Long.parseLong(value) : 0;
    }
}

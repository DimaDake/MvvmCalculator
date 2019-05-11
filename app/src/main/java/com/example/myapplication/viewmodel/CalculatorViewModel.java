package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Calculator;
import com.example.myapplication.model.Operation;

public class CalculatorViewModel extends ViewModel {
    private final MutableLiveData<String> valueOnScreen;
    private final MutableLiveData<Operation> operation;
    private final Calculator calculator;

    private boolean donePrevious;
    private boolean writeNewValue;

    public CalculatorViewModel() {
        this(new Calculator());
    }

    public CalculatorViewModel(final Calculator calculator) {
        this.calculator = calculator;
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

    public void onDigitButtonClicked(final char digit) {
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

    public void onPlusButtonClicked() {
        onEqualsButtonClicked();
        operation.setValue(Operation.PLUS);
    }

    private void doOperation() {
        if (operation.getValue() == null) {
            return;
        }
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
        calculator.setValue(0);
    }

    private long getLongValueOnScreen() {
        String value = valueOnScreen.getValue();
        assert value != null;
        return value.length() > 0 ? Long.parseLong(value) : 0;
    }

    public Operation getOperation() {
        return operation.getValue();
    }
}

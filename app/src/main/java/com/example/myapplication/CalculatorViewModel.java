package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private CalculatorModel calculatorModel;
    private final MutableLiveData<String> value;
    private Operation operation;
    private boolean enable;

    private enum Operation {
        ADD,
        SUB
    }

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        value = new MutableLiveData<>();
        value.setValue("0");
    }

    public LiveData<String> getScreenLiveData() {
        return value;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (value.getValue().equals("0")) {
            value.setValue("");
        }
        if (enable) {
            value.setValue(String.valueOf(digit));
            enable = false;
        } else value.setValue(value.getValue() + digit);
    }

    public void onPlusButtonClicked() {
        onEqualsButtonClicked();
        operation = Operation.ADD;
    }

    public void onMinusButtonClicked() {
        onEqualsButtonClicked();
        operation = Operation.SUB;
    }

    public void onEqualsButtonClicked() {
        if (operation != null) {
            switch (operation) {
                case ADD:
                    calculatorModel.add(getValue());
                    break;
                case SUB:
                    calculatorModel.sub(getValue());
                    break;
            }
        } else {
            calculatorModel.setValue(getValue());
        }
        value.setValue(String.valueOf(calculatorModel.getValue()));
        operation = null;
        enable = true;
    }

    public void onAcButtonClicked() {
        value.setValue("0");
        calculatorModel.clean();
        operation = null;
        enable = true;
    }

    private int getValue() {
        return Integer.parseInt(value.getValue());
    }
}

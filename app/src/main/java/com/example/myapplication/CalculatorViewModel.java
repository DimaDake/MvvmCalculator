package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private final CalculatorModel calcModel;
    private final MutableLiveData<String> value;
    private boolean nextValue;
    private char operation = '0';

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel calcModel) {
        this.calcModel = calcModel;
        value = new MutableLiveData<>();
        value.setValue("0");
        nextValue = true;
    }

    public LiveData<String> getScreenLiveData() {
        return value;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (nextValue || getValue() == 0) {
            value.setValue("");
            nextValue = false;
        }
        value.setValue(value.getValue() + digit);
    }

    public void onPlusButtonClicked() {
        onEqualsButtonClicked();
        operation = '+';
    }

    public void onMinusButtonClicked() {
        onEqualsButtonClicked();
        operation = '-';
    }

    public void onEqualsButtonClicked() {
        if (operation != '0') {
            switch (operation) {
                case '+':
                    calcModel.addition(getValue());
                    break;
                case '-':
                    calcModel.subtraction(getValue());
                    break;
            }
        } else {
            calcModel.setValue(getValue());
        }
        nextValue = true;
        operation = '0';
        value.setValue(Integer.toString(calcModel.getValue()));
    }

    public void onAcButtonClicked() {
        value.setValue("0");
        calcModel.clean();
        operation = '0';
        nextValue = true;
    }

    private int getValue() {
        return value.getValue().length() > 0 ?
                Integer.parseInt(value.getValue()) :
                0;
    }
}

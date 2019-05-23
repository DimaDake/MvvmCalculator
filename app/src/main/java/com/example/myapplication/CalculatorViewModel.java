package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private final CalculatorModel calculatorModel;
    private final MutableLiveData<String> valueOnScreen;
    private Operation operation;
    private boolean writeNextValue;

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        valueOnScreen = new MutableLiveData<>();
        valueOnScreen.setValue("0");
        writeNextValue = true;
    }

    public LiveData<String> getScreenLiveData() {
        return valueOnScreen;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (writeNextValue || getValueOnScreen() == 0) {
            valueOnScreen.setValue("");
            writeNextValue = false;
        }
        valueOnScreen.setValue(valueOnScreen.getValue() + digit);
    }

    public void onPlusButtonClicked() {
        onEqualsButtonClicked();
        operation = Operation.PLUS;
    }

    public void onMinusButtonClicked() {
        onEqualsButtonClicked();
        operation = Operation.MINUS;
    }

    public void onEqualsButtonClicked() {
        if (operation != null) {
            switch (operation) {
                case PLUS:
                    calculatorModel.plus(getValueOnScreen());
                    break;
                case MINUS:
                    calculatorModel.minus(getValueOnScreen());
                    break;
            }
        } else {
            calculatorModel.setValue(getValueOnScreen());
        }
        writeNextValue = true;
        operation = null;
        valueOnScreen.setValue(Integer.toString(calculatorModel.getValue()));
    }

    public void onAcButtonClicked() {
        valueOnScreen.setValue("0");
        calculatorModel.allClean();
        operation = null;
        writeNextValue = true;
    }

    enum Operation {
        PLUS,
        MINUS
    }

    private int getValueOnScreen() {
        return valueOnScreen.getValue().length() > 0 ?
                Integer.parseInt(valueOnScreen.getValue()) :
                0;
    }
}

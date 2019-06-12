package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorModel calculatorModel;
    private final MutableLiveData<String> valueOnScreen;
    private Operation operation;
    private boolean writeNextValue;

    enum Operation {
        PLUS,
        MINUS
    }

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }


    public CalculatorViewModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        this.valueOnScreen = new MutableLiveData<>();
        this.valueOnScreen.setValue("0");
        this.writeNextValue = true;
    }

    public LiveData<String> getScreenLiveData() {
        return valueOnScreen;
    }

    public void onDigitButtonClicked(char digit) {
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
        calculatorModel.clean();
        operation = null;
        writeNextValue = true;
    }

    private int getValueOnScreen() {
        if (valueOnScreen.getValue().length() > 0) {
            return Integer.parseInt(valueOnScreen.getValue());
        } else {
            return 0;
        }
    }
}
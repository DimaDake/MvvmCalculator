package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private CalculatorModel calculatorModel;
    private Operation operation;
    private Boolean readyForChange = true;
    private final MutableLiveData<String> valueScreen;

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        valueScreen = new MutableLiveData<>();
        valueScreen.setValue("0");
    }

    public LiveData<String> getScreenLiveData() {
        return valueScreen;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (digit == '0' && valueScreen.getValue().equals("0")) {
            return;
        }
        if (readyForChange) {
            valueScreen.setValue(String.valueOf(digit));
            readyForChange = false;
        } else {
            valueScreen.setValue(valueScreen.getValue() + digit);
        }
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
        if (operation == Operation.PLUS) {
            calculatorModel.plus(Integer.parseInt(valueScreen.getValue()));
            valueScreen.setValue(String.valueOf(calculatorModel.getValue()));
            operation = null;
        } else if (operation == Operation.MINUS) {
            calculatorModel.minus(Integer.parseInt(valueScreen.getValue()));
            valueScreen.setValue(String.valueOf(calculatorModel.getValue()));
            operation = null;
        } else {
            calculatorModel.setValue(Integer.parseInt(valueScreen.getValue()));
        }
        valueScreen.setValue(Integer.toString(calculatorModel.getValue()));
        readyForChange = true;
    }

    public void onAcButtonClicked() {
        valueScreen.setValue("0");
        readyForChange = true;
        calculatorModel.clean();
    }

    enum Operation {
        PLUS,
        MINUS
    }
}

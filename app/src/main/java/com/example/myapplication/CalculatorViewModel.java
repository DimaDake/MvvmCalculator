package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private final CalculatorModel calculatorModel;
    private final MutableLiveData<String> displayOnScreen;
    private Operation action;
    boolean newCell;

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        displayOnScreen = new MutableLiveData<>();
        displayOnScreen.setValue("0");
        action = Operation.EQUALS;
        newCell = true;
    }

    public LiveData<String> getScreenLiveData() {
        return displayOnScreen;
    }

    public void onDigitButtonClicked(char digit) {
        if (Character.isDigit(digit)) {
            if (newCell){
                displayOnScreen.setValue("0");
                newCell =false;
            }
            if (digit != '0' && getDisplayOnScreen() == 0) {
                displayOnScreen.setValue(Character.toString(digit));
            } else if (!(digit == '0' && getDisplayOnScreen() == 0)) {
                displayOnScreen.setValue(displayOnScreen.getValue() + digit);
            }
        }
    }

    public void onPlusButtonClicked() {
        onEqualsButtonClicked();
        action = Operation.PLUS;
    }

    public void onMinusButtonClicked() {
        onEqualsButtonClicked();
        action = Operation.MINUS;
    }

    public void onEqualsButtonClicked() {
        long number = getDisplayOnScreen();
        if (action == Operation.PLUS || action == Operation.MINUS) {
            switch (action) {
                case PLUS:
                    calculatorModel.plus(number);
                    action = Operation.EQUALS;
                    break;
                case MINUS:
                    calculatorModel.minus(number);
                    action = Operation.EQUALS;
                    break;
            }
        } else {
            calculatorModel.setValue(number);
        }
        setDisplayOnScreen();
        newCell =true;
    }

    public void onAcButtonClicked() {
        displayOnScreen.setValue("0");
        calculatorModel.setValue(0);
        action = Operation.EQUALS;
        newCell =true;
    }

    public void setDisplayOnScreen(){
        displayOnScreen.setValue(Long.toString(calculatorModel.getValue()));
    }

    private long getDisplayOnScreen() {
        String str = displayOnScreen.getValue();
        long value = Integer.parseInt(str);
        return value;
    }

    enum Operation {
        PLUS,
        MINUS,
        EQUALS
    }
}

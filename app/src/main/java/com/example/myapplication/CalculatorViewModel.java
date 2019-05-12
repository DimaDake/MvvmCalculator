package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private MutableLiveData<String> screenLiveData;
    private final CalculatorModel calculatorModel;
    private long leftValue;
    private Operator lastOperator = Operator.AC;
    private boolean cleared;

    enum Operator {
        PLUS,
        MINUS,
        EQUALS,
        AC
    }

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
        screenLiveData = new MutableLiveData<>();
        clearScreenLiveData();
    }

    public LiveData<String> getScreenLiveData() {
        return screenLiveData;
    }

    public void onDigitButtonClicked(char digit) {
        if (lastOperator == Operator.EQUALS) {
            onAcButtonClicked();
        }
        if (!cleared && (lastOperator == Operator.PLUS || lastOperator == Operator.MINUS)) {
            clearScreenLiveData();
        }
        String currentValue = screenLiveData.getValue();
        if (!(currentValue == "" && digit == '0')) {
            screenLiveData.setValue(currentValue + digit);
        }
    }

    public void onPlusButtonClicked() {
        plusOrMinusLogic();
        lastOperator = Operator.PLUS;
    }

    public void onMinusButtonClicked() {
        plusOrMinusLogic();
        lastOperator = Operator.MINUS;
    }

    public void onEqualsButtonClicked() {
        calculate();
        lastOperator = Operator.EQUALS;
    }

    public void onAcButtonClicked() {
        leftValue = 0;
        clearScreenLiveData();
        calculatorModel.reset();
        lastOperator = Operator.AC;
    }

    private void clearScreenLiveData() {
        screenLiveData.setValue("");
        cleared = true;
    }

    private void setLeftValue() {
        String screenValue = screenLiveData.getValue();
        if (screenValue != null && !screenValue.isEmpty()) {
            leftValue = Integer.valueOf(screenValue);
            cleared = false;
        }
    }

    private void calculate() {
        String screenValue = screenLiveData.getValue();
        if (screenValue != null && !screenValue.isEmpty()) {
            int rightValue = Integer.valueOf(screenValue);

            switch (lastOperator) {
                case PLUS: {
                    calculatorModel.plus(leftValue, rightValue);
                    break;
                }
                case MINUS: {
                    calculatorModel.minus(leftValue, rightValue);
                    break;
                }
            }

            long result = calculatorModel.getResult();
            leftValue = result;
            screenLiveData.setValue(String.valueOf(result));
            cleared = false;
        }
    }

    private void plusOrMinusLogic() {
        if (lastOperator == Operator.PLUS || lastOperator == Operator.MINUS) {
            calculate();
        } else {
            setLeftValue();
        }
    }
}

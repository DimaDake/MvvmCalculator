package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private final CalculatorModel model;
    private final MutableLiveData<String> answer;
    private Operation operation;
    private boolean writeNext;

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel model) {
        this.model = model;
        this.answer = new MutableLiveData<>();
        this.answer.setValue(getModelValue());
        this.operation = Operation.EQUALS;
        this.writeNext = true;
    }

    public LiveData<String> getScreenLiveData() {
        return answer;
    }

    public void onDigitButtonClicked(char digit) {
        if (!Character.isDigit(digit)) {
            return;
        }
        if (writeNext || getValueOnScreen() == 0) {
            answer.setValue("");
            writeNext = false;
        }
        answer.setValue(answer.getValue() + digit);
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
        switch (operation) {
            case PLUS:
                model.plus(getValueOnScreen());
                break;
            case MINUS:
                model.minus(getValueOnScreen());
                break;
            default:
                model.setValue(getValueOnScreen());
        }
        operation = Operation.EQUALS;
        writeNext = true;
        answer.setValue(getModelValue());
    }

    public void onAcButtonClicked() {
        model.clear();
        answer.setValue(getModelValue());
    }

    private long getValueOnScreen() {
        String value = answer.getValue();
        if (value == null || value.length() == 0) {
            return 0;
        }
        return Long.parseLong(value);
    }

    private String getModelValue() {
        return String.valueOf(model.getValue());
    }

    private enum Operation {
        /**
         * Addition
         */
        PLUS,
        /**
         * Subtraction
         */
        MINUS,
        /**
         * Calculate result
         */
        EQUALS
    }
}

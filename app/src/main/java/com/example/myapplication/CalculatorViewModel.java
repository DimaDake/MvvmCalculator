package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Calculator;

public class CalculatorViewModel extends ViewModel {

    private final Calculator calculator;
    private MutableLiveData <String> result = new MutableLiveData<>();
    private StringBuilder input = new StringBuilder();

    public CalculatorViewModel() {
        result.setValue("0");
        calculator = new Calculator();
    }

    public LiveData<String> getScreenLiveData() {
        return result;
    }

    public void onDigitButtonClicked(char digit) {
        input.append(digit);
    }

    public void onPlusButtonClicked() {
        input.append('+');
    }

    public void onMinusButtonClicked() {
        input.append('-');
    }

    public void onEqualsButtonClicked() {
        input.append('=');
        long res = calculator.result(input.toString());
        result.setValue(String.valueOf(res));
        input = new StringBuilder();
    }

    public void onAcButtonClicked() {
        calculator.onResetClick();
        input = new StringBuilder();
    }
}

package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorModel model;
    private MutableLiveData<String> screenLiveData;
    private Mode mode;

    private enum Mode {
        ADDICTION,
        SUBTRACTION
    }

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(CalculatorModel model) {
        this.model = model;
        screenLiveData = new MutableLiveData<>();
        screenLiveData.setValue("");
    }

    public LiveData<String> getScreenLiveData() {
        return screenLiveData;
    }

    public void onDigitButtonClicked(char digit) {
        screenLiveData.setValue(screenLiveData.getValue() + digit);
    }

    public void onPlusButtonClicked() {
        if (mode == null)
            model.addition(getValue());
        mode = Mode.ADDICTION;
        model.setSubDigit(null);
        screenLiveData.setValue("");
    }

    private long getValue() {
        return Long.parseLong(screenLiveData.getValue());
    }

    public void onMinusButtonClicked() {
        if (mode == null)
            model.subtraction(getValue());
        mode = Mode.SUBTRACTION;
        model.setSubDigit(null);
        screenLiveData.setValue("");
    }

    public void onEqualsButtonClicked() {
        switch (mode) {
            case ADDICTION:
                if (model.getSubDigit() == null) {
                    model.setSubDigit(getValue());
                }
                model.addition(model.getSubDigit());
                screenLiveData.setValue(String.valueOf(model.getResult()));
                break;
            case SUBTRACTION:
                if (model.getSubDigit() == null) {
                    model.setSubDigit(getValue());
                }
                model.subtraction(model.getSubDigit());
                screenLiveData.setValue(String.valueOf(model.getResult()));
                break;
            default:

        }
    }

    public void onAcButtonClicked() {
        screenLiveData.setValue(String.valueOf(CalculatorModel.INIT_VALUE));
        model.reset();
        mode = null;
    }
}

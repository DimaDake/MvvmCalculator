package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorModel model;
    private MutableLiveData<String> screenLiveData;
    private Mode mode;
    private Mode previousMode;
    private CalculateMode calculateMode;

    private enum Mode {
        ADDICTION,
        SUBTRACTION,
        EQUALS
    }

    private enum CalculateMode {
        SHOWING,
        CALCULATING
    }

    public CalculatorViewModel() {
        this(new CalculatorModel());
    }

    public CalculatorViewModel(CalculatorModel model) {
        this.model = model;
        screenLiveData = new MutableLiveData<>();
        screenLiveData.setValue(String.valueOf(CalculatorModel.INIT_VALUE));
        calculateMode = CalculateMode.CALCULATING;
    }

    public LiveData<String> getScreenLiveData() {
        return screenLiveData;
    }

    public void onDigitButtonClicked(char digit) {
        if (calculateMode == CalculateMode.SHOWING || screenLiveData.getValue().equals(String.valueOf(CalculatorModel.INIT_VALUE))) {
            screenLiveData.setValue("");
        }
        calculateMode = CalculateMode.CALCULATING;
        screenLiveData.setValue(screenLiveData.getValue() + digit);
    }

    public void onPlusButtonClicked() {
        calculate(Mode.ADDICTION);
    }

    private long getValue() {
        return Long.parseLong(screenLiveData.getValue());
    }

    public void calculate(Mode mode) {
        if (calculateMode == CalculateMode.CALCULATING) {
            calculateMode = null;
            if (this.mode == null) {
                model.addition(getValue());
                screenLiveData.setValue(String.valueOf(CalculatorModel.INIT_VALUE));
            } else if (this.mode == Mode.ADDICTION || this.mode == Mode.SUBTRACTION) {
                onEqualsButtonClicked();
                calculateMode = CalculateMode.SHOWING;
            } else {
                screenLiveData.setValue(String.valueOf(CalculatorModel.INIT_VALUE));
            }
            this.mode = mode;
            model.setSubDigit(null);
        }
    }

    public void onMinusButtonClicked() {
        calculate(Mode.SUBTRACTION);
    }

    public void onEqualsButtonClicked() {
        if (mode == Mode.EQUALS)
            mode = previousMode;
        if (mode != null) {
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
            calculateMode = CalculateMode.CALCULATING;
            previousMode = mode;
            mode = Mode.EQUALS;
        }

    }

    public void onAcButtonClicked() {
        screenLiveData.setValue(String.valueOf(CalculatorModel.INIT_VALUE));
        model.reset();
        mode = null;
    }
}

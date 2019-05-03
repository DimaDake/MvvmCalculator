package com.example.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.CalculatorModel;
import com.example.myapplication.model.evaluators.Evaluator;
import com.example.myapplication.model.expressions.ArithmeticExpression;

import static com.example.myapplication.model.expressions.ArithmeticExpression.Operation;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorModel calculatorModel;
    private final MutableLiveData<String> screenLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> correctExprLiveData = new MutableLiveData<>();

    public CalculatorViewModel(
            @NonNull final ArithmeticExpression expression,
            @NonNull final Evaluator evaluator) {
        calculatorModel = new CalculatorModel(expression, evaluator);
        correctExprLiveData.setValue(true);
    }

    public LiveData<String> getScreenLiveData() {
        return screenLiveData;
    }

    public LiveData<Boolean> getCorrectExprLiveData() {
        return correctExprLiveData;
    }

    public void onDigitButtonClicked(char digit) {
        calculatorModel.setSymbol(digit);
        correctExprLiveData.setValue(true);
        screenLiveData.setValue(calculatorModel.getExpression());
    }

    public void onPlusButtonClicked() {
        calculatorModel.setSymbol(Operation.PLUS.getSymbol());
        correctExprLiveData.setValue(true);
        screenLiveData.setValue(calculatorModel.getExpression());
    }

    public void onMinusButtonClicked() {
        calculatorModel.setSymbol(Operation.MINUS.getSymbol());
        correctExprLiveData.setValue(true);
        screenLiveData.setValue(calculatorModel.getExpression());
    }

    public void onEqualsButtonClicked() {
        screenLiveData.setValue(calculatorModel.calculate());
        if (calculatorModel.isExpressionCorrect()) {
            correctExprLiveData.setValue(true);
        } else {
            correctExprLiveData.setValue(false);
        }
    }

    public void onAcButtonClicked() {
        calculatorModel.clearExpression();
        correctExprLiveData.setValue(true);
        screenLiveData.setValue(calculatorModel.getExpression());
    }
}

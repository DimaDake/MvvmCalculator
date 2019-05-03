package com.example.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.model.evaluators.Evaluator;
import com.example.myapplication.model.expressions.ArithmeticExpression;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final ArithmeticExpression expression;

    @NonNull
    private final Evaluator evaluator;

    public ViewModelFactory(
            @NonNull final ArithmeticExpression expression,
            @NonNull final Evaluator evaluator) {
        super();
        this.expression = expression;
        this.evaluator = evaluator;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == CalculatorViewModel.class) {
            return (T) new CalculatorViewModel(expression, evaluator);
        } else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}

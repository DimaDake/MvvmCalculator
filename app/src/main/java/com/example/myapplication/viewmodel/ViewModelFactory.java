package com.example.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.model.CalculatorModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final CalculatorModel calculatorModel;

    public ViewModelFactory(@NonNull final CalculatorModel calculatorModel) {
        super();
        this.calculatorModel = calculatorModel;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == CalculatorViewModel.class) {
            return (T) new CalculatorViewModel(calculatorModel);
        } else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}

package com.example.myapplication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.CalculatorModel;
import com.example.myapplication.model.evaluators.AddSubEvaluator;
import com.example.myapplication.model.expressions.AddSubExpression;
import com.example.myapplication.viewmodel.CalculatorViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;

public class CalculatorActivity extends AppCompatActivity {

    @NonNull
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CalculatorModel calculatorModel = new CalculatorModel(
                new AddSubExpression(),
                new AddSubEvaluator());
        calculatorViewModel =
                ViewModelProviders.of(this, new ViewModelFactory(calculatorModel))
                .get(CalculatorViewModel.class);

        final ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(calculatorViewModel);
    }
}

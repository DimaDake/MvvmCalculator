package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.evaluators.AddSubEvaluator;
import com.example.myapplication.model.evaluators.Evaluator;
import com.example.myapplication.model.expressions.AddSubExpression;
import com.example.myapplication.model.expressions.ArithmeticExpression;
import com.example.myapplication.viewmodel.CalculatorViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;

public class CalculatorActivity extends AppCompatActivity {

    @NonNull
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ArithmeticExpression expression = new AddSubExpression();
        final Evaluator evaluator = new AddSubEvaluator();
        calculatorViewModel =
                ViewModelProviders.of(this, new ViewModelFactory(expression, evaluator))
                .get(CalculatorViewModel.class);

        final ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(calculatorViewModel);
    }
}

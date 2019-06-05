package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    private Button AC;
    private Button minus;
    private Button plus;
    private Button equal;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private CalculatorViewModel calculatorViewModel;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AC = findViewById(R.id.AC);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal_sign);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        editText = findViewById(R.id.editText);
        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        calculatorViewModel.getScreenLiveData().observe(this, this::updateText);

        AC.setOnClickListener(v -> calculatorViewModel.onAcButtonClicked());

        minus.setOnClickListener(v -> calculatorViewModel.onMinusButtonClicked());

        plus.setOnClickListener(v -> calculatorViewModel.onPlusButtonClicked());

        equal.setOnClickListener(v -> calculatorViewModel.onEqualsButtonClicked());

        zero.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('0'));

        one.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('1'));

        two.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('2'));

        three.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('3'));

        four.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('4'));

        five.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('5'));

        six.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('6'));

        seven.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('7'));

        eight.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('8'));

        nine.setOnClickListener(v -> calculatorViewModel.onDigitButtonClicked('9'));
    }

    private void updateText(String text) {
        editText.setText(text);
    }
}

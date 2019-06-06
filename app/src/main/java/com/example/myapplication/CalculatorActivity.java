package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    private EditText tabloCalc;
    private CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabloCalc = findViewById(R.id.tabloCalc);
        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        calculatorViewModel.getScreenLiveData().observe(this, this::updateTabloCalc);
    }

    private void updateTabloCalc(String text) {
        tabloCalc.setText(text);
    }

    public void onClickMyButton(final View view) {
        String str = ((Button) view).getText().toString();
        Character symbol = str.charAt(0);
        if ( Character.toString(symbol).matches("[0-9]")) {
            calculatorViewModel.onDigitButtonClicked(symbol);
        } else {
            switch (str) {
                case "+":
                    calculatorViewModel.onPlusButtonClicked();
                    break;
                case "-":
                    calculatorViewModel.onMinusButtonClicked();
                    break;
                case "=":
                    calculatorViewModel.onEqualsButtonClicked();
                    break;
                case "AC":
                    calculatorViewModel.onAcButtonClicked();
                    break;
            }
        }
    }
}


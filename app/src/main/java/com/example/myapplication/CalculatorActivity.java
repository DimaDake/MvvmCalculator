package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorViewModel calculatorViewModel;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        calculatorViewModel.getScreenLiveData().observe(this, this::updateEditText);
    }

    public void onClick(final View view) {
        String text = ((Button) view).getText().toString();
        if (Character.isDigit(text.charAt(0))) {
            calculatorViewModel.onDigitButtonClicked(text.charAt(0));
            return;
        }
        switch (text) {
            case "+":
                calculatorViewModel.onPlusButtonClicked();
                break;
            case "=":
                calculatorViewModel.onEqualsButtonClicked();
                break;
            case "-":
                calculatorViewModel.onMinusButtonClicked();
                break;
            case "AC":
                calculatorViewModel.onAcButtonClicked();
                break;
        }
    }

    private void updateEditText(String text) {
        editText.setText(text);
    }
}

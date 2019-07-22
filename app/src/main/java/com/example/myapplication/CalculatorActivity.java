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
        calculatorViewModel.getScreenLiveData().observe(this, this::updateText);
    }

    public void onClick(View view) {
        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "+":
                calculatorViewModel.onPlusButtonClicked();
                break;
            case "-":
                calculatorViewModel.onMinusButtonClicked();
                break;
            case "AC":
                calculatorViewModel.onAcButtonClicked();
                break;
            case "=":
                calculatorViewModel.onEqualsButtonClicked();
                break;
            default:
                calculatorViewModel.onDigitButtonClicked(button.getText().charAt(0));
        }
    }

    private void updateText(String text) {
        editText.setText(text);
    }
}

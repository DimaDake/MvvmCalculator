package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorViewModel calcVM;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.am_edt__input_text);
        calcVM = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        calcVM.getScreenLiveData().observe(this, this::updateInputText);
    }

    public void onClick(final View view) {
        char symbol = ((Button) view).getText().toString().charAt(0);
        if (Character.isDigit(symbol)) {
            calcVM.onDigitButtonClicked(symbol);
            return;
        }
        switch (symbol) {
            case '+':
                calcVM.onPlusButtonClicked();
                break;
            case '=':
                calcVM.onEqualsButtonClicked();
                break;
            case '-':
                calcVM.onMinusButtonClicked();
                break;
            case 'A':
                calcVM.onAcButtonClicked();
                break;
        }
    }

    private void updateInputText(String text) {
        inputText.setText(text);
    }
}

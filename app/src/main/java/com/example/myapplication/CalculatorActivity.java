package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorViewModel viewModel;
    private EditText editText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.activity_main_result_edit_text);
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        viewModel.getScreenLiveData().observe(this, this::updateScreen);
    }

    private void updateScreen(final String text) {
        editText.setText(text);
    }

    public void onButtonClick(final View view) {
        final char symbol = view.getTag().toString().charAt(0);

        if (Character.isDigit(symbol)) {
            viewModel.onDigitButtonClicked(symbol);
            return;
        }

        switch (symbol) {
            case '-':
                viewModel.onMinusButtonClicked();
                break;
            case '+':
                viewModel.onPlusButtonClicked();
                break;
            case '=':
                viewModel.onEqualsButtonClicked();
                break;
            case 'D':
                viewModel.onAcButtonClicked();
                break;
        }
    }
}

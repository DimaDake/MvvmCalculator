package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String value) {
                ((EditText) findViewById(R.id.result)).setText(value);
            }
        };
        viewModel.getScreenLiveData().observe(this, observer);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "+":
                viewModel.onPlusButtonClicked();
                break;
            case "-":
                viewModel.onMinusButtonClicked();
                break;
            case "=":
                viewModel.onEqualsButtonClicked();
                break;
            case "AC":
                viewModel.onAcButtonClicked();
                break;
            default:
                viewModel.onDigitButtonClicked(button.getText().charAt(0));
        }
    }
}

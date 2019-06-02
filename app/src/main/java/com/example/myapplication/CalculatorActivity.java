package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText answer = findViewById(R.id.answer);
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        viewModel.getScreenLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                answer.setText(s);
            }
        });
    }

    public void onClick(View view) {
        final Button button = (Button) view;
        final String value = button.getText().toString();
        switch (value) {
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

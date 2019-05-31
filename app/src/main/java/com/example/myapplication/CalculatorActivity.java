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
    private EditText calcScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        calcScreen = findViewById(R.id.calcScreen);

        final Observer<String> screenObserver = screenValue -> calcScreen.setText(screenValue);

        viewModel.getScreenLiveData().observe(this, screenObserver);
    }

    public void onButtonClick(View view) {
        if (view instanceof Button) {
            char buttonChar = ((Button) view).getText().charAt(0);
            if (Character.isDigit(buttonChar)) {
                viewModel.onDigitButtonClicked(buttonChar);
            } else {
                String buttonText = ((Button) view).getText().toString();

                switch (buttonText) {
                    case "+": {
                        viewModel.onPlusButtonClicked();
                        break;
                    }
                    case "-": {
                        viewModel.onMinusButtonClicked();
                        break;
                    }
                    case "=": {
                        viewModel.onEqualsButtonClicked();
                        break;
                    }
                    case "AC": {
                        viewModel.onAcButtonClicked();
                        break;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("View is not instance of Button");
        }
    }
}
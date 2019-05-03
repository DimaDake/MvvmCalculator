package com.example.myapplication.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.viewmodel.CalculatorViewModel;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorViewModel viewModel;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        viewModel.getScreenLiveData().observe(this, this::updateScreen);

        ViewGroup btnGrid = findViewById(R.id.buttonGrid);
        final int count = btnGrid.getChildCount();
        for (int i = 0; i < count; i ++){
            View btn = btnGrid.getChildAt(i);
            if (btn instanceof Button){
                btn.setOnClickListener(this::buttonClick);
            }
        }
    }

    private void buttonClick(View btn){
        String btnValue = ((Button)btn).getText().toString();
        char v = btnValue.charAt(0);
        if (Character.isDigit(v)){
            viewModel.onDigitButtonClicked(v);
            return;
        }

        switch (v){
            case 'A':
                viewModel.onAcButtonClicked();
                break;
            case '-':
                viewModel.onMinusButtonClicked();
                break;
            case '+':
                viewModel.onPlusButtonClicked();
                break;
            case '=':
                viewModel.onEqualsButtonClicked();
                break;
        }
    }

    private void updateScreen(String s){
        editText.setText(s);
    }
}

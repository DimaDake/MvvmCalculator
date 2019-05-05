package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorViewModel calcViewModel = new CalculatorViewModel();
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.activity_main__display);
        calcViewModel.getScreenLiveData().observe(this,calcResultObserver);
    }

    private final Observer <String> calcResultObserver = new Observer<String>() {
        @Override
        public void onChanged(String result) {
            display.setText(result);
        }
    };
}

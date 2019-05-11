package com.example.myapplication;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import static org.junit.Assert.*;


public class TestViewModel {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private CalculatorViewModel calculatorViewModel;


    @Before
    public void setUp() {
        calculatorViewModel = new CalculatorViewModel();
    }


    @Test
    public void checkState(){
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("22", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
        assertEquals("0", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("5", calculatorViewModel.getScreenLiveData().getValue());
    }
}

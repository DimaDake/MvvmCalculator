package com.example.myapplication;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplication.model.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestViewModel {

    @Mock
    private Calculator calculator;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private CalculatorViewModel calculatorViewModel;

    @Before
    public void setUp() {
        calculatorViewModel = new CalculatorViewModel(calculator);
    }


    @Test
    public void addition(){
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        assertEquals("20", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        assertEquals("20+20", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onEqualsButtonClicked();
        Mockito.when(calculator.getResult()).thenReturn((long)40);
        calculatorViewModel.onAcButtonClicked();
        assertEquals("0", calculatorViewModel.getScreenLiveData().getValue());
    }
    @Test
    public void subtraction() {
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        assertEquals("400", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        assertEquals("400-200", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onEqualsButtonClicked();
        Mockito.when(calculator.getResult()).thenReturn((long) 200);
        calculatorViewModel.onAcButtonClicked();
        assertEquals("0", calculatorViewModel.getScreenLiveData().getValue());
    }
}

package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {

    private CalculatorViewModel calculatorViewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    CalculatorModel calculatorModel;

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    @Test
    public void getScreenLiveData() {
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('3');
        final LiveData<String> screenLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("123", screenLiveData.getValue());

        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('2');
        final LiveData<String> screenLiveData2 = calculatorViewModel.getScreenLiveData();
        assertEquals("12", screenLiveData2.getValue());
    }

    @Test
    public void onPlusButtonClicked() {
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('3');
        when(calculatorModel.getValue()).thenReturn(43);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("43", stringLiveData.getValue());
    }

    @Test
    public void onMinusButtonClicked() {
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        when(calculatorModel.getValue()).thenReturn(7);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("7", stringLiveData.getValue());

        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('3');
        when(calculatorModel.getValue()).thenReturn(-3);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData2 = calculatorViewModel.getScreenLiveData();
        assertEquals("-3", stringLiveData2.getValue());
    }

    @Test
    public void onAcButtonClicked() {
        calculatorViewModel.onAcButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("0", stringLiveData.getValue());
    }
}
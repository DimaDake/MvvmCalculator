package com.example.myapplication;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {

    @Mock
    private CalculatorModel model;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private CalculatorViewModel calculatorViewModel;

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(model);
    }

    @Test
    public void inputTest() {
        calculatorViewModel.onDigitButtonClicked('5');
        assertEquals("5", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('0');
        assertEquals("10", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('9');
        assertEquals("2019", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
    }

    @Test
    public void addition() {
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        Mockito.when(model.getResult()).thenReturn(10L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("10", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        Mockito.when(model.getResult()).thenReturn(55L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("55", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        Mockito.when(model.getResult()).thenReturn(55L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("55", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onPlusButtonClicked();
        Mockito.when(model.getResult()).thenReturn(20L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("20", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
    }

    @Test
    public void subtraction() {
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        Mockito.when(model.getResult()).thenReturn(-5L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("-5", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('5');
        Mockito.when(model.getResult()).thenReturn(-60L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("-60", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
    }

}

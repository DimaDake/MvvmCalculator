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
        calculatorViewModel.onDigitButtonClicked('8');
        assertEquals("8", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('8');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        assertEquals("800", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('5');
        assertEquals("555", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('5');
        assertEquals("3535", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
    }

    @Test
    public void addition() {
        calculatorViewModel.onDigitButtonClicked('7');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('8');
        Mockito.when(model.getValue()).thenReturn(15);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("15", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('9');
        Mockito.when(model.getValue()).thenReturn(39);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("39", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
    }

    @Test
    public void subtraction() {
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        Mockito.when(model.getValue()).thenReturn(-5);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("-5", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('6');
        calculatorViewModel.onDigitButtonClicked('4');
        Mockito.when(model.getValue()).thenReturn(-69);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("-69", calculatorViewModel.getScreenLiveData().getValue());
        calculatorViewModel.onAcButtonClicked();
    }
}

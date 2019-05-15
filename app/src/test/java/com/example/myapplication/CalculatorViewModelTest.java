package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private CalculatorModel calculatorModel;

    private CalculatorViewModel calculatorViewModel;

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    private String getValueOnScreen() {
        return calculatorViewModel.getScreenLiveData().getValue();
    }

    @Test
    public void twoValuesEvaluationTest() {
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('9');
        Assert.assertEquals("19", getValueOnScreen());

        calculatorViewModel.onPlusButtonClicked();
        Assert.assertEquals("19", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('4');
        Assert.assertEquals("54", getValueOnScreen());

        Mockito.when(calculatorModel.getResult()).thenReturn(73L);
        calculatorViewModel.onEqualsButtonClicked();
        Assert.assertEquals("73", getValueOnScreen());
    }

    @Test
    public void manyValuesEvaluationTest() {
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('9');
        Assert.assertEquals("19", getValueOnScreen());

        calculatorViewModel.onPlusButtonClicked();
        Assert.assertEquals("19", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('4');
        Assert.assertEquals("54", getValueOnScreen());

        Mockito.when(calculatorModel.getResult()).thenReturn(73L);
        calculatorViewModel.onMinusButtonClicked();
        Assert.assertEquals("73", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        Assert.assertEquals("30", getValueOnScreen());

        Mockito.when(calculatorModel.getResult()).thenReturn(43L);
        calculatorViewModel.onPlusButtonClicked();
        Assert.assertEquals("43", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('2');
        Assert.assertEquals("302", getValueOnScreen());

        Mockito.when(calculatorModel.getResult()).thenReturn(345L);
        calculatorViewModel.onEqualsButtonClicked();
        Assert.assertEquals("345", getValueOnScreen());
    }

    @Test
    public void clearEvaluationTest() {
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('9');

        calculatorViewModel.onPlusButtonClicked();

        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('4');

        Mockito.when(calculatorModel.getResult()).thenReturn(73L);
        calculatorViewModel.onEqualsButtonClicked();
        Assert.assertEquals("73", getValueOnScreen());

        calculatorViewModel.onAcButtonClicked();
        Assert.assertEquals("", getValueOnScreen());
    }

    @Test
    public void leadingZeroTest() {
        Assert.assertEquals("", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('0');
        Assert.assertEquals("", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('7');
        Assert.assertEquals("7", getValueOnScreen());
    }

    @Test
    public void negativeEvaluationTest() {
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('8');

        Mockito.when(calculatorModel.getResult()).thenReturn(-58L);
        calculatorViewModel.onMinusButtonClicked();
        Assert.assertEquals("-58", getValueOnScreen());

        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onDigitButtonClicked('2');

        Mockito.when(calculatorModel.getResult()).thenReturn(-100L);
        calculatorViewModel.onEqualsButtonClicked();
        Assert.assertEquals("-100", getValueOnScreen());
    }
}

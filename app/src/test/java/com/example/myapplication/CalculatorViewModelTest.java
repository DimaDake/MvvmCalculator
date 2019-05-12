package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

public class CalculatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private CalculatorModel calculatorModel;

    private CalculatorViewModel calculatorViewModel;

    @Before
    public void setup() {
        calculatorModel = new CalculatorModel();
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
        calculatorViewModel.onMinusButtonClicked();
        Assert.assertEquals("73", getValueOnScreen());
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        Assert.assertEquals("30", getValueOnScreen());
        calculatorViewModel.onPlusButtonClicked();
        Assert.assertEquals("43", getValueOnScreen());
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('2');
        Assert.assertEquals("302", getValueOnScreen());
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
        calculatorViewModel.onMinusButtonClicked();
        Assert.assertEquals("-58", getValueOnScreen());
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onEqualsButtonClicked();
        Assert.assertEquals("-100", getValueOnScreen());
    }
}

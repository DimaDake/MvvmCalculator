package com.example.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplication.model.CalculatorModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private CalculatorViewModel calculatorViewModel;

    @Mock
    private CalculatorModel calculatorModel;

    @Before
    public void setUp() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    private void dialNumber(@NonNull final String str) {
        for (final char ch : str.toCharArray()) {
            calculatorViewModel.onDigitButtonClicked(ch);
        }
    }

    private void assertCheckState(
            @NonNull final String result,
            final boolean isCorrect) {
        assertEquals(result, calculatorViewModel.getScreenLiveData().getValue());
        assertEquals(isCorrect, calculatorViewModel.getCorrectExprLiveData().getValue());
    }

    private void clearExpression() {
        when(calculatorModel.getExpression()).thenReturn("");
        calculatorViewModel.onAcButtonClicked();
        assertCheckState("", true);
    }

    @Test
    public void dialExpressions() {
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("40");
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("10");
        calculatorViewModel.onPlusButtonClicked();
        when(calculatorModel.getExpression()).thenReturn("-40-10+6");
        dialNumber("6");
        assertCheckState("-40-10+6", true);

        clearExpression();

        // also checking operation replacement
        dialNumber("54");
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("3");
        calculatorViewModel.onMinusButtonClicked();
        when(calculatorModel.getExpression()).thenReturn("54+3-897");
        dialNumber("897");
        assertCheckState("54+3-897", true);

        clearExpression();

        dialNumber("0");
        when(calculatorModel.getExpression()).thenReturn("66");
        dialNumber("66");
        assertCheckState("66", true);

        clearExpression();

        dialNumber("0");
        calculatorViewModel.onMinusButtonClicked();
        when(calculatorModel.getExpression()).thenReturn("0-66");
        dialNumber("66");
        assertCheckState("0-66", true);
    }

    @Test
    public void calculateExpression() {

        // -40 - 10 + 6 = -44
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("40");
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("10");
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("6");
        when(calculatorModel.calculate()).thenReturn("-44");
        when(calculatorModel.isExpressionCorrect()).thenReturn(true);
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("-44", true);

        // -44 + 44 = 0
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("44");
        when(calculatorModel.calculate()).thenReturn("0");
        when(calculatorModel.isExpressionCorrect()).thenReturn(true);
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("0", true);

        // -5 = -5
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("5");
        when(calculatorModel.calculate()).thenReturn("-5");
        when(calculatorModel.isExpressionCorrect()).thenReturn(true);
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("-5", true);

        // +15 = 15
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("15");
        when(calculatorModel.calculate()).thenReturn("15");
        when(calculatorModel.isExpressionCorrect()).thenReturn(true);
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("15", true);

        // 20 = 20
        calculatorViewModel.onAcButtonClicked();
        dialNumber("20");
        when(calculatorModel.calculate()).thenReturn("20");
        when(calculatorModel.isExpressionCorrect()).thenReturn(true);
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("20", true);
    }

    @Test
    public void calculateIncorrectExpressions() {

        // "-" is incorrect expression
        calculatorViewModel.onMinusButtonClicked();
        when(calculatorModel.calculate()).thenReturn("-");
        when(calculatorModel.isExpressionCorrect()).thenReturn(false);
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("-", false);
    }
}
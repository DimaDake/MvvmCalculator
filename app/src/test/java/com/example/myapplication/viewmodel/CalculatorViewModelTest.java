package com.example.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplication.model.evaluators.AddSubEvaluator;
import com.example.myapplication.model.expressions.AddSubExpression;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

public class CalculatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private CalculatorViewModel calculatorViewModel;

    @Before
    public void setUp() {
        calculatorViewModel = new CalculatorViewModel(
                new AddSubExpression(), new AddSubEvaluator());
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
        dialNumber("6");
        assertCheckState("-40-10+6", true);

        clearExpression();

        // also checking operation replacement
        dialNumber("54");
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("3");
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("897");
        assertCheckState("54+3-897", true);

        clearExpression();

        dialNumber("0");
        dialNumber("66");
        assertCheckState("66", true);

        clearExpression();

        dialNumber("0");
        calculatorViewModel.onMinusButtonClicked();
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
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("-44", true);

        // -44 + 44 = 0
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("44");
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("0", true);

        // -5 = -5
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onMinusButtonClicked();
        dialNumber("5");
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("-5", true);

        // +15 = 15
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onPlusButtonClicked();
        dialNumber("15");
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("15", true);

        // 20 = 20
        calculatorViewModel.onAcButtonClicked();
        dialNumber("20");
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("20", true);
    }

    @Test
    public void calculateIncorrectExpressions() {

        // "-" is incorrect expression
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onEqualsButtonClicked();
        assertCheckState("-", false);
    }
}
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

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {
    private final Random random = new Random();
    private CalculatorViewModel calculatorViewModel;

    @Mock
    private CalculatorModel calculatorModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    @Test
    public void inputTest() {
        calculatorViewModel.onDigitButtonClicked('7');
        checkValueOnScreen(7);
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('2');
        checkValueOnScreen(12);
        calculatorViewModel.onAcButtonClicked();

        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('9');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('5');
        checkValueOnScreen(23905);
        calculatorViewModel.onAcButtonClicked();
        checkValueOnScreen(0);
    }

    @Test
    public void overflowTest() {
        long result = Integer.MAX_VALUE;
        for (char digit : Long.toString(result).toCharArray()) {
            calculatorViewModel.onDigitButtonClicked(digit);
        }
        calculatorViewModel.onPlusButtonClicked();
        for (char digit : Long.toString(result).toCharArray()) {
            calculatorViewModel.onDigitButtonClicked(digit);
        }
        result += Integer.MAX_VALUE;
        Mockito.when(calculatorModel.getValue()).thenReturn(result);
        calculatorViewModel.onEqualsButtonClicked();
        checkValueOnScreen(result);
    }

    @Test
    public void underflowTest() {
        long result = Integer.MIN_VALUE;
        for (char digit : Long.toString(result).toCharArray()) {
            calculatorViewModel.onDigitButtonClicked(digit);
        }
        calculatorViewModel.onMinusButtonClicked();
        long maxValue = Integer.MAX_VALUE;
        for (char digit : Long.toString(maxValue).toCharArray()) {
            calculatorViewModel.onDigitButtonClicked(digit);
        }
        result -= maxValue;
        Mockito.when(calculatorModel.getValue()).thenReturn(result);
        calculatorViewModel.onEqualsButtonClicked();
        checkValueOnScreen(result);
    }

    @Test
    public void addRandomTest() {
        for (int i = 0; i < 100; i++) {
            int result = random.nextInt(10000);
            for (char digit : Integer.toString(result).toCharArray()) {
                calculatorViewModel.onDigitButtonClicked(digit);
            }
            calculatorViewModel.onPlusButtonClicked();
            int value = random.nextInt(10000);
            for (char digit : Integer.toString(value).toCharArray()) {
                calculatorViewModel.onDigitButtonClicked(digit);
            }
            result += value;
            Mockito.when(calculatorModel.getValue()).thenReturn((long) result);
            calculatorViewModel.onEqualsButtonClicked();
            checkValueOnScreen(result);
            calculatorViewModel.onAcButtonClicked();
        }
    }

    @Test
    public void minusRandomTest() {
        for (int i = 0; i < 100; i++) {
            int result = random.nextInt(10000);
            for (char digit : Integer.toString(result).toCharArray()) {
                calculatorViewModel.onDigitButtonClicked(digit);
            }
            calculatorViewModel.onMinusButtonClicked();
            int value = random.nextInt(10000);
            for (char digit : Integer.toString(value).toCharArray()) {
                calculatorViewModel.onDigitButtonClicked(digit);
            }
            result -= value;
            Mockito.when(calculatorModel.getValue()).thenReturn((long) result);
            calculatorViewModel.onEqualsButtonClicked();
            checkValueOnScreen(result);
            calculatorViewModel.onAcButtonClicked();
        }
    }

    private void checkValueOnScreen(final long value) {
        final String actualValue = calculatorViewModel.getScreenLiveData().getValue();
        assertNotNull(actualValue);
        assertEquals(value, Long.parseLong(actualValue));
    }
}

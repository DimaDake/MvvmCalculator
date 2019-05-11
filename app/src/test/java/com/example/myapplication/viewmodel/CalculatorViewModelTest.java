package com.example.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplication.model.Calculator;
import com.example.myapplication.model.Operation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    Calculator calculator;

    private CalculatorViewModel viewModel;
    private Random random;

    @Before
    public void init() {
        viewModel = new CalculatorViewModel(calculator);
        random = new Random();
    }

    private void checkOperation(final Operation operation) {
        assertEquals(operation, viewModel.getOperation());
    }

    private void checkValueOnScreen(final long value) {
        final String actualValue = viewModel.getScreenLiveData().getValue();
        assertNotNull(actualValue);
        assertEquals(value, Long.parseLong(actualValue));
    }

    @Test
    public void testClear() {
        viewModel.onDigitButtonClicked('9');
        viewModel.onDigitButtonClicked('1');
        viewModel.onDigitButtonClicked('2');
        viewModel.onDigitButtonClicked('4');
        checkValueOnScreen(9124);
        viewModel.onAcButtonClicked();

        when(calculator.getValue()).thenReturn(0L);
        checkValueOnScreen(0);
    }


    //77 + 3 - 4 + 23 = 99
    @Test
    public void testExpression() {
        viewModel.onDigitButtonClicked('7');
        viewModel.onDigitButtonClicked('7');
        checkValueOnScreen(77);
        viewModel.onPlusButtonClicked();
        checkOperation(Operation.PLUS);

        viewModel.onDigitButtonClicked('3');
        checkValueOnScreen(3);
        when(calculator.getValue()).thenReturn(80L);
        viewModel.onMinusButtonClicked();
        checkOperation(Operation.MINUS);
        checkValueOnScreen(80);

        viewModel.onDigitButtonClicked('4');
        checkValueOnScreen(4);
        when(calculator.getValue()).thenReturn(76L);
        viewModel.onPlusButtonClicked();
        checkOperation(Operation.PLUS);
        checkValueOnScreen(76);


        viewModel.onDigitButtonClicked('2');
        viewModel.onDigitButtonClicked('3');
        checkValueOnScreen(23);
        when(calculator.getValue()).thenReturn(99L);
        viewModel.onEqualsButtonClicked();
        checkValueOnScreen(99);
    }

    // Integer.MAX_VALUE + Integer.MAX_VALUE
    @Test
    public void testOverIntExpression() {
        final String value = Integer.toString(Integer.MAX_VALUE);
        for (int i = 0; i < value.length(); i++) {
            viewModel.onDigitButtonClicked(value.charAt(i));
        }
        checkValueOnScreen(Integer.MAX_VALUE);

        viewModel.onPlusButtonClicked();
        checkOperation(Operation.PLUS);

        for (int i = 0; i < value.length(); i++) {
            viewModel.onDigitButtonClicked(value.charAt(i));
        }
        checkValueOnScreen(Integer.MAX_VALUE);
        when(calculator.getValue()).thenReturn((long) Integer.MAX_VALUE + Integer.MAX_VALUE);
        viewModel.onEqualsButtonClicked();
        checkValueOnScreen((long) Integer.MAX_VALUE + Integer.MAX_VALUE);
    }


    @Test
    public void randomTest() {
        long value = random.nextInt(1000);
        for (char digit : Long.toString(value).toCharArray()) {
            viewModel.onDigitButtonClicked(digit);
        }
        checkValueOnScreen(value);
        viewModel.onPlusButtonClicked();
        checkOperation(Operation.PLUS);

        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(1000);
            for (char digit : Integer.toString(x).toCharArray()) {
                viewModel.onDigitButtonClicked(digit);
            }

            if (random.nextBoolean()) {
                value += x;
                when(calculator.getValue()).thenReturn(value);
                viewModel.onPlusButtonClicked();
                checkOperation(Operation.PLUS);
            } else {
                value -= x;
                when(calculator.getValue()).thenReturn(value);
                viewModel.onMinusButtonClicked();
                checkOperation(Operation.MINUS);
            }
            when(calculator.getValue()).thenReturn(value);
            checkValueOnScreen(value);
        }

        when(calculator.getValue()).thenReturn(value);
        viewModel.onEqualsButtonClicked();
        checkValueOnScreen(value);
    }
}
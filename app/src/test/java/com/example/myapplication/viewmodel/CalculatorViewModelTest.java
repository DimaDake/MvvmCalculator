package com.example.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplication.model.Calculator;
import com.example.myapplication.model.Operation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class CalculatorViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    Calculator calculator;

    private CalculatorViewModel viewModel;

    @Before
    public void init() {
        calculator = new Calculator();
        viewModel = new CalculatorViewModel(calculator);
    }

    private void checkOperation(final Operation operation) {
        assertEquals(operation, viewModel.getOperation());
    }

    private void checkValueOnScreen(final long value) {
        final String actualValue = viewModel.getScreenLiveData().getValue();
        assertNotNull(actualValue);
        assertEquals(Long.parseLong(actualValue), value);
    }

    @Test
    public void testClear() {
        viewModel.onDigitButtonClicked('9');
        viewModel.onDigitButtonClicked('1');
        viewModel.onDigitButtonClicked('2');
        viewModel.onDigitButtonClicked('4');
        verify(calculator).setValue(9124);
        checkValueOnScreen(9124);

        viewModel.onAcButtonClicked();

        verify(calculator).setValue(0);
        checkValueOnScreen(0);
    }


    //77 + 3 - 4 + 23 = 99
    @Test
    public void testExpression() {
        viewModel.onDigitButtonClicked('7');
        viewModel.onDigitButtonClicked('7');
        verify(calculator).setValue(77);
        checkValueOnScreen(77);
        viewModel.onPlusButtonClicked();
        checkOperation(Operation.PLUS);

        viewModel.onDigitButtonClicked('3');
        checkValueOnScreen(3);
        viewModel.onMinusButtonClicked();
        verify(calculator).add(3);
        checkOperation(Operation.MINUS);
        checkValueOnScreen(80);

        viewModel.onDigitButtonClicked('4');
        checkValueOnScreen(4);
        viewModel.onPlusButtonClicked();
        verify(calculator).minus(4);
        checkOperation(Operation.PLUS);


        viewModel.onDigitButtonClicked('2');
        viewModel.onDigitButtonClicked('3');
        checkValueOnScreen(23);
        viewModel.onEqualsButtonClicked();
        verify(calculator).add(23);
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
        viewModel.onEqualsButtonClicked();
        checkValueOnScreen((long) Integer.MAX_VALUE + Integer.MAX_VALUE);
    }

}
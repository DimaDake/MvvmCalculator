package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

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
    private CalculatorViewModel viewModel;
    private LiveData<String> answer;

    @Rule
    public final TestRule rule = new InstantTaskExecutorRule();

    @Mock
    CalculatorModel model;

    @Before
    public void setup() {
        model.clear();
        viewModel = new CalculatorViewModel(model);
        answer = viewModel.getScreenLiveData();
    }

    @Test
    public void testInput() {
        assertEquals("0", answer.getValue());

        viewModel.onDigitButtonClicked('4');
        viewModel.onDigitButtonClicked('2');
        assertEquals("42", answer.getValue());
    }

    @Test
    public void testPlus() {
        viewModel.onDigitButtonClicked('4');
        viewModel.onDigitButtonClicked('2');
        viewModel.onPlusButtonClicked();
        viewModel.onDigitButtonClicked('2');

        when(model.getValue()).thenReturn(44L);
        viewModel.onEqualsButtonClicked();

        assertEquals("44", answer.getValue());
    }

    @Test
    public void testMinus() {
        viewModel.onDigitButtonClicked('4');
        viewModel.onDigitButtonClicked('2');
        viewModel.onMinusButtonClicked();
        viewModel.onDigitButtonClicked('2');

        when(model.getValue()).thenReturn(40L);
        viewModel.onEqualsButtonClicked();

        assertEquals("40", answer.getValue());
    }

    @Test
    public void testPlusAndMinus() {
        viewModel.onDigitButtonClicked('4');
        viewModel.onDigitButtonClicked('2');
        viewModel.onMinusButtonClicked();
        viewModel.onDigitButtonClicked('2');

        when(model.getValue()).thenReturn(40L);
        viewModel.onPlusButtonClicked();

        viewModel.onDigitButtonClicked('6');
        when(model.getValue()).thenReturn(46L);
        viewModel.onEqualsButtonClicked();
        assertEquals("46", answer.getValue());
    }

    @Test
    public void correctAllClean() {
        viewModel.onDigitButtonClicked('4');
        assertEquals("4", answer.getValue());

        viewModel.onAcButtonClicked();
        assertEquals("0", answer.getValue());
    }
}
package com.example.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.example.myapplication.CalculatorModel;
import com.example.myapplication.CalculatorViewModel;

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
    private CalculatorViewModel calculatorViewModel;

    @Rule
    public final TestRule rule = new InstantTaskExecutorRule();

    @Mock
    CalculatorModel calculatorModel;

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    @Test
    public void correctScreenValue() {
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('3');
        final LiveData<String> screenLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("123", screenLiveData.getValue());
    }

    // 123 + 2 = 125
    @Test
    public void correctPlusOperation() {
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('2');
        when(calculatorModel.getValue()).thenReturn(125);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("125", stringLiveData.getValue());
    }

    // 125 - 100 = 25
    @Test
    public void correctMinusOperation() {
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('0');
        calculatorViewModel.onDigitButtonClicked('0');
        when(calculatorModel.getValue()).thenReturn(25);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("25", stringLiveData.getValue());
    }

    // 0
    @Test
    public void correctAllClean(){
        calculatorViewModel.onAcButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("0", stringLiveData.getValue());
    }

    // 4 + 4 - 3 = 5
    @Test
    public void moreOperation(){
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('4');
        when(calculatorModel.getValue()).thenReturn(8);
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        when(calculatorModel.getValue()).thenReturn(5);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("5", stringLiveData.getValue());
    }
}

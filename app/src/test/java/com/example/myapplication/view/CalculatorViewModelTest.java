package com.example.myapplication.view;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.example.myapplication.CalculatorModel;
import com.example.myapplication.CalculatorViewModel;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {

    private CalculatorViewModel calculatorViewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    CalculatorModel calculatorModel;

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    @Test
    public void checkValueOnScreen() {
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('0');
        final LiveData<String> valueOnScreen = calculatorViewModel.getScreenLiveData();
        assertEquals("120", valueOnScreen.getValue());
    }

    @Test
    public void checkOperationPlus() {
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        when(calculatorModel.getValue()).thenReturn(125);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> valueOnScreen = calculatorViewModel.getScreenLiveData();
        assertEquals("125", valueOnScreen.getValue());
    }

    @Test
    public void checkOperationMinus() {
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        when(calculatorModel.getValue()).thenReturn(120);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> valueOnScreen = calculatorViewModel.getScreenLiveData();
        assertEquals("120", valueOnScreen.getValue());
    }

    @Test
    public void checkOperationClean() {
        calculatorViewModel.onAcButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("0", stringLiveData.getValue());

    }

    @Test
    public void checkManyOperations() {
        calculatorViewModel.onDigitButtonClicked('7');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('9');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        when(calculatorModel.getValue()).thenReturn(17);
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        when(calculatorModel.getValue()).thenReturn(10);
        calculatorViewModel.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calculatorViewModel.getScreenLiveData();
        assertEquals("10", stringLiveData.getValue());
    }
}

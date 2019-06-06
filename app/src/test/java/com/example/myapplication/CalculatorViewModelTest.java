package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewModelTest {
    private CalculatorViewModel calculatorViewModel ;

    @Rule
    public final TestRule rule = new InstantTaskExecutorRule();

    @Mock
    CalculatorModel calculatorModel;

    @Before
    public void setup() {
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
    }

    private String getDisplayOnScreen() {
        String str = calculatorViewModel.getScreenLiveData().getValue();
        return str;
    }

    @Test
    public void testOnClickedDigit() {
        for (int i=9;i>=0;i--){
            calculatorViewModel.onDigitButtonClicked(Integer.toString(i).charAt(0));
        }
        assertEquals("9876543210", getDisplayOnScreen());
    }

    @Test
    public void testOnClickedAC(){
        calculatorViewModel.onAcButtonClicked();
        assertEquals("0",getDisplayOnScreen() );
    }

    @Test
    public void testOnClickedPlus(){
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('9');
        calculatorViewModel.onDigitButtonClicked('8');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('4');
        Mockito.when(calculatorModel.getValue()).thenReturn(102L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("102", getDisplayOnScreen());
    }

    @Test
    public void testOnClickedMinus(){
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('5');
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('8');
        Mockito.when(calculatorModel.getValue()).thenReturn(45L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("45", getDisplayOnScreen());
    }

    @Test
    public void testOnClickedPlusAndMinusandEquals(){
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('9');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        Mockito.when(calculatorModel.getValue()).thenReturn(26L);
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onDigitButtonClicked('1');
        Mockito.when(calculatorModel.getValue()).thenReturn(367L);
        calculatorViewModel.onEqualsButtonClicked();
        assertEquals("367", getDisplayOnScreen());
    }
}
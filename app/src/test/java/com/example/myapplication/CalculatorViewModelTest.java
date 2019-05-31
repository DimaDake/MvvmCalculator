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
    private CalculatorViewModel calcVM;

    @Rule
    public final TestRule rule = new InstantTaskExecutorRule();

    @Mock
    CalculatorModel calcModel;

    @Before
    public void setup() {
        calcVM = new CalculatorViewModel(calcModel);
    }

    @Test
    public void correctScreenValue() {
        calcVM.onDigitButtonClicked('7');
        calcVM.onDigitButtonClicked('7');
        calcVM.onDigitButtonClicked('7');
        final LiveData<String> screenLiveData = calcVM.getScreenLiveData();
        assertEquals("777", screenLiveData.getValue());
    }

    @Test
    public void correctPlusOperation() {
        calcVM.onPlusButtonClicked();
        calcVM.onDigitButtonClicked('3');
        when(calcModel.getValue()).thenReturn(780);
        calcVM.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calcVM.getScreenLiveData();
        assertEquals("780", stringLiveData.getValue());
    }

    @Test
    public void correctMinusOperation() {
        calcVM.onMinusButtonClicked();
        calcVM.onDigitButtonClicked('7');
        calcVM.onDigitButtonClicked('7');
        calcVM.onDigitButtonClicked('7');
        when(calcModel.getValue()).thenReturn(3);
        calcVM.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calcVM.getScreenLiveData();
        assertEquals("3", stringLiveData.getValue());
    }

    @Test
    public void correctAllClean(){
        calcVM.onAcButtonClicked();
        final LiveData<String> stringLiveData = calcVM.getScreenLiveData();
        assertEquals("0", stringLiveData.getValue());
    }

    @Test
    public void moreOperation(){
        calcVM.onDigitButtonClicked('4');
        calcVM.onPlusButtonClicked();
        calcVM.onDigitButtonClicked('8');
        when(calcModel.getValue()).thenReturn(8);
        calcVM.onMinusButtonClicked();
        calcVM.onDigitButtonClicked('1');
        calcVM.onDigitButtonClicked('6');
        when(calcModel.getValue()).thenReturn(-4);
        calcVM.onEqualsButtonClicked();
        final LiveData<String> stringLiveData = calcVM.getScreenLiveData();
        assertEquals("-4", stringLiveData.getValue());
    }
}
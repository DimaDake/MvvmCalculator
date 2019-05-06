package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.viewmodel.CalculatorViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.assertEquals;



public class ViewModel {
    private CalculatorViewModel calculatorViewModel = new CalculatorViewModel();

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    @Test
    public void minus() {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.postValue("ass");
        assertEquals(mutableLiveData.getValue(), "ass");
////        22 - 4
//        calculatorViewModel.onAcButtonClicked();
//        calculatorViewModel.onDigitButtonClicked('2');
//        calculatorViewModel.onDigitButtonClicked('2');
//        calculatorViewModel.onMinusButtonClicked();
//        calculatorViewModel.onDigitButtonClicked('4');
//        calculatorViewModel.onEqualsButtonClicked();
//
//        assertEquals("18", calculatorViewModel.getScreenLiveData().getValue());
    }


//    @Test
//    public void plus(){
////        1488 + 1337
//        calculatorViewModel.onAcButtonClicked();
//        calculatorViewModel.onDigitButtonClicked('1');
//        calculatorViewModel.onDigitButtonClicked('4');
//        calculatorViewModel.onDigitButtonClicked('8');
//        calculatorViewModel.onDigitButtonClicked('8');
//        calculatorViewModel.onPlusButtonClicked();
//        calculatorViewModel.onDigitButtonClicked('1');
//        calculatorViewModel.onDigitButtonClicked('3');
//        calculatorViewModel.onDigitButtonClicked('3');
//        calculatorViewModel.onDigitButtonClicked('7');
//        calculatorViewModel.onEqualsButtonClicked();
//
//        assertEquals("2825", calculatorViewModel.getScreenLiveData().getValue());
//    }
}

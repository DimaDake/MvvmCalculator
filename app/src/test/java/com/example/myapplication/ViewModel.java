package com.example.myapplication;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplication.model.CalculatorModel;
import com.example.myapplication.viewmodel.CalculatorViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ViewModel {

    @Mock
    CalculatorModel calculatorModel;

    private CalculatorViewModel calculatorViewModel;
    private Random random;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void before(){
        calculatorViewModel = new CalculatorViewModel(calculatorModel);
        calculatorViewModel = new CalculatorViewModel();
        random = new Random(88005553535L);
    }

    @Test
    public void minus() {
////        22 - 4
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onDigitButtonClicked('2');
        calculatorViewModel.onMinusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onEqualsButtonClicked();

        assertEquals("18", calculatorViewModel.getScreenLiveData().getValue());
    }

    @Test
    public void plus(){
//        1488 + 1337
        calculatorViewModel.onAcButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('4');
        calculatorViewModel.onDigitButtonClicked('8');
        calculatorViewModel.onDigitButtonClicked('8');
        calculatorViewModel.onPlusButtonClicked();
        calculatorViewModel.onDigitButtonClicked('1');
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('3');
        calculatorViewModel.onDigitButtonClicked('7');
        calculatorViewModel.onEqualsButtonClicked();

        assertEquals("2825", calculatorViewModel.getScreenLiveData().getValue());
    }

    @Test
    public void randomTest(){
        for (int i = 0; i < 100; i++){
            calculatorViewModel.onAcButtonClicked();
            int firstArg = random.nextInt();
            int secondArg = random.nextInt();
            boolean isPlusClicked = random.nextBoolean();
            int equals = isPlusClicked ? firstArg + secondArg : firstArg - secondArg;

            // input first argument
            for (char number: Integer.toString(firstArg).toCharArray()){
                calculatorViewModel.onDigitButtonClicked(number);
            }

            // input plus/minus
            if (isPlusClicked){
                calculatorViewModel.onPlusButtonClicked();
            } else {
                calculatorViewModel.onMinusButtonClicked();
            }

            // input second argument
            for (char number: Integer.toString(secondArg).toCharArray()){
                calculatorViewModel.onDigitButtonClicked(number);
            }

            calculatorViewModel.onEqualsButtonClicked();
            assertEquals(Integer.toString(equals), calculatorViewModel.getScreenLiveData().getValue());
        }
    }
}

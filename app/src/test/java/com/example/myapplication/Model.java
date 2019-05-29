package com.example.myapplication;

import com.example.myapplication.model.CalculatorModel;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Model {
    private CalculatorModel calculatorModel = new CalculatorModel();
    private Random random = new Random(88005553535L);

    @Test
    public void sum1(){
        calculatorModel.setData(2);
        calculatorModel.setOperation(CalculatorModel.Operation.PLUS);
        int result = calculatorModel.doOperation(2);
        assertEquals(4, result);
    }
    @Test
    public void sum_with_negative(){
        calculatorModel.setData(-1488);
        assertEquals(1337 - 1488,calculatorModel.add(1337));
    }
    @Test
    public void subtract1(){
        calculatorModel.setData(123);
        calculatorModel.setOperation(CalculatorModel.Operation.MINUS);
        int result = calculatorModel.doOperation(321);
        assertEquals(123-321, result);
    }
    @Test
    public void subtract_with_negative(){
        calculatorModel.setData(227);
        assertEquals(227 + 1488, calculatorModel.subtract(-1488));
    }
    @Test
    public void randomTest(){
        for (int i = 0; i < 100; i++){
            int firstArg = random.nextInt();
            int secondArg = random.nextInt();
            boolean isPlus = random.nextBoolean();
            int equals = isPlus ? firstArg + secondArg : firstArg - secondArg;

            calculatorModel.setData(firstArg);
            if (isPlus){
                calculatorModel.setOperation(CalculatorModel.Operation.PLUS);
            } else {
                calculatorModel.setOperation(CalculatorModel.Operation.MINUS);
            }

            int result = calculatorModel.doOperation(secondArg);
            assertEquals(equals, result);
        }
    }
}

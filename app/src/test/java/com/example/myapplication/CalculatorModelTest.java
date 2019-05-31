package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {

    @Test
    public void correctGetSetValue() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        assertEquals(0, calculatorModel.getValue());
        final int value = 13;
        calculatorModel.setValue(value);
        assertEquals(value, calculatorModel.getValue());
    }

    @Test
    public void correctPlus() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value1 = 13;
        calculatorModel.setValue(value1);
        final int value2 = 33;
        final int result = value1 + value2;
        calculatorModel.addition(value2);
        assertEquals(result, calculatorModel.getValue());
    }

    @Test
    public void correctMinus() {
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value = 13;
        final int result = -value;
        calculatorModel.subtraction(value);
        assertEquals(result, calculatorModel.getValue());
    }

    @Test
    public void correctAllClean(){
        final CalculatorModel calculatorModel = new CalculatorModel();
        final int value = 10;
        calculatorModel.setValue(value);
        calculatorModel.addition(value);
        calculatorModel.clean();
        assertEquals(0,calculatorModel.getValue());

    }
}
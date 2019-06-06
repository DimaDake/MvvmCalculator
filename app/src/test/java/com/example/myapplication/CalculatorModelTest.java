package com.example.myapplication;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorModelTest {
    private final CalculatorModel calculatorModel = new CalculatorModel();
    private final long number1=345;
    private final long number2=967;

    @Test
    public void testSetValueAndGetValue() {
        calculatorModel.setValue(number1);
        assertEquals(number1, calculatorModel.getValue());
    }

    @Test
    public void testPlus() {
        calculatorModel.setValue(number1);
        calculatorModel.plus(number2);
        assertEquals((number1+number2), calculatorModel.getValue());
    }

    @Test
    public void testMinus() {
        calculatorModel.setValue(number1);
        calculatorModel.minus(number2);
        assertEquals((number1-number2), calculatorModel.getValue());
    }
}